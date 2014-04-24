package gcom.transacao;

import gcom.cadastro.atualizacaocadastral.bean.ColunaAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionForm;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionHelper;
import gcom.seguranca.transacao.RepositorioTransacaoUtil;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TesteImpressaoConsultaAtualizacaoCadastral extends TestCase {

	private ColunaAtualizacaoCadastral colunaHidrometro = new ColunaAtualizacaoCadastral();
	private ColunaAtualizacaoCadastral colunaAgua = new ColunaAtualizacaoCadastral();
	private ColunaAtualizacaoCadastral colunaEsgoto = new ColunaAtualizacaoCadastral();
	
	protected void setUp() throws Exception {
		colunaHidrometro.setNomeColuna("imac_nnhidrometro");
		colunaAgua.setNomeColuna("last_id");
		colunaEsgoto.setNomeColuna("lest_id");
	}

	public void testImovelComFiltroAlteracaoHidrometro(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		imovel.setIdTipoAlteracao(1);
		imovel.addColunaAtualizacao(colunaHidrometro);
		
		lista.add(imovel);
				
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		filtro.setExibirCampos(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES.toString());
		filtro.setAlteracaoHidrometro(true);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtro);
		
		Assert.assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}

	public void testImovelComFiltroAlteracaoHidrometroDoisImoveis(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();

		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		imovel.setIdTipoAlteracao(1);
		imovel.addColunaAtualizacao(colunaHidrometro);
		lista.add(imovel);
		
		imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(445566);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		imovel.setIdTipoAlteracao(1);
		imovel.addColunaAtualizacao(colunaAgua);
		lista.add(imovel);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		filtro.setExibirCampos(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES.toString());
		filtro.setAlteracaoHidrometro(true);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtro);
		
		Assert.assertEquals(1984420, array.iterator().next().getIdImovel().intValue());
	}
	
	public void testDoisImoveisComFiltroAlteracaoAguaEsgoto(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		imovel.setIdTipoAlteracao(1);
		imovel.addColunaAtualizacao(colunaEsgoto);
		imovel.addColunaAtualizacao(colunaAgua);
		lista.add(imovel);
		
		imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(445566);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		imovel.setIdTipoAlteracao(1);
		imovel.addColunaAtualizacao(colunaEsgoto);
		imovel.addColunaAtualizacao(colunaAgua);
		lista.add(imovel);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		filtro.setExibirCampos(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES.toString());
		filtro.setAlteracaoSituacaoAgua(true);
		filtro.setAlteracaoSituacaoEsgoto(true);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtro);
		
		int soma = 0;
		for (ConsultarMovimentoAtualizacaoCadastralHelper item : array) {
			soma += item.getIdImovel();
		}
		
		Assert.assertEquals(1984420 + 445566, soma);
	}
	
	public void testUmImovelComFiltroAlteracaoAguaEsgotoEUmImovelComAlteracaoAgua(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		imovel.setIdTipoAlteracao(1);
		imovel.addColunaAtualizacao(colunaAgua);
		lista.add(imovel);
		
		imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(445566);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		imovel.setIdTipoAlteracao(1);
		imovel.addColunaAtualizacao(colunaEsgoto);
		imovel.addColunaAtualizacao(colunaAgua);
		lista.add(imovel);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		filtro.setExibirCampos(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES.toString());
		filtro.setAlteracaoSituacaoAgua(true);
		filtro.setAlteracaoSituacaoEsgoto(true);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtro);
		
		int soma = 0;
		for (ConsultarMovimentoAtualizacaoCadastralHelper item : array) {
			soma += item.getIdImovel();
		}
		
		Assert.assertEquals(445566, soma);
	}	
	
	public void testImovelComFiltroAlteracaoAguaDoisImoveis(){
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> lista = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		ConsultarMovimentoAtualizacaoCadastralHelper imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(1984420);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		imovel.setIdTipoAlteracao(1);
		imovel.addColunaAtualizacao(colunaHidrometro);
		lista.add(imovel);
		
		imovel = new ConsultarMovimentoAtualizacaoCadastralHelper();
		imovel.setIdImovel(445566);
		imovel.setIdLigacaoAgua(3);
		imovel.setIdLigacaoEsgoto(1);
		imovel.setIdTipoAlteracao(1);
		imovel.addColunaAtualizacao(colunaAgua);
		lista.add(imovel);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		filtro.setExibirCampos(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES.toString());
		filtro.setAlteracaoSituacaoAgua(true);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtro);
		
		int soma = 0;
		for (ConsultarMovimentoAtualizacaoCadastralHelper item : array) {
			soma += item.getIdImovel();
		}
		
		Assert.assertEquals(445566, soma);
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
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		filtro.setExibirCampos(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES.toString());		
		filtro.setAlteracaoHidrometro(false);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtro);
		
		int soma = 0;
		for (ConsultarMovimentoAtualizacaoCadastralHelper item : array) {
			soma += item.getIdImovel();
		}
		
		Assert.assertEquals(0, soma);
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
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		filtro.setExibirCampos(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES.toString());		
		filtro.setAlteracaoHidrometro(false);

		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> array = repo.imoveisFiltrados(lista, filtro);
		
		int soma = 0;
		for (ConsultarMovimentoAtualizacaoCadastralHelper item : array) {
			soma += item.getIdImovel();
		}
		
		Assert.assertEquals(1984420, soma);
	}
}
