package gcom.util;

public class ParserUtil {

	private int contador = 0;

	private String fonte;

	public ParserUtil(String fonte) {
		this.fonte = fonte;
	}

	public String obterDadoParserTrim(int tamanho) {
		return obterDadoParser(tamanho).trim();
	}

	public String obterDadoParser(int tamanho) {
		int posicaoInicial = contador;
		contador += tamanho;

		if (contador <= fonte.length()) {
			return fonte.substring(posicaoInicial, contador);
		} else {
			return null;
		}
	}
	
	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
}
