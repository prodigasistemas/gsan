package gcom.gui.relatorio.cobranca;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioImoveisComAcordo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0891]-Gerar Relatório de Imóveis com Acordo
 *
 * @author Rômulo Aurélio
 * @date 23/03/2009
 */
public class GerarRelatorioImoveisComAcordoAction extends
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

		GerarRelatorioImoveisComAcordoActionForm form = (GerarRelatorioImoveisComAcordoActionForm) actionForm;

		// Inicio da parte que vai mandar os parametros para o relatório

		String idUnidadeNegocio = form.getIdUnidadeNegocio();
		String idLocalidadeInicial = form.getIdLocalidadeInicial();
		String idLocalidadeFinal = form.getIdLocalidadeFinal();
		String idGerenciaRegional = form.getIdGerenciaRegional();
		String dataInicialAcordo = form.getPeriodoInicialAcordo();
		String dataFinalAcordo = form.getPeriodoFinalAcordo();
		String rotaInicial = form.getRotaInicial();
		String rotaFinal = form.getRotaFinal();
		String sequencialRotaInicial = form.getSequencialRotaInicial();
		String sequencialRotaFinal = form.getSequencialRotaFinal();
		String idSetorComercialInicial = form.getSetorComercialInicial();
		String idSetorComercialFinal = form.getSetorComercialFinal();
		
		

		RelatorioImoveisComAcordo relatorioImoveisComAcordo = new RelatorioImoveisComAcordo(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));

		if (idUnidadeNegocio != null
				&& !idUnidadeNegocio.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.ID, idUnidadeNegocio));

			Collection colecaoUnidadeNegocio = this.getFachada().pesquisar(filtroUnidadeNegocio,
					UnidadeNegocio.class.getName());

			UnidadeNegocio unidadeNegocio = null;
			
			if (colecaoUnidadeNegocio != null && !colecaoUnidadeNegocio.isEmpty()) {
				
				unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
				
				relatorioImoveisComAcordo.addParametro("unidadeNegocio", unidadeNegocio);
				
			}

		}
		
		if (idGerenciaRegional != null
				&& !idGerenciaRegional.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.ID, idGerenciaRegional));

			Collection colecaoGerenciaRegional = this.getFachada().pesquisar(filtroGerenciaRegional,
					GerenciaRegional.class.getName());

			GerenciaRegional gerenciaRegional = null;
			
			if (colecaoGerenciaRegional != null && !colecaoGerenciaRegional.isEmpty()) {
				
				gerenciaRegional = (GerenciaRegional) Util.retonarObjetoDeColecao(colecaoGerenciaRegional);
				
				relatorioImoveisComAcordo.addParametro(
						"gerenciaRegional", gerenciaRegional);
			}

		}

		Date dataInicial = null;
		Date dataFinal = null;
		
		if (dataInicialAcordo != null && !dataInicialAcordo.equalsIgnoreCase("")) {
			
			dataInicial = Util.converteStringParaDate(dataInicialAcordo);
			
			dataFinal = Util.converteStringParaDate(dataFinalAcordo);
			
			if(dataInicial.after(dataFinal)){
				throw new ActionServletException("atencao.data_final_situacao_cobranca_invalida");
			}

			relatorioImoveisComAcordo.addParametro(
					"dataInicial", dataInicial);
			relatorioImoveisComAcordo.addParametro(
					"dataFinal", dataFinal);
		}
		
		if (idLocalidadeInicial != null
				&& !idLocalidadeInicial.trim().equals("")) {
			
			FiltroLocalidade filtroLocalidadeInicial = new FiltroLocalidade();
			
			filtroLocalidadeInicial.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeInicial));

			Collection colecaoLocalidadeInicial = this.getFachada().pesquisar(filtroLocalidadeInicial,
					Localidade.class.getName());

			Localidade localidadeInicial = null;
			
			if (colecaoLocalidadeInicial != null && !colecaoLocalidadeInicial.isEmpty()) {
				
				localidadeInicial = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeInicial);
				
				relatorioImoveisComAcordo.addParametro(
						"localidadeInicial", localidadeInicial);
			}else{
				throw new ActionServletException("atencao.pesquisa.localidade_inicial_inexistente");
			}

		}
		
		if (idLocalidadeFinal != null
				&& !idLocalidadeFinal.trim().equals("")) {
			
			FiltroLocalidade filtroLocalidadeFinal = new FiltroLocalidade();
			
			filtroLocalidadeFinal.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeFinal));

			Collection colecaoLocalidadeFinal = this.getFachada().pesquisar(filtroLocalidadeFinal,
					Localidade.class.getName());

			Localidade localidadeFinal = null;
			
			if (colecaoLocalidadeFinal != null && !colecaoLocalidadeFinal.isEmpty()) {
				
				localidadeFinal = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeFinal);
				
				relatorioImoveisComAcordo.addParametro(
						"localidadeFinal", localidadeFinal);
			}else{
				throw new ActionServletException("atencao.pesquisa.localidade_final_inexistente");
			}

		}
		
		if (idSetorComercialInicial != null
				&& !idSetorComercialInicial.trim().equals("")) {
			
			FiltroSetorComercial filtroSetorComercialInicial = new FiltroSetorComercial();
			
			filtroSetorComercialInicial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL
					, idSetorComercialInicial));

			Collection colecaoSetorComercialInicial = this.getFachada().pesquisar(filtroSetorComercialInicial,
					SetorComercial.class.getName());

			SetorComercial setorComercialInicial = null;
			
			if (colecaoSetorComercialInicial != null && !colecaoSetorComercialInicial.isEmpty()) {
				
				setorComercialInicial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercialInicial);
				
				relatorioImoveisComAcordo.addParametro(
						"setorComercialInicial", setorComercialInicial);
			}else{
				throw new ActionServletException("atencao.pesquisa.setor_inicial_inexistente");
			}

		}
		
		if (idSetorComercialFinal != null
				&& !idSetorComercialFinal.trim().equals("")) {
			
			FiltroSetorComercial filtroSetorComercialFinal = new FiltroSetorComercial();
			
			filtroSetorComercialFinal.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, idSetorComercialFinal));

			Collection colecaoSetorComercialFinal = this.getFachada().pesquisar(filtroSetorComercialFinal,
					SetorComercial.class.getName());

			SetorComercial setorComercialFinal = null;
			
			if (colecaoSetorComercialFinal != null && !colecaoSetorComercialFinal.isEmpty()) {
				
				setorComercialFinal = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercialFinal);
				
				relatorioImoveisComAcordo.addParametro(
						"setorComercialFinal", setorComercialFinal);
			}else{
				throw new ActionServletException("atencao.pesquisa.setor_final_inexistente");
			}

		}
		
		relatorioImoveisComAcordo.addParametro("rotaInicial", rotaInicial);
		relatorioImoveisComAcordo.addParametro("rotaFinal", rotaFinal);
		
		relatorioImoveisComAcordo.addParametro("sequencialRotaInicial", sequencialRotaInicial);
		relatorioImoveisComAcordo.addParametro("sequencialRotaFinal", sequencialRotaFinal);
		
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioImoveisComAcordo.addParametro(
				"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));

		retorno = processarExibicaoRelatorio(
				relatorioImoveisComAcordo, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);
		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
