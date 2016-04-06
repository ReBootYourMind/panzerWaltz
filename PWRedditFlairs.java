/**
 * 
 */
package panzerWaltz;


/**
 * 6.4.2016
 * @author ReBootYourMind
 *
 */
public class PWRedditFlairs {

	private FlairImage[] flairList;
	private int maxFlairs;
	private PWRedditFlairsGUI redditGUI;
	private final int INITCOUNT = 400;
	
	/**
	 * konstruktori, jolla tehdää yhteys GUI:n kanssa
	 * contructor that makes the connection to the GUI
	 * @param gui gui that created this
	 */
	public PWRedditFlairs(PWRedditFlairsGUI gui) {
		redditGUI = gui;
		flairList = new FlairImage[INITCOUNT];
		maxFlairs = INITCOUNT;
	}
	
	
	/**
	 * Finds the desired files from the locations given
	 * @param first named location
	 * @param second nameless location
	 * @return how many was found -1 if something went wrong
	 */
	public int findFiles(String first, String second){
		//TODO make finding
		//search the folders and add them to the collection, recursion? since they can be inside folders
		//look for the number in the file using  FlairImage.giveNumberFromName(String, char) first 
		//make a new flairimage if there is nothing in the collection in it's spot 
		//and then input it into the slot of the number
		//if there was a image just add it into it with addFile(File)
		//so that a file with 182 will have an index number of 182 in the collection.
		//while counting the number of files added.
		return -1;
	}
	
	/**
	 * unohtaa etsityt kuvat, jotta etsiminen voidaan tehdä uudestaan tyhjästä
	 * 
	 * Forgets all images so that finding can be done from a clean table
	 * @return did it work
	 */
	public boolean forget() {
		flairList = new FlairImage[INITCOUNT];
		maxFlairs = INITCOUNT;
		//RESET everything needed
		return true;
	}
	
	/**
	 * adds a flair to the collection and if there is no room for it makes room for it
	 * @param flair image what to add
	 */
	private void addFlairImage(FlairImage image){
		if (image.getNumber() >= maxFlairs) {
			//no room for the flair, make more room
			int newMax = (maxFlairs + 1 ) * 2;
			FlairImage[] flairListTemp = new FlairImage[newMax];
			for (int i = 0; i < flairList.length; i++) {
				flairListTemp[i] = flairList[i];
			}
			flairList = flairListTemp;
			maxFlairs = newMax;
		}
		if (flairList[image.getNumber()] == null) {
			// there is no flair with that number yet
			flairList[image.getNumber()] = image;
		}
		
	}
	
	/**
	 * nimeää kuvat
	 * names the images in collection
	 * @return how many was named
	 */
	public int nameFiles() {
		int numberOfNamed = 0;
		for (int i = 0; i < flairList.length; i++) {
			if (flairList[i] != null) {
				if (flairList[i].nameSecondWithFirst())
					numberOfNamed++;
			}
		}
		return numberOfNamed;
	}

}
