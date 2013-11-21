package gcom.cobranca;

import java.util.Date;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

public class CmdEmpresaCobrancaContaLigacaoAguaSituacao extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	private CmdEmpresaCobrancaContaLigacaoAguaSituacaoPK cels_id;

	private Date ultimaAlteracao;
	
	private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;
	
	private LigacaoAguaSituacao ligacaoAguaSituacao;
	
	public CmdEmpresaCobrancaContaLigacaoAguaSituacao(){}

	@Override
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "comp_id" };
		return retorno;
	}

	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}

	public void setComandoEmpresaCobrancaConta(
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public CmdEmpresaCobrancaContaLigacaoAguaSituacaoPK getCels_id() {
		return cels_id;
	}

	public void setCels_id(CmdEmpresaCobrancaContaLigacaoAguaSituacaoPK cels_id) {
		this.cels_id = cels_id;
	}

}
