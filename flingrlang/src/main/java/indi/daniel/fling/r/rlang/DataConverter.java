package indi.daniel.fling.r.rlang;

import indi.daniel.fling.r.data.DataMeta;
import indi.daniel.fling.r.data.MatrixData;
import org.rosuda.REngine.*;

public class DataConverter {
    public static MatrixData getJavaMatrix(REXPDouble rexpDouble) throws REXPMismatchException {
        int[] dim = rexpDouble.getAttribute("dim").asIntegers();
        double[][] matrix = vector2Matrix(dim, rexpDouble.asDoubles());
        RList dimNames = rexpDouble.getAttribute("dimnames").asList();
        assert dimNames.size() == 2;
        String[] rowNames = ((REXPString) dimNames.get(0)).asStrings();
        String[] columnNames = ((REXPString) dimNames.get(1)).asStrings();
        return new MatrixData(new DataMeta(rowNames, columnNames), matrix);
    }

    public static REXPDouble getRMatrix(MatrixData javaMatrixData) {

        double[][] matrix = javaMatrixData.getData();

        assert matrix != null && matrix.length != 0;

        RList attr = new RList(2, true);
        attr.put("dim", new REXPInteger(new int[] {matrix.length, matrix[0].length}));
        RList dimNames = new RList();
        dimNames.add(new REXPString(javaMatrixData.getDataMeta().getRowNames()));
        dimNames.add(new REXPString(javaMatrixData.getDataMeta().getColumnNames()));
        attr.put("dimnames", new REXPGenericVector(dimNames));

        return new REXPDouble(
                matrix2Vector(matrix),
                new REXPList(attr));
    }

    public static double[] matrix2Vector(double[][] matrix) {
        assert matrix != null && matrix.length != 0;
        int rowCount = matrix.length;
        int columnCount = matrix[0].length;
        double[] result = new double[rowCount * columnCount];
        for (int i = 0; i < rowCount; i++) {
            assert matrix[i].length == columnCount;
            System.arraycopy(matrix[i], 0, result, i*columnCount, columnCount);
        }
        return result;
    }

    public static double[][] vector2Matrix(int[] dim, double[] vector) {
        assert dim.length == 2 && vector.length != 0;
        int rowCount = dim[0];
        int columnCount = dim[1];
        double[][] result = new double[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            System.arraycopy(vector, i*columnCount, result[i], 0, columnCount);
        }
        return result;
    }
}
