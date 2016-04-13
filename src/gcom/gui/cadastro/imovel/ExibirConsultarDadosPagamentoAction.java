package gcom.gui.cadastro.imovel;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.RegistroCodigo;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.cadastro.cliente.Cliente;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0549] Consultar Dados de um Pagamento
 * 
 * @author Kassia Albuquerque
 * @created 28/06/2007
 */

public class ExibirConsultarDadosPagamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirConsultarDadosPagamentoAction");

		ConsultarDadosPagamentoActionForm form = (ConsultarDadosPagamentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		String idPagamentoHistorico = null;

		String idPagamento = null;

		PagamentoHistorico pagamentoHistorico = null;

		Pagamento pagamento = null;

		if (httpServletRequest.getParameter("pagamentoHistorico") != null) {

			// ------------- PEGA DA TABELA PAGAMENTO_HISTORICO
		

			idPagamentoHistorico = (String) httpServletRequest.getParameter("idPagamento");
			
			Collection<PagamentoHistorico> colecaoPagamentoHistorico = 
				fachada.pesquisarPagamentoHistoricoPeloId(new Integer(idPagamentoHistorico));
			
			pagamentoHistorico = (PagamentoHistorico) Util.retonarObjetoDeColecao(colecaoPagamentoHistorico);

			form.setDescricaoLocalidade(pagamentoHistorico.getLocalidade().getDescricao());
			form.setTipoDocumento(pagamentoHistorico.getDocumentoTipo().getDescricaoDocumentoTipo());

			if (pagamentoHistorico.getImovel() != null
						&& !pagamentoHistorico.getImovel().equals("")) {
				
				form.setMatriculaImovel(""+ pagamentoHistorico.getImovel().getId());
				String imovelInscricao = fachada.pesquisarInscricaoImovel(pagamentoHistorico.getImovel().getId());
				form.setInscricaoImovel(imovelInscricao);
				
				Cliente cliente = fachada.obterDescricaoIdCliente( pagamentoHistorico.getImovel().getId());
				
				if (cliente != null){
					
					form.setClienteId((cliente.getId()).toString());
					form.setClienteNome(cliente.getNome());
				}
				
			}else {
				form.setMatriculaImovel("");
				form.setClienteId("");
				form.setClienteNome("");
				
			}

			if (pagamentoHistorico.getAnoMesReferenciaPagamento() != null
					&& !pagamentoHistorico.getAnoMesReferenciaPagamento().equals("")) {
				
				form.setMesAno(Util.formatarAnoMesParaMesAno(pagamentoHistorico.getAnoMesReferenciaPagamento()));
			}else{
				form.setMesAno("");
			}

			if (pagamentoHistorico.getDebitoTipo() != null
					&& !pagamentoHistorico.getDebitoTipo().equals("")) {
				
				form.setDebitoId(""+ pagamentoHistorico.getDebitoTipo().getId());
				form.setDebitoDescricao(""+ pagamentoHistorico.getDebitoTipo().getDescricao());
			}else {
				form.setDebitoId("");
				form.setDebitoDescricao("");
			}

			form.setDataPagamento(Util.formatarData(pagamentoHistorico.getDataPagamento()));
			form.setValorPagamento(Util.formatarMoedaReal(pagamentoHistorico.getValorPagamento()));
			form.setMesAnoRefPagamento(Util.formatarAnoMesParaMesAno(pagamentoHistorico.getAnoMesReferenciaArrecadacao()));
			form.setDataProcessamento(Util.formatarData(pagamentoHistorico.getUltimaAlteracao()).toString());
			form.setHoraProcessamento(Util.formatarHoraSemData(pagamentoHistorico.getUltimaAlteracao()));
			form.setDescricaoSituacaoAtual(pagamentoHistorico.getPagamentoSituacaoAtual().getDescricao());

			if (pagamentoHistorico.getPagamentoSituacaoAnterior() != null
					&& !pagamentoHistorico.getPagamentoSituacaoAnterior().equals("")) {
				
				form.setDescricaoSituacaoAnterior(pagamentoHistorico.getPagamentoSituacaoAnterior().getDescricao());
			}else{
				form.setDescricaoSituacaoAnterior("");
			}

			if (pagamentoHistorico.getValorExcedente() != null
					&& !pagamentoHistorico.getValorExcedente().equals("")) {
				
				form.setValorExcedente(Util.formatarMoedaReal(pagamentoHistorico.getValorExcedente()));
			}else{
				form.setValorExcedente("");
			}

			if (pagamentoHistorico.getAvisoBancario()!= null && !pagamentoHistorico.getAvisoBancario().equals("")){
				
				httpServletRequest.setAttribute("avisoBancarioPreenchido","avisoBancarioPreenchido");
				
				form.setCodigoArrecadador((pagamentoHistorico.getAvisoBancario().getArrecadador().getCodigoAgente()).toString());
				form.setNomeArrecadador(pagamentoHistorico.getAvisoBancario().getArrecadador().getCliente().getNome());
				form.setDataLancamento(Util.formatarData(pagamentoHistorico.getAvisoBancario().getDataLancamento()));
				
			}else{
				
				form.setCodigoAgenteArrecadador((pagamentoHistorico.getCodigoAgente().toString()));
				String nomeCliente = fachada.pesquisarNomeAgenteArrecadador(pagamentoHistorico.getId());
				
				if (nomeCliente != null){
					
					form.setNomeAgenteArrecadador(nomeCliente);
				}else{
					form.setNomeAgenteArrecadador("");
				}
			}
			
			
			if (pagamentoHistorico.getArrecadadorMovimentoItem()!= null && 
					!pagamentoHistorico.getArrecadadorMovimentoItem().equals("")){
				
				httpServletRequest.setAttribute("arrecadadorMovimentoItem","arrecadadorMovimentoItem");
				
				
				if (pagamentoHistorico.getArrecadadorMovimentoItem().getRegistroCodigo().getDescricao()!= null && 
						!pagamentoHistorico.getArrecadadorMovimentoItem().getRegistroCodigo().getDescricao().equals("")){
					
					form.setDescricaoCodigoRegistro(pagamentoHistorico.getArrecadadorMovimentoItem().getRegistroCodigo().getDescricao());
				}else {
					form.setDescricaoCodigoRegistro("");
				}
				
				form.setDataProcessamentoMovimento(Util.formatarData(pagamentoHistorico.getArrecadadorMovimentoItem().getUltimaAlteracao()));
				form.setHoraProcessamentoMovimento(Util.formatarHoraSemData(pagamentoHistorico.getArrecadadorMovimentoItem().getUltimaAlteracao()));
				
				if (pagamentoHistorico.getArrecadadorMovimentoItem().getDescricaoOcorrencia()!= null &&
						!pagamentoHistorico.getArrecadadorMovimentoItem().getDescricaoOcorrencia().equals("")){
					
					form.setDescricaoOcorrenciaMovimento(pagamentoHistorico.getArrecadadorMovimentoItem().getDescricaoOcorrencia());
				}else{
					form.setDescricaoOcorrenciaMovimento("");
				}
				
				if (pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNumeroSequencialArquivo()!= null && 
						!pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNumeroSequencialArquivo().equals("")){
					
					form.setSequencialArquivoMovimento(pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNumeroSequencialArquivo().toString());
				}else{
					form.setSequencialArquivoMovimento("");
				}

				if (pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getCodigoBanco()!= null && 
						!pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getCodigoBanco().equals("")){
					
					form.setCodigoArrecadadorMovimento(pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getCodigoBanco().toString());
				}else{
					form.setCodigoArrecadadorMovimento("");
				}
				
				if (pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNomeBanco() != null &&
						!pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNomeBanco().equals("")){
					
					form.setNomeArrecadadorMovimento(pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNomeBanco());
				}else{
					form.setNomeArrecadadorMovimento("");
				}
				
				if (pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getDescricaoIdentificacaoServico()!= null &&
						!pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getDescricaoIdentificacaoServico().equals("")){
					
					form.setServicoManutencao(pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getDescricaoIdentificacaoServico());
				}else{
					form.setServicoManutencao("");
				}				
				
				if (pagamentoHistorico.getArrecadadorMovimentoItem().getConteudoRegistro()!= null && 
						!pagamentoHistorico.getArrecadadorMovimentoItem().getConteudoRegistro().equals("")){
					
					// FAZ A PESQUISA E TRAS O CARACTER CORRESPONDENTE A POSIÇÃO 117
					String caracterRetorno = fachada.pesquisarCaracterRetorno(pagamentoHistorico.getArrecadadorMovimentoItem().getId());
					Integer codigoRegistro = pagamentoHistorico.getArrecadadorMovimentoItem().getRegistroCodigo().getId();
					
					// Codigo registro = 6
					if (codigoRegistro.intValue() == RegistroCodigo.CODIGO_SEIS.intValue()){
						
						String descricao = filtrarArrecadacaoForma(form, fachada,"Z");	
						form.setFormaArrecadacao(descricao);	
						
					}else if (caracterRetorno != null && !caracterRetorno.trim().equals("")){
						
							String descricao = filtrarArrecadacaoForma(form, fachada,caracterRetorno);	
							form.setFormaArrecadacao(descricao);
					
						}else if (codigoRegistro.intValue() == RegistroCodigo.CODIGO_SETE.intValue()){
								//	Codigo registro = 7 e posicao 117 nula
								String descricao = filtrarArrecadacaoForma(form, fachada,"1");	
								form.setFormaArrecadacao(descricao);					
							}else{
								form.setFormaArrecadacao("");
							}
				}
			}

		} else {

			// ---------------- PEGA DA TABELA PAGAMENTO ---------------------


			idPagamento = (String) httpServletRequest.getParameter("idPagamento");

		
			Collection<Pagamento> colecaoPagamento = fachada.pesquisarPagamentoPeloId(new Integer(idPagamento));

			pagamento = (Pagamento) Util.retonarObjetoDeColecao(colecaoPagamento);

			form.setDescricaoLocalidade(pagamento.getLocalidade().getDescricao());
			form.setTipoDocumento(pagamento.getDocumentoTipo().getDescricaoDocumentoTipo());

			if (pagamento.getImovel() != null && !pagamento.getImovel().equals("")) {
				
				form.setMatriculaImovel(""+ pagamento.getImovel().getId());
				String imovelInscricao = fachada.pesquisarInscricaoImovel(pagamento.getImovel().getId());
				form.setInscricaoImovel(imovelInscricao);
				
				Cliente cliente = fachada.obterDescricaoIdCliente(pagamento.getImovel().getId());
				
				if (cliente != null){
					
					form.setClienteId((cliente.getId()).toString());
					form.setClienteNome(cliente.getNome());
				}
				
			}else {
				form.setMatriculaImovel("");
				form.setClienteId("");
				form.setClienteNome("");
				
			}

			if (pagamento.getAnoMesReferenciaPagamento() != null && !pagamento.getAnoMesReferenciaPagamento().equals("")) {
				
				form.setMesAno(Util.formatarAnoMesParaMesAno(pagamento.getAnoMesReferenciaPagamento()));
			}else {
				
				if (pagamento.getGuiaPagamento() != null && pagamento.getGuiaPagamento().getGuiaPagamento().getAnoMesReferenciaContabil() != null){
					form.setMesAno(Util.formatarAnoMesParaMesAno(pagamento.getGuiaPagamento().getGuiaPagamento().getAnoMesReferenciaContabil()));
				} else {
					form.setMesAno("");
				}
			}

			if (pagamento.getDebitoTipo() != null && !pagamento.getDebitoTipo().equals("")) {
				
				form.setDebitoId(""+ pagamento.getDebitoTipo().getId());
				form.setDebitoDescricao(""+ pagamento.getDebitoTipo().getDescricao());
			}else{
				form.setDebitoId("");
				form.setDebitoDescricao("");
			}

			form.setDataPagamento(Util.formatarData(pagamento.getDataPagamento()));
			form.setValorPagamento(Util.formatarMoedaReal(pagamento.getValorPagamento()));
			form.setMesAnoRefPagamento(Util.formatarAnoMesParaMesAno(pagamento.getAnoMesReferenciaArrecadacao()));
			form.setDataProcessamento(Util.formatarData(pagamento.getUltimaAlteracao()).toString());
			form.setHoraProcessamento(Util.formatarHoraSemData(pagamento.getUltimaAlteracao()));
			
			if (pagamento.getPagamentoSituacaoAtual()!= null && !pagamento.getPagamentoSituacaoAtual().equals("")){
				
				form.setDescricaoSituacaoAtual(pagamento.getPagamentoSituacaoAtual().getDescricao());
			}else{
				form.setDescricaoSituacaoAtual("");
			}

			if (pagamento.getPagamentoSituacaoAnterior() != null && !pagamento.getPagamentoSituacaoAnterior().equals("")) {
				
				form.setDescricaoSituacaoAnterior(pagamento.getPagamentoSituacaoAnterior().getDescricao());
			}else{
				form.setDescricaoSituacaoAnterior("");
			}

			if (pagamento.getValorExcedente() != null && !pagamento.getValorExcedente().equals("")) {
				
				form.setValorExcedente(Util.formatarMoedaReal(pagamento.getValorExcedente()));
			}else{
				form.setValorExcedente("");
			}
			
			if (pagamento.getAvisoBancario()!= null && !pagamento.getAvisoBancario().equals("")){
				
				httpServletRequest.setAttribute("avisoBancarioPreenchido","avisoBancarioPreenchido");
				
				form.setCodigoArrecadador((pagamento.getAvisoBancario().getArrecadador().getCodigoAgente()).toString());
				form.setNomeArrecadador(pagamento.getAvisoBancario().getArrecadador().getCliente().getNome());
				form.setDataLancamento(Util.formatarData(pagamento.getAvisoBancario().getDataLancamento()));
			}
			
			
			if (pagamento.getArrecadadorMovimentoItem()!= null && 
						!pagamento.getArrecadadorMovimentoItem().equals("")){
				
				httpServletRequest.setAttribute("arrecadadorMovimentoItem","arrecadadorMovimentoItem");
				
				
				if (pagamento.getArrecadadorMovimentoItem().getRegistroCodigo().getDescricao()!= null && 
						!pagamento.getArrecadadorMovimentoItem().getRegistroCodigo().getDescricao().equals("")){
					
					form.setDescricaoCodigoRegistro(pagamento.getArrecadadorMovimentoItem().getRegistroCodigo().getDescricao());
				}else{
					form.setDescricaoCodigoRegistro("");
				}
				
				form.setDataProcessamentoMovimento(Util.formatarData(pagamento.getArrecadadorMovimentoItem().getUltimaAlteracao()));
				form.setHoraProcessamentoMovimento(Util.formatarHoraSemData(pagamento.getArrecadadorMovimentoItem().getUltimaAlteracao()));
				
				if (pagamento.getArrecadadorMovimentoItem().getDescricaoOcorrencia()!= null &&
						!pagamento.getArrecadadorMovimentoItem().getDescricaoOcorrencia().equals("")){
					
					form.setDescricaoOcorrenciaMovimento(pagamento.getArrecadadorMovimentoItem().getDescricaoOcorrencia());
				}else{
					form.setDescricaoOcorrenciaMovimento("");
				}
				
				if (pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNumeroSequencialArquivo()!= null && 
						!pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNumeroSequencialArquivo().equals("")){
					
					form.setSequencialArquivoMovimento(pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNumeroSequencialArquivo().toString());
				}else{
					form.setSequencialArquivoMovimento("");
				}

				if (pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getCodigoBanco()!= null && 
						!pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getCodigoBanco().equals("")){
					
					form.setCodigoArrecadadorMovimento(pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getCodigoBanco().toString());
				}else{
					form.setCodigoArrecadadorMovimento("");
				}
				
				if (pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNomeBanco() != null &&
						!pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNomeBanco().equals("")){
					
					form.setNomeArrecadadorMovimento(pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNomeBanco());
				}else{
					form.setNomeArrecadadorMovimento("");
				}
				
				if (pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getDescricaoIdentificacaoServico()!= null &&
						!pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getDescricaoIdentificacaoServico().equals("")){
					
					form.setServicoManutencao(pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getDescricaoIdentificacaoServico());
				}else{
					form.setServicoManutencao("");
				}
			
				if (pagamento.getArrecadadorMovimentoItem().getConteudoRegistro()!= null && 
						!pagamento.getArrecadadorMovimentoItem().getConteudoRegistro().equals("")){
					
					// FAZ A PESQUISA E TRAS O CARACTER CORRESPONDENTE A POSIÇÃO 117
					String caracterRetorno = fachada.pesquisarCaracterRetorno(pagamento.getArrecadadorMovimentoItem().getId());
					Integer codigoRegistro = pagamento.getArrecadadorMovimentoItem().getRegistroCodigo().getId();
					
					// Codigo registro = 6
					if (codigoRegistro.intValue() == RegistroCodigo.CODIGO_SEIS.intValue()){
						
						String descricao = filtrarArrecadacaoForma(form, fachada,"Z");	
						form.setFormaArrecadacao(descricao);	
						
					}else if (caracterRetorno != null  && !caracterRetorno.trim().equals("")){
						
							String descricao = filtrarArrecadacaoForma(form, fachada,caracterRetorno);	
							form.setFormaArrecadacao(descricao);
					
						}else if (codigoRegistro.intValue() == RegistroCodigo.CODIGO_SETE.intValue()){
								//	Codigo registro = 7 e posicao 117 nula
								String descricao = filtrarArrecadacaoForma(form, fachada,"1");	
								form.setFormaArrecadacao(descricao);					
							}else{
								form.setFormaArrecadacao("");
							}
				}
				
			}
		}
		

		return retorno;
	}

	private String filtrarArrecadacaoForma(ConsultarDadosPagamentoActionForm form, Fachada fachada ,String codigo ) {
		
		FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
		
		filtroArrecadacaoForma.adicionarParametro(new ParametroSimples
						(FiltroArrecadacaoForma.CODIGO_ARRECADACAO_FORMA, codigo));
		
		Collection<ArrecadacaoForma> colecaoArrecadacaoForma = 
				fachada.pesquisar(filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
		
		ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
		
		// Alteracao solicitada por Alexandre Cabral
		// Implementada por Tiago Moreno
		// Homologada por Aryed Lins
		// 14/02/2008
		if (colecaoArrecadacaoForma != null && !colecaoArrecadacaoForma.isEmpty()){
			arrecadacaoForma = (ArrecadacaoForma)colecaoArrecadacaoForma.iterator().next();
		}else{
			filtroArrecadacaoForma.limparListaParametros();
			filtroArrecadacaoForma.limparCamposOrderBy();
			filtroArrecadacaoForma.adicionarParametro(new ParametroSimples
					(FiltroArrecadacaoForma.CODIGO, "1"));
	
			arrecadacaoForma = (ArrecadacaoForma) fachada.pesquisar(
					filtroArrecadacaoForma, ArrecadacaoForma.class.getName()).iterator().next();
		}
		// Fim da Alteracao
		
		
		return arrecadacaoForma.getDescricao();
		
	}
}
