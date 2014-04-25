package gcom.relatorio.cadastro.micromedicao;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.relatorio.cadastro.micromedicao.FiltrarRelatorioColetaMedidorEnergiaHelper;
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
 * [UC0999] Gerar Relatório de Coleta de Medidor de Energia.
 * 
 * @author Hugo Leonardo
 *
 * @date 08/03/2010
 */
public class RelatorioColetaMedidorEnergia extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioColetaMedidorEnergia(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_COLETA_MEDIDOR_ENERGIA);
	}

	@Deprecated
	public RelatorioColetaMedidorEnergia() {
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

		FiltrarRelatorioColetaMedidorEnergiaHelper filtro = 
			(FiltrarRelatorioColetaMedidorEnergiaHelper) getParametro("filtrarRelatorioColetaMedidorEnergiaHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioColetaMedidorEnergiaBean relatorioColetaMedidorEnergiaBean = null;

		Collection<RelatorioColetaMedidorEnergiaHelper> colecao =   
			fachada.pesquisarRelatorioColetaMedidorEnergia(filtro);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioColetaMedidorEnergiaHelper helper = 
					(RelatorioColetaMedidorEnergiaHelper) colecaoIterator.next();
				
				relatorioColetaMedidorEnergiaBean = 
					new RelatorioColetaMedidorEnergiaBean(helper);
				
				
				relatorioColetaMedidorEnergiaBean.setInscricaoImovel(
						fachada.pesquisarInscricaoImovel(Integer.parseInt(helper.getMatriculaImovel())));
				
				try {
					relatorioColetaMedidorEnergiaBean.setEndereco(
							fachada.pesquisarEnderecoFormatado(new Integer(helper.getMatriculaImovel())));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ControladorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				// adiciona o bean a coleção
				relatorioBeans.add(relatorioColetaMedidorEnergiaBean);				
				
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoFormatoRelatorio", "R0999");

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_COLETA_MEDIDOR_ENERGIA,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_COLETA_MEDIDOR_ENERGIA,
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
			Fachada.getInstancia().countRelatorioColetaMedidorEnergia(
				(FiltrarRelatorioColetaMedidorEnergiaHelper) 
					getParametro("filtrarRelatorioColetaMedidorEnergiaHelper"));
		
		if (retorno == 0) {
			// Caso a pesquisa não retorne nenhum resultado comunica ao
			// usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");

		}

		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioColetaMedidorEnergia", this);

	}

}
