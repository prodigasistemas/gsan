package gcom.util;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import static org.junit.Assert.*;

public class TesteMatriculaInvalida {
	
	private boolean matriculaInvalida(String matricula){
		return StringUtils.isEmpty(matricula) || !StringUtils.isNumeric(matricula) || Integer.parseInt(matricula) <=0;
	}
	
	@Test
	public void testComLetras(){
		assertTrue(new TesteMatriculaInvalida().matriculaInvalida("a2"));
	}

	@Test
	public void testNegativo(){
		assertTrue(new TesteMatriculaInvalida().matriculaInvalida("-4"));
	}

	@Test
	public void testZero(){
		assertTrue(new TesteMatriculaInvalida().matriculaInvalida("0"));
	}

	@Test
	public void testNumeroValido(){
		assertFalse(new TesteMatriculaInvalida().matriculaInvalida("2"));
	}

	@Test
	public void testVazio(){
		assertTrue(new TesteMatriculaInvalida().matriculaInvalida(""));
	}

	@Test
	public void testBranco(){
		assertTrue(new TesteMatriculaInvalida().matriculaInvalida(" "));
	}
}
