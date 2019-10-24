package indi.daniel.fling.r.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataMeta {
    private String[] rowNames;
    private String[] columnNames;
}
