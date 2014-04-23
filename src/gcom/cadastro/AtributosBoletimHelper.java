package gcom.cadastro;

import gcom.seguranca.Atributo;

import java.math.BigDecimal;


public class AtributosBoletimHelper {
	
	private Atributo atributo;
	private BigDecimal valorAtualizacaoAtributo;
	private Integer quantidadeAtualizacaoAtributo;
	private AtributosBoletimChaveHelper atributosBoletimChaveHelper;
	
	 /** full constructor */
    public AtributosBoletimHelper(Atributo atributo, BigDecimal valorAtualizacaoAtributo, 
    		Integer quantidadeAtualizacaoAtributo,AtributosBoletimChaveHelper atributosBoletimChaveHelper) {
        this.atributo = atributo;
        this.valorAtualizacaoAtributo = valorAtualizacaoAtributo;
        this.quantidadeAtualizacaoAtributo = quantidadeAtualizacaoAtributo;
        this.atributosBoletimChaveHelper = atributosBoletimChaveHelper;
    }

    /** default constructor */
    public AtributosBoletimHelper() {
    }
	
    public AtributosBoletimHelper(Integer idAtributo, BigDecimal valorAtualizacaoAtributo, Integer quantidadeAtualizacaoAtributo,AtributosBoletimChaveHelper atributosBoletimChaveHelper) {
    	Atributo atributo = new Atributo();
    	atributo.setId(idAtributo);
        this.atributo = atributo;
        this.valorAtualizacaoAtributo = valorAtualizacaoAtributo;
        this.quantidadeAtualizacaoAtributo = quantidadeAtualizacaoAtributo;
        this.atributosBoletimChaveHelper = atributosBoletimChaveHelper;
    }

    public AtributosBoletimHelper(Atributo atributo, BigDecimal valorAtualizacaoAtributo, 
    		Integer quantidadeAtualizacaoAtributo) {
        this.atributo = atributo;
        this.valorAtualizacaoAtributo = valorAtualizacaoAtributo;
        this.quantidadeAtualizacaoAtributo = quantidadeAtualizacaoAtributo;
    }
	
	public Atributo getAtributo() {
		return atributo;
	}
	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}
	public Integer getQuantidadeAtualizacaoAtributo() {
		return quantidadeAtualizacaoAtributo;
	}
	public void setQuantidadeAtualizacaoAtributo(
			Integer quantidadeAtualizacaoAtributo) {
		this.quantidadeAtualizacaoAtributo = quantidadeAtualizacaoAtributo;
	}
	public BigDecimal getValorAtualizacaoAtributo() {
		return valorAtualizacaoAtributo;
	}
	public void setValorAtualizacaoAtributo(BigDecimal valorAtualizacaoAtributo) {
		this.valorAtualizacaoAtributo = valorAtualizacaoAtributo;
	}

	public AtributosBoletimChaveHelper getAtributosBoletimChaveHelper() {
		return atributosBoletimChaveHelper;
	}

	public void setAtributosBoletimChaveHelper(
			AtributosBoletimChaveHelper atributosBoletimChaveHelper) {
		this.atributosBoletimChaveHelper = atributosBoletimChaveHelper;
	}
	
}
