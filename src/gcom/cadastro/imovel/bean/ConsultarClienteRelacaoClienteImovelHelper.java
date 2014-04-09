package gcom.cadastro.imovel.bean;

import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.util.Util;

import java.util.Collection;

/**
 * Esta classe tem a finalidade para facilitar a visualização dos dados na tela
 * [UC0472] Consultar Imovel
 * 
 * @author Sávio Luiz
 * @date 22/08/2007
 */
public class ConsultarClienteRelacaoClienteImovelHelper {

	private ClienteImovel clienteImovel;

	private String nomeClienteUsuario;

	private String enderecoImovel;

	public ClienteImovel getClienteImovel() {
		return clienteImovel;
	}

	public void setClienteImovel(ClienteImovel clienteImovel) {
		this.clienteImovel = clienteImovel;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	public String getMatriculaFormatadaImovel() {
		if(this.clienteImovel!=null){
			return this.clienteImovel.getImovel().getMatriculaFormatada();
		}
		return "";
	}

	public String getInscricaoFormatadaImovel() {
		if(this.clienteImovel!=null){
			return this.clienteImovel.getImovel().getInscricaoFormatada();
		}
		return "";
	}

	public String getDescricaoTipoRelacao() {
		if(this.clienteImovel!=null
				&& this.clienteImovel.getClienteRelacaoTipo()!=null){
			
			return this.clienteImovel.getClienteRelacaoTipo().getDescricao();
		}
		return "";
	}

	public String getDataInicioRelacao() {
		if(this.clienteImovel!=null
				&& this.clienteImovel.getDataInicioRelacao()!=null){
			
			return Util.formatarData(this.clienteImovel.getDataInicioRelacao());
		}
		return "";
	}

	public String getDataFimRelacao(){
		if(this.clienteImovel!=null
				&& this.clienteImovel.getDataFimRelacao()!=null){
			
			return Util.formatarData(this.clienteImovel.getDataFimRelacao());
		}
		return "";
	}
	
	public String getDescricaoMotivoFimRelacao(){
		if(this.clienteImovel!=null 
				&& this.clienteImovel.getClienteImovelFimRelacaoMotivo()!=null){
			
			return this.clienteImovel.getClienteImovelFimRelacaoMotivo().getDescricao();
		}
		return "";
	}

	public Integer getIdCliente(){
		if(this.clienteImovel!=null 
				&& this.clienteImovel.getCliente()!=null){
			
			return this.clienteImovel.getCliente().getId();
		}
		
		return null;
	}

	public String getNomeCliente(){
		if(this.clienteImovel!=null 
				&& this.clienteImovel.getCliente()!=null){
			
			return this.clienteImovel.getCliente().getNome();
		}
		
		return "";
	}

	public String getTelefonePadraoCliente(){
		if(this.clienteImovel!=null 
				&& this.clienteImovel.getCliente()!=null){
			
			Collection<ClienteFone> colecaoFones = this.clienteImovel.getCliente().getClienteFones();
			if( !Util.isVazioOrNulo(colecaoFones) ){
				for(ClienteFone fone : colecaoFones){
					if(fone.getIndicadorTelefonePadrao()!=null && fone.getIndicadorTelefonePadrao() == 1){
						return fone.getDddTelefone();
					}
				}
			}
		}
		
		return "";
	}

	public String getCpfCnpjCliente(){
		if(this.clienteImovel!=null 
				&& this.clienteImovel.getCliente()!=null){
			
			if( Util.verificarNaoVazio(this.clienteImovel.getCliente().getCnpj())){
				return this.clienteImovel.getCliente().getCnpjFormatado();
			}
			
			return this.clienteImovel.getCliente().getCpfFormatado();
		}
		
		return "";
	}

}
