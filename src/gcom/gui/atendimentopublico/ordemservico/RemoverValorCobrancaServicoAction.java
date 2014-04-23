package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoCobrancaValor;
import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
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
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 30/10/2006
 */
public class RemoverValorCobrancaServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		// Obtém os ids de remoção
		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();
		
		// mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum
		// registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}
		
		FiltroServicoCobrancaValor filtroServicoCobrancaValor = new FiltroServicoCobrancaValor();
	
		Collection idsDocumentos = new ArrayList(ids.length);
	
		for (int i = 0; i < ids.length; i++) {
			
			idsDocumentos.add(new Integer(ids[i]));
		}
		
		filtroServicoCobrancaValor.adicionarParametro(new ParametroSimplesIn(FiltroServicoCobrancaValor.ID,idsDocumentos));

		Collection colecaoServicoCobrancaValor = fachada.pesquisar(filtroServicoCobrancaValor,
				ServicoCobrancaValor.class.getName());
		
		Iterator itera = colecaoServicoCobrancaValor.iterator();
		
		while(itera.hasNext()){
			
			ServicoCobrancaValor servicoCobrancaValor = (ServicoCobrancaValor) itera.next();
			
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_VALOR_COBRANCA_SERVICO_REMOVER, servicoCobrancaValor.getServicoTipo().getId(),
					servicoCobrancaValor.getServicoTipo().getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			registradorOperacao.registrarOperacao(servicoCobrancaValor);
			
			fachada.remover(servicoCobrancaValor);
		}
		
		Integer idQt = ids.length;

		montarPaginaSucesso(httpServletRequest, idQt.toString()
				+ " Valor(es) de Cobrança removido(s) com sucesso.",
				"Manter outro Valor de Cobrança do Serviço",
				"exibirFiltrarValorCobrancaServicoAction.do?menu=sim");

		return retorno;
	}

}
