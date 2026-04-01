import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, world");

        String[] arr = {"a", "b", "a", "c", "b", "a"};

        Map<String, Integer> map = ArrayUtils.countElements(arr);

        System.out.println(map);
    }
}