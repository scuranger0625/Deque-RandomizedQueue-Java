import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;  // 課程提供的亂數工具

// 隨機佇列：每次移除/取樣都隨機公平
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array; // 動態陣列存放資料
    private int size;     // 當前元素個數

    // 建構子：初始化空陣列，初始容量設為 2
    public RandomizedQueue() {
        array = (Item[]) new Object[2];
        size = 0;
    }

    // 判斷是否為空
    public boolean isEmpty() {
        return size == 0;
    }

    // 回傳目前元素數量
    public int size() {
        return size;
    }

    // 新增元素（加到陣列尾端）
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("不能加入 null 元素！");
        if (size == array.length) resize(2 * array.length); // 滿了自動擴容
        array[size++] = item;
    }

    // 隨機移除一個元素並回傳
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("隨機佇列為空，無法移除");
        int idx = StdRandom.uniform(size); // 用 StdRandom 產生 [0, size) 的亂數
        Item item = array[idx];
        array[idx] = array[size - 1]; // 用最後一個元素覆蓋抽中的位置
        array[size - 1] = null; // 避免 loitering
        size--;
        // 如果剩下的資料過少，自動縮容
        if (size > 0 && size == array.length / 4) resize(array.length / 2);
        return item;
    }

    // 隨機取樣一個元素（不移除）
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("隨機佇列為空，無法取樣");
        int idx = StdRandom.uniform(size); // 用 StdRandom 產生 [0, size) 的亂數
        return array[idx];
    }

    // 調整動態陣列大小
    private void resize(int capacity) {
        Item[] newArr = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArr[i] = array[i];
        }
        array = newArr;
    }

    // 疊代器：每次產生一個獨立亂序的疊代器
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private final int[] order; // 洗牌後的 index 順序
            private int current = 0;

            {
                // 構造時產生一份隨機順序（StdRandom 洗牌）
                order = new int[size];
                for (int i = 0; i < size; i++) order[i] = i;
                StdRandom.shuffle(order); // 用 StdRandom 來洗牌
            }

            public boolean hasNext() {
                return current < order.length;
            }

            public Item next() {
                if (!hasNext()) throw new NoSuchElementException("沒有更多元素");
                return array[order[current++]];
            }

            public void remove() {
                throw new UnsupportedOperationException("不支援 remove 操作");
            }
        };
    }

    // 主程式：簡易單元測試
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();

        // 測試 enqueue
        rq.enqueue("A");
        rq.enqueue("B");
        rq.enqueue("C");
        rq.enqueue("D");
        System.out.println("size: " + rq.size());

        // 測試 sample（不移除）
        System.out.println("sample: " + rq.sample());

        // 測試 dequeue
        System.out.print("dequeue 順序: ");
        while (!rq.isEmpty()) {
            System.out.print(rq.dequeue() + " ");
        }
        System.out.println();

        // 測試異常狀況
        try {
            rq.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("dequeue 空佇列：通過");
        }

        try {
            rq.sample();
        } catch (NoSuchElementException e) {
            System.out.println("sample 空佇列：通過");
        }

        try {
            rq.enqueue(null);
        } catch (IllegalArgumentException e) {
            System.out.println("enqueue(null)：通過");
        }

        // 測試 iterator 洗牌效果與例外
        rq.enqueue("X");
        rq.enqueue("Y");
        rq.enqueue("Z");
        Iterator<String> it1 = rq.iterator();
        Iterator<String> it2 = rq.iterator();
        System.out.print("it1: ");
        while (it1.hasNext()) System.out.print(it1.next() + " ");
        System.out.println();
        System.out.print("it2: ");
        while (it2.hasNext()) System.out.print(it2.next() + " ");
        System.out.println();
        try {
            it1.next();
        } catch (NoSuchElementException e) {
            System.out.println("iterator.next() 無元素：通過");
        }
        try {
            it1.remove();
        } catch (UnsupportedOperationException e) {
            System.out.println("iterator.remove()：通過");
        }
    }
}
