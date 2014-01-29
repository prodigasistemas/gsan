package gcom.cadastro.cliente;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

import org.apache.commons.lang.StringUtils;

public class ClienteProprietarioBuilder {
	private AtualizacaoCadastralImovel atualizacaoCadastralImovel;
	
	IClienteAtualizacaoCadastral clienteTxt = null;
	
	public ClienteProprietarioBuilder(AtualizacaoCadastralImovel atualizacaoCadastralImovel){
		this.atualizacaoCadastralImovel = atualizacaoCadastralImovel;
		
		buildCliente();
	}
	
	private void buildCliente() {
		clienteTxt = new ClienteAtualizacaoCadastral();

		clienteTxt.setNomeCliente(atualizacaoCadastralImovel.getLinhaCliente("nomeProprietario"));
		clienteTxt.setCpfCnpj(atualizacaoCadastralImovel.getLinhaCliente("cnpjCpfProprietario"));
		clienteTxt.setRg(atualizacaoCadastralImovel.getLinhaCliente("rgProprietario"));
		clienteTxt.setDsUFSiglaOrgaoExpedidorRg(atualizacaoCadastralImovel.getLinhaCliente("ufRgProprietario"));
		
		String campo = atualizacaoCadastralImovel.getLinhaCliente("sexoProprietario");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setIdPessoaSexo(Integer.parseInt(campo));
		}
		
		clienteTxt.setEmail(atualizacaoCadastralImovel.getLinhaCliente("emailProprietario"));
		
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
		clienteTxt.setIdClienteRelacaoTipo(new Integer(ClienteRelacaoTipo.PROPRIETARIO));
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("matriculaImovelCliente");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setIdImovel(Integer.parseInt(campo));
		}
	}

	public IClienteAtualizacaoCadastral getClienteTxt() {
		return clienteTxt;
	}
}
