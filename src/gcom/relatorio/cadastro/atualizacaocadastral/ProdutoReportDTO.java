package gcom.relatorio.cadastro.atualizacaocadastral;

import br.com.prodigasistemas.gsan.relatorio.ReportHeaderType;
import br.com.prodigasistemas.gsan.relatorio.ReportItemDTO;

public class ProdutoReportDTO implements ReportItemDTO{
	private static final long serialVersionUID = -5224725130381064147L;

	@ReportHeaderType(description="Descri\u00E7\u00E3o")
	private String descricao;
	
	@ReportHeaderType(description="Unidade de Medida")
	private String unidadeMedida;
	
	public ProdutoReportDTO(String descricao, String unidadeMedida) {
		this.descricao = descricao;
		this.unidadeMedida = unidadeMedida;
	}

	public ProdutoReportDTO(){
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public String toString() {
		return "ProdutoReport [descricao=" + descricao + ", unidadeMedida=" + unidadeMedida + "]";
	}
}
