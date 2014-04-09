package gcom.gui.operacional.abastecimento;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.FiltroDivisaoEsgoto;
import gcom.operacional.FiltroSistemaEsgoto;
import gcom.operacional.SistemaEsgoto;
import gcom.operacional.SistemaEsgotoTratamentoTipo;
import gcom.operacional.abastecimento.FiltroSistemaEsgotoTratamentoTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC0525] Manter SistemaEsgoto [SB0001]Atualizar SistemaEsgoto
 *
 * @author Kássia Albuquerque
 * @date 16/03/2007
 */

public class ExibirAtualizarSistemaEsgotoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("atualizarSistemaEsgoto");
			
			AtualizarSistemaEsgotoActionForm atualizarSistemaEsgotoActionForm = (AtualizarSistemaEsgotoActionForm)actionForm;
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Fachada fachada = Fachada.getInstancia();
			
			
			FiltroDivisaoEsgoto filtroDivisaoEsgoto = new FiltroDivisaoEsgoto();
			
			filtroDivisaoEsgoto.adicionarParametro(new ParametroSimples
					(FiltroDivisaoEsgoto.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroDivisaoEsgoto.setCampoOrderBy(FiltroDivisaoEsgoto.DESCRICAO);
			
			Collection colecaoDivisaoEsgoto = fachada.pesquisar(filtroDivisaoEsgoto, DivisaoEsgoto.class.getName());
			
			httpServletRequest.setAttribute("colecaoDivisaoEsgoto", colecaoDivisaoEsgoto);
			
			
			
			FiltroSistemaEsgotoTratamentoTipo filtroSistemaEsgotoTratamentoTipo = new FiltroSistemaEsgotoTratamentoTipo();
			
			filtroSistemaEsgotoTratamentoTipo.adicionarParametro(new ParametroSimples
					(FiltroSistemaEsgotoTratamentoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroSistemaEsgotoTratamentoTipo.setCampoOrderBy(FiltroSistemaEsgotoTratamentoTipo.NOME);
			
			Collection colecaoSistemaEsgotoTratamentoTipo = fachada.pesquisar(filtroSistemaEsgotoTratamentoTipo, SistemaEsgotoTratamentoTipo.class.getName());
			
			httpServletRequest.setAttribute("colecaoSistemaEsgotoTratamentoTipo", colecaoSistemaEsgotoTratamentoTipo);			
						
			SistemaEsgoto sistemaEsgoto = null;
			
			String idSistemaEsgoto = null;
			
			if (httpServletRequest.getParameter("idSistemaEsgoto") != null) {
				//tela do manter
				idSistemaEsgoto = (String) httpServletRequest.getParameter("idSistemaEsgoto");
				sessao.setAttribute("idSistemaEsgoto", idSistemaEsgoto);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterSistemaEsgotoAction.do");
			} else if (sessao.getAttribute("idSistemaEsgoto") != null) {
				//tela do filtrar
				idSistemaEsgoto = (String) sessao.getAttribute("idSistemaEsgoto");
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarSistemaEsgotoAction.do");
			}else if(httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null){
				//link na tela de sucesso do inserir sistema esgoto
				idSistemaEsgoto = (String)httpServletRequest.getParameter("idRegistroInseridoAtualizar");
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarSistemaEsgotoAction.do?menu=sim");
			}
			
			if (idSistemaEsgoto == null){
				
				if (sessao.getAttribute("idRegistroAtualizar") != null){
					sistemaEsgoto = (SistemaEsgoto) sessao.getAttribute("idRegistroAtualizar");
				}else{
					idSistemaEsgoto = (String) httpServletRequest.getParameter("idSistemaEsgoto").toString();
				}
			}
			
				//------Inicio da parte que verifica se vem da página de sistema_esgoto_manter.jsp
			
				
				if (sistemaEsgoto == null){
				
					if (idSistemaEsgoto != null && !idSistemaEsgoto.equals("")) {
		
						FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto();
						
						filtroSistemaEsgoto.adicionarCaminhoParaCarregamentoEntidade("divisaoEsgoto");
						filtroSistemaEsgoto.adicionarCaminhoParaCarregamentoEntidade("sistemaEsgotoTratamentoTipo");
						
						filtroSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSistemaEsgoto.ID, idSistemaEsgoto));
		
						Collection colecaoSistemaEsgoto = fachada.pesquisar(filtroSistemaEsgoto, SistemaEsgoto.class.getName());
	
						if (colecaoSistemaEsgoto != null && !colecaoSistemaEsgoto.isEmpty()) {
							
							sistemaEsgoto = (SistemaEsgoto) Util.retonarObjetoDeColecao(colecaoSistemaEsgoto);
							
						}
					}
				}
				
				//  ------  O sistema de esgoto foi encontrado
				
				atualizarSistemaEsgotoActionForm.setDescricaoSistemaEsgoto(sistemaEsgoto.getDescricao());
	
				atualizarSistemaEsgotoActionForm.setDescricaoAbreviada(sistemaEsgoto.getDescricaoAbreviada());
				
				atualizarSistemaEsgotoActionForm.setDivisaoEsgoto(sistemaEsgoto.getDivisaoEsgoto().getId().toString());
				
				atualizarSistemaEsgotoActionForm.setTipoTratamento(sistemaEsgoto.getSistemaEsgotoTratamentoTipo().getId().toString());
				
				atualizarSistemaEsgotoActionForm.setIndicadorUso(""+sistemaEsgoto.getIndicadorUso());
				
				atualizarSistemaEsgotoActionForm.setUltimaAlteracao( Util.formatarDataComHora( sistemaEsgoto.getUltimaAlteracao() ) );

				sessao.setAttribute("sistemaEsgoto", sistemaEsgoto);
				
				httpServletRequest.setAttribute("idSistemaEsgoto", idSistemaEsgoto);
				
				// ------ Fim da parte que verifica se vem da página de sistema_esgoto_manter.jsp
			
			
				return retorno;
	}

}
					
		


