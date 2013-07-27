import java.util.*;

public class Expression {
    
    public String myname;
    public static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    
    /**Expression Tree  */
    public ExprTree exprtree;

    /** Expression constructor. Assumes expression is valid. */
    public Expression (String name) throws IllegalLineException {
        myname = name;
        try {
        exprtree = new ExprTree(name);
        } catch (IllegalLineException e) {
            throw e;
        }
    }
    
    public boolean equals(Object obj) {
        Expression second;
        try {
            second = (Expression) obj;
        } catch (IllegalArgumentException e) {
            throw e;
        }
        return (myname.equals(second.myname)
                && exprtree.equals(second.exprtree));
    }



    /**Binary Tree split along implications or logical operators (&, |, ~) */
    public static class ExprTree {

        ExprTreeNode myRoot;
        
        ExprTree (String s) throws IllegalLineException {
            if (s != null) {
                myRoot = treehelper(s);
            } else {
                throw new IllegalLineException("Input null string for expression");
            }
        }
        
        /** Checks equality between two Expr Trees using tree traversal */
        public boolean equals(Object obj) {
            ExprTree second = (ExprTree) obj;
            return treeEquals(this, second);
        }
        
        public static boolean treeEquals(ExprTree tree1, ExprTree tree2){
            if (tree1.myRoot == null || tree2.myRoot == null){
                throw new IllegalArgumentException("Can't use a null tree");
            } return treeEqualshelper(tree1.myRoot, tree2.myRoot);
        }
        
        private static boolean treeEqualshelper(ExprTreeNode node1, ExprTreeNode node2){
            if(node1 == null && node2 == null){
                return true;
            }
            if(!(node1.myItem.equals(node2.myItem))){
                return false;
            }
            return treeEqualshelper(node1.getleft(), node2.getleft()) && treeEqualshelper(node1.getright(), node2.getright());
        
        }
        
        /** Parses a string to create an expression tree that forks based on
         * operators (=> & | ~) and has leaves of single variables.
         * Throws exceptions if syntax is incorrect.
         * If string isn't surrounded by parentheses, it should be ~~~a or some form.
         * If surrounded by parentheses, should be properly nested (equal amount left/right) */
        ExprTreeNode treehelper (String s) throws IllegalLineException {
            
            //System.out.println(s);
            if (s.length() == 0 || s == null) {
                throw new IllegalLineException("Parser given empty or null string.");
            }
            
            // Initialize node w/ dummy string & no children (always replaced)
            ExprTreeNode node = new ExprTreeNode("To Be Replaced");
            String first = s.substring(0, 1);
            String last = s.substring(s.length()-1, s.length());
            
            //System.out.println(first);
            
            // If first element is (, last must be ) as well
            if (first.equals("(") && !last.equals(")")) {
                throw new IllegalLineException("Expressions starting w/ ( must end w/ )");
            }
            
            // One-element string? Just make it a node(leaf)
            // However, that element MUST be a letter - throw exception otherwise
            if (s.length() == 1) {
                if (!alphabet.contains(s)) {
                    throw new IllegalArgumentException("Branched to single element, wasn't variable.");
                }
                node.myItem = s;
                return node;
            }
            
            //Start substring after first element (ideally a '(' ) and before last ')'
            String rest = s.substring(1, s.length());
            
            // Negation symbols are single nodes; child (rest of expression)
            // is always to its right
            if (first.equals("~")) {
                node.myItem = ("~");
                node.myright = treehelper(s.substring(1));
                return node;
            }
            
            // String wasn't one-element, therefore it should start with only ~ or (
            if (!first.equals("(")) {
                throw new IllegalArgumentException("Branched to subexpression of length > 1,"
                        + "didn't start with ( or ~ (expression must either be wrapped() or ~)");
            }
            
            
            // Keep track of parentheses as expression nests ((a & b) => c) => (a | b))
            // Occurrences of ) decrement parencount, occurrences of ( increment
            // If parencount is ever negative, then there are more ) than (
            // Searches for operators (=> & |) and splits expr into substrings, recurses
            // The program should not pass this loop; the initial expression must have either
            // A unary operator (~, x) or a binary operator (=> & |); the unary operators
            // Are base cases that return before this loop is reached -
            // Exiting this loop means there was no binary operator ((a=>b)d)
            // Or there were unnecessary parentheses (((a=>b))=>c)
            int parencount = 0;
            int i = 1;
            while(i < s.length()) {
                char currentchar = s.charAt(i);
                if (parencount < 0) {
                    throw new IllegalArgumentException("More ) than (");
                }
                if (currentchar == '(') {
                    parencount ++;
                    i ++;
                } else if (currentchar == ')') {
                    parencount --;
                    i ++;
                // Assumes you've reached the '>' in "=>"
                } else if (currentchar == '>' && s.charAt(i-1) == '=' && parencount == 0) {
                    node.myItem = "=>";
                    
                    String preop = s.substring(1, i-1);
                    node.myleft = treehelper(preop);
                    
                    String postop = s.substring(i+1, s.length()-1);
                    node.myright = treehelper(postop);
                    
                    return node;
                // Reached & or |, split along operator and recurse
                } else if ((currentchar == '&' || currentchar == '|') && parencount == 0) {
                    String op = s.substring(i, i+1);
                    node.myItem = op;
                    
                    String preop = s.substring(1, i); // Go from ( until operator
                    node.myleft = treehelper(preop);
                    
                    String postop = s.substring(i+1, s.length()-1);
                    node.myright = treehelper(postop);
                    
                    return node;
                }
                i ++;
                
            }
            
            if (i > -1) {
                throw new IllegalLineException("Constructor had extra () or no central operator");
            }
            return node;
        }
        
        public void print ( ) {
            if (myRoot != null) {
                printHelper (myRoot, 0);
            }
        }
        
        protected static final String indent1 = "    ";
        
        /** Traverses tree to print all elements */
        private static void printHelper (ExprTreeNode root, int indent) {
            if (root.myright != null) {
                printHelper(root.myright, indent+1);
            }
            println (root.myItem, indent);
            if (root.myleft != null) {
                printHelper(root.myleft, indent+1);
            }
        }
        
        /** Prints necessary indents, then the object */
        private static void println (Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print (indent1);
            }
            System.out.println (obj);
        }
        
        /**Nodes of the expression tree. Contain only Strings */
        public static class ExprTreeNode {
            
            /** The item held at this node. */
            private String myItem;
            private ExprTreeNode myleft;
            private ExprTreeNode myright;
            
            ExprTreeNode(String s) {
                myItem = s;
                myleft = myright = null;
            }
            
            /** Make a "full" node with left and right children. */
            ExprTreeNode(String node, String left, String right) {
                myItem = node;
                myleft = new ExprTreeNode(left);
                myright = new ExprTreeNode(right);
            }
            
            /** Get node's item (always a string) */
            public String get() {
                return myItem;
            }
            
            /** Return left child, a Node. */
            public ExprTreeNode getleft() {
                return myleft;
            }
            
            /** Return right child, a Node. */
            public ExprTreeNode getright() {
                return myright;
            }
        }
    }
}
