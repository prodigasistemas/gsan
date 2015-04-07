package gcom.cadastro.cliente;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

import org.apache.commons.lang.StringUtils;

public class ClienteUsuarioBuilder extends ClienteBuilder {
	public ClienteUsuarioBuilder(AtualizacaoCadastralImovel atualizacaoCadastralImovel){
		super(atualizacaoCadastralImovel);
	}
	
	public IClienteAtualizacaoCadastral buildCliente(Short clienteRelacaoTipo) {
		buildCliente(USUARIO, clienteRelacaoTipo);

		String campo;
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("matriculaImovelCliente");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setIdImovel(Integer.parseInt(campo));
		}
		
		clienteTxt.setDescricaoLogradouro(atualizacaoCadastralImovel.getLinhaImovel("logradouroImovel"));
		
		campo = atualizacaoCadastralImovel.getLinhaImovel("idTipoLogradouroImovel");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setIdLogradouroTipo(Integer.parseInt(campo) == 0 ? null : Integer.parseInt(campo));
		}

		clienteTxt.setNumeroImovel(atualizacaoCadastralImovel.getLinhaImovel("numeroImovel"));
		clienteTxt.setComplementoEndereco(atualizacaoCadastralImovel.getLinhaImovel("complementoImovel"));
		clienteTxt.setNomeBairro(atualizacaoCadastralImovel.getLinhaImovel("bairro"));
		
		campo = atualizacaoCadastralImovel.getLinhaImovel("cep");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setCodigoCep(Integer.parseInt(campo));
		}

		clienteTxt.setNomeMunicipio(atualizacaoCadastralImovel.getLinhaImovel("municipio"));
		
		return clienteTxt;
	}
}
