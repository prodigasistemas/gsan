package gcom.cobranca.contratoparcelamento;

import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.credito.CreditoARealizarGeral;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

@ControleAlteracao()
public class ContratoParcelamentoItem  extends ObjetoTransacao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_CANCELAR_CONTRATO_PARCELAMENTO_CLIENTE = 1794; //Operacao.OPERACAO_CANCELAR_CONTRATO_PARCELAMENTO_CLIENTE;
	
	/** identifier field */
	private Integer id;
	
	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	private DocumentoTipo documentoTipo;

	@ControleAlteracao(value=FiltroContratoParcelamentoItem.CONTRATO,
			funcionalidade={ATRIBUTOS_CANCELAR_CONTRATO_PARCELAMENTO_CLIENTE})
	private ContratoParcelamento contrato;
	
	private ContaGeral contaGeral;
	
	private DebitoACobrarGeral debitoACobrarGeral;
	
	private GuiaPagamentoGeral guiaPagamentoGeral;
	
	private CreditoARealizarGeral creditoARealizarGeral;
	
	private BigDecimal valorItem;
	
	private BigDecimal valarAcrescImpont;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CANCELAR_CONTRATO_PARCELAMENTO_CLIENTE})
	private Short indicadorItemCancelado;
	
	/**
	 * Variáveis Transient de auxílio no Obter Débito do Consultar Dados do 
	 * Contrato de Parcelamento po Cliente. 
	 */
	private BigDecimal valorMulta;

	private BigDecimal valorJurosMora;

	private BigDecimal valorAtualizacaoMonetaria;
	
	
	public Short getIndicadorItemCancelado() {
		return indicadorItemCancelado;
	}

	public void setIndicadorItemCancelado(Short indicadorItemCancelado) {
		this.indicadorItemCancelado = indicadorItemCancelado;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroContratoParcelamentoItem filtroContratoParcelamentoItem = new FiltroContratoParcelamentoItem();

		filtroContratoParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContratoParcelamentoItem.CONTRATO);
		filtroContratoParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContratoParcelamentoItem.DOCUMENTO_TIPO);
		filtroContratoParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContratoParcelamentoItem.CONTA_GERAL);
		filtroContratoParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContratoParcelamentoItem.DEBITO_A_COBRAR_GERAL);
		filtroContratoParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContratoParcelamentoItem.GUIA_PAGAMENTO_GERAL);
		filtroContratoParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContratoParcelamentoItem.CREDITO_A_REALIZAR_GERAL);
		
		filtroContratoParcelamentoItem.adicionarParametro(new ParametroSimples(
				FiltroContratoParcelamentoItem.ID, this.getId()));
		
		return filtroContratoParcelamentoItem;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}

	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public ContratoParcelamento getContrato() {
		return contrato;
	}

	public void setContrato(ContratoParcelamento contrato) {
		this.contrato = contrato;
	}

	public DebitoACobrarGeral getDebitoACobrarGeral() {
		return debitoACobrarGeral;
	}

	public void setDebitoACobrarGeral(DebitoACobrarGeral debitoACobrarGeral) {
		this.debitoACobrarGeral = debitoACobrarGeral;
	}

	public GuiaPagamentoGeral getGuiaPagamentoGeral() {
		return guiaPagamentoGeral;
	}

	public void setGuiaPagamentoGeral(GuiaPagamentoGeral guiaPagamentoGeral) {
		this.guiaPagamentoGeral = guiaPagamentoGeral;
	}

	public CreditoARealizarGeral getCreditoARealizarGeral() {
		return creditoARealizarGeral;
	}

	public void setCreditoARealizarGeral(CreditoARealizarGeral creditoARealizarGeral) {
		this.creditoARealizarGeral = creditoARealizarGeral;
	}

	public BigDecimal getValorItem() {
		return valorItem;
	}

	public void setValorItem(BigDecimal valorItem) {
		this.valorItem = valorItem;
	}

	public BigDecimal getValarAcrescImpont() {
		return valarAcrescImpont;
	}

	public void setValarAcrescImpont(BigDecimal valarAcrescImpont) {
		this.valarAcrescImpont = valarAcrescImpont;
	}

	public ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}
	
	public BigDecimal getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}

	public BigDecimal getValorJurosMora() {
		return valorJurosMora;
	}

	public void setValorJurosMora(BigDecimal valorJurosMora) {
		this.valorJurosMora = valorJurosMora;
	}

	public BigDecimal getValorAtualizacaoMonetaria() {
		return valorAtualizacaoMonetaria;
	}

	public void setValorAtualizacaoMonetaria(BigDecimal valorAtualizacaoMonetaria) {
		this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
	}

	public BigDecimal getValorTotalContaValores() {

		BigDecimal retorno = new BigDecimal("0.00");

		// Valor de Multa
		if (this.getValorMulta() != null) {
			retorno = retorno.add(this.getValorMulta());
		}
		// Valor de JurosMora
		if (this.getValorJurosMora() != null) {
			retorno = retorno.add(this.getValorJurosMora());
		}
		// Valor de AtualizacaoMonetaria
		if (this.getValorAtualizacaoMonetaria() != null) {
			retorno = retorno.add(this.getValorAtualizacaoMonetaria());
		}

		retorno = retorno.setScale(2, BigDecimal.ROUND_HALF_UP);

		return retorno;
	}
	
	public String[] retornarAtributosInformacoesOperacaoEfetuada(){
		String []atributos = {"indicadorItemCancelado", "contrato.numero"};
		return atributos;		
	}
	
	public String[] retornarLabelsInformacoesOperacaoEfetuada(){
		String []atributos = {"Indicador Item Cancelado","Contrato de Parcelamento" };
		return atributos;		
	}
	
}
