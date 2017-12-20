package gcom.arrecadacao;

import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.DocumentoTipo;

import java.math.BigDecimal;
import java.util.Date;

public class DadosPagamentosNaoClassificados {

	private Integer id;
	private Integer referenciaArrecadacao;
	private Integer referenciaDocumento;
	private BigDecimal valorPagamento;
	private BigDecimal valorDocumento;
	private boolean indicadorContaRetificada;
	private Date dataPagamento;
	private Date ultimaAlteracao;
	
	private DocumentoTipo documentoTipo;
	private DocumentoTipo documentoAgregador;
	private Imovel imovel;
	private Cliente cliente;
	private AvisoBancario avisoBancario;
	private Arrecadador arrecadador;
	private PagamentoSituacao pagamentoSituacao;
	private ArrecadadorMovimentoItem item;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getReferenciaArrecadacao() {
		return referenciaArrecadacao;
	}
	public void setReferenciaArrecadacao(Integer referenciaArrecadacao) {
		this.referenciaArrecadacao = referenciaArrecadacao;
	}
	public Integer getReferenciaDocumento() {
		return referenciaDocumento;
	}
	public void setReferenciaDocumento(Integer referenciaDocumento) {
		this.referenciaDocumento = referenciaDocumento;
	}
	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}
	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}
	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}
	public boolean isIndicadorContaRetificada() {
		return indicadorContaRetificada;
	}
	public void setIndicadorContaRetificada(boolean indicadorContaRetificada) {
		this.indicadorContaRetificada = indicadorContaRetificada;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}
	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}
	public DocumentoTipo getDocumentoAgregador() {
		return documentoAgregador;
	}
	public void setDocumentoAgregador(DocumentoTipo documentoAgregador) {
		this.documentoAgregador = documentoAgregador;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public AvisoBancario getAvisoBancario() {
		return avisoBancario;
	}
	public void setAvisoBancario(AvisoBancario avisoBancario) {
		this.avisoBancario = avisoBancario;
	}
	public Arrecadador getArrecadador() {
		return arrecadador;
	}
	public void setArrecadador(Arrecadador arrecadador) {
		this.arrecadador = arrecadador;
	}
	public PagamentoSituacao getPagamentoSituacao() {
		return pagamentoSituacao;
	}
	public void setPagamentoSituacao(PagamentoSituacao pagamentoSituacao) {
		this.pagamentoSituacao = pagamentoSituacao;
	}
	public ArrecadadorMovimentoItem getItem() {
		return item;
	}
	public void setItem(ArrecadadorMovimentoItem item) {
		this.item = item;
	}
}
