package gcom.cobranca;

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

public class ComandoEmpresaCobrancaConta extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer codigoSetorComercialInicial;
	private Integer codigoSetorComercialFinal;
	private BigDecimal valorMinimoConta;
	private BigDecimal valorMaximoConta;
	private Integer referenciaContaInicial;
	private Integer referenciaContaFinal;
	private Date dataVencimentoContaInicial;
	private Date dataVencimentoContaFinal;
	private Date dataExecucao;
	private Integer indicadorResidencial;
	private Integer indicadorComercial;
	private Integer indicadorIndustrial;
	private Integer indicadorPublico;
	private Imovel imovel;
	private Cliente cliente;
	private Localidade localidadeInicial;
	private Localidade localidadeFinal;
	private Empresa empresa;
	private Date ultimaAlteracao;
	private Integer numeroQuadraInicial;
	private Integer numeroQuadraFinal;
	private Date dataInicioCiclo;
	private Date dataFimCiclo;
	private Date dataEncerramento;
	private Integer indicadorGeracaoTxt;
	private CobrancaSituacao cobrancaSituacao;
	private ServicoTipo servicoTipo;
	private Integer qtdContasInicial;
	private Integer qtdContasFinal;
	private Integer qtdDiasVencimento;
	private Short indicadorCobrancaTelemarketing;
	private Integer qtdMaximaClientes;
	private Short indicadorGerarComDebitoPreterito;
	private Integer quantidadeContas;
	private Integer quantidadeClientes;
	private BigDecimal valorTotal;
	private Short indicadorPossuiCpfCnpj;

	public static Integer MOTIVO_ENCERRAMENTO_PRAZO_VENCIDO =  1;
	public static Integer MOTIVO_ENCERRAMENTO_CANCELAMENTO_COBRANCA =  2;
	
	public ComandoEmpresaCobrancaConta() {
	}

	public ComandoEmpresaCobrancaConta(Integer id) {
		super();
		this.id = id;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroComandoEmpresaCobrancaConta filtroComandoEmpresaCobrancaConta = new FiltroComandoEmpresaCobrancaConta();

		filtroComandoEmpresaCobrancaConta.adicionarParametro(new ParametroSimples(FiltroComandoEmpresaCobrancaConta.ID, this.getId()));
		return filtroComandoEmpresaCobrancaConta;
	}

	public Integer getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(Integer codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public Integer getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(Integer codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public Date getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public Date getDataVencimentoContaFinal() {
		return dataVencimentoContaFinal;
	}

	public void setDataVencimentoContaFinal(Date dataVencimentoContaFinal) {
		this.dataVencimentoContaFinal = dataVencimentoContaFinal;
	}

	public Date getDataVencimentoContaInicial() {
		return dataVencimentoContaInicial;
	}

	public void setDataVencimentoContaInicial(Date dataVencimentoContaInicial) {
		this.dataVencimentoContaInicial = dataVencimentoContaInicial;
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

	public Integer getReferenciaContaFinal() {
		return referenciaContaFinal;
	}

	public void setReferenciaContaFinal(Integer referenciaContaFinal) {
		this.referenciaContaFinal = referenciaContaFinal;
	}

	public Integer getReferenciaContaInicial() {
		return referenciaContaInicial;
	}

	public void setReferenciaContaInicial(Integer referenciaContaInicial) {
		this.referenciaContaInicial = referenciaContaInicial;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorMaximoConta() {
		return valorMaximoConta;
	}

	public void setValorMaximoConta(BigDecimal valorMaximoConta) {
		this.valorMaximoConta = valorMaximoConta;
	}

	public BigDecimal getValorMinimoConta() {
		return valorMinimoConta;
	}

	public void setValorMinimoConta(BigDecimal valorMinimoConta) {
		this.valorMinimoConta = valorMinimoConta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Localidade getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(Localidade localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public Localidade getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(Localidade localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public Integer getIndicadorComercial() {
		return indicadorComercial;
	}

	public void setIndicadorComercial(Integer indicadorComercial) {
		this.indicadorComercial = indicadorComercial;
	}

	public Integer getIndicadorIndustrial() {
		return indicadorIndustrial;
	}

	public void setIndicadorIndustrial(Integer indicadorIndustrial) {
		this.indicadorIndustrial = indicadorIndustrial;
	}

	public Integer getIndicadorPublico() {
		return indicadorPublico;
	}

	public void setIndicadorPublico(Integer indicadorPublico) {
		this.indicadorPublico = indicadorPublico;
	}

	public Integer getIndicadorResidencial() {
		return indicadorResidencial;
	}

	public void setIndicadorResidencial(Integer indicadorResidencial) {
		this.indicadorResidencial = indicadorResidencial;
	}

	public CobrancaSituacao getCobrancaSituacao() {
		return cobrancaSituacao;
	}

	public void setCobrancaSituacao(CobrancaSituacao cobrancaSituacao) {
		this.cobrancaSituacao = cobrancaSituacao;
	}

	public Date getDataFimCiclo() {
		return dataFimCiclo;
	}

	public void setDataFimCiclo(Date dataFimCiclo) {
		this.dataFimCiclo = dataFimCiclo;
	}

	public Date getDataInicioCiclo() {
		return dataInicioCiclo;
	}

	public void setDataInicioCiclo(Date dataInicioCiclo) {
		this.dataInicioCiclo = dataInicioCiclo;
	}

	public Integer getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}

	public void setNumeroQuadraFinal(Integer numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}

	public Integer getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}

	public void setNumeroQuadraInicial(Integer numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}

	public ServicoTipo getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Integer getIndicadorGeracaoTxt() {
		return indicadorGeracaoTxt;
	}

	public void setIndicadorGeracaoTxt(Integer indicadorGeracaoTxt) {
		this.indicadorGeracaoTxt = indicadorGeracaoTxt;
	}

	public Integer getQtdContasInicial() {
		return qtdContasInicial;
	}

	public void setQtdContasInicial(Integer qtdContasInicial) {
		this.qtdContasInicial = qtdContasInicial;
	}

	public Integer getQtdContasFinal() {
		return qtdContasFinal;
	}

	public void setQtdContasFinal(Integer qtdContasFinal) {
		this.qtdContasFinal = qtdContasFinal;
	}

	public Integer getQtdDiasVencimento() {
		return qtdDiasVencimento;
	}

	public void setQtdDiasVencimento(Integer qtdDiasVencimento) {
		this.qtdDiasVencimento = qtdDiasVencimento;
	}

	public Short getIndicadorCobrancaTelemarketing() {
		return indicadorCobrancaTelemarketing;
	}

	public void setIndicadorCobrancaTelemarketing(Short indicadorCobrancaTelemarketing) {
		this.indicadorCobrancaTelemarketing = indicadorCobrancaTelemarketing;
	}

	public Integer getQtdMaximaClientes() {
		return qtdMaximaClientes;
	}

	public void setQtdMaximaClientes(Integer qtdMaximaClientes) {
		this.qtdMaximaClientes = qtdMaximaClientes;
	}

	public Short getIndicadorGerarComDebitoPreterito() {
		return indicadorGerarComDebitoPreterito;
	}

	public void setIndicadorGerarComDebitoPreterito(Short indicadorGerarComDebitoPreterito) {
		this.indicadorGerarComDebitoPreterito = indicadorGerarComDebitoPreterito;
	}

	public Integer getQuantidadeContas() {
		return quantidadeContas;
	}

	public void setQuantidadeContas(Integer quantidadeContas) {
		this.quantidadeContas = quantidadeContas;
	}

	public Integer getQuantidadeClientes() {
		return quantidadeClientes;
	}

	public void setQuantidadeClientes(Integer quantidadeClientes) {
		this.quantidadeClientes = quantidadeClientes;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public Short getIndicadorPossuiCpfCnpj() {
		return indicadorPossuiCpfCnpj;
	}

	public void setIndicadorPossuiCpfCnpj(Short indicadorPossuiCpfCnpj) {
		this.indicadorPossuiCpfCnpj = indicadorPossuiCpfCnpj;
	}
	
	public boolean isQuantidadeContasValida(int qtd, int qtdMenorFaixa) {
		if (qtdContasInicial != null) {
			return qtd >= qtdContasInicial && qtd <= qtdContasFinal;
		} else if (qtdMenorFaixa > 0) {
			return qtd >= qtdMenorFaixa;
		} else {
			return true;
		}
	}
	
	public boolean isValorValido(double valor) {
		if (valorMinimoConta != null) {
			return valor >= valorMinimoConta.doubleValue() && valor <= valorMaximoConta.doubleValue();
		} else {
			return true;
		}
	}
	
	public boolean isReferenciaValida(Integer referencia) {
		if (referenciaContaInicial != null) {
			return referencia >= referenciaContaInicial && referencia <= referenciaContaFinal;
		} else {
			return true;
		}
	}
	
	public boolean isVencimentoValido(Date vencimento) {
		if (dataVencimentoContaInicial != null) {
			return  vencimento.before(dataVencimentoContaInicial) || vencimento.after(dataVencimentoContaFinal);
		} else {
			return true;
		}
	}
	
	public boolean isDiasVencimentoValido(Date vencimento) {
		Date vencimentoLimite = Util.subtrairNumeroDiasDeUmaData(new Date(), qtdDiasVencimento);
		if (qtdDiasVencimento != null) {
			return vencimento.before(vencimentoLimite);
		} else {
			return true;
		}
	}
	
	public boolean isIndicadorCpfCnpjValido(boolean possuiCpfCnpj) {
		if (indicadorPossuiCpfCnpj.equals(ConstantesSistema.SIM)) {
			return possuiCpfCnpj;
		} else {
			return true;
		}
	}
	
	public boolean isQtdMaximaInformada() {
		return qtdMaximaClientes != null && qtdMaximaClientes > 0;
	}
}
