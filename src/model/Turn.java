package model;

import controller.GameController;
import util.SoundPlayer;
import view.GameView;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.Image;
import java.util.List;
import javax.swing.JButton;



/**
 * The Turn class represents a player's turn in the game.
 */
public class Turn {
    private Player player;
    private GameController controller;
    private GameView view;
    private boolean cardPlayedOrDiscarded;
    private boolean cardDrawn;





     /**
     * Constructs a Turn with the specified player, controller, and view.
     * 
     * @param player the player taking the turn
     * @param controller the game controller
     * @param view the game view
     * @pre player, controller, and view are not null
     * @post a Turn is created with the specified player, controller, and view
     */
    public Turn(Player player, GameController controller, GameView view) {
        this.player = player;
        this.controller = controller;
        this.view = view;
        this.cardPlayedOrDiscarded = false;
        this.cardDrawn = false;
    }





    /**
     * Plays a card during the player's turn.
     * 
     * @param card the card to play
     * @pre card is not null and the player has not already played or discarded a card
     * @post the card is played and the state is updated
     */
    public void playCard(Card card) {
      //  System.out.println("Starting playCard in Turn.java with card: " + card);
        if (!controller.getModel().playCard(player, card, cardPlayedOrDiscarded)) {
            return;
        }
        cardPlayedOrDiscarded = true;
       // System.out.println("Card played successfully in Turn.java with card: " + card);
    }
    



     /**
     * Discards a card during the player's turn.
     * 
     * @param card the card to discard
     * @pre card is not null and the player has not already played or discarded a card
     * @post the card is discarded and the state is updated
     */
    public void discardCard(Card card) {
      //  System.out.println("Starting discardCard in Turn.java with card: " + card);
        if (!controller.getModel().discardCard(player, card, cardPlayedOrDiscarded)) {
            return;
        }
        cardPlayedOrDiscarded = true;
      //  System.out.println(player.getName() + "'s hand after discarding a card: " + player.getHand());
    }

    
    /**
     * Draws a card during the player's turn.
     * 
     * @return the drawn card, or null if the draw is invalid
     * @pre the player has exactly 7 cards in hand
     * @post a card is drawn and the state is updated, or an invalid move message is displayed
     */
    public Card drawCard() {
     //   System.out.println("Attempting to draw a card for player: " + player.getName());
      //  System.out.println("Player's hand before drawing a card: " + player.getHand());
        if (player.getHand().size() == 7) {
            Card drawnCard = controller.getModel().drawCard(player);
            if (drawnCard != null) {
                cardDrawn = true;
                view.displayPlayerHand(player); 
                view.updateRemainingCards(controller.getModel().getRemainingCards());
            } else {
              //  System.out.println("No card drawn. Deck might be empty.");
            }
            return drawnCard;
        } else {
            view.displayInvalidMove("You can only draw a card if you have 7 cards in hand.");
            return null;
        }
    }




     /**
     * Returns the buttons representing the player's hand.
     * 
     * @return the list of buttons representing the player's hand
     * @pre none
     * @post the list of buttons representing the player's hand is returned
     */
    private List<JButton> getPlayerHandButtons() {
        if (controller.getPlayers().indexOf(player) == 0) {
            return view.getPlayer1HandButtons();
        } else {
            return view.getPlayer2HandButtons();
        }
    }





    /**
     * Starts the player's turn.
     * 
     * @pre none
     * @post the turn state is reset and a sound is played based on the player
     */
    public void startTurn() {
        cardPlayedOrDiscarded = false;
        cardDrawn = false;
    if (player.getName().equals("Player 1")) {
        SoundPlayer.playSound("project_assets/music/Player1.wav");
    } else if (player.getName().equals("Player 2")) {
        SoundPlayer.playSound("project_assets/music/Player2.wav");
    }
    }




    /**
     * Returns the number of remaining cards in the deck.
     * 
     * @return the number of remaining cards
     * @pre none
     * @post the number of remaining cards is returned
     */
    public int getRemainingCards() {
        return controller.getRemainingCards();
    }







    /**
     * Checks if the player can end their turn.
     * 
     * @return true if the player can end their turn, false otherwise
     * @pre none
     * @post returns true if the player has played or discarded a card and drawn a card, false otherwise
     */
    public boolean canEndTurn() {
        return cardPlayedOrDiscarded && cardDrawn;
    }





     /**
     * Ends the player's turn.
     * 
     * @pre none
     * @post the turn is ended and the game state is updated
     */
    public void endTurn() {
        controller.getModel().endTurn();
    }
}