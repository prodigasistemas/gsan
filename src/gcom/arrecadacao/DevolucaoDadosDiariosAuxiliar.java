package gcom.arrecadacao;

import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.DocumentoTipo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Adriana Muniz
 *  @date 12/12/2012
 *  Hibernate CodeGenerator */
public class DevolucaoDadosDiariosAuxiliar implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** persistent field */
    private Date dataDevolucao;

    /** persistent field */
    private int quantidadeDevolucoes;

    /** persistent field */
    private BigDecimal valorDevolucoes;

    /** persistent field */
    private Integer quantidadeDocumentos;

    /** nullable persistent field */
    private String devolucaoTipo;

    /** persistent field */
    private Date dataUltimaAlteracao;

    /** persistent field */
    private GerenciaRegional gerenciaRegional;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private DocumentoTipo documentoTipoAgregador;

    /** persistent field */
    private DocumentoTipo documentoTipo;

    /** persistent field */
    private Arrecadador arrecadador;

    /** persistent field */
    private ArrecadacaoForma arrecadacaoForma;

    /** persistent field */
    private UnidadeNegocio unidadeNegocio;

    /** persistent field */
    private CobrancaDocumento cobrancaDocumento;

    public DevolucaoDadosDiariosAuxiliar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DevolucaoDadosDiariosAuxiliar(Integer id, int anoMesReferencia,
			Date dataDevolucao, int quantidadeDevolucoes,
			BigDecimal valorDevolucoes, Integer quantidadeDocumentos,
			String devolucaoTipo, Date dataUltimaAlteracao,
			GerenciaRegional gerenciaRegional, Localidade localidade,
			DocumentoTipo documentoTipoAgregador, DocumentoTipo documentoTipo,
			Arrecadador arrecadador, ArrecadacaoForma arrecadacaoForma,
			UnidadeNegocio unidadeNegocio, CobrancaDocumento cobrancaDocumento) {
		super();
		this.id = id;
		this.anoMesReferencia = anoMesReferencia;
		this.dataDevolucao = dataDevolucao;
		this.quantidadeDevolucoes = quantidadeDevolucoes;
		this.valorDevolucoes = valorDevolucoes;
		this.quantidadeDocumentos = quantidadeDocumentos;
		this.devolucaoTipo = devolucaoTipo;
		this.dataUltimaAlteracao = dataUltimaAlteracao;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.documentoTipoAgregador = documentoTipoAgregador;
		this.documentoTipo = documentoTipo;
		this.arrecadador = arrecadador;
		this.arrecadacaoForma = arrecadacaoForma;
		this.unidadeNegocio = unidadeNegocio;
		this.cobrancaDocumento = cobrancaDocumento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(int anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public int getQuantidadeDevolucoes() {
		return quantidadeDevolucoes;
	}

	public void setQuantidadeDevolucoes(int quantidadeDevolucoes) {
		this.quantidadeDevolucoes = quantidadeDevolucoes;
	}

	public BigDecimal getValorDevolucoes() {
		return valorDevolucoes;
	}

	public void setValorDevolucoes(BigDecimal valorDevolucoes) {
		this.valorDevolucoes = valorDevolucoes;
	}

	public Integer getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}

	public void setQuantidadeDocumentos(Integer quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}

	public String getDevolucaoTipo() {
		return devolucaoTipo;
	}

	public void setDevolucaoTipo(String devolucaoTipo) {
		this.devolucaoTipo = devolucaoTipo;
	}

	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
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

	public DocumentoTipo getDocumentoTipoAgregador() {
		return documentoTipoAgregador;
	}

	public void setDocumentoTipoAgregador(DocumentoTipo documentoTipoAgregador) {
		this.documentoTipoAgregador = documentoTipoAgregador;
	}

	public DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}

	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
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

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public CobrancaDocumento getCobrancaDocumento() {
		return cobrancaDocumento;
	}

	public void setCobrancaDocumento(CobrancaDocumento cobrancaDocumento) {
		this.cobrancaDocumento = cobrancaDocumento;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
