package gcom.model;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;

import gcom.cadastro.atualizacaocadastral.bean.CategoriaAtualizacaoCadastral;

public class TesteCategoriaAtualizacaoCadastral {
	
	@Test
	public void testCategoriaSemDuplicidade(){
		Set<CategoriaAtualizacaoCadastral> set = new HashSet<CategoriaAtualizacaoCadastral>();
		
		set.add(new CategoriaAtualizacaoCadastral(1, 1, 1));
		set.add(new CategoriaAtualizacaoCadastral(1, 1, 1));
		
		assertEquals(1, set.size());
	}
}
