package gcom.cobranca;

import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.faturamento.conta.MotivoNaoEntregaDocumento;

import java.math.BigDecimal;
import java.util.Date;

public class CobrancaDocumentoHistorico {

	private Integer id;

	private int numeroSequenciaDocumento;

	private Date emissao;

	private BigDecimal valorDesconto;

	private Integer numeroQuadra;

	private BigDecimal valorDocumento;

	private BigDecimal valorTaxa;

	private Integer codigoSetorComercial;

	private Date ultimaAlteracao;

	private BigDecimal valorAcrescimos;

	private Date dataSituacaoAcao;

	private Date dataSituacaoDebito;

	private Short numeroSequencialImpressao;

	private Short indicadorAcimaLimite;

	private Short indicadorAntesApos;

	Imovel imovel;
	CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando;
	Empresa empresa;
	CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma;
	DocumentoTipo documentoTipo;
	Quadra quadra;
	DocumentoEmissaoForma documentoEmissaoForma;
	ImovelPerfil imovelPerfil;
	Localidade localidade;
	MotivoNaoEntregaDocumento motivoNaoEntregaDocumento;
	CobrancaAcao cobrancaAcao;
	CobrancaCriterio cobrancaCriterio;
	CobrancaAcaoSituacao cobrancaAcaoSituacao;
	CobrancaDebitoSituacao cobrancaDebitoSituacao;
	Cliente cliente;
	Categoria categoria;
	EsferaPoder esferaPoder;
	FiscalizacaoSituacao fiscalizacaoSituacao;

	private ResolucaoDiretoria resolucaoDiretoria;
	
	public CobrancaDocumentoHistorico() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNumeroSequenciaDocumento() {
		return numeroSequenciaDocumento;
	}

	public void setNumeroSequenciaDocumento(int numeroSequenciaDocumento) {
		this.numeroSequenciaDocumento = numeroSequenciaDocumento;
	}

	public Date getEmissao() {
		return emissao;
	}

	public void setEmissao(Date emissao) {
		this.emissao = emissao;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}

	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	public BigDecimal getValorTaxa() {
		return valorTaxa;
	}

	public void setValorTaxa(BigDecimal valorTaxa) {
		this.valorTaxa = valorTaxa;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorAcrescimos() {
		return valorAcrescimos;
	}

	public void setValorAcrescimos(BigDecimal valorAcrescimos) {
		this.valorAcrescimos = valorAcrescimos;
	}

	public Date getDataSituacaoAcao() {
		return dataSituacaoAcao;
	}

	public void setDataSituacaoAcao(Date dataSituacaoAcao) {
		this.dataSituacaoAcao = dataSituacaoAcao;
	}

	public Date getDataSituacaoDebito() {
		return dataSituacaoDebito;
	}

	public void setDataSituacaoDebito(Date dataSituacaoDebito) {
		this.dataSituacaoDebito = dataSituacaoDebito;
	}

	public Short getNumeroSequencialImpressao() {
		return numeroSequencialImpressao;
	}

	public void setNumeroSequencialImpressao(Short numeroSequencialImpressao) {
		this.numeroSequencialImpressao = numeroSequencialImpressao;
	}

	public Short getIndicadorAcimaLimite() {
		return indicadorAcimaLimite;
	}

	public void setIndicadorAcimaLimite(Short indicadorAcimaLimite) {
		this.indicadorAcimaLimite = indicadorAcimaLimite;
	}

	public Short getIndicadorAntesApos() {
		return indicadorAntesApos;
	}

	public void setIndicadorAntesApos(Short indicadorAntesApos) {
		this.indicadorAntesApos = indicadorAntesApos;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public CobrancaAcaoAtividadeComando getCobrancaAcaoAtividadeComando() {
		return cobrancaAcaoAtividadeComando;
	}

	public void setCobrancaAcaoAtividadeComando(
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public CobrancaAcaoAtividadeCronograma getCobrancaAcaoAtividadeCronograma() {
		return cobrancaAcaoAtividadeCronograma;
	}

	public void setCobrancaAcaoAtividadeCronograma(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma) {
		this.cobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma;
	}

	public DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}

	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public Quadra getQuadra() {
		return quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public DocumentoEmissaoForma getDocumentoEmissaoForma() {
		return documentoEmissaoForma;
	}

	public void setDocumentoEmissaoForma(
			DocumentoEmissaoForma documentoEmissaoForma) {
		this.documentoEmissaoForma = documentoEmissaoForma;
	}

	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public MotivoNaoEntregaDocumento getMotivoNaoEntregaDocumento() {
		return motivoNaoEntregaDocumento;
	}

	public void setMotivoNaoEntregaDocumento(
			MotivoNaoEntregaDocumento motivoNaoEntregaDocumento) {
		this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
	}

	public CobrancaAcao getCobrancaAcao() {
		return cobrancaAcao;
	}

	public void setCobrancaAcao(CobrancaAcao cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}

	public CobrancaCriterio getCobrancaCriterio() {
		return cobrancaCriterio;
	}

	public void setCobrancaCriterio(CobrancaCriterio cobrancaCriterio) {
		this.cobrancaCriterio = cobrancaCriterio;
	}

	public CobrancaAcaoSituacao getCobrancaAcaoSituacao() {
		return cobrancaAcaoSituacao;
	}

	public void setCobrancaAcaoSituacao(
			CobrancaAcaoSituacao cobrancaAcaoSituacao) {
		this.cobrancaAcaoSituacao = cobrancaAcaoSituacao;
	}

	public CobrancaDebitoSituacao getCobrancaDebitoSituacao() {
		return cobrancaDebitoSituacao;
	}

	public void setCobrancaDebitoSituacao(
			CobrancaDebitoSituacao cobrancaDebitoSituacao) {
		this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public EsferaPoder getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(EsferaPoder esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public FiscalizacaoSituacao getFiscalizacaoSituacao() {
		return fiscalizacaoSituacao;
	}

	public void setFiscalizacaoSituacao(
			FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}

	public ResolucaoDiretoria getResolucaoDiretoria() {
		return resolucaoDiretoria;
	}

	public void setResolucaoDiretoria(ResolucaoDiretoria resolucaoDiretoria) {
		this.resolucaoDiretoria = resolucaoDiretoria;
	}

	
}
