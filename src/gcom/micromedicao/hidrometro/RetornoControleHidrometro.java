package gcom.micromedicao.hidrometro;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()

public class RetornoControleHidrometro extends ObjetoTransacao{

	private static final long serialVersionUID = 1L;

	public static final int  OPERACAO_RETORNO_CONTROLE_HIDROMETRO_INSERIR = 1660;
	public static final int  OPERACAO_RETORNO_CONTROLE_HIDROMETRO_ATUALIZAR = 1666;
	public static final int  OPERACAO_RETORNO_CONTROLE_HIDROMETRO_REMOVER = 1667;

	private Integer id;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={OPERACAO_RETORNO_CONTROLE_HIDROMETRO_INSERIR, OPERACAO_RETORNO_CONTROLE_HIDROMETRO_ATUALIZAR, OPERACAO_RETORNO_CONTROLE_HIDROMETRO_REMOVER})
	private String descricao;	

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={OPERACAO_RETORNO_CONTROLE_HIDROMETRO_INSERIR, OPERACAO_RETORNO_CONTROLE_HIDROMETRO_ATUALIZAR, OPERACAO_RETORNO_CONTROLE_HIDROMETRO_REMOVER})
	private Short indicadorGeracao;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={OPERACAO_RETORNO_CONTROLE_HIDROMETRO_INSERIR, OPERACAO_RETORNO_CONTROLE_HIDROMETRO_ATUALIZAR, OPERACAO_RETORNO_CONTROLE_HIDROMETRO_REMOVER})
	private Short indicadorUso;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={OPERACAO_RETORNO_CONTROLE_HIDROMETRO_INSERIR, OPERACAO_RETORNO_CONTROLE_HIDROMETRO_ATUALIZAR, OPERACAO_RETORNO_CONTROLE_HIDROMETRO_REMOVER})
	private Date dataCorrente;


	/**default constructor*/
	public RetornoControleHidrometro() {

	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getIndicadorGeracao() {
		return indicadorGeracao;
	}

	public void setIndicadorGeracao(Short indicadorGeracao) {
		this.indicadorGeracao = indicadorGeracao;
	}

	public Date getDataCorrente() {
		return dataCorrente;
	}

	public void setDataCorrente(Date dataCorrente) {
		this.dataCorrente = dataCorrente;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroRetornoControleHidrometro filtroRetornoControleHidrometro = new FiltroRetornoControleHidrometro();
		filtroRetornoControleHidrometro.adicionarParametro(new ParametroSimples(FiltroRetornoControleHidrometro.ID,this.getId()));       
		return filtroRetornoControleHidrometro;   
	}


	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}


	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroRetornoControleHidrometro.ID,this.getId()));

		return filtro;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}

	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"descricao"};
		return labels;       
	}

	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Descricao"};
		return labels;       
	}

	@Override
	public Date getUltimaAlteracao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		// TODO Auto-generated method stub
		
	}

}
