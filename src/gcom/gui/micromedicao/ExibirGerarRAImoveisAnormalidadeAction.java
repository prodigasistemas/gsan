package gcom.gui.micromedicao;

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que faz a exibição da tela para o usuário setar os campos e permitir
 * que ele insera uma resolução de diretoria [UC0217] Inserir Resolução de
 * Diretoria
 * 
 * @author Rafael Corrêa
 * @since 30/03/2006
 */
public class ExibirGerarRAImoveisAnormalidadeAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
 		ActionForward retorno = actionMapping
				.findForward("exibirGerarRAImoveisAnormalidade");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		GerarRAImoveisAnormalidadeActionForm gerarRAImoveisAnormalidadeActionForm = (GerarRAImoveisAnormalidadeActionForm) actionForm;

		if (httpServletRequest.getParameter("reload") != null
				&& !httpServletRequest.getParameter("reload").trim().equals("")) {
			httpServletRequest.setAttribute("reload", "sim");
		} else {

			if (httpServletRequest.getParameter("limparForm") != null
					&& !httpServletRequest.getParameter("limparForm").trim()
							.equals("")) {
				gerarRAImoveisAnormalidadeActionForm.setSolicitacaoTipo(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO);
				gerarRAImoveisAnormalidadeActionForm
						.setSolicitacaoTipoEspecificacao(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO);
				
				// Recupera os imóveis selecionados pelo usuário
				Collection<Imovel> colecaoImoveisGerarOS = null;
				HashMap<Integer, String> colecaoObservacaoOS = (HashMap<Integer, String>) 
					sessao.getAttribute("colecaoObservacaoOS");

				Collection colecaoIdsImovel = (Collection) sessao
						.getAttribute("colecaoIdsImovelTotal");
				int index = (Integer) sessao.getAttribute("index");

				Imovel imovelAtual = ((ImovelMicromedicao) ((List) colecaoIdsImovel)
						.get(index)).getImovel();

				// Verifica se o imóvel o imóvel atual foi selecionado, em caso
				// afirmativo adiciona-o a coleção
				if (httpServletRequest.getParameter("gerarOS") != null
						&& !httpServletRequest.getParameter("gerarOS").trim()
								.equals("")) {

					if (colecaoImoveisGerarOS == null) {
						colecaoImoveisGerarOS = new ArrayList();
						colecaoObservacaoOS = new HashMap<Integer, String>();
					}

					if (!colecaoImoveisGerarOS.contains(imovelAtual)) {
						colecaoImoveisGerarOS.add(imovelAtual);
						if(httpServletRequest.getParameter("observacao") != null && !httpServletRequest.getParameter("observacao").equals("")){
							String observacao = (String) httpServletRequest.getParameter("observacao");
							colecaoObservacaoOS.put(imovelAtual.getId(), observacao);
						}
					}
				} else {
					if (colecaoImoveisGerarOS != null
							&& colecaoImoveisGerarOS.contains(imovelAtual)) {
						colecaoImoveisGerarOS.remove(imovelAtual);
						colecaoObservacaoOS.remove(imovelAtual.getId());
					}
				}
				
				sessao.setAttribute("colecaoImoveisGerarOS", colecaoImoveisGerarOS);
				sessao.setAttribute("colecaoObservacaoOS", colecaoObservacaoOS);
			}

			/*
			 * Tipo de Solicitação - Carregando a coleção que irá ficar
			 * disponível para escolha do usuário
			 * 
			 * [FS0003] - Verificar existência de dados
			 */
			Collection colecaoSolicitacaoTipo = (Collection) sessao
					.getAttribute("colecaoSolicitacaoTipo");

			if (colecaoSolicitacaoTipo == null) {

				FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo(
						FiltroSolicitacaoTipo.DESCRICAO);

				filtroSolicitacaoTipo.setConsultaSemLimites(true);

				filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
						FiltroSolicitacaoTipo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				colecaoSolicitacaoTipo = fachada.pesquisar(
						filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());

				if (colecaoSolicitacaoTipo == null
						|| colecaoSolicitacaoTipo.isEmpty()) {
					throw new ActionServletException(
							"atencao.entidade_sem_dados_para_selecao", null,
							"SOLICITACAO_TIPO");
				} else {
					sessao.setAttribute("colecaoSolicitacaoTipo",
							colecaoSolicitacaoTipo);
				}
			}

			/*
			 * Especificação - Carregando a coleção que irá ficar disponível
			 * para escolha do usuário
			 * 
			 * [FS0003] - Verificar existência de dados
			 */
			String idSolicitacaoTipo = gerarRAImoveisAnormalidadeActionForm
					.getSolicitacaoTipo();

			if (idSolicitacaoTipo != null
					&& !idSolicitacaoTipo.equals(""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao(
						FiltroSolicitacaoTipoEspecificacao.DESCRICAO);

				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID,
								new Integer(idSolicitacaoTipo)));

				filtroSolicitacaoTipoEspecificacao.setConsultaSemLimites(true);

				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoSolicitacaoTipoEspecificacao = fachada
						.pesquisar(filtroSolicitacaoTipoEspecificacao,
								SolicitacaoTipoEspecificacao.class.getName());

				if (colecaoSolicitacaoTipoEspecificacao == null
						|| colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
					sessao
							.removeAttribute("colecaoSolicitacaoTipoEspecificacao");
					throw new ActionServletException(
							"atencao.entidade_sem_dados_para_selecao", null,
							"SOLICITACAO_TIPO_ESPECIFICACAO");
				} else {
					sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
							colecaoSolicitacaoTipoEspecificacao);
				}
			} else {
				sessao.removeAttribute("colecaoSolicitacaoTipoEspecificacao");
			}

		}

		return retorno;

	}

}
