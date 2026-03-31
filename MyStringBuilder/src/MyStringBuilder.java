import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MyStringBuilder {
    private static final int DEFAULT_CAPACITY = 16;

    private char[] buffer;
    private int length;

    private final Deque<Snapshot> history;

    public MyStringBuilder() {
        this.buffer = new char[DEFAULT_CAPACITY];
        this.length = 0;
        this.history = new ArrayDeque<>();
    }

    public MyStringBuilder append(String value) {
        String safeValue = String.valueOf(value);
        saveSnapshot();
        ensureCapacity(length + safeValue.length());

        for (int i = 0; i < safeValue.length(); i++) {
            buffer[length++] = safeValue.charAt(i);
        }

        return this;
    }

    public MyStringBuilder append(char value) {
        saveSnapshot();
        ensureCapacity(length + 1);
        buffer[length++] = value;
        return this;
    }

    public MyStringBuilder deleteLast(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count must be >= 0");
        }

        if (count == 0 || length == 0) {
            return this;
        }

        saveSnapshot();
        length = Math.max(0, length - count);
        return this;
    }

    public MyStringBuilder clear() {
        if (length == 0) {
            return this;
        }

        saveSnapshot();
        length = 0;
        return this;
    }

    public boolean undo() {
        if (history.isEmpty()) {
            return false;
        }

        Snapshot snapshot = history.pop();

        buffer = Arrays.copyOf(snapshot.characters, Math.max(DEFAULT_CAPACITY, snapshot.length));
        length = snapshot.length;
        return true;
    }

    public int length() {
        return length;
    }

    @Override
    public String toString() {
        return new String(buffer, 0, length);
    }

    private void saveSnapshot() {
        history.push(new Snapshot(Arrays.copyOf(buffer, length), length));
    }

    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity <= buffer.length) {
            return;
        }

        int newCapacity = buffer.length;
        while (newCapacity < requiredCapacity) {
            newCapacity *= 2;
        }

        buffer = Arrays.copyOf(buffer, newCapacity);
    }

    private static final class Snapshot {
        private final char[] characters;
        private final int length;

        private Snapshot(char[] characters, int length) {
            this.characters = characters;
            this.length = length;
        }
    }
}