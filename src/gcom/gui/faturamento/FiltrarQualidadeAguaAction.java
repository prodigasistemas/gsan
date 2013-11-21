package gcom.gui.faturamento;

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroQualidadeAgua;
import gcom.faturamento.QualidadeAgua;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroFonteCaptacao;
import gcom.operacional.FonteCaptacao;
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
 * FILTRAR Qualidade Agua
 * 
 * @author Flávio
 * @date 02/10/2007
 */

public class FiltrarQualidadeAguaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterQualidadeAgua");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarQualidadeAguaActionForm form = (FiltrarQualidadeAguaActionForm) actionForm;

		// Recupera todos os campos da página para ser colocada no filtro
		// posteriormente

		String referencia = form.getReferencia();
		String idLocalidade = form.getIdLocalidade();
		String codigoSetor = form.getCodigoSetor();
		String idFonteCaptacao = form.getFonteCaptacao();
		String sistemaAbastecimento = form.getSistemaAbastecimento();

		// Indicador Atualizar
		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {

			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {
			
				sessao.removeAttribute("indicadorAtualizar");
			
		}

		boolean peloMenosUmParametroInformado = false;
		FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();

		// Código do Arrecadador
		if (referencia != null && !referencia.trim().equals("")) {
			peloMenosUmParametroInformado = true;
				filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
						FiltroQualidadeAgua.ANO_MES_REFERENCIA, Util.formatarMesAnoComBarraParaAnoMes(referencia)));
		}

		// Cliente
		if (idLocalidade != null && !idLocalidade.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
					FiltroQualidadeAgua.LOCALIDADE_ID, idLocalidade));
		}

		// Setor Comercial
		if (codigoSetor != null && !codigoSetor.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetor));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidade()));
			Collection colecaoSetor = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			SetorComercial setorComercial = (SetorComercial)colecaoSetor.iterator().next();
			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
						FiltroQualidadeAgua.SETOR_COMERCIAL_ID, setorComercial.getId()));
					
		}

		// Fonte Captacao
		if (idFonteCaptacao != null && !idFonteCaptacao.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();
			filtroFonteCaptacao.adicionarParametro(new ParametroSimples
					(FiltroFonteCaptacao.ID, idFonteCaptacao));
			Collection colecaoFonte = fachada.pesquisar(filtroFonteCaptacao, FonteCaptacao.class.getName());
			FonteCaptacao fonteCaptacao = (FonteCaptacao)colecaoFonte.iterator().next();
			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
					FiltroQualidadeAgua.ID_FONTE_CAPTACAO, fonteCaptacao.getId()));
		}
		
		// Sistema de Abastecimento
		if (sistemaAbastecimento != null && !sistemaAbastecimento.equals("-1")){
			
			peloMenosUmParametroInformado = true;
			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(FiltroQualidadeAgua.SISTEMA_ABASTECIMENTO, sistemaAbastecimento));
			
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("setorComercial.municipio");
		filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
		filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("sistemaAbastecimento");
		
		filtroQualidadeAgua.setCampoOrderBy(FiltroQualidadeAgua.LOCALIDADE_ID);

		Collection<QualidadeAgua> colecaoQualidadeAgua = fachada.pesquisar(
				filtroQualidadeAgua, QualidadeAgua.class.getName());

		if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Qualidade da Água");
		} else {
			httpServletRequest.setAttribute("colecaoQualidadeAgua",
					colecaoQualidadeAgua);
			QualidadeAgua qualidadeAgua = new QualidadeAgua();
			qualidadeAgua = (QualidadeAgua) Util
					.retonarObjetoDeColecao(colecaoQualidadeAgua);
			String idRegistroAtualizacao = qualidadeAgua.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroQualidadeAgua", filtroQualidadeAgua);

		httpServletRequest.setAttribute("filtroQualidadeAgua", filtroQualidadeAgua);

		return retorno;

	}
}
