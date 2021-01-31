public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextLast = 0;
    private int nextFirst = 7;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    private void reSize() {
        T[] a = (T[]) new Object[items.length * 2];
        System.arraycopy(items, 0, a, 0, items.length);
        items = a;
        nextFirst += items.length / 2;
        nextLast += items.length / 2;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            reSize();
        }

        if (nextFirst == -1) {
            nextFirst = items.length - 1;
        } else if (nextLast == nextFirst) {
            reSize();
        } else {
            nextFirst -= 1;
        }

        items[nextFirst] = item;
        size += 1;
    }

    public void addLast(T item){
        if(nextLast == nextFirst){
            reSize();
        }
        if(nextLast > items.length -1){
            reSize();
        }
        else{
            nextLast += 1;
        }

        items[nextLast] = item;
        size += 1;
    }

    public boolean isEmpty() {
        if(size == 0)
            return true;
        else
            return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for(T item : items){
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public T removeFirst(){
        if(items[0] == null)
            return null;
        else
        {
            T x= items[0];
            items[0] = null;
            size--;
            return x;
        }
    }

    public T removeLast(){
        if(items[size - 1] == null){
            return null;
        }

        else
        {
            T x = items[size -1];
            items[size -1] = null;
            size -= 1;
            return x;
        }
    }

    public T get(int index){
        return items[index];
    }


}
