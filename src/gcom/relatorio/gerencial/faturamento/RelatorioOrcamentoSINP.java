package gcom.relatorio.gerencial.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gerencial.bean.FiltrarRelatorioOrcamentoSINPHelper;
import gcom.gerencial.bean.OrcamentoSINPHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioOrcamentoSINPBean;
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
 * classe responsável por criar o relatório de orçamento e SINP
 * 
 * @author Rafael Pinto
 * @created 22/11/2007
 */
public class RelatorioOrcamentoSINP extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioOrcamentoSINP(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ORCAMENTO_SINP);
	}

	@Deprecated
	public RelatorioOrcamentoSINP() {
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

		FiltrarRelatorioOrcamentoSINPHelper filtro = 
			(FiltrarRelatorioOrcamentoSINPHelper) getParametro("filtrarRelatorioOrcamentoSINPHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioOrcamentoSINPBean relatorioOrcamentoSINPBean = null;

		Collection<OrcamentoSINPHelper> colecao = 
			fachada.pesquisarRelatorioOrcamentoSINP(filtro);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {

				OrcamentoSINPHelper helper = 
					(OrcamentoSINPHelper) colecaoIterator.next();
				
				//Atendendo a [CRC 4214]
				//Filtrar as páginas que não tenham Débito e ou Economias.
				if(!(helper.getAguaTotalLigacoesCadastradas() == 0 &&
						helper.getAguaTotalEconomiasCadastradas() == 0 &&
						helper.getEsgotoTotalLigacoesCadastradas() == 0 &&
						helper.getEsgotoTotalEconomiasCadastradas() == 0 &&
						helper.getSaldoContasReceber().compareTo(new BigDecimal("0"))== 0)){
					relatorioOrcamentoSINPBean = 
						new RelatorioOrcamentoSINPBean(helper);

					// adiciona o bean a coleção
					relatorioBeans.add(relatorioOrcamentoSINPBean);		
				}	
				
				
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("anoMes", Util.formatarAnoMesParaMesAno(filtro.getAnoMesReferencia()));
		
		String opcaoTotalizacaoParametro = "";
		switch (filtro.getOpcaoTotalizacao()){
		
		case 1:
			opcaoTotalizacaoParametro = "ESTADO";
			break;
		case 2:
			opcaoTotalizacaoParametro = "ESTADO POR GERÊNCIAL REGIONAL";
			break;
		case 3:
			opcaoTotalizacaoParametro = "ESTADO POR UNIDADE DE NEGÓCIO";
			break;
		case 5:
			opcaoTotalizacaoParametro = "ESTADO POR CENTRO CUSTO/LOCALIDADE";
			break;
		case 6:
			opcaoTotalizacaoParametro = "GERÊNCIA REGIONAL";
			break;
		case 10:
			opcaoTotalizacaoParametro = "UNIDADE DE NEGÓCIO";
			break;
		case 16:
			opcaoTotalizacaoParametro = "LOCALIDADE";
			break;
		case 19:
			opcaoTotalizacaoParametro = "MUNICÍPIO";
			break;
		case 26:
			opcaoTotalizacaoParametro = "ESTADO POR MUNICÍPIO";
			break;
		}
		
		parametros.put("opcaoTotalizacaoParametro", opcaoTotalizacaoParametro);
		parametros.put("tipoFormatoRelatorio", "R0722");

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ORCAMENTO_SINP,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.ORCAMENTO_SINP,
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
		AgendadorTarefas.agendarTarefa("RelatorioOrcamentoSINP", this);

	}

}
