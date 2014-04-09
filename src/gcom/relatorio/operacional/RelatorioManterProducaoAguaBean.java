package gcom.relatorio.operacional;

import gcom.relatorio.RelatorioBean;


public class RelatorioManterProducaoAguaBean implements RelatorioBean {
	
	private String id;
	
	private String anoMesReferencia;

	private String localidade;
	
	private String volumeProduzido;

	/**
	 * Construtor da classe RelatorioManterMotivoCorteBean
	 * 
	 * @param codigo
	 *            Descrição do parâmetro
	 * @param gerenciaRegional
	 *            Descrição do parâmetro
	 * @param nome
	 *            Descrição do parâmetro
	 * @param indicadorUso
	 *            Descrição do parâmetro
	 */

	public RelatorioManterProducaoAguaBean(String id, String anoMesReferencia, String localidade,
			String volumeProduzido) {
		this.id = id;
		this.anoMesReferencia = anoMesReferencia;
		this.localidade = localidade;
		this.volumeProduzido = volumeProduzido;
	}

	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getVolumeProduzido() {
		return volumeProduzido;
	}

	public void setVolumeProduzido(String volumeProduzido) {
		this.volumeProduzido = volumeProduzido;
	}

	
}
