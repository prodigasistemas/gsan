package gcom.gui.relatorio.faturamento.conta;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.conta.RelatorioConta;
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
 * action responsável pela exibição do relatório de contas
 * 
 * @author Rafael Corrêa
 * @created 27/07/2009
 */
public class GerarRelatorioContaAction extends
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

		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		// cria a variável de retorno
		ActionForward retorno = null;

		GerarRelatorioContaActionForm form = (GerarRelatorioContaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Valida os parâmetro passados como consulta
		boolean peloMenosUmParametroInformado = false;

		// Grupo
		Integer idGrupo = null;

		if (form.getIdGrupoFaturamento() != null
				&& !form.getIdGrupoFaturamento()
						.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			idGrupo = new Integer(form
					.getIdGrupoFaturamento());
			
			
		}
		
		// Rota
		Short codigoRotaInicial = null;
		Short codigoRotaFinal = null;
		Short sequencialRotaInicial = null;
		Short sequencialRotaFinal = null;

		if (form.getCodigoRotaInicial() != null
				&& !form.getCodigoRotaInicial().trim()
						.equals("")) {
			peloMenosUmParametroInformado = true;
			codigoRotaInicial = new Short(form
					.getCodigoRotaInicial());
			codigoRotaFinal = new Short(form
					.getCodigoRotaFinal());
			if (form.getSequencialRotaInicial() != null
					&& !form.getSequencialRotaInicial().trim()
							.equals("")) {
				peloMenosUmParametroInformado = true;
				sequencialRotaInicial = new Short(form
						.getSequencialRotaInicial());
				sequencialRotaFinal = new Short(form
						.getSequencialRotaFinal());
			}
		}

		// Localidade Inicial
		Localidade localidadeInicial = null;
		SetorComercial setorComercialInicial = null;

		String idLocalidadeInicial = form
				.getIdLocalidadeInicial();
		String codigoSetorComercialInicial = form
				.getCodigoSetorComercialInicial();

		if (idLocalidadeInicial != null && !idLocalidadeInicial.equals("")) {
			peloMenosUmParametroInformado = true;

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeInicial));

			Collection colecaoLocalidades = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
				localidadeInicial = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidades);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Localidade Inicial");
			}
			
			// Setor Comercial Inicial
			if (codigoSetorComercialInicial != null && !codigoSetorComercialInicial.trim().equals("")) {
				
				FiltroSetorComercial filtro = new FiltroSetorComercial();
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialInicial));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeInicial.getId()));
				Collection pesquisa = (Collection) fachada.pesquisar(filtro, SetorComercial.class.getName());
				
				if (pesquisa != null && !pesquisa.isEmpty()) {
					setorComercialInicial = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
				} else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
				}
			}
		}

		// Localidade Final
		Localidade localidadeFinal = null;
		SetorComercial setorComercialFinal = null;

		String idLocalidadeFinal = form
				.getIdLocalidadeFinal();
		String codigoSetorComercialFinal = form
				.getCodigoSetorComercialFinal();

		if (idLocalidadeFinal != null && !idLocalidadeFinal.equals("")) {
			peloMenosUmParametroInformado = true;

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeFinal));

			Collection colecaoLocalidades = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
				localidadeFinal = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidades);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Localidade Final");
			}
			
			// Setor Comercial Final
			if (codigoSetorComercialFinal != null && !codigoSetorComercialFinal.trim().equals("")) {
				
				FiltroSetorComercial filtro = new FiltroSetorComercial();
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialFinal));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeFinal.getId()));
				Collection pesquisa = (Collection) fachada.pesquisar(filtro, SetorComercial.class.getName());
				
				if (pesquisa != null && !pesquisa.isEmpty()) {
					setorComercialFinal = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
				} else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
				}
			}
		}
		

		// Referência
		Integer mesAno = null;

		if (form.getMesAno() != null && !form.getMesAno().equals("")) {
			mesAno = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAno());
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// seta os parametros que serão mostrados no relatório

		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		RelatorioConta relatorio = new RelatorioConta(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));

		relatorio.addParametro("mesAno", mesAno);
		relatorio.addParametro("idGrupo", idGrupo);

		if (localidadeInicial != null) {
			relatorio.addParametro("idLocalidadeInicial",
					localidadeInicial.getId());
		}

		if (localidadeFinal != null) {
			relatorio.addParametro("idLocalidadeFinal",
					localidadeFinal.getId());
		}
		
		if (setorComercialInicial != null) {
			relatorio.addParametro("codigoSetorComercialInicial", setorComercialInicial.getCodigo());
		}
		
		if (setorComercialFinal != null) {
			relatorio.addParametro("codigoSetorComercialFinal", setorComercialFinal.getCodigo());
		}

		
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, idGrupo));
		
		Collection colecaoFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) colecaoFaturamentoGrupo.iterator().next();
		//Solicitado por Eduardo Borges
		//Caso o ano mes do grupo - 1 seja menor ou igual ao informado, não existem contas.
		if((Util.subtraiAteSeisMesesAnoMesReferencia(faturamentoGrupo.getAnoMesReferencia(), 1) < mesAno) ){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
			
			
		relatorio.addParametro("codigoRotaInicial", codigoRotaInicial);
		relatorio.addParametro("codigoRotaFinal", codigoRotaFinal);
		
		relatorio.addParametro("sequencialRotaInicial", sequencialRotaInicial);
		relatorio.addParametro("sequencialRotaFinal", sequencialRotaFinal);
		
		relatorio.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		
		String indicadorEmissao = form.getIndicadorEmissao();
		String indicadorOrdenacao = form.getIndicadorOrdenacao();
		
		relatorio.addParametro("indicadorEmissao",indicadorEmissao);
		relatorio.addParametro("indicadorOrdenacao",indicadorOrdenacao);

		retorno = processarExibicaoRelatorio(relatorio,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
