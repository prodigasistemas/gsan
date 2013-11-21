package gcom.gui.cobranca.cobrancaporresultado;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimplesIn;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável por validar os dados informados na página de Encerrar OS do processo 
 * de movimentar ordem de serviço de cobrança por resultado.
 *
 * @author Mariana Victor
 * @date 10/05/2011
 */
public class MovimentarOrdemServicoEncerrarOSAction extends GcomAction {
   
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

        ActionForward retorno = actionMapping.findForward("movimentarOrdemServicoAction");
        
        //Cria uma instância da sessão 
        HttpSession sessao = httpServletRequest.getSession(false);

        MovimentarOrdemServicoActionForm form = (MovimentarOrdemServicoActionForm) actionForm;
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

        //Se clicou no botão de "Encerrar"
        if (httpServletRequest.getParameter("concluir") != null
				&& (httpServletRequest.getParameter("concluir")).toString().equalsIgnoreCase("true")) {
        	
        	String mensagem = validarForm(form, sessao);
			
			if (mensagem != null && !mensagem.equals("")) {
				throw new ActionServletException(
						mensagem);
			}
			MovimentarOrdemServicoEncerrarOSHelper helper = this.montarHelper(form);
			
			Fachada.getInstancia().movimentarOrdemServicoEncerrarOS(helper, usuarioLogado);
			
			httpServletRequest.setAttribute("tipoMovimentacao", "Encerrada(s)");
        }
        
        //Retorna o mapemaneto na variável "retorno"
        return retorno;
    }

    public String validarForm(MovimentarOrdemServicoActionForm form, HttpSession sessao) {
    	
    	if (form.getIdMotivoEncerramento() == null
    			|| form.getIdMotivoEncerramento().equals("")
    			|| form.getIdMotivoEncerramento().equals("-1")) {
    		return "atencao.informe.motivo_encerramento";
    	}
    	
    	if (form.getDataEncerramento() == null
    			|| form.getDataEncerramento().equals("")) {
    		
    		return "atencao.informe.data_encerramento";
    		
    	} else if (Util.validarDiaMesAno(form.getDataEncerramento())) {
    		
    		return "atencao.data_encerramento.invalida";
    		
    	}
    	
    	if (form.getHoraEncerramento() != null
    			&& !form.getHoraEncerramento().equals("")){
  
    		String dataHora =  form.getDataEncerramento().substring(6, 10) 
				    			+ "-" + form.getDataEncerramento().substring(3, 5) 
				    			+ "-" + form.getDataEncerramento().substring(0, 2);
    		
    		dataHora += " " + form.getHoraEncerramento() + ":00";
    		
    		if(Util.validarDataHoraInvalida(dataHora, "yyyy-MM-dd HH:mm:ss")) {
    			return "atencao.hora_encerramento.invalida";
    		}
    	}
    	
    	Collection<AtendimentoMotivoEncerramento> colecaoAtendimentoMotivoEncerramento = (Collection<AtendimentoMotivoEncerramento>)
							sessao.getAttribute("colecaoAtendimentoMotivoEncerramento");
	
		Iterator iterator = colecaoAtendimentoMotivoEncerramento.iterator();
		boolean indicadorEncontrado = false;
		
		while(iterator.hasNext()) {
			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = (AtendimentoMotivoEncerramento) iterator.next();
			
			if (atendimentoMotivoEncerramento.getId().compareTo(new Integer(form.getIdMotivoEncerramento())) == 0) {
				
				if (atendimentoMotivoEncerramento.getIndicadorExecucao() != 0
						&& (new Short(atendimentoMotivoEncerramento.getIndicadorExecucao())).compareTo(ConstantesSistema.NAO) == 0) {
					indicadorEncontrado = true;
				} 
				break;
			}
		}
		
		List<Integer> numerosOSPesquisar = new ArrayList();
		
		if (indicadorEncontrado){
			if (form.getNumeroOSInicial() != null && !form.getNumeroOSInicial().equals("")
					&& (form.getNumeroOSFinal() == null || form.getNumeroOSFinal().equals(""))){
				
				return "atencao.informe.ordens_servico.final";
				
			} else if (form.getNumeroOSInicial() != null && !form.getNumeroOSInicial().equals("")
					&& form.getNumeroOSFinal() != null && !form.getNumeroOSFinal().equals("")){

				if ((new Integer(form.getNumeroOSInicial()))
						.compareTo(new Integer(form.getNumeroOSFinal())) > -1) {
					return "atencao.ordem_servico.final.menor_que.inicial";
				}

				int numeroInicial = new Integer(form.getNumeroOSInicial());
				int numeroFinal = new Integer(form.getNumeroOSFinal());
				
				for (int i = numeroInicial; i <= numeroFinal; i++) {
					numerosOSPesquisar.add(i);
				}
				
			} else if ((form.getNumeroOSInicial() == null || form.getNumeroOSInicial().equals(""))
				&& (form.getNumeroOSFinal() == null || form.getNumeroOSFinal().equals(""))){
			
				boolean numeroOSEncontrado = false;
				
				if (form.getNumerosOS() == null || form.getNumerosOS().length == 0) {
					return "atencao.informe.alguma.ordens_servico";
				}
				
				for (int i = 0; i < form.getNumerosOS().length; i++) {
					
					if (form.getNumerosOS()[i] != null
							&& !form.getNumerosOS()[i].equals("")) {
						
						numerosOSPesquisar.add(new Integer(form.getNumerosOS()[i]));
						numeroOSEncontrado = true;
					}
				}
				
				if (!numeroOSEncontrado) {
					return "atencao.informe.alguma.ordens_servico";
				}
			}
		} else {
			if(!(form.getNumerosOS() != null 
						&& form.getNumerosOS().length != 0
						&& form.getNumerosOS()[0] != null
			            && !form.getNumerosOS()[0].equals(""))){

				return "atencao.informe.alguma.ordens_servico";
			} else {
				numerosOSPesquisar.add(new Integer(form.getNumerosOS()[0]));
			}
		}
		
		if (numerosOSPesquisar != null && !numerosOSPesquisar.isEmpty()) {
			FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
			
			filtroOrdemServico.adicionarParametro(new ParametroSimplesIn(
					FiltroOrdemServico.ID, numerosOSPesquisar));
			
			Collection colecaoOrdemServico = Fachada.getInstancia().pesquisar(filtroOrdemServico, OrdemServico.class.getName());
			
			if (colecaoOrdemServico != null && !colecaoOrdemServico.isEmpty()) {
				form.setColecaoOrdemServico(colecaoOrdemServico);
			} else {
				return "atencao.ordens_servico.inexistente";
			}
		}
    	
    	return null;
    }
    
    private MovimentarOrdemServicoEncerrarOSHelper montarHelper(MovimentarOrdemServicoActionForm form){
    	MovimentarOrdemServicoEncerrarOSHelper helper = new MovimentarOrdemServicoEncerrarOSHelper();
    	
    	String data = form.getDataEncerramento();
    	
    	if (form.getHoraEncerramento() != null && !form.getHoraEncerramento().equals("")) {
    		data = data + " " + form.getHoraEncerramento() + ":00";
    	} else {
    		data = data + " 00:00:00";
    	}

    	Date dataEncerramento = Util.converteStringParaDateHora(data);
    	
    	helper.setColecaoOrdemServico(form.getColecaoOrdemServico());
    	
    	helper.setDataEncerramento(dataEncerramento);
    	
    	helper.setIdMotivoEncerramento(form.getIdMotivoEncerramento());
    	
    	helper.setObservacaoEncerramento(form.getObservacaoEncerramento());
    	
    	return helper;
    }
}
