package gcom.gui.faturamento.conta;


import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

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
 * [UC0407] Filtrar Imóveis para Inserir ou Manter Conta
 * 
 * @author Ana Maria
 * @created 16/03/2007
 */
public class ExibirManterContaConjuntoImovelAction extends GcomAction {
    
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("manterContaConjuntoImovel");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ManterContaConjuntoImovelActionForm manterContaConjuntoImovelActionForm = (ManterContaConjuntoImovelActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoImovel = new ArrayList();
		
		if(manterContaConjuntoImovelActionForm.getIndicadorContaPaga() == null){
			manterContaConjuntoImovelActionForm.setIndicadorContaPaga("2");
		}
		
		if(sessao.getAttribute("colecaoImovel") != null){
			colecaoImovel = (Collection)sessao.getAttribute("colecaoImovel");	
		}
		
		manterContaConjuntoImovelActionForm.setQuatidadeImovel(Integer.toString(colecaoImovel.size()));
		
		Date dataVencimentoContaInicio = null;
		Date dataVencimentoContaFim = null;
		
		if (manterContaConjuntoImovelActionForm.getDataVencimentoInicial() != null &&
			!manterContaConjuntoImovelActionForm.getDataVencimentoInicial().equals("")){
			
			dataVencimentoContaInicio = Util.converteStringParaDate(manterContaConjuntoImovelActionForm.getDataVencimentoInicial());
		}
		
		if (manterContaConjuntoImovelActionForm.getDataVencimentoFinal() != null &&
			!manterContaConjuntoImovelActionForm.getDataVencimentoFinal().equals("")){
			
			dataVencimentoContaFim = Util.converteStringParaDate(manterContaConjuntoImovelActionForm.getDataVencimentoFinal());
		}
		
		//Cliente Superior
		if(sessao.getAttribute("codigoClienteSuperior") != null && sessao.getAttribute("nomeCliente") != null){
			manterContaConjuntoImovelActionForm.setCodigoClienteSuperior((String)sessao.getAttribute("codigoClienteSuperior"));	
			manterContaConjuntoImovelActionForm.setNomeCliente((String)sessao.getAttribute("nomeCliente"));
			httpServletRequest.setAttribute("cliente", "ok");
			httpServletRequest.setAttribute("superior", "ok");			
		}
		//Cliente Imóvel
		if(sessao.getAttribute("codigoCliente") != null && sessao.getAttribute("nomeCliente") != null){
			manterContaConjuntoImovelActionForm.setCodigoCliente( (String) sessao.getAttribute("codigoCliente"));	
			manterContaConjuntoImovelActionForm.setNomeCliente((String)sessao.getAttribute("nomeCliente"));
			httpServletRequest.setAttribute("cliente", "ok");
		}
		
		//Inscrição Imóvel
		if(sessao.getAttribute("inscricaoInicialImovel") != null){
			manterContaConjuntoImovelActionForm.setInscricaoInicial((String)sessao.getAttribute("inscricaoInicialImovel"));
		}
		else{
			manterContaConjuntoImovelActionForm.setInscricaoInicial("");
		}
		
		if(sessao.getAttribute("inscricaoDestinoImovel") != null){
			manterContaConjuntoImovelActionForm.setInscricaoFinal((String)sessao.getAttribute("inscricaoDestinoImovel"));			
		}
		else{
			manterContaConjuntoImovelActionForm.setInscricaoFinal("");
		}
		
		
		String idGrupoFaturamentoSessao = (String) sessao.getAttribute("grupoFaturamento"); 
		Integer idGrupoFaturamento = null;
		
		if (idGrupoFaturamentoSessao != null && !idGrupoFaturamentoSessao.equals("")){
			manterContaConjuntoImovelActionForm.setIdGrupoFaturamento(idGrupoFaturamentoSessao);
			idGrupoFaturamento = new Integer(idGrupoFaturamentoSessao);
		}
		else{
			manterContaConjuntoImovelActionForm.setIdGrupoFaturamento("");
		}
		
		Integer quantidadeConta = 0;
		Integer quantidadeContaRevisao = 0;
		Integer codigoCliente = null;
		if(sessao.getAttribute("codigoCliente") != null){
			codigoCliente = new Integer((String)sessao.getAttribute("codigoCliente"));
		}
		
		Short relacaoTipo = null;
		if(sessao.getAttribute("relacaoTipo") != null){
		   relacaoTipo = ((Integer)sessao.getAttribute("relacaoTipo")).shortValue();
		}
		
		if(httpServletRequest.getParameter("quantidadeConta") != null){
		  
			Integer anoMes = Util.formatarMesAnoComBarraParaAnoMes(manterContaConjuntoImovelActionForm.getMesAnoConta());
			Integer anoMesFinal = Util.formatarMesAnoComBarraParaAnoMes(manterContaConjuntoImovelActionForm.getMesAnoContaFinal());
			String indicadorContaPaga = manterContaConjuntoImovelActionForm.getIndicadorContaPaga(); 
			
			if(sessao.getAttribute("bancos") != null){
				String[] bancos = (String[]) sessao.getAttribute("bancos");
				/*TODO: COSANPA
				 * autor: Adriana Muniz
				 * 
				 * Alteração para a conta considerar como filtro grupo de faturamento, quando o mesmo estiver preenchido
				 * */
				if (idGrupoFaturamento != null) {
					quantidadeConta = fachada
					.countImoveisBancoDebitoAutomaticoPorGrupoFaturamento(bancos, anoMes,
							anoMesFinal, dataVencimentoContaInicio,
							dataVencimentoContaFim, indicadorContaPaga, idGrupoFaturamento);
				} else {
					quantidadeConta = fachada
							.countImoveisBancoDebitoAutomatico(bancos, anoMes,
									anoMesFinal, dataVencimentoContaInicio,
									dataVencimentoContaFim, indicadorContaPaga);
				}
				
				//Collection imoveis = fachada.selecionarImoveisBancoDebitoAutomatico(bancos, anoMes,
					//	anoMesFinal, dataVencimentoContaInicio, dataVencimentoContaFim, indicadorContaPaga);
				
			}else{
				quantidadeConta = fachada.obterContasConjuntoImoveis(anoMes, colecaoImovel, codigoCliente, relacaoTipo,
						  dataVencimentoContaInicio, dataVencimentoContaFim, idGrupoFaturamento, anoMesFinal, indicadorContaPaga);	
				  
				quantidadeContaRevisao = fachada.obterContasRevisaoConjuntoImoveis(anoMes, colecaoImovel, codigoCliente, relacaoTipo,
							  dataVencimentoContaInicio, dataVencimentoContaFim, idGrupoFaturamento, anoMesFinal, indicadorContaPaga);
			}
			
			if(!quantidadeConta.equals(quantidadeContaRevisao)){
				sessao.setAttribute("contaRevisao", "contaRevisao");
			}else{
				sessao.removeAttribute("contaRevisao");
			}
			
		  manterContaConjuntoImovelActionForm.setQuatidadeConta(quantidadeConta.toString());
		}else if(httpServletRequest.getParameter("cancelar") == null){
			manterContaConjuntoImovelActionForm.setMesAnoConta("");
			manterContaConjuntoImovelActionForm.setMesAnoContaFinal("");
			manterContaConjuntoImovelActionForm.setDataVencimentoInicial("");
			manterContaConjuntoImovelActionForm.setDataVencimentoFinal("");
			manterContaConjuntoImovelActionForm.setQuatidadeConta("");
		}
		
		//Seleciona a quantidade de contas depois do cancelamento
		if(httpServletRequest.getParameter("cancelar") != null){
			httpServletRequest.setAttribute("reloadPage", "ok");
			httpServletRequest.setAttribute("mensagemSuceso","Cancelar conjunto de contas encaminhado para processamento.");			
		}
		
		//Fechar popup de retirar débito cobrado
		if(httpServletRequest.getParameter("fecharPopup") != null){
			sessao.removeAttribute("debitosTipoRetirar");
			sessao.removeAttribute("RetirarDebitoCobradoActionForm");
			
		}	
		
		if(httpServletRequest.getParameter("mensagemSuceso") != null){
			httpServletRequest.setAttribute("mensagemSuceso","Alterar vencimento conjunto de contas encaminhado para processamento.");
		}
		
		if(httpServletRequest.getParameter("mensagemSucesoRetificarConjuntoConta") != null){
			httpServletRequest.setAttribute("mensagemSuceso","Retificação de um conjunto de contas foi para batch.");
		}
        
        /* Colocado por Bruno Barros em 05 de Janeiro de 2009
         * Verificamos se o usuário possue permissão especial para atualizar 
         * ou retificar contas pagas
         */
        
        boolean usuarioPodeAtualizarRetificarContasPagas =
            fachada.verificarPermissaoEspecial( 
                    PermissaoEspecial.ATUALIZAR_RETIFICAR_CONTAS_PAGAS, 
                    ( Usuario ) sessao.getAttribute(Usuario.USUARIO_LOGADO ) );
        
        httpServletRequest.
            setAttribute(
                    "usuarioPodeAtualizarRetificarContasPagas",
                    usuarioPodeAtualizarRetificarContasPagas );
        
        // *****************************************************************        
		
		return retorno;
	}
}
