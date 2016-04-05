/**
 * 
 */
package panzerWaltz;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ReBootYourMind
 *
 */
public class FlairImage {

	private String name;
	private int number;
	private File[] fileList = new File[2];
	private boolean firstFile = false;
	private boolean secondFile = false;

	/**
	 * default contructor
	 */
	public FlairImage(){
		this("",0);
	}
	
	/**
	 * constructor with parameters
	 * @param iName what is the name
	 * @param iNumber the number
	 * @example
	 * <pre name="test">
	 * FlairImage testi = new FlairImage();
	 * testi.toString() === " 0";
	 * testi.getNumber() === 0;
	 * testi.getName() === "";
	 * </pre>
	 */
	public FlairImage(String iName, int iNumber){
		name = iName;
		number = iNumber;
	}
	/**
	 * Names the second file with the firsts name
	 * @return did the naming succeed
	 */
	public boolean nameSecondWithFirst(){
		if(!firstFile || !secondFile)
			return false;
		
		File first = fileList[0];
		File second = fileList[1];
		
		String secondPath = shorten(second.getPath(), second.getName().length());
		File newLocation = new File(secondPath + first.getName());
		
		return first.renameTo(newLocation);
	}
	
	/**
	 * lis‰‰ tiedoston luokkaan, ottaa numeron ja nimen tiedostosta jos se oli oikeanlainen
	 * kertoo paluuna onnistuiko lis‰ys
	 * 
	 * adds a file, takes the name and number from the file if it was a good file
	 * @param file what file is added
	 * @return did it succeed
	 */
	public boolean addFile(File file){
		if (secondFile && firstFile)
			return false;
		if (!secondFile) {
			String withoutExtension = takeExtensionAway(file.getName());
			
			char separator ='-';
			StringBuilder withoutExtensionSB = new StringBuilder(withoutExtension);
			AtomicBoolean wasItAtFront = new AtomicBoolean(false);
			int candidate = separateNumber(withoutExtensionSB, separator, wasItAtFront);
			if (candidate <= 0)
				return false;
				
			if (!wasItAtFront.get()) {
				fileList[1] = file;
				secondFile = true;
			}
			else return false;
			number = candidate;
			return true;
		}
		if (!firstFile){
            String withoutExtension = takeExtensionAway(file.getName());
			
			char separator ='-';
			StringBuilder withoutExtensionSB = new StringBuilder(withoutExtension);
			AtomicBoolean wasItAtFront = new AtomicBoolean(false);
			int candidate = separateNumber(withoutExtensionSB, separator, wasItAtFront);
			if (candidate <= 0)
				return false;
				
			if (wasItAtFront.get()) {
				name = withoutExtensionSB.toString();
				fileList[0] = file;
				firstFile = true;
			}
			else return false;
			number = candidate;
			return true;
		}
		return false;
	}
	/**
	 * erottaa jonosta numeron ja jos se oli edess‰ lyhent‰‰ sen pois siit‰ jonosta. Numero oletetaan joku eteen tai loppuun.
	 * Takes a number from the string. If the number from at the start take it from the string. The number must be at the beginning or end.
	 * @param string where to look for the number. This will be shortened too.
	 * @param separator the character that divides the number from the rest
	 * @param wasItAtFront true if the number was a t the front. In this case the string was shortened
	 * @return the number that was found
	 * @example
	 * <pre name="test">
	 * java.util.concurrent.atomic.AtomicBoolean totuus = new java.util.concurrent.atomic.AtomicBoolean(false);
	 * 
	 * StringBuilder testi = new StringBuilder();
	 * separateNumber(testi,'-',totuus) === 0;
	 * 
	 * totuus.set(false);
	 * testi = new StringBuilder("123-asdasd");
	 * separateNumber(testi,'-',totuus) === 123;
	 * testi.toString() === "asdasd";
	 * totuus.get() === true;
	 * 
	 * totuus.set(false);
	 * testi = new StringBuilder("123-asdasd-muumi");
	 * separateNumber(testi,'-',totuus) === 123;
	 * testi.toString() === "asdasd-muumi";
	 * totuus.get() === true;
	 * 
	 * totuus.set(false);
	 * testi = new StringBuilder("asd-asdasd-muumi");
	 * separateNumber(testi,'-',totuus) === 0;
	 * testi.toString() === "asd-asdasd-muumi";
	 * totuus.get() === false;
	 * 
	 * totuus.set(false);
	 * testi = new StringBuilder("asd-asdasd-123");
	 * separateNumber(testi,'-',totuus) === 123;
	 * testi.toString() === "asd-asdasd-123";
	 * totuus.get() === false;
	 * 
	 * totuus.set(false);
	 * testi = new StringBuilder("asd-123-muumi");
	 * separateNumber(testi,'-',totuus) === 0;
	 * testi.toString() === "asd-123-muumi";
	 * totuus.get() === false;
	 * </pre>
	 */
	public static int separateNumber(StringBuilder string, char separator, AtomicBoolean wasItAtFront){
		String[] parts = string.toString().split("" + separator);
		
		int lastSeparator = string.lastIndexOf(""+separator);
		int fisrtSeparator = string.indexOf(""+separator);
		if (lastSeparator < 0 && fisrtSeparator < 0){
			return 0;
		}
		
		int candidateStart = takeNumberFromString(parts[0]); 
		if (candidateStart > 0) {
			string.replace(0, fisrtSeparator+1, "");
			wasItAtFront.set(true);
			return candidateStart;
		}
		
		int candidateEnd = takeNumberFromString(parts[parts.length-1]); 	
		if (candidateEnd > 0) {
			return candidateEnd;
		}
		return 0;
	}
	
	/**
	 * ottaa numeron merkkijonosta
	 * takes a number from a string
	 * @param string where to look for
	 * @return the number found
	 * @example
	 * <pre name="test">
	 * takeNumberFromString("123") === 123;
	 * takeNumberFromString("123a") === 0;
	 * takeNumberFromString("-123") === -123;
	 * takeNumberFromString("--123") === 0;
	 * takeNumberFromString("pz4") === 0;
	 * </pre>
	 */
	public static int takeNumberFromString(String string){
		int numberTaken = 0; 
		try {
			numberTaken = Integer.parseInt(string);
			
		} catch (NumberFormatException e) { }
		return numberTaken;
	}
	
	/**
	 * Will shorten the string with the desired amount
	 * @param string what will be shortened
	 * @return the shorter string
	 * @example
	 * <pre name="test">
	 * shorten("asd.asd",7) === "";
	 * shorten("C:/Users/asd.asd",7) === "C:/Users/";
	 * </pre>
	 */
	public static String shorten(String string, int lenght){
		StringBuilder shorter = new StringBuilder( string);
		return shorter.substring(0, shorter.length() - lenght);
	}
	
	/**
	 * ottaa mahdollisen teidostop‰‰tteen pois merkkijonosta
	 * 
	 * takes the file extension away from a string if it has one.
	 * @param fromWhat the string that will be worked on
	 * @return the shorter string
	 * @example
	 * <pre name="test">
	 * takeExtensionAway("asd.asd") === "asd";
	 * takeExtensionAway("asd.a") === "asd";
	 * takeExtensionAway("asd.") === "asd";
	 * takeExtensionAway("asd") === "asd";
	 * takeExtensionAway(".asd") === "";
	 * takeExtensionAway(".") === "";
	 * takeExtensionAway("asd.asd.asd") === "asd.asd";
	 * takeExtensionAway("merkki.jono.png") === "merkki.jono";
	 * takeExtensionAway("mer.kki.jono.png") === "mer.kki.jono";
	 * </pre>
	 */
	public static String takeExtensionAway(String fromWhat) {
		String withoutExtension;
		int index = fromWhat.lastIndexOf('.');
		if (index != -1) {
			withoutExtension = fromWhat.substring(0,index );
		}
		else {
			withoutExtension = fromWhat;
		}
		return withoutExtension;
	}
	
	
	/**
	 * antaa halutun teidoston takaisin
	 * 
	 * Returns the desired file back
	 * @param n what file you want
	 * @return the file wanted
	 */
	public File getnFile(int n){
		if (0 <= n && n < fileList.length) {
			return fileList[n];
		}
		return null;
	}
	
	/**
	 * antaa arvot merkkijonona
	 * 
	 * Gives the values as a String
	 */
	public String toString() {
		return name + " " + number;
	}
	
	/**
	 * antaa nimen merkkijonona
	 * 
	 * returns the name
	 * @return nimi merkkijonona
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * antaa numeron kokonaislukuna
	 * 
	 * returns the number
	 * @return numero kokonaislukuna
	 */
	public int getNumber(){
		return number;
	}
	
}
