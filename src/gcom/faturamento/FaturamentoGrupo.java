package gcom.faturamento;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class FaturamentoGrupo extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	private Integer id;
    private String descricao;
    private String descricaoAbreviada;
    private Short indicadorUso;
    private Integer anoMesReferencia;
    private Short diaVencimento;
    private Date ultimaAlteracao;
    private Short indicadorVencimentoMesFatura;

    public FaturamentoGrupo(String descricao, String descricaoAbreviada, Short indicadorUso, Integer anoMesReferencia, Short diaVencimento, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.anoMesReferencia = anoMesReferencia;
        this.diaVencimento = diaVencimento;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public FaturamentoGrupo() {
    }
    
    public FaturamentoGrupo(Integer id) {
    	this.id = id;
    }

    public FaturamentoGrupo(String descricao, String descricaoAbreviada) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Integer getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(Integer anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public Short getDiaVencimento() {
        return this.diaVencimento;
    }

    public void setDiaVencimento(Short diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    public String getMesAno() {
        String mesAno = null;
        String mes = null;
        String ano = null;

        if (this.anoMesReferencia != null && !this.anoMesReferencia.toString().trim().equalsIgnoreCase("")) {
            String anoMes = this.anoMesReferencia.toString();

            mes = anoMes.substring(4, 6);
            ano = anoMes.substring(0, 4);
            mesAno = mes + "/" + ano;
        }
        return mesAno;
    }
    
    public String[] retornaCamposChavePrimaria() {
    	String[] retorno = new String[1];
		retorno [0]=  "id" ;
		return retorno;
	}

	public Short getIndicadorVencimentoMesFatura() {
		return indicadorVencimentoMesFatura;
	}

	public void setIndicadorVencimentoMesFatura(Short indicadorVencimentoMesFatura) {
		this.indicadorVencimentoMesFatura = indicadorVencimentoMesFatura;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID,this.getId()));

		return filtroFaturamentoGrupo;
	}

}
