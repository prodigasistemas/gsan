package gcom.gui.cadastro.imovel;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class InformarImovelSituacaoCobrancaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idImovel;
	private String descricaoImovel;
	
	private String[] idRegistrosRemocao;
	
	public String getDescricaoImovel() {
		return descricaoImovel;
	}
	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String[] getIdRegistrosRemocao() {
		return idRegistrosRemocao;
	}
	public void setIdRegistrosRemocao(String[] idRegistrosRemocao) {
		this.idRegistrosRemocao = idRegistrosRemocao;
	}
	
	
}
