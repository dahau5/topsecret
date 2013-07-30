import java.util.*;


public class TheoremSet {

	HashMap<String, Expression> myThms;
	HashMap<String, Expression> myEquivalents;
	String alph = "abcdefghijklmnopqrstuvwxyz";
	boolean DEBUGGING = false;

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
	public void checkTheoremApplication(String thmName, Expression app) throws IllegalLineException {
		// precondition: thmName is a key in myThms, which is checked in
		// Logic.inputStringChecker
		// precondition: @app is of valid Expression syntax

		// myEquivalents keeps track of which subexpressions in the theorem
		// are equivalent to subexpressions of the theorem application
		if (!myThms.containsKey(thmName)) {
			throw new IllegalLineException("Not a valid theorem name");
		}
		myEquivalents = new HashMap<String, Expression>();
		Expression thm = myThms.get(thmName);

		try {
			checkHelper(thm, app);
		}
		catch(IllegalLineException e) {
			throw e;
		}


	}

	// Assembles myEquivalents, which relates each primitive expression in
	// @thm to the relative position in @app
	public void checkHelper(Expression thm, Expression app) throws IllegalLineException {

//
//		if (DEBUGGING) {
//			// print current contents of myEquivalents
//			System.out.println("CURRENT CONTENTS OF myEqvs");
//			for (String name: myEquivalents.keySet()){
//	            String key = name.toString();
//	            String value = myEquivalents.get(name).exprtree.myRoot.toString();  
//	            System.out.println(key + " " + value);  
//			} 
//		}


		/*RECURSIVE VERSION*/
		if (thm == null && app == null) {
			throw new IllegalLineException("Null arguments invalid");
		}
		Expression.ExprTree.ExprTreeNode thmRoot = thm.exprtree.myRoot;
		Expression.ExprTree.ExprTreeNode appRoot = app.exprtree.myRoot;

		Expression thmLeft = null;
		Expression thmRight = null;
		Expression appLeft = null;
		Expression appRight = null;
		Expression appRootExpr;
		
		/* BASE CASE 0 */
		// if thm is longer than app, throw an exception
		if (thm.myname.length() > app.myname.length()) {
			throw new IllegalLineException("Application will always be invalid when" +
					" smaller than the theorem");
		}

		/* BASE CASE 1 */
		// If thmRoot is a lower-case, single character, check myEquivalents
		// to see if we have seen it before
		if (thmRoot.getleft() == null && thmRoot.getright() == null) {
			// If we have seen it before, check to see if @app matches the
			// expression stored in myEqvs at thmRoot
			if (myEquivalents.containsKey(thmRoot.get())) {
				// If @app does not match the stored expr, throw an Exception
				if (!myEquivalents.get(thmRoot.get()).equals(app)) {
					throw new IllegalLineException("Mismatching application of" +
							" previously seen expression");
				}
			}
			// If we have not seen it before, add it to myEquivalents for
			// future reference
			else {
				myEquivalents.put(thmRoot.get(), app);
			}
		}
		
		/* RECURSIVE CASE 1: THM HAS NO LEFT CHILD */
		// if @thm has no left child, 
		else if (thmRoot.getleft() == null) {
			if (appRoot.getleft() == null) {
				thmRight = new Expression(thm.exprtree.myRoot.getright().toString());
				appRight = new Expression(app.exprtree.myRoot.getright().toString());
				checkHelper(thmRight, appRight);
			}
		}
		else if (thmRoot.getright() == null) {
			if (appRoot.getright() == null) {
				thmLeft = new Expression(thm.exprtree.myRoot.getleft().toString());
				appLeft = new Expression(app.exprtree.myRoot.getleft().toString());
				checkHelper(thmLeft, appLeft);
			}
			else {
				appRight = new Expression(app.exprtree.myRoot.getright().toString());
				checkHelper(thm, appRight);
			}
		}
		else {
			thmRight = new Expression(thm.exprtree.myRoot.getright().toString());
			appRight = new Expression(app.exprtree.myRoot.getright().toString());
			thmLeft = new Expression(thm.exprtree.myRoot.getleft().toString());
			if (DEBUGGING) {
				System.out.println("CURRENT APP:       " + app.myname);
				System.out.println("CURRENT THM:       " + thm.myname);
				System.out.println("APP LEFT:     " + appRoot.getleft().toString());
			}
			appLeft = new Expression(app.exprtree.myRoot.getleft().toString());
			checkHelper(thmRight, appRight);
			checkHelper(thmLeft, appLeft);
		}
//		
//		
//		
//		
//		
//		/* RECURSIVE CASE 1: NO LEFT CHILDREN */
//		// If @app has no left child, consider @thm's left child
//		else if (app.exprtree.myRoot.getleft() == null) {
//			// If the @thm has a left child, it is larger than @app
//			// and we return false
//			if (thm.exprtree.myRoot.getleft() != null) {
//				throw new IllegalLineException("Application will always be invalid when" +
//						" smaller than the theorem");
//			}
//			// If neither thm nor app has a left child, consider their
//			// right children
//			else {
//				thmRight = new Expression(thm.exprtree.myRoot.getright().toString());
//				appRight = new Expression(app.exprtree.myRoot.getright().toString());
//				checkHelper(thmRight, appRight);
//			}
//		}
//
//		/* RECURSIVE CASE 2: NO RIGHT CHILDREN */
//		// If @app has no right child, consider @thm's left child
//		else if (app.exprtree.myRoot.getright() == null) {
//			// If @thm has a right child, it is larger than @app
//			// and we return false
//			if (thm.exprtree.myRoot.getright() != null) {
//				throw new IllegalLineException("Not a valid application of thm: " + thm.myname);
//			}
//			else {
//				thmLeft = new Expression(thm.exprtree.myRoot.getleft().toString());
//				appLeft = new Expression(app.exprtree.myRoot.getleft().toString());
//				checkHelper(thmLeft, appLeft);
//			}
//		}
//		
//		/* RECURSIVE CASE 3: CHILDREN ON BOTH SIDES */
//		else if (thmRoot.getleft() != null && thmRoot.getright() != null) {
//			thmLeft = new Expression(thm.exprtree.myRoot.getleft().toString());
//			appLeft = new Expression(app.exprtree.myRoot.getleft().toString());
//			thmRight = new Expression(thm.exprtree.myRoot.getright().toString());
//			appRight = new Expression(app.exprtree.myRoot.getright().toString());
//			checkHelper(thmLeft, appLeft);
//			checkHelper(thmRight, appRight);
//		}
	}
}
