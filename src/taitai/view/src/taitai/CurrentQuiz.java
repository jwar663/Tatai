package taitai;

public class CurrentQuiz {

	int _answer, _questionNumber, _numCorrect, _level;
	boolean _firstTry;
	String _question;

	public CurrentQuiz(int level) {
		_questionNumber = 1;
		_numCorrect = 0;
		_level = level;
		_firstTry = true;
		setQandA();
		
	}

	public void nextQuestion() {
		_questionNumber++;
		_firstTry = true;
		setQandA();
		}
	
	public void incrementNumCorrect() {
		_numCorrect++;
	}

	public void secondTry() {
		_firstTry = false;
	}

	public int getAnswer() {
		return _answer;
	}

	public int getQuestionNumber() {
		return _questionNumber;
	}
	
	public int getNumcorrect() {
		return _numCorrect;
	}
	
	public int getLevel() {
		return _level;
	}
	
	public boolean isFirstTry() {
		return _firstTry;
	}
	
	public String getQuestion() {
		return _question;
	}
	
	private void setQandA() {
		switch (_level) {
		case 1: 
			_answer = TaitaiModel.startTest(1);
			_question = _answer + "";
			break;
		case 2: 
			_answer = TaitaiModel.startTest(2);
			_question = _answer + "";
			break;
		case 3: 
			_answer = TaitaiModel.startTest(3);
			_question = TaitaiModel.generateQuestion(_answer);
			break;
		case 4: 
			// gen random number
			// get question from list
			// _answer = get answerfrom list
			break;
		default:
			break;
		}
	}

}
