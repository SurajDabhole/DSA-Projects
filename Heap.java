import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {
    private ArrayList<T> list;

    public Heap() {
        list = new ArrayList<>();
    }

    public void insert(T value) {
        list.add(value);
        upheap(list.size() - 1);
    }

    public int size() {
        return list.size();
    }

    private void upheap(int index) {
        if (index == 0) return;

        int parentIndex = parent(index);
        if (list.get(index).compareTo(list.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            upheap(parentIndex);
        }
    }

    public T remove() throws Exception {
        if (list.isEmpty()) throw new Exception("Heap is empty.");
        T root = list.get(0);
        T last = list.remove(list.size() - 1);
        if (!list.isEmpty()) {
            list.set(0, last);
            downheap(0);
        }
        return root;
    }

    private void downheap(int index) {
        int left = left(index), right = right(index), smallest = index;

        if (left < list.size() && list.get(left).compareTo(list.get(smallest)) < 0)
            smallest = left;
        if (right < list.size() && list.get(right).compareTo(list.get(smallest)) < 0)
            smallest = right;

        if (smallest != index) {
            swap(index, smallest);
            downheap(smallest);
        }
    }

    private void swap(int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }
}
