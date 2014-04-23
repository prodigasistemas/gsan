package gcom.micromedicao;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class TelemetriaRetMot implements Serializable {

	private static final long serialVersionUID = 1L;
    
	//Constantes telemetria ret mot >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
    public final static Integer ID_QUANTIDADE_INVALIDA = new Integer(1);
    public final static Integer ID_DATA_ENVIO_INVALIDA = new Integer(2);
    public final static Integer ID_CODIGO_EMPRESA_INVALIDO = new Integer(3);
    public final static Integer ID_INSCRICAO_INVALIDA = new Integer(4);
    public final static Integer ID_LEITURA_INVALIDA = new Integer(5);
    public final static Integer ID_HIDROMETRO_INVALIDO = new Integer(6);
    public final static Integer ID_MEDICAO_TIPO_INVALIDA = new Integer(7);
    public final static Integer ID_DATA_LEITURA_INVALIDA = new Integer(8);
    public final static Integer ID_MATRICULA_INVALIDA = new Integer(9);
    public final static Integer ID_MATRICULA_INEXISTENTE = new Integer(10);
    public final static Integer ID_DATA_LEITURA_INERVALO_INVALIDO = new Integer(11);
    public final static Integer ID_FATURAMENTO_GRUPO_INVALIDO = new Integer(12);
    public final static Integer ID_QUANTIDADE_DIFERENTE = new Integer(13);
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
    
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoRetorno;

    /** nullable persistent field */
    private Short indicadorUso;
    
    /** nullable persistent field */
    private Short indicadorMovAceito;

    /** persistent field */
    private Date ultimaAlteracao;

	
    public TelemetriaRetMot() {}
    
    public TelemetriaRetMot(Integer id, String descricaoRetorno, Short indicadorUso, Short indicadorMovAceito, Date ultimaAlteracao) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.descricaoRetorno = descricaoRetorno;
		this.indicadorUso = indicadorUso;
		this.indicadorMovAceito = indicadorMovAceito;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getDescricaoRetorno() {
		return descricaoRetorno;
	}

	public void setDescricaoRetorno(String descricaoRetorno) {
		this.descricaoRetorno = descricaoRetorno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorMovAceito() {
		return indicadorMovAceito;
	}

	public void setIndicadorMovAceito(Short indicadorMovAceito) {
		this.indicadorMovAceito = indicadorMovAceito;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
