package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.bean.ComandoNegativacaoHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC 0653] Pesquisar Comando Negativação
 * 
 * @author Kássia Albuquerque
 * @date 22/10/2007
 */



public class PesquisarComandoNegativacaoAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("exibirResultadoPesquisaComandoNegativacao");
			
			HttpSession sessao = httpServletRequest.getSession(false);
	
			PesquisarComandoNegativacaoActionForm form = (PesquisarComandoNegativacaoActionForm) actionForm;
			
			// Recupera todos os campos da página para ser colocada no filtro posteriomente
			
		
			String tituloComando = form.getTituloComando();
			String tipoPesquisa = form.getTipoPesquisa();
			String indicadorComandoSimulado = form.getIndicadorComandoSimulado();
			String periodoGeracaoComandoInicial = form.getPeriodoGeracaoComandoInicial();
			String periodoGeracaoComandoFinal = form.getPeriodoGeracaoComandoFinal();
			String periodoExecucaoComandoInicial = form.getPeriodoExecucaoComandoInicial();
			String periodoExecucaoComandoFinal = form.getPeriodoExecucaoComandoFinal();
			String usuarioResponsavelId = form.getUsuarioResponsavelId();
			
			ComandoNegativacaoHelper comandoNegativacaoHelper = new ComandoNegativacaoHelper();
			
			boolean peloMenosUmParametroInformado = false;
	
			//	TITULO COMANDO
			if (tituloComando != null && !tituloComando.trim().equals("")){
	
				peloMenosUmParametroInformado = true;
				comandoNegativacaoHelper.setTituloComando(tituloComando);
				comandoNegativacaoHelper.setTipoPesquisaTituloComando(new Short(tipoPesquisa));
	
			}
		
			//   COMANDO SIMULADO
			if (indicadorComandoSimulado != null && !indicadorComandoSimulado.equals("") ) {
				
				if(!indicadorComandoSimulado.equals(ConstantesSistema.TODOS.toString())){
					peloMenosUmParametroInformado = true;
				}
				
				comandoNegativacaoHelper.setIndicadorComandoSimulado(new Short(indicadorComandoSimulado));
			}
			
			//	PERIODO GERAÇÃO DO COMANDO
			if ((periodoGeracaoComandoInicial!= null && periodoGeracaoComandoFinal != null)
					&&(!periodoGeracaoComandoInicial.equalsIgnoreCase("")&& !periodoGeracaoComandoFinal.equalsIgnoreCase(""))) {
				
				peloMenosUmParametroInformado = true;
				comandoNegativacaoHelper.setGeracaoComandoInicio(Util.converteStringParaDate(periodoGeracaoComandoInicial));
				comandoNegativacaoHelper.setGeracaoComandoFim(Util.converteStringParaDate(periodoGeracaoComandoFinal));
			}
			
			
			 //	PERIODO EXECUÇÃO DO COMANDO
			if ((periodoExecucaoComandoInicial!= null && periodoExecucaoComandoFinal != null)
					&&(!periodoExecucaoComandoInicial.equalsIgnoreCase("")&& !periodoExecucaoComandoFinal.equalsIgnoreCase(""))) {
				
				peloMenosUmParametroInformado = true;
				comandoNegativacaoHelper.setExecucaoComandoInicio(Util.converteStringParaDate(periodoExecucaoComandoInicial));
				comandoNegativacaoHelper.setExecucaoComandoFim(Util.converteStringParaDate(periodoExecucaoComandoFinal));
			}
	
			//	USUARIO RESPONSAVEL
			if (usuarioResponsavelId != null && !usuarioResponsavelId.equals("")) {
	
				peloMenosUmParametroInformado = true;
				comandoNegativacaoHelper.setIdUsuarioResponsavel(new Integer(usuarioResponsavelId));
	
			}
	
			// Erro caso o usuário mandou filtrar sem nenhum parâmetro
			if (!peloMenosUmParametroInformado) {
				throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
			}
	
			// Manda o Helper pela sessao para o ExibirResultadoPesquisaComandoNegativacaoAction
			
			sessao.setAttribute("comandoNegativacaoHelper", comandoNegativacaoHelper);
	
	
			
			return retorno;
	}
}
