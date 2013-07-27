// The methods associated with the different logical
// inferences. Evaluating 

//linemap
//datamap

//Not current as of 11:40pm July 26th

import javax.swing.tree.TreeNode;


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
		if(!(node1.myitem.equals(node2))){
			return false;
		}
		return treeEqualshelper(node1.myLeft, node2.myLeft) && treeEqualshelper(node1.myRight, node2.myRight);
	}
	
	public static void assume(Expression exp1, LineNumber myLine){
		// yay pseudo code. So I need to check if the exp1 is the myTree.myleft of the expression
		// tied to the previous line number (its own line number with the last number taken off
		int index;
		String temp = myLine.myNumber;
		if (temp.length()>1){
			for (int i = myLine.myNumber.length()-1; i>0 ; i--){
				if (myLine.myNumber.get(i) == "."){
					index = i;
					break;
				}
			}
			temp = myLine.myNumber.substring(0, index);
		}
		Expression exp2 = hashmap.get(temp).get(1);
		if(treeEquals(exp1, exp2.myTree.myLeft)){
			exp1.isproven = true;
		}else if(exp1.mystring.startsWith("~") && treeEquals(exp1.myTree, exp2.myTree)){
			exp1.isproven = true;
		}
		//need to check if the previous line has a & or a |, in which case
		//the only acceptable expression is the not of it. Otherwise the left hand statement 
		//is A-Ok
		
	}
	

	public static void repeat(LineNumber currentLine, Expression exp1) {
		// repeat can be used to repeat any line that the current line is allowed to reference, other than the 1st.
		// proof checker should just copy the expression from that line into the statement at the current line number.
		// throw InferenceError if accessing a line inside of a subproof outside of a subproof
		// All repeat does is copy the expression of an accessible line into the current line.
		// i.e. 3 repeat 2.1 p ------- throws error. 
		String line = currentLine.myNumber; //Get currentLines Line Number.
		if(line.equals("1")) { //if trying to access the first line throw an exception [ 3 repeat 1 p ]
			throw new IllegalInferenceException("Repeat is not allowed to access First Line of a proof");
		}
		// 3 repeat 2 p
		// line == 2, expr == p
		if (!exp1.myname.equals(myLineNumbers.get(line).getLast()) { //check to make sure repeat is referring to correct expression.
			throw new IllegalInferenceException("Repeat expression " + exp1.myname + "does not match expression " + myLineNumbers.get(line).getLast());
		}
		else {
			exp1 = myLineNumbers.get(line).getLast(); //copy expression at referenced line into current expression.
		}


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
				throw new IllegalInferenceException("Improper use of mp. " + test + " not in " + myE2);
			}
		}else{
			throw new IllegalInferenceException("Improper use of mp. " + myE1 + " not in " + myE2);
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
