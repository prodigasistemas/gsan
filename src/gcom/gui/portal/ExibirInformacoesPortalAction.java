package gcom.gui.portal;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInformacoesPortalAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		ExibirInformacoesPortalActionForm form = (ExibirInformacoesPortalActionForm) actionForm;

		String retorno = "exibirInformacoesPortalAction";

		String method = request.getParameter("method");
		String modo = request.getParameter("modo");

		if (method == null) {
			method = "";
		}

		if (method.equalsIgnoreCase("tarifaSocial")) {
			retorno = "exibirInformacoesTarifaSocialPortalCompesaAction";
			request.setAttribute("voltarInformacoes", true);

		} else if (method.equalsIgnoreCase("negociacaoDebitos")) {
			retorno = "exibirInformacoesNegociacaoDebitosPortalCompesaAction";
			request.setAttribute("voltarInformacoes", true);

		} else if (method.equalsIgnoreCase("normas")) {
			retorno = "exibirNormasInstalacaoPortalCompesaAction";
			request.setAttribute("voltarInformacoes", true);

		} else if (method.equalsIgnoreCase("calendarioAbastecimento")) {
			retorno = "exibirCalendarioAbastecimentoPortalCompesaAction";
			request.setAttribute("voltarInformacoes", true);

		}

		SistemaParametro sistemaParametro = getFachada().pesquisarParametrosDoSistema();

		montarForm(form, sistemaParametro);
		setarDownloadsLoja(sistemaParametro, request);

		if (modo != null && !modo.equals("")) {
			if (modo.equals("verLeiIndividualizacao")) {
				retornaArquivo("leiNormaMedicao", response, sistemaParametro);
			}
			if (modo.equals("verNormaCO")) {
				retornaArquivo("normaCO", response, sistemaParametro);
			}
			if (modo.equals("verNormaCM")) {
				retornaArquivo("normaCM", response, sistemaParametro);
			}
		}

		return actionMapping.findForward(retorno);
	}

	private void retornaArquivo(String arquivo, HttpServletResponse httpServletResponse, SistemaParametro sistemaParametro) {

		String mimeType = ConstantesSistema.CONTENT_TYPE_PDF;
		OutputStream out = null;
		byte[] file = null;

		if (arquivo.equalsIgnoreCase("leiNormaMedicao")) {
			file = sistemaParametro.getArquivoLeiIndividualizacao();
		}
		if (arquivo.equalsIgnoreCase("normaCO")) {
			file = sistemaParametro.getArquivoNormaCO();
		}
		if (arquivo.equalsIgnoreCase("normaCM")) {
			file = sistemaParametro.getArquivoNormaCM();
		}

		try {
			httpServletResponse.setContentType(mimeType);
			out = httpServletResponse.getOutputStream();

			out.write(file);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new ActionServletException("erro.sistema", e);
		}

	}

	private void montarForm(ExibirInformacoesPortalActionForm form, SistemaParametro sistemaParametro) {
		if (sistemaParametro.getDescricaoLeiIndividualizacao() != null && !sistemaParametro.getDescricaoLeiIndividualizacao().equals("")) {
			form.setDescrissaoLeiIndividualizacao(sistemaParametro.getDescricaoLeiIndividualizacao());
		}

		if (sistemaParametro.getDescricaoNormaCM() != null && !sistemaParametro.getDescricaoNormaCM().equals("")) {
			form.setDescrissaoNormaCM(sistemaParametro.getDescricaoNormaCM());
		}

		if (sistemaParametro.getDescricaoNormaCO() != null && !sistemaParametro.getDescricaoNormaCO().equals("")) {
			form.setDescrissaoNormaCO(sistemaParametro.getDescricaoNormaCO());
		}
	}

	private void setarDownloadsLoja(SistemaParametro sistemaParametro, HttpServletRequest httpServletRequest) {
		if (sistemaParametro.getArquivoLeiIndividualizacao() != null && sistemaParametro.getArquivoLeiIndividualizacao().length != 0) {
			httpServletRequest.setAttribute("arquivoLeiNormaMedicao", true);
		} else {
			httpServletRequest.removeAttribute("arquivoLeiNormaMedicao");
		}

		if (sistemaParametro.getArquivoNormaCM() != null && sistemaParametro.getArquivoNormaCM().length != 0) {
			httpServletRequest.setAttribute("arquivoNormaCM", true);
		} else {
			httpServletRequest.removeAttribute("arquivoNormaCM");
		}

		if (sistemaParametro.getArquivoNormaCO() != null && sistemaParametro.getArquivoNormaCO().length != 0) {
			httpServletRequest.setAttribute("arquivoNormaCO", true);
		} else {
			httpServletRequest.removeAttribute("arquivoNormaCO");
		}
	}
}
