package gcom.gui.cadastro.atualizacaocadastral;

import java.util.HashMap;

public class ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm {

	public static String TOTAL_IMOVEIS = "Total de imóveis";
	public static String TRANSMITIDOS = "Imóveis transmitidos";
	public static String APROVADOS = "Imóveis aprovados";
	public static String ANORMALIDADE = "Imóveis com anormalidade";
	public static String ALTERACAO_HIDROMETRO = "Imóveis com alteração de hidrômetro";
	public static String ALTERACAO_LIGACAO_AGUA = "Imóveis com alteração de ligação de água";
	public static String ALTERACAO_LIGACAO_ESGOTO = "Imóveis com alteração de ligação de esgoto";
	public static String ALTERACAO_CATEGORIA_SUB_ECONOMIAS = "Imóveis com alteração de categoria/subcategoria/qtd de economias";

	public HashMap<String, Integer> mapDadosAnalise;
	
	public HashMap<String, Integer> getMapDadosAnalise() {
		return mapDadosAnalise;
	}

	public void setMapDadosAnalise(HashMap<String, Integer> mapAlteracaoCategoriaSubcategoriaEconomias) {
		this.mapDadosAnalise = mapAlteracaoCategoriaSubcategoriaEconomias;
	}
	
}
