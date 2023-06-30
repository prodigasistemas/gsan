package gcom.relatorio.micromedicao;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

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
public class RelatorioGerarDadosLeitura extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioGerarDadosLeitura(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_GERAR_DADOS_LEITURA);
	}
	
	@Deprecated
	public RelatorioGerarDadosLeitura() {
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

		GerarDadosLeituraHelper gerarDadosLeituraHelper = (GerarDadosLeituraHelper) getParametro("gerarDadosLeituraHelper");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		Collection colecaoRelatorioGerarDadosLeituraBeanFinal = new ArrayList();
		
		Collection colecaoRelatorioGerarDadosLeituraBeanInicial = fachada.pesquisarDadosParaLeituraRelatorio(gerarDadosLeituraHelper);
		
		Iterator iteratorColecaoRelatorioGerarDadosLeituraBean = colecaoRelatorioGerarDadosLeituraBeanInicial.iterator();
		
		Imovel imovelNaBase = null;
		
		RelatorioGerarDadosLeituraBean relatorioGerarDadosLeituraBean = null;
		
		while(iteratorColecaoRelatorioGerarDadosLeituraBean.hasNext()) {
			
			relatorioGerarDadosLeituraBean = (RelatorioGerarDadosLeituraBean) iteratorColecaoRelatorioGerarDadosLeituraBean.next();
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.limparListaParametros();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, Integer.parseInt(relatorioGerarDadosLeituraBean.getMatriculaImovel())));
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.FATURAMENTO_SITUACAO_TIPO);
			try {
				imovelNaBase = (Imovel)Util.retonarObjetoDeColecao((getControladorUtil().pesquisar(filtroImovel, Imovel.class.getName())));
			} catch (ControladorException e) {
				e.printStackTrace();
			}
			
			if (imovelNaBase.getFaturamentoSituacaoTipo() == null
					|| (imovelNaBase.getFaturamentoSituacaoTipo()
							.getIndicadorParalisacaoLeitura()
							.equals(ConstantesSistema.NAO) || imovelNaBase
							.getFaturamentoSituacaoTipo()
							.getIndicadorValidoEsgoto()
							.equals(ConstantesSistema.NAO))) {
				colecaoRelatorioGerarDadosLeituraBeanFinal.add(relatorioGerarDadosLeituraBean);
			}
		}
		
		if (colecaoRelatorioGerarDadosLeituraBeanFinal != null && !colecaoRelatorioGerarDadosLeituraBeanFinal.isEmpty()) {
			relatorioBeans.addAll(colecaoRelatorioGerarDadosLeituraBeanFinal);
		}

		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_GERAR_DADOS_LEITURA,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_GERAR_DADOS_LEITURA,
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

		retorno = Fachada
				.getInstancia()
				.pesquisarDadosParaLeituraRelatorioCount(
						(GerarDadosLeituraHelper) getParametro("gerarDadosLeituraHelper"));

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioGerarDadosLeitura", this);
	}
}
