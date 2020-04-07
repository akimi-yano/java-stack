public class Gorilla extends Mammal{
    public void throwSomething(){
        System.out.println("Gorilla threw something...: -5");
        setEnergyLevel(-5);
    }
    public void eatBananas(){
        System.out.println("Gorilla's satisfaction: +10");
        setEnergyLevel(10);
    }

    public void climb(){
        System.out.println("Gorilla has climbed a tree...: -10");
        setEnergyLevel(-10);
    }

}