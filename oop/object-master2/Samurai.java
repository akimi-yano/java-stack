public class Samurai extends Human{
    // ● Samurai: Set a default health of 200
    static int samuraiCount = 0;
    public Samurai(){
        this.health = 200;
        samuraiCount +=1;
    }
    // ● Samurai: Add a method deathBlow(Human) that kills the other human, 
    // but reduces the Samurai's health by half
    public void deathBlow(Human other){
    other.health = 0;
    System.out.print("The other player is dead ");
    this.health = (this.health/2);
    }
    // ● Samurai: Add a method meditate() that heals the Samurai for half of their current health.
    public void meditate(){
        this.health += (this.health/2);
    }
    
    // ● Samurai: Add a method howMany() that returns the total current number of Samurai.
    public int howMany(){
        return samuraiCount;
    }


}