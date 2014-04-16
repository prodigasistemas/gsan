package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoGrupo;
import gcom.seguranca.acesso.usuario.SolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1093] Manter Solicitação de Acesso.
 * 
 * @author Hugo Leonardo
 * @date 17/11/2010
 */
public class RemoverSolicitacaoAcessoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;
		
		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();
		
		// mensagem de erro quando o usuário tenta Atualizar sem ter selecionado
		// nenhum registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}
		
		FiltroSolicitacaoAcesso filtroSolicitacaoAcesso = new FiltroSolicitacaoAcesso();
		
		Collection idsSolicitacaoAcesso = new ArrayList(ids.length);
		
		for (int i = 0; i < ids.length; i++) {
			idsSolicitacaoAcesso.add(new Integer(ids[i]));
		}
		
		filtroSolicitacaoAcesso.adicionarParametro(
				new ParametroSimplesIn(FiltroSolicitacaoAcesso.ID, idsSolicitacaoAcesso));

		Collection coletionSolicitacaoAcesso = fachada.pesquisar(filtroSolicitacaoAcesso,
				SolicitacaoAcesso.class.getName());
		
		Iterator itera = coletionSolicitacaoAcesso.iterator();
		
		while(itera.hasNext()){
		
			SolicitacaoAcesso solicitacaoAcesso = (SolicitacaoAcesso) itera.next();
			
			// Grupos
			FiltroSolicitacaoAcessoGrupo filtroSolicitacaoAcessoGrupo = new FiltroSolicitacaoAcessoGrupo();
			
			filtroSolicitacaoAcessoGrupo.setConsultaSemLimites(true);
			filtroSolicitacaoAcessoGrupo.adicionarCaminhoParaCarregamentoEntidade(
					FiltroSolicitacaoAcessoGrupo.GRUPO);
			filtroSolicitacaoAcessoGrupo.adicionarParametro(new ParametroSimples( 
					FiltroSolicitacaoAcessoGrupo.SOLICITACAO_ACESSO_ID, solicitacaoAcesso.getId()) );
				
			Collection colecaoSolicitacaoAcessoGrupo = 
				Fachada.getInstancia().pesquisar(filtroSolicitacaoAcessoGrupo, SolicitacaoAcessoGrupo.class.getName());
			
			if (colecaoSolicitacaoAcessoGrupo != null && !colecaoSolicitacaoAcessoGrupo.isEmpty()) {
				
				Iterator iterator = colecaoSolicitacaoAcessoGrupo.iterator();
				
				SolicitacaoAcessoGrupo solicitacaoAcessoGrupo = null;
				
				while (iterator.hasNext()) {
				
					solicitacaoAcessoGrupo = (SolicitacaoAcessoGrupo) iterator.next();
					
					// Remover todos os Grupos da solicitação de acesso
					fachada.remover(solicitacaoAcessoGrupo);
				}
			}
			
			// Remover a solicitação de acesso.
			fachada.remover(solicitacaoAcesso);
		}

		Integer idQt = ids.length;
		
		if(sessao.getAttribute("objeto") != null){
			String objeto = (String) sessao.getAttribute("objeto");
			
			if(objeto.equals("atualizar")){
				
				montarPaginaSucesso(httpServletRequest, idQt.toString()
						+ " Solicitação(ões) de Acesso(s) Removido(s) com sucesso.",
						"Manter outra Solicitação de Acesso",
						"exibirFiltrarSolicitacaoAcessoAction.do?menu=sim&objeto=atualizar");
			
			}else if(objeto.equals("autorizar")){
				
				montarPaginaSucesso(httpServletRequest, idQt.toString()
						+ " Solicitação(ões) de Acesso(s) Removido(s) com sucesso.",
						"Manter outra Solicitação de Acesso",
						"exibirFiltrarSolicitacaoAcessoAction.do?menu=sim&objeto=autorizar");
			
			}else if(objeto.equals("cadastrar")){
				
				montarPaginaSucesso(httpServletRequest, idQt.toString()
						+ " Solicitação(ões) de Acesso(s) Removido(s) com sucesso.",
						"Manter outra Solicitação de Acesso",
						"exibirFiltrarSolicitacaoAcessoAction.do?menu=sim&objeto=cadastrar");
			}
		}

		return retorno;
	}

}
