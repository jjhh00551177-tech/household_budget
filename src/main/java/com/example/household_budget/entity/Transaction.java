package com.example.household_budget.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "金額を入力してください")
    @Min(value = 1 , message = "金額は1以上で入力してください")
    private Long amount;

    @NotNull(message = "日時を入力してください")
    private LocalDateTime date;

    @NotNull(message = "種別を入力してください")
    private String type;

    @NotNull(message = "カテゴリを入力してください")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Transaction() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAmount() { return amount; }
    public void setAmount(Long amount) { this.amount = amount; }

    public LocalDateTime getDate() {return date;}
    public void setDate(LocalDateTime date){this.date = date; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}
