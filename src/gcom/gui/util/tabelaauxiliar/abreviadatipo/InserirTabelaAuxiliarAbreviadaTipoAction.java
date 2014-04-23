package gcom.gui.util.tabelaauxiliar.abreviadatipo;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.SistemaAbastecimento;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviadatipo.FiltroTabelaAuxiliarAbreviadaTipo;
import gcom.util.tabelaauxiliar.abreviadatipo.TabelaAuxiliarAbreviadaTipo;

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
 * <Esse componente serve para SetorAbastecimento e ZonaAbastecimento, sendo o tipo SistemaAbastecimento>
 * 
 * @author Administrador
 */
public class InserirTabelaAuxiliarAbreviadaTipoAction extends GcomAction {
	/**
	 * <Descrição do método>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception RemoteException
	 *                Descrição da exceção
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws RemoteException,
			ErroRepositorioException {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Prepara o retorno da Ação
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém o action form
		TabelaAuxiliarAbreviadaTipoActionForm tabelaAuxiliarAbreviadaTipoActionForm = (TabelaAuxiliarAbreviadaTipoActionForm) actionForm;

		// Obtém a fachada
		Fachada fachada = Fachada.getInstancia();

		// Recebe o objeto TabelaAuxiliarAbreviadaTipo
		TabelaAuxiliarAbreviadaTipo tabelaAuxiliarAbreviadaTipo = (TabelaAuxiliarAbreviadaTipo) sessao
				.getAttribute("tabela");

		// Seta a descrição informada
		tabelaAuxiliarAbreviadaTipo.setDescricao(
				tabelaAuxiliarAbreviadaTipoActionForm.getDescricao().toUpperCase());

		// Seta a descrição abreviada informada
		tabelaAuxiliarAbreviadaTipo
				.setDescricaoAbreviada(tabelaAuxiliarAbreviadaTipoActionForm
						.getDescricaoAbreviada().toUpperCase());
		
		// Seta o Indicador de Uso
		tabelaAuxiliarAbreviadaTipo.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		
		// Seta a data e hora
		tabelaAuxiliarAbreviadaTipo.setUltimaAlteracao(new Date());

		SistemaAbastecimento sistemaAbastecimento = new SistemaAbastecimento();
		sistemaAbastecimento.setId(new Integer(tabelaAuxiliarAbreviadaTipoActionForm.getTipo()));
		
		tabelaAuxiliarAbreviadaTipo.setSistemaAbastecimento(sistemaAbastecimento);

		FiltroTabelaAuxiliarAbreviadaTipo filtroTabelaAuxiliarAbreviadaTipo = new FiltroTabelaAuxiliarAbreviadaTipo();

		filtroTabelaAuxiliarAbreviadaTipo
				.adicionarParametro(new ParametroSimples(
						FiltroTabelaAuxiliarAbreviadaTipo.DESCRICAO,
						tabelaAuxiliarAbreviadaTipoActionForm.getDescricao()
								.toUpperCase()));

		Collection colecaoTabelaAuxiliar = fachada.pesquisar(
				filtroTabelaAuxiliarAbreviadaTipo, TabelaAuxiliarAbreviadaTipo.class
						.getName());

		if (colecaoTabelaAuxiliar != null && !colecaoTabelaAuxiliar.isEmpty()) {
			throw new ActionServletException(
					"atencao.descricao_tabela_auxiliar_ja_existente",
					(String) sessao.getAttribute("titulo"),
					tabelaAuxiliarAbreviadaTipoActionForm.getDescricao().toUpperCase());
		}

		// Insere objeto
		fachada.inserirTabelaAuxiliar(tabelaAuxiliarAbreviadaTipo);

		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(
					httpServletRequest,
					((String) sessao.getAttribute("titulo"))
							+ " inserido(a) com sucesso.",
					"Inserir outro(a) "
							+ ((String) sessao.getAttribute("titulo")),
					((String) sessao
							.getAttribute("funcionalidadeTabelaAuxiliarAbreviadaTipoInserir")));
		}

		// Remove os objetos da sessão
		sessao.removeAttribute("funcionalidadeTabelaAuxiliarAbreviadaTipoInserir");
		sessao.removeAttribute("titulo");
		sessao.removeAttribute("tituloTipo");
		sessao.removeAttribute("tabelaAuxiliarAbreviadaTipo");
		sessao.removeAttribute("tabela");

		return retorno;
	}
}
