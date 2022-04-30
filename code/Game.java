import java.util.Scanner;

/**
 * The main driver for the game. Starts threads for Jarvis and Player
 * @author Daniel
 *
 */
public class Game {
	/**
	 * The main method for Hug the Angry Jarvis
	 * @param args
	 */
	public static void main(String[] args) {
				Board easyBoard = new Board(10,10);
				Jarvis j = new Jarvis(easyBoard);
				Player p = new Player(easyBoard);
				Thread t1 = new Thread(j);
				Thread t2 = new Thread(p);
				t1.start();
				t2.start();

	}

}
