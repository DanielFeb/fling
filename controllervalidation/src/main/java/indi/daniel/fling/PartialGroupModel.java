package indi.daniel.fling;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PartialGroupModel {
    public interface GroupA{

    }

    @NotNull(groups = GroupA.class)
    private String a;
    @NotNull
    private String b;
}
