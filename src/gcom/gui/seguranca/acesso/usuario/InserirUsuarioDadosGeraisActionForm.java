package gcom.gui.seguranca.acesso.usuario;

import org.apache.struts.validator.ValidatorActionForm;

public class InserirUsuarioDadosGeraisActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String usuarioTipo = "";

	private String usuarioTipoFuncionario = "false";

	private String empresa = "";

	private String idFuncionario = "";

	private String nomeFuncionario = "";

	private String nome = "";

	private String funcionarioNaoEncontrada = "false";

	private String idLotacao = "";

	private String nomeLotacao = "";

	private String lotacaoNaoEncontrada = "false";

	private String dataInicial = "";

	private String dataFinal = "";

	private String login = "";

	private String email = "";

	private String confirmacaoEmail = "";

	private String[] grupo = null;

	private String abrangencia = "";

	private String gerenciaRegional = "";

	private String idElo = "";

	private String nomeElo = "";

	private String eloNaoEncontrada = "false";

	private String idLocalidade = "";

	private String nomeLocalidade = "";

	private String localidadeNaoEncontrada = "false";

	private String idEmpresaFuncionario = "";

	private String indicadorFuncionario = "";

	private String parmsUsuarioTipo = "";

	private String unidadeNegocio = "";

	private String cpf = "";

	private String dataNascimento;

	private Short indicadorUsuarioBatch;

	private Short indicadorUsuarioInternet;
	
	private String limiteBatch;

	public String getUsuarioTipoFuncionario() {
		return usuarioTipoFuncionario;
	}

	public void setUsuarioTipoFuncionario(String usuarioTipoFuncionario) {
		this.usuarioTipoFuncionario = usuarioTipoFuncionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFuncionarioNaoEncontrada() {
		return funcionarioNaoEncontrada;
	}

	public void setFuncionarioNaoEncontrada(String funcionarioNaoEncontrada) {
		this.funcionarioNaoEncontrada = funcionarioNaoEncontrada;
	}

	public String getLotacaoNaoEncontrada() {
		return lotacaoNaoEncontrada;
	}

	public void setLotacaoNaoEncontrada(String lotacaoNaoEncontrada) {
		this.lotacaoNaoEncontrada = lotacaoNaoEncontrada;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getIdLotacao() {
		return idLotacao;
	}

	public void setIdLotacao(String idLotacao) {
		this.idLotacao = idLotacao;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getNomeLotacao() {
		return nomeLotacao;
	}

	public void setNomeLotacao(String nomeLotacao) {
		this.nomeLotacao = nomeLotacao;
	}

	public String getUsuarioTipo() {
		return usuarioTipo;
	}

	public void setUsuarioTipo(String tipoUsuario) {
		this.usuarioTipo = tipoUsuario;
	}

	public String getAbrangencia() {
		return abrangencia;
	}

	public void setAbrangencia(String abrangencia) {
		this.abrangencia = abrangencia;
	}

	public String getEloNaoEncontrada() {
		return eloNaoEncontrada;
	}

	public void setEloNaoEncontrada(String eloNaoEncontrada) {
		this.eloNaoEncontrada = eloNaoEncontrada;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String[] getGrupo() {
		return grupo;
	}

	public void setGrupo(String[] grupo) {
		this.grupo = grupo;
	}

	public String getIdElo() {
		return idElo;
	}

	public void setIdElo(String idElo) {
		this.idElo = idElo;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getLocalidadeNaoEncontrada() {
		return localidadeNaoEncontrada;
	}

	public void setLocalidadeNaoEncontrada(String localidadeNaoEncontrada) {
		this.localidadeNaoEncontrada = localidadeNaoEncontrada;
	}

	public String getNomeElo() {
		return nomeElo;
	}

	public void setNomeElo(String nomeElo) {
		this.nomeElo = nomeElo;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getIdEmpresaFuncionario() {
		return idEmpresaFuncionario;
	}

	public void setIdEmpresaFuncionario(String idEmpresaFuncionario) {
		this.idEmpresaFuncionario = idEmpresaFuncionario;
	}

	public String getIndicadorFuncionario() {
		return indicadorFuncionario;
	}

	public void setIndicadorFuncionario(String indicadorFuncionario) {
		this.indicadorFuncionario = indicadorFuncionario;
	}

	public String getParmsUsuarioTipo() {
		return parmsUsuarioTipo;
	}

	public void setParmsUsuarioTipo(String parmsUsuarioTipo) {
		this.parmsUsuarioTipo = parmsUsuarioTipo;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getConfirmacaoEmail() {
		return confirmacaoEmail;
	}

	public void setConfirmacaoEmail(String confirmacaoEmail) {
		this.confirmacaoEmail = confirmacaoEmail;
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

	public Short getIndicadorUsuarioBatch() {
		return indicadorUsuarioBatch;
	}

	public void setIndicadorUsuarioBatch(Short indicadorUsuarioBatch) {
		this.indicadorUsuarioBatch = indicadorUsuarioBatch;
	}

	public Short getIndicadorUsuarioInternet() {
		return indicadorUsuarioInternet;
	}

	public void setIndicadorUsuarioInternet(Short indicadorUsuarioInternet) {
		this.indicadorUsuarioInternet = indicadorUsuarioInternet;
	}

	public String getLimiteBatch() {
		return limiteBatch;
	}

	public void setLimiteBatch(String limiteBatch) {
		this.limiteBatch = limiteBatch;
	}
}
