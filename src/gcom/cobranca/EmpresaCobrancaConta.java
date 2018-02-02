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
	private Integer referencia;
	private Date dataEnvio;
	private Date dataRetirada;

	public final static Short INDICADOR_VALIDO_PAGAMENTO_SIM = new Short("1");
	public final static Short INDICADOR_VALIDO_PAGAMENTO_NAO = new Short("2");

	public EmpresaCobrancaConta() {
	}

	public EmpresaCobrancaConta(
			Integer id, 
			Empresa empresa, 
			ContaGeral contaGeral, 
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, 
			BigDecimal valorOriginalConta, 
			BigDecimal percentualEmpresaConta,
			Short indicadorPagamentoValido, 
			Date ultimaAlteracao) {
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

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroEmpresaCobrancaConta filtroEmpresaCobrancaConta = new FiltroEmpresaCobrancaConta();

		filtroEmpresaCobrancaConta.adicionarParametro(new ParametroSimples(FiltroEmpresaCobrancaConta.ID, this.getId()));
		return filtroEmpresaCobrancaConta;
	}

	public ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getPercentualEmpresaConta() {
		return percentualEmpresaConta;
	}

	public void setPercentualEmpresaConta(BigDecimal percentualEmpresaConta) {
		this.percentualEmpresaConta = percentualEmpresaConta;
	}

	public BigDecimal getValorOriginalConta() {
		return valorOriginalConta;
	}

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

	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}

	public void setComandoEmpresaCobrancaConta(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}

	public Short getIndicadorPagamentoValido() {
		return indicadorPagamentoValido;
	}

	public void setIndicadorPagamentoValido(Short indicadorPagamentoValido) {
		this.indicadorPagamentoValido = indicadorPagamentoValido;
	}

	public ComandoEmpresaCobrancaContaExtensao getComandoEmpresaCobrancaContaExtensao() {
		return comandoEmpresaCobrancaContaExtensao;
	}

	public void setComandoEmpresaCobrancaContaExtensao(ComandoEmpresaCobrancaContaExtensao comandoEmpresaCobrancaContaExtensao) {
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

	public Integer getReferencia() {
		return referencia;
	}

	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Date getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
}
