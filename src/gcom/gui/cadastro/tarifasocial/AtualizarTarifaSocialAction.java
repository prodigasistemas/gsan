package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.tarifasocial.RendaTipo;
import gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialRevisaoMotivo;
import gcom.cadastro.tarifasocial.bean.TarifaSocialHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Rafael Corrêa
 */
public class AtualizarTarifaSocialAction extends GcomAction {
	
	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("avancar");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarDadosTarifaSocialActionForm atualizarDadosTarifaSocialActionForm = (AtualizarDadosTarifaSocialActionForm) actionForm;
		
		String concluir = httpServletRequest.getParameter("concluir");
		
		if (concluir != null && !concluir.equals("")) {
			retorno = actionMapping
			.findForward("concluir");
		}
		
		Collection colecaoTarifaSocialHelper = (Collection) sessao.getAttribute("colecaoTarifaSocialHelper");
		
		Imovel imovelSessao = (Imovel) sessao.getAttribute("imovelTarifa");
		
		TarifaSocialHelper tarifaSocialHelperAtualizar = null;
		
		if (sessao.getAttribute("tarifaSocialHelperAtualizar") != null) {
			tarifaSocialHelperAtualizar = (TarifaSocialHelper) sessao.getAttribute("tarifaSocialHelperAtualizar");
		} else {
			tarifaSocialHelperAtualizar = new TarifaSocialHelper();
		}
		
		TarifaSocialDadoEconomia tarifaSocialDadoEconomia = new TarifaSocialDadoEconomia();
		
		TarifaSocialHelper tarifaSocialHelper = (TarifaSocialHelper) sessao.getAttribute("tarifaSocialHelper");
		
		// Seta os valores dentro do objeto
		String numeroCartaoProgramaSocial = atualizarDadosTarifaSocialActionForm.getNumeroCartaoProgramaSocial();
		String tipoCartaoProgramaSocial = atualizarDadosTarifaSocialActionForm.getTipoCartao();
		String dataValidadeCartao = atualizarDadosTarifaSocialActionForm.getDataValidadeCartao();
		String dataComparecimentoCarta = atualizarDadosTarifaSocialActionForm.getDataComparecimentoCarta();
		String numeroParcelas = atualizarDadosTarifaSocialActionForm.getNumeroMesesAdesao();
		String numeroContratoCompanhiaEletrica = atualizarDadosTarifaSocialActionForm.getNumeroContratoCelpe();
		Long numeroContratoCompanhiaEletricaFormatado = null;
		String consumoMedio = atualizarDadosTarifaSocialActionForm.getConsumoCelpe();
		Integer consumoMedioFormatado = null;
		String numeroIptu = atualizarDadosTarifaSocialActionForm.getNumeroIPTU();
		String numeroIptuFormatado = null;
		String areaConstruida = atualizarDadosTarifaSocialActionForm.getAreaConstruida();
		BigDecimal areaConstruidaFormatada = null;
		String idAreaConstruidaFaixa = atualizarDadosTarifaSocialActionForm.getAreaConstruidaFaixa();
		String valorRenda = atualizarDadosTarifaSocialActionForm.getValorRendaFamiliar();
		BigDecimal valorRendaFormatada = null;
		String tipoRenda = atualizarDadosTarifaSocialActionForm.getRendaTipo();
		String motivoRevisao = atualizarDadosTarifaSocialActionForm.getMotivoRevisao();
		String numeroMoradores = atualizarDadosTarifaSocialActionForm.getNumeroMoradores();
		Short numeroMoradoresFormatado = null;
		Date dataCorrente = new Date();
		
		Integer idImovelTarifaSocial = null;
		
		// Id da Tarifa Social
		tarifaSocialDadoEconomia.setId(tarifaSocialHelper.getTarifaSocialDadoEconomia().getId());
		
		// Data de Implantação
		tarifaSocialDadoEconomia.setDataImplantacao(tarifaSocialHelper.getTarifaSocialDadoEconomia().getDataImplantacao());
		
		// Número do Cartão do Programa Social
		if (numeroCartaoProgramaSocial != null && !numeroCartaoProgramaSocial.trim().equals("")) {
			tarifaSocialDadoEconomia.setNumeroCartaoProgramaSocial(new Long(numeroCartaoProgramaSocial));
		}
		
		// Tipo do Cartão do Programa Social
		if (tipoCartaoProgramaSocial != null && !tipoCartaoProgramaSocial.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			TarifaSocialCartaoTipo tarifaSocialCartaoTipo = new TarifaSocialCartaoTipo();
			tarifaSocialCartaoTipo.setId(new Integer(tipoCartaoProgramaSocial));
			tarifaSocialDadoEconomia.setTarifaSocialCartaoTipo(tarifaSocialCartaoTipo);
			
			if (tarifaSocialHelper.getClienteImovel() != null) {
			
			// [FS0016] - Verificar duplicidade do Cartão do Programa Social
				fachada.verificarDuplicidadeCartaoProgramaSocial(new Long(
						numeroCartaoProgramaSocial), tarifaSocialCartaoTipo,
						tarifaSocialHelper.getClienteImovel().getImovel().getId());
			
			}
		}
		
		// Data de Validade do Cartão
		if (dataValidadeCartao != null && !dataValidadeCartao.trim().equals("")) {
			Date dataValidadeCartaoFormatada = Util.converteStringParaDate(dataValidadeCartao);
			
			if (dataCorrente.compareTo(dataValidadeCartaoFormatada) > 0) {
				throw new ActionServletException("atencao.data_validade.menor.data_corrente");
			}
			
			tarifaSocialDadoEconomia.setDataValidadeCartao(dataValidadeCartaoFormatada);
		}
		
		

		if (dataComparecimentoCarta != null && !dataComparecimentoCarta.trim().equals("")) {
			Date dataComparecimentoCartaFormatada = Util.converteStringParaDate(dataComparecimentoCarta);
			
			if (Util.compararData(dataComparecimentoCartaFormatada,dataCorrente) == 1) {
				throw new ActionServletException("atencao.tarifasocial.data_comparecimento_carta_maior_que_hoje");
			}
			
			tarifaSocialHelperAtualizar.setDataComparecimentoCarta(dataComparecimentoCartaFormatada);
		}
		
		
		// Número de Parcelas 
		if (numeroParcelas != null && !numeroParcelas.trim().equals("")) {
			tarifaSocialDadoEconomia.setNumeroMesesAdesao(new Short(numeroParcelas));
		}
		
		// Consumo Médio da Companhia de Energia Elétrica
		if (consumoMedio != null && !consumoMedio.trim().equals("")) {
			consumoMedioFormatado = new Integer(consumoMedio);
			tarifaSocialDadoEconomia.setConsumoCelpe(new Integer(consumoMedio));
		}
		
		// Valor da Renda Familiar
		if (valorRenda != null && !valorRenda.trim().equals("")) {
			valorRendaFormatada = Util.formatarMoedaRealparaBigDecimal(valorRenda);
			tarifaSocialDadoEconomia.setValorRendaFamiliar(valorRendaFormatada);
		}
		
		// Tipo da Renda
		if (tipoRenda != null && !tipoRenda.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			RendaTipo rendaTipo = new RendaTipo();
			rendaTipo.setId(new Integer(tipoRenda));
			if (tipoRenda.equals(RendaTipo.COMPROVADA.toString())) {
				rendaTipo.setDescricao("COMPROVADA");
			} else if (tipoRenda.equals(RendaTipo.DECLARADA.toString())) {
				rendaTipo.setDescricao("DECLARADA");
			}
			tarifaSocialDadoEconomia.setRendaTipo(rendaTipo);
		}
		
		// Motivo de Revisão
		if (motivoRevisao != null && !motivoRevisao.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			TarifaSocialRevisaoMotivo tarifaSocialRevisaoMotivo = new TarifaSocialRevisaoMotivo();
			tarifaSocialRevisaoMotivo.setId(new Integer(motivoRevisao));
			tarifaSocialDadoEconomia.setTarifaSocialRevisaoMotivo(tarifaSocialRevisaoMotivo);
			tarifaSocialDadoEconomia.setDataRevisao(new Date());
		}
		
		Integer idMunicipio = null;
		
		Integer idImovel = null;
		
		// Caso seja de uma economia
		if (tarifaSocialHelper.getClienteImovel() != null) {
			
			ClienteImovel clienteImovel = tarifaSocialHelper.getClienteImovel();
			
			idImovelTarifaSocial = clienteImovel.getImovel().getId();
			
			SetorComercial setorComercial = clienteImovel.getImovel().getSetorComercial();
			
			idMunicipio = setorComercial.getMunicipio().getId();
			
			Imovel imovel = fachada.pesquisarImovelComSituacaoAguaEsgoto(idImovelTarifaSocial);
			
			imovel.setSetorComercial(setorComercial);
			
			// Número do IPTU
			if (numeroIptu != null && !numeroIptu.trim().equals("")) {
				numeroIptuFormatado = Util.formatarIPTU(numeroIptu);
				
				idImovel = fachada.verificarNumeroIptu(numeroIptuFormatado, idImovelTarifaSocial, null, idMunicipio);
				
				if (idImovel != null) {
					throw new ActionServletException("atencao.imovel.iptu_jacadastrado", null, idImovel.toString());
				}
				
				imovel.setNumeroIptu(numeroIptuFormatado);
			} else {
				imovel.setNumeroIptu(null);
			}
			
			// Número do Contrato da Companhia Elétrica
			if (numeroContratoCompanhiaEletrica != null && !numeroContratoCompanhiaEletrica.trim().equals("")) {
				
				numeroContratoCompanhiaEletricaFormatado = new Long(numeroContratoCompanhiaEletrica);
				
				idImovel = fachada.verificarNumeroCompanhiaEletrica(numeroContratoCompanhiaEletricaFormatado, idImovelTarifaSocial, null);
				
				if (idImovel != null) {
					throw new ActionServletException("atencao.imovel.numero_celpe_jacadastrado", null, idImovel.toString());
				}
				
				imovel.setNumeroCelpe(numeroContratoCompanhiaEletricaFormatado);
			} else {
				imovel.setNumeroCelpe(null);
			}
			
			// Área Construída
			if (areaConstruida != null && !areaConstruida.trim().equals("")) {
				areaConstruidaFormatada = Util.formatarMoedaRealparaBigDecimal(areaConstruida);
				imovel.setAreaConstruida(areaConstruidaFormatada);
			} else {
				imovel.setAreaConstruida(null);
			}
			
			// Área Construída Faixa
			if (idAreaConstruidaFaixa != null && !idAreaConstruidaFaixa.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				AreaConstruidaFaixa areaConstruidaFaixa = new AreaConstruidaFaixa();
				areaConstruidaFaixa.setId(new Integer(idAreaConstruidaFaixa));
				imovel.setAreaConstruidaFaixa(areaConstruidaFaixa);
			} else {
				imovel.setAreaConstruidaFaixa(null);
			}
			
			// Número de Moradores
			if (numeroMoradores != null && !numeroMoradores.trim().equals("")) {
				numeroMoradoresFormatado = new Short(numeroMoradores);
				imovel.setNumeroMorador(numeroMoradoresFormatado);
			} else {
				imovel.setNumeroMorador(null);
			}
			
			tarifaSocialDadoEconomia.setImovel(imovel);
			clienteImovel.setImovel(imovel);
			tarifaSocialHelperAtualizar.setClienteImovel(clienteImovel);
			
			// [FS0008] - Verificar Preenchimento dos Campos
			fachada.verificarPreenchimentoManterDadosTarifaSocial(
				numeroContratoCompanhiaEletricaFormatado,
				areaConstruidaFormatada, numeroIptuFormatado,
				idImovelTarifaSocial, numeroCartaoProgramaSocial,
				dataValidadeCartao, numeroParcelas, consumoMedioFormatado,
				valorRendaFormatada, tipoCartaoProgramaSocial, tipoRenda);
		
		} 
		// Caso seja de múltiplas economias
		else {
			ClienteImovelEconomia clienteImovelEconomia = new ClienteImovelEconomia();
			
			idImovelTarifaSocial = tarifaSocialHelper.getClienteImovelEconomia().getImovelEconomia().getId();
			idMunicipio = tarifaSocialHelper.getClienteImovelEconomia()
					.getImovelEconomia().getImovelSubcategoria().getComp_id()
					.getImovel().getSetorComercial().getMunicipio().getId();
			
			ImovelEconomia imovelEconomia = fachada.pesquisarImovelEconomiaPeloId(idImovelTarifaSocial);
			imovelEconomia.setImovelSubcategoria(tarifaSocialHelper.getClienteImovelEconomia()
					.getImovelEconomia().getImovelSubcategoria());
		
			// Número do IPTU
			if (numeroIptu != null && !numeroIptu.trim().equals("")) {
				numeroIptuFormatado = Util.formatarIPTU(numeroIptu);
				
				if (colecaoTarifaSocialHelper != null && !colecaoTarifaSocialHelper.isEmpty()) {
					Iterator colecaoTarifaSocialHelperIterator = colecaoTarifaSocialHelper.iterator();
					
					while (colecaoTarifaSocialHelperIterator.hasNext()) {
						
						TarifaSocialHelper tarifaSocialHelperVerificacao = (TarifaSocialHelper) colecaoTarifaSocialHelperIterator
								.next();

						// Verifica se o número de contrato da companhia já foi usado em outra economia do imóvel
						if (tarifaSocialHelperVerificacao
								.getClienteImovelEconomia().getImovelEconomia()
								.getNumeroIptu() != null
								&& tarifaSocialHelperVerificacao
										.getClienteImovelEconomia()
										.getImovelEconomia()
										.getNumeroIptu()
										.equals(
												numeroIptuFormatado)
								&& !tarifaSocialHelperVerificacao
										.getTarifaSocialDadoEconomia()
										.getId()
										.equals(
												tarifaSocialDadoEconomia
														.getId())) {
							throw new ActionServletException("atencao.imovel.iptu_jacadastrado", null, imovelSessao.getId().toString());
						}
					}
				}
				
				idImovel = fachada.verificarNumeroIptu(numeroIptuFormatado, null, idImovelTarifaSocial, idMunicipio);
				
				if (idImovel != null) {
					throw new ActionServletException("atencao.imovel.iptu_jacadastrado", null, idImovel.toString());
				}
				
				imovelEconomia.setNumeroIptu(numeroIptuFormatado);
				
			} else {
				imovelEconomia.setNumeroIptu(null);
			}
			
			// Número do Contrato da Companhia Elétrica
			if (numeroContratoCompanhiaEletrica != null && !numeroContratoCompanhiaEletrica.trim().equals("")) {
				
				numeroContratoCompanhiaEletricaFormatado = new Long(numeroContratoCompanhiaEletrica);
				
				if (colecaoTarifaSocialHelper != null && !colecaoTarifaSocialHelper.isEmpty()) {
					Iterator colecaoTarifaSocialHelperIterator = colecaoTarifaSocialHelper.iterator();
					
					while (colecaoTarifaSocialHelperIterator.hasNext()) {
						
						TarifaSocialHelper tarifaSocialHelperVerificacao = (TarifaSocialHelper) colecaoTarifaSocialHelperIterator
								.next();

						// Verifica se o número de contrato da companhia já foi usado em outra economia do imóvel
						if (tarifaSocialHelperVerificacao
								.getClienteImovelEconomia().getImovelEconomia()
								.getNumeroCelpe() != null
								&& tarifaSocialHelperVerificacao
										.getClienteImovelEconomia()
										.getImovelEconomia()
										.getNumeroCelpe()
										.equals(
												numeroContratoCompanhiaEletricaFormatado)
								&& !tarifaSocialHelperVerificacao
										.getTarifaSocialDadoEconomia()
										.getId()
										.equals(tarifaSocialDadoEconomia
														.getId())) {
							throw new ActionServletException("atencao.imovel.numero_celpe_jacadastrado", null, imovelSessao.getId().toString());
						}
					}
				}
				
				idImovel = fachada.verificarNumeroCompanhiaEletrica(numeroContratoCompanhiaEletricaFormatado, idImovelTarifaSocial, null);
				
				imovelEconomia.setNumeroCelpe(numeroContratoCompanhiaEletricaFormatado);
				
			} else {
				imovelEconomia.setNumeroCelpe(null);
			}
			
			// Área Construída
			if (areaConstruida != null && !areaConstruida.trim().equals("")) {
				areaConstruidaFormatada = Util.formatarMoedaRealparaBigDecimal(areaConstruida);
				imovelEconomia.setAreaConstruida(areaConstruidaFormatada);
			} else {
				imovelEconomia.setAreaConstruida(null);
			}
			
			// Área Construída Faixa
			if (idAreaConstruidaFaixa != null && !idAreaConstruidaFaixa.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				AreaConstruidaFaixa areaConstruidaFaixa = new AreaConstruidaFaixa();
				areaConstruidaFaixa.setId(new Integer(idAreaConstruidaFaixa));
				imovelEconomia.setAreaConstruidaFaixa(areaConstruidaFaixa);
			} else {
				imovelEconomia.setAreaConstruidaFaixa(null);
			}
			
			// Número de Moradores
			if (numeroMoradores != null && !numeroMoradores.trim().equals("")) {
				numeroMoradoresFormatado = new Short(numeroMoradores);
				imovelEconomia.setNumeroMorador(numeroMoradoresFormatado);
			} else {
				imovelEconomia.setNumeroMorador(null);
			}
			
			Imovel imovel = (Imovel) sessao.getAttribute("imovelTarifa");
			
			clienteImovelEconomia.setImovelEconomia(imovelEconomia);
			tarifaSocialDadoEconomia.setImovelEconomia(imovelEconomia);
			tarifaSocialDadoEconomia.setImovel(imovel);
			tarifaSocialHelperAtualizar.setClienteImovelEconomia(clienteImovelEconomia);
			
			// [FS0008] - Verificar Preenchimento dos Campos
			fachada.verificarPreenchimentoManterDadosTarifaSocialMultiplasEconomias(
				numeroContratoCompanhiaEletricaFormatado,
				areaConstruidaFormatada, numeroIptuFormatado,
				idImovelTarifaSocial, numeroCartaoProgramaSocial,
				dataValidadeCartao, numeroParcelas, consumoMedioFormatado,
				valorRendaFormatada, tipoCartaoProgramaSocial, tipoRenda);
			
		}
		
		httpServletRequest.setAttribute("telaLimpa", true);
		
		tarifaSocialHelperAtualizar.setTarifaSocialDadoEconomia(tarifaSocialDadoEconomia);
		
		sessao.setAttribute("tarifaSocialHelperAtualizar", tarifaSocialHelperAtualizar);

		return retorno;

	}
	
}	
