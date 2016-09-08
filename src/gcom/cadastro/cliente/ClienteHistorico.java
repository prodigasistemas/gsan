package gcom.cadastro.cliente;

import java.util.Date;

import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.MergeProperties;
import gcom.util.filtro.Filtro;

public class ClienteHistorico extends ObjetoTransacao {
	private static final long serialVersionUID = 1147658926124230075L;

	private Integer id;

	private String nome;

	private String nomeAbreviado;

	private String cpf;

	private String rg;

	private Date dataEmissaoRg;

	private Short dataVencimento;

	private Date dataNascimento;

	private String cnpj;

	private String email;

	private Short indicadorUso;

	private Date ultimaAlteracao;

	private Short indicadorAcaoCobranca;

	private Short diaVencimento;

	private String nomeMae;

	private Short indicadorAcrescimos;

	private Short indicadorGeraArquivoTexto;

	private Short indicadorGeraFaturaAntecipada;

	private Short indicadorVencimentoMesSeguinte;

	private Short indicadorUsoNomeFantasiaConta;

	private Short indicadorPermiteNegativacao;
	
    private Short indicadorNegativacaoPeriodo;

	private OrgaoExpedidorRg orgaoExpedidorRg;

	private Cliente cliente;

	private PessoaSexo pessoaSexo;

	private Profissao profissao;

	private UnidadeFederacao unidadeFederacao;

	private ClienteTipo clienteTipo;

	private RamoAtividade ramoAtividade;
	
	private Cliente clienteBase;
	
	private Usuario usuario;

	public ClienteHistorico() {}
	
	public ClienteHistorico(Cliente cliente, Usuario usuario) {
		MergeProperties.mergeProperties(this, cliente);
		this.usuario     = usuario;
		this.clienteBase = cliente;
	}

	@Override
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		return new String[]{"id"};
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataEmissaoRg() {
		return dataEmissaoRg;
	}

	public void setDataEmissaoRg(Date dataEmissaoRg) {
		this.dataEmissaoRg = dataEmissaoRg;
	}

	public Short getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Short dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Short getIndicadorAcaoCobranca() {
		return indicadorAcaoCobranca;
	}

	public void setIndicadorAcaoCobranca(Short indicadorAcaoCobranca) {
		this.indicadorAcaoCobranca = indicadorAcaoCobranca;
	}

	public Short getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Short diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public Short getIndicadorAcrescimos() {
		return indicadorAcrescimos;
	}

	public void setIndicadorAcrescimos(Short indicadorAcrescimos) {
		this.indicadorAcrescimos = indicadorAcrescimos;
	}

	public Short getIndicadorGeraArquivoTexto() {
		return indicadorGeraArquivoTexto;
	}

	public void setIndicadorGeraArquivoTexto(Short indicadorGeraArquivoTexto) {
		this.indicadorGeraArquivoTexto = indicadorGeraArquivoTexto;
	}

	public Short getIndicadorGeraFaturaAntecipada() {
		return indicadorGeraFaturaAntecipada;
	}

	public void setIndicadorGeraFaturaAntecipada(Short indicadorGeraFaturaAntecipada) {
		this.indicadorGeraFaturaAntecipada = indicadorGeraFaturaAntecipada;
	}

	public Short getIndicadorVencimentoMesSeguinte() {
		return indicadorVencimentoMesSeguinte;
	}

	public void setIndicadorVencimentoMesSeguinte(Short indicadorVencimentoMesSeguinte) {
		this.indicadorVencimentoMesSeguinte = indicadorVencimentoMesSeguinte;
	}

	public Short getIndicadorUsoNomeFantasiaConta() {
		return indicadorUsoNomeFantasiaConta;
	}

	public void setIndicadorUsoNomeFantasiaConta(Short indicadorUsoNomeFantasiaConta) {
		this.indicadorUsoNomeFantasiaConta = indicadorUsoNomeFantasiaConta;
	}

	public Short getIndicadorPermiteNegativacao() {
		return indicadorPermiteNegativacao;
	}

	public void setIndicadorPermiteNegativacao(Short indicadorPermiteNegativacao) {
		this.indicadorPermiteNegativacao = indicadorPermiteNegativacao;
	}

	public Short getIndicadorNegativacaoPeriodo() {
		return indicadorNegativacaoPeriodo;
	}

	public void setIndicadorNegativacaoPeriodo(Short indicadorNegativacaoPeriodo) {
		this.indicadorNegativacaoPeriodo = indicadorNegativacaoPeriodo;
	}

	public OrgaoExpedidorRg getOrgaoExpedidorRg() {
		return orgaoExpedidorRg;
	}

	public void setOrgaoExpedidorRg(OrgaoExpedidorRg orgaoExpedidorRg) {
		this.orgaoExpedidorRg = orgaoExpedidorRg;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public PessoaSexo getPessoaSexo() {
		return pessoaSexo;
	}

	public void setPessoaSexo(PessoaSexo pessoaSexo) {
		this.pessoaSexo = pessoaSexo;
	}

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}

	public UnidadeFederacao getUnidadeFederacao() {
		return unidadeFederacao;
	}

	public void setUnidadeFederacao(UnidadeFederacao unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}

	public ClienteTipo getClienteTipo() {
		return clienteTipo;
	}

	public void setClienteTipo(ClienteTipo clienteTipo) {
		this.clienteTipo = clienteTipo;
	}

	public RamoAtividade getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(RamoAtividade ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}

	public Cliente getClienteBase() {
		return clienteBase;
	}

	public void setClienteBase(Cliente clienteBase) {
		this.clienteBase = clienteBase;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
