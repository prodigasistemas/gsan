package gcom.gui.micromedicao;

import gcom.micromedicao.ItemServicoContrato;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0xxx] Informar Valor de Item de Servico Por Contrato
 * 
 * @author Hugo Leonardo
 * @date 26/07/2010
 */

public class AtualizarItensContratoServicoActionForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idEmpresa;
	
	private String idContrato;

	private String numeroContrato;

	private String dtInicioContrato;
	
	private String dtFimContrato;
	
	private String idItemContrato;
	
	private String valorItemContrato;
	
	//Colecao Componentes do arrecadador contrato tarifa
	private Collection<ItemServicoContrato> colecaoItensContrato = new ArrayList<ItemServicoContrato>();
	

	public String getDtFimContrato() {
		return dtFimContrato;
	}

	public void setDtFimContrato(String dtFimContrato) {
		this.dtFimContrato = dtFimContrato;
	}

	public String getDtInicioContrato() {
		return dtInicioContrato;
	}

	public void setDtInicioContrato(String dtInicioContrato) {
		this.dtInicioContrato = dtInicioContrato;
	}

	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Collection<ItemServicoContrato> getColecaoItensContrato() {
		return colecaoItensContrato;
	}

	public void setColecaoItensContrato(
			Collection<ItemServicoContrato> colecaoItensContrato) {
		this.colecaoItensContrato = colecaoItensContrato;
	}

	public String getIdItemContrato() {
		return idItemContrato;
	}

	public void setIdItemContrato(String idItemContrato) {
		this.idItemContrato = idItemContrato;
	}

	public String getValorItemContrato() {
		return valorItemContrato;
	}

	public void setValorItemContrato(String valorItemContrato) {
		this.valorItemContrato = valorItemContrato;
	}
	
}
