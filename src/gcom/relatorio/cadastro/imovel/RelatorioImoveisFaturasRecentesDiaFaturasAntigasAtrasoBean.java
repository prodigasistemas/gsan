package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

/**
 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e Faturas Antigas em Atraso
 * 
 * @author Rafael Pinto
 * @date 08/01/2008
 */
public class RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoBean implements RelatorioBean {
	
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
	
	private String subCategoria;
	private String economias;
	
	private String referenciaInicial;
	private String referenciaFinal;

	private String quantidadeFaturasAtraso;
	private String valorFaturasAtraso;
	
	
	public RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoBean(RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper helper) {
		
		this.inscricaoImovel = helper.getInscricaoImovel();
		this.unidadeNegocio = helper.getUnidadeNegocio()+"-"+helper.getNomeUnidadeNegocio();
		this.gerenciaRegional = helper.getGerenciaRegional()+"-"+helper.getNomeGerenciaRegional();
		this.localidade = helper.getLocalidade()+"-"+helper.getDescricaoLocalidade();
		this.setorComercial = helper.getSetorComercial()+"-"+helper.getDescricaoSetorComercial();
		
		if(helper.getSequencialRota() != null){
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
		
		this.subCategoria = helper.getSubCategoria().toString();
		this.economias = helper.getEconomias().toString();
		
		this.referenciaInicial = Util.formatarAnoMesParaMesAno(helper.getReferenciaInicial());
		this.referenciaFinal = Util.formatarAnoMesParaMesAno(helper.getReferenciaFinal());
		
		this.quantidadeFaturasAtraso = Util.agruparNumeroEmMilhares(helper.getQuantidadeFaturasAtraso());
		this.valorFaturasAtraso = Util.formatarMoedaReal(helper.getValorFaturasAtras());
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

	public String getEconomias() {
		return economias;
	}

	public String getQuantidadeFaturasAtraso() {
		return quantidadeFaturasAtraso;
	}

	public String getReferenciaFinal() {
		return referenciaFinal;
	}

	public String getReferenciaInicial() {
		return referenciaInicial;
	}

	public String getSubCategoria() {
		return subCategoria;
	}

	public String getValorFaturasAtraso() {
		return valorFaturasAtraso;
	}
	
		
}
