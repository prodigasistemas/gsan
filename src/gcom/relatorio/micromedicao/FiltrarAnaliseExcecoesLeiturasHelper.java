/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.relatorio.micromedicao;

import java.io.Serializable;


public class FiltrarAnaliseExcecoesLeiturasHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String idImovel;
	private String idImovelCondominio;
	private String idFaturamentoGrupo;
	private String idEmpresa;
	private String idLocalidade;
	private String codigoSetorComercial;
	private String numeroQuadraInicial;
	private String numeroQuadraFinal;
	private String codigoRota;
	private String idUsuarioAlteracao;
	private String indicadorImovelCondominio;
	private String indicadorDebitoAutomatico;
	private String[] indicadorAnalisado;
	private String[] idsImovelPerfil;
	private String idCategoria;
	private String quantidadeEconomias;
	private String idMedicaoTipo;
	private String idLigacaoTipo;
	private String[] idsAnormalidadeLeituraInformada;
	private String[] idsAnormalidadeLeituraFaturada;
	private String[] idsAnormalidadeConsumo;
	private String consumoFaturadoInicial;
	private String consumoFaturadoFinal;
	private String consumoMedidoInicial;
	private String consumoMedidoFinal;
	private String consumoMedioInicial;
	private String consumoMedioFinal;
	private String idLeituraSituacaoAtual;
	private Integer idTipoAnormalidade;
	private String valorAguaEsgotoInicial;
	private String valorAguaEsgotoFinal;
	
	public static final int ANORMALIDADE_LEITURA_INFORMADA = 1;
	public static final int ANORMALIDADE_LEITURA_FATURADA = 2;
	public static final int ANORMALIDADE_CONSUMO = 3; 
	
	/**
	 * @return Retorna o campo idTipoAnormalidade.
	 */
	public Integer getIdTipoAnormalidade() {
		return idTipoAnormalidade;
	}
	/**
	 * @param idTipoAnormalidade O idTipoAnormalidade a ser setado.
	 */
	public void setIdTipoAnormalidade(Integer idTipoAnormalidade) {
		this.idTipoAnormalidade = idTipoAnormalidade;
	}
	/**
	 * @return Retorna o campo codigoRota.
	 */
	public String getCodigoRota() {
		return codigoRota;
	}
	/**
	 * @param codigoRota O codigoRota a ser setado.
	 */
	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}
	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	/**
	 * @return Retorna o campo consumoFaturadoFinal.
	 */
	public String getConsumoFaturadoFinal() {
		return consumoFaturadoFinal;
	}
	/**
	 * @param consumoFaturadoFinal O consumoFaturadoFinal a ser setado.
	 */
	public void setConsumoFaturadoFinal(String consumoFaturadoFinal) {
		this.consumoFaturadoFinal = consumoFaturadoFinal;
	}
	/**
	 * @return Retorna o campo consumoFaturadoInicial.
	 */
	public String getConsumoFaturadoInicial() {
		return consumoFaturadoInicial;
	}
	/**
	 * @param consumoFaturadoInicial O consumoFaturadoInicial a ser setado.
	 */
	public void setConsumoFaturadoInicial(String consumoFaturadoInicial) {
		this.consumoFaturadoInicial = consumoFaturadoInicial;
	}
	/**
	 * @return Retorna o campo consumoMedidoFinal.
	 */
	public String getConsumoMedidoFinal() {
		return consumoMedidoFinal;
	}
	/**
	 * @param consumoMedidoFinal O consumoMedidoFinal a ser setado.
	 */
	public void setConsumoMedidoFinal(String consumoMedidoFinal) {
		this.consumoMedidoFinal = consumoMedidoFinal;
	}
	/**
	 * @return Retorna o campo consumoMedidoInicial.
	 */
	public String getConsumoMedidoInicial() {
		return consumoMedidoInicial;
	}
	/**
	 * @param consumoMedidoInicial O consumoMedidoInicial a ser setado.
	 */
	public void setConsumoMedidoInicial(String consumoMedidoInicial) {
		this.consumoMedidoInicial = consumoMedidoInicial;
	}
	/**
	 * @return Retorna o campo consumoMedioFinal.
	 */
	public String getConsumoMedioFinal() {
		return consumoMedioFinal;
	}
	/**
	 * @param consumoMedioFinal O consumoMedioFinal a ser setado.
	 */
	public void setConsumoMedioFinal(String consumoMedioFinal) {
		this.consumoMedioFinal = consumoMedioFinal;
	}
	/**
	 * @return Retorna o campo consumoMedioInicial.
	 */
	public String getConsumoMedioInicial() {
		return consumoMedioInicial;
	}
	/**
	 * @param consumoMedioInicial O consumoMedioInicial a ser setado.
	 */
	public void setConsumoMedioInicial(String consumoMedioInicial) {
		this.consumoMedioInicial = consumoMedioInicial;
	}
	/**
	 * @return Retorna o campo idAnormalidadeConsumo.
	 */
	public String[] getIdsAnormalidadeConsumo() {
		return idsAnormalidadeConsumo;
	}
	/**
	 * @param idAnormalidadeConsumo O idAnormalidadeConsumo a ser setado.
	 */
	public void setIdsAnormalidadeConsumo(String[] idsAnormalidadeConsumo) {
		this.idsAnormalidadeConsumo = idsAnormalidadeConsumo;
	}
	/**
	 * @return Retorna o campo idAnormalidadeLeituraFaturada.
	 */
	public String[] getIdsAnormalidadeLeituraFaturada() {
		return idsAnormalidadeLeituraFaturada;
	}
	/**
	 * @param idAnormalidadeLeituraFaturada O idAnormalidadeLeituraFaturada a ser setado.
	 */
	public void setIdsAnormalidadeLeituraFaturada(
			String[] idsAnormalidadeLeituraFaturada) {
		this.idsAnormalidadeLeituraFaturada = idsAnormalidadeLeituraFaturada;
	}
	/**
	 * @return Retorna o campo idAnormalidadeLeituraInformada.
	 */
	public String[] getIdsAnormalidadeLeituraInformada() {
		return idsAnormalidadeLeituraInformada;
	}
	/**
	 * @param idAnormalidadeLeituraInformada O idAnormalidadeLeituraInformada a ser setado.
	 */
	public void setIdsAnormalidadeLeituraInformada(
			String[] idsAnormalidadeLeituraInformada) {
		this.idsAnormalidadeLeituraInformada = idsAnormalidadeLeituraInformada;
	}
	/**
	 * @return Retorna o campo idCategoria.
	 */
	public String getIdCategoria() {
		return idCategoria;
	}
	/**
	 * @param idCategoria O idCategoria a ser setado.
	 */
	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}
	/**
	 * @return Retorna o campo idEmpresa.
	 */
	public String getIdEmpresa() {
		return idEmpresa;
	}
	/**
	 * @param idEmpresa O idEmpresa a ser setado.
	 */
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}
	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	/**
	 * @return Retorna o campo idImovelCondominio.
	 */
	public String getIdImovelCondominio() {
		return idImovelCondominio;
	}
	/**
	 * @param idImovelCondominio O idImovelCondominio a ser setado.
	 */
	public void setIdImovelCondominio(String idImovelCondominio) {
		this.idImovelCondominio = idImovelCondominio;
	}
	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}
	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	/**
	 * @return Retorna o campo idFaturamentoGrupo.
	 */
	public String getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}
	/**
	 * @param idFaturamentoGrupo O idFaturamentoGrupo a ser setado.
	 */
	public void setIdFaturamentoGrupo(String idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}
	/**
	 * @return Retorna o campo idLeituraSituacaoAtual.
	 */
	public String getIdLeituraSituacaoAtual() {
		return idLeituraSituacaoAtual;
	}
	/**
	 * @param idLeituraSituacaoAtual O idLeituraSituacaoAtual a ser setado.
	 */
	public void setIdLeituraSituacaoAtual(String idLeituraSituacaoAtual) {
		this.idLeituraSituacaoAtual = idLeituraSituacaoAtual;
	}
	/**
	 * @return Retorna o campo idsImovelPerfil.
	 */
	public String[] getIdsImovelPerfil() {
		return idsImovelPerfil;
	}
	/**
	 * @param idsImovelPerfil O idsImovelPerfil a ser setado.
	 */
	public void setIdsImovelPerfil(String[] idsImovelPerfil) {
		this.idsImovelPerfil = idsImovelPerfil;
	}
	/**
	 * @return Retorna o campo idUsuarioAlteracao.
	 */
	public String getIdUsuarioAlteracao() {
		return idUsuarioAlteracao;
	}
	/**
	 * @param idUsuarioAlteracao O idUsuarioAlteracao a ser setado.
	 */
	public void setIdUsuarioAlteracao(String idUsuarioAlteracao) {
		this.idUsuarioAlteracao = idUsuarioAlteracao;
	}
	/**
	 * @return Retorna o campo indicadorAnalisado.
	 */
	public String[] getIndicadorAnalisado() {
		return indicadorAnalisado;
	}
	/**
	 * @param indicadorAnalisado O indicadorAnalisado a ser setado.
	 */
	public void setIndicadorAnalisado(String[] indicadorAnalisado) {
		this.indicadorAnalisado = indicadorAnalisado;
	}
	/**
	 * @return Retorna o campo indicadorDebitoAutomatico.
	 */
	public String getIndicadorDebitoAutomatico() {
		return indicadorDebitoAutomatico;
	}
	/**
	 * @param indicadorDebitoAutomatico O indicadorDebitoAutomatico a ser setado.
	 */
	public void setIndicadorDebitoAutomatico(String indicadorDebitoAutomatico) {
		this.indicadorDebitoAutomatico = indicadorDebitoAutomatico;
	}
	/**
	 * @return Retorna o campo indicadorImovelCondominio.
	 */
	public String getIndicadorImovelCondominio() {
		return indicadorImovelCondominio;
	}
	/**
	 * @param indicadorImovelCondominio O indicadorImovelCondominio a ser setado.
	 */
	public void setIndicadorImovelCondominio(String indicadorImovelCondominio) {
		this.indicadorImovelCondominio = indicadorImovelCondominio;
	}
	/**
	 * @return Retorna o campo numeroQuadraFinal.
	 */
	public String getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}
	/**
	 * @param numeroQuadraFinal O numeroQuadraFinal a ser setado.
	 */
	public void setNumeroQuadraFinal(String numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}
	/**
	 * @return Retorna o campo numeroQuadraInicial.
	 */
	public String getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}
	/**
	 * @param numeroQuadraInicial O numeroQuadraInicial a ser setado.
	 */
	public void setNumeroQuadraInicial(String numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}
	/**
	 * @return Retorna o campo quantidadeEconomias.
	 */
	public String getQuantidadeEconomias() {
		return quantidadeEconomias;
	}
	/**
	 * @param quantidadeEconomias O quantidadeEconomias a ser setado.
	 */
	public void setQuantidadeEconomias(String quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}
	/**
	 * @return Retorna o campo idLigacaoTipo.
	 */
	public String getIdLigacaoTipo() {
		return idLigacaoTipo;
	}
	/**
	 * @param idLigacaoTipo O idLigacaoTipo a ser setado.
	 */
	public void setIdLigacaoTipo(String idLigacaoTipo) {
		this.idLigacaoTipo = idLigacaoTipo;
	}
	/**
	 * @return Retorna o campo idMedicaoTipo.
	 */
	public String getIdMedicaoTipo() {
		return idMedicaoTipo;
	}
	/**
	 * @param idMedicaoTipo O idMedicaoTipo a ser setado.
	 */
	public void setIdMedicaoTipo(String idMedicaoTipo) {
		this.idMedicaoTipo = idMedicaoTipo;
	}
	public String getValorAguaEsgotoFinal() {
		return valorAguaEsgotoFinal;
	}
	public void setValorAguaEsgotoFinal(String valorAguaEsgotoFinal) {
		this.valorAguaEsgotoFinal = valorAguaEsgotoFinal;
	}
	public String getValorAguaEsgotoInicial() {
		return valorAguaEsgotoInicial;
	}
	public void setValorAguaEsgotoInicial(String valorAguaEsgotoInicial) {
		this.valorAguaEsgotoInicial = valorAguaEsgotoInicial;
	}

	

}
