package gcom.cadastro.cliente;

import org.apache.commons.lang.StringUtils;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

public class ClienteResponsavelBuilder extends ClienteBuilder{

	public ClienteResponsavelBuilder(AtualizacaoCadastralImovel atualizacaoCadastralImovel){
		super(atualizacaoCadastralImovel);
	}
	

	public IClienteAtualizacaoCadastral buildCliente(Short clienteRelacaoTipo) {
		buildCliente(RESPONSAVEL, clienteRelacaoTipo);
		
		String campo;
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("idTipoLogradouroResponsavel");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setIdLogradouroTipo(Integer.parseInt(campo));
		}
		
		clienteTxt.setDescricaoLogradouro(atualizacaoCadastralImovel.getLinhaCliente("logradouroResponsavel"));
		clienteTxt.setNumeroImovel(atualizacaoCadastralImovel.getLinhaCliente("numeroResponsavel"));
		clienteTxt.setComplementoEndereco(atualizacaoCadastralImovel.getLinhaCliente("complementoResponsavel"));
		clienteTxt.setNomeBairro(atualizacaoCadastralImovel.getLinhaCliente("bairroResponsavel"));
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("cepResponsavel");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setCodigoCep(Integer.parseInt(campo));
		}
		
		clienteTxt.setNomeMunicipio(atualizacaoCadastralImovel.getLinhaCliente("municipioResponsavel"));
		
		return clienteTxt;
	}
}
