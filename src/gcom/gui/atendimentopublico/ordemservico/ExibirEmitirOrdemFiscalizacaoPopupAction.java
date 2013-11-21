package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.FiltroClienteFone;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.GcomAction;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirEmitirOrdemFiscalizacaoPopupAction extends GcomAction {
	/**
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirEmitirOrdemFiscalizacaoPopupAction");

		EmitirOrdemFiscalizacaoForm form =
			(EmitirOrdemFiscalizacaoForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		form.setDataEmissao(Util.formatarData(new Date()));
		
		this.pesquisarValores(form,fachada,sessao);
		this.pesquisarEconomias(form,fachada,sessao);
		this.pesquisarDadosImovel(form,fachada,sessao,httpServletRequest);	          
			
		return retorno;
	}

	private void pesquisarDadosImovel(EmitirOrdemFiscalizacaoForm form,
			Fachada fachada, HttpSession sessao,HttpServletRequest httpServletRequest) {
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		FiltroImovel filtro = new FiltroImovel();
		
		filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, form.getMatriculaImovel()));
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.IMOVEL_PERFIL);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.FATURAMENTO_GRUPO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_HIDROMETRO_INSTALACAO_HISTORICO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.CLIENTES_IMOVEIS);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.UNIDADE_FEDERACAO);
		
		
		Collection<Imovel> imoveis = fachada.pesquisar(filtro, Imovel.class.getName());
		
		Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(imoveis);
		
		form.setIdPerfilImovel(imovel.getImovelPerfil().getId().toString());
		form.setDescricaoPerfilImovel(imovel.getImovelPerfil().getDescricao());
		
		form.setUltimaAlteracao(Util.formatarData(imovel.getUltimaAlteracao()));
		
		form.setFaturamentoGrupo(imovel.getQuadra().getRota().getFaturamentoGrupo().getId().toString());
		
		form.setSituacaoLigacaoAgua(imovel.getLigacaoAguaSituacao().getDescricao());
		
		if(imovel.getLogradouroBairro()!=null &&
				imovel.getLogradouroBairro().getBairro()!=null &&
					imovel.getLogradouroBairro().getBairro().getMunicipio()!=null && 
						imovel.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao()!=null){
			form.setUf(imovel.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao().getSigla());
		}
		
		if(imovel.getLigacaoAgua()!=null 
				&& imovel.getLigacaoAguaSituacao().getId().compareTo(LigacaoAguaSituacao.CORTADO)==0){
			form.setDataCorte(Util.formatarData(imovel.getLigacaoAgua().getDataCorte()));
		}
		
		if(imovel.getLigacaoAgua()!=null 
				&& (imovel.getLigacaoAguaSituacao().getId().compareTo(LigacaoAguaSituacao.SUPR_PARC)==0
						|| imovel.getLigacaoAguaSituacao().getId().compareTo(LigacaoAguaSituacao.SUPR_PARC_PEDIDO)==0)){
			form.setDataSupressaoParcial(Util.formatarData(imovel.getLigacaoAgua().getDataSupressao()));
		}
		if(imovel.getLigacaoAgua()!=null 
				&& imovel.getLigacaoAguaSituacao().getId().compareTo(LigacaoAguaSituacao.SUPRIMIDO)==0){
			form.setDataSupressaoTotal(Util.formatarData(imovel.getLigacaoAgua().getDataSupressao()));
		}
		
		form.setSituacaoLigacaoEsgoto(imovel.getLigacaoEsgotoSituacao().getDescricao());
		
		if(imovel.getLigacaoEsgoto()!=null
			&& imovel.getLigacaoEsgoto().getConsumoMinimo()!=null
		    	&& imovel.getLigacaoEsgotoSituacao()!=null 
			    	&& (imovel.getLigacaoEsgotoSituacao().getId().compareTo(LigacaoEsgotoSituacao.POTENCIAL)!=0
						&& imovel.getLigacaoEsgotoSituacao().getId().compareTo(LigacaoEsgotoSituacao.FACTIVEL)!=0)){
			form.setVolumeFixoEsgoto(imovel.getLigacaoEsgoto().getConsumoMinimo().toString());
		}
		
		/**
		 * TODO : COSANPA Alterando o cálculo da média
		 */
			MedicaoTipo medicao = new MedicaoTipo();
			medicao.setId(new Integer(MedicaoTipo.LIGACAO_AGUA));
			
			boolean houveIntslacaoHidrometro = false;
			try {
				houveIntslacaoHidrometro = fachada.verificarInstalacaoSubstituicaoHidrometro(imovel.getId(), medicao);
			} catch (ControladorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		int idLigacaoTipo = fachada.verificarTipoLigacao(imovel);
		int[] retorno = fachada.obterVolumeMedioAguaEsgoto(imovel.getId(), sistemaParametro.getAnoMesFaturamento(),
				idLigacaoTipo, houveIntslacaoHidrometro);
		
		form.setConsumoMedioAgua(retorno[0]+"");
			
		
		Cliente clienteUsuario = imovel.getClienteUsuario();
		
		if(clienteUsuario!=null){
			
			form.setNomeCliente(clienteUsuario.getNome());
			
			if(clienteUsuario.getCpf()!=null){
				form.setCpfCnpj(clienteUsuario.getCpfFormatado());
			}else if(clienteUsuario.getCnpj()!=null){
				form.setCpfCnpj(clienteUsuario.getCnpjFormatado());
			}
			if(clienteUsuario.getRg()!=null){
				form.setRg(clienteUsuario.getRg());
			}
			
			FiltroClienteFone filtroFone = new FiltroClienteFone();
			
			filtroFone.adicionarParametro(
					new ParametroSimples(FiltroClienteFone.CLIENTE_ID, clienteUsuario.getId()));
			filtroFone.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteFone.FONE_TIPO);
			
			Collection<ClienteFone> clienteFones 
				= fachada.pesquisar(filtroFone, ClienteFone.class.getName());
			
			ClienteFone clienteFone = (ClienteFone) Util.retonarObjetoDeColecao(clienteFones);
			
			if(clienteFone!=null){
				if(clienteFone.getTelefone()!=null){
					form.setNumeroTelefone(clienteFone.getTelefone());
				}
				if(clienteFone.getDdd()!=null){
					form.setDdd(clienteFone.getDdd().toString());
				}
				if(clienteFone.getRamal()!=null){
					form.setRamal(clienteFone.getRamal());
				}
				if(clienteFone.getFoneTipo()!=null){
					form.setTipoTelefone(clienteFone.getFoneTipo().getDescricao());
				}
			}
			
		}
		
		String indicadorAguaEsgoto = ConstantesSistema.CALCULAR_AGUA;
		
		
		String tipo = httpServletRequest.getParameter("tipo");
		
		if(tipo!=null && !tipo.equals("")){
			//Ligação Agua
			if(tipo.equals("1")){
				indicadorAguaEsgoto = ConstantesSistema.CALCULAR_AGUA;
			}			
			//Poço
			if(tipo.equals("2")){
				indicadorAguaEsgoto = ConstantesSistema.CALCULAR_ESGOTO;
			}
			
		}
		
		String ocorrencia = fachada.pesquisarAnormalidadesImovel(new Integer(form.getMatriculaImovel()),indicadorAguaEsgoto);
		
		if(ocorrencia!=null && !ocorrencia.equals("")){
			form.setOcorrencia(ocorrencia);
		}else{
			form.setOcorrencia(null);
		}

		
	}

	private void pesquisarEconomias(EmitirOrdemFiscalizacaoForm form,
			Fachada fachada, HttpSession sessao) {
		
		
		Collection imovelSubcategorias = 
			fachada.pesquisarCategoriasImovel(
					new Integer(form.getMatriculaImovel().trim()));
		
		sessao.setAttribute("imovelSubcategorias",imovelSubcategorias);
		

		form.setQtdeEconResidencial(new Integer(0));
		form.setQtdeEconComercial(new Integer(0));
		form.setQtdeEconIndustrial(new Integer(0));
		form.setQtdeEconPublica(new Integer(0));
		form.setQtdeEconTotal(new Integer(0));
	
		for (Iterator iterator = imovelSubcategorias.iterator(); iterator
				.hasNext();) {
			
			ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) iterator.next();
			
			if(imovelSubcategoria
					.getComp_id().getSubcategoria()
						.getCategoria().getId().compareTo(Categoria.RESIDENCIAL)==0){
				form.setQtdeEconResidencial(form.getQtdeEconResidencial()+
						imovelSubcategoria.getQuantidadeEconomias());
				form.setQtdeEconTotal(form.getQtdeEconTotal()+
						imovelSubcategoria.getQuantidadeEconomias());
			}
			if(imovelSubcategoria
					.getComp_id().getSubcategoria()
						.getCategoria().getId().compareTo(Categoria.COMERCIAL)==0){
				form.setQtdeEconComercial(form.getQtdeEconComercial()+
						imovelSubcategoria.getQuantidadeEconomias());
				form.setQtdeEconTotal(form.getQtdeEconTotal()+
						imovelSubcategoria.getQuantidadeEconomias());
			}
			if(imovelSubcategoria
					.getComp_id().getSubcategoria()
						.getCategoria().getId().compareTo(Categoria.INDUSTRIAL)==0){
				form.setQtdeEconIndustrial(form.getQtdeEconIndustrial()+
						imovelSubcategoria.getQuantidadeEconomias());
				form.setQtdeEconTotal(form.getQtdeEconTotal()+
						imovelSubcategoria.getQuantidadeEconomias());
			}
			if(imovelSubcategoria
					.getComp_id().getSubcategoria()
						.getCategoria().getId().compareTo(Categoria.PUBLICO)==0){
				form.setQtdeEconPublica(form.getQtdeEconPublica()+
						imovelSubcategoria.getQuantidadeEconomias());
				form.setQtdeEconTotal(form.getQtdeEconTotal()+
						imovelSubcategoria.getQuantidadeEconomias());
			}
		}
		
	}

	private void pesquisarValores(EmitirOrdemFiscalizacaoForm form,
			Fachada fachada, HttpSession sessao) {
		
		String referenciaInicial = "01/0001";
		String referenciaFinal = "12/9999";
		String dataVencimentoInicial = "01/01/0001";
		String dataVencimentoFinal = "31/12/9999";

		// Para auxiliar na formatação de uma data
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		String mesInicial = referenciaInicial.substring(0, 2);
		String anoInicial = referenciaInicial.substring(3, referenciaInicial.length());
		String anoMesInicial = anoInicial + mesInicial;
		String mesFinal = referenciaFinal.substring(0, 2);
		String anoFinal = referenciaFinal.substring(3, referenciaFinal.length());
		String anoMesFinal = anoFinal + mesFinal;

		Date dataVencimentoDebitoI;
		Date dataVencimentoDebitoF;

		try {
			dataVencimentoDebitoI = formatoData.parse(dataVencimentoInicial);
		} catch (ParseException ex) {
			dataVencimentoDebitoI = null;
		}
		try {
			dataVencimentoDebitoF = formatoData.parse(dataVencimentoFinal);
		} catch (ParseException ex) {
			dataVencimentoDebitoF = null;
		}

		// seta valores constantes para chamar o metodo que consulta debitos do imovel
		Integer tipoImovel = new Integer(1);
		Integer indicadorPagamento = new Integer(1);
		Integer indicadorConta = new Integer(1);
		Integer indicadorDebito = new Integer(1);
		Integer indicadorCredito = new Integer(1);
		Integer indicadorNotas = new Integer(1);
		Integer indicadorGuias = new Integer(1);
		Integer indicadorAtualizar = new Integer(1);

		// Obtendo os débitos do imovel
		ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = fachada
				.obterDebitoImovelOuCliente(tipoImovel.intValue(),
						form.getMatriculaImovel().trim(), null, null,
						anoMesInicial, anoMesFinal,
						dataVencimentoDebitoI,
						dataVencimentoDebitoF, indicadorPagamento
								.intValue(), indicadorConta
								.intValue(), indicadorDebito
								.intValue(), indicadorCredito
								.intValue(), indicadorNotas
								.intValue(), indicadorGuias
								.intValue(), indicadorAtualizar
								.intValue(), null);

		Collection<ContaValoresHelper> colecaoContaValores = colecaoDebitoImovel.getColecaoContasValores();

		ContaValoresHelper dadosConta = null;

		BigDecimal valorConta = new BigDecimal("0.00");
		BigDecimal valorAcrescimo = new BigDecimal("0.00");
		BigDecimal valorAgua = new BigDecimal("0.00");
		BigDecimal valorEsgoto = new BigDecimal("0.00");
		BigDecimal valorDebito = new BigDecimal("0.00");
		BigDecimal valorCredito = new BigDecimal("0.00");
		BigDecimal valorImposto = new BigDecimal("0.00");

		if (colecaoContaValores != null	&& !colecaoContaValores.isEmpty()) {
			java.util.Iterator<ContaValoresHelper> colecaoContaValoresIterator = colecaoContaValores.iterator();
			// percorre a colecao de conta somando o valor para obter um valor total
			while (colecaoContaValoresIterator.hasNext()) {

				dadosConta = (ContaValoresHelper) colecaoContaValoresIterator.next();
				valorConta = valorConta.add(dadosConta.getConta().getValorTotal());
				valorAcrescimo = valorAcrescimo.add(dadosConta.getValorTotalContaValores());
				valorAgua = valorAgua.add(dadosConta.getConta().getValorAgua());
				valorEsgoto = valorEsgoto.add(dadosConta.getConta().getValorEsgoto());
				valorDebito = valorDebito.add(dadosConta.getConta().getDebitos());
				valorCredito = valorCredito.add(dadosConta.getConta().getValorCreditos());
				valorImposto = valorImposto.add(dadosConta.getConta().getValorImposto());
			}
		}

		Collection<DebitoACobrar> colecaoDebitoACobrar = colecaoDebitoImovel.getColecaoDebitoACobrar();

		BigDecimal valorDebitoACobrar = new BigDecimal("0.00");
		BigDecimal valorDebitoACobrarSemJurosParcelamento = new BigDecimal("0.00");
		DebitoACobrar dadosDebito = null;

		if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
			java.util.Iterator<DebitoACobrar> colecaoDebitoACobrarIterator = colecaoDebitoACobrar.iterator();
			// percorre a colecao de debito a cobrar somando o valor para obter um valor total
			while (colecaoDebitoACobrarIterator.hasNext()) {

				dadosDebito = (DebitoACobrar) colecaoDebitoACobrarIterator.next();
				valorDebitoACobrar = valorDebitoACobrar.add(dadosDebito.getValorTotalComBonus());
				
				if (dadosDebito.getDebitoTipo() != null &&
						!dadosDebito.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)){
					valorDebitoACobrarSemJurosParcelamento = valorDebitoACobrarSemJurosParcelamento.add(dadosDebito.getValorTotalComBonus());
				}
				
			}
		}

		Collection<CreditoARealizar> colecaoCreditoARealizar = colecaoDebitoImovel.getColecaoCreditoARealizar();

		BigDecimal valorCreditoARealizar = new BigDecimal("0.00");
		BigDecimal valorCreditoARealizarSemDescontosParcelamento = new BigDecimal("0.00");
		CreditoARealizar dadosCredito = null;

		if (colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty()) {
			java.util.Iterator<CreditoARealizar> colecaoCreditoARealizarIterator = colecaoCreditoARealizar.iterator();
			// percorre a colecao de credito a realizar somando o valor para obter um valor total
			while (colecaoCreditoARealizarIterator.hasNext()) {

				dadosCredito = (CreditoARealizar) colecaoCreditoARealizarIterator.next();
				valorCreditoARealizar = valorCreditoARealizar.add(dadosCredito.getValorTotalComBonus());
				
				if (dadosCredito.getCreditoOrigem() != null && 
						!dadosCredito.getCreditoOrigem().getId().equals(CreditoOrigem.DESCONTOS_CONCEDIDOS_NO_PARCELAMENTO)){
					valorCreditoARealizarSemDescontosParcelamento = valorCreditoARealizarSemDescontosParcelamento.add(dadosCredito.getValorTotalComBonus());
				}
			}
		}

		Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = colecaoDebitoImovel.getColecaoGuiasPagamentoValores();

		BigDecimal valorGuiaPagamento = new BigDecimal("0.00");
		GuiaPagamentoValoresHelper dadosGuiaPagamentoValoresHelper = null;

		if (colecaoGuiaPagamentoValores != null	&& !colecaoGuiaPagamentoValores.isEmpty()) {
			java.util.Iterator<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValoresHelperIterator = colecaoGuiaPagamentoValores.iterator();
			// percorre a colecao de guia de pagamento somando o valor para obter um valor total
			while (colecaoGuiaPagamentoValoresHelperIterator.hasNext()) {

				dadosGuiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) colecaoGuiaPagamentoValoresHelperIterator.next();
				valorGuiaPagamento = valorGuiaPagamento.add(dadosGuiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());
			}
		}

		

			// Soma o valor total dos debitos e subtrai dos creditos
			BigDecimal valorTotalSemAcrescimo = valorConta.add(valorDebitoACobrar);
			valorTotalSemAcrescimo = valorTotalSemAcrescimo.add(valorGuiaPagamento);
			valorTotalSemAcrescimo = valorTotalSemAcrescimo.subtract(valorCreditoARealizar);
			

			form.setValorDebitosAteDataVencimento(Util
					.formatarMoedaReal(valorTotalSemAcrescimo));
			form.setValorServicos(Util
					.formatarMoedaReal(valorDebitoACobrar));
				
		
	}
}
