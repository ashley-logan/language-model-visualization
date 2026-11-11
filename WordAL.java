// WordAL.java
// Author: Ashley Logan
// represents a single word within the model,
// keeping track of the word itself, its Transitions array,
// and its x,y position in the visualization

public class WordAL {
  private String word;
  private Transition[] transitions;
  private int x;
  private int y;
  
  public WordAL(String thisWord, int nextCount, 
    String[] nextWords, double[] probabilities) {
      transitions = new Transition[nextCount];
      word = thisWord;
  x = 0;
  y = 0;
  }
}
