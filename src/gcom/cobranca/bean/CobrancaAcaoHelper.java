package gcom.cobranca.bean;

import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;

/**
 * [UC0312] Inserir Ação de Cobrança
 * 
 * @author Sávio Luiz
 * @date 04/12/2007
 */
public class CobrancaAcaoHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	/** nullable persistent field */
	private String descricaoCobrancaAcao;

	/** nullable persistent field */
	private String indicadorObrigatoriedade;

	/** nullable persistent field */
	private String indicadorRepeticao;

	/** nullable persistent field */
	private String indicadorSuspensaoAbastecimento;

	/** nullable persistent field */
	private String numeroDiasValidade;

	/** nullable persistent field */
	private String numeroDiasMinimoAcaoPrecedente;

	/** nullable persistent field */
	private String indicadorUso;

	/** nullable persistent field */
	private String ultimaAlteracao;

	/** nullable persistent field */
	private String indicadorCobrancaDebACobrar;
	
	/** persistent field */
	private String indicadorCreditosARealizar;
	
	/** persistent field */
	private String indicadorNotasPromissoria; 

	/** nullable persistent field */
	private String indicadorGeracaoTaxa;

	/** nullable persistent field */
	private String ordemRealizacao;

	/** nullable persistent field */
	private String indicadorAcrescimoImpontualidade;

	/** persistent field */
	private String idCobrancaAcaoPredecessora;

	/** persistent field */
	private String idLigacaoEsgotoSituacao;

	/** persistent field */
	private String idDocumentoTipo;

	/** persistent field */
	private String idLigacaoAguaSituacao;

	/** persistent field */
	private String idServicoTipo;

	/** persistent field */
	private String idCobrancaCriterio;

	/** persistent field */
	private String indicadorCronograma;

	/** persistent field */
	private String indicadorBoletim;

	/** persistent field */
	private String indicadorDebito;

	/** persistent field */
	private String numeroDiasVencimento;

	/** persistent field */
	private String descricaoCobrancaCriterio;

	/** persistent field */
	private String descricaoServicoTipo;
	
	/** persistent field */
	private String indicadorMetasCronograma;
	
	/** persistent field */
	private String indicadorOrdenamentoCronograma;
	
	/** persistent field */
	private String indicadorOrdenamentoEventual;
	
	/** persistent field */
	private String indicadorDebitoInterfereAcao;
	
	/** persistent field */
	private String numeroDiasRemuneracaoTerceiro;
	
	/** persistent field */
	private Usuario usuarioLogado;
	
	/** persistent field */
	private String indicadorOrdenarMaiorValor;
	
	/** persistent field */
	private String indicadorValidarItem;

	/** full constructor */
	public CobrancaAcaoHelper(String descricaoCobrancaAcao,
			String indicadorObrigatoriedade, String indicadorRepeticao,
			String indicadorSuspensaoAbastecimento, String numeroDiasValidade,
			String numeroDiasMinimoAcaoPrecedente, String indicadorUso, String indicadorCobrancaDebACobrar,
			String indicadorGeracaoTaxa, String ordemRealizacao,
			String indicadorAcrescimoImpontualidade,
			String idCobrancaAcaoPredecessora, String idLigacaoEsgotoSituacao,
			String idDocumentoTipo, String idLigacaoAguaSituacao,
			String idServicoTipo, String idCobrancaCriterio,
			String indicadorCronograma, String indicadorBoletim,
			String indicadorDebito, String numeroDiasVencimento,
			String descricaoCobrancaCriterio, String descricaoServicoTipo,
			String indicadorMetasCronograma, String indicadorOrdenamentoCronograma, String indicadorOrdenamentoEventual,
			String indicadorDebitoInterfereAcao, String numeroDiasRemuneracaoTerceiro,
			Usuario usuarioLogado) {
		this.descricaoCobrancaAcao = descricaoCobrancaAcao;
		this.indicadorObrigatoriedade = indicadorObrigatoriedade;
		this.indicadorRepeticao = indicadorRepeticao;
		this.indicadorSuspensaoAbastecimento = indicadorSuspensaoAbastecimento;
		this.numeroDiasValidade = numeroDiasValidade;
		this.numeroDiasMinimoAcaoPrecedente = numeroDiasMinimoAcaoPrecedente;
		this.indicadorUso = indicadorUso;
		this.indicadorCobrancaDebACobrar = indicadorCobrancaDebACobrar;
		this.indicadorGeracaoTaxa = indicadorGeracaoTaxa;
		this.ordemRealizacao = ordemRealizacao;
		this.indicadorAcrescimoImpontualidade = indicadorAcrescimoImpontualidade;
		this.idCobrancaAcaoPredecessora = idCobrancaAcaoPredecessora;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idDocumentoTipo = idDocumentoTipo;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idServicoTipo = idServicoTipo;
		this.idCobrancaCriterio = idCobrancaCriterio;
		this.indicadorCronograma = indicadorCronograma;
		this.indicadorBoletim = indicadorBoletim;
		this.indicadorDebito = indicadorDebito;
		this.numeroDiasVencimento = numeroDiasVencimento;
		this.descricaoCobrancaCriterio = descricaoCobrancaCriterio;
		this.descricaoServicoTipo = descricaoServicoTipo;
		this.indicadorMetasCronograma = indicadorMetasCronograma;
		this.indicadorOrdenamentoCronograma = indicadorOrdenamentoCronograma;
		this.indicadorOrdenamentoEventual = indicadorOrdenamentoEventual;
		this.indicadorDebitoInterfereAcao = indicadorDebitoInterfereAcao;
		this.numeroDiasRemuneracaoTerceiro = numeroDiasRemuneracaoTerceiro;
		this.usuarioLogado = usuarioLogado;

	}
	
	/** full constructor */
	public CobrancaAcaoHelper(String descricaoCobrancaAcao,
			String indicadorObrigatoriedade, String indicadorRepeticao,
			String indicadorSuspensaoAbastecimento, String numeroDiasValidade,
			String numeroDiasMinimoAcaoPrecedente, String indicadorUso, 
			String indicadorCobrancaDebACobrar,
			String indicadorCreditosARealizar,
			String indicadorNotasPromissoria,
			String indicadorGeracaoTaxa, String ordemRealizacao,
			String indicadorAcrescimoImpontualidade,
			String idCobrancaAcaoPredecessora, String idLigacaoEsgotoSituacao,
			String idDocumentoTipo, String idLigacaoAguaSituacao,
			String idServicoTipo, String idCobrancaCriterio,
			String indicadorCronograma, String indicadorBoletim,
			String indicadorDebito, String numeroDiasVencimento,
			String descricaoCobrancaCriterio, String descricaoServicoTipo,
			String indicadorMetasCronograma, String indicadorOrdenamentoCronograma, String indicadorOrdenamentoEventual,
			String indicadorDebitoInterfereAcao, String numeroDiasRemuneracaoTerceiro,
			Usuario usuarioLogado) {
		this.descricaoCobrancaAcao = descricaoCobrancaAcao;
		this.indicadorObrigatoriedade = indicadorObrigatoriedade;
		this.indicadorRepeticao = indicadorRepeticao;
		this.indicadorSuspensaoAbastecimento = indicadorSuspensaoAbastecimento;
		this.numeroDiasValidade = numeroDiasValidade;
		this.numeroDiasMinimoAcaoPrecedente = numeroDiasMinimoAcaoPrecedente;
		this.indicadorUso = indicadorUso;
		this.indicadorCobrancaDebACobrar = indicadorCobrancaDebACobrar;
		this.indicadorCreditosARealizar = indicadorCreditosARealizar;
		this.indicadorNotasPromissoria = indicadorNotasPromissoria;
		this.indicadorGeracaoTaxa = indicadorGeracaoTaxa;
		this.ordemRealizacao = ordemRealizacao;
		this.indicadorAcrescimoImpontualidade = indicadorAcrescimoImpontualidade;
		this.idCobrancaAcaoPredecessora = idCobrancaAcaoPredecessora;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idDocumentoTipo = idDocumentoTipo;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idServicoTipo = idServicoTipo;
		this.idCobrancaCriterio = idCobrancaCriterio;
		this.indicadorCronograma = indicadorCronograma;
		this.indicadorBoletim = indicadorBoletim;
		this.indicadorDebito = indicadorDebito;
		this.numeroDiasVencimento = numeroDiasVencimento;
		this.descricaoCobrancaCriterio = descricaoCobrancaCriterio;
		this.descricaoServicoTipo = descricaoServicoTipo;
		this.indicadorMetasCronograma = indicadorMetasCronograma;
		this.indicadorOrdenamentoCronograma = indicadorOrdenamentoCronograma;
		this.indicadorOrdenamentoEventual = indicadorOrdenamentoEventual;
		this.indicadorDebitoInterfereAcao = indicadorDebitoInterfereAcao;
		this.numeroDiasRemuneracaoTerceiro = numeroDiasRemuneracaoTerceiro;
		this.usuarioLogado = usuarioLogado;
	}
	
	/** full constructor */
	public CobrancaAcaoHelper(String descricaoCobrancaAcao,
			String indicadorObrigatoriedade, String indicadorRepeticao,
			String indicadorSuspensaoAbastecimento, String numeroDiasValidade,
			String numeroDiasMinimoAcaoPrecedente, String indicadorUso, 
			String indicadorCobrancaDebACobrar,
			String indicadorCreditosARealizar,
			String indicadorNotasPromissoria,
			String indicadorGeracaoTaxa, String ordemRealizacao,
			String indicadorAcrescimoImpontualidade,
			String idCobrancaAcaoPredecessora, String idLigacaoEsgotoSituacao,
			String idDocumentoTipo, String idLigacaoAguaSituacao,
			String idServicoTipo, String idCobrancaCriterio,
			String indicadorCronograma, String indicadorBoletim,
			String indicadorDebito, String numeroDiasVencimento,
			String descricaoCobrancaCriterio, String descricaoServicoTipo,
			String indicadorMetasCronograma, String indicadorOrdenamentoCronograma, String indicadorOrdenamentoEventual,
			String indicadorDebitoInterfereAcao, String numeroDiasRemuneracaoTerceiro,
			String indicadorOrdenarMaiorValor, String indicadorValidarItem,
			Usuario usuarioLogado) {
		this.descricaoCobrancaAcao = descricaoCobrancaAcao;
		this.indicadorObrigatoriedade = indicadorObrigatoriedade;
		this.indicadorRepeticao = indicadorRepeticao;
		this.indicadorSuspensaoAbastecimento = indicadorSuspensaoAbastecimento;
		this.numeroDiasValidade = numeroDiasValidade;
		this.numeroDiasMinimoAcaoPrecedente = numeroDiasMinimoAcaoPrecedente;
		this.indicadorUso = indicadorUso;
		this.indicadorCobrancaDebACobrar = indicadorCobrancaDebACobrar;
		this.indicadorCreditosARealizar = indicadorCreditosARealizar;
		this.indicadorNotasPromissoria = indicadorNotasPromissoria;
		this.indicadorGeracaoTaxa = indicadorGeracaoTaxa;
		this.ordemRealizacao = ordemRealizacao;
		this.indicadorAcrescimoImpontualidade = indicadorAcrescimoImpontualidade;
		this.idCobrancaAcaoPredecessora = idCobrancaAcaoPredecessora;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idDocumentoTipo = idDocumentoTipo;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idServicoTipo = idServicoTipo;
		this.idCobrancaCriterio = idCobrancaCriterio;
		this.indicadorCronograma = indicadorCronograma;
		this.indicadorBoletim = indicadorBoletim;
		this.indicadorDebito = indicadorDebito;
		this.numeroDiasVencimento = numeroDiasVencimento;
		this.descricaoCobrancaCriterio = descricaoCobrancaCriterio;
		this.descricaoServicoTipo = descricaoServicoTipo;
		this.indicadorMetasCronograma = indicadorMetasCronograma;
		this.indicadorOrdenamentoCronograma = indicadorOrdenamentoCronograma;
		this.indicadorOrdenamentoEventual = indicadorOrdenamentoEventual;
		this.indicadorDebitoInterfereAcao = indicadorDebitoInterfereAcao;
		this.numeroDiasRemuneracaoTerceiro = numeroDiasRemuneracaoTerceiro;
		this.indicadorOrdenarMaiorValor = indicadorOrdenarMaiorValor;
		this.indicadorValidarItem = indicadorValidarItem; 
		this.usuarioLogado = usuarioLogado;
	}

	/**
	 * @return Retorna o campo descricaoCobrancaAcao.
	 */
	public String getDescricaoCobrancaAcao() {
		return descricaoCobrancaAcao;
	}

	/**
	 * @param descricaoCobrancaAcao O descricaoCobrancaAcao a ser setado.
	 */
	public void setDescricaoCobrancaAcao(String descricaoCobrancaAcao) {
		this.descricaoCobrancaAcao = descricaoCobrancaAcao;
	}

	/**
	 * @return Retorna o campo descricaoCobrancaCriterio.
	 */
	public String getDescricaoCobrancaCriterio() {
		return descricaoCobrancaCriterio;
	}

	/**
	 * @param descricaoCobrancaCriterio O descricaoCobrancaCriterio a ser setado.
	 */
	public void setDescricaoCobrancaCriterio(String descricaoCobrancaCriterio) {
		this.descricaoCobrancaCriterio = descricaoCobrancaCriterio;
	}

	/**
	 * @return Retorna o campo descricaoServicoTipo.
	 */
	public String getDescricaoServicoTipo() {
		return descricaoServicoTipo;
	}

	/**
	 * @param descricaoServicoTipo O descricaoServicoTipo a ser setado.
	 */
	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		this.descricaoServicoTipo = descricaoServicoTipo;
	}

	/**
	 * @return Retorna o campo idCobrancaAcaoPredecessora.
	 */
	public String getIdCobrancaAcaoPredecessora() {
		return idCobrancaAcaoPredecessora;
	}

	/**
	 * @param idCobrancaAcaoPredecessora O idCobrancaAcaoPredecessora a ser setado.
	 */
	public void setIdCobrancaAcaoPredecessora(String idCobrancaAcaoPredecessora) {
		this.idCobrancaAcaoPredecessora = idCobrancaAcaoPredecessora;
	}

	/**
	 * @return Retorna o campo idCobrancaCriterio.
	 */
	public String getIdCobrancaCriterio() {
		return idCobrancaCriterio;
	}

	/**
	 * @param idCobrancaCriterio O idCobrancaCriterio a ser setado.
	 */
	public void setIdCobrancaCriterio(String idCobrancaCriterio) {
		this.idCobrancaCriterio = idCobrancaCriterio;
	}

	/**
	 * @return Retorna o campo idDocumentoTipo.
	 */
	public String getIdDocumentoTipo() {
		return idDocumentoTipo;
	}

	/**
	 * @param idDocumentoTipo O idDocumentoTipo a ser setado.
	 */
	public void setIdDocumentoTipo(String idDocumentoTipo) {
		this.idDocumentoTipo = idDocumentoTipo;
	}

	/**
	 * @return Retorna o campo idLigacaoAguaSituacao.
	 */
	public String getIdLigacaoAguaSituacao() {
		return idLigacaoAguaSituacao;
	}

	/**
	 * @param idLigacaoAguaSituacao O idLigacaoAguaSituacao a ser setado.
	 */
	public void setIdLigacaoAguaSituacao(String idLigacaoAguaSituacao) {
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
	}

	/**
	 * @return Retorna o campo idLigacaoEsgotoSituacao.
	 */
	public String getIdLigacaoEsgotoSituacao() {
		return idLigacaoEsgotoSituacao;
	}

	/**
	 * @param idLigacaoEsgotoSituacao O idLigacaoEsgotoSituacao a ser setado.
	 */
	public void setIdLigacaoEsgotoSituacao(String idLigacaoEsgotoSituacao) {
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
	}

	/**
	 * @return Retorna o campo idServicoTipo.
	 */
	public String getIdServicoTipo() {
		return idServicoTipo;
	}

	/**
	 * @param idServicoTipo O idServicoTipo a ser setado.
	 */
	public void setIdServicoTipo(String idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}

	/**
	 * @return Retorna o campo indicadorAcrescimoImpontualidade.
	 */
	public String getIndicadorAcrescimoImpontualidade() {
		return indicadorAcrescimoImpontualidade;
	}

	/**
	 * @param indicadorAcrescimoImpontualidade O indicadorAcrescimoImpontualidade a ser setado.
	 */
	public void setIndicadorAcrescimoImpontualidade(
			String indicadorAcrescimoImpontualidade) {
		this.indicadorAcrescimoImpontualidade = indicadorAcrescimoImpontualidade;
	}

	/**
	 * @return Retorna o campo indicadorBoletim.
	 */
	public String getIndicadorBoletim() {
		return indicadorBoletim;
	}

	/**
	 * @param indicadorBoletim O indicadorBoletim a ser setado.
	 */
	public void setIndicadorBoletim(String indicadorBoletim) {
		this.indicadorBoletim = indicadorBoletim;
	}

	/**
	 * @return Retorna o campo indicadorCobrancaDebACobrar.
	 */
	public String getIndicadorCobrancaDebACobrar() {
		return indicadorCobrancaDebACobrar;
	}

	/**
	 * @param indicadorCobrancaDebACobrar O indicadorCobrancaDebACobrar a ser setado.
	 */
	public void setIndicadorCobrancaDebACobrar(String indicadorCobrancaDebACobrar) {
		this.indicadorCobrancaDebACobrar = indicadorCobrancaDebACobrar;
	}

	/**
	 * @return Retorna o campo indicadorCronograma.
	 */
	public String getIndicadorCronograma() {
		return indicadorCronograma;
	}

	/**
	 * @param indicadorCronograma O indicadorCronograma a ser setado.
	 */
	public void setIndicadorCronograma(String indicadorCronograma) {
		this.indicadorCronograma = indicadorCronograma;
	}

	/**
	 * @return Retorna o campo indicadorDebito.
	 */
	public String getIndicadorDebito() {
		return indicadorDebito;
	}

	/**
	 * @param indicadorDebito O indicadorDebito a ser setado.
	 */
	public void setIndicadorDebito(String indicadorDebito) {
		this.indicadorDebito = indicadorDebito;
	}

	/**
	 * @return Retorna o campo indicadorGeracaoTaxa.
	 */
	public String getIndicadorGeracaoTaxa() {
		return indicadorGeracaoTaxa;
	}

	/**
	 * @param indicadorGeracaoTaxa O indicadorGeracaoTaxa a ser setado.
	 */
	public void setIndicadorGeracaoTaxa(String indicadorGeracaoTaxa) {
		this.indicadorGeracaoTaxa = indicadorGeracaoTaxa;
	}

	/**
	 * @return Retorna o campo indicadorObrigatoriedade.
	 */
	public String getIndicadorObrigatoriedade() {
		return indicadorObrigatoriedade;
	}

	/**
	 * @param indicadorObrigatoriedade O indicadorObrigatoriedade a ser setado.
	 */
	public void setIndicadorObrigatoriedade(String indicadorObrigatoriedade) {
		this.indicadorObrigatoriedade = indicadorObrigatoriedade;
	}

	/**
	 * @return Retorna o campo indicadorRepeticao.
	 */
	public String getIndicadorRepeticao() {
		return indicadorRepeticao;
	}

	/**
	 * @param indicadorRepeticao O indicadorRepeticao a ser setado.
	 */
	public void setIndicadorRepeticao(String indicadorRepeticao) {
		this.indicadorRepeticao = indicadorRepeticao;
	}

	/**
	 * @return Retorna o campo indicadorSuspensaoAbastecimento.
	 */
	public String getIndicadorSuspensaoAbastecimento() {
		return indicadorSuspensaoAbastecimento;
	}

	/**
	 * @param indicadorSuspensaoAbastecimento O indicadorSuspensaoAbastecimento a ser setado.
	 */
	public void setIndicadorSuspensaoAbastecimento(
			String indicadorSuspensaoAbastecimento) {
		this.indicadorSuspensaoAbastecimento = indicadorSuspensaoAbastecimento;
	}

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * @return Retorna o campo numeroDiasMinimoAcaoPrecedente.
	 */
	public String getNumeroDiasMinimoAcaoPrecedente() {
		return numeroDiasMinimoAcaoPrecedente;
	}

	/**
	 * @param numeroDiasMinimoAcaoPrecedente O numeroDiasMinimoAcaoPrecedente a ser setado.
	 */
	public void setNumeroDiasMinimoAcaoPrecedente(
			String numeroDiasMinimoAcaoPrecedente) {
		this.numeroDiasMinimoAcaoPrecedente = numeroDiasMinimoAcaoPrecedente;
	}

	/**
	 * @return Retorna o campo numeroDiasValidade.
	 */
	public String getNumeroDiasValidade() {
		return numeroDiasValidade;
	}

	/**
	 * @param numeroDiasValidade O numeroDiasValidade a ser setado.
	 */
	public void setNumeroDiasValidade(String numeroDiasValidade) {
		this.numeroDiasValidade = numeroDiasValidade;
	}

	/**
	 * @return Retorna o campo numeroDiasVencimento.
	 */
	public String getNumeroDiasVencimento() {
		return numeroDiasVencimento;
	}

	/**
	 * @param numeroDiasVencimento O numeroDiasVencimento a ser setado.
	 */
	public void setNumeroDiasVencimento(String numeroDiasVencimento) {
		this.numeroDiasVencimento = numeroDiasVencimento;
	}

	/**
	 * @return Retorna o campo ordemRealizacao.
	 */
	public String getOrdemRealizacao() {
		return ordemRealizacao;
	}

	/**
	 * @param ordemRealizacao O ordemRealizacao a ser setado.
	 */
	public void setOrdemRealizacao(String ordemRealizacao) {
		this.ordemRealizacao = ordemRealizacao;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo usuarioLogado.
	 */
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	/**
	 * @param usuarioLogado O usuarioLogado a ser setado.
	 */
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	/**
	 * @return Retorna o campo indicadorDebitoInterfereAcao.
	 */
	public String getIndicadorDebitoInterfereAcao() {
		return indicadorDebitoInterfereAcao;
	}

	/**
	 * @param indicadorDebitoInterfereAcao O indicadorDebitoInterfereAcao a ser setado.
	 */
	public void setIndicadorDebitoInterfereAcao(String indicadorDebitoInterfereAcao) {
		this.indicadorDebitoInterfereAcao = indicadorDebitoInterfereAcao;
	}

	/**
	 * @return Retorna o campo indicadorMetasCronograma.
	 */
	public String getIndicadorMetasCronograma() {
		return indicadorMetasCronograma;
	}

	/**
	 * @param indicadorMetasCronograma O indicadorMetasCronograma a ser setado.
	 */
	public void setIndicadorMetasCronograma(String indicadorMetasCronograma) {
		this.indicadorMetasCronograma = indicadorMetasCronograma;
	}

	/**
	 * @return Retorna o campo indicadorOrdenamentoCronograma.
	 */
	public String getIndicadorOrdenamentoCronograma() {
		return indicadorOrdenamentoCronograma;
	}

	/**
	 * @param indicadorOrdenamentoCronograma O indicadorOrdenamentoCronograma a ser setado.
	 */
	public void setIndicadorOrdenamentoCronograma(
			String indicadorOrdenamentoCronograma) {
		this.indicadorOrdenamentoCronograma = indicadorOrdenamentoCronograma;
	}

	/**
	 * @return Retorna o campo indicadorOrdenamentoEventual.
	 */
	public String getIndicadorOrdenamentoEventual() {
		return indicadorOrdenamentoEventual;
	}

	/**
	 * @param indicadorOrdenamentoEventual O indicadorOrdenamentoEventual a ser setado.
	 */
	public void setIndicadorOrdenamentoEventual(String indicadorOrdenamentoEventual) {
		this.indicadorOrdenamentoEventual = indicadorOrdenamentoEventual;
	}

	/**
	 * @return Retorna o campo numeroDiasRemuneracaoTerceiro.
	 */
	public String getNumeroDiasRemuneracaoTerceiro() {
		return numeroDiasRemuneracaoTerceiro;
	}

	/**
	 * @param numeroDiasRemuneracaoTerceiro O numeroDiasRemuneracaoTerceiro a ser setado.
	 */
	public void setNumeroDiasRemuneracaoTerceiro(
			String numeroDiasRemuneracaoTerceiro) {
		this.numeroDiasRemuneracaoTerceiro = numeroDiasRemuneracaoTerceiro;
	}

	public String getIndicadorCreditosARealizar() {
		return indicadorCreditosARealizar;
	}

	public void setIndicadorCreditosARealizar(String indicadorCreditosARealizar) {
		this.indicadorCreditosARealizar = indicadorCreditosARealizar;
	}

	public String getIndicadorNotasPromissoria() {
		return indicadorNotasPromissoria;
	}

	public void setIndicadorNotasPromissoria(String indicadorNotasPromissoria) {
		this.indicadorNotasPromissoria = indicadorNotasPromissoria;
	}

	public String getIndicadorOrdenarMaiorValor() {
		return indicadorOrdenarMaiorValor;
	}

	public void setIndicadorOrdenarMaiorValor(String indicadorOrdenarMaiorValor) {
		this.indicadorOrdenarMaiorValor = indicadorOrdenarMaiorValor;
	}

	public String getIndicadorValidarItem() {
		return indicadorValidarItem;
	}

	public void setIndicadorValidarItem(String indicadorValidarItem) {
		this.indicadorValidarItem = indicadorValidarItem;
	}
	

}
