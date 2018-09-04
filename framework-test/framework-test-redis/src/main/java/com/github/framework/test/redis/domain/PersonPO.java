package com.github.framework.test.redis.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author longhairen
 * @create 2018-02-25 13:15
 * @description
 **/
@Entity
@Table(name = "t_person")
public class PersonPO implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonPO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
