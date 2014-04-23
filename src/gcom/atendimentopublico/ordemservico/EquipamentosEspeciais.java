package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;
import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Kássia Albuquerque */


public class EquipamentosEspeciais extends TabelaAuxiliarAbreviada implements  Serializable {
	
	private static final long serialVersionUID = 1L;

/*    *//** identifier field *//*
    private Integer id;

    *//** nullable persistent field */
    //private String descricao;

   /* *//** nullable persistent field *//*
    private String descricaoAbreviada;

    *//** persistent field *//*
    private short indicadorUso;

    *//** persistent field *//*
    private Date ultimaAlteracao;*/
/*
    *//** full constructor *//*
    public EquipamentosEspeciais(String descricao, String descricaoAbreviada, short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    *//** default constructor *//*
    public EquipamentosEspeciais() {
    }

    *//** minimal constructor *//*
    public EquipamentosEspeciais(short indicadorUso, Date ultimaAlteracao) {
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }*/

    /*public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }*/
/*
    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }

    public short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }*/

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
		public Filtro retornaFiltro() {
			return null;
		}
}
