package gcom.cobranca;

import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ParcelamentoGrupo extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;
	
	//constantes >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public final static Integer DOCUMENTOS_EMITIDOS = new Integer(1);
	public final static Integer FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO = new Integer(2);
	public final static Integer FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO = new Integer(3);
	public final static Integer PARCELAMENTOS_A_COBRAR_CURTO_PRAZO = new Integer(4);
	public final static Integer PARCELAMENTOS_A_COBRAR_LONGO_PRAZO = new Integer(5);
	public final static Integer JUROS_COBRADOS = new Integer(6);
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String descricaoAbreviada;

    /** nullable persistent field */
    private Date ultimaAlteracao;

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
    
    /** full constructor */
    public ParcelamentoGrupo(String descricao, String descricaoAbreviada, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public ParcelamentoGrupo() {
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
