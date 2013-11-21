package gcom.gui.relatorio.cobranca;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirGerarRelatorioOSAcompanhamentoCobrancaResultadoAction extends
GcomAction{

	
	/**
	 * 
	 * 
	 * [UC1134] 
	 * 
	 * 
	 * @author 
	 * @date 
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	//Obtém a instância da fachada
	Fachada fachada = Fachada.getInstancia();

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorio");
		GerarRelatorioOSAcompanhamentoCobrancaResultadoActionForm relatorioActionForm = (GerarRelatorioOSAcompanhamentoCobrancaResultadoActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		
		
		
		
		//Coleções do formulário
		//====================================//
		
		//Categoria
		Collection colecaoCategoria = fachada.obterCategorias();
		sessao.setAttribute("colecaoCategoria",colecaoCategoria);
		
		//Perfis
		Collection colecaoPerfilImovel = fachada.obterPerfisImoveis();
		sessao.setAttribute("colecaoPerfilImovel",colecaoPerfilImovel);
		
		//Gerência regional
		/*FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional
				.setCampoOrderBy(FiltroGerenciaRegional.NOME_ABREVIADO);
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoGerenciaRegional = fachada
				.pesquisar(filtroGerenciaRegional,
						GerenciaRegional.class.getName());*/
		Collection colecaoGerenciaRegional = fachada.obterColecaoGerenciaRegional();
		
		sessao.setAttribute("colecaoGerenciaRegional",colecaoGerenciaRegional);
		
		//Unidade negócio
		
		/*FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio
				.setCampoOrderBy(FiltroGerenciaRegional.NOME_ABREVIADO);
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoUnidadeNegocio = this.fachada.pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class.getName());*/
		Collection colecaoUnidadeNegocio = fachada.obterColecaoUnidadeNegocio();
		sessao.setAttribute("colecaoUnidadeNegocio",colecaoUnidadeNegocio);
		
		//Tipo Servico
		Collection colecaoTipoServico = fachada.obterColecaoTipoOSgerada();
		sessao.setAttribute("colecaoTipoServico",colecaoTipoServico);
		
		
		//Comando
		HashMap hash = (HashMap) sessao.getAttribute("objetos");
		ComandoEmpresaCobrancaConta comando = (ComandoEmpresaCobrancaConta)hash.get("comando");
		relatorioActionForm.setComando(comando.getId().toString());
		
		
		//Tratamento das buscas através do enter
		//=================================================
		
		//Localidade Inicial
		String pesquisarLocalidadeI = httpServletRequest.getParameter("pesquisarLocalidadeInicial");
		if(pesquisarLocalidadeI != null && !"".equals(pesquisarLocalidadeI)){
			Integer idLocalidade = new Integer(relatorioActionForm.getIdLocalidadeInicial());
			Localidade localidadeInicial = fachada.pesquisarLocalidadeDigitada(idLocalidade);
			
			if(localidadeInicial != null){
				relatorioActionForm.setDescricaoLocalidadeInicial(localidadeInicial.getDescricao());
				relatorioActionForm.setDescricaoLocalidadeFinal(localidadeInicial.getDescricao());
				relatorioActionForm.setIdLocalidadeFinal(idLocalidade.toString());
			}
			else{
				relatorioActionForm.setDescricaoLocalidadeInicial("LOCALIDADE INEXISTENTE");
				relatorioActionForm.setIdLocalidadeInicial("");
				relatorioActionForm.setIdSetorComercialInicial("");
				relatorioActionForm.setDescricaoSetorComercialInicial("");
				relatorioActionForm.setIdQuadraInicial("");
				relatorioActionForm.setDescricaoQuadraInicial("");
				relatorioActionForm.setIdLocalidadeFinal("");
				relatorioActionForm.setDescricaoLocalidadeFinal("");
				relatorioActionForm.setIdSetorComercialFinal("");
				relatorioActionForm.setDescricaoSetorComercialFinal("");
				relatorioActionForm.setIdQuadraFinal("");
				relatorioActionForm.setDescricaoQuadraFinal("");
				httpServletRequest.setAttribute("localidadeInicialException","ok");
			}
			
		}
		
		//Localidade Final
		String pesquisarLocalidadeFinal = httpServletRequest.getParameter("pesquisarLocalidadeFinal");
		if(pesquisarLocalidadeFinal != null && !"".equals(pesquisarLocalidadeFinal)){
			Integer idLocalidade = new Integer(relatorioActionForm.getIdLocalidadeFinal());
			Localidade localidadeFinal = fachada.pesquisarLocalidadeDigitada(idLocalidade);
			
			if(localidadeFinal != null){
				relatorioActionForm.setDescricaoLocalidadeFinal(localidadeFinal.getDescricao());
				
			}
			else{
				relatorioActionForm.setDescricaoLocalidadeFinal("LOCALIDADE INEXISTENTE");
				relatorioActionForm.setIdLocalidadeFinal("");
				relatorioActionForm.setIdSetorComercialFinal("");
				relatorioActionForm.setDescricaoSetorComercialFinal("");
				relatorioActionForm.setIdQuadraFinal("");
				relatorioActionForm.setDescricaoQuadraFinal("");
				httpServletRequest.setAttribute("localidadeFinalException","ok");
			}
			
		}
		
		
		//Setor Comercial Inicial
		String pesquisarSetorComercialInicial = httpServletRequest.getParameter("pesquisarSetorComercialInicial");
		if(pesquisarSetorComercialInicial != null && !"".equals(pesquisarSetorComercialInicial)){
			
			String idSetorComercial = relatorioActionForm.getIdSetorComercialInicial();
			//Localidade localidadeInicial = (Localidade) sessao.getAttribute("localidadeInicial");
			String idLocalidadeInicial = relatorioActionForm.getIdLocalidadeInicial();
				
			SetorComercial setorComercialInicial = fachada.obterSetorComercialLocalidade(idLocalidadeInicial,idSetorComercial);
			
			if(setorComercialInicial != null){
				relatorioActionForm.setDescricaoSetorComercialInicial(setorComercialInicial.getDescricao());
				relatorioActionForm.setDescricaoSetorComercialFinal(setorComercialInicial.getDescricao());
				relatorioActionForm.setIdSetorComercialFinal(idSetorComercial.toString());
				
			}
			else{
				relatorioActionForm.setDescricaoSetorComercialInicial("SETOR COMERCIAL INEXISTENTE");
				relatorioActionForm.setIdSetorComercialInicial("");
				relatorioActionForm.setIdQuadraInicial("");
				relatorioActionForm.setDescricaoQuadraInicial("");
				relatorioActionForm.setIdLocalidadeFinal("");
				relatorioActionForm.setDescricaoLocalidadeFinal("");
				relatorioActionForm.setIdSetorComercialFinal("");
				relatorioActionForm.setDescricaoSetorComercialFinal("");
				relatorioActionForm.setIdQuadraFinal("");
				relatorioActionForm.setDescricaoQuadraFinal("");
				httpServletRequest.setAttribute("setorComercialInicialException","ok");
			}
			
		}
		
		
		//Setor Comercial Final
		String pesquisarSetorComercialFinal = httpServletRequest.getParameter("pesquisarSetorComercialFinal");
		if(pesquisarSetorComercialFinal != null && !"".equals(pesquisarSetorComercialFinal)){
			
			String idSetorComercial = relatorioActionForm.getIdSetorComercialFinal();
			//Localidade localidadeFinal = (Localidade) sessao.getAttribute("localidadeFinal");
			String idLocalidadeFinal = relatorioActionForm.getIdLocalidadeFinal();
			
			SetorComercial setorComercialFinal = fachada.obterSetorComercialLocalidade(idLocalidadeFinal,idSetorComercial);
			
			if(setorComercialFinal != null){
				relatorioActionForm.setDescricaoSetorComercialFinal(setorComercialFinal.getDescricao());
				
			}
			else{
				relatorioActionForm.setDescricaoSetorComercialFinal("SETOR COMERCIAL INEXISTENTE");
				relatorioActionForm.setIdSetorComercialFinal("");
				relatorioActionForm.setIdQuadraFinal("");
				relatorioActionForm.setDescricaoQuadraFinal("");
				httpServletRequest.setAttribute("SetorComercialFinalException","ok");
			}
			
		}
		
		//Quadra Inicial
		String pesquisarQuadraInicial = httpServletRequest.getParameter("pesquisarQuadraInicial");
		if(pesquisarQuadraInicial != null && !"".equals(pesquisarQuadraInicial)){
			
			//SetorComercial setorComercialInicial = (SetorComercial)sessao.getAttribute("setorComercialInicial");
			int idSetorComercialInicial = Integer.parseInt(relatorioActionForm.getIdSetorComercialInicial());
			int idQuadraInicial = Integer.parseInt(relatorioActionForm.getIdQuadraInicial());
			
			Quadra quadraInicial = fachada.obterQuadraSetorComercial(idSetorComercialInicial,idQuadraInicial);
			
			
			
			if(quadraInicial != null){
				relatorioActionForm.setDescricaoQuadraInicial(quadraInicial.getDescricao());
				relatorioActionForm.setDescricaoQuadraFinal(quadraInicial.getDescricao());
				relatorioActionForm.setIdLocalidadeFinal(idQuadraInicial+"");
				sessao.setAttribute("quadraInicial",quadraInicial);
			}
			else{
				relatorioActionForm.setDescricaoQuadraInicial("QUADRA INEXISTENTE");
				relatorioActionForm.setIdQuadraInicial("");
				relatorioActionForm.setIdQuadraFinal("");
				relatorioActionForm.setDescricaoQuadraFinal("");
				sessao.removeAttribute("quadraInicial");
			}
			
		}
		
		//Quadra Final
		String pesquisarQuadraFinal = httpServletRequest.getParameter("pesquisarQuadraFinal");
		if(pesquisarQuadraFinal != null && !"".equals(pesquisarQuadraFinal)){
			
			//SetorComercial setorComercialFinal = (SetorComercial)sessao.getAttribute("setorComercialFinal");
			int idSetorComercialFinal = Integer.parseInt(relatorioActionForm.getIdSetorComercialFinal());
			int idQuadraFinal = Integer.parseInt(relatorioActionForm.getIdQuadraFinal());
			
			Quadra quadraFinal = fachada.obterQuadraSetorComercial(idSetorComercialFinal,idQuadraFinal);
			
			
			
			if(quadraFinal != null){
				relatorioActionForm.setDescricaoQuadraFinal(quadraFinal.getDescricao());
				sessao.setAttribute("quadraFinal",quadraFinal);
			}
			else{
				relatorioActionForm.setDescricaoQuadraFinal("QUADRA INEXISTENTE");
				relatorioActionForm.setIdQuadraFinal("");
				sessao.removeAttribute("quadraFinal");
			}
			
		}
		
		return retorno;
	}
}