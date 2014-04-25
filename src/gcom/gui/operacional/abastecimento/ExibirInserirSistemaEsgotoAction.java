package gcom.gui.operacional.abastecimento;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.FiltroDivisaoEsgoto;
import gcom.operacional.SistemaEsgotoTratamentoTipo;
import gcom.operacional.abastecimento.FiltroSistemaEsgotoTratamentoTipo;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * [UC0524]	INSERIR SISTEMA ESGOTO
 * 
 * @author Kássia Albuquerque
 * @date 08/03/2007
 */
 


public class ExibirInserirSistemaEsgotoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
			ActionForward retorno = actionMapping.findForward("inserirSistemaEsgoto");	
			
			Fachada fachada = Fachada.getInstancia();
		
		
			// [FS0001] - VERIFICAR EXISTENCIA DE DADOS
		
			// DIVISÃO DE ESGOTO
				
			FiltroDivisaoEsgoto filtroDivisaoEsgoto = new FiltroDivisaoEsgoto();
			
			filtroDivisaoEsgoto.adicionarParametro(new ParametroSimples
					(FiltroDivisaoEsgoto.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroDivisaoEsgoto.setCampoOrderBy(FiltroDivisaoEsgoto.DESCRICAO);
			
			Collection<DivisaoEsgoto> colecaoDivisaoEsgoto = fachada.pesquisar(
					filtroDivisaoEsgoto, DivisaoEsgoto.class.getName());
	
			if (colecaoDivisaoEsgoto == null || colecaoDivisaoEsgoto.isEmpty()) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Divisão de Esgoto");
			}
	
			httpServletRequest.setAttribute("colecaoDivisaoEsgoto",colecaoDivisaoEsgoto);
			
			
			// TIPO DE TRATAMENTO
			
			FiltroSistemaEsgotoTratamentoTipo filtroSistemaEsgotoTratamentoTipo =  new FiltroSistemaEsgotoTratamentoTipo();
			
			filtroSistemaEsgotoTratamentoTipo.adicionarParametro(new ParametroSimples
					(FiltroSistemaEsgotoTratamentoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroSistemaEsgotoTratamentoTipo.setCampoOrderBy(FiltroSistemaEsgotoTratamentoTipo.NOME);
			
			Collection<SistemaEsgotoTratamentoTipo> colecaoSistemaTratamentoTipo = fachada.pesquisar
							(filtroSistemaEsgotoTratamentoTipo,SistemaEsgotoTratamentoTipo.class.getName());
			
			if (colecaoSistemaTratamentoTipo == null || colecaoSistemaTratamentoTipo.isEmpty()){
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "Tipo de Tratamento de Sistema de Esgoto");
			}
			httpServletRequest.setAttribute("colecaoSistemaTratamentoTipo",colecaoSistemaTratamentoTipo);
		
		return retorno;
	}
}
