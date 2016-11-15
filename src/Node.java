
public class Node {
	 private static final int M = 4;
	 public int m;                             // number of children
     private Entry[] children = new Entry[M];   // the array of children

     // create a node with k children
     public Node(int k) {
         m = k;
     }

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public Entry[] getChildren() {
		return children;
	}

	public void setChildren(Entry[] children) {
		this.children = children;
	}
     
}
