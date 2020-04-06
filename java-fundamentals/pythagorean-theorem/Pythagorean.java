// import java.math.*;
public class Pythagorean {
    public double calculateHypotenuse(int a, int b) {
        // the hypotenuse is the side across from the right angle. 
        // calculate the value of c given legA and legB
        double c = Math.sqrt((a*a)+(b*b));
        return c;
        // double four = 4.0;
        // calling the sqrt static method of the Math class
        // double squareRoot = Math.sqrt(four); // 2.0
    }
}