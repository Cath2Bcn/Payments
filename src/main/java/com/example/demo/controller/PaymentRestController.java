package com.example.demo.controller;

import com.example.demo.model.Payment;
import com.example.demo.model.Simulation;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentRestController {

    PaymentService paymentService;

    PaymentRepository paymentRepository;

    //CRUD: read
    @RequestMapping("/payments")
    public Iterable<Payment> getAllPayments (){

        return paymentRepository.findAll();

    }

    //CRUD: create
    @PostMapping(path="create", consumes = "application/JSON")
    public Payment createPayment(@RequestBody Payment payment){
        //
        Payment paymentCreated = paymentRepository.save(payment);
        return paymentCreated;
    }


    @RequestMapping("/populate")
    public String populateDB(){

        paymentService.populate();

        return "ok";
    }

}
