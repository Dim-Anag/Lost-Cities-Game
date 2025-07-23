package controller;

import model.GameModel;
import model.Player;
import model.Card;
import model.Turn;
import view.GameView;
import java.util.List;
import javax.swing.JButton;

/**
 * The GameController class manages the interaction between the model and the view.
 * It handles the game logic and updates the view accordingly.
 */
public class GameController {
    private GameModel model;
    private GameView view;
    private Player currentPlayer;
    private Turn currentTurn;
    private GameController controller;

    /**
     * Sets the controller.
     * 
     * @param controller the GameController instance to set
     * @pre controller is not null
     * @post this.controller is set to the provided controller
     */
    public void setController(GameController controller) {
        this.controller = controller;
    }

    /**
     * Constructs a GameController with the specified model and view.
     * 
     * @param model the GameModel instance
     * @param view the GameView instance
     * @pre model is not null
     * @post this.model is set to the provided model and this.view is set to the provided view
     */
    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.model.setController(this); 
    }





    /**
     * Sets the view.
     * 
     * @param view the GameView instance to set
     * @pre view is not null
     * @post this.view is set to the provided view
     */
    public void setView(GameView view) {
        this.view = view;
    }





    /**
     * Sets the current player.
     * 
     * @param player the Player instance to set as the current player
     * @pre player is not null
     * @post this.currentPlayer is set to the provided player and the view is updated to display the current player
     */
    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
        if (view != null) {
            view.displayCurrentPlayer(player);
        }
    }





    /**
     * Starts the game by initializing the model and updating the view.
     * 
     * @pre model and view are not null
     * @post the game is initialized, the board and players' hands are displayed, and the first player's turn is started
     */
    public void startGame() {
        model.initializeGame();
        view.displayBoard();
        view.displayCurrentPlayer(model.getCurrentPlayer());
        view.updateRemainingCards(model.getRemainingCards());

       
        view.displayPlayerHand(model.getPlayers().get(0));
        view.displayPlayerHand(model.getPlayers().get(1));

        
        
        startTurn();
    }



    /**
     * Starts the current player's turn.
     * 
     * @pre model and view are not null
     * @post the current player's turn is started and the view is updated to display the current player
     */
    public void startTurn() {
        currentPlayer = model.getCurrentPlayer();
        currentTurn = new Turn(currentPlayer, this, view);
        currentTurn.startTurn();
        view.displayCurrentPlayer(currentPlayer); 
       // System.out.println("Starting turn for player: " + currentPlayer.getName());
    }

    /**
     * Ends the current player's turn if possible.
     * 
     * @pre currentTurn is not null
     * @post the turn is ended if the current turn can be ended, otherwise an invalid move message is displayed
     */
    public void endTurn() {
        if (currentTurn != null && currentTurn.canEndTurn()) {
            model.endTurn();
        } else {
            view.displayInvalidMove("You must play or discard a card and draw a card before ending your turn.");
        }
    }

    /**
     * Returns the current player.
     * 
     * @return the current Player instance
     * @pre none
     * @post the current player is returned
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns the game model.
     * 
     * @return the GameModel instance
     * @pre none
     * @post the game model is returned
     */
    public GameModel getModel() {
        return model;
    }

    /**
     * Returns the current turn.
     * 
     * @return the current Turn instance
     * @pre none
     * @post the current turn is returned
     */
    public Turn getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Plays a card for the specified player.
     * 
     * @param player the Player instance
     * @param card the Card instance to play
     * @pre player and card are not null
     * @post the card is played if the current turn is not null
     */
    public void playCard(Player player, Card card) {
        if (currentTurn != null) {
            currentTurn.playCard(card);
        }
    }

    /**
     * Discards a card for the specified player.
     * 
     * @param player the Player instance
     * @param card the Card instance to discard
     * @pre player and card are not null
     * @post the card is discarded if the current turn is not null
     */
    public void discardCard(Player player, Card card) {
        if (currentTurn != null) {
           // System.out.println("Attempting to discard card: " + card + " for player: " + player.getName());
            currentTurn.discardCard(card);
        }
    }

    /**
     * Draws a card for the specified player.
     * 
     * @param player the Player instance
     * @return the drawn Card instance, or null if drawing is not possible
     * @pre player is not null
     * @post the drawn card is returned and the view is updated to display the player's hand and remaining cards
     */
    public Card drawCard(Player player) {
        if (currentTurn != null) {
           // System.out.println("Attempting to draw card for player: " + player.getName());
            Card drawnCard = currentTurn.drawCard();
            if (drawnCard != null) {
                view.displayPlayerHand(player); 
                view.updateRemainingCards(model.getRemainingCards()); 
                return drawnCard;
            }
        }
        return null;
    }

    /**
     * Returns the number of remaining cards.
     * 
     * @return the number of remaining cards
     * @pre none
     * @post the number of remaining cards is returned
     */
    public int getRemainingCards() {
        return model.getRemainingCards();
    }

    /**
     * Returns the list of players.
     * 
     * @return the list of Player instances
     * @pre none
     * @post the list of players is returned
     */
    public List<Player> getPlayers() {
        return model.getPlayers();
    }

    /**
     * Returns the next player in turn order.
     * 
     * @return the next Player instance
     * @pre none
     * @post the next player in turn order is returned
     */
    public Player getNextPlayer() {
        int currentIndex = model.getPlayers().indexOf(currentPlayer);
        int nextIndex = (currentIndex + 1) % model.getPlayers().size();
        return model.getPlayers().get(nextIndex);
    }

    /**
     * Returns the list of hand buttons for the specified player.
     * 
     * @param player the Player instance
     * @return the list of JButton instances representing the player's hand
     * @pre player is not null
     * @post the list of hand buttons for the specified player is returned
     */
    private List<JButton> getPlayerHandButtons(Player player) {
        if (model.getPlayers().indexOf(player) == 0) {
            return view.getPlayer1HandButtons();
        } else {
            return view.getPlayer2HandButtons();
        }
    }

    /**
     * Returns the game view.
     * 
     * @return the GameView instance
     * @pre none
     * @post the game view is returned
     */
    public GameView getView() {
        return view;
    }

    
}