package gcom.gui.cobranca.spcserasa;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * @author Thiago Toscano 
 * @date 18/12/2006
 */
public class ExcluirNegativacaoOnLineActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	
	private Collection collNegativador = new ArrayList();
	private String negativador = "";
	private String negativadorAnterior = "";
	private String idImovel  = "";
	private String idImovelAnterior  = "";
	private String inscricaoImovel  = "";
	private String existeImovel = "";
	private String dataHoje = "";
	private String dataEnvio = "";
	
	
	private Collection collMotivoExclusao = new ArrayList();
	private String motivoExclusao = "";
	private String dataExclusao = "";
	private String idUsuario = "";
	private String nomeUsuario = "";
	private String usuarioNaoEncontrada = "";

	/**
	 *
	 * @author Thiago Toscano
	 * @date 22/01/2008
	 *
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		
		super.reset(arg0, arg1);

		collNegativador = new ArrayList();
		negativador = "";
		idImovel  = "";
		inscricaoImovel  = "";
		existeImovel = "";
		idImovelAnterior = "";
		negativadorAnterior = "";
		dataHoje = "";
		dataEnvio = "";

		collMotivoExclusao = new ArrayList();
		motivoExclusao = "";
		dataExclusao = "";
		idUsuario = "";
		nomeUsuario = "";
		usuarioNaoEncontrada = "";
	}
	
	public String getNegativadorAnterior() {
		return negativadorAnterior;
	}

	public void setNegativadorAnterior(String negativadorAnterior) {
		this.negativadorAnterior = negativadorAnterior;
	}

	/**
	 * @return Retorna o campo dataEnvio.
	 */
	public String getDataEnvio() {
		return dataEnvio;
	}

	/**
	 * @param dataEnvio O dataEnvio a ser setado.
	 */
	public void setDataEnvio(String dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	/**
	 * @return Retorna o campo dataHoje.
	 */
	public String getDataHoje() {
		return dataHoje;
	}

	/**
	 * @param dataHoje O dataHoje a ser setado.
	 */
	public void setDataHoje(String dataHoje) {
		this.dataHoje = dataHoje;
	}



	/**
	 * @return Retorna o campo idImovelAnterior.
	 */
	public String getIdImovelAnterior() {
		return idImovelAnterior;
	}

	/**
	 * @param idImovelAnterior O idImovelAnterior a ser setado.
	 */
	public void setIdImovelAnterior(String idImovelAnterior) {
		this.idImovelAnterior = idImovelAnterior;
	}

	/**
	 * @return Retorna o campo collMotivoExclusao.
	 */
	public Collection getCollMotivoExclusao() {
		return collMotivoExclusao;
	}

	/**
	 * @param collMotivoExclusao O collMotivoExclusao a ser setado.
	 */
	public void setCollMotivoExclusao(Collection collMotivoExclusao) {
		this.collMotivoExclusao = collMotivoExclusao;
	}

	/**
	 * @return Retorna o campo dataExclusao.
	 */
	public String getDataExclusao() {
		return dataExclusao;
	}

	/**
	 * @param dataExclusao O dataExclusao a ser setado.
	 */
	public void setDataExclusao(String dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	/**
	 * @return Retorna o campo idUsuario.
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario O idUsuario a ser setado.
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}



	/**
	 * @return Retorna o campo motivoExclusao.
	 */
	public String getMotivoExclusao() {
		return motivoExclusao;
	}



	/**
	 * @param motivoExclusao O motivoExclusao a ser setado.
	 */
	public void setMotivoExclusao(String motivoExclusao) {
		this.motivoExclusao = motivoExclusao;
	}



	/**
	 * @return Retorna o campo nomeUsuario.
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}



	/**
	 * @param nomeUsuario O nomeUsuario a ser setado.
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}



	/**
	 * @return Retorna o campo usuarioNaoEncontrada.
	 */
	public String getUsuarioNaoEncontrada() {
		return usuarioNaoEncontrada;
	}



	/**
	 * @param usuarioNaoEncontrada O usuarioNaoEncontrada a ser setado.
	 */
	public void setUsuarioNaoEncontrada(String usuarioNaoEncontrada) {
		this.usuarioNaoEncontrada = usuarioNaoEncontrada;
	}

	/**
	 * @return Retorna o campo collNegativador.
	 */
	public Collection getCollNegativador() {
		return collNegativador;
	}

	/**
	 * @param collNegativador O collNegativador a ser setado.
	 */
	public void setCollNegativador(Collection collNegativador) {
		this.collNegativador = collNegativador;
	}

	/**
	 * @return Retorna o campo inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	/**
	 * @param inscricaoImovel O inscricaoImovel a ser setado.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}


	/**
	 * @return Retorna o campo existeImovel.
	 */
	public String getExisteImovel() {
		return existeImovel;
	}

	/**
	 * @param existeImovel O existeImovel a ser setado.
	 */
	public void setExisteImovel(String existeImovel) {
		this.existeImovel = existeImovel;
	}

	/**
	 * @return Retorna o campo negativador.
	 */
	public String getNegativador() {
		return negativador;
	}

	/**
	 * @param negativador O negativador a ser setado.
	 */
	public void setNegativador(String negativador) {
		this.negativador = negativador;
	}
}
