package gcom.gui.faturamento.conta;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.conta.ContaMotivoRetificacaoColuna;
import gcom.faturamento.conta.ContaMotivoRetificacaoColunaPK;
import gcom.faturamento.conta.FiltroContaMotivoRetificacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarMotivoRetificacaoAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarMotivoRetificacaoActionForm form = (AtualizarMotivoRetificacaoActionForm) actionForm;

		// [FS0004] - Verificar preenchimento dos campos
        if(form.getDescricao() == null){
        	throw new ActionServletException("atencao.naoinformado",null, "Descrição");
        }
        if(form.getIndicadorCompetenciaConsumo() == null){
        	throw new ActionServletException("atencao.naoinformado",null, "Indicativo de Validar Competência de Consumo");
        }
        if(form.getIndicadorUso() == null){
        	throw new ActionServletException("atencao.naoinformado",null, "Indicativo de Uso");
        }
        
        ContaMotivoRetificacao contaMotivoRetificacao = (ContaMotivoRetificacao) sessao.getAttribute("objetoContaMotivoRetificacao");

        // [FS0006] - Atualização realizada por outro usuário
        FiltroContaMotivoRetificacao filtroContaMotivoRetificacao = new FiltroContaMotivoRetificacao();
        filtroContaMotivoRetificacao.adicionarParametro(
        		new ParametroSimples(FiltroContaMotivoRetificacao.CODIGO, contaMotivoRetificacao.getId()));
        Collection colecaoContaMotivoRetificacao = fachada
        	.pesquisar(filtroContaMotivoRetificacao, ContaMotivoRetificacao.class.getName());
        if (colecaoContaMotivoRetificacao != null && !colecaoContaMotivoRetificacao.isEmpty()){
        	ContaMotivoRetificacao motivoRetificacao = (ContaMotivoRetificacao) Util.retonarObjetoDeColecao(colecaoContaMotivoRetificacao);
        	if (motivoRetificacao.getUltimaAlteracao()
    				.after(contaMotivoRetificacao.getUltimaAlteracao())) {
        		throw new ActionServletException("atencao.naoinformado", null, "Motivo de Retificação da Conta");
        	}
        }
        
        contaMotivoRetificacao.setDescricao(form.getDescricao());
        contaMotivoRetificacao.setIndicadorCompetenciaConsumo(
        		new Short(form.getIndicadorCompetenciaConsumo()));
        contaMotivoRetificacao.setIndicadorUso(
        		new Short(form.getIndicadorUso()));
        if(form.getNumeroOcorrenciasNoAno() != null
        		&& !form.getNumeroOcorrenciasNoAno().equals("")){
        	contaMotivoRetificacao.setNumeroOcorrenciasNoAno(
        			new Integer(form.getNumeroOcorrenciasNoAno()));
        }

        Collection<ContaMotivoRetificacaoColuna> colecaoContaMotivoRetificacaoColuna = null;

        Collection<ContaMotivoRetificacaoColuna> colecaoCamposRemover = null;
        
        if (sessao.getAttribute("colecaoCamposRemover") != null
        		&& !sessao.getAttribute("colecaoCamposRemover").equals("")) {
        	colecaoCamposRemover = (Collection<ContaMotivoRetificacaoColuna>)sessao.getAttribute("colecaoCamposRemover");
        	Iterator iterator = colecaoCamposRemover.iterator();
        	while(iterator.hasNext()) {
        		ContaMotivoRetificacaoColuna motivoRetificacaoColuna = (ContaMotivoRetificacaoColuna)iterator.next();
        		fachada.remover(motivoRetificacaoColuna);
        	}
        }
        
        if (sessao.getAttribute("colecaoContaMotivoRetificacaoColuna") != null
        		&& !sessao.getAttribute("colecaoContaMotivoRetificacaoColuna").equals("")) {
        	colecaoContaMotivoRetificacaoColuna = (Collection<ContaMotivoRetificacaoColuna>)sessao.getAttribute("colecaoContaMotivoRetificacaoColuna");
        	Iterator iterator = colecaoContaMotivoRetificacaoColuna.iterator();
        	while(iterator.hasNext()) {
        		ContaMotivoRetificacaoColuna motivoRetificacaoColuna = (ContaMotivoRetificacaoColuna)iterator.next();
        		
        		if (motivoRetificacaoColuna.getUltimaAlteracao() == null){
        			ContaMotivoRetificacaoColunaPK compId = new ContaMotivoRetificacaoColunaPK();
        			compId.setContaMotivoRetificacaoId(motivoRetificacaoColuna.getContaMotivoRetificacao().getId());
        			compId.setTabelaColunaId(motivoRetificacaoColuna.getTabelaColuna().getId());
        			motivoRetificacaoColuna.setComp_id(compId);
        			motivoRetificacaoColuna.setUltimaAlteracao(new Date());
        			fachada.inserir(motivoRetificacaoColuna);
        		} else {
        			motivoRetificacaoColuna.setUltimaAlteracao(new Date());
        			fachada.atualizar(motivoRetificacaoColuna);
        		}
        		
        	}
        }
        
        fachada.atualizar(contaMotivoRetificacao);

        sessao.removeAttribute("colecaoCamposRemover");
        sessao.removeAttribute("colecaoContaMotivoRetificacaoColuna");
        sessao.removeAttribute("objetoContaMotivoRetificacao");
        sessao.removeAttribute("idContaMotivoRetificacao");

		montarPaginaSucesso(httpServletRequest,
				"Motivo de Retificação "
						+ contaMotivoRetificacao.getDescricao()
						+ " atualizado com sucesso!",
				"Manter outro Motivo de Retificação",
				"exibirFiltrarMotivoRetificacaoAction.do?menu=sim");

		return retorno;
		
	}

}
