<<PROJECT 2 README>>

--Test Rationale--


/ExpressionTest.java/
---------------------------

Our implementation of Expression objects had them contain two fields: a string representation of
the expression, and an expression tree with leaves as variables and branches as logical operators.
The Expression constructor was responsible for checking the syntax of the given string and throwing
any relevant IllegalLineException, and consequently our testing of expressions was based on the
construction and parsing of Expression objects. We set up some small-scale tests where we could
explicitly assert that elements in the expression tree were equal to individual strings.

Ex. Expression e = new Expression("(a=>b)");
String rootval = e.exprtree.myRoot.get();
String leftval = e.exprtree.myRoot.getleft().get();
String rightval = e.exprtree.myRoot.getright().get();
assertTrue(rootval.equals("=>");
assertTrue(leftval.equals("a");
assertTrue(rightval.equals("b");

However, because it would become very difficult to individually check nodes in larger trees, we
then relied upon our boolean 'treeEquals' method, which takes two binary expression trees and returns
true iff the trees are exactly identical in the placement and values of their nodes.

The construction of an expression tree (and thereby the correctness of an expression's syntax) hinges
largely upon a small set of base requirements. The tree-making program should throw an exception if:

- The tree branches to a substring of length 1 and it isn't an letter (i.e. a variable)
- The tree branches to a substring not starting with ~ (negation) or enclosed by () (normal parens)
- The tree branches to a null or empty substring
- The tree cannot find a central logical operator (=> & |) on which to split

Looking at this conversely, Expressions should be white-listed on the idea that they are made up of
branching operators and base case expressions (a=>b) (a|b) (a) (a&b) (~a) thus the tests are sufficient
because the recursive nature of the expression trees means that any sufficiently large/nested expression
will be valid if a slightly smaller / less nested version is as well. Our intetionally bugged tests
point to specific issues - extra parentheses, multi-letter variables, and a lack of a central operator.

---------------------------

/General Note/

Another common testing technique was to create a boolean, 'errored', that would default to false and
only set to true if an exception were caught in the try...catch blocks of constructing expressions. 
This technique was also used in other testing methods to try intentionally erroneous inputs.
We asserted that 'errored' would be true after said try/catch, and then reset it to false to continue
other tests.


---------------------------


/ProofTest.java/
---------------------------

For testing the proof class we implemented small, manually-created proofs of varying length to check the
validity of input lines. Because the proof class is largely responsible for checking the validity of the
overall line (referencing legal instructions, those instructions having the correct number of arguments)
we constructed a very thorough suite of tests (over 1,000 lines long) that gave all manner of possible
user input. We also created tests for specific instructions (mp, ic, mt, etc), and because the validity of
these instructions can rely on previous lines, we created specific instances where the tests could or couldn't
make legal references to previous lines. For example, if the user calls 'assume', it must occur directly
after a call to 'show'. We made cases where that was and wasn't true, and asserted the errors/non-errors as
such.

Again, the concept of whitelisting was very relevant to knowing that ProofTest.java was comprehensive.
The input lines for a proof have a very specific format, and the correctness of expression syntax
is handled elsewhere (Expression.java). Thus, the only occupation of ProofTest is to confirm that the
input has the right structure, and that calls are making references to legal instructions

---------------------------


/TheoremSetTest.java/
---------------------------

Testing of the theoremset relies on a simple pass/fail check of theorem additions and their applications.
There is an easy duality to testing; either the theorem in the input is an existing theorem or it isn't.
Either the application of the theorem correctly matches variable for variable or it doesn't. If not,
exceptions are thrown and there is no consideration for -why- or -where- the theorem wasn't applicable.
Consequently we could set up very explicit tests for situations in which theorems exactly matched and
correctly applied, and situations wherein there wasn't a correct match.

---------------------------


/LogicTest.java/
---------------------------

Because of the intensive abstraction for each portion of the project (creating expressions, handling user
input syntax, applying theorems) and the overall concept of the program (we're merely checking for effective
lines, not applying the actual logic) the testing of logic was very light. All of our logic methods are void
methods whose only job is to throw exceptions if the syntax of the expressions didn't match the correct use
of the rule (you can only assume the negation of a show, or the lefthand side of its implication, etc).
Thus the logic tests merely relied on a series of try/catch blocks that caught exceptions if the void methods
threw them.

---------------------------


/LineNumberTest.java/
---------------------------

Much like the Proof class and ProofTest.java, the testing of LineNumber relied on creating small-scale 
proofs whose line numbers could be related to each other. By creating a small series of line numbers and
relevant expressions, we could test the linenumber objects and their relation (a assume must follow a show,
etc.).

PARTNER CONTRIBUTIONS

Steven Baum wrote the Expression class, generated expression trees and did relevant testing/debugging.
Vincent Budrovich wrote the Logic/Proof classes and did extensive relevant testing/debugging.
Sam Hausman implemented TheoremSet and its integration into the program, as well as black-box testing/debugging.
Trevor Davenport wrote the Logic/Proof classes and did extensive test cases.
