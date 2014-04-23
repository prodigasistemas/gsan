package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * [UC0911] - Gerar Cartas da Campanha de Solidariedade da Criança para Negociação a Vista
 * 
 * @author Vivianne Sousa
 * @data 15/06/2009
 */
public class GerarCartasCampanhaSolidariedadeCriancaNegociacaoAVistaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
    
    private String grupoFaturamento;
    private String acao;

	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}
    

	
}
