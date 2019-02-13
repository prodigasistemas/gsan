package gcom.micromedicao.consumo;

import gcom.util.filtro.Filtro;

public class FiltroComunicadoEmitirConta extends Filtro {

	private static final long serialVersionUID = -3846167904275753409L;

	public FiltroComunicadoEmitirConta() {}
	
	public FiltroComunicadoEmitirConta(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";
	public final static String IMOVEL = "imovel";
	public final static String IMOVEL_ID = "imovel.id";
	public final static String REFERENCIA = "referencia";
	public final static String INDICADOR_EMISSAO = "indicadorEmissao";
	public final static String TIPO_COMUNICADO = "tipoComunicado";
	public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";
	
}
