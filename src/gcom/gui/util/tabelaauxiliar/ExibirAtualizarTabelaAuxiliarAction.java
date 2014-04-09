package gcom.gui.util.tabelaauxiliar;

import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.FiltroTabelaAuxiliar;
import gcom.util.tabelaauxiliar.TabelaAuxiliar;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author rodrigo
 */
public class ExibirAtualizarTabelaAuxiliarAction extends GcomAction {
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
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarTabelaAuxiliar");

		//Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		//Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;
		
		//String com path do pacote mais o nome do objeto
		String pacoteNomeObjeto = (String) sessao
				.getAttribute("pacoteNomeObjeto");
		
		
		String id = null;
		
		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}
		
		
		//Código da tabela auxiliar a ser atualizada
		if (httpServletRequest.getAttribute("id") != null) {
			id = (String) httpServletRequest.getAttribute("id");
			sessao.setAttribute("id", id);
		} else {
			if (manutencaoRegistroActionForm.getIdRegistroAtualizacao() != null) {
				id = manutencaoRegistroActionForm.getIdRegistroAtualizacao();
				sessao.setAttribute("id", id);
			}
		}

		if (sessao.getAttribute("id") != null) {
			id = (String) sessao.getAttribute("id");
		}
		
		
		//Cria o filtro para atividade
		FiltroTabelaAuxiliar filtroTabelaAuxiliar = new FiltroTabelaAuxiliar();
		
		String tela = (String) sessao.getAttribute("tela");

		if (httpServletRequest.getAttribute("desfazer") == null) {
		
		//Adiciona o parâmetro no filtro
		filtroTabelaAuxiliar.adicionarParametro(new ParametroSimples(
				FiltroTabelaAuxiliar.ID, id));
		
		//Pesquisa a tabela auxiliar no sistema
		Collection tabelasAuxiliares = fachada
				.pesquisarTabelaAuxiliar(filtroTabelaAuxiliar,
				pacoteNomeObjeto);

		//Caso a coleção esteja vazia, indica erro inesperado
		if (tabelasAuxiliares == null
				|| tabelasAuxiliares.isEmpty()) {
			throw new ActionServletException("erro.sistema");
		}

		Iterator iteratorTabelaAuxiliar = tabelasAuxiliares
				.iterator();

		//A tabela auxiliar que será atualizada
		TabelaAuxiliar tabelaAuxiliar = (TabelaAuxiliar) iteratorTabelaAuxiliar
				.next();

		//Manda a tabela auxiliar na sessão
		sessao.setAttribute("tabelaAuxiliar", tabelaAuxiliar);

		if(tabelaAuxiliar.getIndicadorUso().intValue() == ConstantesSistema.INDICADOR_USO_ATIVO.intValue()){
			sessao.setAttribute("indicadorUso", "sim");
		}else{
			sessao.setAttribute("indicadorUso", "nao");
		}

		DadosTelaTabelaAuxiliar dados = DadosTelaTabelaAuxiliar
				.obterDadosTelaTabelaAuxiliar(tela);
		
		if (dados.getTabelaAuxiliar() instanceof RegiaoDesenvolvimento) {
			sessao.setAttribute("tamMaxCampoDescricao", 20);
		}

		sessao.setAttribute("funcionalidadeTabelaAuxiliarFiltrar",
				dados.getFuncionalidadeTabelaAuxFiltrar());
		
		}
		
		if (httpServletRequest.getAttribute("desfazer") != null) {

			// Cria o filtro para atividade
			filtroTabelaAuxiliar.limparListaParametros();

			// Adiciona o parâmetro no filtro
			filtroTabelaAuxiliar
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliar.ID, id));

			// Pesquisa a tabela auxiliar no sistema
			Collection tabelasAuxiliaresBase = fachada
					.pesquisarTabelaAuxiliar(filtroTabelaAuxiliar,
							pacoteNomeObjeto);

			// Caso a coleção esteja vazia, indica erro inesperado
			if (tabelasAuxiliaresBase == null
					|| tabelasAuxiliaresBase.isEmpty()) {
				throw new ActionServletException("erro.sistema");
			}

			Iterator iteratorTabelaAuxiliarBase = tabelasAuxiliaresBase
					.iterator();

			// A tabela auxiliar que será atualizada
			TabelaAuxiliar tabelaAuxiliarBase = (TabelaAuxiliar) iteratorTabelaAuxiliarBase
					.next();

			// Manda a tabela auxiliar na sessão
			sessao.setAttribute("tabelaAuxiliar",
					tabelaAuxiliarBase);

		}
		
		httpServletRequest.setAttribute("tela",tela);
		
		
		//Devolve o mapeamento de retorno
		return retorno;
	}

}
