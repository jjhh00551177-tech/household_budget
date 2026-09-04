package com.example.household_budget.controller;

import com.example.household_budget.entity.Transaction;
import com.example.household_budget.service.CategoryService;
import com.example.household_budget.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/transactions")
    public  String list(Model model){

        List<Transaction> transactions = transactionService.findAll();

        model.addAttribute("transactions", transactions);
        model.addAttribute("income",transactionService.sumByType("income"));
        model.addAttribute("expense",transactionService.sumByType("expense"));
        return "transactions";
    }

    @GetMapping("/transactions/new")
    public String newForm(Model model){
        model.addAttribute("transaction",new Transaction());
        model.addAttribute("categories",categoryService.findAll());
        return "form";
    }

    @GetMapping("/transactions/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("transaction",transactionService.findById(id));
        model.addAttribute("categories",categoryService.findAll());
        return "form";
    }

    @PostMapping("/transactions")
    public String create(@Valid Transaction transaction, BindingResult result,Model model){
        if(result.hasErrors()){
            model.addAttribute("categories",categoryService.findAll());
            return "form";
        }

        transactionService.save(transaction);
        return "redirect:/transactions";
    }

    @PostMapping("/transactions/{id}/delete")
    public String delete(@PathVariable Long id){

        transactionService.delete(id);
        return "redirect:/transactions";
    }

    @PostMapping("/transactions/{id}")
    public String update(@PathVariable Long id,@Valid Transaction transaction,BindingResult result,Model model){
        if(result.hasErrors()){
            model.addAttribute("categories",categoryService.findAll());
            return"form";
        }
        transaction.setId(id);
        transactionService.save(transaction);
        return "redirect:/transactions";
    }
}
