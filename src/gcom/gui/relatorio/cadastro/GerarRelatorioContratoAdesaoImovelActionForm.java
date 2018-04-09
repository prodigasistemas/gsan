package gcom.gui.relatorio.cadastro;

import org.apache.struts.action.ActionForm;

public class GerarRelatorioContratoAdesaoImovelActionForm extends ActionForm{

	private static final long serialVersionUID = 4157124637361069100L;
	
	private Integer idImovel;
	private String nomeResponsavel;
	private String endereco;
	private String nomeCidade;
	private String dataConsulta;

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	
	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public String getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(String dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	
}
