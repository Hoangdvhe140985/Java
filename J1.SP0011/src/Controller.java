
import java.math.BigInteger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hoang
 */
public class Controller {

    //chuyen he 2,16 sang he 10
    public String convertOtherToDec(String value, String ibase) {
        BigInteger result = BigInteger.ZERO;
        String hex = "0123456789ABCDEF";
        BigInteger inBase = new BigInteger(ibase);
        for (int i = 0; i < value.length(); i++) {
            // chuyển sang biginteger
            BigInteger num = BigInteger.valueOf(hex.indexOf(value.charAt(i))); // num là giá trị ứng với từng phần tử trong chuỗi nhập vào
            // kết quả sẽ là tổng của số num vừa get được x hệ lũy thùy với số mũ tương ứng
            result = result.add(num.multiply(inBase.pow(value.length() - 1 - i))); // ví dụ
        }
        return result.toString();
    }

    //chuyen he 10 sang he 2,16
    public String convertDecToOther(String decNumber, String obase) {
        String result = "";
        String hex = "0123456789ABCDEF";
        BigInteger dec = new BigInteger(decNumber);
        BigInteger outbase = new BigInteger(obase);

        while (dec.compareTo(BigInteger.ZERO) > 0) {
            // giá trị index là giá trị chia dư thu được
            int index = dec.mod(outbase).intValue();
            result = hex.charAt(index) + result;
            // chia nguyên để giảm số ban đầu
            dec = dec.divide(outbase);
        }
        return result;
    }
}
