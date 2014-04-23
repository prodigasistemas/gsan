package gcom.gui.micromedicao.hidrometro;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author gcom
 *
 */
public class ConsultarHistoricoInstalacaoHidrometroActionForm extends ValidatorActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoImovel;
	
	private String codigoHidrometro;
	
	private String inscricaoImovel;
	
	private String descricaoHidrometro;

	public String getCodigoHidrometro() {
		return codigoHidrometro;
	}

	public void setCodigoHidrometro(String codigoHidrometro) {
		this.codigoHidrometro = codigoHidrometro;
	}

	public String getCodigoImovel() {
		return codigoImovel;
	}

	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	public String getDescricaoHidrometro() {
		return descricaoHidrometro;
	}

	public void setDescricaoHidrometro(String descricaoHidrometro) {
		this.descricaoHidrometro = descricaoHidrometro;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	

}
