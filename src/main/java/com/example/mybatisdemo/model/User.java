package com.example.mybatisdemo.model;
import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String company_name;

    @Column(nullable = false)
    private Double principle;

    public User() {
    }

    public User(Integer id, String company_name, Double principle) {
        this.id = id;
        this.company_name = company_name;
        this.principle = principle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Double getPrinciple() {
        return principle;
    }

    public void setPrinciple(Double principle) {
        this.principle = principle;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + id +
                ", NAME='" + company_name + '\'' +
                '}';
    }
}