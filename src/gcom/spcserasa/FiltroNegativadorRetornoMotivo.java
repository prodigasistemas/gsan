package gcom.spcserasa;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroNegativadorRetornoMotivo extends Filtro implements Serializable {

	private final static long serialVersionUID = 1L;

	public final static String ID = "id";

	public final static String DESCRICAO_RETORNO_CODIGO = "descricaoRetornocodigo";

	public final static String INDICADOR_USO = "indicadorUso";

	public final static String INDICADOR_REGISTRO_ACEITO = "indicadorRegistroAceito";

	public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";

	public final static String NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR = "negativador.id";

	public final static String NEGATIVADOR_MOVIMENTO_REG_RET_MOT = "negativadorMovimentoRegRetMot";

	public final static String CODIGO_RETORNO_MOTIVO = "codigoRetornoMotivo";

	public FiltroNegativadorRetornoMotivo() {
	}

	public FiltroNegativadorRetornoMotivo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
}