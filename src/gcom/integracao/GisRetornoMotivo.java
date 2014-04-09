package gcom.integracao;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class GisRetornoMotivo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final Integer OPERACAO_SUCESSO = new Integer(1); 
	public static final Integer LOGIN_USUARIO_INEXISTENTE = new Integer(2); 
	public static final Integer IDENTIFICADOR_LOGRADOURO_BAIRRO_INVALIDO = new Integer(3); 
	public static final Integer IDENTIFICADOR_LOGRADOURO_CEP_INVALIDO = new Integer(4); 
	public static final Integer IDENTIFICADOR_LOGRADOURO_BAIRRO_INEXISTENTE = new Integer(5); 	
	public static final Integer IDENTIFICADOR_LOGRADOURO_CEP_INEXISTENTE = new Integer(6); 
	public static final Integer COORDENADA_NORTE_INVALIDA = new Integer(7); 
	public static final Integer COORDENADA_LESTE_INVALIDA = new Integer(8); 
	public static final Integer IDENTIFICADOR_REFERENCIA_ENDERECO_INVALIDO = new Integer(9); 
	public static final Integer IDENTIFICADOR_REFERENCIA_ENDERECO_INEXISTENTE = new Integer(10); 
	public static final Integer REGISTRO_ATENDIMENTO_INEXISTENTE = new Integer(11);
	
    private Integer id;
    
    private String descricao;
    
    private Integer indicadorUso;
    
    private Integer indicadorRetorno;

    private Date ultimaAlteracao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIndicadorRetorno() {
		return indicadorRetorno;
	}

	public void setIndicadorRetorno(Integer indicadorRetorno) {
		this.indicadorRetorno = indicadorRetorno;
	}

	public Integer getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Integer indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
 

   
}
