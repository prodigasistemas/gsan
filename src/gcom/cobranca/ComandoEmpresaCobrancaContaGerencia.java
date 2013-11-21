package gcom.cobranca;

import java.util.Date;

import gcom.cadastro.localidade.GerenciaRegional;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

public class ComandoEmpresaCobrancaContaGerencia extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
    private ComandoEmpresaCobrancaContaGerenciaPK comp_id;

	private Date ultimaAlteracao;

	private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;
	
	private GerenciaRegional gerenciaRegional;

	public ComandoEmpresaCobrancaContaGerencia() {
		super();
	}

	public ComandoEmpresaCobrancaContaGerencia(ComandoEmpresaCobrancaContaGerenciaPK comp_id, Date ultimaAlteracao, ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, GerenciaRegional gerenciaRegional) {
		super();
		this.comp_id = comp_id;
		this.ultimaAlteracao = ultimaAlteracao;
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
		this.gerenciaRegional = gerenciaRegional;
	}

	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}

	public void setComandoEmpresaCobrancaConta(
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}

	public ComandoEmpresaCobrancaContaGerenciaPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(ComandoEmpresaCobrancaContaGerenciaPK comp_id) {
		this.comp_id = comp_id;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}    
    
	public Filtro retornaFiltro(){
		FiltroComandoEmpresaCobrancaContaGerencia filtroComandoEmpresaCobrancaContaGerencia = new FiltroComandoEmpresaCobrancaContaGerencia();
		
		filtroComandoEmpresaCobrancaContaGerencia.adicionarCaminhoParaCarregamentoEntidade("comp_id");
		filtroComandoEmpresaCobrancaContaGerencia.adicionarCaminhoParaCarregamentoEntidade("comandoEmpresaCobrancaConta");
		filtroComandoEmpresaCobrancaContaGerencia.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");

		
		filtroComandoEmpresaCobrancaContaGerencia.adicionarParametro(
						new ParametroSimples(FiltroComandoEmpresaCobrancaContaGerencia.COMP_ID_COMANDO_EMPRESA_COBRANCA_CONTA_ID, comandoEmpresaCobrancaConta.getId()));
		
		filtroComandoEmpresaCobrancaContaGerencia.adicionarParametro(
				new ParametroSimples(FiltroComandoEmpresaCobrancaContaGerencia.COMP_ID_GERENCIA_REGIONAL_ID, gerenciaRegional.getId()));
		
		return filtroComandoEmpresaCobrancaContaGerencia; 
	}
	
}
