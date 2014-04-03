package gcom.util;

import gcom.cadastro.atualizacaocadastral.bean.DadosTabelaAtualizacaoCadastralHelper;
import gcom.seguranca.transacao.TabelaColuna;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AtualizacaoCadastralUtil {

	public List<DadosTabelaAtualizacaoCadastralHelper> linhasAtualizacaoCadastral(Collection<DadosTabelaAtualizacaoCadastralHelper> existentes,
			Map<String, List<DadosTabelaAtualizacaoCadastralHelper>> alterados) {
		
		List<DadosTabelaAtualizacaoCadastralHelper> retorno = new ArrayList<DadosTabelaAtualizacaoCadastralHelper>();
		
		Map<String, DadosTabelaAtualizacaoCadastralHelper> subcategorias = new LinkedHashMap<String, DadosTabelaAtualizacaoCadastralHelper>();
		
		for (DadosTabelaAtualizacaoCadastralHelper item : existentes) {
			if (alterados.containsKey(item.getNomeColuna())){
				if (!isSubcategoria(item)){
					retorno.add(alterados.get(item.getNomeColuna()).get(0));
				}else{
					subcategorias.put(item.getComplemento().trim(), item);
				}
			}else{
				retorno.add(item);
			}
		}
		
		retorno.addAll(acrescentaSubcategorias(subcategorias, alterados));

		retorno.addAll(acrescentaNaoSubcategorias(retorno, alterados));

		Collections.sort(retorno, new AtualizacaoCadastralComparator());
		
		return retorno; 
	}

	private Collection<DadosTabelaAtualizacaoCadastralHelper> acrescentaNaoSubcategorias(Collection<DadosTabelaAtualizacaoCadastralHelper> existentes, Map<String, List<DadosTabelaAtualizacaoCadastralHelper>> alterados) {
		List<DadosTabelaAtualizacaoCadastralHelper> naoSubcategorias = new ArrayList<DadosTabelaAtualizacaoCadastralHelper>();
		
		List<String> colunas = new ArrayList<String>();
		for (DadosTabelaAtualizacaoCadastralHelper item : existentes) {
			colunas.add(item.getNomeColuna());
		}
		
		for (String coluna : alterados.keySet()) {
			if (!colunas.contains(coluna)){
				naoSubcategorias.addAll(alterados.get(coluna));
			}
		}
		
		return naoSubcategorias;
	}

	private Collection<DadosTabelaAtualizacaoCadastralHelper> acrescentaSubcategorias(
			Map<String, DadosTabelaAtualizacaoCadastralHelper> subcategorias, Map<String, List<DadosTabelaAtualizacaoCadastralHelper>> alterados) {
		
		if (alterados.containsKey(TabelaColuna.NOME_COLUNA_ECONOMIAS)){
			for (DadosTabelaAtualizacaoCadastralHelper alterado : alterados.get(TabelaColuna.NOME_COLUNA_ECONOMIAS)) {
				subcategorias.put(alterado.getComplemento().trim(), alterado);
			}
		}
		
		return subcategorias.values();
	}

	private boolean isSubcategoria(DadosTabelaAtualizacaoCadastralHelper existente) {
		if (existente.getNomeColuna().equals(TabelaColuna.NOME_COLUNA_ECONOMIAS))
			return true;
		else
			return false;
	}
}
