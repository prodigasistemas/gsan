package gcom.gui.faturamento.autoinfracao;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.autoinfracao.FiltroAutosInfracao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarAutoInfracaoAction extends GcomAction {

	/**
	 * Filtrar Autos de Infração
	 * 
	 * @author Rômulo Aurélio - 22/04/2009
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterAutoInfracaoAction");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarAutoInfracaoActionForm form = (FiltrarAutoInfracaoActionForm) actionForm;

		boolean peloMenosUmParametroInformado = false;

		String idImovel = form.getIdImovel();

		String idFuncionario = form.getIdFuncionario();

		String idFiscalizacaoSituacao = form.getIdFiscalizacaoSituacao();

		String idAutoInfracaoSituacao = form.getIdAutoInfracaoSituacao();

		String dataEmissaoInicialForm = form.getDataEmissaoInicial();

		String dataEmissaoFinalForm = form.getDataEmissaoFinal();

		String dataInicioRecursoInicialForm = form
				.getDataInicioRecursoInicial();

		String dataInicioRecursoFinalForm = form.getDataInicioRecursoFinal();

		String dataTerminoRecursoInicialForm = form
				.getDataTerminoRecursoInicial();

		String dataTerminoRecursoFinalForm = form.getDataTerminoRecursoFinal();

		FiltroAutosInfracao filtroAutosInfracao = new FiltroAutosInfracao();
		// Verifica se o campo codigo foi informado

		if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {

			FiltroImovel filtroImovel = new FiltroImovel();

			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, idImovel));

			Collection colecaoImovel = this.getFachada().pesquisar(
					filtroImovel, Imovel.class.getName());

			if (colecaoImovel == null || colecaoImovel.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Imóvel");
			} else {

				filtroAutosInfracao.adicionarParametro(new ParametroSimples(
						FiltroAutosInfracao.IMOVEL_ID, idImovel));

				peloMenosUmParametroInformado = true;
			}

		}

		if (idFuncionario != null && !idFuncionario.trim().equalsIgnoreCase("")) {

			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, idFuncionario));

			Collection colecaoFuncionario = this.getFachada().pesquisar(
					filtroFuncionario, Funcionario.class.getName());

			if (colecaoFuncionario == null || colecaoFuncionario.isEmpty()) {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Funcionário");

			} else {

				filtroAutosInfracao.adicionarParametro(new ParametroSimples(
						FiltroAutosInfracao.FUNCIONARIO_ID, idFuncionario));

				peloMenosUmParametroInformado = true;
			}
		}

		if (idFiscalizacaoSituacao != null
				&& !idFiscalizacaoSituacao.trim().equalsIgnoreCase("-1")) {

			peloMenosUmParametroInformado = true;

			filtroAutosInfracao.adicionarParametro(new ParametroSimples(
					FiltroAutosInfracao.FISCALIZACAO_SITUACAO_ID,
					idFiscalizacaoSituacao));

		}

		if (idAutoInfracaoSituacao != null
				&& !idAutoInfracaoSituacao.trim().equalsIgnoreCase("-1")) {

			peloMenosUmParametroInformado = true;

			filtroAutosInfracao.adicionarParametro(new ParametroSimples(
					FiltroAutosInfracao.AUTO_INFRACAO_SITUACAO_ID,
					idAutoInfracaoSituacao));

		}

		if (dataEmissaoInicialForm != null
				&& !dataEmissaoInicialForm.equalsIgnoreCase("")) {

			Date dataEmissaoInicial = Util
					.converteStringParaDate(dataEmissaoInicialForm);

			Date dataEmissaoFinal = Util
					.converteStringParaDate(dataEmissaoFinalForm);

			if (dataEmissaoInicial.compareTo(dataEmissaoFinal) > 0) {
				throw new ActionServletException(
						"atencao.data_final_anterior_data_inicial", null,
						"de Emissão");
			} else {
				peloMenosUmParametroInformado = true;
				filtroAutosInfracao.adicionarParametro(new Intervalo(
						FiltroAutosInfracao.DATA_EMISSAO, dataEmissaoInicial,
						dataEmissaoFinal));

			}

		}

		if (dataInicioRecursoInicialForm != null
				&& !dataInicioRecursoInicialForm.equalsIgnoreCase("")) {

			Date dataInicioRecursoInicial = Util
					.converteStringParaDate(dataInicioRecursoInicialForm);

			Date dataInicioRecursoFinal = Util
					.converteStringParaDate(dataInicioRecursoFinalForm);

			if (dataInicioRecursoInicial.compareTo(dataInicioRecursoFinal) > 0) {
				throw new ActionServletException(
						"atencao.data_final_anterior_data_inicial", null,
						"de Início do Recurso");
			} else {
				peloMenosUmParametroInformado = true;
				filtroAutosInfracao.adicionarParametro(new Intervalo(
						FiltroAutosInfracao.DATA_INICIO_RECURSO,
						dataInicioRecursoInicial, dataInicioRecursoFinal));

			}

		}

		if (dataTerminoRecursoInicialForm != null
				&& !dataTerminoRecursoInicialForm.equalsIgnoreCase("")) {

			Date dataTerminoRecursoInicial = Util
					.converteStringParaDate(dataTerminoRecursoInicialForm);

			Date dataTerminoRecursoFinal = Util
					.converteStringParaDate(dataTerminoRecursoFinalForm);

			if (dataTerminoRecursoInicial.compareTo(dataTerminoRecursoFinal) > 0) {
				throw new ActionServletException(
						"atencao.data_final_anterior_data_inicial", null,
						"de Início do Recurso");
			} else {
				peloMenosUmParametroInformado = true;
				filtroAutosInfracao.adicionarParametro(new Intervalo(
						FiltroAutosInfracao.DATA_TERMINO_RECURSO,
						dataTerminoRecursoInicial, dataTerminoRecursoFinal));

			}

		}

		// Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pelo um request uma variável para o
		// ExibirManterAutoInfracaoAction e nele verificar se irá para o
		// atualizar ou para o manter
		if (form.getAtualizar() != null
				&& form.getAtualizar().equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar", form.getAtualizar());

		}
		
		

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		filtroAutosInfracao.setCampoOrderBy(FiltroAutosInfracao.ID);
		
		
//		 Manda o filtro pelo sessao para o
		// ExibirFuncionalidadeAction
		sessao.setAttribute("filtroAutosInfracao", filtroAutosInfracao);

		return retorno;

	}

}
