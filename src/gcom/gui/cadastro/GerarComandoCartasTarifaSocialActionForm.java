package gcom.gui.cadastro;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @author Vivianne Sousa
 * @data 23/03/2011
 */
public class GerarComandoCartasTarifaSocialActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
    
	private String codigoLocalidade;
	
	private String descricaoLocalidade;
	
	private String unidadeNegocioId;
	
	private String gerenciaRegionalId;
	
    private String tipoCarta;

    private String qtdeDiasAtraso;
    
    private String prazoComparecerCompesa;
    
    private String anoMesPesquisaInicial;
    
    private String anoMesPesquisaFinal;

	public String getAnoMesPesquisaFinal() {
		return anoMesPesquisaFinal;
	}

	public void setAnoMesPesquisaFinal(String anoMesPesquisaFinal) {
		this.anoMesPesquisaFinal = anoMesPesquisaFinal;
	}

	public String getAnoMesPesquisaInicial() {
		return anoMesPesquisaInicial;
	}

	public void setAnoMesPesquisaInicial(String anoMesPesquisaInicial) {
		this.anoMesPesquisaInicial = anoMesPesquisaInicial;
	}

	public String getCodigoLocalidade() {
		return codigoLocalidade;
	}

	public void setCodigoLocalidade(String codigoLocalidade) {
		this.codigoLocalidade = codigoLocalidade;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getGerenciaRegionalId() {
		return gerenciaRegionalId;
	}

	public void setGerenciaRegionalId(String gerenciaRegionalId) {
		this.gerenciaRegionalId = gerenciaRegionalId;
	}

	public String getPrazoComparecerCompesa() {
		return prazoComparecerCompesa;
	}

	public void setPrazoComparecerCompesa(String prazoComparecerCompesa) {
		this.prazoComparecerCompesa = prazoComparecerCompesa;
	}

	public String getQtdeDiasAtraso() {
		return qtdeDiasAtraso;
	}

	public void setQtdeDiasAtraso(String qtdeDiasAtraso) {
		this.qtdeDiasAtraso = qtdeDiasAtraso;
	}

	public String getTipoCarta() {
		return tipoCarta;
	}

	public void setTipoCarta(String tipoCarta) {
		this.tipoCarta = tipoCarta;
	}

	public String getUnidadeNegocioId() {
		return unidadeNegocioId;
	}

	public void setUnidadeNegocioId(String unidadeNegocioId) {
		this.unidadeNegocioId = unidadeNegocioId;
	}

	
}
