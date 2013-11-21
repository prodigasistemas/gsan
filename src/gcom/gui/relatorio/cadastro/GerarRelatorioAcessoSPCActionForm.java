package gcom.gui.relatorio.cadastro;

import org.apache.struts.action.ActionForm;

/**
 * [UC1170] - Gerar Relatório Acesso ao SPC
 * 
 * @author Diogo Peixoto
 * @since 06/05/2011
 * 
 */
public class GerarRelatorioAcessoSPCActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	//Período de Referência
	private String dataReferenciaInicial;
	private String dataReferenciaFinal;
	
	//Unidade Organizacional
	private String idUnidadeOrganizacional;
	private String descricaoUnidadeOrganizacional;
	
	//Usuário Responsável
	private String loginUsuarioResponsavel;
	private String nomeUsuarioResponsavel;

	public GerarRelatorioAcessoSPCActionForm() {
		super();
	}

	//Getters and Setters das propriedades do GerarRelatorioAcessoSPCActionForm
	public String getDataReferenciaInicial() {
		return dataReferenciaInicial;
	}

	public void setDataReferenciaInicial(String dataReferenciaInicial) {
		this.dataReferenciaInicial = dataReferenciaInicial;
	}

	public String getDataReferenciaFinal() {
		return dataReferenciaFinal;
	}

	public void setDataReferenciaFinal(String dataReferenciaFinal) {
		this.dataReferenciaFinal = dataReferenciaFinal;
	}

	public String getIdUnidadeOrganizacional() {
		return idUnidadeOrganizacional;
	}

	public void setIdUnidadeOrganizacional(String idUnidadeOrganizacional) {
		this.idUnidadeOrganizacional = idUnidadeOrganizacional;
	}

	public String getLoginUsuarioResponsavel() {
		return loginUsuarioResponsavel;
	}

	public void setLoginUsuarioResponsavel(String loginUsuarioResponsavel) {
		this.loginUsuarioResponsavel = loginUsuarioResponsavel;
	}
	public String getDescricaoUnidadeOrganizacional() {
		return descricaoUnidadeOrganizacional;
	}

	public void setDescricaoUnidadeOrganizacional(
			String descricaoUnidadeOrganizacional) {
		this.descricaoUnidadeOrganizacional = descricaoUnidadeOrganizacional;
	}

	public String getNomeUsuarioResponsavel() {
		return nomeUsuarioResponsavel;
	}

	public void setNomeUsuarioResponsavel(String nomeUsuarioResponsavel) {
		this.nomeUsuarioResponsavel = nomeUsuarioResponsavel;
	}
	//Fim dos Getters and Setters do GerarRelatorioAcessoSPCActionForm
}