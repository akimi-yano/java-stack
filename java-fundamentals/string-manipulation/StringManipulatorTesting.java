public class StringManipulatorTesting{
    public static void main(String[] args){
        StringManipulator manipulator = new StringManipulator();
        String str = manipulator.trimAndConcat("    Hello     ","     World    ");
        System.out.println(str); // HelloWorld 

        manipulator = new StringManipulator();
        char letter = 'o';
        Integer a = manipulator.getIndexOrNull("Coding", letter);
        Integer b = manipulator.getIndexOrNull("Hello World", letter);
        Integer c = manipulator.getIndexOrNull("Hi", letter);
        System.out.println(a); // 1
        System.out.println(b); // 4
        System.out.println(c); // null

        manipulator = new StringManipulator();
        String word = "Hello";
        String subString = "llo";
        String notSubString = "world";
        Integer d = manipulator.getIndexOrNull(word, subString);
        Integer e = manipulator.getIndexOrNull(word, notSubString);
        System.out.println(d); // 2
        System.out.println(e); // null

        manipulator = new StringManipulator();
        String newWord = manipulator.concatSubstring("Hello", 1, 2, "world");
        System.out.println(newWord); // eworld
    }
}

