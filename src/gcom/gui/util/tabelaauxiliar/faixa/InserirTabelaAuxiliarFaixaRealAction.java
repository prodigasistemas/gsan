package gcom.gui.util.tabelaauxiliar.faixa;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.faixa.FiltroTabelaAuxiliarFaixaReal;
import gcom.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixaReal;

import java.math.BigDecimal;
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
public class InserirTabelaAuxiliarFaixaRealAction extends GcomAction{
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
		TabelaAuxiliarFaixaRealActionForm form = (TabelaAuxiliarFaixaRealActionForm) actionForm;

		// Obtém a fachada
		Fachada fachada = Fachada.getInstancia();

		// Recebe o objeto TabelaAuxiliarAbreviada
		TabelaAuxiliarFaixaReal tabelaAuxiliarFaixaReal = (TabelaAuxiliarFaixaReal) sessao
				.getAttribute("tabela");
		
		BigDecimal volumeMenorFaixa = null;
		
		if (form.getVolumeMenorFaixa() != null
				&& !form.getVolumeMenorFaixa().trim().equalsIgnoreCase("")) {
			
			volumeMenorFaixa = new BigDecimal(0);

			String valorAux = form.getVolumeMenorFaixa().toString().replace(".",
					"");

			valorAux = valorAux.replace(",", ".");

			volumeMenorFaixa = new BigDecimal(valorAux);

			tabelaAuxiliarFaixaReal.setVolumeMenorFaixa(volumeMenorFaixa);

		}
		
		
		if (form.getVolumeMaiorFaixa() != null
				&& !form.getVolumeMaiorFaixa().trim().equalsIgnoreCase("")) {
			BigDecimal volumeMaiorFaixa = new BigDecimal(0);

			String valorAux = form.getVolumeMaiorFaixa().toString().replace(".",
					"");

			valorAux = valorAux.replace(",", ".");

			volumeMaiorFaixa = new BigDecimal(valorAux);

			tabelaAuxiliarFaixaReal.setVolumeMaiorFaixa(volumeMaiorFaixa);
			
			if (volumeMaiorFaixa != null					
					&& volumeMenorFaixa != null) {
				if (volumeMenorFaixa.compareTo(volumeMaiorFaixa) > 0) {
					throw new ActionServletException("atencao.volume_menor_faixa_superior_maior_faixa");
				}
			}

		}


		// Seta a data e hora
		tabelaAuxiliarFaixaReal.setUltimaAlteracao(new Date());

		tabelaAuxiliarFaixaReal
				.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		FiltroTabelaAuxiliarFaixaReal filtroTabelaAuxiliarFaixaReal = new FiltroTabelaAuxiliarFaixaReal();

		filtroTabelaAuxiliarFaixaReal
				.adicionarParametro(new ParametroSimples(
						FiltroTabelaAuxiliarFaixaReal.VOLUME_MENOR_FAIXA,
						tabelaAuxiliarFaixaReal.getVolumeMenorFaixa()));
		
		filtroTabelaAuxiliarFaixaReal
		.adicionarParametro(new ParametroSimples(
				FiltroTabelaAuxiliarFaixaReal.VOLUME_MAIOR_FAIXA,
				tabelaAuxiliarFaixaReal.getVolumeMaiorFaixa()));

		Collection colecaoTabelaAuxiliarFaixaReal = fachada.pesquisar(
				filtroTabelaAuxiliarFaixaReal, TabelaAuxiliarFaixaReal.class
						.getName());

		if (colecaoTabelaAuxiliarFaixaReal != null && !colecaoTabelaAuxiliarFaixaReal.isEmpty()) {
			throw new ActionServletException(
					"atencao.descricao_tabela_auxiliar_ja_existente",
					(String) sessao.getAttribute("titulo"),"");
		}

		// Insere objeto
		fachada.inserirTabelaAuxiliar(tabelaAuxiliarFaixaReal);

		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(
					httpServletRequest,
					((String) sessao.getAttribute("titulo"))
							+ " inserido(a) com sucesso.",
					"Inserir outro(a) "
							+ ((String) sessao.getAttribute("titulo")),
					((String) sessao
							.getAttribute("funcionalidadeTabelaAuxiliarFaixaRealInserir")));
		}

		// Remove os objetos da sessão
		sessao.removeAttribute("funcionalidadeTabelaAuxiliarFaixaRealInserir");
		sessao.removeAttribute("titulo");
		sessao.removeAttribute("tabelaAuxiliarAbreviada");

		return retorno;
	}
}
