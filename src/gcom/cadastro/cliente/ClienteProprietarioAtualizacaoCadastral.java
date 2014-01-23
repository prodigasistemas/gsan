package gcom.cadastro.cliente;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

public class ClienteProprietarioAtualizacaoCadastral extends ClienteAtualizacaoCadastral{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3675357746224308883L;

	private AtualizacaoCadastralImovel atualizacaoCadastralImovel;
	
	public ClienteProprietarioAtualizacaoCadastral(AtualizacaoCadastralImovel atualizacaoCadastralImovel){
		this.atualizacaoCadastralImovel = atualizacaoCadastralImovel;
		
		buildCliente();
	}
	
	@Override
	public void buildCliente() {
		this.setNomeCliente(atualizacaoCadastralImovel.getLinhaCliente("nomeProprietario"));
		this.setCpfCnpj(atualizacaoCadastralImovel.getLinhaCliente("cnpjCpfProprietario"));
		this.setRg(atualizacaoCadastralImovel.getLinhaCliente("rgProprietario"));
		this.setDsUFSiglaOrgaoExpedidorRg(atualizacaoCadastralImovel.getLinhaCliente("ufRgProprietario"));
		this.setIdPessoaSexo(Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("sexoProprietario")));
		this.setEmail(atualizacaoCadastralImovel.getLinhaCliente("emailProprietario"));
		this.setIdLogradouroTipo(Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("idTipoLogradouroProprietario")));
		this.setDescricaoLogradouro(atualizacaoCadastralImovel.getLinhaCliente("logradouroProprietario"));
		this.setNumeroImovel(atualizacaoCadastralImovel.getLinhaCliente("numeroProprietario"));
		this.setComplementoEndereco(atualizacaoCadastralImovel.getLinhaCliente("complementoProprietario"));
		this.setNomeBairro(atualizacaoCadastralImovel.getLinhaCliente("bairroProprietario"));
		this.setCodigoCep(atualizacaoCadastralImovel.getLinhaCliente("cepProprietario").equals("") ? null : Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("cepProprietario")));
		this.setNomeMunicipio(atualizacaoCadastralImovel.getLinhaCliente("municipioProprietario"));
		this.setIdClienteRelacaoTipo(new Integer(ClienteRelacaoTipo.PROPRIETARIO));
		this.setIdImovel(Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("matriculaImovelCliente")));
	}

}
