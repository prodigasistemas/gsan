package gcom.relatorio.faturamento;

import gcom.relatorio.ConstantesRelatorios;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;


/**
 * 
 * Este caso de uso permite a inserção de dados na tabela movimento 
 * conta pré-faturada.
 *
 * [UC0923] Incluir Movimento Conta Pré-Faturada
 *
 *
 *  Caso seja chamado por uma tela, o sistema gera uma tela de acordo com o 
 *  movimento, Caso contrário, o sistema gera um relatório e envia, por 
 *  e-mail para o operador, registrado com os seguintes campos:
 *  
 *  No cabeçalho imprimir o grupo de faturamento informado (FTGR_ID), o 
 *  código e descrição da empresa (EMPR_ID e EMPR_NMEMPRESA da tabela 
 *  EMPRESA com EMPR_ID da tabela ROTA com ROTA_ID da tabela QUADRA com 
 *  QDRA_ID da tabela IMOVEL com IMOV_ID=matrícula do imóvel do primeiro 
 *  registro do arquivo que exista na tabela IMOVEL), o código da localidade 
 *  e o título fixo “MOVIMENTO CELULAR - IMPRESSÃO SIMULTÂNEA” quando 
 *  processado o arquivo de movimento;
 *  
 *  Imprimir o erro correspondente encontrado no processamento do imóvel;
 *  
 *  Caso seja chamado por uma tela, imprimir um texto “Arquivo processado 
 *  com problema e enviado para operação para processar o movimento. 
 *  Localidade <<Código da Localidade>>”;    
 *
 * [SB0001] - Gera Tela Resumo das leituras e anormalidades da impressão 
 * simultânea com Problemas
 *
 * @author bruno
 * @date 30/06/2009
 *
 * @param colErrors 
 */ 
public class RelatorioResumoLeiturasAnormalidadesImpressaoSimultanea extends TarefaRelatorio {
    
	private static final long serialVersionUID = 1L;
	
	private byte[] relatorio = null; 
    
	public RelatorioResumoLeiturasAnormalidadesImpressaoSimultanea(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RESUMO_LEITURAS_ANORMALIDADE_IMPRESSAO_SIMULTANEA );
	}
	
	@Deprecated
	public RelatorioResumoLeiturasAnormalidadesImpressaoSimultanea() {
		super(null, "");
	}
	public Object executar() throws TarefaException {
        return relatorio;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

        return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("relatorioResumoLeiturasAnormalidadesRegistradas", this);
	}

	public byte[] getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(byte[] relatorio) {
		this.relatorio = relatorio;
	}

}
