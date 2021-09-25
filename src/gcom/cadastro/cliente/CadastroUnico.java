package gcom.cadastro.cliente;

import java.io.Serializable;
import java.util.Date;

public class CadastroUnico implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String numeroNIS;

	private String nome;

	private Date dataNascimento;

	private String ddd;

	private String telefone;

	private FoneTipo foneTipo;

	private PessoaSexo pessoaSexo;

	private String cpf;

	private Date dataCadastramento;

	private String municipio;

	private Date ultimaAlteracao;

	private Date dataInclusao;

	private Date dataExclusao;

	public CadastroUnico() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroNIS() {
		return numeroNIS;
	}

	public void setNumeroNIS(String numeroNIS) {
		this.numeroNIS = numeroNIS;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

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

	public FoneTipo getFoneTipo() {
		return foneTipo;
	}

	public void setFoneTipo(FoneTipo foneTipo) {
		this.foneTipo = foneTipo;
	}

	public PessoaSexo getPessoaSexo() {
		return pessoaSexo;
	}

	public void setPessoaSexo(PessoaSexo pessoaSexo) {
		this.pessoaSexo = pessoaSexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataCadastramento() {
		return dataCadastramento;
	}

	public void setDataCadastramento(Date dataCadastramento) {
		this.dataCadastramento = dataCadastramento;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}
}
