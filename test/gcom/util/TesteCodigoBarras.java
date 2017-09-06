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
	String pos_20_24_number = "0.0000";
	String pos_25_34        = "0250279207";
	String pos_25_34_number = "02502.79207";
	String pos_35_44        = "4323753518";
	String pos_35_44_number = "43237.53518";
	String dv_campo_01      = "9";
	String dv_campo_02      = "6";
	String dv_campo_03      = "2";
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
		String valor = buildCodigoBarras();
				
		assertEquals(44, valor.toString().length());
		
		String codigo = CodigoBarras.obterEspecificacaoCodigoBarraFichaCompensacao(codigoBanco, codigoMoeda, valorCodigoBarra, nossoNumeroSemDV, carteira, fatorVencimento);
		
		assertEquals(valor.toString(), codigo);
	}
	
	@Test
	public void testRepresentacaoNumericaCodigoBarras(){
		String codigoBarras  = buildCodigoBarras();
		String representacao = buildRepresentacaoNumericaCodigoBarras(); 
		
		assertEquals(representacao, CodigoBarras.obterRepresentacaoNumericaCodigoBarraFichaCompensacao(codigoBarras));
	}
	
	@Test
	public void testeObterFatorVencimento(){
		assertEquals("3", CodigoBarras.obterFatorVencimento(data_1997_10_10));
		assertEquals("7004", CodigoBarras.obterFatorVencimento(data_2016_12_10));		
	}
	
	private String buildCodigoBarras(){
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
		
		return valor.toString();
	}
	
	private String buildRepresentacaoNumericaCodigoBarras(){
		fatorVencimento = CodigoBarras.obterFatorVencimento(data_2016_12_10);
		
		StringBuilder valor = new StringBuilder();
		valor.append(codigoBanco)
		.append(codigoMoeda)
		.append(pos_20_24_number)
		.append(dv_campo_01)
		.append(" ")
		.append(pos_25_34_number)
		.append(dv_campo_02)
		.append(" ")
		.append(pos_35_44_number)
		.append(dv_campo_03)
		.append(" ")
		.append(dv_codigoBarras)
		.append(" ")
		.append(fatorVencimento)
		.append(valorBoleto);
		
		return valor.toString();
	}
	
}