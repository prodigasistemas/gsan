package gcom.gui.atendimentopublico.registroatendimento;

import java.util.Date;

import gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0404] - Manter Especificação da Situação do Imóvel
 * [SB0001] - Atualizar Especificação da situação
 *
 * @author Rafael Pinto
 * 
 * @date 09/11/2006
 */

public class AtualizarEspecificacaoSituacaoImovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarEspecificacaoSituacaoImovelActionForm form = 
			(AtualizarEspecificacaoSituacaoImovelActionForm) actionForm;

		HttpSession sessao = this.getSessao(httpServletRequest);

		EspecificacaoImovelSituacao especificacaoImovelSituacao = 
			(EspecificacaoImovelSituacao) sessao.getAttribute("especificacaoImovelSituacao");

		if (form.getDescricaoEspecificacao() != null && !form.getDescricaoEspecificacao().equals("")) {
		
			especificacaoImovelSituacao.setDescricao(form.getDescricaoEspecificacao());
		} else {
			throw new ActionServletException("atencao.required", null,
					"Descrição da Especificação");
		}
		
		Date ultimaAlteracao = (Date) this.getSessao(httpServletRequest).getAttribute("ultimaAlteracao");
		especificacaoImovelSituacao.setUltimaAlteracao(ultimaAlteracao);
		
		this.getFachada().atualizarEspecificacaoSituacaoImovel(especificacaoImovelSituacao,
				form.getEspecificacaoImovelSituacaoCriterio(),
				form.getEspecificacaoImovelSituacaoCriterioRemovidas(),
				this.getUsuarioLogado(httpServletRequest));

		montarPaginaSucesso(httpServletRequest, "Especificação da Situação do Imóvel "
				+ especificacaoImovelSituacao.getId() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Especificação da Situação do Imóvel",
				"exibirFiltrarEspecificacaoSituacaoImovelAction.do?menu=sim");

		return retorno;
	}

}
