package gcom.cadastro.cliente;

import java.io.Serializable;
import java.util.Date;

public class CadastroUnico implements Serializable {
	
	public static Short SIASTER = new Short("1");
	public static Short CAIXA = new Short("2");
	
	public static Short TEM_NIS = new Short("1");
	public static Short NAO_TEM_NIS = new Short("2");
	public static Short NIS_CAIXA = new Short("3");
	public static Short NIS_SEM_REGISTRO_OFICIAL = new Short("4");

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String numeroNIS;

	private String nome;

	private Date dataNascimento;

	private String ddd;

	private String pessoaSexo;

	private String cpf;

	private String dataCadastramento;

	private String codigoIBGE;

	private Date dataInclusao;

	private Date dataExclusao;

	private Date ultimaAlteracao;
	
	private Short idSeaster;

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

	public String getPessoaSexo() {
		return pessoaSexo;
	}

	public void setPessoaSexo(String pessoaSexo) {
		this.pessoaSexo = pessoaSexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataCadastramento() {
		return dataCadastramento;
	}

	public void setDataCadastramento(String dataCadastramento) {
		this.dataCadastramento = dataCadastramento;
	}

	public String getCodigoIBGE() {
		return codigoIBGE;
	}

	public void setCodigoIBGE(String codigoIBGE) {
		this.codigoIBGE = codigoIBGE;
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

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Short getIdSeaster() {
		return idSeaster;
	}

	public void setIdSeaster(Short idSeaster) {
		this.idSeaster = idSeaster;
	}
}
