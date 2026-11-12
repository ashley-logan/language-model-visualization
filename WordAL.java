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

  public WordAL(String thisWord, int nextCount, String[] nextWords,
                double[] probabilities) {
    transitions = new Transition[nextCount];
    word = thisWord;
    x = -1;
    y = -1;
    for (int i = 0; i < transitions.length; i++) {
      transitions[i] = new Transition(nextWords[i], probabilities[i]);
    }
  }

  public boolean hasTransitionTo(String toWord) {
    boolean hasTran = false;
    for (int i = 0; (i < transitions.length) && (!hasTran); i++) {
      //
    }
  }
  public String getText() { return word; }

  public int getX() { return x; }

  public int getY() { return y; }

  public void setPosition(int newX, int newY) {
    x = newX;
    y = newY;
  }

  public String toString() {
    String output = word;

    if (transitions.length == 0) {
      output += " is a STOP word";
    }

    for (int i = 0; i < transitions.length; i++) {
      output += "\n\t-->" + transitions[i];
    }

    return output;
  }
}
