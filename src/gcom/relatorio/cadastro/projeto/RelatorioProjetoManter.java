package gcom.relatorio.cadastro.projeto;

import gcom.cadastro.projeto.FiltroProjeto;
import gcom.cadastro.projeto.Projeto;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioProjetoManter extends TarefaRelatorio {

	public RelatorioProjetoManter(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_PROJETO_MANTER);
	}

	@Deprecated
	public RelatorioProjetoManter() {
		super(null, "");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public Object executar() throws TarefaException {
		FiltroProjeto filtroProjeto = (FiltroProjeto) getParametro("filtroProjeto");

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterProjetoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoProjetos = fachada.pesquisar(filtroProjeto,
				Projeto.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoProjetos != null && !colecaoProjetos.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator itera = colecaoProjetos.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (itera.hasNext()) {

				Projeto projeto = (Projeto) itera.next();

				relatorioBean = new RelatorioManterProjetoBean(
						
						//Codigo
						projeto.getId()!=null ? 
							projeto.getId().toString():"",
						
						// Nome
						projeto.getNome()!=null?
						projeto.getNome():"",

						// Nome Abreviado
						projeto.getNomeAbreviado()!=null?
						projeto.getNomeAbreviado():"",

						// Nome Órgão Financiador
						projeto.getOrgaoFinanciador()!=null?
						projeto.getOrgaoFinanciador().getNome():"",

						// Data Inicio
						projeto.getDataInicio()!=null?
						projeto.getDataInicio().toString():"",

						// Data Fim
						projeto.getDataFim()!=null?
						projeto.getDataFim().toString():"",

						// Valor Financiamento
						projeto.getValorFinanciamento()!=null?
						Util.formatarMoedaReal(projeto.getValorFinanciamento()):""

				);
				
				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("id", getParametro("id"));
		parametros.put("nome", getParametro("nome"));
		parametros.put("nomeAbreviado", getParametro("nomeAbreviado"));
		parametros.put("idOrgao", getParametro("idOrgao"));
		parametros.put("nomeOrgao", getParametro("nomeOrgao"));

		String sit = (String) getParametro("situacao");
		
		Integer situacao = new Integer(sit);

		switch (situacao) {
		// Todos
		case 1:
			parametros.put("situacao", "Todos");
			break;
		// Em andamento
		case 2:

			parametros.put("situacao", "Em andamento");

			break;
		// Encerrados
		case 3:

			parametros.put("situacao", "Encerrados");

			break;
		default:

			break;
		}

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_PROJETO_MANTER, parametros, ds,
				tipoFormatoRelatorio);

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {

		AgendadorTarefas.agendarTarefa("RelatorioProjetoManter", this);

	}

}
