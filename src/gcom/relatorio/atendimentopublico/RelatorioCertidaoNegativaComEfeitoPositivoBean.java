package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * classe responsável por criar a certidao negativa com efeito positivo
 * 
 * @author Bruno Barros
 * @created 29/01/2008
 */
public class RelatorioCertidaoNegativaComEfeitoPositivoBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String gerenciaRegional;
	private String localidade;
	private String unidadeNegocio;
	private String setorComercial;
	private String nomeClienteUsuario;
	private String matriculaImovel;
	private String endereco;
	private String bairro;
	private String CEP;
	private String municipio;
	private String inscricaoImovel;
	private String categoria;
	private String subcategoria;
	private String economias;
	private String ligacaoAguaSituacao;
	private String ligacaoEsgotoSituacao;
	private String situacaoPoco;
	private String descricaoEmpresa;
	private String enderecoEmpresa;
	private String cepEmpresa;
	private String CNPJEmpresa;
	private String inscricaoEstadualEmpresa;
	private String siteEmpresa;
	private String zeroOitossentosEmpresa;
	private String perfilImovel;
	
	public static long getSerialVersionUID() {
	
		return serialVersionUID;
	}
	
	public String getBairro() {
	
		return bairro;
	}
	
	public String getCategoria() {
	
		return categoria;
	}
	
	public String getCEP() {
	
		return CEP;
	}
	
	public String getCepEmpresa() {
	
		return cepEmpresa;
	}
	
	public String getCNPJEmpresa() {
	
		return CNPJEmpresa;
	}
	
	public String getDescricaoEmpresa() {
	
		return descricaoEmpresa;
	}
	
	public String getEconomias() {
	
		return economias;
	}
	
	public String getEndereco() {
	
		return endereco;
	}
	
	public String getEnderecoEmpresa() {
	
		return enderecoEmpresa;
	}
	
	public String getGerenciaRegional() {
	
		return gerenciaRegional;
	}
	
	public String getInscricaoEstadualEmpresa() {
	
		return inscricaoEstadualEmpresa;
	}
	
	public String getInscricaoImovel() {
	
		return inscricaoImovel;
	}
	
	public String getLigacaoAguaSituacao() {
	
		return ligacaoAguaSituacao;
	}
	
	public String getLigacaoEsgotoSituacao() {
	
		return ligacaoEsgotoSituacao;
	}
	
	public String getLocalidade() {
	
		return localidade;
	}
	
	public String getMatriculaImovel() {
	
		return matriculaImovel;
	}
	
	public String getMunicipio() {
	
		return municipio;
	}
	
	public String getNomeClienteUsuario() {
	
		return nomeClienteUsuario;
	}
	
	public String getPerfilImovel() {
	
		return perfilImovel;
	}
	
	public String getSetorComercial() {
	
		return setorComercial;
	}
	
	public String getSiteEmpresa() {
	
		return siteEmpresa;
	}
	
	public String getSituacaoPoco() {
	
		return situacaoPoco;
	}
	
	public String getSubcategoria() {
	
		return subcategoria;
	}
	
	public String getUnidadeNegocio() {
	
		return unidadeNegocio;
	}
	
	public String getZeroOitossentosEmpresa() {
	
		return zeroOitossentosEmpresa;
	}
}
