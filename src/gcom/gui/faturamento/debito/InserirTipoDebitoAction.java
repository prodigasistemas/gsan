package gcom.gui.faturamento.debito;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 09/03/2007
 */
public class InserirTipoDebitoAction extends GcomAction {
	/**
	public ActionForward execute(ActionMapping actionMapping,
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao
		InserirTipoDebitoActionForm form = (InserirTipoDebitoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		String descricao = form.getDescricao();
		String descricaoAbreviada = form.getDescricaoAbreviada();
		String idTipoFinanciamento = form.getFinanciamentoTipo();
		String indicadorGeracaoDebitoAutomatica = form
		String indicadorGeracaoDebitoConta = form
		String idLancamentoItemContabil = form.getLancamentoItemContabil();
		String valorLimeteDebito = form.getValorLimiteDebito();
		Integer idDebitoTipo = (Integer) fachada.inserirDebitoTipo(descricao,
		montarPaginaSucesso(httpServletRequest, "Tipo de D�bito de c�digo "
		return retorno;
	}
}