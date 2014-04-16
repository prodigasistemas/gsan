package gcom.cobranca;

import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.Rota;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CobrancaAtividadeComandoRota extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private gcom.cobranca.CobrancaAtividadeComandoRotaPK comp_id;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Rota rota;

    /** nullable persistent field */
    private gcom.cobranca.CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando;

    /** full constructor */
    public CobrancaAtividadeComandoRota(gcom.cobranca.CobrancaAtividadeComandoRotaPK comp_id, Date ultimaAlteracao, Rota rota, gcom.cobranca.CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.rota = rota;
        this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
    }

    /** default constructor */
    public CobrancaAtividadeComandoRota() {
    }

    /** minimal constructor */
    public CobrancaAtividadeComandoRota(gcom.cobranca.CobrancaAtividadeComandoRotaPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.cobranca.CobrancaAtividadeComandoRotaPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.cobranca.CobrancaAtividadeComandoRotaPK comp_id) {
        this.comp_id = comp_id;
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

    public gcom.cobranca.CobrancaAcaoAtividadeComando getCobrancaAcaoAtividadeComando() {
        return this.cobrancaAcaoAtividadeComando;
    }

    public void setCobrancaAcaoAtividadeComando(gcom.cobranca.CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
        this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof CobrancaAtividadeComandoRota) ) return false;
        CobrancaAtividadeComandoRota castOther = (CobrancaAtividadeComandoRota) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}    
    
	public Filtro retornaFiltro(){
		FiltroCobrancaAtividadeComandoRotas filtroCobrancaAtividadeComandoRota = new FiltroCobrancaAtividadeComandoRotas();
		
		filtroCobrancaAtividadeComandoRota.adicionarCaminhoParaCarregamentoEntidade("comp_id");
		filtroCobrancaAtividadeComandoRota.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoAtividadeComando");

		
		filtroCobrancaAtividadeComandoRota.adicionarParametro(
						new ParametroSimples(FiltroCobrancaAtividadeComandoRotas.COMP_ID_COBRANCA_ACAO_ATIVIDADE_COMANDO_ID, cobrancaAcaoAtividadeComando.getId()));
		
		filtroCobrancaAtividadeComandoRota.adicionarParametro(
				new ParametroSimples(FiltroCobrancaAtividadeComandoRotas.COMP_ID_ROTA_ID, rota.getId()));
		
		return filtroCobrancaAtividadeComandoRota; 
	}
    
    
}
