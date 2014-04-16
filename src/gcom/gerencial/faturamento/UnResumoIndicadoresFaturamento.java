package gcom.gerencial.faturamento;

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
import gcom.gerencial.faturamento.credito.GCreditoOrigem;
import gcom.gerencial.faturamento.credito.GCreditoTipo;
import gcom.gerencial.faturamento.debito.GDebitoTipo;
import gcom.gerencial.financeiro.GFinanciamentoTipo;
import gcom.gerencial.financeiro.lancamento.GLancamentoItemContabil;
import gcom.gerencial.micromedicao.GRota;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoIndicadoresFaturamento implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** persistent field */
    private String anoReferencia;

    /** persistent field */
    private String mesReferencia;

    /** persistent field */
    private int codigoSetorComercial;

    /** persistent field */
    private int numeroQuadra;

    /** persistent field */
    private int quantidadeContasEmitidas;

    /** persistent field */
    private int quantidadeContasRetificadas;

    /** persistent field */
    private int quantidadeContasCanceladas;

    /** persistent field */
    private int quantidadeContasIncluidas;

    /** persistent field */
    private int quantidadeEconomiasFaturadas;

    /** persistent field */
    private int volumeFaturadoAgua;

    /** persistent field */
    private int volumeCanceladoAgua;

    /** persistent field */
    private int volumeIncluidoAgua;

    /** persistent field */
    private int volumeFaturadoEsgoto;

    /** persistent field */
    private int volumeCanceladoEsgoto;

    /** persistent field */
    private int volumeIncluidoEsgoto;

    /** persistent field */
    private BigDecimal valorFaturadoAgua;

    /** persistent field */
    private BigDecimal valorCanceladoAgua;

    /** persistent field */
    private BigDecimal valorIncluidoAgua;

    /** persistent field */
    private BigDecimal valorFaturadoEsgoto;

    /** persistent field */
    private BigDecimal valorCanceladoEsgoto;

    /** persistent field */
    private BigDecimal valorIncluidoEsgoto;

    /** persistent field */
    private BigDecimal valorDocumentosFaturadosCredito;

    /** persistent field */
    private BigDecimal valorCanceladoCredito;

    /** persistent field */
    private BigDecimal valorIncluidoCredito;

    /** persistent field */
    private BigDecimal valorDocumentosFaturadosOutros;

    /** persistent field */
    private BigDecimal valorCanceladoOutros;

    /** persistent field */
    private BigDecimal valorIncluidoOutros;

    /** persistent field */
    private BigDecimal valorAcrescimoImpontualidade;

    /** persistent field */
    private int quantidadeContasEmitidasMesAno;

    /** persistent field */
    private int quantidadeContasRetificadasMesAno;

    /** persistent field */
    private int quantidadeContasCanceladasMesAno;

    /** persistent field */
    private int quantidadeContasIncluidasMesAno;

    /** persistent field */
    private int quantidadeEconomiasFaturadasMesAno;

    /** persistent field */
    private int volumeFaturadoAguaMesAno;

    /** persistent field */
    private int volumeCanceladoAguaMesAno;

    /** persistent field */
    private int volumeIncluidoAguaMesAno;

    /** persistent field */
    private int volumeFaturadoEsgotoMesAno;

    /** persistent field */
    private int volumeCanceladoEsgotoMesAno;

    /** persistent field */
    private int volumeIncluidoEsgotoMesAno;

    /** persistent field */
    private BigDecimal valorFaturadoAguaMesAno;

    /** persistent field */
    private BigDecimal valorCanceladoAguaMesAno;

    /** persistent field */
    private BigDecimal valorIncluidoAguaMesAno;

    /** persistent field */
    private BigDecimal valorFaturadoEsgotoMesAno;

    /** persistent field */
    private BigDecimal valorCanceladoEsgotoMesAno;

    /** persistent field */
    private BigDecimal valorIncluidoEsgotoMesAno;

    /** persistent field */
    private BigDecimal valorDocumentosFaturadosCreditoMesAno;

    /** persistent field */
    private BigDecimal valorCanceladoCreditoMesAno;

    /** persistent field */
    private BigDecimal valorIncluidoCreditoMesAno;

    /** persistent field */
    private BigDecimal valorDocumentosFaturadosOutrosMesAno;

    /** persistent field */
    private BigDecimal valorCanceladoOutrosMesAno;

    /** persistent field */
    private BigDecimal valorIncluidoOutrosMesAno;

    /** persistent field */
    private BigDecimal valorArrastos;

    /** persistent field */
    private BigDecimal valorParcelamento;

    /** persistent field */
    private int quantidadeGuiasCanceladas;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private GSubcategoria gerSubcategoria;

    /** persistent field */
    private GClienteTipo gerClienteTipo;

    /** persistent field */
    private GLigacaoAguaSituacao gerLigacaoAguaSituacao;

    /** persistent field */
    private GCreditoOrigem gerCreditoOrigem;

    /** persistent field */
    private GQuadra gerQuadra;

    /** persistent field */
    private GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao;

    /** persistent field */
    private GGerenciaRegional gerGerenciaRegional;

    /** persistent field */
    private GSetorComercial gerSetorComercial;

    /** persistent field */
    private GLigacaoAguaPerfil gerLigacaoAguaPerfil;

    /** persistent field */
    private GFinanciamentoTipo gerFinanciamentoTipo;

    /** persistent field */
    private GImovelPerfil gerImovelPerfil;

    /** persistent field */
    private GLancamentoItemContabil gerLancamentoItemContabil;

    /** persistent field */
    private GUnidadeNegocio gerUnidadeNegocio;

    /** persistent field */
    private GCreditoTipo gerCreditoTipo;

    /** persistent field */
    private GLocalidade gerLocalidade;

    /** persistent field */
    private GLocalidade gerLocalidadeElo;

    /** persistent field */
    private GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;

    /** persistent field */
    private GDocumentoTipo gerDocumentoTipo;

    /** persistent field */
    private GEsferaPoder gerEsferaPoder;

    /** persistent field */
    private GDebitoTipo gerDebitoTipo;

    /** persistent field */
    private GCategoria gerCategoria;

    /** persistent field */
    private GRota gerRota;

    /** full constructor */
    public UnResumoIndicadoresFaturamento(Integer id, int anoMesReferencia, String anoReferencia, String mesReferencia, int codigoSetorComercial, int numeroQuadra, int quantidadeContasEmitidas, int quantidadeContasRetificadas, int quantidadeContasCanceladas, int quantidadeContasIncluidas, int quantidadeEconomiasFaturadas, int volumeFaturadoAgua, int volumeCanceladoAgua, int volumeIncluidoAgua, int volumeFaturadoEsgoto, int volumeCanceladoEsgoto, int volumeIncluidoEsgoto, BigDecimal valorFaturadoAgua, BigDecimal valorCanceladoAgua, BigDecimal valorIncluidoAgua, BigDecimal valorFaturadoEsgoto, BigDecimal valorCanceladoEsgoto, BigDecimal valorIncluidoEsgoto, BigDecimal valorDocumentosFaturadosCredito, BigDecimal valorCanceladoCredito, BigDecimal valorIncluidoCredito, BigDecimal valorDocumentosFaturadosOutros, BigDecimal valorCanceladoOutros, BigDecimal valorIncluidoOutros, BigDecimal valorAcrescimoImpontualidade, int quantidadeContasEmitidasMesAno, int quantidadeContasRetificadasMesAno, int quantidadeContasCanceladasMesAno, int quantidadeContasIncluidasMesAno, int quantidadeEconomiasFaturadasMesAno, int volumeFaturadoAguaMesAno, int volumeCanceladoAguaMesAno, int volumeIncluidoAguaMesAno, int volumeFaturadoEsgotoMesAno, int volumeCanceladoEsgotoMesAno, int volumeIncluidoEsgotoMesAno, BigDecimal valorFaturadoAguaMesAno, BigDecimal valorCanceladoAguaMesAno, BigDecimal valorIncluidoAguaMesAno, BigDecimal valorFaturadoEsgotoMesAno, BigDecimal valorCanceladoEsgotoMesAno, BigDecimal valorIncluidoEsgotoMesAno, BigDecimal valorDocumentosFaturadosCreditoMesAno, BigDecimal valorCanceladoCreditoMesAno, BigDecimal valorIncluidoCreditoMesAno, BigDecimal valorDocumentosFaturadosOutrosMesAno, BigDecimal valorCanceladoOutrosMesAno, BigDecimal valorIncluidoOutrosMesAno, BigDecimal valorArrastos, BigDecimal valorParcelamento, int quantidadeGuiasCanceladas, Date ultimaAlteracao, GSubcategoria gerSubcategoria, GClienteTipo gerClienteTipo, GLigacaoAguaSituacao gerLigacaoAguaSituacao, GCreditoOrigem gerCreditoOrigem, GQuadra gerQuadra, GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao, GGerenciaRegional gerGerenciaRegional, GSetorComercial gerSetorComercial, GLigacaoAguaPerfil gerLigacaoAguaPerfil, GFinanciamentoTipo gerFinanciamentoTipo, GImovelPerfil gerImovelPerfil, GLancamentoItemContabil gerLancamentoItemContabil, GUnidadeNegocio gerUnidadeNegocio, GCreditoTipo gerCreditoTipo, GLocalidade gerLocalidade, GLocalidade gerLocalidadeElo, GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil, GDocumentoTipo gerDocumentoTipo, GEsferaPoder gerEsferaPoder, GDebitoTipo gerDebitoTipo, GCategoria gerCategoria, GRota gerRota) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.anoReferencia = anoReferencia;
        this.mesReferencia = mesReferencia;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.quantidadeContasEmitidas = quantidadeContasEmitidas;
        this.quantidadeContasRetificadas = quantidadeContasRetificadas;
        this.quantidadeContasCanceladas = quantidadeContasCanceladas;
        this.quantidadeContasIncluidas = quantidadeContasIncluidas;
        this.quantidadeEconomiasFaturadas = quantidadeEconomiasFaturadas;
        this.volumeFaturadoAgua = volumeFaturadoAgua;
        this.volumeCanceladoAgua = volumeCanceladoAgua;
        this.volumeIncluidoAgua = volumeIncluidoAgua;
        this.volumeFaturadoEsgoto = volumeFaturadoEsgoto;
        this.volumeCanceladoEsgoto = volumeCanceladoEsgoto;
        this.volumeIncluidoEsgoto = volumeIncluidoEsgoto;
        this.valorFaturadoAgua = valorFaturadoAgua;
        this.valorCanceladoAgua = valorCanceladoAgua;
        this.valorIncluidoAgua = valorIncluidoAgua;
        this.valorFaturadoEsgoto = valorFaturadoEsgoto;
        this.valorCanceladoEsgoto = valorCanceladoEsgoto;
        this.valorIncluidoEsgoto = valorIncluidoEsgoto;
        this.valorDocumentosFaturadosCredito = valorDocumentosFaturadosCredito;
        this.valorCanceladoCredito = valorCanceladoCredito;
        this.valorIncluidoCredito = valorIncluidoCredito;
        this.valorDocumentosFaturadosOutros = valorDocumentosFaturadosOutros;
        this.valorCanceladoOutros = valorCanceladoOutros;
        this.valorIncluidoOutros = valorIncluidoOutros;
        this.valorAcrescimoImpontualidade = valorAcrescimoImpontualidade;
        this.quantidadeContasEmitidasMesAno = quantidadeContasEmitidasMesAno;
        this.quantidadeContasRetificadasMesAno = quantidadeContasRetificadasMesAno;
        this.quantidadeContasCanceladasMesAno = quantidadeContasCanceladasMesAno;
        this.quantidadeContasIncluidasMesAno = quantidadeContasIncluidasMesAno;
        this.quantidadeEconomiasFaturadasMesAno = quantidadeEconomiasFaturadasMesAno;
        this.volumeFaturadoAguaMesAno = volumeFaturadoAguaMesAno;
        this.volumeCanceladoAguaMesAno = volumeCanceladoAguaMesAno;
        this.volumeIncluidoAguaMesAno = volumeIncluidoAguaMesAno;
        this.volumeFaturadoEsgotoMesAno = volumeFaturadoEsgotoMesAno;
        this.volumeCanceladoEsgotoMesAno = volumeCanceladoEsgotoMesAno;
        this.volumeIncluidoEsgotoMesAno = volumeIncluidoEsgotoMesAno;
        this.valorFaturadoAguaMesAno = valorFaturadoAguaMesAno;
        this.valorCanceladoAguaMesAno = valorCanceladoAguaMesAno;
        this.valorIncluidoAguaMesAno = valorIncluidoAguaMesAno;
        this.valorFaturadoEsgotoMesAno = valorFaturadoEsgotoMesAno;
        this.valorCanceladoEsgotoMesAno = valorCanceladoEsgotoMesAno;
        this.valorIncluidoEsgotoMesAno = valorIncluidoEsgotoMesAno;
        this.valorDocumentosFaturadosCreditoMesAno = valorDocumentosFaturadosCreditoMesAno;
        this.valorCanceladoCreditoMesAno = valorCanceladoCreditoMesAno;
        this.valorIncluidoCreditoMesAno = valorIncluidoCreditoMesAno;
        this.valorDocumentosFaturadosOutrosMesAno = valorDocumentosFaturadosOutrosMesAno;
        this.valorCanceladoOutrosMesAno = valorCanceladoOutrosMesAno;
        this.valorIncluidoOutrosMesAno = valorIncluidoOutrosMesAno;
        this.valorArrastos = valorArrastos;
        this.valorParcelamento = valorParcelamento;
        this.quantidadeGuiasCanceladas = quantidadeGuiasCanceladas;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerSubcategoria = gerSubcategoria;
        this.gerClienteTipo = gerClienteTipo;
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
        this.gerCreditoOrigem = gerCreditoOrigem;
        this.gerQuadra = gerQuadra;
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
        this.gerGerenciaRegional = gerGerenciaRegional;
        this.gerSetorComercial = gerSetorComercial;
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
        this.gerFinanciamentoTipo = gerFinanciamentoTipo;
        this.gerImovelPerfil = gerImovelPerfil;
        this.gerLancamentoItemContabil = gerLancamentoItemContabil;
        this.gerUnidadeNegocio = gerUnidadeNegocio;
        this.gerCreditoTipo = gerCreditoTipo;
        this.gerLocalidade = gerLocalidade;
        this.gerLocalidadeElo = gerLocalidadeElo;
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
        this.gerDocumentoTipo = gerDocumentoTipo;
        this.gerEsferaPoder = gerEsferaPoder;
        this.gerDebitoTipo = gerDebitoTipo;
        this.gerCategoria = gerCategoria;
        this.gerRota = gerRota;
    }

    /** default constructor */
    public UnResumoIndicadoresFaturamento() {
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

    public String getAnoReferencia() {
        return this.anoReferencia;
    }

    public void setAnoReferencia(String anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    public String getMesReferencia() {
        return this.mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
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

    public int getQuantidadeContasEmitidas() {
        return this.quantidadeContasEmitidas;
    }

    public void setQuantidadeContasEmitidas(int quantidadeContasEmitidas) {
        this.quantidadeContasEmitidas = quantidadeContasEmitidas;
    }

    public int getQuantidadeContasRetificadas() {
        return this.quantidadeContasRetificadas;
    }

    public void setQuantidadeContasRetificadas(int quantidadeContasRetificadas) {
        this.quantidadeContasRetificadas = quantidadeContasRetificadas;
    }

    public int getQuantidadeContasCanceladas() {
        return this.quantidadeContasCanceladas;
    }

    public void setQuantidadeContasCanceladas(int quantidadeContasCanceladas) {
        this.quantidadeContasCanceladas = quantidadeContasCanceladas;
    }

    public int getQuantidadeContasIncluidas() {
        return this.quantidadeContasIncluidas;
    }

    public void setQuantidadeContasIncluidas(int quantidadeContasIncluidas) {
        this.quantidadeContasIncluidas = quantidadeContasIncluidas;
    }

    public int getQuantidadeEconomiasFaturadas() {
        return this.quantidadeEconomiasFaturadas;
    }

    public void setQuantidadeEconomiasFaturadas(int quantidadeEconomiasFaturadas) {
        this.quantidadeEconomiasFaturadas = quantidadeEconomiasFaturadas;
    }

    public int getVolumeFaturadoAgua() {
        return this.volumeFaturadoAgua;
    }

    public void setVolumeFaturadoAgua(int volumeFaturadoAgua) {
        this.volumeFaturadoAgua = volumeFaturadoAgua;
    }

    public int getVolumeCanceladoAgua() {
        return this.volumeCanceladoAgua;
    }

    public void setVolumeCanceladoAgua(int volumeCanceladoAgua) {
        this.volumeCanceladoAgua = volumeCanceladoAgua;
    }

    public int getVolumeIncluidoAgua() {
        return this.volumeIncluidoAgua;
    }

    public void setVolumeIncluidoAgua(int volumeIncluidoAgua) {
        this.volumeIncluidoAgua = volumeIncluidoAgua;
    }

    public int getVolumeFaturadoEsgoto() {
        return this.volumeFaturadoEsgoto;
    }

    public void setVolumeFaturadoEsgoto(int volumeFaturadoEsgoto) {
        this.volumeFaturadoEsgoto = volumeFaturadoEsgoto;
    }

    public int getVolumeCanceladoEsgoto() {
        return this.volumeCanceladoEsgoto;
    }

    public void setVolumeCanceladoEsgoto(int volumeCanceladoEsgoto) {
        this.volumeCanceladoEsgoto = volumeCanceladoEsgoto;
    }

    public int getVolumeIncluidoEsgoto() {
        return this.volumeIncluidoEsgoto;
    }

    public void setVolumeIncluidoEsgoto(int volumeIncluidoEsgoto) {
        this.volumeIncluidoEsgoto = volumeIncluidoEsgoto;
    }

    public BigDecimal getValorFaturadoAgua() {
        return this.valorFaturadoAgua;
    }

    public void setValorFaturadoAgua(BigDecimal valorFaturadoAgua) {
        this.valorFaturadoAgua = valorFaturadoAgua;
    }

    public BigDecimal getValorCanceladoAgua() {
        return this.valorCanceladoAgua;
    }

    public void setValorCanceladoAgua(BigDecimal valorCanceladoAgua) {
        this.valorCanceladoAgua = valorCanceladoAgua;
    }

    public BigDecimal getValorIncluidoAgua() {
        return this.valorIncluidoAgua;
    }

    public void setValorIncluidoAgua(BigDecimal valorIncluidoAgua) {
        this.valorIncluidoAgua = valorIncluidoAgua;
    }

    public BigDecimal getValorFaturadoEsgoto() {
        return this.valorFaturadoEsgoto;
    }

    public void setValorFaturadoEsgoto(BigDecimal valorFaturadoEsgoto) {
        this.valorFaturadoEsgoto = valorFaturadoEsgoto;
    }

    public BigDecimal getValorCanceladoEsgoto() {
        return this.valorCanceladoEsgoto;
    }

    public void setValorCanceladoEsgoto(BigDecimal valorCanceladoEsgoto) {
        this.valorCanceladoEsgoto = valorCanceladoEsgoto;
    }

    public BigDecimal getValorIncluidoEsgoto() {
        return this.valorIncluidoEsgoto;
    }

    public void setValorIncluidoEsgoto(BigDecimal valorIncluidoEsgoto) {
        this.valorIncluidoEsgoto = valorIncluidoEsgoto;
    }

    public BigDecimal getValorDocumentosFaturadosCredito() {
        return this.valorDocumentosFaturadosCredito;
    }

    public void setValorDocumentosFaturadosCredito(BigDecimal valorDocumentosFaturadosCredito) {
        this.valorDocumentosFaturadosCredito = valorDocumentosFaturadosCredito;
    }

    public BigDecimal getValorCanceladoCredito() {
        return this.valorCanceladoCredito;
    }

    public void setValorCanceladoCredito(BigDecimal valorCanceladoCredito) {
        this.valorCanceladoCredito = valorCanceladoCredito;
    }

    public BigDecimal getValorIncluidoCredito() {
        return this.valorIncluidoCredito;
    }

    public void setValorIncluidoCredito(BigDecimal valorIncluidoCredito) {
        this.valorIncluidoCredito = valorIncluidoCredito;
    }

    public BigDecimal getValorDocumentosFaturadosOutros() {
        return this.valorDocumentosFaturadosOutros;
    }

    public void setValorDocumentosFaturadosOutros(BigDecimal valorDocumentosFaturadosOutros) {
        this.valorDocumentosFaturadosOutros = valorDocumentosFaturadosOutros;
    }

    public BigDecimal getValorCanceladoOutros() {
        return this.valorCanceladoOutros;
    }

    public void setValorCanceladoOutros(BigDecimal valorCanceladoOutros) {
        this.valorCanceladoOutros = valorCanceladoOutros;
    }

    public BigDecimal getValorIncluidoOutros() {
        return this.valorIncluidoOutros;
    }

    public void setValorIncluidoOutros(BigDecimal valorIncluidoOutros) {
        this.valorIncluidoOutros = valorIncluidoOutros;
    }

    public BigDecimal getValorAcrescimoImpontualidade() {
        return this.valorAcrescimoImpontualidade;
    }

    public void setValorAcrescimoImpontualidade(BigDecimal valorAcrescimoImpontualidade) {
        this.valorAcrescimoImpontualidade = valorAcrescimoImpontualidade;
    }

    public int getQuantidadeContasEmitidasMesAno() {
        return this.quantidadeContasEmitidasMesAno;
    }

    public void setQuantidadeContasEmitidasMesAno(int quantidadeContasEmitidasMesAno) {
        this.quantidadeContasEmitidasMesAno = quantidadeContasEmitidasMesAno;
    }

    public int getQuantidadeContasRetificadasMesAno() {
        return this.quantidadeContasRetificadasMesAno;
    }

    public void setQuantidadeContasRetificadasMesAno(int quantidadeContasRetificadasMesAno) {
        this.quantidadeContasRetificadasMesAno = quantidadeContasRetificadasMesAno;
    }

    public int getQuantidadeContasCanceladasMesAno() {
        return this.quantidadeContasCanceladasMesAno;
    }

    public void setQuantidadeContasCanceladasMesAno(int quantidadeContasCanceladasMesAno) {
        this.quantidadeContasCanceladasMesAno = quantidadeContasCanceladasMesAno;
    }

    public int getQuantidadeContasIncluidasMesAno() {
        return this.quantidadeContasIncluidasMesAno;
    }

    public void setQuantidadeContasIncluidasMesAno(int quantidadeContasIncluidasMesAno) {
        this.quantidadeContasIncluidasMesAno = quantidadeContasIncluidasMesAno;
    }

    public int getQuantidadeEconomiasFaturadasMesAno() {
        return this.quantidadeEconomiasFaturadasMesAno;
    }

    public void setQuantidadeEconomiasFaturadasMesAno(int quantidadeEconomiasFaturadasMesAno) {
        this.quantidadeEconomiasFaturadasMesAno = quantidadeEconomiasFaturadasMesAno;
    }

    public int getVolumeFaturadoAguaMesAno() {
        return this.volumeFaturadoAguaMesAno;
    }

    public void setVolumeFaturadoAguaMesAno(int volumeFaturadoAguaMesAno) {
        this.volumeFaturadoAguaMesAno = volumeFaturadoAguaMesAno;
    }

    public int getVolumeCanceladoAguaMesAno() {
        return this.volumeCanceladoAguaMesAno;
    }

    public void setVolumeCanceladoAguaMesAno(int volumeCanceladoAguaMesAno) {
        this.volumeCanceladoAguaMesAno = volumeCanceladoAguaMesAno;
    }

    public int getVolumeIncluidoAguaMesAno() {
        return this.volumeIncluidoAguaMesAno;
    }

    public void setVolumeIncluidoAguaMesAno(int volumeIncluidoAguaMesAno) {
        this.volumeIncluidoAguaMesAno = volumeIncluidoAguaMesAno;
    }

    public int getVolumeFaturadoEsgotoMesAno() {
        return this.volumeFaturadoEsgotoMesAno;
    }

    public void setVolumeFaturadoEsgotoMesAno(int volumeFaturadoEsgotoMesAno) {
        this.volumeFaturadoEsgotoMesAno = volumeFaturadoEsgotoMesAno;
    }

    public int getVolumeCanceladoEsgotoMesAno() {
        return this.volumeCanceladoEsgotoMesAno;
    }

    public void setVolumeCanceladoEsgotoMesAno(int volumeCanceladoEsgotoMesAno) {
        this.volumeCanceladoEsgotoMesAno = volumeCanceladoEsgotoMesAno;
    }

    public int getVolumeIncluidoEsgotoMesAno() {
        return this.volumeIncluidoEsgotoMesAno;
    }

    public void setVolumeIncluidoEsgotoMesAno(int volumeIncluidoEsgotoMesAno) {
        this.volumeIncluidoEsgotoMesAno = volumeIncluidoEsgotoMesAno;
    }

    public BigDecimal getValorFaturadoAguaMesAno() {
        return this.valorFaturadoAguaMesAno;
    }

    public void setValorFaturadoAguaMesAno(BigDecimal valorFaturadoAguaMesAno) {
        this.valorFaturadoAguaMesAno = valorFaturadoAguaMesAno;
    }

    public BigDecimal getValorCanceladoAguaMesAno() {
        return this.valorCanceladoAguaMesAno;
    }

    public void setValorCanceladoAguaMesAno(BigDecimal valorCanceladoAguaMesAno) {
        this.valorCanceladoAguaMesAno = valorCanceladoAguaMesAno;
    }

    public BigDecimal getValorIncluidoAguaMesAno() {
        return this.valorIncluidoAguaMesAno;
    }

    public void setValorIncluidoAguaMesAno(BigDecimal valorIncluidoAguaMesAno) {
        this.valorIncluidoAguaMesAno = valorIncluidoAguaMesAno;
    }

    public BigDecimal getValorFaturadoEsgotoMesAno() {
        return this.valorFaturadoEsgotoMesAno;
    }

    public void setValorFaturadoEsgotoMesAno(BigDecimal valorFaturadoEsgotoMesAno) {
        this.valorFaturadoEsgotoMesAno = valorFaturadoEsgotoMesAno;
    }

    public BigDecimal getValorCanceladoEsgotoMesAno() {
        return this.valorCanceladoEsgotoMesAno;
    }

    public void setValorCanceladoEsgotoMesAno(BigDecimal valorCanceladoEsgotoMesAno) {
        this.valorCanceladoEsgotoMesAno = valorCanceladoEsgotoMesAno;
    }

    public BigDecimal getValorIncluidoEsgotoMesAno() {
        return this.valorIncluidoEsgotoMesAno;
    }

    public void setValorIncluidoEsgotoMesAno(BigDecimal valorIncluidoEsgotoMesAno) {
        this.valorIncluidoEsgotoMesAno = valorIncluidoEsgotoMesAno;
    }

    public BigDecimal getValorDocumentosFaturadosCreditoMesAno() {
        return this.valorDocumentosFaturadosCreditoMesAno;
    }

    public void setValorDocumentosFaturadosCreditoMesAno(BigDecimal valorDocumentosFaturadosCreditoMesAno) {
        this.valorDocumentosFaturadosCreditoMesAno = valorDocumentosFaturadosCreditoMesAno;
    }

    public BigDecimal getValorCanceladoCreditoMesAno() {
        return this.valorCanceladoCreditoMesAno;
    }

    public void setValorCanceladoCreditoMesAno(BigDecimal valorCanceladoCreditoMesAno) {
        this.valorCanceladoCreditoMesAno = valorCanceladoCreditoMesAno;
    }

    public BigDecimal getValorIncluidoCreditoMesAno() {
        return this.valorIncluidoCreditoMesAno;
    }

    public void setValorIncluidoCreditoMesAno(BigDecimal valorIncluidoCreditoMesAno) {
        this.valorIncluidoCreditoMesAno = valorIncluidoCreditoMesAno;
    }

    public BigDecimal getValorDocumentosFaturadosOutrosMesAno() {
        return this.valorDocumentosFaturadosOutrosMesAno;
    }

    public void setValorDocumentosFaturadosOutrosMesAno(BigDecimal valorDocumentosFaturadosOutrosMesAno) {
        this.valorDocumentosFaturadosOutrosMesAno = valorDocumentosFaturadosOutrosMesAno;
    }

    public BigDecimal getValorCanceladoOutrosMesAno() {
        return this.valorCanceladoOutrosMesAno;
    }

    public void setValorCanceladoOutrosMesAno(BigDecimal valorCanceladoOutrosMesAno) {
        this.valorCanceladoOutrosMesAno = valorCanceladoOutrosMesAno;
    }

    public BigDecimal getValorIncluidoOutrosMesAno() {
        return this.valorIncluidoOutrosMesAno;
    }

    public void setValorIncluidoOutrosMesAno(BigDecimal valorIncluidoOutrosMesAno) {
        this.valorIncluidoOutrosMesAno = valorIncluidoOutrosMesAno;
    }

    public BigDecimal getValorArrastos() {
        return this.valorArrastos;
    }

    public void setValorArrastos(BigDecimal valorArrastos) {
        this.valorArrastos = valorArrastos;
    }

    public BigDecimal getValorParcelamento() {
        return this.valorParcelamento;
    }

    public void setValorParcelamento(BigDecimal valorParcelamento) {
        this.valorParcelamento = valorParcelamento;
    }

    public int getQuantidadeGuiasCanceladas() {
        return this.quantidadeGuiasCanceladas;
    }

    public void setQuantidadeGuiasCanceladas(int quantidadeGuiasCanceladas) {
        this.quantidadeGuiasCanceladas = quantidadeGuiasCanceladas;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public GSubcategoria getGerSubcategoria() {
        return this.gerSubcategoria;
    }

    public void setGerSubcategoria(GSubcategoria gerSubcategoria) {
        this.gerSubcategoria = gerSubcategoria;
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

    public GCreditoOrigem getGerCreditoOrigem() {
        return this.gerCreditoOrigem;
    }

    public void setGerCreditoOrigem(GCreditoOrigem gerCreditoOrigem) {
        this.gerCreditoOrigem = gerCreditoOrigem;
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

    public GLigacaoAguaPerfil getGerLigacaoAguaPerfil() {
        return this.gerLigacaoAguaPerfil;
    }

    public void setGerLigacaoAguaPerfil(GLigacaoAguaPerfil gerLigacaoAguaPerfil) {
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
    }

    public GFinanciamentoTipo getGerFinanciamentoTipo() {
        return this.gerFinanciamentoTipo;
    }

    public void setGerFinanciamentoTipo(GFinanciamentoTipo gerFinanciamentoTipo) {
        this.gerFinanciamentoTipo = gerFinanciamentoTipo;
    }

    public GImovelPerfil getGerImovelPerfil() {
        return this.gerImovelPerfil;
    }

    public void setGerImovelPerfil(GImovelPerfil gerImovelPerfil) {
        this.gerImovelPerfil = gerImovelPerfil;
    }

    public GLancamentoItemContabil getGerLancamentoItemContabil() {
        return this.gerLancamentoItemContabil;
    }

    public void setGerLancamentoItemContabil(GLancamentoItemContabil gerLancamentoItemContabil) {
        this.gerLancamentoItemContabil = gerLancamentoItemContabil;
    }

    public GUnidadeNegocio getGerUnidadeNegocio() {
        return this.gerUnidadeNegocio;
    }

    public void setGerUnidadeNegocio(GUnidadeNegocio gerUnidadeNegocio) {
        this.gerUnidadeNegocio = gerUnidadeNegocio;
    }

    public GCreditoTipo getGerCreditoTipo() {
        return this.gerCreditoTipo;
    }

    public void setGerCreditoTipo(GCreditoTipo gerCreditoTipo) {
        this.gerCreditoTipo = gerCreditoTipo;
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

    public GLigacaoEsgotoPerfil getGerLigacaoEsgotoPerfil() {
        return this.gerLigacaoEsgotoPerfil;
    }

    public void setGerLigacaoEsgotoPerfil(GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    }

    public GDocumentoTipo getGerDocumentoTipo() {
        return this.gerDocumentoTipo;
    }

    public void setGerDocumentoTipo(GDocumentoTipo gerDocumentoTipo) {
        this.gerDocumentoTipo = gerDocumentoTipo;
    }

    public GEsferaPoder getGerEsferaPoder() {
        return this.gerEsferaPoder;
    }

    public void setGerEsferaPoder(GEsferaPoder gerEsferaPoder) {
        this.gerEsferaPoder = gerEsferaPoder;
    }

    public GDebitoTipo getGerDebitoTipo() {
        return this.gerDebitoTipo;
    }

    public void setGerDebitoTipo(GDebitoTipo gerDebitoTipo) {
        this.gerDebitoTipo = gerDebitoTipo;
    }

    public GCategoria getGerCategoria() {
        return this.gerCategoria;
    }

    public void setGerCategoria(GCategoria gerCategoria) {
        this.gerCategoria = gerCategoria;
    }

    public GRota getGerRota() {
        return this.gerRota;
    }

    public void setGerRota(GRota gerRota) {
        this.gerRota = gerRota;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
