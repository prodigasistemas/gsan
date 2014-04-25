package gcom.gui.relatorio.atendimentopublico.ordemservico;


import org.apache.struts.action.ActionForm;

/**
 * [UC1109] Filtrar Dados para Geração Boletim de Custo de Repavimentação
 * 
 * @author Hugo Leonardo
 *
 * @date 30/12/2010
 */

public class GerarRelatorioBoletimCustoRepavimentacaoPorTipoPavimentoForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idUnidadeRepavimentadora;
	private String mesAnoReferenciaGeracao;
	private String indicadorTipoBoletim;
	

	public void reset(){
		this.idUnidadeRepavimentadora = null;
		this.mesAnoReferenciaGeracao = null;
		this.indicadorTipoBoletim = null;
	}


	public String getIdUnidadeRepavimentadora() {
		return idUnidadeRepavimentadora;
	}


	public void setIdUnidadeRepavimentadora(String idUnidadeRepavimentadora) {
		this.idUnidadeRepavimentadora = idUnidadeRepavimentadora;
	}


	public String getMesAnoReferenciaGeracao() {
		return mesAnoReferenciaGeracao;
	}


	public void setMesAnoReferenciaGeracao(String mesAnoReferenciaGeracao) {
		this.mesAnoReferenciaGeracao = mesAnoReferenciaGeracao;
	}


	public String getIndicadorTipoBoletim() {
		return indicadorTipoBoletim;
	}


	public void setIndicadorTipoBoletim(String indicadorTipoBoletim) {
		this.indicadorTipoBoletim = indicadorTipoBoletim;
	}

}
