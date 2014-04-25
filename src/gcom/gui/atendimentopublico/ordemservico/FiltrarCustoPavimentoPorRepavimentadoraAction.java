package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroUnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.atendimentopublico.ordemservico.FiltroUnidadeRepavimentadoraCustoPavimentoRua;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoRua;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.Maior;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.Menor;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1108] Filtrar Custo de Pavimento por Repavimentadora
 * 
 * @author Hugo Leonardo
 * @date 21/12/2010
 */
public class FiltrarCustoPavimentoPorRepavimentadoraAction extends GcomAction {


	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterCustoPavimentoPorRepavimentadoraAction");

		FiltrarCustoPavimentoPorRepavimentadoraActionForm form = (FiltrarCustoPavimentoPorRepavimentadoraActionForm) actionForm;

		FiltroUnidadeRepavimentadoraCustoPavimentoRua filtroCustoPavimentoRua = new FiltroUnidadeRepavimentadoraCustoPavimentoRua();
		FiltroUnidadeRepavimentadoraCustoPavimentoCalcada filtroCustoPavimentoCalcada = new FiltroUnidadeRepavimentadoraCustoPavimentoCalcada();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;
		
		String idUnidadeRepavimentadora = form.getIdUnidadeRepavimentadora();
		
		// Custo Pavimento Rua 
		String idTipoPavimentoRua = form.getIdTipoPavimentoRua();
		String dataVigenciaInicialPavimentoRua = form.getDataVigenciaInicialPavimentoRua();
		String dataVigenciaFinalPavimentoRua = form.getDataVigenciaFinalPavimentoRua();
		String situacaoVigenciaRua = form.getSituacaoVigenciaRua();
		
		// Custo Pavimento Calcada 
		String idTipoPavimentoCalcada = form.getIdTipoPavimentoCalcada();
		String dataVigenciaInicialPavimentoCalcada = form.getDataVigenciaInicialPavimentoCalcada();
		String dataVigenciaFinalPavimentoCalcada = form.getDataVigenciaFinalPavimentoCalcada();
		String situacaoVigenciaCalcada = form.getSituacaoVigenciaCalcada();
		
		// Verifica se o campo Unidade Repavimentadora foi informado
		if (!Util.verificarIdNaoVazio(idUnidadeRepavimentadora)) {

			throw new ActionServletException("atencao.unidade_repavimentadora_nao_informada");
		}else{
			
			peloMenosUmParametroInformado = true;
			
			filtroCustoPavimentoRua.adicionarParametro(new ParametroSimples(
					FiltroUnidadeRepavimentadoraCustoPavimentoRua.UNIDADE_REPAVIMENTADORA_ID, idUnidadeRepavimentadora));
			
			filtroCustoPavimentoCalcada.adicionarParametro(new ParametroSimples(
					FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.UNIDADE_REPAVIMENTADORA_ID, idUnidadeRepavimentadora));
			
			// setar Unidade Repavimentadora
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, idUnidadeRepavimentadora));
			
			Collection<UnidadeOrganizacional> colecaoUnidadeRepavimentadora = Fachada.getInstancia().pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

			if(!Util.isVazioOrNulo(colecaoUnidadeRepavimentadora)){
				UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeRepavimentadora);
				
				sessao.setAttribute("descricaoUnidadeRepavimentadora", unidadeOrganizacional.getDescricao());
			}
		}
		
		// Verifica se o campo Tipo Pavimento Rua foi informado
		if(Util.verificarIdNaoVazio(idTipoPavimentoRua)){
			
			peloMenosUmParametroInformado = true;
			filtroCustoPavimentoRua.adicionarParametro(new ParametroSimples(
					FiltroUnidadeRepavimentadoraCustoPavimentoRua.PAVIMENTO_RUA_ID, idTipoPavimentoRua));
		}
		
		filtroCustoPavimentoRua.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraCustoPavimentoRua.PAVIMENTO_RUA);
		
		// Verifica se o campo Tipo Pavimento Calcada foi informado
		if(Util.verificarIdNaoVazio(idTipoPavimentoCalcada)){
			
			peloMenosUmParametroInformado = true;
			filtroCustoPavimentoCalcada.adicionarParametro(new ParametroSimples(
					FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.PAVIMENTO_CALCADA_ID, idTipoPavimentoCalcada));
		}
		
		filtroCustoPavimentoCalcada.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.PAVIMENTO_CALCADA);
		
		// Vigente Rua
		if(situacaoVigenciaRua.equals("1")){
			
			peloMenosUmParametroInformado = true;
			Date dataCorrente = new Date();
			
			filtroCustoPavimentoRua.adicionarParametro(new MenorQue(FiltroUnidadeRepavimentadoraCustoPavimentoRua.DATA_VIGENCIA_INICIAL, dataCorrente, ParametroSimples.CONECTOR_AND, 3));
			filtroCustoPavimentoRua.adicionarParametro(new MaiorQue(FiltroUnidadeRepavimentadoraCustoPavimentoRua.DATA_VIGENCIA_FINAL, dataCorrente, ParametroSimples.CONECTOR_AND));
			filtroCustoPavimentoRua.adicionarParametro(new ParametroNaoNulo(FiltroUnidadeRepavimentadoraCustoPavimentoRua.DATA_VIGENCIA_FINAL, ParametroSimples.CONECTOR_OR));
			
			filtroCustoPavimentoRua.adicionarParametro(new MenorQue(FiltroUnidadeRepavimentadoraCustoPavimentoRua.DATA_VIGENCIA_INICIAL, dataCorrente, ParametroSimples.CONECTOR_AND, 2));
			filtroCustoPavimentoRua.adicionarParametro(new ParametroNulo(FiltroUnidadeRepavimentadoraCustoPavimentoRua.DATA_VIGENCIA_FINAL));
		}
		
		// Vigente Calçada
		if(situacaoVigenciaCalcada.equals("1")){
			
			peloMenosUmParametroInformado = true;
			Date dataCorrente = new Date();
			
			filtroCustoPavimentoCalcada.adicionarParametro(new MenorQue(FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.DATA_VIGENCIA_INICIAL, dataCorrente, ParametroSimples.CONECTOR_AND, 3));
			filtroCustoPavimentoCalcada.adicionarParametro(new MaiorQue(FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.DATA_VIGENCIA_FINAL, dataCorrente, ParametroSimples.CONECTOR_AND));
			filtroCustoPavimentoCalcada.adicionarParametro(new ParametroNaoNulo(FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.DATA_VIGENCIA_FINAL, ParametroSimples.CONECTOR_OR));
			
			filtroCustoPavimentoCalcada.adicionarParametro(new MenorQue(FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.DATA_VIGENCIA_INICIAL, dataCorrente, ParametroSimples.CONECTOR_AND, 2));
			filtroCustoPavimentoCalcada.adicionarParametro(new ParametroNulo(FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.DATA_VIGENCIA_FINAL));
		}
		
		// Não Vigente Rua
		if(situacaoVigenciaRua.equals("2")){
			
			peloMenosUmParametroInformado = true;
			Date dataCorrente = new Date();
			
			filtroCustoPavimentoRua.adicionarParametro(new Menor(FiltroUnidadeRepavimentadoraCustoPavimentoRua.DATA_VIGENCIA_FINAL, dataCorrente, ParametroSimples.CONECTOR_AND));
			filtroCustoPavimentoRua.adicionarParametro(new ParametroNaoNulo(FiltroUnidadeRepavimentadoraCustoPavimentoRua.DATA_VIGENCIA_FINAL, ParametroSimples.CONECTOR_OR));
			filtroCustoPavimentoRua.adicionarParametro(new Maior(FiltroUnidadeRepavimentadoraCustoPavimentoRua.DATA_VIGENCIA_INICIAL, dataCorrente));
			
		}
		
		// Não Vigente Calçada
		if(situacaoVigenciaCalcada.equals("2")){
			
			peloMenosUmParametroInformado = true;
			Date dataCorrente = new Date();
			
			filtroCustoPavimentoCalcada.adicionarParametro(new Menor(FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.DATA_VIGENCIA_FINAL, dataCorrente, ParametroSimples.CONECTOR_AND));
			filtroCustoPavimentoCalcada.adicionarParametro(new ParametroNaoNulo(FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.DATA_VIGENCIA_FINAL, ParametroSimples.CONECTOR_OR));
			filtroCustoPavimentoCalcada.adicionarParametro(new Maior(FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.DATA_VIGENCIA_INICIAL, dataCorrente));
			
		}
			
		// Vigência do Pavimento Rua
		if (Util.verificarNaoVazio(dataVigenciaInicialPavimentoRua) 
				&& Util.verificarNaoVazio(dataVigenciaFinalPavimentoRua)){
			
			Date dtInicio = null;
			Date dtFinal = null;
			
			if (!Util.validarDiaMesAno(dataVigenciaInicialPavimentoRua)) {
				
				dtInicio = Util.formatarDataInicial(Util.converteStringParaDate(dataVigenciaInicialPavimentoRua));
				if (!Util.validarDiaMesAno(dataVigenciaFinalPavimentoRua)) {
					
					dtFinal = Util.formatarDataInicial(Util.converteStringParaDate(dataVigenciaFinalPavimentoRua));
					if(Util.compararData(dtInicio, dtFinal) == 1){
						throw new ActionServletException("atencao.atencao.data_vigencia_final_menor");
					}
				}else{
					throw new ActionServletException("atencao.atencao.data_vigencia_final_invalida");
				}			
			}else{
				throw new ActionServletException("atencao.data_vigencia_inicial_invalida");
			}
			
			if(dtInicio != null && dtFinal != null){
				
				peloMenosUmParametroInformado = true;
				filtroCustoPavimentoRua.adicionarParametro(new Intervalo(FiltroUnidadeRepavimentadoraCustoPavimentoRua.DATA_VIGENCIA_INICIAL, dtInicio, dtFinal));
			}
			
		}else if (Util.verificarNaoVazio(dataVigenciaInicialPavimentoRua) && !Util.verificarNaoVazio(dataVigenciaFinalPavimentoRua)){
			
			throw new ActionServletException("atencao.data_vigencia_final.informe");
		}
			
		// Vigência do Pavimento Calcada
		if (Util.verificarNaoVazio(dataVigenciaInicialPavimentoCalcada) 
				&& Util.verificarNaoVazio(dataVigenciaFinalPavimentoCalcada)){
			
			Date dtInicio = null;
			Date dtFinal = null;
			
			if (!Util.validarDiaMesAno(dataVigenciaInicialPavimentoCalcada)) {
				
				dtInicio = Util.formatarDataInicial(Util.converteStringParaDate(dataVigenciaInicialPavimentoCalcada));
				if (!Util.validarDiaMesAno(dataVigenciaFinalPavimentoCalcada)) {
					
					dtFinal = Util.formatarDataInicial(Util.converteStringParaDate(dataVigenciaFinalPavimentoCalcada));
					if(Util.compararData(dtInicio, dtFinal) == 1){
						throw new ActionServletException("atencao.atencao.data_vigencia_final_menor");
					}
				}else{
					throw new ActionServletException("atencao.atencao.data_vigencia_final_invalida");
				}			
			}else{
				throw new ActionServletException("atencao.data_vigencia_inicial_invalida");
			}
			
			if(dtInicio != null && dtFinal != null){
				
				peloMenosUmParametroInformado = true;
				filtroCustoPavimentoCalcada.adicionarParametro(new Intervalo(FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.DATA_VIGENCIA_INICIAL, dtInicio, dtFinal));
				
			}
			
		}else if (Util.verificarNaoVazio(dataVigenciaInicialPavimentoCalcada) && !Util.verificarNaoVazio(dataVigenciaFinalPavimentoCalcada)){
			
			throw new ActionServletException("atencao.data_vigencia_final.informe");
		}

		filtroCustoPavimentoRua.setCampoOrderBy(FiltroUnidadeRepavimentadoraCustoPavimentoRua.PAVIMENTO_RUA_ID + " ASC", FiltroUnidadeRepavimentadoraCustoPavimentoRua.DATA_VIGENCIA_INICIAL + " DESC");
		
		filtroCustoPavimentoCalcada.setCampoOrderBy(FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.PAVIMENTO_CALCADA_ID + " ASC", FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.DATA_VIGENCIA_INICIAL + " DESC");

		Collection <UnidadeRepavimentadoraCustoPavimentoRua> colecaoUnidadeRepavimentadoraCustoPavimentoRua = 
				fachada.pesquisar(filtroCustoPavimentoRua, UnidadeRepavimentadoraCustoPavimentoRua.class.getName());
		
		Collection <UnidadeRepavimentadoraCustoPavimentoCalcada> colecaoUnidadeRepavimentadoraCustoPavimentoCalcada = 
			fachada.pesquisar(filtroCustoPavimentoCalcada, UnidadeRepavimentadoraCustoPavimentoCalcada.class.getName());
		
		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// Verifica se o checkbox Atualizar está marcado.
		if (form.getAtualizar() != null	&& form.getAtualizar().equalsIgnoreCase("1")) {
			
			httpServletRequest.setAttribute("atualizar", form.getAtualizar());
		}

		if(colecaoUnidadeRepavimentadoraCustoPavimentoRua == null || colecaoUnidadeRepavimentadoraCustoPavimentoRua.isEmpty()
				&& (colecaoUnidadeRepavimentadoraCustoPavimentoCalcada == null || colecaoUnidadeRepavimentadoraCustoPavimentoCalcada.isEmpty())){
			
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Unidade Repavimentadora Custo Pavimento");
		}
		
		// Pesquisa sem registros do Custo Pavimento Rua
		if (colecaoUnidadeRepavimentadoraCustoPavimentoRua != null && !colecaoUnidadeRepavimentadoraCustoPavimentoRua.isEmpty()) {
			
			UnidadeRepavimentadoraCustoPavimentoRua unidadeRepavimentadoraCustoPavimentoRua = new UnidadeRepavimentadoraCustoPavimentoRua();
			unidadeRepavimentadoraCustoPavimentoRua = (UnidadeRepavimentadoraCustoPavimentoRua) Util.retonarObjetoDeColecao(colecaoUnidadeRepavimentadoraCustoPavimentoRua);
			
			String idRegistroAtualizar = unidadeRepavimentadoraCustoPavimentoRua.getId().toString();

			sessao.setAttribute("idRegistroAtualizarRua", idRegistroAtualizar);
			httpServletRequest.setAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoRua", colecaoUnidadeRepavimentadoraCustoPavimentoRua);
			sessao.setAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoRua", colecaoUnidadeRepavimentadoraCustoPavimentoRua);
		
		}
		
		// Pesquisa sem registros do Custo Pavimento Calçada
		if (colecaoUnidadeRepavimentadoraCustoPavimentoCalcada != null && !colecaoUnidadeRepavimentadoraCustoPavimentoCalcada.isEmpty()) {
			
			UnidadeRepavimentadoraCustoPavimentoCalcada unidadeRepavimentadoraCustoPavimentoCalcada = new UnidadeRepavimentadoraCustoPavimentoCalcada();
			unidadeRepavimentadoraCustoPavimentoCalcada = (UnidadeRepavimentadoraCustoPavimentoCalcada) Util.retonarObjetoDeColecao(colecaoUnidadeRepavimentadoraCustoPavimentoCalcada);
			
			String idRegistroAtualizar = unidadeRepavimentadoraCustoPavimentoCalcada.getId().toString();

			sessao.setAttribute("idRegistroAtualizarCalcada", idRegistroAtualizar);
			httpServletRequest.setAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoCalcada", colecaoUnidadeRepavimentadoraCustoPavimentoCalcada);
			sessao.setAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoCalcada", colecaoUnidadeRepavimentadoraCustoPavimentoCalcada);
		
		}
		
		httpServletRequest.setAttribute("filtroCustoPavimentoRua", filtroCustoPavimentoRua);
		
		httpServletRequest.setAttribute("filtroCustoPavimentoCalcada", filtroCustoPavimentoCalcada);

		return retorno;

	}
}
