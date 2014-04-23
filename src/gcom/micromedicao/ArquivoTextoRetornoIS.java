package gcom.micromedicao;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import gcom.cadastro.localidade.Localidade;
import gcom.faturamento.FaturamentoGrupo;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

/** @author Pamela Gatinho */
@ControleAlteracao()
public class ArquivoTextoRetornoIS extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	/** identifier field */
	private Integer id;

	private Integer anoMesReferencia;

	private Integer codigoSetorComercial;

	private Integer codigoRota;

	private String nomeArquivo;

	private Date tempoRetornoArquivo;

	private String arquivoTexto;

	private Short tipoFinalizacao;

	private Date ultimaAlteracao;

	private Localidade localidade;

	private FaturamentoGrupo faturamentoGrupo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Integer codigoRota) {
		this.codigoRota = codigoRota;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Date getTempoRetornoArquivo() {
		return tempoRetornoArquivo;
	}

	public void setTempoRetornoArquivo(Date tempoRetornoArquivo) {
		this.tempoRetornoArquivo = tempoRetornoArquivo;
	}

	public String getArquivoTexto() {
		return arquivoTexto;
	}

	public void setArquivoTexto(String arquivoTexto) {
		this.arquivoTexto = arquivoTexto;
	}

	public Short getTipoFinalizacao() {
		return tipoFinalizacao;
	}

	public void setTipoFinalizacao(Short tipoFinalizacao) {
		this.tipoFinalizacao = tipoFinalizacao;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public FaturamentoGrupo getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Filtro retornaFiltro() {
		FiltroArquivoTextoRetornoIS filtroArquivoTextoRetornoIS = new FiltroArquivoTextoRetornoIS();

		filtroArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRetornoIS.ANO_MES_REFERENCIA);
		filtroArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRetornoIS.CODIGO_ROTA);
		filtroArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRetornoIS.CODIGO_SETOR);
		filtroArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRetornoIS.NOME_ARQUIVO);
		filtroArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRetornoIS.TEMPO_FINALIZACAO);
		filtroArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRetornoIS.TEMPO_RETORNO_ARQUIVO);
		filtroArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRetornoIS.FATURAMENTO_GRUPO_ID);
		filtroArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRetornoIS.LOCALIDADE_ID);
		filtroArquivoTextoRetornoIS.adicionarParametro(new ParametroSimples(FiltroArquivoTextoRetornoIS.ID_ARQUIVO_TEXTO_RETORNO_IS,
				this.getId()));
		return filtroArquivoTextoRetornoIS;
	}

	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRetornoIS.FATURAMENTO_GRUPO_ID);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRetornoIS.LOCALIDADE_ID);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRetornoIS.CODIGO_SETOR);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRetornoIS.CODIGO_ROTA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRetornoIS.ANO_MES_REFERENCIA);
		
		return filtro;
	}
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"localidade.descricao","codigoSetorComercial", "codigoRota"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Localidade","Setor Comercial", "Código da Rota"};
		return labels;		
	}

}
