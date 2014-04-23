package gcom.relatorio.arrecadacao;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de Pagamento para Entidades
 * Beneficentes Sintético
 * 
 * @author Daniel Alves
 * @created 25 de Janeiro de 2010.
 */
public class RelatorioPagamentoEntidadesBeneficentesSintetico extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioPagamentoEntidadesBeneficentesSintetico(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_PAGAMENTO_ENTIDADES_BENEFICENTES_SINTETICO);
	}
	
	@Deprecated
	public RelatorioPagamentoEntidadesBeneficentesSintetico() {
		super(null, "");
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		Fachada fachada = Fachada.getInstancia();
		
		String mesAnoInicial = (String) getParametro("mesAnoInicial");
		String mesAnoFinal = (String) getParametro("mesAnoFinal");		
		String idEntidadeBeneficente = (String) getParametro("idEntidadeBeneficente");
		String idGerenciaRegional = (String) getParametro("idGerenciaRegional");
		String idUnidadeNegocio = (String) getParametro("idUnidadeNegocio");
		String idLocalidade = (String) getParametro("idLocalidade");		
		int opcaoTotalizacao = (Integer) getParametro("opcaoTotalizacao");
		
		int retorno = 0;
				
		retorno = 
			(Integer) fachada
  	         .pesquisarPagamentoEntidadesBeneficentesCount(mesAnoInicial, mesAnoFinal, idEntidadeBeneficente, 
  	        		                                 idGerenciaRegional, idUnidadeNegocio, idLocalidade, 
  	        		                                 opcaoTotalizacao, "sintetico");
		 if(retorno == 0){
			 throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		 }
					 
		 return retorno;		
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioPagamentoEntidadesBeneficentesSintetico", this);
	}

	@Override
	public Object executar() throws TarefaException {
						
		Fachada fachada = Fachada.getInstancia();
		
		byte[] retorno = null;
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
				
		String mesAnoInicial = (String) getParametro("mesAnoInicial");
		String mesAnoFinal = (String) getParametro("mesAnoFinal");		
		String idEntidadeBeneficente = (String) getParametro("idEntidadeBeneficente");
		String idGerenciaRegional = (String) getParametro("idGerenciaRegional");
		String idUnidadeNegocio = (String) getParametro("idUnidadeNegocio");
		String idLocalidade = (String) getParametro("idLocalidade");		
		int opcaoTotalizacao = (Integer) getParametro("opcaoTotalizacao");		
				
		Collection<RelatorioPagamentoEntidadesBeneficentesSinteticoBean> beansRelatorio = 
			(Collection<RelatorioPagamentoEntidadesBeneficentesSinteticoBean>) fachada
  	         .pesquisarPagamentoEntidadesBeneficentesSintetico(mesAnoInicial, mesAnoFinal, idEntidadeBeneficente, 
  	        		                                 idGerenciaRegional, idUnidadeNegocio, idLocalidade, opcaoTotalizacao);				
		
		if(beansRelatorio != null){			
			
			int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
			
			//Parâmetros do relatório
			Map parametros = new HashMap();			
			
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			parametros.put("imagem", sistemaParametro.getImagemRelatorio());
			parametros.put("periodoInicial", mesAnoInicial);
			parametros.put("periodoFinal", mesAnoFinal);
			parametros.put("opcaoTotalizacao", opcaoTotalizacao);
			parametros.put("useCase", "R0978");
			
	
			RelatorioDataSource ds = new RelatorioDataSource((List) beansRelatorio);
	
			// exporta o relatório em pdf e retorna o array de bytes
			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_PAGAMENTO_ENTIDADES_BENEFICENTES_SINTETICO,
					parametros, ds, tipoFormatoRelatorio);
		}else {
			throw new ActionServletException(
            "atencao.pesquisa.nenhumresultado");
		}
		// ------------------------------------
		//  Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_PAGAMENTO_ENTIDADES_BENEFICENTES_SINTETICO, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------
		// retorna o relatório gerado
		
		return retorno;
		
	}

}
