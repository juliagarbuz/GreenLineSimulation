/*
 * Julia Garbuz
 */
public class MyNodeList implements MyList{
    N head;
    N i;
    int length = 0;
    public MyNodeList(){
        head = new N("HEADER");
        i = head;
    }

    public boolean add(Object o) {
        N n = new N(o);
        i.setNext(n);
        i = i.getNext();
        length++;
        return true;
    }
    public boolean insert(int index, Object o) {
        if (index < 0 || index > length) {
            return false;
        }
        length++;
        int count = 0;
        i = head;
        while (count < index) {
            i = i.getNext();
            count++;
        }
        N n = new N(o, i.getNext());
        i.setNext(n);
        while (i.getNext()!= null) {
            i = i.getNext();
        }
        return true;
    }
    public void clear() {
        head.setNext(null);
    }

    public boolean contains(Object o) {
        i = head;
        boolean contain = false;
        while (i.getNext()!= null) {
            i = i.getNext();
            if (i.getData().equals(o)) {
                contain = true;
            }
        }
        return contain;
    }
    public Object get(int index) {
        if (index >= length || index < 0) {
            return null;
        }
        else {
            i = head.getNext();
            int count = 0;
            while (count < index) {
                i = i.getNext();
                count ++;
            }
            if (i == null){
                return null;
            }
            return i.getData();
        }
    }
    public int indexOf(Object o) {

        if (!contains(o)) {
            return -1;
        }
        else {
            i = head.getNext();
            int count = 0;
            while (i.getData() != o) {
                count ++;
                i = i.getNext();
            }
            return count;
        }

    }
    public boolean isEmpty() {
        if (head.getNext() == null) {
            return true;
        }
        else {
            return false;
        }
    }
    public Object remove(int index) {
        if (!(index > length || index<0) ) {
            Object temp = get(index);
            i = head.getNext();
            int count = 0;
            if (index == 0) {
                head.setNext(i.getNext());
                length-=1;
                return temp;
            }
            else {
                while (count<index-1) {
                    count++;
                    i = i.getNext();
                }
                i.setNext(i.getNext().getNext());
                length-=1;
                return temp;
            }
        }
        else {
            return null;
        }
    }
    public boolean remove(Object o) {
        if (!contains(o)) {
            return false;
        }
        else {
            remove(indexOf(o));
            return true;
        }
    }
    public void set(int index, Object o) {
        int count = 0;
        i = head.getNext();
        while (count<index) {
            i = i.getNext();
            count++;
        }
        i.setData(o);
    }
    public int size() {
        return length;
    }

}
