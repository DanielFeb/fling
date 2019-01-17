package indi.daniel.fling.springboot.datasource.jpa.entity;

import indi.daniel.fling.springboot.datasource.jpa.dataconverter.SexConverter;
import indi.daniel.fling.springboot.datasource.jpa.dataconverter.SexEnum;

import javax.persistence.*;

/**
 * Created by daniel on 2018/12/20.
 */
@Entity()
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(name = "user_name")
    private String userName = null;

    @Column(name = "note")
    private String note = null;

    @Column(name = "sex")
    @Convert(converter = SexConverter.class)
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
