package gcom.faturamento.bean;

import java.util.List;

public class EntradaParcelamentoHelper {

	private List<EntradaParcelamentoDTO> entradaParcelamento;

	private String nomeArquivo;

	public EntradaParcelamentoHelper(List<EntradaParcelamentoDTO> listaDTO) {
		super();

		this.entradaParcelamento = listaDTO;
		this.nomeArquivo = "ENTRADA_PARCELAMENTO_" + listaDTO.get(0).getMatricula() + ".pdf";
	}

	public List<EntradaParcelamentoDTO> getContas() {
		return entradaParcelamento;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}
}