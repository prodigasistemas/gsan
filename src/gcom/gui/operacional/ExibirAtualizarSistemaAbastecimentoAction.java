package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroFonteCaptacao;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.FiltroTipoCaptacao;
import gcom.operacional.FonteCaptacao;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.TipoCaptacao;
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
 * [UC0522] Atualizar Sistema de Abastecimento
 *
 * @author Fernando Fontelles Filho
 * @date 03/11/2009
 */

public class ExibirAtualizarSistemaAbastecimentoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("sistemaAbastecimentoAtualizar");				
		
		AtualizarSistemaAbastecimentoActionForm form = (AtualizarSistemaAbastecimentoActionForm)actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		String id = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
			id = httpServletRequest.getParameter("idRegistroAtualizacao");
		} else {
			//id = ((SistemaAbastecimento) sessao.getAttribute("sistemaAbastecimento")).getId().toString();
			id = ((SistemaAbastecimento)sessao.getAttribute("atualizarSistemaAbastecimento")).getId().toString();
		}
		
		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}
		
		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}
		
		SistemaAbastecimento sistemaAbastecimento = new SistemaAbastecimento();
		
		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {
		
			FiltroSistemaAbastecimento filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();
			
			filtroSistemaAbastecimento.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
			filtroSistemaAbastecimento.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao.tipoCaptacao");
			
			filtroSistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSistemaAbastecimento.ID, id));
			
			Collection colecaoSistemaAbastecimento = fachada.pesquisar
				(filtroSistemaAbastecimento,SistemaAbastecimento.class.getName());
			
			if(colecaoSistemaAbastecimento != null && !colecaoSistemaAbastecimento.isEmpty()){
			
				sistemaAbastecimento = (SistemaAbastecimento)Util.retonarObjetoDeColecao(colecaoSistemaAbastecimento);
				
			}
			
			if(id == null || id.trim().equals("")){
				
				form.setId(sistemaAbastecimento.getId().toString());
				form.setDescricao(sistemaAbastecimento.getDescricao());
				form.setDescricaoAbreviada(sistemaAbastecimento.getDescricaoAbreviada());
				form.setIndicadorUso(sistemaAbastecimento.getIndicadorUso());
				
				if(sistemaAbastecimento.getFonteCaptacao() != null){
					
					if (sistemaAbastecimento.getFonteCaptacao().getTipoCaptacao() != null){
						
						form.setTipoCaptacao(sistemaAbastecimento.getFonteCaptacao().getTipoCaptacao().getId().toString());
						form.setDescricaoTipoCaptacao(sistemaAbastecimento.getFonteCaptacao().getTipoCaptacao().getDescricao());
						
					}
					
					form.setFonteCaptacaoId(sistemaAbastecimento.getFonteCaptacao().getId().toString());
					form.setFonteCaptacaoDescricao(sistemaAbastecimento.getFonteCaptacao().getDescricao());
					
				}
				
			}
			
			form.setId(sistemaAbastecimento.getId().toString());
			form.setDescricao(sistemaAbastecimento.getDescricao());
			form.setDescricaoAbreviada(sistemaAbastecimento.getDescricaoAbreviada());
			form.setIndicadorUso(sistemaAbastecimento.getIndicadorUso());
			
			if(sistemaAbastecimento.getFonteCaptacao() != null){
				
				if (sistemaAbastecimento.getFonteCaptacao().getTipoCaptacao() != null){
					
					form.setTipoCaptacao(sistemaAbastecimento.getFonteCaptacao().getTipoCaptacao().getId().toString());
					form.setDescricaoTipoCaptacao(sistemaAbastecimento.getFonteCaptacao().
							getTipoCaptacao().getDescricao());
					
				}
				
				form.setFonteCaptacaoId(sistemaAbastecimento.getFonteCaptacao().getId().toString());
				form.setFonteCaptacaoDescricao(sistemaAbastecimento.getFonteCaptacao().getDescricao());
				
			}
			
			if (sistemaAbastecimento.getFonteCaptacao() != null 
					&& sistemaAbastecimento.getFonteCaptacao().getId() != null
					&& sistemaAbastecimento.getFonteCaptacao().getDescricao() != null
					&& !sistemaAbastecimento.getFonteCaptacao().getDescricao().equals("FONTE DE CAPTACÃO INEXISTENTE")){
				httpServletRequest.setAttribute("fonteCaptacaoEncontrada", true);
			}
			
			if (sistemaAbastecimento.getFonteCaptacao() != null
					&& sistemaAbastecimento.getFonteCaptacao().getTipoCaptacao().getId() != null
					&& sistemaAbastecimento.getFonteCaptacao().getTipoCaptacao().getDescricao() != null
					&& !sistemaAbastecimento.getFonteCaptacao().getTipoCaptacao().getDescricao().equals("TIPO DE CAPTACÃO INEXISTENTE")){
				httpServletRequest.setAttribute("tipoCaptacaoEncontrado", true);
			}
			
			sessao.setAttribute("atualizarSistemaAbastecimento", sistemaAbastecimento);

			if (sessao.getAttribute("colecaoSistemaAbastecimento") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarSistemaAbastecimentoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarSistemaAbastecimentoAction.do");
			}
			
		}
		
		// pesquisa os dados do enter
		pesquisarEnter(form, httpServletRequest,fachada); 

		return retorno;
	
	}

	private void pesquisarEnter(
			AtualizarSistemaAbastecimentoActionForm form,
			HttpServletRequest httpServletRequest, Fachada fachada) {

		
		if ( httpServletRequest.getParameter("idTipoCaptacao") != null ) {
			String idTipoCaptacao = (String) httpServletRequest.getParameter("idTipoCaptacao");
			form.setTipoCaptacao(idTipoCaptacao);
		}
		
		if ( httpServletRequest.getParameter("idFonteCaptacao") != null ) {
			String idFonteCaptacao = (String) httpServletRequest.getParameter("idFonteCaptacao");
			form.setFonteCaptacaoId(idFonteCaptacao);
		}
		
//		 pesquisa enter de FONTE DE CAPTACAO sem ter realizado pesquisa de TIPO DE CAPTACAO
		if ((form.getTipoCaptacao() == null || form.getTipoCaptacao().equals("")) 
				&& form.getFonteCaptacaoId() != null
				&& !form.getFonteCaptacaoId().equals("")) {

			FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();
			
			try {
				filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
						FiltroFonteCaptacao.ID, new Integer(
								form.getFonteCaptacaoId())));
			} catch (NumberFormatException ex) {
				throw new ActionServletException(
						"atencao.campo_texto.numero_obrigatorio", null,
						"Fonte de Captação");
			}
			
			filtroFonteCaptacao
					.setCampoOrderBy(FiltroFonteCaptacao.DESCRICAO);
			Collection colecaoFonteCaptacao = fachada.pesquisar(
					filtroFonteCaptacao, FonteCaptacao.class.getName());

			if (colecaoFonteCaptacao != null
					&& !colecaoFonteCaptacao.isEmpty()) {
				FonteCaptacao fonteCaptacao = (FonteCaptacao) Util.retonarObjetoDeColecao(colecaoFonteCaptacao);
				form.setFonteCaptacaoDescricao(fonteCaptacao.getDescricao());
				if (form.getDescricaoTipoCaptacao() != null && form.getTipoCaptacao() != null
						&& !form.getDescricaoTipoCaptacao().equals("TIPO DE CAPTACÃO INEXISTENTE")){
					httpServletRequest.setAttribute("tipoCaptacaoEncontrado", true);
				}
				httpServletRequest.setAttribute("fonteCaptacaoEncontrada", true);
			} else {
				form.setFonteCaptacaoId("");
				form.setFonteCaptacaoDescricao("FONTE DE CAPTACÃO INEXISTENTE");
				httpServletRequest.removeAttribute("fonteCaptacaoEncontrada");
			}

		}
		
		//pesquisa enter de tipo de captacao
		if (form.getTipoCaptacao() != null
				&& !form.getTipoCaptacao().equals("")) {

			FiltroTipoCaptacao filtroTipoCaptacao = new FiltroTipoCaptacao();
			
			try {
				filtroTipoCaptacao.adicionarParametro(new ParametroSimples(
						FiltroTipoCaptacao.ID, new Integer(
								form.getTipoCaptacao())));
			} catch (NumberFormatException ex) {
				throw new ActionServletException(
						"atencao.campo_texto.numero_obrigatorio", null,
						"Tipo de Captação");
			}
			
			filtroTipoCaptacao
					.setCampoOrderBy(FiltroFonteCaptacao.DESCRICAO);
			Collection colecaoTipoCaptacao = fachada.pesquisar(
					filtroTipoCaptacao, TipoCaptacao.class.getName());

			if (colecaoTipoCaptacao != null
					&& !colecaoTipoCaptacao.isEmpty()) {
				TipoCaptacao tipoCaptacao = (TipoCaptacao) Util.retonarObjetoDeColecao(colecaoTipoCaptacao);
				form.setDescricaoTipoCaptacao(tipoCaptacao.getDescricao());
				if (form.getFonteCaptacaoDescricao() != null && form.getFonteCaptacaoId() != null 
						&&	!form.getFonteCaptacaoDescricao().equals("FONTE DE CAPTACÃO INEXISTENTE")){
					httpServletRequest.setAttribute("fonteCaptacaoEncontrada", true);
				}
				httpServletRequest.setAttribute("tipoCaptacaoEncontrado", true);
				
			} else {
				form.setTipoCaptacao("");
				form.setDescricaoTipoCaptacao("TIPO DE CAPTACÃO INEXISTENTE");
				httpServletRequest.removeAttribute("tipoCaptacaoEncontrado");
			}

		}
		
		//Pesquisar Fonte de Captacao Com Tipo de Captacao
		if (form.getTipoCaptacao() != null
				&& !form.getTipoCaptacao().equals("")
				&& form.getFonteCaptacaoId() != null
				&& !form.getFonteCaptacaoId().equals("")) {

			FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();
			
			try {
				filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
						FiltroFonteCaptacao.ID_TIPO_CAPTACAO,form.getTipoCaptacao()));
				filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
						FiltroFonteCaptacao.ID, new Integer(
								form.getFonteCaptacaoId())));
			} catch (NumberFormatException ex) {
				throw new ActionServletException(
						"atencao.campo_texto.numero_obrigatorio", null,
						"Fonte de Captação");
			}
			
			filtroFonteCaptacao
					.setCampoOrderBy(FiltroFonteCaptacao.DESCRICAO);
			Collection colecaoFonteCaptacao = fachada.pesquisar(
					filtroFonteCaptacao, FonteCaptacao.class.getName());

			if (colecaoFonteCaptacao != null
					&& !colecaoFonteCaptacao.isEmpty()) {
				FonteCaptacao fonteCaptacao = (FonteCaptacao) Util.retonarObjetoDeColecao(colecaoFonteCaptacao);
				form.setFonteCaptacaoDescricao(fonteCaptacao.getDescricao());
				httpServletRequest.setAttribute("tipoCaptacaoEncontrado", true);
				httpServletRequest.setAttribute("fonteCaptacaoEncontrada", true);
			} else {
				form.setFonteCaptacaoId("");
				form.setFonteCaptacaoDescricao("FONTE DE CAPTACÃO INEXISTENTE");
				httpServletRequest.removeAttribute("fonteCaptacaoEncontrada");
			}

		}
		
	}
}
