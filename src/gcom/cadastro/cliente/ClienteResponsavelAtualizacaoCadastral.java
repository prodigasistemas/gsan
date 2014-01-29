package gcom.cadastro.cliente;

import org.apache.commons.lang.StringUtils;

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
		
		String campo = atualizacaoCadastralImovel.getLinhaCliente("sexoResponsavel");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			this.setIdPessoaSexo(Integer.parseInt(campo));
		}
		
		this.setEmail(atualizacaoCadastralImovel.getLinhaCliente("emailResponsavel"));
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("idTipoLogradouroResponsavel");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			this.setIdLogradouroTipo(Integer.parseInt(campo));
		}
		
		this.setDescricaoLogradouro(atualizacaoCadastralImovel.getLinhaCliente("logradouroResponsavel"));
		this.setNumeroImovel(atualizacaoCadastralImovel.getLinhaCliente("numeroResponsavel"));
		this.setComplementoEndereco(atualizacaoCadastralImovel.getLinhaCliente("complementoResponsavel"));
		this.setNomeBairro(atualizacaoCadastralImovel.getLinhaCliente("bairroResponsavel"));
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("cepResponsavel");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			this.setCodigoCep(Integer.parseInt(campo));
		}
		
		this.setNomeMunicipio(atualizacaoCadastralImovel.getLinhaCliente("municipioResponsavel"));
		this.setIdClienteRelacaoTipo(new Integer(ClienteRelacaoTipo.RESPONSAVEL));
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("matriculaImovelCliente");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			this.setIdImovel(Integer.parseInt(campo));
		}
	}

}
