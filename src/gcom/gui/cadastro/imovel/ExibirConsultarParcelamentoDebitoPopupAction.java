package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.parcelamento.FiltroParcelamento;
import gcom.cobranca.parcelamento.FiltroParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
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

public class ExibirConsultarParcelamentoDebitoPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("exibirConsultarParcelamentoDebitoPopup");

		ParcelamentoDebitoActionForm form = (ParcelamentoDebitoActionForm) actionForm;

		String codigoImovel = request.getParameter("codigoImovel");
		String codigoParcelamento = request.getParameter("codigoParcelamento");

		if (codigoImovel != null && !codigoImovel.trim().equals("")) {

			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
			filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, codigoImovel));
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial.municipio.unidadeFederacao");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.imovelPerfil");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAguaSituacao");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoEsgotoSituacao");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

			Collection<ClienteImovel> imovelPesquisado = getFachada().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());

			if (imovelPesquisado != null && imovelPesquisado.isEmpty()) {
				request.setAttribute("enderecoFormatado", "Matrícula Inexistente".toUpperCase());
				form.setInscricao("");
				form.setNomeCliente("");
				form.setCpfCnpj("");
				form.setSituacaoAgua("");
				form.setSituacaoEsgoto("");
				form.setImovelPerfil("");
			}

			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				ClienteImovel dadosImovel = (ClienteImovel) ((List) imovelPesquisado).get(0);

				if (dadosImovel.getImovel().getId() != null) {
					form.setCodigoImovel("" + dadosImovel.getImovel().getId());
				}

				if (dadosImovel.getImovel().getInscricaoFormatada() != null) {
					form.setInscricao("" + dadosImovel.getImovel().getInscricaoFormatada());
				}
				
				if (dadosImovel.getImovel().getLigacaoAguaSituacao() != null) {
					form.setSituacaoAgua("" + dadosImovel.getImovel().getLigacaoAguaSituacao().getDescricao());
				}
				
				if (dadosImovel.getImovel().getLigacaoEsgotoSituacao() != null) {
					form.setSituacaoEsgoto("" + dadosImovel.getImovel().getLigacaoEsgotoSituacao().getDescricao());
				}
				
				if (dadosImovel.getCliente().getNome() != null) {
					form.setNomeCliente("" + dadosImovel.getCliente().getNome());
				}
				
				if (dadosImovel.getImovel().getImovelPerfil() != null) {
					form.setImovelPerfil("" + dadosImovel.getImovel().getImovelPerfil().getDescricao());
				}
				
				if (dadosImovel.getCliente().getClienteTipo().getIndicadorPessoaFisicaJuridica() == 1) {
					if (dadosImovel.getCliente().getCpfFormatado() != null) {
						form.setCpfCnpj("" + dadosImovel.getCliente().getCpfFormatado());
					}
				} else {
					if (dadosImovel.getCliente().getCnpjFormatado() != null) {
						form.setCpfCnpj("" + dadosImovel.getCliente().getCnpjFormatado());
					}
				}
				
				if (dadosImovel.getImovel().getNumeroParcelamento() != null) {
					form.setParcelamento("" + dadosImovel.getImovel().getNumeroParcelamento());
				}
				
				if (dadosImovel.getImovel().getNumeroReparcelamento() != null) {
					form.setReparcelamento("" + dadosImovel.getImovel().getNumeroReparcelamento());
				}
				
				if (dadosImovel.getImovel().getNumeroReparcelamentoConsecutivos() != null) {
					form.setReparcelamentoConsecutivo("" + dadosImovel.getImovel().getNumeroReparcelamentoConsecutivos());
				}
				
				request.setAttribute("imovelPesquisado", imovelPesquisado);
				String enderecoFormatado = "";
				try {
					enderecoFormatado = getFachada().pesquisarEnderecoFormatado(new Integer(codigoImovel));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (ControladorException e) {
					e.printStackTrace();
				}

				request.setAttribute("enderecoFormatado", enderecoFormatado);
			}
			
			FiltroParcelamento filtroParcelamento = new FiltroParcelamento();
			filtroParcelamento.adicionarParametro(new ParametroSimples(FiltroParcelamento.ID, codigoParcelamento));
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("parcelamentoSituacao");
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("usuario");
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("funcionario");
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("cobrancaForma");
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("cliente");

			Collection<Parcelamento> colecaoParcelamento = getFachada().pesquisar(filtroParcelamento, Parcelamento.class.getName());

			SistemaParametro sistemaParametro = getFachada().pesquisarParametrosDoSistema();
			
			if (colecaoParcelamento != null && !colecaoParcelamento.isEmpty()) {
				request.setAttribute("colecaoParcelamento", colecaoParcelamento);

				Iterator iteratorParcelamento = colecaoParcelamento.iterator();
				while (iteratorParcelamento.hasNext()) {

					Parcelamento parcelamento = (Parcelamento) iteratorParcelamento.next();

					if ((parcelamento.getAnoMesReferenciaFaturamento().equals(new Integer(sistemaParametro.getAnoMesFaturamento())))
							&& parcelamento.getParcelamentoSituacao().getId().intValue() == ParcelamentoSituacao.NORMAL.intValue()) {

						FiltroParcelamentoMotivoDesfazer filtroParcelamentoMotivoDesfazer = new FiltroParcelamentoMotivoDesfazer();
						Collection<ParcelamentoMotivoDesfazer> collectionParcelamentoMotivoDesfazer = getFachada().pesquisar(filtroParcelamentoMotivoDesfazer, ParcelamentoMotivoDesfazer.class.getName());

						request.setAttribute("collectionParcelamentoMotivoDesfazer", collectionParcelamentoMotivoDesfazer);
					}
				}
			}
		}
		return retorno;
	}
}
