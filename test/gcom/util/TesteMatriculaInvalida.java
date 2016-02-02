package gcom.util;

import org.apache.commons.lang.StringUtils;

import junit.framework.TestCase;

public class TesteMatriculaInvalida extends TestCase {
	
	private boolean matriculaInvalida(String matricula){
		return StringUtils.isEmpty(matricula) || !StringUtils.isNumeric(matricula) || Integer.parseInt(matricula) <=0;
	}
	
	public void testComLetras(){
		assertTrue(new TesteMatriculaInvalida().matriculaInvalida("a2"));
	}

	public void testNegativo(){
		assertTrue(new TesteMatriculaInvalida().matriculaInvalida("-4"));
	}

	public void testZero(){
		assertTrue(new TesteMatriculaInvalida().matriculaInvalida("0"));
	}

	public void testNumeroValido(){
		assertFalse(new TesteMatriculaInvalida().matriculaInvalida("2"));
	}

	public void testVazio(){
		assertTrue(new TesteMatriculaInvalida().matriculaInvalida(""));
	}

	public void testBranco(){
		assertTrue(new TesteMatriculaInvalida().matriculaInvalida(" "));
	}
}
