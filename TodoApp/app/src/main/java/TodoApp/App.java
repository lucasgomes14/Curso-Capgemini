/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package TodoApp;

import Controller.ProjectController;
import Model.Project;
import org.checkerframework.checker.units.qual.A;

public class App {
    public static void main(String[] args) {
        ProjectController projectController = new ProjectController();
        
        Project project = new Project();
        project.setName("projeto teste");
        project.setDescription("description teste");
        projectController.save(project);
    }
}
