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
	
	// Assert than an IllegalLineException is thrown when
	// line does not hold anything but whitespace
	@Test
	public void extendProofWhiteSpaceWithoutReason() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof(" ");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));
	}
	
	/* SHOW-SPECIFIC TEST CASES */

	// Tests that an IllegalLineException is thrown when
	// <expr> is not properly formatted for show
	// show syntax: show <expr>
	@Test
	public void extendProofBadExprTestShowTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("show badexpr");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));
	}
	
	// Test that an IllegalLineException is thrown when
	// show and <expr> have no whitespace between
	@Test
	public void extendProofNoWhiteSpaceShowTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("showexpr");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));
		
	}
	
	// Assert that an IllegalLineException is thrown when
	// line has too many arguments for "show"
	@Test
	public void extendProofTooManyArgsShowTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("show <expr> <expr>");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));
	}
	
	// Assert that an IllegalLineException is thrown when
	// line has too few arguments for "show"
	@Test
	public void extendProofTooFewArgsShowTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("show ");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));
	}
	
	/* ASSUME-SPECIFIC TEST CASES */
	
	// Assert that an IllegalLineException is thrown when
	// line has too many arguments for "assume"
	@Test
	public void extendProofTooManyArgsAssumeTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("assume <expr> <expr>");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));
	}
	
	// Assert that an IllegalLineException is thrown when
	// line has too few arguments for assume
	@Test
	public void extendProofTooFewArgsAssumeTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("assume ");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));
	}
	
	// Test that an IllegalLineException is thrown when
	// assume 
	@Test
	public void extendProofBadExprAssumeTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("assume badexpr");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));
	}
	
	// Test that an IllegalLineException is thrown when
	// assume and <expr> have no whitespace between
	@Test
	public void extendProofNoWhiteSpaceAssumeTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("assumeexpr");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));
		
	}
	
	/* REPEAT-SPECIFIC TEST CASES */
	
	// Test that an IllegalLineException is thrown when
	// Repeat has too few arguments <repeat " "> 
	@Test
	public void extendProofRepeatTooFewArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("repeat ");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));
		
	}	
	
	// Test that an IllegalLineException is thrown when
	// Repeat has too many arguments <repeat linenumber <expr> <expr> <expr>> 
	@Test
	public void extendProofRepeatTooManyArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("repeat linenumber <expr> <expr> <expr>");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));
		
	}

	// Test that an IllegalLineException is thrown when
	// Repeat has illegal argument order <repeat <expr> linenumber <expr>> 
	@Test
	public void extendProofRepeatIncorrectArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("repeat <expr> linenumber <expr>");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));
		
	}

	// Test that an IllegalLineException is thrown when
	// Repeat has missing line number <repeat <expr> <expr> <expr>> 
	@Test
	public void extendProofRepeatMissingLineNumberTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("repeat <expr> <expr> <expr>");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));
		
	}

	/* PRINT-SPECIFIC TEST CASES */

	// Test that an IllegalLineException is thrown when
	// Print has too many args <print expr> 
	@Test
	public void extendProofPrintIncorrectArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("print <expr>");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));
		
	}

	/* MC-SPECIFIC TEST CASES */

	// Test that an IllegalLineException is thrown when
	// MC has too many args <mc linenumber linenumber linenumber proof> 
	@Test
	public void extendProofMPIncorrectArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("mp linenumber linenumber linenumber <expr>");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	// Test that an IllegalLineException is thrown when
	// MC has too few args <mc linenumber proof> 
	@Test
	public void extendProofMPTooFewArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("mp linenumber <expr>");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	// Test that an IllegalLineException is thrown when
	// MC has no args <mc > 
	@Test
	public void extendProofMPnoArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("mp ");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	/* MT-SPECIFIC TEST CASES */

	// Test that an IllegalLineException is thrown when
	// MT has too many args <mt linenumber linenumber linenumber proof> 
	@Test
	public void extendProofMTIncorrectArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("mt linenumber linenumber linenumber <expr>");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	// Test that an IllegalLineException is thrown when
	// MT has too few args <mt linenumber proof> 
	@Test
	public void extendProofMTtooFewArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("mt linenumber <expr>");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	// Test that an IllegalLineException is thrown when
	// MC has no args <mc > 
	@Test
	public void extendProofMTnoArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("mt ");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	/* CO-SPECIFIC TEST CASES */

	// Test that an IllegalLineException is thrown when
	// CO has too many args <mt linenumber linenumber linenumber proof> 
	@Test
	public void extendProofCOIncorrectArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("co linenumber linenumber linenumber <expr>");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	// Test that an IllegalLineException is thrown when
	// CO has too few args <co linenumber proof> 
	@Test
	public void extendProofCOtooFewArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("co linenumber <expr>");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	// Test that an IllegalLineException is thrown when
	// co has no args <co > 
	@Test
	public void extendProofMTnoArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("co ");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	/* ic-SPECIFIC TEST CASES */

	// Test that an IllegalLineException is thrown when
	// ic has too many args <mt linenumber linenumber linenumber proof> 
	@Test
	public void extendProofICIncorrectArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("ic linenumber linenumber <expr>");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	// Test that an IllegalLineException is thrown when
	// ic has too few args <ic proof> 
	@Test
	public void extendProofMTtooFewArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("ic <expr>");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	// Test that an IllegalLineException is thrown when
	// ic has no args <ic > 
	@Test
	public void extendProofMTnoArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("ic ");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	/* BAD THEOREM SPECIFIC TEST CASES */

	// Test that an IllegalLineException is thrown when
	// Bad Theorem name with one open Paren. 
	@Test
	public void extendProofBadTheoremOpenParenArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("theoremName (niceTryAssHole");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	// Test that an IllegalLineException is thrown when
	// Bad Theorem name with two variables 
	@Test
	public void extendProofBadTheoremTwoVariablesArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("theoremName pp");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	// Test that an IllegalLineException is thrown when
	// Bad Theorem name with two negated variables 
	@Test
	public void extendProofBadTheoremNegateTwoVariablesArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("theoremName ~pp");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	// Test that an IllegalLineException is thrown when
	// Bad Theorem name with two negated variables 
	@Test
	public void extendProofBadTheoremNegateTwoVariablesArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("theoremName ~pp");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	// Test that an IllegalLineException is thrown when
	// Bad Theorem name with keyword after
	@Test
	public void extendProofBadTheoremPlusKeywordArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("theoremName show");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	/* BAD CHARACTERS TEST */

	// Test that an IllegalLineException is thrown when
	// Bad Theorem name with Expression then TheoremName
	@Test
	public void extendProofBadCharactersArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("show !!!@@###$$$p");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	// Test that an IllegalLineException is thrown when
	// Bad Theorem name with Expression then TheoremName
	@Test
	public void extendProofBadCharactersAndOperatorsArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("show (=>)");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	/* BAD Parens TEST */

	// Test that an IllegalLineException is thrown when
	// Bad Proof with uneven parens.
	@Test
	public void extendProofBadParensArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("show (((p=>q)");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	// Test that an IllegalLineException is thrown when
	// Bad Proof with illegal operator (>)
	@Test
	public void extendProofIllegalOperatorArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("show (p>((p=>q)))");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}

	// Test that an IllegalLineException is thrown when
	// Reversed Operators
	@Test
	public void extendProofOppositeArgsTest() {
		String err = "";
		
		Proof proof = new Proof(new TheoremSet());
		
		try {
			proof.extendProof("(p=>q) show");
		} catch(IllegalLineException e) {
			err = e.getMessage();
		}
		assertTrue(err.equals("<error message from IllegalLineException"));

	}


}
