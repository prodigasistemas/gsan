package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipoAtividade;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoBoletim;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoMaterial;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoMotivoEncerramento;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoOperacao;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoAtividade;
import gcom.atendimentopublico.ordemservico.ServicoTipoBoletim;
import gcom.atendimentopublico.ordemservico.ServicoTipoMaterial;
import gcom.atendimentopublico.ordemservico.ServicoTipoMotivoEncerramento;
import gcom.atendimentopublico.ordemservico.ServicoTipoOperacao;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.util.ConstantesSistema;
import gcom.util.FachadaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Iterator;

/**
 * [SB0001]Atualizar Tipo Perfil de Serviço
 *
 * @author Thiago Tenorio, Pedro Alexandre
 * @date 31/10/2006, 14/12/2007
 */

public class ExibirAtualizarTipoServicoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarTipoServico");

		AtualizarTipoServicoActionForm atualizarTipoServicoActionForm = (AtualizarTipoServicoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		ServicoTipo servicoTipo = null;
		
		String idServico = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null && !httpServletRequest.getParameter("idRegistroAtualizacao").equals("")) {
			//tela do manter
			idServico = (String) httpServletRequest.getParameter("idRegistroAtualizacao");
			sessao.setAttribute("idServico", idServico);
		} else if (sessao.getAttribute("servicoTipo") != null) {
			//tela do filtrar
			servicoTipo = (ServicoTipo) sessao.getAttribute("servicoTipo"); 
			idServico = servicoTipo.getId().toString();
		}else if(httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null){
			//link na tela de sucesso do inserir Perfil Serviço
			idServico = (String)httpServletRequest.getParameter("idRegistroInseridoAtualizar");
			sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarTipoPerfilServicoAction.do?menu=sim");
		}
		
		if (idServico == null){
			
			if (httpServletRequest.getParameter("idRegistroAtualizacao") == null){
				idServico = (String) sessao.getAttribute("idServico");
			}else{
				idServico = (String) httpServletRequest.getParameter("idRegistroAtualizacao").toString();
			}
		}
		
		boolean pesquisar = false;
		
		if(httpServletRequest.getParameter("pesquisa") != null &&
				httpServletRequest.getParameter("pesquisa").equals("S")){
			pesquisar = true;
		}else if(sessao.getAttribute("pesquisa") != null){
			pesquisar = true;
			sessao.removeAttribute("pesquisa");
		}
		
		//-------Parte que trata do código quando o usuário tecla enter     
		if ((idServico != null && !idServico.equals(""))
				&& pesquisar) {

			FiltroTipoServico filtroTipoServico = new FiltroTipoServico();

			filtroTipoServico
					.adicionarParametro(new ParametroSimples(
							FiltroTipoServico.ID, idServico));
			
			filtroTipoServico.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroTipoServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipoPrioridade");
			filtroTipoServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipoSubgrupo");
			filtroTipoServico.adicionarCaminhoParaCarregamentoEntidade("servicoPerfilTipo");
			filtroTipoServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipoReferencia");
			filtroTipoServico.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");

			Collection colecaoServicoTipo = fachada.pesquisar(
					filtroTipoServico, ServicoTipo.class
							.getName());

			if (colecaoServicoTipo != null
					&& !colecaoServicoTipo.isEmpty()) {
				
				servicoTipo = (ServicoTipo) colecaoServicoTipo.iterator().next();
				atualizarTipoServicoActionForm.setDescricao(servicoTipo.getDescricao());
				sessao.setAttribute("servicoTipo", servicoTipo);
				
				atualizarTipoServicoActionForm.setIndicadorInformacoesBoletimMedicao(servicoTipo.getIndicadorBoletim().toString());
				
				if(servicoTipo.getIndicadorBoletim().equals(ConstantesSistema.SIM)){
					
					FiltroServicoTipoBoletim filtroServicoTipoBoletim = new FiltroServicoTipoBoletim();
					filtroServicoTipoBoletim.adicionarParametro(new ParametroSimples(
							FiltroServicoTipoBoletim.ID, idServico));
					Collection colecaoServicoTipoBoletim = fachada.pesquisar(
							filtroServicoTipoBoletim, ServicoTipoBoletim.class
									.getName());
					
					if (colecaoServicoTipoBoletim != null
							&& !colecaoServicoTipoBoletim.isEmpty()) {
						
						ServicoTipoBoletim	servicoTipoBoletim = (ServicoTipoBoletim) colecaoServicoTipoBoletim.iterator().next();
						
						atualizarTipoServicoActionForm.setIndicativoPavimento(servicoTipoBoletim.getIndicadorPavimento().toString());
						atualizarTipoServicoActionForm.setIndicativoReposicaoAsfalto(servicoTipoBoletim.getIndicadorReposicaoAsfalto().toString());
						atualizarTipoServicoActionForm.setIndicativoReposicaoParalelo(servicoTipoBoletim.getIndicadorReposicaoParalelo().toString());
						atualizarTipoServicoActionForm.setIndicativoReposicaoCalcada(servicoTipoBoletim.getIndicadorReposicaoCalcada().toString());
					}
				}else{
					atualizarTipoServicoActionForm.setIndicativoPavimento("");
					atualizarTipoServicoActionForm.setIndicativoReposicaoAsfalto("");
					atualizarTipoServicoActionForm.setIndicativoReposicaoParalelo("");
					atualizarTipoServicoActionForm.setIndicativoReposicaoCalcada("");
				}
				
				
			} 
			
			FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
			Collection colecaoCreditoTipo = fachada.pesquisar(filtroCreditoTipo, CreditoTipo.class.getName());
			sessao.setAttribute("colecaoCreditoTipo", colecaoCreditoTipo);
			
			FiltroServicoTipoPrioridade filtroServicoTipoPrioridade = new FiltroServicoTipoPrioridade();
			Collection colecaoPrioridadeServico = fachada.pesquisar(filtroServicoTipoPrioridade, ServicoTipoPrioridade.class.getName());
			sessao.setAttribute("colecaoPrioridadeServico", colecaoPrioridadeServico);
			
//		  ------  O servico Tipo foi encontrado
			
			atualizarTipoServicoActionForm.setIdTipoServico(""+servicoTipo.getId());
			atualizarTipoServicoActionForm.setDescricao(""+servicoTipo.getDescricao());
			if (servicoTipo.getDescricaoAbreviada() != null) {
				atualizarTipoServicoActionForm.setAbreviada(""+servicoTipo.getDescricaoAbreviada());
			} else {
				atualizarTipoServicoActionForm.setAbreviada("");
			}
			atualizarTipoServicoActionForm.setSubgrupo(""+servicoTipo.getServicoTipoSubgrupo().getId());
			atualizarTipoServicoActionForm.setValorServico(Util.formatarMoedaReal(servicoTipo.getValor()));
			//atualizarTipoServicoActionForm.setPavimento(""+servicoTipo.getIndicadorPavimento());
			
			atualizarTipoServicoActionForm.setIndicadorPavimentoRua(""+servicoTipo.getIndicadorPavimentoRua());
			atualizarTipoServicoActionForm.setIndicadorPavimentoCalcada(""+servicoTipo.getIndicadorPavimentoCalcada());
			
			atualizarTipoServicoActionForm.setIndicativoTipoSevicoEconomias(""+servicoTipo.getIndicativoTipoSevicoEconomias());
			
			atualizarTipoServicoActionForm.setAtualizacaoComercial(""+servicoTipo.getIndicadorAtualizaComercial());
			atualizarTipoServicoActionForm.setServicoTerceirizado(""+servicoTipo.getIndicadorTerceirizado());
			atualizarTipoServicoActionForm.setIndicadorFiscalizacaoInfracao(""+servicoTipo.getIndicadorFiscalizacaoInfracao());
			atualizarTipoServicoActionForm.setIndicadorVistoria(""+servicoTipo.getIndicadorVistoria());
			atualizarTipoServicoActionForm.setCodigoServico(""+servicoTipo.getCodigoServicoTipo());
			atualizarTipoServicoActionForm.setTempoMedioExecucao(""+servicoTipo.getTempoMedioExecucao());
			atualizarTipoServicoActionForm.setIdPrioridadeServico(servicoTipo.getServicoTipoPrioridade().getId().toString());
			atualizarTipoServicoActionForm.setPerfilServico(servicoTipo.getServicoPerfilTipo().getId().toString());
			atualizarTipoServicoActionForm.setDescricaoPerfilServico(servicoTipo.getServicoPerfilTipo().getDescricao());
			atualizarTipoServicoActionForm.setIndicadorPermiteAlterarValor(""+servicoTipo.getIndicadorPermiteAlterarValor());
			atualizarTipoServicoActionForm.setIndicadorEmpresaCobranca(""+servicoTipo.getIndicadorEmpresaCobranca());
			atualizarTipoServicoActionForm.setIndicativoObrigatoriedadeAtividade(""+servicoTipo.getIndicadorAtividade());
			atualizarTipoServicoActionForm.setIndicativoObrigatoriedadeAtividadeValor(""+servicoTipo.getIndicadorAtividade());
			atualizarTipoServicoActionForm.setIndicadorServicoOrdemSeletiva(""+servicoTipo.getIndicadorServicoOrdemSeletiva());
			atualizarTipoServicoActionForm.setIndicadorEnvioPesquisaSatisfacao(""+servicoTipo.getIndicadorEnvioPesquisaSatisfacao());
			atualizarTipoServicoActionForm.setIndicadorInspecaoAnormalidade(""+servicoTipo.getIndicadorInspecaoAnormalidade());
			atualizarTipoServicoActionForm.setIndicadorProgramacaoAutomatica(""+servicoTipo.getIndicadorProgramacaoAutomatica());
			atualizarTipoServicoActionForm.setIndicadorProgramacaoAutomaticaValor(""+servicoTipo.getIndicadorProgramacaoAutomatica());
			atualizarTipoServicoActionForm.setIndicadorEncAutomaticoRAQndEncOS(""+servicoTipo.getIndicadorEncAutomaticoRAQndEncOS());
			
			FiltroServicoTipoAtividade filtroServicoTipoAtividade = new FiltroServicoTipoAtividade();
			filtroServicoTipoAtividade.adicionarParametro(new ParametroSimples(FiltroServicoTipoAtividade.SERVICO_TIPO_ID, servicoTipo.getId()));
			filtroServicoTipoAtividade.adicionarCaminhoParaCarregamentoEntidade("atividade");
			
			FiltroServicoTipoMaterial filtroServicoTipoMaterial = new FiltroServicoTipoMaterial();
			filtroServicoTipoMaterial.adicionarParametro(new ParametroSimples(FiltroServicoTipoMaterial.ID_SERVICO_TIPO, servicoTipo.getId()));
			filtroServicoTipoMaterial.adicionarCaminhoParaCarregamentoEntidade("material");
			
			FiltroServicoTipoMotivoEncerramento filtroServicoTipoMotivoEncerramento = new FiltroServicoTipoMotivoEncerramento();
			filtroServicoTipoMotivoEncerramento.adicionarParametro(new ParametroSimples(FiltroServicoTipoMotivoEncerramento.SERVICO_TIPO_ID, servicoTipo.getId()));
			filtroServicoTipoMotivoEncerramento.adicionarCaminhoParaCarregamentoEntidade(FiltroServicoTipoMotivoEncerramento.ATENDIMENTO_MOTIVO_ENCERRAMENTO);
			
			
			Collection colecaoServicoTipoMaterial = fachada.pesquisar(filtroServicoTipoMaterial, ServicoTipoMaterial.class.getName());
			
			Collection colecaoServicoTipoAtividade = fachada.pesquisar(filtroServicoTipoAtividade, ServicoTipoAtividade.class.getName());
			
			Collection colecaoAtendimentoMotivoEncerramento = fachada.pesquisar(filtroServicoTipoMotivoEncerramento, ServicoTipoMotivoEncerramento.class.getName());
			
			if (colecaoServicoTipoMaterial != null && !colecaoServicoTipoMaterial.isEmpty()){
				atualizarTipoServicoActionForm.setServicoTipoMateriais(colecaoServicoTipoMaterial);
			}
			
			if (colecaoServicoTipoAtividade == null || colecaoServicoTipoAtividade.isEmpty()) {
				atualizarTipoServicoActionForm.setIndicadorAtividadeUnica("1");
				sessao.removeAttribute("colecaoServicoTipoAtividade");
			} else {
				atualizarTipoServicoActionForm.setIndicadorAtividadeUnica("2");
				sessao.setAttribute("colecaoServicoTipoAtividade", colecaoServicoTipoAtividade);
				atualizarTipoServicoActionForm.setServicoTipoAtividades(colecaoServicoTipoAtividade);
			}
			

			sessao.setAttribute("colecaoServicoTipoMaterial", colecaoServicoTipoMaterial);
			
			if(colecaoAtendimentoMotivoEncerramento != null && !colecaoAtendimentoMotivoEncerramento.isEmpty()){
				Iterator iter = colecaoAtendimentoMotivoEncerramento.iterator();
				ArrayList<AtendimentoMotivoEncerramento> motivosEncerramento = new ArrayList<AtendimentoMotivoEncerramento>();
				while(iter.hasNext()){
					ServicoTipoMotivoEncerramento servicoTipoMotivoEncerramento = (ServicoTipoMotivoEncerramento) iter.next();
					if(servicoTipoMotivoEncerramento.getMotivoEncerramento() !=null){
						motivosEncerramento.add(servicoTipoMotivoEncerramento.getMotivoEncerramento());
					}
				}
				
				sessao.setAttribute("colecaoAtendimentoMotivosEncerramentoInseridos", motivosEncerramento);
			}
			
			
			if(servicoTipo.getDebitoTipo() != null){
				atualizarTipoServicoActionForm.setIdTipoDebito(""+servicoTipo.getDebitoTipo().getId());
				atualizarTipoServicoActionForm.setDescricaoTipoDebito(""+servicoTipo.getDebitoTipo().getDescricao());
			}
			if(servicoTipo.getCreditoTipo() != null){
				atualizarTipoServicoActionForm.setIdTipoCredito(""+servicoTipo.getCreditoTipo().getId());
				atualizarTipoServicoActionForm.setIdPrioridadeServico(""+servicoTipo.getServicoTipoPrioridade().getId());
			}
			atualizarTipoServicoActionForm.setPerfilServico(""+servicoTipo.getServicoPerfilTipo().getId());
			if(servicoTipo.getServicoTipoReferencia() !=  null){
				atualizarTipoServicoActionForm.setIdTipoServicoReferencia(""+servicoTipo.getServicoTipoReferencia().getId());
				atualizarTipoServicoActionForm.setDescricaoTipoServicoReferencia(""+servicoTipo.getServicoTipoReferencia().getDescricao());
			}
			atualizarTipoServicoActionForm.setIndicadorUso(""+servicoTipo.getIndicadorUso());
			
			// Operacao
			
			FiltroServicoTipoOperacao filtroServicoTipoOperacao = new FiltroServicoTipoOperacao();
			filtroServicoTipoOperacao.adicionarParametro(new ParametroSimples(FiltroServicoTipoOperacao.ID_SERVICO_TIPO, servicoTipo.getId()));
			
			Collection collection = getFachada().pesquisar(filtroServicoTipoOperacao, ServicoTipoOperacao.class.getName());
			
			if (collection != null & collection.size() > 0) {
				ServicoTipoOperacao sto = (ServicoTipoOperacao) collection.iterator().next();
				
				if (sto != null) {
					FiltroOperacao filtroOperacao = new FiltroOperacao();
					filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID, sto.getComp_id().getIdOperacao()));
					
					Operacao operacao = (Operacao) getFachada().pesquisar(filtroOperacao, Operacao.class.getName()).iterator().next();
					
					if (operacao != null) {
						atualizarTipoServicoActionForm.setIdOperacao(String.valueOf(sto.getComp_id().getIdOperacao()));
						atualizarTipoServicoActionForm.setDescricaoOperacao(operacao.getDescricao());
						sessao.setAttribute("idOperacao", operacao.getId());
					}
				}
			}
			
			
			
		}else{
		
			
//			Tipo de Débito		
			Integer idDebitoTipo = Util.converterStringParaInteger(atualizarTipoServicoActionForm.getIdTipoDebito());
			
			if (Util.validarNumeroMaiorQueZERO(idDebitoTipo)) {
				try {
					DebitoTipo debitoTipo = fachada.pesquisarDebitoTipo(idDebitoTipo);
					servicoTipo.setDebitoTipo(debitoTipo);
					atualizarTipoServicoActionForm.setIdTipoDebito(debitoTipo.getId().toString());
					atualizarTipoServicoActionForm.setDescricaoTipoDebito(debitoTipo.getDescricao());
				} catch (FachadaException e) {
					servicoTipo.setDebitoTipo(null);
					atualizarTipoServicoActionForm.setDescricaoTipoDebito("Tipo de Débito Inexistente");
				}
			} else {
				servicoTipo.setDebitoTipo(null);
			}
			
			
			
			if(atualizarTipoServicoActionForm.getIdTipoCredito() != null && Integer.parseInt(atualizarTipoServicoActionForm.getIdTipoCredito()) != ConstantesSistema.NUMERO_NAO_INFORMADO){
				httpServletRequest.setAttribute("desabilitaCredito", "desabilitaCredito");			
			}
			//Perfil do Serviço
			
			Integer idServicoPerfilTipo = Util.converterStringParaInteger(atualizarTipoServicoActionForm.getPerfilServico());
			
			if (Util.validarNumeroMaiorQueZERO(idServicoPerfilTipo)) {
				try {
					ServicoPerfilTipo servicoPerfilTipo = fachada.pesquisarServicoPerfilTipo(idServicoPerfilTipo);
					servicoTipo.setServicoPerfilTipo(servicoPerfilTipo);
					atualizarTipoServicoActionForm.setPerfilServico(servicoPerfilTipo.getId().toString());
					atualizarTipoServicoActionForm.setDescricaoPerfilServico(servicoPerfilTipo.getDescricao());
				} catch (FachadaException e) {
					servicoTipo.setServicoPerfilTipo(null);
					atualizarTipoServicoActionForm.setDescricaoPerfilServico("Tipo do Perfil Inexistente");
				}
			}
			
			
			// Operacao
			
			Integer idOperacao = Util.converterStringParaInteger(atualizarTipoServicoActionForm.getIdOperacao());
			
			if (Util.validarNumeroMaiorQueZERO(idOperacao)) {
				try {
					FiltroOperacao filtroOperacao = new FiltroOperacao();
		        	filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID, idOperacao));
		        	
		        	Collection colecaoOperacao = fachada.pesquisar(filtroOperacao, Operacao.class.getName());
		        	
		        	if(colecaoOperacao.isEmpty()) {
		        		atualizarTipoServicoActionForm.setDescricaoOperacao("Operação Inexistente");
		        		sessao.setAttribute("idOperacao", "");
		        	} else {
		        		Operacao operacao = (Operacao) colecaoOperacao.iterator().next();
		        		atualizarTipoServicoActionForm.setIdOperacao(operacao.getId().toString());
		        		atualizarTipoServicoActionForm.setDescricaoOperacao(operacao.getDescricao());
		        		sessao.setAttribute("idOperacao", operacao.getId());
		        	}
				} catch (FachadaException e) {
					sessao.setAttribute("idOperacao", "");
					atualizarTipoServicoActionForm.setDescricaoOperacao("Operacao Inexistente");
				}
			}
			
			//Tipo do Serviço Referência
			
			Integer idServicoTipoReferencia = Util.converterStringParaInteger(atualizarTipoServicoActionForm.getIdTipoServicoReferencia());
			
			if (Util.validarNumeroMaiorQueZERO(idServicoTipoReferencia)) {
				try {
					ServicoTipoReferencia servicoTipoReferencia = fachada.pesquisarServicoTipoReferencia(idServicoTipoReferencia);
					servicoTipo.setServicoTipoReferencia(servicoTipoReferencia);
					atualizarTipoServicoActionForm.setIdTipoServicoReferencia(servicoTipoReferencia.getId().toString());
					atualizarTipoServicoActionForm.setDescricaoTipoServicoReferencia(servicoTipoReferencia.getDescricao());
				} catch (FachadaException e) {
					servicoTipo.setServicoTipoReferencia(null);
					atualizarTipoServicoActionForm.setDescricaoTipoServicoReferencia("Tipo de Serviço de Referência Inexistente");
				}
			}
			
			//outras pesquisar pelo enter
			
			if("removeRowTableServicoTipoReferencia".equals(atualizarTipoServicoActionForm.getMethod())){
				sessao.removeAttribute("servicoTipoReferencia");
				atualizarTipoServicoActionForm.setMethod("");
			}
			
			if ("addServicoTipoAtividade".equals(atualizarTipoServicoActionForm.getMethod())) {
				atualizarTipoServicoActionForm.addServicoTipoAtividade();
			} 
			
			if ("removeServicoTipoAtividade".equals(atualizarTipoServicoActionForm.getMethod())) {
				atualizarTipoServicoActionForm.removeServicoTipoAtividade();
			}
			
			if ("addServicoTipoMaterial".equals(atualizarTipoServicoActionForm.getMethod())) {
				atualizarTipoServicoActionForm.addServicoTipoMaterial();
			} 
			
			if ("removeServicoTipoMaterial".equals(atualizarTipoServicoActionForm.getMethod())) {
				atualizarTipoServicoActionForm.removeServicoTipoMaterial();
			}
			
			if ("removeAllServicoTipoAtividade".equals(atualizarTipoServicoActionForm.getMethod())) {
				atualizarTipoServicoActionForm.removeAllServicoTipoAtividade();
			}
			
			httpServletRequest.setAttribute("servicoTipo", servicoTipo);
			sessao.removeAttribute("colecaoServicoTipoAtividade");
			sessao.removeAttribute("colecaoServicoTipoAtividade");
			sessao.setAttribute("colecaoServicoTipoAtividade", atualizarTipoServicoActionForm.getServicoTipoAtividades());
			sessao.setAttribute("colecaoServicoTipoMaterial", atualizarTipoServicoActionForm.getServicoTipoMateriais());
		}
			

			
			
			
			return retorno;
	}
					
		
}

