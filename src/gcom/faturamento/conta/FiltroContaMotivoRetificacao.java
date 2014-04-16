package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * filtro de Motivo Retificacao de conta
 * 
 * @author  Francisco do Nascimento
 * @created 14 de setembro de 2007
 */
public class FiltroContaMotivoRetificacao extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Description of the Field
     */
    public final static String CODIGO = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";
    /**
     * Description of the Field
     */
    public final static String NUMERO_OCORRENCIAS_NO_ANO = "numeroOcorrenciasNoAno";
    /**
     * Description of the Field
     */
    public final static String INDICADOR_COMPETENCIA_CONSUMO = "indicadorCompetenciaConsumo";
    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";

    /**
     * Construtor da classe FiltroContaMotivoRetificacao
     */
    public FiltroContaMotivoRetificacao() {
    }

    /**
     * Construtor da classe FiltroContaMotivoRetificacao
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroContaMotivoRetificacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
