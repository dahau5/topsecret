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
		if (DEBUGGING) {
			System.out.println(thm.exprtree.myRoot);
		}
		try {
			checkHelper(thm, app);
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	// Assembles myEquivalents, which relates each primitive expression in
	// @thm to the relative position in @app
	public void checkHelper(Expression thm, Expression app) throws IllegalLineException {
		
		
		if (DEBUGGING) {
			// print current contents of myEquivalents
			System.out.println("CURRENT CONTENTS OF myEqvs");
			for (String name: myEquivalents.keySet()){
	            String key = name.toString();
	            String value = myEquivalents.get(name).exprtree.myRoot.toString();  
	            System.out.println(key + " " + value);  
			} 
		}
		
		
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
		
		/* BASE CASE 1 */
		// If thmRoot is a lower-case, single character, check myEquivalents
		// to see if we have seen it before
		if (alph.contains(thmRoot.get())) {
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
		
		/* BASE CASE 2*/
		// If the application has no children, consider the theorem
		
		if (DEBUGGING) {
			System.out.println("VALUE OF @app:     " + app.exprtree.myRoot.toString());
			System.out.println("VALUE OF @app.myleft:       " + app.exprtree.myRoot.getleft());
			System.out.println("VALUE OF @app.myright:       " + app.exprtree.myRoot.getright());
		}
		
		if (appRoot.getleft() == null && appRoot.getright() == null) {
			
			// @thm can never be larger than @app, so if it has children
			// where @app has none, return false
			if (thmRoot.getleft() != null || thmRoot.getright() != null) {
				throw new IllegalLineException("Application will always be invalid when" +
						" smaller than the theorem");
			}
//			
//			else {
//				if (alph.contains(thmRoot.get())) {
//					// If thmRoot is a lower-case single character,
//					// see if we have seen it before
//					if (myEquivalents.containsKey(thmRoot)) {
//						// If we have seen it before and app does not
//						// equal the previously seen expr, throw an Exception
//						if (!myEquivalents.get(thmRoot).equals(app)) {
//							throw new IllegalLineException("The expression at same position" +
//									" relative to the same position in THM is not the same" +
//									" a previous occurrence of the same expression in THM");
//						}
//					}
//					// If we haven't seen thmRoot yet, we can make no judgments
//					// and we memoize the expression at the same relative location
//					// to thmRoot in @app for future reference
//					else {
//						myEquivalents.put(thmRoot.get(), app);
//					}
//				}
//			}
		}
		
		/* RECURSIVE CASE 1: NO LEFT CHILDREN */
		// If @app has no left child, consider @thm's left child
		else if (app.exprtree.myRoot.getleft() == null) {
			// If the @thm has a left child, it is larger than @app
			// and we return false
			if (thm.exprtree.myRoot.getleft() != null) {
				throw new IllegalLineException("Application will always be invalid when" +
						" smaller than the theorem");
			}
			// If neither thm nor app has a left child, consider their
			// right children
			else {
				thmRight = new Expression(thm.exprtree.myRoot.getright().toString());
				appRight = new Expression(app.exprtree.myRoot.getright().toString());
				checkHelper(thmRight, appRight);
			}
		}
		
		/* RECURSIVE CASE 2: NO RIGHT CHILDREN */
		// If @app has no right child, consider @thm's left child
		else if (app.exprtree.myRoot.getright() == null) {
			// If @thm has a right child, it is larger than @app
			// and we return false
			if (thm.exprtree.myRoot.getright() != null) {
				throw new IllegalLineException("Not a valid application of thm: " + thm.myname);
			}
			else {
				thmLeft = new Expression(thm.exprtree.myRoot.getleft().toString());
				appLeft = new Expression(app.exprtree.myRoot.getleft().toString());
				checkHelper(thmLeft, appLeft);
			}
		}

		/* RECURSIVE CASE 3: CHILDREN ON BOTH SIDES */
		// If @thm is not larger than @app, ensure that each expr in @thm maps to the same
		// expr in @app for every occurrence (considering only primitive expr's in @thm
		// to avoid redundancy
//		else if (alph.contains(thmRoot.get())) {
//			
//			if (DEBUGGING) {
//				System.out.println("appRoot EQUALS:    " + appRoot);
//				System.out.println("appRoot.toString() EQUALS:    " + appRoot.toString());
//			}
//			
//			appRootExpr = new Expression(appRoot.toString());
//			// If the expr in @app does not match a prior occurrence of the corresponding
//			// expr in @thm, return false
//			if (myEquivalents.containsKey(thmRoot.get()) && !myEquivalents.get(thmRoot.get()).equals(appRootExpr)) {
//				throw new IllegalLineException("Not a valid application of thm: " + thm.myname);
//			}
//			// If we have not seen this expr before, add it to myEquivalents and continue
//			else {
//				myEquivalents.put(thmRoot.get(), appRootExpr);
//				checkHelper(thmLeft, appLeft);
//				checkHelper(thmRight, appRight);
//			}
//		}
		else if (thmRoot.getleft() != null && thmRoot.getright() != null) {
			thmLeft = new Expression(thm.exprtree.myRoot.getleft().toString());
			appLeft = new Expression(app.exprtree.myRoot.getleft().toString());
			thmRight = new Expression(thm.exprtree.myRoot.getright().toString());
			appRight = new Expression(app.exprtree.myRoot.getright().toString());
			checkHelper(thmLeft, appLeft);
			checkHelper(thmRight, appRight);
		}
	}
}
