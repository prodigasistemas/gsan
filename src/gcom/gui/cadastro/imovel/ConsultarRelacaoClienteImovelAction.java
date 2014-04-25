package gcom.gui.cadastro.imovel;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action ConsultarRelacaoClienteImovelAction
 *
 * @author thiago toscano
 * @date 10/03/2006
 */
public class ConsultarRelacaoClienteImovelAction  extends GcomAction {

	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse httpServletResponse) {

        ConsultarRelacaoClienteImovelActionForm form = (ConsultarRelacaoClienteImovelActionForm) actionForm;
        
        validarPeriodosInformados(form);

        setarAtributosNaSessao(request, form);

        if ( Util.verificarNaoVazio(form.getIdCliente()) ) {
        	return actionMapping.findForward("cliente");
        	
        }
        
    	return actionMapping.findForward("imovel");
    }

	/**
	 *Método coloca alguns atributos na sessão para uso posterior
	 *dos actions que vão exibir os dados da consulta.
	 *
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 */
	private void setarAtributosNaSessao(HttpServletRequest request,
			ConsultarRelacaoClienteImovelActionForm form) {
		
		HttpSession sessao = request.getSession(false);

        sessao.setAttribute("manterDadosSesao", "true");
        sessao.setAttribute("idCliente", form.getIdCliente());
        sessao.setAttribute("idImovel", form.getIdImovel());
        sessao.setAttribute("periodoInicialDataInicioRelacao", form.getPeriodoInicialDataInicioRelacao());
        sessao.setAttribute("periodoFinalDataInicioRelacao", form.getPeriodoFinalDataInicioRelacao());
        sessao.setAttribute("periodoInicialDataFimRelacao", form.getPeriodoInicialDataFimRelacao());
        sessao.setAttribute("periodoFinalDataFimRelacao", form.getPeriodoFinalDataFimRelacao());
        sessao.setAttribute("idClienteImovelFimRelacaoMotivo", form.getIdClienteImovelFimRelacaoMotivo());
        sessao.setAttribute("idClienteRelacaoTipo", form.getIdClienteRelacaoTipo());
        sessao.setAttribute("situacaoRelacao", form.getSituacaoRelacao());
	}

	/**
	 *Este método valida os periodos de datas informados pelo usuario.
	 *Se alguma data inicial for maior que uma data final então uma exceção
	 *será lançada.
	 *
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 */
	private void validarPeriodosInformados(ConsultarRelacaoClienteImovelActionForm form) {

		if ( Util.verificarNaoVazio(form.getPeriodoFinalDataInicioRelacao()) ) {
        	if ( Util.verificarNaoVazio(form.getPeriodoFinalDataInicioRelacao())) {
        		Date periodoInicialDataInicioRelacao = Util.converteStringParaDate(form.getPeriodoInicialDataInicioRelacao());
        		Date periodoFinalDataInicioRelacao = Util.converteStringParaDate(form.getPeriodoFinalDataInicioRelacao());

        		if (periodoInicialDataInicioRelacao.compareTo(periodoFinalDataInicioRelacao) > 0) {
        			throw new ActionServletException("atencao.data.inicio.posterior.data.fim", null, "Início");
        		}
        
        	}
        }
        
        if (Util.verificarNaoVazio(form.getPeriodoInicialDataFimRelacao())) {
        	if (Util.verificarNaoVazio(form.getPeriodoFinalDataFimRelacao())) {
        		Date periodoInicialDataFimRelacao = Util.converteStringParaDate(form.getPeriodoInicialDataFimRelacao());
        		Date periodoFinalDataFimRelacao = Util.converteStringParaDate(form.getPeriodoFinalDataFimRelacao());

        		if (periodoInicialDataFimRelacao.compareTo(periodoFinalDataFimRelacao) > 0) {
        			throw new ActionServletException("atencao.data.inicio.posterior.data.fim", null, "Fim");
        		}
        	
        	}
        }
	}
}
