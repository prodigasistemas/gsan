package gcom.gerencial.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de resumo de faturamento
 * @author Pedro Alexandre
 * @created 09 de Janeiro de 2006
 */
public class FiltroResumoPendencia extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroResumoFaturamento object
	 */
	public FiltroResumoPendencia() {
	}

	/**
	 * Constructor for the FiltroResumoFaturamento object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroResumoPendencia(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id"; 

	public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";

	public final static String ANO_MES_REFERENCIA = "anoMesReferencia";
    
	public final static String CODIGO_SETOR_COMERCIAL = "codigoSetorComercial";
    
	public final static String NUMERO_QUADRA = "numeroQuadra";
    
	public final static String INDICADOR_HIDROMETRO = "indicadorHidrometro";

	public final static String ANO_MES_REFERENCIA_DOCUEMTNO = "anoMesReferenciaDocumento";
   
	public final static String QUANTIDADE_LIGACOES = "quantidadeLigacoes";
    
	public final static String QUANTIDADE_DOCUMENTOS = "quantidadeDocumentos";

	public final static String VALOR_DEBITO = "valorDebito";

	public final static String GERENCIA_REGIONAL = "gerenciaRegional.id";

	public final static String LOCALIDADE = "localidade.id";

	public final static String SETOR_COMERCIAL = "setorComercial.id";
    
	public final static String ROTA = "rota.id";
    
	public final static String QUADRA = "quadra.id";

	public final static String IMOVEL_PERFIL = "imovelPerfil.id";

	public final static String LIGACAO_AGUA_SITUACAO = "ligacaoAguaSituacao.id";

	public final static String LIGACAO_ESGOTO_SITUACAO = "ligacaoEsgotoSituacao.id";

	public final static String CATEGORIA = "categoria.id";
    
	public final static String ESFERA_PODER = "esferaPoder.id";
    
	public final static String DOCUMENTO_TIPO = "documentoTipo.id";
}
