package gcom.gui.relatorio.arrecadacao;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC 1215] – Gerar Relatório de Documentos não Aceitos
 * 
 * @author Raimundo Martins
 *
 * @date 17/08/2011
 */
public class GerarRelatorioDocumentosNaoAceitosActionForm extends ValidatorForm {


	private static final long serialVersionUID = 1L;
	
	private String periodoInicial;
	private String periodoFinal;
	private String arrecadadorCodigo;
	private String arrecadadorDescricao;
	private String idAvisoBancario;
	private String codigoAgenteArrecadador;
	private String dataLancamentoAviso;
	private String numeroSequencialAviso;
	private String movimentoArrecadadorCodigo;
	private String movimentoArrecadadorCodigoBanco;
	private String movimentoArrecadadorCodigoRemessa;
	private String movimentoArrecadadorIdentificacaoServico;
	private String movimentoArrecadadorNumeroSequencial;
	private String idFormaArrecadacao;
	
	public String getPeriodoFinal() {
		return periodoFinal;
	}
	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
	}
	public String getPeriodoInicial() {
		return periodoInicial;
	}
	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = periodoInicial;
	}
	public String getArrecadadorCodigo() {
		return arrecadadorCodigo;
	}
	public void setArrecadadorCodigo(String arrecadadorCodigo) {
		this.arrecadadorCodigo = arrecadadorCodigo;
	}
	public String getArrecadadorDescricao() {
		return arrecadadorDescricao;
	}
	public void setArrecadadorDescricao(String arrecadadorDescricao) {
		this.arrecadadorDescricao = arrecadadorDescricao;
	}
	public String getCodigoAgenteArrecadador() {
		return codigoAgenteArrecadador;
	}
	public void setCodigoAgenteArrecadador(String codigoAgenteArrecadador) {
		this.codigoAgenteArrecadador = codigoAgenteArrecadador;
	}
	public String getDataLancamentoAviso() {
		return dataLancamentoAviso;
	}
	public void setDataLancamentoAviso(String dataLancamentoAviso) {
		this.dataLancamentoAviso = dataLancamentoAviso;
	}
	public String getIdAvisoBancario() {
		return idAvisoBancario;
	}
	public void setIdAvisoBancario(String idAvisoBancario) {
		this.idAvisoBancario = idAvisoBancario;
	}
	public String getNumeroSequencialAviso() {
		return numeroSequencialAviso;
	}
	public void setNumeroSequencialAviso(String numeroSequencialAviso) {
		this.numeroSequencialAviso = numeroSequencialAviso;
	}
	public String getIdFormaArrecadacao() {
		return idFormaArrecadacao;
	}
	public void setIdFormaArrecadacao(String idFormaArrecadacao) {
		this.idFormaArrecadacao = idFormaArrecadacao;
	}
	public String getMovimentoArrecadadorCodigoBanco() {
		return movimentoArrecadadorCodigoBanco;
	}
	public void setMovimentoArrecadadorCodigoBanco(
			String movimentoArrecadadorCodigoBanco) {
		this.movimentoArrecadadorCodigoBanco = movimentoArrecadadorCodigoBanco;
	}
	public String getMovimentoArrecadadorCodigoRemessa() {
		return movimentoArrecadadorCodigoRemessa;
	}
	public void setMovimentoArrecadadorCodigoRemessa(
			String movimentoArrecadadorCodigoRemessa) {
		this.movimentoArrecadadorCodigoRemessa = movimentoArrecadadorCodigoRemessa;
	}
	public String getMovimentoArrecadadorIdentificacaoServico() {
		return movimentoArrecadadorIdentificacaoServico;
	}
	public void setMovimentoArrecadadorIdentificacaoServico(
			String movimentoArrecadadorIdentificacaoServico) {
		this.movimentoArrecadadorIdentificacaoServico = movimentoArrecadadorIdentificacaoServico;
	}
	public String getMovimentoArrecadadorNumeroSequencial() {
		return movimentoArrecadadorNumeroSequencial;
	}
	public void setMovimentoArrecadadorNumeroSequencial(
			String movimentoArrecadadorNumeroSequencial) {
		this.movimentoArrecadadorNumeroSequencial = movimentoArrecadadorNumeroSequencial;
	}
	public String getMovimentoArrecadadorCodigo() {
		return movimentoArrecadadorCodigo;
	}
	public void setMovimentoArrecadadorCodigo(String movimentoArrecadadorCodigo) {
		this.movimentoArrecadadorCodigo = movimentoArrecadadorCodigo;
	}
	
	
}
