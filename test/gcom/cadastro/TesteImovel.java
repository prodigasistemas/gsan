package gcom.cadastro;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.imovel.Imovel;
import junit.framework.TestCase;

public class TesteImovel extends TestCase {
	
	private Imovel imovel;
	
	private Cliente clienteProprietario;
	private Cliente clienteResponsavel;
	private Cliente clienteUsuario;
	
	private ClienteRelacaoTipo proprietario;
	private ClienteRelacaoTipo responsavel;
	private ClienteRelacaoTipo usuario;
	
	public void setUp() {
		imovel = new Imovel();
		
		clienteProprietario = new Cliente();
		clienteProprietario.setNome("Foo Proprietario");
		
		clienteResponsavel = new Cliente();
		clienteResponsavel.setNome("Foo Responsavel");
		
		clienteUsuario = new Cliente();
		clienteUsuario.setNome("Foo Usuario");
		
		proprietario = new ClienteRelacaoTipo("Proprietario", ClienteRelacaoTipo.PROPRIETARIO, new Date());
		proprietario.setId(ClienteRelacaoTipo.PROPRIETARIO.intValue());
		
		responsavel = new ClienteRelacaoTipo("Responsavel", ClienteRelacaoTipo.RESPONSAVEL, new Date());
		responsavel.setId(ClienteRelacaoTipo.RESPONSAVEL.intValue());
		
		usuario = new ClienteRelacaoTipo("Usuario", ClienteRelacaoTipo.USUARIO, new Date());
		usuario.setId(ClienteRelacaoTipo.USUARIO.intValue());
	}

	public void testGetClienteUsuario() {
		Set<ClienteImovel> clienteImoveis = new HashSet<ClienteImovel>();
		ClienteImovel clienteImovel1 = new ClienteImovel(new Date(), imovel, null, clienteProprietario, proprietario);
		ClienteImovel clienteImovel2 = new ClienteImovel(new Date(), imovel, null, clienteResponsavel, responsavel);
		ClienteImovel clienteImovel3 = new ClienteImovel(new Date(), imovel, null, clienteUsuario, usuario);
		
		clienteImoveis.add(clienteImovel1);
		clienteImoveis.add(clienteImovel2);
		clienteImoveis.add(clienteImovel3);
		
		imovel.setClienteImoveis(clienteImoveis);
		
		assertEquals(clienteImovel3.getCliente().getNome(), imovel.getClienteUsuario().getNome());
	}
	
	public void testGetClienteUsuarioSemUsuario() {
		Set<ClienteImovel> clienteImoveis = new HashSet<ClienteImovel>();
		ClienteImovel clienteImovel1 = new ClienteImovel(new Date(), imovel, null, clienteProprietario, proprietario);
		ClienteImovel clienteImovel2 = new ClienteImovel(new Date(), imovel, null, clienteResponsavel, responsavel);
		
		clienteImoveis.add(clienteImovel1);
		clienteImoveis.add(clienteImovel2);
		
		imovel.setClienteImoveis(clienteImoveis);
		
		assertEquals(null, imovel.getClienteUsuario());
	}
}
