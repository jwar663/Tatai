import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Vector;

public class Main {
	
	static Vector<String> maoriNumbers = new Vector<String>(0,1);
	static Vector<Integer> easyScoreVector = new Vector<Integer>(0,1);
	static Vector<Integer> hardScoreVector = new Vector<Integer>(0,1);
	
	private static boolean pronouncedCorrectlyBoolean(String wordSaid, String wordRequired) {
		if(wordSaid.equals(wordRequired)) {
			return true;
		} else {
			return false;
		}
	}
	
	private static String pronouncedCorrectlyMessage(String wordSaid, String wordRequired) {
		String outputString = "";
		if(wordSaid.equals(wordRequired)) {
			outputString = "Correct!";
		} else {
			outputString = "Incorrect, you said " + wordSaid;
		}
		return outputString;
	}
	
	private static int startEasyMode() {
		int score = 0;
		String command = "sh /home/se206/Documents/HTK/MaoriNumbers/GoSpeech";
		for(int i = 0; i < 10; i++) {
			int testNumber = randomInt(1,9);
			String wordRequired = maoriNumbers.get(testNumber);
			createNewProcess(command);
			String wordSaid = readRecoutFile();
			if(pronouncedCorrectlyBoolean(wordSaid, wordRequired)) {
				score++;
			}
			String message = pronouncedCorrectlyMessage(wordSaid, wordRequired);
			
		}
		saveEasyScore(score);
		return score;
	}
	
	private static int startHardMode() {
		int score = 0;
		String command = "sh /home/se206/Documents/HTK/MaoriNumbers/GoSpeech";
		for(int i = 0; i < 10; i++) {
			int testNumber = randomInt(1,99);
			String wordRequired = maoriNumbers.get(testNumber);
			createNewProcess(command);
			String wordSaid = readRecoutFile();
			if(pronouncedCorrectlyBoolean(wordSaid, wordRequired)) {
				score++;
			}
			String message = pronouncedCorrectlyMessage(wordSaid, wordRequired);
			
		}
		saveHardScore(score);
		return score;
	}
	
	private static void assignMaoriNumbers() {
		maoriNumbers.clear();
		maoriNumbers.addElement("kore");
		maoriNumbers.addElement("tahi");
		maoriNumbers.addElement("rua");
		maoriNumbers.addElement("toru");
		maoriNumbers.addElement("wha");
		maoriNumbers.addElement("rima");
		maoriNumbers.addElement("ono");
		maoriNumbers.addElement("whitu");
		maoriNumbers.addElement("waru");
		maoriNumbers.addElement("iwa");
		maoriNumbers.addElement("tekau");
		maoriNumbers.addElement("tekau ma tahi");
		maoriNumbers.addElement("tekau ma rua");
		maoriNumbers.addElement("tekau ma toru");
		maoriNumbers.addElement("tekau ma wha");
		maoriNumbers.addElement("tekau ma rima");
		maoriNumbers.addElement("tekau ma ono");
		maoriNumbers.addElement("tekau ma whitu");
		maoriNumbers.addElement("tekau ma waru");
		maoriNumbers.addElement("tekau ma iwa");
		maoriNumbers.addElement("rua tekau");
		maoriNumbers.addElement("rua tekau ma tahi");
		maoriNumbers.addElement("rua tekau ma rua");
		maoriNumbers.addElement("rua tekau ma toru");
		maoriNumbers.addElement("rua tekau ma wha");
		maoriNumbers.addElement("rua tekau ma rima");
		maoriNumbers.addElement("rua tekau ma ono");
		maoriNumbers.addElement("rua tekau ma whitu");
		maoriNumbers.addElement("rua tekau ma waru");
		maoriNumbers.addElement("rua tekau ma iwa");
		maoriNumbers.addElement("toru tekau");
		maoriNumbers.addElement("toru tekau ma tahi");
		maoriNumbers.addElement("toru tekau ma rua");
		maoriNumbers.addElement("toru tekau ma toru");
		maoriNumbers.addElement("toru tekau ma wha");
		maoriNumbers.addElement("toru tekau ma rima");
		maoriNumbers.addElement("toru tekau ma ono");
		maoriNumbers.addElement("toru tekau ma whitu");
		maoriNumbers.addElement("toru tekau ma waru");
		maoriNumbers.addElement("toru tekau ma iwa");
		maoriNumbers.addElement("wha tekau");
		maoriNumbers.addElement("wha tekau ma tahi");
		maoriNumbers.addElement("wha tekau ma rua");
		maoriNumbers.addElement("wha tekau ma toru");
		maoriNumbers.addElement("wha tekau ma wha");
		maoriNumbers.addElement("wha tekau ma rima");
		maoriNumbers.addElement("wha tekau ma ono");
		maoriNumbers.addElement("wha tekau ma whitu");
		maoriNumbers.addElement("wha tekau ma waru");
		maoriNumbers.addElement("wha tekau ma iwa");
		maoriNumbers.addElement("rima tekau");
		maoriNumbers.addElement("rima tekau ma tahi");
		maoriNumbers.addElement("rima tekau ma rua");
		maoriNumbers.addElement("rima tekau ma toru");
		maoriNumbers.addElement("rima tekau ma wha");
		maoriNumbers.addElement("rima tekau ma rima");
		maoriNumbers.addElement("rima tekau ma ono");
		maoriNumbers.addElement("rima tekau ma whitu");
		maoriNumbers.addElement("rima tekau ma waru");
		maoriNumbers.addElement("rima tekau ma iwa");
		maoriNumbers.addElement("ono tekau");
		maoriNumbers.addElement("ono tekau ma tahi");
		maoriNumbers.addElement("ono tekau ma rua");
		maoriNumbers.addElement("ono tekau ma toru");
		maoriNumbers.addElement("ono tekau ma wha");
		maoriNumbers.addElement("ono tekau ma rima");
		maoriNumbers.addElement("ono tekau ma ono");
		maoriNumbers.addElement("ono tekau ma whitu");
		maoriNumbers.addElement("ono tekau ma waru");
		maoriNumbers.addElement("ono tekau ma iwa");
		maoriNumbers.addElement("whitu tekau");
		maoriNumbers.addElement("whitu tekau ma tahi");
		maoriNumbers.addElement("whitu tekau ma rua");
		maoriNumbers.addElement("whitu tekau ma toru");
		maoriNumbers.addElement("whitu tekau ma wha");
		maoriNumbers.addElement("whitu tekau ma rima");
		maoriNumbers.addElement("whitu tekau ma ono");
		maoriNumbers.addElement("whitu tekau ma whitu");
		maoriNumbers.addElement("whitu tekau ma waru");
		maoriNumbers.addElement("whitu tekau ma iwa");
		maoriNumbers.addElement("waru tekau");
		maoriNumbers.addElement("waru tekau ma tahi");
		maoriNumbers.addElement("waru tekau ma rua");
		maoriNumbers.addElement("waru tekau ma toru");
		maoriNumbers.addElement("waru tekau ma wha");
		maoriNumbers.addElement("waru tekau ma rima");
		maoriNumbers.addElement("waru tekau ma ono");
		maoriNumbers.addElement("waru tekau ma whitu");
		maoriNumbers.addElement("waru tekau ma waru");
		maoriNumbers.addElement("waru tekau ma iwa");
		maoriNumbers.addElement("iwa tekau");
		maoriNumbers.addElement("iwa tekau ma tahi");
		maoriNumbers.addElement("iwa tekau ma rua");
		maoriNumbers.addElement("iwa tekau ma toru");
		maoriNumbers.addElement("iwa tekau ma wha");
		maoriNumbers.addElement("iwa tekau ma rima");
		maoriNumbers.addElement("iwa tekau ma ono");
		maoriNumbers.addElement("iwa tekau ma whitu");
		maoriNumbers.addElement("iwa tekau ma waru");
		maoriNumbers.addElement("iwa tekau ma iwa");
	}
	
	private static void saveEasyScore(int score) {
		easyScoreVector.addElement(score);
	}
	
	private static void saveHardScore(int score) {
		hardScoreVector.addElement(score);
	}
	private static int randomInt(int lowerLimit, int upperLimit) {
		int randomNumber = (int) (Math.random() * upperLimit + lowerLimit);
		return randomNumber;
	}
	
	private static String readRecoutFile() {
		try {
			BufferedReader recout = new BufferedReader(new FileReader("/home/se206/Documents/HTK/MaoriNumbers/recout.mlf"));
			System.out.println("7");
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
		
			System.out.println("lolza");
			return outputString;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("8");
			return null;
		}
		
	}
	//currently function does not wait for process to finish
	//when i put waitFor() in, the process never finishes...
	private static void createNewProcess(String command) {
		try {
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
			File workingDirectory = new File("/home/se206/Documents/HTK/MaoriNumbers/");
			pb.directory(workingDirectory);
			System.out.println(4);
			pb.redirectErrorStream(true);
			System.out.println(5);
			Process process = pb.start();
			BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
			System.out.println(6);
			process.waitFor();
			System.out.println(10);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
//		String command = "sh /home/se206/Documents/HTK/MaoriNumbers/GoSpeech";
//		createNewProcess(command);
		System.out.println(1);
		createNewProcess("arecord -d 3 -r 22050 -c 1 -i -t wav -f s16_LE foo.wav");
		System.out.println(2);
		createNewProcess("HVite -H HMMs/hmm15/macros -H HMMs/hmm15/hmmdefs -C user/configLR  -w user/wordNetworkNum -o SWT -l '*' -i recout.mlf -p 0.0 -s 5.0  user/dictionaryD user/tiedList foo.wav 1> recout.mlf");
		System.out.println(3);
		createNewProcess("aplay foo.wav");
		
		
		
		
		String outputString = readRecoutFile();
		System.out.println(10);
		System.out.println(outputString);
		
	}

}
