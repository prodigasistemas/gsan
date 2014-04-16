package gcom.gui.faturamento.conta;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMensagem;
import gcom.faturamento.conta.FiltroContaMensagem;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoverMensagemContaAction extends GcomAction {

	/**
	 * Autor Tiago Moreno Excluir Tarifa de consumo
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		// Obtém os ids de remoção
		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		HttpSession sessao = httpServletRequest.getSession(false);	
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Integer contadorQtMensagens = new Integer(ids.length);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		//------------ REGISTRAR TRANSAÇÃO ----------------
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_CONTA_MENSAGEM_REMOVER);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSAÇÃO ----------------
		
		// mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}

		for (int i = 0; i < ids.length; i++) {
			String id = (String) ids[i];

			FiltroContaMensagem filtroContaMensagem = new FiltroContaMensagem();
			filtroContaMensagem.adicionarParametro(new ParametroSimples(
					FiltroContaMensagem.ID, id));
			filtroContaMensagem
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroContaMensagem
					.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroContaMensagem
					.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
			filtroContaMensagem
					.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");

			Collection colecaoContaMensagem = fachada.pesquisar(
					filtroContaMensagem, ContaMensagem.class.getName());
			if (colecaoContaMensagem != null && !colecaoContaMensagem.isEmpty()) {
				ContaMensagem contaMensagem = (ContaMensagem) colecaoContaMensagem.iterator().next();
				
				FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
				filtroSistemaParametro.adicionarParametro(new MaiorQue(FiltroSistemaParametro.ANO_MES_REFERECIA_ARRECADACAO, contaMensagem.getAnoMesRreferenciaFaturamento()));
				
				Collection sistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());
				
				if (sistemaParametro != null && !sistemaParametro.isEmpty()){

					//String anoMes = Util.formatarAnoMesParaMesAno(((SistemaParametro) sistemaParametro.iterator().next()).getAnoMesFaturamento());
					
					FiltroSistemaParametro filtroSistemaParametro2 = new FiltroSistemaParametro();
					Collection colecaoFSP = fachada.pesquisar(filtroSistemaParametro2, SistemaParametro.class.getName());
					
					if (colecaoFSP != null && !colecaoFSP.isEmpty()){
						SistemaParametro sistemaParametro2 = (SistemaParametro) colecaoFSP.iterator().next();
					
						/*String mesAno =*/ Util.formatarAnoMesParaMesAno(sistemaParametro2.getAnoMesFaturamento());
						
						throw new ActionServletException(
							 	"atencao.mensagem_nao_inserida");
					}
				}
			}
		}

		//------------ REGISTRAR TRANSAÇÃO ----------------
    	UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
    	Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
    	colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
        //------------ REGISTRAR TRANSAÇÃO ----------------
    	
		fachada.remover(ids, ContaMensagem.class.getName(), operacaoEfetuada, colecaoUsuarios);

		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest, contadorQtMensagens
					.toString()
					+ " Mensagem(ns) da Conta removida(s) com sucesso",
					"Realizar outra Manutenção de Mensagem da Conta",
					"exibirFiltrarMensagemContaAction.do?menu=sim");
		}

		return retorno;
	}
}
