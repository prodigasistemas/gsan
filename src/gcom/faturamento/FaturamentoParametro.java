package gcom.faturamento;

public class FaturamentoParametro {

	private Integer id;
	private String nome;
	private String valor;

	public enum NOME_PARAMETRO_FATURAMENTO {
		EMITIR_CONTA_CODIGO_FEBRABAN,
		REFERENCIA_ANTERIOR_PARA_QUALIDADE_AGUA,
		AGENCIA_REGULADORA_NOME,
		AGENCIA_REGULADORA_ALIQUOTA,
		AGENCIA_REGULADORA_MUNICIPIO,
		QUANTIDADE_DIAS_FATURA_VENCIDA;
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