package gcom.gui.atendimentopublico.registroatendimento;


import org.apache.struts.validator.ValidatorForm;


/**
 * [UC0538] Filtrar RAs na Agencia Reguladora
 *
 * @author Kássia Albuquerque
 * @date 02/05/2007
 */

public class FiltrarRaDadosAgenciaReguladoraActionForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numeroRa;
	private String motivoReclamacao;
	private String motivoEncerramentoAtendimento;
	private String indicadorSituacaoAgencia;
	private String indicadorSituacaoRA;
	private String periodoReclamacaoInicio;
	private String periodoReclamacaoFim;
	private String periodoRetornoInicio;
	private String periodoRetornoFim;
	private String motivoRetornoAgencia;
	
	public String getIndicadorSituacaoAgencia() {
		return indicadorSituacaoAgencia;
	}
	public void setIndicadorSituacaoAgencia(String indicadorSituacaoAgencia) {
		this.indicadorSituacaoAgencia = indicadorSituacaoAgencia;
	}
	public String getIndicadorSituacaoRA() {
		return indicadorSituacaoRA;
	}
	public void setIndicadorSituacaoRA(String indicadorSituacaoRA) {
		this.indicadorSituacaoRA = indicadorSituacaoRA;
	}
	public String getMotivoEncerramentoAtendimento() {
		return motivoEncerramentoAtendimento;
	}
	public void setMotivoEncerramentoAtendimento(
			String motivoEncerramentoAtendimento) {
		this.motivoEncerramentoAtendimento = motivoEncerramentoAtendimento;
	}
	public String getMotivoReclamacao() {
		return motivoReclamacao;
	}
	public void setMotivoReclamacao(String motivoReclamacao) {
		this.motivoReclamacao = motivoReclamacao;
	}
	public String getMotivoRetornoAgencia() {
		return motivoRetornoAgencia;
	}
	public void setMotivoRetornoAgencia(String motivoRetornoAgencia) {
		this.motivoRetornoAgencia = motivoRetornoAgencia;
	}
	public String getNumeroRa() {
		return numeroRa;
	}
	public void setNumeroRa(String numeroRa) {
		this.numeroRa = numeroRa;
	}
	public String getPeriodoReclamacaoFim() {
		return periodoReclamacaoFim;
	}
	public void setPeriodoReclamacaoFim(String periodoReclamacaoFim) {
		this.periodoReclamacaoFim = periodoReclamacaoFim;
	}
	public String getPeriodoReclamacaoInicio() {
		return periodoReclamacaoInicio;
	}
	public void setPeriodoReclamacaoInicio(String periodoReclamacaoInicio) {
		this.periodoReclamacaoInicio = periodoReclamacaoInicio;
	}
	public String getPeriodoRetornoFim() {
		return periodoRetornoFim;
	}
	public void setPeriodoRetornoFim(String periodoRetornoFim) {
		this.periodoRetornoFim = periodoRetornoFim;
	}
	public String getPeriodoRetornoInicio() {
		return periodoRetornoInicio;
	}
	public void setPeriodoRetornoInicio(String periodoRetornoInicio) {
		this.periodoRetornoInicio = periodoRetornoInicio;
	}
   
	
	/*public RaDadosAgenciaReguladora setDadosAgenciaReguladora(RaDadosAgenciaReguladora raDadosAgenciaReguladora) {
		
		
		RegistroAtendimento registroAtendimento = new RegistroAtendimento();
		registroAtendimento.setId(new Integer(getNumeroRADados()));
		raDadosAgenciaReguladora.setRegistroAtendimento(registroAtendimento);
		
		AgenciaReguladoraMotReclamacao agenciaReguladoraMotReclamacao = new AgenciaReguladoraMotReclamacao();
		agenciaReguladoraMotReclamacao.setId(new Integer(getMotivoReclamacao()));
		raDadosAgenciaReguladora.setAgenciaReguladoraMotReclamacao(agenciaReguladoraMotReclamacao);
		
		if (getIdMotivoEncerramento() != null && !getIdMotivoEncerramento().equalsIgnoreCase("")  ){
			
			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
			atendimentoMotivoEncerramento.setId(new Integer(getIdMotivoEncerramento()));
			raDadosAgenciaReguladora.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
		}
		
		raDadosAgenciaReguladora.setAgenciaReguladora(getNumeroRegistroAgenciaReguladora());
		raDadosAgenciaReguladora.setDataPrevisaoOriginal(Util.converteStringParaDate(getDataPrevisaoOriginal()));
		
		raDadosAgenciaReguladora.setDataPrevisaoAtual(Util.converteStringParaDate(getDataPrevisaoAtual()));
		raDadosAgenciaReguladora.setDescricaoReclamacao(getReclamacao());
		raDadosAgenciaReguladora.setCodigoSituacaoArpe(new Short(getCodigoSituacao()));
		raDadosAgenciaReguladora.setCodigoSituacao(RegistroAtendimento.SITUACAO_PENDENTE);
		
	  return raDadosAgenciaReguladora;
	}*/
	
	
	
	}
