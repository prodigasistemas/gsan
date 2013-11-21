package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * 
 * @author Mariana Vcitor
 * @date 18/01/2011
 * 
 */
public class RelatorioVisitaCobrancaSubBean implements RelatorioBean {
	
	private String representacaoNumericaCodBarraFormatada;
	private String representacaoNumericaCodBarraSemDigito;
	private String idImovel;
	private String nossoNumero;
	private String valorDocumento;
	private String sacadoParte01;
	private String sacadoParte03;
	
	public RelatorioVisitaCobrancaSubBean() {
		super();
	}

	public RelatorioVisitaCobrancaSubBean(String representacaoNumericaCodBarraFormatada,
			String representacaoNumericaCodBarraSemDigito, String idImovel, String nossoNumero,
			String valorDocumento, String sacadoParte01, String sacadoParte03) {
		super();
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
		this.idImovel = idImovel;
		this.nossoNumero = nossoNumero;
		this.valorDocumento = valorDocumento;
		this.sacadoParte01 = sacadoParte01;
		this.sacadoParte03 = sacadoParte03;
	}

	public String getRepresentacaoNumericaCodBarraFormatada() {
		return representacaoNumericaCodBarraFormatada;
	}

	public void setRepresentacaoNumericaCodBarraFormatada(
			String representacaoNumericaCodBarraFormatada) {
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	}

	public String getRepresentacaoNumericaCodBarraSemDigito() {
		return representacaoNumericaCodBarraSemDigito;
	}

	public void setRepresentacaoNumericaCodBarraSemDigito(
			String representacaoNumericaCodBarraSemDigito) {
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public String getSacadoParte01() {
		return sacadoParte01;
	}

	public void setSacadoParte01(String sacadoParte01) {
		this.sacadoParte01 = sacadoParte01;
	}

	public String getSacadoParte03() {
		return sacadoParte03;
	}

	public void setSacadoParte03(String sacadoParte03) {
		this.sacadoParte03 = sacadoParte03;
	}

	public String getValorDocumento() {
		return valorDocumento;
	}

	public void setValorDocumento(String valorDocumento) {
		this.valorDocumento = valorDocumento;
	}
	
	
}
