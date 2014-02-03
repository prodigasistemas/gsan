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
		
		clienteTxt.setNome(atualizacaoCadastralImovel.getLinhaCliente("nomeUsuario"));
		
		clienteTxt.setCpf(atualizacaoCadastralImovel.getLinhaCliente("cpfUsuario"));
		clienteTxt.setCnpj(atualizacaoCadastralImovel.getLinhaCliente("cnpjUsuario"));

		clienteTxt.setRg(atualizacaoCadastralImovel.getLinhaCliente("rgUsuario"));
		clienteTxt.setDsUFSiglaOrgaoExpedidorRg(atualizacaoCadastralImovel.getLinhaCliente("ufRgUsuario"));
		
		String campo = atualizacaoCadastralImovel.getLinhaCliente("sexoUsuario");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			PessoaSexo sexo = new PessoaSexo(Integer.parseInt(campo));
			clienteTxt.setPessoaSexo(sexo);
		}
 
		clienteTxt.setEmail(atualizacaoCadastralImovel.getLinhaCliente("emailUsuario"));
		clienteTxt.setIdClienteRelacaoTipo(new Integer(ClienteRelacaoTipo.USUARIO));
		clienteTxt.setIdCliente(new Integer(atualizacaoCadastralImovel.getLinhaCliente("matriculaUsuario")));

		
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
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("tipoPessoaUsuario");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setIdClienteTipo(Integer.parseInt(campo));
		}
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
