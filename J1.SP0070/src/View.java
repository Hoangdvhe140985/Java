
import java.util.Locale;
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
public class View {

    private final Scanner in = new Scanner(System.in);
    Ebank e = new Ebank();

    public void login(Locale locale) {
        e.setLocale(locale);
        inputAccount();
        inputPass();
        inputCapcha();
    }

    public void display() {
        Ebank e = new Ebank();
        View v = new View();
        Locale vietnamese = new Locale("vn");
        Locale english = Locale.ENGLISH;
        System.out.println("1. Vietnamese");
        System.out.println("2. English");
        System.out.println("3. Exit");
        System.out.print("Please choice one option: ");
        int choice = inputIntLimit(1, 3, english);
        switch (choice) {
            case 1:
                v.login(vietnamese);
                break;
            case 2:
                v.login(english);
                break;
            case 3:
                return;
        }
    }

    public static void main(String[] args) {
        View v = new View();
        v.display();
    }

    public void inputAccount() {
        System.out.println(e.getWord("enterAccountNumber"));
        String error = e.getWord("errCheckInputAccount");
        while (error.equals(e.getWord("errCheckInputAccount"))) {
            String account = in.nextLine();
            error = e.checkInputAccount(account);
            if (!error.equals("")) {
                System.out.println(error);
            }
        }
    }

    public void inputPass() {
        System.out.println(e.getWord("enterPassword"));
        String error = e.getWord("errCheckLengthPassword");
        while (error.equals(e.getWord("errCheckLengthPassword"))) {
            String pass = in.nextLine();
            error = e.checkInputPassword(pass);
            if (!error.equals("")) {
                System.out.println(error);                
            }
        //    System.out.println("Eror :" + error + "\nPass :" + pass);
        }
    }

    public void inputCapcha() {
        System.out.println(e.getWord("enterCaptcha"));
        String error = e.getWord("errCaptchaIncorrect");
        while (error.equals(e.getWord("errCaptchaIncorrect"))) {
            String generate = e.generateCaptcha();
            System.out.println("Captcha : " + generate);
            String inputCapcha = in.nextLine();
            error = e.checkInputCaptcha(generate, inputCapcha);
            if (!error.equals("")) {
                System.out.println(error);
            }
        }
    }

    //input giới hạn việc chọn từ 1-3
    public int inputIntLimit(int min, int max, Locale language) {
        e.setLocale(language);
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException ex) {
                System.out.println(e.getWord("errorCheckInputIntLimit"));
            }
        }
    }
}
