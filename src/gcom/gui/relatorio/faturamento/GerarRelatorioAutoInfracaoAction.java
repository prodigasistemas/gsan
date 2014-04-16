package gcom.gui.relatorio.faturamento;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.faturamento.autoinfracao.GerarRelatorioAutoInfracaoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.autoinfracao.RelatorioAutoInfracao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de bairro manter
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioAutoInfracaoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;

		GerarRelatorioAutoInfracaoActionForm gerarRelatorioAutoInfracaoActionForm = (GerarRelatorioAutoInfracaoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = this.getSistemaParametro();
		
		boolean peloMenosUmParametroInformado = false;

		// Inicio da parte que vai mandar os parametros para o relatório

		UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
		Funcionario funcionario = new Funcionario();
		
		String idUnidadeNegocio = gerarRelatorioAutoInfracaoActionForm.getIdUnidadeNegocio();
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		
		if (idUnidadeNegocio != null && !idUnidadeNegocio.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, idUnidadeNegocio));
			
			Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			
			if (colecaoUnidadeNegocio != null && !colecaoUnidadeNegocio.isEmpty()) {
				unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
			}
			
		}
		
		String idFuncionario = gerarRelatorioAutoInfracaoActionForm.getIdFuncionario();
		
		if (idFuncionario != null && !idFuncionario.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, idFuncionario));
			
			Collection colecaoFuncionario = fachada.pesquisar(filtroFuncionario, Funcionario.class.getName());
			
			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {
				funcionario = (Funcionario) Util.retonarObjetoDeColecao(colecaoFuncionario);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Funcionário");
			}
			
		}

		String dataPagamentoInicialForm = gerarRelatorioAutoInfracaoActionForm.getDataPagamentoInicio();
		String dataPagamentoFinalForm = gerarRelatorioAutoInfracaoActionForm.getDataPagamentoFim();
		
		String dataPagamentoInicial = null;
		String dataPagamentoFinal = null;

		if (dataPagamentoInicialForm != null && !dataPagamentoInicialForm.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			
			dataPagamentoInicial = Util.formatarMesAnoParaAnoMesSemBarra(dataPagamentoInicialForm);
			dataPagamentoFinal = Util.formatarMesAnoParaAnoMesSemBarra(dataPagamentoFinalForm);
			
			if (dataPagamentoInicial
					.compareTo(dataPagamentoFinal) > 0) {
				throw new ActionServletException(
						"atencao.data.intervalo.invalido");
			}
			
			//Consulta a data referente ano mes arrecadação direto da tabela e não quando sistema parametro é carregado.
			FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
			filtroSistemaParametro.adicionarParametro( new ParametroSimples( 
					FiltroSistemaParametro.Parm_Id, sistemaParametro.getParmId() ) );
			Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());
			SistemaParametro sistParam = (SistemaParametro) Util.retonarObjetoDeColecao(colecaoSistemaParametro);
			
			if(dataPagamentoFinal.compareTo(sistParam.getAnoMesArrecadacao().toString())>=0){
				throw new ActionServletException(
				"atencao.data.menor.sistema.parametro",null,Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesArrecadacao().toString()));
			}
		}
		
		
		
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
			"atencao.filtro.nenhum_parametro_informado");
		}

		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioAutoInfracao relatorioAutoInfracao = new RelatorioAutoInfracao((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioAutoInfracao.addParametro("unidadeNegocio",
				unidadeNegocio);
		relatorioAutoInfracao.addParametro("funcionario",
				funcionario);
		relatorioAutoInfracao.addParametro("dataPagamentoInicial",
				new Integer(dataPagamentoInicial));
		relatorioAutoInfracao.addParametro("dataPagamentoFinal",
				new  Integer(dataPagamentoFinal));
		
		int count = fachada.countRelatorioAutoInfracao(
				unidadeNegocio.getId(),
				funcionario.getId(),
				new Integer(dataPagamentoInicial),
				new Integer(dataPagamentoFinal));
		if(count==0){
			throw new ActionServletException(
			"atencao.relatorio.vazio");
		}
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioAutoInfracao.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorioAutoInfracao, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
