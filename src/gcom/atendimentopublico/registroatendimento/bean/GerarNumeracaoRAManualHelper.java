package gcom.atendimentopublico.registroatendimento.bean;

import java.util.Collection;


/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o resultado do 
 * [UC0494] Gerar Numeração de RA Manual 
 * 
 * @author Raphael Rossiter
 * @date 06/11/2006
 */
public class GerarNumeracaoRAManualHelper {

	private Integer idUnidadeOrganizacional;
	
	private String descricaoUnidadeOrganizacional;
	
	private Collection<String> colecaoNumeracao;
	
	
	public GerarNumeracaoRAManualHelper(){}

	public Collection<String> getColecaoNumeracao() {
		return colecaoNumeracao;
	}

	public void setColecaoNumeracao(Collection<String> colecaoNumeracao) {
		this.colecaoNumeracao = colecaoNumeracao;
	}

	public String getDescricaoUnidadeOrganizacional() {
		return descricaoUnidadeOrganizacional;
	}

	public void setDescricaoUnidadeOrganizacional(
			String descricaoUnidadeOrganizacional) {
		this.descricaoUnidadeOrganizacional = descricaoUnidadeOrganizacional;
	}

	public Integer getIdUnidadeOrganizacional() {
		return idUnidadeOrganizacional;
	}

	public void setIdUnidadeOrganizacional(Integer idUnidadeOrganizacional) {
		this.idUnidadeOrganizacional = idUnidadeOrganizacional;
	}
	
	
}
