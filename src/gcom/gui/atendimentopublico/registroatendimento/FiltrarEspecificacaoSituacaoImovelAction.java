package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroEspecificacaoImovelSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0403] - Filtrar Especificação da Situação do Imóvel
 * 
 * @author Rafael Francisco Pinto
 * @date 08/11/2006
 */

public class FiltrarEspecificacaoSituacaoImovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterEspecificacaoSituacaoImovelAction");

		FiltrarEspecificacaoSituacaoImovelActionForm form = 
			(FiltrarEspecificacaoSituacaoImovelActionForm) actionForm;

		// Recupera todos os campos da página para ser colocada no filtro
		// posteriormente
		String indicadorAtualizar = form.getIndicadorAtualizar();
		String idEspecificacao = form.getIdEspecificacao();
		String descricaoEspecificacao = form.getDescricaoEspecificacao();
		String tipoPesquisa = form.getTipoPesquisa();

		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			this.getSessao(httpServletRequest).setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {
			this.getSessao(httpServletRequest).removeAttribute("indicadorAtualizar");
		}

		boolean peloMenosUmParametroInformado = false;

		FiltroEspecificacaoImovelSituacao filtro = new FiltroEspecificacaoImovelSituacao();

		// Código
		if (idEspecificacao != null && !idEspecificacao.trim().equals("")) {

			peloMenosUmParametroInformado = true;
			filtro.adicionarParametro(
				new ParametroSimples(FiltroEspecificacaoImovelSituacao.ID, idEspecificacao));
		}

		// Descrição
		if (descricaoEspecificacao != null && !descricaoEspecificacao.trim().equals("")) {

			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				filtro.adicionarParametro(
					new ComparacaoTextoCompleto(FiltroEspecificacaoImovelSituacao.DESCRICAO,
						descricaoEspecificacao));
			} else {
				filtro.adicionarParametro(
					new ComparacaoTexto(FiltroEspecificacaoImovelSituacao.DESCRICAO, 
						descricaoEspecificacao));
			}
			
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		// Manda o filtro pela sessao para o
		// ExibirManterEspecificacaoSituacaoImovelAction
		this.getSessao(httpServletRequest).setAttribute("filtroEspecificacaoSituacaoImovel", filtro);

		return retorno;
	}
}
