package gcom.gui.relatorio.gerencial.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC01003] Emitir Relatório Demonstrativo Sintético das Ligações
 * 
 * @author Daniel Alves
 *
 * @date 08/04/2010
 */

public class ExibirEmissaoRelatorioDemonstrativoSinteticoLigacoesActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String opcaoTotalizacao;
	
	private String mesAnoFaturamento;

	private String localidade;
	private String nomeLocalidade;
	
	private String gerenciaRegional;
	private String unidadeNegocio;
	
	private String municipio;
	private String nomeMunicipio;
	
	public void reset(){
		
		this.localidade = null;
		this.nomeLocalidade = null;
		
		this.gerenciaRegional = null;
		this.unidadeNegocio = null;
	}

	public String getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}

	public void setMesAnoFaturamento(String mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
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

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}
}
