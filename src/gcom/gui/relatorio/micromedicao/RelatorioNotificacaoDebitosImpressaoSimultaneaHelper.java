package gcom.gui.relatorio.micromedicao;


import java.io.Serializable;

/**
 * [UC1021] Filtrar Notificação de Débitos para Impressão Simultânea 
 * 
 * @author Daniel Alves
 *
 * @date 17/05/2010
 */

public class RelatorioNotificacaoDebitosImpressaoSimultaneaHelper implements Serializable{
	
private static final long serialVersionUID = 1L;

    private String anoMesReferencia;	
	private String grupo;
	
	private String empresa;
	
	private String localidade;
	private String setorComercial;	
					
	private String rota;
	
	//Modifica o cabeçalho do relatorio tem
	//localidade, setor e rota
	private int cabecalhoTipo;
	
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

	public String getCodigoSetorComercial() {
		return setorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.setorComercial = codigoSetorComercial;
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

	public String getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	public int getCabecalhoTipo() {
		return cabecalhoTipo;
	}

	public void setCabecalhoTipo(int cabecalhoTipo) {
		this.cabecalhoTipo = cabecalhoTipo;
	}
			
	
}
