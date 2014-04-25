package gcom.arrecadacao;

import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.DocumentoTipo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** @author Hibernate CodeGenerator */
public class ArrecadacaoDadosDiariosAuxiliar implements Serializable {

    /**
	 * @since 05/12/2012
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer anoMesReferenciaArrecadacao;

    /** nullable persistent field */
    private Date dataPagamento;

    /** nullable persistent field */
    private Integer quantidadePagamentos = 0;

    /** nullable persistent field */
    private BigDecimal valorPagamentos = new BigDecimal("0.0");

    /** persistent field */
    private Arrecadador arrecadador;

    /** persistent field */
    private ArrecadacaoForma arrecadacaoForma;

    /** persistent field */
    private DocumentoTipo documentoTipo;

    /** persistent field */
    private GerenciaRegional gerenciaRegional;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private UnidadeNegocio unidadeNegocio;
    
    /** persistent field */
    private Integer quantidadeDocumentos = 0;
    
    /** persistent field */
    private DocumentoTipo documentoTipoAgregador;
    
    /** persistent field */
    private BigDecimal valorUnitarioTarifa = new BigDecimal("0.0");
    
    /** persistent field */
    private Short numeroDiasFloat;
    
    /** persistent field */
    private BigDecimal valorTotalTarifas;
    
    /** persistent field */
    private Date ultimaAlteracao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnoMesReferenciaArrecadacao() {
		return anoMesReferenciaArrecadacao;
	}

	public void setAnoMesReferenciaArrecadacao(Integer anoMesReferenciaArrecadacao) {
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Integer getQuantidadePagamentos() {
		return quantidadePagamentos;
	}

	public void setQuantidadePagamentos(Integer quantidadePagamentos) {
		this.quantidadePagamentos = quantidadePagamentos;
	}

	public BigDecimal getValorPagamentos() {
		return valorPagamentos;
	}

	public void setValorPagamentos(BigDecimal valorPagamentos) {
		this.valorPagamentos = valorPagamentos;
	}

	public Arrecadador getArrecadador() {
		return arrecadador;
	}

	public void setArrecadador(Arrecadador arrecadador) {
		this.arrecadador = arrecadador;
	}

	public ArrecadacaoForma getArrecadacaoForma() {
		return arrecadacaoForma;
	}

	public void setArrecadacaoForma(ArrecadacaoForma arrecadacaoForma) {
		this.arrecadacaoForma = arrecadacaoForma;
	}

	public DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}

	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Integer getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}

	public void setQuantidadeDocumentos(Integer quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}

	public DocumentoTipo getDocumentoTipoAgregador() {
		return documentoTipoAgregador;
	}

	public void setDocumentoTipoAgregador(DocumentoTipo documentoTipoAgregador) {
		this.documentoTipoAgregador = documentoTipoAgregador;
	}

	public BigDecimal getValorUnitarioTarifa() {
		return valorUnitarioTarifa;
	}

	public void setValorUnitarioTarifa(BigDecimal valorUnitarioTarifa) {
		this.valorUnitarioTarifa = valorUnitarioTarifa;
	}

	public Short getNumeroDiasFloat() {
		return numeroDiasFloat;
	}

	public void setNumeroDiasFloat(Short numeroDiasFloat) {
		this.numeroDiasFloat = numeroDiasFloat;
	}

	public BigDecimal getValorTotalTarifas() {
		return valorTotalTarifas;
	}

	public void setValorTotalTarifas(BigDecimal valorTotalTarifas) {
		this.valorTotalTarifas = valorTotalTarifas;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
