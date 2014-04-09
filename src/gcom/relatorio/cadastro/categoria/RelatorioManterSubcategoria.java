package gcom.relatorio.cadastro.categoria;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioManterSubcategoria extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterSubcategoria(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_SUBCATEGORIA_MANTER);
	}
	
	@Deprecated
	public RelatorioManterSubcategoria() {
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

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroSubCategoria filtroSubCategoria = (FiltroSubCategoria) getParametro("filtroSubcategoria");
		Subcategoria subcategoriaParametros = (Subcategoria) getParametro("subcategoriaParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterSubcategoriaBean relatorioBean = null;

		filtroSubCategoria
				.adicionarCaminhoParaCarregamentoEntidade("categoria");
		filtroSubCategoria.limparCamposOrderBy();
		filtroSubCategoria.setCampoOrderBy(FiltroSubCategoria.CATEGORIA_ID,
				FiltroSubCategoria.CODIGO);

		filtroSubCategoria.setConsultaSemLimites(true);

		Collection colecaoSubcategorias = fachada.pesquisar(filtroSubCategoria,
				Subcategoria.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoSubcategorias != null && !colecaoSubcategorias.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoSubcategoriasIterator = colecaoSubcategorias
					.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoSubcategoriasIterator.hasNext()) {

				Subcategoria subcategoria = (Subcategoria) colecaoSubcategoriasIterator
						.next();

				relatorioBean = new RelatorioManterSubcategoriaBean(
						// Código
						"" + subcategoria.getCodigo(),

						// Descrição
						subcategoria.getDescricao(),

						// Categoria
						subcategoria.getCategoria().getDescricao(),

						// Indicador de Uso
						subcategoria.getIndicadorUso().toString());

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if (subcategoriaParametros.getCategoria() != null) {
			parametros.put("categoria", subcategoriaParametros.getCategoria()
					.getDescricao());
		} else {
			parametros.put("categoria", "");
		}

		parametros.put("descricao", subcategoriaParametros.getDescricao());

		String indicadorUso = "";

		if (subcategoriaParametros.getIndicadorUso() != null
				&& !subcategoriaParametros.getIndicadorUso().equals("")) {
			if (subcategoriaParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (subcategoriaParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_SUBCATEGORIA_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_SUBCATEGORIA,
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
		int retorno = 0;

		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
				(FiltroSubCategoria) getParametro("filtroSubcategoria"),
				Subcategoria.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterSubcategoria", this);
	}
}
