public class Bat extends Mammal{
    // Tasks:
    // ● Create a Bat class that can fly(), eatHumans(), and attackTown() and has a default energy level of 300.
    public Bat(){
        this.energyLevel = 300;
    }
    public void fly(){
        System.out.println("Pasa-pasa(sound of bat taking off): -50");
        this.energyLevel -=50;
    }
    public void eatHumans(){
        System.out.println("HMMM(ate a human): +25");
        this.energyLevel +=25;
    }
    public void attackTown(){
        System.out.println("Weeeen(sound of town on fire): -100");
        this.energyLevel -=100;

    }
    // ● For the fly() method, print the sound a bat taking off and decrease its energy by 50.
    
    // ● For the eatHumans() method, print the so- well, never mind, just increase its energy by 25.
    
    // ● For the attackTown() method, print the sound of a town on fire and decrease its energy by 100.
    
    // ● Create a BatTest class to instantiate a bat and have it attack three towns, eat two humans, and fly twice.
}