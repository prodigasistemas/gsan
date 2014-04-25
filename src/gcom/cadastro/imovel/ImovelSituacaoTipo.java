package gcom.cadastro.imovel;

import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ImovelSituacaoTipo extends ObjetoGcom{
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoImovelSituacaoTipo;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    public final static Integer ATIVO = new Integer(1);

    public final static Integer INATIVO = new Integer(2);

    public final static Integer LIGADO_SO_ESGOTO = new Integer(3);

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
    
    /** full constructor */
    public ImovelSituacaoTipo(String descricaoImovelSituacaoTipo, Date ultimaAlteracao) {
        this.descricaoImovelSituacaoTipo = descricaoImovelSituacaoTipo;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public ImovelSituacaoTipo() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoImovelSituacaoTipo() {
        return this.descricaoImovelSituacaoTipo;
    }

    public void setDescricaoImovelSituacaoTipo(String descricaoImovelSituacaoTipo) {
        this.descricaoImovelSituacaoTipo = descricaoImovelSituacaoTipo;
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

}
