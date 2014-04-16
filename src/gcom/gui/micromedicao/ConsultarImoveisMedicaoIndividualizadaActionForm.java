package gcom.gui.micromedicao;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Rhawi Dantas
 * @since 18/01/2006 [UC0180] Consultar Imoveis com Medicao Individualizada
 */
public class ConsultarImoveisMedicaoIndividualizadaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String inscricaoImovel;

	private String nomeCliente;

	private String situacaoAgua;

	private String situacaoEsgoto;

	private String codigoImovel;
	
	private String rateioTipo;
	
	private String quantidadeDeImoveisVinculados;
	
	private String endereco;
	
	private String[] idDebitoACobrar;

	public String getCodigoImovel() {
		return codigoImovel;
	}

	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	public String[] getIdDebitoACobrar() {
		return idDebitoACobrar;
	}

	public void setIdDebitoACobrar(String[] idDebitoACobrar) {
		this.idDebitoACobrar = idDebitoACobrar;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	public String getRateioTipo() {
		return rateioTipo;
	}

	public void setRateioTipo(String rateioTipo) {
		this.rateioTipo = rateioTipo;
	}

	public String getQuantidadeDeImoveisVinculados() {
		return quantidadeDeImoveisVinculados;
	}

	public void setQuantidadeDeImoveisVinculados(
			String quantidadeDeImoveisVinculados) {
		this.quantidadeDeImoveisVinculados = quantidadeDeImoveisVinculados;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		 this.inscricaoImovel= null;

		 this.nomeCliente= null;

		 this.situacaoAgua= null;

		 this.situacaoEsgoto= null;

		 this.codigoImovel= null;
		
		 this.rateioTipo= null;
		
		 this.quantidadeDeImoveisVinculados= null;
		
		 this.endereco= null;
	}

	
	

}
