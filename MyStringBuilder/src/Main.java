public class Main {
    public static void main(String[] args) {
        MyStringBuilder builder = new MyStringBuilder();

        builder.append("Hello");
        builder.append(", ");
        builder.append("world");

        System.out.println("Current value: " + builder);

        builder.deleteLast(5);
        System.out.println("After deleteLast(5): " + builder);

        builder.undo();
        System.out.println("After undo(): " + builder);

        builder.append('!');
        System.out.println("After append('!'): " + builder);

        builder.clear();
        System.out.println("After clear(): " + builder);

        builder.undo();
        System.out.println("After undo() again: " + builder);
    }
}