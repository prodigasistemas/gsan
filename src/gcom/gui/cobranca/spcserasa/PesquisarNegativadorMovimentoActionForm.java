package gcom.gui.cobranca.spcserasa;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0674] Pesquisar Movimento Negativador
 * 
 * @author Yara Taciane
 * @date 27/12/2007
 */
public class PesquisarNegativadorMovimentoActionForm extends
		ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String idNegativadorMovimento;

	private String codigoMovimento;
	
	private String tipoMovimento;	

	private String dataEnvio;

	private String dataProcessamentoInicial;
	private String dataProcessamentoFinal;

	private String dataRetorno;

	private String dataProcessamentoRetorno;

	private String numeroSequencialEnvio;

	private String numeroSequencialRetorno;

	private String numeroRegistrosEnvio;

	private String numeroRegistrosRetorno;

	private String valorTotalEnvio;

	private String ultimaAlteracao;

	private String idNegativador;
	private String negativadorCliente;

	private Collection colecaoNegativador;

	private String idNegativacaoComando;

	/**
	 * @return Retorna o campo codigoMovimento.
	 */
	public String getCodigoMovimento() {
		return codigoMovimento;
	}

	/**
	 * @param codigoMovimento O codigoMovimento a ser setado.
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
	 * @param dataEnvio O dataEnvio a ser setado.
	 */
	public void setDataEnvio(String dataEnvio) {
		this.dataEnvio = dataEnvio;
	}	

	/**
	 * @return Retorna o campo dataProcessamentoRetorno.
	 */
	public String getDataProcessamentoRetorno() {
		return dataProcessamentoRetorno;
	}

	/**
	 * @param dataProcessamentoRetorno O dataProcessamentoRetorno a ser setado.
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
	 * @param dataRetorno O dataRetorno a ser setado.
	 */
	public void setDataRetorno(String dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	/**
	 * @return Retorna o campo idNegativacaoComando.
	 */
	public String getIdNegativacaoComando() {
		return idNegativacaoComando;
	}

	/**
	 * @param idNegativacaoComando O idNegativacaoComando a ser setado.
	 */
	public void setIdNegativacaoComando(String idNegativacaoComando) {
		this.idNegativacaoComando = idNegativacaoComando;
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
	 * @return Retorna o campo idNegativadorMovimento.
	 */
	public String getIdNegativadorMovimento() {
		return idNegativadorMovimento;
	}

	/**
	 * @param idNegativadorMovimento O idNegativadorMovimento a ser setado.
	 */
	public void setIdNegativadorMovimento(String idNegativadorMovimento) {
		this.idNegativadorMovimento = idNegativadorMovimento;
	}

	/**
	 * @return Retorna o campo numeroRegistrosEnvio.
	 */
	public String getNumeroRegistrosEnvio() {
		return numeroRegistrosEnvio;
	}

	/**
	 * @param numeroRegistrosEnvio O numeroRegistrosEnvio a ser setado.
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
	 * @param numeroRegistrosRetorno O numeroRegistrosRetorno a ser setado.
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
	 * @param numeroSequencialEnvio O numeroSequencialEnvio a ser setado.
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
	 * @param numeroSequencialRetorno O numeroSequencialRetorno a ser setado.
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
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
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
	 * @param valorTotalEnvio O valorTotalEnvio a ser setado.
	 */
	public void setValorTotalEnvio(String valorTotalEnvio) {
		this.valorTotalEnvio = valorTotalEnvio;
	}
		
	
	/**
	 * @return Retorna o campo colecaoNegativador.
	 */
	public Collection getColecaoNegativador() {
		return colecaoNegativador;
	}

	/**
	 * @param colecaoNegativador O colecaoNegativador a ser setado.
	 */
	public void setColecaoNegativador(Collection colecaoNegativador) {
		this.colecaoNegativador = colecaoNegativador;
	}

	/**
	 * @return Retorna o campo tipoMovimento.
	 */
	public String getTipoMovimento() {
		return tipoMovimento;
	}

	/**
	 * @param tipoMovimento O tipoMovimento a ser setado.
	 */
	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	
	
	
	
	
	/**
	 * @return Retorna o campo dataProcessamentoFinal.
	 */
	public String getDataProcessamentoFinal() {
		return dataProcessamentoFinal;
	}

	/**
	 * @param dataProcessamentoFinal O dataProcessamentoFinal a ser setado.
	 */
	public void setDataProcessamentoFinal(String dataProcessamentoFinal) {
		this.dataProcessamentoFinal = dataProcessamentoFinal;
	}

	/**
	 * @return Retorna o campo dataProcessamentoInicial.
	 */
	public String getDataProcessamentoInicial() {
		return dataProcessamentoInicial;
	}

	/**
	 * @param dataProcessamentoInicial O dataProcessamentoInicial a ser setado.
	 */
	public void setDataProcessamentoInicial(String dataProcessamentoInicial) {
		this.dataProcessamentoInicial = dataProcessamentoInicial;
	}

	/**
	 * @return Retorna o campo negativadorCliente.
	 */
	public String getNegativadorCliente() {
		return negativadorCliente;
	}

	/**
	 * @param negativadorCliente O negativadorCliente a ser setado.
	 */
	public void setNegativadorCliente(String negativadorCliente) {
		this.negativadorCliente = negativadorCliente;
	}

	@Override
	public void reset(ActionMapping arg0, ServletRequest arg1) {
		
		super.reset(arg0, arg1);
		
		this.idNegativador="";		
		this.idNegativadorMovimento="";
		
		this.tipoMovimento = "";
		this.codigoMovimento="";
		this.dataEnvio="";
		this.dataProcessamentoInicial="";
		this.dataProcessamentoFinal="";
		this.dataRetorno="";
		this.dataProcessamentoRetorno="";
		this.numeroSequencialEnvio="";
		this.numeroSequencialRetorno="";
		this.numeroRegistrosEnvio="";
		this.numeroRegistrosRetorno="";
		this.valorTotalEnvio="";
		this.ultimaAlteracao="";
		this.idNegativador="";
		this.colecaoNegativador = new ArrayList();
		this.idNegativacaoComando = "";
		this.negativadorCliente = "";
		
	
	}
	
	
	
	
	

}
