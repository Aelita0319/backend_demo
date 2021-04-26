package com.example.mybatisdemo.model;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "payer", nullable = false)
    private int payerId;

    @Column(name = "receiver", nullable = false)
    private int receiverId;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private int type;

    public Transaction() {}

    public Transaction(Integer id, int payerId, int receiverId, double amount, int status, int type) {
        this.id = id;
        this.payerId = payerId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.status = status;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPayerId() {
        return payerId;
    }

    public void setPayerId(int payerId) {
        this.payerId = payerId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
