package gcom.gui.relatorio.cadastro.endereco;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1011] Gerar Relatorio Logradouro por Municipio.
 * 
 * @author Wallace Thierre
 *
 * @date 06/09/2010
 */

public class GerarRelatorioLogradouroPorMunicipioActionForm extends ValidatorActionForm{

	private String nomeMunicipio;	
	private String idMunicipio;
	private String nomeBairro;
	private String idBairro;	
	private String colecaoBairro;
	private String idLogradouro;
	private String nomeLogradouro;


	private static final long serialVersionUID = 1L;


	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
	}

	public String getColecaoBairro() {
		return colecaoBairro;
	}

	public void setColecaoBairro(String colecaoBairro) {
		this.colecaoBairro = colecaoBairro;
	}

	public String getIdLogradouro() {
		return idLogradouro;
	}

	public void setIdLogradouro(String idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}



}
