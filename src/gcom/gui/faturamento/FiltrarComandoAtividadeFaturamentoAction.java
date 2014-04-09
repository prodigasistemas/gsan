package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FaturamentoGrupoCronogramaMensal;
import gcom.faturamento.FiltroFaturamentoAtividadeCronograma;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarComandoAtividadeFaturamentoAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("filtrarComandoAtividadeFaturamento");

        //Carrega a instancia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Carrega o objeto sessao
        HttpSession sessao = httpServletRequest.getSession(false);
        
        
        //Limpando todos os objetos colocados na sessão
        //--------------------------------------------------------------------
        sessao.removeAttribute("dataCorrente");
        sessao.removeAttribute("exibirCampoVencimentoGrupo");
        sessao.removeAttribute("faturamentoAtividadeCronograma");
        sessao.removeAttribute("colecaoFaturamentoAtividadeCronogramaRota");
        sessao.removeAttribute("colecaoRotasSelecionadas");
        sessao.removeAttribute("colecaoRotasSelecionadasUsuario");
        sessao
                .removeAttribute("colecaoFaturamentoAtividadeCronogramaRotaUniao");
        sessao.removeAttribute("PesquisarActionForm");
        sessao.removeAttribute("InserirComandoAtividadeFaturamentoActionForm");
        sessao.removeAttribute("statusWizard");
        //--------------------------------------------------------------------
        
        
        FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = new FiltroFaturamentoAtividadeCronograma();
        filtroFaturamentoAtividadeCronograma.setConsultaSemLimites(true);
        
        //filtroFaturamentoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupoCronogramaMensal.faturamentoGrupo.anoMesReferencia");
        filtroFaturamentoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupoCronogramaMensal.faturamentoGrupo");
        
        // Retorna uma lista de atividades de faturamento comandadas e ainda não
        // realizadas
        /*Collection colecaoAtividadesAtualizacao = fachada
                .buscarAtividadeComandadaNaoRealizada(filtroFaturamentoAtividadeCronograma);*/
        
		// 1º Passo - Pegar o total de registros através de um count da consulta que aparecerá na tela
		Integer totalRegistros = fachada
				.buscarAtividadeComandadaNaoRealizadaCount();

		// 2º Passo - Chamar a função de Paginação passando o total de registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);

		// 3º Passo - Obter a coleção da consulta que aparecerá na tela passando o numero de paginas
		// da pesquisa que está no request
		Collection colecaoAtividadesAtualizacao = fachada
			.buscarAtividadeComandadaNaoRealizada((Integer) httpServletRequest
					.getAttribute("numeroPaginasPesquisa"));

        
        Iterator colecaoAtividadesAtualizacaoIt = colecaoAtividadesAtualizacao.iterator();
        Collection colecaoFaturamentoAtividadeCronograma = new Vector();
        Object[] arrayConteudoAtividade = new Object[6];
        FaturamentoAtividadeCronograma faturamentoAtividadeCronograma;
        FaturamentoAtividade faturamentoAtividade;
        FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal;
        Date comando, dataPrevista;
        
        while (colecaoAtividadesAtualizacaoIt.hasNext()){
        	arrayConteudoAtividade = (Object[]) colecaoAtividadesAtualizacaoIt.next();
        	
        	faturamentoAtividade = (FaturamentoAtividade) arrayConteudoAtividade[1];
        	faturamentoGrupoCronogramaMensal = (FaturamentoGrupoCronogramaMensal) arrayConteudoAtividade[4];
        	comando = (Date) arrayConteudoAtividade[2];
        	dataPrevista = (Date) arrayConteudoAtividade[3];
        	
        	faturamentoAtividadeCronograma = new  FaturamentoAtividadeCronograma();
        	faturamentoAtividadeCronograma.setId(new Integer(String.valueOf(arrayConteudoAtividade[0])));
        	faturamentoAtividadeCronograma.setFaturamentoAtividade(faturamentoAtividade);
        	faturamentoAtividadeCronograma.setComando(comando);
        	faturamentoAtividadeCronograma.setDataPrevista(dataPrevista);
        	faturamentoAtividadeCronograma.setFaturamentoGrupoCronogramaMensal(faturamentoGrupoCronogramaMensal);
        	colecaoFaturamentoAtividadeCronograma.add(faturamentoAtividadeCronograma);
        }

        sessao.setAttribute("colecaoAtividadesAtualizacao",
        		colecaoFaturamentoAtividadeCronograma);
        
        
        return retorno;
    }

}
