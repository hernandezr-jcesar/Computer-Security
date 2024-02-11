import java.io.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.xml.bind.*;
public class MiDES {
    Cipher ecipher, dcipher;
    byte[] sal = { (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
                    (byte)0x56, (byte)0x35, (byte)0xE3, (byte)0x03
                    };
    int iterationCount = 19;
    MiDES(String s) {
        try {
            KeySpec ks = new PBEKeySpec(s.toCharArray(), sal, iterationCount);
            SecretKey sk = SecretKeyFactory.getInstance( "PBEWithMD5AndDES").generateSecret(ks);
            ecipher = Cipher.getInstance(sk.getAlgorithm());
            dcipher = Cipher.getInstance(sk.getAlgorithm());
            AlgorithmParameterSpec aps = new PBEParameterSpec(sal, iterationCount);
            ecipher.init(Cipher.ENCRYPT_MODE, sk, aps);
            dcipher.init(Cipher.DECRYPT_MODE, sk, aps);
        } catch (java.security.InvalidAlgorithmParameterException e) {
        } catch (java.security.spec.InvalidKeySpecException e) {
        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
        }
    }
    public String encrypt(String str) {
        try {
            byte[] utf8 = str.getBytes("UTF8");
            byte[] enc = ecipher.doFinal(utf8);
            return new sun.misc.BASE64Encoder().encode(enc);
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
        return null;
    }
    public String decrypt(String str) {
        try {
            byte[] dec = DatatypeConverter.parseBase64Binary(str);
            byte[] utf8 = dcipher.doFinal(dec);
            return new String(utf8, "UTF8");
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
        return null;
    }
}