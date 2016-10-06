package gcom.cadastro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;

public class TesteImovelEnvioConta {
	
	private Imovel imovel;
	
	@Before
	public void setUp() {
		imovel = new Imovel();
	}

	@Test
	public void enviarParaImovel() {
		imovel.definirEnvioContaQuandoNaoHaResponsavel();
		
		assertEquals((short) 2 , imovel.getIndicadorConta().shortValue());
		assertEquals((short) 2 , imovel.getIndicadorEmissaoExtratoFaturamento().shortValue());
		assertEquals(ImovelContaEnvio.ENVIAR_IMOVEL, imovel.getImovelContaEnvio().getId());
	}
	
	@Test
	public void enviarParaEmail() {
		ImovelContaEnvio email = new ImovelContaEnvio();
		email.setId(ImovelContaEnvio.ENVIAR_PARA_EMAIL);
		imovel.setImovelContaEnvio(email);
		imovel.definirEnvioContaQuandoNaoHaResponsavel();
		
		assertEquals((short) 2 , imovel.getIndicadorConta().shortValue());
		assertEquals((short) 2 , imovel.getIndicadorEmissaoExtratoFaturamento().shortValue());
		assertFalse(imovel.getImovelContaEnvio().getId().intValue() == ImovelContaEnvio.ENVIAR_IMOVEL.intValue());
	}	
}
