package gcom.util;

import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

import gcom.cadastro.atualizacaocadastral.LinkedHashSetAlteracaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;

public class TesteConsultaImoveisAtulizacao {
	
	@Test
	public void testInclusaoDoisImoveis(){
		Map<Integer, ConsultarMovimentoAtualizacaoCadastralHelper> set = new LinkedHashSetAlteracaoCadastral();
		ConsultarMovimentoAtualizacaoCadastralHelper item = new ConsultarMovimentoAtualizacaoCadastralHelper();
		item.setIdImovel(10);
		item.setIdTipoAlteracao(2);
		set.put(item.getIdImovel(), item);
		
		item = new ConsultarMovimentoAtualizacaoCadastralHelper();
		item.setIdImovel(15);
		item.setIdTipoAlteracao(1);
		set.put(item.getIdImovel(), item);
		
		assertEquals(2, set.size());
	}
	
	@Test
	public void testInclusaoImovelDuplicado(){
		Map<Integer, ConsultarMovimentoAtualizacaoCadastralHelper> set = new LinkedHashSetAlteracaoCadastral();
		ConsultarMovimentoAtualizacaoCadastralHelper item = new ConsultarMovimentoAtualizacaoCadastralHelper();
		item.setIdImovel(10);
		item.setIdTipoAlteracao(2);
		set.put(item.getIdImovel(), item);
		
		item = new ConsultarMovimentoAtualizacaoCadastralHelper();
		item.setIdImovel(10);
		item.setIdTipoAlteracao(1);
		set.put(item.getIdImovel(), item);
		
		assertEquals(1, set.size());
	}
	
	@Test
	public void testInclusaoImovelDuplicadoComApenasInclusao(){
		Map<Integer, ConsultarMovimentoAtualizacaoCadastralHelper> set = new LinkedHashSetAlteracaoCadastral();
		ConsultarMovimentoAtualizacaoCadastralHelper item = new ConsultarMovimentoAtualizacaoCadastralHelper();
		item.setIdImovel(10);
		item.setIdTipoAlteracao(2);
		set.put(item.getIdImovel(), item);
		
		item = new ConsultarMovimentoAtualizacaoCadastralHelper();
		item.setIdImovel(10);
		item.setIdTipoAlteracao(2);
		set.put(item.getIdImovel(), item);
		
		assertEquals(2, set.get(10).getIdTipoAlteracao().intValue());
	}

	@Test
	public void testInclusaoImovelDuplicadoComAlteracaoEInclusaoSimples(){
		Map<Integer, ConsultarMovimentoAtualizacaoCadastralHelper> set = new LinkedHashSetAlteracaoCadastral();
		ConsultarMovimentoAtualizacaoCadastralHelper item = new ConsultarMovimentoAtualizacaoCadastralHelper();
		item.setIdImovel(10);
		item.setIdTipoAlteracao(2);
		set.put(item.getIdImovel(), item);
		
		item = new ConsultarMovimentoAtualizacaoCadastralHelper();
		item.setIdImovel(10);
		item.setIdTipoAlteracao(1);
		set.put(item.getIdImovel(), item);
		
		assertEquals(1, set.get(10).getIdTipoAlteracao().intValue());
	}
	
	@Test
	public void testInclusaoImovelDuplicadoComAlteracaoEInclusaoSimplesInvertido(){
		Map<Integer, ConsultarMovimentoAtualizacaoCadastralHelper> set = new LinkedHashSetAlteracaoCadastral();
		ConsultarMovimentoAtualizacaoCadastralHelper item = new ConsultarMovimentoAtualizacaoCadastralHelper();
		item.setIdImovel(10);
		item.setIdTipoAlteracao(1);
		set.put(item.getIdImovel(), item);
		
		item = new ConsultarMovimentoAtualizacaoCadastralHelper();
		item.setIdImovel(10);
		item.setIdTipoAlteracao(2);
		set.put(item.getIdImovel(), item);
		
		assertEquals(1, set.get(10).getIdTipoAlteracao().intValue());
	}

	@Test
	public void testInclusaoImovelDuplicadoComAlteracaoEInclusaoMaisRegistros(){
		Map<Integer, ConsultarMovimentoAtualizacaoCadastralHelper> set = new LinkedHashSetAlteracaoCadastral();
		ConsultarMovimentoAtualizacaoCadastralHelper item = new ConsultarMovimentoAtualizacaoCadastralHelper();
		item.setIdImovel(10);
		item.setIdTipoAlteracao(2);
		set.put(item.getIdImovel(), item);
		
		item = new ConsultarMovimentoAtualizacaoCadastralHelper();
		item.setIdImovel(10);
		item.setIdTipoAlteracao(1);
		set.put(item.getIdImovel(), item);
		
		item = new ConsultarMovimentoAtualizacaoCadastralHelper();
		item.setIdImovel(10);
		item.setIdTipoAlteracao(1);
		set.put(item.getIdImovel(), item);
		
		assertEquals(1, set.get(10).getIdTipoAlteracao().intValue());
	}
	
	@Test
	public void testInclusaoImovelDuplicadoComAlteracaoEInclusaoMaisRegistrosTrocado(){
		Map<Integer, ConsultarMovimentoAtualizacaoCadastralHelper> set = new LinkedHashSetAlteracaoCadastral();
		ConsultarMovimentoAtualizacaoCadastralHelper item = new ConsultarMovimentoAtualizacaoCadastralHelper();
		item.setIdImovel(10);
		item.setIdTipoAlteracao(1);
		set.put(item.getIdImovel(), item);
		
		item = new ConsultarMovimentoAtualizacaoCadastralHelper();
		item.setIdImovel(10);
		item.setIdTipoAlteracao(1);
		set.put(item.getIdImovel(), item);
		
		item = new ConsultarMovimentoAtualizacaoCadastralHelper();
		item.setIdImovel(10);
		item.setIdTipoAlteracao(2);
		set.put(item.getIdImovel(), item);
		
		assertEquals(1, set.get(10).getIdTipoAlteracao().intValue());
	}
}
