import static org.junit.Assert.*;

import org.junit.Test;


public class LogicTest2 {

  @Test
	public void testAssume() {
		boolean errored, errored2;
		Expression exp1, exp2, exp3, exp4, exp5, exp6;
		Proof proof = new Proof(new TheoremSet());
		
		errored = errored2 = false;
		exp1 = exp2 = exp3 = exp4 = exp5 = exp6 = null;
	
		proof.nextLineNumber();
		
		try{
			proof.extendProof("show (((a&n)|(~b|y))=>(a|b))");
		}catch (IllegalLineException e){
			errored = true;
			System.out.println(e.getMessage());
		}catch (IllegalInferenceException a){
			errored = true;
			System.out.println(a.getMessage());
		}
		try{
			exp1 = new Expression("~(((a&n)|(~b|y))=>(a|b))"); //good
			exp2 = new Expression("((a&n)|(~b|y))"); //good
			exp3 = new Expression("~((a&n)|(~b|y))"); //bad
			exp4 = new Expression("(((a&n)|(~b|y))=>(a|b))"); //bad
		}catch (IllegalLineException e){
			errored = true;
			System.out.println(e.getMessage());
		}
		try{
			Logic.assume(exp1, "1");
		}catch (IllegalInferenceException a){
			errored = true;
			System.out.println(a.getMessage());
		}
		
		assertFalse(errored);
		
		try{
			Logic.assume(exp2, "1");
		}catch (IllegalInferenceException a){
			errored = true;
			System.out.println(a.getMessage());
		}
		
		assertFalse(errored);
		
		try{
			Logic.assume(exp3, "1");
		}catch (IllegalInferenceException a){
			errored = true;
			System.out.println(a.getMessage());
		}
		
		assertTrue(errored);
		
		try{
			Logic.assume(exp4, "1");
		}catch (IllegalInferenceException a){
			errored = true;
			System.out.println(a.getMessage());
		}
		
		assertTrue(errored);
	}

	
	//Tests the different applications of moduspolens to ensure it is used correctly
	@Test
	public void testmoduspolens(){
		//mp : given E and (E1=>E2) can infer E2
		
		//Test for two different cases that should work correctly. Heavy use of | and ~ to try and break it.
		boolean errored, errored2;
		Expression exp1, exp2, exp3, exp4, exp5, exp6;
		
		errored = errored2 = false;
		exp1 = exp2 = exp3 = exp4 = exp5 = exp6 = null;
		
		try{
			exp1 = new Expression("(a|b)");
			exp2 = new Expression("((a|b)=>((b|c)=>p))");
			exp3 = new Expression("((b|c)=>p)");
			exp4 = new Expression("~a");
			exp5 = new Expression("~(~a=>~b)");
			exp6 = new Expression("~b");
		
		}catch (IllegalLineException e){
			//Hopefully this doesn't happen.	
		}
		try{
			Logic.moduspollens(exp1, exp2, exp3);
		}catch (IllegalInferenceException a){
			errored = true;
		}
		assertFalse(errored);
		
		try{
			Logic.moduspollens(exp4, exp5, exp6);
		}catch (IllegalInferenceException e){
			errored2 = true;
			System.out.println(e.getMessage());
		}
		assertTrue(errored2);
		
		try{
			exp1 = new Expression("(a|b)");
			exp2 = new Expression("((a|b)=>((b|a)=>p))"); //Incorrect use of theorem. b|c does not match anything in this.
			exp3 = new Expression("((b|c)=>p)");
		}catch (IllegalLineException e){
			//Hopefully this doesn't happen.
		}
		try{
			Logic.moduspollens(exp1, exp2, exp3);
		}catch (IllegalInferenceException e){
			System.out.println(e.getMessage());
			errored = true;
		}
		assertTrue(errored);
	}
	
	
	@Test
	public void testRepeat(){
		boolean errored, errored2;
		Expression exp1, exp2, exp3, exp4, exp5, exp6;
		Proof proof = new Proof(new TheoremSet());
		
		errored = errored2 = false;
		exp1 = exp2 = exp3 = exp4 = exp5 = exp6 = null;
		
		try{
			exp1 = new Expression("((a&n)|(~b|y))");
			exp2 = new Expression("(a=>v)");
		}catch (IllegalLineException a){
			errored = true;
			System.out.println(a.getMessage());
		}
	
		proof.nextLineNumber();
		
		try{
			proof.extendProof("show (((a&n)|(~b|y))=>(a|b))");
		}catch (IllegalLineException e){
			errored = true;
			System.out.println(e.getMessage());
		}catch (IllegalInferenceException a){
			errored = true;
			System.out.println(a.getMessage());
		}
		proof.nextLineNumber();
		
		try{
			proof.extendProof("assume ((a&n)|(~b|y))");
		}catch (IllegalLineException e){
			errored = true;
			System.out.println(e.getMessage());
		}catch (IllegalInferenceException a){
			errored = true;
			System.out.println(a.getMessage());
		}
		
		try{
			Logic.repeat(exp2, exp2);
		}catch (IllegalInferenceException a){
			errored = true;
			System.out.println(a.getMessage());
		}
		assertFalse(errored);
		
		try{
			Logic.repeat(exp1, exp2);
		}catch (IllegalInferenceException a){
			errored = true;
			System.out.println(a.getMessage());
		}
		assertTrue(errored);
		
		try{
			Logic.repeat(exp2, exp1);
		}catch (IllegalInferenceException a){
			errored = true;
			System.out.println(a.getMessage());
		}
		assertTrue(errored);
	}
		
	
	
	//Tests different edge cases of modus tollens
	@Test
	public void modustollenstest(){
		//have ~b and (a=>b); implies ~a
		
		boolean errored, errored2;
		Expression exp1, exp2, exp3, exp4, exp5, exp6;
		
		errored = errored2 = false;
		exp1 = exp2 = exp3 = exp4 = exp5 = exp6 = null;
		
		try{
			exp1 = new Expression("~(p=>q)");
			exp2 = new Expression("((p=>q)=>q)");
			exp3 = new Expression("~q");
			exp4 = new Expression("~~q");
			exp5 = new Expression("((p=>q)=>q)");
			exp6 = new Expression("~(p=>q)");
		
		}catch (IllegalLineException e){
			System.out.println(e.getMessage());	
		}
		try{
			Logic.modustollens(exp3, exp2, exp1);
		}catch (IllegalInferenceException a){
			System.out.println(a.getMessage());
			errored = true;
		}
		assertFalse(errored);
		
		try{
			Logic.modustollens(exp4, exp5, exp6);
		}catch (IllegalInferenceException e){
			errored2 = true;
			System.out.println(e.getMessage());
		}
		assertTrue(errored2);
		
		//Round 2 of tests.
		try{
			exp1 = new Expression("~~~(p=>q)");
			exp2 = new Expression("~~((p=>q)=>q)");
			exp3 = new Expression("~~~q");
			exp4 = new Expression("~q");
			exp5 = new Expression("(~(p=>q)=>q)");
			exp6 = new Expression("~(p=>q)");
		
		}catch (IllegalLineException e){
			System.out.println(e.getMessage());	
		}
		try{
			Logic.modustollens(exp3, exp2, exp1);
		}catch (IllegalInferenceException a){
			System.out.println(a.getMessage());
			errored = true;
		}
		assertTrue(errored);
		
		try{
			Logic.modustollens(exp4, exp5, exp6);
		}catch (IllegalInferenceException e){
			errored2 = true;
			System.out.println(e.getMessage());
		}
		assertTrue(errored2);
		
		
		
	}
	
	
	//Tests different edge cases of ic
	@Test
	public void constructiontest(){
		boolean errored, errored2;
		Expression exp1, exp2, exp3, exp4, exp5, exp6;
		
		errored = errored2 = false;
		exp1 = exp2 = exp3 = exp4 = exp5 = exp6 = null;
		
		try{
			exp1 = new Expression("(a=>b)");
			exp2 = new Expression("(((b=>c)&(d=>t))=>(a=>b))");
			exp3 = new Expression("~(a=>b)");
			exp4 = new Expression("(((b|c)=>a)=>~(a=>b))");
			exp5 = new Expression("b");
			exp6 = new Expression("(b=>c)");
		
		}catch (IllegalLineException e){
			System.out.println(e.getMessage());	
		}
		
		try{
			Logic.construction(exp1, exp2);
		}catch (IllegalInferenceException e){
			errored = true;
			System.out.println(e.getMessage());
		}
		assertFalse(errored);
		
		try{
			Logic.construction(exp3, exp4);
		}catch (IllegalInferenceException e){
			errored = true;
			System.out.println(e.getMessage());
		}
		assertFalse(errored);
		
		try{
			Logic.construction(exp5, exp6);
		}catch (IllegalInferenceException e){
			errored = true;
			System.out.println(e.getMessage());
		}
		assertTrue(errored);
	}
	
	
	//Tests different edge cases of co
	@Test
	public void contradiction(){
		boolean errored, errored2;
		Expression exp1, exp2, exp3, exp4, exp5, exp6;
		
		errored = errored2 = false;
		exp1 = exp2 = exp3 = exp4 = exp5 = exp6 = null;
		
		try{
			exp1 = new Expression("(a=>b)");
			exp2 = new Expression("(((b=>c)&(d=>t))=>(d=>r))");
			exp3 = new Expression("~(a=>b)");
			exp4 = new Expression("a");
			exp5 = new Expression("b");
			exp6 = new Expression("~b");
		
		}catch (IllegalLineException e){
			System.out.println(e.getMessage());	
		}
		try{
			Logic.contradiction(exp1, exp3, exp2);
		}catch (IllegalInferenceException a){
			System.out.println(a.getMessage());
			errored = true;
		}
		assertFalse(errored);
		
		try{
			Logic.contradiction(exp4, exp5, exp6);
		}catch (IllegalInferenceException a){
			System.out.println(a.getMessage());
			errored = true;
		}
		assertTrue(errored);
	}
}
