package gcom.gui.relatorio.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * [UC1021] Filtrar Notificação de Débitos para Impressão Simultânea.
 * 
 * @author Daniel Alves
 *
 * @date 14/05/2010
 */

public class ExibirFiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
			.findForward("exibirFiltrarRelatorioNotificacaoDebitosImpressaoSimultanea");
		
		FiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm form = 
			(FiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm) actionForm;
	
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		
		//Empresa
        FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		Collection colecaoEmpresa = (Collection) this.getFachada().pesquisar(
				filtroEmpresa, Empresa.class.getName());
		
		if (colecaoEmpresa != null	&& !colecaoEmpresa.isEmpty()) {
			httpServletRequest.setAttribute("colecaoEmpresa", colecaoEmpresa);
		}else {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Tabela Empresa");
		}

		// Pesquisar Localidade
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			objetoConsulta.trim().equals("1")){

			// Faz a consulta de Localidade
			this.pesquisarLocalidade(form,objetoConsulta);
			
		}
		
		//	Pesquisar Setor Comercial
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			objetoConsulta.trim().equals("2")) {
			
			// Faz a consulta do Setor Comercial
			this.pesquisarSetorComercial(form,objetoConsulta);
		}
		
		//this.pesquisarUnidadeNegocio(httpServletRequest,form);
		this.pesquisarGrupoCobranca(httpServletRequest);
		
		//Seta os request´s encontrados
		this.setaRequest(httpServletRequest,form);
		
		return retorno;
	}
	
	/**
	 * Pesquisa Setor Comercial
	 *
     * @author Daniel Alves
	 * @date 17/05/2010
	 */
	private void pesquisarSetorComercial(FiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm form, 
			String objetoConsulta) {
		
		Object idSetorComercial = null;
		Object local = null;
		
		local = form.getLocalidade();
		
		if(objetoConsulta.trim().equals("2")){
			idSetorComercial = form.getCodigoSetorComercial();
			
		}
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,idSetorComercial));
		
		filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE,local));
		
		// Recupera Setor Comercial
		Collection colecaoSetorComercial = 
			this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
	
		if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {

			SetorComercial setorComercial = 
				(SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
			
			if(objetoConsulta.trim().equals("2")){
				form.setCodigoSetorComercial(""+setorComercial.getCodigo());
				form.setSetorComercialDescricao(setorComercial.getDescricao());
								
			}
						

		} else {
			if(objetoConsulta.trim().equals("2")){
				form.setCodigoSetorComercial(null);
				form.setSetorComercialDescricao("Setor Comercial Inexistente");
				
			}
		}
	}

	/**
	 * Pesquisa Localidade
	 *
	 * @author Daniel Alves
	 * @date 17/05/2010
	 */
	private void pesquisarLocalidade(FiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm form,
		String objetoConsulta) {

		Object local = null;
		
		if(objetoConsulta.trim().equals("1")){
			local = form.getLocalidade();
			
		}
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(
			new ParametroSimples(FiltroLocalidade.ID,local));
		
		// Recupera Localidade
		Collection colecaoLocalidade = 
			this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
	
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			Localidade localidade = 
				(Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			
			if(objetoConsulta.trim().equals("1")){
				form.setLocalidade(localidade.getId().toString());
				form.setNomeLocalidade(localidade.getDescricao());		
				
			}
			

		} 
	}
	
	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Daniel Alves 
	 * @date 14/05/2010
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			FiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm form){
		
		//Localidade
		if(form.getLocalidade() != null && 
			!form.getLocalidade().equals("") && 
			form.getNomeLocalidade() != null && 
			!form.getNomeLocalidade().equals("")){
					
			httpServletRequest.setAttribute("localidadeEncontrada","true");			
		}
		
		//Setor Comercial
		if(form.getCodigoSetorComercial() != null && 
			!form.getCodigoSetorComercial().equals("") && 
			form.getSetorComercialDescricao() != null && 
			!form.getSetorComercialDescricao().equals("")){
					
			httpServletRequest.setAttribute("setorComercialEncontrada","true");			
		}
		
	}
	
	private void pesquisarGrupoCobranca(HttpServletRequest httpServletRequest) {

		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();

		filtroCobrancaGrupo.setConsultaSemLimites(true);

		filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
				FiltroCobrancaGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO ));
		filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);

		Collection colecaoCobrancaGrupo = this.getFachada().pesquisar(
				filtroCobrancaGrupo, CobrancaGrupo.class.getName());

		if (colecaoCobrancaGrupo == null
				|| colecaoCobrancaGrupo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Grupo Cobranca");
		} else {
			httpServletRequest.setAttribute("colecaoGrupo",
					colecaoCobrancaGrupo);
		}
	}
	
	
	
}
