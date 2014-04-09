package gcom.cadastro.unidade;

import gcom.atendimentopublico.ordemservico.FiltroUnidadeRepavimentadoraCustoPavimentoRua;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;


/**
 * Descrição da classe Unidade Repavimentadora Custo Pavimento Rua
 * 
 * @author Hugo Leonardo
 * @date 20/12/2010
 */
@ControleAlteracao()
public class UnidadeRepavimentadoraCustoPavimentoRua extends ObjetoTransacao{
	
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_CUSTO_PAVIMENTO_INSERIR = 1734; //Operacao.OPERACAO_INSERIR_CUSTO_PAVIMENTO
	public static final int ATRIBUTOS_CUSTO_PAVIMENTO_ATUALIZAR = 1736; //Operacao.OPERACAO_ATUALIZAR_CUSTO_PAVIMENTO
	public static final int ATRIBUTOS_CUSTO_PAVIMENTO_EXCLUIR = 1737; //Operacao.OPERACAO_EXCLUIR_CUSTO_PAVIMENTO

	/** identifier field */
	private Integer id;
	
	/** identifier field */
	@ControleAlteracao(value=FiltroUnidadeRepavimentadoraCustoPavimentoRua.UNIDADE_REPAVIMENTADORA)
	private UnidadeOrganizacional unidadeRepavimentadora;

	/** persistent field */
	@ControleAlteracao(value=FiltroUnidadeRepavimentadoraCustoPavimentoRua.PAVIMENTO_RUA)
	private gcom.cadastro.imovel.PavimentoRua pavimentoRua;
	
	/** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CUSTO_PAVIMENTO_INSERIR,ATRIBUTOS_CUSTO_PAVIMENTO_ATUALIZAR,ATRIBUTOS_CUSTO_PAVIMENTO_EXCLUIR})
	private BigDecimal valorPavimento;
	
	/** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CUSTO_PAVIMENTO_INSERIR,ATRIBUTOS_CUSTO_PAVIMENTO_ATUALIZAR,ATRIBUTOS_CUSTO_PAVIMENTO_EXCLUIR})
	private Date dataVigenciaInicial;
	
	/** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CUSTO_PAVIMENTO_INSERIR,ATRIBUTOS_CUSTO_PAVIMENTO_ATUALIZAR,ATRIBUTOS_CUSTO_PAVIMENTO_EXCLUIR})
	private Date dataVigenciaFinal;
	
	/** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CUSTO_PAVIMENTO_INSERIR,ATRIBUTOS_CUSTO_PAVIMENTO_ATUALIZAR})
	private Date ultimaAlteracao;

	/** default constructor */
	public UnidadeRepavimentadoraCustoPavimentoRua() {
		
	}
	
    /** minimal constructor */
    public UnidadeRepavimentadoraCustoPavimentoRua(UnidadeOrganizacional unidadeRepavimentadora, PavimentoRua pavimentoRua, 
    		BigDecimal valorPavimento, Date dataVigenciaInicial, Date ultimaAlteracao) {
    	
        this.unidadeRepavimentadora = unidadeRepavimentadora;
        this.pavimentoRua = pavimentoRua;
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

	public PavimentoRua getPavimentoRua() {
		return pavimentoRua;
	}

	public void setPavimentoRua(PavimentoRua pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
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

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada(){
		String []labels = {"Unid. Repav. Custo Pav. Rua.", 
				"Pavimento Rua",
				"Valor Pavimento."
				};
			return labels;		
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada(){
		String []atributos = {
				"id", "pavimentoRua.descricao", "valorPavimento"};
		
		return atributos;		
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		
		return filtro;
	}
	
	public Filtro retornaFiltro() {
		FiltroUnidadeRepavimentadoraCustoPavimentoRua filtro = new FiltroUnidadeRepavimentadoraCustoPavimentoRua();

		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraCustoPavimentoRua.UNIDADE_REPAVIMENTADORA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraCustoPavimentoRua.PAVIMENTO_RUA);
		filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeRepavimentadoraCustoPavimentoRua.ID, this.getId()));
		
		return filtro;
	}
    
}
