package gcom.relatorio.micromedicao;

import gcom.relatorio.RelatorioBean;

/**
 * @author Thiago Nascimento
 *
 */
public class RelatorioAtualizarLeiturasAnormalidadesCelularBean implements RelatorioBean{

	private String setor;
	
	private String recebido;
	
	private String comLeitura;
	
	private String comAnormalidade;
	
	private String leituraAnormalidade;
	
	private String invalido;
	
	private String codigoAnormalidade;
	
	private String descricaoAnormalidade;
	
	private String quantidade;
	
	private String idSetor;
	
	private String mensagemInvalida;

	/**
	 * @param setor
	 * @param localidade
	 * @param recebido
	 * @param comLeitura
	 * @param comAnormalidade
	 * @param leituraAnormalidade
	 * @param invalido
	 */
	public RelatorioAtualizarLeiturasAnormalidadesCelularBean(String setor,
			String recebido, String comLeitura,
			String comAnormalidade, String leituraAnormalidade, String invalido) {
		super();
		this.setor = setor;
		this.recebido = recebido;
		this.comLeitura = comLeitura;
		this.comAnormalidade = comAnormalidade;
		this.leituraAnormalidade = leituraAnormalidade;
		this.invalido = invalido;
	}

	/**
	 * @param recebido
	 * @param comLeitura
	 * @param comAnormalidade
	 * @param leituraAnormalidade
	 * @param invalido
	 */
	public RelatorioAtualizarLeiturasAnormalidadesCelularBean(String recebido,
			String comLeitura, String comAnormalidade,
			String leituraAnormalidade, String invalido) {
		super();
		this.recebido = recebido;
		this.comLeitura = comLeitura;
		this.comAnormalidade = comAnormalidade;
		this.leituraAnormalidade = leituraAnormalidade;
		this.invalido = invalido;
	}

	/**
	 * @param codigoAnormalidade
	 * @param descricaoAnormalidade
	 * @param quantidade
	 */
	public RelatorioAtualizarLeiturasAnormalidadesCelularBean(
			String codigoAnormalidade, String descricaoAnormalidade,
			String quantidade) {
		super();
		this.codigoAnormalidade = codigoAnormalidade;
		this.descricaoAnormalidade = descricaoAnormalidade;
		this.quantidade = quantidade;
	}

	/**
	 * @param quantidade
	 */
	public RelatorioAtualizarLeiturasAnormalidadesCelularBean(String quantidade) {
		super();
		this.quantidade = quantidade;
	}
	
	public RelatorioAtualizarLeiturasAnormalidadesCelularBean() {
		
	}

	/**
	 * @return Returns the codigoAnormalidade.
	 */
	public String getCodigoAnormalidade() {
		return codigoAnormalidade;
	}

	/**
	 * @param codigoAnormalidade The codigoAnormalidade to set.
	 */
	public void setCodigoAnormalidade(String codigoAnormalidade) {
		this.codigoAnormalidade = codigoAnormalidade;
	}

	/**
	 * @return Returns the comAnormalidade.
	 */
	public String getComAnormalidade() {
		return comAnormalidade;
	}

	/**
	 * @param comAnormalidade The comAnormalidade to set.
	 */
	public void setComAnormalidade(String comAnormalidade) {
		this.comAnormalidade = comAnormalidade;
	}

	/**
	 * @return Returns the comLeitura.
	 */
	public String getComLeitura() {
		return comLeitura;
	}

	/**
	 * @param comLeitura The comLeitura to set.
	 */
	public void setComLeitura(String comLeitura) {
		this.comLeitura = comLeitura;
	}

	/**
	 * @return Returns the descricaoAnormalidade.
	 */
	public String getDescricaoAnormalidade() {
		return descricaoAnormalidade;
	}

	/**
	 * @param descricaoAnormalidade The descricaoAnormalidade to set.
	 */
	public void setDescricaoAnormalidade(String descricaoAnormalidade) {
		this.descricaoAnormalidade = descricaoAnormalidade;
	}

	/**
	 * @return Returns the invalido.
	 */
	public String getInvalido() {
		return invalido;
	}

	/**
	 * @param invalido The invalido to set.
	 */
	public void setInvalido(String invalido) {
		this.invalido = invalido;
	}

	/**
	 * @return Returns the leituraAnormalidade.
	 */
	public String getLeituraAnormalidade() {
		return leituraAnormalidade;
	}

	/**
	 * @param leituraAnormalidade The leituraAnormalidade to set.
	 */
	public void setLeituraAnormalidade(String leituraAnormalidade) {
		this.leituraAnormalidade = leituraAnormalidade;
	}

	/**
	 * @return Returns the quantidade.
	 */
	public String getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade The quantidade to set.
	 */
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @return Returns the recebido.
	 */
	public String getRecebido() {
		return recebido;
	}

	/**
	 * @param recebido The recebido to set.
	 */
	public void setRecebido(String recebido) {
		this.recebido = recebido;
	}

	/**
	 * @return Returns the setor.
	 */
	public String getSetor() {
		return setor;
	}

	/**
	 * @param setor The setor to set.
	 */
	public void setSetor(String setor) {
		this.setor = setor;
	}
	
	
	
	/**
	 * @return Returns the idSetor.
	 */
	public String getIdSetor() {
		return idSetor;
	}

	/**
	 * @param idSetor The idSetor to set.
	 */
	public void setIdSetor(String idSetor) {
		this.idSetor = idSetor;
	}



	public void calcularInvalidos(){
		int recebido = 0;
		if(!this.recebido.equals("")){
			recebido = Integer.parseInt(this.recebido);
		}
		int comLeitura = 0;
		if(!this.comLeitura.equals("")){
			comLeitura = Integer.parseInt(this.comLeitura);
		}
		int comAnormalidade = 0;
		if(!this.comAnormalidade.equals("")){
			comAnormalidade = Integer.parseInt(this.comAnormalidade);
		}
		int leituraAnormalidade = 0;
		if(!this.leituraAnormalidade.equals("")){
			leituraAnormalidade = Integer.parseInt(this.leituraAnormalidade);
		}
		int invalido = recebido - comLeitura - comAnormalidade - leituraAnormalidade;
		if(invalido < 0){
			invalido = 0;
		}
		this.setInvalido(""+invalido);
	}

	/**
	 * @return Returns the mensagemInvalida.
	 */
	public String getMensagemInvalida() {
		return mensagemInvalida;
	}

	/**
	 * @param mensagemInvalida The mensagemInvalida to set.
	 */
	public void setMensagemInvalida(String mensagemInvalida) {
		this.mensagemInvalida = mensagemInvalida;
	}
	
	
}
