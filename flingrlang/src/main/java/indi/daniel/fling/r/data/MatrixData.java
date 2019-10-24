package indi.daniel.fling.r.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class MatrixData {
    private DataMeta dataMeta;
    private double[][] data;
}
