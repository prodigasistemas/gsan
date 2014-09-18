package gcom.util.validacao;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.DSAPublicKeySpec;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sun.misc.BASE64Decoder;





/**
 * 
 * Classe respons�vel por validar assinaturas de algoritmo DSA a partir de uma chave p�blica
 * em formato XML.
 * 
 * @author Hugo Azevedo
 * @date 02/05/2011
 * 
 */
public class AssinaturaDSA {

	
	//Arquivo XML da chave p�blica
	private String arquivoChavePublica;
	
	//Chave p�blica gerada ao se instanciar a classe
	private PublicKey chavePublica;
	
	
	public AssinaturaDSA(String arquivoChavePublica){
		this.arquivoChavePublica = arquivoChavePublica;
		this.chavePublica = this.getChavePublica();
	}
	
	
	public String getArquivoChavePublica() {
		return arquivoChavePublica;
	}


	public void setArquivoChavePublica(String arquivoChavePublica) {
		this.arquivoChavePublica = arquivoChavePublica;
	}

	
	
	/**
	 * M�todo respons�vel por validar um par hash/assinatura a partir
	 * da chave p�blica gerada pela classe
	 * 
	 * @param hash - Hash que ser� validada
	 * @param assinatura - Assinatura que ser� utilizada pelo algoritmo para validar a hash 
	 * @return Indicador se a hash � v�lida ou n�o
	 * 
	 **/
	public boolean validarHash(byte[] hash, byte[] assinatura) {
		boolean retorno = false;
		try {
			
			//Define a assinatura do cliente e o algoritmo utilizado para a descripta��o
			Signature assinaturaCliente = Signature.getInstance("SHA1withDSA");
			
			//Define a chave p�blica
			assinaturaCliente.initVerify(this.chavePublica);
			
			//Define o dado a ser comparada
			assinaturaCliente.update(hash);
			
			//Convertendo para o formato DER, entendido por Java
			
			byte[] assinaturaDecodificada = Base64.decodeBase64(assinatura);
			
			
	       // boolean retorno = verificador.validarHash(usur_nmlogin.getBytes(),verificador.ConvertToDsaSignatureToJavaEncoding(decoded));
		
			//Compara a assinatura do cliente com a passada por par�metro pelo usu�rio, para valida��o
			//A assinatura gerada corretamente pelo C# cont�m exatos 40 bytes.
			if(assinaturaDecodificada.length == 40 && assinaturaCliente.verify(ConvertToDsaSignatureToJavaEncoding(assinaturaDecodificada))){
				retorno = true;
			}
			else{
				retorno = false;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			
			e.printStackTrace();
		} catch (SignatureException e) {
			
			e.printStackTrace();
		}
		
		return retorno;

	}
	
	
	
	/**
	 * M�todo respons�vel por gerar uma chave p�blica a partir do arquivo XML
	 *  
	 * @return PublicKey - Classe de chave p�blica
	 * 
	 **/
	private PublicKey getChavePublica(){
		
		PublicKey chave = null;
		try{
			
			BigInteger Q = this.getParametroChave("Q");
	        BigInteger P = this.getParametroChave("P");
	        BigInteger G = this.getParametroChave("G");
	        BigInteger Y = this.getParametroChave("Y");
	        
	        DSAPublicKeySpec dsaPublicKeySpec = new DSAPublicKeySpec(Y,P,Q,G);
	        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
	        chave = keyFactory.generatePublic(dsaPublicKeySpec);
	 	        
		}
		
		catch(Exception ex){
		}
		return chave;
		
	}
	
	
	/**
	 * M�todo respons�vel retornar o valor de uma DOM do arquivo XML da chave p�blica
	 *  
	 * @param paramDSA - DOM que ter� o valor retornado
	 * @return BigInteger - Valor da DOM
	 * 
	 **/
	
	
	private BigInteger getParametroChave(String paramDSA){
		
		
		BigInteger retorno = null;
		try{
			
			//Leitura do Arquivo da chave p�blica
			File chavePublicaXML = new File(this.arquivoChavePublica);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
	        Document documentoXML = builder.parse(chavePublicaXML);
	      
	        
	        //Recupera o valor da tag passada por par�metro
	        NodeList listaNos = documentoXML.getElementsByTagName(paramDSA);  
	        Element no = (Element)listaNos.item(0);
            NodeList textFNList = no.getChildNodes();
            String chave = ((Node)textFNList.item(0)).getNodeValue().trim();
            byte[] chaveDecodificada = new BASE64Decoder().decodeBuffer(new ByteArrayInputStream(chave.getBytes("UTF-8")));
           
            
            retorno = new BigInteger(1,chaveDecodificada);
	        
	        
		}
		catch(Exception ex){
			  //String msg = ex.getMessage();
		    
		}
		
		return retorno;
	}
	
	
	/**
	 * 
	 * M�todo para transformar a assinatura de formato
	 * IEEE P1363 (C#) em DER (Java) 
	 * 
	 * @param dsa - Array de bytes da assinatura original, em formato p1363
	 * @return Assinatura em formato der
	 * 
	 **/
	
	 public byte[] ConvertToDsaSignatureToJavaEncoding(byte[] dsa){
		  if(dsa.length !=40)
			  System.out.println("Exce��o");
		  byte[] r = new byte[20];
		  System.arraycopy(dsa, 0, r, 0, 20);
		  byte[] s = new byte[20];
		  System.arraycopy(dsa, 20, s, 0, 20);

		  // Convert to complement-2
		  byte[] complementTwoR = ToComplementTwo(r);
		  byte[] complementTwoS = ToComplementTwo(s);

		  // Build the result
		  byte[] res = new byte[complementTwoR.length + complementTwoS.length + 6];
		  // Sequence{
		  res[0] = 0x30;
		  res[1] = (byte) (complementTwoR.length + complementTwoS.length + 4);
		  // Integer (R)
		  res[2] = 0x02;
		  res[3] = (byte) complementTwoR.length;
		  System.arraycopy(complementTwoR, 0, res, 4, complementTwoR.length);
		  // Integer (S)
		  res[complementTwoR.length + 4] = 0x02;
		  res[complementTwoR.length + 5] = (byte) complementTwoS.length;
		  System.arraycopy(complementTwoS, 0, res, complementTwoR.length + 6, complementTwoS.length);

		  return res;
		}

	public byte[] ToComplementTwo(byte[] d){
		// Ensure the top-bit is zero, otherwise remove unneeded zeroes
		// - Find non-zero byte
		int i = 0;
		while (i < d.length && d[i] == 0) i++;
		//- Do we need an extra byte
		int extraByte = (d[i] & 0x80) == 1 ? 1 : 0;
		// - Build the result
		byte[] res = new byte[d.length-i+extraByte];
		System.arraycopy(d, i, res, extraByte, d.length-i);
		return res;
	}
	 

	
	public static void main(String[] arg0){
		
		
		AssinaturaDSA verificador = new AssinaturaDSA("C:\\Encrypt\\DSA_PROGIS_Publica.xml");
		
		/*
		 * Caso de teste
		 * 
		 */ 
		 
		String usur_nmlogin = "25187372947";
		String sign = "1EmixF+4qXfVogAOwbyJrhLvN12vNFmCEij+7suJiJigh2V+M65wKg==";

		
	    boolean retorno = verificador.validarHash(usur_nmlogin.getBytes(),sign.getBytes());
		
	    if(retorno){
			System.out.println("LOGIN V�LIDO");
		}
		else{
			System.out.println("LOGIN INV�LIDO");
		}
        
		         
		
		
	}
	
	
}
