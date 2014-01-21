package gcom.cadastro.atualizacaocadastral.command;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtualizacaoCadastral {
	
	private ArquivoTextoAtualizacaoCadastral arquivoTexto = null;
	
	private List<String> nomesImagens = new ArrayList<String>();
	
	private Map<String, String> linhaImovel = new HashMap<String, String>();
	private HashMap<String, String> linhaCliente = new HashMap<String, String>();
	private HashMap<String, String> linhaRamoAtividade = new HashMap<String, String>();
	private HashMap<String, String> linhaServicos = new HashMap<String, String>();
	private HashMap<String, String> linhaMedidor = new HashMap<String, String>();
	private HashMap<String, String> linhaAnormalidade = new HashMap<String, String>();
	
	private boolean atualizacaoLiberada = false;

	public ArquivoTextoAtualizacaoCadastral getArquivoTexto() {
		return arquivoTexto;
	}

	public void setArquivoTexto(ArquivoTextoAtualizacaoCadastral arquivoTexto) {
		this.arquivoTexto = arquivoTexto;
	}

	public List<String> getNomesImagens() {
		return nomesImagens;
	}

	public void setNomesImagens(List<String> nomesImagens) {
		this.nomesImagens = nomesImagens;
	}

	public Map<String, String> getLinhaImovel() {
		return linhaImovel;
	}
	
	public String getLinhaImovel(String campo) {
		return linhaImovel.get(campo);
	}

	public void setLinhaImovel(Map<String, String> linha2) {
		this.linhaImovel = linha2;
	}

	public HashMap<String, String> getLinhaCliente() {
		return linhaCliente;
	}
	
	public String getLinhaCliente(String campo) {
		return linhaCliente.get(campo);
	}

	public void setLinhaCliente(HashMap<String, String> linha1) {
		this.linhaCliente = linha1;
	}

	public HashMap<String, String> getLinhaRamoAtividade() {
		return linhaRamoAtividade;
	}

	public void setLinhaRamoAtividade(HashMap<String, String> linha3) {
		this.linhaRamoAtividade = linha3;
	}

	public HashMap<String, String> getLinhaServicos() {
		return linhaServicos;
	}
	
	public String getLinhaServicos(String campo) {
		return linhaServicos.get(campo);
	}

	public void setLinhaServicos(HashMap<String, String> linha4) {
		this.linhaServicos = linha4;
	}

	public HashMap<String, String> getLinhaMedidor() {
		return linhaMedidor;
	}
	
	public String getLinhaMedidor(String campo) {
		return linhaMedidor.get(campo);
	}

	public void setLinhaMedidor(HashMap<String, String> linha5) {
		this.linhaMedidor = linha5;
	}

	public HashMap<String, String> getLinhaAnormalidade() {
		return linhaAnormalidade;
	}
	
	public String getLinhaAnormalidade(String campo) {
		return linhaAnormalidade.get(campo);
	}

	public void setLinhaAnormalidade(HashMap<String, String> linha6) {
		this.linhaAnormalidade = linha6;
	}
	
	public void inicializaLeituras(){
		linhaImovel = new HashMap<String, String>();
		linhaCliente = new HashMap<String, String>();
		linhaRamoAtividade = new HashMap<String, String>();
		linhaServicos = new HashMap<String, String>();
		linhaMedidor = new HashMap<String, String>();
		linhaAnormalidade = new HashMap<String, String>();
		atualizacaoLiberada = false;
	}

	public void liberarAtualizacao() {
		atualizacaoLiberada = true;
	}
	
	public boolean atualizacaoLiberada(){
		return atualizacaoLiberada;
	}
}
