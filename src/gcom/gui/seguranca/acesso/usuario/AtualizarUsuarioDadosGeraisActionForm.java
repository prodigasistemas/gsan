package gcom.gui.seguranca.acesso.usuario;

import org.apache.struts.validator.ValidatorForm;

/**
 * 
 * Form que exibe o menu
 * 
 * @author Sávio Luiz
 * @date 02/05/2006
 */
public class AtualizarUsuarioDadosGeraisActionForm extends ValidatorForm {
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

	private String grupoNaoDesabilitados = null;

	private String indicadorFuncionario = "";

	private String parmsUsuarioTipo = "";

	private String unidadeNegocio = "";
	
	private String situacao = "";
	
	private String cpf = "";
	
	private String dataNascimento;
	
	private Short indicadorUsuarioBatch;
	
	private Short indicadorUsuarioInternet;
	
	/**
	 * @return Retorna o campo situacao.
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao O situacao a ser setado.
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	/**
	 * @return Retorna o campo usuarioTipoFuncionario.
	 */
	public String getUsuarioTipoFuncionario() {
		return usuarioTipoFuncionario;
	}

	/**
	 * @param usuarioTipoFuncionario
	 *            O usuarioTipoFuncionario a ser setado.
	 */
	public void setUsuarioTipoFuncionario(String usuarioTipoFuncionario) {
		this.usuarioTipoFuncionario = usuarioTipoFuncionario;
	}

	/**
	 * @return Retorna o campo nome.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            O nome a ser setado.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return Retorna o campo funcionarioNaoEncontrada.
	 */
	public String getFuncionarioNaoEncontrada() {
		return funcionarioNaoEncontrada;
	}

	/**
	 * @param funcionarioNaoEncontrada
	 *            O funcionarioNaoEncontrada a ser setado.
	 */
	public void setFuncionarioNaoEncontrada(String funcionarioNaoEncontrada) {
		this.funcionarioNaoEncontrada = funcionarioNaoEncontrada;
	}

	/**
	 * @return Retorna o campo lotacaoNaoEncontrada.
	 */
	public String getLotacaoNaoEncontrada() {
		return lotacaoNaoEncontrada;
	}

	/**
	 * @param lotacaoNaoEncontrada
	 *            O lotacaoNaoEncontrada a ser setado.
	 */
	public void setLotacaoNaoEncontrada(String lotacaoNaoEncontrada) {
		this.lotacaoNaoEncontrada = lotacaoNaoEncontrada;
	}

	/**
	 * @return Retorna o campo dataFinal.
	 */
	public String getDataFinal() {
		return dataFinal;
	}

	/**
	 * @param dataFinal
	 *            O dataFinal a ser setado.
	 */
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	/**
	 * @return Retorna o campo dataInicial.
	 */
	public String getDataInicial() {
		return dataInicial;
	}

	/**
	 * @param dataInicial
	 *            O dataInicial a ser setado.
	 */
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	/**
	 * @return Retorna o campo email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            O email a ser setado.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return Retorna o campo empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa
	 *            O empresa a ser setado.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return Retorna o campo idFuncionario.
	 */
	public String getIdFuncionario() {
		return idFuncionario;
	}

	/**
	 * @param idFuncionario
	 *            O idFuncionario a ser setado.
	 */
	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	/**
	 * @return Retorna o campo idLotacao.
	 */
	public String getIdLotacao() {
		return idLotacao;
	}

	/**
	 * @param idLotacao
	 *            O idLotacao a ser setado.
	 */
	public void setIdLotacao(String idLotacao) {
		this.idLotacao = idLotacao;
	}

	/**
	 * @return Retorna o campo login.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            O login a ser setado.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return Retorna o campo nomeFuncionario.
	 */
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	/**
	 * @param nomeFuncionario
	 *            O nomeFuncionario a ser setado.
	 */
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	/**
	 * @return Retorna o campo nomeLotacao.
	 */
	public String getNomeLotacao() {
		return nomeLotacao;
	}

	/**
	 * @param nomeLotacao
	 *            O nomeLotacao a ser setado.
	 */
	public void setNomeLotacao(String nomeLotacao) {
		this.nomeLotacao = nomeLotacao;
	}

	/**
	 * @return Retorna o campo tipoUsuario.
	 */
	public String getUsuarioTipo() {
		return usuarioTipo;
	}

	/**
	 * @param tipoUsuario
	 *            O tipoUsuario a ser setado.
	 */
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

	public String getIdEmpresaFuncionario() {
		return idEmpresaFuncionario;
	}

	public void setIdEmpresaFuncionario(String idEmpresaFuncionario) {
		this.idEmpresaFuncionario = idEmpresaFuncionario;
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

	public String getGrupoNaoDesabilitados() {
		return grupoNaoDesabilitados;
	}

	public void setGrupoNaoDesabilitados(String grupoNaoDesabilitados) {
		this.grupoNaoDesabilitados = grupoNaoDesabilitados;
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

}
