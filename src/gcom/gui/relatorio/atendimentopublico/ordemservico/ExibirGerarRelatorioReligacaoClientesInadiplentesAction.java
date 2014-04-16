package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1120] Gerar Relatório de religação de clientes inadimplentes
 * 
 * @author Hugo Leonardo
 *
 * @date 24/01/2011
 */

public class ExibirGerarRelatorioReligacaoClientesInadiplentesAction extends GcomAction {
	
	private String localidadeID = null;
	private String setorComercialCD = null;
	private Collection colecaoPesquisa = null;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
			.findForward("exibirGerarRelatorioReligacaoClientesInadiplentes");
		
		GerarRelatorioReligacaoClientesInadiplentesForm form = 
			(GerarRelatorioReligacaoClientesInadiplentesForm) actionForm;
		
		// Gerência Regional
		this.pesquisarGerenciaRegional(httpServletRequest, form);
		
		// Unidade Organizacional
		this.pesquisarUnidadeNegocio(httpServletRequest, form);
		
		String objetoConsulta = (String) httpServletRequest
			.getParameter("objetoConsulta");
		
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {
			// Localidade
			case 1:

				this.pesquisarLocalidade(form, httpServletRequest);
				break;
			// Setor Comercial
			case 2:

				this.pesquisarLocalidade(form, httpServletRequest);
				this.pesquisarSetorComercial(form, httpServletRequest);
				break;
		
			default:
				break;
			}
		}
		
		// pesquisa Cliente
		if (form.getClienteID() != null && !form.getClienteID().trim().equals("")) {
			
			this.pesquisarCliente( httpServletRequest, form);
		}
		
		// Pesquisa Usuário
		if (form.getUsuarioID() != null && !form.getUsuarioID().trim().equals("")) {
			
			this.pesquisarUsuario( httpServletRequest, form);
		}
		
		return retorno;
	}
	
	/**
	 * Pesquisar Localidade 
	 *
	 * @author Hugo Leonardo
	 * @date 18/01/2011
	 */
	private void pesquisarLocalidade(GerarRelatorioReligacaoClientesInadiplentesForm form,
			HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
	
		// Recebe o valor do campo localidadeOrigemID do formulário.
		localidadeID = (String) form.getLocalidadeID();

		filtroLocalidade.adicionarParametro(new ParametroSimples( FiltroLocalidade.ID, localidadeID));

		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

		// Retorna localidade
		colecaoPesquisa = this.getFachada().pesquisar(filtroLocalidade,
				Localidade.class.getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {

			form.setLocalidadeID("");
			form.setNomeLocalidade("Localidade Inexistente");
			
			httpServletRequest.setAttribute("corLocalidade", "exception");
			httpServletRequest.setAttribute("nomeCampo", "localidadeID");
		} 
		else {
			
			Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
			form.setLocalidadeID(String.valueOf(objetoLocalidade.getId()));
			form.setNomeLocalidade(objetoLocalidade.getDescricao());
			httpServletRequest.setAttribute("corLocalidade", "valor");
			httpServletRequest.setAttribute("nomeCampo", "setorComercialCD");
		}
	}
	
	/**
	 * Pesquisar Setor Comercial 
	 *
	 * @author Hugo Leonardo
	 * @date 18/01/2011
	 */
	private void pesquisarSetorComercial(GerarRelatorioReligacaoClientesInadiplentesForm form,
			HttpServletRequest httpServletRequest) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		// Recebe o valor do campo localidadeOrigemID do formulário.
		localidadeID = (String) form.getLocalidadeID();

		// O campo localidadeOrigemID será obrigatório
		if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {

			setorComercialCD = (String) form.getSetorComercialCD();

			// Adiciona o id da localidade que está no formulário para
			// compor a pesquisa.
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

			// Adiciona o código do setor comercial que esta no formulário
			// para compor a pesquisa.
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna setorComercial
			colecaoPesquisa = this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				
				form.setSetorComercialCD("");
				form.setSetorComercialID("");
				form.setNomeSetorComercial("Setor comercial inexistente.");
				httpServletRequest.setAttribute("corSetorComercial", "exception");
				httpServletRequest.setAttribute("nomeCampo", "setorComercialCD");
			} 
			else {
				
				SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				form.setSetorComercialCD(String.valueOf(objetoSetorComercial.getCodigo()));
				form.setSetorComercialID(String.valueOf(objetoSetorComercial.getId()));
				form.setNomeSetorComercial(objetoSetorComercial.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "clienteID");	
			}
		} else {
			// Limpa o campo setorComercialCD do formulário
			form.setSetorComercialCD("");
			form.setNomeSetorComercial("Informe a localidade.");
			httpServletRequest.setAttribute("corSetorComercial", "exception");
		}
	}
	
	/**
	 * Pesquisar Usuário
	 *
	 * @author Hugo Leonardo
	 * @date 24/01/2011
	 */
	private void pesquisarUsuario(HttpServletRequest httpServletRequest, 
			GerarRelatorioReligacaoClientesInadiplentesForm form) {

		// Pesquisa a usuário na base
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(
				FiltroUsuario.ID, form.getUsuarioID()));

		Collection<Usuario> usuarioPesquisado = this.getFachada().pesquisar(
				filtroUsuario, Usuario.class.getName());

		// Se nenhum usuário for encontrado a mensagem é enviada para a página
		if (usuarioPesquisado != null && !usuarioPesquisado.isEmpty()) {
			Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(usuarioPesquisado);
			form.setUsuarioID("" + usuario.getId());
			form.setNomeUsuario( usuario.getNomeUsuario());

		} else {
			form.setUsuarioID("");
			form.setNomeUsuario("USUARIO INEXISTENTE");
			httpServletRequest.setAttribute("usuarioInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "usuarioID");
			httpServletRequest.setAttribute("corUsuario", "exception");
		}
	}
	
	/**
	 * Pesquisar Cliente
	 *
	 * @author Hugo Leonardo
	 * @date 24/01/2011
	 */
	private void pesquisarCliente(HttpServletRequest httpServletRequest, 
			GerarRelatorioReligacaoClientesInadiplentesForm form) {

		// Pesquisa a usuário na base
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.ID, form.getClienteID()));

		Collection<Cliente> clientePesquisado = this.getFachada().pesquisar(
				filtroCliente, Cliente.class.getName());

		// Se nenhum usuário for encontrado a mensagem é enviada para a página
		if (clientePesquisado != null && !clientePesquisado.isEmpty()) {
			
			Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(clientePesquisado);
			form.setClienteID("" + cliente.getId());
			form.setNomeCliente( cliente.getNome());

		} else {
			form.setClienteID("");
			form.setNomeCliente("CLIENTE INEXISTENTE");
			httpServletRequest.setAttribute("clienteInexistente", true);
			httpServletRequest.setAttribute("nomeCampo", "clienteID");
			httpServletRequest.setAttribute("corCliente", "exception");
		}
	}
	
	/**
	 * Pesquisar Gerência Regional
	 *
	 * @author Hugo Leonardo
	 * @date 24/01/2011
	 */
	private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest,
			GerarRelatorioReligacaoClientesInadiplentesForm form){
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(
				new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());

		if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Gerência Regional");
		} else {
			httpServletRequest.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
		}
	}
	
	/**
	 * Pesquisar Unidade Organizacional
	 *
	 * @author Hugo Leonardo
	 * @date 24/01/2011
	 */
	private void pesquisarUnidadeNegocio(HttpServletRequest httpServletRequest,
			GerarRelatorioReligacaoClientesInadiplentesForm form){
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		
		filtroUnidadeNegocio.setConsultaSemLimites(true);
		
		if(form.getGerenciaRegionalID() != null && !form.getGerenciaRegionalID().equals("-1")){
			
			filtroUnidadeNegocio.adicionarParametro( new ParametroSimples(
					FiltroUnidadeNegocio.ID_GERENCIA, form.getGerenciaRegionalID()));
		}
		
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);

		filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoUnidadeNegocio = 
			this.getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
			
			throw new ActionServletException("atencao.naocadastrado", null, "Unidade Negócio");
		} else {
			
			httpServletRequest.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		}
	}
	
}
