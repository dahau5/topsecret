import static org.junit.Assert.*;

import org.junit.Test;


public class ExpressionTest {

    @Test
    public void testbasic() throws IllegalLineException {
        // Tests explicit base cases; generates the expression tree and manually checks nodes
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
    public void testcomplex() throws IllegalLineException {
        //Test to purely evaluate syntax parsing 
        Expression e = null;
        boolean errored = false;
        // Test "a"
        try {
            e = new Expression("a");
        } catch (IllegalLineException a) {
            errored = true;
        }
        assertFalse(errored);

        try {
            e = new Expression("c");
        } catch (IllegalLineException a) {
            errored = true;
        }
        assertTrue(e.exprtree.myRoot.get() == "c");
        assertFalse(errored);
        
        // Test "(a=>(b))" : Should set 'errored' to true
        try {
            e = new Expression("(a=>(b))");
        } catch (IllegalLineException a) {
            errored = true;
        }
        assertTrue(errored);
        errored = false;
        
        // Test multiple negations
        try {
            e = new Expression("~~~~x");
        } catch (IllegalLineException a) {
            errored = true;
        }
        assertFalse(errored);

        // Test an unnecessary set of (): Should error
        try {
            e = new Expression("(~x(=>)~~a)");
        } catch (IllegalLineException a) {
            errored = true;
        }
        assertTrue(errored);
        errored = false;

        // Test multiple negations into a nested expression
        try {
            e = new Expression("~~~(a=>(b=>c))");
        } catch (IllegalLineException a) {
            errored = true;
        }
        assertFalse(errored);

        // Test heavily nested expression
        try {
            e = new Expression("((a&b)=>~(b=>(c=>d)))");
        } catch (IllegalLineException a) {
            errored = true;
        }
        assertFalse(errored);

        // Test heavily nested expression with slight error: Should error
        try {
            Expression b = new Expression("((a&b)a=>~(b=>(c=>d)))");
        } catch (IllegalLineException a) {
            errored = true;
        }
        assertTrue(errored);
        errored = false;
        
        // Test heavily heavily nested expression
        try {
            Expression l = new Expression("((a=>(b=>c))=>((a=>b)=>(a=>c)))");
        } catch (IllegalLineException a) {
            errored = true;
        }
        assertFalse(errored);
        
        // Test incredibly nested expression
        try {
            Expression a = new Expression("((a=>q)=>((b=>q)=>((a|b)=>q)))");
        } catch (IllegalLineException a) {
            errored = true;
        }
        assertFalse(errored);
        
        
    }

}
