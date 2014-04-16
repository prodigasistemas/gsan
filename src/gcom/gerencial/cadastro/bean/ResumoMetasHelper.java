package gcom.gerencial.cadastro.bean;

import java.util.Date;

/**
 * Classe bean para agrupamento dos historicos de consumo com as quebras
 * solicitadas
 * 
 * 
 * @author Sávio Luiz
 * @date 20/07/2007
 */

public class ResumoMetasHelper {

	private Integer idGerenciaRegional;

	private Integer idUnidadeNegocio;

	private Integer idLocalidade;

	private Integer idElo;

	private Integer idSetorComercial;

	private Integer idRota;

	private Integer idQuadra;

	private Integer codigoSetorComercial;

	private Integer numeroQuadra;

	private Integer idImovelPerfil;

	private Integer idLigacaoAguaSituacao;

	private Integer idLigacaoEsgotoSituacao;

	private Integer idCategoria;

	private Integer idSubCategoria;

	private Integer idEsferaPoder;

	private Integer idClienteTipo;

	private Integer idLigacaoAguaPerfil;

	private Integer idLigacaoEsgotoPerfil;

	private Integer quantidadeLigacoesCadastradas = new Integer(0);

	private Integer quantidadeLigacoesSuprimidas = new Integer(0);

	private Integer quantidadeLigacoesCortadas = new Integer(0);

	private Integer quantidadeLigacoesAtivas = new Integer(0);

	private Integer quantidadeLigacoesAtivasDebito3M = new Integer(0);

	private Integer quantidadeLigacoesConsumoMedido = new Integer(0);

	private Integer quantidadeLigacoesConsumoAte5M3 = new Integer(0);

	private Integer quantidadeLigacoesConsumoMedio = new Integer(0);

	private Integer quantidadeLigacoesConsumoNaoMedido = new Integer(0);

	private Integer quantidadeEconomias = new Integer(0);

	private Date dataLigacao;

	private Date dataSupressao;

	private Date dataCorte;

	private Date dataReligacao;

	private Date dataRestabelecimentoAgua;

	private int[] tipoData;

	private Integer idHidrometroInstalado;

	private Integer codigoGrupoSubcategoria;

	// indicadores da data de ligação agua

	public static final int INIDCADOR_DATA_LIGACAO = 1;

	public static final int INIDCADOR_DATA_SUPRESSAO = 2;

	public static final int INIDCADOR_DATA_CORTE = 3;

	public static final int INIDCADOR_DATA_RELIGACAO = 4;

	public static final int INIDCADOR_DATA_RESTABELECIMENTO = 5;

	public Integer getIdClienteTipo() {
		return idClienteTipo;
	}

	public void setIdClienteTipo(Integer idClienteTipo) {
		this.idClienteTipo = idClienteTipo;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setIdCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getIdElo() {
		return idElo;
	}

	public void setIdElo(Integer idElo) {
		this.idElo = idElo;
	}

	public Integer getIdEsferaPoder() {
		return idEsferaPoder;
	}

	public void setIdEsferaPoder(Integer idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public Integer getIdImovelPerfil() {
		return idImovelPerfil;
	}

	public void setIdImovelPerfil(Integer idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLcalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getIdLigacaoAguaPerfil() {
		return idLigacaoAguaPerfil;
	}

	public void setIdLigacaoAguaPerfil(Integer idLigacaoAguaPerfil) {
		this.idLigacaoAguaPerfil = idLigacaoAguaPerfil;
	}

	public Integer getIdLigacaoAguaSituacao() {
		return idLigacaoAguaSituacao;
	}

	public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao) {
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
	}

	public Integer getIdLigacaoEsgotoPerfil() {
		return idLigacaoEsgotoPerfil;
	}

	public void setIdLigacaoEsgotoPerfil(Integer idLigacaoEsgotoPerfil) {
		this.idLigacaoEsgotoPerfil = idLigacaoEsgotoPerfil;
	}

	public Integer getIdLigacaoEsgotoSituacao() {
		return idLigacaoEsgotoSituacao;
	}

	public void setIdLigacaoEsgotoSituacao(Integer idLigacaoEsgotoSituacao) {
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setIdNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Integer getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}

	public Integer getIdRota() {
		return idRota;
	}

	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}

	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public Integer getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(Integer quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public Date getDataCorte() {
		return dataCorte;
	}

	public void setDataCorte(Date dataCorte) {
		this.dataCorte = dataCorte;
	}

	public Date getDataLigacao() {
		return dataLigacao;
	}

	public void setDataLigacao(Date dataLigacao) {
		this.dataLigacao = dataLigacao;
	}

	public Date getDataReligacao() {
		return dataReligacao;
	}

	public void setDataReligacao(Date dataReligacao) {
		this.dataReligacao = dataReligacao;
	}

	public Date getDataRestabelecimentoAgua() {
		return dataRestabelecimentoAgua;
	}

	public void setDataRestabelecimentoAgua(Date dataRestabelecimentoAgua) {
		this.dataRestabelecimentoAgua = dataRestabelecimentoAgua;
	}

	public Date getDataSupressao() {
		return dataSupressao;
	}

	public void setDataSupressao(Date dataSupressao) {
		this.dataSupressao = dataSupressao;
	}

	public void setTipoData(int[] tipoData) {
		this.tipoData = tipoData;
	}

	public Integer getQuantidadeLigacoesAtivas() {
		return quantidadeLigacoesAtivas;
	}

	public void setQuantidadeLigacoesAtivas(Integer quantidadeLigacoesAtivas) {
		this.quantidadeLigacoesAtivas = quantidadeLigacoesAtivas;
	}

	public Integer getQuantidadeLigacoesAtivasDebito3M() {
		return quantidadeLigacoesAtivasDebito3M;
	}

	public void setQuantidadeLigacoesAtivasDebito3M(
			Integer quantidadeLigacoesAtivasDebito3M) {
		this.quantidadeLigacoesAtivasDebito3M = quantidadeLigacoesAtivasDebito3M;
	}

	public Integer getQuantidadeLigacoesCadastradas() {
		return quantidadeLigacoesCadastradas;
	}

	public void setQuantidadeLigacoesCadastradas(
			Integer quantidadeLigacoesCadastradas) {
		this.quantidadeLigacoesCadastradas = quantidadeLigacoesCadastradas;
	}

	public Integer getQuantidadeLigacoesConsumoAte5M3() {
		return quantidadeLigacoesConsumoAte5M3;
	}

	public void setQuantidadeLigacoesConsumoAte5M3(
			Integer quantidadeLigacoesConsumoAte5M3) {
		this.quantidadeLigacoesConsumoAte5M3 = quantidadeLigacoesConsumoAte5M3;
	}

	public Integer getQuantidadeLigacoesConsumoMedido() {
		return quantidadeLigacoesConsumoMedido;
	}

	public void setQuantidadeLigacoesConsumoMedido(
			Integer quantidadeLigacoesConsumoMedido) {
		this.quantidadeLigacoesConsumoMedido = quantidadeLigacoesConsumoMedido;
	}

	public Integer getQuantidadeLigacoesConsumoMedio() {
		return quantidadeLigacoesConsumoMedio;
	}

	public void setQuantidadeLigacoesConsumoMedio(
			Integer quantidadeLigacoesConsumoMedio) {
		this.quantidadeLigacoesConsumoMedio = quantidadeLigacoesConsumoMedio;
	}

	public Integer getQuantidadeLigacoesConsumoNaoMedido() {
		return quantidadeLigacoesConsumoNaoMedido;
	}

	public void setQuantidadeLigacoesConsumoNaoMedido(
			Integer quantidadeLigacoesConsumoNaoMedido) {
		this.quantidadeLigacoesConsumoNaoMedido = quantidadeLigacoesConsumoNaoMedido;
	}

	public Integer getQuantidadeLigacoesCortadas() {
		return quantidadeLigacoesCortadas;
	}

	public void setQuantidadeLigacoesCortadas(Integer quantidadeLigacoesCortadas) {
		this.quantidadeLigacoesCortadas = quantidadeLigacoesCortadas;
	}

	public Integer getQuantidadeLigacoesSuprimidas() {
		return quantidadeLigacoesSuprimidas;
	}

	public void setQuantidadeLigacoesSuprimidas(
			Integer quantidadeLigacoesSuprimidas) {
		this.quantidadeLigacoesSuprimidas = quantidadeLigacoesSuprimidas;
	}

	public int[] getTipoData() {
		return tipoData;
	}

	/**
	 * Construtor com a sequencia correta de quebras para o caso de uso UC[0570] -
	 * Gerar resumo do consumo de agua
	 * 
	 * OBS - Duas quebras adicionais nao foram passadas neste contrutor, a
	 * saber, idCategoria e idSubCatergoria, pois no momento da criacao deste
	 * objeto essas informacoes nao estao disponiveis.
	 * 
	 * @param idGerenciaRegional
	 *            param idUnidadeNegocio
	 * @param idLocalidade
	 * @param idElo
	 * @param idSetorComercial
	 * @param idRota
	 * @param idQuadra
	 * @param codigoSetorComercial
	 * @param numeroQuadra
	 * @param idImovelPerfil
	 * @param idSituacaoLigacaoAgua
	 * @param idSituacaoLigacaoEsgoto
	 * @param idPerfilLigacaoAgua
	 * @param idPerfilLigacaoEsgoto
	 * @param idTipoConsumo
	 * @param qtdConsumoAgua
	 */
	public ResumoMetasHelper(Integer idGerenciaRegional,
			Integer idUnidadeNegocio, Integer idLocalidade, Integer idElo,
			Integer idSetorComercial, Integer idRota, Integer idQuadra,
			Integer codigoSetorComercial, Integer numeroQuadra,
			Integer idImovelPerfil, Integer idSituacaoLigacaoAgua,
			Integer idSituacaoLigacaoEsgoto, Integer idPerfilLigacaoAgua,
			Integer idPerfilLigacaoEsgoto, Date dataLigacao,
			Date dataSupressao, Date dataCorte, Date dataReligacao,
			Date dataRestabelecimentoAgua, Integer idHidrometroInstalado) {
		this.idGerenciaRegional = idGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.idLocalidade = idLocalidade;
		this.idElo = idElo;
		this.idSetorComercial = idSetorComercial;
		this.idRota = idRota;
		this.idQuadra = idQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.idImovelPerfil = idImovelPerfil;
		this.idLigacaoAguaSituacao = idSituacaoLigacaoAgua;
		this.idLigacaoEsgotoSituacao = idSituacaoLigacaoEsgoto;
		this.idLigacaoAguaPerfil = idPerfilLigacaoAgua;
		this.idLigacaoEsgotoPerfil = idPerfilLigacaoEsgoto;
		this.dataLigacao = dataLigacao;
		this.dataSupressao = dataSupressao;
		this.dataCorte = dataCorte;
		this.dataReligacao = dataReligacao;
		this.dataRestabelecimentoAgua = dataRestabelecimentoAgua;
		this.idHidrometroInstalado = idHidrometroInstalado;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdSubCategoria() {
		return idSubCategoria;
	}

	public void setIdSubCategoria(Integer idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Integer getIdHidrometroInstalado() {
		return idHidrometroInstalado;
	}

	public void setIdHidrometroInstalado(Integer idHidrometroInstalado) {
		this.idHidrometroInstalado = idHidrometroInstalado;
	}

	/**
	 * Compara duas properiedades inteiras, comparando seus valores para
	 * descobrirmos se sao iguais
	 * 
	 * @param pro1
	 *            Primeira propriedade
	 * @param pro2
	 *            Segunda propriedade
	 * @return Se iguais, retorna true
	 */
	private boolean propriedadesIguais(Integer pro1, Integer pro2) {
		if (pro2 != null) {
			if (!pro2.equals(pro1)) {
				return false;
			}
		} else if (pro1 != null) {
			return false;
		}

		// Se chegou ate aqui quer dizer que as propriedades sao iguais
		return true;
	}

	/**
	 * Compara dois objetos levando em consideracao apenas as propriedades que
	 * identificam o agrupamento
	 * 
	 * @param obj
	 *            Objeto a ser comparado com a instancia deste objeto
	 */
	public boolean equals(Object obj) {

		if (!(obj instanceof ResumoMetasHelper)) {
			return false;
		} else {
			ResumoMetasHelper resumoTemp = (ResumoMetasHelper) obj;

			// Verificamos se todas as propriedades que identificam o objeto sao
			// iguais
			return propriedadesIguais(this.idGerenciaRegional,
					resumoTemp.idGerenciaRegional)
					&& propriedadesIguais(this.idUnidadeNegocio,
							resumoTemp.idUnidadeNegocio)
					&& propriedadesIguais(this.idLocalidade,
							resumoTemp.idLocalidade)
					&& propriedadesIguais(this.idElo, resumoTemp.idElo)
					&& propriedadesIguais(this.idSetorComercial,
							resumoTemp.idSetorComercial)
					&& propriedadesIguais(this.idRota, resumoTemp.idRota)
					&& propriedadesIguais(this.idQuadra, resumoTemp.idQuadra)
					&& propriedadesIguais(this.codigoSetorComercial,
							resumoTemp.codigoSetorComercial)
					&& propriedadesIguais(this.numeroQuadra,
							resumoTemp.numeroQuadra)
					&& propriedadesIguais(this.idImovelPerfil,
							resumoTemp.idImovelPerfil)
					&& propriedadesIguais(this.idLigacaoAguaSituacao,
							resumoTemp.idLigacaoAguaSituacao)
					&& propriedadesIguais(this.idLigacaoEsgotoSituacao,
							resumoTemp.idLigacaoEsgotoSituacao)
					&& propriedadesIguais(this.idCategoria,
							resumoTemp.idCategoria)
					&& propriedadesIguais(this.idSubCategoria,
							resumoTemp.idSubCategoria)
					&& propriedadesIguais(this.idEsferaPoder,
							resumoTemp.idEsferaPoder)
					&& propriedadesIguais(this.idClienteTipo,
							resumoTemp.idClienteTipo)
					&& propriedadesIguais(this.idLigacaoAguaPerfil,
							resumoTemp.idLigacaoAguaPerfil)
					&& propriedadesIguais(this.idLigacaoEsgotoPerfil,
							resumoTemp.idLigacaoEsgotoPerfil)
					&& propriedadesIguais(this.codigoGrupoSubcategoria,
							resumoTemp.getCodigoGrupoSubcategoria());
		}
	}

	// seta um indicador no helper que dirá que tipo de data de ligação água é o
	// helper
	public void setarTipoData(Date dataInicial, Date dataFinal) {
		int[] tiposDataAux = new int[5];
		int i = 0;
		if (this.dataLigacao != null
				&& (this.dataLigacao.after(dataInicial) && this.dataLigacao
						.before(dataFinal))) {
			tiposDataAux[i] = INIDCADOR_DATA_LIGACAO;
			i++;
		}
		if (this.dataSupressao != null
				&& (this.dataSupressao.after(dataInicial) && this.dataSupressao
						.before(dataFinal))) {
			tiposDataAux[i] = INIDCADOR_DATA_SUPRESSAO;
			i++;
		}
		if (this.dataCorte != null
				&& (this.dataCorte.after(dataInicial) && this.dataCorte
						.before(dataFinal))) {
			tiposDataAux[i] = INIDCADOR_DATA_CORTE;
			i++;
		}
		if (this.dataReligacao != null
				&& (this.dataReligacao.after(dataInicial) && this.dataReligacao
						.before(dataFinal))) {
			tiposDataAux[i] = INIDCADOR_DATA_RELIGACAO;
			i++;

		}
		if (this.dataRestabelecimentoAgua != null
				&& (this.dataRestabelecimentoAgua.after(dataInicial) && this.dataRestabelecimentoAgua
						.before(dataFinal))) {
			tiposDataAux[i] = INIDCADOR_DATA_RESTABELECIMENTO;
			i++;

		}

		int[] tiposData = new int[i];
		int aux = 0;
		for (int tipoData : tiposDataAux) {
			if (tipoData != 0) {
				tiposData[aux] = tipoData;
				aux++;
			}
		}
		this.setTipoData(tiposData);
	}

	public Integer getCodigoGrupoSubcategoria() {
		return codigoGrupoSubcategoria;
	}

	public void setCodigoGrupoSubcategoria(Integer codigoGrupoSubcategoria) {
		this.codigoGrupoSubcategoria = codigoGrupoSubcategoria;
	}

}
