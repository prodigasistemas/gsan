package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

/**
 * [UC00728] Gerar Relatório de Imóveis Ativos e Não Medidos
 * 
 * @author Rafael Pinto
 * @date 02/01/2008
 */
public class RelatorioImoveisAtivosNaoMedidosBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String inscricaoImovel;
	private String unidadeNegocio;
	private String gerenciaRegional;
	private String localidade;
	private String setorComercial;
	
	private String rota;
	
	private String nomeCliente;
	private String endereco;
	private String matriculaImovel;

	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	
	public RelatorioImoveisAtivosNaoMedidosBean(RelatorioImoveisAtivosNaoMedidosHelper helper) {
		
		this.inscricaoImovel = helper.getInscricaoImovel();
		this.unidadeNegocio = helper.getUnidadeNegocio()+"-"+helper.getNomeUnidadeNegocio();
		this.gerenciaRegional = helper.getGerenciaRegional()+"-"+helper.getNomeGerenciaRegional();
		this.localidade = helper.getLocalidade()+"-"+helper.getDescricaoLocalidade();
		this.setorComercial = helper.getSetorComercial()+"-"+helper.getDescricaoSetorComercial();
		
		if (helper.getSequencialRota() != null &&
				!helper.getSequencialRota().equals("")){
			this.rota = Util.adicionarZerosEsquedaNumero(3,helper.getRota().toString())+"."+
				Util.adicionarZerosEsquedaNumero(3,helper.getSequencialRota().toString());
		}else{
			this.rota = Util.adicionarZerosEsquedaNumero(3,helper.getRota().toString());
		}
		
		this.nomeCliente = helper.getNomeCliente();
		this.endereco = helper.getEndereco();
		this.matriculaImovel = helper.getMatriculaImovel();

		this.situacaoLigacaoAgua = helper.getSituacaoLigacaoAgua();
		this.situacaoLigacaoEsgoto = helper.getSituacaoLigacaoEsgoto();
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	
	public String getLocalidade() {
		return localidade;
	}
	
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}

	public String getRota() {
		return rota;
	}

	public String getSetorComercial() {
		return setorComercial;
	}
	
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
		
}
