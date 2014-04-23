package gcom.gui.faturamento;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoComando;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarSituacaoEspecialFaturamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterSituacaoEspecialFaturamento");
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FaturamentoSituacaoComando comandoFiltro = this.validarEGerarFiltro(actionForm);	
		
		Integer totalRegistros = fachada.pesquisarSituacaoEspecialFaturamentoCount(comandoFiltro);
		
		retorno = this.controlarPaginacao(httpServletRequest, retorno, totalRegistros);
		
		Collection<FaturamentoSituacaoComando> colecaoFaturamentoSituacaoComando = fachada.
		pesquisarSituacaoEspecialFaturamento(comandoFiltro,(Integer) 
				httpServletRequest.getAttribute("numeroPaginasPesquisa"));
		
			
		if ( Util.isVazioOrNulo(colecaoFaturamentoSituacaoComando)) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,"Situação Especial de Faturamento");
		}

		sessao.setAttribute("colecaoFaturamentoSituacaoComando",colecaoFaturamentoSituacaoComando);
		sessao.setAttribute("idRegistroAtualizar", colecaoFaturamentoSituacaoComando.iterator().next().getId().toString());

		return retorno;

	}

	/**
	 * Valida todos os campos das inscrições inicial e final.
	 * Verifica se o destino é >= a origem.
	 *
	 *@since 12/08/2009
	 *@author Marlon Patrick
	 */
	private void validacaoInscricaoOrigemDestino(FiltrarSituacaoEspecialFaturamentoActionForm form) {
	
		validarLocalidadeOrigemDestino(form);
		validarPeriodoOrigemDestino(form);
		/*
		validarSetorComercialOrigemDestino(form);
		validarQuadraOrigemDestino(form);
		validarLoteOrigemDestino(form);
		validarSubloteOrigemDestino(form);
		*/
	}



	/**
	 *Esse método valida se a localidade destino é >= a localidade origem.
	 *
	 *@since 12/08/2009
	 *@author Marlon Patrick
	 */
	private void validarLocalidadeOrigemDestino(
			FiltrarSituacaoEspecialFaturamentoActionForm form) {
		if (Util.verificarNaoVazio(form.getLocalidadeOrigemID())
				&& Util.verificarNaoVazio(form.getLocalidadeDestinoID())) {
				
				int origem = Integer.parseInt(form.getLocalidadeOrigemID());
				int destino = Integer.parseInt(form.getLocalidadeDestinoID());
				
				if (origem > destino){
					throw new ActionServletException
						("atencao.localidade.final.maior.localidade.inicial",null, "");
				}				
		}
	}
	
	/**
	 *Esse método valida se o Periodo destino é >= ao Periodo origem.
	 *
	 *@since 23/03/2010
	 *@author Hugo Fernando
	 */
	private void validarPeriodoOrigemDestino(
			FiltrarSituacaoEspecialFaturamentoActionForm form) {
		
		if (Util.verificarNaoVazio(form.getMesAnoReferenciaFaturamentoInicial())
				&& Util.verificarNaoVazio(form.getMesAnoReferenciaFaturamentoFinal())) {
				
				Integer origem = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferenciaFaturamentoInicial());
				Integer destino = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferenciaFaturamentoFinal());
				
				if (origem.intValue() > destino.intValue()){
					throw new ActionServletException
						("atencao.mes.ano.inicial.maior.mes.ano.final",null, "");
				}				
		}
	}

	private FaturamentoSituacaoComando validarEGerarFiltro(ActionForm actionForm){

		FiltrarSituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm = 
			(FiltrarSituacaoEspecialFaturamentoActionForm) actionForm;

		FaturamentoSituacaoComando comandoFiltro = new FaturamentoSituacaoComando();
		
		Fachada fachada = Fachada.getInstancia();
		String idImovel = null;
		String localidadeOrigemID = null;
		String periodoInicial = null;
		String localidadeDestinoID = null;
		String periodoFinal = null;
		String idFaturamentoSituacaoTipo = null;
		
		idImovel = situacaoEspecialFaturamentoActionForm.getIdImovel();
		localidadeOrigemID = situacaoEspecialFaturamentoActionForm.getLocalidadeOrigemID();
		periodoInicial = situacaoEspecialFaturamentoActionForm.getMesAnoReferenciaFaturamentoInicial();
	
		localidadeDestinoID = situacaoEspecialFaturamentoActionForm.getLocalidadeDestinoID();
		periodoFinal = situacaoEspecialFaturamentoActionForm.getMesAnoReferenciaFaturamentoFinal();
		
		idFaturamentoSituacaoTipo = situacaoEspecialFaturamentoActionForm.getIdFaturamentoSituacaoTipo();
	

		boolean peloMenosUmParametroInformado = false;
		
		if (Util.verificarNaoVazio(idImovel)) {
			
			if(fachada.verificarExistenciaImovel(new Integer(idImovel)) <= 0){
				throw new ActionServletException("atencao.matricula.imovel.inexistente");
			}
			peloMenosUmParametroInformado = true;
			comandoFiltro.setImovel(new Imovel(Integer.parseInt(idImovel)));
		}

		if (Util.verificarNaoVazio(localidadeOrigemID)) {
			
			Integer LocalidadeInicial = fachada.verificarExistenciaLocalidade(new Integer(localidadeOrigemID));
			if( LocalidadeInicial == null || LocalidadeInicial <= 0){
				throw new ActionServletException("atencao.localidade.inexistente");
			}
			peloMenosUmParametroInformado = true;
			comandoFiltro.setLocalidadeInicial(new Localidade(Integer.parseInt(localidadeOrigemID)));
		}
       
		if (Util.verificarNaoVazio(periodoInicial)) {
			peloMenosUmParametroInformado = true;
			comandoFiltro.setAnoMesInicialSituacaoFaturamento(Util.formatarMesAnoComBarraParaAnoMes(periodoInicial));
		}


		// campos localidade destino
		if (Util.verificarNaoVazio(localidadeDestinoID)) {

			if( !localidadeDestinoID.equals(localidadeOrigemID)){
		Integer localidadeFinal = fachada.verificarExistenciaLocalidade(new Integer(localidadeDestinoID));
				if( localidadeFinal == null || localidadeFinal <= 0){
					throw new ActionServletException("atencao.localidade.inexistente");
				}
			}

			peloMenosUmParametroInformado = true;
			
			comandoFiltro.setLocalidadeFinal(new Localidade(Integer.parseInt(localidadeDestinoID)));
		}
		
		if (Util.verificarNaoVazio(periodoFinal)) {
			peloMenosUmParametroInformado = true;
			comandoFiltro.setAnoMesFinalSituacaoFaturamento(Util.formatarMesAnoComBarraParaAnoMes(periodoFinal));
		}

        
		if (Util.verificarNaoVazio(idFaturamentoSituacaoTipo) && !idFaturamentoSituacaoTipo.equals("-1")) {
			
			FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();

			filtroFaturamentoSituacaoTipo.adicionarParametro(new ParametroSimples(FiltroFaturamentoSituacaoTipo.ID,
					idFaturamentoSituacaoTipo));
			
			Collection colecaoFatSituacaoTipo = this.getFachada().pesquisar(
					filtroFaturamentoSituacaoTipo,
					FaturamentoSituacaoTipo.class.getName());
			
			FaturamentoSituacaoTipo faturamentoSituacaoTipo = (FaturamentoSituacaoTipo) Util.retonarObjetoDeColecao(colecaoFatSituacaoTipo);
			
			if(faturamentoSituacaoTipo != null){
				peloMenosUmParametroInformado = true;
				comandoFiltro.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);	
			}
		}


		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		this.validacaoInscricaoOrigemDestino(situacaoEspecialFaturamentoActionForm);
		
		return comandoFiltro;
	}
	
}
