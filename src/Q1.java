// Queue implementation using a linked list of nodes (see N.java)
// Posted previously, but used for simulation

// (class code)

public class Q1 implements Q { //From CSCI1933 Spring 2016 Moodle

    // constructor

    public Q1() {}

    // selectors

    public void add(Object o) {

        if (size == 0) {
          front = new N(o, null);
          rear = front;
        }
        else {
          rear.setNext(new N(o, null));
          rear = rear.getNext();
        }
        size++;
    }

    public Object remove() {

        Object answer;

        if (size == 0)
          return null;
        
        answer = front.getData();
        front = front.getNext();
        size--;
        if (size == 0)
          rear = null;
        return answer;
    }

    public int length() {
        return size;
    }

    public boolean isEmpty(){
        if (length() == 0){return true;}
        return false;
    }

    private int size;
    private N front;
    private N rear;

}  // Q1 class

