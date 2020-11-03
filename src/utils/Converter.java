package utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Converter {
    void conToString(StringBuilder binary) {
        try {
            BigInteger val = new BigInteger("" + binary, 2);
            byte[] imageInByte = val.toByteArray();
            String hi = new String(imageInByte);
            System.out.println(hi);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static byte[] txtToByte(String s) {
        return s.getBytes(StandardCharsets.UTF_8);
    }

}
