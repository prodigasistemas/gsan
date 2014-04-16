package gcom.gui.cobranca;



import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
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
 * [UC0838]FILTRAR LIGACAO DE ESGOTO ESGOTAMENTO
 * 
 * @author Arthur Carvalho
 * @date 25/08/08
 */

public class FiltrarCobrancaSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterCobrancaSituacao");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarCobrancaSituacaoActionForm filtrarCobrancaSituacaoActionForm = (FiltrarCobrancaSituacaoActionForm) actionForm;

		FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarCobrancaSituacaoActionForm.getId();
		String contaMotivoRevisao = filtrarCobrancaSituacaoActionForm.getContaMotivoRevisao();
		String descricao = filtrarCobrancaSituacaoActionForm.getDescricao();
		String indicadorUso = filtrarCobrancaSituacaoActionForm.getIndicadorUso();
		String indicadorExigenciaAdvogado =  filtrarCobrancaSituacaoActionForm.getIndicadorExigenciaAdvogado();
		String tipoPesquisa = filtrarCobrancaSituacaoActionForm.getTipoPesquisa();
		String indicadorBloqueioParcelamento = filtrarCobrancaSituacaoActionForm.getIndicadorBloqueioParcelamento();
		String indicadorBloqueioRetirada = filtrarCobrancaSituacaoActionForm.getIndicadorBloqueioRetirada();
		String indicadorBloqueioInclusao = filtrarCobrancaSituacaoActionForm.getIndicadorBloqueioInclusao();
		String profissao = filtrarCobrancaSituacaoActionForm.getProfissao();
		String ramoAtividade = filtrarCobrancaSituacaoActionForm.getRamoAtividade();
		String indicadorSelecaoApenasComPermissao = filtrarCobrancaSituacaoActionForm.getIndicadorSelecaoApenasComPermissao();
		String indicadorPrescricaoImoveisParticulares = filtrarCobrancaSituacaoActionForm.getIndicadorPrescricaoImoveisParticulares();
		
		//Indicador Atualizar
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {			
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {			
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		//CODIGO
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				
				filtroCobrancaSituacao.adicionarParametro(
					new ParametroSimples(
							FiltroCobrancaSituacao.ID, 
						id));
			}
		}
		
		//Motivo da situacal especial de faturamento
		if (contaMotivoRevisao != null && 
			!contaMotivoRevisao.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroCobrancaSituacao.adicionarParametro(
				new ParametroSimples(
						FiltroCobrancaSituacao.CONTA_MOTIVO_REVISAO, 
						contaMotivoRevisao));
		}
		
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroCobrancaSituacao
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroCobrancaSituacao.DESCRICAO, descricao));
			} else {

				filtroCobrancaSituacao.adicionarParametro(new ComparacaoTexto(
						FiltroCobrancaSituacao.DESCRICAO, descricao));
			}
		}
	
		// Exige Advogado
		if (indicadorExigenciaAdvogado != null && !indicadorExigenciaAdvogado.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.INDICADOR_EXIGENCIA_ADVOGADO, indicadorExigenciaAdvogado));
		}
		
		//Bloqueia Parcelamento
		if (indicadorBloqueioParcelamento != null && !indicadorBloqueioParcelamento.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.INDICADOR_BLOQUEIO_PARCELAMENTO, indicadorBloqueioParcelamento));
		}
		
		//Bloqueia Retirada
		if ( indicadorBloqueioRetirada != null && !indicadorBloqueioRetirada.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.INDICADOR_BLOQUEIO_RETIRADA, indicadorBloqueioRetirada));
		}
		//Indicador Selecao Apenas Com Permissao
		if ( indicadorSelecaoApenasComPermissao != null && !indicadorSelecaoApenasComPermissao.trim().equals("") ) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.INDICADOR_SELECAO_APENAS_COM_PERMISSAO, indicadorSelecaoApenasComPermissao));
		}
		
		// Indicador Prescricao Imoveis Particulares
		if(indicadorPrescricaoImoveisParticulares != null && 
				!indicadorPrescricaoImoveisParticulares.trim().equals("") ){
			
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro( 
				new ParametroSimples( FiltroCobrancaSituacao.INDICADOR_PRESCRICAO_IMOVEIS_PARTICULARES, 
						indicadorPrescricaoImoveisParticulares));
		}
		
		//Bloqueia Retirada
		if ( indicadorBloqueioInclusao != null && !indicadorBloqueioInclusao.trim().equals("") ) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.INDICADOR_BLOQUEIO_INCLUSAO, indicadorBloqueioInclusao));
		}

		
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.INDICADOR_USO, indicadorUso));
		}

		//Profissao
		if (profissao != null && 
			!profissao.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroCobrancaSituacao.adicionarParametro(
				new ParametroSimples(
						FiltroCobrancaSituacao.PROFISSAO, 
						profissao));
		}
		

		//Ramo Atividade
		if (ramoAtividade != null && 
			!ramoAtividade.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroCobrancaSituacao.adicionarParametro(
				new ParametroSimples(
						FiltroCobrancaSituacao.RAMO_ATIVIDADE, 
						ramoAtividade));
		}
		
		filtroCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("contaMotivoRevisao"); 
		
		Collection <CobrancaSituacao> colecaoCobrancaSituacao = fachada
				.pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class
						.getName());
		
		
		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros
		if (colecaoCobrancaSituacao == null
				|| colecaoCobrancaSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Situação de Cobrança");
		} else {
			httpServletRequest.setAttribute("colecaoCobrancaSituacao",
					colecaoCobrancaSituacao);
			CobrancaSituacao cobrancaSituacao= new CobrancaSituacao();
			cobrancaSituacao = (CobrancaSituacao) Util
					.retonarObjetoDeColecao(colecaoCobrancaSituacao);
			String idRegistroAtualizar = cobrancaSituacao.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}

		sessao.setAttribute("filtroCobrancaSituacao", filtroCobrancaSituacao);

		httpServletRequest.setAttribute("filtroCobrancaSituacao",
				filtroCobrancaSituacao);
		
		return retorno;
	}
}
