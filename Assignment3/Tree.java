package assignments2017.a3posted;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import assignments2017.a3posted.WordTree.WordTreeNode;

public class Tree {
	
	public Tree() 
	{
		root = new TreeNode();
	}
	
	TreeNode root;

	// Empty tree has just a root node.  All the children are null.
	TreeNode getPrefixNode(String word)
	{
		//   ADD YOUR CODE BELOW HERE
		int length = word.length();
		TreeNode level = this.root;
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
	
	
	
	public void insert(String word)
	{
		//  ADD YOUR CODE BELOW HERE
		Tree tree=this;
		String prefix = tree.getPrefixNode(word).toString();
		int j=0;
		
		if (prefix.length()==0) {
			while(j<word.length()) {
			tree.getPrefixNode(word).createChild(word.charAt(j));
			j++;
		}
		}
		else {
		int i = prefix.length();
		while (i<word.length()) {
			tree.getPrefixNode(word).createChild(word.charAt(i));
			i++;
		}
		}
		tree.getPrefixNode(word).endOfWord=true;
		
		//  ADD YOUR ABOVE HERE
	}
	
	
	
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
	
	public ArrayList <String> getListPrefixMatches( String prefix )
	{
		//  ADD YOUR CODE BELOW 
		
		
		
		ArrayList<String> word = new ArrayList<String>();
		Stack<TreeNode> store = new Stack<TreeNode>();
		TreeNode cur = getPrefixNode(prefix);
		
		
	  if (cur.parent == null && prefix.length()!=0) {
	   return word;
	  }
	  
	  store.push(cur);
	  while (!store.isEmpty()) {
	   cur = store.peek();
	   if (cur.endOfWord) {
	    word.add(cur.toString());
	   }
	   store.pop();
	   
	   
	   for (int i = 0; i <256; i++) {
	    if (cur.children[i]!=null) {
	     store.push(cur.children[i]);
	    }
	   }
	  }
	  return word;  
/*		ArrayList<String> all = new ArrayList <String>();
		TreeNode node=this.getPrefixNode(prefix);
		all	.add(node.toString());
		
		for (char i =0;i<256;i++) {
			if (node.getChild(i)!=null) {
				all.add(node.getChild(i).toString());
			
			TreeNode node2=node.getChild(i);
				for (char j =0;j<256;j++) {
					if (node2.getChild(j)!=null) {
						all.add(node2.getChild(j).toString());
						
						TreeNode node3=node2.getChild(j);
						for (char k =0;k<256;k++) {
							if (node3.getChild(k)!=null) {
								all.add(node3.getChild(k).toString());
							}
						}
						
							
					}
				}
			
			
			}
			
			
		}
		
		return all;*/
		
			
		
		
		  //  REMOVE THIS STUB
		
		//  ADD YOUR CODE ABOVE HERE
	}
	
	
	
	public void loadWords(ArrayList<String> words)
	{
		for (int i = 0; i < words.size(); i++)
		{
			
			insert(words.get(i));

		}
		return;
	}
	
	
	
	public static void main(String[] args) {
	Tree test = new Tree();
	TreeNode last,another;
	//test.insert("bag");
	//test.insert("baby");
	//test.insert("bat");
	//test.insert("bab");
	test.insert("door");
	test.insert("dot");
	test.insert("dat");
	test.insert("dett");
//	System.out.println(test.getPrefixNode("attackkk").isEndOfWord());
//	System.out.println(test.contains("baby"));
	System.out.println(test.getPrefixNode("do").toString().equals("do"));
	System.out.println(test.getPrefixNode("dett").isEndOfWord());
	System.out.println(test.getPrefixNode("dot"));
	System.out.println(test.contains("door"));
	
	
	System.out.println(test.getListPrefixMatches("do"));

	/*ArrayList<String> list = new ArrayList<String>();
	Collections.addAll(list, "a", "and", "ax", "dog", "door", "dot");
	System.out.println(list);
	Tree   WordTree = new Tree();
	WordTree.loadWords(list);
	

	System.out.println("WordTree contains 'door' = " + WordTree.contains("door") + " (true)" );
	System.out.println("WordTree contains 'and' = " + WordTree.contains("and")   + " (true)");
	System.out.println("WordTree contains 'cat' = " + WordTree.contains("cat")   + " (false)");
	System.out.println("WordTree contains 'dog' = " + WordTree.contains("dog")   + " (true)");
	System.out.println("WordTree contains 'ax' = " + WordTree.contains("ax")     + " (true)");
	System.out.println("WordTree contains 'dot' = " + WordTree.contains("dot")   + " (true)");
	System.out.println("WordTree contains 'a' = " + WordTree.contains("a")       + " (true)");
	System.out.println("WordTree contains 'an' = " + WordTree.contains("an")     + " (false)");      
	
	
	
	*/
	
		
	}
	
	
	public class TreeNode
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

		TreeNode     parent;
		TreeNode[]   children;
		int              depth;            // 0 for root, 1 for root's children, 2 for their children, etc..
		
		char             charInParent;    // Character associated with the tree edge from this node's parent 
		                                  // to this node.
		// See comment above for relationship between an index in 0 to 255 and a char value.
		
		boolean endOfWord;   // Set to true if prefix associated with this node is also a word.

		
		// Constructor for new, empty node with NUMCHILDREN children.  
		//  All the children are automatically initialized to null. 

		public TreeNode()
		{
			children = new TreeNode[NUMCHILDREN];
			
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
		
		public TreeNode createChild(char  c) 
		{	   
			TreeNode child       = new TreeNode();

			// ADD YOUR CODE BELOW HERE
			
			child.parent=this;
			this.children[c]=child;
			child.depth=this.depth+1;
			child.charInParent=c;
			child.endOfWord = false;
			
			// ADD YOUR CODE ABOVE HERE

			return child;
		}

		// Get the child node associated with a given character, i.e. that character is "on" 
		// the edge from this node to the child.  The child could be null.  

		public TreeNode getChild(char c) 
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
			TreeNode cur=this;
			char[] word = new char[1000];
			int i =0;
			while (cur.depth!=0) {
				word[i]=cur.charInParent;
				cur=cur.parent;
				i++;
			}
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
