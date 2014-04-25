package gcom.arrecadacao;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DevolucaoSituacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	//Constantes *********************************
	public final static Integer DEVOLUCAO_CLASSIFICADA = new Integer(1);
	
	public final static Integer DEVOLUCAO_OUTROS_VALORES = new Integer(2);

	public final static Integer PAGAMENTO_DUPLICIDADE_NAO_ENCONTRADO = new Integer(3);

	public final static Integer GUIA_DEVOLUCAO_NAO_INFORMADA = new Integer(4);

	public final static Integer VALOR_NAO_CONFERE = new Integer(5);
	//********************************************
	
	
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoDevolucaoSituacao;

    /** nullable persistent field */
    private String descricaoAbreviado;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public DevolucaoSituacao(String descricaoDevolucaoSituacao, String descricaoAbreviado, Short indicadorUso, Date ultimaAlteracao) {
        this.descricaoDevolucaoSituacao = descricaoDevolucaoSituacao;
        this.descricaoAbreviado = descricaoAbreviado;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public DevolucaoSituacao() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoDevolucaoSituacao() {
        return this.descricaoDevolucaoSituacao;
    }

    public void setDescricaoDevolucaoSituacao(String descricaoDevolucaoSituacao) {
        this.descricaoDevolucaoSituacao = descricaoDevolucaoSituacao;
    }

    public String getDescricaoAbreviado() {
        return this.descricaoAbreviado;
    }

    public void setDescricaoAbreviado(String descricaoAbreviado) {
        this.descricaoAbreviado = descricaoAbreviado;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
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
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

}
