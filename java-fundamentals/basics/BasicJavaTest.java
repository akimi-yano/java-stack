import java.util.ArrayList;

public class BasicJavaTest{
    public static void main(String[] args){
        BasicJava algo = new BasicJava();
        algo.print1To255();
        algo.printOdd1To255();
        algo.printSum();

        int[] arr={1,3,5,7,9,13};
        algo.iterateArr(arr);

        int[] arr2={-3, -5, -7};
        // int[] arr2={1,2,3,4,5,6,7,8};
        algo.findMax(arr2);

        int[] arr3={2, 10, 3};
        // int[] arr3={};
        algo.getAvg(arr3);

        ArrayList<Integer> y = algo.arrayWithOdd();
        System.out.println(y);

        
        int[] testArr = {1, 3, 5, 7};
        int test = 3;
        int count = algo.greaterThanY(testArr, test);
        System.out.println(count);

        int[] arr4={1, 5, 10, -2};
        ArrayList<Integer> newArr = algo.squareVals(arr4);
        System.out.println(newArr);

        int[] arr5={1, 5, 10, -2};
        ArrayList<Integer> allPositives = algo.eliminateNegatives(arr5);
        System.out.println(allPositives);

        int[] arr6={1, 5, 10, -2};
        ArrayList<Integer> summary = algo.summarize(arr6);
        System.out.println(summary);

        int[] arr7={1, 5, 10, 7, -2};
        ArrayList<Integer> shifted = algo.shiftArr(arr7);
        System.out.println(shifted);
}
}