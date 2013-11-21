package gcom.cobranca;

import gcom.util.Util;


/**
 * Classe criada para simular um ResumoNegativacao
 * Onde será implementada o equals e hascold a fim de 
 * encontrar um objeto ja existente dentro de uma colecao 
 * esse equals será o agrupamento de todos os objetos tirando 
 * o valores e quantidades e o id
 * 
 * será usado para o caso de uso:
 * [UC0688] Gerar Resumo Diário da Negativação 
 * Fluxo principal item 3.5
 *
 * @author Thiago Toscano
 * @date 18/01/2008
 */
public class ResumoNegativacaoHelper extends ResumoNegativacao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @author Thiago Toscano
	 * @date 18/01/2008
	 *
	 * @param arg0
	 * @return
	 */
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof ResumoNegativacaoHelper)) {
			return false;
		}
		ResumoNegativacaoHelper castOther = (ResumoNegativacaoHelper) other;

		// testando o Negativador
		if ((this.getNegativador() == null && castOther.getNegativador() != null) || 
				(this.getNegativador() != null && castOther.getNegativador() == null)) {

			return false;
		} else if (this.getNegativador() != null && castOther.getNegativador() != null){

			if (!(this.getNegativador().getId().equals(castOther.getNegativador().getId())) ) {
				return false;
			}
		}

		// testando o NegativacaoComando
		if ((this.getNegativacaoComando() == null && castOther.getNegativacaoComando() != null) || 
				(this.getNegativacaoComando() != null && castOther.getNegativacaoComando() == null)) {

			return false;
		} else if (this.getNegativacaoComando() != null && castOther.getNegativacaoComando() != null){

			if (!(this.getNegativacaoComando().getId().equals(castOther.getNegativacaoComando().getId())) ) {
				return false;
			}
		}

		// testando o DataProcessamentoEnvio
		if ((this.getDataProcessamentoEnvio() == null && castOther.getDataProcessamentoEnvio() != null) || 
				(this.getDataProcessamentoEnvio() != null && castOther.getDataProcessamentoEnvio() == null)) {

			return false;
		} else if (this.getDataProcessamentoEnvio() != null && castOther.getDataProcessamentoEnvio() != null){

			String ddmmaaaaThis = Util.formatarDataSemBarra(this.getDataProcessamentoEnvio());
			String ddmmaaaaCast = Util.formatarDataSemBarra(castOther.getDataProcessamentoEnvio());
			if (!(ddmmaaaaThis.equals(ddmmaaaaCast)) ) {
				return false;
			}
		}

		// testando o IndicadorNegativacaoConfirmada
		if (this.getIndicadorNegativacaoConfirmada() != castOther.getIndicadorNegativacaoConfirmada()) {
			return false;
		}

		// testando o CobrancaDebitoSituacao
		if ((this.getCobrancaDebitoSituacao() == null && castOther.getCobrancaDebitoSituacao() != null) || 
				(this.getCobrancaDebitoSituacao() != null && castOther.getCobrancaDebitoSituacao() == null)) {

			return false;
		} else if (this.getCobrancaDebitoSituacao() != null && castOther.getCobrancaDebitoSituacao() != null){

			if (!(this.getCobrancaDebitoSituacao().getId().equals(castOther.getCobrancaDebitoSituacao().getId())) ) {
				return false;
			}
		}

		// testando o CobrancaGrupo
		if ((this.getCobrancaGrupo() == null && castOther.getCobrancaGrupo() != null) || 
				(this.getCobrancaGrupo() != null && castOther.getCobrancaGrupo() == null)) {

			return false;
		} else if (this.getCobrancaGrupo() != null && castOther.getCobrancaGrupo() != null){

			if (!(this.getCobrancaGrupo().getId().equals(castOther.getCobrancaGrupo().getId())) ) {
				return false;
			}
		}

		// testando o CobrancaGrupo
		if ((this.getCobrancaGrupo() == null && castOther.getCobrancaGrupo() != null) || 
				(this.getCobrancaGrupo() != null && castOther.getCobrancaGrupo() == null)) {

			return false;
		} else if (this.getCobrancaGrupo() != null && castOther.getCobrancaGrupo() != null){

			if (!(this.getCobrancaGrupo().getId().equals(castOther.getCobrancaGrupo().getId())) ) {
				return false;
			}
		}

		// testando o GerenciaRegional
		if ((this.getGerenciaRegional() == null && castOther.getGerenciaRegional() != null) || 
				(this.getGerenciaRegional() != null && castOther.getGerenciaRegional() == null)) {

			return false;
		} else if (this.getGerenciaRegional() != null && castOther.getGerenciaRegional() != null){

			if (!(this.getGerenciaRegional().getId().equals(castOther.getGerenciaRegional().getId())) ) {
				return false;
			}
		}

		// testando o UnidadeNegocio
		if ((this.getUnidadeNegocio() == null && castOther.getUnidadeNegocio() != null) || 
				(this.getUnidadeNegocio() != null && castOther.getUnidadeNegocio() == null)) {

			return false;
		} else if (this.getUnidadeNegocio() != null && castOther.getUnidadeNegocio() != null){

			if (!(this.getUnidadeNegocio().getId().equals(castOther.getUnidadeNegocio().getId())) ) {
				return false;
			}
		}

		// testando o Localidade
		if ((this.getLocalidade() == null && castOther.getLocalidade() != null) || 
				(this.getLocalidade() != null && castOther.getLocalidade() == null)) {

			return false;
		} else if (this.getLocalidade() != null && castOther.getLocalidade() != null){

			if (!(this.getLocalidade().getId().equals(castOther.getLocalidade().getId())) ) {
				return false;
			}
		}

		// testando o LocalidadeElo
		if ((this.getLocalidadeElo() == null && castOther.getLocalidadeElo() != null) || 
				(this.getLocalidadeElo() != null && castOther.getLocalidadeElo() == null)) {

			return false;
		} else if (this.getLocalidadeElo() != null && castOther.getLocalidadeElo() != null){

			if (!(this.getLocalidadeElo().getId().equals(castOther.getLocalidadeElo().getId())) ) {
				return false;
			}
		}

		// testando o SetorComercial
		if ((this.getSetorComercial() == null && castOther.getSetorComercial() != null) || 
				(this.getSetorComercial() != null && castOther.getSetorComercial() == null)) {

			return false;
		} else if (this.getSetorComercial() != null && castOther.getSetorComercial() != null){

			if (!(this.getSetorComercial().getId().equals(castOther.getSetorComercial().getId())) ) {
				return false;
			}
		}

		// testando o Quadra
		if ((this.getQuadra() == null && castOther.getQuadra() != null) || 
				(this.getQuadra() != null && castOther.getQuadra() == null)) {

			return false;
		} else if (this.getQuadra() != null && castOther.getQuadra() != null){

			if (!(this.getQuadra().getId().equals(castOther.getQuadra().getId())) ) {
				return false;
			}
		}


		// testando o CodigoSetorcomercial
		if (this.getCodigoSetorcomercial() != castOther.getCodigoSetorcomercial()) {
			return false;
		}

		// testando o NumeroQuadra
		if (this.getNumeroQuadra() != castOther.getNumeroQuadra()) {
			return false;
		}

		// testando o ImovelPerfil
		if ((this.getImovelPerfil() == null && castOther.getImovelPerfil() != null) || 
				(this.getImovelPerfil() != null && castOther.getImovelPerfil() == null)) {

			return false;
		} else if (this.getImovelPerfil() != null && castOther.getImovelPerfil() != null){

			if (!(this.getImovelPerfil().getId().equals(castOther.getImovelPerfil().getId())) ) {
				return false;
			}
		}

		// testando o ClienteTipo
		if ((this.getClienteTipo() == null && castOther.getClienteTipo() != null) || 
				(this.getClienteTipo() != null && castOther.getClienteTipo() == null)) {

			return false;
		} else if (this.getClienteTipo() != null && castOther.getClienteTipo() != null){

			if (!(this.getClienteTipo().getId().equals(castOther.getClienteTipo().getId())) ) {
				return false;
			}
		}

		// testando o EsferaPoder
		if ((this.getEsferaPoder() == null && castOther.getEsferaPoder() != null) || 
				(this.getEsferaPoder() != null && castOther.getEsferaPoder() == null)) {

			return false;
		} else if (this.getEsferaPoder() != null && castOther.getEsferaPoder() != null){

			if (!(this.getEsferaPoder().getId().equals(castOther.getEsferaPoder().getId())) ) {
				return false;
			}
		}

		// testando o EsferaPoder
		if ((this.getCategoria() == null && castOther.getCategoria() != null) || 
				(this.getCategoria() != null && castOther.getCategoria() == null)) {

			return false;
		} else if (this.getCategoria() != null && castOther.getCategoria() != null){

			if (!(this.getCategoria().getId().equals(castOther.getCategoria().getId())) ) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @author Thiago Toscano
	 * @date 18/01/2008
	 *
	 * @return
	 */
	public int hashCode() {
		return 1;
	}
	
	/**
	 * Método que no Helper retorna o objeto
	 *
	 * @author Thiago Toscano
	 * @date 18/01/2008
	 *
	 * @return
	 */
	public ResumoNegativacao getResumoNegativacao() {
		ResumoNegativacao rn = new ResumoNegativacao();
		
	    rn.setId(getId());
	    rn.setIndicadorNegativacaoConfirmada(getIndicadorNegativacaoConfirmada());
	    rn.setCodigoSetorcomercial(getCodigoSetorcomercial());
	    rn.setNumeroQuadra(getNumeroQuadra());
	    rn.setQuantidadeInclusoes(getQuantidadeInclusoes());
	    rn.setValorDebito(getValorDebito());
	    rn.setValorPendente(getValorPendente());
	    rn.setValorPago(getValorPago());
	    rn.setValorParcelado(getValorParcelado());
	    rn.setValorCancelado(getValorCancelado());
	    rn.setUltimaAlteracao(getUltimaAlteracao());
	    rn.setDataProcessamentoEnvio(getDataProcessamentoEnvio());
	    rn.setLocalidadeElo(getLocalidadeElo());
	    rn.setNegativador(getNegativador());
	    rn.setGerenciaRegional(getGerenciaRegional());
	    rn.setLocalidade(getLocalidade());
	    rn.setQuadra(getQuadra());
	    rn.setImovelPerfil(getImovelPerfil());
	    rn.setCobrancaDebitoSituacao(getCobrancaDebitoSituacao());
	    rn.setSetorComercial(getSetorComercial());
	    rn.setCobrancaGrupo(getCobrancaGrupo());
	    rn.setNegativacaoComando(getNegativacaoComando());
	    rn.setClienteTipo(getClienteTipo());
	    rn.setUnidadeNegocio(getUnidadeNegocio());
	    rn.setEsferaPoder(getEsferaPoder());
	    rn.setCategoria(getCategoria());
	    rn.setNumeroExecucaoResumoNegativacao(getNumeroExecucaoResumoNegativacao());
	    
	    rn.setLigacaoAguaSituacao(getLigacaoAguaSituacao());
	    rn.setLigacaoEsgotoSituacao(getLigacaoEsgotoSituacao());

		return rn;
	}
	
}
