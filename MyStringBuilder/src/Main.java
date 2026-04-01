public class Main {
    public static void main(String[] args) {
        String[] arr = {"hello", "java", "world"};

        String[] result = ArrayUtils.filter(arr, new UpperCaseFilter());

        for (String s : result) {
            System.out.println(s);
        }
    }
}