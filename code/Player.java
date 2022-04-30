import java.io.IOException;
import java.util.Scanner;

public class Player extends Mobile {
	/** The scanner for user input for the player */
	private Scanner input = new Scanner(System.in);
	/** The amount of delay for the player	 */
	private long delayTime;
	
	/**
	 * The constructor for the player. Places the player on the board and then prints the board
	 * @param board the board to place the player
	 */
	public Player(Board board) {
		super(board);
		board.placeElement(this, 0, 0);
		board.printBoard();
	}
	/**
	 * The move function for players
	 * Utilizes a switch statement for each separate direction
	 */
	protected void move() {
		delay();
		
		String move = input.nextLine();
		
		switch(move) {
		case "q":
			board.move(Direction.UP_LEFT, this);
			break;
		case "w":
			board.move(Direction.UP, this);
			break;
		case "e":
			board.move(Direction.UP_RIGHT, this);
			break;
		case "a":
			board.move(Direction.LEFT, this);
			break;
		case "d":
			board.move(Direction.RIGHT, this);
			break;
		case "z":
			board.move(Direction.DOWN_LEFT, this);
			break;
		case "x":
			board.move(Direction.DOWN, this);
			break;
		case "c":
			board.move(Direction.DOWN_RIGHT, this);
			break;
		case "s":
			break;
		default:
		}
		board.printBoard();
	}
	/**
	 * The runnable for the player. 
	 */
	public void run() {
		while(!this.board.beenHugged()) {
			move();
		}
		System.out.println("You have soothed the angry Jarvis!");

	}
	/**
	 * This sets a delay on the players move
	 * @param time the amount of time for the delay
	 */
	public void setDelay(Long time) {
		this.delayTime = time;
	}
	/**
	 * This delays the player move
	 * Waits a specified amount of time
	 */
	private void delay() {
		try {
			Thread.sleep(this.delayTime);
			try {
				while(System.in.available() >0) {
					int buffer = System.in.available();
					byte x [] = new byte[buffer];
					System.in.read(x);
				}
			} catch (IOException e) {

			}
		} catch (InterruptedException e) {
			System.out.println("A mysterious error has occurred");
		}
		this.delayTime = 0;
	}
	/**
	 * This always return false because the player cannot share cells with other things
	 */
	public boolean share(Boardable elem) {
		return false;
	}

	@Override
	public boolean isVisible() {
		return true;
	}
	
	/**
	 * The to string for the player, prints "*"
	 */
	public String toString() {
		return "*";
	}
}
