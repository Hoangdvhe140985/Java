/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.TaskController;
import Emtity.Task;
import Validate.Validate;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class View {

    TaskController control = new TaskController();

    public void menuView() {
        Validate validate = new Validate();
        while (true) {
            System.out.println("======== Task Programe ========");
            System.out.println("1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. Display Task");
            System.out.println("4. Exit");
            int choice = validate.inputIntLimit("Your choice: ",
                    "Input must in range[1-4]!", 1, 4);
            switch (choice) {
                case 1:
                    addTaskView();
                    break;
                case 2:
                    deleteTaskView();
                    break;
                case 3:
                    showTasks();
                    break;
                case 4:
                    return;
            }
        }
    }

    //add 1 task
    public void addTaskView() {
        System.out.println("\n-------- Add Task --------");
        Validate validate = new Validate();
        String name = validate.inputString("Enter Requirement Name: ",
                "Name is required!", "[a-zA-Z ]+");
        int taskTypeId = validate.inputIntLimit("Enter Task Type: ",
                "Must in range[1-4]: 1.Code/ 2.Test/ 3.Design/ 4.Review", 1, 4);
        String date = validate.inputDate("Enter Date: ", "Invalid format!",
                "dd/MM/yyyy");
        double planFrom = validate.inputDoubleLimit("Enter From: ",
                "Invalid time!",
                8, 17);
        double planTo;
        while (true) {
            planTo = validate.inputDoubleLimit("Enter To: ", "Invalid time!", planFrom, 17.5);
            if (planTo != planFrom) {
                break;
            }
            System.out.println("Invalid time!");
        }
        String asignee = validate.inputString("Enter Assignee: ",
                "Invalid assignee!", "[a-zA-Z ]+");
        String reviewer = validate.inputString("Enter Reviewer: ",
                "Invalid reviewer!", "[a-zA-Z ]+");
        control.addTask(name, taskTypeId, date, planFrom, planTo, asignee,
                reviewer);
        System.out.println("Add successful!");
    }

    public void deleteTaskView() {
        System.out.println("-------- Delete Task --------");
        Validate validate = new Validate();
        ArrayList<Task> listTasks = control.getDataTasks();
        int id = validate.inputIntLimit("Id: ", "Invalid Id!", 1,
                Integer.MAX_VALUE);
        for (Task listTask : listTasks) {
            if (listTask.getId() == id) {
                control.deleteTask(id);
                System.out.println("Delete successful!");
                break;
            }
            System.out.println("Not Found !!!");
        }

    }

    public void showTasks() {
        ArrayList<Task> listTasks = control.getDataTasks();
        if (listTasks.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        System.out.printf("\n%-5s%-15s%-15s%-15s%-15s%-15s%-15s\n",
                "ID", "Name", "Task Type", "Date", "Time", "Assign", "Reviewer");
        for (int i = 0; i < listTasks.size(); i++) {
            Task task = listTasks.get(i);
            System.out.printf("%-5d%-15s%-15s%-15s%-15.1f%-15s%-15s\n",
                    task.getId(),
                    task.getName(),
                    task.getTaskTypeId(),
                    task.getDate(),
                    task.getPlanTo() - task.getPlanFrom(),
                    task.getAsignee(),
                    task.getReviewer()
            );
        }
    }

    public static void main(String[] args) {
        View view = new View();
        view.menuView();
    }
}
