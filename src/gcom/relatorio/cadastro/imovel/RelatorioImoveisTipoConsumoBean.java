package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

/**
 * classe responsável por criar o relatório de imoveis por tipo de consumo
 * 
 * @author Bruno Barros
 * @created 11/01/2008
 */
public class RelatorioImoveisTipoConsumoBean implements RelatorioBean {
	
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
	
	private String tipoConsumo;
	
	private String referencia;
	
	

	public RelatorioImoveisTipoConsumoBean(RelatorioImoveisTipoConsumoHelper helper) {
		
		this.inscricaoImovel = helper.getInscricaoImovel();
		this.unidadeNegocio = helper.getUnidadeNegocio()+"-"+helper.getNomeUnidadeNegocio();
		this.gerenciaRegional = helper.getGerenciaRegional()+"-"+helper.getNomeGerenciaRegional();
		this.localidade = helper.getLocalidade()+"-"+helper.getDescricaoLocalidade();
		this.setorComercial = helper.getSetorComercial()+"-"+helper.getDescricaoSetorComercial();
		
		if (helper.getSequencialRota() != null){
			
			this.rota = Util.adicionarZerosEsquedaNumero(3,helper.getCodigoRota().toString())+"."+
			Util.adicionarZerosEsquedaNumero(3,helper.getSequencialRota().toString());
		}
		else{
			
			this.rota = Util.adicionarZerosEsquedaNumero(3,helper.getCodigoRota().toString());
		}
		
		this.nomeCliente = helper.getNomeCliente();
		this.endereco = helper.getEndereco();
		this.matriculaImovel = helper.getMatriculaImovel();
		
		this.situacaoLigacaoAgua = helper.getSituacaoLigacaoAgua();
		this.situacaoLigacaoEsgoto = helper.getSituacaoLigacaoEsgoto();
		
		this.tipoConsumo = helper.getTipoConsumo();
		
		this.referencia = helper.getReferencia();
	}
	public String getReferencia() {
		return referencia;
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
	
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	
	public static long getSerialVersionUID() {
	
		return serialVersionUID;
	}

	
	public String getSituacaoLigacaoEsgoto() {
	
		return situacaoLigacaoEsgoto;
	}

	
	public String getTipoConsumo() {
	
		return tipoConsumo;
	}
		
}
