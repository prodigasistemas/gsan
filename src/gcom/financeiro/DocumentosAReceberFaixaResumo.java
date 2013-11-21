package gcom.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DocumentosAReceberFaixaResumo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer quantidadeDocumentos;
	
	private BigDecimal valorDocumentos;
	
	private Date ultimaAlteracao;
	
	private FaixaDocumentosAReceber faixaDocumentosAReceber;
	
	private DocumentosAReceberResumo documentosAReceberResumo;

	public DocumentosAReceberFaixaResumo() {
		super();
	}

	public DocumentosAReceberFaixaResumo(Integer id, Integer quantidadeDocumentos,
			BigDecimal valorDocumentos, Date ultimaAlteracao, FaixaDocumentosAReceber faixaDocumentosAReceber,
			DocumentosAReceberResumo documentosAReceberResumo) {
		super();
		this.id = id;
		this.quantidadeDocumentos = quantidadeDocumentos;
		this.valorDocumentos = valorDocumentos;
		this.ultimaAlteracao = ultimaAlteracao;
		this.faixaDocumentosAReceber = faixaDocumentosAReceber;
		this.documentosAReceberResumo = documentosAReceberResumo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DocumentosAReceberResumo getDocumentosAReceberResumo() {
		return documentosAReceberResumo;
	}

	public void setDocumentosAReceberResumo(
			DocumentosAReceberResumo documentosAReceberResumo) {
		this.documentosAReceberResumo = documentosAReceberResumo;
	}

	public FaixaDocumentosAReceber getFaixaDocumentosAReceber() {
		return faixaDocumentosAReceber;
	}

	public void setFaixaDocumentosAReceber(
			FaixaDocumentosAReceber faixaDocumentosAReceber) {
		this.faixaDocumentosAReceber = faixaDocumentosAReceber;
	}

	public Integer getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}

	public void setQuantidadeDocumentos(Integer quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorDocumentos() {
		return valorDocumentos;
	}

	public void setValorDocumentos(BigDecimal valorDocumentos) {
		this.valorDocumentos = valorDocumentos;
	}
	
	

}
