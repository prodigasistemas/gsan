package gcom.gui.batch;

import gcom.cobranca.CicloMeta;
import gcom.cobranca.CicloMetaGrupo;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.FiltroCicloMeta;
import gcom.cobranca.FiltroCicloMetaGrupo;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeCronograma;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.util.Util;


/**
 * Action responsável pela pre-exibição da pagina de inserir processo cobrança
 * 
 * @author Rodrigo Silveira, Anderson Italo
 * @created 11/08/2006, 28/09/2009
 */
public class InserirProcessoCobrancaComandadoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();

		String[] idsProcessosCobrancaCronogramaPagina = httpServletRequest
				.getParameterValues("idsCronograma");

		String[] idsProcessosCobrancaEventualPagina = httpServletRequest
				.getParameterValues("idsEventuais");

		Collection<Integer> idsProcessosCobrancaEventual = new ArrayList();
		Collection<Integer> idsProcessosCobrancaCronograma = new ArrayList();

		if (idsProcessosCobrancaCronogramaPagina != null) {
			// FS0010 - Verificar existência de meta para ação
			FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma = new FiltroCobrancaAcaoAtividadeCronograma();
			
			for(int i=0; i< idsProcessosCobrancaCronogramaPagina.length; i++ ){
			     
				if(!idsProcessosCobrancaCronogramaPagina[i].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
			      
				    if(idsProcessosCobrancaCronogramaPagina.length == 1){
				    	
				    	filtroCobrancaAcaoAtividadeCronograma.adicionarParametro(new ParametroSimples(
				    			FiltroCobrancaAcaoAtividadeCronograma.ID, idsProcessosCobrancaCronogramaPagina[i]));
				    	
				    }
				    else if( i == 0 ){
				    	
				    	filtroCobrancaAcaoAtividadeCronograma.adicionarParametro(new ParametroSimples(
				    			FiltroCobrancaAcaoAtividadeCronograma.ID, idsProcessosCobrancaCronogramaPagina[i], ParametroSimples.CONECTOR_OR, idsProcessosCobrancaCronogramaPagina.length));
				    	 
				    }
				    else if( i  == (idsProcessosCobrancaCronogramaPagina.length - 1) ){
				    	
				    	filtroCobrancaAcaoAtividadeCronograma.adicionarParametro(new ParametroSimples(
				    			FiltroCobrancaAcaoAtividadeCronograma.ID, idsProcessosCobrancaCronogramaPagina[i]));
				    
				    }
				    else{
				    	filtroCobrancaAcaoAtividadeCronograma.adicionarParametro(new ParametroSimples(
				    			FiltroCobrancaAcaoAtividadeCronograma.ID, idsProcessosCobrancaCronogramaPagina[i], ParametroSimples.CONECTOR_OR));
				    }
				}
			}
			
			filtroCobrancaAcaoAtividadeCronograma
				.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_ACAO);
			filtroCobrancaAcaoAtividadeCronograma
				.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_ACAO_CRONOGRAMA);
			filtroCobrancaAcaoAtividadeCronograma
			.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_GRUPO_CRONOGRAMA_MES);
			
			Collection colecaoCobrancaAcaoAtividadeCronograma = 
					fachada.pesquisar(filtroCobrancaAcaoAtividadeCronograma, CobrancaAcaoAtividadeCronograma.class.getName());
			
			/* Caso alguma das ações selecionadas para execução utilize metas e as metas não tenham sido geradas
			 * (Verificar a para cada uma das ações selecionadas se o indicador CBAC_ICUSAMETAS = 1 
			 * na tabela COBRANCA_ACAO. Caso seja verificar se existem metas geradas 
			 * na tabela CICLO_META_GRUPO com CLMT_ID = CLMT_ID da tabela CICLO_META 
			 * com CBAC_ID = CBAC_ID e  CLMT_AMREFRENCIA =  Ciclo da ação em questão)*/
			for (Iterator iter = colecaoCobrancaAcaoAtividadeCronograma
					.iterator(); iter.hasNext();) {
				
				CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) iter.next();
				
				//se a ação de cobrança usa metas
				if (cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma()
						.getCobrancaAcao().getIndicadorMetasCronograma() == CobrancaAcao.INDICADOR_USA_METAS_CRONOGRAMA_SIM){
					
					CobrancaAcao cobrancaAcao = cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaAcao();
					int anoMesReferencia = cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes().getAnoMesReferencia();
					
					FiltroCicloMeta filtroCicloMeta = new FiltroCicloMeta();
					
					filtroCicloMeta.adicionarParametro(new ParametroSimples(FiltroCicloMeta.COBRANCA_ACAO_ID, cobrancaAcao.getId()));
					
					filtroCicloMeta.adicionarParametro(new ParametroSimples(FiltroCicloMeta.ANO_MES_REFERENCIA, anoMesReferencia));
					
					Collection colecaoCicloMeta = fachada.pesquisar(filtroCicloMeta, CicloMeta.class.getName());
					
					CicloMeta cicloMeta = (CicloMeta)Util.retonarObjetoDeColecao(colecaoCicloMeta);
					
					//se não possui meta
					if (cicloMeta == null){
						throw new ActionServletException(
								"atencao.metas_acao_cobranca_nao_geradas", null, new String[] { cobrancaAcao.getDescricaoCobrancaAcao(),Util.formatarAnoMesParaMesAno(anoMesReferencia)});
					}
					
					FiltroCicloMetaGrupo filtroCicloMetaGrupo = new FiltroCicloMetaGrupo();
					filtroCicloMetaGrupo.adicionarParametro(new ParametroSimples(FiltroCicloMetaGrupo.CICLO_META_ID, 
						cicloMeta.getId()));
					Collection colecaoCicloMetaGrupo = fachada.pesquisar(
							filtroCicloMetaGrupo, CicloMetaGrupo.class.getName());
					
					//se as metas não foram geradas
					if (colecaoCicloMetaGrupo == null || colecaoCicloMetaGrupo.isEmpty()){
						throw new ActionServletException(
								"atencao.metas_acao_cobranca_nao_geradas", null, new String[] { cobrancaAcao.getDescricaoCobrancaAcao() + Util.formatarAnoMesParaMesAno(anoMesReferencia)});
					}
				}
			}
			
			for (int i = 0; i < idsProcessosCobrancaCronogramaPagina.length; i++) {
				idsProcessosCobrancaCronograma.add(Integer
						.parseInt(idsProcessosCobrancaCronogramaPagina[i]));

			}
		}

		if (idsProcessosCobrancaEventualPagina != null) {
			for (int i = 0; i < idsProcessosCobrancaEventualPagina.length; i++) {
				idsProcessosCobrancaEventual.add(Integer
						.parseInt(idsProcessosCobrancaEventualPagina[i]));

			}
		}
		
		fachada.inserirProcessoIniciadoCobrancaComandado(
				idsProcessosCobrancaCronograma, idsProcessosCobrancaEventual,
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));

		montarPaginaSucesso(httpServletRequest,
				"Processo(s) Iniciado(s) inserido(s) com sucesso.",
				"Inserir outro Processo", "exibirInserirProcessoAction.do");

		return retorno;
	}

}
