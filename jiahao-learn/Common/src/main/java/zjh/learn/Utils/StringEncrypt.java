package zjh.learn.Utils;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 将字符串加密
 * 输出为字符串表示的16进制数字，或者Base64的字符串
 * Created by jiahao on 16-8-10.
 */
public class StringEncrypt {

    private static StringEncrypt ALG_MD5 = new StringEncrypt("MD5");
    private static StringEncrypt ALG_SHA1 = new StringEncrypt("SHA-1");
    private static StringEncrypt ALG_SHA256 = new StringEncrypt("SHA-256");

    public static StringEncrypt getInstance(EncryptType encryptType) {
        return encryptType == null ? null : encryptType.value;
    }

    private String algorithm;

    private MessageDigest messageDigest;

    private BASE64Encoder base64Encoder = new BASE64Encoder();

    private StringEncrypt(String algorithm) {
        this.algorithm = algorithm;
        inti();
    }

    public String encodeHexString(String str) {
        messageDigest.reset();
        messageDigest.update(str.getBytes());
        return bytes2HexString(messageDigest.digest());
    }

    public String encodeBase64(String str) {
        messageDigest.reset();
        messageDigest.update(str.getBytes());
        return base64Encoder.encode(messageDigest.digest()).replace("=", "");
    }

    private String bytes2HexString(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    private void inti() {
        try {
            switch (algorithm) {
                case "MD5":
                    messageDigest = MessageDigest.getInstance("MD5");
                    break;
                case "SHA-1":
                    messageDigest = MessageDigest.getInstance("SHA-1");
                    break;
                case "SHA-256":
                    messageDigest = MessageDigest.getInstance("SHA-256");
                    break;
                default:
                    throw new NoSuchAlgorithmException(algorithm);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static enum EncryptType {
        MD5(ALG_MD5), SHA_1(ALG_SHA1), SHA_256(ALG_SHA256);

        private StringEncrypt value;

        EncryptType(StringEncrypt value) {
            this.value = value;
        }
    }
}
