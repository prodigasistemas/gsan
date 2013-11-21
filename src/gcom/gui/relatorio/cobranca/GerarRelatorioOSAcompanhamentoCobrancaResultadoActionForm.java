package gcom.gui.relatorio.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

public class GerarRelatorioOSAcompanhamentoCobrancaResultadoActionForm extends
		ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String comando;
	private String[] categoriaImovel;
	private String[] perfilImovel;
	private String[] gerenciaRegional;
	private String[] unidadeNegocio;
	private String idLocalidadeInicial;
	private String descricaoLocalidadeInicial;
	private String idLocalidadeFinal;
	private String descricaoLocalidadeFinal;
	private String idSetorComercialInicial;
	private String descricaoSetorComercialInicial;
	private String idSetorComercialFinal;
	private String descricaoSetorComercialFinal;
	private String idQuadraInicial;
	private String descricaoQuadraInicial;
	private String idQuadraFinal;
	private String descricaoQuadraFinal;
	private String tipoServico;
	
	
	public GerarRelatorioOSAcompanhamentoCobrancaResultadoActionForm() {
	}


	public GerarRelatorioOSAcompanhamentoCobrancaResultadoActionForm(String comando, String[] categoriaImovel, String[] perfilImovel, String[] gerenciaRegional, String[] unidadeNegocio, String idLocalidadeInicial, String descricaoLocalidadeInicial, String idLocalidadeFinal, String descricaoLocalidadeFinal, String idSetorComercialInicial, String descricaoSetorComercialInicial, String idSetorComercialFinal, String descricaoSetorComercialFinal, String idQuadraInicial, String descricaoQuadraInicial, String idQuadraFinal, String descricaoQuadraFinal, String tipoServico) {
		this.comando = comando;
		this.categoriaImovel = categoriaImovel;
		this.perfilImovel = perfilImovel;
		this.gerenciaRegional = gerenciaRegional;
		this.unidadeNegocio = unidadeNegocio;
		this.idLocalidadeInicial = idLocalidadeInicial;
		this.descricaoLocalidadeInicial = descricaoLocalidadeInicial;
		this.idLocalidadeFinal = idLocalidadeFinal;
		this.descricaoLocalidadeFinal = descricaoLocalidadeFinal;
		this.idSetorComercialInicial = idSetorComercialInicial;
		this.descricaoSetorComercialInicial = descricaoSetorComercialInicial;
		this.idSetorComercialFinal = idSetorComercialFinal;
		this.descricaoSetorComercialFinal = descricaoSetorComercialFinal;
		this.idQuadraInicial = idQuadraInicial;
		this.descricaoQuadraInicial = descricaoQuadraInicial;
		this.idQuadraFinal = idQuadraFinal;
		this.descricaoQuadraFinal = descricaoQuadraFinal;
		this.tipoServico = tipoServico;
	}





	public String getComando() {
		return comando;
	}


	public void setComando(String comando) {
		this.comando = comando;
	}


	public String[] getCategoriaImovel() {
		return categoriaImovel;
	}


	public void setCategoriaImovel(String[] categoriaImovel) {
		this.categoriaImovel = categoriaImovel;
	}


	public String getDescricaoLocalidadeFinal() {
		return descricaoLocalidadeFinal;
	}


	public void setDescricaoLocalidadeFinal(String descricaoLocalidadeFinal) {
		this.descricaoLocalidadeFinal = descricaoLocalidadeFinal;
	}


	public String getDescricaoLocalidadeInicial() {
		return descricaoLocalidadeInicial;
	}


	public void setDescricaoLocalidadeInicial(String descricaoLocalidadeInicial) {
		this.descricaoLocalidadeInicial = descricaoLocalidadeInicial;
	}


	public String getDescricaoQuadraFinal() {
		return descricaoQuadraFinal;
	}


	public void setDescricaoQuadraFinal(String descricaoQuadraFinal) {
		this.descricaoQuadraFinal = descricaoQuadraFinal;
	}


	public String getDescricaoQuadraInicial() {
		return descricaoQuadraInicial;
	}


	public void setDescricaoQuadraInicial(String descricaoQuadraInicial) {
		this.descricaoQuadraInicial = descricaoQuadraInicial;
	}


	public String getDescricaoSetorComercialFinal() {
		return descricaoSetorComercialFinal;
	}


	public void setDescricaoSetorComercialFinal(String descricaoSetorComercialFinal) {
		this.descricaoSetorComercialFinal = descricaoSetorComercialFinal;
	}


	public String getDescricaoSetorComercialInicial() {
		return descricaoSetorComercialInicial;
	}


	public void setDescricaoSetorComercialInicial(
			String descricaoSetorComercialInicial) {
		this.descricaoSetorComercialInicial = descricaoSetorComercialInicial;
	}


	public String[] getGerenciaRegional() {
		return gerenciaRegional;
	}


	public void setGerenciaRegional(String[] gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}


	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}


	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}


	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}


	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}


	public String getIdQuadraFinal() {
		return idQuadraFinal;
	}


	public void setIdQuadraFinal(String idQuadraFinal) {
		this.idQuadraFinal = idQuadraFinal;
	}


	public String getIdQuadraInicial() {
		return idQuadraInicial;
	}


	public void setIdQuadraInicial(String idQuadraInicial) {
		this.idQuadraInicial = idQuadraInicial;
	}


	public String getIdSetorComercialFinal() {
		return idSetorComercialFinal;
	}


	public void setIdSetorComercialFinal(String idSetorComercialFinal) {
		this.idSetorComercialFinal = idSetorComercialFinal;
	}


	public String getIdSetorComercialInicial() {
		return idSetorComercialInicial;
	}


	public void setIdSetorComercialInicial(String idSetorComercialInicial) {
		this.idSetorComercialInicial = idSetorComercialInicial;
	}


	public String[] getPerfilImovel() {
		return perfilImovel;
	}


	public void setPerfilImovel(String[] perfilImovel) {
		this.perfilImovel = perfilImovel;
	}


	public String getTipoServico() {
		return tipoServico;
	}


	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}


	public String[] getUnidadeNegocio() {
		return unidadeNegocio;
	}


	public void setUnidadeNegocio(String[] unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	
	
	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
	}
	
	public boolean validarCamposVazios(){
		boolean retorno = false;
		if(
				   (this.categoriaImovel  != null && !this.categoriaImovel.equals(""))
				|| (this.perfilImovel  != null && !this.perfilImovel.equals(""))
				|| (this.gerenciaRegional  != null && !this.gerenciaRegional.equals(""))
				|| (this.unidadeNegocio  != null && !this.gerenciaRegional.equals(""))
				|| (this.idLocalidadeInicial  != null && !this.idLocalidadeInicial.equals("")) 
				|| (this.descricaoLocalidadeInicial  != null && !this.descricaoLocalidadeInicial.equals(""))
				|| (this.idLocalidadeFinal  != null && !this.idLocalidadeFinal.equals(""))
				|| (this.descricaoLocalidadeFinal  != null && !this.descricaoLocalidadeFinal.equals(""))
				|| (this.idSetorComercialInicial  != null && !this.idSetorComercialInicial.equals(""))
				|| (this.descricaoSetorComercialInicial  != null && !this.descricaoSetorComercialInicial.equals(""))
				|| (this.idSetorComercialFinal  != null && !this.idSetorComercialFinal.equals(""))
				|| (this.descricaoSetorComercialFinal  != null && !this.descricaoSetorComercialFinal.equals(""))
				|| (this.idQuadraInicial  != null && !this.idQuadraInicial.equals(""))
				|| (this.descricaoQuadraInicial  != null && !this.descricaoQuadraInicial.equals(""))
				|| (this.idQuadraFinal  != null && !this.idQuadraFinal.equals(""))
				|| (this.descricaoQuadraFinal  != null && !this.descricaoQuadraFinal.equals(""))
				|| (this.tipoServico  != null && !this.tipoServico.equals("-1"))
		)
			retorno = true;
		
		return retorno;
	}
	

}
