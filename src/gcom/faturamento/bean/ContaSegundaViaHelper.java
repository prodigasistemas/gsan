package gcom.faturamento.bean;

import java.util.List;

public class ContaSegundaViaHelper {

	private List<ContaSegundaViaDTO> contas;

	private String nomeArquivo;

	public ContaSegundaViaHelper(List<ContaSegundaViaDTO> contas) {
		super();

		this.contas = contas;
		this.nomeArquivo = "SEGUNDA_VIA_" + contas.get(0).getMatricula() + ".pdf";
	}

	public List<ContaSegundaViaDTO> getContas() {
		return contas;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}
}