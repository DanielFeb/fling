package indi.daniel.fling.springboot.datasource.mybatis.entity;

import indi.daniel.fling.springboot.datasource.mybatis.dataconverter.SexEnum;
import org.apache.ibatis.type.Alias;

/**
 * Created by daniel on 2018/12/20.
 */
@Alias(value = "user") //MyBatis 指定别名
public class User {
    private Long id = null;

    private String userName = null;

    private String note = null;

    private SexEnum sex = SexEnum.MALE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }
}
