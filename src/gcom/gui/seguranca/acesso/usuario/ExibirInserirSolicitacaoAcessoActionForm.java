package gcom.gui.seguranca.acesso.usuario;


import org.apache.struts.action.ActionForm;

/**
 * [UC1092] Inserir Solicitação de Acesso
 * 
 * @author Hugo Leonardo
 *
 * @date 03/11/2010
 */

public class ExibirInserirSolicitacaoAcessoActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idFuncionario; 
	private String nomeFuncionario;
	private String idFuncionarioSolicitante; //
	private String nomeFuncionarioSolicitante;
	private String idFuncionarioSuperior; //
	private String nomeFuncionarioSuperior;
	private String[] idsGrupo; //
	private String idTipoUsuario;
	private String idEmpresa;
	private String nomeUsuario; //
	private String cpf; //
	private String dataNascimento; //
	private String idLotacao; //
	private String nomeLotacao;
	private String login; //
	private String email; //
	private String dataInicial; //
	private String dataFinal; //
	private String icNotificar; //

	public void reset(){
		
		this.idFuncionario = null;
		this.nomeFuncionario = null;
		this.idFuncionarioSolicitante = null;
		this.nomeFuncionarioSolicitante = null;
		this.idFuncionarioSuperior = null;
		this.nomeFuncionarioSuperior = null;
		this.idsGrupo = null;
		this.idTipoUsuario = null;
		this.idEmpresa = null;
		this.nomeUsuario = null;
		this.cpf = null;
		this.dataNascimento = null;
		this.idLotacao = null;
		this.nomeLotacao = null;
		this.login = null;
		this.email = null;
		this.dataInicial = null;
		this.dataFinal = null;
		this.icNotificar = null;
		
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

	public String[] getIdsGrupo() {
		return idsGrupo;
	}

	public void setIdsGrupo(String[] idsGrupo) {
		this.idsGrupo = idsGrupo;
	}

	public String getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(String idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public String getIcNotificar() {
		return icNotificar;
	}

	public void setIcNotificar(String icNotificar) {
		this.icNotificar = icNotificar;
	}
	
}
