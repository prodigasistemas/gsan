package gcom.faturamento;

import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.Rota;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FaturamentoAtivCronRota extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private gcom.faturamento.FaturamentoAtivCronRotaPK comp_id;

    /** nullable persistent field */
    private Date dataContaVencimento;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Rota rota;

    /** nullable persistent field */
    private gcom.faturamento.FaturamentoAtividadeCronograma faturamentoAtividadeCronograma;
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroFaturamentoAtivCronRota faturamentoAtivCronRota = new FiltroFaturamentoAtivCronRota();
		faturamentoAtivCronRota. adicionarCaminhoParaCarregamentoEntidade("faturamentoAtividadeCronograma");
		faturamentoAtivCronRota. adicionarCaminhoParaCarregamentoEntidade("rota");
		faturamentoAtivCronRota. adicionarParametro(
				new ParametroSimples(FiltroFaturamentoAtivCronRota.COMP_ID_FATURAMENTO_ATIVIDADE_CRONOGRAMA_ID, this.getFaturamentoAtividadeCronograma().getId()));
		faturamentoAtivCronRota. adicionarParametro(
				new ParametroSimples(FiltroFaturamentoAtivCronRota.COMP_ID_ROTA_ID, this.getRota().getId()));
		
		return faturamentoAtivCronRota; 
	}

    /** full constructor */
    public FaturamentoAtivCronRota(gcom.faturamento.FaturamentoAtivCronRotaPK comp_id, Date dataContaVencimento, Date ultimaAlteracao, Rota rota, gcom.faturamento.FaturamentoAtividadeCronograma faturamentoAtividadeCronograma) {
        this.comp_id = comp_id;
        this.dataContaVencimento = dataContaVencimento;
        this.ultimaAlteracao = ultimaAlteracao;
        this.rota = rota;
        this.faturamentoAtividadeCronograma = faturamentoAtividadeCronograma;
    }

    /** default constructor */
    public FaturamentoAtivCronRota() {
    }

    /** minimal constructor */
    public FaturamentoAtivCronRota(gcom.faturamento.FaturamentoAtivCronRotaPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.faturamento.FaturamentoAtivCronRotaPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.faturamento.FaturamentoAtivCronRotaPK comp_id) {
        this.comp_id = comp_id;
    }

    public Date getDataContaVencimento() {
        return this.dataContaVencimento;
    }

    public void setDataContaVencimento(Date dataContaVencimento) {
        this.dataContaVencimento = dataContaVencimento;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Rota getRota() {
        return this.rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public gcom.faturamento.FaturamentoAtividadeCronograma getFaturamentoAtividadeCronograma() {
        return this.faturamentoAtividadeCronograma;
    }

    public void setFaturamentoAtividadeCronograma(gcom.faturamento.FaturamentoAtividadeCronograma faturamentoAtividadeCronograma) {
        this.faturamentoAtividadeCronograma = faturamentoAtividadeCronograma;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof FaturamentoAtivCronRota) ) return false;
        FaturamentoAtivCronRota castOther = (FaturamentoAtivCronRota) other;
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
