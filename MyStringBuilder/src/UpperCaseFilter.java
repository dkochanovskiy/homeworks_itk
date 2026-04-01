public class UpperCaseFilter implements Filter<String> {

    @Override
    public String apply(String o) {
        return o.toUpperCase();
    }
}