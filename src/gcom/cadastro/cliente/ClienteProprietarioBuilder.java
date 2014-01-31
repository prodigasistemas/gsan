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

		clienteTxt.setNome(atualizacaoCadastralImovel.getLinhaCliente("nomeProprietario"));
		
		String campo = atualizacaoCadastralImovel.getLinhaCliente("cnpjCpfProprietario");
		if (campo.length() == 11) {
			clienteTxt.setCpf(campo);
		} else if (campo.length() == 14) {
			clienteTxt.setCnpj(campo);
		}
		
		clienteTxt.setRg(atualizacaoCadastralImovel.getLinhaCliente("rgProprietario"));
		clienteTxt.setDsUFSiglaOrgaoExpedidorRg(atualizacaoCadastralImovel.getLinhaCliente("ufRgProprietario"));
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("sexoProprietario");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			PessoaSexo sexo = new PessoaSexo(Integer.parseInt(campo));
			clienteTxt.setPessoaSexo(sexo);
		}
		
		clienteTxt.setEmail(atualizacaoCadastralImovel.getLinhaCliente("emailProprietario"));
		clienteTxt.setIdCliente(new Integer(atualizacaoCadastralImovel.getLinhaCliente("matriculaProprietario")));
		
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

		campo = atualizacaoCadastralImovel.getLinhaCliente("tipoPessoaProprietario");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setIdClienteTipo(Integer.parseInt(campo));
		}
	}

	public IClienteAtualizacaoCadastral getClienteTxt() {
		return clienteTxt;
	}
}
