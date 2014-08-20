package gcom.financeiro;

import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.DocumentoTipo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DocumentosAReceberResumo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer anoMesReferenciaRecebimentos;
	
	private GerenciaRegional gerenciaRegional;
	
	private UnidadeNegocio unidadeNegocio;
	
	private Localidade localidade;
	
	private ImovelPerfil imovelPerfil;
	
	private EsferaPoder esferaPoder;
	
	private Categoria categoria;
	
	private DocumentoTipo documentoTipo;
	
	private Short indicadorSituacaoDocumentos;
	
	private Integer quantidadeDiasVencidos;
	
	private Integer quantidadeDocumentos;
	
	private BigDecimal valorDocumentos;
	
	private BigDecimal valorDocumentosSemParcelaAtual;
	
	private Date ultimaAlteracao;
	
	
	public final static Short DOCUMENTO_A_VENCER = new Short("1");
	public final static Short DOCUMENTO_VENCIDO = new Short("2");

	public DocumentosAReceberResumo(){}

	public Integer getAnoMesReferenciaRecebimentos() {
		return anoMesReferenciaRecebimentos;
	}

	public void setAnoMesReferenciaRecebimentos(Integer anoMesReferenciaRecebimentos) {
		this.anoMesReferenciaRecebimentos = anoMesReferenciaRecebimentos;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}

	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public EsferaPoder getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(EsferaPoder esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public Short getIndicadorSituacaoDocumentos() {
		return indicadorSituacaoDocumentos;
	}

	public void setIndicadorSituacaoDocumentos(Short indicadorSituacaoDocumentos) {
		this.indicadorSituacaoDocumentos = indicadorSituacaoDocumentos;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Integer getQuantidadeDiasVencidos() {
		return quantidadeDiasVencidos;
	}

	public void setQuantidadeDiasVencidos(Integer quantidadeDiasVencidos) {
		this.quantidadeDiasVencidos = quantidadeDiasVencidos;
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

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public BigDecimal getValorDocumentos() {
		return valorDocumentos;
	}

	public void setValorDocumentos(BigDecimal valorDocumentos) {
		this.valorDocumentos = valorDocumentos;
	}

	public BigDecimal getValorDocumentosSemParcelaAtual() {
		return valorDocumentosSemParcelaAtual;
	}

	public void setValorDocumentosSemParcelaAtual(BigDecimal valorDocumentosSemParcelaAtual) {
		this.valorDocumentosSemParcelaAtual = valorDocumentosSemParcelaAtual;
	}
}
