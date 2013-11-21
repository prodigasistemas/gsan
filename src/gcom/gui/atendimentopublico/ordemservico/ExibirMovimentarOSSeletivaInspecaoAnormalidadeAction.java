package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ComandoOrdemSeletiva;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
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
 * [UC1192] Movimentar OS Seletiva de Inspeção de Anormalidade
 * 
 * Action responsável por montar todo o esquema do 
 * processo de movimentar ordens de serviço.
 * 
 * @author Vivianne Sousa
 * @date 14/07/2011
 */
public class ExibirMovimentarOSSeletivaInspecaoAnormalidadeAction extends GcomAction {

    /**
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

        //Localiza o action no objeto
        ActionForward retorno = actionMapping.findForward("movimentarOSSeletivaInspecaoAnormalidadeEmitirOS");
        
        MovimentarOSSeletivaInspecaoAnormalidadeActionForm form = (MovimentarOSSeletivaInspecaoAnormalidadeActionForm) actionForm;
    	
        //Obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        if (httpServletRequest.getParameter("desfazer") != null
        		&& httpServletRequest.getParameter("desfazer").equalsIgnoreCase("true")) {

        	this.limparForm(form);
        	this.limparSessao(sessao);
        	
        	this.pesquisarColecoes(sessao, form);
        }
      
        if (httpServletRequest.getParameter("comando") != null
        		&& !httpServletRequest.getParameter("comando").equals("")) {
        	
        	form.setIdComando(httpServletRequest.getParameter("comando"));
        	
        	ComandoOrdemSeletiva comandoOrdemSeletiva = this.getFachada().pesquisarComandoOSSeletiva(new Integer(form.getIdComando()));
        	
        	if(comandoOrdemSeletiva == null){
        		throw new ActionServletException("atencao.comando.inexistente");
        	}else{
//        		if (comandoOrdemSeletiva.getDataExecucao() == null) {
//	        		throw new ActionServletException("atencao.comando.nao_executado.nao_possivel.movimentacao");
//	        	}
	        	
//	        	if (comandoOrdemSeletiva.getIndicadorGeracaoTxt() == null || 
//	        			comandoOrdemSeletiva.getIndicadorGeracaoTxt().compareTo(ConstantesSistema.NAO) == 0) {
//	        		throw new ActionServletException("atencao.comando.arquivo_txt.nao_gerado.nao_possivel.movimentacao");
//	        	}
	        	
	        	if (comandoOrdemSeletiva.getDataEncerramento() != null) {
	        		throw new ActionServletException("atencao.comando.ja_encerrado.nao_possivel.movimentacao");
	        	}
	        	
	        	form.setIdEmpresa(comandoOrdemSeletiva.getEmpresa().getId().toString());
        	}
        	
   
			
        	this.pesquisarColecoes(sessao, form);
		}

        //Monta o Status do Wizard
        StatusWizard statusWizard = new StatusWizard(
                "movimentarOSSeletivaInspecaoAnormalidadeWizardAction", "movimentarOSSeletivaInspecaoAnormalidadeAction",
                "cancelarMovimentarOrdemServicoAction", null);
        
        //monta a primeira aba do processo
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        1, "abaEmitirOSA.gif", "abaEmitirOSD.gif",
                        "exibirMovimentarOSSeletivaInspecaoAnormalidadeEmitirOSAction",
                        "movimentarOSSeletivaInspecaoAnormalidadeEmitirOSAction"));
        
        //monta a segunda aba do processo
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        2, "abaGerarOSA.gif", "abaGerarOSD.gif",
                        "exibirMovimentarOSSeletivaInspecaoAnormalidadeGerarOSAction",
                        "movimentarOSSeletivaInspecaoAnormalidadeGerarOSAction"));
        
        //monta a terceira aba do processo
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        3, "abaEncerrarOSA.gif", "abaEncerrarOSD.gif",
                        "exibirMovimentarOSSeletivaInspecaoAnormalidadeEncerrarOSAction",
                        "movimentarOSSeletivaInspecaoAnormalidadeEncerrarOSAction"));

        statusWizard.setCaminhoActionDesfazer("exibirMovimentarOSSeletivaInspecaoAnormalidadeAction.do");
        statusWizard.setCaminhoActionVoltarFiltro("exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction");
        statusWizard.setNomeBotaoConcluir("Emitir OS");
        
        //manda o statusWizard para a sessão
        sessao.setAttribute("statusWizard", statusWizard);
        
        //retorna o mapeamento contido na variável retorno
        return retorno;
    }
    
    private void limparForm(MovimentarOSSeletivaInspecaoAnormalidadeActionForm form){

    	form.setNumeroOSInicialEmitir("");
    	form.setNumeroOSFinalEmitir("");
    	form.setNumerosOSEmitir(new String[10]);
    	
    	form.setNumeroOSInicial("");
    	form.setNumeroOSFinal("");
    	
    	form.setTipoPesquisa("");

    	
    	form.setIdTipoServico("");
    	form.setMatriculasImoveis(new String[10]);
    	
    	form.setNumerosOS(new String[10]);
    	form.setIdMotivoEncerramento("");
    	form.setDataEncerramento("");
    	form.setHoraEncerramento("");
    	form.setObservacaoEncerramento("");
    }
    
    private void limparSessao(HttpSession sessao){

    	sessao.removeAttribute("colecaoOSEmitir");
    	sessao.removeAttribute("motivoInformado");
    	sessao.removeAttribute("habilitaNumerosOS");
    	sessao.removeAttribute("colecaoOSEncerrar");
    	
    }
    
    private void pesquisarColecoes(
    		HttpSession sessao,
    		MovimentarOSSeletivaInspecaoAnormalidadeActionForm form) {
    	
    	
		// Coleção de Tipos de Serviço
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
		filtroServicoTipo.adicionarParametro(new ParametroSimples(
				FiltroServicoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroServicoTipo.adicionarParametro(new ParametroSimples(
				FiltroServicoTipo.INDICADOR_GERAR_OS_INSP_ANORMALIDADE, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroServicoTipo.setCampoOrderBy(FiltroServicoTipo.DESCRICAO);

		Collection<ServicoTipo> colecaoServicoTipo = 
			this.getFachada().pesquisar(
				filtroServicoTipo, 
				ServicoTipo.class.getName());

		sessao.setAttribute("colecaoServicoTipo", colecaoServicoTipo);
    	
		// Coleção de Motivo de Encerramento de OS
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
		filtroAtendimentoMotivoEncerramento.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);
		filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(
				FiltroAtendimentoMotivoEncerramento.INDICADOR_DUPLICIDADE, ConstantesSistema.NAO));
		filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(
				FiltroAtendimentoMotivoEncerramento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<AtendimentoMotivoEncerramento> colecaoAtendimentoMotivoEncerramento = 
			this.getFachada().pesquisar(
				filtroAtendimentoMotivoEncerramento, 
				AtendimentoMotivoEncerramento.class.getName());

		sessao.setAttribute("colecaoAtendimentoMotivoEncerramento", colecaoAtendimentoMotivoEncerramento);
    }
}
