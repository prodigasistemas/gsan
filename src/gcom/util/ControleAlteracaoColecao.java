package gcom.util;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ControleAlteracaoColecao {
	
	private Collection adicionadas = new ArrayList();
	private Collection removidas = new ArrayList();
	private HashMap alteradas = new HashMap();
	
	private ControleAlteracaoColecao(){
		
	}

	
	public Collection getAdicionadas() {
		return adicionadas;
	}


	public HashMap getAlteradas() {
		return alteradas;
	}


	public Collection getRemovidas() {
		return removidas;
	}


	public static ControleAlteracaoColecao gerarControle(Collection antes, 
		Collection depois){
		
		ControleAlteracaoColecao controle = new ControleAlteracaoColecao();
		if ((antes == null || antes.isEmpty()) && 
			(depois == null || depois.isEmpty())){
			return controle;
		} 
		if (antes == null || antes.isEmpty()){			
			controle.adicionadas = depois;
		} else if (depois == null || depois.isEmpty()){
			controle.removidas = antes;
		} else {
			boolean statusAntes[] = new boolean[antes.size()];
			boolean statusDepois[] = new boolean[depois.size()];
			Object[] objsAntes = antes.toArray(new Object[0]);
			Object[] objsDepois = depois.toArray(new Object[0]);
			for (int i = 0; i < objsDepois.length; i++) {
				for (int j = 0; j < objsAntes.length; j++) {
					if (objsDepois[i].equals(objsAntes[j])){
						statusAntes[j] = true;
						statusDepois[i] = true;
						controle.alteradas.put(objsAntes[j], objsDepois[i]);
						break;
					}
				}
			}
			for (int i = 0; i < objsDepois.length; i++) {
				if (!statusDepois[i]){
					controle.adicionadas.add(objsDepois[i]);					
				} 
			}
			for (int i = 0; i < objsAntes.length; i++) {
				if (!statusAntes[i]){
					controle.removidas.add(objsAntes[i]);					
				}
			}
			removerClientesDataFimRelacaoNaoNula(controle.removidas);			
		}
		 
		return controle;
		
	}
	
	/**
	 * Este método retira da colecao, clientesImoveis com data de fim de relacao diferente de nulo.
	 * Sera usado no controle de alteracao de colecoes, no caso de itens removidos.
	 * Nao iremos considerar uma remocao de um item q ja tinha dataFimRelacao nula.
	 * @param colecao
	 */
	public static void removerClientesDataFimRelacaoNaoNula(Collection colecao){
		if (colecao != null){
			Iterator iter = colecao.iterator();
			while (iter.hasNext()) {
				Object item = iter.next();
				if (item instanceof ClienteImovel){
					ClienteImovel clienteImov = (ClienteImovel) item;
					if (clienteImov.getDataFimRelacao() != null){
						iter.remove();
					}
				} else if (item instanceof ClienteImovelEconomia){
					ClienteImovelEconomia clienteImov = (ClienteImovelEconomia) item;
					if (clienteImov.getDataFimRelacao() != null){
						iter.remove();
					}
				} else {
					return;
				}
			}
		}
	}
	

}
