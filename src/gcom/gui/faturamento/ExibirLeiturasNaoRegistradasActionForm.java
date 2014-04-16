package gcom.gui.faturamento;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * Descrição da classe 
 *
 * @author Vinicius Medeiros
 * @date 10/03/2009
 */

public class ExibirLeiturasNaoRegistradasActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String localidade;
	
	private String idFaturamento;
	
	private String mesAno;
	
	private String setorComercial;
	
	private String codigoRota;
	
	private String leiturasNaoRegistradas;
	

	public String getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	public String getLeiturasNaoRegistradas() {
		return leiturasNaoRegistradas;
	}

	public void setLeiturasNaoRegistradas(String leiturasNaoRegistradas) {
		this.leiturasNaoRegistradas = leiturasNaoRegistradas;
	}

	public String getIdFaturamento() {
		return idFaturamento;
	}

	public void setIdFaturamento(String idFaturamento) {
		this.idFaturamento = idFaturamento;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}


}
