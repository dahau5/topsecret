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

}
