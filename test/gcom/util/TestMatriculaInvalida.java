package gcom.util;

import org.apache.commons.lang.StringUtils;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestMatriculaInvalida extends TestCase {
	
	private boolean matriculaInvalida(String matricula){
		return StringUtils.isEmpty(matricula) || !StringUtils.isNumeric(matricula) || Integer.parseInt(matricula) <=0;
	}
	
	public void testComLetras(){
		Assert.assertTrue(new TestMatriculaInvalida().matriculaInvalida("a2"));
	}

	public void testNegativo(){
		Assert.assertTrue(new TestMatriculaInvalida().matriculaInvalida("-4"));
	}

	public void testZero(){
		Assert.assertTrue(new TestMatriculaInvalida().matriculaInvalida("0"));
	}

	public void testNumeroValido(){
		Assert.assertFalse(new TestMatriculaInvalida().matriculaInvalida("2"));
	}

	public void testVazio(){
		Assert.assertTrue(new TestMatriculaInvalida().matriculaInvalida(""));
	}

	public void testBranco(){
		Assert.assertTrue(new TestMatriculaInvalida().matriculaInvalida(" "));
	}
}
