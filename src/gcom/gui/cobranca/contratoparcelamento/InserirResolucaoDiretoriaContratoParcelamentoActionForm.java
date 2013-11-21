package gcom.gui.cobranca.contratoparcelamento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * Form que Inseri uma ResolucaoDiretoriaContratoParcelamentoCliente
 * 
 * @author Paulo Otávio
 * @date 17/03/2011
 */
public class InserirResolucaoDiretoriaContratoParcelamentoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String numero = "";
	
	private String assunto = "";
	
	private String dataVigenciaInicial = "";
	
	private String dataVigenciaFinal = "";
	
	private Short indicadorDebitoAcrescimo = null;
	
	private Short indicadorParcelamentoJuros = null;
	
	private Short indicadorInformarParcela = null;
	
	private String qtdFaturasParceladas= "";
	
	private String formaPgto = "";
	
	public InserirResolucaoDiretoriaContratoParcelamentoActionForm(){}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDataVigenciaFinal() {
		return dataVigenciaFinal;
	}

	public void setDataVigenciaFinal(String dataVigenciaFinal) {
		this.dataVigenciaFinal = dataVigenciaFinal;
	}

	public String getDataVigenciaInicial() {
		return dataVigenciaInicial;
	}

	public void setDataVigenciaInicial(String dataVigenciaInicial) {
		this.dataVigenciaInicial = dataVigenciaInicial;
	}

	public String getFormaPgto() {
		return formaPgto;
	}

	public void setFormaPgto(String formaPgto) {
		this.formaPgto = formaPgto;
	}

	public Short getIndicadorDebitoAcrescimo() {
		return indicadorDebitoAcrescimo;
	}

	public void setIndicadorDebitoAcrescimo(Short indicadorDebitoAcrescimo) {
		this.indicadorDebitoAcrescimo = indicadorDebitoAcrescimo;
	}

	public Short getIndicadorInformarParcela() {
		return indicadorInformarParcela;
	}

	public void setIndicadorInformarParcela(Short indicadorInformarParcela) {
		this.indicadorInformarParcela = indicadorInformarParcela;
	}

	public Short getIndicadorParcelamentoJuros() {
		return indicadorParcelamentoJuros;
	}

	public void setIndicadorParcelamentoJuros(Short indicadorParcelamentoJuros) {
		this.indicadorParcelamentoJuros = indicadorParcelamentoJuros;
	}

	public String getQtdFaturasParceladas() {
		return qtdFaturasParceladas;
	}

	public void setQtdFaturasParceladas(String qtdFaturasParceladas) {
		this.qtdFaturasParceladas = qtdFaturasParceladas;
	}

}
