public class HolisticHumanTest{
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


        Wizard wizard1 = new Wizard();
        wizard1.displayStats();
        System.out.println("****************");
        wizard1.heal(human2);
        human2.displayStats();
        System.out.println("****************");
        wizard1.fireball(human1);
        human1.displayStats();
        System.out.println("****************");

        Ninja ninja1 = new Ninja();
        ninja1.displayStats();
        System.out.println("****************");
        ninja1.steal(wizard1);
        wizard1.displayStats();
        System.out.println("****************");
        ninja1.displayStats();
        System.out.println("****************");
        ninja1.runAway();
        ninja1.displayStats();

        System.out.println("****************");
        Samurai samurai1 = new Samurai();
        samurai1.displayStats();
        System.out.println("****************");
        samurai1.deathBlow(ninja1);
        ninja1.displayStats();
        System.out.println("****************");
        samurai1.displayStats();
        System.out.println("****************");
        samurai1.meditate();
        samurai1.displayStats();
        System.out.println("****************");
        System.out.println("Samurai Count : "+ samurai1.howMany());
        Samurai samurai2 =new Samurai();
        System.out.println("****************");
        samurai2.displayStats();
        System.out.println("Samurai Count : "+ samurai1.howMany());
    }
}