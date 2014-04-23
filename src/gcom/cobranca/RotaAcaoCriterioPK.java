package gcom.cobranca;

import gcom.interceptor.ObjetoGcom;


/** @author Hibernate CodeGenerator */
public class RotaAcaoCriterioPK extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer rotaId;

    /** identifier field */
    private Integer cobrancaAcaoId;

    /** full constructor */
    public RotaAcaoCriterioPK (Integer rotaId, Integer cobrancaAcaoId) {
        this.rotaId = rotaId;
        this.cobrancaAcaoId = cobrancaAcaoId;
    }

    /** default constructor */
    public RotaAcaoCriterioPK () {

    }

	/**
	 * @return Retorna o campo cobrancaAcaoId.
	 */
	public Integer getCobrancaAcaoId() {
		return cobrancaAcaoId;
	}

	/**
	 * @param cobrancaAcaoId O cobrancaAcaoId a ser setado.
	 */
	public void setCobrancaAcaoId(Integer cobrancaAcaoId) {
		this.cobrancaAcaoId = cobrancaAcaoId;
	}

	/**
	 * @return Retorna o campo rotaId.
	 */
	public Integer getRotaId() {
		return rotaId;
	}

	/**
	 * @param rotaId O rotaId a ser setado.
	 */
	public void setRotaId(Integer rotaId) {
		this.rotaId = rotaId;
	}
	
	
	public boolean equals(Object obj) {
		if (obj instanceof RotaAcaoCriterioPK) {
			RotaAcaoCriterioPK pk = (RotaAcaoCriterioPK)obj;
			
			if (pk.getCobrancaAcaoId() != null) {
				// se os atributos forem diferentes
				if (!pk.getCobrancaAcaoId().equals(this.getCobrancaAcaoId())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getCobrancaAcaoId() != null) {
				return false;
			}
			
			if (pk.getRotaId() != null) {
				// se os atributos forem diferentes
				if (!pk.getRotaId().equals(this.getRotaId())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getRotaId() != null) {
				return false;
			}
			
		} else {
			// se o objeto passado nao for do tipo ImovelResumoLigacaoEconomiaHelper 
			return false;
		}

		// todos os parametros sao iguais
		return true;
	}
	
	
	public int hashCode() {
		
		String retorno =  
		
		this.getCobrancaAcaoId() + "sdf" +
		this.getRotaId() + "sdf";

		return retorno.hashCode();
	}
	
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "rotaId";
		retorno[1] = "cobrancaAcaoId";
		return retorno;
	}
}
