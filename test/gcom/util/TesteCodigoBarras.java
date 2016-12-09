package gcom.util;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TesteCodigoBarras {
	
	String codigoBanco = "001";
	String codigoMoeda = "9";
	String nossoNumeroSemDV = "25027920743237535";
	String carteira         = "18";
	String fatorVencimento  = "";
	String pos_20_24        = "00000";
	String pos_25_34        = "0250279207";
	String pos_35_44        = "4323753518";
	String dv_campo_01      = "7";
	String dv_campo_02      = "0";
	String dv_campo_03      = "1";
	String dv_codigoBarras  = "1";
	String valorBoleto      = "";
	
	BigDecimal valorCodigoBarra = new BigDecimal("4.16");
	
	Date data_1997_10_10 = null;
	Date data_2016_12_10 = null;
	
	
	@Before
	public void init(){
		Calendar cal = Calendar.getInstance();
		cal.set(1997, 9, 10);
		data_1997_10_10 = cal.getTime();

		cal.set(2016, 11, 10);
		data_2016_12_10 = cal.getTime();
		
		valorBoleto = Util.adicionarZerosEsquedaNumero(10, valorCodigoBarra.setScale(2).toString().replace(".", ""));				
	}
	
	@Test
	public void testaCodigoBarrasFichaCompensacao(){
		fatorVencimento = CodigoBarras.obterFatorVencimento(data_2016_12_10);
		
		StringBuilder valor = new StringBuilder();
		valor.append(codigoBanco)
		.append(codigoMoeda)
		.append(dv_codigoBarras)
		.append(fatorVencimento)
		.append(valorBoleto)
		.append(pos_20_24)
		.append(pos_25_34)
		.append(pos_35_44);
//		.append("000000")
//		.append(nossoNumeroSemDV)
//		.append(carteira);
				
		assertEquals(44, valor.toString().length());
		
		String codigo = CodigoBarras.obterEspecificacaoCodigoBarraFichaCompensacao(codigoBanco, codigoMoeda, valorCodigoBarra, nossoNumeroSemDV, carteira, fatorVencimento);
		
		assertEquals(valor.toString(), codigo);
	}
	
	@Test
	public void testeObterFatorVencimento(){
		assertEquals("3", CodigoBarras.obterFatorVencimento(data_1997_10_10));
		assertEquals("7004", CodigoBarras.obterFatorVencimento(data_2016_12_10));		
	}
}
