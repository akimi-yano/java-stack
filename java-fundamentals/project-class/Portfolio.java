import java.util.ArrayList;

public class Portfolio{
    private ArrayList<Project> projects;
    
    public Portfolio(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public ArrayList<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(ArrayList<Project> newProjects) {
        this.projects = newProjects;
    }

    public double getPortfolioCost() {
        double totalCost = 0.0;
        for (int i = 0; i < this.projects.size(); i++) {
            Project currentProject = this.projects.get(i);
            totalCost += currentProject.getInitialCost();
        }
        return totalCost;
    }

    public void showPortfolio() {
        for (int i = 0; i < this.projects.size(); i++) {
            Project currentProject = this.projects.get(i);
            System.out.println("Elevator pitch: " + currentProject.elevatorPitch());
        }
        System.out.println(this.getPortfolioCost());
    }
}