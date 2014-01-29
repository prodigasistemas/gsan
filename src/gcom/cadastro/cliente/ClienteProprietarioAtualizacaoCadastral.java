package gcom.cadastro.cliente;

import org.apache.commons.lang.StringUtils;

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
		this.setNome(atualizacaoCadastralImovel.getLinhaCliente("nomeProprietario"));
		this.setCpf(atualizacaoCadastralImovel.getLinhaCliente("cnpjCpfProprietario"));
		this.setRg(atualizacaoCadastralImovel.getLinhaCliente("rgProprietario"));
		this.setDsUFSiglaOrgaoExpedidorRg(atualizacaoCadastralImovel.getLinhaCliente("ufRgProprietario"));
		
		String campo = atualizacaoCadastralImovel.getLinhaCliente("sexoProprietario");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			this.setIdPessoaSexo(Integer.parseInt(campo));
		}
		
		this.setEmail(atualizacaoCadastralImovel.getLinhaCliente("emailProprietario"));
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("idTipoLogradouroProprietario");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			this.setIdLogradouroTipo(Integer.parseInt(campo));
		}
		
		this.setDescricaoLogradouro(atualizacaoCadastralImovel.getLinhaCliente("logradouroProprietario"));
		this.setNumeroImovel(atualizacaoCadastralImovel.getLinhaCliente("numeroProprietario"));
		this.setComplementoEndereco(atualizacaoCadastralImovel.getLinhaCliente("complementoProprietario"));
		this.setNomeBairro(atualizacaoCadastralImovel.getLinhaCliente("bairroProprietario"));
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("cepProprietario");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			this.setCodigoCep(Integer.parseInt(campo));
		}
		
		this.setNomeMunicipio(atualizacaoCadastralImovel.getLinhaCliente("municipioProprietario"));
		this.setIdClienteRelacaoTipo(new Integer(ClienteRelacaoTipo.PROPRIETARIO));
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("matriculaImovelCliente");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			this.setIdImovel(Integer.parseInt(campo));
		}
		this.setIdImovel(Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("matriculaImovelCliente")));
		this.setIdClienteTipo(Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("tipoPessoaProprietario")));

	}
}
