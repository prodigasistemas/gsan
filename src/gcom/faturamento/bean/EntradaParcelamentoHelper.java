package gcom.faturamento.bean;

import java.util.List;

public class EntradaParcelamentoHelper {

	private List<ContaSegundaViaDTO> contas;

	private String nomeArquivo;

	public EntradaParcelamentoHelper(List<ContaSegundaViaDTO> contas) {
		super();

		this.contas = contas;
		this.nomeArquivo = "ENTRADA_PARCELAMENTO_" + contas.get(0).getMatricula() + ".pdf";
	}

	public List<ContaSegundaViaDTO> getContas() {
		return contas;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}
}