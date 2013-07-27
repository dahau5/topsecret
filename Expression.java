

This repository
Explore
Gist
Blog
Help
stevenbaum
1  Unwatch
Star 0Fork 3PUBLIC stevenbaum/topsecret
forked from dahau5/topsecret
 branch: master  topsecret / Logic.java 
 vwb 5 hours ago Update Logic.java
2 contributors    
 file 153 lines (131 sloc) 4.577 kb EditRawBlameHistory Delete
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
61
62
63
64
65
66
67
68
69
70
71
72
73
74
75
76
77
78
79
80
81
82
83
84
85
86
87
88
89
90
91
92
93
94
95
96
97
98
99
100
101
102
103
104
105
106
107
108
109
110
111
112
113
114
115
116
117
118
119
120
121
122
123
124
125
126
127
128
129
130
131
132
133
134
135
136
137
138
139
140
141
142
143
144
145
146
147
148
149
150
151
152
// The methods associated with the different logical
// inferences. Evaluating 

//linemap
//datamap


import javax.swing.tree.TreeNode;


// The methods associated with the different logical
// inferences. Evaluating 

//linemap
//datamap

public class Logic {


	public Logic(){

	}

	public boolean treeEquals(tree tree1, tree tree2){
		if (tree1.myRoot == null || tree2.myRoot == null){
			throw new IllegalArgumentException("Can't use a null tree");
		} return treeEqualshelper(tree1.myRoot, tree2.myRoot);
	}

	private boolean treeEqualshelper(TreeNode node1, TreeNode node2){
		if(node1 == null && node2 == null){
			return true;
		}
		if(!(node1.myitem.equals(node2))){
			return false;
		}
		return treeEqualshelper(node1.myLeft, node2.myLeft) && treeEqualshelper(node1.myRight, node2.myRight);
	}

	public static void assume(Expression exp1, LineNumber myLine){
		// yay pseudo code. So I need to check if the exp1 is the myTree.myleft of the expression
		// tied to the previous line number (its own line number with the last number taken off
		int index;
		String temp = myLine.myNumber;
		if (temp.length()>1){
			for (int i = myLine.myNumber.length()-1; i>0 ; i--){
				if (myLine.myNumber.get(i) == "."){
					index = i;
					break;
				}
			}
			temp = myLine.myNumber.substring(0, index);
		}
		Expression exp2 = hashmap.get(temp).get(1);
		if(treeEquals(exp1, exp2.myTree.myLeft)){
			exp1.isproven = true;
		}else if(exp1.mystring.startsWith("~") && treeEquals(exp1.myTree, exp2.myTree)){
			exp1.isproven = true;
		}
		//need to check if the previous line has a & or a |, in which case
		//the only acceptable expression is the not of it. Otherwise the left hand statement 
		//is A-Ok

	}

	public static void moduspolens (Expression exp1, Expression exp2, Expression test)
		throws IllegalLineException {
		// from exp1 and (exp1=>exp2)exp2,  test is true
		Expression myE1;
		Expression myE2;
		if (exp1.mystring.length() < exp2.mystring.length()){
			myE1 = exp1;
			myE2 = exp2;
		} else {
			myE1 = exp2;
			myE2 = exp1;
		}
		// I need some way to grab the tree at the right point so as to compare it correctly with 
		// the input value given to mp. Can't compare the entire expression tree each time modus
		// polens is called. Unfeasible. 

		//The toString is grabbing the string representation of an expression tree of 
		//the second value given to modus polens. Or the expression that holds both the
		//assumed statement, and the one we are testing to hold true. Possible that we need to make an expression
		//tree of each myE2 passed into the modus polens to ensure it is logically sound. Just an idea.

		if (treeEquals(myE1.myTree, myE2.myTree.myLeft)){ 
			if (treeEquals(myE2.myTree.myRight, test.myTree)){
				test.isproven = true;
			}else{
				throw new IllegalInferenceException("Improper use of mp. " + test + " not in " + myE2);
			}
		}else{
			throw new IllegalInferenceException("Improper use of mp. " + myE1 + " not in " + myE2);
		}
		//if myE1 the exact same expression as immediately before the 
		//implication in myE2: then "test.isproven = true"
	}

	public static void modustollens (Expression exp1, Expression exp2, Expression test){
		// ~exp2 and exp1=>exp2(exp1) ~exp1(test) is true
		Expression myE1;
		Expression myE2;
		if (exp1.mystring.length() < exp2.mystring.length()){
			myE1 = exp1;
			myE2 = exp2;
		} else {
			myE1 = exp2;
			myE2 = exp1;
		}
		if(myE1.mystring.startsWith("~") && treeEquals(myE1.myTree.myRight, myE2.myTree.myRight)){
			if(test.mystring.startsWith("~") && treeEquals(test.myTree.myRight, myE2.myTree.myLeft)){
				test.isproven = true;
			}
		}
	}

	public static void construction (Expression exp1, Expression exp2){
		// exp2 (exp1 => exp2) for any exp1
		Expression myE1;
		Expression myE2;
		if (exp1.mystring.length() < exp2.mystring.length()){
			myE1 = exp1;
			myE2 = exp2;
		} else {
			myE1 = exp2;
			myE2 = exp1;
		}
		if(treeEquals(myE1.myTree, myE2.myTree.myRight)){
			myE2.isproven = true;
		}
	}

	public static void contradiction (Expression exp1, Expression exp2, Expression test){
		// ~exp1 and exp1 infer ANY EXPRESSION
		Expression myE1;
		Expression myE2;
		if (exp1.mystring.length() < exp2.mystring.length()){
			myE1 = exp1;
			myE2 = exp2;
		} else {
			myE1 = exp2;
			myE2 = exp1;
		}
		//Need to add some check for the 'test' to make sure that the 'test' 
		//variable is correctly inputted and used by the user.
		if(myE2.mystring.startsWith("~") && treeEquals(myE1.myTree, myE2.myTree.myRight)){
			test.isproven = true;
		}

}
}
Status API Training Shop Blog About © 2013 GitHub, Inc. Terms Privacy Security Contact 
