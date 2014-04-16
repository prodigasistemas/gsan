package gcom.gui.cobranca.spcserasa;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form responsável pelas propiedades do caso de uso 
 * [UC0662] Inserir Motivo de Retorno do Registro do Negativador
 * 
 * @author Thiago Vieira
 * @date 03/01/2008
 */
public class FiltrarNegativadorRetornoMotivoActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String idNegativador;

	private Collection colecaoNegativador;
	
	private String codigoMotivo;

	private String descricaoRetornoMotivo;

	private String indicadorUso;

	private String indicadorRegistroAceito;

	private String ultimaAlteracao;
	
	private String corCodigoMotivo;
	
	private String mensagemCodigoMotivo;
	
	private String okCodigoMotivo;
	
	private String checkAtualizar;

	//private String negativadorMovimentoRegRetMot;

	public void reset(ActionMapping arg0, ServletRequest arg1) {
		super.reset(arg0, arg1);

		this.idNegativador = "";
		this.colecaoNegativador = new ArrayList();
		this.codigoMotivo ="";
		this.descricaoRetornoMotivo = "";
		this.indicadorUso = "";
		this.indicadorRegistroAceito = "";
		this.ultimaAlteracao = "";
		this.corCodigoMotivo = "";
		this.mensagemCodigoMotivo = "";
		this.okCodigoMotivo = "";
		this.checkAtualizar = "";
	}

	/**
	 * @return Retorna o campo colecaoNegativador.
	 */
	public Collection getColecaoNegativador() {
		return colecaoNegativador;
	}

	/**
	 * @return Retorna o campo corCodigoMotivo.
	 */
	public String getCorCodigoMotivo() {
		return corCodigoMotivo;
	}

	/**
	 * @param corCodigoMotivo O corCodigoMotivo a ser setado.
	 */
	public void setCorCodigoMotivo(String corCodigoMotivo) {
		this.corCodigoMotivo = corCodigoMotivo;
	}

	/**
	 * @return Retorna o campo mensagemCodigoMotivo.
	 */
	public String getMensagemCodigoMotivo() {
		return mensagemCodigoMotivo;
	}

	/**
	 * @param mensagemCodigoMotivo O mensagemCodigoMotivo a ser setado.
	 */
	public void setMensagemCodigoMotivo(String mensagemCodigoMotivo) {
		this.mensagemCodigoMotivo = mensagemCodigoMotivo;
	}

	/**
	 * @param colecaoNegativador O colecaoNegativador a ser setado.
	 */
	public void setColecaoNegativador(Collection colecaoNegativador) {
		this.colecaoNegativador = colecaoNegativador;
	}

	/**
	 * @return Retorna o campo descricaoRetornoCodigo.
	 */
	public String getDescricaoRetornoMotivo() {
		return descricaoRetornoMotivo;
	}

	/**
	 * @param descricaoRetornoCodigo O descricaoRetornoCodigo a ser setado.
	 */
	public void setDescricaoRetornoMotivo(String descricaoRetornoMotivo) {
		this.descricaoRetornoMotivo = descricaoRetornoMotivo;
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
	 * @return Retorna o campo indicadorRegistroAceito.
	 */
	public String getIndicadorRegistroAceito() {
		return indicadorRegistroAceito;
	}

	/**
	 * @param indicadorRegistroAceito O indicadorRegistroAceito a ser setado.
	 */
	public void setIndicadorRegistroAceito(String indicadorRegistroAceito) {
		this.indicadorRegistroAceito = indicadorRegistroAceito;
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
	 * @return Retorna o campo okCodigoMotivo.
	 */
	public String getOkCodigoMotivo() {
		return okCodigoMotivo;
	}

	/**
	 * @param okCodigoMotivo O okCodigoMotivo a ser setado.
	 */
	public void setOkCodigoMotivo(String okCodigoMotivo) {
		this.okCodigoMotivo = okCodigoMotivo;
	}

	/**
	 * @return Retorna o campo checkAtualizar.
	 */
	public String getCheckAtualizar() {
		return checkAtualizar;
	}

	/**
	 * @param checkAtualizar O checkAtualizar a ser setado.
	 */
	public void setCheckAtualizar(String checkAtualizar) {
		this.checkAtualizar = checkAtualizar;
	}

	

}
