package gcom.gui.cadastro;

import java.util.Collection;

import org.apache.struts.action.ActionForm;

import gcom.cadastro.imovel.Imovel;

public class DadosRecadastramentoAguaParaActionForm extends ActionForm  {

	private static final long serialVersionUID = 7793346732600789615L;

	private Integer id;
	
	private String cpf;
	
	private String rg;
	
	private String numeroNIS;
	
	private String idImovel;
	
	private String nome;
	
	private Integer situacao;
	
	private String telefone;
	
	private Collection imoveis;
	
	private String path;
	
	public DadosRecadastramentoAguaParaActionForm() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Collection getImoveis() {
		return imoveis;
	}

	public void setImoveis(Collection imoveis) {
		this.imoveis = imoveis;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}

