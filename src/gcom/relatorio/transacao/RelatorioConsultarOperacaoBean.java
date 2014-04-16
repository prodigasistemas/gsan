package gcom.relatorio.transacao;

import java.util.Date;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Rafael Corrêa
 * @created 06/04/2006
 */
public class RelatorioConsultarOperacaoBean implements RelatorioBean {

	private Date dataRealizacao;

	private String  nome;
	
	private String  dados;
	
	private String  outrosDados;

	private String  usuario;
	
	private Integer idOperacaoEfetuada;
	
	private String  campo;
	
	private String  valorAnterior;
	
	private String  valorAtual;
	
	private Date    dataAtualizacao;

	private String  ipUsuario;

	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAguaBean
	 * 
	 * @param codigo
	 *            Description of the Parameter
	 * @param nome
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @param codPref
	 *            Description of the Parameter
	 * @param indicadorUso
	 *            Description of the Parameter
	 */
	public RelatorioConsultarOperacaoBean(Date dataRealizacao, String nome,
			String dados, String outrosDados, String usuario, Integer idOperacaoEfetuada,
			String campo, String valorAnterior, String valorAtual, Date dataAtualizacao,String ipUsuario) {
		
		this.dataRealizacao     = dataRealizacao;
		this.nome               = nome;
		this.dados              = dados;
		this.outrosDados        = outrosDados;
		this.usuario            = usuario;
		this.idOperacaoEfetuada = idOperacaoEfetuada;
		this.campo              = campo;
		this.valorAnterior      = valorAnterior;
		this.valorAtual         = valorAtual;
		this.dataAtualizacao    = dataAtualizacao;
		this.ipUsuario          = ipUsuario;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}

	public String getOutrosDados() {
		return outrosDados;
	}

	public void setOutrosDados(String outrosDados) {
		this.outrosDados = outrosDados;
	}

	/**
	 * @return Retorna o campo campo.
	 */
	public String getCampo() {
		return campo;
	}

	/**
	 * @param campo O campo a ser setado.
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}

	/**
	 * @return Retorna o campo dataAtualizacao.
	 */
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	/**
	 * @param dataAtualizacao O dataAtualizacao a ser setado.
	 */
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	/**
	 * @return Retorna o campo idOperacaoEfetuada.
	 */
	public Integer getIdOperacaoEfetuada() {
		return idOperacaoEfetuada;
	}

	/**
	 * @param idOperacaoEfetuada O idOperacaoEfetuada a ser setado.
	 */
	public void setIdOperacaoEfetuada(Integer idOperacaoEfetuada) {
		this.idOperacaoEfetuada = idOperacaoEfetuada;
	}

	/**
	 * @return Retorna o campo valorAnterior.
	 */
	public String getValorAnterior() {
		return valorAnterior;
	}

	/**
	 * @param valorAnterior O valorAnterior a ser setado.
	 */
	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	/**
	 * @return Retorna o campo valorAtual.
	 */
	public String getValorAtual() {
		return valorAtual;
	}

	/**
	 * @param valorAtual O valorAtual a ser setado.
	 */
	public void setValorAtual(String valorAtual) {
		this.valorAtual = valorAtual;
	}

	public String getIpUsuario() {
		return ipUsuario;
	}

	public void setIpUsuario(String ipUsuario) {
		this.ipUsuario = ipUsuario;
	}
}

