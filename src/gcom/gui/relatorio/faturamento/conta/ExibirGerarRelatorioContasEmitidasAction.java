package gcom.gui.relatorio.faturamento.conta;

import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pré-processamento da primeira página de [UC0345] Gerar Relatório de Resumo do
 * Arrecadacao
 * 
 * @author Vivianne Sousa
 */
public class ExibirGerarRelatorioContasEmitidasAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioContasEmitidas");
		
		GerarRelatorioContasEmitidasActionForm form = (GerarRelatorioContasEmitidasActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
        HttpSession sessao = httpServletRequest.getSession(false);
        
        
        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
        filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
                FiltroFaturamentoGrupo.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
     
        Collection colecaoGrupoFaturamento = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
      
        if (!colecaoGrupoFaturamento.isEmpty()) {
            sessao.setAttribute("colecaoGrupoFaturamento", colecaoGrupoFaturamento);
        } else {
            throw new ActionServletException("erro.naocadastrado", null,
                    "grupo de faturamento");
        }
        
        
        FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
        filtroEsferaPoder.adicionarParametro(new ParametroSimples(
        		FiltroEsferaPoder.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
    	filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);
    	
    	Collection colecaoEsferaPoder = fachada.pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
    	
    	if (!colecaoEsferaPoder.isEmpty()) {
    		sessao.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
        } else {
            throw new ActionServletException("erro.naocadastrado", null,
                    "esfera poder");
        }
    	
    	if(httpServletRequest.getParameter("menu")!=null
    			&& httpServletRequest.getParameter("menu").toString().equalsIgnoreCase("SIM")){
    		// 	Caso tenha sido chamada pelo menu
    		// inicializa o tipo de impressão com
    		// o valor NORMAL.
    		form.setTipoImpressao("1");
    	}

		return retorno;
	}
	
}
