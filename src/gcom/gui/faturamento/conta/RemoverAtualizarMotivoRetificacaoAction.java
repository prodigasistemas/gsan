package gcom.gui.faturamento.conta;

import java.util.Collection;
import java.util.Iterator;

import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.conta.ContaMotivoRetificacaoColuna;
import gcom.faturamento.conta.FiltroContaMotivoRetificacao;
import gcom.faturamento.conta.FiltroContaMotivoRetificacaoColuna;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoverAtualizarMotivoRetificacaoAction  extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		String[] idsRegistrosRemocao = manutencaoRegistroActionForm
				.getIdRegistrosRemocao();

		for (int i = 0; i < idsRegistrosRemocao.length; i++) {
			FiltroContaMotivoRetificacaoColuna filtroContaMotivoRetificacaoColuna = new FiltroContaMotivoRetificacaoColuna();
			filtroContaMotivoRetificacaoColuna.adicionarParametro(
					new ParametroSimples(FiltroContaMotivoRetificacaoColuna.CONTA_MOTIVO_RETIFICACAO_ID, idsRegistrosRemocao[i]));
			filtroContaMotivoRetificacaoColuna.adicionarCaminhoParaCarregamentoEntidade(
					FiltroContaMotivoRetificacaoColuna.CONTA_MOTIVO_RETIFICACAO);
			Collection colecaoContaMotivoRetificacaoColuna = fachada
					.pesquisar(filtroContaMotivoRetificacaoColuna,ContaMotivoRetificacaoColuna.class.getName());
			
			ContaMotivoRetificacao contaMotivoRetificacao = null;
			
			if (colecaoContaMotivoRetificacaoColuna != null
					&& !colecaoContaMotivoRetificacaoColuna.isEmpty()) {
				contaMotivoRetificacao = ((ContaMotivoRetificacaoColuna)
						Util.retonarObjetoDeColecao(colecaoContaMotivoRetificacaoColuna)).getContaMotivoRetificacao();
				
				Iterator iterator = colecaoContaMotivoRetificacaoColuna.iterator();
				
				while(iterator.hasNext()){
					ContaMotivoRetificacaoColuna contaMotivoRetificacaoColuna = (ContaMotivoRetificacaoColuna) iterator.next();
					fachada.remover(contaMotivoRetificacaoColuna);
				}
				
			} else {
				FiltroContaMotivoRetificacao filtroContaMotivoRetificacao = new FiltroContaMotivoRetificacao();
				filtroContaMotivoRetificacao.adicionarParametro(
						new ParametroSimples(FiltroContaMotivoRetificacao.CODIGO, idsRegistrosRemocao[i]));
				Collection colecaoContaMotivoRetificacao = fachada.pesquisar(
						filtroContaMotivoRetificacao, ContaMotivoRetificacao.class.getName());
				if (colecaoContaMotivoRetificacao != null && !colecaoContaMotivoRetificacao.isEmpty()) {
					contaMotivoRetificacao = (ContaMotivoRetificacao)Util.retonarObjetoDeColecao(colecaoContaMotivoRetificacao);
				}
			}
			
			fachada.remover(contaMotivoRetificacao);
			
		}
		
		montarPaginaSucesso(httpServletRequest, idsRegistrosRemocao.length
				+ " Motivo(s) de Retificação excluído(s) com sucesso.",
				"Manter outro Motivo de Retificação ",
				"exibirFiltrarMotivoRetificacaoAction.do?menu=sim");

		return retorno;

	}

}
