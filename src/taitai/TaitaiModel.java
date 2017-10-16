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
	public static Vector<String> addSubQuestions = new Vector<String>(0,1);
	public static Vector<String> multDivQuestions = new Vector<String>(0,1);
	public static Vector<String> customQuestions = new Vector<String>(0,1);
	
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
	public static String startTest(int level) {
		String testExpression;
		int elementsInCustom = 0;
		if (level == 1) {
			testExpression = Integer.toString(randomInt(1,99));
		} else if (level == 2) {
			testExpression = addSubQuestions.get(randomInt(0,49));
		} else if (level == 3) {
			testExpression = multDivQuestions.get(randomInt(0,49));
		} else if (level == 4) {
			if(randomInt(1,2) == 1) {
				testExpression = addSubQuestions.get(randomInt(0,49));
			} else {
				testExpression = multDivQuestions.get(randomInt(0,49));
			}		
		} else {
			elementsInCustom = customQuestions.size();
			testExpression = customQuestions.get(elementsInCustom);
		}
		return testExpression;
	}
	
	//returns the word from the maori dictionary that corresponds to the number
	public static String getWordRequired(String expression) {
		return maoriNumbers.get(findAnswerToExpression(expression));
	}
	
	//reads from custom questions file and assigns values to the vector.
	public static void assignCustomQuestions() {
		try {
			customQuestions.clear();
			BufferedReader br = new BufferedReader(new FileReader("questions/.custom"));
			String line;
			while ((line = br.readLine()) != null) {
				customQuestions.addElement(line);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//assigns addition/subtraction and multiplication/division questions
	//to their respective vectors
	public static void assignRegularQuestions() {
		addSubQuestions.clear();
		multDivQuestions.clear();
		
		addSubQuestions.addElement("2 + 2 - 1");
		addSubQuestions.addElement("76 + 22 - 64");
		addSubQuestions.addElement("69 + 22 - 25");
		addSubQuestions.addElement("85 + 10 - 42");
		addSubQuestions.addElement("2 + 92 - 40");
		addSubQuestions.addElement("1 + 32 - 15");
		addSubQuestions.addElement("59 + 32 - 34");
		addSubQuestions.addElement("79 + 17 - 14");
		addSubQuestions.addElement("83 + 5 - 36");
		addSubQuestions.addElement("93 + 6 - 45");
		addSubQuestions.addElement("87 + 5 - 32");
		addSubQuestions.addElement("85 + 6 - 15");
		addSubQuestions.addElement("74 + 24 - 61");
		addSubQuestions.addElement("9 + 22 - 10");
		addSubQuestions.addElement("38 + 41 - 21");
		addSubQuestions.addElement("87 + 2 - 74");
		addSubQuestions.addElement("28 + 13 - 19");
		addSubQuestions.addElement("65 + 18 - 32");
		addSubQuestions.addElement("49 + 22 - 31");
		addSubQuestions.addElement("8 + 50 - 21");
		addSubQuestions.addElement("53 + 10 - 60");
		addSubQuestions.addElement("37 + 33 - 16");
		addSubQuestions.addElement("94 + 2 - 67");
		addSubQuestions.addElement("5 + 8 - 4");
		addSubQuestions.addElement("45 + 45 - 62");
		addSubQuestions.addElement("27 + 11 - 26");
		addSubQuestions.addElement("54 + 36 - 80");
		addSubQuestions.addElement("14 + 41 - 45");
		addSubQuestions.addElement("66 + 22 - 77");
		addSubQuestions.addElement("49 + 6 - 50");
		addSubQuestions.addElement("90 + 5 - 88");
		addSubQuestions.addElement("79 + 20 - 1");
		addSubQuestions.addElement("3 + 56 - 39");
		addSubQuestions.addElement("18 + 17 - 32");
		addSubQuestions.addElement("78 + 5 - 67");
		addSubQuestions.addElement("89 + 6 - 21");
		addSubQuestions.addElement("76 + 4 - 30");
		addSubQuestions.addElement("15 + 25 - 10");
		addSubQuestions.addElement("38 + 40 - 64");
		addSubQuestions.addElement("81 + 13 - 90");
		addSubQuestions.addElement("65 + 18 - 70");
		addSubQuestions.addElement("10 + 17 - 6");
		addSubQuestions.addElement("19 + 62 - 47");
		addSubQuestions.addElement("98 + 1 - 93");
		addSubQuestions.addElement("52 + 19 - 69");
		addSubQuestions.addElement("43 + 50 - 3");
		addSubQuestions.addElement("54 + 34 - 80");
		addSubQuestions.addElement("1 + 46 - 32");
		addSubQuestions.addElement("60 + 29 - 71");
		addSubQuestions.addElement("23 + 28 - 50");
		
		multDivQuestions.addElement("(15 * 5)/ 75");
		multDivQuestions.addElement("(10 * 6)/ 5");
		multDivQuestions.addElement("(20 * 4)/ 10");
		multDivQuestions.addElement("(3 * 4)/ 2");
		multDivQuestions.addElement("(44 * 2)/ 11");
		multDivQuestions.addElement("(32 * 3)/ 4");
		multDivQuestions.addElement("(24 * 1)/ 6");
		multDivQuestions.addElement("(5 * 18)/ 9");
		multDivQuestions.addElement("(7 * 8)/ 2");
		multDivQuestions.addElement("(16 * 3)/ 12");
		multDivQuestions.addElement("(9 * 6)/ 2");
		multDivQuestions.addElement("(10 * 8)/ 20");
		multDivQuestions.addElement("(70 * 1)/ 35");
		multDivQuestions.addElement("(40 * 2)/ 80");
		multDivQuestions.addElement("(2 * 8)/ 4");
		multDivQuestions.addElement("(7 * 6)/ 21");
		multDivQuestions.addElement("(10 * 5)/ 2");
		multDivQuestions.addElement("(16 * 4)/ 32");
		multDivQuestions.addElement("(5 * 16)/ 4");
		multDivQuestions.addElement("(14 * 6)/ 21");
		multDivQuestions.addElement("(63 / 9)* 14");
		multDivQuestions.addElement("(90 / 10)* 3");
		multDivQuestions.addElement("(70 / 35)* 19");
		multDivQuestions.addElement("(4 / 4)* 99");
		multDivQuestions.addElement("(68 / 4)* 5");
		multDivQuestions.addElement("(12 / 3)* 21");
		multDivQuestions.addElement("(88 / 8)* 6");
		multDivQuestions.addElement("(35 / 7)* 17");
		multDivQuestions.addElement("(39 / 13)* 24");
		multDivQuestions.addElement("(49 / 7)* 15");
		multDivQuestions.addElement("(72 / 9)* 12");
		multDivQuestions.addElement("(40 / 4)* 7");
		multDivQuestions.addElement("(75 / 15)* 12");
		multDivQuestions.addElement("(84 / 4)* 3");
		multDivQuestions.addElement("(90 / 10)* 2");
		multDivQuestions.addElement("(22 / 2)* 8");
		multDivQuestions.addElement("(64 / 16)* 2");
		multDivQuestions.addElement("(15 / 5)* 24");
		multDivQuestions.addElement("( / )* ");
		multDivQuestions.addElement("( / )* ");
		multDivQuestions.addElement("( / )* ");
		multDivQuestions.addElement("( / )* ");
		multDivQuestions.addElement("( / )* ");
		multDivQuestions.addElement("( / )* ");
		multDivQuestions.addElement("( / )* ");
		multDivQuestions.addElement("( / )* ");
		multDivQuestions.addElement("( / )* ");
		multDivQuestions.addElement("( / )* ");
		multDivQuestions.addElement("( / )* ");
		multDivQuestions.addElement("( / )* ");
		
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
			stdout.close();
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
			stdout.close();
			stderr.close();
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
	
	
	
	//find an answer to your expression when you know it wont cause an error
	public static int findAnswerToExpression(String expression) {
		try {
			String command = "echo $((" + expression + "))";
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
			Process process = pb.start();
			BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String lineOut = stdout.readLine();
			String lineErr = stderr.readLine();
			process.waitFor();
			stdout.close();
			stderr.close();
			int answerInt = Integer.parseInt(lineOut);
			return answerInt;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	//save question
	public static void saveQuestion(String expression, String questionType) {
		try {
				String command;
				command = "echo " + expression + " >> questions/." + questionType;
				ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
				pb.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}
	
	//creates a file/folder using process builder
	public static void createFile(String command) {
		try {
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//creates all necessary files/folders
	public static void createAllFiles() {
		createFile("mkdir stats");
		createFile(" >> stats/.level1");
		createFile(" >> stats/.level2");
		createFile("mkdir questions");
		createFile(" >> questions/.custom");
//		createFile(" >> questions/.numbers");
//		createFile(" >> questions/.addition_subtraction");
//		createFile(" >> questions/.multiplication_division");
//		createFile(" >> questions/.combination");
	}
}


