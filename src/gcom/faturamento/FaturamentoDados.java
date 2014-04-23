package gcom.faturamento;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FaturamentoDados implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer anoMesReferencia;

    /** nullable persistent field */
    private Short indicadorDebitoConta;

    /** nullable persistent field */
    private Short codigoSetorComercial;

    /** nullable persistent field */
    private Short numeroQuadra;

    /** nullable persistent field */
    private Short indicadorSimulacao;

    /** nullable persistent field */
    private Integer quantidadeEconomia;

    /** nullable persistent field */
    private BigDecimal valorAgua;

    /** nullable persistent field */
    private Integer consumoAgua;

    /** nullable persistent field */
    private BigDecimal valorEsgoto;

    /** nullable persistent field */
    private Integer consumoEsgoto;

    /** nullable persistent field */
    private BigDecimal valorDebitos;

    /** nullable persistent field */
    private BigDecimal valorCreditos;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.faturamento.FaturamentoGrupo faturamentoGrupo;

    /** persistent field */
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

    /** persistent field */
    private ImovelPerfil imovelPerfil;

    /** persistent field */
    private Quadra quadra;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private LigacaoAguaSituacao ligacaoAguaSituacao;

    /** persistent field */
    private Categoria categoria;

    /** full constructor */
    public FaturamentoDados(Integer anoMesReferencia, Short indicadorDebitoConta, Short codigoSetorComercial, Short numeroQuadra, Short indicadorSimulacao, Integer quantidadeEconomia, BigDecimal valorAgua, Integer consumoAgua, BigDecimal valorEsgoto, Integer consumoEsgoto, BigDecimal valorDebitos, BigDecimal valorCreditos, Date ultimaAlteracao, gcom.faturamento.FaturamentoGrupo faturamentoGrupo, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, ImovelPerfil imovelPerfil, Quadra quadra, Localidade localidade, LigacaoAguaSituacao ligacaoAguaSituacao, Categoria categoria) {
        this.anoMesReferencia = anoMesReferencia;
        this.indicadorDebitoConta = indicadorDebitoConta;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.indicadorSimulacao = indicadorSimulacao;
        this.quantidadeEconomia = quantidadeEconomia;
        this.valorAgua = valorAgua;
        this.consumoAgua = consumoAgua;
        this.valorEsgoto = valorEsgoto;
        this.consumoEsgoto = consumoEsgoto;
        this.valorDebitos = valorDebitos;
        this.valorCreditos = valorCreditos;
        this.ultimaAlteracao = ultimaAlteracao;
        this.faturamentoGrupo = faturamentoGrupo;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.imovelPerfil = imovelPerfil;
        this.quadra = quadra;
        this.localidade = localidade;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.categoria = categoria;
    }

    /** default constructor */
    public FaturamentoDados() {
    }

    /** minimal constructor */
    public FaturamentoDados(gcom.faturamento.FaturamentoGrupo faturamentoGrupo, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, ImovelPerfil imovelPerfil, Quadra quadra, Localidade localidade, LigacaoAguaSituacao ligacaoAguaSituacao, Categoria categoria) {
        this.faturamentoGrupo = faturamentoGrupo;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.imovelPerfil = imovelPerfil;
        this.quadra = quadra;
        this.localidade = localidade;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.categoria = categoria;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(Integer anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public Short getIndicadorDebitoConta() {
        return this.indicadorDebitoConta;
    }

    public void setIndicadorDebitoConta(Short indicadorDebitoConta) {
        this.indicadorDebitoConta = indicadorDebitoConta;
    }

    public Short getCodigoSetorComercial() {
        return this.codigoSetorComercial;
    }

    public void setCodigoSetorComercial(Short codigoSetorComercial) {
        this.codigoSetorComercial = codigoSetorComercial;
    }


    public Short getIndicadorSimulacao() {
        return this.indicadorSimulacao;
    }

    public void setIndicadorSimulacao(Short indicadorSimulacao) {
        this.indicadorSimulacao = indicadorSimulacao;
    }

    public Integer getQuantidadeEconomia() {
        return this.quantidadeEconomia;
    }

    public void setQuantidadeEconomia(Integer quantidadeEconomia) {
        this.quantidadeEconomia = quantidadeEconomia;
    }

    public BigDecimal getValorAgua() {
        return this.valorAgua;
    }

    public void setValorAgua(BigDecimal valorAgua) {
        this.valorAgua = valorAgua;
    }

    public Integer getConsumoAgua() {
        return this.consumoAgua;
    }

    public void setConsumoAgua(Integer consumoAgua) {
        this.consumoAgua = consumoAgua;
    }

    public BigDecimal getValorEsgoto() {
        return this.valorEsgoto;
    }

    public void setValorEsgoto(BigDecimal valorEsgoto) {
        this.valorEsgoto = valorEsgoto;
    }

    public Integer getConsumoEsgoto() {
        return this.consumoEsgoto;
    }

    public void setConsumoEsgoto(Integer consumoEsgoto) {
        this.consumoEsgoto = consumoEsgoto;
    }

    public BigDecimal getValorDebitos() {
        return this.valorDebitos;
    }

    public void setValorDebitos(BigDecimal valorDebitos) {
        this.valorDebitos = valorDebitos;
    }

    public BigDecimal getValorCreditos() {
        return this.valorCreditos;
    }

    public void setValorCreditos(BigDecimal valorCreditos) {
        this.valorCreditos = valorCreditos;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.faturamento.FaturamentoGrupo getFaturamentoGrupo() {
        return this.faturamentoGrupo;
    }

    public void setFaturamentoGrupo(gcom.faturamento.FaturamentoGrupo faturamentoGrupo) {
        this.faturamentoGrupo = faturamentoGrupo;
    }

    public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
        return this.ligacaoEsgotoSituacao;
    }

    public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
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

    public LigacaoAguaSituacao getLigacaoAguaSituacao() {
        return this.ligacaoAguaSituacao;
    }

    public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Short getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Short numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

}
