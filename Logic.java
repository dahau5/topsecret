//import javax.swing.tree.TreeNode;


// The methods associated with the different logical
// inferences. Evaluating 

//linemap
//datamap

public class Logic {
	
	
	public Logic(){
		
	}
	
	
	public static void assume(Expression exp1, String myLine) throws IllegalInferenceException{
		Expression exp2 = (Expression)Proof.myLineNumbers.get(myLine).getLast();
		String not = "~" + exp2.myname;
		if(exp2.exprtree.myRoot.get().equals("&") || exp2.exprtree.myRoot.get().equals("|")){
			if (!exp1.myname.equals(not)){
				throw new IllegalInferenceException("Can only infer the '~' of an '&' or '|'.");
			}
		}
		if(!Expression.ExprTree.treeEqualshelper(exp1.exprtree.myRoot, exp2.exprtree.myRoot.getleft()) || !exp1.myname.equals(not)){
			throw new IllegalInferenceException("Bad assumption. Assume must either be the left of the previous expr or '~' of the entire expr.");
		}
		}
		
		
	
	
	public static void moduspollens (Expression exp1, Expression exp2, Expression test)
		throws IllegalInferenceException {
		// from exp1 and (exp1=>exp2),  test(exp2) is true
		Expression myE1;
		Expression myE2;
		if (exp1.myname.length() < exp2.myname.length()){
			myE1 = exp1;
			myE2 = exp2;
		} else {
			myE1 = exp2;
			myE2 = exp1;
		}
		if(!myE2.exprtree.myRoot.get().equals("=>"))
			throw new IllegalInferenceException("One of the referenced expressions must be an implication.");
		
		if (!Expression.ExprTree.treeEqualshelper(myE1.exprtree.myRoot, myE2.exprtree.myRoot.getleft())){ 
			throw new IllegalInferenceException("Bad assumption, improper use of mp. "+ myE1.myname + " must equal the left hand side of the second expression.");
		
		}else if (!Expression.ExprTree.treeEqualshelper(myE2.exprtree.myRoot, test.exprtree.myRoot)){
				throw new IllegalInferenceException("Bad assumption. Improper use of mp. " + test + " not in " + myE2);
			}
	}
	
	public static void modustollens (Expression exp1, Expression exp2, Expression test) throws IllegalInferenceException{
		// ~exp2 and exp1=>exp2(exp1) ~exp1(test) is true
		Expression myE1;
		Expression myE2;
		if (exp1.myname.length() < exp2.myname.length()){
			myE1 = exp1;
			myE2 = exp2;
		} else {
			myE1 = exp2;
			myE2 = exp1;
		}
		if(!myE2.exprtree.myRoot.get().equals("=>"))
			throw new IllegalInferenceException("One of the referenced expressions must be an implication.");
		
		if (!myE1.exprtree.myRoot.get().equals("~") || !test.exprtree.myRoot.get().equals("~")){
			throw new IllegalInferenceException("To use modus tollens means one of the referenced expressions " +
					"must be the '~' of the second half of the other expression.");
		}
		if (!Expression.ExprTree.treeEqualshelper(test.exprtree.myRoot.getright(), myE2.exprtree.myRoot.getleft())){
			throw new IllegalInferenceException("Bad assumption. " + test + " must equal left side of the implication.");
		}
		if (!Expression.ExprTree.treeEqualshelper(myE1.exprtree.myRoot.getright(), myE2.exprtree.myRoot.getright())){
			throw new IllegalInferenceException("Bad assumption. " + myE1 + " must equal the left side of the implication.");
		}
	}
	
	//bad. need to fix! Only check is that this show must be the 
	public static void show (Expression exp1, LineNumber line) throws IllegalInferenceException{
		
	}
	
	public static void construction (Expression exp1, Expression exp2) throws IllegalInferenceException{
		// exp2 (exp1 => exp2) for any exp1
		Expression myE1;
		Expression myE2;
		if (exp1.myname.length() < exp2.myname.length()){
			myE1 = exp1;
			myE2 = exp2;
		} else {
			myE1 = exp2;
			myE2 = exp1;
		}
		if(!Expression.ExprTree.treeEqualshelper(myE1.exprtree.myRoot, myE2.exprtree.myRoot.getright())){
			throw new IllegalInferenceException(myE1.myname + " must be the right hand side of " + myE2.myname + ".");
		}
		
	}
	
	public static void contradiction (Expression exp1, Expression exp2, Expression test) throws IllegalInferenceException{
		// ~exp1 and exp1 infer ANY EXPRESSION
		Expression myE1;
		Expression myE2;
		if (exp1.myname.length() == exp2.myname.length()){
			throw new IllegalInferenceException("One expression must be the ~ of the other.");
		}
		if (exp1.myname.length() < exp2.myname.length()){
			myE1 = exp1;
			myE2 = exp2;
		} else {
			myE1 = exp2;
			myE2 = exp1;
		}
		//Need to add some check for the 'test' to make sure that the 'test' 
		//variable is correctly inputted and used by the user.
		if(!myE2.myname.startsWith("~") || !myE1.myname.startsWith("~")){
			throw new IllegalInferenceException("One expression must be the ~ of the other.");
		}
		
		if(myE2.myname.startsWith("~")){
			if(!Expression.ExprTree.treeEqualshelper(myE1.exprtree.myRoot, myE2.exprtree.myRoot.getright())){
				throw new IllegalInferenceException("The expressions must be the same, except for the presence of a '~'.");
			}
		}
				
		
}
}
