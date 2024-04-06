package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.SimulationRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerService {


    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    SimulationService simulationService;
    @Autowired
    PaymentService paymentService;

    public void populate() {

        // locale in english
        Faker faker = new Faker(new Locale("en-GB"));

        List<Simulation> simulations;
        List<Payment> payments;
        //Date date = new Date();

        // ref variable creation UUID
        String uniqueID;

        for (int i = 0; i <10 ; i++ ){

            uniqueID = UUID.randomUUID().toString();
            Player player =  new Player();
            player.setId(uniqueID);
            player.setActive(true);
            player.setPlayer( faker.artist().name());
            player.setAge(faker.number().numberBetween(10, 100));

            simulations = simulationService.createFakeSimulations();
            for (int j = 0; j <10 ; j++ ) {
                player.addSimulation(simulations.get(j));
            }

            payments = paymentService.createFakePayments();
            for (int j = 0; j <10 ; j++ ) {
                player.addPayment(payments.get(j));
            }

            playerRepository.save(player);

        }
    }
}
