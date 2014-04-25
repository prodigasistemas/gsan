package gcom.faturamento;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@ControleAlteracao()
public class FaturamentoSituacaoComando extends ObjetoTransacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	/** identifier field */
    private Integer id;
    
    private Integer codigoSetorComercialInicial;
    
    private Integer codigoSetorComercialFinal;
    
    private Integer numeroQuadraInicial;
    
    private Integer numeroQuadraFinal;
    
    private Integer numeroLoteInicial;
    
    private Integer numeroLoteFinal;
    
    private Integer numeroSubLoteInicial;
    
    private Integer numeroSubLoteFinal;
    
    private Integer codigoRotaInicial;
    
    private Integer codigoRotaFinal;
    private Integer sequencialRotaInicial;
    
    private Integer sequencialRotaFinal;
    
    private Short indicadorConsumo;
    
    private Integer quantidadeImoveis;
    
    private Short indicadorComando;
    
    private Integer anoMesInicialSituacaoFaturamento;
    
    @ControleAlteracao(funcionalidade={Operacao.OPERACAO_ATUALIZAR_SITUACAO_ESPECIAL_FATURAMENTO})
    private Integer anoMesFinalSituacaoFaturamento;
    
    @ControleAlteracao(funcionalidade={Operacao.OPERACAO_ATUALIZAR_SITUACAO_ESPECIAL_FATURAMENTO})
    private String observacao;
    
    private Date ultimaAlteracao;
    
    private Imovel imovel;
    
    private Localidade localidadeInicial;
    
    private Localidade localidadeFinal;
    
    private Categoria categoria1;
    
    private Categoria categoria2;
    
    private Categoria categoria3;
    
    private Categoria categoria4;
    
    private Usuario usuario;
    
    
    /**
     * variavel usada como auxiliar para geração de consultas
     * ja que o indicadorConsumo pode ser filtrado como um
     * operador in no banco de dados
     * 
     */
    private Collection<Short> indicadoresConsumo;
    
    public static Integer COMANDO_INSERIR = 1;
    public static Integer COMANDO_RETIRAR = 2;
    
    /** persistent field */
    private gcom.faturamento.FaturamentoSituacaoTipo faturamentoSituacaoTipo;
    
    private gcom.faturamento.FaturamentoSituacaoMotivo faturamentoSituacaoMotivo;

	public Integer getAnoMesFinalSituacaoFaturamento() {
		return anoMesFinalSituacaoFaturamento;
	}

	public void setAnoMesFinalSituacaoFaturamento(
			Integer anoMesFinalSituacaoFaturamento) {
		this.anoMesFinalSituacaoFaturamento = anoMesFinalSituacaoFaturamento;
	}

	public Integer getAnoMesInicialSituacaoFaturamento() {
		return anoMesInicialSituacaoFaturamento;
	}

	public void setAnoMesInicialSituacaoFaturamento(
			Integer anoMesInicialSituacaoFaturamento) {
		this.anoMesInicialSituacaoFaturamento = anoMesInicialSituacaoFaturamento;
	}

	public Categoria getCategoria1() {
		return categoria1;
	}

	public void setCategoria1(Categoria categoria1) {
		this.categoria1 = categoria1;
	}

	public Categoria getCategoria2() {
		return categoria2;
	}

	public void setCategoria2(Categoria categoria2) {
		this.categoria2 = categoria2;
	}

	public Categoria getCategoria3() {
		return categoria3;
	}

	public void setCategoria3(Categoria categoria3) {
		this.categoria3 = categoria3;
	}

	public Categoria getCategoria4() {
		return categoria4;
	}

	public void setCategoria4(Categoria categoria4) {
		this.categoria4 = categoria4;
	}

	public Integer getCodigoRotaFinal() {
		return codigoRotaFinal;
	}

	public void setCodigoRotaFinal(Integer codigoRotaFinal) {
		this.codigoRotaFinal = codigoRotaFinal;
	}

	public Integer getCodigoRotaInicial() {
		return codigoRotaInicial;
	}

	public void setCodigoRotaInicial(Integer codigoRotaInicial) {
		this.codigoRotaInicial = codigoRotaInicial;
	}

	public Integer getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(Integer codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public Integer getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(Integer codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public gcom.faturamento.FaturamentoSituacaoMotivo getFaturamentoSituacaoMotivo() {
		return faturamentoSituacaoMotivo;
	}

	public void setFaturamentoSituacaoMotivo(
			gcom.faturamento.FaturamentoSituacaoMotivo faturamentoSituacaoMotivo) {
		this.faturamentoSituacaoMotivo = faturamentoSituacaoMotivo;
	}

	public gcom.faturamento.FaturamentoSituacaoTipo getFaturamentoSituacaoTipo() {
		return faturamentoSituacaoTipo;
	}

	public void setFaturamentoSituacaoTipo(
			gcom.faturamento.FaturamentoSituacaoTipo faturamentoSituacaoTipo) {
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
	}

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

	public Short getIndicadorComando() {
		return indicadorComando;
	}

	public void setIndicadorComando(Short indicadorComando) {
		this.indicadorComando = indicadorComando;
	}

	public Short getIndicadorConsumo() {
		return indicadorConsumo;
	}

	public void setIndicadorConsumo(Short indicadorConsumo) {
		this.indicadorConsumo = indicadorConsumo;
	}

	public Localidade getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(Localidade localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public Localidade getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(Localidade localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public Integer getNumeroLoteFinal() {
		return numeroLoteFinal;
	}

	public void setNumeroLoteFinal(Integer numeroLoteFinal) {
		this.numeroLoteFinal = numeroLoteFinal;
	}

	public Integer getNumeroLoteInicial() {
		return numeroLoteInicial;
	}

	public void setNumeroLoteInicial(Integer numeroLoteInicial) {
		this.numeroLoteInicial = numeroLoteInicial;
	}

	public Integer getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}

	public void setNumeroQuadraFinal(Integer numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}

	public Integer getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}

	public void setNumeroQuadraInicial(Integer numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}

	public Integer getNumeroSubLoteFinal() {
		return numeroSubLoteFinal;
	}

	public void setNumeroSubLoteFinal(Integer numeroSubLoteFinal) {
		this.numeroSubLoteFinal = numeroSubLoteFinal;
	}

	public Integer getNumeroSubLoteInicial() {
		return numeroSubLoteInicial;
	}

	public void setNumeroSubLoteInicial(Integer numeroSubLoteInicial) {
		this.numeroSubLoteInicial = numeroSubLoteInicial;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getQuantidadeImoveis() {
		return quantidadeImoveis;
	}

	public void setQuantidadeImoveis(Integer quantidadeImoveis) {
		this.quantidadeImoveis = quantidadeImoveis;
	}

	public Integer getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}

	public void setSequencialRotaFinal(Integer sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}

	public Integer getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}

	public void setSequencialRotaInicial(Integer sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Collection<Short> getIndicadoresConsumo() {
		return indicadoresConsumo;
	}

	public void setIndicadoresConsumo(Collection<Short> indicadoresConsumo) {
		this.indicadoresConsumo = indicadoresConsumo;
	}
	
	public String getAnoMesFinalSituacaoFaturamentoComoMesAno() {
		return Util.formatarAnoMesParaMesAno(this.anoMesFinalSituacaoFaturamento);
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroFaturamentoSituacaoComando filtro = new FiltroFaturamentoSituacaoComando();
		
		filtro.adicionarParametro(new ParametroSimples(FiltroFaturamentoSituacaoComando.ID,this.getId()));
		
//		filtro.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoMotivo");
//		filtro.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoTipo");
		
		return filtro;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"anoMesFinalSituacaoFaturamentoComoMesAno","observacao"};
		return labels;	
	}

	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Mes/Ano Referencia do Faturamento Final","Observacao"};
		return labels;		
	}
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		return filtro;
	}	

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getId().toString();
	}

}
