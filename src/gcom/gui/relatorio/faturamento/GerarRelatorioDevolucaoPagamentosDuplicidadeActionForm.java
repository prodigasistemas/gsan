package gcom.gui.relatorio.faturamento;


import org.apache.struts.action.ActionForm;

/**
 * [UC1129] Gerar Relatório Devolução dos Pagamentos em Duplicidade
 * 
 * @author Hugo Leonardo
 *
 * @date 10/03/2011
 */

public class GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idGerencia;
	private String idUnidadeNegocio;
	private String idPerfilImovel;
	private String idCategoriaImovel;
	private String idLocalidade;
	private String nomeLocalidade;
	private String mesAnoReferenciaInicial;
	private String mesAnoReferenciaFinal;

	public void reset(){
		
		
		this.idGerencia = null;
		this.idUnidadeNegocio = null;
		this.idPerfilImovel = null;
		this.idCategoriaImovel = null;
		this.idLocalidade = null;
		this.nomeLocalidade = null;
		this.mesAnoReferenciaInicial = null;
		this.mesAnoReferenciaFinal = null;
		
	}

	public String getIdGerencia() {
		return idGerencia;
	}

	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getIdPerfilImovel() {
		return idPerfilImovel;
	}

	public void setIdPerfilImovel(String idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}

	public String getIdCategoriaImovel() {
		return idCategoriaImovel;
	}

	public void setIdCategoriaImovel(String idCategoriaImovel) {
		this.idCategoriaImovel = idCategoriaImovel;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getMesAnoReferenciaInicial() {
		return mesAnoReferenciaInicial;
	}

	public void setMesAnoReferenciaInicial(String mesAnoReferenciaInicial) {
		this.mesAnoReferenciaInicial = mesAnoReferenciaInicial;
	}

	public String getMesAnoReferenciaFinal() {
		return mesAnoReferenciaFinal;
	}

	public void setMesAnoReferenciaFinal(String mesAnoReferenciaFinal) {
		this.mesAnoReferenciaFinal = mesAnoReferenciaFinal;
	}
	
}
