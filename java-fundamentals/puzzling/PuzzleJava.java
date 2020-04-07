import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class PuzzleJava{

    // ● Create an array with the following values: 3,5,1,2,7,9,8,13,25,32. 
    // Print the sum of all numbers in the array. 
    // Also have the function return an array that only includes numbers that are greater than 10 
    // (e.g. when you pass the array above, it should return an array with the values of 13,25,32)
    public ArrayList<Integer> func1(int[] arr){
        ArrayList<Integer> newArr = new ArrayList<Integer>();
        int sum = 0;
        for (int i=0; i<arr.length; i++){
            sum+=arr[i];
            if(arr[i]>10){
                newArr.add(arr[i]);
            }
        }
        System.out.println(sum);
        return newArr;
    }

    // ● Create an array with the following values: Nancy, Jinichi, Fujibayashi, Momochi, Ishikawa. 
    // Shuffle the array and print the name of each person. 
    // Have the method also return an array with names that are longer than 5 characters.

    public ArrayList<String> func2(ArrayList<String> arr){
        ArrayList<String> newArr = new ArrayList<String>();
        Collections.shuffle(arr);
        for(int i=0; i<arr.size(); i++){
            System.out.println(arr.get(i));
            if((arr.get(i)).length()>5){
                newArr.add(arr.get(i));
            }
        }
        return newArr;   
    }
    

     // ● Create an array that contains all 26 letters of the alphabet (this array must have 26 values). 
    // Shuffle the array and, after shuffling, display the last letter from the array. 
    // Have it also display the first letter of the array. 
    // If the first letter in the array is a vowel, have it display a message.
    // To shuffle a collection, you can use the shuffle method of the Collections class. 

    public void func3(ArrayList<Integer> arr){
        ArrayList<Character> vowels= new ArrayList<Character> ();
        vowels.add('a');
        vowels.add('i');
        vowels.add('u');
        vowels.add('e');
        vowels.add('o');
    
        Collections.shuffle(arr);
        
        System.out.println((char) arr.get(arr.size()-1).intValue());
        
        System.out.println((char) arr.get(0).intValue());
        
        if (vowels.indexOf((char) arr.get(0).intValue())>-1){
            System.out.println("Message: Starting with a vowel");
        }
        
    }

    // ● Generate and return an array with 10 random numbers between 55-100.
    // To get a random integer, you can use the nextInt method of the Random class. 

    public ArrayList<Integer> func4(){
        ArrayList<Integer> newArr = new ArrayList<Integer>();
        Random r = new Random();
        for (int i=0; i<10; i++){
            int number = (r.nextInt(46))+ 55;
            newArr.add(number); // exclusive so 55 to 101
        }
        return newArr;
    }





    // ● Generate and return an array with 10 random numbers between 55-100 and have it be sorted 
    // (showing the smallest number in the beginning). 
    // Display all the numbers in the array. 
    // Next, display the minimum value in the array as well as the maximum value.
    // To sort a collection, you can use the sort method of the Collections class.

    public ArrayList<Integer> func5(){
        ArrayList<Integer> newArr = new ArrayList<Integer>();
        Random r = new Random();
        for (int i=0; i<10; i++){
            int number = (r.nextInt(46))+ 55;
            newArr.add(number); // exclusive so 55 to 101
        }
        Collections.sort(newArr);
        return newArr;
    }
    
   
    // ● Create a random string that is 5 characters long.

    
    // int a_int = (int) 'a';
    // int z_int = (int) 'z';

    public String func6(){
        Random r = new Random();
        String word = "";
        for(int i=0; i<5; i++){
            char letter = (char) ((r.nextInt(26))+97);
            word += letter;
        }
        return word;
    }
    
    // ● Generate an array with 10 random strings that are each 5 characters long


    public ArrayList<String> func7(){
        ArrayList<String> newArr = new ArrayList<String>();
        Random r = new Random();
        for (int k=0; k<10; k++){
        String word = "";
        for(int i=0; i<5; i++){
            char letter = (char) ((r.nextInt(26))+97);
            word += letter;
        }
        newArr.add(word);
    }
    return newArr;
}

}