package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.parcelamento.FiltroParcelamento;
import gcom.cobranca.parcelamento.FiltroParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.cobranca.ParcelamentoDebitoActionForm;
import gcom.util.ControladorException;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Popup da 9° Aba - Parcelamento de Débitos
 * 
 * @author Rafael Santos
 * @since 21/09/2006
 */
public class ExibirConsultarParcelamentoDebitoPopupAction extends
		GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta a ação de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarParcelamentoDebitoPopup");

		ParcelamentoDebitoActionForm parcelamentoDebitoActionForm = (ParcelamentoDebitoActionForm) actionForm;
		
		// Obtém a facahda
		Fachada fachada = Fachada.getInstancia();

		// Pega os codigos que o usuario digitou para a pesquisa direta de imovel
		String codigoImovel = httpServletRequest.getParameter("codigoImovel");
		String codigoParcelamento = httpServletRequest.getParameter("codigoParcelamento");
		
		if (codigoImovel != null && !codigoImovel.trim().equals("")) {
			
			// Pesquisa o imovel na base
			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.IMOVEL_ID, codigoImovel));

			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial.municipio.unidadeFederacao");
			
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro");
			
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");

			/*filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.lote");*/
			
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");

			/*filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.subLote");*/
			
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.imovelPerfil");
			
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAguaSituacao");

			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoEsgotoSituacao");
			
			/*filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("cliente.nome");

			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("cliente.cpf");

			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("cliente.cnpj");*/
			
			filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade("cliente");
			
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");

			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

			Collection<ClienteImovel> imovelPesquisado = fachada.pesquisar(
					filtroClienteImovel, ClienteImovel.class.getName());

			// Se nenhum imovel for encontrado a mensagem é enviada para a página
			if (imovelPesquisado != null && imovelPesquisado.isEmpty()) {
				httpServletRequest.setAttribute("enderecoFormatado",
						"Matrícula Inexistente".toUpperCase());
				parcelamentoDebitoActionForm.setInscricao("");
				parcelamentoDebitoActionForm.setNomeCliente("");
				parcelamentoDebitoActionForm.setCpfCnpj("");
				parcelamentoDebitoActionForm.setSituacaoAgua("");
				parcelamentoDebitoActionForm.setSituacaoEsgoto("");
				parcelamentoDebitoActionForm.setImovelPerfil("");
			}
			// obtem o imovel pesquisado
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				
				ClienteImovel dadosImovel = (ClienteImovel) ((List) imovelPesquisado).get(0);
				
				//O endereço foi encontrado
				if (dadosImovel.getImovel().getId() != null) 
				{
					parcelamentoDebitoActionForm.setCodigoImovel(""
							+ dadosImovel.getImovel().getId());
				}
				if (dadosImovel.getImovel().getInscricaoFormatada() != null) 
				{
					parcelamentoDebitoActionForm.setInscricao(""
							+ dadosImovel.getImovel().getInscricaoFormatada());
				}
				if (dadosImovel.getImovel().getLigacaoAguaSituacao() != null) 
				{
					parcelamentoDebitoActionForm.setSituacaoAgua(""
							+ dadosImovel.getImovel().getLigacaoAguaSituacao().getDescricao());
				}
				if (dadosImovel.getImovel().getLigacaoEsgotoSituacao() != null) 
				{
					parcelamentoDebitoActionForm.setSituacaoEsgoto(""
							+ dadosImovel.getImovel().getLigacaoEsgotoSituacao().getDescricao());
				}
				if (dadosImovel.getCliente().getNome() != null) 
				{
					parcelamentoDebitoActionForm.setNomeCliente(""
							+ dadosImovel.getCliente().getNome());
				}
				if (dadosImovel.getImovel().getImovelPerfil() != null) 
				{
					parcelamentoDebitoActionForm.setImovelPerfil(""
							+ dadosImovel.getImovel().getImovelPerfil().getDescricao());
				}
				if (dadosImovel.getCliente().getClienteTipo().getIndicadorPessoaFisicaJuridica() == 1 ){
					if (dadosImovel.getCliente().getCpfFormatado() != null) 
					{
						parcelamentoDebitoActionForm.setCpfCnpj(""
								+ dadosImovel.getCliente().getCpfFormatado());
					}
				}
				else
				{
					if (dadosImovel.getCliente().getCnpjFormatado() != null) 
					{
						parcelamentoDebitoActionForm.setCpfCnpj(""
								+ dadosImovel.getCliente().getCnpjFormatado());
					}
				}
				if (dadosImovel.getImovel().getNumeroParcelamento() != null) 
				{
					parcelamentoDebitoActionForm.setParcelamento(""
							+ dadosImovel.getImovel().getNumeroParcelamento());
				}
				if (dadosImovel.getImovel().getNumeroReparcelamento() != null) 
				{
					parcelamentoDebitoActionForm.setReparcelamento(""
							+ dadosImovel.getImovel().getNumeroReparcelamento());
				}
				if (dadosImovel.getImovel().getNumeroReparcelamentoConsecutivos() != null) 
				{
					parcelamentoDebitoActionForm.setReparcelamentoConsecutivo(""
							+ dadosImovel.getImovel().getNumeroReparcelamentoConsecutivos());
				}
				// Manda a colecao pelo request
				httpServletRequest.setAttribute("imovelPesquisado",
						imovelPesquisado);
				String enderecoFormatado = "";
				try {
					enderecoFormatado = fachada.pesquisarEnderecoFormatado(new Integer(codigoImovel));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (ControladorException e) {
					e.printStackTrace();
				}
				
				httpServletRequest.setAttribute("enderecoFormatado",enderecoFormatado);
			}
			FiltroParcelamento filtroParcelamento = new FiltroParcelamento();

			filtroParcelamento.adicionarParametro(new ParametroSimples(
						FiltroParcelamento.ID, codigoParcelamento));

			filtroParcelamento
			.adicionarCaminhoParaCarregamentoEntidade("parcelamentoSituacao");

			filtroParcelamento
					.adicionarCaminhoParaCarregamentoEntidade("usuario");
			
			filtroParcelamento
					.adicionarCaminhoParaCarregamentoEntidade("funcionario");
		
			filtroParcelamento
					.adicionarCaminhoParaCarregamentoEntidade("cobrancaForma");
			
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("cliente");
			
			Collection<Parcelamento> colecaoParcelamento = fachada.pesquisar(filtroParcelamento, Parcelamento.class.getName() );
			
			SistemaParametro sistemaParametro = null;
	        
			if (colecaoParcelamento != null && !colecaoParcelamento.isEmpty()) 
			{
				httpServletRequest.setAttribute("colecaoParcelamento", colecaoParcelamento);
			
				Iterator iteratorParcelamento = colecaoParcelamento.iterator();
				while (iteratorParcelamento.hasNext()) {
		        	
		        	Parcelamento parcelamento = (Parcelamento) iteratorParcelamento.next();
		        	
		        	// Retorna o único objeto da tabela sistemaParametro
		            sistemaParametro = fachada.pesquisarParametrosDoSistema();

		        	if((parcelamento.getAnoMesReferenciaFaturamento()
		        			.equals(new Integer(sistemaParametro.getAnoMesFaturamento()))) 
		        		&& parcelamento.getParcelamentoSituacao().getId().intValue() == ParcelamentoSituacao.NORMAL.intValue()) 
		        	{
		        		
		        		FiltroParcelamentoMotivoDesfazer filtroParcelamentoMotivoDesfazer = new FiltroParcelamentoMotivoDesfazer();
		        		Collection<ParcelamentoMotivoDesfazer> collectionParcelamentoMotivoDesfazer = fachada.pesquisar(filtroParcelamentoMotivoDesfazer, ParcelamentoMotivoDesfazer.class.getName() );
		        		 
		        		httpServletRequest.setAttribute("collectionParcelamentoMotivoDesfazer", collectionParcelamentoMotivoDesfazer);
		        	}
		        }
			}
		}
		return retorno;
	}
}
