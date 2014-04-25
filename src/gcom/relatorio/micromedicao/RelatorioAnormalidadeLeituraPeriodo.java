package gcom.relatorio.micromedicao;

import gcom.batch.Relatorio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesInterfaceGSAN;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioAnormalidadeLeituraPeriodo extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioAnormalidadeLeituraPeriodo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ANORMALIDADE_LEITURA_PERIODO);
	}

	@Deprecated
	public RelatorioAnormalidadeLeituraPeriodo() {
		super(null, "");
	}

	public Object executar() throws TarefaException {
		
		List<RelatorioAnormalidadeLeituraPeriodoBean> relatorioBeans = executarConsultaRelatoriosBean();
		
		if(Util.isVazioOrNulo(relatorioBeans)){
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_PESQUISA_NENHUM_RESULTADO);
		}

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		byte[] retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ANORMALIDADE_LEITURA_PERIODO,
				criarParametros() , ds, tipoFormatoRelatorio);

		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_ANORMALIDADE_LEITURA_PERIODO,this.getIdFuncionalidadeIniciada());
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException(ConstantesInterfaceGSAN.ERRO_GSAN_ERRO_GRAVAR_RELATORIO_SISTEMA, e);
		}
		
		return retorno;
	}

	/**
	 * O método cria os parametros necessários a geração do relatorio.
	 *
	 *@since 06/10/2009
	 *@author Marlon Patrick
	 */
	private Map<String, Object> criarParametros() {
		FiltrarRelatorioAnormalidadeLeituraPeriodoHelper filtro = (FiltrarRelatorioAnormalidadeLeituraPeriodoHelper) getParametro("filtroRelatorio");

		FiltroLeituraAnormalidade filtroConsumoAnormalidade = new FiltroLeituraAnormalidade();				
		filtroConsumoAnormalidade.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.ID,
				filtro.getAnormalidadeLeitura()));
		filtroConsumoAnormalidade.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<LeituraAnormalidade> colecaoAnormalidadeleitura = Fachada.getInstancia().pesquisar(filtroConsumoAnormalidade, LeituraAnormalidade.class.getName());

		LeituraAnormalidade anormalidade = colecaoAnormalidadeleitura.iterator().next();

		Map<String,Object> parametros = new HashMap<String,Object>();

		parametros.put("imagem", Fachada.getInstancia().pesquisarParametrosDoSistema().getImagemRelatorio());
		parametros.put("filtroPeriodoLeitura", Util.formatarAnoMesParaMesAno(filtro.getAnoMesReferenciaInicial()) + " - " + Util.formatarAnoMesParaMesAno(filtro.getAnoMesReferenciaFinal()));
		parametros.put("filtroAnormalidade", anormalidade.getId() + " - " + anormalidade.getDescricao());

		return parametros;
	}

	/**
	 * Esse método tem a lógica para realizar a consulta referente ao relatorio
	 * e a partir do resultado obtido criar os beans.
	 *
	 *@since 06/10/2009
	 *@author Marlon Patrick
	 */
	private List<RelatorioAnormalidadeLeituraPeriodoBean> executarConsultaRelatoriosBean() {

		FiltrarRelatorioAnormalidadeLeituraPeriodoHelper filtro = (FiltrarRelatorioAnormalidadeLeituraPeriodoHelper) getParametro("filtroRelatorio");

		return (List<RelatorioAnormalidadeLeituraPeriodoBean>)Fachada.getInstancia().pesquisarRelatorioAnormalidadeLeituraPeriodo(filtro);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		Collection<Object[]> colecaoDados = Fachada.getInstancia().pesquisarTotalRegistrosRelatorioAnormalidadeLeituraPeriodo(
				(FiltrarRelatorioAnormalidadeLeituraPeriodoHelper) getParametro("filtroRelatorio"));	
		
		if(Util.isVazioOrNulo(colecaoDados)){
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_PESQUISA_NENHUM_RESULTADO);
		}
		
		return colecaoDados.size();
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAnormalidadeLeituraPeriodo", this);

	}

}
