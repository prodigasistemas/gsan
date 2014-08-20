package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.TabelaAuxiliar;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ContaMotivoRetificacao extends TabelaAuxiliar {
	private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String descricao;
    private Short indicadorUso;
    private Date ultimaAlteracao;
    private Integer numeroOcorrenciasNoAno;
    private Short indicadorCompetenciaConsumo;
    
    public final static Integer VALOR_SERVICO_ERRADO = 4;
    public final static Integer DEVOLUCAO_CREDITADA_EM_CONTA = 12;
    
    public final static Integer DEVOLUCAO_PAGAMENTO_CREDITADO_EM_CONTA = 41;
    public final static Integer ALTERACAO_DE_LEITURA_FATURADA = 104;

    public ContaMotivoRetificacao(String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public ContaMotivoRetificacao() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroContaMotivoRetificacao filtro = new FiltroContaMotivoRetificacao();
		
		filtro.adicionarParametro(
				new ParametroSimples(FiltroContaMotivoRetificacao.CODIGO, this.id));
		return filtro; 
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {		
		return this.getDescricao();
	}

	public Integer getNumeroOcorrenciasNoAno() {
		return numeroOcorrenciasNoAno;
	}

	public void setNumeroOcorrenciasNoAno(Integer numeroOcorrenciasNoAno) {
		this.numeroOcorrenciasNoAno = numeroOcorrenciasNoAno;
	}

	public Short getIndicadorCompetenciaConsumo() {
		return indicadorCompetenciaConsumo;
	}

	public void setIndicadorCompetenciaConsumo(Short indicadorCompetenciaConsumo) {
		this.indicadorCompetenciaConsumo = indicadorCompetenciaConsumo;
	}

	
	
}
