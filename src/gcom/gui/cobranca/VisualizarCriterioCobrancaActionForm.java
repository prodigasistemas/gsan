package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 2 de Junho de 2004
 */
public class VisualizarCriterioCobrancaActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	
	private String opcaoImovelSitEspecial;
	private String opcaoImovelSitCobranca;
	private String opcaoContaRevisao;
	private String opcaoImovelDebito;
	private String opcaoInqDebitoConta;
	private String opcaoInqDebitoContaAntiga;
	private String descricaoCriterio;
	private String dataInicio;
	private String dataFim;
	private String numeroAnos;
	
	
	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getNumeroAnos() {
		return numeroAnos;
	}

	public void setNumeroAnos(String numeroAnos) {
		this.numeroAnos = numeroAnos;
	}

	public String getDescricaoCriterio() {
		return descricaoCriterio;
	}

	public void setDescricaoCriterio(String descricaoCriterio) {
		this.descricaoCriterio = descricaoCriterio;
	}

	public String getOpcaoContaRevisao() {
		return opcaoContaRevisao;
	}

	public void setOpcaoContaRevisao(String opcaoContaRevisao) {
		this.opcaoContaRevisao = opcaoContaRevisao;
	}

	public String getOpcaoImovelDebito() {
		return opcaoImovelDebito;
	}

	public void setOpcaoImovelDebito(String opcaoImovelDebito) {
		this.opcaoImovelDebito = opcaoImovelDebito;
	}

	public String getOpcaoInqDebitoContaAntiga() {
		return opcaoInqDebitoContaAntiga;
	}

	public void setOpcaoInqDebitoContaAntiga(String opcaoInqDebitoContaAntiga) {
		this.opcaoInqDebitoContaAntiga = opcaoInqDebitoContaAntiga;
	}


	public String getOpcaoInqDebitoConta() {
		return opcaoInqDebitoConta;
	}

	public void setOpcaoInqDebitoConta(String opcaoInqDebitoConta) {
		this.opcaoInqDebitoConta = opcaoInqDebitoConta;
	}

	public String getOpcaoImovelSitCobranca() {
		return opcaoImovelSitCobranca;
	}

	public void setOpcaoImovelSitCobranca(String opcaoImovelSitCobranca) {
		this.opcaoImovelSitCobranca = opcaoImovelSitCobranca;
	}

	public String getOpcaoImovelSitEspecial() {
		return opcaoImovelSitEspecial;
	}

	public void setOpcaoImovelSitEspecial(String opcaoImovelSitEspecial) {
		this.opcaoImovelSitEspecial = opcaoImovelSitEspecial;
	}
	
	
	
	
}

