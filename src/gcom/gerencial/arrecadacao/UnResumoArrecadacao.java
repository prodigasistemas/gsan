package gcom.gerencial.arrecadacao;

import gcom.gerencial.arrecadacao.pagamento.GEpocaPagamento;
import gcom.gerencial.arrecadacao.pagamento.GPagamentoSituacao;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.cadastro.cliente.GClienteTipo;
import gcom.gerencial.cadastro.cliente.GEsferaPoder;
import gcom.gerencial.cadastro.imovel.GCategoria;
import gcom.gerencial.cadastro.imovel.GImovelPerfil;
import gcom.gerencial.cadastro.imovel.GSubcategoria;
import gcom.gerencial.cadastro.localidade.GGerenciaRegional;
import gcom.gerencial.cadastro.localidade.GLocalidade;
import gcom.gerencial.cadastro.localidade.GQuadra;
import gcom.gerencial.cadastro.localidade.GSetorComercial;
import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;
import gcom.gerencial.cobranca.GDocumentoTipo;
import gcom.gerencial.micromedicao.GRota;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoArrecadacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** persistent field */
    private int codigoSetorComercial;

    /** persistent field */
    private int numeroQuadra;

    /** persistent field */
    private int quantidadeContas;

    /** persistent field */
    private short indicadorRecebidasNomes;

    /** persistent field */
    private BigDecimal valorAgua;

    /** persistent field */
    private BigDecimal valorEsgoto;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private BigDecimal valorNaoIdentificado;

    /** nullable persistent field */
    private Integer creditoOrigemIdCredito;

    /** nullable persistent field */
    private BigDecimal valorDocumentosRecebidosCredito;

    /** nullable persistent field */
    private Integer lancamentoItemIdCredito;

    /** nullable persistent field */
    private BigDecimal valorDocumentosRecebidosOutros;

    /** nullable persistent field */
    private Integer financiamentoTipoIdOutros;

    /** nullable persistent field */
    private Integer lancamentoItemIdOutros;

    /** nullable persistent field */
    private BigDecimal valorImpostos;

    /** persistent field */
    private gcom.gerencial.arrecadacao.GArrecadacaoForma gerArrecadacaoForma;

    /** persistent field */
    private gcom.gerencial.arrecadacao.GArrecadador gerArrecadador;

    /** persistent field */
    private GGerenciaRegional gerGerenciaRegional;

    /** persistent field */
    private GSetorComercial gerSetorComercial;

    /** persistent field */
    private GSubcategoria gerSubcategoria;

    /** persistent field */
    private GDocumentoTipo gerDocumentoTipo;

    /** persistent field */
    private GLigacaoAguaPerfil gerLigacaoAguaPerfil;

    /** persistent field */
    private GPagamentoSituacao gerPagamentoSituacao;

    /** persistent field */
    private GEpocaPagamento gerEpocaPagamento;

    /** persistent field */
    private GEsferaPoder gerEsferaPoder;

    /** persistent field */
    private GClienteTipo gerClienteTipo;

    /** persistent field */
    private GLigacaoAguaSituacao gerLigacaoAguaSituacao;

    /** persistent field */
    private GUnidadeNegocio gerUnidadeNegocio;

    /** persistent field */
    private GLocalidade gerLocalidade;

    /** persistent field */
    private GLocalidade gerLocalidadeElo;

    /** persistent field */
    private GCategoria gerCategoria;

    /** persistent field */
    private GImovelPerfil gerImovelPerfil;

    /** persistent field */
    private GRota gerRota;

    /** persistent field */
    private GQuadra gerQuadra;

    /** persistent field */
    private GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao;

    /** persistent field */
    private GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;
    
    /** persistent field */
    private short indicadorHidrometro;
    
    /** persistent field */
    private Integer anoMesReferenciaDocumento;
    
    // Dados da Devolucao
    private BigDecimal valorDevolucoesClassificadas = new BigDecimal(0);
	private BigDecimal valorDevolucoesNaoClassificadas = new BigDecimal(0);
	private GDevolucaoSituacao gerDevolucaoSituacao = null;
	
	private int quantidadePagamentos;
    
	
	/** full constructor */
    public UnResumoArrecadacao(int anoMesReferencia, int codigoSetorComercial, int numeroQuadra, int quantidadeContas, short indicadorRecebidasNomes, BigDecimal valorAgua, BigDecimal valorEsgoto, Date ultimaAlteracao, BigDecimal valorNaoIdentificado, Integer creditoOrigemIdCredito, BigDecimal valorDocumentosRecebidosCredito, Integer lancamentoItemIdCredito, BigDecimal valorDocumentosRecebidosOutros, Integer financiamentoTipoIdOutros, Integer lancamentoItemIdOutros, BigDecimal valorImpostos, gcom.gerencial.arrecadacao.GArrecadacaoForma gerArrecadacaoForma, gcom.gerencial.arrecadacao.GArrecadador gerArrecadador, GGerenciaRegional gerGerenciaRegional, GSetorComercial gerSetorComercial, GSubcategoria gerSubcategoria, GDocumentoTipo gerDocumentoTipo, GLigacaoAguaPerfil gerLigacaoAguaPerfil, GPagamentoSituacao gerPagamentoSituacao, GEpocaPagamento gerEpocaPagamento, GEsferaPoder gerEsferaPoder, GClienteTipo gerClienteTipo, GLigacaoAguaSituacao gerLigacaoAguaSituacao, GUnidadeNegocio gerUnidadeNegocio, GLocalidade gerLocalidade, GLocalidade gerLocalidadeElo, GCategoria gerCategoria, GImovelPerfil gerImovelPerfil, GRota gerRota, GQuadra gerQuadra, GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao, GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        //this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.quantidadeContas = quantidadeContas;
        this.indicadorRecebidasNomes = indicadorRecebidasNomes;
        this.valorAgua = valorAgua;
        this.valorEsgoto = valorEsgoto;
        this.ultimaAlteracao = ultimaAlteracao;
        this.valorNaoIdentificado = valorNaoIdentificado;
        this.creditoOrigemIdCredito = creditoOrigemIdCredito;
        this.valorDocumentosRecebidosCredito = valorDocumentosRecebidosCredito;
        this.lancamentoItemIdCredito = lancamentoItemIdCredito;
        this.valorDocumentosRecebidosOutros = valorDocumentosRecebidosOutros;
        this.financiamentoTipoIdOutros = financiamentoTipoIdOutros;
        this.lancamentoItemIdOutros = lancamentoItemIdOutros;
        this.valorImpostos = valorImpostos;
        this.gerArrecadacaoForma = gerArrecadacaoForma;
        this.gerArrecadador = gerArrecadador;
        this.gerGerenciaRegional = gerGerenciaRegional;
        this.gerSetorComercial = gerSetorComercial;
        this.gerSubcategoria = gerSubcategoria;
        this.gerDocumentoTipo = gerDocumentoTipo;
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
        this.gerPagamentoSituacao = gerPagamentoSituacao;
        this.gerEpocaPagamento = gerEpocaPagamento;
        this.gerEsferaPoder = gerEsferaPoder;
        this.gerClienteTipo = gerClienteTipo;
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
        this.gerUnidadeNegocio = gerUnidadeNegocio;
        this.gerLocalidade = gerLocalidade;
        this.gerLocalidadeElo = gerLocalidadeElo;
        this.gerCategoria = gerCategoria;
        this.gerImovelPerfil = gerImovelPerfil;
        this.gerRota = gerRota;
        this.gerQuadra = gerQuadra;
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    }

    /** default constructor */
    public UnResumoArrecadacao() {
    }

    /** minimal constructor */
    public UnResumoArrecadacao(int anoMesReferencia, int codigoSetorComercial, int numeroQuadra, int quantidadeContas, short indicadorRecebidasNomes, BigDecimal valorAgua, BigDecimal valorEsgoto, Date ultimaAlteracao, BigDecimal valorNaoIdentificado, gcom.gerencial.arrecadacao.GArrecadacaoForma gerArrecadacaoForma, gcom.gerencial.arrecadacao.GArrecadador gerArrecadador, GGerenciaRegional gerGerenciaRegional, GSetorComercial gerSetorComercial, GSubcategoria gerSubcategoria, GDocumentoTipo gerDocumentoTipo, GLigacaoAguaPerfil gerLigacaoAguaPerfil, GPagamentoSituacao gerPagamentoSituacao, GEpocaPagamento gerEpocaPagamento, GEsferaPoder gerEsferaPoder, GClienteTipo gerClienteTipo, GLigacaoAguaSituacao gerLigacaoAguaSituacao, GUnidadeNegocio gerUnidadeNegocio, GLocalidade gerLocalidade, GLocalidade gerLocalidadeElo, GCategoria gerCategoria, GImovelPerfil gerImovelPerfil, GRota gerRota, GQuadra gerQuadra, GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao, GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        //this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.quantidadeContas = quantidadeContas;
        this.indicadorRecebidasNomes = indicadorRecebidasNomes;
        this.valorAgua = valorAgua;
        this.valorEsgoto = valorEsgoto;
        this.ultimaAlteracao = ultimaAlteracao;
        this.valorNaoIdentificado = valorNaoIdentificado;
        this.gerArrecadacaoForma = gerArrecadacaoForma;
        this.gerArrecadador = gerArrecadador;
        this.gerGerenciaRegional = gerGerenciaRegional;
        this.gerSetorComercial = gerSetorComercial;
        this.gerSubcategoria = gerSubcategoria;
        this.gerDocumentoTipo = gerDocumentoTipo;
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
        this.gerPagamentoSituacao = gerPagamentoSituacao;
        this.gerEpocaPagamento = gerEpocaPagamento;
        this.gerEsferaPoder = gerEsferaPoder;
        this.gerClienteTipo = gerClienteTipo;
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
        this.gerUnidadeNegocio = gerUnidadeNegocio;
        this.gerLocalidade = gerLocalidade;
        this.gerLocalidadeElo = gerLocalidadeElo;
        this.gerCategoria = gerCategoria;
        this.gerImovelPerfil = gerImovelPerfil;
        this.gerRota = gerRota;
        this.gerQuadra = gerQuadra;
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    }
    
    /** minimal constructor 2 */
    public UnResumoArrecadacao(
    		int anoMesReferencia, 
    		int codigoSetorComercial, 
    		int numeroQuadra, 
    		int quantidadeContas, 
    		short indicadorRecebidasNomes, 
    		BigDecimal valorAgua, 
    		BigDecimal valorEsgoto, 
    		Date ultimaAlteracao, 
    		BigDecimal valorNaoIdentificado, 
    		GSubcategoria gerSubcategoria,
    		GClienteTipo gerClienteTipo,
    		GLigacaoAguaSituacao gerLigacaoAguaSituacao,
    		GUnidadeNegocio gerUnidadeNegocio,
    		GLocalidade gerLocalidade,
    		GLocalidade gerLocalidadeElo,
    		GQuadra gerQuadra,
    		GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao,
    		GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil,
    		GGerenciaRegional gerGerenciaRegional,
    		GSetorComercial gerSetorComercial,
    		GDocumentoTipo gerDocumentoTipo,
    		GPagamentoSituacao gerPagamentoSituacao,
    		GLigacaoAguaPerfil gerLigacaoAguaPerfil,
    		GEpocaPagamento gerEpocaPagamento,
    		GEsferaPoder gerEsferaPoder,
    		GCategoria gerCategoria,
    		GImovelPerfil gerImovelPerfil,
    		GRota gerRota,
    		BigDecimal valorImpostos,
    		short indicadorHidrometro,
    		Integer anoMesReferenciaDocumento) {

        this.anoMesReferencia = anoMesReferencia;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.quantidadeContas = quantidadeContas;
        this.indicadorRecebidasNomes = indicadorRecebidasNomes;
        this.valorAgua = valorAgua;
        this.valorEsgoto = valorEsgoto;
        this.ultimaAlteracao = ultimaAlteracao;
        this.valorNaoIdentificado = valorNaoIdentificado;
        this.gerGerenciaRegional = gerGerenciaRegional;
        this.gerSetorComercial = gerSetorComercial;
        this.gerSubcategoria = gerSubcategoria;
        this.gerDocumentoTipo = gerDocumentoTipo;
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
        this.gerPagamentoSituacao = gerPagamentoSituacao;
        this.gerEpocaPagamento = gerEpocaPagamento;
        this.gerEsferaPoder = gerEsferaPoder;
        this.gerClienteTipo = gerClienteTipo;
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
        this.gerUnidadeNegocio = gerUnidadeNegocio;
        this.gerLocalidade = gerLocalidade;
        this.gerLocalidadeElo = gerLocalidadeElo;
        this.gerCategoria = gerCategoria;
        this.gerImovelPerfil = gerImovelPerfil;
        this.gerRota = gerRota;
        this.gerQuadra = gerQuadra;
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
        this.valorImpostos = valorImpostos;
        this.indicadorHidrometro = indicadorHidrometro;
        this.anoMesReferenciaDocumento = anoMesReferenciaDocumento;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(int anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public int getCodigoSetorComercial() {
        return this.codigoSetorComercial;
    }

    public void setCodigoSetorComercial(int codigoSetorComercial) {
        this.codigoSetorComercial = codigoSetorComercial;
    }

    public int getNumeroQuadra() {
        return this.numeroQuadra;
    }

    public void setNumeroQuadra(int numeroQuadra) {
        this.numeroQuadra = numeroQuadra;
    }

    public int getQuantidadeContas() {
        return this.quantidadeContas;
    }

    public void setQuantidadeContas(int quantidadeContas) {
        this.quantidadeContas = quantidadeContas;
    }

    public short getIndicadorRecebidasNomes() {
        return this.indicadorRecebidasNomes;
    }

    public void setIndicadorRecebidasNomes(short indicadorRecebidasNomes) {
        this.indicadorRecebidasNomes = indicadorRecebidasNomes;
    }

    public BigDecimal getValorAgua() {
        return this.valorAgua;
    }

    public void setValorAgua(BigDecimal valorAgua) {
        this.valorAgua = valorAgua;
    }

    public BigDecimal getValorEsgoto() {
        return this.valorEsgoto;
    }

    public void setValorEsgoto(BigDecimal valorEsgoto) {
        this.valorEsgoto = valorEsgoto;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public BigDecimal getValorNaoIdentificado() {
        return this.valorNaoIdentificado;
    }

    public void setValorNaoIdentificado(BigDecimal valorNaoIdentificado) {
        this.valorNaoIdentificado = valorNaoIdentificado;
    }

    public Integer getCreditoOrigemIdCredito() {
        return this.creditoOrigemIdCredito;
    }

    public void setCreditoOrigemIdCredito(Integer creditoOrigemIdCredito) {
        this.creditoOrigemIdCredito = creditoOrigemIdCredito;
    }

    public BigDecimal getValorDocumentosRecebidosCredito() {
        return this.valorDocumentosRecebidosCredito;
    }

    public void setValorDocumentosRecebidosCredito(BigDecimal valorDocumentosRecebidosCredito) {
        this.valorDocumentosRecebidosCredito = valorDocumentosRecebidosCredito;
    }

    public Integer getLancamentoItemIdCredito() {
        return this.lancamentoItemIdCredito;
    }

    public void setLancamentoItemIdCredito(Integer lancamentoItemIdCredito) {
        this.lancamentoItemIdCredito = lancamentoItemIdCredito;
    }

    public BigDecimal getValorDocumentosRecebidosOutros() {
        return this.valorDocumentosRecebidosOutros;
    }

    public void setValorDocumentosRecebidosOutros(BigDecimal valorDocumentosRecebidosOutros) {
        this.valorDocumentosRecebidosOutros = valorDocumentosRecebidosOutros;
    }

    public Integer getFinanciamentoTipoIdOutros() {
        return this.financiamentoTipoIdOutros;
    }

    public void setFinanciamentoTipoIdOutros(Integer financiamentoTipoIdOutros) {
        this.financiamentoTipoIdOutros = financiamentoTipoIdOutros;
    }

    public Integer getLancamentoItemIdOutros() {
        return this.lancamentoItemIdOutros;
    }

    public void setLancamentoItemIdOutros(Integer lancamentoItemIdOutros) {
        this.lancamentoItemIdOutros = lancamentoItemIdOutros;
    }

    public BigDecimal getValorImpostos() {
        return this.valorImpostos;
    }

    public void setValorImpostos(BigDecimal valorImpostos) {
        this.valorImpostos = valorImpostos;
    }

    public gcom.gerencial.arrecadacao.GArrecadacaoForma getGerArrecadacaoForma() {
        return this.gerArrecadacaoForma;
    }

    public void setGerArrecadacaoForma(gcom.gerencial.arrecadacao.GArrecadacaoForma gerArrecadacaoForma) {
        this.gerArrecadacaoForma = gerArrecadacaoForma;
    }

    public gcom.gerencial.arrecadacao.GArrecadador getGerArrecadador() {
        return this.gerArrecadador;
    }

    public void setGerArrecadador(gcom.gerencial.arrecadacao.GArrecadador gerArrecadador) {
        this.gerArrecadador = gerArrecadador;
    }

    public GGerenciaRegional getGerGerenciaRegional() {
        return this.gerGerenciaRegional;
    }

    public void setGerGerenciaRegional(GGerenciaRegional gerGerenciaRegional) {
        this.gerGerenciaRegional = gerGerenciaRegional;
    }

    public GSetorComercial getGerSetorComercial() {
        return this.gerSetorComercial;
    }

    public void setGerSetorComercial(GSetorComercial gerSetorComercial) {
        this.gerSetorComercial = gerSetorComercial;
    }

    public GSubcategoria getGerSubcategoria() {
        return this.gerSubcategoria;
    }

    public void setGerSubcategoria(GSubcategoria gerSubcategoria) {
        this.gerSubcategoria = gerSubcategoria;
    }

    public GDocumentoTipo getGerDocumentoTipo() {
        return this.gerDocumentoTipo;
    }

    public void setGerDocumentoTipo(GDocumentoTipo gerDocumentoTipo) {
        this.gerDocumentoTipo = gerDocumentoTipo;
    }

    public GLigacaoAguaPerfil getGerLigacaoAguaPerfil() {
        return this.gerLigacaoAguaPerfil;
    }

    public void setGerLigacaoAguaPerfil(GLigacaoAguaPerfil gerLigacaoAguaPerfil) {
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
    }

    public GPagamentoSituacao getGerPagamentoSituacao() {
        return this.gerPagamentoSituacao;
    }

    public void setGerPagamentoSituacao(GPagamentoSituacao gerPagamentoSituacao) {
        this.gerPagamentoSituacao = gerPagamentoSituacao;
    }

    public GEpocaPagamento getGerEpocaPagamento() {
        return this.gerEpocaPagamento;
    }

    public void setGerEpocaPagamento(GEpocaPagamento gerEpocaPagamento) {
        this.gerEpocaPagamento = gerEpocaPagamento;
    }

    public GEsferaPoder getGerEsferaPoder() {
        return this.gerEsferaPoder;
    }

    public void setGerEsferaPoder(GEsferaPoder gerEsferaPoder) {
        this.gerEsferaPoder = gerEsferaPoder;
    }

    public GClienteTipo getGerClienteTipo() {
        return this.gerClienteTipo;
    }

    public void setGerClienteTipo(GClienteTipo gerClienteTipo) {
        this.gerClienteTipo = gerClienteTipo;
    }

    public GLigacaoAguaSituacao getGerLigacaoAguaSituacao() {
        return this.gerLigacaoAguaSituacao;
    }

    public void setGerLigacaoAguaSituacao(GLigacaoAguaSituacao gerLigacaoAguaSituacao) {
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
    }

    public GUnidadeNegocio getGerUnidadeNegocio() {
        return this.gerUnidadeNegocio;
    }

    public void setGerUnidadeNegocio(GUnidadeNegocio gerUnidadeNegocio) {
        this.gerUnidadeNegocio = gerUnidadeNegocio;
    }

    public GLocalidade getGerLocalidade() {
        return this.gerLocalidade;
    }

    public void setGerLocalidade(GLocalidade gerLocalidade) {
        this.gerLocalidade = gerLocalidade;
    }

    public GLocalidade getGerLocalidadeElo() {
        return this.gerLocalidadeElo;
    }

    public void setGerLocalidadeElo(GLocalidade gerLocalidadeElo) {
        this.gerLocalidadeElo = gerLocalidadeElo;
    }

    public GCategoria getGerCategoria() {
        return this.gerCategoria;
    }

    public void setGerCategoria(GCategoria gerCategoria) {
        this.gerCategoria = gerCategoria;
    }

    public GImovelPerfil getGerImovelPerfil() {
        return this.gerImovelPerfil;
    }

    public void setGerImovelPerfil(GImovelPerfil gerImovelPerfil) {
        this.gerImovelPerfil = gerImovelPerfil;
    }

    public GRota getGerRota() {
        return this.gerRota;
    }

    public void setGerRota(GRota gerRota) {
        this.gerRota = gerRota;
    }

    public GQuadra getGerQuadra() {
        return this.gerQuadra;
    }

    public void setGerQuadra(GQuadra gerQuadra) {
        this.gerQuadra = gerQuadra;
    }

    public GLigacaoEsgotoSituacao getGerLigacaoEsgotoSituacao() {
        return this.gerLigacaoEsgotoSituacao;
    }

    public void setGerLigacaoEsgotoSituacao(GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao) {
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
    }

    public GLigacaoEsgotoPerfil getGerLigacaoEsgotoPerfil() {
        return this.gerLigacaoEsgotoPerfil;
    }

    public void setGerLigacaoEsgotoPerfil(GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public short getIndicadorHidrometro() {
		return indicadorHidrometro;
	}

	public void setIndicadorHidrometro(short indicadorHidrometro) {
		this.indicadorHidrometro = indicadorHidrometro;
	}

	public Integer getAnoMesReferenciaDocumento() {
		return anoMesReferenciaDocumento;
	}

	public void setAnoMesReferenciaDocumento(Integer anoMesReferenciaDocumento) {
		this.anoMesReferenciaDocumento = anoMesReferenciaDocumento;
	}
	
	public BigDecimal getValorDevolucoesClassificadas() {
		return valorDevolucoesClassificadas;
	}

	public void setValorDevolucoesClassificadas(
			BigDecimal valorDevolucoesClassificadas) {
		this.valorDevolucoesClassificadas = valorDevolucoesClassificadas;
	}

	public BigDecimal getValorDevolucoesNaoClassificadas() {
		return valorDevolucoesNaoClassificadas;
	}

	public void setValorDevolucoesNaoClassificadas(
			BigDecimal valorDevolucoesNaoClassificadas) {
		this.valorDevolucoesNaoClassificadas = valorDevolucoesNaoClassificadas;
	}

	public GDevolucaoSituacao getGerDevolucaoSituacao() {
		return gerDevolucaoSituacao;
	}

	public void setGerDevolucaoSituacao(
			GDevolucaoSituacao gerDevolucaoSituacao) {
		this.gerDevolucaoSituacao = gerDevolucaoSituacao;
	}

	public int getQuantidadePagamentos() {
		return quantidadePagamentos;
	}

	public void setQuantidadePagamentos(int quantidadePagamentos) {
		this.quantidadePagamentos = quantidadePagamentos;
	}
}
