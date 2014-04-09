package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaAtividade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite consultar comandos de ação de cobrança 
 * [UC0325] Consultar Comandos de Ação de Conbrança
 * @author Rafael Santos
 * @since 11/05/2006
 */
public class ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoAction  extends GcomAction{
	
	
	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirComandosAcaoCobrancaCronogramaDadosComando");
        
        String idCobrancaAcaoAtividadeCronograma =  httpServletRequest.getParameter("idCobrancaAcaoAtividadeCronograma");
        
        Fachada fachada = Fachada.getInstancia();
        
        CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma =  fachada.obterCobrancaAcaoAtividadeCronograma(idCobrancaAcaoAtividadeCronograma);
        
        HttpSession sessao = httpServletRequest.getSession(false);        
        
        ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm = (ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm)actionForm;
/*        //limpar formulario
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setGrupoCobranca("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setReferenciaCobranca("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setAcaoCobranca("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setAtividadeCobranca("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setDataComando("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setHoraComando("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setValorDocumentos("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setQuantidadeDocumentos("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setQuantidadeItensDocumentos("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setSituacaoComando("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setSituacaoCronograma("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setDataPrevistaCronograma("");*/
        
        
        if(cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma() != null &&
        		cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes() != null
        		&& cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes().getCobrancaGrupo() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setGrupoCobranca(cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes().getCobrancaGrupo().getDescricao());
        }
        
        if(cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma() != null 
        		&& cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes() != null ){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setReferenciaCobranca(new Integer(cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes().getAnoMesReferencia()).toString());
        }
        if(cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma() != null 
        		&& cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaAcao() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setAcaoCobranca(cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaAcao().getDescricaoCobrancaAcao());
        }
        if(cobrancaAcaoAtividadeCronograma.getCobrancaAtividade() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setAtividadeCobranca(cobrancaAcaoAtividadeCronograma.getCobrancaAtividade().getDescricaoCobrancaAtividade());
        }
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setDataPrevistaCronograma(Util.formatarData(cobrancaAcaoAtividadeCronograma.getDataPrevista()));
        if(cobrancaAcaoAtividadeCronograma.getComando() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setDataComando(Util.formatarData(cobrancaAcaoAtividadeCronograma.getComando()));
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setHoraComando(Util.formatarHoraSemData(cobrancaAcaoAtividadeCronograma.getComando()));
        }
        if(cobrancaAcaoAtividadeCronograma.getRealizacao() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setDataRealizacao(Util.formatarData(cobrancaAcaoAtividadeCronograma.getRealizacao()));
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setHoraRealizacao(Util.formatarHoraSemData(cobrancaAcaoAtividadeCronograma.getRealizacao()));
        }
        if(cobrancaAcaoAtividadeCronograma.getValorDocumentos() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setValorDocumentos(cobrancaAcaoAtividadeCronograma.getValorDocumentos().toString());
        }
        if(cobrancaAcaoAtividadeCronograma.getQuantidadeDocumentos() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setQuantidadeDocumentos(cobrancaAcaoAtividadeCronograma.getQuantidadeDocumentos().toString());
        }
        if(cobrancaAcaoAtividadeCronograma.getQuantidadeItensCobrados() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setQuantidadeItensDocumentos(cobrancaAcaoAtividadeCronograma.getQuantidadeItensCobrados().toString());
        }
        
        if(cobrancaAcaoAtividadeCronograma.getComando() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setSituacaoComando("Comandado");
        	
        }else{
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setSituacaoComando("Não Comandado");
        }
              
        if(cobrancaAcaoAtividadeCronograma.getRealizacao() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setSituacaoCronograma("Realizado");
        	if(cobrancaAcaoAtividadeCronograma.getCobrancaAtividade().getId().equals(CobrancaAtividade.EMITIR)){
          	  httpServletRequest.setAttribute("emitir", "sim");	
          	}
        }else{
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setSituacaoCronograma("Não Realizado");
        }
        
        if(fachada.verificarPermissaoEmissaoDocumentoCobranca((Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO))){
        	 httpServletRequest.setAttribute("permissaoEmitir", "sim");	
        }
        
        httpServletRequest.setAttribute("idCobrancaAcaoAtividadeCronograma", idCobrancaAcaoAtividadeCronograma);
        sessao.setAttribute("cobrancaAcaoAtividadeCronograma", cobrancaAcaoAtividadeCronograma);                
		        
        return retorno;
    }

}
