package gcom.gui.cobranca;

import gcom.fachada.Fachada;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0896] Gerar Arquivo Texto das Contas em Cobrança por Empresa
 * 
 * @author Rômulo Aurélio
 * @since 29/10/2008
 */
public class GerarArquivoTextoContasCobrancaEmpresaAction extends ExibidorProcessamentoTarefaRelatorio  {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException  {

		
		ActionForward retorno = actionMapping.findForward("telaSucesso");



		GerarArquivoTextoContasCobrancaEmpresaActionForm form = (GerarArquivoTextoContasCobrancaEmpresaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		Integer[] idsRegistros = form.getIdRegistros() ;
		
		Collection colecaoIdsRegistro = new ArrayList();
		//Converte a String[] de id em Collection
		for (int i = 0; i < idsRegistros.length; i++) {
			colecaoIdsRegistro.add(idsRegistros[i]);
		}
		
		String idEmpresa = form.getIdEmpresa();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		
		fachada.inserirProcessoIniciadoContasCobranca(colecaoIdsRegistro,new Integer(idEmpresa),usuarioLogado);
		
		//montando página de sucesso
		montarPaginaSucesso(httpServletRequest,
			"Arquivo Texto Contas Cobrança por Empresa Enviado para Processamento", 
			"Voltar",
			"exibirGerarArquivoTextoContasCobrancaEmpresaAction.do");

	
		return retorno;
	}


}
