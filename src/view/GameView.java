package view;

import model.Board;
import model.Player;
import model.Path;
import model.Finding;
import model.RareFinding;
import model.Fresco;
import model.GameModel;
import model.NumberCard;
import model.SnakeGoddess;
import model.Turn;
import model.Card;
import model.Pawn;
import controller.GameController;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.util.List;
import java.util.ArrayList;





/**
 * The GameView class represents the graphical user interface for the game.
 */
public class GameView extends JFrame {
    private Board board;
    private List<Player> players;
    private JLayeredPane boardPanel;
    private JPanel boardGrid;
    private JLabel remainingCardsLabel;
    private JLabel currentPlayerInfoLabel;
    private GameController controller;
    private GameModel model; 
    private List<JLabel> rareFindingLabels; 
    private List<JButton> player1HandButtons; 
    private List<JButton> player2HandButtons; 
    private JLabel knossosLabel1;
    private JLabel knossosLabel2;
    private JLabel phaistosLabel1;
    private JLabel phaistosLabel2;
    private JLabel maliaLabel1;
    private JLabel maliaLabel2;
    private JLabel zakrosLabel1;
    private JLabel zakrosLabel2;
    private JLabel player1ArchaeologistsLabel;
    private JLabel player1TheseusLabel;
    private JLabel player2ArchaeologistsLabel;
    private JLabel player2TheseusLabel; 
    private List<Pawn> pawns;
    private JLabel player1StatuesLabel;
    private JLabel player2StatuesLabel;

    


    /**
     * Constructs a GameView with the specified board, players, and model.
     * 
     * @param board the game board
     * @param players the list of players
     * @param model the game model
     * @pre board, players, and model are not null
     * @post a GameView is created with the specified board, players, and model
     */
    public GameView(Board board, List<Player> players, GameModel model) {
        this.board = board;
        this.players = players;
        this.rareFindingLabels = new ArrayList<>(); 
        this.player1HandButtons = new ArrayList<>(); 
        this.player2HandButtons = new ArrayList<>();
        this.pawns = new ArrayList<>();
        this.model = model; 
        this.remainingCardsLabel = new JLabel("Remaining Cards: ");
        this.currentPlayerInfoLabel = new JLabel("Playing: ");

        for (Player player : players) {
            for (Pawn pawn : player.getPawns()) {
            addPawn(pawn);
             }
        }
    }




     /**
     * Adds a pawn to the list of pawns.
     * 
     * @param pawn the pawn to add
     * @pre pawn is not null
     * @post the pawn is added to the list of pawns
     */
    public void addPawn(Pawn pawn) {
        pawns.add(pawn);
    }
    



     /**
     * Sets the controller for the GameView.
     * 
     * @param controller the game controller
     * @pre controller is not null
     * @post the controller is set and the UI is initialized
     */
    public void setController(GameController controller) {
        this.controller = controller;
        initializeUI();
        remainingCardsLabel.setText("Remaining Cards: " + controller.getRemainingCards());
        currentPlayerInfoLabel.setText("Playing: " + controller.getCurrentPlayer().getName());
    }





     /**
     * Returns the list of buttons representing Player 1's hand.
     * 
     * @return the list of buttons representing Player 1's hand
     * @pre none
     * @post the list of buttons representing Player 1's hand is returned
     */
    public List<JButton> getPlayer1HandButtons() {
        return player1HandButtons;
    }




    /**
     * Returns the list of buttons representing Player 2's hand.
     * 
     * @return the list of buttons representing Player 2's hand
     * @pre none
     * @post the list of buttons representing Player 2's hand is returned
     */
    public List<JButton> getPlayer2HandButtons() {
        return player2HandButtons;
    }





    /**
 * The CardButtonListener class handles mouse events for card buttons.
 */
    private class CardButtonListener implements MouseListener {
        private Player player;
        private int cardIndex;



    /**
     * Constructs a CardButtonListener with the specified player and card index.
     * 
     * @param player the player associated with the card
     * @param cardIndex the index of the card in the player's hand
     * @pre player is not null, cardIndex is a valid index in the player's hand
     * @post a CardButtonListener is created with the specified player and card index
     */
        public CardButtonListener(Player player, int cardIndex) {
            this.player = player;
            this.cardIndex = cardIndex;
        }




    /**
     * Handles the mouse click event on a card button.
     * 
     * @param e the mouse event
     * @pre e is not null
     * @post the card is played or discarded based on the mouse button clicked
     */
        @Override
        public void mouseClicked(MouseEvent e) {
            if (controller.getCurrentPlayer() != player) {
                JOptionPane.showMessageDialog(null, "Play your cards, your turn!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Card card = player.getHand().get(cardIndex);
            if (SwingUtilities.isLeftMouseButton(e)) {
                if (controller != null) {
                    if (card instanceof NumberCard) {
                        String palace = ((NumberCard) card).getPalace();
                        if (!player.hasPawnInPath(palace)) {
                            Pawn pawn = promptPlayerToChoosePawn(player);
                            if (pawn == null) {
                                return;
                            }

                            controller.playCard(player, card);
                        } else {
                            controller.playCard(player, card);
                        }
                    } else {
                        controller.playCard(player, card);
                    }
                    updateRemainingPawns(player);
                }
            } else if (SwingUtilities.isRightMouseButton(e)) {
                if (controller != null) {
                    controller.discardCard(player, card);
                }
                
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // Not used
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // Not used
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // Not used
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // Not used
        }
    }
    





     /**
     * Displays the game over dialog with the winner's name.
     * 
     * @param winner the player who won the game, or null if the game is a tie
     * @pre none
     * @post a game over dialog is displayed with the winner's name or a tie message
     */
    public void displayGameOver(Player winner) {
        if (winner != null) {
            JOptionPane.showMessageDialog(this, "Game Over. The winner is: " + winner.getName(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Game Over. The game is a tie.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
    }




/**
 * Adds labels to the board representing the points for each path step.
 * 
 * @pre none
 * @post labels are added to the board panel representing the points for each path step
 */ 
    private void addPathLabels() {
        String[] pathLabels = {
            "-20 points", "-15 points", "-10 points", "5 points", "10 points", 
            "15 points", "30 points<br>CHECK POINT!", "35 points", "50 points"
        };

        for (int i = 0; i < pathLabels.length; i++) {
            JLabel label = new JLabel("<html>" + pathLabels[i].replace("\n", "<br>") + "</html>");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setBounds(250 + i * 95, 0, 100, 50); 
            boardPanel.add(label, JLayeredPane.MODAL_LAYER);
        }
    }









/**
 * Initializes the user interface for the game.
 * 
 * @pre none
 * @post the user interface is initialized and displayed
 */
    private void initializeUI() {
        setTitle("Lost Cities Game");
        setSize(1200, 900); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); 

        boardPanel = new JLayeredPane();
        boardPanel.setBounds(0, 150, 1200, 600); 
        JLabel backgroundLabel = new JLabel(new ImageIcon(new ImageIcon("project_assets/images/background.jpg").getImage().getScaledInstance(1200, 600, Image.SCALE_SMOOTH)));
        backgroundLabel.setBounds(0, 0, 1200, 600);
        boardPanel.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        boardGrid = new JPanel(new GridBagLayout());
        boardGrid.setOpaque(false); 
        boardGrid.setBounds(250, 50, 900, 500); 
        boardPanel.add(boardGrid, JLayeredPane.PALETTE_LAYER);
       
        
        JButton deckButton = new JButton(new ImageIcon(new ImageIcon("project_assets/images/cards/backCard.jpg").getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH))); 
        deckButton.setBounds(50, 250, 100, 150); 
        deckButton.addMouseListener(new MouseListener() {

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e) && controller != null) {
            Turn currentTurn = controller.getCurrentTurn();
            if (currentTurn != null) {
                currentTurn.drawCard();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Not used
    }
});

    boardPanel.add(deckButton, JLayeredPane.MODAL_LAYER);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBounds(35, 410, 150, 80); 

        remainingCardsLabel = new JLabel("Remaining Cards: ");
        currentPlayerInfoLabel = new JLabel("Playing: \n");

        infoPanel.add(remainingCardsLabel);
        infoPanel.add(currentPlayerInfoLabel);

        boardPanel.add(infoPanel, JLayeredPane.MODAL_LAYER);

        add(boardPanel);

        addPathLabels();

        JPanel player1Outline = new JPanel();
        player1Outline.setBounds(0, 0, 1200, 150); 
        player1Outline.setBorder(new LineBorder(Color.GREEN, 3));
        player1Outline.setLayout(null);
        add(player1Outline);

        JPanel player2Outline = new JPanel();
        player2Outline.setBounds(0, 750, 1200, 150);
        player2Outline.setBorder(new LineBorder(Color.PINK, 3));
        player2Outline.setLayout(null);
        add(player2Outline);

        addPlayerComponents(player1Outline, players.get(0), "Player 1", player1HandButtons);

        addPlayerComponents(player2Outline, players.get(1), "Player 2", player2HandButtons);

        addPalaceComponents(player1Outline, 650, 10); 
        addPalaceComponents(player2Outline, 650, 10); 

        knossosLabel1 = new JLabel();
        knossosLabel1.setBounds(650, 10, 60, 100); 
        player1Outline.add(knossosLabel1, JLayeredPane.PALETTE_LAYER); 

        phaistosLabel1 = new JLabel();
        phaistosLabel1.setBounds(750, 10, 60, 100); 
        player1Outline.add(phaistosLabel1, JLayeredPane.PALETTE_LAYER); 
    
        maliaLabel1 = new JLabel();
        maliaLabel1.setBounds(850, 10, 60, 100); 
        player1Outline.add(maliaLabel1, JLayeredPane.PALETTE_LAYER); 

        zakrosLabel1 = new JLabel();
        zakrosLabel1.setBounds(950, 10, 60, 100); 
        player1Outline.add(zakrosLabel1, JLayeredPane.PALETTE_LAYER); 

        knossosLabel2 = new JLabel();
        knossosLabel2.setBounds(650, 10, 60, 100); 
        player2Outline.add(knossosLabel2, JLayeredPane.PALETTE_LAYER); 

        phaistosLabel2 = new JLabel();
        phaistosLabel2.setBounds(750, 10, 60, 100); 
        player2Outline.add(phaistosLabel2, JLayeredPane.PALETTE_LAYER); 

        maliaLabel2 = new JLabel();
        maliaLabel2.setBounds(850, 10, 60, 100); 
        player2Outline.add(maliaLabel2, JLayeredPane.PALETTE_LAYER); 

        zakrosLabel2 = new JLabel();
        zakrosLabel2.setBounds(950, 10, 60, 100); 
        player2Outline.add(zakrosLabel2, JLayeredPane.PALETTE_LAYER); 

    for (JButton button : player2HandButtons) {
        button.addActionListener(e -> {
            if (controller.getCurrentPlayer() == players.get(1)) {
            }
        });
    }

}








/**
 * Adds components to the player's outline panel.
 * 
 * @param playerOutline the panel to which components are added
 * @param player the player associated with the components
 * @param playerName the name of the player
 * @param playerHandButtons the list of buttons representing the player's hand
 * @pre playerOutline, player, playerName, and playerHandButtons are not null
 * @post components are added to the player's outline panel
 */
    private void addPlayerComponents(JPanel playerOutline, Player player, String playerName, List<JButton> playerHandButtons) {
        playerOutline.setLayout(null); 
    
        JButton playerFrescoesButton = new JButton("Frescoes");
        playerFrescoesButton.setBounds(1050, 10, 100, 25); 
        playerFrescoesButton.addActionListener(e -> {
            if (controller.getCurrentPlayer() != player) {
                JOptionPane.showMessageDialog(null, "It's not your turn!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            displayPlayerFrescoes(player);
        });
        playerOutline.add(playerFrescoesButton);
    
        JButton endTurnButton = new JButton("End Turn");
        endTurnButton.setBounds(1050, 40, 100, 25); 
        endTurnButton.addActionListener(e -> {
            if (controller.getCurrentPlayer() != player) {
                JOptionPane.showMessageDialog(null, "It's not your turn!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            controller.endTurn();
        });
        playerOutline.add(endTurnButton);
    
        JLabel playerScoreLabel = player.getScoreLabel(); 
        playerScoreLabel.setBounds(1050, 70, 100, 25);
        playerOutline.add(playerScoreLabel);
    
        JLabel playerStatuesLabel = new JLabel("Statues: " + model.countStatues(player));
        playerStatuesLabel.setBounds(1050, 95, 100, 25); 
        playerOutline.add(playerStatuesLabel);
    
        if (players.indexOf(player) == 0) {
            player1StatuesLabel = playerStatuesLabel;
        } else {
            player2StatuesLabel = playerStatuesLabel;
        }
    
        SnakeGoddess snakeGoddess = new SnakeGoddess("Snake Goddess Statue", 5); 
        String snakeGoddessIconPath = snakeGoddess.getImagePath(); 
        ImageIcon snakeGoddessIcon = new ImageIcon(new ImageIcon(snakeGoddessIconPath).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        JLabel snakeGoddessIconLabel = new JLabel(snakeGoddessIcon);
        snakeGoddessIconLabel.setBounds(1150, 95, 30, 30); 
        playerOutline.add(snakeGoddessIconLabel);
    
        for (int i = 0; i < 8; i++) {
            JButton cardButton = new JButton();
            cardButton.setBounds(10 + i * 70, 10, 60, 100); 
            cardButton.addMouseListener(new CardButtonListener(player, i));
            playerOutline.add(cardButton);
            playerHandButtons.add(cardButton); 
        }
    
        JLabel playerNameLabel = new JLabel(playerName);
        playerNameLabel.setBounds(10, 130, 100, 20);
        playerOutline.add(playerNameLabel);
    
        JLabel archaeologistsLabel = new JLabel("Archaeologists: " + player.getArchaeologistCount());
        archaeologistsLabel.setBounds(120, 130, 150, 20); 
        playerOutline.add(archaeologistsLabel);
    
        JLabel theseusLabel = new JLabel("Theseus: " + player.getTheseusCount());
        theseusLabel.setBounds(280, 130, 150, 20); 
        playerOutline.add(theseusLabel);
    
        if (controller.getPlayers().indexOf(player) == 0) {
            player1ArchaeologistsLabel = archaeologistsLabel;
            player1TheseusLabel = theseusLabel;
        } else {
            player2ArchaeologistsLabel = archaeologistsLabel;
            player2TheseusLabel = theseusLabel;
        }
    
        updatePlayerScore(player);
    
        playerOutline.revalidate();
        playerOutline.repaint();
    }








/**
 * Adds components representing palaces and rare findings to the player's outline panel.
 * 
 * @param playerOutline the panel to which components are added
 * @param xOffset the x offset for positioning the components
 * @param yOffset the y offset for positioning the components
 * @pre playerOutline is not null
 * @post components representing palaces and rare findings are added to the player's outline panel
 */
    private void addPalaceComponents(JPanel playerOutline, int xOffset, int yOffset) {
        String[] palaces = {"Knossos", "Phaistos", "Malia", "Zakros"};
        Color[] palaceColors = {Color.RED, Color.WHITE, Color.YELLOW, Color.BLUE};
        String[] rareFindings = {"Minoas Ring (Knossos)", "Phaistos Disc", "Malia Jewel", "Zakros Rhyton"};
        
    
        for (int i = 0; i < palaces.length; i++) {
            JLabel palaceLabel = new JLabel(palaces[i]);
            palaceLabel.setBounds(xOffset + i * 100, yOffset, 60, 100); 
            palaceLabel.setBorder(new LineBorder(palaceColors[i], 2)); 
            playerOutline.add(palaceLabel);
    

            JLabel rareFindingLabel = new JLabel();
            rareFindingLabel.setBounds(xOffset + i * 100, yOffset + 100, 30, 30); 
            RareFinding rareFinding = new RareFinding(rareFindings[i]); 
            String imagePath = rareFinding.getImagePath(); 
            rareFindingLabel.setIcon(new ImageIcon(createGrayImage(new ImageIcon(imagePath).getImage()).getScaledInstance(30, 30, Image.SCALE_SMOOTH))); 
            playerOutline.add(rareFindingLabel);
            rareFindingLabels.add(rareFindingLabel); 
            
            

    }   
}







/**
 * Updates the statues label for the specified player.
 * 
 * @param player the player whose statues label needs to be updated
 * @pre player is not null
 * @post the statues label for the specified player is updated
 */
public void updateStatuesLabel(Player player) {
    if (players.indexOf(player) == 0) {
        player1StatuesLabel.setText("Statues: " + model.countStatues(player));
    } else {
        player2StatuesLabel.setText("Statues: " + model.countStatues(player));
    }
}








/**
 * Displays the player's hand by updating the card buttons.
 * 
 * @param player the player whose hand is to be displayed
 * @pre player is not null
 * @post the player's hand is displayed with updated card buttons
 */
public void displayPlayerHand(Player player) {
    List<JButton> playerHandButtons = getPlayerHandButtons(player);
    List<Card> hand = player.getHand();

    for (int i = 0; i < playerHandButtons.size(); i++) {
        JButton cardButton = playerHandButtons.get(i);
        for (MouseListener listener : cardButton.getMouseListeners()) {
            cardButton.removeMouseListener(listener);
        }
        if (i < hand.size()) {
            Card card = hand.get(i);
            cardButton.setIcon(new ImageIcon(card.getIcon().getImage().getScaledInstance(60, 100, Image.SCALE_SMOOTH)));
            cardButton.setEnabled(true);
            cardButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (controller.getCurrentPlayer() != player) {
                        JOptionPane.showMessageDialog(null, "It's not your turn!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        handlePlayOrDiscardCard(player, card);
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        if (controller != null) {
                            controller.discardCard(player, card);
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    // Not used
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    // Not used
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // Not used
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Not used
                }
            });
        } else {
            cardButton.setIcon(null);
            cardButton.setEnabled(false);
        }
    }
}







/**
 * Updates the score label for the specified player.
 * 
 * @param player the player whose score label needs to be updated
 * @pre player is not null
 * @post the score label for the specified player is updated
 */
public void updatePlayerScore(Player player) {
    int totalScore = model.calculateTotalScore(player);
    JLabel playerScoreLabel = player.getScoreLabel();
    playerScoreLabel.setText("Score: " + totalScore);
}









/**
 * Updates the player hand buttons for both players.
 * 
 * @pre none
 * @post the hand buttons for both players are enabled and their hands are displayed
 */
public void updatePlayerHandButtons() {
    for (JButton button : player1HandButtons) {
        button.setEnabled(true); 
    }
    for (JButton button : player2HandButtons) {
        button.setEnabled(true); 
    }
    displayPlayerHand(players.get(0));
    displayPlayerHand(players.get(1));
}











/**
 * Returns the list of buttons representing the player's hand.
 * 
 * @param player the player whose hand buttons are to be returned
 * @pre player is not null
 * @post the list of buttons representing the player's hand is returned
 * @return the list of buttons representing the player's hand
 */
private List<JButton> getPlayerHandButtons(Player player) {
    if (controller.getPlayers().indexOf(player) == 0) {
        return getPlayer1HandButtons();
    } else {
        return getPlayer2HandButtons();
    }
}
    







/**
 * Displays the game board by updating the board grid with path labels and icons.
 * 
 * @pre none
 * @post the board grid is updated with path labels and icons, and the UI is refreshed
 */
public void displayBoard() {
        boardGrid.removeAll();
        Path[] paths = board.getPaths();
        for (int pathIndex = 0; pathIndex < paths.length; pathIndex++) {
            Path path = paths[pathIndex];
            for (int i = 0; i < path.getPositions().length; i++) {
                JLabel positionLabel = new JLabel();
                positionLabel.setHorizontalAlignment(SwingConstants.CENTER);
                positionLabel.setVerticalAlignment(SwingConstants.CENTER);
                positionLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                ImageIcon icon;
                if (i == 8) { 
                    icon = new ImageIcon(new ImageIcon(path.getImagePath(i)).getImage().getScaledInstance(220, 110, Image.SCALE_SMOOTH)); 
                    positionLabel.setPreferredSize(new Dimension(220, 110));
                } else { 
                    icon = new ImageIcon(new ImageIcon(path.getImagePath(i)).getImage().getScaledInstance(130, 110, Image.SCALE_SMOOTH));
                    positionLabel.setPreferredSize(new Dimension(130, 110));
                }
                positionLabel.setIcon(icon);

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = i;
                gbc.gridy = pathIndex;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.weightx = (i == 8) ? 2.0 : 1.0; 
                gbc.weighty = 1.0;

                boardGrid.add(positionLabel, gbc);
            }
        }
        boardGrid.revalidate();
        boardGrid.repaint();
}








/**
 * Displays the player areas by updating the hands of both players.
 * 
 * @pre none
 * @post the hands of both players are displayed
 */
public void displayPlayerAreas() {
        displayPlayerHand(players.get(0));
        displayPlayerHand(players.get(1));
}









/**
 * Displays the player's collected frescoes.
 * 
 * @param player the player whose frescoes are to be displayed
 * @pre player is not null
 * @post a dialog is displayed showing the player's collected frescoes
 */
private void displayPlayerFrescoes(Player player) {
        List<Finding> findings = player.getFindings();
        List<Finding> frescoes = new ArrayList<>();
    
        for (Finding finding : findings) {
            if (finding instanceof Fresco) {
                frescoes.add(finding);
            }
        }
    
        if (frescoes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No frescoes collected yet!", "Frescoes", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
        for (Finding fresco : frescoes) {
            JLabel label = new JLabel();
            label.setText(fresco.getDescription());
            label.setIcon(new ImageIcon(fresco.getImagePath()));
            panel.add(label);
        }
    
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(400, 300));
    
        JOptionPane.showMessageDialog(null, scrollPane, "Collected Frescoes", JOptionPane.INFORMATION_MESSAGE);
}












/**
 * Displays a dialog with information about a finding.
 * 
 * @param finding the finding to be displayed
 * @param title the title of the dialog
 * @pre finding and title are not null
 * @post a dialog is displayed showing the finding's information
 */
public void showFindingDialog(Finding finding, String title) {
        ImageIcon findingImage = new ImageIcon(finding.getImagePath());
        String specificText = finding.getSpecificText(); 
    
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("<html>" + title + ": " + finding.getDescription() + "<br>" + specificText + "</html>");
        label.setIcon(findingImage);
        panel.add(label);
        panel.setPreferredSize(new Dimension(500, 500)); 
    
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(400, 400)); 
    
        JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
}







/**
 * Displays a dialog when a palace is reached.
 * 
 * @param palaceName the name of the palace
 * @param message the message to be displayed
 * @pre palaceName and message are not null
 * @post a dialog is displayed showing the message for the reached palace
 */
    public void showPalaceReachedDialog(String palaceName, String message) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("<html>" + message + "</html>");
        panel.add(label);
        panel.setPreferredSize(new Dimension(500, 500)); 
    
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(400, 400)); 
    
        JOptionPane.showMessageDialog(null, scrollPane, palaceName, JOptionPane.INFORMATION_MESSAGE);
    }







/**
 * Displays a dialog to confirm if a finding should be destroyed.
 * 
 * @param finding the finding to be destroyed
 * @pre finding is not null
 * @post a dialog is displayed to confirm the destruction of the finding
 * @return true if the finding should be destroyed, false otherwise
 */
    public boolean showDestroyFindingDialog(Finding finding) {
        Object[] options = {"Yes", "No"};
        int response = JOptionPane.showOptionDialog(null,
                "Do you want to destroy this finding?",
                "Destroy Finding",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        return response == JOptionPane.YES_OPTION;
    }






/**
 * Creates a gray image from the given image.
 * 
 * @param image the original image
 * @pre image is not null
 * @post a gray version of the image is created and returned
 * @return the gray version of the image
 */
    private Image createGrayImage(Image image) {
        GrayFilter filter = new GrayFilter(true, 50);
        ImageProducer producer = new FilteredImageSource(image.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(producer);
    }









    /**
 * Updates the icon for a rare finding for the specified player.
 * 
 * @param player the player who has the rare finding
 * @param rareFindingDescription the description of the rare finding
 * @pre player and rareFindingDescription are not null
 * @post the icon for the rare finding is updated for the specified player
 */
    public void updateRareFindingIcon(Player player, String rareFindingDescription) {
        int playerIndex = players.indexOf(player);
        int palaceIndex = -1;
    
        switch (rareFindingDescription) {
            case "Minoas Ring (Knossos)":
                palaceIndex = 0;
                break;
            case "Phaistos Disc":
                palaceIndex = 1;
                break;
            case "Malia Jewel":
                palaceIndex = 2;
                break;
            case "Zakros Rhyton":
                palaceIndex = 3;
                break;
        }
    
        if (palaceIndex != -1) {
            JLabel rareFindingLabel = rareFindingLabels.get(palaceIndex + playerIndex * 4); 
            RareFinding rareFinding = new RareFinding(rareFindingDescription); 
            String imagePath = rareFinding.getImagePath(); 
            rareFindingLabel.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))); 
            rareFindingLabel.revalidate();
            rareFindingLabel.repaint();
        }
    }






/**
 * Updates the displayed Knossos card for the specified player.
 * 
 * @param cardIcon the icon of the card to be displayed
 * @param isKnossos true if the card is a Knossos card, false otherwise
 * @param playerIndex the index of the player (0 for Player 1, 1 for Player 2)
 * @pre cardIcon is not null, playerIndex is valid
 * @post the displayed Knossos card for the specified player is updated
 */
    public void updatePlayedKnossosCard(ImageIcon cardIcon, boolean isKnossos, int playerIndex) {
        JLabel knossosLabel = (playerIndex == 0) ? knossosLabel1 : knossosLabel2;
        if (isKnossos) {
            knossosLabel.setIcon(cardIcon);
            knossosLabel.setBorder(new LineBorder(Color.red, 2)); 
        } else {
            knossosLabel.setIcon(null);
            knossosLabel.setBorder(null); 
        }
        knossosLabel.revalidate();
        knossosLabel.repaint();
    }







/**
 * Updates the displayed Phaistos card for the specified player.
 * 
 * @param cardIcon the icon of the card to be displayed
 * @param isPhaistos true if the card is a Phaistos card, false otherwise
 * @param playerIndex the index of the player (0 for Player 1, 1 for Player 2)
 * @pre cardIcon is not null, playerIndex is valid
 * @post the displayed Phaistos card for the specified player is updated
 */
    public void updatePlayedPhaistosCard(ImageIcon cardIcon, boolean isPhaistos, int playerIndex) {
        JLabel phaistosLabel = (playerIndex == 0) ? phaistosLabel1 : phaistosLabel2;
        if (isPhaistos) {
            phaistosLabel.setIcon(cardIcon);
            phaistosLabel.setBorder(new LineBorder(Color.red, 2)); 
        } else {
            phaistosLabel.setIcon(null);
            phaistosLabel.setBorder(null);
        }
        phaistosLabel.revalidate();
        phaistosLabel.repaint();
    }






/**
 * Updates the displayed Malia card for the specified player.
 * 
 * @param cardIcon the icon of the card to be displayed
 * @param isMalia true if the card is a Malia card, false otherwise
 * @param playerIndex the index of the player (0 for Player 1, 1 for Player 2)
 * @pre cardIcon is not null, playerIndex is valid
 * @post the displayed Malia card for the specified player is updated
 */
    public void updatePlayedMaliaCard(ImageIcon cardIcon, boolean isMalia, int playerIndex) {
        JLabel maliaLabel = (playerIndex == 0) ? maliaLabel1 : maliaLabel2;
        if (isMalia) {
            maliaLabel.setIcon(cardIcon);
            maliaLabel.setBorder(new LineBorder(Color.red, 2)); 
        } else {
            maliaLabel.setIcon(null);
            maliaLabel.setBorder(null); 
        }
        maliaLabel.revalidate();
        maliaLabel.repaint();
    }
    




/**
 * Updates the displayed Zakros card for the specified player.
 * 
 * @param cardIcon the icon of the card to be displayed
 * @param isZakros true if the card is a Zakros card, false otherwise
 * @param playerIndex the index of the player (0 for Player 1, 1 for Player 2)
 * @pre cardIcon is not null, playerIndex is valid
 * @post the displayed Zakros card for the specified player is updated
 */
    public void updatePlayedZakrosCard(ImageIcon cardIcon, boolean isZakros, int playerIndex) {
        JLabel zakrosLabel = (playerIndex == 0) ? zakrosLabel1 : zakrosLabel2;
        if (isZakros) {
            zakrosLabel.setIcon(cardIcon);
            zakrosLabel.setBorder(new LineBorder(Color.red, 2)); 
        } else {
            zakrosLabel.setIcon(null);
            zakrosLabel.setBorder(null); 
        }
        zakrosLabel.revalidate();
        zakrosLabel.repaint();
    }








/**
 * Adds a pawn to the board at the specified path and position.
 * 
 * @param pawn the pawn to be added
 * @param pathIndex the index of the path where the pawn will be placed
 * @param playerIndex the index of the player (0 for Player 1, 1 for Player 2)
 * @pre pawn is not null, pathIndex and playerIndex are valid
 * @post the pawn is added to the board at the specified path and position
 */
    public void addPawnToBoard(Pawn pawn, int pathIndex, int playerIndex) {
        Path path = board.getPaths()[pathIndex];
        int position = pawn.getPosition();
        ImageIcon pawnIcon = pawn.isHidden() ? pawn.getHiddenImageIcon() : pawn.getImageIcon();
        JLabel pawnLabel = new JLabel(pawnIcon);

        pawn.setHidden(true);
    
      //  System.out.println("Adding pawn to board: " + pawn.getType());
    
        int stepWidth = 100; 
        int stepHeight = 110; 
        int palaceWidth = 220;
    
        int x = 250 + position * stepWidth;
      //  System.out.println("Initial x calculation: 250 + " + position + " * " + stepWidth + " = " + x);
        if (position == 8) {
            x = 250 + 7 * stepWidth + palaceWidth - stepWidth;
           // System.out.println("Adjusted x for position 8: 250 + 7 * " + stepWidth + " + " + palaceWidth + " - " + stepWidth + " = " + x);
        }
    
        int y = 50 + pathIndex * stepHeight;
     //   System.out.println("Initial y calculation: 50 + " + pathIndex + " * " + stepHeight + " = " + y);
    
        if (pathIndex > 0) {
            y += 40; 
          //  System.out.println("Adjusted y for pathIndex > 0: " + y);
        }
    
        if (playerIndex == 1) {
            y += 30; 
         //   System.out.println("Adjusted y for Player 2: " + y);
        }
    
        pawnLabel.setBounds(x, y, 30, 30); 
    
     //   System.out.println("Pawn coordinates: x = " + x + ", y = " + y);
    
        if (playerIndex == 0) {
            pawnLabel.setBorder(new LineBorder(Color.GREEN, 2));
        } else {
            pawnLabel.setBorder(new LineBorder(Color.PINK, 2));
        }
    if (pawn.isImmobilized()) {
        pawnLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
    }
    
        int pawnIndex = pawns.indexOf(pawn);
        pawnLabel.setName("pawn-" + pawnIndex + "-" + pathIndex + "-" + position);
    
        boardPanel.add(pawnLabel, JLayeredPane.MODAL_LAYER);
        boardPanel.revalidate();
        boardPanel.repaint();
    
        updateRemainingPawns(controller.getPlayers().get(playerIndex));
    }
    







/**
 * Moves a pawn on the board to the specified path and position.
 * 
 * @param pawn the pawn to be moved
 * @param pathIndex the index of the path where the pawn will be placed
 * @param playerIndex the index of the player (0 for Player 1, 1 for Player 2)
 * @pre pawn is not null, pathIndex and playerIndex are valid
 * @post the pawn is moved on the board to the specified path and position
 */
    public void movePawnOnBoard(Pawn pawn, int pathIndex, int playerIndex) {
        Path path = board.getPaths()[pathIndex];
        int position = pawn.getPosition();
        ImageIcon pawnIcon = pawn.isHidden() ? pawn.getHiddenImageIcon() : pawn.getImageIcon();
        JLabel pawnLabel = new JLabel(pawnIcon);
    
      //  System.out.println("Moving pawn on board: " + pawn.getType());
    
        int stepWidth = 100;
        int stepHeight = 110;
        int palaceWidth = 220;
    
        int x = 250 + position * stepWidth;
      //  System.out.println("Initial x calculation: 250 + " + position + " * " + stepWidth + " = " + x);
        if (position == 8) {
            x = 250 + 7 * stepWidth + palaceWidth - stepWidth;
          //  System.out.println("Adjusted x for position 8: 250 + 7 * " + stepWidth + " + " + palaceWidth + " - " + stepWidth + " = " + x);
        }
    
        int y = 50 + pathIndex * stepHeight;
      //  System.out.println("Initial y calculation: 50 + " + pathIndex + " * " + stepHeight + " = " + y);
    
        if (pathIndex > 0) {
            y += 40;
          //  System.out.println("Adjusted y for pathIndex > 0: " + y);
        }
    
        if (playerIndex == 1) {
            y += 30;
          //  System.out.println("Adjusted y for Player 2: " + y);
        }
    
        pawnLabel.setBounds(x, y, 30, 30);
       // System.out.println("Pawn coordinates: x = " + x + ", y = " + y);
    
        if (playerIndex == 0) {
            pawnLabel.setBorder(new LineBorder(Color.GREEN, 2));
        } else {
            pawnLabel.setBorder(new LineBorder(Color.PINK, 2));
        }
        
    if (pawn.isImmobilized()) {
        pawnLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
    }
        int pawnIndex = pawns.indexOf(pawn);
        pawnLabel.setName("pawn-" + pawnIndex + "-" + pathIndex + "-" + position);
    
        for (Component component : boardPanel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getName() != null && label.getName().startsWith("pawn-" + pawnIndex + "-" + pathIndex + "-")) {
                    boardPanel.remove(label);
                  //  System.out.println("Removed previous pawn label: " + label.getName());
                }
            }
        }
    
        boardPanel.add(pawnLabel, JLayeredPane.MODAL_LAYER);
        boardPanel.revalidate();
        boardPanel.repaint();
    
//        System.out.println("Pawn index in the list: " + pawnIndex);
    }










    /**
 * Updates the appearance of a pawn on the board.
 * 
 * @param pawn the pawn whose appearance is to be updated
 * @param pathIndex the index of the path where the pawn is located
 * @param playerIndex the index of the player (0 for Player 1, 1 for Player 2)
 * @pre pawn is not null, pathIndex and playerIndex are valid
 * @post the appearance of the pawn is updated on the board
 */
    public void updatePawnAppearance(Pawn pawn, int pathIndex, int playerIndex) {
        for (Component component : boardPanel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getIcon() == pawn.getImageIcon()) {
                    if (pawn.isImmobilized()) {
                        label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                    } else {
                        if (playerIndex == 0) {
                            label.setBorder(new LineBorder(Color.GREEN, 2));
                        } else {
                            label.setBorder(new LineBorder(Color.PINK, 2));
                        }
                    }
                    repaint();
                    break;
                }
            }
        }
    }
   
    








/**
 * Updates the remaining pawns labels for the specified player.
 * 
 * @param player the player whose remaining pawns are to be updated
 * @pre player is not null
 * @post the remaining pawns labels for the specified player are updated
 */
public void updateRemainingPawns(Player player) {
    int playerIndex = controller.getPlayers().indexOf(player);
    int archaeologistCount = player.getArchaeologistCount();
    int theseusCount = player.getTheseusCount();

 //   System.out.println("Updating remaining pawns for Player " + (playerIndex + 1));
  //  System.out.println("Archaeologists: " + archaeologistCount);
   // System.out.println("Theseus: " + theseusCount);

    if (playerIndex == 0) {
        player1ArchaeologistsLabel.setText("Archaeologists: " + archaeologistCount);
        player1TheseusLabel.setText("Theseus: " + theseusCount);
    } else {
        player2ArchaeologistsLabel.setText("Archaeologists: " + archaeologistCount);
        player2TheseusLabel.setText("Theseus: " + theseusCount);
    }
}









/**
 * Handles the action of playing or discarding a card.
 * 
 * @param player the player who is playing or discarding the card
 * @param card the card to be played or discarded
 * @pre player and card are not null
 * @post the card is played or discarded and the remaining pawns are updated
 */
private void handlePlayOrDiscardCard(Player player, Card card) {
    if (card instanceof NumberCard) {
        String palace = ((NumberCard) card).getPalace();
        Path path = model.getBoard().getPathByPalace(palace);
        Pawn pawn = player.getPawnInPath(palace); // Use getPawnInPath instead of getPawnOnPath

        if (pawn != null && pawn.getPosition() == 8) {
            JOptionPane.showMessageDialog(null, "Invalid move! The pawn is already at the palace.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        controller.playCard(player, card);
    } else {
        controller.playCard(player, card);
    }
    updateRemainingPawns(player);
    updatePlayerHandButtons(); // Update hand buttons after playing or discarding a card
}








/**
 * Prompts the player to choose a pawn to place on the path.
 * 
 * @param player the player who is choosing the pawn
 * @pre player is not null
 * @post the chosen pawn is returned, or null if no pawn is chosen
 * @return the chosen pawn, or null if no pawn is chosen
 */
    public Pawn promptPlayerToChoosePawn(Player player) {
        String[] options = {"Archaeologist", "Theseus"};
        int choice = JOptionPane.showOptionDialog(null, "What do you want to put in this path?", "Choose a Pawn",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    
     //   System.out.println("Dialog box promptplayer: Player choice: " + (choice == 0 ? "Archaeologist" : "Theseus"));
    
        if (choice == JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(null, "You must choose a pawn.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    
        Pawn pawn;
        if (choice == 0) {
            pawn = player.getArchaeologist();
          //  System.out.println("Created pawn: Archaeologist");
        } else {
            pawn = player.getTheseus();
         //   System.out.println("Created pawn: Theseus");
        }
    
        return pawn;
    }






/**
 * Displays a dialog indicating an invalid move.
 * 
 * @param message the message to be displayed
 * @pre message is not null
 * @post a dialog is displayed indicating an invalid move
 */
    public void displayInvalidMove(String message) {
        JOptionPane.showMessageDialog(this, "Invalid move!", "Error", JOptionPane.ERROR_MESSAGE);
    }





/**
 * Displays a dialog indicating that the deck is empty.
 * 
 * @pre none
 * @post a dialog is displayed indicating that the deck is empty
 */
    public void displayDeckEmpty() {
        JOptionPane.showMessageDialog(this, "The deck is empty!", "Info", JOptionPane.INFORMATION_MESSAGE);
    }





/**
 * Updates the label displaying the number of remaining cards.
 * 
 * @param remainingCards the number of remaining cards
 * @pre remainingCards is a non-negative integer
 * @post the label displaying the number of remaining cards is updated
 */
    public void updateRemainingCards(int remainingCards) {
        remainingCardsLabel.setText("Remaining Cards: " + remainingCards);
    }





/**
 * Updates the label displaying the current player's name.
 * 
 * @param player the current player
 * @pre player is not null
 * @post the label displaying the current player's name is updated
 */
    public void displayCurrentPlayer(Player player) {
        currentPlayerInfoLabel.setText("Playing: " + player.getName());
      //  System.out.println("Updated current player label to: " + player.getName());
    }






/**
 * Returns the board panel.
 * 
 * @pre none
 * @post the board panel is returned
 * @return the board panel
 */ 
    public JLayeredPane getBoardPanel() {
        return boardPanel;
    }
/* X
    private int countStatues(Player player) {
        int count = 0;
        for (Finding finding : player.getFindings()) {
            if (finding instanceof SnakeGoddess) {
                count++;
            }
        }
        return count;
    }
*/
    
}