package gcom.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * Essa classe tem o papel de fornecer ao sistema serviços de criptografia da
 * biblioteca java.security
 * 
 * @author Rodrigo Silveira
 */
public final class Criptografia {
    //private static Criptografia instance;

    /**
     * Construtor da classe ServicosCriptografia
     */
    private Criptografia() {
    }

    /**
     * Esse método recebe uma senha digitada pelo usuário e aplica um algoritmo
     * de hash(SHA) para tornar a senha criptografada
     * 
     * @param plaintext
     *            Senha digitada pelo usuário
     * @return O hash da senha
     * @exception ErroCriptografiaException
     *                Ocorrência de algum erro no mecanismo de criptografia
     */
    public static synchronized String encriptarSenha(String plaintext)
            throws ErroCriptografiaException {
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

        String hash = (new BASE64Encoder()).encode(raw);

        return hash;
    }

    /**
     * Metodo responsavel por encriptar as faixas de leitura
     *
     * [UC0627] Gerar Arquivo Texto Leiturista
     *
     * @author Pedro Alexandre
     * @date 21/09/2007
     *
     * @param str
     * @return
     */
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

    /**
     * The main program for the Criptografia class
     * 
     * @param args
     *            The command line arguments
     * @exception ErroCriptografiaException
     *                Descrição da exceção
     */
    public static void main(String[] args) throws ErroCriptografiaException {
        System.out.print(Criptografia.encriptarSenha("usuario"));
    }
}
