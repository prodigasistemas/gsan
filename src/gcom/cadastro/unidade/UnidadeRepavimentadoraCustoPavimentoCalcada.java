package gcom.cadastro.unidade;

import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Descrição da classe Unidade Repavimentadora Custo Pavimento Calçada
 * 
 * @author Hugo Leonardo
 * @date 20/12/2010
 */
public class UnidadeRepavimentadoraCustoPavimentoCalcada implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;
	
	/** identifier field */
	private UnidadeOrganizacional unidadeRepavimentadora;

	/** persistent field */
	private PavimentoCalcada pavimentoCalcada;
	
	/** persistent field */
	private BigDecimal valorPavimento;
	
	/** persistent field */
	private Date dataVigenciaInicial;
	
	/** persistent field */
	private Date dataVigenciaFinal;
	
	/** persistent field */
	private Date ultimaAlteracao;

	/** default constructor */
	public UnidadeRepavimentadoraCustoPavimentoCalcada() {
		
	}
	
    /** minimal constructor */
    public UnidadeRepavimentadoraCustoPavimentoCalcada(UnidadeOrganizacional unidadeRepavimentadora, PavimentoCalcada pavimentoCalcada, 
    		BigDecimal valorPavimento, Date dataVigenciaInicial, Date ultimaAlteracao) {
    	
        this.unidadeRepavimentadora = unidadeRepavimentadora;
        this.pavimentoCalcada = pavimentoCalcada;
        this.valorPavimento = valorPavimento;
        this.dataVigenciaInicial = dataVigenciaInicial;
        this.ultimaAlteracao = ultimaAlteracao;
    }

	public Date getDataVigenciaFinal() {
		return dataVigenciaFinal;
	}

	public void setDataVigenciaFinal(Date dataVigenciaFinal) {
		this.dataVigenciaFinal = dataVigenciaFinal;
	}

	public Date getDataVigenciaInicial() {
		return dataVigenciaInicial;
	}

	public void setDataVigenciaInicial(Date dataVigenciaInicial) {
		this.dataVigenciaInicial = dataVigenciaInicial;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PavimentoCalcada getPavimentoCalcada() {
		return pavimentoCalcada;
	}

	public void setPavimentoCalcada(PavimentoCalcada pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public UnidadeOrganizacional getUnidadeRepavimentadora() {
		return unidadeRepavimentadora;
	}

	public void setUnidadeRepavimentadora(
			UnidadeOrganizacional unidadeRepavimentadora) {
		this.unidadeRepavimentadora = unidadeRepavimentadora;
	}

	public BigDecimal getValorPavimento() {
		return valorPavimento;
	}

	public void setValorPavimento(BigDecimal valorPavimento) {
		this.valorPavimento = valorPavimento;
	}
	
	public boolean isPodeAtualizar(){
		boolean retorno = true;
		
		if(this.getDataVigenciaFinal() != null 
				&& Util.compararData(this.getDataVigenciaFinal(), new Date()) == -1){
			
			retorno = false;
		}
		return retorno;
	}
    
}
