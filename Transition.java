// Transition.java				Author: AMH
// Represents the data about one transition: which word it leads to (as text)
// and how likely it is.

public class Transition {

    private String toText;			// the word that this transition leads to
    private double probability;		// the probability this is the next word

	// Initializes a Transition object using the provided values
    public Transition(String to, double transitionProbability) {
        toText = to;
        probability = transitionProbability;
    }

	// Returns the word led to in this transition as a String
    public String getTo() {
        return toText;
    }

	// Returns the probability of this transition as a decimal value between 0 and 1
    public double getProbability() {
        return probability;
    }

	// Returns a text summary of both elements of this object
    public String toString() {
        return toText + " (" + probability + ")";
    }
}
