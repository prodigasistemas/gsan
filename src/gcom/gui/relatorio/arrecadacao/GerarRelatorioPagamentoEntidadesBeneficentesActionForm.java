package gcom.gui.relatorio.arrecadacao;

import org.apache.struts.action.ActionForm;
/**
 * action responsável pela exibição do Relatório de Pagamento para Entidades Beneficentes
 * [UC0959]
 * @author Daniel Alves
 * @created 13/01/2010
 */
public class GerarRelatorioPagamentoEntidadesBeneficentesActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String tipo;
	
	private String tipoRelatorio;
	
	private String idEntidadeBeneficente;
	
	private String mesAnoInicial;
	
	private String mesAnoFinal;

	private String indicadorEstado;

	private String indicadorGerenciaRegional;	               
		
	private String indicadorUnidadeNegocio;
	
	private String indicadorLocalidade;
	
	private String idGerenciaRegional;
	
	private String idUnidadeNegocio;
	
	private String idLocalidade;
	
	private int opcaoTotalizacao;
	
	
	/*public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
		mesAnoInicial = null;
		mesAnoFinal = null;		
		tipo = null;
	}*/

	
	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getIdEntidadeBeneficente() {
		return idEntidadeBeneficente;
	}


	public void setIdEntidadeBeneficente(String idEntidadeBeneficente) {
		this.idEntidadeBeneficente = idEntidadeBeneficente;
	}


	public String getMesAnoInicial() {
		return mesAnoInicial;
	}


	public void setMesAnoInicial(String mesAnoInicial) {
		this.mesAnoInicial = mesAnoInicial;
	}


	public String getMesAnoFinal() {
		return mesAnoFinal;
	}


	public void setMesAnoFinal(String mesAnoFinal) {
		this.mesAnoFinal = mesAnoFinal;
	}


	public String getIndicadorEstado() {
		return indicadorEstado;
	}


	public void setIndicadorEstado(String indicadorEstado) {
		this.indicadorEstado = indicadorEstado;
	}

	
	public String getIndicadorUnidadeNegocio() {
		return indicadorUnidadeNegocio;
	}


	public void setIndicadorUnidadeNegocio(String indicadorUnidadeNegocio) {
		this.indicadorUnidadeNegocio = indicadorUnidadeNegocio;
	}


	public String getIndicadorGerenciaRegional() {
		return indicadorGerenciaRegional;
	}


	public void setIndicadorGerenciaRegional(String indicadorGerenciaRegional) {
		this.indicadorGerenciaRegional = indicadorGerenciaRegional;
	}
	
	
	public String getIndicadorLocalidade() {
		return indicadorLocalidade;
	}


	public void setIndicadorLocalidade(String indicadorLocalidade) {
		this.indicadorLocalidade = indicadorLocalidade;
	}


	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}


	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}


	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}


	public String getIdLocalidade() {
		return idLocalidade;
	}


	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}


	public int getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}


	public void setOpcaoTotalizacao(int opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}


	public String getTipoRelatorio() {
		return tipoRelatorio;
	}


	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}
	
	
}
