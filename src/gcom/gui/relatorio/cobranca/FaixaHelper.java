package gcom.gui.relatorio.cobranca;

import java.io.Serializable;

public class FaixaHelper implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	private Integer valorInicial;
	private Integer valorFinal;

	public FaixaHelper(String descricao, Integer valorInicial,
			Integer valorFinal) {
		this.descricao = descricao;
		this.valorInicial = valorInicial;
		this.valorFinal = valorFinal;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getValorInicial() {
		return valorInicial;
	}
	public void setValorInicial(Integer valorInicial) {
		this.valorInicial = valorInicial;
	}
	public Integer getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(Integer valorFinal) {
		this.valorFinal = valorFinal;
	}
	
	
	/*
	 * Metodo que verifica se faixa esta contida no intervalo de outra faixa
	 */
	public boolean verificarFaixa(FaixaHelper faixa){
		boolean retorno = false;
		
		if(this.getValorInicial()>=faixa.getValorInicial()
				&& this.getValorInicial()<=faixa.getValorFinal()){
			retorno = true;
		}
		if(this.getValorFinal()<=faixa.getValorFinal()
				&& this.getValorFinal()>=faixa.getValorInicial()){
			retorno = true;
		}
		
		return retorno;
		
	}

	@Override
	public boolean equals(Object obj) {
		
		boolean retorno = false;
		
		if (obj instanceof FaixaHelper) {
			FaixaHelper faixa = (FaixaHelper) obj;
			
			if(faixa.getDescricao().equals(this.descricao)
					&& faixa.getValorInicial().compareTo(this.valorInicial)==0
						&& faixa.getValorFinal().compareTo(this.valorFinal)==0){
				
				retorno = true;
				
			}
		}
		return retorno;
	}
	
}	
