/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author hoang
 */
public class Validate {

    private Scanner in = new Scanner(System.in);

    //input giới hạn int
    public int inputIntLimit(String msg, String err, int min, int max) {
        while (true) {
            try {
                System.out.print(msg);
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.println(err);
            }
        }
    }

    //input giới hạn double
    public double inputDoubleLimit(String msg, String err, double min, double max) {
        while (true) {
            try {
                System.out.print(msg);
                //nhỏ hơn min và lớn hơn max loại
                double result = Double.parseDouble(in.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                // input các số không chia kết cho 0,5 loại
                if (result % 0.5 != 0) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.println(err);
            }
        }
    }

    //input string theo regex
    public String inputString(String msg, String err, String regex) {
        String result = null;
        while (true) {
            System.out.print(msg);
            result = in.nextLine().trim();
            if (!result.matches(regex)) {
                System.err.println(err);
            } else {
                return result;
            }

        }
    }

    //input date
    public String inputDate(String msg, String err, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        while (true) {
            try {
                System.out.print(msg);
                String result = in.nextLine().trim();
                Date date = sdf.parse(result);
//                Date dt = new java.util.Date();
//                if(date.after(dt)){
//                    System.out.println("không nhập quá ngày hiện tại");
//                    continue;
//                }
                return sdf.format(date);
            } catch (Exception e) {
                System.err.println(err);
            }
        }
    }
   
}
