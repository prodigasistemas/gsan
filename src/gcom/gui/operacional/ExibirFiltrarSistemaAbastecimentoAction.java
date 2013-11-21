package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroFonteCaptacao;
import gcom.operacional.FiltroTipoCaptacao;
import gcom.operacional.FonteCaptacao;
import gcom.operacional.TipoCaptacao;
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
 * 
 * @author Fernando Fontelles Filho
 * @date 30/10/2009
 */



public class ExibirFiltrarSistemaAbastecimentoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("filtrarSistemaAbastecimento");

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarSistemaAbastecimentoActionForm form = (FiltrarSistemaAbastecimentoActionForm) actionForm;
		
		//	Código para checar ou não o ATUALIZAR e Passar o valor do Indicador de Uso como "TODOS"
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}
		
		//pesquisa os dados do enter
		pesquisarEnter(form, httpServletRequest,fachada);
		
		return retorno;
	}
	
	private void pesquisarEnter(
			FiltrarSistemaAbastecimentoActionForm form,
			HttpServletRequest httpServletRequest, Fachada fachada) {

		// pesquisa enter de FONTE DE CAPTACAO sem ter realizado pesquisa de TIPO DE CAPTACAO
		if ((form.getTipoCaptacao() == null || form.getTipoCaptacao().equals("")) 
				&& form.getIdFonteCaptacao() != null
				&& !form.getIdFonteCaptacao().equals("")) {

			FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();
			
			try {
				filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
						FiltroFonteCaptacao.ID, new Integer(
								form.getIdFonteCaptacao())));
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
				form.setDescricaoFonteCaptacao(fonteCaptacao.getDescricao());
				if (form.getDescricaoTipoCaptacao() != null && form.getTipoCaptacao() != null
						&& !form.getDescricaoTipoCaptacao().equals("TIPO DE CAPTACÃO INEXISTENTE")){
					httpServletRequest.setAttribute("tipoCaptacaoEncontrado", true);
				}
				httpServletRequest.setAttribute("fonteCaptacaoEncontrada", true);
			} else {
				form.setIdFonteCaptacao("");
				form.setDescricaoFonteCaptacao("FONTE DE CAPTACÃO INEXISTENTE");
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
				if (form.getDescricaoFonteCaptacao() != null && form.getIdFonteCaptacao() != null 
						&&	!form.getDescricaoFonteCaptacao().equals("FONTE DE CAPTACÃO INEXISTENTE")){
					httpServletRequest.setAttribute("fonteCaptacaoEncontrada", true);
				}
				httpServletRequest.setAttribute("tipoCaptacaoEncontrado", true);
			} else {
				form.setTipoCaptacao("");
				form.setDescricaoTipoCaptacao("TIPO DE CAPTACÃO INEXISTENTE");
				if (form.getDescricaoFonteCaptacao() != null && form.getIdFonteCaptacao() != null 
						&&	!form.getDescricaoFonteCaptacao().equals("FONTE DE CAPTACÃO INEXISTENTE")){
					httpServletRequest.setAttribute("fonteCaptacaoEncontrada", true);
				}
				
			}

		}
		
		//Pesquisar Fonte de Captacao Com Tipo de Captacao
		if (form.getTipoCaptacao() != null
				&& !form.getTipoCaptacao().equals("")
				&& form.getIdFonteCaptacao() != null
				&& !form.getIdFonteCaptacao().equals("")
				&& (form.getDescricaoFonteCaptacao() == null || form.getDescricaoFonteCaptacao().equals(""))) {

			FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();
			
			try {
				filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
						FiltroFonteCaptacao.ID_TIPO_CAPTACAO,form.getTipoCaptacao()));
				filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
						FiltroFonteCaptacao.ID, new Integer(
								form.getIdFonteCaptacao())));
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
				form.setDescricaoFonteCaptacao(fonteCaptacao.getDescricao());
				httpServletRequest.setAttribute("tipoCaptacaoEncontrado", true);
				httpServletRequest.setAttribute("fonteCaptacaoEncontrada", true);
			} else {
				form.setIdFonteCaptacao("");
				form.setDescricaoFonteCaptacao("FONTE DE CAPTACÃO INEXISTENTE");
			}

		}
		
	}
	
}



