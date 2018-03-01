package gcom.cadastro;

import java.io.Serializable;
import java.util.Date;

public class LojaAtendimento implements Serializable {

	private static final long serialVersionUID = 2627652294154154608L;

	private Integer id;

	private String nome;

	private String localidade;

	private String endereco;

	private String ddd;

	private String telefone;

	private String horario;

	private Integer coordenadas;

	private Date ultimaAlteracao;

	public LojaAtendimento() {
		super();
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

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Integer getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(Integer coordenadas) {
		this.coordenadas = coordenadas;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
