package gcom.gui.cobranca;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros
 * para realização da da transferência de débitos e créditos entre imóveis.
 * 
 * @author Raphael Rossiter
 * @date 06/06/2007
 */
public class TransferenciaDebitoCreditoDadosImovelActionForm extends ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idRegistroAtendimento;
	
	private String descricaoEspecificacaoRA;
	
	private String idImovelOrigem;
	
	private String inscricaoImovelOrigem;
	
	private String nomeClienteUsuarioImovelOrigem;
	
	private String descricaoLigacaoAguaSituacaoImovelOrigem;
	
	private String descricaoLigacaoEsgotoSituacaoImovelOrigem;
	
	private String idImovelDestino;
	
	private String inscricaoImovelDestino;
	
	private String nomeClienteUsuarioImovelDestino;
	
	private String descricaoLigacaoAguaSituacaoImovelDestino;
	
	private String descricaoLigacaoEsgotoSituacaoImovelDestino;
	
	private String indicadorEmissao;
	
	private String indicadorTipoEmissao;

	
	public String getDescricaoEspecificacaoRA() {
		return descricaoEspecificacaoRA;
	}

	public void setDescricaoEspecificacaoRA(String descricaoEspecificacaoRA) {
		this.descricaoEspecificacaoRA = descricaoEspecificacaoRA;
	}

	public String getNomeClienteUsuarioImovelDestino() {
		return nomeClienteUsuarioImovelDestino;
	}

	public void setNomeClienteUsuarioImovelDestino(
			String nomeClienteUsuarioImovelDestino) {
		this.nomeClienteUsuarioImovelDestino = nomeClienteUsuarioImovelDestino;
	}

	public String getNomeClienteUsuarioImovelOrigem() {
		return nomeClienteUsuarioImovelOrigem;
	}

	public void setNomeClienteUsuarioImovelOrigem(String nomeClienteUsuarioImovelOrigem) {
		this.nomeClienteUsuarioImovelOrigem = nomeClienteUsuarioImovelOrigem;
	}

	public String getIdImovelDestino() {
		return idImovelDestino;
	}

	public void setIdImovelDestino(String idImovelDestino) {
		this.idImovelDestino = idImovelDestino;
	}

	public String getIdImovelOrigem() {
		return idImovelOrigem;
	}

	public void setIdImovelOrigem(String idImovelOrigem) {
		this.idImovelOrigem = idImovelOrigem;
	}

	public String getDescricaoLigacaoAguaSituacaoImovelDestino() {
		return descricaoLigacaoAguaSituacaoImovelDestino;
	}

	public void setDescricaoLigacaoAguaSituacaoImovelDestino(
			String descricaoLigacaoAguaSituacaoImovelDestino) {
		this.descricaoLigacaoAguaSituacaoImovelDestino = descricaoLigacaoAguaSituacaoImovelDestino;
	}

	public String getDescricaoLigacaoAguaSituacaoImovelOrigem() {
		return descricaoLigacaoAguaSituacaoImovelOrigem;
	}

	public void setDescricaoLigacaoAguaSituacaoImovelOrigem(
			String descricaoLigacaoAguaSituacaoImovelOrigem) {
		this.descricaoLigacaoAguaSituacaoImovelOrigem = descricaoLigacaoAguaSituacaoImovelOrigem;
	}

	public String getDescricaoLigacaoEsgotoSituacaoImovelDestino() {
		return descricaoLigacaoEsgotoSituacaoImovelDestino;
	}

	public void setDescricaoLigacaoEsgotoSituacaoImovelDestino(
			String descricaoLigacaoEsgotoSituacaoImovelDestino) {
		this.descricaoLigacaoEsgotoSituacaoImovelDestino = descricaoLigacaoEsgotoSituacaoImovelDestino;
	}

	public String getDescricaoLigacaoEsgotoSituacaoImovelOrigem() {
		return descricaoLigacaoEsgotoSituacaoImovelOrigem;
	}

	public void setDescricaoLigacaoEsgotoSituacaoImovelOrigem(
			String descricaoLigacaoEsgotoSituacaoImovelOrigem) {
		this.descricaoLigacaoEsgotoSituacaoImovelOrigem = descricaoLigacaoEsgotoSituacaoImovelOrigem;
	}

	public String getIdRegistroAtendimento() {
		return idRegistroAtendimento;
	}

	public void setIdRegistroAtendimento(String idRegistroAtendimento) {
		this.idRegistroAtendimento = idRegistroAtendimento;
	}

	public String getInscricaoImovelDestino() {
		return inscricaoImovelDestino;
	}

	public void setInscricaoImovelDestino(String inscricaoImovelDestino) {
		this.inscricaoImovelDestino = inscricaoImovelDestino;
	}

	public String getInscricaoImovelOrigem() {
		return inscricaoImovelOrigem;
	}

	public void setInscricaoImovelOrigem(String inscricaoImovelOrigem) {
		this.inscricaoImovelOrigem = inscricaoImovelOrigem;
	}

	public String getIndicadorEmissao() {
		return indicadorEmissao;
	}

	public void setIndicadorEmissao(String indicadorEmissao) {
		this.indicadorEmissao = indicadorEmissao;
	}

	public String getIndicadorTipoEmissao() {
		return indicadorTipoEmissao;
	}

	public void setIndicadorTipoEmissao(String indicadorTipoEmissao) {
		this.indicadorTipoEmissao = indicadorTipoEmissao;
	}
	
	
	
}
