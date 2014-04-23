package gcom.arrecadacao;

import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ArrecadadorMovimentoItem extends ObjetoGcom {

	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String conteudoRegistro;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private String descricaoOcorrencia;

    /** nullable persistent field */
    private Short indicadorAceitacao;

    /** persistent field */
    private gcom.arrecadacao.ArrecadadorMovimento arrecadadorMovimento;

    /** persistent field */
    private gcom.arrecadacao.RegistroCodigo registroCodigo;
    
    private Imovel imovel;
    
    
    public final static Short INDICADOR_ACEITO = 1;
    public final static String DESCRICAO_INDICADOR_ACEITO = "ACEITO";
    
    public final static Short INDICADOR_NAO_ACEITO = 2;
    public final static String DESCRICAO_INDICADOR_NAO_ACEITO = "NÃO ACEITO";

    /** full constructor */
    public ArrecadadorMovimentoItem(String conteudoRegistro, Date ultimaAlteracao, String descricaoOcorrencia, Short indicadorAceitacao, gcom.arrecadacao.ArrecadadorMovimento arrecadadorMovimento, gcom.arrecadacao.RegistroCodigo registroCodigo) {
        this.conteudoRegistro = conteudoRegistro;
        this.ultimaAlteracao = ultimaAlteracao;
        this.descricaoOcorrencia = descricaoOcorrencia;
        this.indicadorAceitacao = indicadorAceitacao;
        this.arrecadadorMovimento = arrecadadorMovimento;
        this.registroCodigo = registroCodigo;
    }

    /** default constructor */
    public ArrecadadorMovimentoItem() {
    }

    /** minimal constructor */
    public ArrecadadorMovimentoItem(gcom.arrecadacao.ArrecadadorMovimento arrecadadorMovimento, gcom.arrecadacao.RegistroCodigo registroCodigo) {
        this.arrecadadorMovimento = arrecadadorMovimento;
        this.registroCodigo = registroCodigo;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConteudoRegistro() {
        return this.conteudoRegistro;
    }

    public void setConteudoRegistro(String conteudoRegistro) {
        this.conteudoRegistro = conteudoRegistro;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String getDescricaoOcorrencia() {
        return this.descricaoOcorrencia;
    }

    public void setDescricaoOcorrencia(String descricaoOcorrencia) {
        this.descricaoOcorrencia = descricaoOcorrencia;
    }

    public Short getIndicadorAceitacao() {
        return this.indicadorAceitacao;
    }

    public void setIndicadorAceitacao(Short indicadorAceitacao) {
        this.indicadorAceitacao = indicadorAceitacao;
    }

    public gcom.arrecadacao.ArrecadadorMovimento getArrecadadorMovimento() {
        return this.arrecadadorMovimento;
    }

    public void setArrecadadorMovimento(gcom.arrecadacao.ArrecadadorMovimento arrecadadorMovimento) {
        this.arrecadadorMovimento = arrecadadorMovimento;
    }

    public gcom.arrecadacao.RegistroCodigo getRegistroCodigo() {
        return this.registroCodigo;
    }

    public void setRegistroCodigo(gcom.arrecadacao.RegistroCodigo registroCodigo) {
        this.registroCodigo = registroCodigo;
    }

    public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
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
