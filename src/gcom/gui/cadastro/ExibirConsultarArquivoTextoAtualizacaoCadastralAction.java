package gcom.gui.cadastro;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.DadosLeiturista;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
public class ExibirConsultarArquivoTextoAtualizacaoCadastralAction extends GcomAction {

	/**
	 * 
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("consultarArquivoTextoAtualizacaoCadastral");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		ConsultarArquivoTextoAtualizacaoCadastralActionForm form = (ConsultarArquivoTextoAtualizacaoCadastralActionForm) actionForm;

		// Pesquisar Localidade
		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {
			pesquisarLocalidade(form, fachada, httpServletRequest);			
		}
		
		Collection colecaoLeiturista = new ArrayList();

		if (httpServletRequest.getParameter("menu") != null) {
		
			// Parte que passa as coleções da Empresa necessárias no jsp
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.setCampoOrderBy(FiltroEmpresa.ID);
			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
					Empresa.class.getName());

			if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
				sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Empresa");
			}
			
			sessao.removeAttribute("permissao");
			if (usuarioLogado.getEmpresa().getIndicadorEmpresaPrincipal().equals(
					new Short("1"))) {
				sessao.setAttribute("permissao", "1");
			} else {
				sessao.setAttribute("permissao", "2");
			}
			
			form.setIdEmpresa(""+ usuarioLogado.getEmpresa().getId());
			
			// Leiturista da Empresa
			if (form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1")
					&& !form.getIdEmpresa().equals("")) {

				FiltroLeiturista filtroLeiturista = new FiltroLeiturista(
						FiltroLeiturista.ID);
				filtroLeiturista.adicionarParametro(new ParametroSimples(
						FiltroLeiturista.EMPRESA_ID,form.getIdEmpresa()));
				filtroLeiturista.adicionarParametro(new ParametroSimples(
						FiltroLeiturista.INDICADOR_USO,ConstantesSistema.SIM));
				filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
				filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
				

				Collection colecao = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());

				if (colecao != null && !colecao.isEmpty()) {
					Iterator it = colecao.iterator();
					while (it.hasNext()) {
						Leiturista leitu = (Leiturista) it.next();
						DadosLeiturista dadosLeiu = null;
						if (leitu.getFuncionario() != null) {
							dadosLeiu = new DadosLeiturista(leitu.getId(), leitu
									.getFuncionario().getNome());
						} else {
							dadosLeiu = new DadosLeiturista(leitu.getId(), leitu
									.getCliente().getNome());
						}
						colecaoLeiturista.add(dadosLeiu);
					}
				}			
			}

			sessao.setAttribute("colecaoLeiturista", colecaoLeiturista);
		}
		
		return retorno;

	}
	
	private void pesquisarLocalidade(
			ConsultarArquivoTextoAtualizacaoCadastralActionForm form,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		// Recebe o valor do campo localidadeOrigemID do formulário.
		String localidadeID = (String) form.getIdLocalidade();		
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, localidadeID));
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		// Retorna localidade
		Collection colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		
		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
			// Localidade nao encontrada
			// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
			// formulário
			form.setIdLocalidade("");
			form.setNomeLocalidade("Localidade inexistente");
			
			httpServletRequest.setAttribute("corLocalidadeOrigem", "exception");
			httpServletRequest.setAttribute("nomeCampo","localidadeInicial");
			
		} else {
			Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
			form.setIdLocalidade(String.valueOf(objetoLocalidade.getId()));
			form.setNomeLocalidade(objetoLocalidade.getDescricao());
			
			httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
			httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialInicial");
			
			httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
		}

	}

}
