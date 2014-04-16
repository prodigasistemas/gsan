package gcom.cadastro.localidade;

import gcom.util.tabelaauxiliar.indicador.TabelaAuxiliarIndicador;

/** @author Hibernate CodeGenerator */
public class QuadraPerfil extends TabelaAuxiliarIndicador {
	
	private static final long serialVersionUID = 1L;

   /* *//** identifier field *//*
    private Integer id;

    *//** nullable persistent field *//*
    private String descricao;

    *//** nullable persistent field *//*
    private Short indicadorBaixaRenda;

    *//** nullable persistent field *//*
    private Short indicadorUso;

    *//** nullable persistent field *//*
    private Date ultimaAlteracao;

    *//** full constructor *//*
    public QuadraPerfil(String descricao, Short indicadorBaixaRenda,
            Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorBaixaRenda = indicadorBaixaRenda;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    *//** default constructor *//*
    public QuadraPerfil() {
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

    public Short getIndicadorBaixaRenda() {
        return this.indicadorBaixaRenda;
    }

    public void setIndicadorBaixaRenda(Short indicadorBaixaRenda) {
        this.indicadorBaixaRenda = indicadorBaixaRenda;
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
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}*/
}
