package gcom.gui.util.tabelaauxiliar.faixa;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.faixa.FiltroTabelaAuxiliarFaixaInteiro;
import gcom.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixaInteiro;

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
 * @author gcom
 *
 */
public class InserirTabelaAuxiliarFaixaInteiroAction extends GcomAction{
	/**
	 * < <Descrição do método>>
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
		TabelaAuxiliarFaixaInteiroActionForm form = (TabelaAuxiliarFaixaInteiroActionForm) actionForm;

		// Obtém a fachada
		Fachada fachada = Fachada.getInstancia();

		// Recebe o objeto TabelaAuxiliarAbreviada
		TabelaAuxiliarFaixaInteiro tabelaAuxiliarFaixaInteiro = (TabelaAuxiliarFaixaInteiro) sessao
				.getAttribute("tabela");
		
		if (form.getMenorFaixa() != null
				&& !form.getMenorFaixa().trim().equalsIgnoreCase("")) {
			

			tabelaAuxiliarFaixaInteiro.setMenorFaixa(new Integer(form.getMenorFaixa() ));

		}
		
		
		if (form.getMaiorFaixa() != null
				&& !form.getMaiorFaixa().trim().equalsIgnoreCase("")) {
			

			tabelaAuxiliarFaixaInteiro.setMaiorFaixa(new Integer(form.getMaiorFaixa() ));

		}
		
		if (form.getMenorFaixa() != null
				&& !form.getMenorFaixa().trim().equalsIgnoreCase("")
				&& form.getMaiorFaixa()  != null
				&& !form.getMaiorFaixa() .trim().equalsIgnoreCase("")) {
			if (new Integer(form.getMenorFaixa()).intValue() > new Integer(
					form.getMaiorFaixa() )) {
				throw new ActionServletException("atencao.menor_faixa_superior_maior_faixa");
			}
		}


		// Seta a data e hora
		tabelaAuxiliarFaixaInteiro.setUltimaAlteracao(new Date());

		tabelaAuxiliarFaixaInteiro
				.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		FiltroTabelaAuxiliarFaixaInteiro filtroTabelaAuxiliarFaixaInteiro = new FiltroTabelaAuxiliarFaixaInteiro();

		filtroTabelaAuxiliarFaixaInteiro
				.adicionarParametro(new ParametroSimples(
						FiltroTabelaAuxiliarFaixaInteiro.MENOR_FAIXA,
						tabelaAuxiliarFaixaInteiro.getMenorFaixa()));
		
		filtroTabelaAuxiliarFaixaInteiro
		.adicionarParametro(new ParametroSimples(
				FiltroTabelaAuxiliarFaixaInteiro.MAIOR_FAIXA,
				tabelaAuxiliarFaixaInteiro.getMaiorFaixa()));

		Collection colecaoTabelaAuxiliarFaixaInteiro = fachada.pesquisar(
				filtroTabelaAuxiliarFaixaInteiro, TabelaAuxiliarFaixaInteiro.class
						.getName());

		if (colecaoTabelaAuxiliarFaixaInteiro != null && !colecaoTabelaAuxiliarFaixaInteiro.isEmpty()) {
			throw new ActionServletException(
					"atencao.descricao_tabela_auxiliar_ja_existente",
					(String) sessao.getAttribute("titulo"),"");
		}

		// Insere objeto
		fachada.inserirTabelaAuxiliar(tabelaAuxiliarFaixaInteiro);

		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(
					httpServletRequest,
					((String) sessao.getAttribute("titulo"))
							+ " inserido(a) com sucesso.",
					"Inserir outro(a) "
							+ ((String) sessao.getAttribute("titulo")),
					((String) sessao
							.getAttribute("funcionalidadeTabelaAuxiliarFaixaInteiroInserir")));
		}

		// Remove os objetos da sessão
		sessao.removeAttribute("funcionalidadeTabelaAuxiliarFaixaInteiroInserir");
		sessao.removeAttribute("titulo");
		sessao.removeAttribute("tabelaAuxiliarAbreviada");

		return retorno;
	}
}
