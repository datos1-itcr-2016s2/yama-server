
public class BTree<Content extends Comparable<Content>>  {
    // max children per B-tree node = M-1
    // (must be even and greater than 2)
    private static final int M = 4;

    private Node root;       // root of the B-tree
    private int height;      // height of the B-tree
    private int n;           // number of key-value pairs in the B-tree
    
    
    public BTree() {
        root = new Node(0);
    }
 
    /**
     * Returns true if this symbol table is empty.
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    /**
     * Returns the height of this B-tree (for debugging).
     *
     * @return the height of this B-tree
     */
    public int height() {
        return height;
    }


    /**
     * Returns the value associated with the given key.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public String getMessageData(Content content) {
        if (content == null) throw new IllegalArgumentException();
        return search(root, content, height);
    }

    private String search(Node x, Content content, int ht) {
    	String result = "";
        Entry[] children = x.getChildren();

        // external node
        if (ht == 0) {
            for (int j = 0; j < x.m; j++) {
                if (eq(content, children[j].getContent())) 
                	result += "Message: " + children[j].getContent() +"\nRoute: "  + children[j].getRoute()  + "\nTime: " + children[j].getTime();
                	return result;
            }
        }

        // internal node
        else {
            for (int j = 0; j < x.m; j++) {
                if (j+1 == x.m || less(content, children[j+1].getContent()))
                    return search(children[j].getNext(), content, ht-1);
            }
        }
        return null;
    }

    public void putMessage(Entry message){
    	Content cont = (Content) message.getContent();
    	put(cont, message.route, message.time);
    }

    /**
     * Inserts the key-value pair into the symbol table, overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is {@code null}, this effectively deletes the key from the symbol table.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Content content, String[] route, int time) {
        if (content == null) throw new IllegalArgumentException();
        Node u = insert(root, content, route, time, height); 
        n++;
        if (u == null) return;

        // need to split root
        Node t = new Node(2);
        t.getChildren()[0] = new Entry(root.getChildren()[0].getContent(), null, 0, root);
        t.getChildren()[1] = new Entry(u.getChildren()[0].getContent(), null, 0,  u);
        root = t;
        height++;
    }

    private Node insert(Node h, Content content, String[] route, int time, int ht) {
        int j;
        Entry t = new Entry(content, route, time, null);

        // external node
        if (ht == 0) {
            for (j = 0; j < h.m; j++) {
                if (less(content, h.getChildren()[j].getContent())) break;
            }
        }

        // internal node
        else {
            for (j = 0; j < h.m; j++) {
                if ((j+1 == h.m) || less(content, h.getChildren()[j+1].getContent())) {
                    Node u = insert(h.getChildren()[j++].getNext(), content, route, time, ht-1);
                    if (u == null) return null;
                    t.setContent(u.getChildren()[0].getContent());
                    t.setNext(u);
                    break;
                }
            }
        }

        for (int i = h.m; i > j; i--)
            h.getChildren()[i] = h.getChildren()[i-1];
        h.getChildren()[j] = t;
        h.m++;
        if (h.m < M) return null;
        else         return split(h);
    }

    // split node in half
    private Node split(Node h) {
        Node t = new Node(M/2);
        h.m = M/2;
        for (int j = 0; j < M/2; j++)
            t.getChildren()[j] = h.getChildren()[M/2+j]; 
        return t;    
    }

    /**
     * Returns a string representation of this B-tree (for debugging).
     *
     * @return a string representation of this B-tree.
     */
    public String toString() {
        return toString(root, height, "") + "\n";
    }

    private String toString(Node h, int ht, String indent) {
        StringBuilder s = new StringBuilder();
        Entry[] children = h.getChildren();

        if (ht == 0) {
            for (int j = 0; j < h.m; j++) {
                s.append(indent + children[j].getContent() + " " + children[j].getRoute() +  " " + children[j].getTime() + "\n" );
            }
        }
        else {
            for (int j = 0; j < h.m; j++) {
                if (j > 0) s.append(indent + "(" + children[j].getContent() + ")\n");
                s.append(toString(children[j].getNext(), ht-1, indent + "     "));
            }
        }
        return s.toString();
    }


    // comparison functions - make Comparable instead of Key to avoid casts
    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

    private boolean eq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }


    }
