package gcom.arrecadacao.pagamento;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.ArrecadadorMovimentoItem;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */

@ControleAlteracao()
public class Pagamento extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	public static final int ATRIBUTOS_INSERIR_PAGAMENTO = 167; //Operacao.OPERACAO_INSERIR_PAGAMENTO
	public static final int ATRIBUTOS_INSERIR_PAGAMENTO_CONTRATO_PARCELAMENTO = 1804; //Operacao.OPERACAO_INFORMAR_PAGAMENTO_CONTRATO_PARCELAMENTO_POR_CLIENTE;

	/** identifier field */
	private Integer id;

	/** persistent field */
	private int anoMesReferencia;

	/** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_PAGAMENTO, ATRIBUTOS_INSERIR_PAGAMENTO_CONTRATO_PARCELAMENTO})
	private Date dataPagamento;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_PAGAMENTO_CONTRATO_PARCELAMENTO})
	private int anoMesReferenciaArrecadacao;

	/** persistent field */
	@ControleAlteracao(value=FiltroPagamento.CONTA,
			funcionalidade={ATRIBUTOS_INSERIR_PAGAMENTO})
	private ContaGeral contaGeral;

	/** persistent field */
	@ControleAlteracao(value=FiltroPagamento.LOCALIDADE,
			funcionalidade={ATRIBUTOS_INSERIR_PAGAMENTO_CONTRATO_PARCELAMENTO})
	private Localidade localidade;

	/** persistent field */
	@ControleAlteracao(value=FiltroPagamento.DEBITO_TIPO_,
			funcionalidade={ATRIBUTOS_INSERIR_PAGAMENTO, ATRIBUTOS_INSERIR_PAGAMENTO_CONTRATO_PARCELAMENTO})
	private DebitoTipo debitoTipo;

	/** persistent field */
	@ControleAlteracao(value=FiltroPagamento.DEBITO_A_COBRAR_,
			funcionalidade={ATRIBUTOS_INSERIR_PAGAMENTO})
	private DebitoACobrarGeral debitoACobrarGeral;

	/** persistent field */
	private Short indicadorExpurgado;

	/** persistent field */
	private gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAtual;

	/** persistent field */
	private gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAnterior;

	/** persistent field */
	@ControleAlteracao(value=FiltroPagamento.GUIA_PAGAMENTO,
			funcionalidade={ATRIBUTOS_INSERIR_PAGAMENTO, ATRIBUTOS_INSERIR_PAGAMENTO_CONTRATO_PARCELAMENTO})
	private gcom.arrecadacao.pagamento.GuiaPagamento guiaPagamento;

	/** persistent field */
	@ControleAlteracao(value=FiltroPagamento.DOCUMENTO_TIPO,
			funcionalidade={ATRIBUTOS_INSERIR_PAGAMENTO, ATRIBUTOS_INSERIR_PAGAMENTO_CONTRATO_PARCELAMENTO})
	private DocumentoTipo documentoTipo;

	/** persistent field */
	@ControleAlteracao(value=FiltroPagamento.AVISO_BANCARIO,
			funcionalidade={ATRIBUTOS_INSERIR_PAGAMENTO, ATRIBUTOS_INSERIR_PAGAMENTO_CONTRATO_PARCELAMENTO})
	private AvisoBancario avisoBancario;

	/** persistent field */
	@ControleAlteracao(value=FiltroPagamento.IMOVEL,
			funcionalidade={ATRIBUTOS_INSERIR_PAGAMENTO, ATRIBUTOS_INSERIR_PAGAMENTO_CONTRATO_PARCELAMENTO})
	private Imovel imovel;

	/** persistent field */
	private ArrecadadorMovimentoItem arrecadadorMovimentoItem;

	/** persistent field */
	@ControleAlteracao(value=FiltroPagamento.ARRECADACAO_FORMA,
			funcionalidade={ATRIBUTOS_INSERIR_PAGAMENTO, ATRIBUTOS_INSERIR_PAGAMENTO_CONTRATO_PARCELAMENTO})
	private ArrecadacaoForma arrecadacaoForma;

	/** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_PAGAMENTO, ATRIBUTOS_INSERIR_PAGAMENTO_CONTRATO_PARCELAMENTO})
	private BigDecimal valorPagamento;

	/** persistent field */
	private BigDecimal valorExcedente;

	/** persistent field */
	private Integer anoMesReferenciaPagamento;

	/** persistent field */
	@ControleAlteracao(value=FiltroPagamento.CLIENTE,
			funcionalidade={ATRIBUTOS_INSERIR_PAGAMENTO, ATRIBUTOS_INSERIR_PAGAMENTO_CONTRATO_PARCELAMENTO})
	private Cliente cliente;

	private Integer quantidadePagamentos;

	private Fatura fatura;

	private CobrancaDocumento cobrancaDocumento;
	
	private DocumentoTipo documentoTipoAgregador;
	
	private PagamentoCartaoDebito pagamentoCartaoDebito;

	public final static Short INDICADOR_EXPURGADO_SIM = new Short("1");

	public final static Short INDICADOR_EXPURGADO_NAO = new Short("2");

	/**
	 * [UC0242] - Registrar Movimento dos Arrecadadores [SF0004] - Processar
	 * Registro Código F Autor: Sávio Luiz Data: 15/02/2006
	 */

	private Date dataPrevistaCreditoHelper;

	/**
	 * [UC0739] Informar Situação de Expurgadodo Pagamento Indicador que dirá se
	 * é de pagamento ou pagamento_historico,foi utilizado esse indicador porque
	 * em pagamento e pagamento_historico pode ter o mesmo id.
	 * 
	 * @author Sávio Luiz
	 * @date 18/02/2008
	 * @return void
	 */

	private short indicadorPagamento;

	private Date dataProcessamento;

	public final static Short INDICADOR_PAGAMENTO_SIM = new Short("1");

	public final static Short INDICADOR_PAGAMENTO_NAO = new Short("2");

	/** full constructor */
	public Pagamento(
			int anoMesReferencia,
			Date dataPagamento,
			Date ultimaAlteracao,
			int anoMesReferenciaArrecadacao,
			ContaGeral contaGeral,
			Localidade localidade,
			DebitoTipo debitoTipo,
			gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAtual,
			gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAnterior,
			gcom.arrecadacao.pagamento.GuiaPagamento guiaPagamento,
			DocumentoTipo documentoTipo, AvisoBancario avisoBancario,
			Imovel imovel, ArrecadadorMovimentoItem arrecadadorMovimentoItem,
			ArrecadacaoForma arrecadacaoForma, BigDecimal valorPagamento,
			Integer anoMesReferenciaPagamento, Cliente cliente) {
		this.anoMesReferencia = anoMesReferencia;
		this.dataPagamento = dataPagamento;
		this.ultimaAlteracao = ultimaAlteracao;
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
		this.contaGeral = contaGeral;
		this.localidade = localidade;
		this.debitoTipo = debitoTipo;
		this.pagamentoSituacaoAtual = pagamentoSituacaoAtual;
		this.pagamentoSituacaoAnterior = pagamentoSituacaoAnterior;
		this.guiaPagamento = guiaPagamento;
		this.documentoTipo = documentoTipo;
		this.avisoBancario = avisoBancario;
		this.imovel = imovel;
		this.arrecadadorMovimentoItem = arrecadadorMovimentoItem;
		this.arrecadacaoForma = arrecadacaoForma;
		this.valorPagamento = valorPagamento;
		this.anoMesReferenciaPagamento = anoMesReferenciaPagamento;
		this.cliente = cliente;
	}

	/** default constructor */
	public Pagamento() {
	}

	/** minimal constructor */
	public Pagamento(
			int anoMesReferencia,
			Date dataPagamento,
			int anoMesReferenciaArrecadacao,
			ContaGeral contaGeral,
			Localidade localidade,
			DebitoTipo debitoTipo,
			gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAtual,
			gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAnterior,
			gcom.arrecadacao.pagamento.GuiaPagamento guiaPagamento,
			DocumentoTipo documentoTipo, AvisoBancario avisoBancario,
			Imovel imovel, ArrecadadorMovimentoItem arrecadadorMovimentoItem,
			ArrecadacaoForma arrecadacaoForma, BigDecimal valorPagamento,
			Integer anoMesReferenciaPagamento, Cliente cliente) {
		this.anoMesReferencia = anoMesReferencia;
		this.dataPagamento = dataPagamento;
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
		this.contaGeral = contaGeral;
		this.localidade = localidade;
		this.debitoTipo = debitoTipo;
		this.pagamentoSituacaoAtual = pagamentoSituacaoAtual;
		this.pagamentoSituacaoAnterior = pagamentoSituacaoAnterior;
		this.guiaPagamento = guiaPagamento;
		this.documentoTipo = documentoTipo;
		this.avisoBancario = avisoBancario;
		this.imovel = imovel;
		this.arrecadadorMovimentoItem = arrecadadorMovimentoItem;
		this.arrecadacaoForma = arrecadacaoForma;
		this.valorPagamento = valorPagamento;
		this.anoMesReferenciaPagamento = anoMesReferenciaPagamento;
		this.cliente = cliente;
	}

	/**
	 * constructor para o caso de uso [UC0301] Gerar Dados Diários da
	 * Arrecadação
	 */
	public Pagamento(Imovel imovel, BigDecimal valorPagamento,
			Integer quantidadePagamentos, AvisoBancario avisoBancario,
			Localidade localidade, DocumentoTipo documentoTipo,
			ArrecadacaoForma arrecadacaoForma, Date dataPagamento) {

		this.imovel = imovel;
		this.valorPagamento = valorPagamento;
		this.quantidadePagamentos = quantidadePagamentos;
		this.avisoBancario = avisoBancario;
		this.localidade = localidade;
		this.documentoTipo = documentoTipo;
		this.arrecadacaoForma = arrecadacaoForma;
		this.dataPagamento = dataPagamento;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAnoMesReferencia() {
		return this.anoMesReferencia;
	}

	public void setAnoMesReferencia(int anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Date getDataPagamento() {
		return this.dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public int getAnoMesReferenciaArrecadacao() {
		return this.anoMesReferenciaArrecadacao;
	}

	public void setAnoMesReferenciaArrecadacao(int anoMesReferenciaArrecadacao) {
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
	}

	public ContaGeral getContaGeral() {
		return this.contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	public Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public DebitoTipo getDebitoTipo() {
		return this.debitoTipo;
	}

	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	public gcom.arrecadacao.pagamento.PagamentoSituacao getPagamentoSituacaoAtual() {
		return this.pagamentoSituacaoAtual;
	}

	public void setPagamentoSituacaoAtual(
			gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAtual) {
		this.pagamentoSituacaoAtual = pagamentoSituacaoAtual;
	}

	public gcom.arrecadacao.pagamento.PagamentoSituacao getPagamentoSituacaoAnterior() {
		return this.pagamentoSituacaoAnterior;
	}

	public void setPagamentoSituacaoAnterior(
			gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAnterior) {
		this.pagamentoSituacaoAnterior = pagamentoSituacaoAnterior;
	}

	public gcom.arrecadacao.pagamento.GuiaPagamento getGuiaPagamento() {
		return this.guiaPagamento;
	}

	public void setGuiaPagamento(
			gcom.arrecadacao.pagamento.GuiaPagamento guiaPagamento) {
		this.guiaPagamento = guiaPagamento;
	}

	public DocumentoTipo getDocumentoTipo() {
		return this.documentoTipo;
	}

	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public AvisoBancario getAvisoBancario() {
		return this.avisoBancario;
	}

	public void setAvisoBancario(AvisoBancario avisoBancario) {
		this.avisoBancario = avisoBancario;
	}

	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public ArrecadadorMovimentoItem getArrecadadorMovimentoItem() {
		return this.arrecadadorMovimentoItem;
	}

	public void setArrecadadorMovimentoItem(
			ArrecadadorMovimentoItem arrecadadorMovimentoItem) {
		this.arrecadadorMovimentoItem = arrecadadorMovimentoItem;
	}

	public ArrecadacaoForma getArrecadacaoForma() {
		return this.arrecadacaoForma;
	}

	public void setArrecadacaoForma(ArrecadacaoForma arrecadacaoForma) {
		this.arrecadacaoForma = arrecadacaoForma;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * @return Returns the valorPagamento.
	 */
	public BigDecimal getValorPagamento() {
		if(valorPagamento != null){
			return valorPagamento.setScale(2);
		}else{
			return valorPagamento;	
		}
	}
	/**
	 * @param valorPagamento
	 *            The valorPagamento to set.
	 */
	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	/**
	 * @return Returns the anoMesReferenciaPagamento.
	 */
	public Integer getAnoMesReferenciaPagamento() {
		return anoMesReferenciaPagamento;
	}

	/**
	 * @param anoMesReferenciaPagamento
	 *            The anoMesReferenciaPagamento to set.
	 */
	public void setAnoMesReferenciaPagamento(Integer anoMesReferenciaPagamento) {
		this.anoMesReferenciaPagamento = anoMesReferenciaPagamento;
	}

	/**
	 * @return Returns the cliente.
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            The cliente to set.
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataPrevistaCreditoHelper() {
		return dataPrevistaCreditoHelper;
	}

	public void setDataPrevistaCreditoHelper(Date dataPrevistaCreditoHelper) {
		this.dataPrevistaCreditoHelper = dataPrevistaCreditoHelper;
	}

	public DebitoACobrarGeral getDebitoACobrarGeral() {
		return debitoACobrarGeral;
	}

	public void setDebitoACobrarGeral(DebitoACobrarGeral debitoACobrarGeral) {
		this.debitoACobrarGeral = debitoACobrarGeral;
	}

	public Integer getQuantidadePagamentos() {
		return quantidadePagamentos;
	}

	public void setQuantidadePagamentos(Integer quantidadePagamentos) {
		this.quantidadePagamentos = quantidadePagamentos;
	}

	public BigDecimal getValorExcedente() {
		if(valorExcedente != null){
			return valorExcedente.setScale(2);
		}else{
			return valorExcedente;	
		}
	}
	
	public void setValorExcedente(BigDecimal valorExcedente) {
		this.valorExcedente = valorExcedente;
	}

	public String getFormatarAnoMesPagamentoParaMesAno() {
		String anoMesFormatado = "";
		
		if (this.getAnoMesReferenciaPagamento() != null && !this.getAnoMesReferenciaPagamento().equals("")) {

			String anoMesRecebido = "" + this.getAnoMesReferenciaPagamento();
			String mes = anoMesRecebido.substring(4, 6);
			String ano = anoMesRecebido.substring(0, 4);
			
			anoMesFormatado = mes + "/" + ano;
		}
		
		return anoMesFormatado;

	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof Pagamento)) {
			return false;
		}
		Pagamento castOther = (Pagamento) other;

		return (this.getId().equals(castOther.getId()));
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroPagamento filtroPagamento = new FiltroPagamento();

		filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("contaGeral");
		filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtroPagamento
				.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral");
		filtroPagamento
				.adicionarCaminhoParaCarregamentoEntidade("pagamentoSituacaoAtual");
		filtroPagamento
				.adicionarCaminhoParaCarregamentoEntidade("guiaPagamento");
		filtroPagamento
				.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
		filtroPagamento
				.adicionarCaminhoParaCarregamentoEntidade("avisoBancario");
		filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroPagamento
				.adicionarCaminhoParaCarregamentoEntidade("arrecadadorMovimentoItem");
		filtroPagamento
				.adicionarCaminhoParaCarregamentoEntidade("arrecadacaoForma");
		filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroPagamento.adicionarParametro(new ParametroSimples(
				FiltroPagamento.ID, this.getId()));
		return filtroPagamento;
	}

	public Short getIndicadorExpurgado() {
		return indicadorExpurgado;
	}

	public void setIndicadorExpurgado(Short indicadorExpurgado) {
		this.indicadorExpurgado = indicadorExpurgado;
	}

	public short getIndicadorPagamento() {
		return indicadorPagamento;
	}

	public void setIndicadorPagamento(short indicadorPagamento) {
		this.indicadorPagamento = indicadorPagamento;
	}

	public CobrancaDocumento getCobrancaDocumento() {
		return cobrancaDocumento;
	}

	public void setCobrancaDocumento(CobrancaDocumento cobrancaDocumento) {
		this.cobrancaDocumento = cobrancaDocumento;
	}

	public DocumentoTipo getDocumentoTipoAgregador() {
		return documentoTipoAgregador;
	}

	public void setDocumentoTipoAgregador(DocumentoTipo documentoTipoAgregador) {
		this.documentoTipoAgregador = documentoTipoAgregador;
	}

	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
	
	public String[] retornarAtributosInformacoesOperacaoEfetuada(){
		String []atributos = {"formatarAnoMesPagamentoParaMesAno", "documentoTipo.descricaoDocumentoTipo"};
		return atributos;		
	}
	
	public String[] retornarLabelsInformacoesOperacaoEfetuada(){
		String []atributos = {"Referencia","Tipo do Documento" };
		return atributos;		
	}

	public PagamentoCartaoDebito getPagamentoCartaoDebito() {
		return pagamentoCartaoDebito;
	}

	public void setPagamentoCartaoDebito(PagamentoCartaoDebito pagamentoCartaoDebito) {
		this.pagamentoCartaoDebito = pagamentoCartaoDebito;
	}
	
	public String getFormatarAnoMesParaMesAno() {

		String anoMesRecebido = "" + this.getAnoMesReferenciaPagamento();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatado = mes + "/" + ano;

		return anoMesFormatado.toString();
	}
}
