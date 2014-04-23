package gcom.gui.faturamento;

import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
 * Form do Manter Débtio a Cobrar do Imovel
 * 
 * @author Rafael Santos
 * @since 29/15/2005 [UC0184] Manter Débito A Cobrar
 */
public class ManterDebitoACobrarActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String inscricaoImovel;

	private String nomeCliente;

	private String situacaoAgua;

	private String situacaoEsgoto;

	private String codigoImovel;
	
	private String[] idDebitoACobrar;

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
	}

	/**
	 * @return Returns the codigoImovel.
	 */
	public String getCodigoImovel() {
		return codigoImovel;
	}

	/**
	 * @param codigoImovel
	 *            The codigoImovel to set.
	 */
	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	/**
	 * @return Returns the inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	/**
	 * @param inscricaoImovel
	 *            The inscricaoImovel to set.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Returns the nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente
	 *            The nomeCliente to set.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Returns the situacaoAgua.
	 */
	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	/**
	 * @param situacaoAgua
	 *            The situacaoAgua to set.
	 */
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	/**
	 * @return Returns the situacaoEsgoto.
	 */
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	/**
	 * @param situacaoEsgoto
	 *            The situacaoEsgoto to set.
	 */
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	/**
	 * @return Returns the idDebitoACobrar.
	 */
	public String[] getIdDebitoACobrar() {
		return idDebitoACobrar;
	}

	/**
	 * @param idDebitoACobrar The idDebitoACobrar to set.
	 */
	public void setIdDebitoACobrar(String[] idDebitoACobrar) {
		this.idDebitoACobrar = idDebitoACobrar;
	}

}
