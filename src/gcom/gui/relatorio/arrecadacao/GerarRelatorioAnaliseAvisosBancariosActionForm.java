package gcom.gui.relatorio.arrecadacao;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0827] Gerar Relatório Análise dos Avisos Bancários
 * 
 * @see gcom.gui.relatorio.arrecadacao.ExibirGerarRelatorioAnaliseAvisosBancariosAction
 * @see gcom.gui.relatorio.arrecadacao.GerarRelatorioAnaliseAvisosBancariosAction
 * @see gcom.relatorio.arrecadacao.RelatorioAnaliseAvisosBancarios
 * 
 * @author Victor Cisneiros
 * @date 30/07/2008
 */
public class GerarRelatorioAnaliseAvisosBancariosActionForm extends ValidatorForm {
	
private static final long serialVersionUID = 1L;
	
	private String mesAno;
	private String estado;
	private String porArrecadador;
	private String porFormaArrecadacao;
	
	private String idArrecadador;
	private String idFormaArrecadacao;
	
	/**
	 * @return Retorna o campo idArrecadador.
	 */
	public String getIdArrecadador() {
		return idArrecadador;
	}
	/**
	 * @param idArrecadador O idArrecadador a ser setado.
	 */
	public void setIdArrecadador(String idArrecadador) {
		this.idArrecadador = idArrecadador;
	}
	/**
	 * @return Retorna o campo idFormaArrecadacao.
	 */
	public String getIdFormaArrecadacao() {
		return idFormaArrecadacao;
	}
	/**
	 * @param idFormaArrecadacao O idFormaArrecadacao a ser setado.
	 */
	public void setIdFormaArrecadacao(String idFormaArrecadacao) {
		this.idFormaArrecadacao = idFormaArrecadacao;
	}
	/**
	 * @return Retorna o campo estado.
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado O estado a ser setado.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return Retorna o campo mesAno.
	 */
	public String getMesAno() {
		return mesAno;
	}
	/**
	 * @param mesAno O mesAno a ser setado.
	 */
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	/**
	 * @return Retorna o campo porArrecadador.
	 */
	public String getPorArrecadador() {
		return porArrecadador;
	}
	/**
	 * @param porArrecadador O porArrecadador a ser setado.
	 */
	public void setPorArrecadador(String porArrecadador) {
		this.porArrecadador = porArrecadador;
	}
	/**
	 * @return Retorna o campo porFormaArrecadacao.
	 */
	public String getPorFormaArrecadacao() {
		return porFormaArrecadacao;
	}
	/**
	 * @param porFormaArrecadacao O porFormaArrecadacao a ser setado.
	 */
	public void setPorFormaArrecadacao(String porFormaArrecadacao) {
		this.porFormaArrecadacao = porFormaArrecadacao;
	}

}
