import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class PuzzleJavaTest{
    public static void main(String[] args){
        PuzzleJava ch = new PuzzleJava();

        int[] arr1 ={3,5,1,2,7,9,8,13,25,32};
        ArrayList<Integer> result = ch.func1(arr1);
        System.out.println(result);

        ArrayList<String> arr2 = new ArrayList<String>();
        arr2.add("Nancy");
        arr2.add("Jinichi");
        arr2.add("Fujibayashi");
        arr2.add("Momochi");
        arr2.add("Ishikawa");
        ArrayList<String> result1 = ch.func2(arr2);
        System.out.println(result1); 

        ArrayList<Integer> arr3 = new ArrayList<Integer>();
        int a_int = (int) 'a';
        // System.out.println(a_int);
        // char char_value = (char) a_int;
        // System.out.println(char_value);
        int z_int = (int) 'z';
        // System.out.println(z_int);

        for (int i = a_int; i<=z_int; i++){
            arr3.add(i);
        }
        ch.func3(arr3);


        ArrayList<Integer> arr4 = ch.func4();
        System.out.println(arr4);

        ArrayList<Integer> arr5 = ch.func5();
        System.out.println(arr5);
        System.out.println(arr5.get(0));
        System.out.println(arr5.get(arr5.size()-1));

        // TEST
        // int a_int = (int) 'a';
        // int z_int = (int) 'z';
        // System.out.println(a_int);
        // System.out.println(z_int);
        // a = 97 and z = 122
        // 1-26 alph
        // 0-25 zerobased
        // 0-26 random

        String newStr = ch.func6();
        System.out.println(newStr);

        ArrayList<String> randomArr = ch.func7();
        System.out.println(randomArr);

    }


}