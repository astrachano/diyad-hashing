

public class LinkHashStringSet {
    private static int SIZE = 100000;
    
    private class Node{
        String info;
        Node next;
        public Node(String key){
            this.info = key;
            this.next = null;
        }
    }
    private Node[] myTable;
    private int mySize;
    
    public LinkHashStringSet(){
        myTable = new Node[SIZE];
    }
    
    private int getHash(String key){
        return Math.abs(key.hashCode()) % SIZE;
    }
    
    private Node getNode(String key){
        int bucketIndex = getHash(key);
        Node list = myTable[bucketIndex];
        while (list != null){
            if (list.info.equals(key)) return list;
            list = list.next;
        }
        return null;
    }
    public boolean contains(String key) {
        return getNode(key) != null;
    }

    public boolean add(String key) {
        int bucketIndex = getHash(key);
        Node list = myTable[bucketIndex];
        while (list != null){
            if (list.info.equals(key)) {
                return false;
            }
            list = list.next;
        }
        list = new Node(key);
        list.next = myTable[bucketIndex];
        myTable[bucketIndex] = list;
        mySize++;
        return true;
    }

    public int size() {
        return mySize;
    }
    public void clear(){
        myTable = new Node[SIZE];
    }

    public boolean remove(Object o) {
    		int index = getHash((String) o);
    		
    		Node list = myTable[index];
    		
    		if (list == null) return false;
    		
    		// check first node as a special case
    		// has nothing before it
    		if (list.info.equals(o)) {
    			myTable[index] = myTable[index].next;
    			return true;
    		}
    		while (list.next != null) {
    			if (list.next.info.equals(o)) {
    				list.next = list.next.next;
    				return true;
    			}
    			list = list.next;
    		}  		
    		return false;
    }
}
