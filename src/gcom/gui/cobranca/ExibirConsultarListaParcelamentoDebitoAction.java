package gcom.gui.cobranca;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.parcelamento.FiltroParcelamento;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.filtro.Filtro;
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

	private ParcelamentoDebitoActionForm form;

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		this.form = (ParcelamentoDebitoActionForm) actionForm;

		String codigoImovel = request.getParameter("codigoImovel");

		if (codigoImovel != null && !codigoImovel.trim().equals("")) {
			Collection<ClienteImovel> clienteImovel = pesquisarClienteImovel(codigoImovel);

			resetar(request, clienteImovel);

			if (clienteImovel != null && !clienteImovel.isEmpty()) {
				setarForm(clienteImovel);
				request.setAttribute("imovelPesquisado", clienteImovel);
				request.setAttribute("enderecoFormatado", pesquisarEndereco(codigoImovel));
				pesquisarParcelamentos(request, codigoImovel);
			}
		}

		return mapping.findForward("consultarListaParcelamentoDebito");
	}

	private void setarForm(Collection<ClienteImovel> clienteImovel) {
		ClienteImovel dados = (ClienteImovel) ((List<ClienteImovel>) clienteImovel).get(0);
		Imovel imovel = dados.getImovel();
		Cliente cliente = dados.getCliente();

		if (imovel.getId() != null) {
			form.setCodigoImovel(imovel.getId().toString());
		}

		if (imovel.getInscricaoFormatada() != null) {
			form.setInscricao(imovel.getInscricaoFormatada());
		}

		if (imovel.getLigacaoAguaSituacao() != null) {
			form.setSituacaoAgua(imovel.getLigacaoAguaSituacao().getDescricao());
		}

		if (imovel.getLigacaoEsgotoSituacao() != null) {
			form.setSituacaoEsgoto(imovel.getLigacaoEsgotoSituacao().getDescricao());
		}

		if (cliente.getNome() != null) {
			form.setNomeCliente(cliente.getNome());
		}

		if (imovel.getImovelPerfil() != null) {
			form.setImovelPerfil(imovel.getImovelPerfil().getDescricao());
		}

		if (cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica() == 1) {
			if (cliente.getCpfFormatado() != null) {
				form.setCpfCnpj(cliente.getCpfFormatado());
			}
		} else {
			if (cliente.getCnpjFormatado() != null) {
				form.setCpfCnpj(cliente.getCnpjFormatado());
			}
		}

		if (imovel.getNumeroParcelamento() != null) {
			form.setParcelamento(imovel.getNumeroParcelamento().toString());
		}

		if (imovel.getNumeroReparcelamento() != null) {
			form.setReparcelamento(imovel.getNumeroReparcelamento().toString());
		}

		if (imovel.getNumeroReparcelamentoConsecutivos() != null) {
			form.setReparcelamentoConsecutivo(imovel.getNumeroReparcelamentoConsecutivos().toString());
		}
	}

	private String pesquisarEndereco(String codigoImovel) {
		String enderecoFormatado = "";
		try {
			enderecoFormatado = getFachada().pesquisarEnderecoFormatado(new Integer(codigoImovel));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		return enderecoFormatado;
	}

	private void resetar(HttpServletRequest request, Collection<ClienteImovel> clienteImovel) {
		if (clienteImovel != null && clienteImovel.isEmpty()) {
			request.setAttribute("enderecoFormatado", "");
			form.setNomeCliente("");
			form.setCpfCnpj("");
			form.setSituacaoAgua("");
			form.setSituacaoEsgoto("");
			request.setAttribute("corImovel", "exception");
			form.setInscricao(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
		}
	}

	@SuppressWarnings("unchecked")
	private void pesquisarParcelamentos(HttpServletRequest request, String codigoImovel) {
		Filtro filtro = new FiltroParcelamento("parcelamento");
		filtro.adicionarParametro(new ParametroSimples(FiltroParcelamento.IMOVEL_ID, codigoImovel));
		filtro.adicionarCaminhoParaCarregamentoEntidade("parcelamentoSituacao");

		Collection<Parcelamento> parcelamentos = getFachada().pesquisar(filtro, Parcelamento.class.getName());
		if (parcelamentos != null && !parcelamentos.isEmpty()) {
			request.setAttribute("colecaoParcelamento", parcelamentos);
		} else {
			if (parcelamentos == null || parcelamentos.isEmpty()) {
				throw new ActionServletException("atencao.parcelamento.inexistente");
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Collection<ClienteImovel> pesquisarClienteImovel(String codigoImovel) {
		Filtro filtro = new FiltroClienteImovel();
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

		return getFachada().pesquisar(filtro, ClienteImovel.class.getName());
	}
}
