package gcom.relatorio.cadastro;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1170] - Gerar Relatório Acesso ao SPC
 * 
 * @author Diogo Peixoto
 * @since 09/05/2011
 * 
 */
public class RelatorioAcessoSPCBean implements RelatorioBean{

	private String unidadeOrganizacional;
	private String usuario;
	private String dataAcesso;
	private String cpfCnpj;
	private String nomeRazaoSocial;
	
	//Construtor inicializando todas as variáveis de instância.
	public RelatorioAcessoSPCBean(String unidade, String usuario, String dataAcesso, String cpfCnpj, String nomeRazaoSocial){
		this.unidadeOrganizacional = unidade;
		this.usuario = usuario;
		this.dataAcesso = dataAcesso;
		this.cpfCnpj = cpfCnpj;
		this.nomeRazaoSocial = nomeRazaoSocial;
	}
	
	//Construtor vazio
	public RelatorioAcessoSPCBean() {
	}

	public String getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}
	public String getUsuario() {
		return usuario;
	}
	public String getDataAcesso() {
		return dataAcesso;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public String getNomeRazaoSocial() {
		return nomeRazaoSocial;
	}
	public void setUnidadeOrganizacional(String unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public void setDataAcesso(String dataAcesso) {
		this.dataAcesso = dataAcesso;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public void setNomeRazaoSocial(String nomeRazaoSocial) {
		this.nomeRazaoSocial = nomeRazaoSocial;
	}
}