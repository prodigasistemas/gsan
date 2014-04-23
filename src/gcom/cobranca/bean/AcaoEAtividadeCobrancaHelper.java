package gcom.cobranca.bean;

import gcom.cobranca.CobrancaAcao;

import java.io.Serializable;
import java.util.Collection;

/**
 * [UC0312] Inserir Cromograma de Cobrança  
 *
 * @author Vivianne Sousa
 * @date 29/11/2010
 */
public class AcaoEAtividadeCobrancaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private CobrancaAcao acaoCobranca;
	
	private Collection atividadesCobranca;
	

	public Collection getAtividadesCobranca() {
		return atividadesCobranca;
	}

	public void setAtividadesCobranca(Collection atividadesCobranca) {
		this.atividadesCobranca = atividadesCobranca;
	}

	public CobrancaAcao getAcaoCobranca() {
		return acaoCobranca;
	}

	public void setAcaoCobranca(CobrancaAcao acaoCobranca) {
		this.acaoCobranca = acaoCobranca;
	}



	}
