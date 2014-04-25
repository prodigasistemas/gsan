package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroMeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoDocumentoObrigatorio;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoDocumentoObrigatorio;
import gcom.atendimentopublico.registroatendimento.SolicitacaoDocumentoObrigatorioPK;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1067] - Informar Obrigatoriedade Documento Especificação
 * 
 * @author Hugo Leonardo
 *
 * @date 03/09/2010
 */

public class InformarSolicitacaoDocumentoObrigatorioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();
		
		ExibirInformarSolicitacaoDocumentoObrigatorioActionForm form = 
			(ExibirInformarSolicitacaoDocumentoObrigatorioActionForm) actionForm;
		
		Collection<SolicitacaoTipoEspecificacao> colecaoSolTipoEspec = new ArrayList();
		
		Collection<SolicitacaoDocumentoObrigatorio> colecaoSolDocObrig = new ArrayList();
		
		Collection<Integer> colecaoIdsTipoEspecificacao = new ArrayList();
		
		if (form.getIdsTipoEspecificacao() != null && !form.getIdsTipoEspecificacao().equals("-1") ){

			String[] array = form.getIdsTipoEspecificacao();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecaoIdsTipoEspecificacao.add(new Integer(array[i]));
				}
			}
			
			FiltroSolicitacaoTipoEspecificacao filtroSolTipoEspec = new FiltroSolicitacaoTipoEspecificacao();
			filtroSolTipoEspec.adicionarParametro(new ParametroSimplesIn( 
					FiltroSolicitacaoTipoEspecificacao.ID, colecaoIdsTipoEspecificacao));	
			
			colecaoSolTipoEspec = this.getFachada().pesquisar(filtroSolTipoEspec, 
					SolicitacaoTipoEspecificacao.class.getName());
		}
		
		
		if(colecaoSolTipoEspec != null && !colecaoSolTipoEspec.isEmpty()){
			
			Iterator iterator = null;
			iterator = colecaoSolTipoEspec.iterator();
			
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = null;
			
			// [FS0001] - verifica incompatibilidade das opções
			while (iterator.hasNext()) {
				solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) iterator.next();
				
				// Caso indicador Documento Solicitante Obrigatorio = 1 e
				// indicador Cliente obrigatório da Solicitação = 2
				if(form.getIcDocumentoSolicitanteObrigatorio().equals("1") 
						&& solicitacaoTipoEspecificacao.getIndicadorCliente().toString().equals("2")){
					
					throw new ActionServletException("atencao.especificacao.obrigatoriedade.cliente", null ,
							solicitacaoTipoEspecificacao.getId().toString() + " " + solicitacaoTipoEspecificacao.getDescricao());
				}
				
				// Caso indicador Documento Solicitante Obrigatorio = 2 e
				// indicador Validar Documento Cliente Responsavel = 1
				if(form.getIcDocumentoSolicitanteObrigatorio().equals("2")
						&& form.getIcValidarDocumentoClienteResponsavel().equals("1")){
					
					throw new ActionServletException("atencao.especificacao.obrigatoriedade.cliente_imovel", null ,
							solicitacaoTipoEspecificacao.getId().toString() + " " + solicitacaoTipoEspecificacao.getDescricao());
				}
				
				// Caso indicador Validar Documento Cliente Responsavel = 1 e
				// indicador Matricula obrigatório da Solicitação = 2
				if(form.getIcValidarDocumentoClienteResponsavel().equals("1")
						&& solicitacaoTipoEspecificacao.getIndicadorMatricula().equals("2")){
					
					throw new ActionServletException("atencao.especificacao.obrigatoriedade.imovel", null ,
							solicitacaoTipoEspecificacao.getId().toString() + " " + solicitacaoTipoEspecificacao.getDescricao());
				}
				
				if(form.getIcDocumentoSolicitanteObrigatorio().equals("1")){
					
					solicitacaoTipoEspecificacao.setIndicadorDocumentoObrigatorio(new Short("1"));
				}else{
					solicitacaoTipoEspecificacao.setIndicadorDocumentoObrigatorio(new Short("2"));
				}
				
				if(form.getIcValidarDocumentoClienteResponsavel().equals("1")){
					
					solicitacaoTipoEspecificacao.setIndicadorValidarDocResponsavel(new Short("1"));
				}else{
					solicitacaoTipoEspecificacao.setIndicadorValidarDocResponsavel(new Short("2"));
				}
				
				// Fluxo 7.1 - Atualiza Solicitação Tipo Especificação
				fachada.atualizar(solicitacaoTipoEspecificacao);
				
			}
			
			// Fluxo 7.2
			FiltroSolicitacaoDocumentoObrigatorio filtroSolDocObrig = new FiltroSolicitacaoDocumentoObrigatorio();
			filtroSolDocObrig.adicionarParametro(new ParametroSimplesIn( 
					FiltroSolicitacaoDocumentoObrigatorio.SOLICITACAO_TIPO_ESPECIFICACAO_ID, colecaoIdsTipoEspecificacao));	
			
			colecaoSolDocObrig = this.getFachada().pesquisar(filtroSolDocObrig, 
					SolicitacaoDocumentoObrigatorio.class.getName());
			
			if(colecaoSolDocObrig != null && !colecaoSolDocObrig.isEmpty()){
				
				Iterator iteratorSolDocObrig = null;
				iteratorSolDocObrig = colecaoSolDocObrig.iterator();
				SolicitacaoDocumentoObrigatorio solicitacaoDocumentoObrigatorio= null;
				
				while (iteratorSolDocObrig.hasNext()) {
					solicitacaoDocumentoObrigatorio = (SolicitacaoDocumentoObrigatorio) iteratorSolDocObrig.next();
					
					// Caso Documento Solicitante Obrigatório seja igual a "NÃO" e existir informações na 
					// Tabela sol_espec_doc_obrig_meio para o step_id selecionado(s), deverá remover 
					// todas as linhas existentes da tabela acima para o step_id correnpondente.

					fachada.remover(solicitacaoDocumentoObrigatorio);

				}
			}	

			// Fluxo - 7.3.1.1 Incluir SOL_ESPEC_DOC_OBRIG_MEIO
			if(form.getIdsMeioSolicitacaoObrigatorio() != null){
				
				List lista = Arrays.asList(form.getIdsMeioSolicitacaoObrigatorio());  
				Collection<MeioSolicitacao> colecaoMeioSolicitacao = new ArrayList();
				
				FiltroMeioSolicitacao filtroMeioSolicitacao = new FiltroMeioSolicitacao();
				filtroMeioSolicitacao.adicionarParametro(new ParametroSimplesIn( 
						FiltroMeioSolicitacao.ID, lista));	
				
				colecaoMeioSolicitacao = this.getFachada().pesquisar(filtroMeioSolicitacao, 
						MeioSolicitacao.class.getName());
				
				if(colecaoMeioSolicitacao != null && !colecaoMeioSolicitacao.isEmpty() 
						&& form.getIcDocumentoSolicitanteObrigatorio().equals("1") ){
					
					Iterator iteratorMeioSolicitacao = colecaoMeioSolicitacao.iterator();
					
					iterator = colecaoSolTipoEspec.iterator();
					solicitacaoTipoEspecificacao = null;
					MeioSolicitacao meioSolicitacao = null;
					SolicitacaoDocumentoObrigatorio solicitacaoDocumentoObrigatorio = null;
					SolicitacaoDocumentoObrigatorioPK solDocObrigatorioPK = null;
					
					
					while (iterator.hasNext()) {
						solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) iterator.next();
						
						iteratorMeioSolicitacao = colecaoMeioSolicitacao.iterator();
						
						while (iteratorMeioSolicitacao.hasNext()) {
						
							meioSolicitacao = (MeioSolicitacao) iteratorMeioSolicitacao.next();
							
							solDocObrigatorioPK = new SolicitacaoDocumentoObrigatorioPK(
									solicitacaoTipoEspecificacao, meioSolicitacao);
							
							solicitacaoDocumentoObrigatorio = new SolicitacaoDocumentoObrigatorio(
									solDocObrigatorioPK, new Date());
							
							fachada.inserir(solicitacaoDocumentoObrigatorio);
						}
					}
				}else{
					//throw new ActionServletException("atencao.naocadastrado", null, "Meio Solicitação");
				}
			}
			
		}
		
		
		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Obrigatoriedade(s) de Documento(s) por Especificação "
				+ "eferuada(s) com sucesso.",
				"Realizar outra Manutenção em Obrigatoriedade de Documento por Especificação",
				"exibirInformarSolicitacaoDocumentoObrigatorioAction.do?menu=sim");

		return retorno;
	}

}
