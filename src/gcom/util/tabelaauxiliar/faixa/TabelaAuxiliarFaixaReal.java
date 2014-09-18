package gcom.util.tabelaauxiliar.faixa;

import gcom.util.tabelaauxiliar.TabelaAuxiliarAbstrata;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Administrador
 *
 */
public class TabelaAuxiliarFaixaReal extends TabelaAuxiliarAbstrata {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal VolumeMenorFaixa;

    private BigDecimal VolumeMaiorFaixa;
    
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
    public TabelaAuxiliarFaixaReal(Integer id, Date ultimaAlteracao,
            BigDecimal faixaInical, BigDecimal faixaFinal, String faixaCompleta) {
        super.setId(id);
        super.setUltimaAlteracao(ultimaAlteracao);
        this.VolumeMenorFaixa = faixaInical;
        this.VolumeMaiorFaixa = faixaFinal;
        this.faixaCompleta = faixaCompleta;
    }

    /**
     * default constructor
     */
    public TabelaAuxiliarFaixaReal() {
    }

	/**
	 * @return Returns the volumeMaiorFaixa.
	 */
	public BigDecimal getVolumeMaiorFaixa() {
		return VolumeMaiorFaixa;
	}

	/**
	 * @param volumeMaiorFaixa The volumeMaiorFaixa to set.
	 */
	public void setVolumeMaiorFaixa(BigDecimal volumeMaiorFaixa) {
		VolumeMaiorFaixa = volumeMaiorFaixa;
	}

	/**
	 * @return Returns the volumeMenorFaixa.
	 */
	public BigDecimal getVolumeMenorFaixa() {
		return VolumeMenorFaixa;
	}

	/**
	 * @param volumeMenorFaixa The volumeMenorFaixa to set.
	 */
	public void setVolumeMenorFaixa(BigDecimal volumeMenorFaixa) {
		VolumeMenorFaixa = volumeMenorFaixa;
	}

//	@Override
//	public Filtro retornaFiltro() {
//		
//		return null;
//	}

	public String getFaixaCompleta() {
		faixaCompleta = this.getVolumeMenorFaixa() + " a "
				+ this.getVolumeMaiorFaixa() + "m3";
		return faixaCompleta;
	}

	
	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 20/09/2007
	 *
	 * @return
	 */
	public String getFaixaCompletaComId() {
	
		if(this.getId().compareTo(10) == -1){
			faixaCompletaComId = "0" + this.getId() + " - " + this.getVolumeMenorFaixa() + " a " + this.getVolumeMaiorFaixa() + "m3";
		}else{
			faixaCompletaComId = this.getId() + " - " + this.getVolumeMenorFaixa() + " a " + this.getVolumeMaiorFaixa() + "m3";
		}
		
		return faixaCompletaComId;
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

	public String getDescricao(){
		return this.getFaixaCompleta();
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		if (this.getVolumeMenorFaixa() != null && this.getVolumeMaiorFaixa() != null){
			return super.getDescricaoParaRegistroTransacao();
		} else {
			return null;
		}
	}
}
