
public class Book {
	// Static or class variables
	
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		System.out.println(Sentence.numberOfSentences);

		Sentence sentenceOne = new Sentence("My first sentence.");
		Sentence sentenceTwo = new Sentence("My second sentence.");
		
		System.out.println(Sentence.numberOfSentences);
		
		sentenceOne.theSentence = "My junk sentence.";
		

		sentenceOne.printSentence();
		sentenceTwo.printSentence();
		sentenceOne.printNumberOfSentences();
	
	
	}

}
class Sentence{
	 static int numberOfSentences = 0;
	
	/**
	 * Constructor. This creates a new object and does some things.
	 */
	public Sentence(String theSent){
		System.out.println("I have just been created");
		numberOfSentences++;
		theSentence = theSent;
	}
	

	public String theSentence;
	
	public void printSentence(){
		System.out.println(theSentence);
	}
	
	public void printNumberOfSentences(){
		System.out.println(Sentence.numberOfSentences);
	}
}
