package gcom.cobranca;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.conta.ContaGeral;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

public class EmpresaCobrancaConta extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Empresa empresa;
	private ContaGeral contaGeral;
	private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;
	private BigDecimal valorOriginalConta;
	private BigDecimal percentualEmpresaConta;
	private Short indicadorPagamentoValido;
	private Date ultimaAlteracao;
	private ComandoEmpresaCobrancaContaExtensao comandoEmpresaCobrancaContaExtensao;
	private Imovel imovel;
	private OrdemServico ordemServico;
	
	public final static Short INDICADOR_VALIDO_PAGAMENTO_SIM = new Short("1");
	public final static Short INDICADOR_VALIDO_PAGAMENTO_NAO = new Short("2");


	public EmpresaCobrancaConta(Integer id, Empresa empresa, ContaGeral contaGeral,
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, BigDecimal valorOriginalConta, 
			BigDecimal percentualEmpresaConta, Short indicadorPagamentoValido, Date ultimaAlteracao) {
		super();
		
		this.id = id;
		this.empresa = empresa;
		this.contaGeral = contaGeral;
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
		this.valorOriginalConta = valorOriginalConta;
		this.percentualEmpresaConta = percentualEmpresaConta;
		this.indicadorPagamentoValido = indicadorPagamentoValido;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public EmpresaCobrancaConta() {

	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroEmpresaCobrancaConta filtroEmpresaCobrancaConta = new FiltroEmpresaCobrancaConta();

		filtroEmpresaCobrancaConta.adicionarParametro(new ParametroSimples(
				FiltroEmpresaCobrancaConta.ID, this.getId()));
		return filtroEmpresaCobrancaConta;
	}

	/**
	 * @return Retorna o campo contaGeral.
	 */
	public ContaGeral getContaGeral() {
		return contaGeral;
	}

	/**
	 * @param contaGeral O contaGeral a ser setado.
	 */
	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	/**
	 * @return Retorna o campo empresa.
	 */
	public Empresa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa O empresa a ser setado.
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo percentualEmpresaConta.
	 */
	public BigDecimal getPercentualEmpresaConta() {
		return percentualEmpresaConta;
	}

	/**
	 * @param percentualEmpresaConta O percentualEmpresaConta a ser setado.
	 */
	public void setPercentualEmpresaConta(BigDecimal percentualEmpresaConta) {
		this.percentualEmpresaConta = percentualEmpresaConta;
	}

	/**
	 * @return Retorna o campo valorOriginalConta.
	 */
	public BigDecimal getValorOriginalConta() {
		return valorOriginalConta;
	}

	/**
	 * @param valorOriginalConta O valorOriginalConta a ser setado.
	 */
	public void setValorOriginalConta(BigDecimal valorOriginalConta) {
		this.valorOriginalConta = valorOriginalConta;
	}

	@Override
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo comandoEmpresaCobrancaConta.
	 */
	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}

	/**
	 * @param comandoEmpresaCobrancaConta O comandoEmpresaCobrancaConta a ser setado.
	 */
	public void setComandoEmpresaCobrancaConta(
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}

	/**
	 * @return Retorna o campo indicadorPagamentoValido.
	 */
	public Short getIndicadorPagamentoValido() {
		return indicadorPagamentoValido;
	}

	/**
	 * @param indicadorPagamentoValido O indicadorPagamentoValido a ser setado.
	 */
	public void setIndicadorPagamentoValido(Short indicadorPagamentoValido) {
		this.indicadorPagamentoValido = indicadorPagamentoValido;
	}

	public ComandoEmpresaCobrancaContaExtensao getComandoEmpresaCobrancaContaExtensao() {
		return comandoEmpresaCobrancaContaExtensao;
	}

	public void setComandoEmpresaCobrancaContaExtensao(
			ComandoEmpresaCobrancaContaExtensao comandoEmpresaCobrancaContaExtensao) {
		this.comandoEmpresaCobrancaContaExtensao = comandoEmpresaCobrancaContaExtensao;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

}
