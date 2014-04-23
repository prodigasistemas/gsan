package gcom.gui.atendimentopublico.ligacaoesgoto;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0789] Filtrar Situacao de Ligação de Esgoto
 * 
 * @author Bruno Barros
 *
 * @date 15/05/2008
 */
public class FiltrarLigacaoEsgotoSituacaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirManterLigacaoEsgotoSituacao");
		
		// Sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Form
		FiltrarLigacaoEsgotoSituacaoActionForm filtrarSistemaEsgotoActionForm 
			= (FiltrarLigacaoEsgotoSituacaoActionForm) actionForm;
		
		// Verificamos os campos obrigatórios foram informados
		boolean informouAoMenos1Parametro = false;
		
		// Criamos o filtro para a pesquisa
		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
		
		// Verificamos se o código foi informado
		if ( filtrarSistemaEsgotoActionForm.getCodigo() != null &&
			 !filtrarSistemaEsgotoActionForm.getCodigo().equals( "" ) ){
			filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples( FiltroLigacaoEsgotoSituacao.ID, filtrarSistemaEsgotoActionForm.getCodigo() )  );
			informouAoMenos1Parametro = true;
		}
		
		// Verificamos se a descrição foi informada
		if (filtrarSistemaEsgotoActionForm.getDescricao() != null && 
			!filtrarSistemaEsgotoActionForm.getDescricao().trim().equalsIgnoreCase("")) {
			informouAoMenos1Parametro = true;
			if (filtrarSistemaEsgotoActionForm.getDescricao() != null &&
				filtrarSistemaEsgotoActionForm.getDescricao().equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				filtroLigacaoEsgotoSituacao
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroLigacaoEsgotoSituacao.DESCRICAO, filtrarSistemaEsgotoActionForm.getDescricao() ) );
			} else {
				filtroLigacaoEsgotoSituacao.adicionarParametro(new ComparacaoTexto(
						FiltroLigacaoEsgotoSituacao.DESCRICAO, filtrarSistemaEsgotoActionForm.getDescricao() ) );
			}
		}
		
		// Verificamos se a descrição abreviada foi informada
		if ( filtrarSistemaEsgotoActionForm.getDescricaoAbreviada() != null &&
			 !filtrarSistemaEsgotoActionForm.getDescricaoAbreviada().equals( "" ) ){
			filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples( FiltroLigacaoEsgotoSituacao.DESCRICAOABREVIADA, filtrarSistemaEsgotoActionForm.getDescricaoAbreviada() )  );
			informouAoMenos1Parametro = true;			
		}
		
		// Verificamos se o Consumo Minimo foi informado
		if ( filtrarSistemaEsgotoActionForm.getConsumoMinimoFaturamento() != null &&
			 !filtrarSistemaEsgotoActionForm.getConsumoMinimoFaturamento().equals( "" ) ){
			// Verificamos se o volume minimo é um número válido
			if ( Util.validarStringNumerica( filtrarSistemaEsgotoActionForm.getConsumoMinimoFaturamento() ) ){
				filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples( FiltroLigacaoEsgotoSituacao.VOLUMEMINIMOFATURAMENTO, filtrarSistemaEsgotoActionForm.getConsumoMinimoFaturamento() ) );
				informouAoMenos1Parametro = true;
			} else {
				throw new ActionServletException( "atencao.campo_texto.numero_obrigatorio", null, "Consumo Mínimo" );
			}
		}
		
		// Verificamos se o indicador de faturamento está sendo usado
		if ( !filtrarSistemaEsgotoActionForm.getIndicadorFaturamento().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples( FiltroLigacaoEsgotoSituacao.INDICADORFATURAMENTOSITUACAO, filtrarSistemaEsgotoActionForm.getIndicadorFaturamento() ) );
			informouAoMenos1Parametro = true;			
		}
		
		// Verificamos se o indicador de existencia de rede está sendo usado
		if ( !filtrarSistemaEsgotoActionForm.getIndicadorExistenciaRede().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples( FiltroLigacaoEsgotoSituacao.INDICADOREXISTENCIAREDE, filtrarSistemaEsgotoActionForm.getIndicadorExistenciaRede() ) );
			informouAoMenos1Parametro = true;			
		}
		
		// Verificamos se o indicador de existencia de ligação está sendo usado
		if ( !filtrarSistemaEsgotoActionForm.getIndicadorExistenciaLigacao().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples( FiltroLigacaoEsgotoSituacao.INDICADOREXISTENCIALIGACAO, filtrarSistemaEsgotoActionForm.getIndicadorExistenciaLigacao() ) );
			informouAoMenos1Parametro = true;			
		}
		
		// Verificamos se o indicador de uso está sendo usado
		if ( !filtrarSistemaEsgotoActionForm.getIndicadorUso().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples( FiltroLigacaoEsgotoSituacao.INDICADORUSO, filtrarSistemaEsgotoActionForm.getIndicadorUso() ) );
			informouAoMenos1Parametro = true;			
		}	
		
		// FS0001 - Verificar preenchimento dos campos
		if ( !informouAoMenos1Parametro ){
			throw new ActionServletException( "atencao.filtrar_informar_um_filtro" );
		}		
		
		// Pega a instancia da fachada		
		Fachada fachada = Fachada.getInstancia();
		
		Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = fachada
				.pesquisar( filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName() );

		// Pesquisa sem registros
		if (colecaoLigacaoEsgotoSituacao == null
				|| colecaoLigacaoEsgotoSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Situação de Ligação de Esgoto");
		} else {
			// Guardamos a colecao no request e o id do registro que será atualizado
			httpServletRequest.setAttribute( "colecaoLigacaoEsgotoSituacao", colecaoLigacaoEsgotoSituacao );
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
			
			ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util
					.retonarObjetoDeColecao(colecaoLigacaoEsgotoSituacao);
			
			String idRegistroAtualizacao = ligacaoEsgotoSituacao.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}
		
		// Colocamos o filtro na sessao		
		sessao.setAttribute("filtroLigacaoEsgotoSituacao", filtroLigacaoEsgotoSituacao);
		
		httpServletRequest.setAttribute("filtroLigacaoEsgotoSituacao",
				filtroLigacaoEsgotoSituacao);

		return retorno;
	}
}
