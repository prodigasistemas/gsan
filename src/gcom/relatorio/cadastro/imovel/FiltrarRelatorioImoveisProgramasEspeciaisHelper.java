package gcom.relatorio.cadastro.imovel;

import java.io.Serializable;


/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio de imoveis em Programas Especiais
 *
 * @author Hugo Leonardo
 * @date 18/01/2010
 */
public class FiltrarRelatorioImoveisProgramasEspeciaisHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String mesAnoReferencia;
	private String perfilImovel;
	private String nomePerfilImovel;
	private String tipo;
	private String opcaoTotalizacao;
	//private String idUnidadeNegocio;
	private String idRegiaoDesenvolvimento;
	private String idLocalidade;
	
	/**
	 * @return Returns the idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}
	
	/**
	 * @param idLocalidade The idLocalidade to set.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	
	/**
	 * @return Returns the mesAnoReferencia.
	 */
	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}
	
	/**
	 * @param mesAnoReferencia The mesAnoReferencia to set.
	 */
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}
	
	/**
	 * @return Returns the opcaoTotalizacao.
	 */
	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}
	
	/**
	 * @param opcaoTotalizacao The opcaoTotalizacao to set.
	 */
	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}
	
	/**
	 * @return Returns the perfilImovel.
	 */
	public String getPerfilImovel() {
		return perfilImovel;
	}
	
	/**
	 * @param perfilImovel The perfilImovel to set.
	 */
	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
	

	/**
	 * @return Returns the tipo.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo The tipo to set.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return Returns the nomePerfilImovel.
	 */
	public String getNomePerfilImovel() {
		return nomePerfilImovel;
	}

	/**
	 * @param nomePerfilImovel The nomePerfilImovel to set.
	 */
	public void setNomePerfilImovel(String nomePerfilImovel) {
		this.nomePerfilImovel = nomePerfilImovel;
	}

	public String getIdRegiaoDesenvolvimento() {
		return idRegiaoDesenvolvimento;
	}

	public void setIdRegiaoDesenvolvimento(String idRegiaoDesenvolvimento) {
		this.idRegiaoDesenvolvimento = idRegiaoDesenvolvimento;
	}
	
}
