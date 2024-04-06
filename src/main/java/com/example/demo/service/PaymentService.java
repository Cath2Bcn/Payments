package com.example.demo.service;

import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentService {


    //static ArrayList<Payment> payments = new ArrayList<>();

    @Autowired
    PaymentRepository paymentRepository;

    public List<Payment> createFakePayments() {

        // locale in english
        Faker faker = new Faker(new Locale("en-GB"));
        Date date = new Date();
        List<Payment> payments = new ArrayList<>();

        // ref variable creation UUID
        String uniqueID;

        for (int i = 0; i <10 ; i++ ){
            uniqueID = UUID.randomUUID().toString();
            Payment payment = new Payment(
                    uniqueID,
                    date.toString(),
                    faker.number().randomDouble(2,0,200),
                    faker.gameOfThrones().character(),
                    false,
                    null
            );
            payments.add(payment);


        }

        return payments;
    }

    public List<Payment> populate() {

        List<Payment> payments = createFakePayments();

        for (int i = 0; i <10 ; i++ ){
            paymentRepository.save(payments.get(i));
            payments.add(payments.get(i));
        }

        return payments;
    }

}
