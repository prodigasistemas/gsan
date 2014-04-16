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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ContaHistorico implements IConta {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferenciaConta;

    /** nullable persistent field */
    private Date contaHistorico;

    /** persistent field */
    private Short lote;

    /** persistent field */
    private Short sublote;

    /** persistent field */
    private Integer setorComercial;

    /** persistent field */
    private Integer numeroQuadra;

    /** persistent field */
    private short verificadorConta;

    /** persistent field */
    private short indicadorCobrancaMulta;

    /** persistent field */
    private Short indicadorAlteracaoVencimento;

    /** persistent field */
    private Integer consumoAgua;

    /** persistent field */
    private Integer consumoEsgoto;

    /** nullable persistent field */
    private Integer consumoRateioAgua;

    /** nullable persistent field */
    private Integer consumoRateioEsgoto;

    /** persistent field */
    private BigDecimal valorAgua;

    /** persistent field */
    private BigDecimal valorImposto;
    
    /** persistent field */
    private BigDecimal valorEsgoto;

    /** persistent field */
    private BigDecimal valorDebitos;

    /** nullable persistent field */
    private BigDecimal valorCreditos;

    /** persistent field */
    private BigDecimal percentualEsgoto;

    /** persistent field */
    private Date dataVencimentoConta;
    
    private Date dataVencimentoOriginal;

    /** nullable persistent field */
    private Date dataValidadeConta;

    /** persistent field */
    private Date dataInclusao;

    /** nullable persistent field */
    private Date dataRevisao;

    /** nullable persistent field */
    private Date dataRetificacao;

    /** nullable persistent field */
    private Date dataCancelamento;

    /** nullable persistent field */
    private Date dataEmissao;

    /** nullable persistent field */
    private Integer anoMesReferenciaContabil;

    /** nullable persistent field */
    private Integer anoMesReferenciaBaixaContabil;

    /** nullable persistent field */
    private Short indicadorDebitoConta;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** nullable persistent field */
    private Integer numeroRetificacoes;

    /** persistent field */
    private FaturamentoTipo faturamentoTipo;

    /** persistent field */
    private ContaMotivoCancelamento contaMotivoCancelamento;

    /** persistent field */
    private ContaBancaria contaBancaria;

    /** persistent field */
    private ContaMotivoInclusao contaMotivoInclusao;

    /** persistent field */
    private DebitoCreditoSituacao debitoCreditoSituacaoAnterior;

    /** persistent field */
    private DebitoCreditoSituacao debitoCreditoSituacaoAtual;

    /** persistent field */
    private ContaMotivoRevisao contaMotivoRevisao;

    /** persistent field */
    private RegistroAtendimento registroAtendimento;

    /** persistent field */
    private Imovel imovel;

    /** persistent field */
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

    /** persistent field */
    private DocumentoTipo documentoTipo;

    /** persistent field */
    private ConsumoTarifa consumoTarifa;

    /** persistent field */
    private ImovelPerfil imovelPerfil;

    /** persistent field */
    private Quadra quadra;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private MotivoNaoEntregaDocumento motivoNaoEntregaDocumento;

    /** persistent field */
    private LigacaoAguaSituacao ligacaoAguaSituacao;

    /** persistent field */
    private ContaMotivoRetificacao contaMotivoRetificacao;

    /** persistent field */
    private Funcionario funcionarioEntrega;

    /** persistent field */
    private Funcionario funcionarioLeitura;

    /** persistent field */
    private Set creditoRealizadoHistoricos;

    /** persistent field */
    private Set debitoCobradoHistoricos;

    /** persistent field */
    private Set parcelamentoItems;
    
	/** persistent field */
    private Date dataEnvioEmailConta;

	/**TODO: COSANPA
     * Mantis 648 - Atualização de rotina que transfere contas para histórico.
     * 
     * @author: Wellington Rocha
     * @date: 07/11/2012*/
    private BigDecimal valorRateioAgua;
	
	private BigDecimal valorRateioEsgoto;
    
    //
    private Parcelamento parcelamento;
    
    private ContaGeral origem;
    
    private Usuario usuario;
    
    private String numeroFatura;
    
    private FaturamentoGrupo faturamentoGrupo;
	
    private Rota rota;
    
	private Integer numeroLeituraAtual;
	private Integer numeroLeituraAnterior;
	
	private BigDecimal percentualColeta;
	private Integer numeroLeituraAtualPoco;
	private Integer numeroLeituraAnteriorPoco;
	private Integer numeroVolumePoco;
	
	private Integer numeroBoleto;
	
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

	/** full constructor */
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
    		Set debitoCobradoHistoricos, Set parcelamentoItems, Integer numeroRetificacoes) {
        this.id = id;
        this.anoMesReferenciaConta = anoMesReferenciaConta;
        this.contaHistorico = contaHistorico;
        this.lote = lote;
        this.sublote = sublote;
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
    }

    /** default constructor */
    public ContaHistorico() {
    }

    /** minimal constructor */
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

	/**
	 * @return Returns the alteracaoVencimento.
	 */
	public Short getIndicadorAlteracaoVencimento() {
		return indicadorAlteracaoVencimento;
	}

	/**
	 * @param alteracaoVencimento The alteracaoVencimento to set.
	 */
	public void setIndicadorAlteracaoVencimento(Short indicadorAlteracaoVencimento) {
		this.indicadorAlteracaoVencimento = indicadorAlteracaoVencimento;
	}

	/**
	 * @return Returns the anoMesReferenciaBaixaContabil.
	 */
	public Integer getAnoMesReferenciaBaixaContabil() {
		return anoMesReferenciaBaixaContabil;
	}

	/**
	 * @param anoMesReferenciaBaixaContabil The anoMesReferenciaBaixaContabil to set.
	 */
	public void setAnoMesReferenciaBaixaContabil(
			Integer anoMesReferenciaBaixaContabil) {
		this.anoMesReferenciaBaixaContabil = anoMesReferenciaBaixaContabil;
	}

	/**
	 * @return Returns the anoMesReferenciaConta.
	 */
	public int getAnoMesReferenciaConta() {
		return anoMesReferenciaConta;
	}
	
	public int getReferencia(){
		return anoMesReferenciaConta;
	}

	/**
	 * @param anoMesReferenciaConta The anoMesReferenciaConta to set.
	 */
	public void setAnoMesReferenciaConta(int anoMesReferenciaConta) {
		this.anoMesReferenciaConta = anoMesReferenciaConta;
	}

	/**
	 * @return Returns the anoMesReferenciaContabil.
	 */
	public Integer getAnoMesReferenciaContabil() {
		return anoMesReferenciaContabil;
	}

	/**
	 * @param anoMesReferenciaContabil The anoMesReferenciaContabil to set.
	 */
	public void setAnoMesReferenciaContabil(Integer anoMesReferenciaContabil) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
	}

	/**
	 * @return Returns the cobrancaMulta.
	 */
	public short getIndicadorCobrancaMulta() {
		return indicadorCobrancaMulta;
	}

	/**
	 * @param cobrancaMulta The cobrancaMulta to set.
	 */
	public void setIndicadorCobrancaMulta(short indicadorCobrancaMulta) {
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

	/**
	 * @return Returns the consumoRateioAgua.
	 */
	public Integer getConsumoRateioAgua() {
		return consumoRateioAgua;
	}

	/**
	 * @param consumoRateioAgua The consumoRateioAgua to set.
	 */
	public void setConsumoRateioAgua(Integer consumoRateioAgua) {
		this.consumoRateioAgua = consumoRateioAgua;
	}

	/**
	 * @return Returns the consumoRateioEsgoto.
	 */
	public Integer getConsumoRateioEsgoto() {
		return consumoRateioEsgoto;
	}

	/**
	 * @param consumoRateioEsgoto The consumoRateioEsgoto to set.
	 */
	public void setConsumoRateioEsgoto(Integer consumoRateioEsgoto) {
		this.consumoRateioEsgoto = consumoRateioEsgoto;
	}

	/**
	 * @return Returns the consumoTarifa.
	 */
	public gcom.faturamento.consumotarifa.ConsumoTarifa getConsumoTarifa() {
		return consumoTarifa;
	}

	/**
	 * @param consumoTarifa The consumoTarifa to set.
	 */
	public void setConsumoTarifa(
			gcom.faturamento.consumotarifa.ConsumoTarifa consumoTarifa) {
		this.consumoTarifa = consumoTarifa;
	}

	/**
	 * @return Returns the contaBancaria.
	 */
	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	/**
	 * @param contaBancaria The contaBancaria to set.
	 */
	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	/**
	 * @return Returns the contaHistorico.
	 */
	public Date getContaHistorico() {
		return contaHistorico;
	}

	/**
	 * @param contaHistorico The contaHistorico to set.
	 */
	public void setContaHistorico(Date contaHistorico) {
		this.contaHistorico = contaHistorico;
	}

	/**
	 * @return Returns the contaMotivoCancelamento.
	 */
	public gcom.faturamento.conta.ContaMotivoCancelamento getContaMotivoCancelamento() {
		return contaMotivoCancelamento;
	}

	/**
	 * @param contaMotivoCancelamento The contaMotivoCancelamento to set.
	 */
	public void setContaMotivoCancelamento(
			gcom.faturamento.conta.ContaMotivoCancelamento contaMotivoCancelamento) {
		this.contaMotivoCancelamento = contaMotivoCancelamento;
	}

	/**
	 * @return Returns the contaMotivoInclusao.
	 */
	public gcom.faturamento.conta.ContaMotivoInclusao getContaMotivoInclusao() {
		return contaMotivoInclusao;
	}

	/**
	 * @param contaMotivoInclusao The contaMotivoInclusao to set.
	 */
	public void setContaMotivoInclusao(
			gcom.faturamento.conta.ContaMotivoInclusao contaMotivoInclusao) {
		this.contaMotivoInclusao = contaMotivoInclusao;
	}

	/**
	 * @return Returns the contaMotivoRetificacao.
	 */
	public gcom.faturamento.conta.ContaMotivoRetificacao getContaMotivoRetificacao() {
		return contaMotivoRetificacao;
	}

	/**
	 * @param contaMotivoRetificacao The contaMotivoRetificacao to set.
	 */
	public void setContaMotivoRetificacao(
			gcom.faturamento.conta.ContaMotivoRetificacao contaMotivoRetificacao) {
		this.contaMotivoRetificacao = contaMotivoRetificacao;
	}

	/**
	 * @return Returns the contaMotivoRevisao.
	 */
	public gcom.faturamento.conta.ContaMotivoRevisao getContaMotivoRevisao() {
		return contaMotivoRevisao;
	}

	/**
	 * @param contaMotivoRevisao The contaMotivoRevisao to set.
	 */
	public void setContaMotivoRevisao(
			gcom.faturamento.conta.ContaMotivoRevisao contaMotivoRevisao) {
		this.contaMotivoRevisao = contaMotivoRevisao;
	}

	/**
	 * @return Returns the creditoRealizadoHistoricos.
	 */
	public Set getCreditoRealizadoHistoricos() {
		return creditoRealizadoHistoricos;
	}

	/**
	 * @param creditoRealizadoHistoricos The creditoRealizadoHistoricos to set.
	 */
	public void setCreditoRealizadoHistoricos(Set creditoRealizadoHistoricos) {
		this.creditoRealizadoHistoricos = creditoRealizadoHistoricos;
	}

	/**
	 * @return Returns the dataCancelamento.
	 */
	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	/**
	 * @param dataCancelamento The dataCancelamento to set.
	 */
	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	/**
	 * @return Returns the dataEmissao.
	 */
	public Date getDataEmissao() {
		return dataEmissao;
	}

	/**
	 * @param dataEmissao The dataEmissao to set.
	 */
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	/**
	 * @return Returns the dataInclusao.
	 */
	public Date getDataInclusao() {
		return dataInclusao;
	}

	/**
	 * @param dataInclusao The dataInclusao to set.
	 */
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	/**
	 * @return Returns the dataRetificacao.
	 */
	public Date getDataRetificacao() {
		return dataRetificacao;
	}

	/**
	 * @param dataRetificacao The dataRetificacao to set.
	 */
	public void setDataRetificacao(Date dataRetificacao) {
		this.dataRetificacao = dataRetificacao;
	}

	/**
	 * @return Returns the dataRevisao.
	 */
	public Date getDataRevisao() {
		return dataRevisao;
	}

	/**
	 * @param dataRevisao The dataRevisao to set.
	 */
	public void setDataRevisao(Date dataRevisao) {
		this.dataRevisao = dataRevisao;
	}

	/**
	 * @return Returns the dataValidadeConta.
	 */
	public Date getDataValidadeConta() {
		return dataValidadeConta;
	}

	/**
	 * @param dataValidadeConta The dataValidadeConta to set.
	 */
	public void setDataValidadeConta(Date dataValidadeConta) {
		this.dataValidadeConta = dataValidadeConta;
	}

	/**
	 * @return Returns the dataVencimentoConta.
	 */
	public Date getDataVencimentoConta() {
		return dataVencimentoConta;
	}

	/**
	 * @param dataVencimentoConta The dataVencimentoConta to set.
	 */
	public void setDataVencimentoConta(Date dataVencimentoConta) {
		this.dataVencimentoConta = dataVencimentoConta;
	}

	/**
	 * @return Returns the debitoCobradoHistoricos.
	 */
	public Set getDebitoCobradoHistoricos() {
		return debitoCobradoHistoricos;
	}

	/**
	 * @param debitoCobradoHistoricos The debitoCobradoHistoricos to set.
	 */
	public void setDebitoCobradoHistoricos(Set debitoCobradoHistoricos) {
		this.debitoCobradoHistoricos = debitoCobradoHistoricos;
	}

	/**
	 * @return Returns the debitoConta.
	 */
	public Short getIndicadorDebitoConta() {
		return indicadorDebitoConta;
	}

	/**
	 * @param debitoConta The debitoConta to set.
	 */
	public void setIndicadorDebitoConta(Short indicadorDebitoConta) {
		this.indicadorDebitoConta = indicadorDebitoConta;
	}

	/**
	 * @return Returns the debitoCreditoSituacaoAnterior.
	 */
	public gcom.faturamento.debito.DebitoCreditoSituacao getDebitoCreditoSituacaoAnterior() {
		return debitoCreditoSituacaoAnterior;
	}

	/**
	 * @param debitoCreditoSituacaoAnterior The debitoCreditoSituacaoAnterior to set.
	 */
	public void setDebitoCreditoSituacaoAnterior(
			gcom.faturamento.debito.DebitoCreditoSituacao debitoCreditoSituacaoAnterior) {
		this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
	}

	/**
	 * @return Returns the debitoCreditoSituacaoAtual.
	 */
	public gcom.faturamento.debito.DebitoCreditoSituacao getDebitoCreditoSituacaoAtual() {
		return debitoCreditoSituacaoAtual;
	}

	/**
	 * @param debitoCreditoSituacaoAtual The debitoCreditoSituacaoAtual to set.
	 */
	public void setDebitoCreditoSituacaoAtual(
			gcom.faturamento.debito.DebitoCreditoSituacao debitoCreditoSituacaoAtual) {
		this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
	}

	/**
	 * @return Returns the documentoTipo.
	 */
	public gcom.cobranca.DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}

	/**
	 * @param documentoTipo The documentoTipo to set.
	 */
	public void setDocumentoTipo(gcom.cobranca.DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	/**
	 * @return Returns the faturamentoTipo.
	 */
	public gcom.faturamento.FaturamentoTipo getFaturamentoTipo() {
		return faturamentoTipo;
	}

	/**
	 * @param faturamentoTipo The faturamentoTipo to set.
	 */
	public void setFaturamentoTipo(gcom.faturamento.FaturamentoTipo faturamentoTipo) {
		this.faturamentoTipo = faturamentoTipo;
	}

	/**
	 * @return Returns the funcionarioEntrega.
	 */
	public gcom.cadastro.funcionario.Funcionario getFuncionarioEntrega() {
		return funcionarioEntrega;
	}

	/**
	 * @param funcionarioEntrega The funcionarioEntrega to set.
	 */
	public void setFuncionarioEntrega(
			gcom.cadastro.funcionario.Funcionario funcionarioEntrega) {
		this.funcionarioEntrega = funcionarioEntrega;
	}

	/**
	 * @return Returns the funcionarioLeitura.
	 */
	public gcom.cadastro.funcionario.Funcionario getFuncionarioLeitura() {
		return funcionarioLeitura;
	}

	/**
	 * @param funcionarioLeitura The funcionarioLeitura to set.
	 */
	public void setFuncionarioLeitura(
			gcom.cadastro.funcionario.Funcionario funcionarioLeitura) {
		this.funcionarioLeitura = funcionarioLeitura;
	}

	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Returns the imovel.
	 */
	public gcom.cadastro.imovel.Imovel getImovel() {
		return imovel;
	}

	/**
	 * @param imovel The imovel to set.
	 */
	public void setImovel(gcom.cadastro.imovel.Imovel imovel) {
		this.imovel = imovel;
	}

	/**
	 * @return Returns the imovelPerfil.
	 */
	public gcom.cadastro.imovel.ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	/**
	 * @param imovelPerfil The imovelPerfil to set.
	 */
	public void setImovelPerfil(gcom.cadastro.imovel.ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	/**
	 * @return Returns the ligacaoAguaSituacao.
	 */
	public gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	/**
	 * @param ligacaoAguaSituacao The ligacaoAguaSituacao to set.
	 */
	public void setLigacaoAguaSituacao(
			gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	/**
	 * @return Returns the ligacaoEsgotoSituacao.
	 */
	public gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	/**
	 * @param ligacaoEsgotoSituacao The ligacaoEsgotoSituacao to set.
	 */
	public void setLigacaoEsgotoSituacao(
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	/**
	 * @return Returns the localidade.
	 */
	public gcom.cadastro.localidade.Localidade getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade The localidade to set.
	 */
	public void setLocalidade(gcom.cadastro.localidade.Localidade localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Returns the motivoNaoEntregaDocumento.
	 */
	public gcom.faturamento.conta.MotivoNaoEntregaDocumento getMotivoNaoEntregaDocumento() {
		return motivoNaoEntregaDocumento;
	}

	/**
	 * @param motivoNaoEntregaDocumento The motivoNaoEntregaDocumento to set.
	 */
	public void setMotivoNaoEntregaDocumento(
			gcom.faturamento.conta.MotivoNaoEntregaDocumento motivoNaoEntregaDocumento) {
		this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
	}

	/**
	 * @return Returns the lote.
	 */
	public Short getLote() {
		return lote;
	}

	/**
	 * @param numeroLote The numeroLote to set.
	 */
	public void setLote(Short lote) {
		this.lote = lote;
	}

	/**
	 * @return Returns the numeroQuadra.
	 */
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	/**
	 * @param numeroQuadra The numeroQuadra to set.
	 */
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	/**
	 * @return Returns the numeroSublote.
	 */
	public Short getSublote() {
		return sublote;
	}

	/**
	 * @param numeroSublote The numeroSublote to set.
	 */
	public void setSublote(Short sublote) {
		this.sublote = sublote;
	}

	/**
	 * @return Returns the parcelamentoItems.
	 */
	public Set getParcelamentoItems() {
		return parcelamentoItems;
	}

	/**
	 * @param parcelamentoItems The parcelamentoItems to set.
	 */
	public void setParcelamentoItems(Set parcelamentoItems) {
		this.parcelamentoItems = parcelamentoItems;
	}

	/**
	 * @return Returns the percentualEsgoto.
	 */
	public BigDecimal getPercentualEsgoto() {
		return percentualEsgoto;
	}

	/**
	 * @param percentualEsgoto The percentualEsgoto to set.
	 */
	public void setPercentualEsgoto(BigDecimal percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}

	/**
	 * @return Returns the quadra.
	 */
	public gcom.cadastro.localidade.Quadra getQuadra() {
		return quadra;
	}

	/**
	 * @param quadra The quadra to set.
	 */
	public void setQuadra(gcom.cadastro.localidade.Quadra quadra) {
		this.quadra = quadra;
	}

	/**
	 * @return Returns the registroAtendimento.
	 */
	public RegistroAtendimento getRegistroAtendimento() {
		return registroAtendimento;
	}

	/**
	 * @param registroAtendimento The registroAtendimento to set.
	 */
	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	/**
	 * @return Returns the setorComercial.
	 */
	public Integer getSetorComercial() {
		return setorComercial;
	}

	/**
	 * @param setorComercial The setorComercial to set.
	 */
	public void setSetorComercial(Integer setorComercial) {
		this.setorComercial = setorComercial;
	}

	/**
	 * @return Returns the ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao The ultimaAlteracao to set.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Returns the valorAgua.
	 */
	public BigDecimal getValorAgua() {
		return valorAgua;
	}

	/**
	 * @param valorAgua The valorAgua to set.
	 */
	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}

	/**
	 * @return Returns the valorCreditos.
	 */
	public BigDecimal getValorCreditos() {
		return valorCreditos;
	}

	/**
	 * @param valorCreditos The valorCreditos to set.
	 */
	public void setValorCreditos(BigDecimal valorCreditos) {
		this.valorCreditos = valorCreditos;
	}

	/**
	 * @return Returns the valorDebitos.
	 */
	public BigDecimal getValorDebitos() {
		return valorDebitos;
	}

	/**
	 * @param valorDebitos The valorDebitos to set.
	 */
	public void setValorDebitos(BigDecimal valorDebitos) {
		this.valorDebitos = valorDebitos;
	}

	/**
	 * @return Returns the valorEsgoto.
	 */
	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}

	/**
	 * @param valorEsgoto The valorEsgoto to set.
	 */
	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	/**
	 * @return Returns the verificadorConta.
	 */
	public short getVerificadorConta() {
		return verificadorConta;
	}

	/**
	 * @param verificadorConta The verificadorConta to set.
	 */
	public void setVerificadorConta(short verificadorConta) {
		this.verificadorConta = verificadorConta;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
	
	
	/**
	 * Este método retorna o valor total do histórico conta 
	 * (VALOR_AGUA + VALOR_ESGOTO + VALOR_DEBITOS) - VALOR_CREDITOS - VALOR_IMPOSTOS
	 *
	 * @author Pedro Alexandre , Yara Taciane
	 * @date 29/03/2006
	 *
	 */
	public BigDecimal getValorTotal() {

		BigDecimal valorTotalConta = new BigDecimal("0.00");

		// Valor de água
		if (this.getValorAgua() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorAgua());
		}

		// Valor de esgoto
		if (this.getValorEsgoto() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorEsgoto());
		}

		// Valor dos débitos
		if (this.getValorDebitos() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorDebitos());
		}

		// Valor dos créditos
		if (this.getValorCreditos() != null) {
			valorTotalConta = valorTotalConta.subtract(this.getValorCreditos());
		}
		
		

		//----------------------------------------------------------------------
		// Alterado por: Yara Taciane
		// data : 15/08/2008
		//----------------------------------------------------------------------
		// Valor dos impostos	
		if (this.getValorImposto() != null) {
			valorTotalConta = valorTotalConta.subtract(this.getValorImposto());
		}
		//----------------------------------------------------------------------
		
		
		
		valorTotalConta = valorTotalConta.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return valorTotalConta;	
	}

	/**
	 * @return Retorna o campo dataVencimentoOriginal.
	 */
	public Date getDataVencimentoOriginal() {
		return dataVencimentoOriginal;
	}

	/**
	 * @param dataVencimentoOriginal O dataVencimentoOriginal a ser setado.
	 */
	public void setDataVencimentoOriginal(Date dataVencimentoOriginal) {
		this.dataVencimentoOriginal = dataVencimentoOriginal;
	}

	/**
	 * @return Retorna o campo parcelamento.
	 */
	public Parcelamento getParcelamento() {
		return parcelamento;
	}

	/**
	 * @param parcelamento O parcelamento a ser setado.
	 */
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

	/**
	 * @return Retorna o campo numeroRetificacoes.
	 */
	public Integer getNumeroRetificacoes() {
		return numeroRetificacoes;
	}

	/**
	 * @param numeroRetificacoes O numeroRetificacoes a ser setado.
	 */
	public void setNumeroRetificacoes(Integer numeroRetificacoes) {
		this.numeroRetificacoes = numeroRetificacoes;
	}

	/**
	 * @return Returns the numeroFatura.
	 */
	public String getNumeroFatura() {
		return numeroFatura;
	}

	/**
	 * @param numeroFatura The numeroFatura to set.
	 */
	public void setNumeroFatura(String numeroFatura) {
		this.numeroFatura = numeroFatura;
	}

	/**
	 * @return Returns the faturamentoGrupo.
	 */
	public FaturamentoGrupo getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	/**
	 * @param faturamentoGrupo The faturamentoGrupo to set.
	 */
	public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}

	/**
	 * @return Returns the rota.
	 */
	public Rota getRota() {
		return rota;
	}

	/**
	 * @param rota The rota to set.
	 */
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

	/**TODO: COSANPA
     * Mantis 648 - Atualização de rotina que transfere contas para histórico.
     * 
     * @author: Wellington Rocha
     * @date: 07/11/2012*/
	
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

	public void setReferenciaContabil(Integer referenciaContabil) {
		this.anoMesReferenciaContabil = referenciaContabil;
	}
}
