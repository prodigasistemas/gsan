package gcom.gui.arrecadacao.aviso;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade gerar o formulário que irá apresentar a análise do aviso bancário e os
 * pagamentos/devoluções associados
 *
 * @author Raphael Rossiter
 * @date 23/03/2006
 */
public class ApresentarAnaliseAvisoBancarioActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idArrecadador;
	private String codigoNomeArrecadador;
	private String codigoDescricaoArrecadacaoForma;
	private String dataLancamento;
	private String sequencial;
	private String numeroDocumento;
	private String situacao;
	private String dataPrevistaCredito;
	private String valorPrevistoCredito;
	private String dataRealCredito;
	private String valorRealCredito;
	private String valorArrecadacao;
	private String valorDevolucao;
	private String valorContabilizado;
	private String anoMesArrecadacao;
	private String tipoAviso;
	private String contaBancaria;
	private String valorArrecadacaoCalculado;
	private String valorArrecadacaoInformado;
	private String valorDevolucaoCalculado;
	private String valorDevolucaoInformado;
	
	private String idAvisoBancario;
	private String bancoContaBancaria ;
	private String agenciaContaBancaria ;
	private String numeroContaBancaria ;
	private String valorSomatorioDeducoes ;
	private String valorSomatorioAcertosArrecadacao ;
	private String valorSomatorioAcertosDevolucao ;
	private String valorDiferencaArrecadacaoDevolucao ;
	
	
	public String getCodigoNomeArrecadador() {
		return codigoNomeArrecadador;
	}
	public void setCodigoNomeArrecadador(String codigoNomeArrecadador) {
		this.codigoNomeArrecadador = codigoNomeArrecadador;
	}
	public String getContaBancaria() {
		return contaBancaria;
	}
	public void setContaBancaria(String contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
	public String getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public String getDataPrevistaCredito() {
		return dataPrevistaCredito;
	}
	public void setDataPrevistaCredito(String dataPrevistaCredito) {
		this.dataPrevistaCredito = dataPrevistaCredito;
	}
	public String getDataRealCredito() {
		return dataRealCredito;
	}
	public void setDataRealCredito(String dataRealCredito) {
		this.dataRealCredito = dataRealCredito;
	}
	public String getIdArrecadador() {
		return idArrecadador;
	}
	public void setIdArrecadador(String idArrecadador) {
		this.idArrecadador = idArrecadador;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getSequencial() {
		return sequencial;
	}
	public void setSequencial(String sequencial) {
		this.sequencial = sequencial;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getTipoAviso() {
		return tipoAviso;
	}
	public void setTipoAviso(String tipoAviso) {
		this.tipoAviso = tipoAviso;
	}
	public String getValorArrecadacao() {
		return valorArrecadacao;
	}
	public void setValorArrecadacao(String valorArrecadacao) {
		this.valorArrecadacao = valorArrecadacao;
	}
	public String getValorContabilizado() {
		return valorContabilizado;
	}
	public void setValorContabilizado(String valorContabilizado) {
		this.valorContabilizado = valorContabilizado;
	}
	public String getValorDevolucao() {
		return valorDevolucao;
	}
	public void setValorDevolucao(String valorDevolucao) {
		this.valorDevolucao = valorDevolucao;
	}
	public String getValorPrevistoCredito() {
		return valorPrevistoCredito;
	}
	public void setValorPrevistoCredito(String valorPrevistoCredito) {
		this.valorPrevistoCredito = valorPrevistoCredito;
	}
	public String getValorRealCredito() {
		return valorRealCredito;
	}
	public void setValorRealCredito(String valorRealCredito) {
		this.valorRealCredito = valorRealCredito;
	}
	
	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	    /**@todo: finish this method, this is just the skeleton.*/
	    return null;
	}
	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	}
	public String getAnoMesArrecadacao() {
		return anoMesArrecadacao;
	}
	public void setAnoMesArrecadacao(String anoMesArrecadacao) {
		this.anoMesArrecadacao = anoMesArrecadacao;
	}
	/**
	 * @return Retorna o campo valorArrecadacaoCalculado.
	 */
	public String getValorArrecadacaoCalculado() {
		return valorArrecadacaoCalculado;
	}
	/**
	 * @param valorArrecadacaoCalculado O valorArrecadacaoCalculado a ser setado.
	 */
	public void setValorArrecadacaoCalculado(String valorArrecadacaoCalculado) {
		this.valorArrecadacaoCalculado = valorArrecadacaoCalculado;
	}
	/**
	 * @return Retorna o campo valorArrecadacaoInformado.
	 */
	public String getValorArrecadacaoInformado() {
		return valorArrecadacaoInformado;
	}
	/**
	 * @param valorArrecadacaoInformado O valorArrecadacaoInformado a ser setado.
	 */
	public void setValorArrecadacaoInformado(String valorArrecadacaoInformado) {
		this.valorArrecadacaoInformado = valorArrecadacaoInformado;
	}
	/**
	 * @return Retorna o campo valorDevolucaoCalculado.
	 */
	public String getValorDevolucaoCalculado() {
		return valorDevolucaoCalculado;
	}
	/**
	 * @param valorDevolucaoCalculado O valorDevolucaoCalculado a ser setado.
	 */
	public void setValorDevolucaoCalculado(String valorDevolucaoCalculado) {
		this.valorDevolucaoCalculado = valorDevolucaoCalculado;
	}
	/**
	 * @return Retorna o campo valorDevolucaoInformado.
	 */
	public String getValorDevolucaoInformado() {
		return valorDevolucaoInformado;
	}
	/**
	 * @param valorDevolucaoInformado O valorDevolucaoInformado a ser setado.
	 */
	public void setValorDevolucaoInformado(String valorDevolucaoInformado) {
		this.valorDevolucaoInformado = valorDevolucaoInformado;
	}
	public String getAgenciaContaBancaria() {
		return agenciaContaBancaria;
	}
	public void setAgenciaContaBancaria(String agenciaContaBancaria) {
		this.agenciaContaBancaria = agenciaContaBancaria;
	}
	public String getBancoContaBancaria() {
		return bancoContaBancaria;
	}
	public void setBancoContaBancaria(String bancoContaBancaria) {
		this.bancoContaBancaria = bancoContaBancaria;
	}
	public String getNumeroContaBancaria() {
		return numeroContaBancaria;
	}
	public void setNumeroContaBancaria(String numeroContaBancaria) {
		this.numeroContaBancaria = numeroContaBancaria;
	}
	public String getValorDiferencaArrecadacaoDevolucao() {
		return valorDiferencaArrecadacaoDevolucao;
	}
	public void setValorDiferencaArrecadacaoDevolucao(
			String valorDiferencaArrecadacaoDevolucao) {
		this.valorDiferencaArrecadacaoDevolucao = valorDiferencaArrecadacaoDevolucao;
	}
	public String getValorSomatorioAcertosArrecadacao() {
		return valorSomatorioAcertosArrecadacao;
	}
	public void setValorSomatorioAcertosArrecadacao(
			String valorSomatorioAcertosArrecadacao) {
		this.valorSomatorioAcertosArrecadacao = valorSomatorioAcertosArrecadacao;
	}
	public String getValorSomatorioAcertosDevolucao() {
		return valorSomatorioAcertosDevolucao;
	}
	public void setValorSomatorioAcertosDevolucao(
			String valorSomatorioAcertosDevolucao) {
		this.valorSomatorioAcertosDevolucao = valorSomatorioAcertosDevolucao;
	}
	public String getValorSomatorioDeducoes() {
		return valorSomatorioDeducoes;
	}
	public void setValorSomatorioDeducoes(String valorSomatorioDeducoes) {
		this.valorSomatorioDeducoes = valorSomatorioDeducoes;
	}
	public String getIdAvisoBancario() {
		return idAvisoBancario;
	}
	public void setIdAvisoBancario(String idAvisoBancario) {
		this.idAvisoBancario = idAvisoBancario;
	}
	public String getCodigoDescricaoArrecadacaoForma() {
		return codigoDescricaoArrecadacaoForma;
	}
	public void setCodigoDescricaoArrecadacaoForma(
			String codigoDescricaoArrecadacaoForma) {
		this.codigoDescricaoArrecadacaoForma = codigoDescricaoArrecadacaoForma;
	}
	
	
}
