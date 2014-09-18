package gcom.gui.cobranca.spcserasa;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Yara Taciane de Souza
 * @created 07/01/2008
 */

public class FiltrarNegativadorRegistroTipoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String idNegativadorRegistroTipo;

	private String descricaoRegistroTipo;

	private String codigoRegistro;

	private String ultimaAlteracao;

	private String idNegativador;

	private Collection collNegativador;

	private String indicadorAtualizar;

	/**
	 * @return Retorna o campo codigoRegistro.
	 */
	public String getCodigoRegistro() {
		return codigoRegistro;
	}

	/**
	 * @param codigoRegistro
	 *            O codigoRegistro a ser setado.
	 */
	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	/**
	 * @return Retorna o campo collNegativador.
	 */
	public Collection getCollNegativador() {
		return collNegativador;
	}

	/**
	 * @param collNegativador
	 *            O collNegativador a ser setado.
	 */
	public void setCollNegativador(Collection collNegativador) {
		this.collNegativador = collNegativador;
	}

	/**
	 * @return Retorna o campo descricaoRegistroTipo.
	 */
	public String getDescricaoRegistroTipo() {
		return descricaoRegistroTipo;
	}

	/**
	 * @param descricaoRegistroTipo
	 *            O descricaoRegistroTipo a ser setado.
	 */
	public void setDescricaoRegistroTipo(String descricaoRegistroTipo) {
		this.descricaoRegistroTipo = descricaoRegistroTipo;
	}

	/**
	 * @return Retorna o campo idNegativador.
	 */
	public String getIdNegativador() {
		return idNegativador;
	}

	/**
	 * @param idNegativador
	 *            O idNegativador a ser setado.
	 */
	public void setIdNegativador(String idNegativador) {
		this.idNegativador = idNegativador;
	}

	/**
	 * @return Retorna o campo idNegativadorRegistroTipo.
	 */
	public String getIdNegativadorRegistroTipo() {
		return idNegativadorRegistroTipo;
	}

	/**
	 * @param idNegativadorRegistroTipo
	 *            O idNegativadorRegistroTipo a ser setado.
	 */
	public void setIdNegativadorRegistroTipo(String idNegativadorRegistroTipo) {
		this.idNegativadorRegistroTipo = idNegativadorRegistroTipo;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao
	 *            O ultimaAlteracao a ser setado.
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
	 * @param indicadorAtualizar
	 *            O indicadorAtualizar a ser setado.
	 */
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	public void reset(ActionMapping arg0, ServletRequest arg1) {
		
		super.reset(arg0, arg1);

		this.idNegativadorRegistroTipo = "";
		this.codigoRegistro = "";
		this.descricaoRegistroTipo = "";
		this.ultimaAlteracao = "";
		this.idNegativador = "";
		this.collNegativador = new ArrayList();
		this.ultimaAlteracao = "";
		this.indicadorAtualizar = "";

	}

}
