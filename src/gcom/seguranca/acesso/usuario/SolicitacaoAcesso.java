package gcom.seguranca.acesso.usuario;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import java.io.Serializable;
import java.util.Date;

/** @author Hibernate CodeGenerator */
public class SolicitacaoAcesso implements Serializable {

	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** identifier field */
	private Funcionario funcionarioSolicitante;

	/** persistent field */
	private Funcionario funcionarioResponsavel;

	/** persistent field */
	private Short indicadorNotificarResponsavel;

	/** persistent field */
	private UsuarioTipo usuarioTipo;

	/** persistent field */
	private Empresa empresa;

	/** persistent field */
	private Funcionario funcionario;

	/** persistent field */
	private String nomeUsuario;
	
	/** persistent field */
	private String cpf;

	/** persistent field */
	private Date dataNascimento;
	
	/** persistent field */
	private UnidadeOrganizacional unidadeOrganizacional;
	
	/** persistent field */
	private String login;
	
	/** persistent field */
	private String email;
	
	/** persistent field */
	private Date periodoInicial;
	
	/** persistent field */
	private Date periodoFinal;
	
	private SolicitacaoAcessoSituacao solicitacaoAcessoSituacao;

	/** persistent field */
	private Date ultimaAlteracao;
	
	/** persistent field */
	private Date dataAutorizacao;
	
	/** persistent field */
	private Date dataCadastramento;
	
	/** persistent field */
	private Date dataSolicitacao;

	/** default constructor */
	public SolicitacaoAcesso() {

	}

	/** minimal constructor */
	public SolicitacaoAcesso(Funcionario funcionarioSolicitante, Funcionario funcionarioResponsavel, UsuarioTipo usuarioTipo,
			Empresa empresa, Funcionario funcionario, String nomeUsuario, Date dataNascimento, UnidadeOrganizacional unidadeOrganizacional, 
			String login, String email, SolicitacaoAcessoSituacao solicitacaoAcessoSituacao, Date ultimaAlteracao, 
			Date periodoInicial, Date periodoFinal, Date dataSolicitacao) {

		this.funcionarioSolicitante = funcionarioSolicitante;
		this.funcionarioResponsavel = funcionarioResponsavel;
		this.usuarioTipo = usuarioTipo;
		this.empresa = empresa;
		this.funcionario = funcionario;
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.solicitacaoAcessoSituacao = solicitacaoAcessoSituacao;
		this.nomeUsuario = nomeUsuario;
		this.dataNascimento = dataNascimento;
		this.login = login;
		this.email = email;
		this.periodoInicial = periodoInicial;
		this.periodoFinal = periodoFinal;
		this.ultimaAlteracao = ultimaAlteracao;
		this.dataSolicitacao = dataSolicitacao;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Funcionario getFuncionarioResponsavel() {
		return funcionarioResponsavel;
	}

	public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
		this.funcionarioResponsavel = funcionarioResponsavel;
	}

	public Funcionario getFuncionarioSolicitante() {
		return funcionarioSolicitante;
	}

	public void setFuncionarioSolicitante(Funcionario funcionarioSolicitante) {
		this.funcionarioSolicitante = funcionarioSolicitante;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorNotificarResponsavel() {
		return indicadorNotificarResponsavel;
	}

	public void setIndicadorNotificarResponsavel(Short indicadorNotificarResponsavel) {
		this.indicadorNotificarResponsavel = indicadorNotificarResponsavel;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Date getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(Date periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	public Date getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(Date periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public SolicitacaoAcessoSituacao getSolicitacaoAcessoSituacao() {
		return solicitacaoAcessoSituacao;
	}

	public void setSolicitacaoAcessoSituacao(
			SolicitacaoAcessoSituacao solicitacaoAcessoSituacao) {
		this.solicitacaoAcessoSituacao = solicitacaoAcessoSituacao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public UsuarioTipo getUsuarioTipo() {
		return usuarioTipo;
	}

	public void setUsuarioTipo(UsuarioTipo usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataAutorizacao() {
		return dataAutorizacao;
	}

	public void setDataAutorizacao(Date dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}

	public Date getDataCadastramento() {
		return dataCadastramento;
	}

	public void setDataCadastramento(Date dataCadastramento) {
		this.dataCadastramento = dataCadastramento;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

}
