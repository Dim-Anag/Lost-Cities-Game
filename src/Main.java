import model.Board;
import model.GameModel;
import model.Player;
import view.GameView;
import controller.GameController;

import java.util.ArrayList;
import java.util.List;




/**
 * The Main class initializes and starts the game.
 */
public class Main {
   
   
   /**
     * The main method to start the game.
     * 
     * @param args command-line arguments
     * @pre none
     * @post the game is initialized and started
     */
    public static void main(String[] args) {
        Board board = new Board();
        List<Player> players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));

        GameModel model = new GameModel(board, players);

        GameController controller = new GameController(model, null);

        model.setController(controller);

        controller.setCurrentPlayer(players.get(0));

        GameView view = new GameView(board, players, model); 
        view.setController(controller); 

        controller.setView(view);

        model.setView(view);

        view.setVisible(true);

        controller.startGame();
/* DEBUG 
        for (Player player : players) {
            System.out.println(player.getName() + "'s hand: " + player.getHand());
        }
       */   
      }
}