package gcom.relatorio.cobranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o Relatório de Emissão de Declaracao de Transferencia de Débito 
 * 
 * @author Daniel Alves
 * @created 26 de Fevereiro de 2010.
 */
public class RelatorioEmitirDeclaracaoTransferenciaDebito extends TarefaRelatorio{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public RelatorioEmitirDeclaracaoTransferenciaDebito(Usuario usuario) {
			super(usuario,
					ConstantesRelatorios.RELATORIO_DECLARACAO_TRANSFERENCIA_DEBITO_CREDITO);
		}

		@Override
		public int calcularTotalRegistrosRelatorio() {									 
			 return 1; 
		}

		@Override
		public void agendarTarefaBatch() {
			AgendadorTarefas.agendarTarefa("RelatorioEmitirDeclaracaoTransferenciaDebito", this);
		}

		@Override
		public Object executar() throws TarefaException {
							
			Fachada fachada = Fachada.getInstancia();
			

			byte[] retorno = null;			
			
			String clienteUsuarioDestino = (String) getParametro("clienteUsuarioDestino");		
			String clienteUsuarioOrigem = (String) getParametro("clienteUsuarioOrigem");
			String valorNovaConta = (String) getParametro("valorNovaConta");			
			String indicadorTipoEmissao = (String) getParametro("indicadorTipoEmissao");	
			String municipio = (String)getParametro("municipio");
			
			Collection<RelatorioEmitirDeclaracaoTransferenciaDebitoBean> beansRelatorio =			
				   fachada.gerarRelatorioEmitirDeclaracaoTransferenciaDebitoCredito(clienteUsuarioDestino, clienteUsuarioOrigem, valorNovaConta, indicadorTipoEmissao, municipio);
			
			
			int tipoFormatoRelatorio = TarefaRelatorio.TIPO_PDF;
				
			//Parâmetros do relatório
			Map parametros = new HashMap();						
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();				
			parametros.put("imagem", sistemaParametro.getImagemRelatorio());
			parametros.put("empresa", sistemaParametro.getNomeEmpresa());
			
			RelatorioDataSource ds = new RelatorioDataSource((List) beansRelatorio);
		
			// exporta o relatório em pdf e retorna o array de bytes
			retorno = gerarRelatorio(
						ConstantesRelatorios.RELATORIO_DECLARACAO_TRANSFERENCIA_DEBITO_CREDITO,
						parametros, ds, tipoFormatoRelatorio);
						
			return retorno;
			
		}

}