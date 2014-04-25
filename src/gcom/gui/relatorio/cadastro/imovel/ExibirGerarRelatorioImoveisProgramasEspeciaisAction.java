package gcom.gui.relatorio.cadastro.imovel;

import gcom.cadastro.geografico.FiltroRegiaoDesenvolvimento;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
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
 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais
 * 
 * @author Hugo Leonardo, Ivan Sergio
 * @date 14/01/2010
 * 		 14/09/2010 - CRC4734 - Retirar do filtro o perfil do imóvel e obter as contas a partir
 * 								de fatura item da fatura selecionada;
 */

public class ExibirGerarRelatorioImoveisProgramasEspeciaisAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
			.findForward("exibirGerarRelatorioImoveisProgramasEspeciaisAction");
		
		GerarRelatorioImoveisProgramasEspeciaisActionForm form = 
			(GerarRelatorioImoveisProgramasEspeciaisActionForm) actionForm;

		//Primeira vez que carrega a pagina
		if ( httpServletRequest.getParameter("menu") != null && 
				httpServletRequest.getParameter("menu").equals("sim")) {
			
			form.setTipo("0");
			
		}
		
		this.pesquisarRegiaoDesenvolvimento(httpServletRequest, form);
		
		//Seta os request´s encontrados
		this.setaRequest(httpServletRequest,form);
		
		//  Pega os codigos que o usuario digitou para a pesquisa direta de
		// localidade

		if (form.getIdLocalidade() != null && !form.getIdLocalidade().trim().equals("")) {
			pesquisarLocalidade( httpServletRequest, form);

		}
		return retorno;
	}

	private void setaRequest(HttpServletRequest httpServletRequest,
			GerarRelatorioImoveisProgramasEspeciaisActionForm form){
		
		//Localidade Inicial
		if(form.getIdLocalidade() != null && 
			!form.getIdLocalidade().equals("") && 
			form.getNomeLocalidade() != null && 
			!form.getNomeLocalidade().equals("")){
					
			httpServletRequest.setAttribute("localidadeEncontrada","true");
		}
		
	}
	
	/**
	 * [CRC_3895] Filtrar por Região de Desenvolvimento.
	 * 
	 * @author Hugo Leonardo
	 *
	 * @date 17/03/2010
	 */
	
	private void pesquisarRegiaoDesenvolvimento(HttpServletRequest httpServletRequest,
			GerarRelatorioImoveisProgramasEspeciaisActionForm form){
		
		FiltroRegiaoDesenvolvimento filtroRegiaoDesenvolvimento = new FiltroRegiaoDesenvolvimento();
		
		filtroRegiaoDesenvolvimento.setConsultaSemLimites(true);
		filtroRegiaoDesenvolvimento.setCampoOrderBy(FiltroRegiaoDesenvolvimento.DESCRICAO);

		filtroRegiaoDesenvolvimento.adicionarParametro(
				new ParametroSimples(FiltroRegiaoDesenvolvimento.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoRegiaoDesenvolvimento = 
			this.getFachada().pesquisar(filtroRegiaoDesenvolvimento,RegiaoDesenvolvimento.class.getName());

		if (colecaoRegiaoDesenvolvimento == null || colecaoRegiaoDesenvolvimento.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Região de Desenvolvimento");
		} else {
			httpServletRequest.setAttribute("colecaoRegiaoDesenvolvimento",colecaoRegiaoDesenvolvimento);
		}
	}

	private void pesquisarLocalidade(HttpServletRequest httpServletRequest, 
						GerarRelatorioImoveisProgramasEspeciaisActionForm form) {

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a localidade na base
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, form.getIdLocalidade()));

		Collection<Localidade> localidadePesquisada = fachada.pesquisar(
				filtroLocalidade, Localidade.class.getName());

		// Se nenhuma localidade for encontrada a mensagem é enviada para a
		// página
		if (localidadePesquisada != null && !localidadePesquisada.isEmpty()) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(localidadePesquisada);
			form.setIdLocalidade(""+localidade.getId());
			form.setNomeLocalidade(localidade.getDescricao());

		} else {
			form.setIdLocalidade("");
			form.setNomeLocalidade("LOCALIDADE INEXISTENTE");
			httpServletRequest.setAttribute("localidadeInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
		}
	}
	
}
