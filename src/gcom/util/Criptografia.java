package gcom.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public final class Criptografia {
    private Criptografia() {
    }

	public static synchronized String encriptarSenha(String plaintext) throws ErroCriptografiaException {
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("SHA");

		} catch (NoSuchAlgorithmException e) {
			throw new ErroCriptografiaException(e.getMessage());
		}
		try {
			md.update(plaintext.getBytes("UTF-8"));

		} catch (UnsupportedEncodingException e) {
			throw new ErroCriptografiaException(e.getMessage());
		}

		byte raw[] = md.digest();

		String hash = new String(Base64.encodeBase64(raw));

		return hash;
	}

    public static String encrypt(String str) {
        int tab[] = {77,110,70,114,90,100,86,103,111,75};
        int i;
        int value = 0;
        int len = str.length();
        String response = "";

        for (i=0; i < len; i++) {
            value = (int) str.charAt(i);
            response += (char) tab[ (value - 48) ];
        }
       
        return response;
    }

    public static void main(String[] args) throws ErroCriptografiaException {
        System.out.print(Criptografia.encriptarSenha("usuario"));
    }
}
