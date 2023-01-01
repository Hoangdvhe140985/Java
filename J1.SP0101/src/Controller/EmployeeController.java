/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Emtity.Employee;
import java.util.ArrayList;
import java.util.Collections;



/**
 *
 * @author hoang
 */
public class EmployeeController {

    private final ArrayList<Employee> listEmployees = new ArrayList<>();

    public EmployeeController() {
        listEmployees.add(new Employee(1, "A", "Mr", "0123456789",
                "123@gamail.com", "Ha Noi", "21/07/2000", true, 900, "None"));
        listEmployees.add(new Employee(2, "B", "Mr", "9876543210",
                "131@gamail.com", "Ha Noi", "10/02/1999", false, 1000, "None"));
    }

    public ArrayList<Employee> getListEmployees() {
        return listEmployees;
    }

    //add employee
    public boolean addEmployee(int id, String firstName, String lastName,
                              String phone, String email, String address,
                              String dob, boolean sex, double salary, String agency) {     
        return listEmployees.add(new Employee(id, firstName, lastName,
                                     phone, email, address, dob, sex, salary, agency));
    }

    // update employee
    public void updateEmployee(int id, String firstName, String lastName, 
                             String phone, String email, String address, 
                             String dob, Boolean sex, double salary, String agency) {
        // lấy id tìm dk
        Employee em = getEployeeById(id);
        if (!"".equals(firstName)) {
            em.setFirstName(firstName);
        }
        if (!"".equals(lastName)) {
            em.setLastName(lastName);
        }
        if (!"".equals(address)) {
            em.setAddress(address);
        }
        if (!"".equals(agency)) {
            em.setAgency(agency);
        }
        if (!"".equals(email)) {
            em.setEmail(email);
        }
        if (!"".equals(phone)) {
            em.setPhone(phone);
        }
        if (!"".equals(dob)) {
            em.setDob(dob);
        }
        if (salary != 0) {
            em.setSalary(salary);
        }
        if (sex != null) {
            em.setSex(sex);
        }

    }

    // lấy id của nhân viên
    public Employee getEployeeById(int id) {
        for (Employee em : listEmployees) {
            if (em.getId() == id) {
                return em;
            }
        }
        return null;
    }

    // xóa nhân viên theo id
    public boolean removeEmployee(int id) {
        for (Employee em : listEmployees) {
            if (em.getId() == id) {
                listEmployees.remove(em);
                return true;
            }
        }
        return false;
    }

    //tìm kiếm 1 nhân viên
    public ArrayList<Employee> searchEmployeeByName(String searchValue) {
        //k nhập gì trả về all nhân viên
        if (searchValue == null) {
            return listEmployees;           
        } else {
            //tao 1 list nhân viên mới
            ArrayList<Employee> ListSearch = new ArrayList<>();
            //duyêt list cũ
            for (Employee em : listEmployees) {
                //nối firstName và LastName lại với nhau
                String name = em.getFirstName() + em.getLastName();
                //sử dụng  contains để tìm kiếm trong chuỗi
                if (name.toUpperCase().contains(searchValue.toUpperCase())) {
                    //add vào list mới
                    ListSearch.add(em);
                }
            }
            return ListSearch;
        }
    }

    // sắp sếp employee by salary
    public ArrayList<Employee> sortEmployeeBySalary() {
        ArrayList<Employee> sortedEmployee = listEmployees;       
        Collections.sort(sortedEmployee);
        return sortedEmployee;
    }
}
