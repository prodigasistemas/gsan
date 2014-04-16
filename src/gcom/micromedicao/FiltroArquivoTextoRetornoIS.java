package gcom.micromedicao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroArquivoTextoRetornoIS extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final String ID_ARQUIVO_TEXTO_RETORNO_IS = "id";
	
	public static final String ANO_MES_REFERENCIA = "anoMesReferencia";
	
	public static final String CODIGO_SETOR = "codigoSetorComercial";
	
	public static final String CODIGO_ROTA = "codigo";
	
	public static final String NOME_ARQUIVO = "nomeArquivo";
	
	public static final String TEMPO_RETORNO_ARQUIVO = "tempoRetornoArquivo";
	
	public static final String TEMPO_FINALIZACAO = "tempoFinalizacao";
	
    public static final String FATURAMENTO_GRUPO_ID = "faturamentoGrupo.id";
    
    public static final String LOCALIDADE_ID = "localidade.id";

}
