package gcom.faturamento;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class QualidadeAguaPadrao implements Serializable {	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoPadraoTurbidez;

    /** nullable persistent field */
    private String descricaoPadraoPh;

    /** nullable persistent field */
    private String descricaoPadraoCor;

    /** nullable persistent field */
    private String descricaoPadraoCloro;

    /** nullable persistent field */
    private String descricaoPadraoFluor;        
    /** nullable persistent field */	private String descricaoPadraoEColi;    
    /** nullable persistent field */
    private String descricaoPadraoFerro;

    /** nullable persistent field */
    private String descricaoPadraoColiformesTotais;

    /** nullable persistent field */
    private String descricaoPadraoColiformesFecais;

    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private String descricaoNitrato;
   
    /** persistent field */
    private String descricaoPadraoColiformesTermotolerantes;
    
    /** persistent field */
    private String descricaoPadraoAlcalinidade;

	/** full constructor */
    public QualidadeAguaPadrao(Integer id, String descricaoPadraoTurbidez, String descricaoPadraoPh, String descricaoPadraoCor, String descricaoPadraoCloro, String descricaoPadraoFluor, String descricaoPadraoEColi, String descricaoPadraoFerro, String descricaoPadraoColiformesTotais, String descricaoPadraoColiformesFecais, Date ultimaAlteracao) {
        this.id = id;
        this.descricaoPadraoTurbidez = descricaoPadraoTurbidez;
        this.descricaoPadraoPh = descricaoPadraoPh;
        this.descricaoPadraoCor = descricaoPadraoCor;
        this.descricaoPadraoCloro = descricaoPadraoCloro;
        this.descricaoPadraoFluor = descricaoPadraoFluor;                this.descricaoPadraoEColi = descricaoPadraoEColi;
        this.descricaoPadraoFerro = descricaoPadraoFerro;
        this.descricaoPadraoColiformesTotais = descricaoPadraoColiformesTotais;
        this.descricaoPadraoColiformesFecais = descricaoPadraoColiformesFecais;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public QualidadeAguaPadrao() {
    }

    /** minimal constructor */
    public QualidadeAguaPadrao(Integer id, Date ultimaAlteracao) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

 

    public String getDescricaoPadraoCloro() {
		return descricaoPadraoCloro;
	}

	public void setDescricaoPadraoCloro(String descricaoPadraoCloro) {
		this.descricaoPadraoCloro = descricaoPadraoCloro;
	}

	public String getDescricaoPadraoColiformesFecais() {
		return descricaoPadraoColiformesFecais;
	}

	public void setDescricaoPadraoColiformesFecais(
			String descricaoPadraoColiformesFecais) {
		this.descricaoPadraoColiformesFecais = descricaoPadraoColiformesFecais;
	}

	public String getDescricaoPadraoColiformesTotais() {
		return descricaoPadraoColiformesTotais;
	}

	public void setDescricaoPadraoColiformesTotais(
			String descricaoPadraoColiformesTotais) {
		this.descricaoPadraoColiformesTotais = descricaoPadraoColiformesTotais;
	}

	public String getDescricaoPadraoCor() {
		return descricaoPadraoCor;
	}

	public void setDescricaoPadraoCor(String descricaoPadraoCor) {
		this.descricaoPadraoCor = descricaoPadraoCor;
	}

	public String getDescricaoPadraoFerro() {
		return descricaoPadraoFerro;
	}

	public void setDescricaoPadraoFerro(String descricaoPadraoFerro) {
		this.descricaoPadraoFerro = descricaoPadraoFerro;
	}

	public String getDescricaoPadraoFluor() {
		return descricaoPadraoFluor;
	}

	public void setDescricaoPadraoFluor(String descricaoPadraoFluor) {
		this.descricaoPadraoFluor = descricaoPadraoFluor;
	}

	public String getDescricaoPadraoPh() {
		return descricaoPadraoPh;
	}

	public void setDescricaoPadraoPh(String descricaoPadraoPh) {
		this.descricaoPadraoPh = descricaoPadraoPh;
	}

	public String getDescricaoPadraoTurbidez() {
		return descricaoPadraoTurbidez;
	}

	public void setDescricaoPadraoTurbidez(String descricaoPadraoTurbidez) {
		this.descricaoPadraoTurbidez = descricaoPadraoTurbidez;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public String getDescricaoNitrato() {
		return descricaoNitrato;
	}

	public void setDescricaoNitrato(String descricaoNitrato) {
		this.descricaoNitrato = descricaoNitrato;
	}

	public String getDescricaoPadraoColiformesTermotolerantes() {
		return descricaoPadraoColiformesTermotolerantes;
	}

	public void setDescricaoPadraoColiformesTermotolerantes(
			String descricaoPadraoColiformesTermotolerantes) {
		this.descricaoPadraoColiformesTermotolerantes = descricaoPadraoColiformesTermotolerantes;
	}
	public String getDescricaoPadraoAlcalinidade() {
		return descricaoPadraoAlcalinidade;
	}



	public void setDescricaoPadraoAlcalinidade(String descricaoPadraoAlcalinidade) {
		this.descricaoPadraoAlcalinidade = descricaoPadraoAlcalinidade;
	}	public String getDescricaoPadraoEColi() {		return descricaoPadraoEColi;	}	public void setDescricaoPadraoEColi(String descricaoPadraoEColi) {		this.descricaoPadraoEColi = descricaoPadraoEColi;	}
}
