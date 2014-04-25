package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.atendimentopublico.registroatendimento.ConjuntoTramitacaoRaActionForm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de encerra inserir
 * dados de pavimento na ordem serviço.
 * 
 * @author Pedro Alexandre
 * @created 03/01/2007
 */
public class InserirDadosPavimentoOrdemServicoPopupAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		String tramitarConjunto  =(String) sessao.getAttribute("tramitarConjunto");
		
		/*
		 * Caso o usuário esteja tramitando um conjuntode RA
		 * seta o mapeamento de retorno para a tela de tramitar
		 * conjunto de RA
		 * Caso contrário seta o mapeamento de retorno para 
		 * a página de tramitar RA individualmente.
		 */
		if(tramitarConjunto.equals("sim")){
			retorno = actionMapping.findForward("registroAtendimentoTramitacaoConjuntoAction");
		}else{
			retorno = actionMapping.findForward("registroAtendimentoTramitacaoAction");
		}
		
		//Recupera o imóvel da sessão
		Imovel imovel = (Imovel)sessao.getAttribute("imovel");
		
		ConjuntoTramitacaoRaActionForm conjuntoTramitacaoRaActionForm = (ConjuntoTramitacaoRaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		//Monta a ordem de serviço que esta sendo tratada.
		String numeroOS = conjuntoTramitacaoRaActionForm.getIdOrdemServico();
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setId(new Integer(numeroOS));
		
		//Recupera os dados de metragem de pavimento rua e calçada
	    String metragemPavimentoRua = conjuntoTramitacaoRaActionForm.getMetragemPavimentoRua();
	    String metragemPavimentoCalcada = conjuntoTramitacaoRaActionForm.getMetragemPavimentoCalcada(); 
		
	    BigDecimal areaPavimentoRua = null;
	    if(metragemPavimentoRua != null && !metragemPavimentoRua.trim().equals("")){
	    	areaPavimentoRua = new BigDecimal(metragemPavimentoRua);
	    }
	    	
	    BigDecimal areaPavimentoCalcada = null;
	    if(metragemPavimentoCalcada != null && !metragemPavimentoCalcada.trim().equals("")){
	    	areaPavimentoCalcada = new BigDecimal(metragemPavimentoCalcada);
	    }

	    
		Collection colecaoOrdemServico = null;
		
		//Adiciona a ordem de serviço a coleção de OS já tratadas.
		colecaoOrdemServico = (Collection)sessao.getAttribute("colecaoOrdemServicoJaTratada");
		if(colecaoOrdemServico != null && !colecaoOrdemServico.isEmpty()){
			colecaoOrdemServico.add(ordemServico);
		}else{
			colecaoOrdemServico = new ArrayList();
			colecaoOrdemServico.add(ordemServico);
		}
		
		sessao.setAttribute("colecaoOrdemServicoJaTratada",colecaoOrdemServico);
		
		//Inseri a OrdemServicoPavimento na base de dados.
		OrdemServicoPavimento ordemServicoPavimento = new OrdemServicoPavimento();
		ordemServicoPavimento.setOrdemServico(ordemServico);
		ordemServicoPavimento.setPavimentoRua(imovel.getPavimentoRua());
		ordemServicoPavimento.setAreaPavimentoRua(areaPavimentoRua);
		ordemServicoPavimento.setPavimentoCalcada(imovel.getPavimentoCalcada());
		ordemServicoPavimento.setAreaPavimentoCalcada(areaPavimentoCalcada);
		ordemServicoPavimento.setPavimentoRuaRetorno(null);
		ordemServicoPavimento.setAreaPavimentoRuaRetorno(null);
		ordemServicoPavimento.setPavimentoCalcadaRetorno(null);
		ordemServicoPavimento.setAreaPavimentoCalcadaRetorno(null);
		ordemServicoPavimento.setDataGeracao(new Date());
		
		fachada.inserirOrdemServicoPavimento(ordemServicoPavimento);
		
		
		return retorno;
	}
}
