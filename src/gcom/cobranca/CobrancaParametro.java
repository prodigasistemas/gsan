package gcom.cobranca;

import java.io.Serializable;

public class CobrancaParametro implements Serializable {

	private static final long serialVersionUID = -7918264574580624530L;

	private Integer id;
	private String nome;
	private String valor;

	public enum NOME_PARAMETRO_COBRANCA {
		DATA_INICIO_PARCELAMENTO_GSAN;
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

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
