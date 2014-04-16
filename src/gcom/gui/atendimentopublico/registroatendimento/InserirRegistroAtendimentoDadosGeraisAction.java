package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Esta classe tem por finalidade validar as informações da primeira aba do processo de inserção
 * de um registro de atendimento
 *
 * @author Raphael Rossiter
 * @date 25/07/2006
 */
public class InserirRegistroAtendimentoDadosGeraisAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("");
        
        InserirRegistroAtendimentoActionForm inserirRegistroAtendimentoActionForm = 
        (InserirRegistroAtendimentoActionForm) actionForm;
        
    	HttpSession sessao = httpServletRequest.getSession(false);
        
        Fachada fachada = Fachada.getInstancia();
        
        sessao.removeAttribute("gis");	
        
        FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID, inserirRegistroAtendimentoActionForm.getEspecificacao()));
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_ENCERRAMENTO_AUTOMATICO, SolicitacaoTipoEspecificacao.INDICADOR_COM_ENCERRAMENTO_AUTOMATICO));
		
		Collection colecaoSolicitacaoTipoEspecificacao = fachada
				.pesquisar(filtroSolicitacaoTipoEspecificacao,
						SolicitacaoTipoEspecificacao.class.getName());
		
		// Caso a especificação seja de encerramento automático, verifica se a observação foi informada
		if (colecaoSolicitacaoTipoEspecificacao != null && !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
			if (inserirRegistroAtendimentoActionForm.getObservacao() == null || inserirRegistroAtendimentoActionForm.getObservacao().trim().equals("")) {
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Observação");
			}
		}
		
		if(inserirRegistroAtendimentoActionForm.getObservacao() != null && 
				!inserirRegistroAtendimentoActionForm.getObservacao().equals("") && 
				inserirRegistroAtendimentoActionForm.getObservacao().length() > 400){

			String[] msg = new String[2];
			msg[0]="Observação";
			msg[1]="400";
				
			throw new ActionServletException("atencao.execedeu_limit_observacao",null,msg);
		}
        
        fachada.validarInserirRegistroAtendimentoDadosGerais(inserirRegistroAtendimentoActionForm.getDataAtendimento(),
        inserirRegistroAtendimentoActionForm.getHora(), inserirRegistroAtendimentoActionForm.getTempoEsperaInicial(),
        inserirRegistroAtendimentoActionForm.getTempoEsperaFinal(), inserirRegistroAtendimentoActionForm.getUnidade(),
        inserirRegistroAtendimentoActionForm.getNumeroAtendimentoManual());
	
        return retorno;
	}

}
