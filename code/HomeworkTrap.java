/**
 * This contains all the variables and methods for the Homework Trap object
 * @author Daniel
 *
 */
public class HomeworkTrap implements Boardable {
	/**	This is the board the homework trap is placed on */
	private Board board;
	
	/**
	 * The constructor for HomeworkTrap
	 * @param board the board we are using
	 */
	public HomeworkTrap(Board board) {
		this.board = board;
	}
	
	@Override
	public boolean isVisible() {
		return false;
	}

	@Override
	public boolean share(Boardable elem) {
		if(elem instanceof Mobile) {
			if(elem instanceof Player) {
				System.out.println("You have hit a homework trap!");
				((Player) elem).setDelay((long) 5000);
				board.removeElement(this);
			}
			return true;
		}
		return false;
	}
	/**
	 * The toString method for HomeWorkTrap return ""
	 */
	public String toString(){
		return " ";
	}

}
