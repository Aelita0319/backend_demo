package com.example.mybatisdemo.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    @Column(name = "payer", nullable = false)
    private int payer;

    @Column(name = "receiver", nullable = false)
    private int receiver;

    @Column(name = "created_time")
    private Timestamp time;

    @Column
    private Integer month;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private int type;

    public Transaction() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPayer() {
        return payer;
    }

    public void setPayer(int payerId) {
        this.payer = payerId;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiverId) {
        this.receiver = receiverId;
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
