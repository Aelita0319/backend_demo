package com.example.mybatisdemo.model;
import javax.persistence.*;

@Entity
@Table(name = "banks")
public class Bank {
    @Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    @Column(nullable = false)
    private String name;

    private Double arg1;
    private Double arg2;
    private Double arg3;

    public Bank() {
    }

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

    public Double getArg1() {
        return arg1;
    }

    public void setArg1(Double arg1) {
        this.arg1 = arg1;
    }

    public Double getArg2() {
        return arg2;
    }

    public void setArg2(Double arg2) {
        this.arg2 = arg2;
    }

    public Double getArg3() {
        return arg3;
    }

    public void setArg3(Double arg3) {
        this.arg3 = arg3;
    }
}
