package com.logicea.cards.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.logicea.cards.Models.Card;
import com.logicea.cards.Models.CardStatus;
import com.logicea.cards.Repositories.CardRepository;

import java.util.List;

@Configuration
public class CardConfig {

    @Bean
    CommandLineRunner commandCardLineRunner(CardRepository repository) {
        return args -> {
            Card card1 = new Card(null, "card1", "#FF9933", "description", CardStatus.TODO, null, null, 1, 1);
            Card card2 = new Card(null, "card2", null, "description", CardStatus.TODO, null, null, 2, 2);
            Card card3 = new Card(null, "card3", null, "description", CardStatus.TODO, null, null, 1, 1);
            Card card4 = new Card(null, "card4", null, "description", CardStatus.TODO, null, null, 1, 1);

            repository.saveAll(List.of(card1, card2, card3, card4));
        };
    }

}
