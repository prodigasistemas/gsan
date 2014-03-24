package gcom.cadastro.cliente;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

import org.apache.commons.lang.StringUtils;

public class ClienteProprietarioBuilder extends ClienteBuilder {
	public ClienteProprietarioBuilder(AtualizacaoCadastralImovel atualizacaoCadastralImovel){
		super(atualizacaoCadastralImovel);
	}

	public IClienteAtualizacaoCadastral buildCliente(Short clienteRelacaoTipo) {
		String campo;
		
		buildCliente(PROPRIETARIO, clienteRelacaoTipo);
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("idTipoLogradouroProprietario");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setIdLogradouroTipo(Integer.parseInt(campo));
		}

		clienteTxt.setDescricaoLogradouro(atualizacaoCadastralImovel.getLinhaCliente("logradouroProprietario"));
		clienteTxt.setNumeroImovel(atualizacaoCadastralImovel.getLinhaCliente("numeroProprietario"));
		clienteTxt.setComplementoEndereco(atualizacaoCadastralImovel.getLinhaCliente("complementoProprietario"));
		clienteTxt.setNomeBairro(atualizacaoCadastralImovel.getLinhaCliente("bairroProprietario"));
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("cepProprietario");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setCodigoCep(Integer.parseInt(campo));
		}
		
		clienteTxt.setNomeMunicipio(atualizacaoCadastralImovel.getLinhaCliente("municipioProprietario"));
		
		return clienteTxt;
	}
}
