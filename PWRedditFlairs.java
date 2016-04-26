/**
 * 
 */
package panzerWaltz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;


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
	 * konstruktori, jolla tehd‰‰ yhteys GUI:n kanssa
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
		int howMany = -1;
		//TODO make finding
		File folder1 = new File(first);
		File folder2 = new File(second);
		//redditGUI.doesntWork("findfiles\n" + folder1.getName() + "\n" + folder1.getPath()+ "\n" + folder1.isDirectory() + "\n"+ folder2.getName() + "\n" + folder2.getPath()+ "\n" + folder2.isDirectory());
		char separator = '-';
		if (folder1.isDirectory() && folder2.isDirectory()) {
			//both exsist and are folders
			howMany = 0;
			howMany = howMany + findAFolder(folder2, separator);
			howMany = howMany + findAFolder(folder1, separator);
		}
		
		//while counting the number of files added.
		return howMany;
	}
	
	/**
	 * adds a folder in the collection
	 * @param folder where to look for images
	 * @param separator what separates the number from the rest
	 * @return how many images were added
	 */
	private int findAFolder(File folder,char separator){
		int howMany = 0;
		File[] fileList = folder.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isFile()) {
				//look for the number in the file using  FlairImage.giveNumberFromName(String, char) 
				int number = FlairImage.giveNumberFromName(FlairImage.takeExtensionAway(fileList[i].getName()), separator);
				if (number <= 0) {
					break; //the number was negative or 0 meaning that the function didn't get anything good.
				}
				if (flairList[number] == null) {
					//make a new flairimage if there is nothing in the collection in it's spot 
					FlairImage newImage = new FlairImage();
					if (newImage.addFile(fileList[i])) {
						addFlairImage(newImage);
						howMany++;
					}
					//and then input it into the slot of the number
				}
				else {
					//if there was a image just add it into it with addFile(File)
					if (flairList[number].addFile(fileList[i])) {
						howMany++;
					}
				}
			}
			else if (fileList[i].isDirectory()){
				howMany = howMany + findAFolder(fileList[i], separator); //recursion
			}
		}
		return howMany;
	}
	
	/**
	 * unohtaa etsityt kuvat, jotta etsiminen voidaan tehd‰ uudestaan tyhj‰st‰
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
	 * outputs a list of flairs into the location given
	 * @param where the location where to output the file
	 * @return did it succeed
	 */
	protected boolean outputList(String where){
		//File file = new File(where + "flairOutput.txt");
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(where + "flairOutput.txt"), "utf-8"))) {
			writer.write("number|name\n");
			for (FlairImage flairImage : flairList) {
				if (flairImage != null) {
					writer.write(flairImage.toString() + "\n");
				}
			}
			return true;
		} catch (IOException e) {
		//nothing
		}
		return false;
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
	 * nime‰‰ kuvat
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
