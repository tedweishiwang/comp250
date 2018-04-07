//name: Weishi Wang
//ID  : 260540022

package hashMap;


public class HashLinkedList<K,V>{
	/*
	 * Fields
	 */
	private HashNode<K,V> head;

	private Integer size;

	/*
	 * Constructor
	 */

	HashLinkedList(){
		head = null;
		size = 0;
	}


	/*
	 *Add (Hash)node at the front of the linked list
	 */

	public void add(K key, V value){
		// ADD CODE BELOW HERE
		HashNode<K,V> newNode = new HashNode<K,V> (key, value);
		size++;
		if(head == null) {
			head = newNode;
		}
		else {
			newNode.next = head;
			head = newNode;
		}

		// ADD CODE ABOVE HERE
	}

	/*
	 * Get Hash(node) by key
	 * returns the node with key
	 */

	public HashNode<K,V> getListNode(K key){
		// ADD CODE BELOW HERE
		HashNode<K,V> cur = head;
		if(head==null) {
			return null;
		}
		else {
		while(!cur.getKey().equals(key)) {
			cur=cur.next;
			if(cur==null) break;
		}
		}
		
		return cur;

		// ADD CODE ABOVE HERE
	}


	/*
	 * Remove the head node of the list
	 * Note: Used by remove method and next method of hash table Iterator
	 */

	public HashNode<K,V> removeFirst(){
		// ADD CODE BELOW HERE
		if (head==null) {
			return null;
		}
		else {
		HashNode<K,V> cp = head.clone();
		head=head.next;
		this.size--;
		return cp;
		}
		// ADD CODE ABOVE HERE
	}

	/*
	 * Remove Node by key from linked list 
	 */

	public HashNode<K,V> remove(K key){
		// ADD CODE BELOW HERE
		HashNode<K,V> cur = head;
		if(cur==null) {
			return null;
		}
		else if(cur.getKey().equals(key)) {
			head = head.next;
			size--;
			return cur;
		}
		while(cur.next!=null){
			if(cur.next.getKey().equals(key)) {
				HashNode<K,V> cp = cur.next.clone();
				cur.next=cur.next.next;
				size--;
				return cp;
			}
			else {
				cur=cur.next;
			}
			
		}
		// ADD CODE ABOVE HERE
		return null; // removing failed
	}



	/*
	 * Delete the whole linked list
	 */
	public void clear(){
		head = null;
		size = 0;
	}
	/*
	 * Check if the list is empty
	 */

	boolean isEmpty(){
		return size == 0? true:false;
	}

	int size(){
		return this.size;
	}

	//ADD YOUR HELPER  METHODS BELOW THIS
	public HashNode<K,V> getFirst(){
		if (head==null) {
			return null;
		}
		else {
		return head;
		}
	}

	//ADD YOUR HELPER METHODS ABOVE THIS


}
