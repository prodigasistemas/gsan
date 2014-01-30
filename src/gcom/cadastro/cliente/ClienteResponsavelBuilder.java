package gcom.cadastro.cliente;

import org.apache.commons.lang.StringUtils;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

public class ClienteResponsavelBuilder{
	private AtualizacaoCadastralImovel atualizacaoCadastralImovel;
	
	IClienteAtualizacaoCadastral clienteTxt = null;

	public ClienteResponsavelBuilder(AtualizacaoCadastralImovel atualizacaoCadastralImovel){
		this.atualizacaoCadastralImovel = atualizacaoCadastralImovel;
		
		buildCliente();
	}
	

	private void buildCliente() {
		clienteTxt = new ClienteAtualizacaoCadastral();

		clienteTxt.setNome(atualizacaoCadastralImovel.getLinhaCliente("nomeResponsavel"));
		
		String campo = atualizacaoCadastralImovel.getLinhaCliente("cnpjCpfResponsavel");
		if (campo.length() == 11) {
			clienteTxt.setCpf(campo);
		} else if (campo.length() == 14) {
			clienteTxt.setCnpj(campo);
		}
		
		clienteTxt.setRg(atualizacaoCadastralImovel.getLinhaCliente("rgResponsavel"));
		clienteTxt.setDsUFSiglaOrgaoExpedidorRg(atualizacaoCadastralImovel.getLinhaCliente("ufRgResponsavel"));
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("sexoResponsavel");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			PessoaSexo sexo = new PessoaSexo(Integer.parseInt(campo));
			clienteTxt.setPessoaSexo(sexo);
		}
		
		clienteTxt.setEmail(atualizacaoCadastralImovel.getLinhaCliente("emailResponsavel"));
		clienteTxt.setIdCliente(new Integer(atualizacaoCadastralImovel.getLinhaCliente("matriculaResponsavel")));

		
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
		clienteTxt.setIdClienteRelacaoTipo(new Integer(ClienteRelacaoTipo.RESPONSAVEL));
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("matriculaImovelCliente");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setIdImovel(Integer.parseInt(campo));
		}

		campo = atualizacaoCadastralImovel.getLinhaCliente("tipoPessoaResponsavel");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setIdClienteTipo(Integer.parseInt(campo));
		}
	}
	
	public IClienteAtualizacaoCadastral getClienteTxt() {
		return clienteTxt;
	}
}
