package gcom.gui.micromedicao;

import org.apache.struts.action.ActionForm;

/**
 * [UC0218] Manter Situacao Usuario
 * @author Thiago Tenório
 * @since 10/04/2006
 */
public class LiberarArquivoTextoLeituraActionForm  extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String empresaID;

	private String grupoFaturamentoID;
	
	private String mesAno;
	
	private String situaTransmLeitura;
	
	private String numeroArquivo;
	
	private String quantidadeImovel;
	
	private String localidadeID;
	
	private String	numeroFoneLeiturista;
	
	private String ultimaAlteracao;

	public String getLocalidadeID() {
		return localidadeID;
	}

	public void setLocalidadeID(String localidadeID) {
		this.localidadeID = localidadeID;
	}

	public String getNumeroArquivo() {
		return numeroArquivo;
	}

	public void setNumeroArquivo(String numeroArquivo) {
		this.numeroArquivo = numeroArquivo;
	}

	public String getNumeroFoneLeiturista() {
		return numeroFoneLeiturista;
	}

	public void setNumeroFoneLeiturista(String numeroFoneLeiturista) {
		this.numeroFoneLeiturista = numeroFoneLeiturista;
	}

	public String getQuantidadeImovel() {
		return quantidadeImovel;
	}

	public void setQuantidadeImovel(String quantidadeImovel) {
		this.quantidadeImovel = quantidadeImovel;
	}

	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getEmpresaID() {
		return empresaID;
	}

	public void setEmpresaID(String empresaID) {
		this.empresaID = empresaID;
	}

	public String getGrupoFaturamentoID() {
		return grupoFaturamentoID;
	}

	public void setGrupoFaturamentoID(String grupoFaturamentoID) {
		this.grupoFaturamentoID = grupoFaturamentoID;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getSituaTransmLeitura() {
		return situaTransmLeitura;
	}

	public void setSituaTransmLeitura(String situaTransmLeitura) {
		this.situaTransmLeitura = situaTransmLeitura;
	}
}
