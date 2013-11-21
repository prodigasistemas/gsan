package gcom.cobranca;

import java.util.Date;

import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

public class ComandoEmpresaCobrancaContaUnidadeNegocio  extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
    private ComandoEmpresaCobrancaContaUnidadeNegocioPK comp_id;
    
    private Date ultimaAlteracao;
    
    private UnidadeNegocio unidadeNegocio;
    
    private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;

    
	public ComandoEmpresaCobrancaContaUnidadeNegocio() {
		super();
	}

	public ComandoEmpresaCobrancaContaUnidadeNegocio(ComandoEmpresaCobrancaContaUnidadeNegocioPK comp_id, Date ultimaAlteracao, UnidadeNegocio unidadeNegocio, ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		super();
		this.comp_id = comp_id;
		this.ultimaAlteracao = ultimaAlteracao;
		this.unidadeNegocio = unidadeNegocio;
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}

	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}

	public void setComandoEmpresaCobrancaConta(
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}

	public ComandoEmpresaCobrancaContaUnidadeNegocioPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(ComandoEmpresaCobrancaContaUnidadeNegocioPK comp_id) {
		this.comp_id = comp_id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}    
    
	public Filtro retornaFiltro(){
		FiltroComandoEmpresaCobrancaContaUnidadeNegocio filtroComandoEmpresaCobrancaContaUnidadeNegocio = new FiltroComandoEmpresaCobrancaContaUnidadeNegocio();
		
		filtroComandoEmpresaCobrancaContaUnidadeNegocio.adicionarCaminhoParaCarregamentoEntidade("comp_id");
		filtroComandoEmpresaCobrancaContaUnidadeNegocio.adicionarCaminhoParaCarregamentoEntidade("comandoEmpresaCobrancaConta");
		filtroComandoEmpresaCobrancaContaUnidadeNegocio.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");

		
		filtroComandoEmpresaCobrancaContaUnidadeNegocio.adicionarParametro(
						new ParametroSimples(FiltroComandoEmpresaCobrancaContaUnidadeNegocio.COMP_ID_COMANDO_EMPRESA_COBRANCA_CONTA_ID, comandoEmpresaCobrancaConta.getId()));
		
		filtroComandoEmpresaCobrancaContaUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroComandoEmpresaCobrancaContaUnidadeNegocio.COMP_ID_UNIDADE_NEGOCIO_ID, unidadeNegocio.getId()));
		
		return filtroComandoEmpresaCobrancaContaUnidadeNegocio; 
	}
    
    
}
