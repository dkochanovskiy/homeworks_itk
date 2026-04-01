import java.util.HashMap;
import java.util.Map;

public class ArrayUtils {
    

    public static <T> Map<T, Integer> countElements(T[] array) {

        Map<T, Integer> result = new HashMap<>();

        for (T element : array) {
            result.merge(element, 1, Integer::sum);
        }

        return result;
    }
}
