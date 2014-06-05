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
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * @author gcom	
 */
@ControleAlteracao()
public class Conta extends ObjetoTransacao implements IConta {
	private static final long serialVersionUID = 1L;

	public static final int ATRIBUTOS_RETIFICAR_CONTA = 261; //Operacao.OPERACAO_CONTA_RETIFICAR;
	public static final int ATRIBUTOS_RETIFICAR_CONTA_CANCELAR = 230;//Operacao.OPERACAO_CANCELAR_CONTA;
	public static final int ATRIBUTOS_RETIFICAR_CONTA_REVISAO = 56; //Operacao.OPERACAO_COLOCAR_CONTA_REVISAO
	public static final int ATRIBUTOS_RETIFICAR_CONTA_ALTERAR_VENCIMENTO = 412; // Operacao.OPERACAO_ALTERAR_VENCIMENTO_CONTA
	public static final int ATRIBUTOS_DESFAZER_CONTA_CANCELAR = 361; // Operacao.OPERACAO_CANCELAMENTO_RETIFICACAO_CONTA_DESFAZER
	public static final int ATRIBUTOS_RETIFICAR_CONTA_RETIRAR_REVISAO = 57; // Operacao.OPERACAO_RETIRAR_CONTA_REVISAO
	public static final int ATRIBUTOS_INSERIR_CONTA = 53; //OPERACAO_INSERIR_CONTA = new Integer(53);
		
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CONTA,ATRIBUTOS_RETIFICAR_CONTA_CANCELAR})
	private Integer id;
	private Integer referenciaContabil;
	private int referencia;
	private Short lote;
	private Short subLote;
	private Integer codigoSetorComercial;
	private Integer quadra;
	private short digitoVerificadorConta;
	private short indicadorCobrancaMulta;
	private Short indicadorAlteracaoVencimento;
	private Integer consumoRateioAgua;
	private Integer consumoRateioEsgoto;
	private BigDecimal valorImposto;
	private Date dataValidadeConta;
	private Date dataInclusao;
	private Date dataRevisao;
	private Date dataRetificacao;
	private Date dataCancelamento;
	private Date dataEmissao;
	private Integer referenciaBaixaContabil;
	private Short indicadorDebitoConta;
	private Date ultimaAlteracao;
	private Integer numeroRetificacoes;
	private Integer numeroAlteracoesVencimento;
	private String numeroFatura;
	private DocumentoTipo documentoTipo;
	private ContaBancaria contaBancaria;
	private FaturamentoTipo faturamentoTipo;
	private RegistroAtendimento registroAtendimento;
	private Imovel imovel;
	private Date dataVencimentoOriginal;
	private Integer anoMesReferenciaBaixaSocial;
	private Integer numeroLeituraAtual;
	private Integer numeroLeituraAnterior;
	private BigDecimal percentualColeta;
	private Integer numeroLeituraAtualPoco;
	private Integer numeroLeituraAnteriorPoco;
	private Integer numeroVolumePoco;
	private Integer numeroBoleto;
	private Date dataEnvioEmailConta;
	private BigDecimal valorRateioAgua;
	private BigDecimal valorRateioEsgoto;
	
	@ControleAlteracao(funcionalidade=ATRIBUTOS_RETIFICAR_CONTA)
	private Integer consumoAgua;

	@ControleAlteracao(funcionalidade=ATRIBUTOS_RETIFICAR_CONTA)
	private Integer consumoEsgoto;

	@ControleAlteracao(funcionalidade=ATRIBUTOS_RETIFICAR_CONTA)
	private BigDecimal valorAgua;

	@ControleAlteracao(funcionalidade=ATRIBUTOS_RETIFICAR_CONTA)
	private BigDecimal valorEsgoto;

	@ControleAlteracao(funcionalidade=ATRIBUTOS_RETIFICAR_CONTA)
	private BigDecimal debitos;

	@ControleAlteracao(funcionalidade=ATRIBUTOS_RETIFICAR_CONTA)
	private BigDecimal valorCreditos;

	@ControleAlteracao(funcionalidade=ATRIBUTOS_RETIFICAR_CONTA)
	private BigDecimal percentualEsgoto;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_RETIFICAR_CONTA,ATRIBUTOS_RETIFICAR_CONTA_ALTERAR_VENCIMENTO})
	private Date dataVencimentoConta;
	
	@ControleAlteracao(value=FiltroConta.CONTA_MOTIVO_CANCELAMENTO, funcionalidade=ATRIBUTOS_RETIFICAR_CONTA_CANCELAR)
	private gcom.faturamento.conta.ContaMotivoCancelamento contaMotivoCancelamento;

	@ControleAlteracao(value=FiltroConta.CONTA_MOTIVO_INCLUSAO, funcionalidade=ATRIBUTOS_INSERIR_CONTA)
	private gcom.faturamento.conta.ContaMotivoInclusao contaMotivoInclusao;

	@ControleAlteracao(value=FiltroConta.LIGACAO_ESGOTO_SITUACAO, funcionalidade=ATRIBUTOS_RETIFICAR_CONTA)
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

	@ControleAlteracao(value=FiltroConta.LIGACAO_AGUA_SITUACAO, funcionalidade=ATRIBUTOS_RETIFICAR_CONTA)
	private LigacaoAguaSituacao ligacaoAguaSituacao;

	private ConsumoTarifa consumoTarifa;
	private ImovelPerfil imovelPerfil;
	private Quadra quadraConta;
	private Localidade localidade;
	private MotivoNaoEntregaDocumento motivoNaoEntregaDocumento;
	private Funcionario funcionarioEntrega;
	private DebitoCreditoSituacao debitoCreditoSituacaoAnterior;
	private Funcionario funcionarioLeitura;
	private ContaGeral contaGeral;
	private Parcelamento parcelamento;
	private ContaGeral origem;
	private Usuario usuario;
	private FaturamentoGrupo faturamentoGrupo;
	private Rota rota;

	@ControleAlteracao(value=FiltroConta.CONTA_MOTIVO_REVISAO, funcionalidade={ATRIBUTOS_RETIFICAR_CONTA_REVISAO, ATRIBUTOS_RETIFICAR_CONTA_RETIRAR_REVISAO})	
	private gcom.faturamento.conta.ContaMotivoRevisao contaMotivoRevisao;

	@ControleAlteracao(value=FiltroConta.CONTA_MOTIVO_RETIFICACAO,funcionalidade=ATRIBUTOS_RETIFICAR_CONTA)
	private gcom.faturamento.conta.ContaMotivoRetificacao contaMotivoRetificacao;

	@ControleAlteracao(value=FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL, funcionalidade={ATRIBUTOS_DESFAZER_CONTA_CANCELAR})
	private DebitoCreditoSituacao debitoCreditoSituacaoAtual;

	@SuppressWarnings("rawtypes")
	@ControleAlteracao(value=FiltroConta.CONTA_CATEGORIA,funcionalidade=ATRIBUTOS_RETIFICAR_CONTA)
	private Set contaCategorias;

	@SuppressWarnings("rawtypes")
	@ControleAlteracao(value=FiltroConta.CREDITOS_REALIZADOS,funcionalidade=ATRIBUTOS_RETIFICAR_CONTA)
	private Set debitoCobrados;

	@SuppressWarnings("rawtypes")
	@ControleAlteracao(value=FiltroConta.DEBITOS_COBRADOS,funcionalidade=ATRIBUTOS_RETIFICAR_CONTA)
	private Set creditoRealizados;

	@SuppressWarnings("rawtypes")
	private Set clienteContas;	
	
	@SuppressWarnings("rawtypes")
	private Set contaImpostosDeduzidos;
	
	@SuppressWarnings("rawtypes")
	private Set debitoAutomaticoMovimento;
	
	public static final Short INDICADOR_ALTERACAO_VENCIMENTO_ATIVO = new Short("1");
	public static final Short INDICADOR_DEBITO_CONTA_SIM = new Short("1");

	@SuppressWarnings("rawtypes")
	public Conta(
			Integer referenciaContabil,
			int referencia,
			Short lote,
			Short subLote,
			Integer codigoSetorComercial,
			Integer quadra,
			short digitoVerificadorConta,
			short indicadorCobrancaMulta,
			Short indicadorAlteracaoVencimento,
			Integer consumoAgua,
			Integer consumoEsgoto,
			Integer consumoRateioAgua,
			Integer consumoRateioEsgoto,
			BigDecimal valorAgua,
			BigDecimal valorEsgoto,
			BigDecimal debitos,
			BigDecimal valorCreditos,
			BigDecimal percentualEsgoto,
			Date dataVencimentoConta,
			Date dataValidadeConta,
			Date dataInclusao,
			Date dataRevisao,
			Date dataRetificacao,
			Date dataCancelamento,
			Date dataEmissao,
			Integer referenciaBaixaContabil,
			Short indicadorDebitoConta,
			Date ultimaAlteracao,
			gcom.faturamento.conta.ContaMotivoCancelamento contaMotivoCancelamento,
			gcom.faturamento.conta.ContaMotivoInclusao contaMotivoInclusao,
			DocumentoTipo documentoTipo,
			ContaBancaria contaBancaria,
			FaturamentoTipo faturamentoTipo,
			
			RegistroAtendimento registroAtendimento,
			Imovel imovel,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			ConsumoTarifa consumoTarifa,
			ImovelPerfil imovelPerfil,
			Quadra quadraConta,
			Localidade localidade,
			gcom.faturamento.conta.MotivoNaoEntregaDocumento motivoNaoEntregaDocumento,
			LigacaoAguaSituacao ligacaoAguaSituacao,
			Funcionario funcionarioEntrega,
			gcom.faturamento.conta.ContaMotivoRevisao contaMotivoRevisao,
			gcom.faturamento.conta.ContaMotivoRetificacao contaMotivoRetificacao,
			DebitoCreditoSituacao debitoCreditoSituacaoAtual,
			DebitoCreditoSituacao debitoCreditoSituacaoAnterior,
			Funcionario funcionarioLeitura, Set contaCategorias,
			Set debitoCobrados, Set creditoRealizados,
			Parcelamento parcelamento, Date dataVencimentoOriginal, BigDecimal valorImposto, Integer numeroRetificacoes) {
		this.referenciaContabil = referenciaContabil;
		this.referencia = referencia;
		this.lote = lote;
		this.subLote = subLote;
		this.codigoSetorComercial = codigoSetorComercial;
		this.quadra = quadra;
		this.digitoVerificadorConta = digitoVerificadorConta;
		this.indicadorCobrancaMulta = indicadorCobrancaMulta;
		this.indicadorAlteracaoVencimento = indicadorAlteracaoVencimento;
		this.consumoAgua = consumoAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.consumoRateioAgua = consumoRateioAgua;
		this.consumoRateioEsgoto = consumoRateioEsgoto;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.percentualEsgoto = percentualEsgoto;
		this.dataVencimentoConta = dataVencimentoConta;
		this.dataValidadeConta = dataValidadeConta;
		this.dataInclusao = dataInclusao;
		this.dataRevisao = dataRevisao;
		this.dataRetificacao = dataRetificacao;
		this.dataCancelamento = dataCancelamento;
		this.dataEmissao = dataEmissao;
		this.referenciaBaixaContabil = referenciaBaixaContabil;
		this.indicadorDebitoConta = indicadorDebitoConta;
		this.ultimaAlteracao = ultimaAlteracao;
		this.contaMotivoCancelamento = contaMotivoCancelamento;
		this.contaMotivoInclusao = contaMotivoInclusao;
		this.documentoTipo = documentoTipo;
		this.contaBancaria = contaBancaria;
		this.faturamentoTipo = faturamentoTipo;
		
		this.registroAtendimento = registroAtendimento;
		this.imovel = imovel;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.consumoTarifa = consumoTarifa;
		this.imovelPerfil = imovelPerfil;
		this.quadraConta = quadraConta;
		this.localidade = localidade;
		this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.funcionarioEntrega = funcionarioEntrega;
		this.contaMotivoRevisao = contaMotivoRevisao;
		this.contaMotivoRetificacao = contaMotivoRetificacao;
		this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
		this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
		this.funcionarioLeitura = funcionarioLeitura;
		this.contaCategorias = contaCategorias;
		this.debitoCobrados = debitoCobrados;
		this.creditoRealizados = creditoRealizados;
		
		this.parcelamento = parcelamento;
		this.dataVencimentoOriginal = dataVencimentoOriginal;
		this.valorImposto = valorImposto;
		this.numeroRetificacoes = numeroRetificacoes;
	}

	public Conta() {
	}
	
	public Conta(Integer id) {
		this.id = id;
	}

	@SuppressWarnings("rawtypes")
	public Conta(
			int referencia,
			short digitoVerificadorConta,
			short indicadorCobrancaMulta,
			BigDecimal valorAgua,
			BigDecimal valorEsgoto,
			BigDecimal debitos,
			BigDecimal percentualEsgoto,
			Date dataVencimentoConta,
			DocumentoTipo documentoTipo,
			ContaBancaria contaBancaria,
			FaturamentoTipo faturamentoTipo,
			
			RegistroAtendimento registroAtendimento,
			Imovel imovel,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			ConsumoTarifa consumoTarifa,
			ImovelPerfil imovelPerfil,
			Quadra quadraConta,
			Localidade localidade,
			gcom.faturamento.conta.MotivoNaoEntregaDocumento motivoNaoEntregaDocumento,
			LigacaoAguaSituacao ligacaoAguaSituacao,
			Funcionario funcionarioEntrega,
			gcom.faturamento.conta.ContaMotivoRevisao contaMotivoRevisao,
			gcom.faturamento.conta.ContaMotivoRetificacao contaMotivoRetificacao,
			DebitoCreditoSituacao debitoCreditoSituacaoAtual,
			DebitoCreditoSituacao debitoCreditoSituacaoAnterior,
			Funcionario funcionarioLeitura, Set contaCategorias,
			Set debitoCobrados, Set creditoRealizados) {
		this.referencia = referencia;
		this.digitoVerificadorConta = digitoVerificadorConta;
		this.indicadorCobrancaMulta = indicadorCobrancaMulta;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.percentualEsgoto = percentualEsgoto;
		this.dataVencimentoConta = dataVencimentoConta;
		this.documentoTipo = documentoTipo;
		this.contaBancaria = contaBancaria;
		this.faturamentoTipo = faturamentoTipo;
		
		this.registroAtendimento = registroAtendimento;
		this.imovel = imovel;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.consumoTarifa = consumoTarifa;
		this.imovelPerfil = imovelPerfil;
		this.quadraConta = quadraConta;
		this.localidade = localidade;
		this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.funcionarioEntrega = funcionarioEntrega;
		this.contaMotivoRevisao = contaMotivoRevisao;
		this.contaMotivoRetificacao = contaMotivoRetificacao;
		this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
		this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
		this.funcionarioLeitura = funcionarioLeitura;
		this.contaCategorias = contaCategorias;
		this.debitoCobrados = debitoCobrados;
		this.creditoRealizados = creditoRealizados;
		
	}
	
	public Conta(Integer id, Integer referencia, Date dataVencimentoConta, BigDecimal valorAgua, BigDecimal valorEsgoto,
			BigDecimal debitos, BigDecimal valorCreditos, BigDecimal valorImposto, Integer consumoAgua, Integer consumoEsgoto,
			Date dataValidadeConta, Date dataRevisao, Integer referenciaContabil, Imovel imovel, Integer referenciaBaixaContabil) {
		
		this.id = id;
		this.referencia = referencia;
		this.dataVencimentoConta = dataVencimentoConta;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.valorImposto = valorImposto;
		this.consumoAgua = consumoAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.dataValidadeConta = dataValidadeConta;
		this.dataRevisao = dataRevisao;
		this.referenciaContabil = referenciaContabil;
		this.imovel = imovel;
		this.referenciaBaixaContabil = referenciaBaixaContabil;
	}
	
	public Conta(BigDecimal valorAgua, BigDecimal valorEsgoto, BigDecimal valorCreditos, BigDecimal valorDebitos, BigDecimal valorImpostos) {
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.valorCreditos = valorCreditos;
		this.debitos = valorDebitos;
		this.valorImposto = valorImpostos;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReferenciaContabil() {
		return this.referenciaContabil;
	}

	public void setReferenciaContabil(Integer referenciaContabil) {
		this.referenciaContabil = referenciaContabil;
	}

	public int getReferencia() {
		return this.referencia;
	}
	
	public int getAnoMesReferenciaConta() {
		return this.referencia;
	}

	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}

	public Short getLote() {
		return this.lote;
	}

	public void setLote(Short lote) {
		this.lote = lote;
	}

	public Short getSubLote() {
		return this.subLote;
	}

	public void setSubLote(Short subLote) {
		this.subLote = subLote;
	}

	public Integer getCodigoSetorComercial() {
		return this.codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getQuadra() {
		return this.quadra;
	}

	public void setQuadra(Integer quadra) {
		this.quadra = quadra;
	}

	public short getDigitoVerificadorConta() {
		return this.digitoVerificadorConta;
	}

	public void setDigitoVerificadorConta(short digitoVerificadorConta) {
		this.digitoVerificadorConta = digitoVerificadorConta;
	}

	public short getIndicadorCobrancaMulta() {
		return this.indicadorCobrancaMulta;
	}

	public void setIndicadorCobrancaMulta(short indicadorCobrancaMulta) {
		this.indicadorCobrancaMulta = indicadorCobrancaMulta;
	}

	public Short getIndicadorAlteracaoVencimento() {
		return this.indicadorAlteracaoVencimento;
	}

	public void setIndicadorAlteracaoVencimento(Short indicadorAlteracaoVencimento) {
		this.indicadorAlteracaoVencimento = indicadorAlteracaoVencimento;
	}

	public Integer getConsumoAgua() {
		return this.consumoAgua;
	}

	public void setConsumoAgua(Integer consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	public Integer getConsumoEsgoto() {
		return this.consumoEsgoto;
	}

	public void setConsumoEsgoto(Integer consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}

	public Integer getConsumoRateioAgua() {
		return this.consumoRateioAgua;
	}

	public void setConsumoRateioAgua(Integer consumoRateioAgua) {
		this.consumoRateioAgua = consumoRateioAgua;
	}

	public Integer getConsumoRateioEsgoto() {
		return this.consumoRateioEsgoto;
	}

	public void setConsumoRateioEsgoto(Integer consumoRateioEsgoto) {
		this.consumoRateioEsgoto = consumoRateioEsgoto;
	}

	public BigDecimal getValorAgua() {
		return this.valorAgua;
	}

	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}

	public BigDecimal getValorEsgoto() {
		return this.valorEsgoto;
	}

	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	public BigDecimal getDebitos() {
		return this.debitos;
	}

	public void setDebitos(BigDecimal debitos) {
		this.debitos = debitos;
	}

	public BigDecimal getValorCreditos() {
		return this.valorCreditos;
	}

	public void setValorCreditos(BigDecimal valorCreditos) {
		this.valorCreditos = valorCreditos;
	}

	public BigDecimal getPercentualEsgoto() {
		return this.percentualEsgoto;
	}

	public void setPercentualEsgoto(BigDecimal percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}

	public Date getDataVencimentoConta() {
		return this.dataVencimentoConta;
	}

	public void setDataVencimentoConta(Date dataVencimentoConta) {
		this.dataVencimentoConta = dataVencimentoConta;
	}

	public Date getDataValidadeConta() {
		return this.dataValidadeConta;
	}

	public void setDataValidadeConta(Date dataValidadeConta) {
		this.dataValidadeConta = dataValidadeConta;
	}

	public Date getDataInclusao() {
		return this.dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataRevisao() {
		return this.dataRevisao;
	}

	public void setDataRevisao(Date dataRevisao) {
		this.dataRevisao = dataRevisao;
	}

	public Date getDataRetificacao() {
		return this.dataRetificacao;
	}

	public void setDataRetificacao(Date dataRetificacao) {
		this.dataRetificacao = dataRetificacao;
	}

	public Date getDataCancelamento() {
		return this.dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public Date getDataEmissao() {
		return this.dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Integer getReferenciaBaixaContabil() {
		return this.referenciaBaixaContabil;
	}

	public void setReferenciaBaixaContabil(Integer referenciaBaixaContabil) {
		this.referenciaBaixaContabil = referenciaBaixaContabil;
	}

	public Short getIndicadorDebitoConta() {
		return this.indicadorDebitoConta;
	}

	public void setIndicadorDebitoConta(Short indicadorDebitoConta) {
		this.indicadorDebitoConta = indicadorDebitoConta;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.faturamento.conta.ContaMotivoCancelamento getContaMotivoCancelamento() {
		return this.contaMotivoCancelamento;
	}

	public void setContaMotivoCancelamento(
			gcom.faturamento.conta.ContaMotivoCancelamento contaMotivoCancelamento) {
		this.contaMotivoCancelamento = contaMotivoCancelamento;
	}

	public gcom.faturamento.conta.ContaMotivoInclusao getContaMotivoInclusao() {
		return this.contaMotivoInclusao;
	}

	public void setContaMotivoInclusao(
			gcom.faturamento.conta.ContaMotivoInclusao contaMotivoInclusao) {
		this.contaMotivoInclusao = contaMotivoInclusao;
	}

	public DocumentoTipo getDocumentoTipo() {
		return this.documentoTipo;
	}

	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public ContaBancaria getContaBancaria() {
		return this.contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public FaturamentoTipo getFaturamentoTipo() {
		return this.faturamentoTipo;
	}

	public void setFaturamentoTipo(FaturamentoTipo faturamentoTipo) {
		this.faturamentoTipo = faturamentoTipo;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return this.registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return this.ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public ConsumoTarifa getConsumoTarifa() {
		return this.consumoTarifa;
	}

	public void setConsumoTarifa(ConsumoTarifa consumoTarifa) {
		this.consumoTarifa = consumoTarifa;
	}

	public ImovelPerfil getImovelPerfil() {
		return this.imovelPerfil;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public Quadra getQuadraConta() {
		return this.quadraConta;
	}

	public void setQuadraConta(Quadra quadraConta) {
		this.quadraConta = quadraConta;
	}

	public Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public gcom.faturamento.conta.MotivoNaoEntregaDocumento getMotivoNaoEntregaDocumento() {
		return this.motivoNaoEntregaDocumento;
	}

	public void setMotivoNaoEntregaDocumento(
			gcom.faturamento.conta.MotivoNaoEntregaDocumento motivoNaoEntregaDocumento) {
		this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return this.ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public Funcionario getFuncionarioEntrega() {
		return this.funcionarioEntrega;
	}

	public void setFuncionarioEntrega(Funcionario funcionarioEntrega) {
		this.funcionarioEntrega = funcionarioEntrega;
	}

	public gcom.faturamento.conta.ContaMotivoRevisao getContaMotivoRevisao() {
		return this.contaMotivoRevisao;
	}

	public void setContaMotivoRevisao(
			gcom.faturamento.conta.ContaMotivoRevisao contaMotivoRevisao) {
		this.contaMotivoRevisao = contaMotivoRevisao;
	}

	public gcom.faturamento.conta.ContaMotivoRetificacao getContaMotivoRetificacao() {
		return this.contaMotivoRetificacao;
	}

	public void setContaMotivoRetificacao(
			gcom.faturamento.conta.ContaMotivoRetificacao contaMotivoRetificacao) {
		this.contaMotivoRetificacao = contaMotivoRetificacao;
	}

	public DebitoCreditoSituacao getDebitoCreditoSituacaoAtual() {
		return this.debitoCreditoSituacaoAtual;
	}

	public void setDebitoCreditoSituacaoAtual(
			DebitoCreditoSituacao debitoCreditoSituacaoAtual) {
		this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
	}

	public DebitoCreditoSituacao getDebitoCreditoSituacaoAnterior() {
		return this.debitoCreditoSituacaoAnterior;
	}

	public void setDebitoCreditoSituacaoAnterior(
			DebitoCreditoSituacao debitoCreditoSituacaoAnterior) {
		this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
	}

	public Funcionario getFuncionarioLeitura() {
		return this.funcionarioLeitura;
	}

	public void setFuncionarioLeitura(Funcionario funcionarioLeitura) {
		this.funcionarioLeitura = funcionarioLeitura;
	}

	@SuppressWarnings("rawtypes")
	public Set getContaCategorias() {
		return this.contaCategorias;
	}

	@SuppressWarnings("rawtypes")
	public void setContaCategorias(Set contaCategorias) {
		this.contaCategorias = contaCategorias;
	}

	@SuppressWarnings("rawtypes")
	public Set getDebitoCobrados() {
		return this.debitoCobrados;
	}

	@SuppressWarnings("rawtypes")
	public void setDebitoCobrados(Set debitoCobrados) {
		this.debitoCobrados = debitoCobrados;
	}

	@SuppressWarnings("rawtypes")
	public Set getCreditoRealizados() {
		return this.creditoRealizados;
	}

	@SuppressWarnings("rawtypes")
	public void setCreditoRealizados(Set creditoRealizados) {
		this.creditoRealizados = creditoRealizados;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public BigDecimal getValorImposto() {
		return valorImposto;
	}

	public void setValorImposto(BigDecimal valorImposto) {
		this.valorImposto = valorImposto;
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

	public String getValorTotalConta() {
		return this.getValorTotalContaBigDecimal().toString();
	}

	public BigDecimal getValorTotalContaBigDecimal() {
		BigDecimal valorTotalConta = new BigDecimal("0.00");

		if (this.getValorAgua() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorAgua());
		}

		if (this.getValorEsgoto() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorEsgoto());
		}

		if (this.getDebitos() != null) {
			valorTotalConta = valorTotalConta.add(this.getDebitos());
		}

		if (this.getValorCreditos() != null) {
			valorTotalConta = valorTotalConta.subtract(this.getValorCreditos());
		}
		
		if (this.getValorImposto() != null) {
			valorTotalConta = valorTotalConta.subtract(this.getValorImposto());
		}
		
		return valorTotalConta;
	}
	
	public BigDecimal getValorTotal() {
		return getValorTotalContaBigDecimal().setScale(2, BigDecimal.ROUND_HALF_UP);	
	}

	public String getFormatarAnoMesParaMesAno() {

		String anoMesRecebido = "" + this.getReferencia();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatado = mes + "/" + ano;

		return anoMesFormatado.toString();
	}

	@SuppressWarnings("rawtypes")
	public Set getClienteContas() {
		return clienteContas;
	}

	@SuppressWarnings("rawtypes")
	public void setClienteContas(Set clienteContas) {
		this.clienteContas = clienteContas;
	}
	
	public ContaGeral getContaGeral() {
        return this.contaGeral;
    }
	 
	 public void setContaGeral(ContaGeral contaGeral) {
        this.contaGeral = contaGeral;
    }
	 
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof Conta)) {
			return false;
		}
		Conta castOther = (Conta) other;

		return (this.getId().equals(castOther.getId()));
	}
	
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroConta filtroConta = new FiltroConta();
		
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.CONTA_MOTIVO_CANCELAMENTO);
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.CONTA_MOTIVO_INCLUSAO);
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.DOCUMENTO_TIPO);
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.CONTA_BANCARIA);
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.FATURAMENTO_TIPO);
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.REGISTRO_ATENDIMENTO);
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.LIGACAO_AGUA_SITUACAO);
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.LIGACAO_ESGOTO_SITUACAO);
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.IMOVEL);
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.CONSUMO_TARIFA);
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.IMOVEL_PERFIL);
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.LOCALIDADE);
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.MOTIVO_NAO_ENTREGA_DOCUMENTO);
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.CONTA_MOTIVO_REVISAO);
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.CONTA_MOTIVO_RETIFICACAO);
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.CONTA_GERAL);
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.PARCELAMENTO);
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.USUARIO);
		
		filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.ID, this.getId()));
		
		return filtroConta; 
	}
	
	@SuppressWarnings("rawtypes")
	public Set getContaImpostosDeduzidos() {
		return contaImpostosDeduzidos;
	}

	@SuppressWarnings("rawtypes")
	public void setContaImpostosDeduzidos(Set contaImpostosDeduzidos) {
		this.contaImpostosDeduzidos = contaImpostosDeduzidos;
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
	
	@SuppressWarnings("rawtypes")
	public Set getDebitoAutomaticoMovimento() {
		return debitoAutomaticoMovimento;
	}
	
	@SuppressWarnings("rawtypes")
	public void setDebitoAutomaticoMovimento(Set debitoAutomaticoMovimento) {
		this.debitoAutomaticoMovimento = debitoAutomaticoMovimento;
	}
	
	public String[] retornarAtributosInformacoesOperacaoEfetuada(){
		String []atributos = { "referenciaFormatada" , "localidade.descricao" };
		return atributos;		
	}
	
	public String[] retornarLabelsInformacoesOperacaoEfetuada(){
		String []labels = {"Referencia" , "Localidade" };
		return labels;		
	}
	
	public String getReferenciaFormatada(){
		return Util.formatarAnoMesParaMesAno(this.referencia);
	}
	
	public void initializeLazy(){
		initilizarCollectionLazy(this.getContaCategorias());
		initilizarCollectionLazy(this.getDebitoCobrados());
		initilizarCollectionLazy(getCreditoRealizados());		

		if (debitoCreditoSituacaoAtual != null){
			debitoCreditoSituacaoAtual.initializeLazy();
		}
		if (debitoCreditoSituacaoAnterior != null){
			debitoCreditoSituacaoAnterior.initializeLazy();
		}		
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

	public Integer getNumeroAlteracoesVencimento() {
		return numeroAlteracoesVencimento;
	}

	public void setNumeroAlteracoesVencimento(Integer numeroAlteracoesVencimento) {
		this.numeroAlteracoesVencimento = numeroAlteracoesVencimento;
	}

	public Integer getAnoMesReferenciaBaixaSocial() {
		return anoMesReferenciaBaixaSocial;
	}

	public void setAnoMesReferenciaBaixaSocial(Integer anoMesReferenciaBaixaSocial) {
		this.anoMesReferenciaBaixaSocial = anoMesReferenciaBaixaSocial;
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
}
