package gcom.gui.faturamento;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * Prescrever Imoveis Publicos
 * 
 * @author Hugo Leonardo
 */
public class PrescreverImoveisPublicosActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idCliente;
	private String descricaoCliente;
	private String idImovel;
	private String descricaoImovel;
	private String[] idsEsferaPoder;
	private String periodoInicial;
	private String periodoFinal;
	private String formaPrescricao;
	
	public String getDescricaoCliente() {
		return descricaoCliente;
	}
	
	public void setDescricaoCliente(String descricaoCliente) {
		this.descricaoCliente = descricaoCliente;
	}
	
	public String getDescricaoImovel() {
		return descricaoImovel;
	}
	
	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}
	
	public String getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getIdImovel() {
		return idImovel;
	}
	
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	
	public String getPeriodoFinal() {
		return periodoFinal;
	}
	
	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
	}
	
	public String getPeriodoInicial() {
		return periodoInicial;
	}
	
	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public String getFormaPrescricao() {
		return formaPrescricao;
	}

	public void setFormaPrescricao(String formaPrescricao) {
		this.formaPrescricao = formaPrescricao;
	}

	public String[] getIdsEsferaPoder() {
		return idsEsferaPoder;
	}

	public void setIdsEsferaPoder(String[] idsEsferaPoder) {
		this.idsEsferaPoder = idsEsferaPoder;
	}
	
}
