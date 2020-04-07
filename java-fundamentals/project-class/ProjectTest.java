import java.util.ArrayList;

public class ProjectTest{
    public static void main(String[] args){
Project project = new Project();

String name1 = "OOP";
String description1 = "finish OOP stuff";
// String pitch=project.elevatorPitch(name1, description1);
double cost = 0.0;
String pitch=project.elevatorPitch();
System.out.println(pitch);

// Project test1 = new Project();
// System.out.println(test1.getName());
// System.out.println(test1.getDescription());

// String testName = "icecandi";
// Project test2 = new Project(testName);
// System.out.println(test2.getName());
// System.out.println(test2.getDescription());
// String newDesc = "coding all day for 2 weeks";
// test2.setDescription(newDesc);
// System.out.println(test2.getDescription());

Project test3 =new Project("studyforbeltexam", "finish everything");
System.out.println(test3.getName());
System.out.println(test3.getDescription());

ArrayList<Project> projects = new ArrayList<Project>();
projects.add(test3);

Portfolio portfolio = new Portfolio(projects);
double portfolioCost = portfolio.getPortfolioCost();
System.out.println("Portfolio cost: " + portfolioCost);

portfolio.showPortfolio();

}
    
}