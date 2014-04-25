package gcom.gui.util.tabelaauxiliar.abreviada;

import gcom.arrecadacao.banco.Banco;
import gcom.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.operacional.FonteCaptacao;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;
import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;

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
public class ExibirAtualizarTabelaAuxiliarAbreviadaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("atualizarTabelaAuxiliarAbreviada");

		//Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;
		
		//String com path do pacote mais o nome do objeto
		String pacoteNomeObjeto = (String) sessao.getAttribute("pacoteNomeObjeto");
		
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
		FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();
		
		String tela = (String) sessao.getAttribute("tela");

		if (httpServletRequest.getAttribute("desfazer") == null) {
		
			//Adiciona o parâmetro no filtro
			filtroTabelaAuxiliarAbreviada.adicionarParametro(
				new ParametroSimples(
					FiltroTabelaAuxiliarAbreviada.ID, id));

			//Pesquisa a tabela auxiliar no sistema
			Collection tabelasAuxiliaresAbreviadas = this.getFachada()
					.pesquisarTabelaAuxiliar(filtroTabelaAuxiliarAbreviada,
					pacoteNomeObjeto);

			//Caso a coleção esteja vazia, indica erro inesperado
			if (tabelasAuxiliaresAbreviadas == null || tabelasAuxiliaresAbreviadas.isEmpty()) {
				throw new ActionServletException("erro.sistema");
			}

			Iterator iteratorTabelaAuxiliarAbreviada = tabelasAuxiliaresAbreviadas.iterator();
	
			//A tabela auxiliar abreviada que será atualizada
			TabelaAuxiliarAbreviada tabelaAuxiliarAbreviada = 
				(TabelaAuxiliarAbreviada) iteratorTabelaAuxiliarAbreviada.next();
	
			//Manda a tabela auxiliar na sessão
			sessao.setAttribute("tabelaAuxiliarAbreviada", tabelaAuxiliarAbreviada);

			if(tabelaAuxiliarAbreviada.getIndicadorUso().intValue() == ConstantesSistema.INDICADOR_USO_ATIVO.intValue()){
				sessao.setAttribute("indicadorUso", "sim");
			}else{
				sessao.setAttribute("indicadorUso", "nao");
			}

			DadosTelaTabelaAuxiliarAbreviada dados = DadosTelaTabelaAuxiliarAbreviada
					.obterDadosTelaTabelaAuxiliar(tela);

			if (dados.getTabela() instanceof FonteCaptacao) {
				sessao.setAttribute("tamMaxCampoDescricao", 30);
				sessao.setAttribute("tamMaxCampoDescricaoAbreviada", 10);
			}
			
			sessao.setAttribute("funcionalidadeTabelaAuxiliarAbreviadaFiltrar",
					dados.getFuncionalidadeTabelaAuxFiltrar());
		
			
			if (dados.getTabela() instanceof EquipamentosEspeciais) {
				sessao.setAttribute("tamMaxCampoDescricao", 40);
				sessao.setAttribute("tamMaxCampoDescricaoAbreviada", 8);
			}
			
			if (dados.getTabela() instanceof Banco) {
				sessao.setAttribute("tamMaxCampoDescricao", 40);
				sessao.setAttribute("tamMaxCampoDescricaoAbreviada", 20);
			}
		
		}
		
	
		
		if (httpServletRequest.getAttribute("desfazer") != null) {

			// Cria o filtro para atividade
			filtroTabelaAuxiliarAbreviada.limparListaParametros();

			// Adiciona o parâmetro no filtro
			filtroTabelaAuxiliarAbreviada
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarAbreviada.ID, id));

			// Pesquisa a tabela auxiliar no sistema
			Collection tabelasAuxiliaresAbreviadaBase = this.getFachada()
					.pesquisarTabelaAuxiliar(filtroTabelaAuxiliarAbreviada,
							pacoteNomeObjeto);

			// Caso a coleção esteja vazia, indica erro inesperado
			if (tabelasAuxiliaresAbreviadaBase == null
					|| tabelasAuxiliaresAbreviadaBase.isEmpty()) {
				throw new ActionServletException("erro.sistema");
			}

			Iterator iteratorTabelaAuxiliarAbreviadaBase = tabelasAuxiliaresAbreviadaBase
					.iterator();

			// A tabela auxiliar abreviada que será atualizada
			TabelaAuxiliarAbreviada tabelaAuxiliarAbreviadaBase = (TabelaAuxiliarAbreviada) iteratorTabelaAuxiliarAbreviadaBase
					.next();

			// Manda a tabela auxiliar na sessão
			sessao.setAttribute("tabelaAuxiliarAbreviada",
					tabelaAuxiliarAbreviadaBase);

		}
		
		httpServletRequest.setAttribute("tela",tela);
		
		
		//Devolve o mapeamento de retorno
		return retorno;
	}

}
