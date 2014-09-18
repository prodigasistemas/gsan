package gcom.util.tabelaauxiliar.faixa;

import gcom.util.tabelaauxiliar.TabelaAuxiliarAbstrata;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Rômulo Aurélio
 *
 */
public class TabelaAuxiliarFaixaInteiro extends TabelaAuxiliarAbstrata {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer menorFaixa;

    private Integer maiorFaixa;
    
    private String faixaCompleta;
    
    private String faixaCompletaComId;
    /**
     * full constructor
     * 
     * @param id
     *            Descrição do parâmetro
     * @param faixaInical
     *            Descrição do parâmetro
     * @param faixaFinal
     *            Descrição do parâmetro
     */
    public TabelaAuxiliarFaixaInteiro(Integer id, Date ultimaAlteracao,
    		Integer menorFaixa, Integer maiorFaixa, String faixaCompleta) {
        super.setId(id);
        super.setUltimaAlteracao(ultimaAlteracao);
        this.menorFaixa = menorFaixa;
        this.maiorFaixa = maiorFaixa;
        this.faixaCompleta = faixaCompleta;
    }

    /**
     * default constructor
     */
    public TabelaAuxiliarFaixaInteiro() {
    }

	

	/**
	 * @return Returns the maiorFaixa.
	 */
	public Integer getMaiorFaixa() {
		return maiorFaixa;
	}

	/**
	 * @param maiorFaixa The maiorFaixa to set.
	 */
	public void setMaiorFaixa(Integer maiorFaixa) {
		this.maiorFaixa = maiorFaixa;
	}

	/**
	 * @return Returns the menorFaixa.
	 */
	public Integer getMenorFaixa() {
		return menorFaixa;
	}

	/**
	 * @param menorFaixa The menorFaixa to set.
	 */
	public void setMenorFaixa(Integer menorFaixa) {
		this.menorFaixa = menorFaixa;
	}

//	@Override
//	public Filtro retornaFiltro() {
//		
//		return null;
//	}

	/**
    * Retorna o valor de faixaCompleta
    * 
    * @return O valor de faixaCompleta
    */
   public String getFaixaCompleta() {
       faixaCompleta = this.getMenorFaixa() + " a " + this.getMaiorFaixa()
               + "m2";
       return faixaCompleta;
   }

	/**
	 * @return Returns the faixaCompleta.
	 */
	
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	/**
	 * @param faixaCompleta The faixaCompleta to set.
	 */
	public void setFaixaCompleta(String faixaCompleta) {
		this.faixaCompleta = faixaCompleta;
	}

	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 26/09/2007
	 *
	 * @return
	 */
	public String getFaixaCompletaComId() {
		
		if(this.getId().compareTo(10) == -1){
			faixaCompletaComId = "0" + getId() + " - " + this.getMenorFaixa() + " a " + this.getMaiorFaixa() + "m2";
		}else{
			faixaCompletaComId = getId() + " - " + this.getMenorFaixa() + " a " + this.getMaiorFaixa() + "m2";
		}
	       return faixaCompletaComId;
	   }
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		if(this.getMenorFaixa() != null && this.getMaiorFaixa() != null) {
			return getFaixaCompleta();	
		} 
		return null;
	}
}
