package gcom.seguranca.acesso.usuario;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao()
public class Usuario extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	public static final int ATRIBUTOS_INSERIR_USUARIO = 58;
	public static final int ATRIBUTOS_ATUALIZAR_USUARIO = 59;
	public static final int ATRIBUTOS_REMOVER_USUARIO = 60;
	public static final int ATRIBUTOS_USUARIO_ALTERAR_SENHA = 52;
	public static final int ATRIBUTOS_USUARIO_ALTERAR_SENHA_LOGIN = 818;

	public static final String USUARIO_LOGADO = "usuarioLogado";
	public static final Usuario USUARIO_BATCH;

	static {
		USUARIO_BATCH = new Usuario();
		USUARIO_BATCH.setId(new Integer(1));
		UsuarioAbrangencia usuarioAbrangencia = new UsuarioAbrangencia();
		usuarioAbrangencia.setId(1);
		USUARIO_BATCH.setUsuarioAbrangencia(usuarioAbrangencia);
	}

	public static final Usuario USUARIO_TESTE;

	static {
		USUARIO_TESTE = new Usuario();
		USUARIO_TESTE.setId(new Integer(1));
		USUARIO_TESTE.setLogin("pedro");
		USUARIO_TESTE.setUltimaAlteracao(Util.converteStringParaDate("01/01/2006"));
		UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
		unidadeOrganizacional.setId(3);
		USUARIO_TESTE.setUnidadeOrganizacional(unidadeOrganizacional);
		UsuarioAbrangencia usuarioAbrangencia = new UsuarioAbrangencia();
		usuarioAbrangencia.setId(2);
		USUARIO_TESTE.setUsuarioAbrangencia(usuarioAbrangencia);
	}

	private Integer id;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_INSERIR_USUARIO, ATRIBUTOS_REMOVER_USUARIO, ATRIBUTOS_USUARIO_ALTERAR_SENHA, ATRIBUTOS_USUARIO_ALTERAR_SENHA_LOGIN })
	private String login;
	private String senha;
	private Integer numeroAcessos;
	private Short bloqueioAcesso;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_INSERIR_USUARIO })
	private Date dataExpiracaoAcesso;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_INSERIR_USUARIO })
	private Date dataPrazoMensagemExpiracao;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_INSERIR_USUARIO })
	private Date dataCadastroAcesso;

	private Date dataCadastroInicio;
	private Date dataCadastroFim;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_INSERIR_USUARIO, ATRIBUTOS_ATUALIZAR_USUARIO })
	private String descricaoEmail;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_ATUALIZAR_USUARIO, ATRIBUTOS_USUARIO_ALTERAR_SENHA, ATRIBUTOS_USUARIO_ALTERAR_SENHA_LOGIN })
	private Date ultimaAlteracao;

	private Date ultimoAcesso;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_INSERIR_USUARIO, ATRIBUTOS_ATUALIZAR_USUARIO })
	private UnidadeOrganizacional unidadeOrganizacional;

	@ControleAlteracao(value = FiltroUsuarioSituacao.DESCRICAO, funcionalidade = { ATRIBUTOS_ATUALIZAR_USUARIO })
	private UsuarioSituacao usuarioSituacao;

	@ControleAlteracao(value = FiltroUsuario.EMPRESA, funcionalidade = { ATRIBUTOS_INSERIR_USUARIO, ATRIBUTOS_ATUALIZAR_USUARIO })
	private Empresa empresa;

	@ControleAlteracao(value = FiltroUsuario.GERENCIA_REGIONAL, funcionalidade = { ATRIBUTOS_INSERIR_USUARIO, ATRIBUTOS_ATUALIZAR_USUARIO })
	private GerenciaRegional gerenciaRegional;

	@ControleAlteracao(value = FiltroUsuario.UNIDADE_NEGOCIO, funcionalidade = { ATRIBUTOS_INSERIR_USUARIO, ATRIBUTOS_ATUALIZAR_USUARIO })
	private UnidadeNegocio unidadeNegocio;

	@ControleAlteracao(value = FiltroUsuario.LOCALIDADE_ELO, funcionalidade = { ATRIBUTOS_INSERIR_USUARIO, ATRIBUTOS_ATUALIZAR_USUARIO })
	private Localidade localidadeElo;

	@ControleAlteracao(value = FiltroUsuario.LOCALIDADE, funcionalidade = { ATRIBUTOS_INSERIR_USUARIO, ATRIBUTOS_ATUALIZAR_USUARIO })
	private Localidade localidade;

	@ControleAlteracao(value = FiltroUsuarioTipo.DESCRICAO, funcionalidade = { ATRIBUTOS_INSERIR_USUARIO, ATRIBUTOS_ATUALIZAR_USUARIO })
	private UsuarioTipo usuarioTipo;

	@ControleAlteracao(value = FiltroUsuario.USUARIO_ABRANGENCIA, funcionalidade = { ATRIBUTOS_INSERIR_USUARIO, ATRIBUTOS_ATUALIZAR_USUARIO })
	private UsuarioAbrangencia usuarioAbrangencia;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_INSERIR_USUARIO })
	private Funcionario funcionario;

	private String nomeUsuario;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_INSERIR_USUARIO })
	private Date dataNascimento;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_INSERIR_USUARIO, ATRIBUTOS_ATUALIZAR_USUARIO, ATRIBUTOS_USUARIO_ALTERAR_SENHA })
	private String lembreteSenha;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_INSERIR_USUARIO })
	private String cpf;

	private Short indicadorTipoRelatorioPadrao;
	private Short indicadorExibeMensagem;
	private Short indicadorUsuarioBatch;
	private Short indicadorUsuarioInternet;

	@SuppressWarnings("rawtypes")
	private Set usuarioGrupos;

	private String ipLogado;

	private Short limiteBatch;
	
	@SuppressWarnings("rawtypes")
	public Set getUsuarioGrupos() {
		return usuarioGrupos;
	}

	@SuppressWarnings("rawtypes")
	public void setUsuarioGrupos(Set usuarioGrupos) {
		this.usuarioGrupos = usuarioGrupos;
	}

	public Usuario(String login, String senha, Integer numeroAcessos, Short bloqueioAcesso, Date dataExpiracaoAcesso, Date dataPrazoMensagemExpiracao, Date dataCadastroAcesso,
			Date dataCadastroInicio, Date dataCadastroFim, String descricaoEmail, Date ultimaAlteracao, Date ultimoAcesso, UnidadeOrganizacional unidadeOrganizacional,
			UsuarioSituacao usuarioSituacao, Empresa empresa, GerenciaRegional gerenciaRegional, Localidade localidadeElo, Localidade localidade, UsuarioTipo usuarioTipo,
			UsuarioAbrangencia usuarioAbrangencia, Funcionario funcionario, String nomeUsuario, Short indicadorTipoRelatorioPadrao, Short indicadorExibeMensagem) {
		this.login = login;
		this.senha = senha;
		this.numeroAcessos = numeroAcessos;
		this.bloqueioAcesso = bloqueioAcesso;
		this.dataExpiracaoAcesso = dataExpiracaoAcesso;
		this.dataPrazoMensagemExpiracao = dataPrazoMensagemExpiracao;
		this.dataCadastroAcesso = dataCadastroAcesso;
		this.dataCadastroInicio = dataCadastroInicio;
		this.dataCadastroFim = dataCadastroFim;
		this.descricaoEmail = descricaoEmail;
		this.ultimaAlteracao = ultimaAlteracao;
		this.ultimoAcesso = ultimoAcesso;
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.usuarioSituacao = usuarioSituacao;
		this.empresa = empresa;
		this.gerenciaRegional = gerenciaRegional;
		this.localidadeElo = localidadeElo;
		this.localidade = localidade;
		this.usuarioTipo = usuarioTipo;
		this.usuarioAbrangencia = usuarioAbrangencia;
		this.funcionario = funcionario;
		this.nomeUsuario = nomeUsuario;
		this.indicadorTipoRelatorioPadrao = indicadorTipoRelatorioPadrao;
		this.indicadorExibeMensagem = indicadorExibeMensagem;
	}

	public Usuario(String login, String senha, Integer numeroAcessos, Short bloqueioAcesso, Date dataExpiracaoAcesso, Date dataPrazoMensagemExpiracao, Date dataCadastroAcesso,
			Date dataCadastroInicio, Date dataCadastroFim, String descricaoEmail, String cpf, Date ultimaAlteracao, Date ultimoAcesso, UnidadeOrganizacional unidadeOrganizacional,
			UsuarioSituacao usuarioSituacao, Empresa empresa, GerenciaRegional gerenciaRegional, Localidade localidadeElo, Localidade localidade, UsuarioTipo usuarioTipo,
			UsuarioAbrangencia usuarioAbrangencia, Funcionario funcionario, String nomeUsuario) {
		this.login = login;
		this.senha = senha;
		this.numeroAcessos = numeroAcessos;
		this.bloqueioAcesso = bloqueioAcesso;
		this.dataExpiracaoAcesso = dataExpiracaoAcesso;
		this.dataPrazoMensagemExpiracao = dataPrazoMensagemExpiracao;
		this.dataCadastroAcesso = dataCadastroAcesso;
		this.dataCadastroInicio = dataCadastroInicio;
		this.dataCadastroFim = dataCadastroFim;
		this.descricaoEmail = descricaoEmail;
		this.ultimaAlteracao = ultimaAlteracao;
		this.ultimoAcesso = ultimoAcesso;
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.usuarioSituacao = usuarioSituacao;
		this.empresa = empresa;
		this.gerenciaRegional = gerenciaRegional;
		this.localidadeElo = localidadeElo;
		this.localidade = localidade;
		this.usuarioTipo = usuarioTipo;
		this.usuarioAbrangencia = usuarioAbrangencia;
		this.funcionario = funcionario;
		this.nomeUsuario = nomeUsuario;
		this.cpf = cpf;
	}

	public Short getIndicadorExibeMensagem() {
		return indicadorExibeMensagem;
	}

	public void setIndicadorExibeMensagem(Short indicadorExibeMensagem) {
		this.indicadorExibeMensagem = indicadorExibeMensagem;
	}

	public Short getIndicadorUsuarioBatch() {
		return indicadorUsuarioBatch;
	}

	public void setIndicadorUsuarioBatch(Short indicadorUsuarioBatch) {
		this.indicadorUsuarioBatch = indicadorUsuarioBatch;
	}

	public Short getIndicadorTipoRelatorioPadrao() {
		return indicadorTipoRelatorioPadrao;
	}

	public void setIndicadorTipoRelatorioPadrao(Short indicadorTipoRelatorioPadrao) {
		this.indicadorTipoRelatorioPadrao = indicadorTipoRelatorioPadrao;
	}

	public Usuario() {
	}

	public Usuario(UnidadeOrganizacional unidadeOrganizacional, UsuarioSituacao usuarioSituacao, Empresa empresa, GerenciaRegional gerenciaRegional, Localidade localidadeElo, Localidade localidade,
			UsuarioTipo usuarioTipo, UsuarioAbrangencia usuarioAbrangencia, Funcionario funcionario) {
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.usuarioSituacao = usuarioSituacao;
		this.empresa = empresa;
		this.gerenciaRegional = gerenciaRegional;
		this.localidadeElo = localidadeElo;
		this.localidade = localidade;
		this.usuarioTipo = usuarioTipo;
		this.usuarioAbrangencia = usuarioAbrangencia;
		this.funcionario = funcionario;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getNumeroAcessos() {
		return this.numeroAcessos;
	}

	public void setNumeroAcessos(Integer numeroAcessos) {
		this.numeroAcessos = numeroAcessos;
	}

	public Short getBloqueioAcesso() {
		return this.bloqueioAcesso;
	}

	public void setBloqueioAcesso(Short bloqueioAcesso) {
		this.bloqueioAcesso = bloqueioAcesso;
	}

	public Date getDataExpiracaoAcesso() {
		return this.dataExpiracaoAcesso;
	}

	public void setDataExpiracaoAcesso(Date dataExpiracaoAcesso) {
		this.dataExpiracaoAcesso = dataExpiracaoAcesso;
	}

	public Date getDataPrazoMensagemExpiracao() {
		return this.dataPrazoMensagemExpiracao;
	}

	public void setDataPrazoMensagemExpiracao(Date dataPrazoMensagemExpiracao) {
		this.dataPrazoMensagemExpiracao = dataPrazoMensagemExpiracao;
	}

	public Date getDataCadastroAcesso() {
		return this.dataCadastroAcesso;
	}

	public void setDataCadastroAcesso(Date dataCadastroAcesso) {
		this.dataCadastroAcesso = dataCadastroAcesso;
	}

	public Date getDataCadastroInicio() {
		return this.dataCadastroInicio;
	}

	public void setDataCadastroInicio(Date dataCadastroInicio) {
		this.dataCadastroInicio = dataCadastroInicio;
	}

	public Date getDataCadastroFim() {
		return this.dataCadastroFim;
	}

	public void setDataCadastroFim(Date dataCadastroFim) {
		this.dataCadastroFim = dataCadastroFim;
	}

	public String getDescricaoEmail() {
		return this.descricaoEmail;
	}

	public void setDescricaoEmail(String descricaoEmail) {
		this.descricaoEmail = descricaoEmail;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Date getUltimoAcesso() {
		return this.ultimoAcesso;
	}

	public void setUltimoAcesso(Date ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public UsuarioSituacao getUsuarioSituacao() {
		return this.usuarioSituacao;
	}

	public void setUsuarioSituacao(UsuarioSituacao usuarioSituacao) {
		this.usuarioSituacao = usuarioSituacao;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public GerenciaRegional getGerenciaRegional() {
		return this.gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Localidade getLocalidadeElo() {
		return this.localidadeElo;
	}

	public void setLocalidadeElo(Localidade localidadeElo) {
		this.localidadeElo = localidadeElo;
	}

	public Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public UsuarioTipo getUsuarioTipo() {
		return this.usuarioTipo;
	}

	public void setUsuarioTipo(UsuarioTipo usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}

	public UsuarioAbrangencia getUsuarioAbrangencia() {
		return this.usuarioAbrangencia;
	}

	public void setUsuarioAbrangencia(UsuarioAbrangencia usuarioAbrangencia) {
		this.usuarioAbrangencia = usuarioAbrangencia;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getLembreteSenha() {
		return lembreteSenha;
	}

	public void setLembreteSenha(String lembreteSenha) {
		this.lembreteSenha = lembreteSenha;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getIpLogado() {
		return ipLogado;
	}

	public void setIpLogado(String ipLogado) {
		this.ipLogado = ipLogado;
	}

	public boolean equals(Object arg) {
		if (arg == null) {
			return false;
		}
		if (!(arg instanceof Usuario)) {
			return false;
		}
		return this.id.intValue() == ((Usuario) arg).getId().intValue();
	}

	public Filtro retornaFiltro() {
		FiltroUsuario filtroUsuario = new FiltroUsuario();

		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, this.getId()));

		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("usuarioSituacao");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("localidadeElo");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("usuarioTipo");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("usuarioAbrangencia");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("funcionario");

		return filtroUsuario;
	}

	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		FiltroUsuario filtro = new FiltroUsuario();

		filtro.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, this.getId()));

		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.UNIDADE_ORGANIZACIONAL);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.USUARIO_SITUACAO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.EMPRESA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.GERENCIA_REGIONAL);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.LOCALIDADE_ELO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.USUARIO_TIPO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.USUARIO_ABRANGENCIA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.FUNCIONARIO);

		return filtro;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "nomeUsuario" };
		return retorno;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getNomeUsuario();
	}

	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String[] labels = { "nomeUsuario" };
		return labels;
	}

	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String[] labels = { "Nome usuario" };
		return labels;
	}

	@Override
	public void initializeLazy() {
		getNomeUsuario();
		getLembreteSenha();
		getDataCadastroFim();
	}

	public Short getIndicadorUsuarioInternet() {
		return indicadorUsuarioInternet;
	}

	public void setIndicadorUsuarioInternet(Short indicadorUsuarioInternet) {
		this.indicadorUsuarioInternet = indicadorUsuarioInternet;
	}

	public Short getLimiteBatch() {
		return limiteBatch;
	}

	public void setLimiteBatch(Short limiteBatch) {
		this.limiteBatch = limiteBatch;
	}
}
