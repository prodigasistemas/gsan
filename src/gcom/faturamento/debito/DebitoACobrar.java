package gcom.faturamento.debito;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.ParcelamentoGrupo;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@ControleAlteracao()
public class DebitoACobrar extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_DEBITO_A_COBRAR_INSERIR = 70;
	public static final int ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR = 71;
	public static final int ATRIBUTOS_CONFIRMAR_PARCELAMENTO_CARTAO_CREDITO = 1522;

	private Integer id;
    private Date geracaoDebito;
    private Integer anoMesReferenciaDebito;
    private Integer anoMesCobrancaDebito;

    @ControleAlteracao(funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_INSERIR,ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR})
    private BigDecimal valorDebito;

    @ControleAlteracao(funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_INSERIR, ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR, LigacaoAgua.ATRIBUTOS_EFETUAR_LIGACAO})
    private short numeroPrestacaoDebito;

    @ControleAlteracao(funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_INSERIR,ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR})
    private short numeroPrestacaoCobradas;

    private Integer codigoSetorComercial;
    private Integer numeroQuadra;
    private Short numeroLote;
    private Short numeroSubLote;
    private Date ultimaAlteracao;
    private Integer anoMesReferenciaContabil;

    @ControleAlteracao(funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_INSERIR,ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR})
    private BigDecimal percentualTaxaJurosFinanciamento;
    
    private Short numeroParcelaBonus;
    private Imovel imovel;
    private DocumentoTipo documentoTipo;

    @ControleAlteracao(value=FiltroDebitoACobrar.PARCELAMENTO,funcionalidade={ATRIBUTOS_CONFIRMAR_PARCELAMENTO_CARTAO_CREDITO})
    private Parcelamento parcelamento;

    @ControleAlteracao(value=FiltroDebitoACobrar.FINANCIAMENTO_TIPO, funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR})
    private FinanciamentoTipo financiamentoTipo;

    @ControleAlteracao(value=FiltroDebitoACobrar.ORDEM_SERVICO, funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_INSERIR,ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR})    
    private OrdemServico ordemServico;

    private Quadra quadra;

    private Localidade localidade;

    @ControleAlteracao(value=FiltroDebitoACobrar.DEBITO_TIPO, funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_INSERIR,ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR})
    private gcom.faturamento.debito.DebitoTipo debitoTipo;

    @ControleAlteracao(value=FiltroDebitoACobrar.REGISTRO_ATENDIMENTO, funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_INSERIR,ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR})        
    private RegistroAtendimento registroAtendimento;

    @ControleAlteracao(value=FiltroDebitoACobrar.LANCAMENTO_ITEM_CONTABIL, funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR})
    private LancamentoItemContabil lancamentoItemContabil;

    private DebitoCreditoSituacao debitoCreditoSituacaoAnterior;

    @ControleAlteracao(value=FiltroDebitoACobrar.DEBITO_CREDITO_SITUACAO_ATUAL, funcionalidade={ATRIBUTOS_CONFIRMAR_PARCELAMENTO_CARTAO_CREDITO,ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR})
    private gcom.faturamento.debito.DebitoCreditoSituacao debitoCreditoSituacaoAtual;

    private ParcelamentoGrupo parcelamentoGrupo;

    @ControleAlteracao(value=FiltroDebitoACobrar.COBRANCA_FORMA, funcionalidade={ATRIBUTOS_CONFIRMAR_PARCELAMENTO_CARTAO_CREDITO})
    private CobrancaForma cobrancaForma;
    
    private Usuario usuario;

    @SuppressWarnings("rawtypes")
	private Set debitoACobrarCategorias;
    
    private BigDecimal valorDebitoPorCategoria;
    
    private DebitoACobrarGeral debitoACobrarGeralOrigem;
    
    private DebitoACobrarGeral debitoACobrarGeral;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_INSERIR})
    private BigDecimal valorEntrada;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_INSERIR})
    private BigDecimal valorTotalDebito;
    
    private Integer anoMesReferenciaPrestacao;
    
    private Integer numeroParcelasAntecipadas;
    
    private Date dataRevisao;
    
    private ContaMotivoRevisao contaMotivoRevisao;
    
    private Integer situacaoAtual;

    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroDebitoACobrar filtro = new FiltroDebitoACobrar();
		filtro. adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtro. adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
		filtro. adicionarCaminhoParaCarregamentoEntidade("parcelamento");
		filtro. adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
		filtro. adicionarCaminhoParaCarregamentoEntidade("ordemServico");
		filtro. adicionarCaminhoParaCarregamentoEntidade("quadra");
		filtro. adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtro. adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtro. adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
		filtro. adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
		filtro. adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAnterior");
		filtro. adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
		filtro. adicionarCaminhoParaCarregamentoEntidade("parcelamentoGrupo");
		filtro. adicionarCaminhoParaCarregamentoEntidade("cobrancaForma");
		filtro. adicionarCaminhoParaCarregamentoEntidade("usuario");
		filtro. adicionarParametro(new ParametroSimples(FiltroDebitoACobrar .ID, this.getId()));
		
		return filtro; 
	}
    
	public DebitoACobrar() {}
	
	@SuppressWarnings("rawtypes")
	public DebitoACobrar(
			Date geracaoDebito, 
			Integer anoMesReferenciaDebito, 
			Integer anoMesCobrancaDebito, 
			BigDecimal valorDebito,
			short numeroPrestacaoDebito, 
			short numeroPrestacaoCobradas, 
			Integer codigoSetorComercial, 
			Integer numeroQuadra, 
			Short numeroLote,
			Short numeroSubLote, 
			Date ultimaAlteracao, 
			Integer anoMesReferenciaContabil, 
			BigDecimal percentualTaxaJurosFinanciamento, 
			Imovel imovel, 
			DocumentoTipo documentoTipo, 
			Parcelamento parcelamento, 
			FinanciamentoTipo financiamentoTipo, 
			OrdemServico ordemServico, 
			Quadra quadra, 
			Localidade localidade, 
			DebitoTipo debitoTipo, 
			RegistroAtendimento registroAtendimento, 
			LancamentoItemContabil lancamentoItemContabil, 
			DebitoCreditoSituacao debitoCreditoSituacaoAnterior, 
			DebitoCreditoSituacao debitoCreditoSituacaoAtual, 
			ParcelamentoGrupo parcelamentoGrupo, 
			CobrancaForma cobrancaForma, 
			Usuario usuario, 
			Set debitoACobrarCategorias) {
		super();
		
        this.geracaoDebito = geracaoDebito;
        this.anoMesReferenciaDebito = anoMesReferenciaDebito;
        this.anoMesCobrancaDebito = anoMesCobrancaDebito;
        this.valorDebito = valorDebito;
        this.numeroPrestacaoDebito = numeroPrestacaoDebito;
        this.numeroPrestacaoCobradas = numeroPrestacaoCobradas;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.numeroLote = numeroLote;
        this.numeroSubLote = numeroSubLote;
        this.ultimaAlteracao = ultimaAlteracao;
        this.anoMesReferenciaContabil = anoMesReferenciaContabil;
        this.percentualTaxaJurosFinanciamento = percentualTaxaJurosFinanciamento;
        this.imovel = imovel;
        this.documentoTipo = documentoTipo;
        this.parcelamento = parcelamento;
        this.financiamentoTipo = financiamentoTipo;
        this.ordemServico = ordemServico;
        this.quadra = quadra;
        this.localidade = localidade;
        this.debitoTipo = debitoTipo;
        this.registroAtendimento = registroAtendimento;
        this.lancamentoItemContabil = lancamentoItemContabil;
        this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
        this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
        this.parcelamentoGrupo = parcelamentoGrupo;
        this.cobrancaForma = cobrancaForma;
        this.usuario = usuario;
        this.debitoACobrarCategorias = debitoACobrarCategorias;
    }

    @SuppressWarnings("rawtypes")
    public DebitoACobrar(
    		Date geracaoDebito, 
    		BigDecimal valorDebito, 
    		short numeroPrestacaoDebito, 
    		short numeroPrestacaoCobradas, 
    		Imovel imovel, 
    		DocumentoTipo documentoTipo, 
    		Parcelamento parcelamento, 
    		FinanciamentoTipo financiamentoTipo, 
    		OrdemServico ordemServico, 
    		Quadra quadra, 
    		Localidade localidade,
    		DebitoTipo debitoTipo,
    		RegistroAtendimento registroAtendimento, 
    		LancamentoItemContabil lancamentoItemContabil, 
    		DebitoCreditoSituacao debitoCreditoSituacaoAnterior, 
    		DebitoCreditoSituacao debitoCreditoSituacaoAtual, 
    		ParcelamentoGrupo parcelamentoGrupo, 
    		CobrancaForma cobrancaForma, 
    		Set debitoACobrarCategorias) {
    	super();
    	
        this.geracaoDebito = geracaoDebito;
        this.valorDebito = valorDebito;
        this.numeroPrestacaoDebito = numeroPrestacaoDebito;
        this.numeroPrestacaoCobradas = numeroPrestacaoCobradas;
        this.imovel = imovel;
        this.documentoTipo = documentoTipo;
        this.parcelamento = parcelamento;
        this.financiamentoTipo = financiamentoTipo;
        this.ordemServico = ordemServico;
        this.quadra = quadra;
        this.localidade = localidade;
        this.debitoTipo = debitoTipo;
        this.registroAtendimento = registroAtendimento;
        this.lancamentoItemContabil = lancamentoItemContabil;
        this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
        this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
        this.parcelamentoGrupo = parcelamentoGrupo;
        this.cobrancaForma = cobrancaForma;
        this.debitoACobrarCategorias = debitoACobrarCategorias;
    }

    public DebitoACobrar(BigDecimal valorDebito, short numeroPrestacaoDebito, short numeroPrestacaoCobradas, LancamentoItemContabil lancamentoItemContabil) {
        this.valorDebito = valorDebito;
        this.numeroPrestacaoDebito = numeroPrestacaoDebito;
        this.numeroPrestacaoCobradas = numeroPrestacaoCobradas;
        this.lancamentoItemContabil = lancamentoItemContabil;
    }
    
    public DebitoACobrar(Integer id,BigDecimal valorDebito, short numeroPrestacaoDebito, 
    	short numeroPrestacaoCobradas, Short numeroParcelaBonus) {
    	
    	this.id = id;
    	this.valorDebito = valorDebito;
        this.numeroPrestacaoDebito = numeroPrestacaoDebito;
        this.numeroPrestacaoCobradas = numeroPrestacaoCobradas;
        this.numeroParcelaBonus = numeroParcelaBonus;
    }

    public DebitoACobrar(
    		DebitoACobrarGeral debitoACobrarGeral,
    		Imovel imovel,
    		BigDecimal valorDebito,
    		Short numeroPrestacaoDebito,
    		Short numeroPrestacaoCobradas,
    		Date geracaoDebito,
    		Integer anoMesReferenciaDebito,
    		Integer anoMesCobrancaDebito,
    		Integer anoMesReferenciaContabil,
    		BigDecimal percentualTaxaJurosFinanciamento,
    		DebitoTipo debitoTipo,
    		Integer idDebitoCreditoSituacaoAtual,
    		ParcelamentoGrupo parcelamentoGrupo,
    		Integer idCobrancaForma,
    		Usuario usuario) {
    	super();
    	
    	this.id = debitoACobrarGeral.getId();
    	this.debitoACobrarGeral = debitoACobrarGeral;
    	this.imovel = imovel;
    	this.valorDebito = valorDebito;
    	this.numeroPrestacaoDebito = numeroPrestacaoDebito;
    	this.numeroPrestacaoCobradas = numeroPrestacaoCobradas;
    	this.geracaoDebito = geracaoDebito;
    	this.anoMesReferenciaDebito = anoMesReferenciaDebito;
    	this.anoMesCobrancaDebito = anoMesCobrancaDebito;
    	this.anoMesReferenciaContabil = anoMesReferenciaContabil;
    	this.localidade = imovel.getLocalidade();
    	this.codigoSetorComercial = imovel.getSetorComercia().getCodigo();
    	this.quadra = imovel.getQuadra();
    	this.numeroQuadra = imovel.getQuadra().getNumeroQuadra();
    	this.numeroLote = imovel.getLote();
    	this.numeroSubLote = imovel.getSubLote();
    	this.percentualTaxaJurosFinanciamento = percentualTaxaJurosFinanciamento;
    	this.debitoTipo = debitoTipo;
    	this.financiamentoTipo = debitoTipo.getFinanciamentoTipo();
    	this.lancamentoItemContabil = debitoTipo.getLancamentoItemContabil();
    	this.debitoCreditoSituacaoAtual = new DebitoCreditoSituacao(idDebitoCreditoSituacaoAtual);
    	this.parcelamentoGrupo = parcelamentoGrupo;
    	this.cobrancaForma = new CobrancaForma(idCobrancaForma);
    	this.usuario = usuario;
    	this.ultimaAlteracao = new Date();
    	
    }
    
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getGeracaoDebito() {
		return this.geracaoDebito;
	}

	public void setGeracaoDebito(Date geracaoDebito) {
		this.geracaoDebito = geracaoDebito;
	}

	public Integer getAnoMesReferenciaDebito() {
		return this.anoMesReferenciaDebito;
	}

	public void setAnoMesReferenciaDebito(Integer anoMesReferenciaDebito) {
		this.anoMesReferenciaDebito = anoMesReferenciaDebito;
	}

	public Integer getAnoMesCobrancaDebito() {
		return this.anoMesCobrancaDebito;
	}

	public void setAnoMesCobrancaDebito(Integer anoMesCobrancaDebito) {
		this.anoMesCobrancaDebito = anoMesCobrancaDebito;
	}

	public BigDecimal getValorDebito() {
		return this.valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	public short getNumeroPrestacaoDebito() {
		return this.numeroPrestacaoDebito;
	}

	public void setNumeroPrestacaoDebito(short numeroPrestacaoDebito) {
		this.numeroPrestacaoDebito = numeroPrestacaoDebito;
	}

	public short getNumeroPrestacaoCobradas() {
		return this.numeroPrestacaoCobradas;
	}

	public void setNumeroPrestacaoCobradas(short numeroPrestacaoCobradas) {
		this.numeroPrestacaoCobradas = numeroPrestacaoCobradas;
	}

	public Integer getCodigoSetorComercial() {
		return this.codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getNumeroQuadra() {
		return this.numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Short getNumeroLote() {
		return this.numeroLote;
	}

	public void setNumeroLote(Short numeroLote) {
		this.numeroLote = numeroLote;
	}

	public Short getNumeroSubLote() {
		return this.numeroSubLote;
	}

	public void setNumeroSubLote(Short numeroSubLote) {
		this.numeroSubLote = numeroSubLote;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getAnoMesReferenciaContabil() {
		return this.anoMesReferenciaContabil;
	}

	public void setAnoMesReferenciaContabil(Integer anoMesReferenciaContabil) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
	}

	public BigDecimal getPercentualTaxaJurosFinanciamento() {
		return this.percentualTaxaJurosFinanciamento;
	}

	public void setPercentualTaxaJurosFinanciamento(BigDecimal percentualTaxaJurosFinanciamento) {
		this.percentualTaxaJurosFinanciamento = percentualTaxaJurosFinanciamento;
	}

	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public DocumentoTipo getDocumentoTipo() {
		return this.documentoTipo;
	}

	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public Parcelamento getParcelamento() {
		return this.parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	public FinanciamentoTipo getFinanciamentoTipo() {
		return this.financiamentoTipo;
	}

	public void setFinanciamentoTipo(FinanciamentoTipo financiamentoTipo) {
		this.financiamentoTipo = financiamentoTipo;
	}

	public OrdemServico getOrdemServico() {
		return this.ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Quadra getQuadra() {
		return this.quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public gcom.faturamento.debito.DebitoTipo getDebitoTipo() {
		return this.debitoTipo;
	}

	public void setDebitoTipo(gcom.faturamento.debito.DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return this.registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public LancamentoItemContabil getLancamentoItemContabil() {
		return this.lancamentoItemContabil;
	}

	public void setLancamentoItemContabil(LancamentoItemContabil lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	public DebitoCreditoSituacao getDebitoCreditoSituacaoAnterior() {
		return this.debitoCreditoSituacaoAnterior;
	}

	public void setDebitoCreditoSituacaoAnterior(DebitoCreditoSituacao debitoCreditoSituacaoAnterior) {
		this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
	}

	public DebitoCreditoSituacao getDebitoCreditoSituacaoAtual() {
		return this.debitoCreditoSituacaoAtual;
	}

	public void setDebitoCreditoSituacaoAtual(DebitoCreditoSituacao debitoCreditoSituacaoAtual) {
		this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
	}

	public ParcelamentoGrupo getParcelamentoGrupo() {
		return this.parcelamentoGrupo;
	}

	public void setParcelamentoGrupo(ParcelamentoGrupo parcelamentoGrupo) {
		this.parcelamentoGrupo = parcelamentoGrupo;
	}

	public CobrancaForma getCobrancaForma() {
		return this.cobrancaForma;
	}

	public void setCobrancaForma(CobrancaForma cobrancaForma) {
		this.cobrancaForma = cobrancaForma;
	}

	@SuppressWarnings("rawtypes")
	public Set getDebitoACobrarCategorias() {
		return this.debitoACobrarCategorias;
	}

	@SuppressWarnings("rawtypes")
	public void setDebitoACobrarCategorias(Set debitoACobrarCategorias) {
		this.debitoACobrarCategorias = debitoACobrarCategorias;
	}

	public String toString() {
		return "DebitoACobrar [id=" + id + "]";
	}

	public BigDecimal getValorTotal() {

		BigDecimal retornoDivisao = new BigDecimal("0.00");
		BigDecimal retornoMultiplicacao = new BigDecimal("0.00");
		BigDecimal retorno = new BigDecimal("0.00");

		if (valorDebito != null) {
			retornoDivisao = getValorPrestacao();
		}

		retornoMultiplicacao = retornoDivisao.multiply(new BigDecimal(numeroPrestacaoCobradas));
		retorno = this.valorDebito.subtract(retornoMultiplicacao);

		return retorno;
	}

	public BigDecimal getValorDebitoPorCategoria() {
		return valorDebitoPorCategoria;
	}

	public void setValorDebitoPorCategoria(BigDecimal valorDebitoPorCategoria) {
		this.valorDebitoPorCategoria = valorDebitoPorCategoria;
	}

	public String getFormatarAnoMesCobrancaDebito() {

		String anoMesFormatado = "";

		if (this.getAnoMesCobrancaDebito() != null && !this.getAnoMesCobrancaDebito().toString().trim().equals("")) {
			String anoMesRecebido = "" + this.getAnoMesCobrancaDebito();
			String mes = anoMesRecebido.substring(4, 6);
			String ano = anoMesRecebido.substring(0, 4);
			anoMesFormatado = mes + "/" + ano;
		}

		return anoMesFormatado.toString();
	}

	public String getFormatarAnoMesReferenciaDebito() {

		String anoMesFormatado = "";

		if (this.getAnoMesReferenciaDebito() != null && !this.getAnoMesReferenciaDebito().toString().trim().equals("")) {
			String anoMesRecebido = "" + this.getAnoMesReferenciaDebito();
			String mes = anoMesRecebido.substring(4, 6);
			String ano = anoMesRecebido.substring(0, 4);
			anoMesFormatado = mes + "/" + ano;
		}

		return anoMesFormatado.toString();
	}

	public String getFormatarAnoMesReferenciaContabil() {

		String anoMesFormatado = "";

		if (this.getAnoMesReferenciaContabil() != null && !this.getAnoMesReferenciaContabil().toString().trim().equals("")) {
			String anoMesRecebido = "" + this.getAnoMesReferenciaContabil();
			String mes = anoMesRecebido.substring(4, 6);
			String ano = anoMesRecebido.substring(0, 4);
			anoMesFormatado = mes + "/" + ano;
		}

		return anoMesFormatado.toString();
	}

	public short getParcelasRestante() {

		short retorno = Short.parseShort("" + (getNumeroPrestacaoDebito() - getNumeroPrestacaoCobradas()));

		return retorno;
	}

	public DebitoACobrarGeral getDebitoACobrarGeral() {
		return debitoACobrarGeral;
	}

	public void setDebitoACobrarGeral(DebitoACobrarGeral debitoACobrarGeral) {
		this.debitoACobrarGeral = debitoACobrarGeral;
	}

	public DebitoACobrarGeral getDebitoACobrarGeralOrigem() {
		return debitoACobrarGeralOrigem;
	}

	public void setDebitoACobrarGeralOrigem(DebitoACobrarGeral debitoACobrarGeralOrigem) {
		this.debitoACobrarGeralOrigem = debitoACobrarGeralOrigem;
	}

	@Override
	public void initializeLazy() {
		if (debitoTipo != null) {
			getDebitoTipo().initializeLazy();
		}
		if (lancamentoItemContabil != null) {
			getLancamentoItemContabil().initializeLazy();
		}
		if (financiamentoTipo != null) {
			getFinanciamentoTipo().initializeLazy();
		}
		if (registroAtendimento != null) {
			getRegistroAtendimento().initializeLazy();
		}
		if (ordemServico != null) {
			getOrdemServico().initializeLazy();
		}
	}

	public Short getNumeroParcelaBonus() {
		return numeroParcelaBonus;
	}

	public void setNumeroParcelaBonus(Short numeroParcelaBonus) {
		this.numeroParcelaBonus = numeroParcelaBonus;
	}

	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String[] atributos = { "debitoTipo.descricao", "valorDebito" };
		return atributos;
	}

	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String[] labels = { "Tipo Debito", "Valor" };
		return labels;
	}

	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public BigDecimal getValorTotalDebito() {
		return valorTotalDebito;
	}

	public Integer getAnoMesReferenciaPrestacao() {
		return anoMesReferenciaPrestacao;
	}

	public void setAnoMesReferenciaPrestacao(Integer anoMesReferenciaPrestacao) {
		this.anoMesReferenciaPrestacao = anoMesReferenciaPrestacao;
	}

	public short getParcelasRestanteComBonus() {

		short retorno = (short) (getNumeroPrestacaoDebito() - getNumeroPrestacaoCobradas());

		if (getNumeroParcelaBonus() != null) {
			retorno = (short) (retorno - getNumeroParcelaBonus().shortValue());
		}

		return retorno;
	}

	public void setValorTotalDebito(BigDecimal valorTotalDebito) {
		this.valorTotalDebito = valorTotalDebito;
	}

	public BigDecimal getValorTotalComBonus() {
		BigDecimal retornoDivisao = new BigDecimal("0.00");
		BigDecimal retornoMultiplicacao = new BigDecimal("0.00");
		BigDecimal retorno = new BigDecimal("0.00");

		Short bonus = 0;
		if (numeroParcelaBonus != null) {
			bonus = numeroParcelaBonus;
		}

		if (numeroPrestacaoCobradas == numeroPrestacaoDebito - bonus) {
			retorno = new BigDecimal("0.00");

		} else {
			if (valorDebito != null) {
				retornoDivisao = getValorPrestacao();
			}
			retornoMultiplicacao = retornoDivisao.multiply(new BigDecimal(numeroPrestacaoCobradas + bonus));
			retorno = this.valorDebito.subtract(retornoMultiplicacao);
		}

		if (retorno != null) {
			return retorno.setScale(2);
		} else {
			return retorno;
		}
	}

	public short getNumeroPrestacaoDebitoMenosBonus() {
		short retorno = getNumeroPrestacaoDebito();

		if (getNumeroParcelaBonus() != null) {
			retorno = Short.parseShort("" + (retorno - getNumeroParcelaBonus().shortValue()));
		}

		return retorno;
	}

	public BigDecimal getValorPrestacao() {
		BigDecimal retornoDivisao = this.valorDebito.divide(new BigDecimal(numeroPrestacaoDebito), 2, BigDecimal.ROUND_DOWN);

		return retornoDivisao;
	}

	public short getNumeroPrestacaoCobradasMaisBonus() {
		short retorno = getNumeroPrestacaoCobradas();

		if (getNumeroParcelaBonus() != null) {
			retorno = Short.parseShort("" + (retorno + getNumeroParcelaBonus().shortValue()));
		}

		return retorno;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataRevisao() {
		return dataRevisao;
	}

	public void setDataRevisao(Date dataRevisao) {
		this.dataRevisao = dataRevisao;
	}

	public Integer getSituacaoAtual() {
		return situacaoAtual;
	}

	public void setSituacaoAtual(Integer situacaoAtual) {
		this.situacaoAtual = situacaoAtual;
	}

	public ContaMotivoRevisao getContaMotivoRevisao() {
		return contaMotivoRevisao;
	}

	public void setContaMotivoRevisao(ContaMotivoRevisao contaMotivoRevisao) {
		this.contaMotivoRevisao = contaMotivoRevisao;
	}

	public BigDecimal getValorParcela() {
		return getValorPrestacao();
	}

	public short getNumeroPrestacaoRestante() {
		short retorno = this.getNumeroPrestacaoDebitoMenosBonus();
		return Short.parseShort("" + (retorno - this.getNumeroPrestacaoCobradas()));
	}

	public boolean isAntecipacaoParcela() {
		if (this.getNumeroPrestacaoRestante() > 1) {
			return true;
		} else {
			return false;
		}
	}

	public BigDecimal getValorAntecipacaoParcela(int quantidadePrestacoes) {

		return this.getValorPrestacao().multiply(BigDecimal.valueOf(quantidadePrestacoes));
	}

	public Integer getNumeroParcelasAntecipadas() {
		return numeroParcelasAntecipadas;
	}

	public void setNumeroParcelasAntecipadas(Integer numeroParcelasAntecipadas) {
		this.numeroParcelasAntecipadas = numeroParcelasAntecipadas;
	}

	public boolean equals(Object other) {

		boolean retorno = false;

		if (other instanceof DebitoACobrar) {

			DebitoACobrar castOther = (DebitoACobrar) other;

			retorno = this.getId().compareTo(castOther.getId()) == 0;
		}

		return retorno;
	}

	public boolean pertenceParcelamento(int anoMesReferencia) {
		return parcelamento != null && parcelamento.getAnoMesReferenciaFaturamento() != null && parcelamento.getAnoMesReferenciaFaturamento() >= anoMesReferencia && this.naPrimeiraParcela();
	}

	private boolean naPrimeiraParcela() {
		return numeroPrestacaoCobradas == 0;
	}
}
