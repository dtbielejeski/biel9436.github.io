import java.util.ArrayList;
import java.util.HashMap;

/**
 * This contains all the methods to construct a board object and 
 * move things around the board
 * @author Daniel
 *
 */
public class Board {
	/** Board is a 2D array where each object in it is a cell at the array coordinates*/
	private Cell[][] board;
	/** Height is the height of the board*/
	private int height;
	/** Width is the width of the board*/
	private int width;
	/**This is whether the Jarvis has been hugged or not */
	private boolean hugged;
	
	/** elementPlace is a hashmap with each element mapped to a specific cell*/
	private HashMap<Boardable, Cell> elementPlace;
	/**
	 * The constructor for board
	 * @param height the height of the board must be between 1-100
	 * @param width the width of the board must be between 1-100
	 */
	public Board(int height, int width) {
		if(height > 100 || height < 1 || width > 100 || width < 1) {
			throw new IllegalArgumentException("Height and width must be between 1-100");
		}
		elementPlace = new HashMap<Boardable, Cell>();
		this.height = height;
		this.width = width;
		board = new Cell[height][width];
		for(int rowIndex = 0; rowIndex < height; rowIndex++) {
			for(int colIndex = 0; colIndex < width; colIndex++) {
				board[rowIndex][colIndex] = new Cell(rowIndex, colIndex);
			}
		}
	}
	/**
	 * This moves elements around the board
	 * @param dir the desired movement direction
	 * @param elem the element that is being moved
	 * @return true if you can move, false if not
	 */
	public synchronized boolean move(Direction dir, Boardable elem) {	
		if(elementPlace.isEmpty() || !elementPlace.containsKey(elem) ) {
			throw new IllegalArgumentException("Element not contained in the board");
		}
		switch (dir) {
		case UP_LEFT:
			if(isValid(elem, -1,-1)) {
				elementPlace.get(elem).removeElement(elem);
				placeElement(elem, elementPlace.get(elem).row-1, elementPlace.get(elem).col-1);
			}else {
				return false;
			}
			break;
		case UP:
			if(isValid(elem, -1,0)) {
				elementPlace.get(elem).removeElement(elem);
				placeElement(elem, elementPlace.get(elem).row-1, elementPlace.get(elem).col);
			}else {
				return false;
			}
			break;
		case UP_RIGHT:
			if(isValid(elem, -1,1)) {
				elementPlace.get(elem).removeElement(elem);
				placeElement(elem, elementPlace.get(elem).row-1, elementPlace.get(elem).col+1);
			}else {
				return false;
			}
			break;
		case LEFT:
			if(isValid(elem, 0,-1)) {
				elementPlace.get(elem).removeElement(elem);
				placeElement(elem, elementPlace.get(elem).row, elementPlace.get(elem).col-1);
			}else {
				return false;
			}
			break;
		case RIGHT:
			if(isValid(elem, 0, 1)) {
				elementPlace.get(elem).removeElement(elem);
				placeElement(elem, elementPlace.get(elem).row, (elementPlace.get(elem).col)+1);
			}else {
				return false;
			}
			break;
		case DOWN_LEFT:
			if(isValid(elem, 1,-1)) {
				elementPlace.get(elem).removeElement(elem);
				placeElement(elem, elementPlace.get(elem).row+1, elementPlace.get(elem).col-1);
			}else {
				return false;
			}
			break;
		case DOWN:
			if(isValid(elem, 1,0)) {
				elementPlace.get(elem).removeElement(elem);
				placeElement(elem, elementPlace.get(elem).row+1, elementPlace.get(elem).col);
			}else {
				return false;
			}
			break;
		case DOWN_RIGHT:
			if(isValid(elem, 1,1)) {
				elementPlace.get(elem).removeElement(elem);
				placeElement(elem, elementPlace.get(elem).row+1, elementPlace.get(elem).col+1);
			}else {
				return false;
			}
			break;
		default: 
			return false;
		}
		return true;

	}
	/**
	 * This places an element on the board
	 * @param elem the element to be placed on the board
	 * @param row the row to be placed in
	 * @param col the column to be placed in
	 * @return true if the element can be placed on the board
	 */
	public synchronized boolean placeElement(Boardable elem, int row, int col) {
		if (row >= height || col >= width || row < 0 || col < 0) {
			return false;

		}
		if (board[row][col].elements == null) {
			elementPlace.put(elem, board[row][col]);
			board[row][col].addElement(elem);
			return true;
		} else {
			for (int i =0; i<board[row][col].elements.size(); i++) {
				if (!board[row][col].elements.get(i).share(elem)) {
					return false;
				}

			}
			elementPlace.put(elem, board[row][col]);
			board[row][col].addElement(elem);
			return true;
		}

	}
	/**
	 * This removes an element from the hashmap
	 * @param elem the element to be removed
	 * @return true if able to be removed, false if not
	 */
	public synchronized boolean removeElement(Boardable elem) {
		if(!elementPlace.containsKey(elem)) {
			return false;
		}
		elementPlace.get(elem).removeElement(elem);
		elementPlace.remove(elem);
		return true;
	}
	/**
	 * This prints the board
	 */
	public synchronized void printBoard() {
		for(int rowIndex = 0; rowIndex < height; rowIndex++) {
			for(int colIndex = 0; colIndex < width; colIndex++) {
				System.out.print(board[rowIndex][colIndex]);
			}
			System.out.println();
		}
	}
	/**
	 * This checks to see if a move can be made
	 * @param elem the element to be moved
	 * @param shiftRow the row to be moved to
	 * @param shiftCol the column to be move to
	 * @return true if you can move the element to the cell
	 */
	private synchronized boolean isValid(Boardable elem, int shiftRow, int shiftCol) {
		int rowNum = elementPlace.get(elem).row;
		int colNum = elementPlace.get(elem).col;
		if(rowNum + shiftRow > height-1 || rowNum + shiftRow < 0 || colNum + shiftCol > width-1 || colNum + shiftCol < 0) {
			return false;
		}
		return true;
	}
	/**
	 *  Gets the row of the elem
	 * @param elem the element we want the location of
	 * @return the integer value of the row
	 */
	public int getRow(Boardable elem) {
		if(!elementPlace.containsKey(elem)) {
			throw new IllegalArgumentException("Element not contained in board");
		}
		return elementPlace.get(elem).row;
	}
	/**
	 * This gets the column of the element
	 * @param elem the element we want the column of
	 * @return the column the element is in
	 */
	public int getColumn(Boardable elem) {
		if(!elementPlace.containsKey(elem)) {
			throw new IllegalArgumentException("Element not on the board");
		}
		return elementPlace.get(elem).col;
	}
	/**
	 * This sets the hugged boolean
	 * @param hugged the value we want the hugged boolean to be
	 */
	public synchronized void setHugged(boolean hugged) {
		this.hugged = hugged;
	}
	/**
	 * This returns if the Jarvis has been hugged
	 * @return true if it has, false if not
	 */
	public boolean beenHugged() {
		return this.hugged;
	}
	/**
	 * This is a nested class in board. Contains all the methods a cell needs
	 * @author Daniel
	 *
	 */
 	private class Cell{
		/** The row the cell is in*/
		private int row;
		/** The column the cell is in*/
		private int col;
		/** Says if this cell is visible or not*/
		private boolean isVisible;
		/** An arraylist of the elements at the cell location*/
		private ArrayList<Boardable> elements = new ArrayList<Boardable>();
		
		/**
		 * The constructor for Cell
		 * @param row the row the cell is in
		 * @param col the column the cell is in
		 */
		public Cell(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		/**
		 * This adds an element at the cell
		 * @param elem the element to be added
		 */
		public void addElement(Boardable elem) {
			if(!isVisible) {
				isVisible = elem.isVisible();
			}
			elements.add(elem);
		}
		/**
		 * This removes an element from the cell
		 * @param elem the element to be removed
		 * @return true if it is removed, false if not
		 */
		public synchronized boolean removeElement(Boardable elem) {
			if(elements.isEmpty()) {
				return false;
			}
			else {
				elements.remove(elem);
				return true;
			}
		}
		/**
		 * The toString for cell
		 * Prints # if not visible
		 * Prints nothing if no element
		 * Prints the toString of the element if it contains an element
		 */
		public String toString() {
			if(isVisible) {
				if(elements.isEmpty()) {
					return " ";
				}
				return elements.get(elements.size()-1).toString();
			}
			else {
				return "#";
			}
		}	
	}
	
}
