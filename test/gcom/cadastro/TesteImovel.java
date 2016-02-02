package gcom.cadastro;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.Imovel;

public class TesteImovel {
	
	private Imovel imovel;
	
	private Cliente clienteProprietario;
	private Cliente clienteResponsavel;
	private Cliente clienteUsuario;
	
	private ClienteRelacaoTipo proprietario;
	private ClienteRelacaoTipo responsavel;
	private ClienteRelacaoTipo usuario;
	
	@Before
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

	@Test
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
	
	@Test
	public void testGetClienteUsuarioSemUsuario() {
		Set<ClienteImovel> clienteImoveis = new HashSet<ClienteImovel>();
		ClienteImovel clienteImovel1 = new ClienteImovel(new Date(), imovel, null, clienteProprietario, proprietario);
		ClienteImovel clienteImovel2 = new ClienteImovel(new Date(), imovel, null, clienteResponsavel, responsavel);
		
		clienteImoveis.add(clienteImovel1);
		clienteImoveis.add(clienteImovel2);
		
		imovel.setClienteImoveis(clienteImoveis);
		
		assertEquals(null, imovel.getClienteUsuario());
	}
	
	@Test
	public void testEnderecoFormatado() {
		Imovel imovel = buildImovelComEnderecoCompleto();
		
		assertEquals("Avenida Almirante Barroso - em frente ao banco do brasil - 123 - "
					+ "apto 1111 - Marco PA 66000-000 "
					+ "ENTRE Travessa Mauriti E Travessa Barão do Triunfo", imovel.getEnderecoFormatado());
	}
	
	@Test
	public void testEnderecoFormatadoAbreviado() {
		Imovel imovel = buildImovelComEnderecoAbreviado();
		
		assertEquals("Av Alm Barroso, em frente ao BB 123 - "
				+ "apto 1111 - Marco PA 66000-000 "
				+ "ENTRE Travessa Mauriti E Travessa Barão do Triunfo", imovel.getEnderecoFormatadoAbreviado());
	}
	
	@Test
	public void testEnderecoTipoTituloLogradouro() {
		
	}
	
	@Test
	public void testInscricaoFormatada() {
		
	}
	
	@Test
	public void testInscricaoFormatadaSemPonto() {
		
	}

	private Imovel buildImovelComEnderecoCompleto() {
		LogradouroTitulo logradouroTitulo = mock(LogradouroTitulo.class);
		when(logradouroTitulo.getDescricao()).thenReturn("Almirante");
		
		LogradouroTipo logradouroTipo = mock(LogradouroTipo.class);
		when(logradouroTipo.getDescricao()).thenReturn("Avenida");
		
		Logradouro logradouro = mock(Logradouro.class);
		when(logradouro.getId()).thenReturn(1);
		when(logradouro.getLogradouroTipo()).thenReturn(logradouroTipo);
		when(logradouro.getLogradouroTitulo()).thenReturn(logradouroTitulo);
		when(logradouro.getNome()).thenReturn("Barroso");
		
		Cep cep = mock(Cep.class);
		when(cep.getCepFormatado()).thenReturn("66000-000");
		
		LogradouroCep logradouroCep = mock(LogradouroCep.class);
		when(logradouroCep.getLogradouro()).thenReturn(logradouro);
		when(logradouroCep.getCep()).thenReturn(cep);
		
		EnderecoReferencia enderecoReferencia = mock(EnderecoReferencia.class);
		when(enderecoReferencia.getDescricao()).thenReturn("em frente ao banco do brasil");
		
		UnidadeFederacao unidadeFederacao = mock(UnidadeFederacao.class);
		when(unidadeFederacao.getId()).thenReturn(1);
		when(unidadeFederacao.getSigla()).thenReturn("PA");
		
		Municipio municipio = mock(Municipio.class);
		when(municipio.getNome()).thenReturn("Belém");
		when(municipio.getUnidadeFederacao()).thenReturn(unidadeFederacao);
		
		Bairro bairro = mock(Bairro.class);
		when(bairro.getId()).thenReturn(1);
		when(bairro.getNome()).thenReturn("Marco");
		when(bairro.getMunicipio()).thenReturn(municipio);
		
		LogradouroBairro logradouroBairro = mock(LogradouroBairro.class);
		when(logradouroBairro.getBairro()).thenReturn(bairro);
		
		Logradouro perimetroInicial = mock(Logradouro.class);
		when(perimetroInicial.getDescricaoFormatada()).thenReturn("Travessa Mauriti");
		
		Logradouro perimetroFinal = mock(Logradouro.class);
		when(perimetroFinal.getDescricaoFormatada()).thenReturn("Travessa Barão do Triunfo");
		
		imovel.setLogradouroCep(logradouroCep);
		imovel.setLogradouroBairro(logradouroBairro);
		imovel.setEnderecoReferencia(enderecoReferencia);
		imovel.setNumeroImovel("123");
		imovel.setComplementoEndereco("apto 1111");
		imovel.setPerimetroInicial(perimetroInicial);
		imovel.setPerimetroFinal(perimetroFinal);
		
		return imovel;
	}
	
	private Imovel buildImovelComEnderecoAbreviado() {
		LogradouroTitulo logradouroTitulo = mock(LogradouroTitulo.class);
		when(logradouroTitulo.getDescricaoAbreviada()).thenReturn("Alm");
		
		LogradouroTipo logradouroTipo = mock(LogradouroTipo.class);
		when(logradouroTipo.getDescricaoAbreviada()).thenReturn("Av");
		
		Logradouro logradouro = mock(Logradouro.class);
		when(logradouro.getId()).thenReturn(1);
		when(logradouro.getLogradouroTipo()).thenReturn(logradouroTipo);
		when(logradouro.getLogradouroTitulo()).thenReturn(logradouroTitulo);
		when(logradouro.getNome()).thenReturn("Barroso");
		
		Cep cep = mock(Cep.class);
		when(cep.getCepFormatado()).thenReturn("66000-000");
		
		LogradouroCep logradouroCep = mock(LogradouroCep.class);
		when(logradouroCep.getLogradouro()).thenReturn(logradouro);
		when(logradouroCep.getCep()).thenReturn(cep);
		
		EnderecoReferencia enderecoReferencia = mock(EnderecoReferencia.class);
		when(enderecoReferencia.getDescricaoAbreviada()).thenReturn("em frente ao BB");
		
		UnidadeFederacao unidadeFederacao = mock(UnidadeFederacao.class);
		when(unidadeFederacao.getId()).thenReturn(1);
		when(unidadeFederacao.getSigla()).thenReturn("PA");
		
		Municipio municipio = mock(Municipio.class);
		when(municipio.getNome()).thenReturn("Belém");
		when(municipio.getUnidadeFederacao()).thenReturn(unidadeFederacao);
		
		Bairro bairro = mock(Bairro.class);
		when(bairro.getId()).thenReturn(1);
		when(bairro.getNome()).thenReturn("Marco");
		when(bairro.getMunicipio()).thenReturn(municipio);
		
		LogradouroBairro logradouroBairro = mock(LogradouroBairro.class);
		when(logradouroBairro.getBairro()).thenReturn(bairro);
		
		Logradouro perimetroInicial = mock(Logradouro.class);
		when(perimetroInicial.getDescricaoFormatada()).thenReturn("Travessa Mauriti");
		
		Logradouro perimetroFinal = mock(Logradouro.class);
		when(perimetroFinal.getDescricaoFormatada()).thenReturn("Travessa Barão do Triunfo");
		
		imovel.setLogradouroCep(logradouroCep);
		imovel.setLogradouroBairro(logradouroBairro);
		imovel.setEnderecoReferencia(enderecoReferencia);
		imovel.setNumeroImovel("123");
		imovel.setComplementoEndereco("apto 1111");
		imovel.setPerimetroInicial(perimetroInicial);
		imovel.setPerimetroFinal(perimetroFinal);
		
		return imovel;
	}
}
