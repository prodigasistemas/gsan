package gcom.atendimentopublico.ordemservico.bean;

import java.math.BigDecimal;

/**
 * [UC0460] Obter Carga de Trabalho da Equipe
 * 
 * @author Leonardo Regis
 * @date 18/09/2006
 */
public class ObterCargaTrabalhoEquipeHelper {

	private BigDecimal percentualCargaTrabalhoPrevista;
	private BigDecimal percentualCargaRealizada;
	
	public ObterCargaTrabalhoEquipeHelper(){}

	public BigDecimal getPercentualCargaRealizada() {
		return percentualCargaRealizada;
	}

	public void setPercentualCargaRealizada(BigDecimal percentualCargaRealizada) {
		this.percentualCargaRealizada = percentualCargaRealizada;
	}

	public BigDecimal getPercentualCargaTrabalhoPrevista() {
		return percentualCargaTrabalhoPrevista;
	}

	public void setPercentualCargaTrabalhoPrevista(
			BigDecimal percentualCargaTrabalhoPrevista) {
		this.percentualCargaTrabalhoPrevista = percentualCargaTrabalhoPrevista;
	}


}
