package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * @author Raphael Rossiter
 * @date 03/03/2007
 */
public class FiltroFiscalizacaoSituacaoServicoACobrar extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	public FiltroFiscalizacaoSituacaoServicoACobrar() {
	}

	
	public FiltroFiscalizacaoSituacaoServicoACobrar(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	
	public final static String ID_FISCALIZACAO_SITUACAO = "comp_id.idFiscalizacaoSituacao";
	
	
	public final static String ID_DEBITO_TIPO = "comp_id.idDebitoTipo";
	
	public final static String CONSUMO_CALCULO = "consumoCalculo";
	
//	public final static String CONSUMO_CALCULO = "consumoCalculo";
	

}
