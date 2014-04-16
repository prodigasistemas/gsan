package gcom.gui.cobranca;


import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.cliente.FiltroProfissao;
import gcom.cadastro.cliente.FiltroRamoAtividade;
import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.FiltroContaMotivoRevisao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;


import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;


/**			
 * @date 05/09/08
 * @author Arthur Carvalho
 */

public class ExibirFiltrarCobrancaSituacaoAction extends GcomAction {
	
	/*
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarCobrancaSituacao");

		Collection colecaoPesquisa = null;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarCobrancaSituacaoActionForm filtrarCobrancaSituacaoActionForm = (FiltrarCobrancaSituacaoActionForm) actionForm;
		
		//Motivo de revisao da conta
        FiltroContaMotivoRevisao filtroContaMotivoRevisao = new FiltroContaMotivoRevisao();
        
        filtroContaMotivoRevisao.setCampoOrderBy(FiltroContaMotivoRevisao.ID);
        
        filtroContaMotivoRevisao.adicionarParametro(new ParametroSimples(
        		FiltroContaMotivoRevisao.INDICADOR_USO,
        		ConstantesSistema.INDICADOR_USO_ATIVO));
       
        //Retorna Motivo de revisao da conta
        colecaoPesquisa = this.getFachada().pesquisar(filtroContaMotivoRevisao,
        		ContaMotivoRevisao.class.getName());
        
        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
            //Nenhum registro na tabela foi encontrado
            throw new ActionServletException(
                    "atencao.pesquisa.nenhum_registro_tabela", null,
                    "Motivo de Revisão da Conta");
        } else {
            httpServletRequest.setAttribute("colecaoContaMotivoRevisao", colecaoPesquisa);
        }
        

        //Profissao
        FiltroProfissao filtroProfissao = new FiltroProfissao();
        
        filtroProfissao.setCampoOrderBy(FiltroProfissao.ID);
        
        filtroProfissao.adicionarParametro(new ParametroSimples(
        		FiltroProfissao.INDICADOR_USO,
        		ConstantesSistema.INDICADOR_USO_ATIVO));
       
        Collection colecaoProfissao = null;
        //Retorna Profissao
        colecaoProfissao = this.getFachada().pesquisar(filtroProfissao,
        		Profissao.class.getName());
        
        if (colecaoProfissao == null || colecaoProfissao.isEmpty()) {
            //Nenhum registro na tabela foi encontrado
            throw new ActionServletException(
                    "atencao.pesquisa.nenhum_registro_tabela", null,
                    "Profissao");
        } else {
            httpServletRequest.setAttribute("colecaoProfissao", colecaoProfissao);
        }
        
        //Ramo Atividade
        FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade();
        
        filtroRamoAtividade.setCampoOrderBy(FiltroRamoAtividade.ID);
        
        filtroRamoAtividade.adicionarParametro(new ParametroSimples(
        		FiltroRamoAtividade.INDICADOR_USO,
        		ConstantesSistema.INDICADOR_USO_ATIVO));
       
        Collection colecaoRamoAtividade = null;
        //Retorna Ramo de Atividade
        colecaoRamoAtividade = this.getFachada().pesquisar(filtroRamoAtividade,
        		RamoAtividade.class.getName());
        
        if (colecaoRamoAtividade == null || colecaoRamoAtividade.isEmpty()) {
            //Nenhum registro na tabela foi encontrado
            throw new ActionServletException(
                    "atencao.pesquisa.nenhum_registro_tabela", null,
                    "Ramo Atvidade");
        } else {
            httpServletRequest.setAttribute("colecaoRamoAtividade", colecaoRamoAtividade);
        }
        
		
		String primeiraVez = httpServletRequest.getParameter("menu");
			if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarCobrancaSituacaoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}			

		
		if(filtrarCobrancaSituacaoActionForm.getIndicadorAtualizar()==null){
			filtrarCobrancaSituacaoActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	
        	filtrarCobrancaSituacaoActionForm.setId("");
        	filtrarCobrancaSituacaoActionForm.setDescricao("");
        	filtrarCobrancaSituacaoActionForm.setContaMotivoRevisao("");
        	filtrarCobrancaSituacaoActionForm.setIndicadorUso("");
        	filtrarCobrancaSituacaoActionForm.setIndicadorBloqueioParcelamento("");
        	filtrarCobrancaSituacaoActionForm.setIndicadorExigenciaAdvogado("");
        	filtrarCobrancaSituacaoActionForm.setIndicadorBloqueioRetirada("");
        	filtrarCobrancaSituacaoActionForm.setProfissao("");
        	filtrarCobrancaSituacaoActionForm.setRamoAtividade("");
        	filtrarCobrancaSituacaoActionForm.setIndicadorPrescricaoImoveisParticulares("");
        	
        }
       return retorno;

	}

}
