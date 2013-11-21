package gcom.arrecadacao.bean;

import gcom.arrecadacao.ArrecadacaoDadosDiarios;
import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.DevolucaoDadosDiarios;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.DocumentoTipo;
import gcom.micromedicao.Rota;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Representa um item da tabela de valores de dados diarios acumuladas
 * (campos identificadores -> somatorios e contadores)
 * Somatorios: valorArrecadadoBruto, valorDescontos, valorDevolucoes, valorArrecadadoLiquido
 * Contadores: quantidadeDocumentos, quantidadePagamentos
 * @author Francisco do Nascimento
 * @date   03/09/08
 *
 */
public class ArrecadacaoDadosDiariosItemAcumuladorHelper implements Serializable{

    /**
	 * 
	 */

	public static final int GROUP_BY_DATA = 1;
	
	public static final int GROUP_BY_UNIDADE_NEGOCIO = 2;
	
	public static final int GROUP_BY_ELO = 3;
	
	public static final int GROUP_BY_ARRECADADOR = 4;
	
	public static final int GROUP_BY_ARRECADACAO_FORMA = 5;
	
	public static final int GROUP_BY_DOCUMENTO_AGREGADOR = 6;
	
	public static final int GROUP_BY_DOCUMENTO_TIPO = 7;
	
	public static final int GROUP_BY_CATEGORIA = 8;
	
	public static final int GROUP_BY_ANO_MES = 9;
	
	public static final int GROUP_BY_GERENCIA = 10;
	
	public static final int GROUP_BY_LOCALIDADE = 11;
	
	public static final int GROUP_BY_PERFIL = 12;
	
	private static final long serialVersionUID = 1L;

	/** persistent field */
    private int anoMesReferencia;

    /** nullable persistent field */
    private Integer codigoSetorComercial;

    /** nullable persistent field */
    private Integer numeroQuadra;

    /** persistent field */
    private Short indicadorHidrometro;

    /** persistent field */
    private Date data;

    /** persistent field */
    private GerenciaRegional gerenciaRegional;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private Quadra quadra;

    /** persistent field */
    private DocumentoTipo documentoTipoAgregador;

    /** persistent field */
    private DocumentoTipo documentoTipo;

    /** persistent field */
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

    /** persistent field */
    private ImovelPerfil imovelPerfil;

    /** persistent field */
    private Rota rota;

    /** persistent field */
    private Arrecadador arrecadador;

    /** persistent field */
    private ArrecadacaoForma arrecadacaoForma;

    /** persistent field */
    private SetorComercial setorComercial;

    /** persistent field */
    private LigacaoAguaSituacao ligacaoAguaSituacao;

    /** persistent field */
    private UnidadeNegocio unidadeNegocio;

    /** persistent field */
    private EsferaPoder esferaPoder;

    /** persistent field */
    private CobrancaDocumento cobrancaDocumento;

    /** persistent field */
    private Categoria categoria;

    /** persistent field */
    private Integer quantidadeDocumentos = 0;

    /** persistent field */
    private Integer quantidadePagamentos = 0;

    /** persistent field */
    private BigDecimal valorArrecadadoBruto = new BigDecimal("0.0");

    /** persistent field */
    private BigDecimal valorDescontos = new BigDecimal("0.0");

    /** persistent field */
    private BigDecimal valorDevolucoes = new BigDecimal("0.0");

    /** persistent field */
    private BigDecimal valorArrecadadoLiquido = new BigDecimal("0.0");

	public ArrecadacaoDadosDiariosItemAcumuladorHelper(int anoMesReferencia, Integer codigoSetorComercial, Integer numeroQuadra, Short indicadorHidrometro, Date data, GerenciaRegional gerenciaRegional, Localidade localidade, Quadra quadra, DocumentoTipo documentoTipoAgregador, DocumentoTipo documentoTipo, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, ImovelPerfil imovelPerfil, Rota rota, Arrecadador arrecadador, ArrecadacaoForma arrecadacaoForma, SetorComercial setorComercial, LigacaoAguaSituacao ligacaoAguaSituacao, UnidadeNegocio unidadeNegocio, EsferaPoder esferaPoder, CobrancaDocumento cobrancaDocumento, Categoria categoria, int quantidadeDocumentos, Integer quantidadePagamentos, BigDecimal valorArrecadadoBruto, BigDecimal valorDescontos, BigDecimal valorDevolucoes, BigDecimal valorArrecadadoLiquido) {
		super();
		// TODO Auto-generated constructor stub
		this.anoMesReferencia = anoMesReferencia;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.indicadorHidrometro = indicadorHidrometro;
		this.data = data;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.quadra = quadra;
		this.documentoTipoAgregador = documentoTipoAgregador;
		this.documentoTipo = documentoTipo;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.imovelPerfil = imovelPerfil;
		this.rota = rota;
		this.arrecadador = arrecadador;
		this.arrecadacaoForma = arrecadacaoForma;
		this.setorComercial = setorComercial;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.unidadeNegocio = unidadeNegocio;
		this.esferaPoder = esferaPoder;
		this.cobrancaDocumento = cobrancaDocumento;
		this.categoria = categoria;
		this.quantidadeDocumentos = quantidadeDocumentos;
		this.quantidadePagamentos = quantidadePagamentos;
		this.valorArrecadadoBruto = valorArrecadadoBruto;
		this.valorDescontos = valorDescontos;
		this.valorDevolucoes = valorDevolucoes;
		this.valorArrecadadoLiquido = valorArrecadadoLiquido;
	}

	public ArrecadacaoDadosDiariosItemAcumuladorHelper(int anoMesReferencia, Integer codigoSetorComercial, Integer numeroQuadra, Short indicadorHidrometro, Date data, GerenciaRegional gerenciaRegional, Localidade localidade, Quadra quadra, DocumentoTipo documentoTipoAgregador, DocumentoTipo documentoTipo, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, ImovelPerfil imovelPerfil, Rota rota, Arrecadador arrecadador, ArrecadacaoForma arrecadacaoForma, SetorComercial setorComercial, LigacaoAguaSituacao ligacaoAguaSituacao, UnidadeNegocio unidadeNegocio, EsferaPoder esferaPoder, CobrancaDocumento cobrancaDocumento, Categoria categoria) {
		super();
		// TODO Auto-generated constructor stub
		this.anoMesReferencia = anoMesReferencia;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.indicadorHidrometro = indicadorHidrometro;
		this.data = data;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.quadra = quadra;
		this.documentoTipoAgregador = documentoTipoAgregador;
		this.documentoTipo = documentoTipo;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.imovelPerfil = imovelPerfil;
		this.rota = rota;
		this.arrecadador = arrecadador;
		this.arrecadacaoForma = arrecadacaoForma;
		this.setorComercial = setorComercial;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.unidadeNegocio = unidadeNegocio;
		this.esferaPoder = esferaPoder;
		this.cobrancaDocumento = cobrancaDocumento;
		this.categoria = categoria;
	}

	public ArrecadacaoDadosDiariosItemAcumuladorHelper() {
		// TODO Auto-generated constructor stub
	}

	public int getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(int anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public ArrecadacaoForma getArrecadacaoForma() {
		return arrecadacaoForma;
	}

	public void setArrecadacaoForma(ArrecadacaoForma arrecadacaoForma) {
		this.arrecadacaoForma = arrecadacaoForma;
	}

	public Arrecadador getArrecadador() {
		return arrecadador;
	}

	public void setArrecadador(Arrecadador arrecadador) {
		this.arrecadador = arrecadador;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public CobrancaDocumento getCobrancaDocumento() {
		return cobrancaDocumento;
	}

	public void setCobrancaDocumento(CobrancaDocumento cobrancaDocumento) {
		this.cobrancaDocumento = cobrancaDocumento;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}

	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public DocumentoTipo getDocumentoTipoAgregador() {
		return documentoTipoAgregador;
	}

	public void setDocumentoTipoAgregador(DocumentoTipo documentoTipoAgregador) {
		this.documentoTipoAgregador = documentoTipoAgregador;
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

	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public Short getIndicadorHidrometro() {
		return indicadorHidrometro;
	}

	public void setIndicadorHidrometro(Short indicadorHidrometro) {
		this.indicadorHidrometro = indicadorHidrometro;
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

	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Quadra getQuadra() {
		return quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public int getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}

	public void addQuantidadeDocumentos(Integer quantidadeDocumentos) {
		this.quantidadeDocumentos = this.quantidadeDocumentos + quantidadeDocumentos;
	}

	public Integer getQuantidadePagamentos() {
		return quantidadePagamentos;
	}

	public void addQuantidadePagamentos(Integer quantidadePagamentos) {
		this.quantidadePagamentos = this.quantidadePagamentos + quantidadePagamentos;
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public BigDecimal getValorArrecadadoBruto() {
		return valorArrecadadoBruto;
	}

	public void addValorArrecadadoBruto(BigDecimal valorArrecadadoBruto) {
		this.valorArrecadadoBruto = this.valorArrecadadoBruto.add(valorArrecadadoBruto);
	}

	public BigDecimal getValorArrecadadoLiquido() {
		return valorArrecadadoLiquido;
	}

	public void setValorArrecadadoLiquido(BigDecimal valorArrecadadoLiquido) {
		this.valorArrecadadoLiquido = valorArrecadadoLiquido;
	}

	public BigDecimal getValorDescontos() {
		return valorDescontos;
	}
	
	public void setValorDescontos(BigDecimal desc){
		this.valorDescontos = desc;
	}

	public void addValorDescontos(BigDecimal valorDescontos) {
		this.valorDescontos = this.valorDescontos.add(valorDescontos);
	}

	public BigDecimal getValorDevolucoes() {
		return valorDevolucoes;
	}

	public void addValorDevolucoes(BigDecimal valorDevolucoes) {
		this.valorDevolucoes = this.valorDevolucoes.add(valorDevolucoes);
	}
        
	
	public boolean equalsAgrupamento(ArrecadacaoDadosDiarios arrecadacaoDadosDiarios, 
			DevolucaoDadosDiarios devolucaoDadosDiarios, int tipoFiltro){
		boolean retorno = false;
		switch (tipoFiltro) {
		case GROUP_BY_DATA:
			Date dataPagamentoOuDevolucao = null;
			if (arrecadacaoDadosDiarios != null){
				dataPagamentoOuDevolucao = arrecadacaoDadosDiarios.getDataPagamento();
			} else if (devolucaoDadosDiarios != null){
				dataPagamentoOuDevolucao = devolucaoDadosDiarios.getDataDevolucao();
			}		
			retorno = 
				this.getData().compareTo(dataPagamentoOuDevolucao) == 0;
			break;
		case GROUP_BY_UNIDADE_NEGOCIO:
			Integer idUnidadeNegocio = null;
			if (arrecadacaoDadosDiarios != null){
				idUnidadeNegocio = arrecadacaoDadosDiarios.getUnidadeNegocio().getId();
			} else if (devolucaoDadosDiarios != null){
				idUnidadeNegocio = devolucaoDadosDiarios.getUnidadeNegocio().getId();
			}
			retorno =
			   this.unidadeNegocio != null && this.unidadeNegocio.getId().intValue() == 
				   idUnidadeNegocio.intValue();
			break;
		case GROUP_BY_ELO:
			Integer idLocalidadeElo = null;
			if (arrecadacaoDadosDiarios != null){
				idLocalidadeElo = arrecadacaoDadosDiarios.getLocalidade().getLocalidade().getId();
			} else if (devolucaoDadosDiarios != null){
				idLocalidadeElo = devolucaoDadosDiarios.getLocalidade().getLocalidade().getId();
			}
			Integer idGerenciaRegional = null;
			if (arrecadacaoDadosDiarios != null){
				idGerenciaRegional = arrecadacaoDadosDiarios.getGerenciaRegional().getId();
			} else if (devolucaoDadosDiarios != null){
				idGerenciaRegional = devolucaoDadosDiarios.getGerenciaRegional().getId();
			}
			retorno =
			   this.localidade != null && 
			   this.localidade.getLocalidade().getGerenciaRegional().getId().intValue()
			   	== idGerenciaRegional.intValue() && 
			   this.localidade.getLocalidade().getId().intValue() == idLocalidadeElo.intValue();
			break;
		case GROUP_BY_LOCALIDADE:
			Integer idLocalidade = null;
			if (arrecadacaoDadosDiarios != null){
				idLocalidade = arrecadacaoDadosDiarios.getLocalidade().getId();
			} else if (devolucaoDadosDiarios != null){
				idLocalidade = devolucaoDadosDiarios.getLocalidade().getId();
			}
			retorno =
			   this.localidade != null && 
			   this.localidade.getId().intValue() == idLocalidade.intValue();
			break;
		case GROUP_BY_ARRECADADOR:
			Integer idArrecadador = null;
			if (arrecadacaoDadosDiarios != null){
				idArrecadador = arrecadacaoDadosDiarios.getArrecadador().getId();
			} else if (devolucaoDadosDiarios != null){
				idArrecadador = devolucaoDadosDiarios.getArrecadador().getId();
			}
			retorno =
			   this.arrecadador != null && this.arrecadador.getId().intValue() == idArrecadador.intValue();
			break;
		case GROUP_BY_ARRECADACAO_FORMA:
			Integer idArrecadacaoForma = new Integer(0);
			if (arrecadacaoDadosDiarios != null && arrecadacaoDadosDiarios.getArrecadacaoForma() != null){
				idArrecadacaoForma = arrecadacaoDadosDiarios.getArrecadacaoForma().getId();	
			} else if (devolucaoDadosDiarios != null && devolucaoDadosDiarios.getArrecadacaoForma() != null){
				
				idArrecadacaoForma = devolucaoDadosDiarios.getArrecadacaoForma().getId();
			}
			retorno =
			   this.arrecadacaoForma != null && this.arrecadacaoForma.getId().intValue()
			   	== idArrecadacaoForma.intValue();
			break;			
		case GROUP_BY_DOCUMENTO_AGREGADOR:
			Integer idTipoDocumentoAgregador = new Integer(0);
			if (arrecadacaoDadosDiarios != null && arrecadacaoDadosDiarios.getDocumentoTipoAgregador() != null){
				idTipoDocumentoAgregador = arrecadacaoDadosDiarios.getDocumentoTipoAgregador().getId();	
			} else if (devolucaoDadosDiarios != null && devolucaoDadosDiarios.getDocumentoTipoAgregador() != null){				
				idTipoDocumentoAgregador = devolucaoDadosDiarios.getDocumentoTipoAgregador().getId();
			}
			retorno =
			   this.documentoTipoAgregador != null && this.documentoTipoAgregador.getId().intValue() 
			   	== idTipoDocumentoAgregador.intValue();
			break;			
		case GROUP_BY_DOCUMENTO_TIPO:
			Integer idTipoDocumento = new Integer(0);
			if (arrecadacaoDadosDiarios != null && arrecadacaoDadosDiarios.getDocumentoTipo() != null){
				idTipoDocumento = arrecadacaoDadosDiarios.getDocumentoTipo().getId();	
			} else if (devolucaoDadosDiarios != null && devolucaoDadosDiarios.getDocumentoTipo() != null){				
				idTipoDocumento = devolucaoDadosDiarios.getDocumentoTipo().getId();
			}
			retorno =
			   this.documentoTipo != null && this.documentoTipo.getId().intValue() == idTipoDocumento.intValue();
			break;			
		case GROUP_BY_CATEGORIA:
			Integer idCategoria = new Integer(0);
			if (arrecadacaoDadosDiarios != null && arrecadacaoDadosDiarios.getCategoria() != null){
				idCategoria = arrecadacaoDadosDiarios.getCategoria().getId();	
			} else if (devolucaoDadosDiarios != null && devolucaoDadosDiarios.getCategoria() != null){				
				idCategoria = devolucaoDadosDiarios.getCategoria().getId();
			}
			retorno =
			   this.categoria != null && this.categoria.getId().intValue() == idCategoria.intValue();
			break;
		case GROUP_BY_ANO_MES:
			Integer anomes = new Integer(0);
			if (arrecadacaoDadosDiarios != null && arrecadacaoDadosDiarios.getAnoMesReferenciaArrecadacao() != null){
				anomes = arrecadacaoDadosDiarios.getAnoMesReferenciaArrecadacao();	
			} else if (devolucaoDadosDiarios != null){				
				anomes = devolucaoDadosDiarios.getAnoMesReferencia();
			}
			retorno = this.anoMesReferencia == anomes.intValue();
			break;				
		case GROUP_BY_GERENCIA:
			idGerenciaRegional = null;
			if (arrecadacaoDadosDiarios != null){
				idGerenciaRegional = arrecadacaoDadosDiarios.getGerenciaRegional().getId();
			} else if (devolucaoDadosDiarios != null){
				idGerenciaRegional = devolucaoDadosDiarios.getGerenciaRegional().getId();
			}
			retorno =
			   this.gerenciaRegional != null && this.gerenciaRegional.getId().intValue()
			   		== idGerenciaRegional.intValue();
			break;
		case GROUP_BY_PERFIL:
			Integer idPerfil = null;
			if (arrecadacaoDadosDiarios != null && arrecadacaoDadosDiarios.getImovelPerfil() != null){
				idPerfil = arrecadacaoDadosDiarios.getImovelPerfil().getId();
			} else if (devolucaoDadosDiarios != null && devolucaoDadosDiarios.getImovelPerfil() != null){
				idPerfil = devolucaoDadosDiarios.getImovelPerfil().getId();
			}
			retorno =
			   this.imovelPerfil != null && this.imovelPerfil.getId().intValue() == idPerfil.intValue();
			break;
		default:

		}
		return retorno;
	}
}
