package gcom.faturamento;

import gcom.util.filtro.Filtro;

public class FiltroFaturamentoSituacaoComando extends Filtro{
	 
	private static final long serialVersionUID = 1L;
	/**
     * Construtor da classe FiltroAreaConstruidaFaxia
     */
    public FiltroFaturamentoSituacaoComando() {
    }
    public FiltroFaturamentoSituacaoComando(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
    public final static String ID = "id";
    public final static String IMOVEL_ID = "imovel.id";
    public final static String LOCALIDADE_INICIAL_ID = "localidadeInicial.id";
    public final static String LOCALIDADE_FINAL_ID = "localidadeFinal.id";
    public final static String CODIGO_SETOR_INICIAL = "codigoSetorComercialInicial";
    public final static String CODIGO_SETOR_FINAL = "codigoSetorComercialFinal";
    
    public final static String NUMERO_QUADRA_FINAL = "numeroQuadraFinal";
    public final static String NUMERO_QUADRA_INICIAL = "numeroQuadraInicial";
    public final static String NUMERO_LOTE_INICIAL = "numeroLoteInicial";
    public final static String NUMERO_LOTE_FINAL = "numeroLoteFinal";
    public final static String NUMERO_SUBLOTE_INICIAL = "numeroSubLoteInicial";
    public final static String NUMERO_SUBLOTE_FINAL = "numeroSubLoteFinal";

    public final static String CODIGO_ROTA_INICIAL = "codigoRotaInicial";
    public final static String CODIGO_ROTA_FINAL = "codigoRotaFinal";
    public final static String SEQUENCIAL_ROTA_INICIAL = "sequencialRotaInicial";
    public final static String SEQUENCIAL_ROTA_FINAL = "sequencialRotaFinal";

    public final static String CATEGORIA_1_ID = "categoria1.id";
    public final static String CATEGORIA_2_ID = "categoria2.id";
    public final static String CATEGORIA_3_ID = "categoria3.id";
    public final static String CATEGORIA_4_ID = "categoria4.id";
    
    public final static String INDICADOR_CONSUMO = "indicadorConsumo";
    public final static String INDICADOR_COMANDO = "indicadorComando";
    
    public final static String FATURAMENTO_SITUACAO_TIPO_DESCRICAO = "faturamentoSituacaoTipo.descricao";

    public final static String IMOVEL = "imovel";
    public final static String FATURAMENTO_SITUACAO_TIPO = "faturamentoSituacaoTipo";
    public final static String LOCALIDADE_INICIAL = "localidadeInicial";
    public final static String LOCALIDADE_FINAL = "localidadeFinal";
    public final static String CATEGORIA_1 = "categoria1";
    public final static String CATEGORIA_2 = "categoria2";
    public final static String CATEGORIA_3 = "categoria3";
    public final static String CATEGORIA_4 = "categoria4";
    public final static String FATURAMENTO_SITUACAO_MOTIVO = "faturamentoSituacaoMotivo";
    public final static String USUARIO = "usuario";

}
