package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroFonteCaptacao;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.FonteCaptacao;
import gcom.operacional.SistemaAbastecimento;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * FILTRAR Sistema Abastecimento
 * 
 * @author Fernando Fontelles Filho
 * @date 29/10/2009
 */

public class FiltrarSistemaAbastecimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterSistemaAbastecimento");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarSistemaAbastecimentoActionForm form = (FiltrarSistemaAbastecimentoActionForm) actionForm;

		// Recupera todos os campos da página para ser colocada no filtro
		// posteriormente

		String codigo = form.getCodigo();
		String idFonteCaptacao = form.getIdFonteCaptacao();
		String descricao = form.getDescricao();
		String descricaoAbreviada = form.getDescricaoAbreviada();
		String indicadorUso = form.getIndicadorUso();
		String tipoPesquisa = form.getTipoPesquisa();
		String tipoCaptacao = form.getTipoCaptacao();

		// Indicador Atualizar
		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {

			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {
			
				sessao.removeAttribute("indicadorAtualizar");
			
		}

		boolean peloMenosUmParametroInformado = false;
		FiltroSistemaAbastecimento filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();
		
		// Código Sistema Abastecimento
		if (codigo != null && !codigo.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroSistemaAbastecimento.adicionarParametro(new ParametroSimples(
					FiltroSistemaAbastecimento.ID, codigo));
		}

		//Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroSistemaAbastecimento
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroSistemaAbastecimento.DESCRICAO, descricao));
			} else {

				filtroSistemaAbastecimento.adicionarParametro(new ComparacaoTexto(
						FiltroSistemaAbastecimento.DESCRICAO, descricao));
			}
		}

		// Descrição Abreviada
		if (descricaoAbreviada != null && !descricaoAbreviada.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroSistemaAbastecimento.adicionarParametro(new ParametroSimples(
					FiltroSistemaAbastecimento.DESCRICAO_ABREVIADA, descricaoAbreviada));
					
		}
		


		//Código Tipo Captacao
		if (tipoCaptacao != null && !tipoCaptacao.trim().equals("")){
			
			peloMenosUmParametroInformado = true;
			FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();
			filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
					FiltroFonteCaptacao.ID_TIPO_CAPTACAO,tipoCaptacao));
			
			if (idFonteCaptacao != null && !idFonteCaptacao.trim().equals("")){
				filtroFonteCaptacao.adicionarParametro(new ParametroSimples
						(FiltroFonteCaptacao.ID, idFonteCaptacao));
			}
			
			Collection colecaoTipo = fachada.pesquisar(filtroFonteCaptacao, FonteCaptacao.class.getName());
			FonteCaptacao fonteCaptacao = (FonteCaptacao)colecaoTipo.iterator().next();
			filtroSistemaAbastecimento.adicionarParametro(new ParametroSimples(
					FiltroSistemaAbastecimento.ID_FONTE_CAPTACAO, fonteCaptacao.getId()));
			
		}

		// Fonte Captacao
		
		if ((tipoCaptacao == null || tipoCaptacao.trim().equals("")) &&
				idFonteCaptacao != null && !idFonteCaptacao.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();
			filtroFonteCaptacao.adicionarParametro(new ParametroSimples
					(FiltroFonteCaptacao.ID, idFonteCaptacao));
			Collection colecaoFonte = fachada.pesquisar(filtroFonteCaptacao, FonteCaptacao.class.getName());
			FonteCaptacao fonteCaptacao = (FonteCaptacao)colecaoFonte.iterator().next();
			filtroSistemaAbastecimento.adicionarParametro(new ParametroSimples(
						FiltroSistemaAbastecimento.ID_FONTE_CAPTACAO, fonteCaptacao.getId()));
		}
		
		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            if (indicadorUso.equalsIgnoreCase(String
                    .valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
                filtroSistemaAbastecimento.adicionarParametro(new ParametroSimples(
                		FiltroSistemaAbastecimento.INDICADOR_USO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));
            } else {
            	filtroSistemaAbastecimento.adicionarParametro(new ParametroSimples(
            			FiltroSistemaAbastecimento.INDICADOR_USO,
                        ConstantesSistema.INDICADOR_USO_DESATIVO));
            }
        }

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroSistemaAbastecimento.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
		
		filtroSistemaAbastecimento.setCampoOrderBy(FiltroSistemaAbastecimento.ID);

		Collection<SistemaAbastecimento> colecaoSistemaAbastecimento = fachada.pesquisar(
				filtroSistemaAbastecimento, SistemaAbastecimento.class.getName());

		if (colecaoSistemaAbastecimento == null || colecaoSistemaAbastecimento.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,
					"Sistema de Abastecimento");
		} else {
			httpServletRequest.setAttribute("colecaoSistemaAbastecimento",
					colecaoSistemaAbastecimento);
			SistemaAbastecimento sistemaAbastecimento = new SistemaAbastecimento();
			sistemaAbastecimento = (SistemaAbastecimento) Util
					.retonarObjetoDeColecao(colecaoSistemaAbastecimento);
			String idRegistroAtualizacao = sistemaAbastecimento.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroSistemaAbastecimento", filtroSistemaAbastecimento);

		httpServletRequest.setAttribute("filtroSistemaAbastecimento", filtroSistemaAbastecimento);

		return retorno;

	}
}
