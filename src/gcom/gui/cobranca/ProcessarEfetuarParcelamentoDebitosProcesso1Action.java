package gcom.gui.cobranca;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cadastro.imovel.ImovelSituacao;
import gcom.cadastro.imovel.ImovelSituacaoTipo;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
import org.apache.struts.action.DynaActionForm;

/**
 * Permite efetuar o parcelamento dos débitos de um imóvel
 * 
 * [UC0214] Efetuar Parcelamento de Débitos
 *
 * @author Roberta Costa
 * @date 24/01/2006
 */
public class ProcessarEfetuarParcelamentoDebitosProcesso1Action extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("processo1");

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		DynaActionForm efetuarParcelamentoDebitosActionForm = (DynaActionForm) actionForm;
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		// Pega os codigos que o usuario digitou para a pesquisa direta de imovel
		String codigoImovel = httpServletRequest.getParameter("matriculaImovel");
		//String codigoImovelAntes = (String) efetuarParcelamentoDebitosActionForm.get("codigoImovelAntes");
		String inscricaoImovel = (String) efetuarParcelamentoDebitosActionForm.get("inscricaoImovel");
//		Boolean indicadorContas = true;
		String dataParcelamento = (String)(efetuarParcelamentoDebitosActionForm.get("dataParcelamento"));
		String resolucaoDiretoria = (String)(efetuarParcelamentoDebitosActionForm.get("resolucaoDiretoria"));
		String inicioIntervaloParcelamento = (String)efetuarParcelamentoDebitosActionForm.get("inicioIntervaloParcelamento");
		String fimIntervaloParcelamento = (String)efetuarParcelamentoDebitosActionForm.get("fimIntervaloParcelamento");
		String indicadorContasRevisao = (String) efetuarParcelamentoDebitosActionForm.get("indicadorContasRevisao");
		String indicadorGuiasPagamento = (String) efetuarParcelamentoDebitosActionForm.get("indicadorGuiasPagamento");
		String indicadorAcrescimosImpotualidade = (String) efetuarParcelamentoDebitosActionForm.get("indicadorAcrescimosImpotualidade");
		String indicadorDebitosACobrar = (String) efetuarParcelamentoDebitosActionForm.get("indicadorDebitosACobrar");
		String indicadorCreditoARealizar = (String) efetuarParcelamentoDebitosActionForm.get("indicadorCreditoARealizar");
		String indicadorDividaAtiva = (String) efetuarParcelamentoDebitosActionForm.get("indicadorDividaAtiva");
		String indicadorRestabelecimento = (String) efetuarParcelamentoDebitosActionForm.get("indicadorRestabelecimento");
		String numeroReparcelamento = (String)efetuarParcelamentoDebitosActionForm.get("numeroReparcelamento");
		String idClienteParcelamento = (String) efetuarParcelamentoDebitosActionForm.get("idClienteParcelamento");
		String cpfClienteParcelamentoDigitado = (String) efetuarParcelamentoDebitosActionForm.get("cpfClienteParcelamentoDigitado");
		String cpfClienteParcelamento = (String) efetuarParcelamentoDebitosActionForm.get("cpfClienteParcelamento");
		
		BigDecimal valorDebitoTotalAtualizadoImovel = new BigDecimal("0.00");
		if( efetuarParcelamentoDebitosActionForm.get("valorDebitoTotalAtualizadoImovel") != null 
				 && !efetuarParcelamentoDebitosActionForm.get("valorDebitoTotalAtualizadoImovel").equals("")){
			valorDebitoTotalAtualizadoImovel = Util.formatarMoedaRealparaBigDecimal(
					(String) efetuarParcelamentoDebitosActionForm.get("valorDebitoTotalAtualizadoImovel"));
		}
		
		// 1. Obtem a situação do imovel
		Integer situacaoAguaId = new Integer ((String)(efetuarParcelamentoDebitosActionForm.get("situacaoAguaId")));
		Integer situacaoEsgotoId = new Integer ((String)(efetuarParcelamentoDebitosActionForm.get("situacaoEsgotoId")));
		
		if( inscricaoImovel.equals("")){
			throw new ActionServletException("atencao.imovel.parcelamento.pesquisar");
		}
		
		if((cpfClienteParcelamentoDigitado == null || cpfClienteParcelamentoDigitado.equals(""))&&
				(cpfClienteParcelamento == null || cpfClienteParcelamento.equals(""))){
			
		}else if(cpfClienteParcelamentoDigitado != null && !cpfClienteParcelamentoDigitado.equals("")){
			validarCpf(cpfClienteParcelamentoDigitado,idClienteParcelamento,fachada);
		}
		
		// Data Parcelamento obrigatório	
		if( dataParcelamento == null || dataParcelamento.equals("") ){
			throw new ActionServletException("atencao.data.parcelamento.obrigatorio");
		}

		// Resolução de diretoria obrigatório	
		if( resolucaoDiretoria == null || resolucaoDiretoria.equals("") ){
			throw new ActionServletException("atencao.resolucao.diretoria.obrigatorio");
		}
		
		//adicionado por Vivianne Sousa - 19/11/2009 - analista:Adriano Britto
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		boolean existeCobrancaDocumentoDoImovel = fachada.existeCobrancaDocumentoDoImovel(new Integer(codigoImovel),DocumentoTipo.CARTA_DE_FINAL_DE_ANO_2009);
		if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COMPESA) 
			&& resolucaoDiretoria.equalsIgnoreCase("13") && !existeCobrancaDocumentoDoImovel){
			throw new ActionServletException("atencao.imovel.nao.recebeu.carta");
		}
		
		
		if (situacaoAguaId.equals(LigacaoAguaSituacao.SUPRIMIDO)
				|| situacaoAguaId.equals(LigacaoAguaSituacao.SUPR_PARC)
				|| situacaoAguaId.equals(LigacaoAguaSituacao.SUPR_PARC_PEDIDO)) {
	        if( indicadorRestabelecimento == null || indicadorRestabelecimento.trim().equals("") ){
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Com Restabelecimento?");
			}
        }
		
		
		// Verifica se as perguntas sobre o parcelamento foram respondidas
		if( indicadorContasRevisao == null || indicadorContasRevisao.equals("") ){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "'Considerar Contas em Revisão?'");
		}
		if( indicadorGuiasPagamento == null || indicadorGuiasPagamento.equals("") ){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "'Considerar Guias de Pagamento?'");
		}
		if( indicadorAcrescimosImpotualidade == null || indicadorAcrescimosImpotualidade.equals("") ){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "'Considerar Acréscimos por Impontualidade?'");
		}
		if( indicadorDebitosACobrar == null || indicadorDebitosACobrar.equals("") ){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "'Considerar Débitos a Cobrar?'");
		}
		if( indicadorCreditoARealizar == null || indicadorCreditoARealizar.equals("") ){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "'Considerar Créditos a Realizar?'");
		}
		
		// Caso o valor Total do Débito seja negativo não há débitos para parcelar
		if( valorDebitoTotalAtualizadoImovel.compareTo(new BigDecimal("0.00")) == -1 ){
			throw new ActionServletException("atencao.imovel.sem.debitos", null, codigoImovel);
		}
		
		// Caso a data do parcelamento seja maior que a data atual
		Date dataParcelamentoFormatado = Util.converteStringParaDate((String)efetuarParcelamentoDebitosActionForm.get("dataParcelamento"));
		if (dataParcelamentoFormatado.after(new Date())) {
			throw new ActionServletException("atencao.data.parcelamento.menor.igual.data.corrente");
		}
		
		if(fachada.verificarRDUtilizadaPeloImovel(new Integer(resolucaoDiretoria),new Integer(codigoImovel)) != null){
			throw new ActionServletException("atencao.rd.ja.utilizada.parcelamento");
		}
		
		ImovelSituacao imovelSituacao = null;

		// Condição 1
		if (situacaoAguaId != null && situacaoEsgotoId != null) {
			imovelSituacao = fachada.obterSituacaoImovel(situacaoAguaId, situacaoEsgotoId);
			// Condição 2
			if (imovelSituacao == null) {
				imovelSituacao = fachada.obterSituacaoImovel(situacaoAguaId, null);
			}
		}

		// [FS004] Verificar existência da situação do imóvel
		if (imovelSituacao == null) {
			throw new ActionServletException("atencao.nao.existe.situacao.imovel.correspondente.situacao.agua.esgoto");
		}
		
		// 2. Obtem o perfil do parcelamento para o imóvel
		Integer perfilImovelId = new Integer ((String)(efetuarParcelamentoDebitosActionForm.get("perfilImovel")));
		ParcelamentoPerfil parcelamentoPerfil = new ParcelamentoPerfil();

		if (imovelSituacao != null) {
			// Pega a subcategoria do imóvel
			Imovel imovel = new Imovel();
			imovel.setId(new Integer(codigoImovel));

			Collection colecaoImovelSubCategoria = fachada.obterColecaoImovelSubcategorias(imovel, 1);

			Subcategoria subcategoria = null;
			Categoria categoria = null;

			if (colecaoImovelSubCategoria != null && !colecaoImovelSubCategoria.isEmpty()) {
				Iterator iteretorImovelSubCategoria = colecaoImovelSubCategoria.iterator();
				int quantidadeEconomisas = 0;
				int maiorQuantidadeEconomisas = 0;

				while (iteretorImovelSubCategoria.hasNext()) {
					ImovelSubcategoria imovelSubCategoria = (ImovelSubcategoria) iteretorImovelSubCategoria	.next();
					quantidadeEconomisas = imovelSubCategoria.getQuantidadeEconomias();
					if (quantidadeEconomisas > maiorQuantidadeEconomisas) {
						maiorQuantidadeEconomisas = quantidadeEconomisas;
						subcategoria = imovelSubCategoria.getComp_id().getSubcategoria();
						categoria = subcategoria.getCategoria();
						
					}
				}
			}

			
			parcelamentoPerfil = fachada.obterPerfilParcelamento(new Integer(codigoImovel),imovelSituacao.getImovelSituacaoTipo().getId(),
					perfilImovelId, subcategoria.getId(), new Integer(resolucaoDiretoria), categoria.getId());
			
			if(parcelamentoPerfil == null){
				//Condição 1 - iper_id = iper_id do imovel e scat_id = scat_id do imovel
				parcelamentoPerfil = fachada.obterPerfilParcelamento(new Integer(codigoImovel),imovelSituacao.getImovelSituacaoTipo().getId(),
						perfilImovelId, subcategoria.getId(), new Integer(resolucaoDiretoria), null);

				if (parcelamentoPerfil == null) {
					parcelamentoPerfil = fachada.obterPerfilParcelamento(new Integer(codigoImovel),imovelSituacao.getImovelSituacaoTipo().getId(),
							perfilImovelId, null, new Integer(resolucaoDiretoria), categoria.getId());
					
					//Condição 2 - iper_id = iper_id do imovel e scat_id = null do imovel
					if (parcelamentoPerfil == null) {
						parcelamentoPerfil = fachada.obterPerfilParcelamento(new Integer(codigoImovel),imovelSituacao.getImovelSituacaoTipo().getId(),
								perfilImovelId, null, new Integer(resolucaoDiretoria),null);
						
						if (parcelamentoPerfil == null) {
							parcelamentoPerfil = fachada.obterPerfilParcelamento(new Integer(codigoImovel),imovelSituacao.getImovelSituacaoTipo().getId(),
									null, subcategoria.getId(), new Integer(resolucaoDiretoria),categoria.getId());
						
							//Condição 3 - iper_id = null do imovel e scat_id = scat_id do imovel
							if (parcelamentoPerfil == null) {
								parcelamentoPerfil = fachada.obterPerfilParcelamento(new Integer(codigoImovel),imovelSituacao.getImovelSituacaoTipo().getId(),
										null, subcategoria.getId(), new Integer(resolucaoDiretoria),null);
								
								if(parcelamentoPerfil == null){
									parcelamentoPerfil = fachada.obterPerfilParcelamento(new Integer(codigoImovel),imovelSituacao.getImovelSituacaoTipo().getId(), 
											null, null, new Integer(resolucaoDiretoria),categoria.getId());
									
									//Condição 4 - iper_id = null do imovel e scat_id = null
									if (parcelamentoPerfil == null) {
										parcelamentoPerfil = fachada.obterPerfilParcelamento(new Integer(codigoImovel),imovelSituacao.getImovelSituacaoTipo().getId(), 
											null, null, new Integer(resolucaoDiretoria),null);
									}
								}
							}
						}
					}
				}
			}
			
			
//			// Condição 1 - iper_id = iper_id do imovel e scat_id = scat_id do imovel
//			parcelamentoPerfil = fachada.obterPerfilParcelamento(new Integer(codigoImovel),imovelSituacao.getImovelSituacaoTipo().getId(),perfilImovelId, subcategoria.getId(), new Integer(resolucaoDiretoria),null);
//
//			// Condição 2 - iper_id = iper_id do imovel e scat_id = null do
//			// imovel
//			if (parcelamentoPerfil == null) {
//				parcelamentoPerfil = fachada.obterPerfilParcelamento(new Integer(codigoImovel),imovelSituacao.getImovelSituacaoTipo().getId(),perfilImovelId, null, new Integer(resolucaoDiretoria),null);
//				// Condição 3 - iper_id = null do imovel e scat_id = scat_id do imovel
//				if (parcelamentoPerfil == null) {
//					parcelamentoPerfil = fachada.obterPerfilParcelamento(new Integer(codigoImovel),imovelSituacao.getImovelSituacaoTipo().getId(),null, subcategoria.getId(), new Integer(resolucaoDiretoria),null);
//					// Condição 4 - iper_id = null do imovel e scat_id = null
//					if (parcelamentoPerfil == null) {
//						parcelamentoPerfil = fachada.obterPerfilParcelamento(new Integer(codigoImovel), imovelSituacao.getImovelSituacaoTipo().getId(), null,	null, new Integer(resolucaoDiretoria),null);
//					}
//				}
//			}
			
			
		}

		// [FS005] Verificar existência do perfil de parcelamento
		if (parcelamentoPerfil == null) {
			throw new ActionServletException(
					"atencao.nao.existe.perfil.parcelamento.correspondente.situacao.imovel");
		}
		
		//[FS0025] Verificar quantidade máxima de reparcelamento
		if (parcelamentoPerfil.getQuantidadeMaximaReparcelamento() != null && 
				parcelamentoPerfil.getQuantidadeMaximaReparcelamento().compareTo(new Integer(numeroReparcelamento)) == -1){
			throw new ActionServletException(
				"atencao.quantidade.reparcel.nao.permite.parcel");
		}
		
		Integer idImovel = new Integer(codigoImovel);
		if(efetuarParcelamentoDebitosActionForm.get("areaConstruidaImovel") != null &&
				!efetuarParcelamentoDebitosActionForm.get("areaConstruidaImovel").equals("")){
			BigDecimal areaConstruidaImovel = new BigDecimal ((String) efetuarParcelamentoDebitosActionForm.get("areaConstruidaImovel"));
			
			if(parcelamentoPerfil.getNumeroAreaConstruida() != null && 
					areaConstruidaImovel.compareTo(parcelamentoPerfil.getNumeroAreaConstruida()) > 0 ){
				// area contruida do imovel > a area contruida do perfil do parcelamento
				throw new ActionServletException("atencao.nao.existe.perfil.parcelamento.correspondente.situacao.imovel");
			}
		}
        Imovel imovel = new Imovel();
        imovel.setId(idImovel);
		if(parcelamentoPerfil.getCategoria() != null){
            Collection colecaoCategoria = fachada.pesquisarCategoriasImovel(imovel);
            boolean existePerfilParaCategoria = false;
            Iterator iter = colecaoCategoria.iterator();
            while (iter.hasNext()) {
                Categoria categoria = (Categoria) iter.next();
                
                if(categoria.getId().equals(parcelamentoPerfil.getCategoria().getId())){ 
                	 existePerfilParaCategoria = true;
                }

                //comentado por Vivianne Sousa 22/05/2008
                // não funcionava correto para imovel misto
                
//                if(!categoria.getId().equals(parcelamentoPerfil.getCategoria().getId())){
//                    //categoria principal do imovel != categoria do perfl do parcelamento
//                    throw new ActionServletException("atencao.nao.existe.perfil.parcelamento.correspondente.situacao.imovel");  
//                }
                
            }
            if(!existePerfilParaCategoria){
            	//categoria principal do imovel != categoria do perfl do parcelamento
                throw new ActionServletException("atencao.nao.existe.perfil.parcelamento.correspondente.situacao.imovel");  
            }
		}
		
		int qtdeEconomiasImovel = fachada.obterQuantidadeEconomias(imovel);
		
		if(parcelamentoPerfil.getNumeroConsumoEconomia() != null){
			
			Integer idLigacaoTipo = LigacaoTipo.LIGACAO_AGUA;
			
			if(imovelSituacao.getImovelSituacaoTipo().getId().equals(ImovelSituacaoTipo.LIGADO_SO_ESGOTO)){
				idLigacaoTipo = LigacaoTipo.LIGACAO_ESGOTO;
			}
			
			Integer consumoMedio = fachada.obterConsumoMedioEmConsumoHistorico(idImovel,idLigacaoTipo);
			
			Integer consumoMedioPorEconomia = 0;
			if(consumoMedio != null && consumoMedio.intValue() != 0){
				consumoMedioPorEconomia =  Util.dividirArredondarResultado(consumoMedio,qtdeEconomiasImovel);
			}
			
			if(consumoMedioPorEconomia.compareTo(parcelamentoPerfil.getNumeroConsumoEconomia()) > 0){
				throw new ActionServletException("atencao.nao.existe.perfil.parcelamento.correspondente.situacao.imovel");
			}
			
//			if(fachada.verificarExistenciaHidrometroEmLigacaoAgua(idImovel)){
//				Integer consumoMedio6meses = fachada.obterValorConsumoMedio6meses(idImovel);
//				Imovel imovel = new Imovel();
//				imovel.setId(idImovel);
//				
//				int qtdeEconomiasImovel = fachada.obterQuantidadeEconomias(imovel);
//				Integer consumoMedio6mesesEconomia =  Util.dividirArredondarResultado(consumoMedio6meses,qtdeEconomiasImovel);
//				
//				if(consumoMedio6mesesEconomia.compareTo(parcelamentoPerfil.getNumeroConsumoEconomia()) > 0){
//					throw new ActionServletException("atencao.nao.existe.perfil.parcelamento.correspondente.situacao.imovel");
//				}
//			}
			
		}
		
		if(parcelamentoPerfil.getQuantidadeEconomias() != null){
			
			if(qtdeEconomiasImovel > parcelamentoPerfil.getQuantidadeEconomias().intValue()){
				throw new ActionServletException("atencao.nao.existe.perfil.parcelamento.correspondente.situacao.imovel");
			}
		}
			
		if(parcelamentoPerfil.getCapacidadeHidrometro() != null &&
				parcelamentoPerfil.getCapacidadeHidrometro().equals(ConstantesSistema.SIM)){
			
			HidrometroCapacidade hidrometroCapacidade = null;
			hidrometroCapacidade = fachada.obterHidrometroCapacidadeEmLigacaoAgua(idImovel);
			
			if (hidrometroCapacidade != null){
				if (!hidrometroCapacidade.getId().equals(1) &&
					!hidrometroCapacidade.getId().equals(2) &&
					!hidrometroCapacidade.getId().equals(8)){
					throw new ActionServletException("atencao.nao.existe.perfil.parcelamento.correspondente.situacao.imovel");
				}
			}
		}
		
		sessao.setAttribute("parcelamentoPerfil",parcelamentoPerfil);
		
		//[FS0021] - Verificar situação de cobrança
		if (parcelamentoPerfil.getIndicadorChequeDevolvido().equals(ConstantesSistema.SIM)){
			
			//CRC3323 - adicionado por Vivianne Sousa - analista:Fatima Sampaio - 05/05/2010 
			 Collection colecaoImovelCobrancaSituacao = fachada.pesquisarImovelCobrancaSituacaoPorImovel(new Integer(codigoImovel));
             if(colecaoImovelCobrancaSituacao != null && !colecaoImovelCobrancaSituacao.isEmpty()){
            	 
            	 Iterator iterImovelCobrancaSituacao  = colecaoImovelCobrancaSituacao.iterator();
            	 String descricao = "";
            	 while (iterImovelCobrancaSituacao.hasNext()) {
					ImovelCobrancaSituacao imovelCobrancaSituacao = 
						(ImovelCobrancaSituacao) iterImovelCobrancaSituacao.next();
					
					if(imovelCobrancaSituacao.getCobrancaSituacao().getIndicadorBloqueioParcelamento().equals(ConstantesSistema.SIM)){
						
						descricao = descricao + imovelCobrancaSituacao.getCobrancaSituacao().getDescricao() + ", ";
					}
				}
            	 
            	if(!descricao.equalsIgnoreCase("")){
            		descricao = Util.removerUltimosCaracteres(descricao, 2);
            		
            		if(!fachada.verificarPermissaoEspecial(PermissaoEspecial.IMOVEL_EM_SITUACAO_COBRANCA,usuario)){
            			throw new ActionServletException("atencao.imovel.situacao.cobranca", null, descricao);
            		}
            		
            	}

             }
			
//			FiltroImovel filtroImovel = new FiltroImovel();
//			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacao");
//			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, codigoImovel));
//
//			Collection colecaoImovelCobrancaSituacaoEncontrada = fachada.pesquisar(filtroImovel,Imovel.class.getName());
//
//			// Verifica se o Imóvel tem situação de cobrança
//			if (colecaoImovelCobrancaSituacaoEncontrada != null && !colecaoImovelCobrancaSituacaoEncontrada.isEmpty()) {
//
////				CobrancaSituacao cobrancaSituacao = ((Imovel) ((List) imovelCobrancaSituacaoEncontrada).get(0)).getCobrancaSituacao();
////				if (cobrancaSituacao != null) {
////					throw new ActionServletException("atencao.imovel.situacao.cobranca" , null , cobrancaSituacao.getDescricao());
////				}
//                
//                //Alterado por Vivianne Sousa 18/03/2008
//                //analista responsavel:Fatima
//                Imovel imovelCobrancaSituacaoEncontrada = (Imovel)Util.retonarObjetoDeColecao(colecaoImovelCobrancaSituacaoEncontrada);
//                
//                //[FS0021]-Verificar situação de cobrança
//                //efetuar o parcelamento de um imovel com cobrancaSituacao 
//                fachada.verificarPermissaoEspecial(PermissaoEspecial.IMOVEL_EM_SITUACAO_COBRANCA,
//                        usuario,imovelCobrancaSituacaoEncontrada) ;
//			}
			
		}
		
		
		String bloqueiaIntervaloParcelamento = (String) sessao.getAttribute("bloqueiaIntervaloParcelamento");
		
		if (bloqueiaIntervaloParcelamento != null 
				&& bloqueiaIntervaloParcelamento.equalsIgnoreCase("nao")){	
			
			if(inicioIntervaloParcelamento == null || inicioIntervaloParcelamento.equals("")  ) {
				throw new ActionServletException(
	                    "atencao.campo_texto.obrigatorio", null, "Intervalo do Parcelamento Inicio");
			}else if( fimIntervaloParcelamento == null || fimIntervaloParcelamento.equals("") ){
				throw new ActionServletException(
	                    "atencao.campo_texto.obrigatorio", null, "Intervalo do Parcelamento Fim");
			}
		
			// [FS0002] Validar mês e ano de referência
           // Caso o mês/ano esteja inválido
			if( ! Util.validarMesAno(fimIntervaloParcelamento) ){
				throw new ActionServletException("atencao.mes.ano.referencia.final.invalido");
			}
			
			// Caso mês/ano referência final seja anterior ao de referência incial
			Integer fimIntervaloParcelamentoFormatado = Util.formatarMesAnoComBarraParaAnoMes(fimIntervaloParcelamento);
			Integer inicioIntervaloParcelamentoFormatado = Util.formatarMesAnoComBarraParaAnoMes(inicioIntervaloParcelamento);
			
			if( fimIntervaloParcelamentoFormatado < inicioIntervaloParcelamentoFormatado ){
				throw new ActionServletException("atencao.mes.ano.referencia.final.anterior.referencia.inicial");
			}
				
		
		
		//adicionado por Vivianne Sousa 10/02/2009 - analista:Adriano
		//[FS0028] Verifica se existe Parcelas em atraso
		fachada.verificaSeExisteParcelasEmAtraso(idImovel, new Integer(resolucaoDiretoria), 
				inicioIntervaloParcelamentoFormatado, fimIntervaloParcelamentoFormatado);
		
		}
		
		
		// Verifica se os campos obrigatórios continuam iguais quando houve navegação nas abas
		if( sessao.getAttribute("codigoImovelEscolhida") == null && 
			sessao.getAttribute("dataParcelamentoEscolhida") == null && 
			sessao.getAttribute("resolucaoDiretoriaEscolhida") == null &&
			sessao.getAttribute("fimIntervaloParcelamentoEscolhida") == null && 
			sessao.getAttribute("inicioIntervaloParcelamentoEscolhida") == null &&
			sessao.getAttribute("indicadorContasRevisaoEscolhida") == null && 
			sessao.getAttribute("indicadorGuiasPagamentoEscolhida") == null &&
			sessao.getAttribute("indicadorAcrescimosImpotualidadeEscolhida") == null && 
			sessao.getAttribute("indicadorDebitosACobrarEscolhida") == null &&
			sessao.getAttribute("indicadorCreditoARealizarEscolhida") == null  &&
			sessao.getAttribute("indicadorDividaAtivaEscolhida") == null ){

			sessao.setAttribute("codigoImovelEscolhida", codigoImovel);
			sessao.setAttribute("dataParcelamentoEscolhida", dataParcelamento);
			sessao.setAttribute("resolucaoDiretoriaEscolhida", resolucaoDiretoria);
			sessao.setAttribute("fimIntervaloParcelamentoEscolhida", fimIntervaloParcelamento);
			sessao.setAttribute("inicioIntervaloParcelamentoEscolhida", inicioIntervaloParcelamento); 
			sessao.setAttribute("indicadorContasRevisaoEscolhida", indicadorContasRevisao);
			sessao.setAttribute("indicadorGuiasPagamentoEscolhida", indicadorGuiasPagamento);
			sessao.setAttribute("indicadorAcrescimosImpotualidadeEscolhida", indicadorAcrescimosImpotualidade);
			sessao.setAttribute("indicadorDebitosACobrarEscolhida", indicadorDebitosACobrar); 
			sessao.setAttribute("indicadorCreditoARealizarEscolhida", indicadorCreditoARealizar);
			sessao.setAttribute("indicadorDividaAtivaEscolhida", indicadorDividaAtiva);

		}else{
			// Caso algum campo tenha mudado limpar a sessão
			if( ! codigoImovel.equals(""+sessao.getAttribute("codigoImovelEscolhida")) || 
				! dataParcelamento.equals(""+sessao.getAttribute("dataParcelamentoEscolhida")) || 
				! resolucaoDiretoria.equals(""+sessao.getAttribute("resolucaoDiretoriaEscolhida")) || 
				! fimIntervaloParcelamento.equals(""+sessao.getAttribute("fimIntervaloParcelamentoEscolhida")) || 
				! inicioIntervaloParcelamento.equals(""+sessao.getAttribute("inicioIntervaloParcelamentoEscolhida")) || 
				! indicadorContasRevisao.equals(""+sessao.getAttribute("indicadorContasRevisaoEscolhida")) ||
				! indicadorGuiasPagamento.equals(""+sessao.getAttribute("indicadorGuiasPagamentoEscolhida")) || 
				! indicadorAcrescimosImpotualidade.equals(""+sessao.getAttribute("indicadorAcrescimosImpotualidadeEscolhida")) || 
				! indicadorDebitosACobrar.equals(""+sessao.getAttribute("indicadorDebitosACobrarEscolhida")) || 
				! indicadorCreditoARealizar.equals(""+sessao.getAttribute("indicadorCreditoARealizarEscolhida")) ||
				! indicadorDividaAtiva.equals(""+sessao.getAttribute("indicadorDividaAtivaEscolhida")) ){
				
				sessao.removeAttribute("colecaoContaValores");
				
				//Monta a página de confirmação do wizard para perguntar se o usuário quer 
  		        retorno = montarPaginaConfirmacaoWizard(
  		               "atencao.parametros.modificados.parcelamento",
  		               httpServletRequest, actionMapping, codigoImovel, dataParcelamento);
			}
		}
		return retorno;
	}
	
	public void validarCpf(String cpf, String idClienteParcelamento,Fachada fachada){
		//Validar CPF de cliente
		if (cpf != null && !cpf.equals("")) {
			boolean igual = true;
			Integer i = 0;

	        Integer tam = cpf.length() - 1;
	          
	        while ( i < tam ) {
	        	if ( (cpf.charAt(i)) != (cpf.charAt(i + 1)) ){
	        		igual = false;
	                break;
	            } else {
	            	i++;
	            }
	        }

			if (igual) {
				throw new ActionServletException("atencao.cpf_invalido");
			}
			
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.CPF, cpf));

			Collection clienteComCpfExistente = fachada.pesquisar(
					filtroCliente, Cliente.class.getName());

			if (clienteComCpfExistente != null && !clienteComCpfExistente.isEmpty()) {
				Cliente clienteComCpf = (Cliente) clienteComCpfExistente.iterator().next();
				
				if(!clienteComCpf.getId().equals(new Integer(idClienteParcelamento))){
					throw new ActionServletException("atencao.cpf.cliente.ja_cadastrado", null, ""
									+ clienteComCpf.getId());
				}
			}
		}
	}
	
}
