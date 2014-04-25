package gcom.relatorio.faturamento.autoinfracao;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Rafael Corrêa
 * @created 08/08/2006
 */
/**
 * @author Administrador
 *
 */
public class RelatorioAutoInfracaoBean implements RelatorioBean {

	private String unidadeNegocio;

	private String funcionario;

	private String dataPagamento;

	private String autoInfracao;
	
	private String descricaoServico;

	private BigDecimal valorServico;

	private BigDecimal valorFuncionario;

	private String matricula;
	
	private String nomeCliente;
	
	private String qtdParcelas;
	
	private String parcelaPaga;
	
	private BigDecimal valorParcelaPaga;

	public RelatorioAutoInfracaoBean(String unidadeNegocio, String funcionario,
			String dataPagamento, String autoInfracao, String descricaoServico,
			BigDecimal valorServico, BigDecimal valorFuncionario,
			String matricula, String nomeCliente, String qtdParcelas, String parcelaPaga,
			BigDecimal valorParcelaPaga ) {
		this.unidadeNegocio = unidadeNegocio;
		this.funcionario = funcionario;
		this.dataPagamento = dataPagamento;
		this.autoInfracao = autoInfracao;
		this.descricaoServico = descricaoServico;
		this.valorServico = valorServico;
		this.valorFuncionario = valorFuncionario;
		this.matricula = matricula;
		this.nomeCliente = nomeCliente;
		this.qtdParcelas = qtdParcelas;
		this.parcelaPaga = parcelaPaga;
		this.valorParcelaPaga = valorParcelaPaga;
	}

	public RelatorioAutoInfracaoBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Retorna o campo autoInfracao.
	 */
	public String getAutoInfracao() {
		return autoInfracao;
	}

	/**
	 * @param autoInfracao O autoInfracao a ser setado.
	 */
	public void setAutoInfracao(String autoInfracao) {
		this.autoInfracao = autoInfracao;
	}

	/**
	 * @return Retorna o campo dataPagamento.
	 */
	public String getDataPagamento() {
		return dataPagamento;
	}

	/**
	 * @param dataPagamento O dataPagamento a ser setado.
	 */
	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	/**
	 * @return Retorna o campo descricaoServico.
	 */
	public String getDescricaoServico() {
		return descricaoServico;
	}

	/**
	 * @param descricaoServico O descricaoServico a ser setado.
	 */
	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

	/**
	 * @return Retorna o campo funcionario.
	 */
	public String getFuncionario() {
		return funcionario;
	}

	/**
	 * @param funcionario O funcionario a ser setado.
	 */
	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * @return Retorna o campo matricula.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula O matricula a ser setado.
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	/**
	 * @return Retorna o campo valorFuncionario.
	 */
	public BigDecimal getValorFuncionario() {
		return valorFuncionario;
	}

	/**
	 * @param valorFuncionario O valorFuncionario a ser setado.
	 */
	public void setValorFuncionario(BigDecimal valorFuncionario) {
		this.valorFuncionario = valorFuncionario;
	}

	/**
	 * @return Retorna o campo valorServico.
	 */
	public BigDecimal getValorServico() {
		return valorServico;
	}

	/**
	 * @param valorServico O valorServico a ser setado.
	 */
	public void setValorServico(BigDecimal valorServico) {
		this.valorServico = valorServico;
	}

	public String getParcelaPaga() {
		return parcelaPaga;
	}

	public void setParcelaPaga(String parcelaPaga) {
		this.parcelaPaga = parcelaPaga;
	}

	public String getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(String qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public BigDecimal getValorParcelaPaga() {
		return valorParcelaPaga;
	}

	public void setValorParcelaPaga(BigDecimal valorParcelaPaga) {
		this.valorParcelaPaga = valorParcelaPaga;
	}

	
}
