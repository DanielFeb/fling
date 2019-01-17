package indi.daniel.fling.springboot.datasource.mybatis.dataconverter;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import javax.persistence.AttributeConverter;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by daniel on 2018/12/20.
 */
public class SexTypeHandler extends BaseTypeHandler<SexEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, SexEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getId());
    }

    @Override
    public SexEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int sex = rs.getInt(columnName);
        if (sex != SexEnum.MALE.getId() && sex != SexEnum.FEMALE.getId()) {
            return null;
        }
        return SexEnum.getEmumId(sex);
    }

    @Override
    public SexEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int sex = rs.getInt(columnIndex);
        if (sex != SexEnum.MALE.getId() && sex != SexEnum.FEMALE.getId()) {
            return null;
        }
        return SexEnum.getEmumId(sex);
    }

    @Override
    public SexEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int sex = cs.getInt(columnIndex);
        if (sex != SexEnum.MALE.getId() && sex != SexEnum.FEMALE.getId()) {
            return null;
        }
        return SexEnum.getEmumId(sex);
    }
}
