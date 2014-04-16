package gcom.relatorio.financeiro;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.financeiro.ResumoReceita;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de imoveis por situação da ligação de agua
 * 
 * @author Flávio Leonardo
 * @created 16/04/2010
 */
public class RelatorioResumoReceitaAnalitico extends TarefaRelatorio {
	
	public RelatorioResumoReceitaAnalitico(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;
	
	public RelatorioResumoReceitaAnalitico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RESUMO_RECEITA_SINTETICO);
	}
	
	@Deprecated
	public RelatorioResumoReceitaAnalitico() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		ResumoReceitaHelper resumoReceitaHelper = 
			(ResumoReceitaHelper) getParametro("resumoReceitaHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioResumoReceitaAnaliticoBean relatorioResumoReceitaAnaliticoBean = null;

		Collection colecao =  
			fachada.pesquisarResumoReceitaRelatorioAnalitico(resumoReceitaHelper);
		
		
		
		BigDecimal valorTotal = BigDecimal.ZERO;

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();
			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {

				ResumoReceita resumoReceita = 
					(ResumoReceita) colecaoIterator.next();
				
				relatorioResumoReceitaAnaliticoBean = 
					new RelatorioResumoReceitaAnaliticoBean(resumoReceita);

				valorTotal = valorTotal.add(resumoReceita.getValorReceita());
				
				// adiciona o bean a coleção
				relatorioBeans.add(relatorioResumoReceitaAnaliticoBean);				
				
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("valorTotal", Util.formatarMoedaReal(valorTotal));
		parametros.put("mesAno", (String)getParametro("mesAno"));
		
		if(!((String)getParametro("gerenciaRegional")).equals("-1")){
			parametros.put("gerenciaRegional", (String)getParametro("gerenciaRegional"));			
		}
		if(!((String)getParametro("localidadeInicial")).equals("")){
			parametros.put("localidadeInicial", (String)getParametro("localidadeInicial"));			
		}
		if(!((String)getParametro("localidadeFinal")).equals("")){
			parametros.put("localidadeFinal", (String)getParametro("localidadeFinal"));			
		}
		if(!((String)getParametro("categoria")).equals("-1")){
			parametros.put("categoria", (String)getParametro("categoria"));			
		}

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_RESUMO_RECEITA_ANALITICO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_RESUMO_RECEITA_ANALITICO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		int retorno = 1;

		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioResumoReceitaAnalitico", this);

	}

}
