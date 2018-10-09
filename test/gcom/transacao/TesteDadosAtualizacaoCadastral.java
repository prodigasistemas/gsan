package gcom.transacao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import gcom.cadastro.atualizacaocadastral.bean.CategoriaAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ColunaAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionForm;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionHelper;
import gcom.seguranca.transacao.RepositorioTransacaoUtil;

public class TesteDadosAtualizacaoCadastral {

	@Test
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
		filtroHelper.setSituacaoImoveis(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES);
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	@Test
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
		filtroHelper.setSituacaoImoveis(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES);
		filtroHelper.setAlteracaoHidrometro(false);

		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	@Test
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
		filtroHelper.setSituacaoImoveis(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES);
		filtroHelper.setAlteracaoHidrometro(true);

		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	@Test
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
		filtroHelper.setSituacaoImoveis(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES);
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	@Test
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
		filtroHelper.setSituacaoImoveis(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES);
		filtroHelper.setAlteracaoSituacaoAgua(false);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	@Test
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
		filtroHelper.setSituacaoImoveis(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES);
		filtroHelper.setAlteracaoSituacaoAgua(true);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}	

	@Test
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
		filtroHelper.setSituacaoImoveis(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES);
		filtroHelper.setAlteracaoSituacaoAgua(true);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		assertEquals(0, array.size());
	}	
	
	@Test
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
		filtroHelper.setSituacaoImoveis(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES);
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	@Test
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
		filtroHelper.setSituacaoImoveis(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES);
		filtroHelper.setAlteracaoSituacaoEsgoto(false);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	@Test
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
		filtroHelper.setSituacaoImoveis(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES);
		filtroHelper.setAlteracaoSituacaoEsgoto(true);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	@Test
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
		filtroHelper.setSituacaoImoveis(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES);
		filtroHelper.setAlteracaoCategoria(null);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	@Test
	public void testImovelSemAlteracaoCategoria(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdTipoAlteracao(1);

		lista.add(imovel);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtroHelper = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		filtroHelper.setSituacaoImoveis(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES);
		filtroHelper.setAlteracaoCategoria(false);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	@Test
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
		filtroHelper.setSituacaoImoveis(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES);
		filtroHelper.setAlteracaoCategoria(true);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	@Test
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
		filtroHelper.setSituacaoImoveis(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES);
		filtroHelper.setAlteracaoCategoria(false);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		assertEquals(0, array.size());
	}
	
	@Test
	public void testImovelComAlteracaoCategoriaSemResultados(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdTipoAlteracao(1);

		lista.add(imovel);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtroHelper = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		filtroHelper.setSituacaoImoveis(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES);
		filtroHelper.setAlteracaoCategoria(true);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtroHelper);
		
		assertEquals(0, array.size());
	}	
}
