package gcom.cadastro.cliente;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

public class ClienteResponsavelAtualizacaoCadastral extends ClienteAtualizacaoCadastral{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8230996977700617985L;
	
	private AtualizacaoCadastralImovel atualizacaoCadastralImovel;
	
	public ClienteResponsavelAtualizacaoCadastral(AtualizacaoCadastralImovel atualizacaoCadastralImovel){
		this.atualizacaoCadastralImovel = atualizacaoCadastralImovel;
		
		buildCliente();
	}
	

	@Override
	public void buildCliente() {
		this.setNomeCliente(atualizacaoCadastralImovel.getLinhaCliente("nomeResponsavel"));
		this.setCpfCnpj(atualizacaoCadastralImovel.getLinhaCliente("cnpjCpfResponsavel"));
		this.setRg(atualizacaoCadastralImovel.getLinhaCliente("rgResponsavel"));
		this.setDsUFSiglaOrgaoExpedidorRg(atualizacaoCadastralImovel.getLinhaCliente("ufRgResponsavel"));
		this.setIdPessoaSexo(atualizacaoCadastralImovel.getLinhaCliente("sexoResponsavel").equals("") ? null : Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("sexoResponsavel")));
		this.setEmail(atualizacaoCadastralImovel.getLinhaCliente("emailResponsavel"));
		this.setIdLogradouroTipo(Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("idTipoLogradouroResponsavel")));
		this.setDescricaoLogradouro(atualizacaoCadastralImovel.getLinhaCliente("logradouroResponsavel"));
		this.setNumeroImovel(atualizacaoCadastralImovel.getLinhaCliente("numeroResponsavel"));
		this.setComplementoEndereco(atualizacaoCadastralImovel.getLinhaCliente("complementoResponsavel"));
		this.setNomeBairro(atualizacaoCadastralImovel.getLinhaCliente("bairroResponsavel"));
		this.setCodigoCep(Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("cepResponsavel")));
		this.setNomeMunicipio(atualizacaoCadastralImovel.getLinhaCliente("municipioResponsavel"));
		this.setIdClienteRelacaoTipo(new Integer(ClienteRelacaoTipo.RESPONSAVEL));
		this.setIdImovel(Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("matriculaImovelCliente")));
	}

}
