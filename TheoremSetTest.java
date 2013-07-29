import static org.junit.Assert.*;

import org.junit.Test;


public class TheoremSetTest {

	// thm: a
	// app: b
	// app is a valid application of thm, no exception
	// should be thrown
	
	boolean DEBUGGING = false;
	
	@Test
	public void validSimpleAppTest() {
		String thm =  "a";
		String app = "b";
		TheoremSet thmSet = new TheoremSet();
		boolean erred = false;
		try {
			thmSet.put("thm", new Expression(thm)); // myThms: "thm" : Expression("a")
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
		}
		try {
			thmSet.checkTheoremApplication("thm", new Expression(app));
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
			erred = true;
		}
		assertFalse(erred);
	}
	
	// thm: (p=>q)
	// app: (a=>b)
	// app is a valid application of thm, no exception
	// should be thrown
	@Test
	public void validAppTest() {
		TheoremSet thmSet = new TheoremSet();
		boolean erred = false;
		try {
			thmSet.put("thm", new Expression("(p=>q)"));
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
		}
		try {
			thmSet.checkTheoremApplication("thm", new Expression("(a=>b)"));
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
			erred = true;
		}
		assertFalse(erred);
	}
	
	// app is a valid application of thm, no exception
	// should be thrown
//	@Test
	public void validAppTest2() {
		String thm =  "((f=>g)=>(h=>i))";
		String app = "((a=>b)=>(c=>d))";
		TheoremSet thmSet = new TheoremSet();
		boolean erred = false;
		try {
			thmSet.put("thm", new Expression(thm));
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
		}
		try {
			thmSet.checkTheoremApplication("thm", new Expression(app));
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
			erred = true;
		}
		assertFalse(erred);
	}
	
	@Test
	public void validComplexTest() {
		String thm =  "a";
		String app = "(b=>c)";
		TheoremSet thmSet = new TheoremSet();
		boolean erred = false;
		try {
			thmSet.put("thm", new Expression(thm));
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
		}
		try {
			thmSet.checkTheoremApplication("thm", new Expression(app));
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
			erred = true;
		}
		assertFalse(erred);
	}
	
	@Test
	public void validComplexTest2() {
		String thm =  "(p=>(p=>q))";
		String app = "((a=>b)=>((a=>b)=>c))";
		TheoremSet thmSet = new TheoremSet();
		boolean erred = false;
		try {
			thmSet.put("thm", new Expression(thm));
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
		}
		try {
			thmSet.checkTheoremApplication("thm", new Expression(app));
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
			erred = true;
		}
		assertFalse(erred);
	}
	
	@Test
	public void validComplexTest3() {
		String thm =  "~(p=>(p=>(p=>q)))";
		String app = "~((a=>b)=>((a=>b)=>((a=>b)=>c)))";
		TheoremSet thmSet = new TheoremSet();
		boolean erred = false;
		try {
			thmSet.put("thm", new Expression(thm));
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
		}
		try {
			thmSet.checkTheoremApplication("thm", new Expression(app));
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
			erred = true;
		}
		assertFalse(erred);
	}
	
	@Test
	public void validComplexTest4() {
		String thm =  "~(p=>(p=>(p=>q)))";
		String app = "~((a=>b)=>((a=>b)=>((a=>b)=>(b=>c))))";
		TheoremSet thmSet = new TheoremSet();
		boolean erred = false;
		try {
			thmSet.put("thm", new Expression(thm));
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
		}
		try {
			thmSet.checkTheoremApplication("thm", new Expression(app));
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
			erred = true;
		}
		assertFalse(erred);
	}
	
	@Test
	public void validComplexTest5() {
		String thm =  "~~~~~(~p=>(p=>(p=>q)))";
		String app = "~~~~~(~(a=>b)=>((a=>b)=>((a=>b)=>(b=>c))))";
		TheoremSet thmSet = new TheoremSet();
		boolean erred = false;
		try {
			thmSet.put("thm", new Expression(thm));
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
		}
		try {
			thmSet.checkTheoremApplication("thm", new Expression(app));
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
			erred = true;
		}
		assertFalse(erred);
	}
	
	@Test
	public void invalidTest1() {
		String thm = "a";
		String app = null;
		TheoremSet thmSet = new TheoremSet();
		String errmsg = "";
		try {
			thmSet.put("thm", new Expression(thm));
		}
		catch(IllegalLineException e) {
			errmsg = e.getMessage();
			if (DEBUGGING) {
				System.out.println(errmsg);
			}
		}
		try {
			thmSet.checkTheoremApplication("thm", new Expression(app));
		}
		catch(IllegalLineException e) {
			errmsg = e.getMessage();
		}
		assertTrue(errmsg.equals("Input null string for expression"));
	}
	
	@Test
	public void invalidTest2() {
		String thm = null;
		String app = "a";
		TheoremSet thmSet = new TheoremSet();
		String errmsg = "";
		try {
			thmSet.put("thm", new Expression(thm));
		}
		catch(IllegalLineException e) {
			errmsg = e.getMessage();
			if (DEBUGGING) {
				System.out.println(errmsg);
			}
		}
		try {
			thmSet.checkTheoremApplication("thm", new Expression(app));
		}
		catch(IllegalLineException e) {
			errmsg = e.getMessage();
			if (DEBUGGING) {
				System.out.println(errmsg);
			}
		}
		assertTrue(errmsg.equals("Not a valid theorem name"));
	}
	
	// Test that exception is thrown when thm has two children and
	// app has none
	@Test
	public void invalidTest3() {
		String thm = "(a=>b)";
		String app = "c";
		TheoremSet thmSet = new TheoremSet();
		String errmsg = "";
		boolean erred = false;
		try {
			thmSet.put("thm", new Expression(thm));
		}
		catch(IllegalLineException e) {
			errmsg = e.getMessage();
			if (DEBUGGING) {
				System.out.println(errmsg);
			}
		}
		try {
			thmSet.checkTheoremApplication("thm", new Expression(app));
		}
		catch(IllegalLineException e) {
			errmsg = e.getMessage();
			if (DEBUGGING) {
				System.out.println("errmsg:    " + errmsg);
			}
			erred = true;
		}
		assertTrue(erred);
		assertTrue(errmsg.equals("Application will always be invalid when smaller than the theorem"));
	}
	
	// Test that exception is thrown when thm has more
	// right children than app
	@Test
	public void invalidTest4() {
		String thm = "(a=>(b=>c))";
		String app = "(c=>d)";
		TheoremSet thmSet = new TheoremSet();
		String errmsg = "";
		boolean erred = false;
		try {
			thmSet.put("thm", new Expression(thm));
		}
		catch(IllegalLineException e) {
			errmsg = e.getMessage();
			if (DEBUGGING) {
				System.out.println(errmsg);
			}
		}
		try {
			thmSet.checkTheoremApplication("thm", new Expression(app));
		}
		catch(IllegalLineException e) {
			errmsg = e.getMessage();
			if (DEBUGGING) {
				System.out.println("errmsg:    " + errmsg);
			}
			erred = true;
		}
		assertTrue(erred);
		assertTrue(errmsg.equals("Application will always be invalid when smaller than the theorem"));
	}
	
	// Test that exception is thrown when thm has more
	// left children than app
	@Test
	public void invalidTest5() {
		String thm = "((a=>b)=>c)";
		String app = "(c=>d)";
		TheoremSet thmSet = new TheoremSet();
		String errmsg = "";
		boolean erred = false;
		try {
			thmSet.put("thm", new Expression(thm));
		}
		catch(IllegalLineException e) {
			errmsg = e.getMessage();
			if (DEBUGGING) {
				System.out.println(errmsg);
			}
		}
		try {
			thmSet.checkTheoremApplication("thm", new Expression(app));
		}
		catch(IllegalLineException e) {
			errmsg = e.getMessage();
			if (DEBUGGING) {
				System.out.println("errmsg:    " + errmsg);
			}
			erred = true;
		}
		assertTrue(erred);
		assertTrue(errmsg.equals("Application will always be invalid when smaller than the theorem"));
	}
	
	// Test that exception is thrown when second occurrence
	// of p in app does not match first
	@Test
	public void invalidTest6() {
		String thm =  "(p=>(p=>q))";
		String app = "((a=>b)=>((a=>c)=>c))";
		TheoremSet thmSet = new TheoremSet();
		String errmsg = "";
		boolean erred = false;
		try {
			thmSet.put("thm", new Expression(thm));
		}
		catch(IllegalLineException e) {
			errmsg = e.getMessage();
			if (DEBUGGING) {
				System.out.println(errmsg);
			}
		}
		try {
			thmSet.checkTheoremApplication("thm", new Expression(app));
		}
		catch(IllegalLineException e) {
			errmsg = e.getMessage();
			if (DEBUGGING) {
				System.out.println("errmsg:    " + errmsg);
			}
			erred = true;
		}
		assertTrue(erred);
		assertTrue(errmsg.equals("Mismatching application of" +
				" previously seen expression"));
	}
	
	// Test that exception is thrown when third occurrence of
	// p in app does not match second
	@Test
	public void invalidTest7() {
		String thm =  "~(p=>(p=>(p=>q)))";
		String app = "~((a=>b)=>((a=>b)=>((a=>c)=>(b=>c))))";
		TheoremSet thmSet = new TheoremSet();
		String errmsg = "";
		boolean erred = false;
		try {
			thmSet.put("thm", new Expression(thm));
		}
		catch(IllegalLineException e) {
			errmsg = e.getMessage();
			if (DEBUGGING) {
				System.out.println(errmsg);
			}
		}
		try {
			thmSet.checkTheoremApplication("thm", new Expression(app));
		}
		catch(IllegalLineException e) {
			errmsg = e.getMessage();
			if (DEBUGGING) {
				System.out.println("errmsg:    " + errmsg);
			}
			erred = true;
		}
		assertTrue(erred);
		assertTrue(errmsg.equals("Mismatching application of" +
				" previously seen expression"));
	}
	
	// Test that exception is thrown when third occurrence  of
	// p in app does not match second and both expressions involve
	// multiple negations
	@Test
	public void invalidTest8() {
		String thm =  "~~~(p=>(p=>(p=>q)))";
		String app = "~~~~((a=>b)=>((a=>b)=>((a=>b)=>(b=>c))))";
		TheoremSet thmSet = new TheoremSet();
		boolean erred = false;
		String errmsg = "";
		try {
			thmSet.put("thm", new Expression(thm));
		}
		catch(IllegalLineException e) {
			errmsg = e.getMessage();
			if (DEBUGGING) {
				System.out.println(errmsg);
			}
		}
		try {
			thmSet.checkTheoremApplication("thm", new Expression(app));
		}
		catch(IllegalLineException e) {
			errmsg = e.getMessage();
			erred = true;
			if (DEBUGGING) {
				System.out.println(errmsg);
			}
		}
		assertTrue(erred);
	}

}
