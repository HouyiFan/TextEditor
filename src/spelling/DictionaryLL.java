package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;	
	//private int size;
	
    // TODO: Add a constructor
	public DictionaryLL() {
		this.dict = new LinkedList<String>();
		//this.size = 0;
	}


    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	String lowerWord = word.toLowerCase();
    	if(lowerWord.equals("")){
    		return false;
    	}
    	if(dict.contains(lowerWord)){
    		return false;
    	}
    	else{
    		dict.add(lowerWord);
    		return true;
    	}
    	
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	s = s.toLowerCase();
    	if(s.equals("")){
    		return false;
    	}
    	if(dict.contains(s)){
    		return true;
    	}   	
        return false;
    }

    
}
