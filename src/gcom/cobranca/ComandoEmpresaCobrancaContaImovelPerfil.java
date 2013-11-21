package gcom.cobranca;

import gcom.cadastro.imovel.ImovelPerfil;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

public class ComandoEmpresaCobrancaContaImovelPerfil extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
    private ComandoEmpresaCobrancaContaImovelPerfilPK comp_id;

	private Date ultimaAlteracao;

	private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;
	
	private ImovelPerfil imovelPerfil;

	
	public ComandoEmpresaCobrancaContaImovelPerfil() {
		super();
	}

	public ComandoEmpresaCobrancaContaImovelPerfil(ComandoEmpresaCobrancaContaImovelPerfilPK comp_id, Date ultimaAlteracao, ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, ImovelPerfil imovelPerfil) {
		super();
		this.comp_id = comp_id;
		this.ultimaAlteracao = ultimaAlteracao;
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
		this.imovelPerfil = imovelPerfil;
	}

	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}

	public void setComandoEmpresaCobrancaConta(
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}

	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ComandoEmpresaCobrancaContaImovelPerfilPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(ComandoEmpresaCobrancaContaImovelPerfilPK comp_id) {
		this.comp_id = comp_id;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}    
    
	public Filtro retornaFiltro(){
		FiltroComandoEmpresaCobrancaContaImovelPerfil filtroComandoEmpresaCobrancaContaImovelPerfil = new FiltroComandoEmpresaCobrancaContaImovelPerfil();
		
		filtroComandoEmpresaCobrancaContaImovelPerfil.adicionarCaminhoParaCarregamentoEntidade("comp_id");
		filtroComandoEmpresaCobrancaContaImovelPerfil.adicionarCaminhoParaCarregamentoEntidade("comandoEmpresaCobrancaConta");
		filtroComandoEmpresaCobrancaContaImovelPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");

		
		filtroComandoEmpresaCobrancaContaImovelPerfil.adicionarParametro(
						new ParametroSimples(FiltroComandoEmpresaCobrancaContaImovelPerfil.COMP_ID_COMANDO_EMPRESA_COBRANCA_CONTA_ID, comandoEmpresaCobrancaConta.getId()));
		
		filtroComandoEmpresaCobrancaContaImovelPerfil.adicionarParametro(
				new ParametroSimples(FiltroComandoEmpresaCobrancaContaImovelPerfil.COMP_ID_IMOVEL_PERFIL_ID, imovelPerfil.getId()));
		
		return filtroComandoEmpresaCobrancaContaImovelPerfil; 
	}

}
