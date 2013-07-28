import java.util.*;


public class Proof {
	
	public static ArrayList<String> lastLineNumber = new ArrayList<String>();
	TheoremSet mytheorems;
	LineNumber valueholder = new LineNumber("1");
	public ArrayList<String> proofSteps = new ArrayList<String>();
	public static Stack<Expression> expr;

	public static HashMap<String, LinkedList<Object>> myLineNumbers;

	public Proof (TheoremSet theorems) {
		mytheorems = theorems;
		expr = new Stack<Expression>();
		myLineNumbers = new HashMap<String, LinkedList<Object>>();
	}
	
	

	public LineNumber nextLineNumber ( ) {
		if(myLineNumbers.isEmpty()){
			return valueholder;
		}else{
			lastLineNumber.add(valueholder.getNum());
			String operation = (String)myLineNumbers.get(lastLineNumber.get(lastLineNumber.size()-1)).getFirst();
			//System.out.println(operation);
			Expression pastExpr = (Expression)myLineNumbers.get(lastLineNumber.get(lastLineNumber.size()-1)).getLast();
			valueholder = valueholder.nextlinenumber_helper(operation, pastExpr, lastLineNumber.get(lastLineNumber.size()-1));
			return valueholder;
		}
	}

	public void extendProof (String x) throws IllegalLineException, IllegalInferenceException {
		//add the parsed expression to the hashmap at the key of "lastLineNumber"
		LinkedList<Object> templist = new LinkedList<Object>();
		try{
			 templist = inputstringparser(x);
		}catch (IllegalLineException e){
			System.out.println(e.getMessage());
		}catch (IllegalInferenceException a){
			System.out.println(a.getMessage());
		}
		
		try{
			MethodCaller(templist);
		}catch (IllegalLineException e){
			System.out.println(e.getMessage());
		}catch (IllegalInferenceException a){
			System.out.println(a.getMessage());
		}
		proofSteps.add(valueholder.getNum() + "    " + x);
		Proof.myLineNumbers.put(valueholder.getNum(), templist);
		
		
		//if (myLineNumbers.containsKey("1")){
			//System.out.println("There is a 1 key in the hashmap");
			
			//if (myLineNumbers.containsKey("2")){
				
				//System.out.println("There is a 2 key in the hashmap");
				//if(myLineNumbers.containsKey("3")){
					//System.out.println("There is a 3 key in the hashmap");
				//}
				//System.out.println(myLineNumbers.get("1").equals(myLineNumbers.get("2")));
			//}
		//}
	}

	public String toString ( ) {
		String str = "";
		Iterator<String> iter = proofSteps.iterator();
		while (iter.hasNext()){
			str += iter.next().toString();
			str += "\n";
		}
		return str;
	}

	public boolean isComplete ( ) {
		if (valueholder.getfirstshow().equals((Expression) myLineNumbers.get(lastLineNumber).getLast())) {
			return true;
		}
		return false;
	}
	
	public LinkedList<Object> inputstringparser(String x) throws IllegalLineException, IllegalInferenceException{
		
		LinkedList<Object> myLinkedList = new LinkedList<Object>();
		
		//Might not need this. Attempt to prevent person from inputting an empty line.
		
		String[] myValues = x.split(" "); //Splits the input string based on white space.
		
		//Test if the user entered too many inputs for a single line.
		if (myValues.length > 4){
			throw new IllegalLineException("Too many arguments inputted. Max possible is 4.");
		}
		if (myValues.length < 1){
			throw new IllegalLineException("Must input something.");
		}
		
		
		for (int i = 0; i < myValues.length; i++){ //Adds each element of the split up input string 
			myLinkedList.add(myValues[i]);         //into a LinkedList in sequential order. 
		}										   //ex. "assume (a=>b)" is put in as "assume", "(a=>b)"
		
		//System.out.println(myLinkedList.size());
		
		String test = (String)myLinkedList.getFirst(); //The string representation of the operator.
		
		//System.out.println(test);
		
		//Test checking if the first argument of the input line is anything other than an approved operator.
		//If it is not approved, then it throws an illegal line operator. 
		if (!test.equals("show") && !test.equals("assume") && !test.equals("repeat") && !test.equals("print")  
				&& !test.equals("mp") && !test.equals("ic")  && !test.equals("mt") && !test.equals("co") && !mytheorems.myThms.containsKey(test)){
			throw new IllegalLineException("Invalid operator.");
		}
		
		//Test if wrong number of arguments are entered for show, assume, or a theorem.
		if ((test == "show" || test == "assume" || mytheorems.myThms.containsKey(test)) && myLinkedList.size() != 2){
			throw new IllegalLineException("Wrong number of arguments supplied for " + test + "statement. 2 needed.");
		}
		
		//Test if wrong number of arguments are entered for repeat, mp, mt, or co.
		if ((test == "repeat" || test == "mp" || test == "mt" || test == "co") && myLinkedList.size() != 4){
			throw new IllegalLineException("Wrong number of arguments for " + test + "statement. 4 needed.");
		}
		
		//Test if wrong number of arguments are entered for print.
		if (test == "print" && myLinkedList.size() != 1){
			throw new IllegalLineException("Wrong number of arguments for " + test + "statement. 1 needed.");
		}
		
		//Test if wrong number of arguments entered for ic
		if (test == "ic" && myLinkedList.size() != 3){
			throw new IllegalLineException("Wrong number of arguments for " + test + "statement. 3 needed.");
		}
		
		//The string representation of the expression in the Linked List.
		String Expr = (String)myLinkedList.getLast(); 
		
		Expression newExpr;
		
		//Attempts to create an expression object from the last element of the input. If error occurs,
		//throws it right along until it is dealt with in extend proof.
		try{	
			 newExpr = new Expression(Expr);
		}catch(IllegalLineException e){
			throw e;
		}
		
		//Removing the string object from the end of the LinkedList(which is holding the input string)
		//and then adding the correct expression object to the end of the linked list. 
		int removal_index = myLinkedList.size()-1;
		myLinkedList.remove(removal_index);
		myLinkedList.addLast(newExpr);
		//System.out.println(myLinkedList.size());
		return myLinkedList; //returning the correctly syntaxed LinkedList to then have methods called upon it.
		}
		
	public void MethodCaller(LinkedList fun) throws IllegalLineException, IllegalInferenceException{
		Expression exp1;
		Expression exp2;
		Expression test;
		String linetest1 = new String("");
		String linetest2 = new String("");
		
		if(fun.size() == 3){
			linetest1 = (String)fun.get(1);
		}
		if(fun.size() == 4){
			linetest1 = (String)fun.get(1);
			linetest2 = (String)fun.get(2);
		}
		
		String op = (String)fun.getFirst();
		
		if (op == "show"){
			
			if (fun.size() != 2){
				throw new IllegalLineException("Wrong number of arguments.");
			}

		
		}else if(op == "assume"){
			
			if(fun.size() != 2){
				throw new IllegalLineException("Wrong number of arguments.");
			}
			
			try{
				Logic.assume((Expression)fun.getLast(), lastLineNumber.get(lastLineNumber.size()-1));
			}catch (IllegalInferenceException a){
				throw a;
			}
		
		}else if(op == "repeat"){
			
			if(fun.size() != 3){
				throw new IllegalLineException("Wrong number of arguments.");
			}
			try{
				linechecker(linetest1);
			}catch (IllegalInferenceException a){
				throw a;
			}catch (IllegalLineException e){
				throw e;
			}
			
			//stub Need to call a repeat method here. 
			
		}else if(op == "print"){
			
			if(fun.size() != 1){
				throw new IllegalLineException("Wrong number of arguments.");
			}
			
			this.toString();
		
		}else if(op == "mp" || op == "mt" || op == "co"){
			
			if(fun.size() > 4){
				throw new IllegalLineException("Too many arguments.");
			}
			
			if(fun.size() < 4){
				throw new IllegalLineException("Too few arguments.");
			}
			
			try{
				linechecker(linetest1);
			}catch (IllegalInferenceException a){
				throw a;
			}catch (IllegalLineException e){
				throw e;
			}
			
			try{
				linechecker(linetest2);
			}catch (IllegalInferenceException a){
				throw a;
			}catch (IllegalLineException e){
				throw e;
			}
		
			exp1 = (Expression)myLineNumbers.get(linetest1).getLast();
			exp2 = (Expression)myLineNumbers.get(linetest2).getLast();	
			test = (Expression)fun.getLast();
			
			//Calling the particular logic functions based on which "op" is entered.
			if(op == "mp"){
				try{
					Logic.moduspollens(exp1, exp2, test);
				}catch(IllegalInferenceException a){
					throw a;
				}
			}
			if(op == "mt"){
				try{
					Logic.modustollens(exp1, exp2, test);
				}catch (IllegalInferenceException a){
					throw a;
				}
			}
			if(op == "co"){
				try{
					Logic.contradiction(exp1, exp2, test);
				} catch (IllegalInferenceException a){
					throw a;
				}
			}
			
			
		}else if(op == "ic"){
			
			if (fun.size() != 3){
				throw new IllegalLineException("Wrong number of arguments.");
			}
			
			try{
				linechecker(linetest1);
			}catch (IllegalInferenceException a){
				throw a;
			}catch (IllegalLineException e){
				throw e;
			}
			
			exp1 = (Expression)myLineNumbers.get(linetest1).getLast();
			
			try{
				Logic.construction(exp1, (Expression)fun.getLast());
			}catch (IllegalInferenceException a){
				throw a;
			}
			
		}
	
	}
	//Method that checks if the referenced line number is valid.
	

	public void linechecker(String test) throws IllegalLineException, IllegalInferenceException{
		String[] testline_values;
		String[] my_line_values;
		testline_values = test.split(".");
		my_line_values = valueholder.getNum().split(".");
		
		//
		if(!myLineNumbers.containsKey(test)){
			throw new IllegalLineException("Bad line reference");
		}
		if (testline_values.length > my_line_values.length){
			throw new IllegalInferenceException("Bad line reference. " + test + "references an inner proof.");
	    }
		if(testline_values.length <= my_line_values.length){
			for (int i = 0; i < testline_values.length ; i++){
				if((i+1) == testline_values.length){
					if(Integer.parseInt(testline_values[i]) >= Integer.parseInt(my_line_values[i])){
						throw new IllegalInferenceException("Bad line reference. " + test);
					}
				}
				if (Integer.parseInt(testline_values[i]) != Integer.parseInt(my_line_values[i])){
					throw new IllegalInferenceException("Bad line reference. " + test);
				}
			}
		}
		
	}
}

