package gcom.faturamento.controladores;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalHome;
import javax.ejb.EJBLocalObject;
import javax.ejb.RemoveException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import gcom.arrecadacao.repositorio.RepositorioDevolucao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.Imovel;
import gcom.enums.Status;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.repositorio.RepositorioCreditoARealizar;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

public class ControladorAnaliseGeracaoContaTest {

    @InjectMocks
	private ControladorAnaliseGeracaoConta analisadorGeracaoConta;
	
	private Imovel imovel;
	private LigacaoAguaSituacao aguaLigada;
	private LigacaoAguaSituacao aguaDesligada;
	private LigacaoEsgotoSituacao esgotoLigado;
	private LigacaoEsgotoSituacao esgotoDesligado;
	private int anoMesFaturamento;
	
	@Mock
	private RepositorioCreditoARealizar creditoRealizarRepositorio;
	
	@Mock
	private RepositorioDevolucao repositorioDevolucao;
		
	private boolean aguaEsgotoZerados;
	
	private Collection<DebitoACobrar> debitos;
	
	@Before
	public void setup(){
        analisadorGeracaoConta = new ControladorAnaliseGeracaoConta(){
            private static final long serialVersionUID = 6767710520463134199L;

            protected ControladorDebitoACobrarLocal getControladorDebitoACobrar(){
                return new ControladorDebitoACobrarLocal() {
                    
                    public void remove() throws RemoveException, EJBException {
                        
                    }
                    
                    public boolean isIdentical(EJBLocalObject arg0) throws EJBException {
                        return false;
                    }
                    
                    public Object getPrimaryKey() throws EJBException {
                        return null;
                    }
                    
                    public EJBLocalHome getEJBLocalHome() throws EJBException {
                        return null;
                    }
                    
                    public Collection<DebitoACobrar> debitosCobrarVigentes(Integer idImovel) throws ControladorException {
                        return null;
                    }
                    
                    public Collection<DebitoACobrar> debitosCobrarSemPagamentos(Integer idImovel) throws ControladorException {
                        return getDebitos();
                    }
                    
                    public void atualizarDebitoCobrar(List<DebitoACobrar> debitosCobrar) throws ErroRepositorioException {
                    }
                };
            }           
        };
		
		anoMesFaturamento = 0;

		imovel = new Imovel();
		aguaLigada = new LigacaoAguaSituacao();
		aguaLigada.setId(LigacaoAguaSituacao.LIGADO);
		imovel.setLigacaoAguaSituacao(aguaLigada);
		aguaDesligada = new LigacaoAguaSituacao();
		aguaDesligada.setId(LigacaoAguaSituacao.POTENCIAL);
		
		esgotoLigado = new LigacaoEsgotoSituacao();
		esgotoLigado.setId(LigacaoEsgotoSituacao.LIGADO);
		imovel.setLigacaoEsgotoSituacao(esgotoLigado);
		esgotoDesligado = new LigacaoEsgotoSituacao();
		esgotoDesligado.setId(LigacaoEsgotoSituacao.POTENCIAL);
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void naoGeraContaSemConsumo_AguaLigada_EsgotoLigado_NaoPertenceACondominio() throws Exception {
		aguaEsgotoZerados = true;
		
		assertFalse(analisadorGeracaoConta.verificarSituacaoImovelParaGerarConta(aguaEsgotoZerados, imovel));
	}
	
	@Test
	public void naoGeraContaSemConsumo_AguaDesligada_EsgotoDesligado_NaoPertenceACondominio() throws Exception {
	    aguaEsgotoZerados = true;
	    imovel.setLigacaoAguaSituacao(aguaLigada);
	    imovel.setLigacaoEsgotoSituacao(esgotoDesligado);
	    
	    assertFalse(analisadorGeracaoConta.verificarSituacaoImovelParaGerarConta(aguaEsgotoZerados, imovel));
	}
	
	@Test
	public void geraContaComConsumoDeAguaEEsgoto_AguaLigada_sgotoDesligado_NaoPertenceACondominio() throws Exception {
		aguaEsgotoZerados = false;
		imovel.setLigacaoEsgotoSituacao(esgotoDesligado);
		
		assertTrue(analisadorGeracaoConta.verificarSituacaoImovelParaGerarConta(aguaEsgotoZerados, imovel));
	}
	
	@Test
	public void geraContaComConsumoDeAguaEEsgoto_AguaLigada() throws Exception {
		aguaEsgotoZerados = false;
		
		assertTrue(analisadorGeracaoConta.verificarSituacaoImovelParaGerarConta(aguaEsgotoZerados, imovel));
	}

	@Test
	public void naoGeraContaComConsumoDeAguaEsgoto_AguaDesligada_EsgotoDesligado() throws Exception {
		aguaEsgotoZerados = false;
		
		imovel.setLigacaoAguaSituacao(aguaDesligada);
		imovel.setLigacaoEsgotoSituacao(esgotoDesligado);
		
		assertFalse(analisadorGeracaoConta.verificarSituacaoImovelParaGerarConta(aguaEsgotoZerados, imovel));
	}
	
	@Test
	public void naoGeraContaComConsumoDeAguaEsgoto_AguaEsgotoDesligados_ESemCondominio() throws Exception {
		aguaEsgotoZerados = false;
		
        imovel.setLigacaoAguaSituacao(aguaDesligada);
        imovel.setLigacaoEsgotoSituacao(esgotoDesligado);
		
		assertFalse(analisadorGeracaoConta.verificarSituacaoImovelParaGerarConta(aguaEsgotoZerados, imovel));
	}
	
	@Test
	public void geraContaSemConsumo_AguaDesligada_EsgotoDesligado_EPerterceACondominio() throws Exception {
		aguaEsgotoZerados = true;
		
        imovel.setLigacaoAguaSituacao(aguaDesligada);
        imovel.setLigacaoEsgotoSituacao(esgotoDesligado);
		
		imovel.setImovelCondominio(new Imovel());
		
		assertTrue(analisadorGeracaoConta.verificarSituacaoDeCondominio(aguaEsgotoZerados, imovel));
	}

	@Test
	public void geraContaComDebitoCobrar() throws Exception {
		assertFalse(analisadorGeracaoConta.verificarDebitosECreditosParaGerarConta(anoMesFaturamento, imovel));
	}
	
	@Test
	public void geraContaComParalisacaoFaturamento() throws Exception {
		this.debitos = buildCollectionDebitosCobrarVazio(false);
		
		adicionaFaturamentoSituacaoTipoParaImovel(Status.ATIVO);
		
		assertFalse(analisadorGeracaoConta.verificarDebitosECreditosParaGerarConta(anoMesFaturamento, imovel));
	}
	
	@Test
	public void geraContaQuandoHaDebitoSemPagamento() throws Exception {
		this.debitos = buildCollectionDebitosCobrarVazio(false);
		
		adicionaFaturamentoSituacaoTipoParaImovel(Status.INATIVO);
		
		assertTrue(analisadorGeracaoConta.verificarDebitosECreditosParaGerarConta(anoMesFaturamento, imovel));
	}
	
	@Test
	public void naoGeraContaQuandoNaoHaDebitosCobrarAtivosENaoHaCreditosRealizar() throws Exception {

		this.debitos = buildCollectionDebitosCobrarComDebitoTipo(Status.INATIVO);
		
		mockPesquisarCreditoARealizar(null);
		
		adicionaFaturamentoSituacaoTipoParaImovel(Status.INATIVO);
		
		assertFalse(analisadorGeracaoConta.verificarDebitosECreditosParaGerarConta(anoMesFaturamento, imovel));
	}
	
	@Test
	public void geraContaQuandoHaDebitosCobrarAtivosENaoHaCreditosRealizar() throws Exception {
		this.debitos = buildCollectionDebitosCobrarComDebitoTipo(Status.ATIVO);
		
		mockPesquisarCreditoARealizar(null);
		
		adicionaFaturamentoSituacaoTipoParaImovel(Status.INATIVO);
		
		assertTrue(analisadorGeracaoConta.verificarDebitosECreditosParaGerarConta(anoMesFaturamento, imovel));
	}
	
	@Test
	public void naoGeraContaQuandoNaoHaDebitosCobrarAtivosEHaCreditosRealizarComDevolucao() throws Exception {
		this.debitos = buildCollectionDebitosCobrarComDebitoTipo(Status.INATIVO);
				
		Collection<CreditoARealizar> creditosRealizar = buildCollectionCreditosRealizar();
		mockPesquisarCreditoARealizar(creditosRealizar);
		mockExisteCreditoComDevolucao(creditosRealizar, true);
		
		adicionaFaturamentoSituacaoTipoParaImovel(Status.INATIVO);
		
		assertFalse(analisadorGeracaoConta.verificarDebitosECreditosParaGerarConta(anoMesFaturamento, imovel));
	}
	
	@Test
	public void geraContaQuandoHaDebitosCobrarAtivosEHaCreditosRealizarComDevolucao() throws Exception {
		this.debitos = buildCollectionDebitosCobrarComDebitoTipo(Status.ATIVO);
		
		Collection<CreditoARealizar> creditosRealizar = buildCollectionCreditosRealizar();
		
		mockPesquisarCreditoARealizar(creditosRealizar);
		
		mockExisteCreditoComDevolucao(creditosRealizar, true);
		
		adicionaFaturamentoSituacaoTipoParaImovel(Status.INATIVO);
		
		assertTrue(analisadorGeracaoConta.verificarDebitosECreditosParaGerarConta(anoMesFaturamento, imovel));
	}
	
	@Test
	public void geraContaQuandoHaCreditosRealizarENaoHaCreditosComDevolucao() throws Exception {
		this.debitos = buildCollectionDebitosCobrarVazio(false);
		
		Collection<CreditoARealizar> creditosRealizar = buildCollectionCreditosRealizar();
		
		mockPesquisarCreditoARealizar(creditosRealizar);
		mockExisteCreditoComDevolucao(creditosRealizar, false);
		
		adicionaFaturamentoSituacaoTipoParaImovel(Status.INATIVO);
		
		assertTrue(analisadorGeracaoConta.verificarDebitosECreditosParaGerarConta(anoMesFaturamento, imovel));
	}

	private Collection<CreditoARealizar> buildCollectionCreditosRealizar() {
		Collection<CreditoARealizar> creditosRealizar = new ArrayList<CreditoARealizar>();
		CreditoARealizar creditoRealizar = new CreditoARealizar();
		creditosRealizar.add(creditoRealizar);
		return creditosRealizar;
	}
	
	private void mockPesquisarCreditoARealizar(Collection<CreditoARealizar> retorno) throws ErroRepositorioException {
		when(creditoRealizarRepositorio.buscarCreditoRealizarPorImovel(imovel.getId(), DebitoCreditoSituacao.NORMAL, anoMesFaturamento))
			.thenReturn(retorno);
	}
	
	private void mockExisteCreditoComDevolucao(Collection<CreditoARealizar> creditosRealizar, boolean retorno) throws ErroRepositorioException {
		when(repositorioDevolucao.existeCreditoComDevolucao(creditosRealizar))
			.thenReturn(retorno);
	}
		
	private void adicionaFaturamentoSituacaoTipoParaImovel(Status status) {
		FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
		faturamentoSituacaoTipo.setIndicadorParalisacaoFaturamento(status.getId());
		imovel.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
	}
	
	private Collection<DebitoACobrar> buildCollectionDebitosCobrarVazio(boolean vazio){
		Collection<DebitoACobrar> debitosCobrar = new ArrayList<DebitoACobrar>();
		
		if(vazio == false){
			DebitoACobrar debitoCobrar = new DebitoACobrar();
			DebitoTipo debitoTipo = new DebitoTipo();
			debitoTipo.setIndicadorGeracaoConta(Status.ATIVO.getId());
			debitoCobrar.setDebitoTipo(debitoTipo);
			debitosCobrar.add(debitoCobrar);
		}
		
		return debitosCobrar;
	}
	
	private Collection<DebitoACobrar> buildCollectionDebitosCobrarComDebitoTipo(Status status){
		Collection<DebitoACobrar> debitosCobrar = new ArrayList<DebitoACobrar>();
		
		DebitoTipo debitoTipo = new DebitoTipo();
		debitoTipo.setIndicadorGeracaoConta(status.getId());
		
		DebitoACobrar debitoCobrar = new DebitoACobrar();
		debitoCobrar.setDebitoTipo(debitoTipo);
		debitosCobrar.add(debitoCobrar);
		
		return debitosCobrar;
	}	

	private Collection<DebitoACobrar> getDebitos(){
        return debitos;
    } 	
}