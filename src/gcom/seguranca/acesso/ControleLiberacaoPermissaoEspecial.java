package gcom.seguranca.acesso;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao
/** @author Hibernate CodeGenerator */
public class ControleLiberacaoPermissaoEspecial extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
	
	/** identifier field */
	private Integer id;

	@ControleAlteracao(FiltroControleLiberacaoPermissaoEspecial.FUNCIONALIDADE)
	/** persistent field */
	private gcom.seguranca.acesso.Funcionalidade funcionalidade;
	
	@ControleAlteracao(FiltroControleLiberacaoPermissaoEspecial.PERMISSAO_ESPECIAL)
	/** persistent field */
	private gcom.seguranca.acesso.PermissaoEspecial permissaoEspecial;

	@ControleAlteracao(funcionalidade = 
	{OPERACAO_INSERIR_CONTROLE_LIBERACAO_PERMISSAO_ESPECIAL, OPERACAO_MANTER_CONTROLE_LIBERACAO_PERMISSAO_ESPECIAL})
	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	
    public static final int OPERACAO_INSERIR_CONTROLE_LIBERACAO_PERMISSAO_ESPECIAL = 1654;	
	public static final int OPERACAO_MANTER_CONTROLE_LIBERACAO_PERMISSAO_ESPECIAL = 1673;
		

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	/** full constructor */
	public ControleLiberacaoPermissaoEspecial(
			PermissaoEspecial permissaoEspecial, Funcionalidade funcionalidade,
			Short indicadorUso, Date ultimaAlteracao) {
		
		this.permissaoEspecial = permissaoEspecial;
		this.funcionalidade = funcionalidade;		
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
	public ControleLiberacaoPermissaoEspecial() {
	}

	/** minimal constructor */
	public ControleLiberacaoPermissaoEspecial(gcom.seguranca.acesso.Funcionalidade funcionalidade, gcom.seguranca.acesso.PermissaoEspecial permissaoEspecial) {
		this.permissaoEspecial = permissaoEspecial;
		this.funcionalidade = funcionalidade;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public gcom.seguranca.acesso.Funcionalidade getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(
			gcom.seguranca.acesso.Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public gcom.seguranca.acesso.PermissaoEspecial getPermissaoEspecial() {
		return permissaoEspecial;
	}

	public void setPermissaoEspecial(
			gcom.seguranca.acesso.PermissaoEspecial permissaoEspecial) {
		this.permissaoEspecial = permissaoEspecial;
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
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param other
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof ControleLiberacaoPermissaoEspecial)) {
			return false;
		}
		ControleLiberacaoPermissaoEspecial castOther = (ControleLiberacaoPermissaoEspecial) other;

		return (this.getId().equals(castOther.getId()));
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada(){
		String []atributos = {
				"funcionalidade.descricao", 
				"permissaoEspecial.descricao"};
		
		return atributos;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada(){
		String []labels = { 
				"Funcionalidade",
				"Permissao Especial"
				};
			return labels;		
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroControleLiberacaoPermissaoEspecial.FUNCIONALIDADE);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroControleLiberacaoPermissaoEspecial.PERMISSAO_ESPECIAL);
		
		return filtro;
	}
	
	public Filtro retornaFiltro() {
		FiltroControleLiberacaoPermissaoEspecial filtro = new FiltroControleLiberacaoPermissaoEspecial();

		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroControleLiberacaoPermissaoEspecial.FUNCIONALIDADE);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroControleLiberacaoPermissaoEspecial.PERMISSAO_ESPECIAL);
		filtro.adicionarParametro(new ParametroSimples(FiltroControleLiberacaoPermissaoEspecial.ID,
				this.getId()));
		
		
		return filtro;
	}

}
