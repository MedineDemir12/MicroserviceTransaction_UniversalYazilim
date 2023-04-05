package com.universal_yazilim.bid.ysm.transaction_app.controller;

import com.universal_yazilim.bid.ysm.transaction_app.model.entity.Transaction;
import com.universal_yazilim.bid.ysm.transaction_app.model.service.AbstractTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("api/transaction")
@RestController
public class TransactionController
{
    @Autowired
    private AbstractTransactionService transactionService;

    @GetMapping("{id}")
    public ResponseEntity findByID(@PathVariable(name = "id") Integer transactionID)
    {
        Transaction transaction = transactionService.findByID(transactionID);

        return ResponseEntity.ok(transaction);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<List<Transaction>> getTransactionsOfUser(@PathVariable(name = "id") Integer userID)
    {
        List<Transaction> transactionList = transactionService.findAllByUserID(userID);
        return ResponseEntity.ok(transactionList);
    }


    @DeleteMapping("{id}")
    public ResponseEntity deleteByID(@PathVariable(name = "id") Integer transactionID)
    {
        transactionService.deleteByID(transactionID);

        return ResponseEntity.ok("transactionID: " + transactionID + " has been deleted.");
    }

    @PostMapping
    public ResponseEntity<Transaction> save(@RequestBody Transaction transaction)
    {

        Transaction savedTransaction = transactionService.save(transaction);

        return new ResponseEntity<>(savedTransaction, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAll()
    {
        List<Transaction> transactionList = transactionService.getAll();

        return ResponseEntity.ok(transactionList);
    }




}