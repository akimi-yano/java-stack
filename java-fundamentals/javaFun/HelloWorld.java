import java.math.BigInteger;

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

        int primitiveInt = 1;
        Integer classInt = primitiveInt;
        BigInteger bigInt = new BigInteger("100000000000000000000000000");
        System.out.println(bigInt);
        // toString() defines how to represent this object as a String; every class has a toString() method
        System.out.println(bigInt.toString());
        System.out.println(bigInt.toString().toString());

        // implicit casting: 0.3f (float) is casted to a string
        String strWithFloat = 0.3f + " hi";
        System.out.println(strWithFloat);
    }
}