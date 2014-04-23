package gcom.relatorio.cadastro;

import gcom.batch.Relatorio;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *  [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
 * 
 * @author Daniel Alves
 * @date 28/09/2010
 * 
 * "Relatorio de Resumo"
 */

public class RelatorioResumoAtualizacaoCadastralViaInternet extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
		
	public RelatorioResumoAtualizacaoCadastralViaInternet(Usuario usuario) {		
		super(usuario, ConstantesRelatorios.RELATORIO_RESUMO_ATUALIZACAO_CADASTRAL_VIA_INTERNET);		
	}
	
	@Deprecated
	public RelatorioResumoAtualizacaoCadastralViaInternet() {
		super(null, "");
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();

		String filtroPeriodoInicial = (String) getParametro("filtroPeriodoInicial");
		String filtroPeriodoFinal = (String) getParametro("filtroPeriodoFinal");
		String filtroGerenciaRegional = (String) getParametro("filtroGerenciaRegional");
		String filtroUnidadeNegocio = (String) getParametro("filtroUnidadeNegocio");
		String filtroLocalidadeInicial = (String) getParametro("filtroLocalidadeInicial");
		String filtroLocalidadeFinal = (String) getParametro("filtroLocalidadeFinal");

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		//Parametros
		String gerencia = "";
		String unidade = "";
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		GerarRelatorioAtualizacaoCadastralViaInternetHelper filtro = new GerarRelatorioAtualizacaoCadastralViaInternetHelper();
		
		filtro.setPeriodoReferenciaInicial(filtroPeriodoInicial);
		filtro.setPeriodoReferenciaFinal(filtroPeriodoFinal);
		
		if(filtroGerenciaRegional != null && !filtroGerenciaRegional.equals("-1")){
			filtro.setIdGerenciaRegional(Integer.parseInt(filtroGerenciaRegional));
			
			FiltroGerenciaRegional pesquisaGerenciaRegional = new FiltroGerenciaRegional();
			
			pesquisaGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, filtroGerenciaRegional));
			
			Collection<GerenciaRegional> colecaoGerencias = fachada.pesquisar(pesquisaGerenciaRegional, GerenciaRegional.class.getName());
			
			for (GerenciaRegional gerenciaRegional : colecaoGerencias) {
				gerencia = gerenciaRegional.getNome();
			}
			
		}
		
		if(filtroUnidadeNegocio != null && !filtroUnidadeNegocio.equals("-1")){
			filtro.setIdUnidadeNegocio(Integer.parseInt(filtroUnidadeNegocio));
			
			FiltroUnidadeNegocio pesquisaUnidadeNegocio = new FiltroUnidadeNegocio();
			
			pesquisaUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, filtroUnidadeNegocio));
			
			Collection<UnidadeNegocio> colecaoUnidade = fachada.pesquisar(pesquisaUnidadeNegocio, UnidadeNegocio.class.getName());
			
			for (UnidadeNegocio unidadeNegocio : colecaoUnidade) {
				unidade = unidadeNegocio.getNome();
			}
		}
		
		if(filtroLocalidadeInicial != null && !filtroLocalidadeInicial.equals("")
				&& filtroLocalidadeFinal != null && !filtroLocalidadeFinal.equals("")){
			
			filtro.setIdLocalidadeInicial(Integer.parseInt(filtroLocalidadeInicial));
			filtro.setIdLocalidadeFinal(Integer.parseInt(filtroLocalidadeFinal));
		}
			
		Collection colecaResumoAtualizacaoCadastralViaInternet = null;
		
		try {
			colecaResumoAtualizacaoCadastralViaInternet = fachada.pesquisarDadosRelatorioResumoAtualizacaoCadastralViaInternet(filtro);
		} catch (ControladorException e1) {
			//throw new ActionServletException("atencao.naocadastrado", null,	"Unidade de Negócio");		
		}	
		
		Iterator iterator = colecaResumoAtualizacaoCadastralViaInternet.iterator();
		
		RelatorioResumoAtualizacaoCadastralViaInternetBean relatorioBean = null;
		
		while(iterator.hasNext()){
			relatorioBean = new RelatorioResumoAtualizacaoCadastralViaInternetBean();
			
			Object[] obj = (Object[]) iterator.next();
			
			relatorioBean.setQuantidadeNome(obj[0]!=null?(Integer)obj[0]:0);				
			relatorioBean.setQuantidadeCPF(obj[1]!=null?(Integer)obj[1]:0);				
			relatorioBean.setQuantidadeCNPJ(obj[2]!=null?(Integer)obj[2]:0);				
			relatorioBean.setQuantidadeEmail(obj[3]!=null?(Integer)obj[3]:0);	
			relatorioBean.setQuantidadeClientesAlterados(obj[4]!=null?(Integer)obj[4]:0);
			
			relatorioBeans.add(relatorioBean);
		}
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("filtroGerenciaRegional", gerencia);
		parametros.put("filtroUnidadeNegocio", unidade);
		parametros.put("filtroLocalidadeInicial", filtroLocalidadeInicial);
		parametros.put("filtroLocalidadeFinal", filtroLocalidadeFinal);
		parametros.put("filtroPeriodoInicial", filtroPeriodoInicial);
		parametros.put("filtroPeriodoFinal", filtroPeriodoFinal);
		parametros.put("tipoRelatorio", "R1076");
		

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) relatorioBeans);

		
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_RESUMO_ATUALIZACAO_CADASTRAL_VIA_INTERNET, parametros, ds,
					tipoFormatoRelatorio);
		

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_RESUMO_ATUALIZACAO_CADASTRAL_VIA_INTERNET,
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
		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioResumoAtualizacaoCadastralViaInternet", this);
	}
	
}
