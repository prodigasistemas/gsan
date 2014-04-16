package gcom.gui.faturamento.consumotarifa;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltrarConsumoTarifaSubCategoriaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String descTarifa;
	private String dataVigencia;
	private String dataVigenciaFim;
	private String atualizarFiltro;
	
	
	
	public String getDataVigencia() {
		return dataVigencia;
	}
	public void setDataVigencia(String dataVigencia) {
		this.dataVigencia = dataVigencia;
	}
	public String getDescTarifa() {
		return descTarifa;
	}
	public void setDescTarifa(String descTarifa) {
		this.descTarifa = descTarifa;
	}
	public String getDataVigenciaFim() {
		return dataVigenciaFim;
	}
	public void setDataVigenciaFim(String dataVigenciaFim) {
		this.dataVigenciaFim = dataVigenciaFim;
	}
	/**
	 * @return Retorna o campo atualizarFiltro.
	 */
	public String getAtualizarFiltro() {
		return atualizarFiltro;
	}
	/**
	 * @param atualizarFiltro O atualizarFiltro a ser setado.
	 */
	public void setAtualizarFiltro(String atualizarFiltro) {
		this.atualizarFiltro = atualizarFiltro;
	}
}
