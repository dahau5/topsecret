import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;


public class ProofTest {
	//	ArrayList<String> ops = new ArrayList<String>();
	//	ops.add("this");
	//	ops.add("show");
	//	ops.add("repeat");
	//	ops.add("assume");
	//	ops.add("ic");
	//	ops.add("mt");
	//	ops.add("mp");
	//	ops.add("co");
	//	ops.add("print");

	/* GENERAL TEST CASES */

	//Test that a simple proof can be run and throw no errors.
	@Test
	public void CorrectProofCheck() throws IllegalLineException{
		boolean errored = false;
		Proof proof = new Proof(new TheoremSet());
		proof.nextLineNumber();
		
		try{
			proof.extendProof("show (a=>b)");
		}catch(IllegalLineException e){
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		proof.nextLineNumber();
		
		try{
			proof.extendProof("assume a");
		}catch(IllegalLineException e){
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		proof.nextLineNumber();
		
		try{
			proof.extendProof("show b");
		}catch(IllegalLineException e){
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		
		//System.out.println(proof.proofSteps.get(2));
		//System.out.println(proof.myLineNumbers.get(new LineNumber("1")));
		//System.out.println(proof.myLineNumbers.get(new LineNumber("2")));
		
		LineNumber test = new LineNumber("2");
		assertFalse(errored);
		//assertTrue(proof.myLineNumbers.containsKey(test));
		LinkedList<Object> copy = new LinkedList<Object>();
		copy.add("assume");
		copy.add(new Expression("a"));
		assertEquals(proof.myLineNumbers.get("2").get(0), copy.get(0));
		assertEquals(proof.myLineNumbers.get("2").get(1), copy.get(1));
	}
	
	//Test that a correctly formatted proof with a single sub proof throws no errors. 
	@Test
	public void subprooftest(){
		Proof proof1 = new Proof(new TheoremSet());
		boolean errored = false;
		proof1.nextLineNumber();
		try{
			proof1.extendProof("show (~~p=>p)");
		}catch (IllegalLineException e){
			errored = true;
		}catch (IllegalInferenceException a){
			errored = true;
		}
		System.out.println(proof1.proofSteps.get(0));

		proof1.nextLineNumber();
		try{
			proof1.extendProof("assume ~~p");
		}catch (IllegalLineException e){
			errored = true;
		}catch (IllegalInferenceException a){
			errored = true;
		}
		System.out.println(proof1.proofSteps.get(1));

		proof1.nextLineNumber();
		try{
			proof1.extendProof("show p");
		}catch (IllegalLineException e){
			errored = true;
		}catch (IllegalInferenceException a){
			errored = true;
		}
		System.out.println(proof1.proofSteps.get(2));

		proof1.nextLineNumber();
		try{
			proof1.extendProof("assume ~p");
		}catch (IllegalLineException e){
			errored = true;
		}catch (IllegalInferenceException a){
			errored = true;
		}
		System.out.println(proof1.proofSteps.get(3));

		proof1.nextLineNumber();
		try{
			proof1.extendProof("co 2 3.1 p");
		}catch (IllegalLineException e){
			errored = true;
		}catch (IllegalInferenceException a){
			errored = true;
		}
		assertFalse(proof1.isComplete());
		System.out.println(proof1.proofSteps.get(4));

		proof1.nextLineNumber();
		try{
			proof1.extendProof("ic 3 (~~p=>p)");
		}catch (IllegalLineException e){
			errored = true;
		}catch (IllegalInferenceException a){
			errored = true;
		}
		System.out.println(proof1.proofSteps.get(5));
		
		assertFalse(errored);
		assertTrue(proof1.isComplete());
	}
	
	//Test asserting that the a proof with multiple sub proofs throws no errors. 
	@Test
	public void multiple_sub_proof_test(){
		boolean errored  = false;
		Proof proof1 = new Proof(new TheoremSet());
		//Line1
		assertFalse(errored);
		try{
			proof1.extendProof("show ((a=>(b=>c))=>((a=>b)=>(a=>c)))");
		}catch (IllegalLineException e){
			System.out.println(e.getMessage());
			errored = true;
		}catch (IllegalInferenceException a){
			System.out.println(a.getMessage());
			errored = true;
		}
		assertFalse(errored);
		//Line2
		try{
			proof1.extendProof("assume (a=>(b=>c))");
		}catch (IllegalLineException e){
			errored = true;
		}catch (IllegalInferenceException a){
			errored = true;
		}
		assertFalse(errored);
		//Line3
		try{
			proof1.extendProof("show ((a=>b)=>(a=>c))");
		}catch (IllegalLineException e){
			errored = true;
		}catch (IllegalInferenceException a){
			errored = true;
		}
		assertFalse(errored);
		//Line4
		try{
			proof1.extendProof("assume (a=>b)");
		}catch (IllegalLineException e){
			errored = true;
		}catch (IllegalInferenceException a){
			errored = true;
		}
		assertFalse(errored);
		//Line5
		try{
			proof1.extendProof("show (a=>c)");
		}catch (IllegalLineException e){
			errored = true;
		}catch (IllegalInferenceException a){
			errored = true;
		}
		assertFalse(errored);
		//Line6
		try{
			proof1.extendProof("assume a");
		}catch (IllegalLineException e){
			errored = true;
		}catch (IllegalInferenceException a){
			errored = true;
		}
		assertFalse(errored);
		//Line7
		try{
			proof1.extendProof("mp 2 3.2.1 (b=>c)");
		}catch (IllegalLineException e){
			errored = true;
		}catch (IllegalInferenceException a){
			errored = true;
		}
		assertFalse(errored);
		//Line8
		try{
			proof1.extendProof("mp 3.2.1 3.1 b");
		}catch (IllegalLineException e){
			errored = true;
		}catch (IllegalInferenceException a){
			errored = true;
		}
		assertFalse(errored);
		//Line9
		try{
			proof1.extendProof("mp 3.2.3 3.2.2 c");
		}catch (IllegalLineException e){
			errored = true;
		}catch (IllegalInferenceException a){
			errored = true;
		}
		assertFalse(errored);
		try{
			proof1.extendProof("ic 3.2.4 (a=>c)");
		}catch (IllegalLineException e){
			errored = true;
		}catch (IllegalInferenceException a){
			errored = true;
		}
		assertFalse(errored);
		try{
			proof1.extendProof("ic 3.2 ((a=>b)=>(a=>c))");
		}catch (IllegalLineException e){
			errored = true;
		}catch (IllegalInferenceException a){
			errored = true;
		}
		assertFalse(errored);
		try{
			proof1.extendProof("ic 3 ((a=>(b=>c))=>((a=>b)=>(a=>c)))");
		}catch (IllegalLineException e){
			errored = true;
		}catch (IllegalInferenceException a){
			errored = true;
		}
		assertFalse(errored);
	}
	// Assert than an IllegalLineException is thrown when
	// line does not hold anything but whitespace
	@Test
	public void extendProofWhiteSpaceWithoutReason() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());		
		try {
			proof.extendProof(" ");
		} catch(IllegalLineException e) {
			errored = true;
		} catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);
	}

	/* SHOW-SPECIFIC TEST CASES */

	// Tests that an IllegalLineException is thrown when
	// <expr> is not properly formatted for show
	// show syntax: show <expr>
	@Test
	public void extendProofBadExprTestShowTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("show badexpr");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);
	}

	// Test that an IllegalLineException is thrown when
	// show and <expr> have no whitespace between
	@Test
	public void extendProofNoWhiteSpaceShowTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("showexpr");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);

	}

	// Assert that an IllegalLineException is thrown when
	// line has too many arguments for "show"
	@Test
	public void extendProofTooManyArgsShowTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("show (r=>t) (a=>b)");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);
	}

	// Assert that an IllegalLineException is thrown when
	// line has too few arguments for "show"
	@Test
	public void extendProofTooFewArgsShowTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("show ");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);
	}

	/* ASSUME-SPECIFIC TEST CASES */

	// Assert that an IllegalLineException is thrown when
	// line has too many arguments for "assume"
	@Test
	public void extendProofTooManyArgsAssumeTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("assume <expr> <expr>");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);
	}

	// Assert that an IllegalLineException is thrown when
	// line has too few arguments for assume
	@Test
	public void extendProofTooFewArgsAssumeTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("assume ");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);
	}

	// Test that an IllegalLineException is thrown when
	// assume 
	@Test
	public void extendProofBadExprAssumeTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("assume badexpr");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;
	}

	// Test that an IllegalLineException is thrown when
	// assume and <expr> have no whitespace between
	@Test
	public void extendProofNoWhiteSpaceAssumeTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("assumeexpr");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	/* REPEAT-SPECIFIC TEST CASES */

	// Test that an IllegalLineException is thrown when
	// Repeat has too few arguments <repeat " "> 
	@Test
	public void extendProofRepeatTooFewArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("repeat ");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}	

	// Test that an IllegalLineException is thrown when
	// Repeat has too many arguments <repeat linenumber <expr> <expr> <expr>> 
	@Test
	public void extendProofRepeatTooManyArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("repeat linenumber <expr> <expr> <expr>");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	// Test that an IllegalLineException is thrown when
	// Repeat has illegal argument order <repeat <expr> linenumber <expr>> 
	@Test
	public void extendProofRepeatIncorrectArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("repeat <expr> linenumber <expr>");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	// Test that an IllegalLineException is thrown when
	// Repeat has missing line number <repeat <expr> <expr> <expr>> 
	@Test
	public void extendProofRepeatMissingLineNumberTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("repeat <expr> <expr> <expr>");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	/* PRINT-SPECIFIC TEST CASES */

	// Test that an IllegalLineException is thrown when
	// Print has too many args <print expr> 
	@Test
	public void extendProofPrintIncorrectArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("print <expr>");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	/* MC-SPECIFIC TEST CASES */

	// Test that an IllegalLineException is thrown when
	// MC has too many args <mc linenumber linenumber linenumber proof> 
	@Test
	public void extendProofMPIncorrectArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("mp linenumber linenumber linenumber <expr>");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	// Test that an IllegalLineException is thrown when
	// MC has too few args <mc linenumber proof> 
	@Test
	public void extendProofMPTooFewArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("mp linenumber <expr>");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	// Test that an IllegalLineException is thrown when
	// MC has no args <mc > 
	@Test
	public void extendProofMPnoArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("mp ");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	/* MT-SPECIFIC TEST CASES */

	// Test that an IllegalLineException is thrown when
	// MT has too many args <mt linenumber linenumber linenumber proof> 
	@Test
	public void extendProofMTIncorrectArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("mt linenumber linenumber linenumber <expr>");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	// Test that an IllegalLineException is thrown when
	// MT has too few args <mt linenumber proof> 
	@Test
	public void extendProofMTtooFewArgsTest2() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("mt linenumber <expr>");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	// Test that an IllegalLineException is thrown when
	// MC has no args <mc > 
	@Test
	public void extendProofMTnoArgsTest1() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("mt ");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	/* CO-SPECIFIC TEST CASES */

	// Test that an IllegalLineException is thrown when
	// CO has too many args <mt linenumber linenumber linenumber proof> 
	@Test
	public void extendProofCOIncorrectArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("co linenumber linenumber linenumber <expr>");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	// Test that an IllegalLineException is thrown when
	// CO has too few args <co linenumber proof> 
	@Test
	public void extendProofCOtooFewArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("co linenumber <expr>");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	// Test that an IllegalLineException is thrown when
	// co has no args <co > 
	@Test
	public void extendProofMTnoArgsTest5() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("co ");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	/* ic-SPECIFIC TEST CASES */

	// Test that an IllegalLineException is thrown when
	// ic has too many args <mt linenumber linenumber linenumber proof> 
	@Test
	public void extendProofICIncorrectArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("ic linenumber linenumber <expr>");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	// Test that an IllegalLineException is thrown when
	// ic has too few args <ic proof> 
	@Test
	public void extendProofMTtooFewArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("ic <expr>");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	// Test that an IllegalLineException is thrown when
	// ic has no args <ic > 
	@Test
	public void extendProofMTnoArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("ic ");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	/* BAD THEOREM SPECIFIC TEST CASES */

	// Test that an IllegalLineException is thrown when
	// Bad Theorem name with one open Paren. 
	@Test
	public void extendProofBadTheoremOpenParenArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("theoremName (niceTryAssHole");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	// Test that an IllegalLineException is thrown when
	// Bad Theorem name with two variables 
	@Test
	public void extendProofBadTheoremTwoVariablesArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("theoremName pp");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	// Test that an IllegalLineException is thrown when
	// Bad Theorem name with two negated variables 
	@Test
	public void extendProofBadTheoremNegateTwoVariablesArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("theoremName ~pp");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	// Test that an IllegalLineException is thrown when
	// Bad Theorem name with two negated variables 
	@Test
	public void extendProofBadTheoremNegateTwoVariablesArgsTest5() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("theoremName ~pp");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	// Test that an IllegalLineException is thrown when
	// Bad Theorem name with keyword after
	@Test
	public void extendProofBadTheoremPlusKeywordArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("theoremName show");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	/* BAD CHARACTERS TEST */

	// Test that an IllegalLineException is thrown when
	// Bad Theorem name with Expression then TheoremName
	@Test
	public void extendProofBadCharactersArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("show !!!@@###$$$p");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	// Test that an IllegalLineException is thrown when
	// Bad Theorem name with Expression then TheoremName
	@Test
	public void extendProofBadCharactersAndOperatorsArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("show (=>)");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	/* BAD Parens TEST */

	// Test that an IllegalLineException is thrown when
	// Bad Proof with uneven parens.
	@Test
	public void extendProofBadParensArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("show (((p=>q)");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	// Test that an IllegalLineException is thrown when
	// Bad Proof with illegal operator (>)
	@Test
	public void extendProofIllegalOperatorArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("show (p>((p=>q)))");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

	// Test that an IllegalLineException is thrown when
	// Reversed Operators
	@Test
	public void extendProofOppositeArgsTest() {
		boolean errored = false;

		Proof proof = new Proof(new TheoremSet());

		try {
			proof.extendProof("(p=>q) show");
		} catch(IllegalLineException e) {
			errored = true;
		}catch(IllegalInferenceException a){
			errored = true;
		}
		assertTrue(errored);;

	}

}
