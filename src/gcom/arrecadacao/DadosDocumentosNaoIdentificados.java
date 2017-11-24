package gcom.arrecadacao;

import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.bean.RegistroHelperCodigoG;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;

public class DadosDocumentosNaoIdentificados {

	private Integer id;
	private Integer referenciaArrecadacao;
	private BigDecimal valorDocumento;
	private Date dataPagamento;
	private Date ultimaAlteracao;
	
	private AvisoBancario avisoBancario;
	private Arrecadador arrecadador;
	
	public DadosDocumentosNaoIdentificados(){
	}
	
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
	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}
	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
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
	
	public void setDadosCodigoBarras(RegistroHelperCodigoG codigoBarras){
		this.referenciaArrecadacao = Util.recuperaAnoMesDaData(Util.converteStringParaDateAmericana(codigoBarras.getDataPagamento()));
		this.valorDocumento = new BigDecimal(codigoBarras.getValorRecebido());
		this.dataPagamento = Util.converteStringParaDateAmericana(codigoBarras.getDataPagamento());
	}
}
