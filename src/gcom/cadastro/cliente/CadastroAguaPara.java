package gcom.cadastro.cliente;

import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ControleAlteracao;

@ControleAlteracao()
public class CadastroAguaPara {
	
	public static final int TODOS = 4;
	
	public static final int PENDENTE = 3;

	public static final int RECUSADO = 2;

	public static final int ACEITO = 1;
	
	public static final int VAZIO = 0;

	
	private Integer id;
	
	private String cpf;
	
	private String rg;
	
	private String numeroNIS;
	
	private Imovel imovel;
	
	private String nome;
	
	private Integer situacao;
	
	private String telefone;
	
	
	public CadastroAguaPara() {
		
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

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
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
	
}
