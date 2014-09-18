package gcom.relatorio.transacao;
 
import gcom.relatorio.RelatorioBean;

public class RelatorioOperacaoBean implements RelatorioBean {

	private String identificadorLinha1;
	private String identificadorLinha2;
	private String nomeTabela;
	private String nomeUsuario;
	private String acaoUsuario;
	private String tipoUsuario;
	private String nomeColuna;
	private String conteudoAnterior;
	private String conteudoAtual;
	private String dataHora;
	
	
	public RelatorioOperacaoBean ( String identificadorLinha1, String identificadorLinha2,
		String nomeTabela , String nomeUsuario , String acaoUsuario , 
		String tipoUsuario , String nomeColuna , String conteudoAnterior , 
		String conteudoAtual , String dataHora ) {
		
		super();
		
		this.identificadorLinha1 = identificadorLinha1;
		this.identificadorLinha2 = identificadorLinha2;
		this.nomeTabela = nomeTabela;
		this.nomeUsuario = nomeUsuario;
		this.acaoUsuario = acaoUsuario;
		this.tipoUsuario = tipoUsuario;
		this.nomeColuna = nomeColuna;
		this.conteudoAnterior = conteudoAnterior;
		this.conteudoAtual = conteudoAtual;
		this.dataHora = dataHora;
	}
	
	public String getAcaoUsuario() {
		return acaoUsuario;
	}
	public void setAcaoUsuario(String acaoUsuario) {
		this.acaoUsuario = acaoUsuario;
	}
	public String getConteudoAnterior() {
		return conteudoAnterior;
	}
	public void setConteudoAnterior(String conteudoAnterior) {
		this.conteudoAnterior = conteudoAnterior;
	}
	public String getConteudoAtual() {
		return conteudoAtual;
	}
	public void setConteudoAtual(String conteudoAtual) {
		this.conteudoAtual = conteudoAtual;
	}
	public String getDataHora() {
		return dataHora;
	}
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	public String getIdentificadorLinha1() {
		return identificadorLinha1;
	}
	public void setIdentificadorLinha1(String identificadorLinha1) {
		this.identificadorLinha1 = identificadorLinha1;
	}
	public String getIdentificadorLinha2() {
		return identificadorLinha2;
	}
	public void setIdentificadorLinha2(String identificadorLinha2) {
		this.identificadorLinha2 = identificadorLinha2;
	}
	public String getNomeColuna() {
		return nomeColuna;
	}
	public void setNomeColuna(String nomeColuna) {
		this.nomeColuna = nomeColuna;
	}
	public String getNomeTabela() {
		return nomeTabela;
	}
	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	
}
