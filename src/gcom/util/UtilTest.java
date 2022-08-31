package gcom.util;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class UtilTest {

	@Test
	public void cpfOuCnpjDeveSerValido() {
		
		String cpf = "04945341000190";
		boolean cpfCnpjInvalido = Util.cpfCnpjInvalido(cpf);
		
		Assert.assertFalse(cpfCnpjInvalido);
	}
	
	@Test
	public void cpfOuCnpjNaoDeveSerValido() {
		
		String cpf = "01762878258";
		boolean cpfCnpjInvalido = Util.cpfCnpjInvalido(cpf);
		Assert.assertTrue(cpfCnpjInvalido);
	}

}
