package gcom.transacao;

import gcom.cadastro.atualizacaocadastral.SituacaoAguaHelper;
import gcom.cadastro.atualizacaocadastral.SituacaoEsgotoHelper;
import gcom.cadastro.atualizacaocadastral.SituacaoSubcategoriaHelper;
import gcom.cadastro.atualizacaocadastral.bean.DadosTabelaAtualizacaoCadastralHelper;
import gcom.util.AtualizacaoCadastralUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

public class TesteCargaAlteracoesArquivo extends TestCase {
	
	private Collection<DadosTabelaAtualizacaoCadastralHelper> montaObjetos(Collection<DadosTabelaAtualizacaoCadastralHelper> existentes, List<DadosTabelaAtualizacaoCadastralHelper> lista){
		Map<String, List<DadosTabelaAtualizacaoCadastralHelper>> map = new HashMap<String, List<DadosTabelaAtualizacaoCadastralHelper>>();
		
		for (DadosTabelaAtualizacaoCadastralHelper item : lista) {
			List<DadosTabelaAtualizacaoCadastralHelper> testes = map.get(item.getNomeColuna());
			if (testes == null){
				testes = new LinkedList<DadosTabelaAtualizacaoCadastralHelper>();
				map.put(item.getNomeColuna(), testes);
			}
			testes.add(item);
		}
		
		Collection<DadosTabelaAtualizacaoCadastralHelper> nova = new AtualizacaoCadastralUtil().linhasAtualizacaoCadastral(existentes, map);
		return nova;
	}
	
	public void testaCargaSemAlteracaoAgua(){
		Collection<DadosTabelaAtualizacaoCadastralHelper> existentes = new ArrayList<DadosTabelaAtualizacaoCadastralHelper>();
		List<DadosTabelaAtualizacaoCadastralHelper> lista = new ArrayList<DadosTabelaAtualizacaoCadastralHelper>();
		
		SituacaoAguaHelper sitAgua = new SituacaoAguaHelper("LIGADO");
		
		existentes.add(sitAgua);
		
		
		DadosTabelaAtualizacaoCadastralHelper helper = montaObjetos(existentes, lista).iterator().next();

		StringBuilder campo = new StringBuilder();
		campo.append(helper.getNomeColuna())
			.append(";")
			.append(helper.getColunaValorAnterior())
			.append(";")
			.append(helper.getColunaValorAtual());
			
		assertEquals("last_id;LIGADO;LIGADO", campo.toString());
	}
	
	public void testaCargaComAlteracaoAgua(){
		Collection<DadosTabelaAtualizacaoCadastralHelper> existentes = new ArrayList<DadosTabelaAtualizacaoCadastralHelper>();
		List<DadosTabelaAtualizacaoCadastralHelper> lista = new ArrayList<DadosTabelaAtualizacaoCadastralHelper>();

		SituacaoAguaHelper sitAgua = new SituacaoAguaHelper("LIGADO");
		
		existentes.add(sitAgua);
		
		DadosTabelaAtualizacaoCadastralHelper item = new DadosTabelaAtualizacaoCadastralHelper();
		item.setColunaValorAnterior("LIGADO");
		item.setColunaValorAtual("DESLIGADO");
		item.setNomeColuna("last_id");
		lista.add(item);
		
		DadosTabelaAtualizacaoCadastralHelper helper = montaObjetos(existentes, lista).iterator().next();
		
		StringBuilder campo = new StringBuilder();
		campo.append(helper.getNomeColuna())
			.append(";")
			.append(helper.getColunaValorAnterior())
			.append(";")
			.append(helper.getColunaValorAtual());
		
		assertEquals("last_id;LIGADO;DESLIGADO", campo.toString());
	}
	
	public void testaCargaSemAlteracaoAguaComAlteracaoEsgoto(){
		Collection<DadosTabelaAtualizacaoCadastralHelper> existentes = new ArrayList<DadosTabelaAtualizacaoCadastralHelper>();
		List<DadosTabelaAtualizacaoCadastralHelper> lista = new ArrayList<DadosTabelaAtualizacaoCadastralHelper>();
		
		SituacaoAguaHelper sitAgua   = new SituacaoAguaHelper("LIGADO");
		SituacaoEsgotoHelper sitEsgo = new SituacaoEsgotoHelper("LIGADO");
		
		existentes.add(sitAgua);
		existentes.add(sitEsgo);
		
		DadosTabelaAtualizacaoCadastralHelper item = new DadosTabelaAtualizacaoCadastralHelper();
		item.setColunaValorAnterior("LIGADO");
		item.setColunaValorAtual("DESLIGADO");
		item.setNomeColuna("lest_id");
		lista.add(item);
		
		Collection<DadosTabelaAtualizacaoCadastralHelper> nova = montaObjetos(existentes, lista);
		
		StringBuilder retorno = new StringBuilder();
		for (DadosTabelaAtualizacaoCadastralHelper dado : nova) {
			retorno.append("[").append(dado.getNomeColuna())
			.append(";")
			.append(dado.getColunaValorAnterior())
			.append(";")
			.append(dado.getColunaValorAtual())
			.append("]");
		}
		
		assertEquals("[last_id;LIGADO;LIGADO][lest_id;LIGADO;DESLIGADO]", retorno.toString());
	}	

	public void testaCargaSemAlteracaoSemAlteracaoEsgoto(){
		Collection<DadosTabelaAtualizacaoCadastralHelper> existentes = new ArrayList<DadosTabelaAtualizacaoCadastralHelper>();
		List<DadosTabelaAtualizacaoCadastralHelper> lista = new ArrayList<DadosTabelaAtualizacaoCadastralHelper>();
		
		SituacaoAguaHelper sitAgua   = new SituacaoAguaHelper("LIGADO");
		SituacaoEsgotoHelper sitEsgo = new SituacaoEsgotoHelper("DESLIGADO");
		
		existentes.add(sitAgua);
		existentes.add(sitEsgo);
		
		Collection<DadosTabelaAtualizacaoCadastralHelper> nova = montaObjetos(existentes, lista);
		
		StringBuilder retorno = new StringBuilder();
		for (DadosTabelaAtualizacaoCadastralHelper dado : nova) {
			retorno.append("[").append(dado.getNomeColuna())
			.append(";")
			.append(dado.getColunaValorAnterior())
			.append(";")
			.append(dado.getColunaValorAtual())
			.append("]");
		}
		
		assertEquals("[last_id;LIGADO;LIGADO][lest_id;DESLIGADO;DESLIGADO]", retorno.toString());
	}	

	public void testaCargaSemAlteracaoCategoria(){
		Collection<DadosTabelaAtualizacaoCadastralHelper> existentes = new LinkedList<DadosTabelaAtualizacaoCadastralHelper>();
		List<DadosTabelaAtualizacaoCadastralHelper> lista = new ArrayList<DadosTabelaAtualizacaoCadastralHelper>();

		SituacaoSubcategoriaHelper sitSubcat = new SituacaoSubcategoriaHelper("4", "RESIDENCIAL - R2");
		
		existentes.add(sitSubcat);
		
		
		Collection<DadosTabelaAtualizacaoCadastralHelper> nova = montaObjetos(existentes, lista);
		
		StringBuilder retorno = new StringBuilder();
		for (DadosTabelaAtualizacaoCadastralHelper dado : nova) {
			retorno.append("[").append(dado.getNomeColuna())
			.append(";")
			.append(dado.getColunaValorAnterior())
			.append(";")
			.append(dado.getColunaValorAtual())
			.append("]");
		}
		
		assertEquals("[isac_qteconomia;4;4]", retorno.toString());
	}	

	public void testaCargaComAlteracaoCategoria(){
		Collection<DadosTabelaAtualizacaoCadastralHelper> existentes = new LinkedList<DadosTabelaAtualizacaoCadastralHelper>();
		List<DadosTabelaAtualizacaoCadastralHelper> lista = new ArrayList<DadosTabelaAtualizacaoCadastralHelper>();

		SituacaoSubcategoriaHelper sitSubcat = new SituacaoSubcategoriaHelper("4", "RESIDENCIAL - R2");
		
		existentes.add(sitSubcat);
		
		DadosTabelaAtualizacaoCadastralHelper item = new DadosTabelaAtualizacaoCadastralHelper();
		item.setColunaValorAnterior("4");
		item.setColunaValorAtual("2");
		item.setComplemento("RESIDENCIAL - R2");		
		item.setNomeColuna("isac_qteconomia");
		lista.add(item);

		Collection<DadosTabelaAtualizacaoCadastralHelper> nova = montaObjetos(existentes, lista);
		
		StringBuilder retorno = new StringBuilder();
		for (DadosTabelaAtualizacaoCadastralHelper dado : nova) {
			retorno.append("[").append(dado.getNomeColuna())
			.append(";")
			.append(dado.getColunaValorAnterior())
			.append(";")
			.append(dado.getColunaValorAtual())
			.append("]");
		}
		
		assertEquals("[isac_qteconomia;4;2]", retorno.toString());
	}

	public void testaCargaSemAlteracaoCategoriaComAddCategoria(){
		Collection<DadosTabelaAtualizacaoCadastralHelper> existentes = new LinkedList<DadosTabelaAtualizacaoCadastralHelper>();
		List<DadosTabelaAtualizacaoCadastralHelper> lista = new LinkedList<DadosTabelaAtualizacaoCadastralHelper>();
		
		SituacaoSubcategoriaHelper sitSubcat = new SituacaoSubcategoriaHelper("4", "RESIDENCIAL - R2");
		
		existentes.add(sitSubcat);
		
		DadosTabelaAtualizacaoCadastralHelper item = new DadosTabelaAtualizacaoCadastralHelper();
		item.setColunaValorAnterior("4");
		item.setColunaValorAtual("2");
		item.setComplemento("RESIDENCIAL - R1");
		item.setNomeColuna("isac_qteconomia");
		lista.add(item);

		Collection<DadosTabelaAtualizacaoCadastralHelper> nova = montaObjetos(existentes, lista);

		StringBuilder retorno = new StringBuilder();
		for (DadosTabelaAtualizacaoCadastralHelper dado : nova) {
			retorno.append("[").append(dado.getNomeColuna())
			.append(";")
			.append(dado.getColunaValorAnterior())
			.append(";")
			.append(dado.getColunaValorAtual())
			.append(";")
			.append(dado.getComplemento())
			.append("]");
		}
		
		assertEquals("[isac_qteconomia;4;4;RESIDENCIAL - R2][isac_qteconomia;4;2;RESIDENCIAL - R1]", retorno.toString());
	}
	
	public void testaCargaSemAlteracaoCategoriaComDuasCategoriasExistentes(){
		Collection<DadosTabelaAtualizacaoCadastralHelper> existentes = new LinkedList<DadosTabelaAtualizacaoCadastralHelper>();
		List<DadosTabelaAtualizacaoCadastralHelper> lista = new LinkedList<DadosTabelaAtualizacaoCadastralHelper>();
		
		existentes.add(new SituacaoSubcategoriaHelper("4", "RESIDENCIAL - R2"));
		existentes.add(new SituacaoSubcategoriaHelper("1", "COMERCIAL - C2"));
		
		DadosTabelaAtualizacaoCadastralHelper item = new DadosTabelaAtualizacaoCadastralHelper();
		item.setColunaValorAnterior("4");
		item.setColunaValorAtual("2");
		item.setComplemento("RESIDENCIAL - R1");
		item.setNomeColuna("isac_qteconomia");
		lista.add(item);
		
		Collection<DadosTabelaAtualizacaoCadastralHelper> nova = montaObjetos(existentes, lista);
		
		StringBuilder retorno = new StringBuilder();
		for (DadosTabelaAtualizacaoCadastralHelper dado : nova) {
			retorno.append("[").append(dado.getNomeColuna())
			.append(";")
			.append(dado.getColunaValorAnterior())
			.append(";")
			.append(dado.getColunaValorAtual())
			.append(";")
			.append(dado.getComplemento())
			.append("]");
		}
		
		assertEquals("[isac_qteconomia;4;4;RESIDENCIAL - R2][isac_qteconomia;1;1;COMERCIAL - C2][isac_qteconomia;4;2;RESIDENCIAL - R1]", retorno.toString());
	}
	
	public void testaCargaComESemAlteracaoCategoriaComDuasCategoriasExistentes(){
		Collection<DadosTabelaAtualizacaoCadastralHelper> existentes = new LinkedList<DadosTabelaAtualizacaoCadastralHelper>();
		List<DadosTabelaAtualizacaoCadastralHelper> lista = new LinkedList<DadosTabelaAtualizacaoCadastralHelper>();
		
		existentes.add(new SituacaoSubcategoriaHelper("4", "RESIDENCIAL - R2"));
		existentes.add(new SituacaoSubcategoriaHelper("1", "COMERCIAL - C2"));
		
		DadosTabelaAtualizacaoCadastralHelper item = new DadosTabelaAtualizacaoCadastralHelper();
		item.setColunaValorAnterior("4");
		item.setColunaValorAtual("2");
		item.setComplemento("RESIDENCIAL - R1");
		item.setNomeColuna("isac_qteconomia");
		lista.add(item);

		item = new DadosTabelaAtualizacaoCadastralHelper();
		item.setColunaValorAnterior("4");
		item.setColunaValorAtual("2");
		item.setComplemento("RESIDENCIAL - R2");
		item.setNomeColuna("isac_qteconomia");
		lista.add(item);
		
		Collection<DadosTabelaAtualizacaoCadastralHelper> nova = montaObjetos(existentes, lista);
		
		StringBuilder retorno = new StringBuilder();
		for (DadosTabelaAtualizacaoCadastralHelper dado : nova) {
			retorno.append("[").append(dado.getNomeColuna())
			.append(";")
			.append(dado.getColunaValorAnterior())
			.append(";")
			.append(dado.getColunaValorAtual())
			.append(";")
			.append(dado.getComplemento())
			.append("]");
		}
		assertEquals("[isac_qteconomia;1;1;COMERCIAL - C2][isac_qteconomia;4;2;RESIDENCIAL - R2][isac_qteconomia;4;2;RESIDENCIAL - R1]", retorno.toString());
	}
	

	public void testaCargaComAlteracaoAguaEOutraColuna(){
		Collection<DadosTabelaAtualizacaoCadastralHelper> existentes = new ArrayList<DadosTabelaAtualizacaoCadastralHelper>();
		List<DadosTabelaAtualizacaoCadastralHelper> lista = new ArrayList<DadosTabelaAtualizacaoCadastralHelper>();

		SituacaoAguaHelper sitAgua = new SituacaoAguaHelper("LIGADO");
		
		existentes.add(sitAgua);
		
		DadosTabelaAtualizacaoCadastralHelper item = new DadosTabelaAtualizacaoCadastralHelper();
		item.setColunaValorAnterior("NOME");
		item.setColunaValorAtual("SOBRENOME");
		item.setNomeColuna("nome");
		lista.add(item);
		
		Collection<DadosTabelaAtualizacaoCadastralHelper> nova = montaObjetos(existentes, lista);
		
		StringBuilder campo = new StringBuilder();
		for (DadosTabelaAtualizacaoCadastralHelper helper : nova) {
			campo.append("[").append(helper.getNomeColuna())
			.append(";")
			.append(helper.getColunaValorAnterior())
			.append(";")
			.append(helper.getColunaValorAtual())
			.append("]");
		}

		
		assertEquals("[last_id;LIGADO;LIGADO][nome;NOME;SOBRENOME]", campo.toString());
	}	
}
