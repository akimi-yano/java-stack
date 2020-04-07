public class HumanTest{
    public static void main(String[] args){
        Human human1 = new Human();
        human1.displayStats();
        System.out.println("****************");

        Human human2 = new Human();
        human2.displayStats();
        System.out.println("****************");

        human1.attack(human2);
        human2.displayStats();
        System.out.println("****************");
    }
}