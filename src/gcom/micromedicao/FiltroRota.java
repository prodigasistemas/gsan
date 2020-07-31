package gcom.micromedicao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class FiltroRota extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String CODIGO_ROTA = "codigo";
	
	public static final String ID_ROTA = "id";

    public static final String FATURAMENTO_GRUPO_ID = "faturamentoGrupo.id";

    public static final String INDICADOR_USO = "indicadorUso";
    
    public static final String INDICADOR_ROTA_ALTERNATIVA = "indicadorRotaAlternativa";

    public static final String GERENCIA_REGIONAL_ID = "setorComercial.localidade.gerenciaRegional.id";

    public final static String GERENCIA_REGIONAL_NOME_ABREVIADO = "setorComercial.localidade.gerenciaRegional.nomeAbreviado";

    public static final String LOCALIDADE_ID = "setorComercial.localidade.id";
    
    public final static String LOCALIDADE_DESCRICAO = "setorComercial.localidade.descricao";

    public static final String SETOR_COMERCIAL_ID = "setorComercial.id";

    public static final String SETOR_COMERCIAL_CODIGO = "setorComercial.codigo";
    
    public static final String UNIDADE_NEGOCIO = "setorComercial.localidade.unidadeNegocio";
    
    public static final String UNIDADE_NEGOCIO_ID = "setorComercial.localidade.unidadeNegocio.id";
    
    public static final String UNIDADE_NEGOCIO_NOME_ABREVIADO = "setorComercial.localidade.unidadeNegocio.nomeAbreviado";
    
    public static final String COBRANCA_GRUPO_ID = "cobrancaGrupo.id";
    
    public static final String COBRANCA_CRITERIO = "cobrancaCriterio";
    
    public static final String EMPRESA_COBRANCA_ID = "empresaCobranca.id";
    
    public static final String EMPRESA = "empresa";
    
    public static final String FATURAMENTO_GRUPO = "faturamentoGrupo";
    
    public static final String LOCALIDADE = "setorComercial.localidade";
    
    public static final String SETOR_COMERCIAL = "setorComercial";
    
    public static final String LEITURA_TIPO = "leituraTipo";
    
    public static final String LEITURA_TIPO_ID = "leituraTipo.id";
    
    public static final String EMPRESA_ID = "empresa.id";
    
    public static final String LEITURISTA_ID = "leiturista.id";
    
    public static final String LEITURISTA = "leiturista";
    
    public static final String LEITURA_SEQUENCIA = "numeroSequenciaLeitura";
    
    public static final String CLIENTE = "leiturista.cliente";
    
    public static final String EMPRESA_ENTREGA_CONTAS = "empresaEntregaContas.id";
    
    public static final String COBRANCA_GRUPO = "cobrancaGrupo";
    
    public static final String EMPRESA_COBRANCA = "empresaCobranca";
    
    public static final String INDICADOR_TRANSMISSAO_OFFLINE = "indicadorTransmissaoOffline";
    
    public static final String INDICADOR_IMPRESSAO_TERMICA_FINAL_GRUPO = "indicadorImpressaoTermicaFinalGrupo"; 

    public FiltroRota() {
    }

    public FiltroRota(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
