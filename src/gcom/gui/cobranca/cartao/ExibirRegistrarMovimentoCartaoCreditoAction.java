package gcom.gui.cobranca.cartao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirRegistrarMovimentoCartaoCreditoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("registrarMovimentoCartaoCredito");
		
		Fachada fachada = Fachada.getInstancia();

		String idArrecadador = null;

		if (httpServletRequest.getParameter("pesquisarArrecadador") != null) {

			try {

				DiskFileUpload upload = new DiskFileUpload();

				// Parse the request
				List items = upload.parseRequest(httpServletRequest);

				if (items != null) {
					FileItem item = null;

					// pega uma lista de itens do form
					Iterator iter = items.iterator();
					while (iter.hasNext()) {

						item = (FileItem) iter.next();

						if (item.getFieldName().equals("idArrecadador")) {
							idArrecadador = item.getString();
						}
					}
				}
			} 
			catch (FileUploadException e) {
				throw new ActionServletException("erro.sistema", e);
			}

			// Verifica se o código foi digitado
			if (idArrecadador != null && !idArrecadador.trim().equals("")
				&& Integer.parseInt(idArrecadador) > 0) {

				FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
				
				filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");

				filtroArrecadador.adicionarParametro(new ParametroSimples(
				FiltroArrecadador.ID, Integer.valueOf(idArrecadador)));

				Collection arrecadadorEncontrado = this.getFachada().pesquisar(
				filtroArrecadador, Arrecadador.class.getName());

				if (arrecadadorEncontrado != null && !arrecadadorEncontrado.isEmpty()) {

					//[FS0002 – Verificar arrecadação forma cartão crédito]
					fachada.verificarArrecadacaoFormaCartaoCredito(Integer.valueOf(idArrecadador));
					
					//ARRECADADOR ENCONTRADO
					httpServletRequest.setAttribute("parametroidArrecadador",
					"" + ((Arrecadador) ((List) arrecadadorEncontrado)
					.get(0)).getCodigoAgente());

					httpServletRequest.setAttribute("parametroNomeArrecadador",
					"" + ((Arrecadador) ((List) arrecadadorEncontrado).get(0)).getCliente()
					.getNome());

					httpServletRequest.setAttribute("idArrecadadorNaoEncontrado", "true");

				} 
				else {

					httpServletRequest.setAttribute("parametroNomeArrecadador", "");
					httpServletRequest.setAttribute("parametroNomeArrecadador", "ARRECADADOR INEXISTENTE");

					httpServletRequest.setAttribute("idArrecadadorNaoEncontrado", "exception");
				}
			}
		}

		
		return retorno;
	}
}
