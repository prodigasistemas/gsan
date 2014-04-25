package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

/**
 * classe responsável por criar o relatório de imoveis por Consumo Medio
 * 
 * @author Bruno Barros
 * @created 17/12/2007
 */
public class RelatorioImoveisConsumoMedioBean implements RelatorioBean {
	
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
	
	private String consumoMedioAgua;
	private String consumoMedioEsgoto;
	
	public RelatorioImoveisConsumoMedioBean(RelatorioImoveisConsumoMedioHelper helper) {
		
		this.inscricaoImovel = helper.getInscricaoImovel();
		this.unidadeNegocio = helper.getUnidadeNegocio()+"-"+helper.getNomeUnidadeNegocio();
		this.gerenciaRegional = helper.getGerenciaRegional()+"-"+helper.getNomeGerenciaRegional();
		this.localidade = helper.getLocalidade()+"-"+helper.getDescricaoLocalidade();
		this.setorComercial = helper.getSetorComercial()+"-"+helper.getDescricaoSetorComercial();
		
		if (helper.getSequencialRota() != null){
			this.rota = Util.adicionarZerosEsquedaNumero(3,helper.getCodigoRota().toString())+"."+
				Util.adicionarZerosEsquedaNumero(3,helper.getSequencialRota().toString());
		}else{
			this.rota = Util.adicionarZerosEsquedaNumero(3,helper.getCodigoRota().toString())+"."+
			Util.adicionarZerosEsquedaNumero(3,"");
		}
		
		this.nomeCliente = helper.getNomeCliente();
		this.endereco = helper.getEndereco();
		this.matriculaImovel = helper.getMatriculaImovel();

		this.situacaoLigacaoAgua = helper.getSituacaoLigacaoAgua();
		this.situacaoLigacaoEsgoto = helper.getSituacaoLigacaoEsgoto();
		
		this.consumoMedioAgua = Util.agruparNumeroEmMilhares( helper.getConsumoMedioAgua() );
		this.consumoMedioEsgoto = Util.agruparNumeroEmMilhares( helper.getConsumoMedioEsgoto() );
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

	
	public String getConsumoMedioAgua() {
	
		return consumoMedioAgua;
	}

	
	public String getConsumoMedioEsgoto() {
	
		return consumoMedioEsgoto;
	}

	
	public String getSituacaoLigacaoEsgoto() {
	
		return situacaoLigacaoEsgoto;
	}
}
