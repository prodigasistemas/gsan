package gcom.gui.operacional;


import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarDivisaoEsgotoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarDivisaoEsgotoActionForm atualizarDivisaoEsgotoActionForm = (AtualizarDivisaoEsgotoActionForm) actionForm;

		DivisaoEsgoto divisaoEsgoto= (DivisaoEsgoto) sessao.getAttribute("atualizarDivisaoEsgoto");
		
		Collection colecaoPesquisa = null;

		String idDivisaoEsgoto= atualizarDivisaoEsgotoActionForm.getId();		
        String descricaoDivisaoEsgoto = atualizarDivisaoEsgotoActionForm
        .getDescricao();            
        Short indicadorUsoDivisaoEsgoto= atualizarDivisaoEsgotoActionForm
        .getIndicadorUso();
        String unidadeOrganizacionalId= atualizarDivisaoEsgotoActionForm.getUnidadeOrganizacionalId();
		
        //Unidade Organizacional é obrigatório.
        if (unidadeOrganizacionalId == null || unidadeOrganizacionalId.equalsIgnoreCase("")) {
            throw new ActionServletException("atencao.required", null, "Unidade Organizacional");
        }
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, unidadeOrganizacionalId));

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.INDICADOR_USO,
		        ConstantesSistema.INDICADOR_USO_ATIVO));

		//Retorna Unidade Organizacional
		colecaoPesquisa = fachada.pesquisar(filtroUnidadeOrganizacional,
				UnidadeOrganizacional.class.getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		    throw new ActionServletException(
		            "atencao.pesquisa.unidade_organizacional_inexistente");
		}
        
        divisaoEsgoto.setId(new Integer (idDivisaoEsgoto));
        divisaoEsgoto.setDescricao(descricaoDivisaoEsgoto);
        divisaoEsgoto.setUltimaAlteracao( new Date() );	
        divisaoEsgoto.setIndicadorUso(new Short(indicadorUsoDivisaoEsgoto));
        
        UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
        unidadeOrganizacional.setId(new Integer(atualizarDivisaoEsgotoActionForm.getUnidadeOrganizacionalId()));
        divisaoEsgoto.setUnidadeOrganizacional(unidadeOrganizacional);
		
		fachada.atualizar(divisaoEsgoto);

		montarPaginaSucesso(httpServletRequest, "Divisão de Esgoto "
				+ atualizarDivisaoEsgotoActionForm.getDescricao() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Divisão de Egoto ",
				"exibirFiltrarDivisaoEsgotoAction.do?menu=sim");        
        
		return retorno;
	}
}
