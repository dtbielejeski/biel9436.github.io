import java.util.Random;
/**
 * The methods and instance variables for the Jarvis
 * @author Daniel
 *
 */
public class Jarvis extends Mobile {
	/**A random object to generate random values	 */
	Random rand = new Random();
	/**An array of elements	 */
	Direction[] directions = Direction.values();
	/**
	 * A counter for which turn Jarvis is on	 */
	private int curMove = 1;
	/** The constant time delay for Jarvis	 */
	private final long timeDelay = 500;
	/*This is for how frequently Jarvis lays traps*/
	private int trapNum = 6;
	
	/**
	 * The constructor Jarvis, places Jarvis on the board
	 * @param board the board we are placing on
	 */
	public Jarvis(Board board) {
		super(board);
		board.placeElement(this, rand.nextInt(10), rand.nextInt(10));
	}

	@Override
	protected void move() {
		while(!board.move(directions[rand.nextInt(directions.length)], this)) {
			
		}
		if(this.curMove%trapNum == 0) {
			curMove = 0;
			layTrap();
		}
	}
	
	/**
	 * This lays a trap on the board
	 */
	private void layTrap() {
		int[][] directions = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
		HomeworkTrap temp = new HomeworkTrap(this.board);
		int i = 0;
		while(i < 8 && !board.placeElement(temp, board.getRow(this) + directions[i][0], board.getColumn(this)+ directions[i][1])) {
			i++;
		}
	}
	/**
	 * The run to implement Runnable, calls move repeatedly until the game ends
	 */
	public void run() {
		while(!this.board.beenHugged()) {
			try {
				Thread.sleep(timeDelay);
			} catch (InterruptedException e) {
				System.out.println("A mysterious error has occurred");
			}
			move();
			this.curMove++;
		}
	}
	/**
	 * Always returns true. If it is a player it sets the board beenHugged variable to true
	 * This will end the game
	 */
	public boolean share(Boardable elem) {
		if(elem instanceof Player) {
			board.setHugged(true);
			return true;
		}
		return true;
		
	}

	@Override
	public boolean isVisible() {
		return false;
	}
	
	@Override
	public String toString() {
		return "?";
	}
}
