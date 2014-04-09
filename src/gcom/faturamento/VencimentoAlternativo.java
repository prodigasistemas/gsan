package gcom.faturamento;

import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class VencimentoAlternativo extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_VENCIMENTO_ALTERNATIVO_INSERIR = 54; //Operacao.OPERACAO_INSERIR_VENCIMENTO_ALTERNATIVO
	public static final int ATRIBUTOS_VENCIMENTO_ALTERNATIVO_EXCLUIR = 55; //Operacao.OPERACAO_EXCLUIR_VENCIMENTO_ALTERNATIVO

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date dataImplantacao;

	/**
	 * persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_VENCIMENTO_ALTERNATIVO_INSERIR, ATRIBUTOS_VENCIMENTO_ALTERNATIVO_EXCLUIR})
    private Short dateVencimento;

	/**
	 * persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_VENCIMENTO_ALTERNATIVO_EXCLUIR})
    private Date dateExclusao;

	/**
	 * persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_VENCIMENTO_ALTERNATIVO_INSERIR, ATRIBUTOS_VENCIMENTO_ALTERNATIVO_EXCLUIR})
    private Date ultimaAlteracao;

    /** persistent field */
    private Imovel imovel;

    /** full constructor */
    public VencimentoAlternativo(Date dataImplantacao, Short dateVencimento, Date dateExclusao, Date ultimaAlteracao, Imovel imovel) {
        this.dataImplantacao = dataImplantacao;
        this.dateVencimento = dateVencimento;
        this.dateExclusao = dateExclusao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.imovel = imovel;
    }

    /** default constructor */
    public VencimentoAlternativo() {
    }

    /** minimal constructor */
    public VencimentoAlternativo(Imovel imovel) {
        this.imovel = imovel;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataImplantacao() {
        return this.dataImplantacao;
    }

    public void setDataImplantacao(Date dataImplantacao) {
        this.dataImplantacao = dataImplantacao;
    }

    public Short getDateVencimento() {
        return this.dateVencimento;
    }

    public void setDateVencimento(Short dateVencimento) {
        this.dateVencimento = dateVencimento;
    }

    public Date getDateExclusao() {
        return this.dateExclusao;
    }

    public void setDateExclusao(Date dateExclusao) {
        this.dateExclusao = dateExclusao;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
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
	
	public Filtro retornaFiltro(){
		FiltroVencimentoAlternativo filtroVencimentoAlternativo = new FiltroVencimentoAlternativo();
		
		filtroVencimentoAlternativo.adicionarParametro(
				new ParametroSimples(FiltroVencimentoAlternativo.ID, this.getId()));
		filtroVencimentoAlternativo.adicionarCaminhoParaCarregamentoEntidade("imovel");
		return filtroVencimentoAlternativo; 
	}

}
