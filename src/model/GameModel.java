package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import util.SoundPlayer;
import javax.swing.*;
import java.awt.*;
import controller.GameController;
import view.GameView;


/**
 * The GameModel class represents the model in the MVC pattern for the game.
 * It manages the game state, including players, deck, board, and game logic.
 */
public class GameModel {
    private List<Player> players;
    private Deck deck;
    private Board board;
    private int currentPlayerIndex;
    private GameView view;
    private GameController controller;
    




     /**
     * Constructs a GameModel with the specified controller and view.
     * 
     * @param controller the GameController instance
     * @param view the GameView instance
     * @pre controller and view are not null
     * @post a GameModel is created with the specified controller and view
     */
    public GameModel(GameController controller, GameView view) {
        this.controller = controller;
        this.view = view;
        players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        deck = new Deck();
        board = new Board();
        currentPlayerIndex = 0;
    }




    /**
     * Constructs a GameModel with the specified board and players.
     * 
     * @param board the Board instance
     * @param players the list of Player instances
     * @pre board and players are not null
     * @post a GameModel is created with the specified board and players
     */
    public GameModel(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.deck = new Deck();
        this.currentPlayerIndex = 0;
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
     * Returns the view.
     * 
     * @return the GameView instance
     * @pre none
     * @post the view is returned
     */
    public GameView getView() {
        return view;
    }




    /**
     * Initializes the game by setting up the board, shuffling the deck, and dealing initial cards.
     * 
     * @pre none
     * @post the game is initialized, the board and players' hands are displayed, and the first player's turn is started
     */
    public void initializeGame() {

        /* Random random = new Random();
        currentPlayerIndex = random.nextInt(players.size()); */
        currentPlayerIndex = 0;
    
        initializeFindings();

        deck.shuffle();

        dealInitialCards();

        view.displayBoard();
        view.displayCurrentPlayer(getCurrentPlayer());
        view.updateRemainingCards(getRemainingCards());
    }




/**
 * Initializes the findings on the board by placing rare findings in their respective paths
 * and distributing other findings randomly.
 * 
 * @pre none
 * @post the board is populated with rare findings and other findings in specific positions
 */
private void initializeFindings() {
    List<RareFinding> rareFindings = new ArrayList<>();
    List<Finding> otherFindings = new ArrayList<>();

    
    for (String description : RareFinding.getRareFindings().keySet()) {
        rareFindings.add(new RareFinding(description));
    }

    
    for (String description : Fresco.getFrescoes().keySet()) {
        otherFindings.add(new Fresco(description));
    }
    for (int i = 0; i < 10; i++) {
        otherFindings.add(new SnakeGoddess("Snake Goddess Statue", 5));
    }

    placeRareFindings(rareFindings);

    Collections.shuffle(otherFindings);

    for (Path path : board.getPaths()) {
        for (int pos : new int[]{1, 3, 5, 7, 8}) {
            if (path.getPositions()[pos] instanceof FindingPosition) {
                FindingPosition findingPosition = (FindingPosition) path.getPositions()[pos];
                if (!findingPosition.isFindingAvailable() && !otherFindings.isEmpty()) {
                    findingPosition.setFinding(otherFindings.remove(0));
                }
            }
        }
    }
}





/**
 * Places rare findings in their respective paths on the board.
 * 
 * @param rareFindings the list of RareFinding instances to place
 * @pre rareFindings is not null
 * @post each rare finding is placed in a specific position on the board based on its description
 */
private void placeRareFindings(List<RareFinding> rareFindings) {
    Map<String, String> palaceMapping = new HashMap<>();
    palaceMapping.put("Phaistos Disc", "Phaistos");
    palaceMapping.put("Minoas Ring (Knossos)", "Knossos");
    palaceMapping.put("Malia Jewel", "Malia");
    palaceMapping.put("Zakros Rhyton", "Zakros");

    for (RareFinding rareFinding : rareFindings) {
        String palaceName = palaceMapping.get(rareFinding.getDescription());
        for (Path path : board.getPaths()) {
            if (path.getPalaceName().equals(palaceName)) {
                List<Integer> positions = Arrays.asList(1, 3, 5, 7, 8);
                Collections.shuffle(positions);
                for (int pos : positions) {
                    if (path.getPositions()[pos] instanceof FindingPosition) {
                        FindingPosition findingPosition = (FindingPosition) path.getPositions()[pos];
                        if (!findingPosition.isFindingAvailable()) {
                            findingPosition.setFinding(rareFinding);
                            break;
                        }
                    }
                }
                break;
            }
        }
    }
}





    /**
     * Deals the initial cards to each player.
     * 
     * @pre none
     * @post each player is dealt 8 cards from the deck
     */
    public void dealInitialCards() {
        for (Player player : players) {
            for (int i = 0; i < 8; i++) {
                drawCard(player);
            }
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
        return players.get(currentPlayerIndex);
    }





     /**
     * Returns the list of players.
     * 
     * @return the list of Player instances
     * @pre none
     * @post the list of players is returned
     */
    public List<Player> getPlayers() {
        return players;
    }





    /**
     * Ends the current player's turn and starts the next player's turn.
     * also logic for the immobilized pawns and the must immobilazation for 1 round
     * @pre none
     * @post the current player's turn is ended, and the next player's turn is started
     */
    public void endTurn() {
        Player currentPlayer = controller.getCurrentPlayer();
       // System.out.println("Ending turn for player: " + currentPlayer.getName());
    
        for (Player player : players) {
            for (Pawn pawn : player.getPawns()) {
                if (pawn.getImmobilizedTurns() > 0) {
                    pawn.setImmobilizedTurns(pawn.getImmobilizedTurns() - 1);
                    if (pawn.getImmobilizedTurns() == 0) {
                        pawn.setImmobilized(false);
                        view.updatePawnAppearance(pawn, getPathIndex(pawn.getPath()), players.indexOf(player));
                    }
                }
            }
        }
    
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size(); 
        Player nextPlayer = players.get(currentPlayerIndex);
      //  System.out.println("Next player: " + nextPlayer.getName());
        controller.setCurrentPlayer(nextPlayer); 
        view.updatePlayerHandButtons(); 
        controller.startTurn();
    }





     /**
     * Returns the board.
     * 
     * @return the Board instance
     * @pre none
     * @post the board is returned
     */
    public Board getBoard() {
        return board;
    }





    /**
     * Handles a finding at the specified position on the specified path for the specified player.
     * 
     * @param player the Player instance
     * @param pathName the name of the path
     * @param position the position on the path
     * @return true if the finding was handled, false otherwise
     * @pre player, pathName, and position are valid
     * @post the finding is handled if available, and the player's score is updated
     */
    public boolean handleFinding(Player player, String pathName, int position) {
        Path path = getPathByName(pathName);
        if (path != null && path.getPositions()[position] instanceof FindingPosition) {
            FindingPosition findingPosition = (FindingPosition) path.getPositions()[position];
            if (findingPosition.isFindingAvailable()) {
                Finding finding = findingPosition.getFinding();
                
            
                if (finding instanceof Fresco) {
                    if (finding.getPhotographedBy() != null && finding.getPhotographedBy().equals(player)) {
                        JOptionPane.showMessageDialog(null, "You have already photographed this fresco.", "Fresco Already Photographed", JOptionPane.INFORMATION_MESSAGE);
                        return false;
                    }
                } else {
                   
                    if (finding.getPhotographedBy() != null && !finding.getPhotographedBy().equals(player)) {
                        JOptionPane.showMessageDialog(null, "This finding has already been claimed by another player.", "Finding Already Claimed", JOptionPane.INFORMATION_MESSAGE);
                     //   System.out.println("Debug: Finding " + finding.getDescription() + " has already been claimed by player " + finding.getPhotographedBy().getName());
                        return false;
                    }
                }
    
               
                Pawn pawn = player.getPawnInPath(pathName);
                if (pawn != null && pawn.getType().equals("Theseus")) {
                    if (pawn.getFindingsDestroyed() >= 3) {
                        JOptionPane.showMessageDialog(null, "This Theseus has already destroyed the maximum of 3 findings.", "Cannot Destroy Finding", JOptionPane.INFORMATION_MESSAGE);
                        return false; 
                    }
    
                    
                    boolean destroy = view.showDestroyFindingDialog(finding);
                    if (destroy) {
                        findingPosition.setFinding(null); 
                        pawn.incrementFindingsDestroyed(); 
                        JOptionPane.showMessageDialog(null, "The finding has been destroyed.", "Finding Destroyed", JOptionPane.INFORMATION_MESSAGE);
                      //  System.out.println("Debug: Player " + player.getName() + " has destroyed the finding: " + finding.getDescription());
                        view.updatePlayerScore(player); 
                       
                    pawn.setHidden(false);
                    int pathIndex = getPathIndex(pathName);
                    int playerIndex = players.indexOf(player);
                    view.movePawnOnBoard(pawn, pathIndex, playerIndex);


                        return true; 
                    } else {
                        return false; 
                    }
                } else {
                   
                    Object[] options = {"Yes", "No"};
                    int response = JOptionPane.showOptionDialog(null,
                            "Do you want to open the finding?",
                            "Open Finding",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (response == JOptionPane.YES_OPTION) {
                        if (finding instanceof Fresco) {
                            player.addFinding(finding);
                            finding.setPhotographedBy(player); 
                            view.showFindingDialog(finding, "Fresco Found");
                         //   System.out.println("Debug: Player " + player.getName() + " has photographed the fresco: " + finding.getDescription());
                        } else if (finding instanceof RareFinding || finding instanceof SnakeGoddess) {
                            player.addFinding(finding);
                            finding.setPhotographedBy(player); 
                            findingPosition.setFinding(null); 
                            view.showFindingDialog(finding, "Finding Claimed");
                           // System.out.println("Debug: Player " + player.getName() + " has claimed the finding: " + finding.getDescription());
    
                           
                            if (finding instanceof SnakeGoddess) {
                                System.out.println("Snake Goddess statue found by " + player.getName());
                                int statueCount = countStatues(player);
                                System.out.println("Updated statue count for " + player.getName() + ": " + statueCount);
                            }
    
                            view.updateStatuesLabel(player);
    
                        
                            if (finding instanceof RareFinding) {
                                view.updateRareFindingIcon(player, finding.getDescription());
                            }
                        }
                       
                        view.updatePlayerScore(player);

                        
                    pawn.setHidden(false);
                    int pathIndex = getPathIndex(pathName);
                    int playerIndex = players.indexOf(player);
                    view.movePawnOnBoard(pawn, pathIndex, playerIndex);


                        return true; 


                    }
                }
            }
        }
        return false; 
    }
   
    





/**
 * Returns the path with the specified name.
 * 
 * @param pathName the name of the path
 * @return the Path instance with the specified name, or null if not found
 * @pre pathName is not null
 * @post the path with the specified name is returned, or null if not found
 */
    private Path getPathByName(String pathName) {
        for (Path path : board.getPaths()) {
            if (path.getPalaceName().equals(pathName)) {
                return path;
            }
        }
        return null;
    }

    





    /**
     * Checks if a pawn can be placed on the specified palace for the specified player.
     * 
     * @param player the Player instance
     * @param palace the name of the palace
     * @return true if the pawn can be placed, false otherwise
     * @pre player and palace are valid
     * @post returns true if the pawn can be placed, false otherwise
     */
    public boolean canPlacePawn(Player player, String palace) {
        return !player.hasPawnInPath(palace);
    }







     /**
     * Places a pawn on the specified palace for the specified player.
     * 
     * @param player the Player instance
     * @param palace the name of the palace
     * @param pawn the Pawn instance to place
     * @pre player, palace, and pawn are valid
     * @post the pawn is placed on the specified palace for the specified player
     */
    public void placePawn(Player player, String palace, Pawn pawn) {
     //   System.out.println("Placing new pawn at position: " + pawn.getPosition());
     //   System.out.println("Pawn type before placing: " + pawn.getType());
        pawn.setPath(palace);
        pawn.setPosition(0); // Set the initial position to 0
        player.addPawnPath(palace);
      //  System.out.println("Pawn type after placing: " + pawn.getType());
    
       
        if (pawn.getType().equals("Archaeologist")) {
            player.decrementArchaeologistCount();
        } else if (pawn.getType().equals("Theseus")) {
            player.decrementTheseusCount();
        }

        
        pawn.setHidden(true);
    
       
        int pathIndex = getPathIndex(palace);
        int playerIndex = players.indexOf(player);
        view.addPawnToBoard(pawn, pathIndex, playerIndex);
        
         
        view.updatePlayerScore(player);
        
    //    System.out.println("Remaining Archaeologists: " + player.getArchaeologistCount());
    //  System.out.println("Remaining Theseus: " + player.getTheseusCount());
    }







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
     * Moves a pawn to the specified new position.
     * 
     * @param pawn the Pawn instance to move
     * @param newPosition the new position to move the pawn to
     * @pre pawn and newPosition are valid
     * @post the pawn is moved to the specified new position
     */
    public void movePawn(Pawn pawn, int newPosition) {
    if (pawn.isImmobilized()) {
        view.displayInvalidMove("This Theseus is immobilized and cannot move this turn.");
      //  System.out.println("Invalid move. This Theseus is immobilized and cannot move this turn.");
        return;
    }

 //   System.out.println("Moving pawn to new position: " + newPosition);
 //   System.out.println("Pawn type before moving: " + pawn.getType());
    pawn.setPosition(newPosition);
 //   System.out.println("Pawn type after moving: " + pawn.getType());

  
    if (pawn.getImmobilizedTurns() > 0) {
        pawn.setImmobilizedTurns(pawn.getImmobilizedTurns() - 1);
        if (pawn.getImmobilizedTurns() == 0) {
            pawn.setImmobilized(false);
        }
    }

    
    int pathIndex = getPathIndex(pawn.getPath());
    int playerIndex = players.indexOf(pawn.getPlayer());
    view.movePawnOnBoard(pawn, pathIndex, playerIndex);

    
    if (newPosition == 8) {
        String palaceName = pawn.getPath();
        String message = PALACE_TEXTS.get(palaceName);
        if (message != null) {
            view.showPalaceReachedDialog(palaceName, message); 
        }
    }

    
    handleFinding(pawn.getPlayer(), pawn.getPath(), newPosition);

    
    view.updatePlayerScore(pawn.getPlayer());

    
    Player opponent = players.get((players.indexOf(pawn.getPlayer()) + 1) % players.size());
    Pawn opponentPawn = opponent.getPawnInPath(pawn.getPath());
    if (opponentPawn != null) {
        int opponentPosition = opponentPawn.getPosition();
        int opponentX = 250 + opponentPosition * 100;
        int opponentY = 50 + pathIndex * 110;
        if (pathIndex > 0) {
            opponentY += 40;
        }
        if (players.indexOf(opponent) == 1) {
            opponentY += 30;
        }
     //   System.out.println("Opponent's pawn coordinates: x = " + opponentX + ", y = " + opponentY);
    } else {
      //  System.out.println("No opponent's pawn in the same path.");
    }
}

    





 /**
     * Draws a card for the specified player.
     * 
     * @param player the Player instance
     * @return the drawn Card instance
     * @pre player is not null
     * @post a card is drawn from the deck and added to the player's hand
     */
public Card drawCard(Player player) {
  //  System.out.println("GameModel: Drawing a card for player: " + player.getName());
    Card drawnCard = deck.drawCard();
    if (drawnCard != null) {
        player.addCardToHand(drawnCard);
        view.updateRemainingCards(getRemainingCards());
        // System.out.println(player.getName() + " drew a card: " + drawnCard);
    } else {
        view.displayDeckEmpty();
        endGame(); // End the game if the deck is empty
    }
    return drawnCard;
}





     /**
     * Plays an Ariadne card for the specified player in the specified palace.
     * 
     * @param player the Player instance
     * @param palace the name of the palace
     * @return true if the card was played successfully, false otherwise
     * @pre player and palace are valid
     * @post the Ariadne card is played, and the player's pawn is moved forward
     */
    public boolean playAriadneCard(Player player, String palace) {
      //  System.out.println("Playing Ariadne card for player: " + player.getName() + " in palace: " + palace);
    
        if (!player.hasPawnInPath(palace)) {
          //  System.out.println("Player has no pawn in the path of the palace.");
            view.displayInvalidMove("Player has no pawn in the path of the palace.");
            return false; 
        }
    
        Pawn playerPawn = player.getPawnInPath(palace);
        if (playerPawn.isImmobilized()) {
            view.displayInvalidMove("This Theseus is immobilized and cannot move this turn.");
          //  System.out.println("Invalid move. This Theseus is immobilized and cannot move this turn.");
            return false;
        }
    
        int previousPosition = playerPawn.getPosition();
        int intermediatePosition = previousPosition + 1; 
        int finalPosition = previousPosition + 2; 
    
     //   System.out.println("Moving player's pawn 1 step forward in palace: " + palace);
        movePawn(playerPawn, intermediatePosition); 
    
     //   System.out.println("Moving player's pawn another step forward in palace: " + palace);
        movePawn(playerPawn, finalPosition); // Move another step forward
    
        return true;
    }






     /**
     * Plays a Minotaur card for the specified player in the specified palace.
     * 
     * @param player the Player instance
     * @param palace the name of the palace
     * @return true if the card was played successfully, false otherwise
     * @pre player and palace are valid
     * @post the Minotaur card is played, and the opponent's pawn is affected
     */
    public boolean playMinotaurCard(Player player, String palace) {
        Player opponent = controller.getNextPlayer(); 
     //   System.out.println("Playing Minotaur card for player: " + player.getName() + " in palace: " + palace);
    
        if (!opponent.hasPawnInPath(palace)) {
         //   System.out.println("Opponent has no pawn in the path of the palace.");
            view.displayInvalidMove("Opponent has no pawn in the path of the palace.");
            return false; 
        }
    
        Pawn opponentPawn = opponent.getPawnInPath(palace);
    
        
        if (opponentPawn.getPosition() >= 6) {
          //  System.out.println("Invalid move. Opponent's pawn is in step 7 or greater.");
            view.displayInvalidMove("Invalid move. Opponent's pawn has claimed the checkpoint!.");
            return false;
        }

        
        opponentPawn.setHidden(false);
    
       
        if (opponentPawn.getType().equals("Theseus")) {
            opponentPawn.setImmobilized(true);
            opponentPawn.setImmobilizedTurns(2); 
          //  System.out.println("Opponent's Theseus is immobilized for the next turn in palace: " + palace);
        } else {
            int previousPosition = opponentPawn.getPosition();
            int newPosition = Math.max(0, previousPosition - 2); 
          //  System.out.println("Moving opponent's pawn 2 steps back in palace: " + palace);
            movePawn(opponentPawn, newPosition);
        }
    
        view.updatePlayerScore(opponent);

        int pathIndex = getPathIndex(palace);
        int playerIndex = players.indexOf(opponent);
        view.movePawnOnBoard(opponentPawn, pathIndex, playerIndex);
    
        return true;
    }





    /**
     * Calculates the total score for the specified player.
     * 
     * @param player the Player instance
     * @return the total score of the player
     * @pre player is not null
     * @post the total score of the player is returned
     */
    public int calculateTotalScore(Player player) {
        int totalScore = 0;
    
        for (Finding finding : player.getFindings()) {
            if (finding instanceof RareFinding) {
                totalScore += ((RareFinding) finding).getScore();
            }
        }
    
        for (Finding finding : player.getFindings()) {
            if (finding instanceof Fresco) {
                totalScore += ((Fresco) finding).getScore();
            }
        }
    
        int statueCount = countStatues(player);
        totalScore += getStatueScore(statueCount);
    
        for (Pawn pawn : player.getPawns()) {
            totalScore += getPositionScore(pawn);
        }
    
        return totalScore;
    }






    /**
     * Plays a number card for the specified player.
     * 
     * @param player the Player instance
     * @param card the NumberCard instance to play
     * @return true if the card was played successfully, false otherwise
     * @pre player and card are valid
     * @post the number card is played, and the player's pawn is moved forward
     */
    public boolean playNumberCard(Player player, NumberCard card) {
        String palace = card.getPalace();
        int cardValue = card.getValue();
        int highestValueCard = player.getHighestValueCard(palace);
    
        if (cardValue < highestValueCard) {
            view.displayInvalidMove("Card value must be equal or greater than the highest value card previously played.");
          //  System.out.println("Invalid move. Card value must be equal or greater than the highest value card previously played.");
            return false;
        }
    
        if (!player.hasPawnInPath(palace)) {
            if (player.getArchaeologistCount() == 0 && player.getTheseusCount() == 0) {
                view.displayInvalidMove("No available pawns to place.");
              //  System.out.println("No available pawns to place.");
                return false;
            }
    
            Pawn pawn = view.promptPlayerToChoosePawn(player);
            if (pawn == null) {
             //   System.out.println("No pawn selected.");
                return false;
            }
    
            if (!canPlacePawn(player, palace)) {
                view.displayInvalidMove("You can only place one pawn per path.");
              //  System.out.println("Cannot place more than one pawn per path.");
                return false;
            }
         //   System.out.println("Placing new pawn in palace: " + palace);
            placePawn(player, palace, pawn);
            view.updateRemainingPawns(player); 
            player.addPawnPath(palace); 
        } else {
            Pawn existingPawn = player.getPawnInPath(palace);
            if (existingPawn.isImmobilized()) {
                view.displayInvalidMove("This Theseus is immobilized and cannot move this turn.");
              //  System.out.println("Invalid move. This Theseus is immobilized and cannot move this turn.");
                return false;
            }
          //  System.out.println("Moving existing pawn to next position in palace: " + palace);
            movePawn(existingPawn, existingPawn.getPosition() + 1);
        }
    
        player.updateHighestValueCard(palace, cardValue);
        return true;
    }






    /**
     * Plays a card for the specified player.
     * 
     * @param player the Player instance
     * @param card the Card instance to play
     * @param cardPlayedOrDiscarded a flag indicating if a card has already been played or discarded
     * @return true if the card was played successfully, false otherwise
     * @pre player and card are valid
     * @post the card is played, and the player's hand is updated
     */
    public boolean playCard(Player player, Card card, boolean cardPlayedOrDiscarded) {
      //  System.out.println("Starting playCard in GameModel with card: " + card);
        if (cardPlayedOrDiscarded) {
            view.displayInvalidMove("You can only play or discard one card per turn.");
          //  System.out.println("Card already played or discarded.");
            return false;
        }
    
        boolean cardPlayedSuccessfully = false;
    
        if (card instanceof NumberCard) {
            cardPlayedSuccessfully = playNumberCard(player, (NumberCard) card);
        } else if (card instanceof AriadneCard) {
            String palace = ((AriadneCard) card).getPalace();
         //   System.out.println("Playing Ariadne card in palace: " + palace);
            cardPlayedSuccessfully = playAriadneCard(player, palace);
            if (cardPlayedSuccessfully) {
                Pawn playerPawn = player.getPawnInPath(palace);
                if (playerPawn.isImmobilized()) {
                    view.displayInvalidMove("This Theseus is immobilized and cannot move this turn.");
                 //   System.out.println("Invalid move. This Theseus is immobilized and cannot move this turn.");
                    return false;
                }
                view.movePawnOnBoard(playerPawn, getPathIndex(palace), getPlayers().indexOf(player));
            }
        } else if (card instanceof MinotaurCard) {
            String palace = ((MinotaurCard) card).getPalace();
          //  System.out.println("Playing Minotaur card in palace: " + palace);
            cardPlayedSuccessfully = playMinotaurCard(player, palace);
            if (cardPlayedSuccessfully) {
                Player opponent = controller.getNextPlayer();
                Pawn opponentPawn = opponent.getPawnInPath(palace);
                view.movePawnOnBoard(opponentPawn, getPathIndex(palace), getPlayers().indexOf(opponent));
            }
        }
    
        if (!cardPlayedSuccessfully) {
            return false;
        }
    
        player.getHand().remove(card);
        view.displayBoard();
        view.displayPlayerHand(player);
        int playerIndex = getPlayers().indexOf(player);
        if (card instanceof NumberCard) {
            String palace = ((NumberCard) card).getPalace(); 
            ImageIcon cardIcon = new ImageIcon(((NumberCard) card).getIcon().getImage().getScaledInstance(60, 100, Image.SCALE_SMOOTH));
            if (palace.equals("Knossos")) {
                view.updatePlayedKnossosCard(cardIcon, true, playerIndex); 
            } else if (palace.equals("Phaistos")) {
                view.updatePlayedPhaistosCard(cardIcon, true, playerIndex); 
            } else if (palace.equals("Malia")) {
                view.updatePlayedMaliaCard(cardIcon, true, playerIndex); 
            } else if (palace.equals("Zakros")) {
                view.updatePlayedZakrosCard(cardIcon, true, playerIndex); 
            }
        }

       
    if (checkEndGameCondition()) {
        endGame();
    }


      //  System.out.println("Card played successfully in GameModel with card: " + card);
        return true;
    }







    /**
     * Discards a card for the specified player.
     * 
     * @param player the Player instance
     * @param card the Card instance to discard
     * @param cardPlayedOrDiscarded a flag indicating if a card has already been played or discarded
     * @return true if the card was discarded successfully, false otherwise
     * @pre player and card are valid
     * @post the card is discarded, and the player's hand is updated
     */
public boolean discardCard(Player player, Card card, boolean cardPlayedOrDiscarded) {
    if (cardPlayedOrDiscarded) {
        view.displayInvalidMove("You can only play or discard one card per turn.");
        return false;
    }

    String cardDescription = card.toString();
    int response = JOptionPane.showConfirmDialog(null, "Do you want to discard this card: " + cardDescription + "?", "Discard Card", JOptionPane.YES_NO_OPTION);
    if (response == JOptionPane.YES_OPTION) {
        player.getHand().remove(card);
        view.displayPlayerHand(player); 
      //  System.out.println(player.getName() + "'s hand after discarding a card: " + player.getHand());
        return true;
    }
    return false;
}







     /**
     * Returns the number of remaining cards in the deck.
     * 
     * @return the number of remaining cards
     * @pre none
     * @post the number of remaining cards is returned
     */
    public int getRemainingCards() {
        return deck.getRemainingCards();
    }






    /**
     * Returns the index of the specified palace.
     * 
     * @param palace the name of the palace
     * @return the index of the palace
     * @pre palace is valid
     * @post the index of the palace is returned
     */
    public int getPathIndex(String palace) {
        switch (palace) {
            case "Knossos":
                return 0;
            case "Phaistos":
                return 1;
            case "Malia":
                return 2;
            case "Zakros":
                return 3;
            default:
                throw new IllegalArgumentException("Invalid palace: " + palace);
        }
    }
   






    /**
     * Ends the game and determines the winner.
     * 
     * @pre none
     * @post the game is ended, scores are updated, and the winner is determined
     */
    private void endGame() {
        for (Player player : players) {
            player.setScore(calculateTotalScore(player));
        }
    
        Player winner = determineWinner();
        String message;
        
        if (winner != null) {
            message = "Winner: " + winner.getName() + " with score: " + winner.getScore();
        } else {
            message = "The game is a draw.";
        }
        
        JOptionPane.showMessageDialog(null, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        
        System.exit(0);
    }








    /**
     * Checks if the end game condition is met.
     * 
     * @return true if the end game condition is met, false otherwise
     * @pre none
     * @post returns true if the end game condition is met, false otherwise
     */
    private boolean checkEndGameCondition() {
        int pawnsAtCheckpoint = 0;
        for (Player player : players) {
            for (Pawn pawn : player.getPawns()) {
                if (pawn.getPosition() >= 6) { 
                    pawnsAtCheckpoint++;
                }
            }
        }
        return deck.isEmpty() || pawnsAtCheckpoint >= 4;
    }






    /**
     * Determines the winner of the game.
     * 
     * @return the Player instance who is the winner, or null if it's a draw
     * @pre none
     * @post the winner of the game is determined and returned, or null if it's a draw
     */
    private Player determineWinner() {
        Player player1 = players.get(0);
        Player player2 = players.get(1);
    
        if (player1.getScore() > player2.getScore()) {
            return player1;
        } else if (player1.getScore() < player2.getScore()) {
            return player2;
        }
    
        int player1RareFindings = countRareFindings(player1);
        int player2RareFindings = countRareFindings(player2);
        if (player1RareFindings > player2RareFindings) {
            return player1;
        } else if (player1RareFindings < player2RareFindings) {
            return player2;
        }
    
        int player1Frescoes = countFrescoes(player1);
        int player2Frescoes = countFrescoes(player2);
        if (player1Frescoes > player2Frescoes) {
            return player1;
        } else if (player1Frescoes < player2Frescoes) {
            return player2;
        }
    
        int player1Statues = countStatues(player1);
        int player2Statues = countStatues(player2);
        if (player1Statues > player2Statues) {
            return player1;
        } else if (player1Statues < player2Statues) {
            return player2;
        }
    
        return null;
    }
    
    


    
    
    /**
     * Updates the score for the specified player.
     * 
     * @param player the Player instance
     * @pre player is not null
     * @post the player's score is updated
     */
    public void updatePlayerScore(Player player) {
        int totalScore = 0;
    
        for (Finding finding : player.getFindings()) {
            totalScore += getFindingScore(finding);
        }
    
        int statueCount = countStatues(player);
        totalScore += getStatueScore(statueCount);
    
        for (Pawn pawn : player.getPawns()) {
            totalScore += getPositionScore(pawn);
        }
    
        player.setScore(totalScore);
        view.updatePlayerScore(player); 
    }






    /**
     * Returns the score of the specified finding.
     * 
     * @param finding the Finding instance
     * @return the score of the finding
     * @pre finding is not null
     * @post the score of the finding is returned
     */
    private int getFindingScore(Finding finding) {
        if (finding instanceof RareFinding) {
            return ((RareFinding) finding).getScore(); 
        } else if (finding instanceof Fresco) {
            return ((Fresco) finding).getScore(); 
        }
        return 0;
    }






     /**
     * Returns the score of the specified pawn's position.
     * 
     * @param pawn the Pawn instance
     * @return the score of the pawn's position
     * @pre pawn is not null
     * @post the score of the pawn's position is returned
     */
    private int getPositionScore(Pawn pawn) {
        Path path = getPathByName(pawn.getPath());
        if (path != null) {
            Position position = path.getPositions()[pawn.getPosition()];
            int score = position.getScore();
            if (pawn.getType().equals("Theseus")) {
                return score * 2; 
            }
            return score;
        }
        return 0;
    }






    /**
     * Returns the score based on the number of statues.
     * 
     * @param statueCount the number of statues
     * @return the score based on the number of statues
     * @pre none
     * @post the score based on the number of statues is returned, max statues 6
     */
    private int getStatueScore(int statueCount) {
        switch (statueCount) {
            case 0: return 0;
            case 1: return -20;
            case 2: return -15;
            case 3: return 10;
            case 4: return 15;
            case 5: return 30;
            case 6: return 50;
            default: return 50; 
        }
    }





   
    /**
     * Returns the number of rare findings for the specified player.
     * 
     * @param player the Player instance
     * @return the number of rare findings
     * @pre player is not null
     * @post the number of rare findings is returned
     */
    private int countRareFindings(Player player) {
        int count = 0;
        for (Finding finding : player.getFindings()) {
            if (finding instanceof RareFinding) {
                count++;
            }
        }
        return count;
    }






     /**
     * Returns the number of frescoes for the specified player.
     * 
     * @param player the Player instance
     * @return the number of frescoes
     * @pre player is not null
     * @post the number of frescoes is returned
     */   
    private int countFrescoes(Player player) {
        int count = 0;
        for (Finding finding : player.getFindings()) {
            if (finding instanceof Fresco) {
                count++;
            }
        }
        return count;
    }





    /**
     * Returns the number of statues for the specified player.
     * 
     * @param player the Player instance
     * @return the number of statues
     * @pre player is not null
     * @post the number of statues is returned
     */
    public int countStatues(Player player) {
        int count = 0;
        for (Finding finding : player.getFindings()) {
            if (finding instanceof SnakeGoddess) {
                count++;
            }
        }
        return count;
    }






/**
 * A map containing the texts associated with each palace.
 * The texts provide descriptions and historical information about each palace.
 */
    private static final Map<String, String> PALACE_TEXTS = new HashMap<>();
static {
    PALACE_TEXTS.put("Knossos", "Eftases sto Anaktoro tis Knosoy!!!;To minoiko anaktoro einai o kyrios episkepsimos xoros tis Knosoy (i Knossoy), simantikis polis kata tin arxaiotita, me synexi zoi apo ta neolithika xronia eos ton 5o ai. Einai xtismeno sto lofo tis Kefalas, me eykoli prosbasi sti thalassa alla kai sto esoteriko tis Kritis. Kata tin paradosi, ypirxe i edra toy sofoy basilia Minoa. Synarpastikoi mythoi, toy Labyrinthoy me to Minotayro kai toy Daidaloy me ton Ikaro, syndeontai me to anaktoro tis Knossoy.\r\n" + 
                "Έφτασες στο Ανάκτορο της Κνωσού!!!;Το μινωικό ανάκτορο είναι ο κύριος επισκέψιμος χώρος της Κνωσού (ή Κνωσσού), σημαντικής πόλης κατά την αρχαιότητα, με συνεχή ζωή από τα νεολιθικά χρόνια έως τον 5ο αι. Είναι χτισμένο στο λόφο της Κεφάλας, με εύκολη πρόσβαση στη θάλασσα αλλά και στο εσωτερικό της Κρήτης. Κατά την παράδοση, υπήρξε η έδρα του σοφού βασιλιά Μίνωα. Συναρπαστικοί μύθοι, του Λαβύρινθου με το Μινώταυρο και του Δαίδαλου με τον Ίκαρο, συνδέονται με το ανάκτορο της Κνωσσού.\r\n");
    PALACE_TEXTS.put("Phaistos", "Eftases sto Anaktoro tis Knosoy!!!;To minoiko anaktoro einai o kyrios episkepsimos xoros tis Knosoy (i Knossoy), simantikis polis kata tin arxaiotita, me synexi zoi apo ta neolithika xronia eos ton 5o ai. Einai xtismeno sto lofo tis Kefalas, me eykoli prosbasi sti thalassa alla kai sto esoteriko tis Kritis. Kata tin paradosi, ypirxe i edra toy sofoy basilia Minoa. Synarpastikoi mythoi, toy Labyrinthoy me to Minotayro kai toy Daidaloy me ton Ikaro, syndeontai me to anaktoro tis Knossoy.\r\n" + 
    "Έφτασες στο Ανάκτορο της Φαιστού!!!; Το Μινωικό Ανάκτορο της Φαιστού  βρίσκεται στην νότιο-κεντρική Κρήτη, στην πεδιάδα της Μεσαράς, 55 χιλιόμετρα νότια από το Ηράκλειο και σε μικρή απόσταση από τον αρχαιολογικό χώρο στην Αγία Τριάδα, τον αρχαιολογικό χώρο στη Γόρτυνα και τα Μάταλα. Το μινωικό ανάκτορο της Φαιστού αντιστοιχεί σε ακμαία πόλη που, όχι τυχαία, αναπτύχθηκε στην έφορη πεδιάδα της Μεσαράς κατά τους προϊστορικούς χρόνους, δηλαδή από το 6.000 π.Χ. περίπου μέχρι και τον 1ο π.Χ. αιώνα, όπως επιβεβαιώνουν τα αρχαιολογικά ευρήματα.\r\n");
    PALACE_TEXTS.put("Malia", "Eftases sto Anaktoro ton Malion!!!; Anatolika apo ta simerina Malia brisketai to minoiko anaktoro ton Malion. Einai to trito se megethos anaktoro tis minoikis Kritis kai einai xtismeno se mia topothesia pronomiaki, konta sti thalassa kai pano sto dromo poy syndeei tin anatoliki me tin kentriki Kriti. To anaktoro ton Malion kata ti mythologia xrisimeye san katoikia toy Sarpidona, aderfoy toy Minoa, kai protoxtizetai to 1900 p.X. O proYparxon isxyros oikismos, apo ton opoio sozontai synoikies gyro apo to anaktoro, metatrepetai etsi se anaktoriko kentro-poli.\r\n" + //
                "paths/maliaPalace.jpg; Έφτασες στο Ανάκτορο των Μαλίων!!!; Ανατολικά από τα σημερινά Μάλια βρίσκεται το μινωικό ανάκτορο των Μαλίων. Είναι το τρίτο σε μέγεθος ανάκτορο της μινωικής Κρήτης και είναι χτισμένο σε μια τοποθεσία προνομιακή, κοντά στη θάλασσα και πάνω στο δρόμο που συνδέει την ανατολική με την κεντρική Κρήτη. Το ανάκτορο των Μαλίων κατά τη μυθολογία χρησίμευε σαν κατοικία του Σαρπηδόνα, αδερφού του Μίνωα, και πρωτοχτίζεται το 1900 π.Χ. Ο προϋπάρχων ισχυρός οικισμός, από τον οποίο σώζονται συνοικίες γύρω από το ανάκτορο, μετατρέπεται έτσι σε ανακτορικό κέντρο-πόλη.\r\n");
    PALACE_TEXTS.put("Zakros", "Eftases sto Anaktoro tis Zakroy!!!;To anaktoro tis Zakroy einai to tetarto se megethostis Minoikis Kritis. Briskotan se simantiko stratigiko simeio, se asfalismeno kolpisko, kai itan kentro emporikon antallagon me tis xores tis Anatolis, opos fainetai apo ta eyrimata (xayliodontes elefanta, fagentiani, xalkos klp).  To anaktoro apotelese to kentro dioikisis, thriskeias kai emporioy. To peristoixize i poli. Sto xoro den egine nea oikodomisi, ektos apo kapoies kalliergeies.\r \n" +
    "Έφτασες στο Ανάκτορο της Ζάκρου!!!;Το ανάκτορο της Ζάκρου είναι το τέταρτο σε μέγεθος της Μινωικής Κρήτης. Βρισκόταν σε σημαντικό στρατηγικό σημείο, σε ασφαλισμένο κολπίσκο, και ήταν κέντρο εμπορικών ανταλλαγών με τις χώρες της Ανατολής, όπως φαίνεται από τα ευρήματα (χαυλιόδοντες ελέφαντα, φαγεντιανή, χαλκός κλπ).  Το ανάκτορο αποτέλεσε το κέντρο διοίκησης, θρησκείας και εμπορίου. Το περιστοίχιζε η πόλη. Στο χώρο δεν έγινε νέα οικοδόμηση, εκτός από κάποιες καλλιέργειες.");
}


}