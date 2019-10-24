package indi.daniel.fling.r;


//import edu.cornell.med.icb.R.script.RDataObjectType;
//import edu.cornell.med.icb.R.script.RScript;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;

public class RUtilsDemo {
    public static void main(String[] args) throws REXPMismatchException, REngineException {

        //TODO create R server pool with RUtils
//        final String ksTest = "q <- ks.test(x,y)\n"
//                + "p_value <- q$p.value\n"
//                + "test_statistic <- q$statistic[[1]]";
//        final RScript rscript = RScript.createFromScriptString(ksTest);
//        final double[] xValues = new double[] {0.1, 0.2, 0.3, 0.4, 0.5};
//        final double[] yValues = new double[] {0.6, 0.7, 0.8, 0.9, 1.0};
//        // 为脚本指定输入变量的名字和值
//        rscript.setInput("x", xValues);
//        rscript.setInput("y", yValues);
//        // 为脚本输出指定变量名和类型, 输出必须在脚本执行前指定
//        rscript.setOutput("p_value", RDataObjectType.Double);
//        rscript.setOutput("test_statistic", RDataObjectType.Double);
//        rscript.execute();
//        final double pvalue = rscript.getOutputDouble("p_value");
//        final double testStat = rscript.getOutputDouble("test_statistic");
//        System.out.println("hello world");
    }
}
