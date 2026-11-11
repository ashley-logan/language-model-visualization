// EdgeAL.java							Author: AMH
// Represents a transition for the purposes of visualization. Includes both the
// source and destination Word objects and a Line object connecting the
// locations of these two objects

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class EdgeAL {

  private WordAL from; // source word for the transition
  private WordAL to;   // word that can fall after the source word
  private Line line;   // Line starting at from coordinates and
                       // leading to to coordinates

  // Initializes the edge using the provided objects, instantiated elsewhere
  public EdgeAL(WordAL fromWord, WordAL toWord, Line edgeLine) {
    from = fromWord;
    to = toWord;
    line = edgeLine;
  }

  // Returns a reference to the source word of the transition
  public WordAL getFrom() { return from; }

  // Returns a reference to the word that can come next in this transition
  public WordAL getTo() { return to; }

  // Changes the Line associated with this transition to the indicated color
  public void setColor(Color color) { line.setStroke(color); }
}
