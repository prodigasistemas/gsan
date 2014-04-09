package gcom.gui.util.tabelaauxiliar.indicador;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.indicador.FiltroTabelaAuxiliarIndicador;
import gcom.util.tabelaauxiliar.indicador.TabelaAuxiliarIndicador;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurélio
 *
 */
public class InserirTabelaAuxiliarIndicadorAction extends GcomAction{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws RemoteException,
			ErroRepositorioException {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Prepara o retorno da Ação
		HttpSession sessao = this.getSessao(httpServletRequest);

		// Obtém o action form
		TabelaAuxiliarIndicadorActionForm form = (TabelaAuxiliarIndicadorActionForm) actionForm;

		// Obtém a fachada
		Fachada fachada = Fachada.getInstancia();

		// Recebe o objeto TabelaAuxiliarAbreviada
		TabelaAuxiliarIndicador tabelaAuxiliarIndicador = 
			(TabelaAuxiliarIndicador) sessao.getAttribute("tabela");
		
		if (form.getDescricao() != null && !form.getDescricao().trim().equalsIgnoreCase("")) {
			tabelaAuxiliarIndicador.setDescricao(form.getDescricao().toUpperCase());
		}
		
		if (form.getIndicadorNegocio() != null) {
			tabelaAuxiliarIndicador.setIndicadorBaixaRenda(new Short(form.getIndicadorNegocio()));
		}

		// Seta a data e hora
		tabelaAuxiliarIndicador.setUltimaAlteracao(new Date());
		tabelaAuxiliarIndicador.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		FiltroTabelaAuxiliarIndicador filtroTabelaAuxiliarIndicador = new FiltroTabelaAuxiliarIndicador();

		filtroTabelaAuxiliarIndicador.adicionarParametro(
			new ParametroSimples(FiltroTabelaAuxiliarIndicador.DESCRICAO,
				tabelaAuxiliarIndicador.getDescricao()));
		
		filtroTabelaAuxiliarIndicador.adicionarParametro(
			new ParametroSimples(
				FiltroTabelaAuxiliarIndicador.INDICADOR_BAIXA_RENDA,
				tabelaAuxiliarIndicador.getIndicadorBaixaRenda().toString()));

		Collection colecaoTabelaAuxiliarIndicador = 
			fachada.pesquisar(filtroTabelaAuxiliarIndicador, 
				TabelaAuxiliarIndicador.class.getName());

		if (colecaoTabelaAuxiliarIndicador != null && !colecaoTabelaAuxiliarIndicador.isEmpty()) {
			throw new ActionServletException(
				"atencao.descricao_tabela_auxiliar_ja_existente", (String) sessao.getAttribute("titulo"),"");
		}

		// Insere objeto
		fachada.inserirTabelaAuxiliar(tabelaAuxiliarIndicador);

		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(
				httpServletRequest,
				((String) sessao.getAttribute("titulo")) + " " + tabelaAuxiliarIndicador.getDescricao() +" inserido(a) com sucesso.",
				"Inserir outro(a) " + ((String) sessao.getAttribute("titulo")),
				((String) sessao.getAttribute("funcionalidadeTabelaAuxiliarIndicadorInserir")));
		}

		// Remove os objetos da sessão
		sessao.removeAttribute("funcionalidadeTabelaAuxiliarIndicadorInserir");
		sessao.removeAttribute("titulo");

		return retorno;
	}
}
