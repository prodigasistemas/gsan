package gcom.gui.cobranca;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cobranca.parcelamento.FiltroParcelamento;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarListaParcelamentoDebitoAction extends GcomAction {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ActionForward retorno = actionMapping.findForward("consultarListaParcelamentoDebito");

		ParcelamentoDebitoActionForm form = (ParcelamentoDebitoActionForm) actionForm;

		String codigoImovel = httpServletRequest.getParameter("codigoImovel");
		if (codigoImovel != null && !codigoImovel.trim().equals("")) {
			FiltroClienteImovel filtro = new FiltroClienteImovel();
			filtro.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, codigoImovel));
			filtro.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, ClienteRelacaoTipo.USUARIO));
			filtro.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
			filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial.municipio.unidadeFederacao");
			filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro");
			filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
			filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
			filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.imovelPerfil");
			filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAguaSituacao");
			filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoEsgotoSituacao");
			filtro.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtro.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
			filtro.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

			Collection<ClienteImovel> imovelPesquisado = getFachada().pesquisar(filtro, ClienteImovel.class.getName());

			if (imovelPesquisado != null && imovelPesquisado.isEmpty()) {
				httpServletRequest.setAttribute("enderecoFormatado", "".toUpperCase());
				form.setNomeCliente("");
				form.setCpfCnpj("");
				form.setSituacaoAgua("");
				form.setSituacaoEsgoto("");
				httpServletRequest.setAttribute("corImovel", "exception");
				form.setInscricao(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
			}

			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				ClienteImovel dadosImovel = (ClienteImovel) ((List<ClienteImovel>) imovelPesquisado).get(0);

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
				
				httpServletRequest.setAttribute("imovelPesquisado", imovelPesquisado);
				
				String enderecoFormatado = "";
				try {
					enderecoFormatado = getFachada().pesquisarEnderecoFormatado(new Integer(codigoImovel));
				} catch (NumberFormatException e) {

					e.printStackTrace();
				} catch (ControladorException e) {

					e.printStackTrace();
				}

				httpServletRequest.setAttribute("enderecoFormatado", enderecoFormatado);
				FiltroParcelamento filtroParcelamento = new FiltroParcelamento();
				filtroParcelamento.adicionarParametro(new ParametroSimples(FiltroParcelamento.IMOVEL_ID, codigoImovel));
				filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("parcelamentoSituacao");

				Collection<Parcelamento> colecaoParcelamento = getFachada().pesquisar(filtroParcelamento, Parcelamento.class.getName());
				if (colecaoParcelamento != null && !colecaoParcelamento.isEmpty()) {
					httpServletRequest.setAttribute("colecaoParcelamento", colecaoParcelamento);
				} else {
					if (colecaoParcelamento == null || colecaoParcelamento.isEmpty()) {
						throw new ActionServletException("atencao.parcelamento.inexistente");
					}
				}
			}
		}
		return retorno;
	}
}
