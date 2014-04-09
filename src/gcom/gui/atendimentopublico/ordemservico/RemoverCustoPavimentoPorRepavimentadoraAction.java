package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroUnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.atendimentopublico.ordemservico.FiltroUnidadeRepavimentadoraCustoPavimentoRua;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoRua;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 * 
 * @author Hugo Leonardo
 * @date 27/12/2010
 */
public class RemoverCustoPavimentoPorRepavimentadoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		if(httpServletRequest.getParameter("acao") != null && 
	        	httpServletRequest.getParameter("acao").equals("removerRua") ){
			
			String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

			// mensagem de erro quando o usuário tenta excluir sem ter selecionado
			// nenhum registro
			if (ids == null || ids.length == 0) {
				throw new ActionServletException(
						"atencao.registros.nao_selecionados");
			}
			
			FiltroUnidadeRepavimentadoraCustoPavimentoRua filtroUnidadeRepavimentadoraCustoPavimentoRua = 
					new FiltroUnidadeRepavimentadoraCustoPavimentoRua();
			
			Collection idsUnidadeRepavimentadoraCustoPavimentoRua = new ArrayList(ids.length);
			
			for (int i = 0; i < ids.length; i++) {
				idsUnidadeRepavimentadoraCustoPavimentoRua.add(new Integer(ids[i]));
			}
			
			filtroUnidadeRepavimentadoraCustoPavimentoRua.adicionarParametro(new ParametroSimplesIn(
					FiltroUnidadeRepavimentadoraCustoPavimentoRua.ID, idsUnidadeRepavimentadoraCustoPavimentoRua));
			
			filtroUnidadeRepavimentadoraCustoPavimentoRua.adicionarCaminhoParaCarregamentoEntidade(
					FiltroUnidadeRepavimentadoraCustoPavimentoRua.PAVIMENTO_RUA);

			Collection coletionUnidadeRepavimentadoraCustoPavimentoRua = fachada.pesquisar(filtroUnidadeRepavimentadoraCustoPavimentoRua,
					UnidadeRepavimentadoraCustoPavimentoRua.class.getName());
			
			Iterator itera = coletionUnidadeRepavimentadoraCustoPavimentoRua.iterator();
			
			while(itera.hasNext()){
			
				UnidadeRepavimentadoraCustoPavimentoRua unidRepavCustoPavimentoRua = (UnidadeRepavimentadoraCustoPavimentoRua) itera.next();
				
				if(!fachada.verificaRemoverCustoPavimentoPorRepavimentadora(
						unidRepavCustoPavimentoRua.getUnidadeRepavimentadora().getId(), 
						unidRepavCustoPavimentoRua.getPavimentoRua().getId(), new Integer("1"))){
					
					// ------------ REGISTRAR TRANSAÇÃO ----------------
					RegistradorOperacao registradorOperacao = new RegistradorOperacao(
							Operacao.OPERACAO_EXCLUIR_CUSTO_PAVIMENTO, unidRepavCustoPavimentoRua.getId(),
							unidRepavCustoPavimentoRua.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
					// ------------ REGISTRAR TRANSAÇÃO ----------------
					registradorOperacao.registrarOperacao(unidRepavCustoPavimentoRua);
					
					// Remover Unidade Repavimentadora Custo Pavimento Rua
					fachada.remover(unidRepavCustoPavimentoRua);
				}else{
					throw new ActionServletException("atencao.custo_pavimento_por_repavimentadora_rua.remover");
				}
			}

			Integer idQt = ids.length;

			montarPaginaSucesso(httpServletRequest, idQt.toString()
					+ " Custo(s) de Pavimento de Rua removido(s) com sucesso.",
					"Realizar outra Manutenção de Custo do Pavimento de Rua",
					"exibirFiltrarCustoPavimentoPorRepavimentadoraAction.do?menu=sim");
			
		}else if(httpServletRequest.getParameter("acao") != null && 
	        	httpServletRequest.getParameter("acao").equals("removerCalcada") ){
			
			String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao_segunda();

			// mensagem de erro quando o usuário tenta excluir sem ter selecionado
			// nenhum registro
			if (ids == null || ids.length == 0) {
				throw new ActionServletException(
						"atencao.registros.nao_selecionados");
			}
			
			FiltroUnidadeRepavimentadoraCustoPavimentoCalcada filtroUnidadeRepavimentadoraCustoPavimentoCalcada = 
					new FiltroUnidadeRepavimentadoraCustoPavimentoCalcada();
			
			Collection idsUnidadeRepavimentadoraCustoPavimentoCalcada = new ArrayList(ids.length);
			
			for (int i = 0; i < ids.length; i++) {
				idsUnidadeRepavimentadoraCustoPavimentoCalcada.add(new Integer(ids[i]));
			}
			
			filtroUnidadeRepavimentadoraCustoPavimentoCalcada.adicionarParametro(new ParametroSimplesIn(
					FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.ID, idsUnidadeRepavimentadoraCustoPavimentoCalcada));
			
			filtroUnidadeRepavimentadoraCustoPavimentoCalcada.adicionarCaminhoParaCarregamentoEntidade(
					FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.PAVIMENTO_CALCADA);

			Collection coletionUnidadeRepavimentadoraCustoPavimentoCalcada = fachada.pesquisar(filtroUnidadeRepavimentadoraCustoPavimentoCalcada,
					UnidadeRepavimentadoraCustoPavimentoCalcada.class.getName());
			
			Iterator itera = coletionUnidadeRepavimentadoraCustoPavimentoCalcada.iterator();
			
			while(itera.hasNext()){
			
				UnidadeRepavimentadoraCustoPavimentoCalcada unidRepavCustoPavimentoCalcada = (UnidadeRepavimentadoraCustoPavimentoCalcada) itera.next();
				
				if(!fachada.verificaRemoverCustoPavimentoPorRepavimentadora(
						unidRepavCustoPavimentoCalcada.getUnidadeRepavimentadora().getId(), 
						unidRepavCustoPavimentoCalcada.getPavimentoCalcada().getId(), new Integer("2"))){
					
					// Remover Unidade Repavimentadora Custo Pavimento Rua
					fachada.remover(unidRepavCustoPavimentoCalcada);
				}else{
					throw new ActionServletException("atencao.custo_pavimento_por_repavimentadora_calcada.remover");
				}

			}

			Integer idQt = ids.length;

			montarPaginaSucesso(httpServletRequest, idQt.toString()
					+ " Custo(s) de Pavimento de Calçada removido(s) com sucesso.",
					"Realizar outra Manutenção de Custo do Pavimento de Calçada",
					"exibirFiltrarCustoPavimentoPorRepavimentadoraAction.do?menu=sim");
		}
		
		return retorno;
	}

}
