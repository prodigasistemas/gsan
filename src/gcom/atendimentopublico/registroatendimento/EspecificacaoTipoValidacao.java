package gcom.atendimentopublico.registroatendimento;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * 
 * Classe da tabela atendimentopublico.solicitacao_tipo_especificacao
 *
 * @author Rafael Santos
 * @date 26/10/2006
 */
public class EspecificacaoTipoValidacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;
    
    /** nullable persistent field */
    private String descricaoEspecificacaoTipoValidacao;

    private char codigoConstante;

    private short indicadorUso;

    private Date ultimaAlteracao;
    
    public final static char ALTERACAO_CADASTRAL = 'I';
    
    public final static char ALTERACAO_CONTA = 'C';
    
    public final static char VENCIMENTO_ALTERNATIVO = 'V';
    
    public final static char TRANSFERENCIA_DEBITO = 'T';
    
	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo codigoConstante.
	 */
	public char getCodigoConstante() {
		return codigoConstante;
	}


	/**
	 * @param codigoConstante O codigoConstante a ser setado.
	 */
	public void setCodigoConstante(char codigoConstante) {
		this.codigoConstante = codigoConstante;
	}


	/**
	 * @return Retorna o campo descricaoEspecificacaoTipoValidacao.
	 */
	public String getDescricaoEspecificacaoTipoValidacao() {
		return descricaoEspecificacaoTipoValidacao;
	}


	/**
	 * @param descricaoEspecificacaoTipoValidacao O descricaoEspecificacaoTipoValidacao a ser setado.
	 */
	public void setDescricaoEspecificacaoTipoValidacao(
			String descricaoEspecificacaoTipoValidacao) {
		this.descricaoEspecificacaoTipoValidacao = descricaoEspecificacaoTipoValidacao;
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
	 * @return Retorna o campo indicadorUso.
	 */
	public short getIndicadorUso() {
		return indicadorUso;
	}


	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}


	/**
	 * @return Retorna o campo solicitacaoTipoEspecificacao.
	 */
	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacao() {
		return solicitacaoTipoEspecificacao;
	}


	/**
	 * @param solicitacaoTipoEspecificacao O solicitacaoTipoEspecificacao a ser setado.
	 */
	public void setSolicitacaoTipoEspecificacao(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
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


}
