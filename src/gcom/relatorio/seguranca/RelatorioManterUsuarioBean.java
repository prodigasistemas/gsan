package gcom.relatorio.seguranca;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Arthur Carvalho
 * @created 09/04/2008
 */
public class RelatorioManterUsuarioBean implements RelatorioBean {

	private String nome;

	private String tipo;

	private String unidadeOrganizacional;

	private String situacao;
	
	private String abrangenciaAcesso;

	private String login;

	private String dataCadastroFim;

	private String grupo;

	/**
	 * Construtor da classe RelatorioManterUsuarioBean
	 */
	public RelatorioManterUsuarioBean(String nome, String tipo,
			String unidadeOrganizacional, String situacao,
			String abrangenciaAcesso, String login,
			String dataCadastroFim, String grupo) {
		this.nome = nome;
		this.tipo = tipo;
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.situacao = situacao;
		this.abrangenciaAcesso = abrangenciaAcesso;
		this.login = login;
		this.dataCadastroFim = dataCadastroFim;
		this.grupo = grupo;
	}

	/**
	 * @return Retorna o campo abrangenciaAcesso.
	 */
	public String getAbrangenciaAcesso() {
		return abrangenciaAcesso;
	}

	/**
	 * @param abrangenciaAcesso O abrangenciaAcesso a ser setado.
	 */
	public void setAbrangenciaAcesso(String abrangenciaAcesso) {
		this.abrangenciaAcesso = abrangenciaAcesso;
	}

	/**
	 * @return Retorna o campo login.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login O login a ser setado.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return Retorna o campo dataCadastroFim.
	 */
	public String getDataCadastroFim() {
		return dataCadastroFim;
	}

	/**
	 * @param dataCadastroFim O dataCadastroFim a ser setado.
	 */
	public void setDataCadastroFim(String dataCadastroFim) {
		this.dataCadastroFim = dataCadastroFim;
	}

	/**
	 * @return Retorna o campo grupo.
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo O grupo a ser setado.
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/**
	 * @return Retorna o campo nome.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome O nome a ser setado.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

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
	 * @return Retorna o campo tipo.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo O tipo a ser setado.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return Retorna o campo unidadeOrganizacional.
	 */
	public String getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	/**
	 * @param unidadeOrganizacional O unidadeOrganizacional a ser setado.
	 */
	public void setUnidadeOrganizacional(String unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}


}
