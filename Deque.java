import java.util.Iterator;
import java.util.NoSuchElementException;

// 雙端佇列 Deque，支援兩端插入與移除
public class Deque<Item> implements Iterable<Item> {
    // 內部 Node 節點類別
    private class Node {
        Item item;    // 實際存放的資料
        Node next;    // 指向下一個節點
        Node prev;    // 指向上一個節點
    }

    private Node first; // 指向佇列頭部
    private Node last;  // 指向佇列尾部
    private int size;   // 當前佇列長度

    // 建構子：初始化為空佇列
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // 判斷佇列是否為空
    public boolean isEmpty() {
        return size == 0;
    }

    // 回傳佇列長度
    public int size() {
        return size;
    }

    // 在頭部加入元素
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("傳入參數不能為 null");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        if (isEmpty()) {
            last = first; // 若原本是空佇列，尾指標也要指向新節點
        } else {
            oldFirst.prev = first;
        }
        size++;
    }

    // 在尾部加入元素
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("傳入參數不能為 null");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        if (isEmpty()) {
            first = last; // 若原本是空佇列，頭指標也要指向新節點
        } else {
            oldLast.next = last;
        }
        size++;
    }

    // 移除並回傳頭部元素
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("佇列為空，無法移除");
        Item item = first.item;
        first = first.next; // 把頭指標往後移
        size--;
        if (isEmpty()) {
            last = null; // 若刪完變空，尾指標也設 null
        } else {
            first.prev = null; // 新頭節點的 prev 要設為 null
        }
        return item;
    }

    // 移除並回傳尾部元素
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("佇列為空，無法移除");
        Item item = last.item;
        last = last.prev; // 把尾指標往前移
        size--;
        if (isEmpty()) {
            first = null; // 若刪完變空，頭指標也設 null
        } else {
            last.next = null; // 新尾節點的 next 要設為 null
        }
        return item;
    }

    // 疊代器（支援 for-each 迴圈）
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node current = first;

            public boolean hasNext() {
                return current != null;
            }

            public Item next() {
                if (!hasNext()) throw new NoSuchElementException("沒有更多元素");
                Item item = current.item;
                current = current.next;
                return item;
            }

            public void remove() {
                throw new UnsupportedOperationException("不支援 remove 操作");
            }
        };
    }

    // 主程式：簡單單元測試
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        // 測試 addFirst / addLast
        deque.addFirst(1); // [1]
        deque.addLast(2);  // [1,2]
        deque.addFirst(0); // [0,1,2]
        System.out.println("Deque 現在長度: " + deque.size()); // 3

        // 測試 iterator
        System.out.print("Deque 內容: ");
        for (int x : deque) System.out.print(x + " ");
        System.out.println();

        // 測試 removeFirst / removeLast
        System.out.println("移除頭: " + deque.removeFirst()); // 0
        System.out.println("移除尾: " + deque.removeLast());  // 2
        System.out.println("剩下: " + deque.removeFirst());   // 1

        // 再測 isEmpty
        System.out.println("Deque 是否為空: " + deque.isEmpty());

        // 測試例外狀況
        try {
            deque.removeFirst();
        } catch (NoSuchElementException e) {
            System.out.println("removeFirst() on empty: 通過");
        }

        try {
            deque.addFirst(null);
        } catch (IllegalArgumentException e) {
            System.out.println("addFirst(null): 通過");
        }

        // 測試 iterator 例外
        Deque<String> d2 = new Deque<>();
        d2.addLast("A");
        Iterator<String> it = d2.iterator();
        it.next();
        try {
            it.next();
        } catch (NoSuchElementException e) {
            System.out.println("iterator.next() on end: 通過");
        }
        try {
            it.remove();
        } catch (UnsupportedOperationException e) {
            System.out.println("iterator.remove(): 通過");
        }
    }
}
