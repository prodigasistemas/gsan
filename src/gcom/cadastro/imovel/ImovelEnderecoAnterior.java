package gcom.cadastro.imovel;

import gcom.interceptor.ObjetoGcom;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ImovelEnderecoAnterior extends ObjetoGcom implements Serializable  {
	
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String enderecoAnterior;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private gcom.cadastro.imovel.Imovel imovel;

    /** full constructor */
    public ImovelEnderecoAnterior(String enderecoAnterior,
            Date ultimaAlteracao, gcom.cadastro.imovel.Imovel imovel) {
        this.enderecoAnterior = enderecoAnterior;
        this.ultimaAlteracao = ultimaAlteracao;
        this.imovel = imovel;
    }

    /** default constructor */
    public ImovelEnderecoAnterior() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnderecoAnterior() {
        return this.enderecoAnterior;
    }

    public void setEnderecoAnterior(String enderecoAnterior) {
        this.enderecoAnterior = enderecoAnterior;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cadastro.imovel.Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(gcom.cadastro.imovel.Imovel imovel) {
        this.imovel = imovel;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
 	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}
}
