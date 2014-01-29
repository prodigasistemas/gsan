package gcom.cadastro.cliente;

import org.apache.commons.lang.StringUtils;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.endereco.FiltroLogradouroTipo;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.fachada.Fachada;
import gcom.util.filtro.ParametroSimples;

public class ClienteUsuarioBuilder{
	private AtualizacaoCadastralImovel atualizacaoCadastralImovel;
	
	IClienteAtualizacaoCadastral clienteTxt = null;
	
	public ClienteUsuarioBuilder(AtualizacaoCadastralImovel atualizacaoCadastralImovel){
		this.atualizacaoCadastralImovel = atualizacaoCadastralImovel;
		
		buildCliente();
	}
	
	private void buildCliente() {
		clienteTxt = new ClienteAtualizacaoCadastral();
		
		clienteTxt.setNomeCliente(atualizacaoCadastralImovel.getLinhaCliente("nomeUsuario"));
		clienteTxt.setCpfCnpj(atualizacaoCadastralImovel.getLinhaCliente("cnpjCpfUsuario"));
		clienteTxt.setRg(atualizacaoCadastralImovel.getLinhaCliente("rgUsuario"));
		clienteTxt.setDsUFSiglaOrgaoExpedidorRg(atualizacaoCadastralImovel.getLinhaCliente("ufRgUsuario"));
		
		String campo = atualizacaoCadastralImovel.getLinhaCliente("sexoUsuario");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setIdPessoaSexo(Integer.parseInt(campo));
		}
 
		clienteTxt.setEmail(atualizacaoCadastralImovel.getLinhaCliente("emailUsuario"));
		clienteTxt.setIdClienteRelacaoTipo(new Integer(ClienteRelacaoTipo.USUARIO));
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("matriculaImovelCliente");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setIdImovel(Integer.parseInt(campo));
		}
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("idTipoLogradouroImovel");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setIdLogradouroTipo(Integer.parseInt(campo));
		}
		
		clienteTxt.setDescricaoLogradouro(atualizacaoCadastralImovel.getLinhaImovel("logradouroImovel"));
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("idTipoLogradouroImovel");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setIdLogradouroTipo(Integer.parseInt(campo));
		}

		campo = atualizacaoCadastralImovel.getLinhaCliente("idTipoLogradouroImovel");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setDsLogradouroTipo(getDescricaoLogradouro(Integer.parseInt(campo)));
		}

		clienteTxt.setNumeroImovel(atualizacaoCadastralImovel.getLinhaImovel("numeroImovel"));
		clienteTxt.setComplementoEndereco(atualizacaoCadastralImovel.getLinhaImovel("complementoImovel"));
		clienteTxt.setNomeBairro(atualizacaoCadastralImovel.getLinhaImovel("bairro"));
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("cep");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setCodigoCep(Integer.parseInt(campo));
		}

		clienteTxt.setNomeMunicipio(atualizacaoCadastralImovel.getLinhaImovel("municipio"));
	}
	
	private String getDescricaoLogradouro(int idTipoLogradouro) {
		FiltroLogradouroTipo filtro = new FiltroLogradouroTipo();
		filtro.adicionarParametro(new ParametroSimples(FiltroLogradouroTipo.ID, idTipoLogradouro));
		LogradouroTipo logradouroTipo = (LogradouroTipo) (Fachada.getInstancia().pesquisar(filtro, LogradouroTipo.class.getName()).iterator().next());

		return logradouroTipo.getDescricao();
	}

	public IClienteAtualizacaoCadastral getClienteTxt() {
		return clienteTxt;
	}
}
