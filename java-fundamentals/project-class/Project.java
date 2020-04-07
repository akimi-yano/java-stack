public class Project{
// Tasks:
// ● Create a Project class that has the fields of name and description.

private String name;
private String description;
private double initialCost;


// ● Create an instance method (non-static) called elevatorPitch that 
// will return the name and description separated by a colon.

public String elevatorPitch(){
    return (name + " (" + initialCost + ")"+ " : " + description);
}

// ● Overload the constructor method in three different ways.

// public Project() {}
public Project(){
    this("rest", "intensive rest sesh");
}
// public Project(String name) {}
public Project(String name){
    this.name = name;
    this.description = "TBD";
    this.initialCost = 0.0;
}
// public Project(String name, String description) {}
public Project(String name, String description){
    this.name = name;
    this.description = description;
    this.initialCost = 0.0;
}
// ● Create getter and setters for each field

public String getName(){
    return name;
}
public void setName(String newName){
    name = newName;
}

public String getDescription(){
    return description;
}
public void setDescription(String newDescription){
    description = newDescription;
}

public double getInitialCost(){
    return initialCost;
}
public void setInitialCost(double newInitialCost){
    initialCost= newInitialCost;
}

// ● Create a ProjectTest file that will test all the functionality.

// Optional Challenges:
// ● Add an additional initialCost member variable that is of type double and has both getters and setters

// ● Add this to the elevator pitch in parentheses after the name, e.g.: name (cost): description

// ● Create a Portfolio class that will keep an ArrayList of Projects in the field projects. 
// Use generics to ensure these are Project objects.

// ● Add the appropriate getters/setters/constructors for this class to work

// ● Add the getPortfolioCost method that calculates and returns the cost to buy the entire portfolio.

// ● Add the showPortfolio method that will print all the project elevator pitches, followed by the total cost.




}