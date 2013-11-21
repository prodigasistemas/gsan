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
import gcom.seguranca.transacao.TabelaColuna;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela exibição da pagina de inserir motivo de retificação
 * [UC1117] Inserir Motivo Retificação
 * 
 * @author Mariana Victor
 * @date 11/01/2011
 */
public class InserirMotivoRetificacaoAction extends GcomAction {
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

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		InserirMotivoRetificacaoActionForm form = (InserirMotivoRetificacaoActionForm) actionForm;
	
		Fachada fachada = Fachada.getInstancia();
		
		//[FS0004] - Verificar preenchimento dos campos
        if(form.getDescricao() == null){
        	throw new ActionServletException("atencao.naoinformado",null, "Descrição");
        }
        if(form.getIndicadorCompetenciaConsumo() == null){
        	throw new ActionServletException("atencao.naoinformado",null, "Indicativo de Validar Competência de Consumo");
        }
        
		//[FS0001] – Verificar existência da descrição
		FiltroContaMotivoRetificacao filtroContaMotivoRetificacao = new FiltroContaMotivoRetificacao();
		filtroContaMotivoRetificacao.adicionarParametro(
				new ParametroSimples(FiltroContaMotivoRetificacao.DESCRICAO, form.getDescricao()));
		
		Collection colecaoContaMotivoRetificacao = fachada.pesquisar(filtroContaMotivoRetificacao, ContaMotivoRetificacao.class.getName());
		
		if (colecaoContaMotivoRetificacao != null && !colecaoContaMotivoRetificacao.isEmpty()) {
        	throw new ActionServletException("Motivo da Retificação com esta descrição já existe",
        			null, "Descrição");
		}
		
		ContaMotivoRetificacao contaMotivoRetificacao = new ContaMotivoRetificacao();
		Date data = new Date();
		contaMotivoRetificacao.setIndicadorUso(ConstantesSistema.SIM);
		contaMotivoRetificacao.setDescricao(
				form.getDescricao());
		contaMotivoRetificacao.setUltimaAlteracao(
				data);
		contaMotivoRetificacao.setIndicadorCompetenciaConsumo(
				new Short(form.getIndicadorCompetenciaConsumo()));
		
		if (form.getNumeroOcorrenciasNoAno() != null && !form.getNumeroOcorrenciasNoAno().equals("")) {
			contaMotivoRetificacao.setNumeroOcorrenciasNoAno(
					new Integer(form.getNumeroOcorrenciasNoAno()));
		}

		Integer id = (Integer) fachada.inserir(contaMotivoRetificacao);
		ContaMotivoRetificacao contaMotivo = new ContaMotivoRetificacao();
		contaMotivo.setId(id);
		
		if (sessao.getAttribute("colecaoCampos") != null
				&& !sessao.getAttribute("colecaoCampos").equals("")) {
			Collection<TabelaColuna> colecaoCampos = (Collection<TabelaColuna>) sessao.getAttribute("colecaoCampos");
			
			Iterator iterator = colecaoCampos.iterator();
			
			while (iterator.hasNext()) {
				TabelaColuna tabelaColuna = (TabelaColuna) iterator.next();

				ContaMotivoRetificacaoColunaPK contaMotivoRetificacaoColunaPK = new ContaMotivoRetificacaoColunaPK();
				contaMotivoRetificacaoColunaPK.setContaMotivoRetificacaoId(contaMotivo.getId());
				contaMotivoRetificacaoColunaPK.setTabelaColunaId(tabelaColuna.getId());
				
				ContaMotivoRetificacaoColuna contaMotivoRetificacaoColuna = new ContaMotivoRetificacaoColuna();
				contaMotivoRetificacaoColuna.setComp_id(contaMotivoRetificacaoColunaPK);
				contaMotivoRetificacaoColuna.setUltimaAlteracao(data);
				
				fachada.inserir(contaMotivoRetificacaoColuna);
			}
			
			
		}
		
		
		montarPaginaSucesso(httpServletRequest,
				"Motivo de retificação de conta inserido com sucesso",
				"Inserir outro motivo de retificação de conta",
				"exibirInserirMotivoRetificacaoAction.do?menu=sim",
				"exibirAtualizarMotivoRetificacaoAction.do?idContaMotivoRetificacao="+id,
				"Atualizar motivo de retificação de conta inserida");

		 return retorno;
	}

}
