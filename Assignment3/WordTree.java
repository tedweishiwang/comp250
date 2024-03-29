package assignments2017.a3posted;


//Name: Weishi Wang
//Student ID: 260540022


//COMP 250 - Introduction to Computer Science - Fall 2017
//Assignment #3 - Question 1

import java.util.*;

import assignments2017.a3posted.Tree.TreeNode;

/*
 *  WordTree class.  Each node is associated with a prefix of some word 
 *  stored in the tree.   (Any string is a prefix of itself.)
 */

public class WordTree
{

	WordTreeNode root;

	// Empty tree has just a root node.  All the children are null.

	public WordTree() 
	{
		root = new WordTreeNode();
	}


	/*
	 * Insert word into the tree.  First, find the longest 
	 * prefix of word that is already in the tree (use getPrefixNode() below). 
	 * Then, add TreeNode(s) such that the word is inserted 
	 * according to the specification in PDF.
	 */

	public void insert(String word)
	{
		//  ADD YOUR CODE BELOW HERE
		String prefix = this.getPrefixNode(word).toString();
		int j=0;
		if (prefix.length()==0) {
			while(j<word.length()) {
			this.getPrefixNode(word).createChild(word.charAt(j));
			j++;
		}
		}
		else {
		int i = prefix.length();
		while (i<word.length()) {
			this.getPrefixNode(word).createChild(word.charAt(i));
			i++;
		}
		}
		this.getPrefixNode(word).endOfWord=true;
		
		//  ADD YOUR ABOVE HERE
	}

	// insert each word in a given list 

	public void loadWords(ArrayList<String> words)
	{
		for (int i = 0; i < words.size(); i++)
		{
			insert(words.get(i));
		}
		return;
	}

	/*
	 * Given an input word, return the TreeNode corresponding the longest prefix that is found. 
	 * If no prefix is found, return the root. 
	 * In the example in the PDF, running getPrefixNode("any") should return the
	 * dashed node under "n", since "an" is the longest prefix of "any" in the tree. 
	 */
	WordTreeNode getPrefixNode(String word)
	{
		//   ADD YOUR CODE BELOW HERE
		//compare word by word with the prefix
		int length = word.length();
		WordTreeNode level = this.root;
		for(int i=0;i<length;i++) {
		char letter = word.charAt(i);
			if(level.getChild(letter)!=null) {
				if(level.getChild(letter).charInParent==letter) {
				level=level.getChild(letter);
			}
			else {
				break;
			}
		}
			else {
				break;
			}
		}
		
		return level;
		
		
		//   ADD YOUR CODE ABOVE HERE

	}

	/*
	 * Similar to getPrefixNode() but now return the prefix as a String, rather than as a TreeNode.
	 */

	public String getPrefix(String word)
	{
		return getPrefixNode(word).toString();
	}


	/*
	 *  Return true if word is contained in the tree (i.e. it was added by insert), false otherwise.
	 *  Hint:  any string is a prefix of itself, so you can use getPrefixNode().
	 */
	public boolean contains(String word)
	{  
		//   ADD YOUR CODE BELOW HERE
		if(word.length()==this.getPrefixNode(word).toString().length() && this.getPrefixNode(word).isEndOfWord()==true) {
			return true;
		}
		else {
			return false;
		}
		//   ADD YOUR CODE ABOVE HERE
	}

	/*
	 *  Return a list of all words in the tree that have the given prefix. 
	 *  For example,  getListPrefixMatches("") should return all words in the tree.
	 */
	public ArrayList<String> getListPrefixMatches( String prefix )
	{
		//  ADD YOUR CODE BELOW 
		
		//use stack to do this method
			ArrayList<String> word = new ArrayList<String>();
			Stack<WordTreeNode> store = new Stack<WordTreeNode>();
			WordTreeNode cur = getPrefixNode(prefix);
			//if the prefix do not exist,then return an empty arraylist
			if(!cur.toString().equals(prefix)) {
				return word;
			}
			
		  if (cur.parent == null && prefix.length()!=0) {
		   return word;
		  }
		  //every time pop a node, look for its children and push them into the stack
		  store.push(cur);
		  while (!store.isEmpty()) {
		   cur = store.peek();
		   if (cur.endOfWord) {
		    word.add(cur.toString());
		   }
		   store.pop();
		   
		   //look for its children and push them in the stack
		   for (int i = 0; i <256; i++) {
		    if (cur.children[i]!=null) {
		     store.push(cur.children[i]);
		    }
		   }
		  }

		  return word;  
		
		//  ADD YOUR CODE ABOVE HERE
	}


	/*
	 *  Below is the inner class defining a node in a Tree (prefix) tree.  
	 *  A node contains an array of children: one for each possible character.  
	 *  The children themselves are nodes.
	 *  The i-th slot in the array contains a reference to a child node which corresponds 
	 *  to character  (char) i, namely the character with  ASCII (and UNICODE) code value i. 
	 *  Similarly the index of character c is obtained by "casting":   (int) c.
	 *  So children[97] = children[ (int) 'a']  would reference a child node corresponding to 'a' 
	 *  since (char)97 == 'a'   and  (int)'a' == 97.
	 * 
	 *  To learn more:
	 * -For all unicode charactors, see http://unicode.org/charts
	 *  in particular, the ascii characters are listed at http://unicode.org/charts/PDF/U0000.pdf
	 * -For ascii table, see http://www.asciitable.com/
	 * -For basic idea of converting (casting) from one type to another, see 
	 *  any intro to Java book (index "primitive type conversions"), or google
	 *  that phrase.   We will cover casting of reference types when get to the
	 *  Object Oriented Design part of this course.
	 */

	public class WordTreeNode
	{
		/*  
		 *   Highest allowable character index is NUMCHILDREN-1
		 *   (assuming one-byte ASCII i.e. "extended ASCII")
		 *   
		 *   NUMCHILDREN is constant (static and final)
		 *   To access it, write "TreeNode.NUMCHILDREN"
		 *   
		 *   For simplicity,  we have given each WordTree node 256 children. 
		 *   Note that if our words only consisted of characters from {a,...,z,A,...,Z} then
		 *   we would only need 52 children.   The WordTree can represent more general words
		 *   e.g.  it could also represent many special characters often used in passwords.
		 */

		public static final int NUMCHILDREN = 256;

		WordTreeNode     parent;
		WordTreeNode[]   children;
		int              depth;            // 0 for root, 1 for root's children, 2 for their children, etc..
		
		char             charInParent;    // Character associated with the tree edge from this node's parent 
		                                  // to this node.
		// See comment above for relationship between an index in 0 to 255 and a char value.
		
		boolean endOfWord;   // Set to true if prefix associated with this node is also a word.

		
		// Constructor for new, empty node with NUMCHILDREN children.  
		//  All the children are automatically initialized to null. 

		public WordTreeNode()
		{
			children = new WordTreeNode[NUMCHILDREN];
			
			//   These assignments below are unnecessary since they are just the default values.
			
			endOfWord = false;
			depth = 0; 
			charInParent = (char)0; 
		}


		/*
		 *  Add a child to current node.  The child is associated with the character specified by
		 *  the method parameter.  Make sure you set as many fields in the child node as you can.
		 *  
		 *  To implement this method, see the comment above the inner class TreeNode declaration.  
		 *  
		 */
		
		public WordTreeNode createChild(char  c) 
		{	   
			WordTreeNode child       = new WordTreeNode();

			// ADD YOUR CODE BELOW HERE
			child.parent=this;
			this.children[c]=child;
			//the depth increases for children
			child.depth=this.depth+1;
			child.charInParent=c;
			child.endOfWord = false;
			// ADD YOUR CODE ABOVE HERE

			return child;
		}

		// Get the child node associated with a given character, i.e. that character is "on" 
		// the edge from this node to the child.  The child could be null.  

		public WordTreeNode getChild(char c) 
		{
			return children[ c ];
		}

		// Test whether the path from the root to this node is a word in the tree.  
		// Return true if it is, false if it is prefix but not a word.

		public boolean isEndOfWord() 
		{
			return endOfWord;
		}

		// Set to true for the node associated with the last character of an input word

		public void setEndOfWord(boolean endOfWord)
		{
			this.endOfWord = endOfWord;
		}

		/*  
		 *  Return the prefix (as a String) associated with this node.  This prefix
		 *  is defined by descending from the root to this node.  However, you will
		 *  find it is easier to implement by ascending from the node to the root,
		 *  composing the prefix string from its last character to its first.  
		 *
		 *  This overrides the default toString() method.
		 */

		public String toString()
		{
			// ADD YOUR CODE BELOW HERE

			String pre="";
			WordTreeNode cur=this;
			//assume that there will be no word exceeding 1000 charactors, which is quite reasonable
			char[] word = new char[1000];
			int i =0;
			//use the depth to get to the root from the node
			while (cur.depth!=0) {
				word[i]=cur.charInParent;
				cur=cur.parent;
				i++;
			}
			//the stored word is in the inverse order, so we need to reverse the word
			i=i-1;
			while(i>=0) {
				pre=pre+word[i];
				i--;
			}
			return pre;
			
			// ADD YOUR CODE ABOVE HERE
		}
	}

}

