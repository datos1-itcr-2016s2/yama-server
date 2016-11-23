

    // internal nodes: only use content and next
    // external nodes: only use content and route
    public class Entry {
        private Comparable content;
        public String[] route;
        public int time;
        private Node next;     // helper field to iterate over array entries
        public Entry(Comparable content, String[] route, int time, Node next) {
            this.setContent(content);
            this.route  = route;
            this.time = time;
            this.setNext(next);
        }
		public Comparable getContent() {
			return content;
		}
		public String getRoute() {
			String result = "[";
			for(int i=0; i<route.length; i++){
				result += route[i];
				if(i != route.length-1){
					result += ", ";
				}
				else{result += ']';}
			}
			return result;
		}
		public String getTime() {
			return time + "s";
		}
		public void setContent(Comparable content) {
			this.content = content;
		}
		public Object getroute() {
			return route;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
    }
