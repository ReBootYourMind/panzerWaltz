/**
 * 
 */
package panzerWaltz;

import java.util.ArrayList;
import java.util.List;

/**
 * 6.4.2016
 * @author ReBootYourMind
 *
 */
public class PWRedditFlairs {

	private List<FlairImage> flairList;
	private PWRedditFlairsGUI redditGUI;
	private final int INITCOUNT = 400;
	
	/**
	 * konstruktori, jolla tehd‰‰ yhteys GUI:n kanssa
	 * contructor that makes the connection to the GUI
	 * @param gui gui that created this
	 */
	public PWRedditFlairs(PWRedditFlairsGUI gui) {
		redditGUI = gui;
		flairList = new ArrayList<FlairImage>(INITCOUNT);
	}
	
	
	/**
	 * Finds the desired files from the locations given
	 * @param first named location
	 * @param second nameless location
	 * @return how many was found -1 if something went wrong
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
		flairList = new ArrayList<FlairImage>(INITCOUNT);
		//RESET everything needed
		return true;
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
