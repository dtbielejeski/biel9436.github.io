/**
 * This interface contains the methods for Boardable objects
 * @author Daniel
 *
 */
public interface Boardable {
	/**
	 * This returns true or false depending on if the object is visible or not
	 * @return true if visible, false if invisible
	 */
	public boolean isVisible();
	
	/**
	 * This functions determines what elements this element can share a cell with
	 * @param elem the element we are checking
	 * @return true if you can share with it, false if not
	 */
	public boolean share(Boardable elem);
	
	/**
	 * The toString for each element
	 * @return the given string we want to print
	 */
	public String toString();
}
