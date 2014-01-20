package gcom.cadastro.atualizacaocadastral.command;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtualizacaoCadastral {

	private ArquivoTextoAtualizacaoCadastral arquivoTexto = null;

	private List<String> nomesImagens = new ArrayList<String>();

	private String matricula = "";

	private Map<String, String> linhaImovel = new HashMap<String, String>();
	private Map<String, String> linhaCliente = new HashMap<String, String>();
	private Map<String, String> linhaRamoAtividade = new HashMap<String, String>();
	private Map<String, String> linhaServicos = new HashMap<String, String>();
	private Map<String, String> linhaMedidor = new HashMap<String, String>();
	private Map<String, String> linhaAnormalidade = new HashMap<String, String>();

	private boolean atualizacaoLiberada = false;
	
	private boolean imovelAprovado = false;

	private List<String> mensagensErro = new ArrayList<String>();

	private boolean erroCadastro = false;
	
	private DadoAtualizacaoImovel dadosImovel = new DadoAtualizacaoImovel();

	private List<DadoAtualizacaoRamoAtividade> dadosRamoAtividade = new ArrayList<DadoAtualizacaoRamoAtividade>();

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

	public void setLinhaImovel(Map<String, String> linha2) {
		this.linhaImovel = linha2;
	}

	public Map<String, String> getLinhaCliente() {
		return linhaCliente;
	}

	public void setLinhaCliente(HashMap<String, String> linha1) {
		this.linhaCliente = linha1;
	}

	public Map<String, String> getLinhaRamoAtividade() {
		return linhaRamoAtividade;
	}

	public void setLinhaRamoAtividade(HashMap<String, String> linha3) {
		this.linhaRamoAtividade = linha3;
	}

	public Map<String, String> getLinhaServicos() {
		return linhaServicos;
	}

	public void setLinhaServicos(HashMap<String, String> linha4) {
		this.linhaServicos = linha4;
	}

	public Map<String, String> getLinhaMedidor() {
		return linhaMedidor;
	}

	public void setLinhaMedidor(HashMap<String, String> linha5) {
		this.linhaMedidor = linha5;
	}

	public Map<String, String> getLinhaAnormalidade() {
		return linhaAnormalidade;
	}

	public void setLinhaAnormalidade(HashMap<String, String> linha6) {
		this.linhaAnormalidade = linha6;
	}

	public void liberarAtualizacao() {
		atualizacaoLiberada = true;
	}

	public boolean atualizacaoLiberada() {
		return atualizacaoLiberada;
	}

	public boolean cadastroInvalido() {
		return erroCadastro;
	}

	public List<String> getMensagensErro() {
		return mensagensErro;
	}

	public void addMensagemErro(String erro) {
		erroCadastro = true;
		mensagensErro.add(erro);
	}
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public boolean isImovelAprovado() {
		return imovelAprovado;
	}

	public void setImovelAprovado(boolean imovelAprovado) {
		this.imovelAprovado = imovelAprovado;
	}

	public void inicializaLeituras() {
		linhaImovel = new HashMap<String, String>();
		linhaCliente = new HashMap<String, String>();
		linhaRamoAtividade = new HashMap<String, String>();
		linhaServicos = new HashMap<String, String>();
		linhaMedidor = new HashMap<String, String>();
		linhaAnormalidade = new HashMap<String, String>();
		atualizacaoLiberada = false;
		imovelAprovado = false;
	}

	public DadoAtualizacaoImovel getDadosImovel() {
		return dadosImovel;
	}

	public void setDadosImovel(DadoAtualizacaoImovel dadosImovel) {
		this.dadosImovel = dadosImovel;
	}

	public List<DadoAtualizacaoRamoAtividade> getDadosRamoAtividade() {
		return dadosRamoAtividade;
	}

	public void addDadoRamoAtividade(DadoAtualizacaoRamoAtividade dadoRamoAtividade) {
		this.dadosRamoAtividade.add(dadoRamoAtividade);
	}
}