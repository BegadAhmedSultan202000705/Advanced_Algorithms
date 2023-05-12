package submisson;

public class TwoColorDoubleStack<T> {
    private T[] arr;
    private int redTop;
    private int blueTop;

    @SuppressWarnings("unchecked")
	public TwoColorDoubleStack(int capacity) {
        arr = (T[]) new Object[capacity];
        redTop = -1;
        blueTop = capacity;
    }

    public void redPush(T item) {
        if (redTop + 1 >= blueTop) {
            throw new IllegalStateException("Stack overflow");
        }
        arr[++redTop] = item;
    }

    public T redPop() {
        if (redTop == -1) {
            throw new IllegalStateException("Red stack underflow");
        }
        return arr[redTop--];
    }

    public void bluePush(T item) {
        if (blueTop - 1 <= redTop) {
            throw new IllegalStateException("Stack overflow");
        }
        arr[--blueTop] = item;
    }

    public T bluePop() {
        if (blueTop == arr.length) {
            throw new IllegalStateException("Blue stack underflow");
        }
        return arr[blueTop++];
    }

    public static void main(String[] args) {
        TwoColorDoubleStack<Integer> stack = new TwoColorDoubleStack<>(10);
        stack.redPush(1);
        stack.bluePush(2);
        stack.redPush(3);
        stack.bluePush(4);
        System.out.println(stack.redPop()); // 3
        System.out.println(stack.bluePop()); // 4
        System.out.println(stack.redPop()); // 1
        System.out.println(stack.bluePop()); // 2
    }
}