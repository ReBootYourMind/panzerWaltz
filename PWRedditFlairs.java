/**
 * 
 */
package panzerWaltz;

import java.util.List;

/**
 * 6.4.2016
 * @author ReBootYourMind
 *
 */
public class PWRedditFlairs {

	private List<FlairImage> flairit;
	private PWRedditFlairsGUI redditGUI;
	
	/**
	 * konstruktori, jolla tehd‰‰ yhteys GUI:n kanssa
	 * @param gui gui that created this
	 */
	public PWRedditFlairs(PWRedditFlairsGUI gui) {
		redditGUI = gui;
		//TODO make the collection
	}
	
	
	/**
	 * Finds the desired files from the locations given
	 * @param first named location
	 * @param second nameless location
	 * @return how many was found
	 */
	public int findFiles(String first, String second){
		//TODO make finding
		//search the folders and add them to the collection
		//look for the number in the file first and then input it into the slot of the number
		//so that a file with 182 will have an index number of 182 in the collection.
		return -1;
	}
	
	/**
	 * unohtaa etsityt kuvat, jotta etsiminen voidaan tehd‰ uudestaan tyhj‰st‰
	 * 
	 * Forgets all images so that finding can be done from a clean table
	 * @return did it work
	 */
	public boolean forget() {
		//TODO make the forget
		//make a new collection
		return false;
	}
	
	/**
	 * nime‰‰ kuvat
	 * names the images
	 * @return how many was found
	 */
	public int nameFiles() {
		//TODO make the naming
		//for loop that goes trought the collection and if it has both files named the other
		return -1;
	}

}
