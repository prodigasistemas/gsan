package gcom.arrecadacao.banco;

import gcom.util.filtro.Filtro;
import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Banco extends TabelaAuxiliarAbreviada implements Serializable {
	
	private static final long serialVersionUID = 1L;

//  /*  /** identifier field */
//    private Integer id;
//
//    /** persistent field */
//    private String nomeAbreviado;
//
//    /** persistent field */
//    private int numeroSequenciaRecebimentoFita;
//
//    /** persistent field */
//    private int numeroSequenciaEnvioFita;
//
//    /** nullable persistent field */
//    private String nomeBanco;
//
//    /** nullable persistent field */
//    private Short indicadorUso;
//
//    /** nullable persistent field */
//    private Date ultimaAlteracao;
//
//    /** full constructor */
//    public Banco(String nomeAbreviado, int numeroSequenciaRecebimentoFita, int numeroSequenciaEnvioFita, String nomeBanco, Short indicadorUso, Date ultimaAlteracao) {
//        this.nomeAbreviado = nomeAbreviado;
//        this.numeroSequenciaRecebimentoFita = numeroSequenciaRecebimentoFita;
//        this.numeroSequenciaEnvioFita = numeroSequenciaEnvioFita;
//        this.nomeBanco = nomeBanco;
//        this.indicadorUso = indicadorUso;
//        this.ultimaAlteracao = ultimaAlteracao;
//    }
//
//    /** default constructor */
//    public Banco() {
//    }
//
//    /** minimal constructor */
//    public Banco(String nomeAbreviado, int numeroSequenciaRecebimentoFita, int numeroSequenciaEnvioFita) {
//        this.nomeAbreviado = nomeAbreviado;
//        this.numeroSequenciaRecebimentoFita = numeroSequenciaRecebimentoFita;
//        this.numeroSequenciaEnvioFita = numeroSequenciaEnvioFita;
//    }
//
//    public Integer getId() {
//        return this.id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getNomeAbreviado() {
//        return this.nomeAbreviado;
//    }
//
//    public void setNomeAbreviado(String nomeAbreviado) {
//        this.nomeAbreviado = nomeAbreviado;
//    }
//
//    public int getNumeroSequenciaRecebimentoFita() {
//        return this.numeroSequenciaRecebimentoFita;
//    }
//
//    public void setNumeroSequenciaRecebimentoFita(int numeroSequenciaRecebimentoFita) {
//        this.numeroSequenciaRecebimentoFita = numeroSequenciaRecebimentoFita;
//    }
//
//    public int getNumeroSequenciaEnvioFita() {
//        return this.numeroSequenciaEnvioFita;
//    }
//
//    public void setNumeroSequenciaEnvioFita(int numeroSequenciaEnvioFita) {
//        this.numeroSequenciaEnvioFita = numeroSequenciaEnvioFita;
//    }
//
//    public String getNomeBanco() {
//        return this.nomeBanco;
//    }
//
//    public void setNomeBanco(String nomeBanco) {
//        this.nomeBanco = nomeBanco;
//    }
//
//    public Short getIndicadorUso() {
//        return this.indicadorUso;
//    }
//
//    public void setIndicadorUso(Short indicadorUso) {
//        this.indicadorUso = indicadorUso;
//    }
//
//    public Date getUltimaAlteracao() {
//        return this.ultimaAlteracao;
//    }
//
//    public void setUltimaAlteracao(Date ultimaAlteracao) {
//        this.ultimaAlteracao = ultimaAlteracao;
//    }
//    
//    
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		return null;
	}

}
