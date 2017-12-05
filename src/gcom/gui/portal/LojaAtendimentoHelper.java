package gcom.gui.portal;

import gcom.util.Util;

public class LojaAtendimentoHelper {

	private String nome;

	private String localidade;

	private String endereco;

	private String ddd;

	private String telefone;

	private String horario;

	private String coordenadas;

	public LojaAtendimentoHelper() {
		super();
	}

	public LojaAtendimentoHelper(String nome, String localidade, String endereco, String ddd, String telefone, String horario, String coordenadas) {
		super();
		this.nome = nome;
		this.localidade = localidade;
		this.endereco = endereco;
		this.ddd = ddd;
		this.telefone = telefone;
		this.horario = horario;
		this.coordenadas = coordenadas;
	}

	public String getNome() {
		return nome;
	}

	public String getLocalidade() {
		return localidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getTelefoneFormatado() {
		return Util.formatarTelefone(ddd, telefone);
	}

	public String getHorario() {
		return horario;
	}

	public String getCoordenadas() {
		return coordenadas;
	}
}
