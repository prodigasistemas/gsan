package gcom.gui.seguranca.acesso.usuario;


import org.apache.struts.action.ActionForm;

/**
 * [UC1096] Filtrar Solicitação de Acesso
 * 
 * @author Hugo Leonardo
 *
 * @date 16/11/2010
 */

public class FiltrarSolicitacaoAcessoActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idFuncionario; 
	private String nomeFuncionario;
	private String idFuncionarioSolicitante; 
	private String nomeFuncionarioSolicitante;
	private String idFuncionarioSuperior; 
	private String nomeFuncionarioSuperior;
	private String[] idsSituacao; 
	private String idEmpresa;
	private String nomeUsuario; 
	private String idLotacao; 
	private String nomeLotacao;
	private String dataInicial; 
	private String dataFinal; 
	private String atualizar;

	public void reset(){
		
		this.idFuncionario = null;
		this.nomeFuncionario = null;
		this.idFuncionarioSolicitante = null;
		this.nomeFuncionarioSolicitante = null;
		this.idFuncionarioSuperior = null;
		this.nomeFuncionarioSuperior = null;
		this.idsSituacao = null;
		this.idEmpresa = null;
		this.nomeUsuario = null;
		this.idLotacao = null;
		this.nomeLotacao = null;
		this.dataInicial = null;
		this.dataFinal = null;
		this.atualizar = null;
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getIdFuncionarioSolicitante() {
		return idFuncionarioSolicitante;
	}

	public void setIdFuncionarioSolicitante(String idFuncionarioSolicitante) {
		this.idFuncionarioSolicitante = idFuncionarioSolicitante;
	}

	public String getNomeFuncionarioSolicitante() {
		return nomeFuncionarioSolicitante;
	}

	public void setNomeFuncionarioSolicitante(String nomeFuncionarioSolicitante) {
		this.nomeFuncionarioSolicitante = nomeFuncionarioSolicitante;
	}

	public String getIdFuncionarioSuperior() {
		return idFuncionarioSuperior;
	}

	public void setIdFuncionarioSuperior(String idFuncionarioSuperior) {
		this.idFuncionarioSuperior = idFuncionarioSuperior;
	}

	public String getNomeFuncionarioSuperior() {
		return nomeFuncionarioSuperior;
	}

	public void setNomeFuncionarioSuperior(String nomeFuncionarioSuperior) {
		this.nomeFuncionarioSuperior = nomeFuncionarioSuperior;
	}

	public String[] getIdsSituacao() {
		return idsSituacao;
	}

	public void setIdsSituacao(String[] idsSituacao) {
		this.idsSituacao = idsSituacao;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getIdLotacao() {
		return idLotacao;
	}

	public void setIdLotacao(String idLotacao) {
		this.idLotacao = idLotacao;
	}

	public String getNomeLotacao() {
		return nomeLotacao;
	}

	public void setNomeLotacao(String nomeLotacao) {
		this.nomeLotacao = nomeLotacao;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getAtualizar() {
		return atualizar;
	}

	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}
	
}
