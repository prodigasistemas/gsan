package gcom.relatorio.faturamento;

import gcom.cadastro.cliente.Cliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Fernanda Paiva
 * @created 11 de Julho de 2005
 */
public class RelatorioProtocoloEntregaFatura extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	public RelatorioProtocoloEntregaFatura(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_PROTOCOLO_ENTREGA_FATURA);
	}

	@Deprecated
	public RelatorioProtocoloEntregaFatura() {
		super(null, "");
	}
	
	/**
	 * < <Descrição do método>>
	 * 
	 * @param localidades
	 *            Description of the Parameter
	 * @param localidadeParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	@SuppressWarnings("unchecked")
	public Object executar() throws TarefaException {
		
		Fachada fachada = Fachada.getInstancia();
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		Integer anoMes = (Integer) getParametro("anoMes");
		Cliente cliente = (Cliente) getParametro("cliente");
		Collection<Integer> idsClientes = (Collection<Integer>) getParametro("idsClientes");
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		Collection colecaoRelatorioProtocoloEntregaFatura = fachada.pesquisarDadosRelatorioProtocoloEntregaFatura(anoMes, cliente, idsClientes);
		
		if(colecaoRelatorioProtocoloEntregaFatura == null || colecaoRelatorioProtocoloEntregaFatura.isEmpty()){
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,"");
		}
		
		
		relatorioBeans.addAll(colecaoRelatorioProtocoloEntregaFatura);
        
		// Parâmetros do relatório
		Map parametros = new HashMap();

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_PROTOCOLO_ENTREGA_FATURA, parametros,
				ds, tipoFormatoRelatorio);
		
		// retorna o relatório gerado
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		Integer retorno = 0;
		
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioProtocoloEntregaFatura", this);
	}

}
