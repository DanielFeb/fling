package indi.daniel.fling.springboot.datasource.mybatis.dao;

import indi.daniel.fling.springboot.datasource.mybatis.dataconverter.SexEnum;
import indi.daniel.fling.springboot.datasource.mybatis.dataconverter.SexTypeHandler;
import indi.daniel.fling.springboot.datasource.mybatis.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

/**
 * Created by daniel on 2018/12/23.
 */
//@Mapper
@Repository
public interface UserDao {
    //TODO:create is not valid
//    String createSql = "INSERT INTO t_user\n" +
//                        "(user_name,\n" +
//                        "sex,\n" +
//                        "note)\n" +
//                        "VALUES\n" +
//                        "(#{userName},\n" +
//                        "#{sex},\n" +
//                        "#{note})";
//    @Insert(createSql)
//    @ConstructorArgs(
//            @Arg(name="sex", column = "sex", jdbcType = JdbcType.INTEGER, javaType = SexEnum.class, typeHandler = SexTypeHandler.class)
//    )
    long create(User user);
//    @Select("select * from t_user where id = #{userId}")
//    @Results({
//            @Result(property = "sex",  column = "sex", javaType = SexEnum.class, typeHandler = SexTypeHandler.class),
//            @Result(property = "userName", column = "user_name")
//    })
    User getUser(long userId);
}
