package gcom.model;

import gcom.cadastro.atualizacaocadastral.bean.CategoriaAtualizacaoCadastral;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class TestaCategoriaAtualizacaoCadastral extends TestCase {
	public void testCategoriaSemDuplicidade(){
		Set<CategoriaAtualizacaoCadastral> set = new HashSet<CategoriaAtualizacaoCadastral>();
		
		set.add(new CategoriaAtualizacaoCadastral(1, 1, 1));
		set.add(new CategoriaAtualizacaoCadastral(1, 1, 1));
		
		assertEquals(1, set.size());
	}
}
