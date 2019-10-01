package gcom.cadastro.cliente;

import org.apache.commons.lang.StringUtils;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.endereco.EnderecoTipo;

public class ClienteResponsavelBuilder extends ClienteBuilder {

	public ClienteResponsavelBuilder(AtualizacaoCadastralImovel atualizacaoCadastralImovel) {
		super(atualizacaoCadastralImovel);
	}

	public IClienteAtualizacaoCadastral buildCliente(Short clienteRelacaoTipo) {
		buildCliente(RESPONSAVEL, clienteRelacaoTipo);

		String campo;

		clienteTxt.setDescricaoLogradouro(atualizacaoCadastralImovel.getLinhaCliente("logradouroResponsavel"));

		campo = atualizacaoCadastralImovel.getLinhaCliente("idTipoLogradouroResponsavel");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)) {
			clienteTxt.setIdLogradouroTipo(Integer.parseInt(campo) == 0 ? null : Integer.parseInt(campo));
		}

		clienteTxt.setNumeroImovel(atualizacaoCadastralImovel.getLinhaCliente("numeroResponsavel"));
		clienteTxt.setComplementoEndereco(atualizacaoCadastralImovel.getLinhaCliente("complementoResponsavel"));
		clienteTxt.setNomeBairro(atualizacaoCadastralImovel.getLinhaCliente("bairroResponsavel"));

		campo = atualizacaoCadastralImovel.getLinhaCliente("cepResponsavel");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)) {
			clienteTxt.setCodigoCep(Integer.parseInt(campo));
		}

		clienteTxt.setNomeMunicipio(atualizacaoCadastralImovel.getLinhaCliente("municipioResponsavel"));

		campo = atualizacaoCadastralImovel.getLinhaCliente("tipoEnderecoResponsavel");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)) {
			clienteTxt.setEnderecoTipo(new EnderecoTipo(Integer.parseInt(campo) == 0 ? null : Integer.parseInt(campo)));
		}

		return clienteTxt;
	}
}
