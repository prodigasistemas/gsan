package gcom.gui.relatorio.micromedicao;


import org.apache.struts.action.ActionForm;

/**
 * [UC1021] Filtrar Notificação de Débitos para Impressão Simultânea 
 * 
 * @author Daniel Alves
 *
 * @date 17/05/2010
 */

public class FiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm extends ActionForm {
	
private static final long serialVersionUID = 1L;

    private String anoMesReferencia;	
	private String grupo;
	
	private String empresa;
	
	private String localidade;
	private String nomeLocalidade;
	
	private String codigoSetorComercial;
	private String setorComercialDescricao;
					
	private String rota;
	
	public void reset(){
		this.anoMesReferencia = null;
		
		this.grupo = null;
		this.empresa = null;
		
		this.localidade = null;
		this.nomeLocalidade = null;
		
		this.codigoSetorComercial = null;
		this.setorComercialDescricao = null;
		
		this.rota = null;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getSetorComercialDescricao() {
		return setorComercialDescricao;
	}

	public void setSetorComercialDescricao(String setorComercialDescricao) {
		this.setorComercialDescricao = setorComercialDescricao;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

			
	
}
