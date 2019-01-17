package old;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by daniel on 2018/11/1.
 */
public class CharsetTest {
    public static void main (String args[]) {
        testStringToByte();
    }

    public static void testByteToString (){

        byte[] bytes = {-26, -120, -111, -26, -104, -81, -25, -120, -72, -25, -120, -72};
        byte[] gbkbytes = {-50, -46, -54, -57, -80, -42, -80, -42};

        String str = new String(bytes);
        System.out.println(str);

        String str2 = null;
        try {
            str2 = new String(gbkbytes, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(str2);
    }

    public static void testStringToByte () {
        String string = "I am 徐清锋";
        System.out.println(Charset.defaultCharset());
        toHex(string.toCharArray());

        try {
            byte[] iso8859 = string.getBytes("ISO-8859-1");
            toHex(iso8859);

            byte[] gbk = string.getBytes("GBK");
            toHex(gbk);

            byte[] utf16 = string.getBytes("UTF-16");
            toHex(utf16);

            byte[] utf8 = string.getBytes("UTF-8");
            toHex(utf8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public static void toHex(byte[] bytes) {

        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        System.out.println(sb.toString());
    }

    public static void toHex(char[] chars) {

        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            byte h = (byte)c;
            byte l = (byte)(c >> 8);
            sb.append(String.format("%02X ", l));
            sb.append(String.format("%02X ", h));
        }
        System.out.println(sb.toString());
    }
}
