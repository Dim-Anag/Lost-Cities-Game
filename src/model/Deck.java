package model;

import java.util.Collections;
import java.util.Stack;



/**
 * The Deck class represents a deck of cards used in the game.
 */
public class Deck {
    private Stack<Card> cards;





    /**
     * Constructs a Deck and initializes it with 100 cards.
     * 
     * @pre none
     * @post the deck is initialized with 100 cards and shuffled
     */
    public Deck() {
        cards = new Stack<>();
        initializeDeck();
        shuffle();
    }





    /**
     * Initializes the deck with 100 cards.
     * 
     * @pre none
     * @post the deck is populated with 100 cards (20 NumberCards, 3 AriadneCards, and 2 MinotaurCards for each palace)
     */
    private void initializeDeck() {
        String[] palaces = {"Knossos", "Phaistos", "Malia", "Zakros"};
        for (String palace : palaces) {
            for (int j = 1; j <= 10; j++) {
                cards.push(new NumberCard(palace, j));
                cards.push(new NumberCard(palace, j));
            }
            for (int j = 0; j < 3; j++) {
                cards.push(new AriadneCard(palace));
            }
            for (int j = 0; j < 2; j++) {
                cards.push(new MinotaurCard(palace));
            }
        }
    }





    /**
     * Shuffles the deck.
     * 
     * @pre none
     * @post the deck is shuffled
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }





    /**
     * Draws a card from the deck.
     * 
     * @return the drawn Card instance
     * @pre the deck is not empty
     * @post a card is removed from the deck and returned
     */
    public Card drawCard() {
        return cards.pop();
    }





    /**
     * Checks if the deck is empty.
     * 
     * @return true if the deck is empty, false otherwise
     * @pre none
     * @post returns true if the deck is empty, false otherwise
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }





    /**
     * Returns the number of remaining cards in the deck.
     * 
     * @return the number of remaining cards
     * @pre none
     * @post the number of remaining cards is returned
     */
    public int getRemainingCards() {
        return cards.size();
    }




    
    /**
     * Deals the initial cards to the specified player.
     * 
     * @param player the Player instance to deal cards to
     * @pre player is not null
     * @post the player is dealt 8 cards from the deck
     */
    public void dealInitialCards(Player player) {
        for (int i = 0; i < 8; i++) {
            player.addCardToHand(drawCard());
        }
    }

}