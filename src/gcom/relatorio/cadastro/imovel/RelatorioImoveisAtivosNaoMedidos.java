package gcom.relatorio.cadastro.imovel;

import gcom.batch.Relatorio;
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
 * [UC00728] Gerar Relatório de Imóveis Ativos e Não Medidos
 * 
 * @author Rafael Pinto
 * @date 02/01/2008
 */
public class RelatorioImoveisAtivosNaoMedidos extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioImoveisAtivosNaoMedidos(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_ATIVOS_NAO_MEDIDOS);
	}

	@Deprecated
	public RelatorioImoveisAtivosNaoMedidos() {
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

		FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro = 
			(FiltrarRelatorioImoveisAtivosNaoMedidosHelper) getParametro("filtrarRelatorioImoveisAtivosNaoMedidosHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioImoveisAtivosNaoMedidosBean relatorioImoveisAtivosNaoMedidosBean = null;

		Collection<RelatorioImoveisAtivosNaoMedidosHelper> colecao =   
			fachada.pesquisarRelatorioImoveisAtivosNaoMedidos(filtro);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioImoveisAtivosNaoMedidosHelper helper = 
					(RelatorioImoveisAtivosNaoMedidosHelper) colecaoIterator.next();
				
				relatorioImoveisAtivosNaoMedidosBean = 
					new RelatorioImoveisAtivosNaoMedidosBean(helper);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioImoveisAtivosNaoMedidosBean);				
				
				
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoFormatoRelatorio", "R0728");

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_ATIVOS_NAO_MEDIDOS,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.IMOVEIS_ATIVOS_NAO_MEDIDO,
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

		retorno = 
			Fachada.getInstancia().pesquisarTotalRegistroRelatorioImoveisAtivosNaoMedidos(
				(FiltrarRelatorioImoveisAtivosNaoMedidosHelper) 
					getParametro("filtrarRelatorioImoveisAtivosNaoMedidosHelper"));

		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImoveisAtivosNaoMedidos", this);

	}

}
