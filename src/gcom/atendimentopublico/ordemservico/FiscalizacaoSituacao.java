package gcom.atendimentopublico.ordemservico;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.cobranca.CobrancaSituacao;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FiscalizacaoSituacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	 public final static Integer LIGADO_CLANDESTINO_DE_AGUA = new Integer(10);
	    
	 public final static Integer LIGADO_CLANDESTINO_DE_ESGOTO = new Integer(16);
	    
	 public final static Integer LIGADO_CLANDESTINO_DE_AGUA_ESGOTO = new Integer(17);
	

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricaoFiscalizacaoSituacao;

    /** persistent field */
    private short indicadorMedido;

    /** persistent field */
    private short indicadorAguaSituacao;

    /** persistent field */
    private short indicadorEsgotoSituacao;

    /** persistent field */
    private short indicadorHidrometroCapacidade;

    /** persistent field */
    //private short indicadorValorCalculo;

    /** persistent field */
    private short indicadorConsumoCalculo;

    /** persistent field */
    private short indicadorLigacaoDataAtualiza;

    /** persistent field */
    private short indicadorNotificacaoEsgoto;
    
    /** persistent field */
    //private short indicadorCorteSupressaoEmissao;

    /** persistent field */
    //private short indicadorLigacaoSituacaoAtualiza;

    /** persistent field */
    //private short indicadorExecaoAtualiza;
    
    /** persistent field */
    private short indicadorOpcaoMedicao;

    /** persistent field */
    private Date ultimaAlteracao;
    
    private Short indicadorAtualizacaoAutosInfracao;

    /** persistent field */
    private Set resumoCobrancaAcaos;

    /** persistent field */
    private Set fiscalizacaoSituacaoEsgotos;

    /** persistent field */
    private Set fiscalizacaoSituacaoAguas;

    /** persistent field */
    private Set ordemServicos;

    /** persistent field */
    private Set fiscalizacaoSituacaoHidrometroCapacidades;

    /** persistent field */
    private Set fiscalizacaoSituacaoServicoACobrars;
    
    private Short indicadorVerificarReincidencia;
    
    private CobrancaSituacao cobrancaSituacao;
    
    private Short indicadorExibirFormulario;
    
    private AtendimentoMotivoEncerramento atendimentoMotivoEncerramento;
    
    public final static short INDICADOR_SIM = new Short("1");

    public String toString() {
        return new ToStringBuilder(this)
            .append("fzstId", getId())
            .toString();
    }
    
    

	public short getIndicadorOpcaoMedicao() {
		return indicadorOpcaoMedicao;
	}



	public void setIndicadorOpcaoMedicao(short indicadorOpcaoMedicao) {
		this.indicadorOpcaoMedicao = indicadorOpcaoMedicao;
	}



	/**
	 * @return Retorna o campo descricaoFiscalizacaoSituacao.
	 */
	public String getDescricaoFiscalizacaoSituacao() {
		return descricaoFiscalizacaoSituacao;
	}

	/**
	 * @param descricaoFiscalizacaoSituacao O descricaoFiscalizacaoSituacao a ser setado.
	 */
	public void setDescricaoFiscalizacaoSituacao(
			String descricaoFiscalizacaoSituacao) {
		this.descricaoFiscalizacaoSituacao = descricaoFiscalizacaoSituacao;
	}

	/**
	 * @return Retorna o campo fiscalizacaoSituacaoAguas.
	 */
	public Set getFiscalizacaoSituacaoAguas() {
		return fiscalizacaoSituacaoAguas;
	}

	/**
	 * @param fiscalizacaoSituacaoAguas O fiscalizacaoSituacaoAguas a ser setado.
	 */
	public void setFiscalizacaoSituacaoAguas(Set fiscalizacaoSituacaoAguas) {
		this.fiscalizacaoSituacaoAguas = fiscalizacaoSituacaoAguas;
	}

	/**
	 * @return Retorna o campo fiscalizacaoSituacaoEsgotos.
	 */
	public Set getFiscalizacaoSituacaoEsgotos() {
		return fiscalizacaoSituacaoEsgotos;
	}

	/**
	 * @param fiscalizacaoSituacaoEsgotos O fiscalizacaoSituacaoEsgotos a ser setado.
	 */
	public void setFiscalizacaoSituacaoEsgotos(Set fiscalizacaoSituacaoEsgotos) {
		this.fiscalizacaoSituacaoEsgotos = fiscalizacaoSituacaoEsgotos;
	}

	/**
	 * @return Retorna o campo fiscalizacaoSituacaoHidrometroCapacidades.
	 */
	public Set getFiscalizacaoSituacaoHidrometroCapacidades() {
		return fiscalizacaoSituacaoHidrometroCapacidades;
	}

	/**
	 * @param fiscalizacaoSituacaoHidrometroCapacidades O fiscalizacaoSituacaoHidrometroCapacidades a ser setado.
	 */
	public void setFiscalizacaoSituacaoHidrometroCapacidades(
			Set fiscalizacaoSituacaoHidrometroCapacidades) {
		this.fiscalizacaoSituacaoHidrometroCapacidades = fiscalizacaoSituacaoHidrometroCapacidades;
	}

	/**
	 * @return Retorna o campo fiscalizacaoSituacaoServicoACobrars.
	 */
	public Set getFiscalizacaoSituacaoServicoACobrars() {
		return fiscalizacaoSituacaoServicoACobrars;
	}

	/**
	 * @param fiscalizacaoSituacaoServicoACobrars O fiscalizacaoSituacaoServicoACobrars a ser setado.
	 */
	public void setFiscalizacaoSituacaoServicoACobrars(
			Set fiscalizacaoSituacaoServicoACobrars) {
		this.fiscalizacaoSituacaoServicoACobrars = fiscalizacaoSituacaoServicoACobrars;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo indicadorAguaSituacao.
	 */
	public short getIndicadorAguaSituacao() {
		return indicadorAguaSituacao;
	}

	/**
	 * @param indicadorAguaSituacao O indicadorAguaSituacao a ser setado.
	 */
	public void setIndicadorAguaSituacao(short indicadorAguaSituacao) {
		this.indicadorAguaSituacao = indicadorAguaSituacao;
	}

	/**
	 * @return Retorna o campo indicadorConsumoCalculo.
	 */
	public short getIndicadorConsumoCalculo() {
		return indicadorConsumoCalculo;
	}

	/**
	 * @param indicadorConsumoCalculo O indicadorConsumoCalculo a ser setado.
	 */
	public void setIndicadorConsumoCalculo(short indicadorConsumoCalculo) {
		this.indicadorConsumoCalculo = indicadorConsumoCalculo;
	}

	/**
	 * @return Retorna o campo indicadorCorteSupressaoEmissao.
	 */
	/*public short getIndicadorCorteSupressaoEmissao() {
		return indicadorCorteSupressaoEmissao;
	}*/

	/**
	 * @param indicadorCorteSupressaoEmissao O indicadorCorteSupressaoEmissao a ser setado.
	 */
	/*public void setIndicadorCorteSupressaoEmissao(
			short indicadorCorteSupressaoEmissao) {
		this.indicadorCorteSupressaoEmissao = indicadorCorteSupressaoEmissao;
	}*/

	/**
	 * @return Retorna o campo indicadorEsgotoSituacao.
	 */
	public short getIndicadorEsgotoSituacao() {
		return indicadorEsgotoSituacao;
	}

	/**
	 * @param indicadorEsgotoSituacao O indicadorEsgotoSituacao a ser setado.
	 */
	public void setIndicadorEsgotoSituacao(short indicadorEsgotoSituacao) {
		this.indicadorEsgotoSituacao = indicadorEsgotoSituacao;
	}

	/**
	 * @return Retorna o campo indicadorExecaoAtualiza.
	 */
	/*public short getIndicadorExecaoAtualiza() {
		return indicadorExecaoAtualiza;
	}*/

	/**
	 * @param indicadorExecaoAtualiza O indicadorExecaoAtualiza a ser setado.
	 */
	/*public void setIndicadorExecaoAtualiza(short indicadorExecaoAtualiza) {
		this.indicadorExecaoAtualiza = indicadorExecaoAtualiza;
	}*/

	/**
	 * @return Retorna o campo indicadorHidrometroCapacidade.
	 */
	public short getIndicadorHidrometroCapacidade() {
		return indicadorHidrometroCapacidade;
	}

	/**
	 * @param indicadorHidrometroCapacidade O indicadorHidrometroCapacidade a ser setado.
	 */
	public void setIndicadorHidrometroCapacidade(short indicadorHidrometroCapacidade) {
		this.indicadorHidrometroCapacidade = indicadorHidrometroCapacidade;
	}

	/**
	 * @return Retorna o campo indicadorLigacaoDataAtualiza.
	 */
	public short getIndicadorLigacaoDataAtualiza() {
		return indicadorLigacaoDataAtualiza;
	}

	/**
	 * @param indicadorLigacaoDataAtualiza O indicadorLigacaoDataAtualiza a ser setado.
	 */
	public void setIndicadorLigacaoDataAtualiza(short indicadorLigacaoDataAtualiza) {
		this.indicadorLigacaoDataAtualiza = indicadorLigacaoDataAtualiza;
	}

	/**
	 * @return Retorna o campo indicadorLigacaoSituacaoAtualiza.
	 */
	/*public short getIndicadorLigacaoSituacaoAtualiza() {
		return indicadorLigacaoSituacaoAtualiza;
	}*/

	/**
	 * @param indicadorLigacaoSituacaoAtualiza O indicadorLigacaoSituacaoAtualiza a ser setado.
	 */
	/*public void setIndicadorLigacaoSituacaoAtualiza(
			short indicadorLigacaoSituacaoAtualiza) {
		this.indicadorLigacaoSituacaoAtualiza = indicadorLigacaoSituacaoAtualiza;
	}*/

	/**
	 * @return Retorna o campo indicadorMedido.
	 */
	public short getIndicadorMedido() {
		return indicadorMedido;
	}

	/**
	 * @param indicadorMedido O indicadorMedido a ser setado.
	 */
	public void setIndicadorMedido(short indicadorMedido) {
		this.indicadorMedido = indicadorMedido;
	}

	/**
	 * @return Retorna o campo indicadorValorCalculo.
	 */
	/*public short getIndicadorValorCalculo() {
		return indicadorValorCalculo;
	}*/

	/**
	 * @param indicadorValorCalculo O indicadorValorCalculo a ser setado.
	 */
	/*public void setIndicadorValorCalculo(short indicadorValorCalculo) {
		this.indicadorValorCalculo = indicadorValorCalculo;
	}*/

	/**
	 * @return Retorna o campo ordemServicos.
	 */
	public Set getOrdemServicos() {
		return ordemServicos;
	}

	/**
	 * @param ordemServicos O ordemServicos a ser setado.
	 */
	public void setOrdemServicos(Set ordemServicos) {
		this.ordemServicos = ordemServicos;
	}

	/**
	 * @return Retorna o campo resumoCobrancaAcaos.
	 */
	public Set getResumoCobrancaAcaos() {
		return resumoCobrancaAcaos;
	}

	/**
	 * @param resumoCobrancaAcaos O resumoCobrancaAcaos a ser setado.
	 */
	public void setResumoCobrancaAcaos(Set resumoCobrancaAcaos) {
		this.resumoCobrancaAcaos = resumoCobrancaAcaos;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Short getIndicadorAtualizacaoAutosInfracao() {
		return indicadorAtualizacaoAutosInfracao;
	}

	public void setIndicadorAtualizacaoAutosInfracao(
			Short indicadorAtualizacaoAutosInfracao) {
		this.indicadorAtualizacaoAutosInfracao = indicadorAtualizacaoAutosInfracao;
	}



	public Short getIndicadorVerificarReincidencia() {
		return indicadorVerificarReincidencia;
	}



	public void setIndicadorVerificarReincidencia(
			Short indicadorVerificarReincidencia) {
		this.indicadorVerificarReincidencia = indicadorVerificarReincidencia;
	}



	public CobrancaSituacao getCobrancaSituacao() {
		return cobrancaSituacao;
	}

	public void setCobrancaSituacao(CobrancaSituacao cobrancaSituacao) {
		this.cobrancaSituacao = cobrancaSituacao;
	}



	public Short getIndicadorExibirFormulario() {
		return indicadorExibirFormulario;
	}



	public void setIndicadorExibirFormulario(Short indicadorExibirFormulario) {
		this.indicadorExibirFormulario = indicadorExibirFormulario;
	}

	public AtendimentoMotivoEncerramento getAtendimentoMotivoEncerramento() {
		return atendimentoMotivoEncerramento;
	}

	public void setAtendimentoMotivoEncerramento(
			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento) {
		this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
	}

	public short getIndicadorNotificacaoEsgoto() {
		return indicadorNotificacaoEsgoto;
	}

	public void setIndicadorNotificacaoEsgoto(short indicadorNotificacaoEsgoto) {
		this.indicadorNotificacaoEsgoto = indicadorNotificacaoEsgoto;
	}
}
