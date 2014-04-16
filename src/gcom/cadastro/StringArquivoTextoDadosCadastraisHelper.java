package gcom.cadastro;

import java.io.Serializable;


/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio de imoveis por Tipo de Consumo 
 *
 * @author Bruno Barros
 * @date 10/01/2008
 */
public class StringArquivoTextoDadosCadastraisHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String inscricaoImovel;
	private String matriculaImovel;
	private String nomeCliente;
	private String numeroImovel;
	private String complementoEndereco;
	private String situacaoAgua;
	private String situacaoImovel;
	private String situacaoEsgoto;
	private String codigoSubcategoria01;
	private String quantidadeSubcategoria01;
	private String codigoSubcategoria02;
	private String quantidadeSubcategoria02;
	private String codigoSubcategoria03;
	private String quantidadeSubcategoria03;
	private String codigoSubcategoria04;
	private String quantidadeSubcategoria04;
	private String codigoSubcategoria05;
	private String quantidadeSubcategoria05;
	private String tipoLogradouro;
	private String tituloLogradouro;
	private String nomeLogradouro;
	private String nomeBairro;
	
	
	public String getCodigoSubcategoria01() {
		return codigoSubcategoria01;
	}
	public void setCodigoSubcategoria01(String codigoSubcategoria01) {
		this.codigoSubcategoria01 = codigoSubcategoria01;
	}
	public String getCodigoSubcategoria02() {
		return codigoSubcategoria02;
	}
	public void setCodigoSubcategoria02(String codigoSubcategoria02) {
		this.codigoSubcategoria02 = codigoSubcategoria02;
	}
	public String getCodigoSubcategoria03() {
		return codigoSubcategoria03;
	}
	public void setCodigoSubcategoria03(String codigoSubcategoria03) {
		this.codigoSubcategoria03 = codigoSubcategoria03;
	}
	public String getCodigoSubcategoria04() {
		return codigoSubcategoria04;
	}
	public void setCodigoSubcategoria04(String codigoSubcategoria04) {
		this.codigoSubcategoria04 = codigoSubcategoria04;
	}
	public String getCodigoSubcategoria05() {
		return codigoSubcategoria05;
	}
	public void setCodigoSubcategoria05(String codigoSubcategoria05) {
		this.codigoSubcategoria05 = codigoSubcategoria05;
	}
	public String getComplementoEndereco() {
		return complementoEndereco;
	}
	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getNomeBairro() {
		return nomeBairro;
	}
	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getNomeLogradouro() {
		return nomeLogradouro;
	}
	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}
	public String getNumeroImovel() {
		return numeroImovel;
	}
	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}
	public String getQuantidadeSubcategoria01() {
		return quantidadeSubcategoria01;
	}
	public void setQuantidadeSubcategoria01(String quantidadeSubcategoria01) {
		this.quantidadeSubcategoria01 = quantidadeSubcategoria01;
	}
	public String getQuantidadeSubcategoria02() {
		return quantidadeSubcategoria02;
	}
	public void setQuantidadeSubcategoria02(String quantidadeSubcategoria02) {
		this.quantidadeSubcategoria02 = quantidadeSubcategoria02;
	}
	public String getQuantidadeSubcategoria03() {
		return quantidadeSubcategoria03;
	}
	public void setQuantidadeSubcategoria03(String quantidadeSubcategoria03) {
		this.quantidadeSubcategoria03 = quantidadeSubcategoria03;
	}
	public String getQuantidadeSubcategoria04() {
		return quantidadeSubcategoria04;
	}
	public void setQuantidadeSubcategoria04(String quantidadeSubcategoria04) {
		this.quantidadeSubcategoria04 = quantidadeSubcategoria04;
	}
	public String getQuantidadeSubcategoria05() {
		return quantidadeSubcategoria05;
	}
	public void setQuantidadeSubcategoria05(String quantidadeSubcategoria05) {
		this.quantidadeSubcategoria05 = quantidadeSubcategoria05;
	}
	public String getSituacaoAgua() {
		return situacaoAgua;
	}
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}
	public String getSituacaoImovel() {
		return situacaoImovel;
	}
	public void setSituacaoImovel(String situacaoImovel) {
		this.situacaoImovel = situacaoImovel;
	}
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	public String getTituloLogradouro() {
		return tituloLogradouro;
	}
	public void setTituloLogradouro(String tituloLogradouro) {
		this.tituloLogradouro = tituloLogradouro;
	}
	
	
}
