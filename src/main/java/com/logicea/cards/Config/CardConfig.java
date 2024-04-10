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

            Card card5 = new Card(null, "card5", "#FF9933", "description", CardStatus.TODO, null, null, 1, 1);
            Card card6 = new Card(null, "card6", null, "description", CardStatus.TODO, null, null, 2, 2);
            Card card7 = new Card(null, "card7", null, "description", CardStatus.TODO, null, null, 1, 1);
            Card card8 = new Card(null, "card8", null, "description", CardStatus.TODO, null, null, 1, 1);

            Card card9 = new Card(null, "card9", "#FF9933", "description", CardStatus.TODO, null, null, 1, 1);
            Card card10 = new Card(null, "card10", null, "description", CardStatus.TODO, null, null, 2, 2);
            Card card11 = new Card(null, "card11", null, "description", CardStatus.TODO, null, null, 1, 1);
            Card card12 = new Card(null, "card12", null, "description", CardStatus.TODO, null, null, 1, 1);

            Card card13 = new Card(null, "card13", "#FF9933", "description", CardStatus.TODO, null, null, 1, 1);
            Card card14 = new Card(null, "card14", null, "description", CardStatus.TODO, null, null, 2, 2);
            Card card15 = new Card(null, "card15", null, "description", CardStatus.TODO, null, null, 1, 1);
            Card card16 = new Card(null, "card16", null, "description", CardStatus.TODO, null, null, 1, 1);

            repository.saveAll(
                    List.of(card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12,
                            card13, card14, card15, card16));
        };
    }

}
