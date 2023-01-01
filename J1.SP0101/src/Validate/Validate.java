/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validate;

import Emtity.Employee;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author hoang
 */
public class Validate {

    private Scanner in = new Scanner(System.in);

    //check giới hạn mes yêu cầu nhập,mess lỗi , max, min, set true false cho hàm add và update
    public int inputIntLimit(String msg, String err, int min, int max, boolean allowNull) {
        while (true) {
            try {
                System.out.print(msg);
                String temp = null;
                //input number
                temp = in.nextLine().trim();
                //if true set temp được phét bỏ trống
                if (temp.isEmpty() && allowNull == true) {
                    return 0;
                }
                //if người dùng input theo format trả về kết quả
                // if sai thì hiển thị lỗi
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

    // input tiền lương tương tự input integer
    public double inputDoubleLimit(String msg, String err, double min, double max, boolean allowNull) {
        while (true) {
            try {
                System.out.print(msg);
                String temp = null;
                //input number
                temp = in.nextLine().trim();
                //kiểm tra true thì dk phép bỏ trống
                if (temp.isEmpty() && allowNull == true) {
                    return 0;
                }
                // nếu nhập check nhập với điều kiện
                double result = Double.parseDouble(temp);
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.println(err);
            }
        }
    }

    //check input string
    public String inputString(String msg, String err, String regex, boolean allowNull) {
        String result = null;
        while (true) {
            System.out.print(msg);
            //input 1 chuỗi
            result = in.nextLine().trim();
            //kiểm tra true thì dk phép bỏ trống
            if (result.isEmpty() && allowNull == true) {
                return "";
            }
            // nếu nhập thì check với regex xem có thỏa mãi regex k
            if (!result.matches(regex)) {
                System.err.println(err);
            } else {
                return result;
            }

        }
    }

    //check date 
    public String inputDate(String msg, String err, String format, boolean allowNull) {
        //khơi tạo SimpleDateFormat class định dạng ngày tháng năm
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        while (true) {
            try {
                System.out.print(msg);
                //intput date
                String result = in.nextLine().trim();
                //lay ngay hien tai
                LocalDate today = java.time.LocalDate.now();
                Date dt = new java.util.Date();
                //chuyển string to date 
                Date date = sdf.parse(result);
                //kiểm tra true thì dk phép bỏ trống
                if (result.isEmpty() && allowNull == true) {
                    return "";
                }
                if (date.before(dt)) {
                    //chuyen doi date co dk sang localDate
                    Instant instant = date.toInstant();
                    ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
                    LocalDate givDate = zone.toLocalDate();
                    //tinhs toan ngay nhap voi ngay hien tai
                    Period per = Period.between(givDate, today);
                    //nam nho hon 18 thif print mess
                    if (per.getYears() < 18) {
                        System.out.println("Khong du 18 tuoi");
                        continue;
                    }
                } else {
                    System.out.println("khong nhap qua ngay hien tai");
                    continue;
                }

                return sdf.format(date);
            } catch (Exception e) {
                System.err.println(err);
            }
        }
    }

    // check trùng lặp id 
    public boolean checkDuplicateId(int id, ArrayList<Employee> list) {
        //duyệt aray
        for (Employee em : list) {
            //có id trả về true
            if (em.getId() == id) {
                return true;
            }
        }
        return false;
    }

//    //check duplicate
//    public static boolean checkDuplicate(ArrayList<Employee> list, String firstName, String lastName,
//            String phone, String email, String address,
//            String dob, boolean sex, double salary, String agency) {
//        //check from first to last list doctor
//        for (Employee em : list) {
//            if (firstName.equalsIgnoreCase(em.getFirstName())
//                    && lastName.equalsIgnoreCase(em.getLastName())
//                    && phone.equalsIgnoreCase(em.getPhone())
//                    && email.equalsIgnoreCase(em.getEmail())
//                    && address.equalsIgnoreCase(em.getAddress())
//                    && dob.equalsIgnoreCase(em.getDob())
//                    && sex == em.isSex()
//                    && salary == em.getSalary()
//                    && agency.equalsIgnoreCase(em.getAgency())
//                    ) {
//                return false;
//            }
//        }
//        return true;
//    }
}
