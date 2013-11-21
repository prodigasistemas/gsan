package gcom.gui.relatorio.atendimentopublico;

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioGestaoSolicitacoesRAPorChefia;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0497] Gerar Relatorio Resumo de Solicitacoes de RA por Unidade
 * 
 * @see gcom.gui.relatorio.atendimentopublico.GerarRelatorioGestaoSolicitacoesRAPorChefiaActionForm
 * @see gcom.gui.relatorio.atendimentopublico.ExibirGerarRelatorioGestaoSolicitacoesRAPorChefiaAction
 * @see gcom.relatorio.atendimentopublico.RelatorioGestaoSolicitacoesRAPorChefia
 * 
 * @author Victor Cisneiros
 * @date 20/06/2008
 */
public class GerarRelatorioGestaoSolicitacoesRAPorChefiaAction extends ExibidorProcessamentoTarefaRelatorio {

	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuarioLogado");
		
		request.setAttribute("telaSucessoRelatorio",true);
		
		GerarRelatorioGestaoSolicitacoesRAPorChefiaActionForm form = (GerarRelatorioGestaoSolicitacoesRAPorChefiaActionForm) actionForm;
		
		// ------------------------------
		// -- Parametros
		// ------------------------------
		Short situacao = null;
		Collection<Integer> idsSolicitacaoTipo = null;
		Collection<Integer> idsSolicitacaoTipoEspecificacao = null;
		Date dataAtendimentoInicial = null;
		Date dataAtendimentoFinal = null;
		Integer idUnidadeOrganizacional = null;
		Integer idUnidadeSuperior = null;
		Integer idMunicipio = null;
		Integer codigoBairro = null;
		
		// ------------------------------
		// -- Situacao
		// ------------------------------
		if (form.getSituacaoRA() != null && !form.getSituacaoRA().trim().equals("")) {
			situacao = new Short(form.getSituacaoRA());
		}
		
		// ------------------------------
		// -- SolicitacaoTipo e SolicitacaoTipoEspecificacao
		// ------------------------------
		if (form.getSolicitacaoTipo() != null && !form.getSolicitacaoTipo()[0].equals("-1")) {
			// solicitacoes
			idsSolicitacaoTipo = new ArrayList<Integer>();
			for (String idSolicitacao : form.getSolicitacaoTipo()) {
				idsSolicitacaoTipo.add(new Integer(idSolicitacao));
			}
			
			// se apenas uma solicitação foi selecionada restringir por especificao
			if (idsSolicitacaoTipo.size() == 1
					&&  form.getEspecificacao() != null 
					&& !form.getEspecificacao()[0].equals("-1")) {
				
				idsSolicitacaoTipo = null;
				idsSolicitacaoTipoEspecificacao = new ArrayList<Integer>();
				for (String idEspecificacao : form.getEspecificacao()) {
					idsSolicitacaoTipoEspecificacao.add(new Integer(idEspecificacao));
				}
			}
		} else {
			idsSolicitacaoTipo = null;
		}
		
		// ------------------------------
		// -- Data de Atendimento Inicial
		// ------------------------------
		if (form.getDataAtendimentoInicial() != null && !form.getDataAtendimentoInicial().trim().equals("")) {
			dataAtendimentoInicial = Util.converteStringParaDate(form.getDataAtendimentoInicial());
		} else {
			throw new ActionServletException("atencao.required", null, "Data de Atendimento Inicial");
		}
		
		// ------------------------------
		// -- Data de Atendimento Final
		// ------------------------------
		if (form.getDataAtendimentoFinal() != null && !form.getDataAtendimentoFinal().trim().equals("")) {
			dataAtendimentoFinal = Util.converteStringParaDate(form.getDataAtendimentoFinal());
		} else {
			dataAtendimentoFinal = dataAtendimentoInicial;
		}
		
		if ((dataAtendimentoFinal.getTime() - dataAtendimentoInicial.getTime()) > 1000L * 60L * 60L * 24L * 31L) {
			throw new ActionServletException("atencao.filtrar_intervalo_limite", null, "Data de Atendimento");
		}
		
		dataAtendimentoFinal = Util.adaptarDataFinalComparacaoBetween(dataAtendimentoFinal);
		
		// ------------------------------
		// -- UnidadeOrganizacional ou UnidadeSuperior
		// ------------------------------
		if (form.getIdUnidade() != null && !form.getIdUnidade().trim().equals("")) {
			idUnidadeOrganizacional = new Integer(form.getIdUnidade());
		}
		else if (form.getIdUnidadeSuperior() != null && !form.getIdUnidadeSuperior().trim().equals("")) {
			idUnidadeSuperior = new Integer(form.getIdUnidadeSuperior());
		}
		
		// ------------------------------
		// -- Municipio, Bairro
		// ------------------------------
		if (form.getIdMunicipio() != null && !form.getIdMunicipio().trim().equals("")) {
			idMunicipio = new Integer(form.getIdMunicipio());
			
			if (form.getIdBairro() != null && !form.getIdBairro().trim().equals("")) {
				codigoBairro = new Integer(form.getIdBairro());
			}
		}
		
		// ------------------------------
		// -- Tipo do Relatorio
		// ------------------------------
		int tipoRelatorio = TarefaRelatorio.TIPO_PDF;
		try {
			tipoRelatorio = Integer.parseInt(request.getParameter("tipoRelatorio")); 
		} catch (NumberFormatException e) { }
		
		// ------------------------------
		// -- Geracao do Relatorio
		// ------------------------------
		RelatorioGestaoSolicitacoesRAPorChefia relatorio = new RelatorioGestaoSolicitacoesRAPorChefia(usuario);
		relatorio.addParametro("situacao", situacao);
		relatorio.addParametro("idsSolicitacaoTipo", idsSolicitacaoTipo);
		relatorio.addParametro("idsSolicitacaoTipoEspecificacao", idsSolicitacaoTipoEspecificacao);
		relatorio.addParametro("dataAtendimentoInicial", dataAtendimentoInicial);
		relatorio.addParametro("dataAtendimentoFinal", dataAtendimentoFinal);
		relatorio.addParametro("idUnidadeOrganizacional", idUnidadeOrganizacional);
		relatorio.addParametro("idUnidadeSuperior", idUnidadeSuperior);
		relatorio.addParametro("idMunicipio", idMunicipio);
		relatorio.addParametro("codigoBairro", codigoBairro);
		relatorio.addParametro("tipoRelatorio", tipoRelatorio);
		
		return processarExibicaoRelatorio(
				relatorio, tipoRelatorio, request, response, mapping);
	}
	
}
