package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.bean.ParametrosComandoNegativacaoHelper;
import gcom.fachada.Fachada;
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
 * @date 08/11/2007
 */


public class ExibirConsultarParametrosComandoNegativacaoPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("consultarParametrosComandoNegativacaoPopup");
	
			ConsultarParametrosComandoNegativacaoActionForm form = (ConsultarParametrosComandoNegativacaoActionForm) actionForm;
	
			HttpSession sessao = httpServletRequest.getSession(false);
	
			Fachada fachada = Fachada.getInstancia();

			
			String popup = (String) sessao.getAttribute("popup");
			if (popup != null && popup.equals("2")) {
				sessao.setAttribute("popup", popup);
			} else {
				sessao.removeAttribute("popup");
			}
			
			
			/*
			 * 
			 * [SB0002 - Consultar Parâmetro do Comando Negativação]
			 */
			
			if (httpServletRequest.getParameter("idComandoNegativacao")!= null){
				
				Integer idComandoNegativacao = new Integer(httpServletRequest.getParameter("idComandoNegativacao"));
				
				ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper = 
					fachada.pesquisarParametrosComandoNegativacao(idComandoNegativacao);
				
				form.reset();
		        //Removendo todos os objetos da sessão 
		        // Aba 01
		        sessao.removeAttribute("colecaoNegativacaoCriterioCpfTipo");
		        
		        //Aba 03
		        sessao.removeAttribute("colecaoClienteRelacaoTipo");
		        sessao.removeAttribute("colecaoSubcategoria");
		        sessao.removeAttribute("colecaoPerfilImovel");
		        sessao.removeAttribute("colecaoTipoCliente");
		        
		        //Aba 04
		        sessao.removeAttribute("colcaoCobrancaGrupo");
		        sessao.removeAttribute("colecaoGerenciaRegional");        
		        sessao.removeAttribute("colecaoUnidadeNegocio");
		        sessao.removeAttribute("colecaoEloPolo");
				
				form = setFormDadosGerais(form, sessao, parametrosComandoNegativacaoHelper);
					
				form = setFormDadosDebito(form, parametrosComandoNegativacaoHelper);
					
				form = setFormDadosImovel(form, sessao,parametrosComandoNegativacaoHelper);
					
				form = setFormDadosLocalizacao(form, sessao, parametrosComandoNegativacaoHelper);
				
			}
			return retorno;
		}

private ConsultarParametrosComandoNegativacaoActionForm setFormDadosLocalizacao(ConsultarParametrosComandoNegativacaoActionForm form, 
		HttpSession sessao, ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper) {
		
		// Grupo de Cobrança
		if (parametrosComandoNegativacaoHelper.getColecaoGrupoCobranca()!= null){
			
			sessao.setAttribute("colecaoGrupoCobranca",parametrosComandoNegativacaoHelper.getColecaoGrupoCobranca());
		}else{
			form.setGrupoCobranca("");
		}
		
		// Gerencia Regional
		
		if (parametrosComandoNegativacaoHelper.getColecaoGerenciaRegional()!= null){
			
			sessao.setAttribute("colecaoGerenciaRegional",parametrosComandoNegativacaoHelper.getColecaoGerenciaRegional());
		}else{
			form.setGerenciaRegional("");
		}
		
		// Unidade de Negocio
		
		if (parametrosComandoNegativacaoHelper.getColecaoUnidadeNegocio()!= null){
			
			sessao.setAttribute("colecaoUnidadeNegocio",parametrosComandoNegativacaoHelper.getColecaoUnidadeNegocio());
		}else{
			form.setUnidadeNegocio("");
		}
		
		// Elo Pólo
		
		if (parametrosComandoNegativacaoHelper.getColecaoEloPolo()!= null){
			
			sessao.setAttribute("colecaoEloPolo",parametrosComandoNegativacaoHelper.getColecaoEloPolo());
		}else{
			form.setEloPolo("");
		}
		
		// Localidade Inicial
		if (parametrosComandoNegativacaoHelper.getLocInicial()!= null){
			
			form.setLocInicial(parametrosComandoNegativacaoHelper.getLocInicial());
		}
		
		// Localidade Final
		if (parametrosComandoNegativacaoHelper.getLocFinal()!= null){
			
			form.setLocFinal(parametrosComandoNegativacaoHelper.getLocFinal());
		}
		
		// Setor Comercial Inicial
		if (parametrosComandoNegativacaoHelper.getSetComInicial()!= null){
			
			form.setSetComInicial(parametrosComandoNegativacaoHelper.getSetComInicial().toString());
		}
		
		// Setor Comercial Final
		if (parametrosComandoNegativacaoHelper.getSetComFinal()!= null){
			
			form.setSetComFinal(parametrosComandoNegativacaoHelper.getSetComFinal().toString());
		}
		
		return form;
	}

private ConsultarParametrosComandoNegativacaoActionForm setFormDadosImovel(ConsultarParametrosComandoNegativacaoActionForm form, 
		HttpSession sessao, ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper) {
		
		
		//	Id Cliente
		if (parametrosComandoNegativacaoHelper.getIdCliente()!= null){
			
			form.setIdCliente(parametrosComandoNegativacaoHelper.getIdCliente().toString());
		}
		
		// Nome Cliente
		if (parametrosComandoNegativacaoHelper.getNomeCliente()!= null){
			
			form.setNomeCliente(parametrosComandoNegativacaoHelper.getNomeCliente());
		}
		
		// Tipo de Relação com o Cliente
		if (parametrosComandoNegativacaoHelper.getTipoRelClie()!= null){
			
			form.setTipoRelClie(parametrosComandoNegativacaoHelper.getTipoRelClie());
		}
		
		// Imovel com Sit. Especial de Cobranca
		if (parametrosComandoNegativacaoHelper.getIndicadorEspCobranca()!= null){
			
			if (parametrosComandoNegativacaoHelper.getIndicadorEspCobranca().equals(ConstantesSistema.SIM)){
				form.setIndicadorEspecialCobranca(ConstantesSistema.SIM.toString());
			}else{
				form.setIndicadorEspecialCobranca(ConstantesSistema.NAO.toString());
			}
		}
		
		// Imovel com Sit.de Cobranca
		if (parametrosComandoNegativacaoHelper.getIndicadorSitCobranca()!= null){
			
			if (parametrosComandoNegativacaoHelper.getIndicadorSitCobranca().equals(ConstantesSistema.SIM)){
				form.setIndicadorSituacaoCobranca(ConstantesSistema.SIM.toString());
			}else{
				form.setIndicadorSituacaoCobranca(ConstantesSistema.NAO.toString());
			}
		}
		
		//	Lista de Subcategoria
		if (parametrosComandoNegativacaoHelper.getColecaoSubcategoria()!= null){
			
			sessao.setAttribute("colecaoSubcategoria",parametrosComandoNegativacaoHelper.getColecaoSubcategoria());
		}
		
		//	Lista de Perfil Imovel
		if (parametrosComandoNegativacaoHelper.getColecaoPerfilImovel()!= null){
			
			sessao.setAttribute("colecaoPerfilImovel",parametrosComandoNegativacaoHelper.getColecaoPerfilImovel());
		}
		
		//	Lista de Tipo de Cliente
		if (parametrosComandoNegativacaoHelper.getColecaoTipoCliente()!= null){
			
			sessao.setAttribute("colecaoTipoCliente",parametrosComandoNegativacaoHelper.getColecaoTipoCliente());
		}
		
		return form;
	}

private ConsultarParametrosComandoNegativacaoActionForm setFormDadosDebito(ConsultarParametrosComandoNegativacaoActionForm form, 
		ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper) {
		
		//	Periodo de referencia Inicial
		if (parametrosComandoNegativacaoHelper.getReferenciaInicial() != null){
			
			form.setReferenciaInicial(Util.formatarAnoMesParaMesAno(parametrosComandoNegativacaoHelper.getReferenciaInicial()));
		}
		
		// Periodo de referencia Final
		if (parametrosComandoNegativacaoHelper.getReferenciaFinal()!= null){
			
			form.setReferenciaFinal(Util.formatarAnoMesParaMesAno(parametrosComandoNegativacaoHelper.getReferenciaFinal()));
		}
		
		// Periodo de Vencimento Debito inicial
		if (parametrosComandoNegativacaoHelper.getVencimentoInicial()!= null){
			
			form.setVencimentoInicial(Util.formatarData(parametrosComandoNegativacaoHelper.getVencimentoInicial()));
		}
		
		// Periodo de Vencimento Debito Final
		if (parametrosComandoNegativacaoHelper.getVencimentoFinal()!= null){
			
			form.setVencimentoFinal(Util.formatarData(parametrosComandoNegativacaoHelper.getVencimentoFinal()));
		}
		
		// Valor Minimo do Debito
		if (parametrosComandoNegativacaoHelper.getValoMinimoDebito() != null){
			
			form.setValoMinimoDebito(parametrosComandoNegativacaoHelper.getValoMinimoDebito().toString());
		}
		
		// Valor Maximo do Debito
		if (parametrosComandoNegativacaoHelper.getValoMaximoDebito()!= null){
			
			form.setValoMaximoDebito(parametrosComandoNegativacaoHelper.getValoMaximoDebito().toString());
		}
		
		// Quantidade Minina de Contas
		if (parametrosComandoNegativacaoHelper.getQtdMinimaContas()!= null){
			
			form.setQtdMinimaContas(parametrosComandoNegativacaoHelper.getQtdMinimaContas().toString());
		}
		
		// Quantidade Maxima de Contas
		if (parametrosComandoNegativacaoHelper.getQtdMaximaContas()!= null){
			
			form.setQtdMaximaContas(parametrosComandoNegativacaoHelper.getQtdMaximaContas().toString());
		}
		
		// Contas em revisao
		if (parametrosComandoNegativacaoHelper.getIndicadorContaRevisao()!= null){
			
			if (parametrosComandoNegativacaoHelper.getIndicadorContaRevisao().equals(ConstantesSistema.SIM)){
				form.setIndicadorContasRevisao(ConstantesSistema.SIM.toString());
			}else{
				form.setIndicadorContasRevisao(ConstantesSistema.NAO.toString());
			}
		}
		
		// Guia de pagamento
		if (parametrosComandoNegativacaoHelper.getIndicadorGuiaPagamento()!= null){
			
			if (parametrosComandoNegativacaoHelper.getIndicadorGuiaPagamento().equals(ConstantesSistema.SIM)){
				form.setIndicadorGuiaPagamento(ConstantesSistema.SIM.toString());
			}else{
				form.setIndicadorGuiaPagamento(ConstantesSistema.NAO.toString());
			}
		}
		
		// Parcelamento em Atraso
		if (parametrosComandoNegativacaoHelper.getIndicadorParcelamentoAtraso()!= null){
			
			if (parametrosComandoNegativacaoHelper.getIndicadorParcelamentoAtraso().equals(ConstantesSistema.SIM)){
				form.setIndicadorParcelamentoAtraso(ConstantesSistema.SIM.toString());
			}else{
				form.setIndicadorParcelamentoAtraso(ConstantesSistema.NAO.toString());
			}
		}
		
		// Dias em atraso de Parcelamento
		if (parametrosComandoNegativacaoHelper.getNumDiasAtrasoParcelamento()!= null){
			
			form.setNumDiasAtrasoParcelamento(parametrosComandoNegativacaoHelper.getNumDiasAtrasoParcelamento().toString());
		}
		
		// Recebeu Carta de Parcelamento em Atraso
		if (parametrosComandoNegativacaoHelper.getIndicadorCartaParcelamentoAtraso()!= null){
			
			if (parametrosComandoNegativacaoHelper.getIndicadorCartaParcelamentoAtraso().equals(ConstantesSistema.SIM)){
				form.setIndicadorRecCartaAtraso(ConstantesSistema.SIM.toString());
			}else{
				form.setIndicadorRecCartaAtraso(ConstantesSistema.NAO.toString());
			}
		}
		
		// Dias em Atraso apos Recebimento de Carta
		if (parametrosComandoNegativacaoHelper.getNumDiasAtrasoAposRecCarta()!= null){
			
			form.setNumDiasAtrasoAposRecCarta(parametrosComandoNegativacaoHelper.getNumDiasAtrasoAposRecCarta().toString());
		}
		return form;
	}

private ConsultarParametrosComandoNegativacaoActionForm setFormDadosGerais(ConsultarParametrosComandoNegativacaoActionForm form, 
		HttpSession sessao, ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper) {
		
		
		// Negativador
		if (parametrosComandoNegativacaoHelper.getNegativador() != null){
			
			form.setNegativador(parametrosComandoNegativacaoHelper.getNegativador());
		}
		
		// Valor Quantidade de Inclusoes
		if (parametrosComandoNegativacaoHelper.getQtdInclusoes()!= null &&
				!parametrosComandoNegativacaoHelper.getQtdInclusoes().equals("")){
			
			form.setQtdInclusoes(parametrosComandoNegativacaoHelper.getQtdInclusoes().toString());
		}
		
		//Valor Total do Debito
		if (parametrosComandoNegativacaoHelper.getValorTotalDebito()!= null){
			
			form.setValorTotalDebito(parametrosComandoNegativacaoHelper.getValorTotalDebito().toString());
		}
		
		//	Quantidade Itens Incluidos
		if (parametrosComandoNegativacaoHelper.getQtdItensIncluidos()!= null && 
				!parametrosComandoNegativacaoHelper.getQtdItensIncluidos().equals("")){
			
			form.setQtdItensIncluidos(parametrosComandoNegativacaoHelper.getQtdItensIncluidos().toString());
		}
		
		// Titulo Comando
		if (parametrosComandoNegativacaoHelper.getTituloComando()!= null){
			
			form.setTituloComando(parametrosComandoNegativacaoHelper.getTituloComando());
		}
		
		// Descricao da Solicitacao
		if (parametrosComandoNegativacaoHelper.getDescricaoSolicitacao() != null){
			
			form.setDescricaoSolicitacao(parametrosComandoNegativacaoHelper.getDescricaoSolicitacao());
		}
		
		// Simular Negativacao
		if (parametrosComandoNegativacaoHelper.getSimularNegativacao()!= null){
			
			if (parametrosComandoNegativacaoHelper.getSimularNegativacao().equals(ConstantesSistema.SIM)){
				form.setIndicadorSimularNegativacao(ConstantesSistema.SIM.toString());
			}else{
				form.setIndicadorSimularNegativacao(ConstantesSistema.NAO.toString());
			}
		}
		
		//Data Prevista p Execucao
		if (parametrosComandoNegativacaoHelper.getDataExecucao()!= null){
			
			form.setDataExecucao(Util.formatarData(parametrosComandoNegativacaoHelper.getDataExecucao()));
		}
		
		//	Usuario Responsavel
		if (parametrosComandoNegativacaoHelper.getUsuarioResponsavel()!= null){
			form.setIdUsuarioResponsavel(parametrosComandoNegativacaoHelper.getIdUsuario().toString());
			form.setUsuarioResponsavel(parametrosComandoNegativacaoHelper.getUsuarioResponsavel());
		}
		
		// Quantidade Maxima Inclusoes
		if (parametrosComandoNegativacaoHelper.getQtdMaxInclusoes()!= null){
			
			form.setQtdMaxInclusoes(parametrosComandoNegativacaoHelper.getQtdMaxInclusoes().toString());
		}
		
		// Titularidade do CPF/CNPJ da Negativacao
		
		if (parametrosComandoNegativacaoHelper.getColecaoTitularNegativacao()!= null){
			
			sessao.setAttribute("colecaoTitularidadeNegativacao",parametrosComandoNegativacaoHelper.getColecaoTitularNegativacao());
		}
		
		return form;
	}
	
}
