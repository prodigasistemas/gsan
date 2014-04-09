package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form responsável pelas propiedades do caso de uso de inserir os
 * critérios da cobrança.
 * 
 * @author Sávio Luiz
 * @date 29/05/2006
 */
public class InserirCriterioCobrancaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String descricaoCriterio;

	private String dataInicioVigencia;

	private String numeroAnoContaAntiga;

	private String opcaoAcaoImovelSitEspecial;

	private String opcaoAcaoImovelSit;

	private String opcaoContasRevisao;

	private String opcaoAcaoImovelDebitoMesConta;

	private String opcaoAcaoInquilinoDebitoMesConta;
	
	private String opcaoAcaoImovelDebitoContasAntigas;

	private String valorDebitoMinimo;

	private String valorDebitoMaximo;

	private String qtdContasMinima;

	private String qtdContasMaxima;

	private String vlMinimoDebitoCliente;

	private String qtdMinContasCliente;

	private String vlMinimoContasMes;

	public String getDataInicioVigencia() {
		return dataInicioVigencia;
	}

	public void setDataInicioVigencia(String dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}

	public String getDescricaoCriterio() {
		return descricaoCriterio;
	}

	public void setDescricaoCriterio(String descricaoCriterio) {
		this.descricaoCriterio = descricaoCriterio;
	}

	public String getNumeroAnoContaAntiga() {
		return numeroAnoContaAntiga;
	}

	public void setNumeroAnoContaAntiga(String numeroAnoContaAntiga) {
		this.numeroAnoContaAntiga = numeroAnoContaAntiga;
	}

	public String getOpcaoAcaoImovelDebitoMesConta() {
		return opcaoAcaoImovelDebitoMesConta;
	}

	public void setOpcaoAcaoImovelDebitoMesConta(
			String opcaoAcaoImovelDebitoMesConta) {
		this.opcaoAcaoImovelDebitoMesConta = opcaoAcaoImovelDebitoMesConta;
	}

	public String getOpcaoAcaoImovelSit() {
		return opcaoAcaoImovelSit;
	}

	public void setOpcaoAcaoImovelSit(String opcaoAcaoImovelSit) {
		this.opcaoAcaoImovelSit = opcaoAcaoImovelSit;
	}

	public String getOpcaoAcaoImovelSitEspecial() {
		return opcaoAcaoImovelSitEspecial;
	}

	public void setOpcaoAcaoImovelSitEspecial(String opcaoAcaoImovelSitEspecial) {
		this.opcaoAcaoImovelSitEspecial = opcaoAcaoImovelSitEspecial;
	}

	public String getOpcaoAcaoInquilinoDebitoMesConta() {
		return opcaoAcaoInquilinoDebitoMesConta;
	}

	public void setOpcaoAcaoInquilinoDebitoMesConta(
			String opcaoAcaoInquilinoDebitoMesConta) {
		this.opcaoAcaoInquilinoDebitoMesConta = opcaoAcaoInquilinoDebitoMesConta;
	}

	public String getOpcaoContasRevisao() {
		return opcaoContasRevisao;
	}

	public void setOpcaoContasRevisao(String opcaoContasRevisao) {
		this.opcaoContasRevisao = opcaoContasRevisao;
	}

	public String getQtdContasMaxima() {
		return qtdContasMaxima;
	}

	public void setQtdContasMaxima(String qtdContasMaxima) {
		this.qtdContasMaxima = qtdContasMaxima;
	}

	public String getQtdContasMinima() {
		return qtdContasMinima;
	}

	public void setQtdContasMinima(String qtdContasMinima) {
		this.qtdContasMinima = qtdContasMinima;
	}

	public String getQtdMinContasCliente() {
		return qtdMinContasCliente;
	}

	public void setQtdMinContasCliente(String qtdMinContasCliente) {
		this.qtdMinContasCliente = qtdMinContasCliente;
	}

	public String getValorDebitoMaximo() {
		return valorDebitoMaximo;
	}

	public void setValorDebitoMaximo(String valorDebitoMaximo) {
		this.valorDebitoMaximo = valorDebitoMaximo;
	}

	public String getValorDebitoMinimo() {
		return valorDebitoMinimo;
	}

	public void setValorDebitoMinimo(String valorDebitoMinimo) {
		this.valorDebitoMinimo = valorDebitoMinimo;
	}

	public String getVlMinimoContasMes() {
		return vlMinimoContasMes;
	}

	public void setVlMinimoContasMes(String vlMinimoContasMes) {
		this.vlMinimoContasMes = vlMinimoContasMes;
	}

	public String getVlMinimoDebitoCliente() {
		return vlMinimoDebitoCliente;
	}

	public void setVlMinimoDebitoCliente(String vlMinimoDebitoCliente) {
		this.vlMinimoDebitoCliente = vlMinimoDebitoCliente;
	}
	
	

	public String getOpcaoAcaoImovelDebitoContasAntigas() {
		return opcaoAcaoImovelDebitoContasAntigas;
	}

	public void setOpcaoAcaoImovelDebitoContasAntigas(
			String opcaoAcaoImovelDebitoContasAntigas) {
		this.opcaoAcaoImovelDebitoContasAntigas = opcaoAcaoImovelDebitoContasAntigas;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		descricaoCriterio = null;
		dataInicioVigencia = null;
		numeroAnoContaAntiga = null;
		opcaoAcaoImovelSitEspecial = null;
		opcaoAcaoImovelSit = null;
		opcaoContasRevisao = null;
		opcaoAcaoImovelDebitoMesConta = null;
		opcaoAcaoInquilinoDebitoMesConta = null;
		opcaoAcaoImovelDebitoContasAntigas = null;
		valorDebitoMinimo = null;
		valorDebitoMaximo = null;
		qtdContasMinima = null;
		qtdContasMaxima = null;
		vlMinimoDebitoCliente = null;
		qtdMinContasCliente = null;
		vlMinimoContasMes = null;
	}

}
