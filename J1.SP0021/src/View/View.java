/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.studentManager;
import Emtity.Report;
import Emtity.Student;
import Validate.Validate;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class View {

    studentManager control = new studentManager();

    public void menuView() {

        while (true) {
            Validate validate = new Validate();
            System.out.println("======== Student Manager ========");
            System.out.println(" 1.	Create");
            System.out.println(" 2.	Find and Sort");
            System.out.println(" 3.	Update/Delete");
            System.out.println(" 4.	Report");
            System.out.println(" 5.	Exit");
            int choice = validate.inputIntLimit("Enter your choice: ",
                    "Please input number in rage [1,5]", 1, 5, false);
            switch (choice) {
                case 1:
                    createStudentView();
                    break;
                case 2:
                    searchAndSortView();
                    break;
                case 3:
                    updateAndDeleteView();
                    break;
                case 4:
                    reportView();
                    break;
                case 5:
                    return;
            }

        }
    }

    // add new student
    public void createStudentView() {
        Validate validate = new Validate();
        System.out.println("======== Create Student ========");
        while (true) {
            //kiểm tra số lượng học sinh
            if (validate.CountStudent(control.getListStudents(), 10)) {
                //input yes or no
                String choice = validate.inputString("Do you want to continues(Y/N)?: ",
                        "Please input Y or N!", "[yYnN]", false);
                if (!validate.checkYesNo(choice)) {
                    return;
                }
            }
            //add mã sinh viên theo regex
            String id = validate.inputString("ID: ", "Invalid format(HE123456/SE123456)!",
                    "(SE|HE)[0-9]{6}", false);
            //add name sv theo regex
            String name = validate.inputString("Name: ", "Invalid name!",
                    "[a-zA-Z ]+", false);
            // check lỗi info
            if (!validate.checkId(control.getListStudents(), id, name)) {
                System.out.println("Wrong info!");
                System.out.println("Please try again!");
                continue;
            }
            // input học kì 1-9
            int semester = validate.inputIntLimit("Semester(1-9): ",
                    "Invalid semester!", 1, 9, false);
            //chọn 1 trong 3 course
            int course = validate.inputIntLimit("Course(1.Java|2.Net|3.C/C++):",
                    "Invalid Course", 1, 3, false);

            //Check dulicate infor
            if (validate.isExistStudent(control.getListStudents(), id, name, semester, course)) {
                System.out.println("Already exist information of this student");
                continue;
            }
            // add 1 new student
            control.addStudent(id, name, semester, course);

        }
    }

    // tìm kiếm sinh viên
    public void searchAndSortView() {
        // nếu list trống
        if (control.getListStudents().isEmpty()) {
            System.out.println("No student information yet.");
            return;
        }
        Validate validate = new Validate();
        // input name
        String searchValue = validate.inputString("Name: ", "Invalid student name!", "[a-zA-Z ]+", true);
        ArrayList<Student> listSearch = control.searchStudents(searchValue);       
        if (listSearch.isEmpty()) {
            System.out.println("Name not found ");
            return;
        }
        control.sortByName(listSearch);
        System.out.printf("%-15s%-15s%-10s%-15s\n", "Id", "Student name", "semester", "Course Name");
        //loop from first to last element of list student
        for (Student student : listSearch) {
            System.out.printf("%-15s%-15s%-10s%-15s\n", student.getId(), student.getName(), student.getSemester(),
                    student.getCourseName());
        }

    }

    //update and remove
    public void updateAndDeleteView() {
        Validate validate = new Validate();
        System.out.println("======= Update And Delete ========");
        //Check empty
        if (control.getListStudents().isEmpty()) {
            System.out.println("List empty!");
            return;
        }
        //input id
        String idSearch = validate.inputString("ID: ", "Invalid format(HE123456/SE123456)!",
                "(SE|HE)[0-9]{6}", false);
        //get list student by id
        ArrayList<Student> listSearch = control.getStudentById(idSearch);
        if (listSearch.isEmpty()) {
            System.out.println("Not found!");
            return;
        }
        //display list search
        System.out.printf("%-5s%-15s%-15s%-15s%-10s\n", "", "Id", "Name", "Semester", "Course");
        int i = 0;
        for (Student student : listSearch) {
            if (idSearch.toLowerCase().equalsIgnoreCase(student.getId())) {
                ++i;
                System.out.printf("%-5s%-15s%-15s%-15s%-10s\n", i, student.getId(),
                        student.getName(), student.getSemester(), student.getCourseName());
            }
        }
        // chọn student cần update or remove
        int select = validate.inputIntLimit("Select student: ", "Please input [1-" + i + "]", 1, i, false);
        Student stSelect = listSearch.get(select - 1);
        //update or remove
        String choice = validate.inputString("Do you want to update (U) or delete (D) student?: ",
                "Please input U/D!", "[uUdD]", false);
        //update
        if (choice.toUpperCase().equals("U")) {
            System.out.println("-------- Update --------");
            System.out.println("Blank if you don't want to change old infor!");
            String name = validate.inputString("Name: ", "Invalid name!",
                    "[a-zA-Z ]+", true);
            int semester = validate.inputIntLimit("Semester(1-9): ",
                    "Invalid semester!", 1, 9, true);
            int course = validate.inputIntLimit("Course(1.Java|2.Net|3.C/C++):",
                    "Invalid Course", 1, 3, true);
            // nếu bỏ trống hết 
            if (validate.isExistStudent(control.getListStudents(), stSelect.getId(), (name == "") ? stSelect.getName() : name,
                    (semester == 0) ? stSelect.getSemester() : semester, (course == 0) ? stSelect.getCourse() : course)) {
                System.out.println("Update fail! Dupplication infomation!");
                return;
            }
            if (control.updateStudent(stSelect, name, semester, course)) {
                System.out.println("Update successful!");
            } else {
                System.out.println("Update fail! Nothing change.");
            }
        } else {
            control.deleteStudent(stSelect);
            System.out.println("Delete successful!");
        }

    }

    //report 
    public void reportView() {
        studentManager stManager = new studentManager();
        //list trống
        if (control.getListStudents().isEmpty()) {
            System.out.println("List is Empty!\n");
            return;
        }
        //
        ArrayList<Report> listReport = stManager.getReport();
        for (int i = 0; i < listReport.size(); i++) {
            System.out.printf("%-15s|%-6s|%-5d\n", listReport.get(i).getStudentName(),
                    listReport.get(i).getCourseName(), listReport.get(i).getTotalCourse());
        }
    }

    public static void main(String[] args) {
        View stView = new View();
        stView.menuView();
    }

}