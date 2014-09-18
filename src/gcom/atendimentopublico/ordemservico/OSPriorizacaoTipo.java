package gcom.atendimentopublico.ordemservico;


import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

/**
 * [UC1185] Informar Calibragem
 * 
 * @author Thúlio Araújo
 *
 * @date 20/06/2011
 */
public class OSPriorizacaoTipo extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descricaoPriorizacao;
	private String descricaoAbreviado;
	

    
	/**
     * Description of the Field
     */
	public final static Integer LOCALIZACAO_SERVICO = 1;

    /**
     * Description of the Field
     */
    public final static Integer DIAMETRO_REDE = 2;
    /**
     * Description of the Field
     */
	public final static Integer REITERACAO = 3;

    /**
     * Description of the Field
     */
    public final static Integer REINCIDENCIA = 4;
    /**
     * Description of the Field
     */
	public final static Integer DIAS_OS_ABERTA = 5;

    /**
     * Description of the Field
     */
    public final static Integer TIPO_SERVICO = 6;
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public Date getUltimaAlteracao() {
		
		return null;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		
		
	}

	@Override
	public Filtro retornaFiltro() {
		
		return null;
	}

	public String getDescricaoAbreviado() {
		return descricaoAbreviado;
	}

	public void setDescricaoAbreviado(String descricaoAbreviado) {
		this.descricaoAbreviado = descricaoAbreviado;
	}

	public String getDescricaoPriorizacao() {
		return descricaoPriorizacao;
	}

	public void setDescricaoPriorizacao(String descricaoPriorizacao) {
		this.descricaoPriorizacao = descricaoPriorizacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
