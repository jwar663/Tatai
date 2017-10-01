package taitai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Vector;

public class TaitaiModel {
	
	static Vector<String> maoriNumbers = new Vector<String>(0,1);
	static Vector<Integer> easyScoreVector = new Vector<Integer>(0,1);
	static Vector<Integer> hardScoreVector = new Vector<Integer>(0,1);
	
	public static void saveStats(int numCorrect, int level) {
		try {
			String command;
			if (level == 1) {
				command = "echo " + numCorrect + " >> stats/.level1";
			} else {
				command = "echo " + numCorrect + " >> stats/.level2";
			}
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
			Process process = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void clearStats() {
		try {
			String command = "rm -r mydir";
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
			Process process = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String[] readStats(String file) {
		try {
			LineNumberReader reader  = new LineNumberReader(new FileReader(file));
			int count = 0;
			String lineRead = "";
			while ((lineRead = reader.readLine()) != null) {
				// do nothing
			}
			count = reader.getLineNumber(); // might need to + 1 to cnt
			reader.close();
			String[] stats = new String[count];
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			for (int i = 0; i < count; i++) {
				line = br.readLine();
				stats[i] = line;
			}
			br.close();
			return stats;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	//public static String[] getStats(int level) {
		// finish
	//}

	//checks if the word was pronounced correctly and returns a boolean
	public static boolean pronouncedCorrectlyBoolean(String wordSaid, String wordRequired) {
		if(wordSaid.equals(wordRequired)) {
			return true;
		} else {
			return false;
		}
	}
	
	//checks if the word was pronounced correctly and returns a corresponding message
	private static String pronouncedCorrectlyMessage(String wordSaid, String wordRequired) {
		String outputString = "";
		if(wordSaid.equals(wordRequired)) {
			outputString = "Correct!";
		} else {
			outputString = "Incorrect, you said " + wordSaid;
		}
		return outputString;
	}
	
	//starts the testing in easy mode, using multiple other functions 
	public static int startTest(int level) {
		int testNumber;
		if (level == 1) {
			testNumber = randomInt(1,9);
		} else {
			testNumber = randomInt(1,99);
		}
			
		return testNumber;
	}

	
	//starts the testing in hard mode, using multiple other functions
	private static int startHardMode() {
		int score = 0;
		String command = "sh /home/se206/Documents/HTK/MaoriNumbers/GoSpeech";
		//for(int i = 0; i < 10; i++) {
			int testNumber = randomInt(1,99);
			String wordRequired = maoriNumbers.get(testNumber);
			createNewProcess(command);
			String wordSaid = readRecoutFile();
			if(pronouncedCorrectlyBoolean(wordSaid, wordRequired)) {
				score++;
			}
			//String message = pronouncedCorrectlyMessage(wordSaid, wordRequired);
			
		//}
		saveHardScore(score);
		return score;
	}
	
	public static String getWordRequired(int number) {
		return maoriNumbers.get(number);
	}
	
	//Assigns all of the numbers in maori to their corresponding position in a vector
	private static void assignMaoriNumbers() {
		maoriNumbers.clear();
		maoriNumbers.addElement("kore");
		maoriNumbers.addElement("tahi");
		maoriNumbers.addElement("rua");
		maoriNumbers.addElement("toru");
		maoriNumbers.addElement("whaa");
		maoriNumbers.addElement("rima");
		maoriNumbers.addElement("ono");
		maoriNumbers.addElement("whitu");
		maoriNumbers.addElement("waru");
		maoriNumbers.addElement("iwa");
		maoriNumbers.addElement("tekau");
		maoriNumbers.addElement("tekau ma tahi");
		maoriNumbers.addElement("tekau ma rua");
		maoriNumbers.addElement("tekau ma toru");
		maoriNumbers.addElement("tekau ma whaa");
		maoriNumbers.addElement("tekau ma rima");
		maoriNumbers.addElement("tekau ma ono");
		maoriNumbers.addElement("tekau ma whitu");
		maoriNumbers.addElement("tekau ma waru");
		maoriNumbers.addElement("tekau ma iwa");
		maoriNumbers.addElement("rua tekau");
		maoriNumbers.addElement("rua tekau ma tahi");
		maoriNumbers.addElement("rua tekau ma rua");
		maoriNumbers.addElement("rua tekau ma toru");
		maoriNumbers.addElement("rua tekau ma whaa");
		maoriNumbers.addElement("rua tekau ma rima");
		maoriNumbers.addElement("rua tekau ma ono");
		maoriNumbers.addElement("rua tekau ma whitu");
		maoriNumbers.addElement("rua tekau ma waru");
		maoriNumbers.addElement("rua tekau ma iwa");
		maoriNumbers.addElement("toru tekau");
		maoriNumbers.addElement("toru tekau ma tahi");
		maoriNumbers.addElement("toru tekau ma rua");
		maoriNumbers.addElement("toru tekau ma toru");
		maoriNumbers.addElement("toru tekau ma whaa");
		maoriNumbers.addElement("toru tekau ma rima");
		maoriNumbers.addElement("toru tekau ma ono");
		maoriNumbers.addElement("toru tekau ma whitu");
		maoriNumbers.addElement("toru tekau ma waru");
		maoriNumbers.addElement("toru tekau ma iwa");
		maoriNumbers.addElement("whaa tekau");
		maoriNumbers.addElement("whaa tekau ma tahi");
		maoriNumbers.addElement("whaa tekau ma rua");
		maoriNumbers.addElement("whaa tekau ma toru");
		maoriNumbers.addElement("whaa tekau ma whaa");
		maoriNumbers.addElement("whaa tekau ma rima");
		maoriNumbers.addElement("whaa tekau ma ono");
		maoriNumbers.addElement("whaa tekau ma whitu");
		maoriNumbers.addElement("whaa tekau ma waru");
		maoriNumbers.addElement("whaa tekau ma iwa");
		maoriNumbers.addElement("rima tekau");
		maoriNumbers.addElement("rima tekau ma tahi");
		maoriNumbers.addElement("rima tekau ma rua");
		maoriNumbers.addElement("rima tekau ma toru");
		maoriNumbers.addElement("rima tekau ma whaa");
		maoriNumbers.addElement("rima tekau ma rima");
		maoriNumbers.addElement("rima tekau ma ono");
		maoriNumbers.addElement("rima tekau ma whitu");
		maoriNumbers.addElement("rima tekau ma waru");
		maoriNumbers.addElement("rima tekau ma iwa");
		maoriNumbers.addElement("ono tekau");
		maoriNumbers.addElement("ono tekau ma tahi");
		maoriNumbers.addElement("ono tekau ma rua");
		maoriNumbers.addElement("ono tekau ma toru");
		maoriNumbers.addElement("ono tekau ma whaa");
		maoriNumbers.addElement("ono tekau ma rima");
		maoriNumbers.addElement("ono tekau ma ono");
		maoriNumbers.addElement("ono tekau ma whitu");
		maoriNumbers.addElement("ono tekau ma waru");
		maoriNumbers.addElement("ono tekau ma iwa");
		maoriNumbers.addElement("whitu tekau");
		maoriNumbers.addElement("whitu tekau ma tahi");
		maoriNumbers.addElement("whitu tekau ma rua");
		maoriNumbers.addElement("whitu tekau ma toru");
		maoriNumbers.addElement("whitu tekau ma whaa");
		maoriNumbers.addElement("whitu tekau ma rima");
		maoriNumbers.addElement("whitu tekau ma ono");
		maoriNumbers.addElement("whitu tekau ma whitu");
		maoriNumbers.addElement("whitu tekau ma waru");
		maoriNumbers.addElement("whitu tekau ma iwa");
		maoriNumbers.addElement("waru tekau");
		maoriNumbers.addElement("waru tekau ma tahi");
		maoriNumbers.addElement("waru tekau ma rua");
		maoriNumbers.addElement("waru tekau ma toru");
		maoriNumbers.addElement("waru tekau ma whaa");
		maoriNumbers.addElement("waru tekau ma rima");
		maoriNumbers.addElement("waru tekau ma ono");
		maoriNumbers.addElement("waru tekau ma whitu");
		maoriNumbers.addElement("waru tekau ma waru");
		maoriNumbers.addElement("waru tekau ma iwa");
		maoriNumbers.addElement("iwa tekau");
		maoriNumbers.addElement("iwa tekau ma tahi");
		maoriNumbers.addElement("iwa tekau ma rua");
		maoriNumbers.addElement("iwa tekau ma toru");
		maoriNumbers.addElement("iwa tekau ma whaa");
		maoriNumbers.addElement("iwa tekau ma rima");
		maoriNumbers.addElement("iwa tekau ma ono");
		maoriNumbers.addElement("iwa tekau ma whitu");
		maoriNumbers.addElement("iwa tekau ma waru");
		maoriNumbers.addElement("iwa tekau ma iwa");
	}
	
	//adds an element to the easy score vector of the most recent test
	public static void saveEasyScore(int score) {
		easyScoreVector.addElement(score);
	}
	
	//adds an element to the hard score vector of the most recent test
	public static void saveHardScore(int score) {
		hardScoreVector.addElement(score);
	}

	//finds a random integer, given a minimum and maximum integer
	private static int randomInt(int lowerLimit, int upperLimit) {
		int randomNumber = (int) (Math.random() * upperLimit + lowerLimit);
		return randomNumber;
	}
	
	//reads the word that was said from the bash command
	private static String readRecoutFile() {
		try {
			BufferedReader recout = new BufferedReader(new FileReader("/home/se206/Documents/HTK/MaoriNumbers/recout.mlf"));
			int count = 0;
			String line;
			String outputString = "You didn't say anything.";
			String MLF = "#!MLF!#";
			String sil = "sil";
			String foo = "\"*/foo.rec\"";
			while((line = recout.readLine()) != null) {
				System.out.println(line);
				if ((line.equals(MLF)) || (line.equals(sil)) || (line.equals(foo)) || (line.equals("."))) {
					
				} else {
					if(count == 0) {
						outputString = line;
						count++;
					} else {
						outputString = outputString + " " + line;
					}
				}
			}
			recout.close();
			return outputString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	//currently function does not wait for process to finish
	//when i put waitFor() in, the process never finishes...

	//creates a new process that takes a bash command as an input
	public static void createNewProcess(String command) {
		try {
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
			File workingDirectory = new File("/home/se206/Documents/HTK/MaoriNumbers/");
			pb.directory(workingDirectory);
			pb.redirectErrorStream(true);
			Process process = pb.start();
			BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		String command = "sh /home/se206/Documents/HTK/MaoriNumbers/GoSpeech";
//		createNewProcess(command);
		String outputString = readRecoutFile();
		System.out.println(10);
		System.out.println(outputString);
		
	}

}