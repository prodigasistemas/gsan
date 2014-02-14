package gcom.util;

import gcom.cadastro.atualizacaocadastral.LinkedHashSetAlteracaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestaConsultaImoveisAtulizacao extends TestCase {
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
		
		Assert.assertEquals(2, set.size());
	}
	
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
		
		Assert.assertEquals(1, set.size());
	}
	
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
		
		Assert.assertEquals(2, set.get(10).getIdTipoAlteracao().intValue());
	}

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
		
		Assert.assertEquals(1, set.get(10).getIdTipoAlteracao().intValue());
	}
	
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
		
		Assert.assertEquals(1, set.get(10).getIdTipoAlteracao().intValue());
	}

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
		
		Assert.assertEquals(1, set.get(10).getIdTipoAlteracao().intValue());
	}
	
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
		
		Assert.assertEquals(1, set.get(10).getIdTipoAlteracao().intValue());
	}
}
