package gcom.gui.cadastro.atualizacaocadastral;

import java.util.HashMap;

import org.apache.struts.action.ActionForm;

public class ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm extends ActionForm{

	private static final long serialVersionUID = -5025032611669050238L;

	public static String TOTAL_IMOVEIS = "Total de imóveis";
	public static String TRANSMITIDOS = "Imóveis transmitidos";
	public static String APROVADOS = "Imóveis aprovados";
	public static String ANORMALIDADE = "Imóveis com anormalidade";
	public static String ALTERACAO_HIDROMETRO = "Imóveis com alteração de hidrômetro";
	public static String ALTERACAO_LIGACAO_AGUA = "Imóveis com alteração de ligação de água";
	public static String ALTERACAO_LIGACAO_ESGOTO = "Imóveis com alteração de ligação de esgoto";
	public static String ALTERACAO_CATEGORIA_SUB_ECONOMIAS = "Imóveis com alteração de categoria/subcategoria/qtd de economias";

	public HashMap<String, Integer> mapDadosAnalise;
	
	
	public ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm() {
		this.mapDadosAnalise = new HashMap<String, Integer>();
	}
	
	public HashMap<String, Integer> getMapDadosAnalise() {
		return mapDadosAnalise;
	}

	public void setMapDadosAnalise(HashMap<String, Integer> mapAlteracaoCategoriaSubcategoriaEconomias) {
		this.mapDadosAnalise = mapAlteracaoCategoriaSubcategoriaEconomias;
	}
	
	public Integer getTotalImoveis() {
		return mapDadosAnalise.get(TOTAL_IMOVEIS);
	}
	
	public Integer getImoveisTransmitidos() {
		return mapDadosAnalise.get(TRANSMITIDOS);
	}
	
	public Integer getImoveisAprovados() {
		return mapDadosAnalise.get(APROVADOS);
	}
	
	public Integer getImoveisComAnormalidade() {
		return mapDadosAnalise.get(ANORMALIDADE);
	}
	
	public Integer getImoveisComAlteracaoHidrometro() {
		return mapDadosAnalise.get(ALTERACAO_HIDROMETRO);
	}
	
	public Integer getImoveisComAlteracaoLigacaoAgua() {
		return mapDadosAnalise.get(ALTERACAO_LIGACAO_AGUA);
	}
	
	public Integer getImoveisComAlteracaoLigacaoEsgoto() {
		return mapDadosAnalise.get(ALTERACAO_LIGACAO_ESGOTO);
	}

	public Integer getImoveisComAlteracaoCategoriaSubEconomias() {
		return mapDadosAnalise.get(ALTERACAO_CATEGORIA_SUB_ECONOMIAS);
	}
	
	
}
