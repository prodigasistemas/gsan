package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.FiltroHidrometroMovimentacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroMovimentado;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroMovimentacao;
import gcom.micromedicao.hidrometro.HidrometroMovimentado;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição da tela de consultar crédito a realizar
 * 
 * @author Fernanda Paiva
 * @created 17 de Janeiro de 2006
 */
public class ExibirMovimentacaoHidrometroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno e seta o mapeamento para a tela de
		// consultar Movimentacao Hidrometro
		ActionForward retorno = actionMapping
				.findForward("movimentacaoHidrometro");

		// cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// recupera o código da conta do request
		String idMovimentacao = httpServletRequest
				.getParameter("idRegistroAtualizacao");

		// se o código não for nulo
		if (idMovimentacao != null && !idMovimentacao.equalsIgnoreCase("")) {
			// remove a coleção de movimentacões da sessão
			sessao.removeAttribute("colecaoMovimentacaoHidrometro");
		}

		/*
		 * Pesquisando a movimentacao
		 * =====================================================================
		 */

		// se não existir a coleção na sessão
		// cria o filtro de créditos a realizar
		FiltroHidrometroMovimentacao filtroHidrometroMovimentacao = new FiltroHidrometroMovimentacao();

		// carrega o motivo
		filtroHidrometroMovimentacao
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroMotivoMovimentacao");

		// carrega o local de armazenagem origem
		filtroHidrometroMovimentacao
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalArmazenagemOrigem");

		// carrega o local de armazenagem destino
		filtroHidrometroMovimentacao
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalArmazenagemDestino");
		
		//		 carrega o local de armazenagem origem
		filtroHidrometroMovimentacao
				.adicionarCaminhoParaCarregamentoEntidade("usuario");

		// seta o código do imovel no filtro
		filtroHidrometroMovimentacao.adicionarParametro(new ParametroSimples(
				FiltroHidrometroMovimentacao.ID, idMovimentacao));

		// pesquisa a coleção de créditos a realizar
		Collection<HidrometroMovimentacao> colecaoMovimentacaoHidrometro = fachada
				.pesquisar(filtroHidrometroMovimentacao,
						HidrometroMovimentacao.class.getName());

		Collection colecaoHidrometroMovimentado = null;
		
		if (!colecaoMovimentacaoHidrometro.isEmpty()) {

			Iterator hidrometroMovimentacaoIterator = colecaoMovimentacaoHidrometro.iterator();
			
			while (hidrometroMovimentacaoIterator.hasNext()) {
				
				HidrometroMovimentacao hidrometroMovimentacao = (HidrometroMovimentacao) hidrometroMovimentacaoIterator.next();

				FiltroHidrometroMovimentado filtroHidrometroMovimentado = new FiltroHidrometroMovimentado();
				
				filtroHidrometroMovimentado
						.adicionarCaminhoParaCarregamentoEntidade("hidrometro");

				filtroHidrometroMovimentado
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometroMovimentado.HIDROMETRO_MOVIMENTACAO_ID,
								hidrometroMovimentacao.getId()));
				
				filtroHidrometroMovimentado.setConsultaSemLimites(true);
				
				colecaoHidrometroMovimentado = fachada.pesquisar(
						filtroHidrometroMovimentado,
						HidrometroMovimentado.class.getName());

				Integer quantidade = colecaoHidrometroMovimentado.size();
				
				if (!colecaoHidrometroMovimentado.isEmpty()) 
				{
					String faixaInicial = ((HidrometroMovimentado) ((List) colecaoHidrometroMovimentado).get(0)).getHidrometro().getNumero();
					
					String faixaFinal = ((HidrometroMovimentado) ((List) colecaoHidrometroMovimentado).get(quantidade-1)).getHidrometro().getNumero();
					
					if(faixaInicial != null)
					{
						Integer tamanhoFaixaInicial = faixaInicial.length();
						if(tamanhoFaixaInicial > 4)
						{
							String fixo = faixaInicial.substring(0,4);
							hidrometroMovimentacao.setFixo(fixo);
						}
						faixaInicial = faixaInicial.substring(4,tamanhoFaixaInicial-1);
					}
					if(faixaFinal != null)
					{
						Integer tamanhoFaixaFinal = faixaFinal.length();
						faixaFinal = faixaFinal.substring(4,tamanhoFaixaFinal-1);
					}
					hidrometroMovimentacao.setFaixaInicial(faixaInicial);
					
					hidrometroMovimentacao.setFaixaFinal(faixaFinal);
					
				}
				
				hidrometroMovimentacao.setQuantidade(quantidade.toString());
			}
		}
		Collection colecaoHidrometro = null;
		Collection colecaoObterHidrometro = new ArrayList();
		
		if (!colecaoHidrometroMovimentado.isEmpty()) {

			Iterator hidrometroMovimentadoIterator = colecaoHidrometroMovimentado.iterator();
			
			while (hidrometroMovimentadoIterator.hasNext()) {
				
				HidrometroMovimentado hidrometroMovimentado = (HidrometroMovimentado) hidrometroMovimentadoIterator.next();

				FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
				
				filtroHidrometro
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometro.ID,
								hidrometroMovimentado.getHidrometro().getId()));
				filtroHidrometro
						.adicionarCaminhoParaCarregamentoEntidade("hidrometroMarca");
				filtroHidrometro
						.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");
				filtroHidrometro
						.adicionarCaminhoParaCarregamentoEntidade("hidrometroSituacao");
				filtroHidrometro
                        .setCampoOrderBy(FiltroHidrometro.NUMERO_HIDROMETRO);
                
				colecaoHidrometro = fachada.pesquisar(
						filtroHidrometro, Hidrometro.class.getName());

				if (!colecaoHidrometro.isEmpty()) 
				{
					colecaoObterHidrometro.addAll(colecaoHidrometro);
				}
			}
		}

		// ====================================================================
		if (colecaoMovimentacaoHidrometro == null
				|| colecaoMovimentacaoHidrometro.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Movimentação de Hidrômetro");
		} else {
			// seta a coleção de movimentações na sessão
			sessao.setAttribute("colecaoMovimentacaoHidrometro",
					colecaoMovimentacaoHidrometro);
			sessao.setAttribute("colecaoObterHidrometro",
					colecaoObterHidrometro);
		}
		
		// retorna o mapeamento contido na variável retorno
		return retorno;
	}
}
