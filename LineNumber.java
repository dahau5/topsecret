import java.util.*;

public class LineNumber {

  private String myNumber;
  public Expression firstShow;
  private boolean isinitialized = false;

	public LineNumber(String num) {
		myNumber = num;
	}
	
	public String getNum(){
		return myNumber;
	}
	
	public boolean equals(Object obj){
		LineNumber test = (LineNumber) obj;
		if (this.myNumber.equals(test.getNum())){
			return true;
		}else{
			return false;
		}
	}
	
	public Expression getfirstshow(){
		return firstShow;
	}
	


	public LineNumber nextlinenumber_helper(String op, Expression provenExpr, String previousline){

		int index = 0; // Used for finding '.' in old LineNumber
		//System.out.println(op + " : I am the operation passed into nextline_helper.");
		//System.out.println(provenExpr.myname + " : I am the expression passed into nextline_helper.");
		//System.out.println(previousline + " : I am the previous line number passed into nextline_helper.");

<<<<<<< HEAD
=======
	public String getNum(){
		return myNumber;
	}

	public boolean equals(Object obj){
		LineNumber test = (LineNumber) obj;
		if (this.myNumber.equals(test.getNum())){
			return true;
		}else{
			return false;
		}
	}

	public Expression getfirstshow(){
		return firstShow;
	}



	public LineNumber nextlinenumber_helper(String op, Expression provenExpr, String previousline){

		int index = 0; // Used for finding '.' in old LineNumber
		//System.out.println(op + " : I am the operation passed into nextline_helper.");
		//System.out.println(provenExpr.myname + " : I am the expression passed into nextline_helper.");
		//System.out.println(previousline + " : I am the previous line number passed into nextline_helper.");

>>>>>>> Added toString() to ExprTreeNode
		// @line is the next proof line, read from InputSource
		// This will return myNumber + 1 if line does not include "show"
		// or the line does not prove a previous "show"s expression
		// If it is a show, concatenate ".1" to the current line number

		// if the operation is "print", do nothing
<<<<<<< HEAD
		
		myNumber = previousline;
		
=======

		myNumber = previousline;

>>>>>>> Added toString() to ExprTreeNode
		if (op.equals("print")) {
			return null;
		}

		// if the operation is "show", then we are entering a subproof


		if (op.equals("show")) {
			Proof.expr.push(provenExpr); //changed to add the expr to the stack rather than the op
			if(Proof.expr.empty()){
				System.out.println("The stack is empty!");
			}
			if (Proof.myLineNumbers.size() == 1) {
				try{
					firstShow = new Expression(provenExpr.myname); // First occurence of show and expression
				}catch (IllegalLineException e){
					System.err.println(e.getMessage());
				}
				myNumber = "2";
				return new LineNumber(myNumber);
			}
			myNumber = myNumber + ".1"; // 1 becomes 1.1
			return new LineNumber(myNumber);
		}
		// if the operation proves the expression we are trying to show, exit the subproof
		if (op.equals("ic") || op.equals("mp") || op.equals("co") || op.equals("mt")) {
<<<<<<< HEAD
			
			//System.out.println(Proof.expr.firstElement().myname);
			// If the last expression to be shown is proven in this line, end the subproof
			
			//If the top element of the stack is equal to the proven expr input, continue. 
			//Use expression equals method because they are still expression objects and not
			//strings.
			
			if (provenExpr.equals(Proof.expr.lastElement())) {
				
				//System.out.println("I made it here!!!!");
				
				Proof.expr.pop();
				
=======

			//System.out.println(Proof.expr.firstElement().myname);
			// If the last expression to be shown is proven in this line, end the subproof

			//If the top element of the stack is equal to the proven expr input, continue. 
			//Use expression equals method because they are still expression objects and not
			//strings.

			if (provenExpr.equals(Proof.expr.lastElement())) {

				//System.out.println("I made it here!!!!");

				Proof.expr.pop();

>>>>>>> Added toString() to ExprTreeNode
				// Find the rightmost decimal and remove it and all that follows it
				// i.e. 3.2.1 --> 3.2
				for (int i = myNumber.length()-1; i >= 0; i--) {  // 3.2.3
					if (myNumber.charAt(i) == '.') {
						index = i;
						break;
					}
				}
				myNumber = myNumber.substring(0, index); // 3.2
<<<<<<< HEAD
				
				//System.out.println(myNumber);
				
=======

				//System.out.println(myNumber);

>>>>>>> Added toString() to ExprTreeNode
				// increment the number following the rightmost decimal
				// 3.2 --> 3.3
				// Find the new rightmost decimal
				if(!myNumber.contains(".")){
					int newnum = Integer.parseInt(myNumber) + 1;
					myNumber = newnum + "";
				}else{
<<<<<<< HEAD
				
=======

>>>>>>> Added toString() to ExprTreeNode
					for (int i = myNumber.length()-1; i >= 0; i--) {
						if (myNumber.charAt(i) == '.') {
							index = i;
							break;
						}
					}
					// Convert the string to the right of the rightmost decimal to an integer
					// increment it, convert it back to a string and concatenate to myNumber
					int newRight = Integer.parseInt(myNumber.substring(index, myNumber.length())) + 1; // newRight = "3"
					String temp = myNumber.substring(0, index);
					myNumber = myNumber.substring(0, index + 1) + newRight + ""; // myNumber.substring(0, index + 1) = "3."
					// myNumber = "3.3"
				}
			}
		}
		else {
			// Increment the digit(s) following the last decimal of myNumber if it has one
			if (myNumber.contains(".")) {
				for (int i = myNumber.length()-1; i >= 0; i--) {
						if (myNumber.charAt(i) == '.') {
							index = i;
							break;
						}
					}
					// Convert the string to the right of the rightmost decimal to an integer
					// increment it, convert it back to a string and concatenate to myNumber
					int newRight = Integer.parseInt(myNumber.substring(index+1, myNumber.length())) + 1;
					String temp = myNumber.substring(0, index+1);
					myNumber = temp + newRight + "";
			}
			// If myNumber contains no decimals, simply increment it by 1
			else {
				//System.out.println(myNumber);
				int num = Integer.parseInt(myNumber);
				myNumber = (num + 1) + "";
			}
		}
		return new LineNumber(myNumber);

	}




	/*1	show (~~p=>p)
	2	assume ~~p
	3	show p
	3.1	assume ~p
	3.2	co 2 3.1 p
	4	ic 3 (~~p=>p)*/


	/*
	1 show (p=>((p=>q)=>q))
	2 assume p
	3 show ((p=>q)=>q)
	3.1 assume (p=>q)
	3.2 show q
	3.2.1 mp 2 3.1 q
	3.3 ic 3.2 ((p=>q)=>q)
	4 ic 3 (p=>((p=>q)=>q)) */

	//Show called on expression, save expression, if not proven, when it is true, then jump out of line number back to parent number


}
