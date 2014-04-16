package gcom.cadastro.funcionario;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Funcionario extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** persistent field */
	private String nome;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private Empresa empresa;
	
	/** persistent field */
	private String numeroCpf;
	
	/** persistent field */
	private Date dataNascimento;

	/** persistent field */
	private UnidadeOrganizacional unidadeOrganizacional;
	
	/** persistent field */
	private FuncionarioCargo funcionarioCargo;

	/** full constructor */
	public Funcionario(String descricaoCargo, String nome,
			Date ultimaAlteracao, Empresa empresa, String numeroCpf, Date dataNascimento, UnidadeOrganizacional unidadeOrganizacional, FuncionarioCargo funcionarioCargo) {
		this.nome = nome;
		this.ultimaAlteracao = ultimaAlteracao;
		this.empresa = empresa;
		this.numeroCpf = numeroCpf;
		this.dataNascimento = dataNascimento;
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.funcionarioCargo = funcionarioCargo;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	/** default constructor */
	public Funcionario() {
	}

	/** minimal constructor */
	public Funcionario(String descricaoCargo, String nome, Empresa empresa,
			UnidadeOrganizacional unidadeOrganizacional) {
		this.nome = nome;
		this.empresa = empresa;
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNumeroCpf() {
		return numeroCpf;
	}

	public void setNumeroCpf(String numeroCpf) {
		this.numeroCpf = numeroCpf;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}
	
	/**
	 * @return Retorna o campo funcionarioCargo.
	 */
	public FuncionarioCargo getFuncionarioCargo() {
		return funcionarioCargo;
	}

	/**
	 * @param funcionarioCargo O funcionarioCargo a ser setado.
	 */
	public void setFuncionarioCargo(FuncionarioCargo funcionarioCargo) {
		this.funcionarioCargo = funcionarioCargo;
	}
	
	public Filtro retornaFiltro(){
		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
		
		filtroFuncionario. adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtroFuncionario. adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroFuncionario. adicionarCaminhoParaCarregamentoEntidade("funcionarioCargo");
		filtroFuncionario. adicionarParametro(
				new ParametroSimples(FiltroFuncionario.ID, this.getId()));
		return filtroFuncionario; 
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getNome();
	}

}
