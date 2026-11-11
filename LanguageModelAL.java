// LanguageModelAL				Authors: AMH + Ashley Logan
// HERE Implements a graphics application that processes a text file
// representing a Markov language model, displays the possible transitions
// between words, and simulates the generation of sentences following the model.

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LanguageModelAL extends Application {

  private WordAL[] words; // collection of WordAL objects, one for each unique
                          // word in the language model
  private WordAL currentWord; // the current word is the most recent one added
                              // to a sentence
  private WordAL
      previousWord; // word added to the sentence previous to the current one
  private EdgeAL[] edges;   // collection of EdgeAL objects for visually
                            // representing all transitions in the model
  private Circle spotlight; // circle to highlight the current word
  private Text
      sentence; // Text object for displaying the sentence being generated
  private TextArea history; // text box for displaying all generated sentences
  private Text selection;   // Text object for displaying the transitions
                            // associated with a clicked word
  private final int WINDOW_WIDTH = 800;  // window width in pixels
  private final int WINDOW_HEIGHT = 600; // window height in pixels
  
  private final boolean TEST = true;

  public void start(Stage stage) throws IOException {

    // Choose which test file to use by leaving exactly one of these lines
    // uncommented
    String filename = "smallTransitions.txt";
    // String filename = "loopTransitions.txt";
    // String filename = "skewedTransitions.txt";
    // String filename = "hubTransitions.txt";
    // String filename = "transitions.txt";
    // String filename = "scrambledTransitions.txt";

    // TODO: process the file to count the lines, get a dictionary of unique
    // words, and then instantiate and initialize the WordAL array

    int lineCount = 0;
    try {
      // count the number of lines in the file
      Scanner filescan = new Scanner(new File(filename));
      while (filescan.hasNextLine()) {
        filescan.nextLine();
        lineCount++;
      }
      if (TEST) System.out.println("DEBUG: lines in file: " + lineCount);

      String[] dict = new String[lineCount * 2]; // unique words in file
      filescan = new Scanner(new File(filename));

      int uniqueCount = 0; // keeps track of the number of unique words from the file
      String firstWord;
      String secondWord;
      int dictIdx = 0;
      // iterates through the first and second word on each line of the file
      for (int i = 0; i < lineCount; i++) {
        firstWord = filescan.next();
        secondWord = filescan.next();
        if (TEST) System.out.println("DEBUG: Line " + i);
        filescan.nextLine();

        boolean repeatWord = false;
        // iterates through the unique words, checking if it contains the first
        // word
        for (int j = 0; j < uniqueCount && !repeatWord; j++) {
          repeatWord = (firstWord.equalsIgnoreCase(dict[j]))
        }
        if (!repeatWord) {
          dict[uniqueCount] = firstWord;
          uniqueCount++;
        }

        repeatWord = false;
        // iterates through the unique words, checking if it contains the second
        // word
        for (int j = 0; j < uniqueCount && !repeatWord; j++) {
          repeatWord = (secondWord.equalsIgnoreCase(dict[j]))
        }
        if (!repeatWord) {
          dict[uniqueCount] = secondWord;
          uniqueCount++;
        }
      }

      words = new WordAL[uniqueCount];

      // iterates through the unique words dictionary and
      // instantiates word objects and inserts them into the words array
      for (int i = 0; i < uniqueCount; i++) {
        words[i] = // WordAL;
        String nextWords = new String[lineCount];
        double probs = new double[lineCount];
        int transitionCount = 0; // count of nextWords and count of probs for this word
        filescan = new Scanner(new File(filename));

        // iterates through the file, adding the next words and probabilties
        // associated with each unique word
        for (int j = 0; j < lineCount; j++) {
          String firstWord = filescan.next();
          if (firstWord.equalsIgnoreCase(dict[i])) {
            nextWords[transitionCount] = filescan.next();
            probs[transitionCount] = filescan.nextDouble();
            transitionCount++;
          }
        }
        // TODO: instantiate WordAL object
      }

    } catch (Exception e) {
      // TODO: handle exception
    }
    // Create root Group for collecting graphical objects
    Group root = new Group();

    // Uncomment the following code once your WordAL array is complete to
    // compute and set the position of each WordAL object within the
    // visualization, create the associated Text objects, and add them to the
    // Group It also sets an initial value for currentWord and initializes the
    // highlight circle to the location of that word
    // For this code to work, it must include the methods called on a WordAL
    // object as shown in this code.
    /*
double centerX = WINDOW_WIDTH / 2.0;
double centerY = WINDOW_HEIGHT / 2.0;
double radius = Math.min(WINDOW_WIDTH, WINDOW_HEIGHT) / 2.0 - 80;
for (int i = 0; i < words.length; i++) {
double angle = 2 * Math.PI * i / words.length - Math.PI / 2;
words[i].setPosition(centerX + radius * Math.cos(angle),
                                                            centerY + radius *
Math.sin(angle));
}

    for (int i = 0; i < words.length; i++) {
Text label = new Text(words[i].getX(), words[i].getY(), words[i].getText());
label.setFont(Font.font("System", FontWeight.BOLD, 16));
root.getChildren().add(label);
}

    currentWord = words[0];
spotlight = new Circle(words[0].getX(), words[0].getY(), 30);
spotlight.setFill(null);
spotlight.setStroke(Color.YELLOW);
spotlight.setStrokeWidth(4);
root.getChildren().add(spotlight);
    */

    // TODO: add code to draw the lines between WordAL objects connected by
    // transitions and include them in the root Group
    // NOTE: given a Line object, the toBack() method can be called on it to
    // have it fall behind other objects in the visualization

    // Add the "Next word" button in the bottom-left corner
    Button nextButton = new Button("Next word");
    nextButton.setTranslateX(20);
    nextButton.setTranslateY(WINDOW_HEIGHT - 50);
    nextButton.setOnAction(this::nextAction);
    root.getChildren().add(nextButton);

    // Add the "Reset" button next to the "Next word" button
    Button resetButton = new Button("Reset");
    resetButton.setTranslateX(110);
    resetButton.setTranslateY(WINDOW_HEIGHT - 50);
    resetButton.setOnAction(this::resetAction);
    root.getChildren().add(resetButton);

    // Add the Text object for generated sentences
    sentence = new Text(20, 30, "");
    sentence.setFont(Font.font("System", FontWeight.BOLD, 16));
    sentence.setWrappingWidth(WINDOW_WIDTH - 40);
    root.getChildren().add(sentence);

    // Add the TextArea object for the sentence history
    history = new TextArea();
    history.setEditable(false);
    history.setWrapText(true);
    history.setPrefSize(150, 440);
    history.setTranslateX(20);
    history.setTranslateY(70);
    root.getChildren().add(history);

    // Add the Text object for descriptions of clicked words
    selection = new Text(210, WINDOW_HEIGHT - 50, "");
    selection.setFont(Font.font("System", FontWeight.BOLD, 16));
    selection.setWrappingWidth(WINDOW_WIDTH - 230);
    root.getChildren().add(selection);

    Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    scene.setOnMouseClicked(this::windowClicked);

    stage.setTitle("Language Model Explorer");
    stage.setScene(scene);
    stage.show();
  }

  private void nextAction(ActionEvent event) {}

  private void resetAction(ActionEvent event) {}

  private void windowClicked(MouseEvent event) {
    System.out.println(event.getX() + " " + event.getY());
  }

  private void colorEdges() {}

  // A local method to this class that can be called as follows and returns a
  // Polygon object shaped as a small triangle whose tip is at the end of the
  // line provided as an argument Example method call: Polygon head =
  // arrowhead(line);
  private static Polygon arrowhead(Line line) {
    double length = 12;
    double spread = 0.45;
    double tipX = line.getEndX();
    double tipY = line.getEndY();
    double angle = Math.atan2(tipY - line.getStartY(), tipX - line.getStartX());
    return new Polygon(tipX, tipY, tipX - length * Math.cos(angle - spread),
                       tipY - length * Math.sin(angle - spread),
                       tipX - length * Math.cos(angle + spread),
                       tipY - length * Math.sin(angle + spread));
  }

  public static void main(String[] args) { launch(args); }
}
