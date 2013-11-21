/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gcom.util;

/**
 * 
 * @author gcom
 */
public class ParserUtil {

	private int contador = 0;

	private String fonte;

	/**
	 * 
	 * @param fonte
	 */
	public ParserUtil(String fonte) {
		this.fonte = fonte;
	}

	public String obterDadoParserTrim(int tamanho) {
		return obterDadoParser(tamanho).trim();
	}
	
	/**
	 * 
	 * @param tamanho
	 * @return
	 */
	public String obterDadoParser(int tamanho) {
		int posicaoInicial = contador;
		contador += tamanho;
		return fonte.substring(posicaoInicial, contador);

	}

	/**
	 * 
	 * @return
	 */
	public String getFonte() {
		return fonte;
	}

	/**
	 * 
	 * @param fonte
	 */
	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	/**
	 * 
	 * @return
	 */
	public int getContador() {
		return contador;
	}

	/**
	 * 
	 * @param contador
	 */
	public void setContador(int contador) {
		this.contador = contador;
	}
}
