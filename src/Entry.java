

    // internal nodes: only use key and next
    // external nodes: only use key and value
    public class Entry {
        private Comparable key;
        private final Object val;
        private Node next;     // helper field to iterate over array entries
        public Entry(Comparable key, Object val, Node next) {
            this.setKey(key);
            this.val  = val;
            this.setNext(next);
        }
		public Comparable getKey() {
			return key;
		}
		public void setKey(Comparable key) {
			this.key = key;
		}
		public Object getVal() {
			return val;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
    }
