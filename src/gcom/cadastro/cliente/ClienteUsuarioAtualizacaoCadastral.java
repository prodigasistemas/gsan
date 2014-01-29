package gcom.cadastro.cliente;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.endereco.FiltroLogradouroTipo;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.fachada.Fachada;
import gcom.util.filtro.ParametroSimples;

public class ClienteUsuarioAtualizacaoCadastral extends ClienteAtualizacaoCadastral{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3501536744175623822L;
	
	private AtualizacaoCadastralImovel atualizacaoCadastralImovel;
	
	public ClienteUsuarioAtualizacaoCadastral(AtualizacaoCadastralImovel atualizacaoCadastralImovel){
		this.atualizacaoCadastralImovel = atualizacaoCadastralImovel;
		
		buildCliente();
	}
	
	@Override
	public void buildCliente() {
		this.setNome(atualizacaoCadastralImovel.getLinhaCliente("nomeUsuario"));
		this.setCpf(atualizacaoCadastralImovel.getLinhaCliente("cnpjCpfUsuario"));
		this.setRg(atualizacaoCadastralImovel.getLinhaCliente("rgUsuario"));
		this.setDsUFSiglaOrgaoExpedidorRg(atualizacaoCadastralImovel.getLinhaCliente("ufRgUsuario"));
		this.setIdPessoaSexo(atualizacaoCadastralImovel.getLinhaCliente("sexoUsuario").equals("0") ? null : Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("sexoUsuario")));
		this.setEmail(atualizacaoCadastralImovel.getLinhaCliente("emailUsuario"));
		this.setIdClienteRelacaoTipo(new Integer(ClienteRelacaoTipo.USUARIO));
		this.setIdImovel(Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("matriculaImovelCliente")));
		this.setIdLogradouroTipo(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("idTipoLogradouroImovel")));
		this.setDescricaoLogradouro(atualizacaoCadastralImovel.getLinhaImovel("logradouroImovel"));
		this.setDsLogradouroTipo(getDescricaoLogradouro(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("idTipoLogradouroImovel"))));
		this.setNumeroImovel(atualizacaoCadastralImovel.getLinhaImovel("numeroImovel"));
		this.setComplementoEndereco(atualizacaoCadastralImovel.getLinhaImovel("complementoImovel"));
		this.setNomeBairro(atualizacaoCadastralImovel.getLinhaImovel("bairro"));
		this.setCodigoCep(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("cep")));
		this.setNomeMunicipio(atualizacaoCadastralImovel.getLinhaImovel("municipio"));
		this.setIdClienteTipo(Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("tipoPessoaUsuario")));
	}
	
	public String getDescricaoLogradouro(int idTipoLogradouro) {
		FiltroLogradouroTipo filtro = new FiltroLogradouroTipo();
		filtro.adicionarParametro(new ParametroSimples(FiltroLogradouroTipo.ID, idTipoLogradouro));
		LogradouroTipo logradouroTipo = (LogradouroTipo) (Fachada.getInstancia().pesquisar(filtro, LogradouroTipo.class.getName()).iterator().next());

		return logradouroTipo.getDescricao();
	}

}
