package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/**
 * [UC1171] Inserir Motivos de Não Aceitação de Encerramento de O.S
 * 
 * Classe responsável por guardar os valores do filtro para pesquisar
 * os motivos de não aceitação do encerramento de OS.
 * 
 * @author Diogo Peixoto
 * @since 20/05/2011
 */

public class FiltroMotivoNaoAceitacaoEncerramentoOS extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	public FiltroMotivoNaoAceitacaoEncerramentoOS(){
		
	}
	
	public FiltroMotivoNaoAceitacaoEncerramentoOS(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id";
	public final static String DESCRICAO = "descricaoMotivoNaoAceitacaoEncerramentoOS";
	public final static String MULTIPLICADOR_VALOR_SERVICO_DESCONTAR_CORTE_SUPRESSAO = "multiplicadorValorServicoDescontarCorteSupressao";
	public final static String MULTIPLICADOR_VALOR_SERVICO_DESCONTAR_NAO_EXECUTADOS = "multiplicadorValorServicoDescontarNaoExecutados";
	public final static String PERCENTUAL_MULTA_APLICAR = "percentualMultaAplicar";
	public final static String INDICADOR_USO = "indicadorUso";
	public final static String INDICADOR_MOTIVO_FISCALIZACAO = "indicadorMotivoFiscalizacao";
}