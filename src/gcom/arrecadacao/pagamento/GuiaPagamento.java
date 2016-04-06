package gcom.arrecadacao.pagamento;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
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

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class GuiaPagamento extends ObjetoTransacao implements IGuiaPagamento {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_GUIA_PAGAMENTO_INSERIR = 115; //Operacao.OPERACAO_GUIA_PAGAMENTO_INSERIR
	public static final int ATRIBUTOS_GUIA_PAGAMENTO_CANCELAR = 116; //Operacao.OPERACAO_GUIA_PAGAMENTO_CANCELAR
	
	private Integer id;
	private Integer anoMesReferenciaContabil;
	private Date ultimaAlteracao;
	private Cliente cliente;
	private Parcelamento parcelamento;
	private DocumentoTipo documentoTipo;
	private Imovel imovel;
	private Localidade localidade;
	private FinanciamentoTipo financiamentoTipo;
	private LancamentoItemContabil lancamentoItemContabil;
	private DebitoCreditoSituacao debitoCreditoSituacaoAnterior;
	private Short indicadoCobrancaMulta;
	private Short numeroPrestacaoDebito;
	private Set clientesGuiaPagamento;
	private GuiaPagamentoGeral guiaPagamentoGeral;
	private Integer numeroGuia;
	private Integer anoGuia;
	private Integer lotePagamento;
	private Usuario usuario;
	private GuiaPagamentoGeral origem;
	private String observacao;
	private Short indicadorEmitirObservacao;
	private String numeroGuiaFatura;
	
	@ControleAlteracao(value=FiltroGuiaPagamento.DEBITO_TIPO,funcionalidade={ATRIBUTOS_GUIA_PAGAMENTO_INSERIR})
	private Date dataEmissao;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_GUIA_PAGAMENTO_INSERIR,ATRIBUTOS_GUIA_PAGAMENTO_CANCELAR})
	private Date dataVencimento;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_GUIA_PAGAMENTO_INSERIR,ATRIBUTOS_GUIA_PAGAMENTO_CANCELAR})
	private BigDecimal valorDebito;
	
	
	@ControleAlteracao(value=FiltroGuiaPagamento.REGISTRO_ATENDIMENTO, funcionalidade={ATRIBUTOS_GUIA_PAGAMENTO_INSERIR,ATRIBUTOS_GUIA_PAGAMENTO_CANCELAR})
	private RegistroAtendimento registroAtendimento;
	
	
	@ControleAlteracao(value=FiltroGuiaPagamento.DEBITO_TIPO, funcionalidade={ATRIBUTOS_GUIA_PAGAMENTO_INSERIR,ATRIBUTOS_GUIA_PAGAMENTO_CANCELAR})
	private DebitoTipo debitoTipo;
	
	@ControleAlteracao(value=FiltroGuiaPagamento.ORDEM_SERVICO, funcionalidade={ATRIBUTOS_GUIA_PAGAMENTO_INSERIR,ATRIBUTOS_GUIA_PAGAMENTO_CANCELAR})
	private OrdemServico ordemServico;
	
	
	@ControleAlteracao(value=FiltroGuiaPagamento.DEBITO_CREDITO_SITUACAO_ATUAL, funcionalidade={ATRIBUTOS_GUIA_PAGAMENTO_CANCELAR})
	private DebitoCreditoSituacao debitoCreditoSituacaoAtual;
	
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_GUIA_PAGAMENTO_INSERIR,ATRIBUTOS_GUIA_PAGAMENTO_CANCELAR})
	private Short numeroPrestacaoTotal;
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();

		filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.ID, this.getId()));
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("parcelamento");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("ordemServico");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAnterior");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("usuario");

		return filtroGuiaPagamento;
	}


	public GuiaPagamento() {
	}

	public GuiaPagamento(Integer id) {
		this.id = id;
	}

	public GuiaPagamento(Integer anoMesReferenciaContabil, Date dataEmissao,
			Date dataVencimento, BigDecimal valorDebito, Date ultimaAlteracao,
			Cliente cliente, Parcelamento parcelamento,
			DocumentoTipo documentoTipo,
			RegistroAtendimento registroAtendimento, Imovel imovel,
			Localidade localidade, FinanciamentoTipo financiamentoTipo,
			DebitoTipo debitoTipo, OrdemServico ordemServico,
			LancamentoItemContabil lancamentoItemContabil,
			DebitoCreditoSituacao debitoCreditoSituacaoAtual,
			DebitoCreditoSituacao debitoCreditoSituacaoAnterior,
			Short indicadoCobrancaMulta, Usuario usuario) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
		this.dataEmissao = dataEmissao;
		this.dataVencimento = dataVencimento;
		this.valorDebito = valorDebito;
		this.ultimaAlteracao = ultimaAlteracao;
		this.cliente = cliente;
		this.parcelamento = parcelamento;
		this.documentoTipo = documentoTipo;
		this.registroAtendimento = registroAtendimento;
		this.imovel = imovel;
		this.localidade = localidade;
		this.financiamentoTipo = financiamentoTipo;
		this.debitoTipo = debitoTipo;
		this.ordemServico = ordemServico;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
		this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
		this.indicadoCobrancaMulta = indicadoCobrancaMulta;
		this.usuario = usuario;
	}

	public GuiaPagamento(Integer anoMesReferenciaContabil, Date dataEmissao,
			Date dataVencimento, BigDecimal valorDebito, Date ultimaAlteracao,
			Cliente cliente, Parcelamento parcelamento,
			DocumentoTipo documentoTipo,
			RegistroAtendimento registroAtendimento, Imovel imovel,
			Localidade localidade, FinanciamentoTipo financiamentoTipo,
			DebitoTipo debitoTipo, OrdemServico ordemServico,
			LancamentoItemContabil lancamentoItemContabil,
			DebitoCreditoSituacao debitoCreditoSituacaoAtual,
			DebitoCreditoSituacao debitoCreditoSituacaoAnterior) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
		this.dataEmissao = dataEmissao;
		this.dataVencimento = dataVencimento;
		this.valorDebito = valorDebito;
		this.ultimaAlteracao = ultimaAlteracao;
		this.cliente = cliente;
		this.parcelamento = parcelamento;
		this.documentoTipo = documentoTipo;
		this.registroAtendimento = registroAtendimento;
		this.imovel = imovel;
		this.localidade = localidade;
		this.financiamentoTipo = financiamentoTipo;
		this.debitoTipo = debitoTipo;
		this.ordemServico = ordemServico;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
		this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
	}

	public GuiaPagamento(Cliente cliente, Parcelamento parcelamento,
			DocumentoTipo documentoTipo,
			RegistroAtendimento registroAtendimento, Imovel imovel,
			Localidade localidade, FinanciamentoTipo financiamentoTipo,
			DebitoTipo debitoTipo, OrdemServico ordemServico,
			LancamentoItemContabil lancamentoItemContabil,
			DebitoCreditoSituacao debitoCreditoSituacaoAtual,
			DebitoCreditoSituacao debitoCreditoSituacaoAnterior) {
		this.cliente = cliente;
		this.parcelamento = parcelamento;
		this.documentoTipo = documentoTipo;
		this.registroAtendimento = registroAtendimento;
		this.imovel = imovel;
		this.localidade = localidade;
		this.financiamentoTipo = financiamentoTipo;
		this.debitoTipo = debitoTipo;
		this.ordemServico = ordemServico;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
		this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnoMesReferenciaContabil() {
		return this.anoMesReferenciaContabil;
	}

	public void setAnoMesReferenciaContabil(Integer anoMesReferenciaContabil) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
	}

	public Date getDataEmissao() {
		return this.dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataVencimento() {
		return this.dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorDebito() {
		if(valorDebito != null){
			return valorDebito.setScale(2);
		}else{
			return valorDebito;	
		}		
	}
	
	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Parcelamento getParcelamento() {
		return this.parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	public DocumentoTipo getDocumentoTipo() {
		return this.documentoTipo;
	}

	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
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

	public Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public FinanciamentoTipo getFinanciamentoTipo() {
		return this.financiamentoTipo;
	}

	public void setFinanciamentoTipo(FinanciamentoTipo financiamentoTipo) {
		this.financiamentoTipo = financiamentoTipo;
	}

	public DebitoTipo getDebitoTipo() {
		return this.debitoTipo;
	}

	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	public OrdemServico getOrdemServico() {
		return this.ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public LancamentoItemContabil getLancamentoItemContabil() {
		return this.lancamentoItemContabil;
	}

	public void setLancamentoItemContabil(
			LancamentoItemContabil lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
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

	public Set getClientesGuiaPagamento() {
		return clientesGuiaPagamento;
	}

	public void setClientesGuiaPagamento(Set clientesGuiaPagamento) {
		this.clientesGuiaPagamento = clientesGuiaPagamento;
	}

	public Short getIndicadoCobrancaMulta() {
		return indicadoCobrancaMulta;
	}

	public void setIndicadoCobrancaMulta(Short indicadoCobrancaMulta) {
		this.indicadoCobrancaMulta = indicadoCobrancaMulta;
	}

	public GuiaPagamentoGeral getGuiaPagamentoGeral() {
		return guiaPagamentoGeral;
	}

	public void setGuiaPagamentoGeral(GuiaPagamentoGeral guiaPagamentoGeral) {
		this.guiaPagamentoGeral = guiaPagamentoGeral;
	}
	
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public GuiaPagamentoGeral getOrigem() {
		return origem;
	}

	public void setOrigem(GuiaPagamentoGeral origem) {
		this.origem = origem;
	}

	public Short getNumeroPrestacaoDebito() {
		return numeroPrestacaoDebito;
	}

	public void setNumeroPrestacaoDebito(Short numeroPrestacaoDebito) {
		this.numeroPrestacaoDebito = numeroPrestacaoDebito;
	}

	public Short getNumeroPrestacaoTotal() {
		return numeroPrestacaoTotal;
	}

	public void setNumeroPrestacaoTotal(Short numeroPrestacaoTotal) {
		this.numeroPrestacaoTotal = numeroPrestacaoTotal;
	}
    
    public String getPrestacaoFormatada(){
        String prestacaoFormatada = "";
        
        if(getNumeroPrestacaoDebito() != null &&
                getNumeroPrestacaoTotal() != null){
            prestacaoFormatada = prestacaoFormatada + getNumeroPrestacaoDebito() + " / " + getNumeroPrestacaoTotal();
        }
        
        return  prestacaoFormatada ;
    }
	
	public String[] retornarAtributosInformacoesOperacaoEfetuada(){
		String []atributos = {
				"debitoTipo.descricao"
				, "valorDebito"
				};
			return atributos;		
	}
	
	public String[] retornarLabelsInformacoesOperacaoEfetuada(){
		String []labels = {"Tipo Debito"
				, "Valor"
				};
			return labels;		
	}

	public Integer getAnoGuia() {
		return anoGuia;
	}

	public void setAnoGuia(Integer anoGuia) {
		this.anoGuia = anoGuia;
	}

	public Integer getLotePagamento() {
		return lotePagamento;
	}

	public void setLotePagamento(Integer lotePagamento) {
		this.lotePagamento = lotePagamento;
	}

	public Integer getNumeroGuia() {
		return numeroGuia;
	}

	public void setNumeroGuia(Integer numeroGuia) {
		this.numeroGuia = numeroGuia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Short getIndicadorEmitirObservacao() {
		return indicadorEmitirObservacao;
	}

	public void setIndicadorEmitirObservacao(Short indicadorEmitirObservacao) {
		this.indicadorEmitirObservacao = indicadorEmitirObservacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof GuiaPagamento)) {
			return false;
		}
		
		GuiaPagamento castOther = (GuiaPagamento) other;

		return (this.getId().equals(castOther.getId()));
	}

	public String getNumeroGuiaFatura() {
		return numeroGuiaFatura;
	}

	public void setNumeroGuiaFatura(String numeroGuiaFatura) {
		this.numeroGuiaFatura = numeroGuiaFatura;
	}
	
	public String getFormatarAnoMesReferenciaGuia() {

		String anoMesRecebido = "" + this.getAnoGuia();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatado = mes + "/" + ano;

		return anoMesFormatado.toString();
	}
}
