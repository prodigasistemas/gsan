package gcom.cadastro.cliente;

import org.apache.commons.lang.StringUtils;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.endereco.FiltroLogradouroTipo;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.fachada.Fachada;
import gcom.util.filtro.ParametroSimples;

public class ClienteUsuarioBuilder extends ClienteBuilder {
	public ClienteUsuarioBuilder(AtualizacaoCadastralImovel atualizacaoCadastralImovel){
		super(atualizacaoCadastralImovel);
	}
	
	public IClienteAtualizacaoCadastral buildCliente(Short clienteRelacaoTipo) {
		String campo;
		
		buildCliente(USUARIO, clienteRelacaoTipo);
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("matriculaImovelCliente");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setIdImovel(Integer.parseInt(campo));
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
		
		return clienteTxt;
	}
	
	private String getDescricaoLogradouro(int idTipoLogradouro) {
		FiltroLogradouroTipo filtro = new FiltroLogradouroTipo();
		filtro.adicionarParametro(new ParametroSimples(FiltroLogradouroTipo.ID, idTipoLogradouro));
		LogradouroTipo logradouroTipo = (LogradouroTipo) (Fachada.getInstancia().pesquisar(filtro, LogradouroTipo.class.getName()).iterator().next());

		return logradouroTipo.getDescricao();
	}
}
