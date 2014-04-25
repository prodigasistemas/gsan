package gcom.relatorio.atendimentopublico;

import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.medicao.MedicaoHistorico;


/**
 * @author Arthur Carvalho
 * @date 07/05/2010
 * 
 */
public class RelatorioHistoricoMedicaoPocoHelper  {
	
	private ConsumoHistorico consumoHistorico;
	
	private MedicaoHistorico medicaoHistorico;
	
	private ConsumoAnormalidade consumoAnormalidade;

	
	
	/**
	 * @return Returns the consumoAnormalidade.
	 */
	public ConsumoAnormalidade getConsumoAnormalidade() {
		return consumoAnormalidade;
	}

	/**
	 * @param consumoAnormalidade The consumoAnormalidade to set.
	 */
	public void setConsumoAnormalidade(ConsumoAnormalidade consumoAnormalidade) {
		this.consumoAnormalidade = consumoAnormalidade;
	}

	/**
	 * @return Returns the consumoHistorico.
	 */
	public ConsumoHistorico getConsumoHistorico() {
		return consumoHistorico;
	}

	/**
	 * @param consumoHistorico The consumoHistorico to set.
	 */
	public void setConsumoHistorico(ConsumoHistorico consumoHistorico) {
		this.consumoHistorico = consumoHistorico;
	}

	/**
	 * @return Returns the medicaoHistorico.
	 */
	public MedicaoHistorico getMedicaoHistorico() {
		return medicaoHistorico;
	}

	/**
	 * @param medicaoHistorico The medicaoHistorico to set.
	 */
	public void setMedicaoHistorico(MedicaoHistorico medicaoHistorico) {
		this.medicaoHistorico = medicaoHistorico;
	}
	
	
	
}
