// ● Create a Human class

public class Human{

protected int strength;
protected int stealth;
protected int intelligence;
protected int health;

// ● Add the strength, stealth, and intelligence attributes 
// with a default of 3
// ● Add the health attribute with a default of 100
public Human(){
    this.strength = 3;
    this.stealth = 3;
    this.intelligence =3;
    this.health = 100;
}

// ● Add the attack(Human) method that reduces the health of 
// the attacked human by the strength of the current human.

public void attack(Human other){
    other.health -= this.strength;
}

public void displayStats(){
    System.out.println("Strength: " + this.strength);
    System.out.println("Stealth: " + this.stealth);
    System.out.println("Intelligence: " + this.intelligence);
    System.out.println("Health: " + this.health);
}
// ● Test these methods work using a HumanTest class
}