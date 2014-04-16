package gcom.gui.micromedicao;

import org.apache.struts.action.ActionForm;

/**
 * Classe responsável por ajudar o caso de uso [UC0269] Resumo de Ligacoes Economias 
 *
 * @author Thiago Toscano
 * @date 20/04/2006 
 */
public class GerarRelatorioAnormalidadeLeituraPeriodoActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	
	private String idUnidadeNegocio;

	private String idLocalidadeInicial;
	private String nomeLocalidadeInicial;
	
	private String idLocalidadeFinal;
	private String nomeLocalidadeFinal;

//	private String idSetorComercialInicial; 
//	private String idSetorComercialFinal;

	private String codigoSetorComercialInicial;
	private String codigoSetorComercialFinal;

	private String nomeSetorComercialInicial;
	private String nomeSetorComercialFinal;

	private String codigoRotaInicial;
	private String codigoRotaFinal;
	
	private String sequencialRotaInicial;
	private String sequencialRotaFinal;
	
	private String mesAnoReferenciaInicial;
	private String mesAnoReferenciaFinal;
	
	private String idAnormalidadeLeitura;
	
	private String idGrupoFaturamento;

	
	public void limparForm(){
		 idUnidadeNegocio= "";

		 idLocalidadeInicial= "";
		 nomeLocalidadeInicial= "";
		
		 idLocalidadeFinal= "";
		 nomeLocalidadeFinal= "";

//		 idSetorComercialInicial= ""; 
//		 idSetorComercialFinal= "";

		 codigoSetorComercialInicial= "";
		 codigoSetorComercialFinal= "";

		 nomeSetorComercialInicial= "";
		 nomeSetorComercialFinal= "";

		 codigoRotaInicial= "";
		 codigoRotaFinal= "";
		
		 sequencialRotaInicial= "";
		 sequencialRotaFinal= "";
		
		 mesAnoReferenciaInicial= "";
		 mesAnoReferenciaFinal= "";
		
		 idAnormalidadeLeitura= "";
		
		 idGrupoFaturamento= "";
	}
	
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}

//	public String getIdSetorComercialInicial() {
//		return idSetorComercialInicial;
//	}
//
//	public void setIdSetorComercialInicial(String idSetorComercialInicial) {
//		this.idSetorComercialInicial = idSetorComercialInicial;
//	}
//
//	public String getIdSetorComercialFinal() {
//		return idSetorComercialFinal;
//	}
//
//	public void setIdSetorComercialFinal(String idSetorComercialFinal) {
//		this.idSetorComercialFinal = idSetorComercialFinal;
//	}

	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}

	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}

	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}

	public void setNomeSetorComercialFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}

	public String getCodigoRotaInicial() {
		return codigoRotaInicial;
	}

	public void setCodigoRotaInicial(String codigoRotaInicial) {
		this.codigoRotaInicial = codigoRotaInicial;
	}

	public String getCodigoRotaFinal() {
		return codigoRotaFinal;
	}

	public void setCodigoRotaFinal(String codigoRotaFinal) {
		this.codigoRotaFinal = codigoRotaFinal;
	}

	public String getMesAnoReferenciaInicial() {
		return mesAnoReferenciaInicial;
	}

	public void setMesAnoReferenciaInicial(String referenciaInicial) {
		this.mesAnoReferenciaInicial = referenciaInicial;
	}

	public String getMesAnoReferenciaFinal() {
		return mesAnoReferenciaFinal;
	}

	public void setMesAnoReferenciaFinal(String referenciaFinal) {
		this.mesAnoReferenciaFinal = referenciaFinal;
	}

	public String getIdAnormalidadeLeitura() {
		return idAnormalidadeLeitura;
	}

	public void setIdAnormalidadeLeitura(String idAnormalidadeLeitura) {
		this.idAnormalidadeLeitura = idAnormalidadeLeitura;
	}

	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public String getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}

	public void setSequencialRotaInicial(String sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}

	public String getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}

	public void setSequencialRotaFinal(String sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}
}
