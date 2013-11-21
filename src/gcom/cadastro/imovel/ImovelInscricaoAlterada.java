package gcom.cadastro.imovel;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.SetorComercial;
import gcom.faturamento.FaturamentoGrupo;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

@ControleAlteracao()
public class ImovelInscricaoAlterada extends ObjetoTransacao {
	
	public static final int OPERACAO_AUTORIZAR_ALTERACAO_INSCRICAO_IMOVEL = 1776;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Imovel imovel;
	
	private Localidade localidadeAtual;
	private SetorComercial setorComercialAtual;
	private Quadra quadraAtual;
	private QuadraFace quadraFaceAtual;
	private Short loteAtual;
	private Short subLoteAtual;
	
	private Localidade localidadeAnterior;
	private SetorComercial setorComercialAnterior;
	private Quadra quadraAnterior;
	private QuadraFace quadraFaceAnterior;
	private Short loteAnterior;
	private Short subLoteAnterior;
	
	private Short indicadorAtualizado;
	private Short indicadorAtualizacaoExcluida;
	private Short indicadorImovelExcluido;
	private Short indicadorErroAlteracao;
	@ControleAlteracao(funcionalidade={OPERACAO_AUTORIZAR_ALTERACAO_INSCRICAO_IMOVEL})
	private Short indicadorAutorizado;
	
	private Date dataAlteracaoOnline;
	private Date dataAlteracaoBatch;
	private Date ultimaAlteracao;
	private FaturamentoGrupo faturamentoGrupo;
	private Usuario usuarioAlteracao;
	private Usuario usuarioAutorizacao;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public Localidade getLocalidadeAtual() {
		return localidadeAtual;
	}
	public void setLocalidadeAtual(Localidade localidadeAtual) {
		this.localidadeAtual = localidadeAtual;
	}
	public SetorComercial getSetorComercialAtual() {
		return setorComercialAtual;
	}
	public void setSetorComercialAtual(SetorComercial setorComercialAtual) {
		this.setorComercialAtual = setorComercialAtual;
	}
	public Quadra getQuadraAtual() {
		return quadraAtual;
	}
	public void setQuadraAtual(Quadra quadraAtual) {
		this.quadraAtual = quadraAtual;
	}
	public QuadraFace getQuadraFaceAtual() {
		return quadraFaceAtual;
	}
	public void setQuadraFaceAtual(QuadraFace quadraFaceAtual) {
		this.quadraFaceAtual = quadraFaceAtual;
	}
	public Short getLoteAtual() {
		return loteAtual;
	}
	public void setLoteAtual(Short loteAtual) {
		this.loteAtual = loteAtual;
	}
	public Short getSubLoteAtual() {
		return subLoteAtual;
	}
	public void setSubLoteAtual(Short subLoteAtual) {
		this.subLoteAtual = subLoteAtual;
	}
	public Localidade getLocalidadeAnterior() {
		return localidadeAnterior;
	}
	public void setLocalidadeAnterior(Localidade localidadeAnterior) {
		this.localidadeAnterior = localidadeAnterior;
	}
	public SetorComercial getSetorComercialAnterior() {
		return setorComercialAnterior;
	}
	public void setSetorComercialAnterior(SetorComercial setorComercialAnterior) {
		this.setorComercialAnterior = setorComercialAnterior;
	}
	public Quadra getQuadraAnterior() {
		return quadraAnterior;
	}
	public void setQuadraAnterior(Quadra quadraAnterior) {
		this.quadraAnterior = quadraAnterior;
	}
	public QuadraFace getQuadraFaceAnterior() {
		return quadraFaceAnterior;
	}
	public void setQuadraFaceAnterior(QuadraFace quadraFaceAnterior) {
		this.quadraFaceAnterior = quadraFaceAnterior;
	}
	public Short getLoteAnterior() {
		return loteAnterior;
	}
	public void setLoteAnterior(Short loteAnterior) {
		this.loteAnterior = loteAnterior;
	}
	public Short getSubLoteAnterior() {
		return subLoteAnterior;
	}
	public void setSubLoteAnterior(Short subLoteAnterior) {
		this.subLoteAnterior = subLoteAnterior;
	}
	public Short getIndicadorAtualizado() {
		return indicadorAtualizado;
	}
	public void setIndicadorAtualizado(Short indicadorAtualizado) {
		this.indicadorAtualizado = indicadorAtualizado;
	}
	public Short getIndicadorAtualizacaoExcluida() {
		return indicadorAtualizacaoExcluida;
	}
	public void setIndicadorAtualizacaoExcluida(Short indicadorAtualizacaoExcluida) {
		this.indicadorAtualizacaoExcluida = indicadorAtualizacaoExcluida;
	}
	public Short getIndicadorImovelExcluido() {
		return indicadorImovelExcluido;
	}
	public void setIndicadorImovelExcluido(Short indicadorImovelExcluido) {
		this.indicadorImovelExcluido = indicadorImovelExcluido;
	}
	public Short getIndicadorErroAlteracao() {
		return indicadorErroAlteracao;
	}
	public void setIndicadorErroAlteracao(Short indicadorErroAlteracao) {
		this.indicadorErroAlteracao = indicadorErroAlteracao;
	}
	public Date getDataAlteracaoOnline() {
		return dataAlteracaoOnline;
	}
	public void setDataAlteracaoOnline(Date dataAlteracaoOnline) {
		this.dataAlteracaoOnline = dataAlteracaoOnline;
	}
	public Date getDataAlteracaoBatch() {
		return dataAlteracaoBatch;
	}
	public void setDataAlteracaoBatch(Date dataAlteracaoBatch) {
		this.dataAlteracaoBatch = dataAlteracaoBatch;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	/**
	 * @return Returns the faturamentoGrupo.
	 */
	public FaturamentoGrupo getFaturamentoGrupo() {
		return faturamentoGrupo;
	}
	/**
	 * @param faturamentoGrupo The faturamentoGrupo to set.
	 */
	public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}
	public Short getIndicadorAutorizado() {
		return indicadorAutorizado;
	}
	public void setIndicadorAutorizado(Short indicadorAutorizado) {
		this.indicadorAutorizado = indicadorAutorizado;
	}
	
	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}
	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	public Usuario getUsuarioAutorizacao() {
		return usuarioAutorizacao;
	}
	public void setUsuarioAutorizacao(Usuario usuarioAutorizacao) {
		this.usuarioAutorizacao = usuarioAutorizacao;
	}
	@Override
	public Filtro retornaFiltro() {
		FiltroImovelInscricaoAlterada filtroImovelInscricaoAlterada = new FiltroImovelInscricaoAlterada();
		filtroImovelInscricaoAlterada.adicionarParametro(new ParametroSimples(FiltroImovelInscricaoAlterada.ID,this.getId()));		
       	return filtroImovelInscricaoAlterada;	
    }


	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
		
		
		@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovelInscricaoAlterada.ID,this.getId()));
		
		return filtro;
	}
		
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}
		
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"indicadorAutorizado"};
		return labels;		
	}
		
	@Override
		public String[] retornarLabelsInformacoesOperacaoEfetuada() {
			String []labels = {"Indicador Autorizado"};
			return labels;		
		}

}
