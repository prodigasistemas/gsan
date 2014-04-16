package gcom.gui.relatorio.cadastro.imovel;


import org.apache.struts.action.ActionForm;

/**
 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais
 * 
 * @author Hugo Leonardo
 *
 * @date 14/01/2010
 */

public class GerarRelatorioImoveisProgramasEspeciaisActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String mesAnoReferencia;
	private String perfilImovel;
	private String tipo;
	private String opcaoTotalizacao;
	private String regiaoDesenvolvimento;
	private String nomeRegiaoDesenvolvimento;
	//private String unidadeNegocio;
	//private String nomeUnidadeNegocio;
	private String idLocalidade;
	private String nomeLocalidade;

	public void reset(){
		
		this.mesAnoReferencia = null;
		this.perfilImovel = null;
		this.tipo = null;
		this.opcaoTotalizacao = null;
		//this.unidadeNegocio = null;
		//this.nomeUnidadeNegocio = null;
		this.idLocalidade = null;
		this.nomeLocalidade = null;
	}

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
	 * @return Returns the nomeLocalidade.
	 */
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	/**
	 * @param nomeLocalidade The nomeLocalidade to set.
	 */
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
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

	public String getNomeRegiaoDesenvolvimento() {
		return nomeRegiaoDesenvolvimento;
	}

	public void setNomeRegiaoDesenvolvimento(String nomeRegiaoDesenvolvimento) {
		this.nomeRegiaoDesenvolvimento = nomeRegiaoDesenvolvimento;
	}

	public String getRegiaoDesenvolvimento() {
		return regiaoDesenvolvimento;
	}

	public void setRegiaoDesenvolvimento(String regiaoDesenvolvimento) {
		this.regiaoDesenvolvimento = regiaoDesenvolvimento;
	}
	
	
}
