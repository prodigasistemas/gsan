package gcom.gui.micromedicao.leitura;

import org.apache.struts.action.ActionForm;

/**
 * Inserir Leiturista
 * 
 * @author Thiago Tenório
 * @date 22/07/2007
 */
public class FiltrarLeituristaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String indicadorAtualizar;
	
	private String idFuncionario;

	private String descricaoFuncionario;
	
	private String empresaID;
	
	private String idCliente;
	
	private String descricaoCliente;

	private String telefone;

	private String ddd;
	
	private String indicadorUso;	
	
    private String tipoPesquisa;
	
	private String atualizar;
	
	private String imei;
	
	private String loginUsuario;	
	private String nomeUsuario;
	

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDescricaoCliente() {
		return descricaoCliente;
	}

	public void setDescricaoCliente(String descricaoCliente) {
		this.descricaoCliente = descricaoCliente;
	}

	public String getDescricaoFuncionario() {
		return descricaoFuncionario;
	}

	public void setDescricaoFuncionario(String descricaoFuncionario) {
		this.descricaoFuncionario = descricaoFuncionario;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getAtualizar() {
		return atualizar;
	}

	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}

	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getEmpresaID() {
		return empresaID;
	}

	public void setEmpresaID(String empresaID) {
		this.empresaID = empresaID;
	}

	/**
	 * @return Returns the imei.
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * @param imei The imei to set.
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
}
