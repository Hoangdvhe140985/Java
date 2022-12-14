/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validate;

import Emtity.Report;
import Emtity.Student;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hoang
 */
public class Validate {

    private final Scanner in = new Scanner(System.in);

    //input int
    public int inputIntLimit(String msg, String err, int min, int max, boolean allowNull) {
        while (true) {
            try {
                System.out.print(msg);
                String temp = in.nextLine().trim();
                if (allowNull == true) {
                    if (temp.isEmpty()) {
                        return 0;
                    }
                }
                int result = Integer.parseInt(temp);
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.println(err);
            }
        }
    }

    //input string
    public String inputString(String msg, String err, String regex, boolean allowNull) {
        
        while (true) {
            System.out.print(msg);
            String result = in.nextLine().trim();
            if (allowNull == true) {
                if (result.isEmpty()) {
                    return "";
                }
            }
            if (!result.matches(regex)) {
                System.err.println(err);
            } else {
                return result;
            }
        }
    }

    //check dulicate
    public boolean isExistStudent(ArrayList<Student> ls, String id,
            String studentName, int semester, int course) {
        int size = ls.size();
        for (Student student : ls) {
            if (id.equalsIgnoreCase(student.getId())
                    && studentName.toLowerCase().equalsIgnoreCase(student.getName().toLowerCase())
                    && semester == student.getSemester()
                    && course == student.getCourse()) {
                return true;
            }
        }
        return false;
    }

    //check info
    public boolean checkId(ArrayList<Student> ls, String id,
            String studentName) {
        int size = ls.size();
        for (Student student : ls) {
            // n???u ng d??ng nh???p 1 id d?? c?? trong list
            if (id.equalsIgnoreCase(student.getId())) {
                //nh??ng name k ????ng trong list
                if (!student.getName().toUpperCase().equalsIgnoreCase(studentName.toUpperCase())) {
                    return false;//return false
                }
            }
        }
        return true;
    }

    // check s??? l?????ng 
    public boolean CountStudent(ArrayList<Student> stu, int amount) {
        int count = 0;
        ArrayList<Student> newStu = new ArrayList<>();
        //duyet trong list 10 id kh??c nhau
        for (Student student : stu) {
            int key = 0;       
            //nueeus tr??ng id kh??ng t??nh
            for (Student stuInNew : newStu) {
                if (student.getId().equalsIgnoreCase(stuInNew.getId())) {
                    key = 1;
                    break;
                }
            }            
            if (key == 0) {
                count++;
                newStu.add(student);
            }
        }
        //count >= 10 return true t???c c?? ????? 10 ng trong list
        System.out.println(count);
        return (count >= amount) ? true : false;

    }

    // ng d??ng chon yes ????? ti???p t???c no ????? d???ng l???i
    public boolean checkYesNo(String choice) {
        if (choice.toUpperCase().equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    //check dulicate
    public boolean checkReportExist(ArrayList<Report> lr, String id, String course) {      
        for (Report report : lr) {
            //id, cous tr??ng v???i id,cousr c???a list repost 
            if (id.equalsIgnoreCase(report.getId())
                    && report.getCourseName().equalsIgnoreCase(course)) {
                return true;
            }
        }
        return false;
    }

}
