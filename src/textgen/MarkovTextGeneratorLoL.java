package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * An implementation of the MTG interface that uses a list of lists.
 *
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

    // The list of words with their next words
    private List<ListNode> wordList;

    // The starting "word"
    private String starter;

    // The random number generator
    private Random rnGenerator;

    public MarkovTextGeneratorLoL(Random generator) {
        this.wordList = new LinkedList<ListNode>();
        this.starter = "";
        this.rnGenerator = generator;
    }

    /** Train the generator by adding the sourceText */
    @Override
    public void train(String sourceText) {
        if (sourceText.length() == 0) {
            return;
        }
        String[] word = sourceText.split("[!,.? ]+");
        word = sourceText.split("[ ]+");
        word = sourceText.split("[\\s]+");
        //String[] sdasd = {"sdsd"};
        //word = new String[] {"sdad","dgdg"};
        //System.out.println(word.length);
        //LinkedList<String> words = new LinkedList<String> ();
        //for(int i = 0; i < word.length; i++){
        //	words.add(word[i]);
        //}
        this.starter = word[0];

        String prevWord = this.starter;
        String w;
        for (int i = 1; i <= word.length; i++) {
            if (i == word.length) {
                w = this.starter;
            } else {
                w = word[i];
            }
            ListNode n = this.findNode(prevWord);
            if (n == null) {
                n = new ListNode(prevWord);
                n.addNextWord(w);
                this.wordList.add(n);
            } else {
                n.addNextWord(w);
            }
            prevWord = w;
        }

    }

    /**
     * Generate the number of words requested.
     */
    @Override
    public String generateText(int numWords) {
        String currWord = this.starter;
        String output = "";
        if (this.wordList.isEmpty() || numWords == 0) {
            return output;
        }
        output += currWord + " ";
        for (int i = 1; i < numWords; i++) {
            ListNode n = this.findNode(currWord);
            String w = n.getRandomNextWord(this.rnGenerator);
            output += w + " ";
            currWord = w;
        }
        return output;
    }

    // Can be helpful for debugging
    @Override
    public String toString() {
        String toReturn = "";
        for (ListNode n : this.wordList) {
            toReturn += n.toString();
        }
        return toReturn;
    }

    /** Retrain the generator from scratch on the source text */
    @Override
    public void retrain(String sourceText) {
        // TODO: Implement this method.
        this.wordList = new LinkedList<ListNode>();
        this.train(sourceText);
    }

    // TODO: Add any private helper methods you need here.
    private ListNode findNode(String word) {
        for (ListNode node : this.wordList) {
            if (word.equals(node.getWord())) {
                return node;
            }
        }
        return null;
    }

    /**
     * This is a minimal set of tests. Note that it can be difficult to test
     * methods/classes with randomized behavior.
     *
     * @param args
     */
    public static void main(String[] args) {
        // feed the generator a fixed random value for repeatable behavior
        MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
        String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
        System.out.println(textString);
        gen.train(textString);
        System.out.println(gen);
        System.out.println(gen.generateText(20));
        String textString2 = "You say yes, I say no, "
                + "You say stop, and I say go, go, go, "
                + "Oh no. You say goodbye and I say hello, hello, hello, "
                + "I don't know why you say goodbye, I say hello, hello, hello, "
                + "I don't know why you say goodbye, I say hello. "
                + "I say high, you say low, "
                + "You say why, and I say I don't know. " + "Oh no. "
                + "You say goodbye and I say hello, hello, hello. "
                + "I don't know why you say goodbye, I say hello, hello, hello, "
                + "I don't know why you say goodbye, I say hello. "
                + "Why, why, why, why, why, why, " + "Do you say goodbye. "
                + "Oh no. " + "You say goodbye and I say hello, hello, hello. "
                + "I don't know why you say goodbye, I say hello, hello, hello, "
                + "I don't know why you say goodbye, I say hello. "
                + "You say yes, I say no, "
                + "You say stop and I say go, go, go. " + "Oh, oh no. "
                + "You say goodbye and I say hello, hello, hello. "
                + "I don't know why you say goodbye, I say hello, hello, hello, "
                + "I don't know why you say goodbye, I say hello, hello, hello, "
                + "I don't know why you say goodbye, I say hello, hello, hello,";
        System.out.println(textString2);
        gen.retrain(textString2);
        System.out.println(gen);
        System.out.println(gen.generateText(20));
    }

}

/**
 * Links a word to the next words in the list You should use this class in your
 * implementation.
 */
class ListNode {
    // The word that is linking to the next words
    private String word;

    // The next words that could follow it
    private List<String> nextWords;

    ListNode(String word) {
        this.word = word;
        this.nextWords = new LinkedList<String>();
    }

    public String getWord() {
        return this.word;
    }

    public void addNextWord(String nextWord) {
        this.nextWords.add(nextWord);
    }

    public String getRandomNextWord(Random generator) {
        // TODO: Implement this method
        // The random number generator should be passed from
        // the MarkovTextGeneratorLoL class
        int index = generator.nextInt(this.nextWords.size());
        return this.nextWords.get(index);
    }

    @Override
    public String toString() {
        String toReturn = this.word + ": ";
        for (String s : this.nextWords) {
            toReturn += s + "->";
        }
        toReturn += "\n";
        return toReturn;
    }

}
