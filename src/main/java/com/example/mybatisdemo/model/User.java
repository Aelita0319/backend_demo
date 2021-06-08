package com.example.mybatisdemo.model;
import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "fixed_assets", nullable = false)
    private Double fixedAssets;

    @Column(name = "current_assets", nullable = false)
    private Double currentAssets;

    @Column(nullable = false)
    private Double loans;

    public User() {
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

    public Double getFixedAssets() {
        return fixedAssets;
    }

    public void setFixedAssets(Double principle) {
        this.fixedAssets = principle;
    }

    public Double getCurrentAssets() {
        return currentAssets;
    }

    public void setCurrentAssets(Double currentAssets) {
        this.currentAssets = currentAssets;
    }

    public Double getLoans() {
        return loans;
    }

    public void setLoans(Double loans) {
        this.loans = loans;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + id +
                ", NAME='" + companyName + '\'' +
                '}';
    }
}