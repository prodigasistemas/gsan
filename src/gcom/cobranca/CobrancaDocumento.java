package gcom.cobranca;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.faturamento.conta.MotivoNaoEntregaDocumento;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class CobrancaDocumento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private int numeroSequenciaDocumento;
	private Date emissao;
	private BigDecimal valorDesconto;
	private Integer numeroQuadra;
	private BigDecimal valorDocumento;
	private BigDecimal valorTaxa;
	private Integer codigoSetorComercial;
	private Date ultimaAlteracao;
	private DocumentoEmissaoForma documentoEmissaoForma;
	private CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando;
	private Imovel imovel;
	private Empresa empresa;
	private DocumentoTipo documentoTipo;
	private ImovelPerfil imovelPerfil;
	private Quadra quadra;
	private Localidade localidade;
	private CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma;
	private MotivoNaoEntregaDocumento motivoNaoEntregaDocumento;
	private CobrancaCriterio cobrancaCriterio;
	private CobrancaAcao cobrancaAcao;
	private BigDecimal valorAcrescimos;
	private Date dataSituacaoAcao;
	private Date dataSituacaoDebito;
	private Integer sequencialImpressao;
	private Short indicadorAntesApos;
	private Short indicadorLimite;
	private CobrancaDebitoSituacao cobrancaDebitoSituacao;
	private CobrancaAcaoSituacao cobrancaAcaoSituacao;
	private Cliente cliente;
	private Categoria categoria;
	private EsferaPoder esferaPoder;
	private FiscalizacaoSituacao fiscalizacaoSituacao;
	private AtendimentoMotivoEncerramento motivoEncerramento;
	private ResolucaoDiretoria resolucaoDiretoria;
	private LigacaoAguaSituacao ligacaoAguaSituacao;
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
	private Date dataEmissaoPredecessor;
	private String numeroDocumentoFatura;
	private BigDecimal valorImpostos;
	private Usuario usuario;

	public final static String INCLUIR_ACRESCIMOS = "1";
	public final static String NAO_INCLUIR_ACRESCIMOS = "2";
	public final static String INCLUIR_ACRESCIMOS_COM_DESCONTO = "3";

	public CobrancaDocumento(
			Integer id,
			int numeroSequenciaDocumento,
			Date emissao, 
			BigDecimal valorDesconto, 
			Integer numeroQuadra,
			BigDecimal valorDocumento, 
			BigDecimal valorTaxa,
			Integer codigoSetorComercial, 
			Date ultimaAlteracao,
			DocumentoEmissaoForma documentoEmissaoForma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			Imovel imovel, 
			Empresa empresa, 
			DocumentoTipo documentoTipo,
			ImovelPerfil imovelPerfil, 
			Quadra quadra, Localidade localidade,
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			MotivoNaoEntregaDocumento motivoNaoEntregaDocumento,
			CobrancaCriterio cobrancaCriterio, 
			CobrancaAcao cobrancaAcao,
			BigDecimal valorAcrescimos) {
		super();
		this.id = id;
		this.numeroSequenciaDocumento = numeroSequenciaDocumento;
		this.emissao = emissao;
		this.valorDesconto = valorDesconto;
		this.numeroQuadra = numeroQuadra;
		this.valorDocumento = valorDocumento;
		this.valorTaxa = valorTaxa;
		this.codigoSetorComercial = codigoSetorComercial;
		this.ultimaAlteracao = ultimaAlteracao;
		this.documentoEmissaoForma = documentoEmissaoForma;
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
		this.imovel = imovel;
		this.empresa = empresa;
		this.documentoTipo = documentoTipo;
		this.imovelPerfil = imovelPerfil;
		this.quadra = quadra;
		this.localidade = localidade;
		this.cobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma;
		this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
		this.cobrancaCriterio = cobrancaCriterio;
		this.cobrancaAcao = cobrancaAcao;
		this.valorAcrescimos = valorAcrescimos;
	}

	public CobrancaDocumento(
			int numeroSequenciaDocumento,
			Date emissao,
			BigDecimal valorDesconto,
			Integer numeroQuadra,
			BigDecimal valorDocumento,
			BigDecimal valorTaxa,
			Integer codigoSetorComercial,
			Date ultimaAlteracao,
			DocumentoEmissaoForma documentoEmissaoForma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			Imovel imovel,
			Empresa empresa,
			DocumentoTipo documentoTipo,
			ImovelPerfil imovelPerfil,
			Quadra quadra,
			Localidade localidade,
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			MotivoNaoEntregaDocumento motivoNaoEntregaDocumento,
			CobrancaCriterio cobrancaCriterio) {
		this.numeroSequenciaDocumento = numeroSequenciaDocumento;
		this.emissao = emissao;
		this.valorDesconto = valorDesconto;
		this.numeroQuadra = numeroQuadra;
		this.valorDocumento = valorDocumento;
		this.valorTaxa = valorTaxa;
		this.codigoSetorComercial = codigoSetorComercial;
		this.ultimaAlteracao = ultimaAlteracao;
		this.documentoEmissaoForma = documentoEmissaoForma;
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
		this.imovel = imovel;
		this.empresa = empresa;
		this.documentoTipo = documentoTipo;
		this.imovelPerfil = imovelPerfil;
		this.quadra = quadra;
		this.localidade = localidade;
		this.cobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma;
		this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
		this.cobrancaCriterio = cobrancaCriterio;
	}

	public CobrancaDocumento() {
	}

	public CobrancaDocumento(
			int numeroSequenciaDocumento,
			DocumentoEmissaoForma documentoEmissaoForma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			Imovel imovel,
			Empresa empresa,
			DocumentoTipo documentoTipo,
			ImovelPerfil imovelPerfil,
			Quadra quadra,
			Localidade localidade,
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			MotivoNaoEntregaDocumento motivoNaoEntregaDocumento) {
		this.numeroSequenciaDocumento = numeroSequenciaDocumento;
		this.documentoEmissaoForma = documentoEmissaoForma;
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
		this.imovel = imovel;
		this.empresa = empresa;
		this.documentoTipo = documentoTipo;
		this.imovelPerfil = imovelPerfil;
		this.quadra = quadra;
		this.localidade = localidade;
		this.cobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma;
		this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNumeroSequenciaDocumento() {
		return this.numeroSequenciaDocumento;
	}

	public void setNumeroSequenciaDocumento(int numeroSequenciaDocumento) {
		this.numeroSequenciaDocumento = numeroSequenciaDocumento;
	}

	public Date getEmissao() {
		return this.emissao;
	}

	public void setEmissao(Date emissao) {
		this.emissao = emissao;
	}

	public BigDecimal getValorDesconto() {
		return this.valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Integer getNumeroQuadra() {
		return this.numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public BigDecimal getValorDocumento() {
		return this.valorDocumento;
	}

	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	public BigDecimal getValorTaxa() {
		return this.valorTaxa;
	}

	public void setValorTaxa(BigDecimal valorTaxa) {
		this.valorTaxa = valorTaxa;
	}

	public Integer getCodigoSetorComercial() {
		return this.codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public DocumentoEmissaoForma getDocumentoEmissaoForma() {
		return this.documentoEmissaoForma;
	}

	public void setDocumentoEmissaoForma(
			DocumentoEmissaoForma documentoEmissaoForma) {
		this.documentoEmissaoForma = documentoEmissaoForma;
	}

	public CobrancaAcaoAtividadeComando getCobrancaAcaoAtividadeComando() {
		return this.cobrancaAcaoAtividadeComando;
	}

	public void setCobrancaAcaoAtividadeComando(CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
	}

	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public DocumentoTipo getDocumentoTipo() {
		return this.documentoTipo;
	}

	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public ImovelPerfil getImovelPerfil() {
		return this.imovelPerfil;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public Quadra getQuadra() {
		return this.quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public CobrancaAcaoAtividadeCronograma getCobrancaAcaoAtividadeCronograma() {
		return this.cobrancaAcaoAtividadeCronograma;
	}

	public void setCobrancaAcaoAtividadeCronograma(CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma) {
		this.cobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma;
	}

	public MotivoNaoEntregaDocumento getMotivoNaoEntregaDocumento() {
		return this.motivoNaoEntregaDocumento;
	}

	public void setMotivoNaoEntregaDocumento(
			MotivoNaoEntregaDocumento motivoNaoEntregaDocumento) {
		this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
	}

	public CobrancaCriterio getCobrancaCriterio() {
		return cobrancaCriterio;
	}

	public void setCobrancaCriterio(CobrancaCriterio cobrancaCriterio) {
		this.cobrancaCriterio = cobrancaCriterio;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public CobrancaAcao getCobrancaAcao() {
		return cobrancaAcao;
	}

	public void setCobrancaAcao(CobrancaAcao cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}

	public BigDecimal getValorAcrescimos() {
		return valorAcrescimos;
	}

	public void setValorAcrescimos(BigDecimal valorAcrescimos) {
		this.valorAcrescimos = valorAcrescimos;
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

	public Short getIndicadorAntesApos() {
		return indicadorAntesApos;
	}

	public void setIndicadorAntesApos(Short indicadorAntesApos) {
		this.indicadorAntesApos = indicadorAntesApos;
	}

	public Short getIndicadorLimite() {
		return indicadorLimite;
	}

	public void setIndicadorLimite(Short indicadorLimite) {
		this.indicadorLimite = indicadorLimite;
	}

	public Integer getSequencialImpressao() {
		return sequencialImpressao;
	}

	public void setSequencialImpressao(Integer sequencialImpressao) {
		this.sequencialImpressao = sequencialImpressao;
	}

	public AtendimentoMotivoEncerramento getMotivoEncerramento() {
		return motivoEncerramento;
	}

	public void setMotivoEncerramento(
			AtendimentoMotivoEncerramento motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}

	public ResolucaoDiretoria getResolucaoDiretoria() {
		return resolucaoDiretoria;
	}

	public void setResolucaoDiretoria(ResolucaoDiretoria resolucaoDiretoria) {
		this.resolucaoDiretoria = resolucaoDiretoria;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public Date getDataEmissaoPredecessor() {
		return dataEmissaoPredecessor;
	}

	public void setDataEmissaoPredecessor(Date dataEmissaoPredecessor) {
		this.dataEmissaoPredecessor = dataEmissaoPredecessor;
	}

	public String getNumeroDocumentoFatura() {
		return numeroDocumentoFatura;
	}

	public void setNumeroDocumentoFatura(String numeroDocumentoFatura) {
		this.numeroDocumentoFatura = numeroDocumentoFatura;
	}

	public BigDecimal getValorImpostos() {
		return valorImpostos;
	}

	public void setValorImpostos(BigDecimal valorImpostos) {
		this.valorImpostos = valorImpostos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
