package gcom.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Representa um problema no nível da fachada
 * 
 * @author rodrigo
 */
public class FachadaException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> parametrosMensagem = new ArrayList();

	private String parametroMensagem;
	
	private String urlBotaoVoltar;


	/**
	 * @param mensagem
	 * @param excecaoCausa
	 */
	public FachadaException(String mensagem, Exception excecaoCausa) {
		super(mensagem, excecaoCausa);

	}

	/**
	 * @param mensagem
	 */
	public FachadaException(String mensagem) {
		this(mensagem, null);
	}

	/**
	 * @param mensagem
	 * @param excecaoCausa
	 * @param parametroMensagem
	 */
	public FachadaException(String mensagem, Exception excecaoCausa,
			List<String> parametrosMensagem) {
		super(mensagem, excecaoCausa);
		this.parametrosMensagem = (ArrayList) parametrosMensagem;

	}

	public FachadaException(String mensagem, Exception excecaoCausa,
			String parametroMensagem) {
		super(mensagem, excecaoCausa);
		this.parametroMensagem = parametroMensagem;

	}
	
	/**
	 * @param mensagem
	 * @param excecaoCausa
	 * @param parametroMensagem
	 */
	public FachadaException(String mensagem,String urlBotaoVoltar, Exception excecaoCausa,
			List<String> parametrosMensagem) {
		super(mensagem, excecaoCausa);
		this.urlBotaoVoltar = urlBotaoVoltar;
		this.parametrosMensagem = (ArrayList) parametrosMensagem;

	}


	public List<String> getParametroMensagem() {
		ArrayList<String> list = new ArrayList();
		list.addAll(parametrosMensagem);
		if (parametroMensagem != null && !parametroMensagem.trim().equals("")) {
			list.add(parametroMensagem);
		}
		return list;
	}

	public String getParametroMensagem(int numeroMensagem) {
		return getParametroMensagem().get(numeroMensagem);

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
