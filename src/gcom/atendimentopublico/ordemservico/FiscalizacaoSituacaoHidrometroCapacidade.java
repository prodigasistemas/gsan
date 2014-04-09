package gcom.atendimentopublico.ordemservico;

import gcom.micromedicao.hidrometro.HidrometroCapacidade;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FiscalizacaoSituacaoHidrometroCapacidade implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private FiscalizacaoSituacaoHidrometroCapacidadePK comp_id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private HidrometroCapacidade hidrometroCapacidade;

    /** nullable persistent field */
    private FiscalizacaoSituacao fiscalizacaoSituacao;


    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }


	/**
	 * @return Retorna o campo comp_id.
	 */
	public FiscalizacaoSituacaoHidrometroCapacidadePK getComp_id() {
		return comp_id;
	}


	/**
	 * @param comp_id O comp_id a ser setado.
	 */
	public void setComp_id(FiscalizacaoSituacaoHidrometroCapacidadePK comp_id) {
		this.comp_id = comp_id;
	}


	/**
	 * @return Retorna o campo fiscalizacaoSituacao.
	 */
	public FiscalizacaoSituacao getFiscalizacaoSituacao() {
		return fiscalizacaoSituacao;
	}


	/**
	 * @param fiscalizacaoSituacao O fiscalizacaoSituacao a ser setado.
	 */
	public void setFiscalizacaoSituacao(FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}


	/**
	 * @return Retorna o campo hidrometroCapacidade.
	 */
	public HidrometroCapacidade getHidrometroCapacidade() {
		return hidrometroCapacidade;
	}


	/**
	 * @param hidrometroCapacidade O hidrometroCapacidade a ser setado.
	 */
	public void setHidrometroCapacidade(HidrometroCapacidade hidrometroCapacidade) {
		this.hidrometroCapacidade = hidrometroCapacidade;
	}


	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}


	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
