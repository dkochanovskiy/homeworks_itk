import java.lang.reflect.Array;

@SuppressWarnings("unchecked")
public class ArrayUtils {

    public static <T> T[] filter(T[] array, Filter<T> filter) {

        T[] result = (T[]) Array.newInstance(
                array.getClass().getComponentType(),
                array.length
        );

        for (int i = 0; i < array.length; i++) {
            result[i] = filter.apply(array[i]);
        }

        return result;
    }
}