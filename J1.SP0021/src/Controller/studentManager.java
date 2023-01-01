/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Emtity.Report;
import Emtity.Student;
import Validate.Validate;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author hoang
 */
public class studentManager {

    private ArrayList<Student> listStudents = new ArrayList<>();
    
   
    public studentManager() {
        listStudents.add(new Student("HE141054", "Nguyen Van A", 6, 1));
        listStudents.add(new Student("HE141054", "Nguyen Van A", 5, 2));
        listStudents.add(new Student("HE141053", "Nguyen Van C", 4, 3));
        listStudents.add(new Student("HE141054", "Nguyen Van A", 4, 1));//trùng tên trùng cos vs 1
        listStudents.add(new Student("HE141052", "Nguyen Van D", 3, 3));
        listStudents.add(new Student("HE141051", "Nguyen Van E", 4, 1));
        listStudents.add(new Student("HE141050", "Nguyen Van C", 3, 2));
        listStudents.add(new Student("HE141051", "Nguyen Van E", 4, 1));
        listStudents.add(new Student("HE141049", "Nguyen Van F", 3, 3));
        listStudents.add(new Student("HE141048", "Nguyen Van G", 4, 1));
        listStudents.add(new Student("HE141047", "Nguyen Van H", 3, 3));

    }

    // get list student
    public ArrayList<Student> getListStudents() {
        return listStudents;
    }
    
    //add 1 new student
    public void addStudent( String id, String name,
            int semester, int course) {
        listStudents.add(new Student(id, name, semester, course));
    }

    // xoa 1 student
    public void deleteStudent(Student student) {
        listStudents.remove(student);
    }

    // lấy student by id
    public ArrayList<Student> getStudentById( String id) {
        ArrayList<Student> listSearch = new ArrayList<>();
        for (Student st : listStudents) {
            if (st.getId().equals(id)) {
                listSearch.add(st);
            }
        }
        return listSearch;
    }

    // update student
    public boolean updateStudent( Student oldStudent,
            String name, int semester, int course) {
        Validate validate = new Validate();
        // nếu k update trả về false
        if (name == "" && semester == 0 && course == 0) {
            return false;
        }
        //update
        for (Student st : listStudents) {
            if (st.getId().equalsIgnoreCase(oldStudent.getId())) {
                if (name != "") {
                    st.setName(name);
                }

                if (st.getSemester() == oldStudent.getSemester()
                        && st.getCourseName().equalsIgnoreCase(oldStudent.getCourseName())) {

                    if (semester != 0) {
                        st.setSemester(semester);
                    }
                    if (course != 0) {
                        st.setCourse(course);
                    }
                }
            }
        }
        return true;
    }

    //tìm kiếm student
    public ArrayList<Student> searchStudents(String searchValue) {
        ArrayList<Student> listSearch = new ArrayList<>();
//        if(searchValue == "")
        for (Student st : listStudents) {
            String stName = st.getName().toUpperCase();
            if (stName.contains(searchValue.toUpperCase())) {
                listSearch.add(st);
            }
        }
        return listSearch;
    }

    // sort by name
    public void sortByName(ArrayList<Student> listSearch) {
        Collections.sort(listSearch);
    }

    //get report
    public ArrayList<Report> getReport() {
        Validate validate = new Validate();
        ArrayList<Report> listReport = new ArrayList<>();

        for (int i = 0; i < listStudents.size(); i++) {
            //lấy id,name,course
            String id = listStudents.get(i).getId();
            String name = listStudents.get(i).getName();
            String course = listStudents.get(i).getCourseName();
            int total = 1;
            //trùng id, trùng course thì total ++
            for (Student student : listStudents) {
                if (student.getId().equals(listStudents.get(i).getId())
                        && student.getCourseName().equals(listStudents.get(i).getCourseName())
                        && student.getSemester() != listStudents.get(i).getSemester()) {
                    total++;
                }
            }
            //
            if (!validate.checkReportExist(listReport,id, course)) {
                listReport.add(new Report(id,name, course, total));
            }

        }
        return listReport;
    }

    public static void main(String[] args) {
        studentManager st  = new studentManager();
        st.deleteStudent(null);
    }
}