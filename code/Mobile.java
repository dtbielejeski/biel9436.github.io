/**
 * This abstract class has the methods for all the Mobile types in the game
 * @author Daniel
 *
 */
public abstract class Mobile implements Boardable, Runnable {
	/**
	 * The board for the game
	 */
	protected Board board;
	/**
	 * The constructor for the Mobile, 
	 * @param board the board we will be using for the class
	 */
	public Mobile(Board board) {
		this.board = board;
	}
	/**
	 * The method to move the object around
	 */
	protected abstract void move();
	
	@Override
	public abstract void run();

	@Override
	public abstract boolean isVisible();

	@Override
	public abstract boolean share(Boardable elem);
	

}
