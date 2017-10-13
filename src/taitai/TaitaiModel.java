package taitai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Vector;

/**
 * @author Matthew Taylor, Jaedyn Ward
 */

public class TaitaiModel {
	
	public static Vector<String> maoriNumbers = new Vector<String>(0,1);
	
	//saves the stats to the file corresponding to the level
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
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}
	
	//clears stats
	public static void clearStats() {
		try {
			String command = "rm stats/.level1";
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
			Process process = pb.start();
			command = "rm stats/.level2";
			pb = new ProcessBuilder("bash", "-c", command);
			process = pb.start();
		} catch (Exception f) {
			f.printStackTrace();
		}
	}
	
	//reads the stats from files to label in the stats window
	public static String[] readStats(String file) {
		try {
			LineNumberReader reader  = new LineNumberReader(new FileReader(file));
			int count = 0;
			String lineRead = "";
			while ((lineRead = reader.readLine()) != null) {
				// do nothing
			}
			count = reader.getLineNumber();
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

	//checks if the word was pronounced correctly and returns a boolean
	public static boolean pronouncedCorrectlyBoolean(String wordSaid, String wordRequired) {
		if(wordSaid.equals(wordRequired)) {
			return true;
		} else {
			return false;
		}
	}

	//starts the test, by finding a random number that corresponds to the level
	public static int startTest(int level) {
		int testNumber;
		if (level == 1) {
			testNumber = randomInt(1,9);
		} else {
			testNumber = randomInt(1,99);
		}
		return testNumber;
	}
	
	//returns the word from the maori dictionary that corresponds to the number
	public static String getWordRequired(int number) {
		return maoriNumbers.get(number);
	}
	
	//Assigns all of the numbers in maori to their corresponding position in a vector
	public static void assignMaoriNumbers() {
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
		maoriNumbers.addElement("tekau maa tahi");
		maoriNumbers.addElement("tekau maa rua");
		maoriNumbers.addElement("tekau maa toru");
		maoriNumbers.addElement("tekau maa whaa");
		maoriNumbers.addElement("tekau maa rima");
		maoriNumbers.addElement("tekau maa ono");
		maoriNumbers.addElement("tekau maa whitu");
		maoriNumbers.addElement("tekau maa waru");
		maoriNumbers.addElement("tekau maa iwa");
		maoriNumbers.addElement("rua tekau");
		maoriNumbers.addElement("rua tekau maa tahi");
		maoriNumbers.addElement("rua tekau maa rua");
		maoriNumbers.addElement("rua tekau maa toru");
		maoriNumbers.addElement("rua tekau maa whaa");
		maoriNumbers.addElement("rua tekau maa rima");
		maoriNumbers.addElement("rua tekau maa ono");
		maoriNumbers.addElement("rua tekau maa whitu");
		maoriNumbers.addElement("rua tekau maa waru");
		maoriNumbers.addElement("rua tekau maa iwa");
		maoriNumbers.addElement("toru tekau");
		maoriNumbers.addElement("toru tekau maa tahi");
		maoriNumbers.addElement("toru tekau maa rua");
		maoriNumbers.addElement("toru tekau maa toru");
		maoriNumbers.addElement("toru tekau maa whaa");
		maoriNumbers.addElement("toru tekau maa rima");
		maoriNumbers.addElement("toru tekau maa ono");
		maoriNumbers.addElement("toru tekau maa whitu");
		maoriNumbers.addElement("toru tekau maa waru");
		maoriNumbers.addElement("toru tekau maa iwa");
		maoriNumbers.addElement("whaa tekau");
		maoriNumbers.addElement("whaa tekau maa tahi");
		maoriNumbers.addElement("whaa tekau maa rua");
		maoriNumbers.addElement("whaa tekau maa toru");
		maoriNumbers.addElement("whaa tekau maa whaa");
		maoriNumbers.addElement("whaa tekau maa rima");
		maoriNumbers.addElement("whaa tekau maa ono");
		maoriNumbers.addElement("whaa tekau maa whitu");
		maoriNumbers.addElement("whaa tekau maa waru");
		maoriNumbers.addElement("whaa tekau maa iwa");
		maoriNumbers.addElement("rima tekau");
		maoriNumbers.addElement("rima tekau maa tahi");
		maoriNumbers.addElement("rima tekau maa rua");
		maoriNumbers.addElement("rima tekau maa toru");
		maoriNumbers.addElement("rima tekau maa whaa");
		maoriNumbers.addElement("rima tekau maa rima");
		maoriNumbers.addElement("rima tekau maa ono");
		maoriNumbers.addElement("rima tekau maa whitu");
		maoriNumbers.addElement("rima tekau maa waru");
		maoriNumbers.addElement("rima tekau maa iwa");
		maoriNumbers.addElement("ono tekau");
		maoriNumbers.addElement("ono tekau maa tahi");
		maoriNumbers.addElement("ono tekau maa rua");
		maoriNumbers.addElement("ono tekau maa toru");
		maoriNumbers.addElement("ono tekau maa whaa");
		maoriNumbers.addElement("ono tekau maa rima");
		maoriNumbers.addElement("ono tekau maa ono");
		maoriNumbers.addElement("ono tekau maa whitu");
		maoriNumbers.addElement("ono tekau maa waru");
		maoriNumbers.addElement("ono tekau maa iwa");
		maoriNumbers.addElement("whitu tekau");
		maoriNumbers.addElement("whitu tekau maa tahi");
		maoriNumbers.addElement("whitu tekau maa rua");
		maoriNumbers.addElement("whitu tekau maa toru");
		maoriNumbers.addElement("whitu tekau maa whaa");
		maoriNumbers.addElement("whitu tekau maa rima");
		maoriNumbers.addElement("whitu tekau maa ono");
		maoriNumbers.addElement("whitu tekau maa whitu");
		maoriNumbers.addElement("whitu tekau maa waru");
		maoriNumbers.addElement("whitu tekau maa iwa");
		maoriNumbers.addElement("waru tekau");
		maoriNumbers.addElement("waru tekau maa tahi");
		maoriNumbers.addElement("waru tekau maa rua");
		maoriNumbers.addElement("waru tekau maa toru");
		maoriNumbers.addElement("waru tekau maa whaa");
		maoriNumbers.addElement("waru tekau maa rima");
		maoriNumbers.addElement("waru tekau maa ono");
		maoriNumbers.addElement("waru tekau maa whitu");
		maoriNumbers.addElement("waru tekau maa waru");
		maoriNumbers.addElement("waru tekau maa iwa");
		maoriNumbers.addElement("iwa tekau");
		maoriNumbers.addElement("iwa tekau maa tahi");
		maoriNumbers.addElement("iwa tekau maa rua");
		maoriNumbers.addElement("iwa tekau maa toru");
		maoriNumbers.addElement("iwa tekau maa whaa");
		maoriNumbers.addElement("iwa tekau maa rima");
		maoriNumbers.addElement("iwa tekau maa ono");
		maoriNumbers.addElement("iwa tekau maa whitu");
		maoriNumbers.addElement("iwa tekau maa waru");
		maoriNumbers.addElement("iwa tekau maa iwa");
	}

	//finds a random integer, given a minimum and maximum integer
	private static int randomInt(int lowerLimit, int upperLimit) {
		int randomNumber = (int) (Math.random() * upperLimit + lowerLimit);
		return randomNumber;
	}
	
	//reads the word that was said from the bash command
	public static String readRecoutFile() {
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
	
	//creates a new process that takes a bash command as an input
	public static void createNewProcess(String command) {
		try {
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
			File workingDirectory = new File("/home/se206/Documents/HTK/MaoriNumbers/");
			pb.directory(workingDirectory);
			pb.redirectErrorStream(true);
			Process process = pb.start();
			BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
			process.waitFor();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//function to play the file that was previously recorded
	public static void playAudio() {
		String command = "aplay foo.wav";
		createNewProcess(command);
	}
	
	//uses the dictionary to identify what was said and store it in a file
	public static void writeToRecout() {
		String command = "HVite -H HMMs/hmm15/macros -H HMMs/hmm15/hmmdefs -C user/configLR  -w user/wordNetworkNum -o SWT -l '*' -i recout.mlf -p 0.0 -s 5.0  user/dictionaryD user/tiedList foo.wav 1> recout.mlf";
		createNewProcess(command);
	}
	
	//records the audio to be compared
	public static void recordAudio(String time) {
		String command = "arecord -d " + time + " -r 22050 -c 1 -i -t wav -f s16_LE foo.wav";
		createNewProcess(command);
	}
	
	//user inputs an expression as a string and completes a bash 
	//command to find out the mathematical answer
	public static String computeExpression(String expression) {
		try {
			String AnswerOrError;
			String command = "echo $((" + expression + "))";
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
			Process process = pb.start();
			BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String lineOut = stdout.readLine();
			String lineErr = stderr.readLine();
			process.waitFor();
			if(lineOut == null) {
				AnswerOrError = "error bash";
			} else {
				int answerInt = Integer.parseInt(lineOut);
				if((answerInt <= 99) && (answerInt > 0)) {
					AnswerOrError = lineOut;
				} else {
					AnswerOrError = "error bounds";
				}
				
			}
//			System.out.println(lineOut);
//			System.out.println(lineErr);
			
			return AnswerOrError;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//checks what sort of error or what the answer to the expression is
	//need to implement adding the equation.
	public static int checkExpression(String expression) {
		String message = computeExpression(expression);
		if(message.equals("error bash")) {
			System.out.println(message);
			return -1;
		} else if(message.equals("error bounds")) {
			System.out.println(message);
			return -2;
		} else {
			System.out.println(message);
			//write to file here.
			return Integer.parseInt(message);
			
		}
	}
}


