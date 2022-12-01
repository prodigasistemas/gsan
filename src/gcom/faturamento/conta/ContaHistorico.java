package gcom.faturamento.conta;

import gcom.arrecadacao.banco.ContaBancaria;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FaturamentoTipo;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ContaHistorico implements IConta {
	private static final long serialVersionUID = 1L;
    
    private Integer id;
    private int anoMesReferenciaConta;
    private Date contaHistorico;
    private Short lote;
    private Short subLote;
    private Integer setorComercial;
    private Integer numeroQuadra;
    private short verificadorConta;
    private short indicadorCobrancaMulta;
    private Short indicadorAlteracaoVencimento;
    private Integer consumoAgua;
    private Integer consumoEsgoto;
    private Integer consumoRateioAgua;
    private Integer consumoRateioEsgoto;
    private BigDecimal valorAgua;
    private BigDecimal valorImposto;
    private BigDecimal valorEsgoto;
    private BigDecimal valorDebitos;
    private BigDecimal valorCreditos;
    private BigDecimal percentualEsgoto;
    private Date dataVencimentoConta;
    private Date dataVencimentoOriginal;
    private Date dataValidadeConta;
    private Date dataInclusao;
    private Date dataRevisao;
    private Date dataRetificacao;
    private Date dataCancelamento;
    private Date dataEmissao;
    private Integer anoMesReferenciaContabil;
    private Integer anoMesReferenciaBaixaContabil;
    private Short indicadorDebitoConta;
    private Date ultimaAlteracao;
    private Integer numeroRetificacoes;
    private Date dataEnvioEmailConta;
    private BigDecimal valorRateioAgua;
    private BigDecimal valorRateioEsgoto;
    private String numeroFatura;
    private Integer numeroLeituraAtual;
    private Integer numeroLeituraAnterior;
    private BigDecimal percentualColeta;
    private Integer numeroLeituraAtualPoco;
    private Integer numeroLeituraAnteriorPoco;
    private Integer numeroVolumePoco;
    private Integer numeroBoleto;

    private FaturamentoTipo faturamentoTipo;
    private ContaMotivoCancelamento contaMotivoCancelamento;
    private ContaBancaria contaBancaria;
    private ContaMotivoInclusao contaMotivoInclusao;
    private DebitoCreditoSituacao debitoCreditoSituacaoAnterior;
    private DebitoCreditoSituacao debitoCreditoSituacaoAtual;
    private ContaMotivoRevisao contaMotivoRevisao;
    private RegistroAtendimento registroAtendimento;
    private Imovel imovel;
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
    private DocumentoTipo documentoTipo;
    private ConsumoTarifa consumoTarifa;
    private ImovelPerfil imovelPerfil;
    private Quadra quadra;
    private Localidade localidade;
    private MotivoNaoEntregaDocumento motivoNaoEntregaDocumento;
    private LigacaoAguaSituacao ligacaoAguaSituacao;
    private ContaMotivoRetificacao contaMotivoRetificacao;
    private Funcionario funcionarioEntrega;
    private Funcionario funcionarioLeitura;
    private Parcelamento parcelamento;
    private ContaGeral origem;
    private Usuario usuario;
    private FaturamentoGrupo faturamentoGrupo;
    private Rota rota;
    
    @SuppressWarnings("rawtypes")
	private Set creditoRealizadoHistoricos;
    
    @SuppressWarnings("rawtypes")
    private Set debitoCobradoHistoricos;
    
    @SuppressWarnings("rawtypes")
    private Set parcelamentoItems;
    
    private BigDecimal valorTotalDaConta;
	
    public Integer getNumeroLeituraAnteriorPoco() {
		return numeroLeituraAnteriorPoco;
	}

	public void setNumeroLeituraAnteriorPoco(Integer numeroLeituraAnteriorPoco) {
		this.numeroLeituraAnteriorPoco = numeroLeituraAnteriorPoco;
	}

	public Integer getNumeroLeituraAtualPoco() {
		return numeroLeituraAtualPoco;
	}

	public void setNumeroLeituraAtualPoco(Integer numeroLeituraAtualPoco) {
		this.numeroLeituraAtualPoco = numeroLeituraAtualPoco;
	}

	public Integer getNumeroVolumePoco() {
		return numeroVolumePoco;
	}

	public void setNumeroVolumePoco(Integer numeroVolumePoco) {
		this.numeroVolumePoco = numeroVolumePoco;
	}


	public BigDecimal getPercentualColeta() {
		return percentualColeta;
	}

	public void setPercentualColeta(BigDecimal percentualColeta) {
		this.percentualColeta = percentualColeta;
	}

	public Integer getNumeroLeituraAnterior() {
		return numeroLeituraAnterior;
	}

	public void setNumeroLeituraAnterior(Integer numeroLeituraAnterior) {
		this.numeroLeituraAnterior = numeroLeituraAnterior;
	}

	public Integer getNumeroLeituraAtual() {
		return numeroLeituraAtual;
	}

	public void setNumeroLeituraAtual(Integer numeroLeituraAtual) {
		this.numeroLeituraAtual = numeroLeituraAtual;
	}

	@SuppressWarnings("rawtypes")
	public ContaHistorico(Integer id, int anoMesReferenciaConta, Date contaHistorico, 
    		Short lote, Short sublote, Integer setorComercial, Integer numeroQuadra, 
    		short verificadorConta, short indicadorCobrancaMulta, Short indicadorAlteracaoVencimento, 
    		Integer consumoAgua, Integer consumoEsgoto, Integer consumoRateioAgua, 
    		Integer consumoRateioEsgoto, BigDecimal valorAgua, BigDecimal valorEsgoto, 
    		BigDecimal valorDebitos, BigDecimal valorCreditos, BigDecimal percentualEsgoto, 
    		Date dataVencimentoConta, Date dataValidadeConta, Date dataInclusao, 
    		Date dataRevisao, Date dataRetificacao, Date dataCancelamento, 
    		Date dataEmissao, Integer anoMesReferenciaContabil, Integer anoMesReferenciaBaixaContabil, 
    		Short indicadorDebitoConta, Date ultimaAlteracao, FaturamentoTipo faturamentoTipo, 
    		ContaMotivoCancelamento contaMotivoCancelamento, 
    		ContaBancaria contaBancaria, ContaMotivoInclusao contaMotivoInclusao, 
    		DebitoCreditoSituacao debitoCreditoSituacaoAnterior,
    		DebitoCreditoSituacao debitoCreditoSituacaoAtual, 
    		ContaMotivoRevisao contaMotivoRevisao, 
    		RegistroAtendimento registroAtendimento, 
    		Imovel imovel, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, 
    		DocumentoTipo documentoTipo, ConsumoTarifa consumoTarifa, 
    		ImovelPerfil imovelPerfil, Quadra quadra,
    		Localidade localidade, MotivoNaoEntregaDocumento motivoNaoEntregaDocumento, 
    		LigacaoAguaSituacao ligacaoAguaSituacao, 
    		ContaMotivoRetificacao contaMotivoRetificacao, 
    		Funcionario funcionarioEntrega, 
    		Funcionario funcionarioLeitura, Set creditoRealizadoHistoricos, 
    		Set debitoCobradoHistoricos, Set parcelamentoItems, Integer numeroRetificacoes,
    		BigDecimal valorTotalDaConta
			) {
        this.id = id;
        this.anoMesReferenciaConta = anoMesReferenciaConta;
        this.contaHistorico = contaHistorico;
        this.lote = lote;
        this.subLote = sublote;
        this.setorComercial = setorComercial;
        this.numeroQuadra = numeroQuadra;
        this.verificadorConta = verificadorConta;
        this.indicadorCobrancaMulta = indicadorCobrancaMulta;
        this.indicadorAlteracaoVencimento = indicadorAlteracaoVencimento;
        this.consumoAgua = consumoAgua;
        this.consumoEsgoto = consumoEsgoto;
        this.consumoRateioAgua = consumoRateioAgua;
        this.consumoRateioEsgoto = consumoRateioEsgoto;
        this.valorAgua = valorAgua;
        this.valorEsgoto = valorEsgoto;
        this.valorDebitos = valorDebitos;
        this.valorCreditos = valorCreditos;
        this.percentualEsgoto = percentualEsgoto;
        this.dataVencimentoConta = dataVencimentoConta;
        this.dataValidadeConta = dataValidadeConta;
        this.dataInclusao = dataInclusao;
        this.dataRevisao = dataRevisao;
        this.dataRetificacao = dataRetificacao;
        this.dataCancelamento = dataCancelamento;
        this.dataEmissao = dataEmissao;
        this.anoMesReferenciaContabil = anoMesReferenciaContabil;
        this.anoMesReferenciaBaixaContabil = anoMesReferenciaBaixaContabil;
        this.indicadorDebitoConta = indicadorDebitoConta;
        this.ultimaAlteracao = ultimaAlteracao;
        this.faturamentoTipo = faturamentoTipo;
        this.contaMotivoCancelamento = contaMotivoCancelamento;
        this.contaBancaria = contaBancaria;
        this.contaMotivoInclusao = contaMotivoInclusao;
        this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
        this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
        this.contaMotivoRevisao = contaMotivoRevisao;
        this.registroAtendimento = registroAtendimento;
        this.imovel = imovel;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.documentoTipo = documentoTipo;
        this.consumoTarifa = consumoTarifa;
        this.imovelPerfil = imovelPerfil;
        this.quadra = quadra;
        this.localidade = localidade;
        this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.contaMotivoRetificacao = contaMotivoRetificacao;
        this.funcionarioEntrega = funcionarioEntrega;
        this.funcionarioLeitura = funcionarioLeitura;
        this.creditoRealizadoHistoricos = creditoRealizadoHistoricos;
        this.debitoCobradoHistoricos = debitoCobradoHistoricos;
        this.parcelamentoItems = parcelamentoItems;
        this.numeroRetificacoes = numeroRetificacoes;
        this.valorTotalDaConta = valorTotalDaConta;
    }

    public ContaHistorico() {
    }

    public ContaHistorico(Integer id) {
    	this.id = id;
    }
    
    @SuppressWarnings("rawtypes")
    public ContaHistorico(Integer id, int anoMesReferenciaConta, Short lote, Short sublote, 
    		Integer setorComercial, Integer numeroQuadra, short verificadorConta, short indicadorCobrancaMulta, 
    		Short indicadorAlteracaoVencimento, Integer consumoAgua, Integer consumoEsgoto, BigDecimal valorAgua, 
    		BigDecimal valorEsgoto, BigDecimal valorDebitos, BigDecimal percentualEsgoto, 
    		Date dataVencimentoConta, Date dataInclusao, FaturamentoTipo faturamentoTipo, 
    		ContaMotivoCancelamento contaMotivoCancelamento, 
    		ContaBancaria contaBancaria, ContaMotivoInclusao contaMotivoInclusao, 
    		DebitoCreditoSituacao debitoCreditoSituacaoAnterior, 
    		DebitoCreditoSituacao debitoCreditoSituacaoAtual, 
    		ContaMotivoRevisao contaMotivoRevisao, RegistroAtendimento registroAtendimento, 
    		Imovel imovel, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, 
    		DocumentoTipo documentoTipo, ConsumoTarifa consumoTarifa, 
    		ImovelPerfil imovelPerfil, Quadra quadra, 
    		Localidade localidade, MotivoNaoEntregaDocumento motivoNaoEntregaDocumento, 
    		LigacaoAguaSituacao ligacaoAguaSituacao, 
    		ContaMotivoRetificacao contaMotivoRetificacao, 
    		Funcionario funcionarioEntrega, 
    		Funcionario funcionarioLeitura, 
    		Set creditoRealizadoHistoricos, Set debitoCobradoHistoricos, Set parcelamentoItems) {

        this.id = id;
        this.anoMesReferenciaConta = anoMesReferenciaConta;
        this.lote = lote;
        this.setorComercial = setorComercial;
        this.numeroQuadra = numeroQuadra;
        this.verificadorConta = verificadorConta;
        this.indicadorCobrancaMulta = indicadorCobrancaMulta;
        this.indicadorAlteracaoVencimento = indicadorAlteracaoVencimento;
        this.consumoAgua = consumoAgua;
        this.consumoEsgoto = consumoEsgoto;
        this.valorAgua = valorAgua;
        this.valorEsgoto = valorEsgoto;
        this.valorDebitos = valorDebitos;
        this.percentualEsgoto = percentualEsgoto;
        this.dataVencimentoConta = dataVencimentoConta;
        this.dataInclusao = dataInclusao;
        this.faturamentoTipo = faturamentoTipo;
        this.contaMotivoCancelamento = contaMotivoCancelamento;
        this.contaBancaria = contaBancaria;
        this.contaMotivoInclusao = contaMotivoInclusao;
        this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
        this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
        this.contaMotivoRevisao = contaMotivoRevisao;
        this.registroAtendimento = registroAtendimento;
        this.imovel = imovel;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.documentoTipo = documentoTipo;
        this.consumoTarifa = consumoTarifa;
        this.imovelPerfil = imovelPerfil;
        this.quadra = quadra;
        this.localidade = localidade;
        this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.contaMotivoRetificacao = contaMotivoRetificacao;
        this.funcionarioEntrega = funcionarioEntrega;
        this.funcionarioLeitura = funcionarioLeitura;
        this.creditoRealizadoHistoricos = creditoRealizadoHistoricos;
        this.debitoCobradoHistoricos = debitoCobradoHistoricos;
        this.parcelamentoItems = parcelamentoItems;
    }

	public Short getIndicadorAlteracaoVencimento() {
		return indicadorAlteracaoVencimento;
	}

	public void setIndicadorAlteracaoVencimento(Short indicadorAlteracaoVencimento) {
		this.indicadorAlteracaoVencimento = indicadorAlteracaoVencimento;
	}

	public Integer getAnoMesReferenciaBaixaContabil() {
		return anoMesReferenciaBaixaContabil;
	}

	public void setAnoMesReferenciaBaixaContabil(Integer anoMesReferenciaBaixaContabil) {
		this.anoMesReferenciaBaixaContabil = anoMesReferenciaBaixaContabil;
	}

	public int getAnoMesReferenciaConta() {
		return anoMesReferenciaConta;
	}
	
	public int getReferencia(){
		return anoMesReferenciaConta;
	}
	
	public void setReferencia(int ref){
		anoMesReferenciaConta = ref;
	}

	public void setAnoMesReferenciaConta(int anoMesReferenciaConta) {
		this.anoMesReferenciaConta = anoMesReferenciaConta;
	}

	public Integer getAnoMesReferenciaContabil() {
		return anoMesReferenciaContabil;
	}

	public void setAnoMesReferenciaContabil(Integer anoMesReferenciaContabil) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
	}

	public Short getIndicadorCobrancaMulta() {
		return indicadorCobrancaMulta;
	}

	public void setIndicadorCobrancaMulta(Short indicadorCobrancaMulta) {
		this.indicadorCobrancaMulta = indicadorCobrancaMulta;
	}

	public Integer getConsumoAgua() {
		return consumoAgua;
	}

	public void setConsumoAgua(Integer consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	public Integer getConsumoEsgoto() {
		return consumoEsgoto;
	}

	public void setConsumoEsgoto(Integer consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}

	public Integer getConsumoRateioAgua() {
		return consumoRateioAgua;
	}

	public void setConsumoRateioAgua(Integer consumoRateioAgua) {
		this.consumoRateioAgua = consumoRateioAgua;
	}

	public Integer getConsumoRateioEsgoto() {
		return consumoRateioEsgoto;
	}

	public void setConsumoRateioEsgoto(Integer consumoRateioEsgoto) {
		this.consumoRateioEsgoto = consumoRateioEsgoto;
	}

	public ConsumoTarifa getConsumoTarifa() {
		return consumoTarifa;
	}

	public void setConsumoTarifa(ConsumoTarifa consumoTarifa) {
		this.consumoTarifa = consumoTarifa;
	}

	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public Date getContaHistorico() {
		return contaHistorico;
	}

	public void setContaHistorico(Date contaHistorico) {
		this.contaHistorico = contaHistorico;
	}

	public ContaMotivoCancelamento getContaMotivoCancelamento() {
		return contaMotivoCancelamento;
	}

	public void setContaMotivoCancelamento(ContaMotivoCancelamento contaMotivoCancelamento) {
		this.contaMotivoCancelamento = contaMotivoCancelamento;
	}

	public ContaMotivoInclusao getContaMotivoInclusao() {
		return contaMotivoInclusao;
	}

	public void setContaMotivoInclusao(ContaMotivoInclusao contaMotivoInclusao) {
		this.contaMotivoInclusao = contaMotivoInclusao;
	}

	public ContaMotivoRetificacao getContaMotivoRetificacao() {
		return contaMotivoRetificacao;
	}

	public void setContaMotivoRetificacao(ContaMotivoRetificacao contaMotivoRetificacao) {
		this.contaMotivoRetificacao = contaMotivoRetificacao;
	}

	public ContaMotivoRevisao getContaMotivoRevisao() {
		return contaMotivoRevisao;
	}

	public void setContaMotivoRevisao(ContaMotivoRevisao contaMotivoRevisao) {
		this.contaMotivoRevisao = contaMotivoRevisao;
	}

	@SuppressWarnings("rawtypes")
	public Set getCreditoRealizadoHistoricos() {
		return creditoRealizadoHistoricos;
	}

	@SuppressWarnings("rawtypes")
	public void setCreditoRealizadoHistoricos(Set creditoRealizadoHistoricos) {
		this.creditoRealizadoHistoricos = creditoRealizadoHistoricos;
	}

	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataRetificacao() {
		return dataRetificacao;
	}

	public void setDataRetificacao(Date dataRetificacao) {
		this.dataRetificacao = dataRetificacao;
	}

	public Date getDataRevisao() {
		return dataRevisao;
	}

	public void setDataRevisao(Date dataRevisao) {
		this.dataRevisao = dataRevisao;
	}

	public Date getDataValidadeConta() {
		return dataValidadeConta;
	}

	public void setDataValidadeConta(Date dataValidadeConta) {
		this.dataValidadeConta = dataValidadeConta;
	}

	public Date getDataVencimentoConta() {
		return dataVencimentoConta;
	}

	public void setDataVencimentoConta(Date dataVencimentoConta) {
		this.dataVencimentoConta = dataVencimentoConta;
	}

	@SuppressWarnings("rawtypes")
	public Set getDebitoCobradoHistoricos() {
		return debitoCobradoHistoricos;
	}

	@SuppressWarnings("rawtypes")
	public void setDebitoCobradoHistoricos(Set debitoCobradoHistoricos) {
		this.debitoCobradoHistoricos = debitoCobradoHistoricos;
	}

	public Short getIndicadorDebitoConta() {
		return indicadorDebitoConta;
	}

	public void setIndicadorDebitoConta(Short indicadorDebitoConta) {
		this.indicadorDebitoConta = indicadorDebitoConta;
	}

	public DebitoCreditoSituacao getDebitoCreditoSituacaoAnterior() {
		return debitoCreditoSituacaoAnterior;
	}

	public void setDebitoCreditoSituacaoAnterior(DebitoCreditoSituacao debitoCreditoSituacaoAnterior) {
		this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
	}

	public DebitoCreditoSituacao getDebitoCreditoSituacaoAtual() {
		return debitoCreditoSituacaoAtual;
	}

	public void setDebitoCreditoSituacaoAtual(DebitoCreditoSituacao debitoCreditoSituacaoAtual) {
		this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
	}

	public DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}

	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public FaturamentoTipo getFaturamentoTipo() {
		return faturamentoTipo;
	}

	public void setFaturamentoTipo(FaturamentoTipo faturamentoTipo) {
		this.faturamentoTipo = faturamentoTipo;
	}

	public Funcionario getFuncionarioEntrega() {
		return funcionarioEntrega;
	}

	public void setFuncionarioEntrega(Funcionario funcionarioEntrega) {
		this.funcionarioEntrega = funcionarioEntrega;
	}

	public Funcionario getFuncionarioLeitura() {
		return funcionarioLeitura;
	}

	public void setFuncionarioLeitura(Funcionario funcionarioLeitura) {
		this.funcionarioLeitura = funcionarioLeitura;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public MotivoNaoEntregaDocumento getMotivoNaoEntregaDocumento() {
		return motivoNaoEntregaDocumento;
	}

	public void setMotivoNaoEntregaDocumento(MotivoNaoEntregaDocumento motivoNaoEntregaDocumento) {
		this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
	}

	public Short getLote() {
		return lote;
	}

	public void setLote(Short lote) {
		this.lote = lote;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Short getSubLote() {
		return subLote;
	}

	public void setSubLote(Short sublote) {
		this.subLote = sublote;
	}

	@SuppressWarnings("rawtypes")
	public Set getParcelamentoItems() {
		return parcelamentoItems;
	}

	@SuppressWarnings("rawtypes")
	public void setParcelamentoItems(Set parcelamentoItems) {
		this.parcelamentoItems = parcelamentoItems;
	}

	public BigDecimal getPercentualEsgoto() {
		return percentualEsgoto;
	}

	public void setPercentualEsgoto(BigDecimal percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}

	public Quadra getQuadra() {
		return quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public Integer getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(Integer setorComercial) {
		this.setorComercial = setorComercial;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorAgua() {
		return valorAgua;
	}

	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}

	public BigDecimal getValorCreditos() {
		return valorCreditos;
	}

	public void setValorCreditos(BigDecimal valorCreditos) {
		this.valorCreditos = valorCreditos;
	}

	public BigDecimal getValorDebitos() {
		return valorDebitos;
	}

	public void setValorDebitos(BigDecimal valorDebitos) {
		this.valorDebitos = valorDebitos;
	}

	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}

	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	public short getVerificadorConta() {
		return verificadorConta;
	}

	public void setVerificadorConta(short verificadorConta) {
		this.verificadorConta = verificadorConta;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
	
	public BigDecimal getValorTotal() {

		BigDecimal valorTotalConta = new BigDecimal("0.00");

		if (this.getValorAgua() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorAgua());
		}

		if (this.getValorEsgoto() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorEsgoto());
		}

		if (this.getValorDebitos() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorDebitos());
		}

		if (this.getValorCreditos() != null) {
			valorTotalConta = valorTotalConta.subtract(this.getValorCreditos());
		}
		
		if (this.getValorImposto() != null) {
			valorTotalConta = valorTotalConta.subtract(this.getValorImposto());
		}
		
		valorTotalConta = valorTotalConta.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return valorTotalConta;	
	}

	public Date getDataVencimentoOriginal() {
		return dataVencimentoOriginal;
	}

	public void setDataVencimentoOriginal(Date dataVencimentoOriginal) {
		this.dataVencimentoOriginal = dataVencimentoOriginal;
	}

	public Parcelamento getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

    public BigDecimal getValorImposto() {
        return valorImposto;
    }

    public void setValorImposto(BigDecimal valorImposto) {
        this.valorImposto = valorImposto;
    }
    
	public String getFormatarAnoMesParaMesAno() {

		String anoMesRecebido = "" + this.getAnoMesReferenciaConta();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatado = mes + "/" + ano;

		return anoMesFormatado.toString();
	}

	public ContaGeral getOrigem() {
		return origem;
	}

	public void setOrigem(ContaGeral origem) {
		this.origem = origem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getNumeroRetificacoes() {
		return numeroRetificacoes;
	}

	public void setNumeroRetificacoes(Integer numeroRetificacoes) {
		this.numeroRetificacoes = numeroRetificacoes;
	}

	public String getNumeroFatura() {
		return numeroFatura;
	}

	public void setNumeroFatura(String numeroFatura) {
		this.numeroFatura = numeroFatura;
	}

	public FaturamentoGrupo getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public Integer getNumeroBoleto() {
		return numeroBoleto;
	}

	public void setNumeroBoleto(Integer numeroBoleto) {
		this.numeroBoleto = numeroBoleto;
	}

	public Date getDataEnvioEmailConta() {
		return dataEnvioEmailConta;
	}

	public void setDataEnvioEmailConta(Date dataEnvioEmailConta) {
		this.dataEnvioEmailConta = dataEnvioEmailConta;
	}

	public BigDecimal getValorRateioAgua() {
		return valorRateioAgua;
	}

	public void setValorRateioAgua(BigDecimal valorRateioAgua) {
		this.valorRateioAgua = valorRateioAgua;
	}

	public BigDecimal getValorRateioEsgoto() {
		return valorRateioEsgoto;
	}

	public void setValorRateioEsgoto(BigDecimal valorRateioEsgoto) {
		this.valorRateioEsgoto = valorRateioEsgoto;
	}

	public void setDebitos(BigDecimal debitos) {
		this.valorDebitos = debitos;
	}

	public Integer getReferenciaContabil() {
		return this.anoMesReferenciaContabil;
	}
	
	public void setReferenciaContabil(Integer referenciaContabil) {
		this.anoMesReferenciaContabil = referenciaContabil;
	}
	
	public Conta buildConta(Conta conta){
		conta.setCodigoSetorComercial(this.getSetorComercial());
		conta.setQuadra(this.getQuadra().getId());
		conta.setDigitoVerificadorConta(this.getVerificadorConta());
		conta.setQuadra(this.getNumeroQuadra());
		conta.setQuadraConta(this.getQuadra());
		return conta;
	}
	
	public BigDecimal getValorTotalContaBigDecimal() {
		BigDecimal valorTotalConta = new BigDecimal("0.00");

		if (this.getValorAgua() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorAgua());
		}

		if (this.getValorEsgoto() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorEsgoto());
		}

		if (this.getValorDebitos() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorDebitos());
		}

		if (this.getValorCreditos() != null) {
			valorTotalConta = valorTotalConta.subtract(this.getValorCreditos());
		}

		if (this.getValorImposto() != null) {
			valorTotalConta = valorTotalConta.subtract(this.getValorImposto());
		}

		return valorTotalConta;
	}
	

	public BigDecimal getValorTotalDaConta() {
		valorTotalDaConta  = getValorTotal();
		return valorTotalDaConta;
	}

	public void setValorTotalDaConta(BigDecimal valorTotalDaConta) {
		this.valorTotalDaConta = valorTotalDaConta;
	}
}
