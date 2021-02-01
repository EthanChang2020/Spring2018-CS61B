
public class LinkedListDeque<T> {
    private int size;
    private stuffNode sentinel;

    private class stuffNode{
        private T item;
        stuffNode prev;
        stuffNode next;
        public stuffNode(T item,stuffNode prev,stuffNode next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    //empty LinkedListDeque
    public LinkedListDeque(){
        sentinel = new stuffNode(null,null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }


    //return true if LinkedListDeque is empty
    public boolean isEmpty(){
        if(sentinel == sentinel.prev && sentinel == sentinel.next){
            return true;
        }
        else
        {
            return false;
        }
    }

    public int size(){
        return size;
    }

    public void addFirst(T item){
        sentinel.next = new stuffNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item){
        stuffNode p = sentinel;
        while(p.next != sentinel.next){
            p = p.next;
        }
        p.next = new stuffNode(item, p, sentinel);
        sentinel.prev = p.next;
        size += 1;
    }

    public void printDeque(){
        stuffNode p = sentinel.next;
        while(p.next != sentinel){
            System.out.print(p.item + " ");
        }
        System.out.print(p.item + " ");
    }

    public T removeFirst(){
        if(sentinel.next == sentinel && sentinel == sentinel.prev){
            return null;
        }
        else
        {
            T result = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            size -= 1;
            
            if(size == 0){
                sentinel.prev = sentinel;
                sentinel.next = sentinel;
                return result;
            }

            sentinel.next.prev = sentinel;
            return result;
        }
    }

    public T removeLast() {
        if(sentinel.next == sentinel && sentinel == sentinel.prev) {
            return null;
        }
        else {
            stuffNode p = sentinel;
            while(p.next != sentinel) {
                p = p.next;
            }
            p.prev.next = sentinel;
            sentinel.prev = p.prev;
            return p.prev.item;
        }
    }

    public T get(int index){
        int count = 0;
        if(index > size){
            return null;
        }
        stuffNode p = sentinel;
        while(count != index){
            p = p.next;
            count++;
        }
        return p.item;
    }

    //************************Implement Recursively**********************


    public T getRecursive(int index){
            return getRecursiveHelper(sentinel, index);
    }

    private T getRecursiveHelper(stuffNode p, int index) {
        if (index == 0) {
            return p.item;
        } else {
            p = p.next;
            return getRecursiveHelper(p, index - 1);
        }
    }


}
