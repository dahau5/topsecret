import static org.junit.Assert.*;

import org.junit.Test;


public class ExpressionTest {

    @Test
    public void testbasic() throws IllegalLineException {
        boolean errored = false;
        Expression e = null;
        try {
            e = new Expression("a");
        } catch (IllegalLineException i) {
            errored = true;
            System.err.println("Shouldn't print or set boolean.");
        }
        assertTrue(e.exprtree.myRoot.get().equals("a"));
        assertTrue(e.exprtree.myRoot.getleft() == null);
        assertTrue(e.exprtree.myRoot.getright() == null);
        assertFalse(errored);
        
        
        Expression f = null;
        try {
            f = new Expression("(a=>b)");
        } catch (IllegalLineException i) {
            errored = true;
            System.err.println("Shouldn't print or set boolean.");
        }
        
        assertTrue(f.exprtree.myRoot.get().equals("=>"));
        assertTrue(f.exprtree.myRoot.getleft().get().equals("a"));
        assertTrue(f.exprtree.myRoot.getright().get().equals("b"));
        assertFalse(errored);
        
        Expression g = null;
        try {
            g = new Expression("(a&b)");
        } catch (IllegalLineException i) {
            errored = true;
        }
        assertTrue(g.exprtree.myRoot.get().equals("&"));
        assertTrue(g.exprtree.myRoot.getleft().get().equals("a"));
        assertTrue(g.exprtree.myRoot.getright().get().equals("b"));
    }
    
    @Test
    public void test() throws IllegalLineException {
        Expression e = new Expression("a");

        e = new Expression("c");
        assertTrue(e.exprtree.myRoot.get() == "c");
        //e.exprtree.print();
        
        e = new Expression("(a=>b)");
        //e.exprtree.print();
        
        e = new Expression("~~~~x");
        //e.exprtree.print();
        
        e = new Expression("(~x=>~~a)");
        //e.exprtree.print();
        
        e = new Expression("~~~(a=>b)");
        //e.exprtree.print(); */
        
        e = new Expression("((a&b)=>~(b=>(c=>d)))");
        //e.exprtree.print();
        Expression b = new Expression("((a&b)=>~(b=>(c=>d)))");
        
        Expression l = new Expression("((a=>(b=>c))=>((a=>b)=>(a=>c)))");
        //e.exprtree.print();
        
        assertTrue(e.exprtree.equals(b.exprtree));
        
        Expression a = new Expression("((a=>q)=>((b=>q)=>((a|b)=>q)))");
        //a.exprtree.print();
        
        
    }
    
        @Test
	public void nodeToStringTest() {
		Expression ex = null;
		try {
			ex = new Expression("~a");
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
		}
		Expression.ExprTree.ExprTreeNode node = ex.exprtree.myRoot;
		assertEquals(node.toString(), "~a");
		
		Expression ex1 = null;
		try {
			ex1 = new Expression("a");
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
		}
		Expression.ExprTree.ExprTreeNode node1 = ex1.exprtree.myRoot;
		assertEquals(node1.toString(), "a");
		
		Expression ex2 = null;
		try {
			ex2 = new Expression("(a=>b)");
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
		}
		Expression.ExprTree.ExprTreeNode node2 = ex2.exprtree.myRoot;
		assertEquals(node2.toString(), "(a=>b)");
		
		Expression ex3 = null;
		try {
			ex3 = new Expression("((a=>b)=>c)");
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
		}
		Expression.ExprTree.ExprTreeNode node3 = ex3.exprtree.myRoot;
		assertEquals(node3.toString(), "((a=>b)=>c)");
		
		Expression ex4 = null;
		try {
			ex4 = new Expression("((a=>b)=>(c=>d))");
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
		}
		Expression.ExprTree.ExprTreeNode node4 = ex4.exprtree.myRoot;
		assertEquals(node4.toString(), "((a=>b)=>(c=>d))");
		
		Expression ex5 = null;
		try {
			ex5 = new Expression("((~a=>b)=>(c=>d))");
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
		}
		Expression.ExprTree.ExprTreeNode node5 = ex5.exprtree.myRoot;
		assertEquals(node5.toString(), "((~a=>b)=>(c=>d))");
		
		Expression ex6 = null;
		try {
			ex6 = new Expression("((~a=>b)=>(c=>~(d=>~(e=>(f=>~g)))))");
		}
		catch(IllegalLineException e) {
			System.out.println(e.getMessage());
		}
		Expression.ExprTree.ExprTreeNode node6 = ex6.exprtree.myRoot;
		assertEquals(node6.toString(), "((~a=>b)=>(c=>~(d=>~(e=>(f=>~g)))))");

}
