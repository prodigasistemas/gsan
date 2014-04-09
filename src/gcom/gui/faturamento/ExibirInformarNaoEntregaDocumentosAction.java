package gcom.gui.faturamento;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.cobranca.FiltroMotivoNaoEntregaDocumento;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.MotivoNaoEntregaDocumento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0559]Informar Nao Entrega de Documentos
 * 
 * @author Thiago Tenório
 * @date 29/03/2007
 */

public class ExibirInformarNaoEntregaDocumentosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("informarNaoEntregaDocumentos");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		InformarNaoEntregaDocumentosActionForm form = (InformarNaoEntregaDocumentosActionForm) actionForm;

		String limparForm = httpServletRequest.getParameter("limparCampos");
		
		// Verificar Existência do Responsável pela Entrega
		if ((form.getIdResponsavelEntrega() != null && !form
				.getIdResponsavelEntrega().equals(""))) {

			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, form.getIdResponsavelEntrega()));

			Collection colecaoResponsavelEntrega = fachada.pesquisar(
					filtroCliente, Cliente.class.getName());

			if (colecaoResponsavelEntrega != null
					&& !colecaoResponsavelEntrega.isEmpty()) {

				Cliente cliente = (Cliente) colecaoResponsavelEntrega
						.iterator().next();
				form.setDescricaoResponsavelEntrega(cliente.getNome());

			} else {
				httpServletRequest.setAttribute("clienteEncontrado",
						"exception");
				form.setIdResponsavelEntrega("");
				form.setDescricaoResponsavelEntrega("RESPONSAVEL PELA ENTREGA INEXISTENTE");
			}

		}

		// Passando a coleção do Motivo da Não Entrega para o JSP
		FiltroMotivoNaoEntregaDocumento filtroMotivoNaoEntregaDocumento = new FiltroMotivoNaoEntregaDocumento();

		filtroMotivoNaoEntregaDocumento.setCampoOrderBy(FiltroMotivoNaoEntregaDocumento.MOTIVO);

		filtroMotivoNaoEntregaDocumento.adicionarParametro(new ParametroSimples(
				FiltroMotivoNaoEntregaDocumento.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		// Retorna o Tipo do Documento
		Collection colecaoMotivoNaoEntrega = fachada.pesquisar(filtroMotivoNaoEntregaDocumento,
				MotivoNaoEntregaDocumento.class.getName());

		if (colecaoMotivoNaoEntrega == null || colecaoMotivoNaoEntrega.isEmpty()) {
			// Nenhum registro na tabela Documento_Tipo foi encontrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhum_registro_tabela", null,
					"Motivo da Não Entrega");
		} else {
			sessao.setAttribute("colecaoMotivoNaoEntrega", colecaoMotivoNaoEntrega);
		}
		
		// Passando a coleção do Tipo do Documento para o JSP
		FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();

		filtroDocumentoTipo.setCampoOrderBy(FiltroDocumentoTipo.DESCRICAO);

		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.INDICADOR_PAGAVEL,
				ConstantesSistema.SIM));
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		// Retorna o Tipo do Documento
		Collection colecaoDocumentoTipo = fachada.pesquisar(filtroDocumentoTipo,
				DocumentoTipo.class.getName());

		if (colecaoDocumentoTipo == null || colecaoDocumentoTipo.isEmpty()) {
			// Nenhum registro na tabela Documento_Tipo foi encontrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhum_registro_tabela", null,
					"Tipo do Documento");
		} else {
			sessao.setAttribute("colecaoTipoDocumento", colecaoDocumentoTipo);
		}
		
		// DESFAZER
		if ((limparForm != null && !limparForm.trim().equalsIgnoreCase(""))
				|| (httpServletRequest.getParameter("desfazer") != null && httpServletRequest
						.getParameter("desfazer").equalsIgnoreCase("S"))) {
			// -------------- bt DESFAZER ---------------

			// Limpando o formulario e setando alguns valores aos objetos
			form.setTipoDocumento("1");
			form.setMesAnoConta("");
			
			form.setMatriculaOuNumeroDocumento1("");
			form.setMatriculaOuNumeroDocumento2("");
			form.setMatriculaOuNumeroDocumento3("");
			form.setMatriculaOuNumeroDocumento4("");
			form.setMatriculaOuNumeroDocumento5("");
			form.setMatriculaOuNumeroDocumento6("");
			form.setMatriculaOuNumeroDocumento7("");
			form.setMatriculaOuNumeroDocumento8("");
			form.setMatriculaOuNumeroDocumento9("");
			form.setMatriculaOuNumeroDocumento10("");
			form.setMatriculaOuNumeroDocumento11("");
			form.setMatriculaOuNumeroDocumento12("");
			form.setMatriculaOuNumeroDocumento13("");
			form.setMatriculaOuNumeroDocumento14("");
			form.setMatriculaOuNumeroDocumento15("");
			form.setMatriculaOuNumeroDocumento16("");
			form.setMatriculaOuNumeroDocumento17("");
			form.setMatriculaOuNumeroDocumento18("");
			form.setMatriculaOuNumeroDocumento19("");
			form.setMatriculaOuNumeroDocumento20("");
			// Pausa para descansar os dedos
			// É muito form ne?
			// Me mandaram fazer assim oq eu posso fazer?
			form.setQtTentativas1("1");
			form.setQtTentativas2("1");
			form.setQtTentativas3("1");
			form.setQtTentativas3("1");
			form.setQtTentativas4("1");
			form.setQtTentativas5("1");
			form.setQtTentativas6("1");
			form.setQtTentativas7("1");
			form.setQtTentativas8("1");
			form.setQtTentativas9("1");
			form.setQtTentativas10("1");
			form.setQtTentativas11("1");
			form.setQtTentativas12("1");
			form.setQtTentativas13("1");
			form.setQtTentativas14("1");
			form.setQtTentativas15("1");
			form.setQtTentativas16("1");
			form.setQtTentativas17("1");
			form.setQtTentativas18("1");
			form.setQtTentativas19("1");
			form.setQtTentativas20("1");

			form.setMotivo("");

		}

		 if (httpServletRequest.getParameter("menu") != null
				&& !httpServletRequest.getParameter("menu").equals("")) {
			form.setTipoDocumento("1");
			form.setQtTentativas1("1");
			form.setQtTentativas2("1");
			form.setQtTentativas3("1");
			form.setQtTentativas4("1");
			form.setQtTentativas5("1");
			form.setQtTentativas6("1");
			form.setQtTentativas7("1");
			form.setQtTentativas8("1");
			form.setQtTentativas9("1");
			form.setQtTentativas10("1");
			form.setQtTentativas11("1");
			form.setQtTentativas12("1");
			form.setQtTentativas13("1");
			form.setQtTentativas14("1");
			form.setQtTentativas15("1");
			form.setQtTentativas16("1");
			form.setQtTentativas17("1");
			form.setQtTentativas18("1");
			form.setQtTentativas19("1");
			form.setQtTentativas20("1");
		}
		
		form.setMatriculaOuNumeroDocumento1("");
		form.setMatriculaOuNumeroDocumento2("");
		form.setMatriculaOuNumeroDocumento3("");
		form.setMatriculaOuNumeroDocumento4("");
		form.setMatriculaOuNumeroDocumento5("");
		form.setMatriculaOuNumeroDocumento6("");
		form.setMatriculaOuNumeroDocumento7("");
		form.setMatriculaOuNumeroDocumento8("");
		form.setMatriculaOuNumeroDocumento9("");
		form.setMatriculaOuNumeroDocumento10("");
		form.setMatriculaOuNumeroDocumento11("");
		form.setMatriculaOuNumeroDocumento12("");
		form.setMatriculaOuNumeroDocumento13("");
		form.setMatriculaOuNumeroDocumento14("");
		form.setMatriculaOuNumeroDocumento15("");
		form.setMatriculaOuNumeroDocumento16("");
		form.setMatriculaOuNumeroDocumento17("");
		form.setMatriculaOuNumeroDocumento18("");
		form.setMatriculaOuNumeroDocumento19("");
		form.setMatriculaOuNumeroDocumento20("");
		 
		if (form.getTipoDocumento() != null && form.getTipoDocumento().equals(DocumentoTipo.CONTA.toString())) {
			httpServletRequest.setAttribute("exibirTabela", true);
			form.setTipoCampo(InformarNaoEntregaDocumentosActionForm.MATRICULA);
		} else {
			form.setTipoCampo(InformarNaoEntregaDocumentosActionForm.NUMERO_DOCUMENTO);
		}
		
		return retorno;
	}
}
