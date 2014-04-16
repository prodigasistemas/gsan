package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

/**
 * [UC0731] Gerar Relatório de Imóveis com os Ultimos Consumos de Agua
 * 
 * @author Rafael Pinto
 *
 * @date 18/12/2007
 */
public class RelatorioImoveisUltimosConsumosAguaBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String gerenciaRegional;
	private String localidade;
	private String unidadeNegocio;
	private String setorComercial;
	
	private String inscricaoImovel;	
	private String rota;
	private String matriculaImovel;
	
	private String nomeCliente;
	private String endereco;

	private String subCategoria;
	private String economias;

	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	
	private String descricaoConsumo1;
	private String descricaoConsumo2;
	private String descricaoConsumo3;
	private String descricaoConsumo4;
	private String descricaoConsumo5;
	private String descricaoConsumo6;

	private String descricaoConsumo7;
	private String descricaoConsumo8;
	private String descricaoConsumo9;
	private String descricaoConsumo10;
	private String descricaoConsumo11;
	private String descricaoConsumo12;

	private String consumoAgua1;
	private String consumoAgua2;
	private String consumoAgua3;
	private String consumoAgua4;
	private String consumoAgua5;
	private String consumoAgua6;

	private String consumoAgua7;
	private String consumoAgua8;
	private String consumoAgua9;
	private String consumoAgua10;
	private String consumoAgua11;
	private String consumoAgua12;
	
	public RelatorioImoveisUltimosConsumosAguaBean(RelatorioImoveisUltimosConsumosAguaHelper helper) {
		
		this.inscricaoImovel = helper.getInscricaoImovel();
		this.unidadeNegocio = helper.getUnidadeNegocio()+"-"+helper.getNomeUnidadeNegocio();
		this.gerenciaRegional = helper.getGerenciaRegional()+"-"+helper.getNomeGerenciaRegional();
		this.localidade = helper.getLocalidade()+"-"+helper.getDescricaoLocalidade();
		this.setorComercial = helper.getSetorComercial()+"-"+helper.getDescricaoSetorComercial();
		
		if (helper.getSequencialRota() != null){
			this.rota = Util.adicionarZerosEsquedaNumero(3,helper.getRota().toString())+"."+
				Util.adicionarZerosEsquedaNumero(3,helper.getSequencialRota().toString());
		}else{
			this.rota = Util.adicionarZerosEsquedaNumero(3,helper.getRota().toString());
		}
		
		this.nomeCliente = helper.getNomeCliente();
		this.endereco = helper.getEndereco();
		this.matriculaImovel = helper.getMatriculaImovel();
		
		this.subCategoria = helper.getSubCategoria().toString();
		this.economias = helper.getEconomias().toString();

		this.situacaoLigacaoAgua = helper.getSituacaoLigacaoAgua();
		this.situacaoLigacaoEsgoto = helper.getSituacaoLigacaoEsgoto();
		
		this.descricaoConsumo1 = helper.getDescricaoConsumo1();
		this.descricaoConsumo2 = helper.getDescricaoConsumo2();
		this.descricaoConsumo3 = helper.getDescricaoConsumo3();
		this.descricaoConsumo4 = helper.getDescricaoConsumo4();
		this.descricaoConsumo5 = helper.getDescricaoConsumo5();
		this.descricaoConsumo6 = helper.getDescricaoConsumo6();

		this.descricaoConsumo7 = helper.getDescricaoConsumo7();
		this.descricaoConsumo8 = helper.getDescricaoConsumo8();
		this.descricaoConsumo9 = helper.getDescricaoConsumo9();
		this.descricaoConsumo10 = helper.getDescricaoConsumo10();
		this.descricaoConsumo11 = helper.getDescricaoConsumo11();
		this.descricaoConsumo12 = helper.getDescricaoConsumo12();

		this.consumoAgua1 = helper.getConsumoAgua1();
		this.consumoAgua2 = helper.getConsumoAgua2();
		this.consumoAgua3 = helper.getConsumoAgua3();
		this.consumoAgua4 = helper.getConsumoAgua4();
		this.consumoAgua5 = helper.getConsumoAgua5();
		this.consumoAgua6 = helper.getConsumoAgua6();

		this.consumoAgua7 = helper.getConsumoAgua7();
		this.consumoAgua8 = helper.getConsumoAgua8();
		this.consumoAgua9 = helper.getConsumoAgua9();
		this.consumoAgua10 = helper.getConsumoAgua10();
		this.consumoAgua11 = helper.getConsumoAgua11();
		this.consumoAgua12 = helper.getConsumoAgua12();
		
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

	public String getSubCategoria() {
		return subCategoria;
	}

	public String getConsumoAgua1() {
		return consumoAgua1;
	}

	public String getConsumoAgua2() {
		return consumoAgua2;
	}

	public String getConsumoAgua3() {
		return consumoAgua3;
	}

	public String getConsumoAgua4() {
		return consumoAgua4;
	}

	public String getConsumoAgua5() {
		return consumoAgua5;
	}

	public String getConsumoAgua6() {
		return consumoAgua6;
	}

	public String getDescricaoConsumo1() {
		return descricaoConsumo1;
	}

	public String getDescricaoConsumo2() {
		return descricaoConsumo2;
	}

	public String getDescricaoConsumo3() {
		return descricaoConsumo3;
	}

	public String getDescricaoConsumo4() {
		return descricaoConsumo4;
	}

	public String getDescricaoConsumo5() {
		return descricaoConsumo5;
	}

	public String getDescricaoConsumo6() {
		return descricaoConsumo6;
	}

	public String getConsumoAgua10() {
		return consumoAgua10;
	}

	public String getConsumoAgua11() {
		return consumoAgua11;
	}

	public String getConsumoAgua12() {
		return consumoAgua12;
	}

	public String getConsumoAgua7() {
		return consumoAgua7;
	}

	public String getConsumoAgua8() {
		return consumoAgua8;
	}

	public String getConsumoAgua9() {
		return consumoAgua9;
	}

	public String getDescricaoConsumo10() {
		return descricaoConsumo10;
	}

	public String getDescricaoConsumo11() {
		return descricaoConsumo11;
	}

	public String getDescricaoConsumo12() {
		return descricaoConsumo12;
	}

	public String getDescricaoConsumo7() {
		return descricaoConsumo7;
	}

	public String getDescricaoConsumo8() {
		return descricaoConsumo8;
	}

	public String getDescricaoConsumo9() {
		return descricaoConsumo9;
	}
	
	

}
