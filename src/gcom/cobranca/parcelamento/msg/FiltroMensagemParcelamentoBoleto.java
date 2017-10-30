package gcom.cobranca.parcelamento.msg;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroMensagemParcelamentoBoleto extends Filtro implements Serializable {

	private static final long serialVersionUID = -6949222685995508237L;
	
	public final static String ID = "id";
	public final static String INICIO_VIGENCIA = "dataInicioVigencia";
	public final static String FIM_VIGENCIA = "dataFimVigencia";
	
	public FiltroMensagemParcelamentoBoleto() {
	}

	public FiltroMensagemParcelamentoBoleto(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

}
