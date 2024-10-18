
public class Article extends Content {
	/*
	 * Subclass of Content representing an article to be stored in the program
	 */

	private int currentParagraph;
	private int articleLengthInWords;

	public Article() {
		super();
		currentParagraph = 0;
		articleLengthInWords = 0;
	}

	public Article(String t, String a, int len, String stat, String[] tagsArr) {
		super(t, a, stat, tagsArr);
		articleLengthInWords = len;
		currentParagraph = 0;
	}

	public Article(String t, String a, int len, String stat, String[] tagsArr, int curPar) {
		super(t, a, stat, tagsArr);
		articleLengthInWords = len;
		currentParagraph = curPar;
	}

	// set the current paragraph for this article
	public void setCurrentPar(int newParNum) {
		currentParagraph = newParNum;
	}

	// get the current paragraph for this article
	public int getCurrentPar() {
		return currentParagraph;
	}

	// get the length of this article in paragraphs
	public int getLength() {
		return articleLengthInWords;
	}

	// set the length of this article in paragraphs
	public void setLength(int len) {
		articleLengthInWords = len;
	}

	// get the class of this item
	public String getClassOfObj() {
		return "Article";
	}

}
