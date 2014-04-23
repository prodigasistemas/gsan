package gcom.gui.portal;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe Responsável por exibir Canais de Atendimento
 * 
 * @author Erivan Sousa
 * @date 28/06/2011
 */
public class ExibirInformacoesPortalCompesaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ExibirInformacoesPortalCompesaActionForm exibirInformacoesPortalCompesaActionForm = (ExibirInformacoesPortalCompesaActionForm) actionForm;

		String retorno = "exibirInformacoesPortalCompesaAction";

		String method = httpServletRequest.getParameter("method");
		String modo = httpServletRequest.getParameter("modo");

		if (method == null) {
			method = "";
		}

		if (method.equalsIgnoreCase("tarifaSocial")) {
			retorno = "exibirInformacoesTarifaSocialPortalCompesaAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);

		} else if (method.equalsIgnoreCase("negociacaoDebitos")) {
			retorno = "exibirInformacoesNegociacaoDebitosPortalCompesaAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);

		} else if (method.equalsIgnoreCase("normas")) {
			retorno = "exibirNormasInstalacaoPortalCompesaAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);

		} else if (method.equalsIgnoreCase("calendarioAbastecimento")) {
			retorno = "exibirCalendarioAbastecimentoPortalCompesaAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);

		}
		
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		
		Collection colecaoSistemaParametro = this.getFachada().pesquisar(
				filtroSistemaParametro, SistemaParametro.class.getName());

		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro
				.iterator().next();
		
		this.montarForm(exibirInformacoesPortalCompesaActionForm, sistemaParametro);
		this.setarDownloadsLoja(sistemaParametro, httpServletRequest);
		
		if(modo != null && !modo.equals("")){			
			if(modo.equals("verLeiIndividualizacao")){
				this.retornaArquivo("leiNormaMedicao", httpServletResponse, sistemaParametro);
			}
			if(modo.equals("verNormaCO")){
				this.retornaArquivo("normaCO", httpServletResponse, sistemaParametro);
			}
			if(modo.equals("verNormaCM")){
				this.retornaArquivo("normaCM", httpServletResponse, sistemaParametro);
			}
		}

		return actionMapping.findForward(retorno);
	}
	
	private void retornaArquivo(String arquivo, HttpServletResponse httpServletResponse,
			SistemaParametro sistemaParametro){
		
		String mimeType = ConstantesSistema.CONTENT_TYPE_PDF;
		OutputStream out = null;
		byte[] file = null;
		
		if(arquivo.equalsIgnoreCase("leiNormaMedicao")){
			file = sistemaParametro.getArquivoLeiIndividualizacao();
		}
		if(arquivo.equalsIgnoreCase("normaCO")){
			file = sistemaParametro.getArquivoNormaCO();
		}
		if(arquivo.equalsIgnoreCase("normaCM")){
			file = sistemaParametro.getArquivoNormaCM();
		}
		
		
		try {
			httpServletResponse.setContentType(mimeType);
			out = httpServletResponse.getOutputStream();
			
			out.write(file);
			out.flush();
			out.close();
		} 
		catch (IOException e) {
			throw new ActionServletException("erro.sistema", e);
		}
		
	}
	
	//seta os dados do form
	private void montarForm(ExibirInformacoesPortalCompesaActionForm exibirInformacoesPortalCompesaActionForm, SistemaParametro sistemaParametro){
		
		//Descrissao Lei Individualização
		if(sistemaParametro.getDescricaoLeiIndividualizacao() != null &&
				!sistemaParametro.getDescricaoLeiIndividualizacao().equals("")){
			exibirInformacoesPortalCompesaActionForm.setDescrissaoLeiIndividualizacao(
					sistemaParametro.getDescricaoLeiIndividualizacao());
		}
		
		//Descrissao norma CM
		if(sistemaParametro.getDescricaoNormaCM() != null &&
				!sistemaParametro.getDescricaoNormaCM().equals("")){
			exibirInformacoesPortalCompesaActionForm.setDescrissaoNormaCM(
					sistemaParametro.getDescricaoNormaCM());
		}
		
		//Descrissao Norma CO
		if(sistemaParametro.getDescricaoNormaCO() != null &&
				!sistemaParametro.getDescricaoNormaCO().equals("")){
			exibirInformacoesPortalCompesaActionForm.setDescrissaoNormaCO(
					sistemaParametro.getDescricaoNormaCO());
		}
	}
	
	//Verifica a existencias dos arquivos no banco	
	private void setarDownloadsLoja(SistemaParametro sistemaParametro, HttpServletRequest httpServletRequest ){
			
		if(sistemaParametro.getArquivoLeiIndividualizacao() != null &&
				sistemaParametro.getArquivoLeiIndividualizacao().length != 0){
			httpServletRequest.setAttribute("arquivoLeiNormaMedicao", true);
		}else{
			httpServletRequest.removeAttribute("arquivoLeiNormaMedicao");
		}
		
		if(sistemaParametro.getArquivoNormaCM() != null &&
				sistemaParametro.getArquivoNormaCM().length != 0){
			httpServletRequest.setAttribute("arquivoNormaCM", true);
		}else{
			httpServletRequest.removeAttribute("arquivoNormaCM");
		}
		
		if(sistemaParametro.getArquivoNormaCO() != null &&
				sistemaParametro.getArquivoNormaCO().length != 0){
			httpServletRequest.setAttribute("arquivoNormaCO", true);
		}else{
			httpServletRequest.removeAttribute("arquivoNormaCO");
		}
	}

	
}
