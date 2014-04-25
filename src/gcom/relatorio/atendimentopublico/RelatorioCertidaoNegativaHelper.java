package gcom.relatorio.atendimentopublico;

import java.io.Serializable;
import java.util.Collection;


/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio de certidao negativa 
 *
 * @author Bruno Barros
 * @date 29/01/2008
 */
public class RelatorioCertidaoNegativaHelper implements Serializable, Cloneable{
	
	private static final long serialVersionUID = 1L;
	
	private String nomeClienteUsuario;
	private Integer matriculaImovel;
	private String endereco;
	private String bairro;
	private String CEP;
	private String municipio;
	private String localidade;
	private String inscricaoImovel;
	private String categoria;
	private String subcategoria;
	private Short economias;
	private String ligacaoAguaSituacao;
	private String ligacaoEsgotoSituacao;
	private String situacaoPoco;
	private String descricaoAbreviadaEmpresa;
	private String descricaoEmpresa;
	private String enderecoEmpresa;
	private String cepEmpresa;
	private String CNPJEmpresa;
	private String inscricaoEstadualEmpresa;
	private String siteEmpresa;
	private String zeroOitossentosEmpresa;
	private String perfilImovel;
	private String area;
	private String numeroHidrometro;
	private String nomeUsuario;
	private Boolean imovelComParcelamento;
	
	private String unidadeNegocio;
	private String cpfCnpj;
	private String enderecoCompleto;
	
	private Collection<RelatorioCertidaoNegativaItemBean> itens;
	
	
	public Collection<RelatorioCertidaoNegativaItemBean> getItens() {
	
		return itens;
	}
	
	
	public void setItens(Collection<RelatorioCertidaoNegativaItemBean> itens) {
	
		this.itens = itens;
	}



	public String getBairro() {
	
		return bairro;
	}
	
	public void setBairro(String bairro) {
	
		this.bairro = bairro;
	}
	
	public String getCategoria() {
	
		return categoria;
	}
	
	public void setCategoria(String categoria) {
	
		this.categoria = categoria;
	}
	
	public String getCEP() {
	
		return CEP;
	}
	
	public void setCEP(String cep) {
	
		CEP = cep;
	}
	
	public String getCepEmpresa() {
	
		return cepEmpresa;
	}
	
	public void setCepEmpresa(String cepEmpresa) {
	
		this.cepEmpresa = cepEmpresa;
	}
	
	public String getCNPJEmpresa() {
	
		return CNPJEmpresa;
	}
	
	public void setCNPJEmpresa(String empresa) {
	
		CNPJEmpresa = empresa;
	}
	
	public String getDescricaoEmpresa() {
	
		return descricaoEmpresa;
	}
	
	public void setDescricaoEmpresa(String descricaoEmpresa) {
	
		this.descricaoEmpresa = descricaoEmpresa;
	}
	
	public Short getEconomias() {
	
		return economias;
	}
	
	public void setEconomias(Short economias) {
	
		this.economias = economias;
	}
	
	public String getEndereco() {
	
		return endereco;
	}
	
	public void setEndereco(String endereco) {
	
		this.endereco = endereco;
	}
	
	public String getEnderecoEmpresa() {
	
		return enderecoEmpresa;
	}
	
	public void setEnderecoEmpresa(String enderecoEmpresa) {
	
		this.enderecoEmpresa = enderecoEmpresa;
	}
	
	public String getInscricaoEstadualEmpresa() {
	
		return inscricaoEstadualEmpresa;
	}
	
	public void setInscricaoEstadualEmpresa(String inscricaoEstadualEmpresa) {
	
		this.inscricaoEstadualEmpresa = inscricaoEstadualEmpresa;
	}
	
	public String getInscricaoImovel() {
	
		return inscricaoImovel;
	}
	
	public void setInscricaoImovel(String inscricaoImovel) {
	
		this.inscricaoImovel = inscricaoImovel;
	}
	
	public String getLigacaoAguaSituacao() {
	
		return ligacaoAguaSituacao;
	}
	
	public void setLigacaoAguaSituacao(String ligacaoAguaSituacao) {
	
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}
	
	public String getLigacaoEsgotoSituacao() {
	
		return ligacaoEsgotoSituacao;
	}
	
	public void setLigacaoEsgotoSituacao(String ligacaoEsgotoSituacao) {
	
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}
	
	public Integer getMatriculaImovel() {
	
		return matriculaImovel;
	}
	
	public void setMatriculaImovel(Integer matriculaImovel) {
	
		this.matriculaImovel = matriculaImovel;
	}
	
	public String getMunicipio() {
	
		return municipio;
	}
	
	public void setMunicipio(String municipio) {
	
		this.municipio = municipio;
	}
	
	public String getNomeClienteUsuario() {
	
		return nomeClienteUsuario;
	}
	
	public void setNomeClienteUsuario(String nomeClienteUsuario) {
	
		this.nomeClienteUsuario = nomeClienteUsuario;
	}
	
	public String getPerfilImovel() {
	
		return perfilImovel;
	}
	
	public void setPerfilImovel(String perfilImovel) {
	
		this.perfilImovel = perfilImovel;
	}
	
	public String getSiteEmpresa() {
	
		return siteEmpresa;
	}
	
	public void setSiteEmpresa(String siteEmpresa) {
	
		this.siteEmpresa = siteEmpresa;
	}
	
	public String getSituacaoPoco() {
	
		return situacaoPoco;
	}
	
	public void setSituacaoPoco(String situacaoPoco) {
	
		this.situacaoPoco = situacaoPoco;
	}
	
	public String getSubcategoria() {
	
		return subcategoria;
	}
	
	public void setSubcategoria(String subcategoria) {
	
		this.subcategoria = subcategoria;
	}
	
	public String getZeroOitossentosEmpresa() {
	
		return zeroOitossentosEmpresa;
	}
	
	public void setZeroOitossentosEmpresa(String zeroOitossentosEmpresa) {
	
		this.zeroOitossentosEmpresa = zeroOitossentosEmpresa;
	}

	
	public String getLocalidade() {
	
		return localidade;
	}

	
	public void setLocalidade(String localidade) {
	
		this.localidade = localidade;
	}

	
	public static long getSerialVersionUID() {
	
		return serialVersionUID;
	}

	
	public String getDescricaoAbreviadaEmpresa() {
	
		return descricaoAbreviadaEmpresa;
	}

	
	public void setDescricaoAbreviadaEmpresa(String descricaoAbreviadaEmpresa) {
	
		this.descricaoAbreviadaEmpresa = descricaoAbreviadaEmpresa;
	}


	/**
	 * @return Retorna o campo area.
	 */
	public String getArea() {
		return area;
	}


	/**
	 * @param area O area a ser setado.
	 */
	public void setArea(String area) {
		this.area = area;
	}


	/**
	 * @return Retorna o campo numeroHidrometro.
	 */
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}


	/**
	 * @param numeroHidrometro O numeroHidrometro a ser setado.
	 */
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}


	/**
	 * @return Retorna o campo nomeUsuario.
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}


	/**
	 * @param nomeUsuario O nomeUsuario a ser setado.
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}


	public Boolean getImovelComParcelamento() {
		return imovelComParcelamento;
	}


	public void setImovelComParcelamento(Boolean imovelComParcelamento) {
		this.imovelComParcelamento = imovelComParcelamento;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}
	
}
