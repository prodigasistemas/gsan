package gcom.util;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestaUtil extends TestCase {

	public void testaValidaNome01(){
		Assert.assertEquals(true, Util.nomeInvalido("João"));
	}
	
	public void testaValidaNome02(){
		Assert.assertEquals(false, Util.nomeInvalido("E S"));
	}
	
	public void testaValidaNome03(){
		Assert.assertEquals(false, Util.nomeInvalido("Pedro Henrique"));
	}
	
	public void testaCpfCnpjInvalido01(){
		Assert.assertEquals(false, Util.cpfCnpjInvalido("00065069684272"));
	}
	
	public void testaCpfCnpjInvalido02(){
		Assert.assertEquals(false, Util.cpfCnpjInvalido("   65069684272"));
	}
	
	public void testaCpfCnpjInvalido03(){
		Assert.assertEquals(false, Util.cpfCnpjInvalido("10865358000130"));
	}
	
	public void testaCpfCnpjInvalido04(){
		Assert.assertEquals(true, Util.cpfCnpjInvalido("6506968572"));
	}
	public void testaCpfCnpjInvalido05(){
		Assert.assertEquals(true, Util.cpfCnpjInvalido("10865358000134"));
	}
}
