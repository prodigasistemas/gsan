package gcom.gui.batch;

import gcom.batch.FiltroProcessoIniciado;
import gcom.batch.FuncionalidadeSituacao;
import gcom.batch.ProcessoIniciado;
import gcom.batch.ProcessoSituacao;
import gcom.batch.ProcessoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Remove os processos iniciados selecionados na lista da funcionalidade
 * Consultar Processo Iniciado
 * 
 * @author Genival Barbosa
 * @date 09/07/2009
 */
public class AutorizarProcessoIniciadoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		String[] ids = manutencaoRegistroActionForm.getIdRegistrosAutorizar();

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		// mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}
		
		
		for(int i=0; i<ids.length;i++) {
			
			FiltroProcessoIniciado filtroProcessoIniciado = new FiltroProcessoIniciado();
			
			filtroProcessoIniciado.adicionarCaminhoParaCarregamentoEntidade(FiltroProcessoIniciado.PROCESSO);
	        filtroProcessoIniciado.adicionarCaminhoParaCarregamentoEntidade(FiltroProcessoIniciado.USUARIO);
	        filtroProcessoIniciado.adicionarCaminhoParaCarregamentoEntidade(FiltroProcessoIniciado.PROCESSO_SITUACAO);
	  
			filtroProcessoIniciado.adicionarParametro(new ParametroSimples(FiltroProcessoIniciado.ID,ids[i]));
			 
	        Collection coll = fachada.pesquisar(filtroProcessoIniciado,ProcessoIniciado.class.getName());
	        
	        if (coll !=null && !coll.isEmpty()) {
	    		Iterator it = coll.iterator();
    			ProcessoIniciado processoIniciado = (ProcessoIniciado) it.next();
    			
    			if(!processoIniciado.getProcesso().getProcessoTipo().getId().equals(ProcessoTipo.RELATORIO)){
    				fachada.autorizarProcessoIniciado(processoIniciado,ProcessoSituacao.EM_ESPERA,FuncionalidadeSituacao.EM_ESPERA); 		
    			}else{
    				fachada.autorizarProcessoIniciado(processoIniciado,ProcessoSituacao.AGENDADO,FuncionalidadeSituacao.AGENDADA); 		
    			}
	    				
	    	}			
			
		}

		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest, ids.length
					+ " Processo(s) Iniciado(s) autorizado(s) com sucesso",
					"Autorizar Outro Relatório/Processo", "exibirAutorizarRelatoriosBatchAction.do?menu=sim"
					);
		}

		return retorno;
	}
}
