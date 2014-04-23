package gcom.gui.micromedicao;

import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
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
 * Action que faz a exibição da tela para o usuário setar os campos e permitir
 * que ele insera uma resolução de diretoria [UC0217] Inserir Resolução de
 * Diretoria
 * 
 * @author Rafael Corrêa
 * @since 28/06/2008
 */
public class ExibirGerarDadosLeituraAction extends GcomAction {

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
				.findForward("exibirGerarDadosLeitura");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		GerarDadosLeituraActionForm form = (GerarDadosLeituraActionForm) actionForm;
		
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
		
		Collection colecaoFaturamentoGrupo = fachada.pesquisar(
				filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		
		sessao.setAttribute("colecaoFaturamentoGrupo", colecaoFaturamentoGrupo);
		
		if (form.getIdRota() != null && !form.getIdRota().trim().equals("")) {
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, form.getIdRota()));
			filtroRota.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
			
			Collection colecaoRota = fachada.pesquisar(filtroRota, Rota.class.getName());
			
			if (colecaoRota != null && !colecaoRota.isEmpty()) {
				Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRota);
				form.setCodigoRota(rota.getCodigo().toString());
				
				form.setIdGrupoFaturamento(rota.getFaturamentoGrupo().getId() + "");
				
				sessao.setAttribute("idFaturamentoGrupo", rota.getFaturamentoGrupo().getId());
			}
			
		}else{
			sessao.removeAttribute("idFaturamentoGrupo");
		}
		
		if (form.getIdLocalidadeInicial() != null && !form.getIdLocalidadeInicial().trim().equals("")) {
			
			Localidade localidade = fachada.pesquisarLocalidadeDigitada(new Integer(form.getIdLocalidadeInicial()));
			
			if (localidade != null) {
				form.setIdLocalidadeInicial(localidade.getId().toString());
				form.setNomeLocalidadeInicial(localidade.getDescricao());
			} else {
				form.setIdLocalidadeInicial("");
				form.setNomeLocalidadeInicial("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("localidadeInicialInexistente", true);
			}
			
		} else {
			form.setNomeLocalidadeInicial("");
		}
		
		if (form.getIdLocalidadeFinal() != null && !form.getIdLocalidadeFinal().trim().equals("")) {
			
			Localidade localidade = fachada.pesquisarLocalidadeDigitada(new Integer(form.getIdLocalidadeFinal()));
			
			if (localidade != null) {
				form.setIdLocalidadeFinal(localidade.getId().toString());
				form.setNomeLocalidadeFinal(localidade.getDescricao());
			} else {
				form.setIdLocalidadeFinal("");
				form.setNomeLocalidadeFinal("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("localidadeFinalInexistente", true);
			}
			
		} else {
			form.setNomeLocalidadeFinal("");
		}
		
		return retorno;

	}

}
