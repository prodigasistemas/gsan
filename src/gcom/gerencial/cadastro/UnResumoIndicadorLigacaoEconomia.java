package gcom.gerencial.cadastro;

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
import gcom.gerencial.faturamento.GConsumoTarifa;
import gcom.gerencial.micromedicao.GRota;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoIndicadorLigacaoEconomia implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private gcom.gerencial.cadastro.UnResumoIndicadorLigacaoEconomiaPK comp_id;

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
    private short indicadorHidrometro;

    /** persistent field */
    private short indicadorVolumeFixadoAgua;

    /** persistent field */
    private short indicadorVolumeFixadoEsgoto;

    /** persistent field */
    private short indicadorPoco;

    /** persistent field */
    private short indicadorHidrometroPoco;

    /** persistent field */
    private int quantidadeLigacoesAtivasAgua;

    /** persistent field */
    private int quantidadeLigacoesAtivasAguaMesAno;

    /** persistent field */
    private int quantidadeLigacoesAtivasEsgoto;

    /** persistent field */
    private int quantidadeLigacoesAtivasEsgotoMesAno;

    /** persistent field */
    private int quantidadeEconomiasTotaisAgua;

    /** persistent field */
    private int quantidadeEconomiasTotaisAguaMesAno;

    /** persistent field */
    private int quantidadeEconomiasTotaisEsgoto;

    /** persistent field */
    private int quantidadeEconomiasTotaisEsgotoMesAno;

    /** persistent field */
    private int quantidadeClientesAguaPotenciaisFactiveis;

    /** persistent field */
    private int quantidadeClientesAguaLigadosCortados;

    /** persistent field */
    private int quantidadeClientesAguaSuprimidos;

    /** persistent field */
    private int quantidadeEconomiasAguaPotenciaisFactiveis;

    /** persistent field */
    private int quantidadeEconomiasAguaLigadosCortados;

    /** persistent field */
    private int quantidadeEconomiasAguaSuprimidos;

    /** persistent field */
    private int quantidadeClientesEsgotoPotenciaisFactiveis;

    /** persistent field */
    private int quantidadeClientesEsgotoLigados;

    /** persistent field */
    private int quantidadeClientesEsgotoLigadosForaUso;

    /** persistent field */
    private int quantidadeEconomiasEsgotoPotenciaisFactiveis;

    /** persistent field */
    private int quantidadeEconomiasEsgotoLigados;

    /** persistent field */
    private int quantidadeEconomiasEsgotoLigadosForaUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private GSubcategoria gerSubCategoria;

    /** persistent field */
    private GClienteTipo gerClienteTipo;

    /** persistent field */
    private GLigacaoAguaSituacao gerLigacaoAguaSituacao;

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
    private GImovelPerfil gerImovelPerfil;

    /** persistent field */
    private GUnidadeNegocio gerUnidadeNegocio;

    /** persistent field */
    private GLocalidade gerLocalidadeElo;

    /** persistent field */
    private GLocalidade gerLocalidade;

    /** persistent field */
    private GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;

    /** persistent field */
    private GConsumoTarifa gerConsumoTarifa;

    /** persistent field */
    private GEsferaPoder gerEsferaPoder;

    /** persistent field */
    private GCategoria gerCategoria;

    /** persistent field */
    private GRota gerRota;

    /** full constructor */
    public UnResumoIndicadorLigacaoEconomia(gcom.gerencial.cadastro.UnResumoIndicadorLigacaoEconomiaPK comp_id, int anoMesReferencia, String anoReferencia, String mesReferencia, int codigoSetorComercial, int numeroQuadra, short indicadorHidrometro, short indicadorVolumeFixadoAgua, short indicadorVolumeFixadoEsgoto, short indicadorPoco, short indicadorHidrometroPoco, int quantidadeLigacoesAtivasAgua, int quantidadeLigacoesAtivasAguaMesAno, int quantidadeLigacoesAtivasEsgoto, int quantidadeLigacoesAtivasEsgotoMesAno, int quantidadeEconomiasTotaisAgua, int quantidadeEconomiasTotaisAguaMesAno, int quantidadeEconomiasTotaisEsgoto, int quantidadeEconomiasTotaisEsgotoMesAno, int quantidadeClientesAguaPotenciaisFactiveis, int quantidadeClientesAguaLigadosCortados, int quantidadeClientesAguaSuprimidos, int quantidadeEconomiasAguaPotenciaisFactiveis, int quantidadeEconomiasAguaLigadosCortados, int quantidadeEconomiasAguaSuprimidos, int quantidadeClientesEsgotoPotenciaisFactiveis, int quantidadeClientesEsgotoLigados, int quantidadeClientesEsgotoLigadosForaUso, int quantidadeEconomiasEsgotoPotenciaisFactiveis, int quantidadeEconomiasEsgotoLigados, int quantidadeEconomiasEsgotoLigadosForaUso, Date ultimaAlteracao, GSubcategoria gerSubCategoria, GClienteTipo gerClienteTipo, GLigacaoAguaSituacao gerLigacaoAguaSituacao, GQuadra gerQuadra, GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao, GGerenciaRegional gerGerenciaRegional, GSetorComercial gerSetorComercial, GLigacaoAguaPerfil gerLigacaoAguaPerfil, GImovelPerfil gerImovelPerfil, GUnidadeNegocio gerUnidadeNegocio, GLocalidade gerLocalidadeElo, GLocalidade gerLocalidade, GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil, GConsumoTarifa gerConsumoTarifa, GEsferaPoder gerEsferaPoder, GCategoria gerCategoria, GRota gerRota) {
        this.comp_id = comp_id;
        this.anoMesReferencia = anoMesReferencia;
        this.anoReferencia = anoReferencia;
        this.mesReferencia = mesReferencia;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.indicadorHidrometro = indicadorHidrometro;
        this.indicadorVolumeFixadoAgua = indicadorVolumeFixadoAgua;
        this.indicadorVolumeFixadoEsgoto = indicadorVolumeFixadoEsgoto;
        this.indicadorPoco = indicadorPoco;
        this.indicadorHidrometroPoco = indicadorHidrometroPoco;
        this.quantidadeLigacoesAtivasAgua = quantidadeLigacoesAtivasAgua;
        this.quantidadeLigacoesAtivasAguaMesAno = quantidadeLigacoesAtivasAguaMesAno;
        this.quantidadeLigacoesAtivasEsgoto = quantidadeLigacoesAtivasEsgoto;
        this.quantidadeLigacoesAtivasEsgotoMesAno = quantidadeLigacoesAtivasEsgotoMesAno;
        this.quantidadeEconomiasTotaisAgua = quantidadeEconomiasTotaisAgua;
        this.quantidadeEconomiasTotaisAguaMesAno = quantidadeEconomiasTotaisAguaMesAno;
        this.quantidadeEconomiasTotaisEsgoto = quantidadeEconomiasTotaisEsgoto;
        this.quantidadeEconomiasTotaisEsgotoMesAno = quantidadeEconomiasTotaisEsgotoMesAno;
        this.quantidadeClientesAguaPotenciaisFactiveis = quantidadeClientesAguaPotenciaisFactiveis;
        this.quantidadeClientesAguaLigadosCortados = quantidadeClientesAguaLigadosCortados;
        this.quantidadeClientesAguaSuprimidos = quantidadeClientesAguaSuprimidos;
        this.quantidadeEconomiasAguaPotenciaisFactiveis = quantidadeEconomiasAguaPotenciaisFactiveis;
        this.quantidadeEconomiasAguaLigadosCortados = quantidadeEconomiasAguaLigadosCortados;
        this.quantidadeEconomiasAguaSuprimidos = quantidadeEconomiasAguaSuprimidos;
        this.quantidadeClientesEsgotoPotenciaisFactiveis = quantidadeClientesEsgotoPotenciaisFactiveis;
        this.quantidadeClientesEsgotoLigados = quantidadeClientesEsgotoLigados;
        this.quantidadeClientesEsgotoLigadosForaUso = quantidadeClientesEsgotoLigadosForaUso;
        this.quantidadeEconomiasEsgotoPotenciaisFactiveis = quantidadeEconomiasEsgotoPotenciaisFactiveis;
        this.quantidadeEconomiasEsgotoLigados = quantidadeEconomiasEsgotoLigados;
        this.quantidadeEconomiasEsgotoLigadosForaUso = quantidadeEconomiasEsgotoLigadosForaUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerSubCategoria = gerSubCategoria;
        this.gerClienteTipo = gerClienteTipo;
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
        this.gerQuadra = gerQuadra;
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
        this.gerGerenciaRegional = gerGerenciaRegional;
        this.gerSetorComercial = gerSetorComercial;
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
        this.gerImovelPerfil = gerImovelPerfil;
        this.gerUnidadeNegocio = gerUnidadeNegocio;
        this.gerLocalidadeElo = gerLocalidadeElo;
        this.gerLocalidade = gerLocalidade;
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
        this.gerConsumoTarifa = gerConsumoTarifa;
        this.gerEsferaPoder = gerEsferaPoder;
        this.gerCategoria = gerCategoria;
        this.gerRota = gerRota;
    }

    /** default constructor */
    public UnResumoIndicadorLigacaoEconomia() {
    }

    public gcom.gerencial.cadastro.UnResumoIndicadorLigacaoEconomiaPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.gerencial.cadastro.UnResumoIndicadorLigacaoEconomiaPK comp_id) {
        this.comp_id = comp_id;
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

    public short getIndicadorHidrometro() {
        return this.indicadorHidrometro;
    }

    public void setIndicadorHidrometro(short indicadorHidrometro) {
        this.indicadorHidrometro = indicadorHidrometro;
    }

    public short getIndicadorVolumeFixadoAgua() {
        return this.indicadorVolumeFixadoAgua;
    }

    public void setIndicadorVolumeFixadoAgua(short indicadorVolumeFixadoAgua) {
        this.indicadorVolumeFixadoAgua = indicadorVolumeFixadoAgua;
    }

    public short getIndicadorVolumeFixadoEsgoto() {
        return this.indicadorVolumeFixadoEsgoto;
    }

    public void setIndicadorVolumeFixadoEsgoto(short indicadorVolumeFixadoEsgoto) {
        this.indicadorVolumeFixadoEsgoto = indicadorVolumeFixadoEsgoto;
    }

    public short getIndicadorPoco() {
        return this.indicadorPoco;
    }

    public void setIndicadorPoco(short indicadorPoco) {
        this.indicadorPoco = indicadorPoco;
    }

    public short getIndicadorHidrometroPoco() {
        return this.indicadorHidrometroPoco;
    }

    public void setIndicadorHidrometroPoco(short indicadorHidrometroPoco) {
        this.indicadorHidrometroPoco = indicadorHidrometroPoco;
    }

    public int getQuantidadeLigacoesAtivasAgua() {
        return this.quantidadeLigacoesAtivasAgua;
    }

    public void setQuantidadeLigacoesAtivasAgua(int quantidadeLigacoesAtivasAgua) {
        this.quantidadeLigacoesAtivasAgua = quantidadeLigacoesAtivasAgua;
    }

    public int getQuantidadeLigacoesAtivasAguaMesAno() {
        return this.quantidadeLigacoesAtivasAguaMesAno;
    }

    public void setQuantidadeLigacoesAtivasAguaMesAno(int quantidadeLigacoesAtivasAguaMesAno) {
        this.quantidadeLigacoesAtivasAguaMesAno = quantidadeLigacoesAtivasAguaMesAno;
    }

    public int getQuantidadeLigacoesAtivasEsgoto() {
        return this.quantidadeLigacoesAtivasEsgoto;
    }

    public void setQuantidadeLigacoesAtivasEsgoto(int quantidadeLigacoesAtivasEsgoto) {
        this.quantidadeLigacoesAtivasEsgoto = quantidadeLigacoesAtivasEsgoto;
    }

    public int getQuantidadeLigacoesAtivasEsgotoMesAno() {
        return this.quantidadeLigacoesAtivasEsgotoMesAno;
    }

    public void setQuantidadeLigacoesAtivasEsgotoMesAno(int quantidadeLigacoesAtivasEsgotoMesAno) {
        this.quantidadeLigacoesAtivasEsgotoMesAno = quantidadeLigacoesAtivasEsgotoMesAno;
    }

    public int getQuantidadeEconomiasTotaisAgua() {
        return this.quantidadeEconomiasTotaisAgua;
    }

    public void setQuantidadeEconomiasTotaisAgua(int quantidadeEconomiasTotaisAgua) {
        this.quantidadeEconomiasTotaisAgua = quantidadeEconomiasTotaisAgua;
    }

    public int getQuantidadeEconomiasTotaisAguaMesAno() {
        return this.quantidadeEconomiasTotaisAguaMesAno;
    }

    public void setQuantidadeEconomiasTotaisAguaMesAno(int quantidadeEconomiasTotaisAguaMesAno) {
        this.quantidadeEconomiasTotaisAguaMesAno = quantidadeEconomiasTotaisAguaMesAno;
    }

    public int getQuantidadeEconomiasTotaisEsgoto() {
        return this.quantidadeEconomiasTotaisEsgoto;
    }

    public void setQuantidadeEconomiasTotaisEsgoto(int quantidadeEconomiasTotaisEsgoto) {
        this.quantidadeEconomiasTotaisEsgoto = quantidadeEconomiasTotaisEsgoto;
    }

    public int getQuantidadeEconomiasTotaisEsgotoMesAno() {
        return this.quantidadeEconomiasTotaisEsgotoMesAno;
    }

    public void setQuantidadeEconomiasTotaisEsgotoMesAno(int quantidadeEconomiasTotaisEsgotoMesAno) {
        this.quantidadeEconomiasTotaisEsgotoMesAno = quantidadeEconomiasTotaisEsgotoMesAno;
    }

    public int getQuantidadeClientesAguaPotenciaisFactiveis() {
        return this.quantidadeClientesAguaPotenciaisFactiveis;
    }

    public void setQuantidadeClientesAguaPotenciaisFactiveis(int quantidadeClientesAguaPotenciaisFactiveis) {
        this.quantidadeClientesAguaPotenciaisFactiveis = quantidadeClientesAguaPotenciaisFactiveis;
    }

    public int getQuantidadeClientesAguaLigadosCortados() {
        return this.quantidadeClientesAguaLigadosCortados;
    }

    public void setQuantidadeClientesAguaLigadosCortados(int quantidadeClientesAguaLigadosCortados) {
        this.quantidadeClientesAguaLigadosCortados = quantidadeClientesAguaLigadosCortados;
    }

    public int getQuantidadeClientesAguaSuprimidos() {
        return this.quantidadeClientesAguaSuprimidos;
    }

    public void setQuantidadeClientesAguaSuprimidos(int quantidadeClientesAguaSuprimidos) {
        this.quantidadeClientesAguaSuprimidos = quantidadeClientesAguaSuprimidos;
    }

    public int getQuantidadeEconomiasAguaPotenciaisFactiveis() {
        return this.quantidadeEconomiasAguaPotenciaisFactiveis;
    }

    public void setQuantidadeEconomiasAguaPotenciaisFactiveis(int quantidadeEconomiasAguaPotenciaisFactiveis) {
        this.quantidadeEconomiasAguaPotenciaisFactiveis = quantidadeEconomiasAguaPotenciaisFactiveis;
    }

    public int getQuantidadeEconomiasAguaLigadosCortados() {
        return this.quantidadeEconomiasAguaLigadosCortados;
    }

    public void setQuantidadeEconomiasAguaLigadosCortados(int quantidadeEconomiasAguaLigadosCortados) {
        this.quantidadeEconomiasAguaLigadosCortados = quantidadeEconomiasAguaLigadosCortados;
    }

    public int getQuantidadeEconomiasAguaSuprimidos() {
        return this.quantidadeEconomiasAguaSuprimidos;
    }

    public void setQuantidadeEconomiasAguaSuprimidos(int quantidadeEconomiasAguaSuprimidos) {
        this.quantidadeEconomiasAguaSuprimidos = quantidadeEconomiasAguaSuprimidos;
    }

    public int getQuantidadeClientesEsgotoPotenciaisFactiveis() {
        return this.quantidadeClientesEsgotoPotenciaisFactiveis;
    }

    public void setQuantidadeClientesEsgotoPotenciaisFactiveis(int quantidadeClientesEsgotoPotenciaisFactiveis) {
        this.quantidadeClientesEsgotoPotenciaisFactiveis = quantidadeClientesEsgotoPotenciaisFactiveis;
    }

    public int getQuantidadeClientesEsgotoLigados() {
        return this.quantidadeClientesEsgotoLigados;
    }

    public void setQuantidadeClientesEsgotoLigados(int quantidadeClientesEsgotoLigados) {
        this.quantidadeClientesEsgotoLigados = quantidadeClientesEsgotoLigados;
    }

    public int getQuantidadeClientesEsgotoLigadosForaUso() {
        return this.quantidadeClientesEsgotoLigadosForaUso;
    }

    public void setQuantidadeClientesEsgotoLigadosForaUso(int quantidadeClientesEsgotoLigadosForaUso) {
        this.quantidadeClientesEsgotoLigadosForaUso = quantidadeClientesEsgotoLigadosForaUso;
    }

    public int getQuantidadeEconomiasEsgotoPotenciaisFactiveis() {
        return this.quantidadeEconomiasEsgotoPotenciaisFactiveis;
    }

    public void setQuantidadeEconomiasEsgotoPotenciaisFactiveis(int quantidadeEconomiasEsgotoPotenciaisFactiveis) {
        this.quantidadeEconomiasEsgotoPotenciaisFactiveis = quantidadeEconomiasEsgotoPotenciaisFactiveis;
    }

    public int getQuantidadeEconomiasEsgotoLigados() {
        return this.quantidadeEconomiasEsgotoLigados;
    }

    public void setQuantidadeEconomiasEsgotoLigados(int quantidadeEconomiasEsgotoLigados) {
        this.quantidadeEconomiasEsgotoLigados = quantidadeEconomiasEsgotoLigados;
    }

    public int getQuantidadeEconomiasEsgotoLigadosForaUso() {
        return this.quantidadeEconomiasEsgotoLigadosForaUso;
    }

    public void setQuantidadeEconomiasEsgotoLigadosForaUso(int quantidadeEconomiasEsgotoLigadosForaUso) {
        this.quantidadeEconomiasEsgotoLigadosForaUso = quantidadeEconomiasEsgotoLigadosForaUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public GSubcategoria getGerSubCategoria() {
        return this.gerSubCategoria;
    }

    public void setGerSubCategoria(GSubcategoria gerSubCategoria) {
        this.gerSubCategoria = gerSubCategoria;
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

    public GImovelPerfil getGerImovelPerfil() {
        return this.gerImovelPerfil;
    }

    public void setGerImovelPerfil(GImovelPerfil gerImovelPerfil) {
        this.gerImovelPerfil = gerImovelPerfil;
    }

    public GUnidadeNegocio getGerUnidadeNegocio() {
        return this.gerUnidadeNegocio;
    }

    public void setGerUnidadeNegocio(GUnidadeNegocio gerUnidadeNegocio) {
        this.gerUnidadeNegocio = gerUnidadeNegocio;
    }

    public GLocalidade getGerLocalidadeElo() {
        return this.gerLocalidadeElo;
    }

    public void setGerLocalidadeElo(GLocalidade gerLocalidadeElo) {
        this.gerLocalidadeElo = gerLocalidadeElo;
    }

    public GLocalidade getGerLocalidade() {
        return this.gerLocalidade;
    }

    public void setGerLocalidade(GLocalidade gerLocalidade) {
        this.gerLocalidade = gerLocalidade;
    }

    public GLigacaoEsgotoPerfil getGerLigacaoEsgotoPerfil() {
        return this.gerLigacaoEsgotoPerfil;
    }

    public void setGerLigacaoEsgotoPerfil(GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    }

    public GConsumoTarifa getGerConsumoTarifa() {
        return this.gerConsumoTarifa;
    }

    public void setGerConsumoTarifa(GConsumoTarifa gerConsumoTarifa) {
        this.gerConsumoTarifa = gerConsumoTarifa;
    }

    public GEsferaPoder getGerEsferaPoder() {
        return this.gerEsferaPoder;
    }

    public void setGerEsferaPoder(GEsferaPoder gerEsferaPoder) {
        this.gerEsferaPoder = gerEsferaPoder;
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
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UnResumoIndicadorLigacaoEconomia) ) return false;
        UnResumoIndicadorLigacaoEconomia castOther = (UnResumoIndicadorLigacaoEconomia) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
