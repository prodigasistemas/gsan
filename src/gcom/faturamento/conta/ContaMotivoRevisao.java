package gcom.faturamento.conta;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ContaMotivoRevisao extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoMotivoRevisaoConta;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    
    public static final Integer REVISAO_POR_PAGAMENTO_COMPROVADO = new Integer("98");
    public static final Integer REVISAO_AUTOMATICA_ESTOURO_CONSUMO = new Integer("91");
    public static final Integer REVISAO_AUTOMATICA_BAIXO_CONSUMO = new Integer("100");
    public static final Integer REVISAO_ENTRADA_DE_PARCELAMENTO = new Integer("101");
    
    public static final Integer REVISAO_POR_ANTIGUIDADE = new Integer("102");
    
    public static final Integer CONTA_EM_CONTRATO_PARCELAMENTO = new Integer("105");
    
    public static final Integer DEBITO_A_COBRAR_EM_CONTRATO_PARCELAMENTO = new Integer("106");

    /** full constructor */
    public ContaMotivoRevisao(String descricaoMotivoRevisaoConta, Short indicadorUso, Date ultimaAlteracao) {
        this.descricaoMotivoRevisaoConta = descricaoMotivoRevisaoConta;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public ContaMotivoRevisao() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoMotivoRevisaoConta() {
        return this.descricaoMotivoRevisaoConta;
    }

    public void setDescricaoMotivoRevisaoConta(String descricaoMotivoRevisaoConta) {
        this.descricaoMotivoRevisaoConta = descricaoMotivoRevisaoConta;
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
		Filtro filtro = new FiltroContaMotivoRevisao();
		
		filtro.adicionarParametro(
				new ParametroSimples(FiltroContaMotivoRevisao.ID, this.id));
		return filtro; 
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricaoMotivoRevisaoConta();
	}
}
