package com.expenses.holder.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "Expenses")
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private Long UserId;

    private String categorie;
    private String shop_name;
    private String expense_name;
    @NotNull
    private Double amount;

    private String currency;





}
