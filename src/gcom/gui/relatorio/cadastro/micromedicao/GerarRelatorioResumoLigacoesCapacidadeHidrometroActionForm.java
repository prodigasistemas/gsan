package gcom.gui.relatorio.cadastro.micromedicao;


import org.apache.struts.action.ActionForm;

/**
 * [UC0997] Gerar Resumo de Ligações por Capacidade de Hidrômetro
 * 
 * @author Hugo Leonardo
 *
 * @date 29/03/2010
 */

public class GerarRelatorioResumoLigacoesCapacidadeHidrometroActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String opcaoTotalizacao;
	private String regional;
	private String unidadeNegocio;
	private String idLocalidade;
	private String nomeLocalidade;

	public void reset(){
		
		this.opcaoTotalizacao = null;
		this.regional = null;
		this.unidadeNegocio = null;
		this.idLocalidade = null;
		this.nomeLocalidade = null;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getRegional() {
		return regional;
	}

	public void setRegional(String regional) {
		this.regional = regional;
	}
	
}
