public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");

        // primitive types: not a class; denoted with lower case
        int a = 1;
        float f = 1.0f;
        double d = 1.0;
        boolean b = false;
        char c = 'c';

        // Class String (part of standard library)
        String test = "this is a test string";
        // toUpperCase() is a non-static method; it operates on the instance object
        System.out.println(test.toUpperCase());

        boolean booleanValue = false;
        // valueOf() is a static method; it does not operate on a particular instance
        System.out.println(String.valueOf(booleanValue));
    }
}