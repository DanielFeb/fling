package indi.daniel.fling.springboot.datasource.jpa.dataconverter;

import javax.persistence.AttributeConverter;

/**
 * Created by daniel on 2018/12/20.
 */
public class SexConverter implements AttributeConverter<SexEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SexEnum sex) {
        return sex.getId();
    }

    @Override
    public SexEnum convertToEntityAttribute(Integer integer) {
        return SexEnum.getEmumId(integer);
    }
}
