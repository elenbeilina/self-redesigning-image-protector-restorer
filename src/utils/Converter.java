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

    void conToBinary(String s) {
        byte[] arr = txtToByte(s);
        StringBuilder binary = new StringBuilder();
        for (byte b : arr) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        System.out.println("'" + s + "' to binary: " + binary);
        conToString(binary);
    }

    public static byte[] txtToByte(String s) {
        return s.getBytes(StandardCharsets.UTF_8);
    }

}
