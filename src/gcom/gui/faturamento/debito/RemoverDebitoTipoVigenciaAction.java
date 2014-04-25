package gcom.gui.faturamento.debito;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.faturamento.debito.FiltroDebitoTipoVigencia;
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
 * [UC0986] Manter tipo de débito com vigência
 * [SB0003] - Excluir débito tipo valor
 * 
 * @author Josenildo Neves
 * @date 22/02/2010
 */
public class RemoverDebitoTipoVigenciaAction extends GcomAction {

	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
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

		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		// mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}
		
		FiltroDebitoTipoVigencia filtroDebitoTipoVigencia = 
				new FiltroDebitoTipoVigencia();
		
		Collection idsDebitoVigencia = new ArrayList(ids.length);
		
		for (int i = 0; i < ids.length; i++) {
			idsDebitoVigencia.add(new Integer(ids[i]));
		}
		
		filtroDebitoTipoVigencia.adicionarParametro(new ParametroSimplesIn(FiltroDebitoTipoVigencia.ID,idsDebitoVigencia));

		Collection coletionDebitoTipoVigencia = fachada.pesquisar(filtroDebitoTipoVigencia,
				DebitoTipoVigencia.class.getName());
		
		Iterator itera = coletionDebitoTipoVigencia.iterator();
		
		while(itera.hasNext()){
		
			DebitoTipoVigencia debitoTipoVigencia = (DebitoTipoVigencia) itera.next();
			
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_EXCLUIR_DEBITO_TIPO_VIGENCIA, debitoTipoVigencia.getDebitoTipo().getId(),
					debitoTipoVigencia.getDebitoTipo().getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			
			//debitoTipoVigencia.setDebitoTipo(debitoTipo);
			
			registradorOperacao.registrarOperacao(debitoTipoVigencia);
			
			fachada.remover(debitoTipoVigencia);
			
		}

		Integer idQt = ids.length;

		montarPaginaSucesso(httpServletRequest, idQt.toString()
				+ " Debito(os) Tipo Vigencia removido(s) com sucesso.",
				"Manter outro Debito Tipo Vigencia",
				"exibirFiltrarDebitoTipoVigenciaAction.do?menu=sim");

		return retorno;
	}

}
