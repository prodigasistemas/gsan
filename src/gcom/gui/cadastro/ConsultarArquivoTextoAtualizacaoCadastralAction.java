package gcom.gui.cadastro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Consultar Arquivo Texto da Atualização Cadastral
 * 
 * @author Ana Maria 
 * @date 02/03/2009
 */
public class ConsultarArquivoTextoAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("consultarArquivoTextoAtualizacaoCadastral");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		ConsultarArquivoTextoAtualizacaoCadastralActionForm form = (ConsultarArquivoTextoAtualizacaoCadastralActionForm) actionForm;
		
		String empresaID = form.getIdEmpresa();
		
		String idLocalidade = form.getIdLocalidade();

		String idSituacaoTransmissao = form.getIdSituacaoTransmissao();

		String leiturista = form.getLeituristaID();

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		sessao.removeAttribute("permissao");
		if (usuarioLogado.getEmpresa().getIndicadorEmpresaPrincipal().equals(
				new Short("1"))) {
			sessao.setAttribute("permissao", "1");
		} else {
			sessao.setAttribute("permissao", "2");
		}

		Collection colecaoArquivoTextoAtualizacaoCadastral = fachada.pesquisarArquivoTextoAtualizacaoCadastro(empresaID, idLocalidade, leiturista, idSituacaoTransmissao);
		
		if (colecaoArquivoTextoAtualizacaoCadastral == null || colecaoArquivoTextoAtualizacaoCadastral.isEmpty()) {
			throw new ActionServletException(
					"atencao.nenhum_arquivo_txt_atualizacao_cadastral");

		}

		sessao.setAttribute("colecaoArquivoTextoAtualizacaoCadastral",colecaoArquivoTextoAtualizacaoCadastral);

		return retorno;
	}


}
