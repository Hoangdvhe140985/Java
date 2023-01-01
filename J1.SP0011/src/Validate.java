
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hoang
 */
public class Validate {

    Scanner sc = new Scanner(System.in);

    // validate inputBase
    public String inputBase(String msg, String err) {
        while (true) {
            System.out.print(msg);
            String base = sc.nextLine();
            if (base.equals("2") || base.equals("10") || base.equals("16")) {
                return base;
            }
            System.out.println(err);
        }
    }

    // validate input value
    public String inputValue(String msg, String err, String inBase) {
        while (true) {
            System.out.print(msg);
            String value = sc.nextLine().toUpperCase();
            if (value.matches(inBase)) {
                return value;
            }
            System.out.println(err);
        }
    }

    // validate input yes/no
    public String inputYesNo(String msg, String err) {
        while (true) {
            System.out.print(msg);
            String yesNo = sc.nextLine();
            if (yesNo.matches("[yYnN]")) {
                return yesNo;
            }
            System.out.println(err);
        }
    }
}
