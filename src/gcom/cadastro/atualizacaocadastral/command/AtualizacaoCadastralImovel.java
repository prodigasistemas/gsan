package gcom.cadastro.atualizacaocadastral.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtualizacaoCadastralImovel {

	private int matricula = 0;

	private Map<String, String> linhaImovel = new HashMap<String, String>();
	private Map<String, String> linhaCliente = new HashMap<String, String>();
	private Map<String, String> linhaRamoAtividade = new HashMap<String, String>();
	private Map<String, String> linhaServicos = new HashMap<String, String>();
	private Map<String, String> linhaMedidor = new HashMap<String, String>();
	private Map<String, String> linhaAnormalidade = new HashMap<String, String>();

	private boolean imovelAprovado = false;

	private List<String> mensagensErro = new ArrayList<String>();

	private DadoAtualizacaoImovel dadosImovel = new DadoAtualizacaoImovel();

	private List<DadoAtualizacaoRamoAtividade> dadosRamoAtividade = new ArrayList<DadoAtualizacaoRamoAtividade>();
	
	private AtualizacaoCadastral atualizacaoArquivo;
	
	private Boolean erroLayout = false;
	
	private Boolean existeMedidor = false;
	
	public AtualizacaoCadastralImovel(){
		
	}
	
	public AtualizacaoCadastralImovel(AtualizacaoCadastral atualizacaoArquivo) {
		this.atualizacaoArquivo = atualizacaoArquivo;
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

	public Map<String, String> getLinhaCliente() {
		return linhaCliente;
	}
	
	public String getLinhaCliente(String campo) {
		return linhaCliente.get(campo);
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
	
	public String getLinhaServicos(String campo) {
		return linhaServicos.get(campo);
	}

	public void setLinhaServicos(HashMap<String, String> linha4) {
		this.linhaServicos = linha4;
	}

	public Map<String, String> getLinhaMedidor() {
		return linhaMedidor;
	}
	
	public String getLinhaMedidor(String campo) {
		return linhaMedidor.get(campo);
	}

	public void setLinhaMedidor(HashMap<String, String> linha5) {
		this.linhaMedidor = linha5;
	}

	public Map<String, String> getLinhaAnormalidade() {
		return linhaAnormalidade;
	}
	
	public String getLinhaAnormalidade(String campo) {
		return linhaAnormalidade.get(campo);
	}

	public void setLinhaAnormalidade(HashMap<String, String> linha6) {
		this.linhaAnormalidade = linha6;
	}

	public boolean cadastroInvalido() {
		return mensagensErro.size() > 0;
	}

	public List<String> getMensagensErro() {
		return mensagensErro;
	}

	public void addMensagemErro(String erro) {
		mensagensErro.add(erro.toUpperCase());
	}

	public void addMensagemErroLayout(String erro) {
		erroLayout = true;
		mensagensErro.add(erro.toUpperCase());
	}
	
	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public boolean isImovelAprovado() {
		return imovelAprovado;
	}

	public void setImovelAprovado(boolean imovelAprovado) {
		this.imovelAprovado = imovelAprovado;
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

	public AtualizacaoCadastral getAtualizacaoArquivo() {
		return atualizacaoArquivo;
	}
	
	public Boolean isErroLayout(){
		return erroLayout;
	}
	
	public Boolean isExisteMedidor() {
		return existeMedidor;
	}

	public void setExisteMedidor(Boolean existeMedidor) {
		this.existeMedidor = existeMedidor;
	}

	public void limparDadosUsuario() {
		linhaCliente.put("matriculaUsuario", "0");
	}
	
	public void limparDadosProprietario() {
		linhaCliente.put("usuarioProprietario", "1");
		linhaCliente.put("matriculaProprietario", "0");
		linhaCliente.put("cnpjCpfProprietario", "");
	}		
	
	public void limparDadosResponsavel() {
		linhaCliente.put("tipoResponsavel", "0");
		linhaCliente.put("matriculaResponsavel", "0");
		linhaCliente.put("cnpjCpfResponsavel", "");
	}
}
