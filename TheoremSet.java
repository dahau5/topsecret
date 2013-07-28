import java.util.*;


public class TheoremSet {

	HashMap<String, Expression> myThms;
	HashMap<String, Expression> myEquivalents;

	public TheoremSet ( ) {
		myThms = new HashMap<String, Expression>();
	}

	public Expression put (String s, Expression e) {

		//Sets the value at Key(s) in myThems to Expression(e) 
		//and returns the old value at S or null if empty.
		return myThms.put(s,e);
	}
	
	// Throws an exception is @app is not a valid application of
	// the theorem @thmName
	public void checkTheoremApplication(Expression app, String thmName) {
		// precondition: thmName is a key in myThms, which is checked in
		// Logic.inputStringChecker
		// precondition: @app is of valid Expression syntax
		
		// myEquivalents keeps track of which subexpressions in the theorem
		// are equivalent to subexpressions of the theorem application
		myEquivalents = new HashMap<String, Expression>();
		Expression thm = myThms.get(thmName);
		
		
	}
	
	private boolean checkHelper(Expression thm, Expression app) {
		String thmLeftmost;
		int depth = 0;
		while (thm.exprtree.myRoot.getleft() != null ) {
			depth++;
			thmLeftmost = thm.exprtree.myRoot.getleft().get();
		}
		String appLeftmost;
		while (depth > 0) {
			appLeftmost = app.exprtree.myRoot.getleft().get();
			depth--;
		}
		
		
		
		
		
		
		return false;
	}
}
