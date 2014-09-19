package gcom.gui.cobranca.spcserasa;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Yara Taciane de Souza
 * @created 03/01/2008
 */

public class FiltrarNegativadorExclusaoMotivoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idNegativadorExclusaoMotivo;

	private String descricaoExclusaoMotivo;

	private String codigoMotivo;

	private String indicadorUso;

	private String ultimaAlteracao;

	private String idNegativador;

	private Collection collNegativador;

	private String idCobrancaDebitoSituacao;

	private Collection colecaoCobrancaDebitoSituacao;
	
	private String indicadorAtualizar;

	/**
	 * @return Retorna o campo codigoMotivo.
	 */
	public String getCodigoMotivo() {
		return codigoMotivo;
	}

	/**
	 * @param codigoMotivo O codigoMotivo a ser setado.
	 */
	public void setCodigoMotivo(String codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}

	/**
	 * @return Retorna o campo colecaoCobrancaDebitoSituacao.
	 */
	public Collection getColecaoCobrancaDebitoSituacao() {
		return colecaoCobrancaDebitoSituacao;
	}

	/**
	 * @param colecaoCobrancaDebitoSituacao O colecaoCobrancaDebitoSituacao a ser setado.
	 */
	public void setColecaoCobrancaDebitoSituacao(
			Collection colecaoCobrancaDebitoSituacao) {
		this.colecaoCobrancaDebitoSituacao = colecaoCobrancaDebitoSituacao;
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
	 * @return Retorna o campo descricaoExclusaoMotivo.
	 */
	public String getDescricaoExclusaoMotivo() {
		return descricaoExclusaoMotivo;
	}

	/**
	 * @param descricaoExclusaoMotivo O descricaoExclusaoMotivo a ser setado.
	 */
	public void setDescricaoExclusaoMotivo(String descricaoExclusaoMotivo) {
		this.descricaoExclusaoMotivo = descricaoExclusaoMotivo;
	}

	/**
	 * @return Retorna o campo idCobrancaDebitoSituacao.
	 */
	public String getIdCobrancaDebitoSituacao() {
		return idCobrancaDebitoSituacao;
	}

	/**
	 * @param idCobrancaDebitoSituacao O idCobrancaDebitoSituacao a ser setado.
	 */
	public void setIdCobrancaDebitoSituacao(String idCobrancaDebitoSituacao) {
		this.idCobrancaDebitoSituacao = idCobrancaDebitoSituacao;
	}

	/**
	 * @return Retorna o campo idNegativador.
	 */
	public String getIdNegativador() {
		return idNegativador;
	}

	/**
	 * @param idNegativador O idNegativador a ser setado.
	 */
	public void setIdNegativador(String idNegativador) {
		this.idNegativador = idNegativador;
	}

	/**
	 * @return Retorna o campo idNegativadorExclusaoMotivo.
	 */
	public String getIdNegativadorExclusaoMotivo() {
		return idNegativadorExclusaoMotivo;
	}

	/**
	 * @param idNegativadorExclusaoMotivo O idNegativadorExclusaoMotivo a ser setado.
	 */
	public void setIdNegativadorExclusaoMotivo(String idNegativadorExclusaoMotivo) {
		this.idNegativadorExclusaoMotivo = idNegativadorExclusaoMotivo;
	}

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	
	
	
	
	/**
	 * @return Retorna o campo indicadorAtualizar.
	 */
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	/**
	 * @param indicadorAtualizar O indicadorAtualizar a ser setado.
	 */
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	public void reset(ActionMapping arg0, ServletRequest arg1) {
		
		super.reset(arg0, arg1);

		this.idNegativadorExclusaoMotivo = "";
		this.codigoMotivo = "";
		this.descricaoExclusaoMotivo = "";
		this.indicadorUso = "1";
		this.ultimaAlteracao = "";
		this.idNegativador = "";
		this.collNegativador = new ArrayList();
		this.idCobrancaDebitoSituacao = "";
		this.colecaoCobrancaDebitoSituacao = new ArrayList();
		this.ultimaAlteracao = "";
		this.indicadorAtualizar="1";

	}

	
	
}

