package gcom.gui.faturamento.debito;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.financeiro.FiltroFinanciamentoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.FiltroLancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
public class ExibirAtualizarTipoDebitoAction extends GcomAction {
	/**
	public ActionForward execute(ActionMapping actionMapping,
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
		AtualizarTipoDebitoActionForm form = (AtualizarTipoDebitoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		String id = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		/*
		// Carregando dados da tabela LancamentoItemContabil
		filtroLancamentoItemContabil
		filtroLancamentoItemContabil.adicionarParametro(new ParametroSimples(
		// Verifica se os dados foram informados da tabela existem e joga numa
		Collection<LancamentoItemContabil> colecaoLancamentoItemContabil = fachada
		if (colecaoLancamentoItemContabil == null
			throw new ActionServletException(
		}
		httpServletRequest.setAttribute("colecaoLancamentoItemContabil",
		// Carregando dados da tabela FinanciamentoTipo
		filtroFinanciamentoTipo
		filtroFinanciamentoTipo.adicionarParametro(new ParametroSimples(
		// Verifica se os dados foram informados da tabela existem e joga numa
		Collection<FinanciamentoTipo> colecaoFinanciamentoTipo = fachada
		if (colecaoFinanciamentoTipo == null
			throw new ActionServletException(
		}
		httpServletRequest.setAttribute("colecaoFinanciamentoTipo",
		// Verifica se veio do filtrar ou do manter
			sessao.removeAttribute("manter");
		}
		// Verifica se a funcionalidade j� est� na sess�o, em caso afirmativo
			if (sessao.getAttribute("objetoDebitoTipo") != null) {
				DebitoTipo debitoTipo = (DebitoTipo) sessao
				form.setCodigo(debitoTipo.getId().toString());
				form.setDescricao(debitoTipo.getDescricao());
				form.setFinanciamentoTipo(debitoTipo.getFinanciamentoTipo()
				form.setLancamentoItemContabil(debitoTipo
				form.setIndicadorGeracaoDebitoAutomatica(""
				form.setIndicadorGeracaoDebitoConta(""
				form.setIndicadorUso("" + debitoTipo.getIndicadorUso());
				String valorAux = Util.formatarMoedaReal(debitoTipo
				form.setValorLimiteDebito("" + valorAux);
				id = debitoTipo.getId().toString();
			} else {
				String idTipoDebito = null;
					idTipoDebito = (String) sessao.getAttribute("idTipoDebito");
				} else {
					sessao.setAttribute("idDebitoTipo", idTipoDebito);
				}
				sessao.setAttribute("idTipoDebito", idTipoDebito);
				id = idTipoDebito;
				FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
				filtroDebitoTipo
				filtroDebitoTipo
				filtroDebitoTipo.adicionarParametro(new ParametroSimples(
				Collection<DebitoTipo> colecaoDebitoTipo = fachada.pesquisar(
				if (colecaoDebitoTipo == null || colecaoDebitoTipo.isEmpty()) {
				}
				httpServletRequest.setAttribute("colecaoDebitoTipo",
				DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo
				form.setCodigo(debitoTipo.getId().toString());
				form.setDescricao(debitoTipo.getDescricao());
				form.setFinanciamentoTipo(debitoTipo.getFinanciamentoTipo()
				form.setLancamentoItemContabil(debitoTipo
				form.setIndicadorGeracaoDebitoAutomatica(""
				form.setIndicadorGeracaoDebitoConta(""
				form.setIndicadorUso("" + debitoTipo.getIndicadorUso());
				String valorAux = Util.formatarMoedaReal(debitoTipo
				form.setValorLimiteDebito("" + valorAux);
				form.setIdTipoDebito(debitoTipo.getId().toString());
			}
		}
		// -------------- bt DESFAZER ---------------
			String idDebitoTipo = null;
				idDebitoTipo = (String) sessao.getAttribute("idDebitoTipo");
			} else {
				idDebitoTipo = (String) httpServletRequest
				sessao.setAttribute("idDebitoTipo", idDebitoTipo);
			}
			if (idDebitoTipo.equalsIgnoreCase("")) {
				idDebitoTipo = null;
			}
			if ((idDebitoTipo == null) && (id == null)) {
				DebitoTipo debitoTipo = (DebitoTipo) sessao
				form.setCodigo(debitoTipo.getId().toString());
				form.setIdTipoDebito(debitoTipo.getId().toString());
				form.setDescricao(debitoTipo.getDescricao());
				form.setDescricaoAbreviada(debitoTipo.getDescricaoAbreviada());
				form.setFinanciamentoTipo(debitoTipo.getFinanciamentoTipo()
				form.setLancamentoItemContabil(debitoTipo
				form.setIndicadorGeracaoDebitoAutomatica(""
				form.setIndicadorGeracaoDebitoConta(""
				form.setIndicadorUso("" + debitoTipo.getIndicadorUso());
				form.setValorLimiteDebito("" + valorAux);
				sessao.setAttribute("debitoTipoAtualizar", debitoTipo);
				sessao.removeAttribute("debitoTipo");
			}
			if ((idDebitoTipo == null) && (id != null)) {
				idDebitoTipo = id;
			}
			if (idDebitoTipo != null) {
				FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
				filtroDebitoTipo
				filtroDebitoTipo.adicionarParametro(new ParametroSimples(
				Collection<DebitoTipo> colecaoDebitoTipo = fachada.pesquisar(
						filtroDebitoTipo, DebitoTipo.class.getName());
				if (colecaoDebitoTipo == null || colecaoDebitoTipo.isEmpty()) {
					throw new ActionServletException(
				httpServletRequest.setAttribute("colecaoDebitoTipo",
				DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo
				form.setCodigo(debitoTipo.getId().toString());
				form.setDescricaoAbreviada(debitoTipo.getDescricaoAbreviada());
				form.setFinanciamentoTipo(debitoTipo.getFinanciamentoTipo()
				form.setLancamentoItemContabil(debitoTipo
				form.setIndicadorGeracaoDebitoAutomatica(""
				form.setIndicadorUso("" + debitoTipo.getIndicadorUso());
				String valorAux = Util.formatarMoedaReal(debitoTipo
				form.setValorLimiteDebito("" + valorAux);
				form.setIdTipoDebito(debitoTipo.getId().toString());
			}
		}
		// -------------- bt DESFAZER ---------------
		httpServletRequest.setAttribute("colecaoDebitoTipoTela", sessao
		return retorno;
	}
}