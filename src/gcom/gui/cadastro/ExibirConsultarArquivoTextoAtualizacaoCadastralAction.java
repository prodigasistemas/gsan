package gcom.gui.cadastro;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
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

public class ExibirConsultarArquivoTextoAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("consultarArquivoTextoAtualizacaoCadastral");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		ConsultarArquivoTextoAtualizacaoCadastralActionForm form = (ConsultarArquivoTextoAtualizacaoCadastralActionForm) actionForm;

		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("") && (objetoConsulta.trim().equals("1"))) {
			pesquisarLocalidade(form, httpServletRequest);			
		}
		
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("") && (objetoConsulta.trim().equals("2"))) {
			pesquisarSetorComercial(form, httpServletRequest);			
		}
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		
		if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Empresa");
		}
		
		sessao.removeAttribute("permissao");
		if (usuarioLogado.getEmpresa().getIndicadorEmpresaPrincipal().equals(
				new Short("1"))) {
			sessao.setAttribute("permissao", "1");
		} else {
			sessao.setAttribute("permissao", "2");
		}
		
		if (httpServletRequest.getParameter("menu") != null) {
			form.setIdEmpresa(""+ usuarioLogado.getEmpresa().getId());
		}
		
		Collection colecaoLeiturista = new ArrayList();
		
		if (form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1") && !form.getIdEmpresa().equals("")) {
			
			FiltroLeiturista filtroLeiturista = new FiltroLeiturista(FiltroLeiturista.ID);
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.EMPRESA_ID,form.getIdEmpresa()));
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.INDICADOR_USO,ConstantesSistema.SIM));
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
			
			
			Collection colecao = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());
			
			if (colecao != null && !colecao.isEmpty()) {
				
				Iterator iterator = colecao.iterator();
				
				while (iterator.hasNext()) {
					
					Leiturista leiturista = (Leiturista) iterator.next();
					DadosLeiturista dadosLeiturista = null;
					
					if (leiturista.getFuncionario() != null) {
						dadosLeiturista = new DadosLeiturista(leiturista.getId(),
								leiturista.getFuncionario().getNome());
					} else {
						dadosLeiturista = new DadosLeiturista(leiturista.getId(),
								leiturista.getCliente().getNome());
					}
					
					colecaoLeiturista.add(dadosLeiturista);
				}
			}			
		}
		
		sessao.setAttribute("colecaoLeiturista", colecaoLeiturista);
		
		return retorno;

	}
	
	private void pesquisarLocalidade(ConsultarArquivoTextoAtualizacaoCadastralActionForm form, HttpServletRequest request) {
		FiltroLocalidade filtro = new FiltroLocalidade();
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, (String) form.getIdLocalidade()));
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection pesquisa = Fachada.getInstancia().pesquisar(filtro, Localidade.class.getName());
		
		if (pesquisa == null || pesquisa.isEmpty()) {
			form.setIdLocalidade("");
			form.setNomeLocalidade("Localidade inexistente");
			
			request.setAttribute("corLocalidadeOrigem", "exception");
			request.setAttribute("nomeCampo","localidadeInicial");
			
		} else {
			Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(pesquisa);
			form.setIdLocalidade(String.valueOf(objetoLocalidade.getId()));
			form.setNomeLocalidade(objetoLocalidade.getDescricao());
			
			request.setAttribute("corLocalidadeOrigem", "valor");
			request.setAttribute("nomeCampo","nomeLocalidadeInicial");
		}
	}
	
	private void pesquisarSetorComercial(ConsultarArquivoTextoAtualizacaoCadastralActionForm form, HttpServletRequest request) {
		FiltroSetorComercial filtro = new FiltroSetorComercial();
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercial()));
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.LOCALIDADE, form.getIdLocalidade()));
		
		Collection pesquisa = Fachada.getInstancia().pesquisar(filtro, SetorComercial.class.getName());
		
		if (pesquisa == null || pesquisa.isEmpty()) {
			form.setCodigoSetorComercial("");
			form.setNomeSetorComercial("Setor inexistente");
			
			request.setAttribute("corSetorComercialOrigem", "exception");
			request.setAttribute("nomeCampo","nomeSetorComercialInicial");
		} else {
			SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
			form.setCodigoSetorComercial(String.valueOf(objetoSetorComercial.getCodigo()));
			form.setNomeSetorComercial(objetoSetorComercial.getDescricao());
			
			request.setAttribute("corSetorComercialOrigem", "valor");
			request.setAttribute("nomeCampo","nomeSetorComercialInicial");
		}
	}
}
