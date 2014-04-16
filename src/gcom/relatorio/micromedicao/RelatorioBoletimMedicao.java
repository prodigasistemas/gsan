package gcom.relatorio.micromedicao;

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
 * classe responsável por criar o relatório de Boletim de Medição
 * 
 * [UC1054] - Gerar Relatório Boletim de Medição
 * 
 * @author Hugo Leonardo
 *
 * @date 04/08/2010
 */
public class RelatorioBoletimMedicao extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioBoletimMedicao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_BOLETIM_MEDICAO);
	}

	@Deprecated
	public RelatorioBoletimMedicao() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltrarRelatorioBoletimMedicaoHelper relatorioHelper = 
			(FiltrarRelatorioBoletimMedicaoHelper) getParametro("filtrarRelatorioBoletimMedicaoHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioBoletimMedicaoBean relatorioBoletimMedicaoBean = null;

		String mesAno = (String)getParametro("mesAno");
		
		Collection colecao = fachada.pesquisarRelatorioBoletimMedicao(relatorioHelper);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioBoletimMedicaoHelper helper = 
					(RelatorioBoletimMedicaoHelper) colecaoIterator.next();
				
				relatorioBoletimMedicaoBean = 
					new RelatorioBoletimMedicaoBean(
							helper.getGerenciaRegional(),
							helper.getEmpresa(),
							helper.getNumeroContrato(),
							helper.getLocalidade(),
							helper.getItemCodigo(),
							helper.getItemDescricao(),
							helper.getQuantidade(),
							helper.getValor() );

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBoletimMedicaoBean);				
			}
		}else{
			
			relatorioBoletimMedicaoBean = 
				new RelatorioBoletimMedicaoBean();
			
			relatorioBeans.add(relatorioBoletimMedicaoBean);
			
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());	
		
		parametros.put("mesAno", mesAno);
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		if(colecao != null && colecao.size() > 0){
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_BOLETIM_MEDICAO,
					parametros, ds, tipoFormatoRelatorio);
		}else{
			
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
					parametros, ds, tipoFormatoRelatorio);
		}
			
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_BOLETIM_MEDICAO,
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
		
		int retorno = 2;
   
		if (retorno == 0) {
			// Caso a pesquisa não retorne nenhum resultado comunica ao
			// usuário;
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioBoletimMedicao", this);
	}

}
