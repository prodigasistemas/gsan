package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Este caso de uso permite reativar um RA
 * 
 * @author Ana Maria
 * @date 30/06/2006
 */

public class ReativarRegistroAtendimentoAction extends GcomAction {
	/**
	 * Este caso de uso permite retivar um registro de atendimento
	 * 
	 * [UC0426] Reativar Registro de Atendimento
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

		ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        ReativarRegistroAtendimentoActionForm form = (ReativarRegistroAtendimentoActionForm) actionForm;
        
        Fachada fachada = Fachada.getInstancia();
        
		HttpSession sessao = httpServletRequest.getSession(false);
        
        validarParecerUnidadeDestino(form);
        
        if(!form.getIdUnidadeDestino().equals("") && form.getIdUnidadeDestino() != null){
          fachada.verificaPossibilidadeEncaminhamentoUnidadeDestino(Util.converterStringParaInteger(form.getIdUnidadeDestino()));
        }
        
        /* Validação dos dados de reativação
         * [FS0003] - Verificar data do atendimento
         * [FS0004] - Verificar hora do atendimento        
         * [FS0005] - Verificar tempo de espera inicial para atendimento
         * [FS0006] - Verificar tempo de espera final para atendimento
         * [FS0007] - Verificar tempo de espera final para atendimento menor que o início
         * [FS0009] - Verificar autorização da unidade de atendimento par abertura de ra
		 * Mesma validação usada no [UC0366] Inserir Registro Atendimento
		 */
        fachada.validarInserirRegistroAtendimentoDadosGerais(form.getDataAtendimentoReativado(),
        		form.getHoraAtendimentoReativado(), 
        		form.getTempoEsperaInicial(),
        		form.getTempoEsperaFinal(), 
        		form.getIdUnidadeAtendimento(), 
        		null);
        
		if(form.getParecerUnidadeDestino() != null && 
			!form.getParecerUnidadeDestino().equals("") && 
			form.getParecerUnidadeDestino().length() > 200){
					
			String[] msg = new String[2];
			msg[0]="Parecer";
			msg[1]="200";
				
			throw new ActionServletException("atencao.execedeu_limit_observacao",null,msg);
		}
		
        if(form.getObservacao() != null && 
				!form.getObservacao().equals("") && 
				form.getObservacao().length() > 200){
				
			String[] msg = new String[2];
			msg[0]="Observação";
			msg[1]="200";
				
			throw new ActionServletException("atencao.execedeu_limit_observacao",null,msg);
		}
		

		
        
		//Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");	
		
		validarUnidadeAtendimento(form, fachada, usuario);
 
        RegistroAtendimento ra = new RegistroAtendimento();
        
        form.setFormValues(ra);
        
        Integer[] idRAGerado = 
        	fachada.reativarRegistroAtendimento(ra, 
        		Util.converterStringParaInteger(form.getIdUnidadeAtendimento()),
        		usuario.getId(),
        		Util.converterStringParaInteger(form.getIdClienteSolicitante()) ,
        		form.getIdRaSolicitante() ,
        		Util.converterStringParaInteger(form.getIdUnidadeDestino()) ,
        		form.getParecerUnidadeDestino(), 
        		Util.converterStringParaInteger(form.getTipoSolicitacaoId()));
        
        sessao.removeAttribute("colecaoMeioSolicitacao");
        sessao.removeAttribute("colecaoMotivoReativacao");
        
        if (fachada.gerarOrdemServicoAutomatica(ra.getSolicitacaoTipoEspecificacao().getId())){
        	
        	montarPaginaSucesso(httpServletRequest, "Registro de Atendimento de código "
				    + idRAGerado[0] + " e Ordem de Serviço de código " + idRAGerado[1] + " inseridos com sucesso.", "Voltar",
                    "exibirConsultarRegistroAtendimentoAction.do?numeroRA="
					+ form.getNumeroRA());
        }
        else{
        	
        	montarPaginaSucesso(httpServletRequest, "Registro de Atendimento de código "
                    + idRAGerado[0] + " inserido com sucesso.", "Voltar",
                    "exibirConsultarRegistroAtendimentoAction.do?numeroRA="
					+ form.getNumeroRA());
        }
        	
	
		return retorno;
	}

	/**
	 * Parecer para a Unidade Destino(caso a Unidade Destino esteja preenchida,
	 * obrigatório; caso contrário, opcional)
	 */		
	private void validarParecerUnidadeDestino(ReativarRegistroAtendimentoActionForm form) {
		if((form.getParecerUnidadeDestino().equals("") || form.getParecerUnidadeDestino().trim().equals("")) &&
        		(form.getIdUnidadeDestino() != null && !form.getIdUnidadeDestino().equals(""))){
			throw new ActionServletException("atencao.informe_parecer_unidade_destino_selecionada");	
        }
	}
	
	/**
	 * [FS0001]Valida possibilidade de reativação
	 *
	 * Verificar se o indicador de autorização para manutenção do RA não possui permissão
	 * (valor correspondente a 2-não) 
	 */	
	private void validarUnidadeAtendimento(ReativarRegistroAtendimentoActionForm form, Fachada fachada, Usuario usuario) {
		Short indicadorAutorizacao = fachada.obterIndicadorAutorizacaoManutencaoRA(Util.converterStringParaInteger(form.getIdUnidadeAtendimento()), usuario.getId());
		if(indicadorAutorizacao == null || indicadorAutorizacao.equals(ConstantesSistema.NAO)){
			throw new ActionServletException("atencao.unidade_nao_autorizada",
					null, form.getUnidadeAtendimento());		
		}
	}
	
}
