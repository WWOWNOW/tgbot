package com.expenses.holder.repository;

import com.expenses.holder.entity.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepo  extends CrudRepository<Expense, Long> {
}