package indi.daniel.fling.r;

import indi.daniel.fling.r.data.DataMeta;
import indi.daniel.fling.r.data.MatrixData;
import indi.daniel.fling.r.rlang.DataConverter;
import indi.daniel.fling.r.rlang.GreatRConnection;
import indi.daniel.fling.r.rlang.RLangTryErrorException;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPDouble;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo {
    public static void main(String[] args) throws REngineException, REXPMismatchException {
        testGrammar();
//        testError();
//
//        StringWriter sw = new StringWriter();
//        PrintWriter pw = new PrintWriter(sw);
//        testSingleValue(pw);
//        testMatrix(pw);
//        System.out.println(sw.toString());
//
//        testMultiThread();
    }

    public static void testGrammar() throws REngineException, REXPMismatchException {
        GreatRConnection rc = new GreatRConnection(new RConnection());
        //单值的加法运算
        String script ="result=\"\"\n" +
                        "v <- LETTERS[1:4]\n" +
                        "for ( i in v) {\n" +
                        "   print(i)\n" +
                        "   print(value){\n" +
                        "   result=paste(result,i)\n" +
                        "}\n" +
                        "return(result)\n";
        try {
            System.out.println(rc.tryParseAndEval2(script).asString());
        } catch (RLangTryErrorException e) {
            System.out.println("成功捕获到异常：" + e.getErrorMessage());
        }

    }

    public static void testMultiThread() {
        int threadCount = 10;
        AtomicInteger failedCount = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        CountDownLatch printCountDownLatch = new CountDownLatch(threadCount);
        do {
            Thread thread = new Thread(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + "就绪");
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    System.err.println(threadName + e);
                    failedCount.incrementAndGet();
                    printCountDownLatch.countDown();
                    return;
                }
                try {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
//                    testSingleValue(pw);
                    testMatrix(pw);
                } catch (Exception e) {
                    System.err.println(threadName + "  " + e);
                    failedCount.incrementAndGet();
                    printCountDownLatch.countDown();
                    return;
                }
                System.out.println(threadName + " 成功！");
                printCountDownLatch.countDown();
            });
            thread.start();
            countDownLatch.countDown();
        } while (countDownLatch.getCount() != 0L);

        try {
            printCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("失败个数：" + failedCount);

    }


    public static void testError() throws REngineException, REXPMismatchException {

        GreatRConnection rc = new GreatRConnection(new RConnection());

        rc.assign("value1", new REXPDouble(1D));
        rc.assign("value2", new REXPDouble(2D));

        //单值的加法运算
        String script = "value3 = value1 + value2\n" +
                "value4 = value2 * value3\n" +
                "value4; value5";
        try {
            rc.tryParseAndEval(script);
        } catch (RLangTryErrorException e) {
            System.out.println("成功捕获到异常：" + e.getErrorMessage());
            return;
        }
        assert false;
    }

    public static void testSingleValue(PrintWriter ps) throws REngineException, REXPMismatchException {
        RConnection rc = new RConnection();
        //相当于
        // value1 <- 1D
        // value2 <- 2D
        rc.assign("value1", new REXPDouble(1D));
        rc.assign("value2", new REXPDouble(2D));

        //单值的加法运算
        String script = "value1 + value2";
        REXP rexp = rc.eval(script);

        assert rexp.asDouble() == 3D;
        ps.println("单值测试成功");
        ps.println();

        rc.close();
    }

    public static void testMatrix(PrintWriter ps) throws REngineException, REXPMismatchException {

        //构建java中的矩阵数据
        String[] rowNames = {"行1", "行2", "行3"};
        String[] columnNames = {"列1", "列2", "列3"};
        double[][] data = {{1D, 2D, 3D}, {4D, 5D, 6D}, {7D, 8D, 9D}};
        MatrixData javaMatrixData = new MatrixData(new DataMeta(rowNames, columnNames), data);
        ps.println("原矩阵：" + javaMatrixData);
        ps.println();

        //将java的矩阵数据转换为R语言格式
        REXPDouble rMatrix = DataConverter.getRMatrix(javaMatrixData);

        RConnection rc = new RConnection();
        rc.assign("matrix1", rMatrix);

        //执行矩阵相加，自己加自己
        String script = "matrix1 + matrix1";
        MatrixData result = DataConverter.getJavaMatrix((REXPDouble) rc.eval(script));
        ps.println("矩阵加：" + result);
        ps.println();

        //执行矩阵相加，自己点乘自己
        script = "matrix1 * matrix1";
        result = DataConverter.getJavaMatrix((REXPDouble) rc.eval(script));
        ps.println("矩阵点乘：" + result);
        ps.println();

        //执行矩阵相加，自己乘自己
        script = "matrix1 %*% matrix1";
        result = DataConverter.getJavaMatrix((REXPDouble) rc.eval(script));
        ps.println("矩阵乘：" + result);
        ps.println();
        rc.close();
    }


}
