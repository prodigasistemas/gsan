package gcom.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Representa um problema no nível do controlador
 * 
 * @author rodrigo
 */
public class ControladorException extends Exception {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> parametrosMensagem = new ArrayList();

	private String parametroMensagem;
	
	private String urlBotaoVoltar;
	

	/**
	 * Construtor da classe ControladorException
	 * 
	 * @param mensagem
	 *            Chave do erro que aconteceu no controlador(mensagem obtida num
	 *            arquivo de properties)
	 * @param excecaoCausa
	 *            Exceção que originou o problema
	 */
	public ControladorException(String mensagem, Throwable excecaoCausa) {
		super(mensagem, excecaoCausa);

	}
	
	public ControladorException(String mensagem,String urlBotaoVoltar, Throwable excecaoCausa) {
		this(mensagem,excecaoCausa);
		this.urlBotaoVoltar = urlBotaoVoltar;
	}

	/**
	 * Construtor da classe ControladorException
	 * 
	 * @param mensagem
	 *            Descrição do parâmetro
	 */
	public ControladorException(String mensagem) {
		
		//super(mensagem);
		this(mensagem,null);
	}

	/**
	 * Construtor da classe ControladorException
	 * 
	 * @param mensagem
	 *            Chave do erro que aconteceu no controlador(mensagem obtida num
	 *            arquivo de properties)
	 * @param excecaoCausa
	 *            Exceção que originou o problema
	 * @param parametroMensagem
	 *            Descrição do parâmetro
	 */
	public ControladorException(String mensagem, Throwable excecaoCausa,
			String... parametroMensagem) {
		super(mensagem, excecaoCausa);
		parametrosMensagem.addAll(Arrays.asList(parametroMensagem));

	}

	
	
	public ControladorException(String mensagem, Throwable excecaoCausa,
			String parametroMensagem) {
		super(mensagem, excecaoCausa);
		this.parametroMensagem = parametroMensagem;

	}
	

	public List<String> getParametroMensagem() {
		ArrayList<String> list = new ArrayList();
		list.addAll(parametrosMensagem);
		if (parametroMensagem != null && !parametroMensagem.trim().equals("")) {
			list.add(parametroMensagem);
		}
		return list;
	}

	public void setParametroMensagem(String... parametroMensagem) {
		parametrosMensagem.addAll(Arrays.asList(parametroMensagem));
	}
	
	public String getUrlBotaoVoltar() {
		return urlBotaoVoltar;
	}

	public void setUrlBotaoVoltar(String urlBotaoVoltar) {
		this.urlBotaoVoltar = urlBotaoVoltar;
	}


}
