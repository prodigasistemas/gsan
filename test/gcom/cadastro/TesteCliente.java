package gcom.cadastro;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;

public class TesteCliente {

	private Cliente cliente;
	
	@Before
	public void setup() {
		cliente = new Cliente();
	}
	
	@Test
	public void testCpfFormatado() {
		cliente.setCpf("62444617622");
		
		assertEquals("624.446.176-22", cliente.getCpfFormatado());
	}
	
	@Test
	public void testCpfInvalidoFormatado() {
		cliente.setCpf("624446176");
		
		assertEquals("624446176", cliente.getCpfFormatado());
	}
	
	@Test
	public void testCnpjFormatado() {
		cliente.setCnpj("15346185000168");
		
		assertEquals("15.346.185/0001-68", cliente.getCnpjFormatado());
	}
	
	@Test
	public void testCnpjInvalidoFormatado() {
		cliente.setCnpj("153461850001");
		
		assertEquals("00.153.461/8500-01", cliente.getCnpjFormatado());
	}
	
	@Test
	public void testClienteFederal() {
		ClienteTipo clienteFederal = new ClienteTipo();
		
		EsferaPoder esfera = new EsferaPoder("FEDERAL", EsferaPoder.FEDERAL, null, null, new Date());
		esfera.setId(EsferaPoder.FEDERAL.intValue());
		clienteFederal.setEsferaPoder(esfera);
		
		cliente.setClienteTipo(clienteFederal);
		
		assertTrue(cliente.isClienteFederal());
	}
	
	@Test
	public void testClienteNaoFederal() {
		ClienteTipo clienteMunicipal = new ClienteTipo();
		
		EsferaPoder esfera = new EsferaPoder("MUNICIPAL", EsferaPoder.MUNICIPAL, null, null, new Date());
		esfera.setId(EsferaPoder.MUNICIPAL.intValue());
		clienteMunicipal.setEsferaPoder(esfera);
		
		cliente.setClienteTipo(clienteMunicipal);
		
		assertFalse(cliente.isClienteFederal());
	}
	
	@Test
	public void testCodigoNome() {
		cliente.setId(1);
		cliente.setNome("Foo");
		
		assertEquals("1 - Foo", cliente.getCodigoNome());
	}
	
	@Test
	public void testCodigoNomeSemId() {
		cliente.setNome("Foo");
		
		assertEquals("null - Foo", cliente.getCodigoNome());
	}

}
