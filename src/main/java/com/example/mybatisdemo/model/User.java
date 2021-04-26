package com.example.mybatisdemo.model;
import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(nullable = false)
    private Double principle;

    public User() {
    }

    public User(Integer id, String companyName, Double principle) {
        this.id = id;
        this.companyName = companyName;
        this.principle = principle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
                ", NAME='" + companyName + '\'' +
                '}';
    }
}