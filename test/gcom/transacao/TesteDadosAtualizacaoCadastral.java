package gcom.transacao;

import gcom.cadastro.atualizacaocadastral.bean.CategoriaAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ColunaAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionHelper;
import gcom.seguranca.transacao.RepositorioTransacaoUtil;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TesteDadosAtualizacaoCadastral extends TestCase {

	
	public void testImovelSemFiltroAlteracaoHidrometro(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		imovel.setIdTipoAlteracao(1);
		
		lista.add(imovel);
				
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtroHelper = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		Assert.assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	public void testImovelSemAlteracaoHidrometroSemResultados(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setNumeroHidrometro("1212121");
		imovel.setIdTipoAlteracao(1);
		imovel.setIdImovel(1984420);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		
		lista.add(imovel);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtroHelper = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		filtroHelper.setAlteracaoSituacaoImovel(false);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		Assert.assertEquals(0, array.size());
	}
	
	public void testImovelSemAlteracaoHidrometro(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();

		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		imovel.setIdTipoAlteracao(1);

		lista.add(imovel);
		
		ColunaAtualizacaoCadastral coluna = new ColunaAtualizacaoCadastral();
		coluna.setNomeColuna("umaqualquer");
		imovel.addColunaAtualizacao(coluna);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtroHelper = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		filtroHelper.setAlteracaoSituacaoImovel(false);

		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		Assert.assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	public void testImovelComAlteracaoHidrometro(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();

		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		imovel.setIdTipoAlteracao(1);

		ColunaAtualizacaoCadastral coluna = new ColunaAtualizacaoCadastral();
		coluna.setNomeColuna("imac_nnhidrometro");
		imovel.addColunaAtualizacao(coluna);

		lista.add(imovel);

		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtroHelper = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		filtroHelper.setAlteracaoSituacaoImovel(true);

		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		Assert.assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	public void testImovelSemFiltroAlteracaoSituacaoAgua(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		imovel.setIdTipoAlteracao(1);

		lista.add(imovel);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtroHelper = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		Assert.assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	public void testImovelSemAlteracaoSituacaoAgua(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();

		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();

		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		imovel.setIdTipoAlteracao(1);

		lista.add(imovel);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtroHelper = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		
		filtroHelper.setAlteracaoSituacaoAgua(false);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		Assert.assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	public void testImovelComAlteracaoSituacaoAgua(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		imovel.setIdTipoAlteracao(1);

		ColunaAtualizacaoCadastral coluna = new ColunaAtualizacaoCadastral();
		coluna.setNomeColuna("last_id");
		imovel.addColunaAtualizacao(coluna);
		
		lista.add(imovel);

		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtroHelper = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		
		filtroHelper.setAlteracaoSituacaoAgua(true);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		Assert.assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}	

	public void testImovelComAlteracaoSituacaoAguaSemResultados(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		imovel.setIdTipoAlteracao(1);

		lista.add(imovel);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtroHelper = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		
		filtroHelper.setAlteracaoSituacaoAgua(true);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		Assert.assertEquals(0, array.size());
	}	
	
	public void testImovelSemFiltroAlteracaoSituacaoEsgoto(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		imovel.setIdTipoAlteracao(1);

		lista.add(imovel);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtroHelper = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		Assert.assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	public void testImovelSemAlteracaoSituacaoEsgoto(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		imovel.setIdTipoAlteracao(1);

		lista.add(imovel);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtroHelper = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		
		filtroHelper.setAlteracaoSituacaoEsgoto(false);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		Assert.assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	public void testImovelComAlteracaoSituacaoEsgoto(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		imovel.setIdTipoAlteracao(1);

		ColunaAtualizacaoCadastral coluna = new ColunaAtualizacaoCadastral();
		coluna.setNomeColuna("lest_id");
		imovel.addColunaAtualizacao(coluna);
		
		lista.add(imovel);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtroHelper = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		
		filtroHelper.setAlteracaoSituacaoEsgoto(true);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		Assert.assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	public void testImovelSemFiltroAlteracaoCategoria(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdTipoAlteracao(1);

		CategoriaAtualizacaoCadastral categoria = new CategoriaAtualizacaoCadastral(1, 1, 1);
		imovel.addCategoria(categoria);
		
		lista.add(imovel);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtroHelper = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		filtroHelper.setAlteracaoCategoria(null);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		Assert.assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	public void testImovelSemAlteracaoCategoria(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdTipoAlteracao(1);

		lista.add(imovel);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtroHelper = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		filtroHelper.setAlteracaoCategoria(false);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		Assert.assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	public void testImovelComAlteracaoCategoria(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdTipoAlteracao(1);

		ColunaAtualizacaoCadastral coluna = new ColunaAtualizacaoCadastral();
		coluna.setNomeColuna("isac_qteconomia");
		imovel.addColunaAtualizacao(coluna);
		
		lista.add(imovel);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtroHelper = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		
		filtroHelper.setAlteracaoCategoria(true);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		Assert.assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	public void testImovelSemAlteracaoCategoriaSemResultados(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdTipoAlteracao(1);

		ColunaAtualizacaoCadastral coluna = new ColunaAtualizacaoCadastral();
		coluna.setNomeColuna("isac_qteconomia");
		imovel.addColunaAtualizacao(coluna);
		
		lista.add(imovel);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtroHelper = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		
		filtroHelper.setAlteracaoCategoria(false);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		Assert.assertEquals(0, array.size());
	}
	
	public void testImovelComAlteracaoCategoriaSemResultados(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdTipoAlteracao(1);

		lista.add(imovel);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtroHelper = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		
		filtroHelper.setAlteracaoCategoria(true);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		Assert.assertEquals(0, array.size());
	}	
}
