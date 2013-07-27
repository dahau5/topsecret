import java.util.*;


public class Proof {
	
	public static LineNumber lastLineNumber;
	private ArrayList<String> proofSteps;
	LinkedList<Object> myLinkedList;
	TheoremSet mytheorems;
	LineNumber valueholder = new LineNumber("1");
	
	public static HashMap<LineNumber, LinkedList> myLineNumbers = new HashMap<LineNumber, LinkedList>();

	public Proof (TheoremSet theorems) {
		mytheorems = theorems;
		proofSteps = new ArrayList<String>();
	}

	public LineNumber nextLineNumber ( ) {
		lastLineNumber = valueholder;
		LineNumber valueholder = LineNumber.nextlinenumber_helper((String)myLineNumbers.get(lastLineNumber).getFirst(), (Expression)myLineNumbers.get(lastLineNumber).getLast());
		return valueholder;
	}

	public void extendProof (String x) throws IllegalLineException, IllegalInferenceException {
		//add the parsed expression to the hashmap at the key of "lastLineNumber"
		LinkedList<Object> templist = new LinkedList<Object>();
		try{
			 templist = inputstringparser(x);
		}catch (IllegalLineException e){
			System.err.println(e.getMessage());
		}catch (IllegalInferenceException a){
			System.err.println(a.getMessage());
		}
		
		try{
			MethodCaller(templist);
		}catch (IllegalLineException e){
			System.err.println(e.getMessage());
		}catch (IllegalInferenceException a){
			System.err.println(a.getMessage());
		}
		proofStep.add(x);

		myLineNumbers.put(lastLineNumber, myLinkedList);
	}

	public String toString ( ) {
		String str = "";
		Iterator<String> iter = proofStep.iterator();
		while (iter.hasNext()){
			str += iter.next().toString();
			str += "\n";
		}
		return ret;
	}

	public boolean isComplete ( ) {
		if (LineNumber.firstShow == (Expression) myLineNumbers.get(lastLineNumber).getLast()) {
			return true;
		}
		return false;
	}
	
	public LinkedList<Object> inputstringparser(String x) throws IllegalLineException, IllegalInferenceException{
		
		//Might not need this. Attempt to prevent person from inputting an empty line.
		if(x == null){
			throw new IllegalLineException("You must input something.");
		}
		
		String[] myValues = x.split("\\s"); //Splits the input string based on white space.
		
		//Test if the user entered too many inputs for a single line.
		if (myValues.length > 4){
			throw new IllegalLineException("Too many arguments inputted. Max possible is 4.");
		}
		
		for (int i = 0; i < myValues.length; i++){ //Adds each element of the split up input string 
			myLinkedList.add(myValues[i]);         //into a LinkedList in sequential order. 
		}										   //ex. "assume (a=>b)" is put in as "assume", "(a=>b)"
		
		String test = (String)myLinkedList.getFirst(); //The string representation of the operator.
		
		//Test checking if the first argument of the input line is anything other than an approved operator.
		//If it is not approved, then it throws an illegal line operator. 
		if (test != "show" || test != "assume" || test != "repeat" || test != "print"  
				|| test != "mp" || test != "ic" || test != "mt" || test != "co" || !mytheorems.myThms.containsKey(test)){
			throw new IllegalLineException("Invalid operator.");
		}
		
		//Test if wrong number of arguments are entered for show, assume, or a theorem.
		if ((test == "show" || test == "assume" || mytheorems.myThms.containsKey(test)) && myLinkedList.size() != 2){
			throw new IllegalLineException("Wrong number of arguments supplied for " + test + "statement. 2 needed.");
		}
		
		//Test if wrong number of arguments are entered for repeat, mc, mt, or co.
		if ((test == "repeat" || test == "mc" || test == "mt" || test == "co") && myLinkedList.size() != 4){
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
		return myLinkedList; //returning the correctly syntaxed LinkedList to then have methods called upon it.
		}
		
	public void MethodCaller(LinkedList fun) throws IllegalLineException, IllegalInferenceException{
		Expression exp1;
		Expression exp2;
		Expression test;
		String[] testline_values1;
		String[] testline_values2;
		String[] my_line_values;
		LineNumber linetest1 = new LineNumber("");
		LineNumber linetest2 = new LineNumber("");
		
		if(fun.size() == 3){
			linetest1 = new LineNumber((String)fun.get(1));
		}
		if(fun.size() == 4){
			linetest1 = new LineNumber((String)fun.get(1));
			linetest2 = new LineNumber((String)fun.get(2));
		}
		
		String op = (String)fun.getFirst();
		
		if (op == "show"){
			
			if (fun.size() != 2){
				throw new IllegalLineException("Wrong number of arguments.");
			}
			
			try{
				Logic.show((Expression)fun.get(1), lastLineNumber);
			}catch (IllegalInferenceException a){
				throw a;
			}
		
		}else if(op == "assume"){
			
			if(fun.size() != 2){
				throw new IllegalLineException("Wrong number of arguments.");
			}
			
			try{
				Logic.assume((Expression)fun.get(1), lastLineNumber);
			}catch (IllegalInferenceException a){
				throw a;
			}
		
		}else if(op == "repeat"){
			
			if(fun.size() != 3){
				throw new IllegalLineException("Wrong number of arguments.");
			}
			if(!myLineNumbers.containsKey(linetest1) || !myLineNumbers.containsKey(linetest2)){
				throw new IllegalInferenceException("Bad line reference. Not a valid linenumber.");
			}
			testline_values1 = linetest1.getNum().split(".");
			my_line_values = valueholder.getNum().split(".");
			
			if(testline_values1.length <= my_line_values.length){
				for (int i = 0; i < testline_values1.length ; i++){
					if((i+1) == testline_values1.length){
						if(Integer.parseInt(testline_values1[i]) >= Integer.parseInt(my_line_values[i])){
							throw new IllegalInferenceException("Bad line reference. " + linetest1.getNum());
						}
					}
					if (Integer.parseInt(testline_values1[i]) != Integer.parseInt(my_line_values[i])){
						throw new IllegalInferenceException("Bad line reference. " + linetest1.getNum());
					}
				}
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
			
			//Testing what "op" is entered and then testing the line numbers if they correctly reference a line number
			//in our hashmap.
			if(!myLineNumbers.containsKey(linetest1) || !myLineNumbers.containsKey(linetest2)){
				throw new IllegalInferenceException("Bad line reference. Not a valid linenumber.");
			}
			
			//Splits each line number object based on the placement of the "." and then returns the string in an array form.
			testline_values1 = linetest1.getNum().split(".");
			testline_values2 = linetest2.getNum().split(".");
			my_line_values = valueholder.getNum().split(".");
			
			//Tests determining if the linenumber reference itself is a valid reference. Aka, its in the hashmap of our values
			//but attempts to access a proof it shouldn't be able to access. 
			
			
			//Tests the two entered linenumbers if they have an extra decimal position compared to the current line. Illegal to reference a 
			// subproof that is already been completed.
			if (testline_values1.length > my_line_values.length){
				throw new IllegalInferenceException("Bad line reference. " + linetest1.getNum() + "references an inner proof.");
			}
			if(testline_values2.length > my_line_values.length){
				throw new IllegalInferenceException("Bad line reference. " + linetest2.getNum() + "references an inner proof.");
			}
			
			//If the input line has less decimals than the current line but does not = 1, than the first digits must equal each other. But 
			// otherwise they must be equal to or less than the value of the current line number at that position.
			if(testline_values1.length <= my_line_values.length){
				for (int i = 0; i < testline_values1.length ; i++){
					if((i+1) == testline_values1.length){
						if(Integer.parseInt(testline_values1[i]) >= Integer.parseInt(my_line_values[i])){
							throw new IllegalInferenceException("Bad line reference. " + linetest1.getNum());
						}
					}
					if (Integer.parseInt(testline_values1[i]) != Integer.parseInt(my_line_values[i])){
						throw new IllegalInferenceException("Bad line reference. " + linetest1.getNum());
					}
				}
			}
			if(testline_values2.length <= my_line_values.length){
				for (int i = 0; i < testline_values2.length ; i++){
					if((i+1) == testline_values2.length){
						if(Integer.parseInt(testline_values2[i]) >= Integer.parseInt(my_line_values[i])){
							throw new IllegalInferenceException("Bad line reference. " + linetest2.getNum());
						}
					}
					if (Integer.parseInt(testline_values2[i]) != Integer.parseInt(my_line_values[i])){
						throw new IllegalInferenceException("Bad line reference. " + linetest2.getNum());
					}
				}
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
			
			if(!myLineNumbers.containsKey(linetest1)){
				throw new IllegalLineException("Bad line reference");
			}
			
			testline_values1 = linetest1.getNum().split(".");
			my_line_values = valueholder.getNum().split(".");
			
			if (testline_values1.length > my_line_values.length){
				throw new IllegalInferenceException("Bad line reference. " + linetest1.getNum() + "references an inner proof.");
			}
			if(testline_values1.length <= my_line_values.length){
				for (int i = 0; i < testline_values1.length ; i++){
					if((i+1) == testline_values1.length){
						if(Integer.parseInt(testline_values1[i]) >= Integer.parseInt(my_line_values[i])){
							throw new IllegalInferenceException("Bad line reference. " + linetest1.getNum());
						}
					}
					if (Integer.parseInt(testline_values1[i]) != Integer.parseInt(my_line_values[i])){
						throw new IllegalInferenceException("Bad line reference. " + linetest1.getNum());
					}
				}
			}
			exp1 = (Expression)myLineNumbers.get(linetest1).getLast();
			
			try{
				Logic.construction(exp1, (Expression)fun.getLast());
			}catch (IllegalInferenceException a){
				throw a;
			}
			
		}
	
	}
}
