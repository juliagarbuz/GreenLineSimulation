/*
 * Julia Garbuz
 */

public class MyArrayList {
    Object[] olist;
    final int SIZE = 2;
    int ptr = -1;
    int elements = 0;
    public MyArrayList()
    {
        olist = new Object[SIZE];
    }

    private Object[] expand(Object[] objects)
    {
        Object[] temp = new Object[objects.length*2];
        for(int i = 0; i < objects.length; i++)
            temp[i] = objects[i];

        return temp;
    }

    public boolean add(Object o)
    {
        //System.out.println("PTR IN ADD: " + ptr);
        if(o == null)
        {
            //System.out.println(">> add() << null object");
            return false;
        }
        if(isFull(olist) == true)
        {
            olist = expand(olist);
            ptr++;
            olist[ptr] = o;
            elements++;
            //System.out.println(">> add() << full list");
            return true;
        }
        else
        {
            ptr++;
            olist[ptr] = o;
            elements++;
            //System.out.println(">> add() << just plain add");
            return true;
        }
    }
    // Inserts the given object at the given index.
    // Shifts the element at the specified index and any subsequent elements (if any) towards the end of the list
    // So if you have a list of size n, you should be able to insert any object from indices 0 <= index < n.
    // The list should be able to grow if need be.
    // If an index is given not in the range 0 <= index < n, return false. If the given object is null, return false.


    public boolean insert(int index, Object o)
    {
        int tptr = 0;

        //if the index is not in the given range or the given object is null, return false
        if(index > olist.length || o.equals(null)) {
            //System.out.println(">> insert() << Invalid index OR null object");
            return false;
        }

        if(isFull(olist) == true) {
            //System.out.println(">> insert() << LIST FULL –> expanded");
            olist = expand(olist);
            //System.out.println("NEW LENGTH: " + olist.length);
        }

        if(olist[index] == null) {
            //System.out.println(">> insert() << desired index was already null –> just insert");
            olist[index] = o;
        }

        else
        {
            //System.out.println(">> insert() << ELSE");
            for (int i = 0; i < olist.length; i++){
                if (olist[i] == null){
                    ptr = i-1;  // ptr assigned to index of last not-null object
                    tptr = i;   //temp pointer assigned to index of first null
                    break;
                }
            }

            //System.out.println("ptr: " + ptr);
            //System.out.println("tptr: " + tptr);

            if (index == tptr) {    // already inserting at end of list
                //System.out.println(">> insert() << index is end of list (just insert)");
                ptr = tptr;
                olist[index] = o;
            }

            else{
                for (int z = ptr; z >= index; z--){
                    //System.out.println("olist[z+1]: " + olist[z+1] + "\tolist[z]: " + olist[z]);
                    olist[z+1] = olist[z];
                }
                olist[index] = o;
                ptr++;
            }
        }
        elements++;
        return true;
    }

    public void clear()
    {
        olist = new Object[SIZE];
    }

    public boolean contains(Object o)
    {
        if(o == null) {
            return false;
        }
        else
        {
            for(int i = 0; i < elements; i++)
            {
                if(o.equals(olist[i]))
                    return true;
            }
            return false;
        }
    }

    // Returns the object at a given index. Returns null if index is out of bounds.

    public Object get(int index)
    {
        if (index < olist.length)
        {
            return olist[index];
        }
        else
        {
            return null;
        }
    }

    // Returns the index of the input object in the list. Return -1 if the object is not found or is null.

    public int indexOf(Object o)
    {
        if (o == null){
            return -1;
        }

        for(int i = 0; i < olist.length; i++)
        {
            if(o.equals(olist[i])) {
                return i;
            }
        }
        return -1;
    }

    // Returns true if the list is empty and false otherwise.

    public boolean isEmpty()
    {
        for(int i = 0; i < olist.length; i++)
        {
            if(olist[i] != null)
                return false;
        }
        return true;
    }

    private void shift(int r){
        for (int i = r; i < (olist.length-1); i++){
            olist[i] = olist[i+1];
            olist[i+1] = null;
        }
    }

    // Removes and returns object at the given index.

    public Object remove(int index)
    {
        Object o = olist[index];
        olist[index] = null;
        shift(index);
        for (int i = 0; i < olist.length; i++){
            if (olist[i] == null){
                ptr = i-1;
                //System.out.println("PTR IN REMOVE: " + ptr + " (index: " + i + ")");
                break;
            }
        }
        return o;
    }

    // Removes the first instance of the input object from the list.
    // Return true if successful and false if the object is not found.

    public boolean remove(Object o)
    {
        if (size() == 0){
            return false;
        }
        for(int i = 0; i < olist.length; i++)
        {
            if(olist[i].equals(o))
            {
                olist[i] = null;
                shift(i);
                for (int j = 0; j < olist.length; j++){
                    if (olist[j] == null){
                        ptr = j-1;
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }

    // Replaces the object at the given instance with the given object.
    // If the index is out of bounds or the object is null, do nothing.

    public void set(int index, Object o)
    {
        try
        {
            olist[index] = o;
        }
        catch( ArrayIndexOutOfBoundsException e)
        {

        }
    }

    // Returns the size of the list.

    public int size()
    {
        int i = 0;
        while (get(i)!=null){
            i++;
        }
        return i;
    }

    private boolean isFull(Object[] li){
        for (int i = 0; i < li.length; i++){
            if (li[i] == null){
                return false;
            }
        }
        return true;
    }

}
