package gcom.gui.cobranca;


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

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Arthur Carvalho
 * @created 04 de setembro de 2008
 */
public class ExibirInserirCobrancaSituacaoAction extends GcomAction {
	/**
	 * Description of the Method
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

		ActionForward retorno = actionMapping.findForward("inserirCobrancaSituacao");

		InserirCobrancaSituacaoActionForm inserirCobrancaSituacaoActionForm = (InserirCobrancaSituacaoActionForm) actionForm;
		
		
		//Motivo de Revisao da Conta
		 FiltroContaMotivoRevisao filtroContaMotivoRevisao = new FiltroContaMotivoRevisao();
	        
		 filtroContaMotivoRevisao.setCampoOrderBy(FiltroContaMotivoRevisao.ID);
	        
		 filtroContaMotivoRevisao.adicionarParametro(new ParametroSimples(
				 FiltroContaMotivoRevisao.INDICADOR_USO,
	        		ConstantesSistema.INDICADOR_USO_ATIVO));
	       
	        Collection colecaoPesquisa = null;
	        
	        //Retorna tipo de situacao especial de faturamento
	       colecaoPesquisa = this.getFachada().pesquisar(filtroContaMotivoRevisao,
	    		   ContaMotivoRevisao.class.getName());
	        
	        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
	            //Nenhum registro na tabela leitura_anormalidade_consumo foi encontrado
	            throw new ActionServletException(
	                    "atencao.pesquisa.nenhum_registro_tabela", null,
	                    "Motivo de Revisão da Conta");
	        } else {
	            httpServletRequest.setAttribute("colecaoContaMotivoRevisao", colecaoPesquisa);
	        }
	        
	        //Profissão
	        FiltroProfissao filtroProfissao = new FiltroProfissao();
	        filtroProfissao.setCampoOrderBy(FiltroProfissao.ID);
	        filtroProfissao.adicionarParametro(new ParametroSimples(
	        		FiltroProfissao.INDICADOR_USO,
	        			ConstantesSistema.INDICADOR_USO_ATIVO));
	        Collection colecaoProfissao = null;
	        colecaoProfissao = this.getFachada().pesquisar(filtroProfissao, 
	        		Profissao.class.getName());
	        
	        if ( colecaoProfissao == null || colecaoProfissao.isEmpty() ) {
	        	throw new ActionServletException (
	        			"atencao.pesquisa.nenhum_registro_tabela", null,
	                    "Profissão");
	        } else {
	        	httpServletRequest.setAttribute("colecaoProfissao", colecaoProfissao );
	        }
	        
	        //Ramo Atividade
	        FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade();
	        filtroRamoAtividade.setCampoOrderBy(FiltroRamoAtividade.ID);
	        filtroRamoAtividade.adicionarParametro(new ParametroSimples(
	        		FiltroRamoAtividade.INDICADOR_USO,
	        			ConstantesSistema.INDICADOR_USO_ATIVO));
	        Collection colecaoRamoAtividade = null;
	        colecaoRamoAtividade = this.getFachada().pesquisar(filtroRamoAtividade, 
	        		RamoAtividade.class.getName());
	        
	        if ( colecaoRamoAtividade == null || colecaoRamoAtividade.isEmpty() ) {
	        	throw new ActionServletException (
	        			"atencao.pesquisa.nenhum_registro_tabela", null,
	                    "Ramo Atividade");
	        } else {
	        	httpServletRequest.setAttribute("colecaoRamoAtividade", colecaoRamoAtividade );
	        }
	        
        if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
        	inserirCobrancaSituacaoActionForm.setIndicadorBloqueioParcelamento(ConstantesSistema.NAO);
        	inserirCobrancaSituacaoActionForm.setIndicadorExigenciaAdvogado(ConstantesSistema.NAO); 
        	inserirCobrancaSituacaoActionForm.setIndicadorBloqueioInclusao(ConstantesSistema.NAO);
        	inserirCobrancaSituacaoActionForm.setIndicadorBloqueioRetirada(ConstantesSistema.NAO);
        	inserirCobrancaSituacaoActionForm.setIndicadorSelecaoApenasComPermissao(ConstantesSistema.NAO);
        	inserirCobrancaSituacaoActionForm.setIndicadorPrescricaoImoveisParticulares( new Integer(ConstantesSistema.NAO.toString()));
        	inserirCobrancaSituacaoActionForm.setIndicadorNaoIncluirImoveisEmCobrancaResultado( new Integer(ConstantesSistema.NAO.toString()));
        }
        
        
		return retorno;
	}
}
