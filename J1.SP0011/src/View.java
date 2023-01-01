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

    public static void main(String[] args) {
        Validate v = new Validate();
        String s = "0123456789ABCDEF";
        while (true) {
            String inBase = v.inputBase("Enter the base number in: ",
                    "Only choose 2 or 10 or 16");
            String outBase = v.inputBase("Enter the base number out: ",
                    "Only choose 2 or 10 or 16");
            String value = v.inputValue("Enter the input value: ",
                    "Invalid value,please input again", "[" + s.substring(0, Integer.parseInt(inBase)) + "]+");
            System.out.println("Output value: ");
            changeBase(inBase, outBase, value.toUpperCase());

            // check Yes/No
            if (!chooseYesNo()) {
                return;
            }
        }
    }

    // convert các hệ
    public static void changeBase(String iBase, String oBase, String value) {
        Controller cb = new Controller();

        if (iBase.equals("10")) {
            System.out.println(cb.convertDecToOther(value, oBase));
        } else {
            String dec = cb.convertOtherToDec(value, iBase);
            System.out.println(cb.convertDecToOther(dec, oBase));
        }
    }

    // lựa chọn y/n
    public static boolean chooseYesNo() {
        Validate getInput = new Validate();
        String yesNo;
        yesNo = getInput.inputYesNo("Do you want to continue? Yes/No: ",
                "Must enter Y/y for yes,N/n for no !");
        return yesNo.equalsIgnoreCase("y");
    }
}
