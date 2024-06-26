package com.paymybuddy.db.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "extern_transactions")
public class ExternTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "account", nullable = false, length = 50)
    private String account;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Size(max = 2)
    @NotNull
    @Column(name = "type", nullable = false, length = 2)
    private String type;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Float amount;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_users", nullable = false)
    private User idUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public User getIdUsers() {
        return idUser;
    }

    public void setIdUsers(User idUser) {
        this.idUser = idUser;
    }

}