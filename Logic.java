// The methods associated with the different logical
// inferences. Evaluating 

//linemap
//datamap

public class Logic {
  
	
	public Logic(){
		
	}
	
	public boolean treeEquals(tree tree1, tree tree2){
		if (tree1.myRoot == null || tree2.myRoot == null){
			throw new IllegalArgumentException("Can't use a null tree");
		} return treeEqualshelper(tree1.myRoot, tree2.myRoot);
	}
	
	private boolean treeEqualshelper(TreeNode node1, TreeNode node2){
		if(node1 == null && node2 == null){
			return true;
		}
		if(!(node1.equals(node2))){
			return false;
		}
		return treeEqualshelper(node1.myLeft, node2.myLeft) && treeEqualshelper(node1.myRight, node2.myRight);
	}
	
	public static void assume(Expression exp1){
		
	}
	
	public static void moduspolens (Expression exp1, Expression exp2, Expression test)
		throws IllegalLineException {
		// from exp1 and (exp1=>exp2)exp2,  test is true
		Expression myE1;
		Expression myE2;
		if (exp1.mystring.length() < exp2.mystring.length()){
			myE1 = exp1;
			myE2 = exp2;
		} else {
			myE1 = exp2;
			myE2 = exp1;
		}
		// I need some way to grab the tree at the right point so as to compare it correctly with 
		// the input value given to mp. Can't compare the entire expression tree each time modus
		// polens is called. Unfeasible. 
		
		//The toString is grabbing the string representation of an expression tree of 
		//the second value given to modus polens. Or the expression that holds both the
		//assumed statement, and the one we are testing to hold true. Possible that we need to make an expression
		//tree of each myE2 passed into the modus polens to ensure it is logically sound. Just an idea.
		
		if (treeEquals(myE1.myTree, myE2.myTree.myLeft)){ 
			if (treeEquals(myE2.myTree.myRight, test.myTree)){
				test.isproven = true;
			}else{
				throw new IllegalLineException("Improper use of mp. " + test + " not in " + myE2);
			}
		}else{
			throw new IllegalLineException("Improper use of mp. " + myE1 + " not in " + myE2);
		}
		//if myE1 the exact same expression as immediately before the 
		//implication in myE2: then "test.isproven = true"
	}
	
	public static void modustollens (Expression exp1, Expression exp2, Expression test){
		// ~exp2 and exp1=>exp2(exp1) ~exp1(test) is true
		Expression myE1;
		Expression myE2;
		if (exp1.mystring.length() < exp2.mystring.length()){
			myE1 = exp1;
			myE2 = exp2;
		} else {
			myE1 = exp2;
			myE2 = exp1;
		}
		if(myE1.mystring.startsWith("~") && treeEquals(myE1.myTree.myRight, myE2.myTree.myRight)){
			if(test.mystring.startsWith("~") && treeEquals(test.myTree.myRight, myE2.myTree.myLeft)){
				test.isproven = true;
			}
		}
	}
	
	public static void construction (Expression exp1, Expression exp2){
		// exp2 (exp1 => exp2) for any exp1
		Expression myE1;
		Expression myE2;
		if (exp1.mystring.length() < exp2.mystring.length()){
			myE1 = exp1;
			myE2 = exp2;
		} else {
			myE1 = exp2;
			myE2 = exp1;
		}
		if(treeEquals(myE1.myTree, myE2.myTree.myRight)){
			myE2.isproven = true;
		}
	}
	
	public static void contradiction (Expression exp1, Expression exp2, Expression test){
		// ~exp1 and exp1 infer ANY EXPRESSION
		Expression myE1;
		Expression myE2;
		if (exp1.mystring.length() < exp2.mystring.length()){
			myE1 = exp1;
			myE2 = exp2;
		} else {
			myE1 = exp2;
			myE2 = exp1;
		}
		//Need to add some check for the 'test' to make sure that the 'test' 
		//variable is correctly inputted and used by the user.
		if(myE2.mystring.startsWith("~") && treeEquals(myE1.myTree, myE2.myTree.myRight)){
			test.isproven = true;
		}
		
}
}
