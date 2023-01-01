/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Emtity.Task;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class TaskController {

    private final ArrayList<Task> listTasks = new ArrayList<>();

    //add 1 task
    public int addTask(String name, int taskTypeId, String date, double planFrom, double planTo, String asignee, String reviewer) {
        int id = 0;
        //list trống id dk add lần đầu sẽ = 1
        if (listTasks.isEmpty()) {
            id = 1;
        } else {
            //lấy id cuối cùng trong list + 1
            id = listTasks.get(listTasks.size() - 1).getId() + 1;
        }
        //add list
        listTasks.add(new Task(id, name, taskTypeId, date, planFrom, planTo, asignee, reviewer));
        return id;
        
    }

    // xóa  1 task
    public void deleteTask(int id) {
        for (Task task : listTasks) {
            if (task.getId() == id) {
                listTasks.remove(task);
                return;
            }
        }       
    }

    //lấy data trong list
    public ArrayList<Task> getDataTasks() {
        return listTasks;
    }
}
