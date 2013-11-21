package gcom.gui.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

public class FiltrarAnalisarReleituraImoveisActionForm extends ValidatorActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mesAno;	
	private String idGrupo;	
	private String idRota;	
	private String codigoRota;
	private String descricaoRota;
	
	private String idQuadra;
	private String idSituacaoReleitura;
	private String empresa;
	

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getDescricaoRota() {
		return descricaoRota;
	}

	public void setDescricaoRota(String descricaoRota) {
		this.descricaoRota = descricaoRota;
	}

	public String getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getIdRota() {
		return idRota;
	}

	public void setIdRota(String idRota) {
		this.idRota = idRota;
	}

	public String getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(String idQuadra) {
		this.idQuadra = idQuadra;
	}

	public String getIdSituacaoReleitura() {
		return idSituacaoReleitura;
	}

	public void setIdSituacaoReleitura(String idSituacaoReleitura) {
		this.idSituacaoReleitura = idSituacaoReleitura;
	}

	public String getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}
	
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

}
