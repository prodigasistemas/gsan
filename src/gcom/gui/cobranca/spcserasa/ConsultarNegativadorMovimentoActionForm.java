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

public class ConsultarNegativadorMovimentoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String idNegativador;

	private Collection collNegativador;

	private String codigoMovimento;

	private String dataEnvio;

	private String horaEnvio;

	private String dataProcessamentoEnvio;

	private String dataRetorno;

	private String horaRetorno;

	private String dataProcessamentoRetorno;

	private String numeroSequencialEnvio;

	private String numeroSequencialRetorno;

	private String numeroRegistrosEnvio;

	private String numeroRegistrosRetorno;

	private String valorTotalEnvio;

	private String ultimaAlteracao;

	private String negativador;

	private String situacaoMovimento;

	private String totalRegistrosAceitos;
	
	private Collection collNegativadorMovimentoReg;
	private String negativadorMovimentoReg;
	
	private String indicadorCorrecao;

    private String[] idRegistrosCorrecao;
	
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
	 * @return Retorna o campo codigoMovimento.
	 */
	public String getCodigoMovimento() {
		return codigoMovimento;
	}

	/**
	 * @param codigoMovimento
	 *            O codigoMovimento a ser setado.
	 */
	public void setCodigoMovimento(String codigoMovimento) {
		this.codigoMovimento = codigoMovimento;
	}

	/**
	 * @return Retorna o campo dataEnvio.
	 */
	public String getDataEnvio() {
		return dataEnvio;
	}

	/**
	 * @param dataEnvio
	 *            O dataEnvio a ser setado.
	 */
	public void setDataEnvio(String dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	/**
	 * @return Retorna o campo dataProcessamentoEnvio.
	 */
	public String getDataProcessamentoEnvio() {
		return dataProcessamentoEnvio;
	}

	/**
	 * @param dataProcessamentoEnvio
	 *            O dataProcessamentoEnvio a ser setado.
	 */
	public void setDataProcessamentoEnvio(String dataProcessamentoEnvio) {
		this.dataProcessamentoEnvio = dataProcessamentoEnvio;
	}

	/**
	 * @return Retorna o campo dataProcessamentoRetorno.
	 */
	public String getDataProcessamentoRetorno() {
		return dataProcessamentoRetorno;
	}

	/**
	 * @param dataProcessamentoRetorno
	 *            O dataProcessamentoRetorno a ser setado.
	 */
	public void setDataProcessamentoRetorno(String dataProcessamentoRetorno) {
		this.dataProcessamentoRetorno = dataProcessamentoRetorno;
	}

	/**
	 * @return Retorna o campo dataRetorno.
	 */
	public String getDataRetorno() {
		return dataRetorno;
	}

	/**
	 * @param dataRetorno
	 *            O dataRetorno a ser setado.
	 */
	public void setDataRetorno(String dataRetorno) {
		this.dataRetorno = dataRetorno;
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
	 * @return Retorna o campo negativador.
	 */
	public String getNegativador() {
		return negativador;
	}

	/**
	 * @param negativador
	 *            O negativador a ser setado.
	 */
	public void setNegativador(String negativador) {
		this.negativador = negativador;
	}

	/**
	 * @return Retorna o campo numeroRegistrosEnvio.
	 */
	public String getNumeroRegistrosEnvio() {
		return numeroRegistrosEnvio;
	}

	/**
	 * @param numeroRegistrosEnvio
	 *            O numeroRegistrosEnvio a ser setado.
	 */
	public void setNumeroRegistrosEnvio(String numeroRegistrosEnvio) {
		this.numeroRegistrosEnvio = numeroRegistrosEnvio;
	}

	/**
	 * @return Retorna o campo numeroRegistrosRetorno.
	 */
	public String getNumeroRegistrosRetorno() {
		return numeroRegistrosRetorno;
	}

	/**
	 * @param numeroRegistrosRetorno
	 *            O numeroRegistrosRetorno a ser setado.
	 */
	public void setNumeroRegistrosRetorno(String numeroRegistrosRetorno) {
		this.numeroRegistrosRetorno = numeroRegistrosRetorno;
	}

	/**
	 * @return Retorna o campo numeroSequencialEnvio.
	 */
	public String getNumeroSequencialEnvio() {
		return numeroSequencialEnvio;
	}

	/**
	 * @param numeroSequencialEnvio
	 *            O numeroSequencialEnvio a ser setado.
	 */
	public void setNumeroSequencialEnvio(String numeroSequencialEnvio) {
		this.numeroSequencialEnvio = numeroSequencialEnvio;
	}

	/**
	 * @return Retorna o campo numeroSequencialRetorno.
	 */
	public String getNumeroSequencialRetorno() {
		return numeroSequencialRetorno;
	}

	/**
	 * @param numeroSequencialRetorno
	 *            O numeroSequencialRetorno a ser setado.
	 */
	public void setNumeroSequencialRetorno(String numeroSequencialRetorno) {
		this.numeroSequencialRetorno = numeroSequencialRetorno;
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
	 * @return Retorna o campo valorTotalEnvio.
	 */
	public String getValorTotalEnvio() {
		return valorTotalEnvio;
	}

	/**
	 * @param valorTotalEnvio
	 *            O valorTotalEnvio a ser setado.
	 */
	public void setValorTotalEnvio(String valorTotalEnvio) {
		this.valorTotalEnvio = valorTotalEnvio;
	}

	/**
	 * @return Retorna o campo situacaoMovimento.
	 */
	public String getSituacaoMovimento() {
		return situacaoMovimento;
	}

	/**
	 * @param situacaoMovimento
	 *            O situacaoMovimento a ser setado.
	 */
	public void setSituacaoMovimento(String situacaoMovimento) {
		this.situacaoMovimento = situacaoMovimento;
	}

	/**
	 * @return Retorna o campo totalRegistrosAceitos.
	 */
	public String getTotalRegistrosAceitos() {
		return totalRegistrosAceitos;
	}

	/**
	 * @param totalRegistrosAceitos
	 *            O totalRegistrosAceitos a ser setado.
	 */
	public void setTotalRegistrosAceitos(String totalRegistrosAceitos) {
		this.totalRegistrosAceitos = totalRegistrosAceitos;
	}

	/**
	 * @return Retorna o campo horaRetorno.
	 */
	public String getHoraRetorno() {
		return horaRetorno;
	}

	/**
	 * @param horaRetorno
	 *            O horaRetorno a ser setado.
	 */
	public void setHoraRetorno(String horaRetorno) {
		this.horaRetorno = horaRetorno;
	}

	/**
	 * @return Retorna o campo horaEnvio.
	 */
	public String getHoraEnvio() {
		return horaEnvio;
	}

	/**
	 * @param horaEnvio
	 *            O horaEnvio a ser setado.
	 */
	public void setHoraEnvio(String horaEnvio) {
		this.horaEnvio = horaEnvio;
	}
	

	/**
	 * @return Retorna o campo collNegativadorMovimentoReg.
	 */
	public Collection getCollNegativadorMovimentoReg() {
		return collNegativadorMovimentoReg;
	}

	/**
	 * @param collNegativadorMovimentoReg O collNegativadorMovimentoReg a ser setado.
	 */
	public void setCollNegativadorMovimentoReg(
			Collection collNegativadorMovimentoReg) {
		this.collNegativadorMovimentoReg = collNegativadorMovimentoReg;
	}

	/**
	 * @return Retorna o campo negativadorMovimentoReg.
	 */
	public String getNegativadorMovimentoReg() {
		return negativadorMovimentoReg;
	}

	/**
	 * @param negativadorMovimentoReg O negativadorMovimentoReg a ser setado.
	 */
	public void setNegativadorMovimentoReg(String negativadorMovimentoReg) {
		this.negativadorMovimentoReg = negativadorMovimentoReg;
	}

	public void reset(ActionMapping arg0, ServletRequest arg1) {
		
		super.reset(arg0, arg1);

		this.idNegativador = "";
		this.collNegativador = new ArrayList();
		this.codigoMovimento = "";
		this.dataEnvio = "";
		this.dataProcessamentoEnvio = "";
		this.dataRetorno = "";
		this.dataProcessamentoRetorno = "";
		this.numeroSequencialEnvio = "";
		this.numeroSequencialRetorno = "";
		this.numeroRegistrosEnvio = "";
		this.numeroRegistrosRetorno = "";
		this.valorTotalEnvio = "";
		this.ultimaAlteracao = "";
		this.negativador = "";
		this.situacaoMovimento = "";
		this.totalRegistrosAceitos = "";
		this.horaRetorno = "";
		this.horaEnvio = "";
		this.collNegativadorMovimentoReg=new ArrayList();
		this.negativadorMovimentoReg = "";

	}

	public String getIndicadorCorrecao() {
		return indicadorCorrecao;
	}

	public void setIndicadorCorrecao(String indicadorCorrecao) {
		this.indicadorCorrecao = indicadorCorrecao;
	}

	public String[] getIdRegistrosCorrecao() {
		return idRegistrosCorrecao;
	}

	public void setIdRegistrosCorrecao(String[] idRegistrosCorrecao) {
		this.idRegistrosCorrecao = idRegistrosCorrecao;
	}

	
}
