package gcom.cobranca;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

public class ComandoEmpresaCobrancaConta extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;
	
	/** nullable persistent field */
	private Integer codigoSetorComercialInicial;
	
	/** nullable persistent field */
	private Integer codigoSetorComercialFinal;
	
	/** nullable persistent field */
	private BigDecimal valorMinimoConta;
	
	/** nullable persistent field */
	private BigDecimal valorMaximoConta;
	
	/** nullable persistent field */
	private Integer referenciaContaInicial;
	
	/** nullable persistent field */
	private Integer referenciaContaFinal;
	
	/** nullable persistent field */
	private Date dataVencimentoContaInicial;
	
	/** nullable persistent field */
	private Date dataVencimentoContaFinal;
	
	/** nullable persistent field */
	private Date dataExecucao;
	
	/** nullable persistent field */
	private Integer indicadorResidencial;
	
	/** nullable persistent field */
	private Integer indicadorComercial;
	
	/** nullable persistent field */
	private Integer indicadorIndustrial;
	
	/** nullable persistent field */
	private Integer indicadorPublico;
	
	/** nullable persistent field */
	private Imovel imovel;
	
	/** nullable persistent field */
	private Cliente cliente;
	
	/** nullable persistent field */
	private Localidade localidadeInicial;
	
	/** nullable persistent field */
	private Localidade localidadeFinal;
	
	/** nullable persistent field */
	private UnidadeNegocio unidadeNegocio;
	
	/** nullable persistent field */
	private Empresa empresa;
	
	
	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	private Integer numeroQuadraInicial;
	
	private Integer numeroQuadraFinal;
	
	private Date dataInicioCiclo;
	
	private Date dataFimCiclo;
	
	private Date dataEncerramento;
	
	private Integer indicadorGeracaoTxt;
	
	private Quadra quadraInicial;
	
	private Quadra quadraFinal;
	
	private GerenciaRegional gerenciaRegional;
	
	private ImovelPerfil imovelPerfil;
	
	private CobrancaSituacao cobrancaSituacao;
	
	private ServicoTipo servicoTipo;
	
	private Integer qtdContasInicial;
	
	private Integer qtdContasFinal;
	
	private Integer qtdDiasVencimento;
	
	private LigacaoAguaSituacao ligacaoAguaSituacao;
	

	public ComandoEmpresaCobrancaConta(Integer id, Integer codigoSetorComercialInicial, Integer codigoSetorComercialFinal, BigDecimal valorMinimoConta, BigDecimal valorMaximoConta, Integer referenciaContaInicial, Integer referenciaContaFinal, Date dataVencimentoContaInicial, Date dataVencimentoContaFinal, Date dataExecucao, Imovel imovel, Cliente cliente, Localidade localidadeInicial, Localidade localidadeFinal, UnidadeNegocio unidadeNegocio, Empresa empresa, Date ultimaAlteracao) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
		this.valorMinimoConta = valorMinimoConta;
		this.valorMaximoConta = valorMaximoConta;
		this.referenciaContaInicial = referenciaContaInicial;
		this.referenciaContaFinal = referenciaContaFinal;
		this.dataVencimentoContaInicial = dataVencimentoContaInicial;
		this.dataVencimentoContaFinal = dataVencimentoContaFinal;
		this.dataExecucao = dataExecucao;
		this.imovel = imovel;
		this.cliente = cliente;
		this.localidadeInicial = localidadeInicial;
		this.localidadeFinal = localidadeFinal;
		this.unidadeNegocio = unidadeNegocio;
		this.empresa = empresa;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ComandoEmpresaCobrancaConta() {

	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroComandoEmpresaCobrancaConta filtroComandoEmpresaCobrancaConta = new FiltroComandoEmpresaCobrancaConta();

		filtroComandoEmpresaCobrancaConta.adicionarParametro(new ParametroSimples(
				FiltroComandoEmpresaCobrancaConta.ID, this.getId()));
		return filtroComandoEmpresaCobrancaConta;
	}

	/**
	 * @return Retorna o campo codigoSetorComercialFinal.
	 */
	public Integer getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	/**
	 * @param codigoSetorComercialFinal O codigoSetorComercialFinal a ser setado.
	 */
	public void setCodigoSetorComercialFinal(Integer codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	/**
	 * @return Retorna o campo codigoSetorComercialInicial.
	 */
	public Integer getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	/**
	 * @param codigoSetorComercialInicial O codigoSetorComercialInicial a ser setado.
	 */
	public void setCodigoSetorComercialInicial(Integer codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	/**
	 * @return Retorna o campo dataExecucao.
	 */
	public Date getDataExecucao() {
		return dataExecucao;
	}

	/**
	 * @param dataExecucao O dataExecucao a ser setado.
	 */
	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	/**
	 * @return Retorna o campo dataVencimentoContaFinal.
	 */
	public Date getDataVencimentoContaFinal() {
		return dataVencimentoContaFinal;
	}

	/**
	 * @param dataVencimentoContaFinal O dataVencimentoContaFinal a ser setado.
	 */
	public void setDataVencimentoContaFinal(Date dataVencimentoContaFinal) {
		this.dataVencimentoContaFinal = dataVencimentoContaFinal;
	}

	/**
	 * @return Retorna o campo dataVencimentoContaInicial.
	 */
	public Date getDataVencimentoContaInicial() {
		return dataVencimentoContaInicial;
	}

	/**
	 * @param dataVencimentoContaInicial O dataVencimentoContaInicial a ser setado.
	 */
	public void setDataVencimentoContaInicial(Date dataVencimentoContaInicial) {
		this.dataVencimentoContaInicial = dataVencimentoContaInicial;
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
	 * @return Retorna o campo referenciaContaFinal.
	 */
	public Integer getReferenciaContaFinal() {
		return referenciaContaFinal;
	}

	/**
	 * @param referenciaContaFinal O referenciaContaFinal a ser setado.
	 */
	public void setReferenciaContaFinal(Integer referenciaContaFinal) {
		this.referenciaContaFinal = referenciaContaFinal;
	}

	/**
	 * @return Retorna o campo referenciaContaInicial.
	 */
	public Integer getReferenciaContaInicial() {
		return referenciaContaInicial;
	}

	/**
	 * @param referenciaContaInicial O referenciaContaInicial a ser setado.
	 */
	public void setReferenciaContaInicial(Integer referenciaContaInicial) {
		this.referenciaContaInicial = referenciaContaInicial;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo valorMaximoConta.
	 */
	public BigDecimal getValorMaximoConta() {
		return valorMaximoConta;
	}

	/**
	 * @param valorMaximoConta O valorMaximoConta a ser setado.
	 */
	public void setValorMaximoConta(BigDecimal valorMaximoConta) {
		this.valorMaximoConta = valorMaximoConta;
	}

	/**
	 * @return Retorna o campo valorMinimoConta.
	 */
	public BigDecimal getValorMinimoConta() {
		return valorMinimoConta;
	}

	/**
	 * @param valorMinimoConta O valorMinimoConta a ser setado.
	 */
	public void setValorMinimoConta(BigDecimal valorMinimoConta) {
		this.valorMinimoConta = valorMinimoConta;
	}

	/**
	 * @return Retorna o campo cliente.
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente O cliente a ser setado.
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return Retorna o campo imovel.
	 */
	public Imovel getImovel() {
		return imovel;
	}

	/**
	 * @param imovel O imovel a ser setado.
	 */
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	/**
	 * @return Retorna o campo localidadeFinal.
	 */
	public Localidade getLocalidadeFinal() {
		return localidadeFinal;
	}

	/**
	 * @param localidadeFinal O localidadeFinal a ser setado.
	 */
	public void setLocalidadeFinal(Localidade localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	/**
	 * @return Retorna o campo localidadeInicial.
	 */
	public Localidade getLocalidadeInicial() {
		return localidadeInicial;
	}

	/**
	 * @param localidadeInicial O localidadeInicial a ser setado.
	 */
	public void setLocalidadeInicial(Localidade localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	/**
	 * @return Retorna o campo indicadorComercial.
	 */
	public Integer getIndicadorComercial() {
		return indicadorComercial;
	}

	/**
	 * @param indicadorComercial O indicadorComercial a ser setado.
	 */
	public void setIndicadorComercial(Integer indicadorComercial) {
		this.indicadorComercial = indicadorComercial;
	}

	/**
	 * @return Retorna o campo indicadorIndustrial.
	 */
	public Integer getIndicadorIndustrial() {
		return indicadorIndustrial;
	}

	/**
	 * @param indicadorIndustrial O indicadorIndustrial a ser setado.
	 */
	public void setIndicadorIndustrial(Integer indicadorIndustrial) {
		this.indicadorIndustrial = indicadorIndustrial;
	}

	/**
	 * @return Retorna o campo indicadorPublico.
	 */
	public Integer getIndicadorPublico() {
		return indicadorPublico;
	}

	/**
	 * @param indicadorPublico O indicadorPublico a ser setado.
	 */
	public void setIndicadorPublico(Integer indicadorPublico) {
		this.indicadorPublico = indicadorPublico;
	}

	/**
	 * @return Retorna o campo indicadorResidencial.
	 */
	public Integer getIndicadorResidencial() {
		return indicadorResidencial;
	}

	/**
	 * @param indicadorResidencial O indicadorResidencial a ser setado.
	 */
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

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
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

	public Quadra getQuadraFinal() {
		return quadraFinal;
	}

	public void setQuadraFinal(Quadra quadraFinal) {
		this.quadraFinal = quadraFinal;
	}

	public Quadra getQuadraInicial() {
		return quadraInicial;
	}

	public void setQuadraInicial(Quadra quadraInicial) {
		this.quadraInicial = quadraInicial;
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

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}
	
}
