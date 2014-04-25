package gcom.gui.atendimentopublico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Arthur Carvalho
 * @date 15/10/2008
 */
public class InserirPerfilLigacaoEsgotoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String descricao;
	
	private String percentualEsgotoConsumidaColetada;
	
	private Short indicadorUso;
	
	private Short indicadorPrincipal;
	
	
    public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPercentualEsgotoConsumidaColetada() {
		return percentualEsgotoConsumidaColetada;
	}

	public void setPercentualEsgotoConsumidaColetada(String percentualEsgoto) {
		this.percentualEsgotoConsumidaColetada = percentualEsgoto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Short getIndicadorPrincipal() {
		return indicadorPrincipal;
	}

	public void setIndicadorPrincipal(Short indicadorPrincipal) {
		this.indicadorPrincipal = indicadorPrincipal;
	}

	
}
