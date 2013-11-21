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
package gcom.gui.cadastro.imovel;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 2 de Junho de 2004
 */
public class ImovelOutrosCriteriosActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idClienteTipo;
	
	private String nomeCliente;
	
	private String id;
	
	private String descricao;
	
	private String indicadorUso;
	
	private String intervaloMediaMinimaHidrometroInicio;
	
	private String intervaloMediaMinimaHidrometroFinal;
	
	private String intervaloMediaMinimaImovelInicio;//
	
	private String intervaloMediaMinimaImovelFinal;//
	
	private String indicadorMedicao;//
	
	private String intervaloPercentualEsgotoInicial;
	
	private String intervaloPercentualEsgotoFinal;
	
	private String inscricaoTipo;//
	
	private String consumoMinimo;
	
	private String nomeMunicipio;
	
	private String idNomeConta;
	
	private String idImovelPrincipal;
	
	private String idImovelCondominio;
	
	private String tipoRelacao;//Falta modificar no hbm pra fazer reverso, so com rodrigo...
	
	private String indicadorCpfCnpjInformado;
	
	private String cpfCnpj;
	
	private String idCliente;
	
	private String inscricaoImovelPrincipal;
	
	private String inscricaoImovelCondominio;
	
	private String loteDestino;
	
	private String quadraDestinoNM;
	
	private String idBairro;
	
	private String nomeBairro;
	
	private String loteOrigem;
	
	private String idLocalidade;

	private String nomeLocalidadeOrigem;

	private String nomeSetorComercialOrigem;

	private String quadraOrigemNM;

	private String quadraMensagemOrigem;

	private String nomeLocalidadeDestino;

	private String setorComercialDestinoCD;

	private String setorComercialOrigemCD;

	private String setorComercialOrigemID;

	private String quadraOrigemID;

	private String localidadeDestinoID;

	private String localidadeOrigemID;	

	private String nomeSetorComercialDestino;

	private String setorComercialDestinoID;

	private String quadraMensagemDestino;

	private String idGerenciaRegional;
	
	private String unidadeNegocio;
	
	private String quadraDestinoID;
	
	private String idMunicipio;
	
	private String consumoMinimoInicial;//
	
	private String consumoMinimoFinal;//
	
	private String situacaoAgua;
	
	private String situacaoLigacaoEsgoto;
	
	private String consumoMinimoFixadoEsgotoInicial;
	
	private String consumoMinimoFixadoEsgotoFinal;
	
	private String tipoMedicao;
	
	private String perfilImovel;
	
	private String categoriaImovel;
	
	private String subcategoria; 
	
	private String quantidadeEconomiasInicial;//
	
	private String quantidadeEconomiasFinal;//
	
	private String numeroPontosInicial;//
	
	private String numeroPontosFinal;//
	
	private String numeroMoradoresInicial;//
	
	private String numeroMoradoresFinal;//
	
	private String areaConstruidaInicial;//

	private String areaConstruidaFinal;//
	
	private String areaConstruidaFaixa;//
	
	private String tipoPoco;
	
	private String tipoSituacaoEspecialFaturamento;//
	
	private String tipoSituacaoEspecialCobranca;//
	
	private String situacaoCobranca;//
	
	private String diaVencimentoAlternativo;//
	
	private String anormalidadeElo;//
	
	private String ocorrenciaCadastro;//
	
	private String tarifaConsumo;//
	
	private String CEP;
	
	private String descricaoCep;

	private String idLogradouro;
	
	private String tarifaSocialCartaoTipoId;//Esperar pra fazer o hql
	
	private String tarifaSocialRendaTipoId;//
	
	private String tarifaSocialExclusaoMotivoId;//Perto \R cliente_relacao_tipoem vermelho
	
	private String nomeLogradouro;	
	
	private String recadastramentoNumeroFinal;
	
	private String recadastramentoNumeroInicial;
	
	private String dataFimRecadastramento;
	
	private String dataInicioRecadastramento;
	
	private String celpeFinal;
	
	private String celpeInicial;
	
	private String rendaFinal;
	
	private String rendaInicial;
	
	private String mesInicioAdesao;
	
	private String mesFimAdesao;
	
	private String idMotivoExclusao;
	
	private String dataFimExclusao;
	
	private String dataInicioExclusao;
	
	private String dataFimImplantacao;
	
	private String dataInicioImplantacao;
	
	private String indicadorImovelTarifaSocial;
	
	private String dataFimValidadeCartao;
	
	private String dataInicioValidadeCartao;
	
	private String cdRotaInicial;

	private String sequencialRotaInicial;

	private String cdRotaFinal;

	private String sequencialRotaFinal;
	
	private String ordenacaoRelatorio;
	
	private String[] tipoPocoList;
	
	private String indicadorHabilitaOuDesabilitaTipoPoco;
	
	//Aba de Débito
	private String todosTipoDebito;
	private String[] tipoDebito;
	private String valorDebitoInicial;
	private String valorDebitoFinal;
	private String qtdContasInicial;
	private String qtdContasFinal;	
	private String referenciaFaturaInicial;
	private String referenciaFaturaFinal;
	private String vencimentoInicial;
	private String vencimentoFinal;	
	private String qtdImoveis;
	private String qtdMaiores;
	private String indicadorCodigoBarra;
		
	public String getIndicadorCodigoBarra() {
		return indicadorCodigoBarra;
	}

	public void setIndicadorCodigoBarra(String indicadorCodigoBarra) {
		this.indicadorCodigoBarra = indicadorCodigoBarra;
	}

	public String getDataFimValidadeCartao() {
		return dataFimValidadeCartao;
	}

	public void setDataFimValidadeCartao(String dataFimValidadeCartao) {
		this.dataFimValidadeCartao = dataFimValidadeCartao;
	}

	public String getDataInicioValidadeCartao() {
		return dataInicioValidadeCartao;
	}

	public void setDataInicioValidadeCartao(String dataInicioValidadeCartao) {
		this.dataInicioValidadeCartao = dataInicioValidadeCartao;
	}

	public String getIndicadorImovelTarifaSocial() {
		return indicadorImovelTarifaSocial;
	}

	public void setIndicadorImovelTarifaSocial(String indicadorImovelTarifaSocial) {
		this.indicadorImovelTarifaSocial = indicadorImovelTarifaSocial;
	}

	public String getCelpeFinal() {
		return celpeFinal;
	}

	public void setCelpeFinal(String celpeFinal) {
		this.celpeFinal = celpeFinal;
	}

	public String getCelpeInicial() {
		return celpeInicial;
	}

	public void setCelpeInicial(String celpeInicial) {
		this.celpeInicial = celpeInicial;
	}

	public String getDataFimExclusao() {
		return dataFimExclusao;
	}

	public void setDataFimExclusao(String dataFimExclusao) {
		this.dataFimExclusao = dataFimExclusao;
	}

	public String getDataFimImplantacao() {
		return dataFimImplantacao;
	}

	public void setDataFimImplantacao(String dataFimImplantacao) {
		this.dataFimImplantacao = dataFimImplantacao;
	}

	public String getDataFimRecadastramento() {
		return dataFimRecadastramento;
	}

	public void setDataFimRecadastramento(String dataFimRecadastramento) {
		this.dataFimRecadastramento = dataFimRecadastramento;
	}

	public String getMesFimAdesao() {
		return mesFimAdesao;
	}

	public void setMesFimAdesao(String mesFimAdesao) {
		this.mesFimAdesao = mesFimAdesao;
	}

	public String getMesInicioAdesao() {
		return mesInicioAdesao;
	}

	public void setMesInicioAdesao(String mesInicioAdesao) {
		this.mesInicioAdesao = mesInicioAdesao;
	}

	public String getDataInicioExclusao() {
		return dataInicioExclusao;
	}

	public void setDataInicioExclusao(String dataInicioExclusao) {
		this.dataInicioExclusao = dataInicioExclusao;
	}

	public String getDataInicioImplantacao() {
		return dataInicioImplantacao;
	}

	public void setDataInicioImplantacao(String dataInicioImplantacao) {
		this.dataInicioImplantacao = dataInicioImplantacao;
	}

	public String getDataInicioRecadastramento() {
		return dataInicioRecadastramento;
	}

	public void setDataInicioRecadastramento(String dataInicioRecadastramento) {
		this.dataInicioRecadastramento = dataInicioRecadastramento;
	}

	public String getIdMotivoExclusao() {
		return idMotivoExclusao;
	}

	public void setIdMotivoExclusao(String idMotivoExclusao) {
		this.idMotivoExclusao = idMotivoExclusao;
	}

	public String getRecadastramentoNumeroFinal() {
		return recadastramentoNumeroFinal;
	}

	public void setRecadastramentoNumeroFinal(String recadastramentoNumeroFinal) {
		this.recadastramentoNumeroFinal = recadastramentoNumeroFinal;
	}

	public String getRecadastramentoNumeroInicial() {
		return recadastramentoNumeroInicial;
	}

	public void setRecadastramentoNumeroInicial(String recadastramentoNumeroInicial) {
		this.recadastramentoNumeroInicial = recadastramentoNumeroInicial;
	}

	public String getRendaFinal() {
		return rendaFinal;
	}

	public void setRendaFinal(String rendaFinal) {
		this.rendaFinal = rendaFinal;
	}

	public String getRendaInicial() {
		return rendaInicial;
	}

	public void setRendaInicial(String rendaInicial) {
		this.rendaInicial = rendaInicial;
	}

	public String getIdLogradouro() {
		return idLogradouro;
	}

	public String getAnormalidadeElo() {
		return anormalidadeElo;
	}

	public void setAnormalidadeElo(String anormalidadeElo) {
		this.anormalidadeElo = anormalidadeElo;
	}

	public String getAreaConstruidaFaixa() {
		return areaConstruidaFaixa;
	}

	public void setAreaConstruidaFaixa(String areaConstruidaFaixa) {
		this.areaConstruidaFaixa = areaConstruidaFaixa;
	}

	public String getCategoriaImovel() {
		return categoriaImovel;
	}

	public void setCategoriaImovel(String categoriaImovel) {
		this.categoriaImovel = categoriaImovel;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cep) {
		CEP = cep;
	}

	public String getDiaVencimentoAlternativo() {
		return diaVencimentoAlternativo;
	}

	public void setDiaVencimentoAlternativo(String diaVencimentoAlternativo) {
		this.diaVencimentoAlternativo = diaVencimentoAlternativo;
	}

	public String getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdImovelCondominio() {
		return idImovelCondominio;
	}

	public void setIdImovelCondominio(String idImovelCondominio) {
		this.idImovelCondominio = idImovelCondominio;
	}

	public String getIdImovelPrincipal() {
		return idImovelPrincipal;
	}

	public void setIdImovelPrincipal(String idImovelPrincipal) {
		this.idImovelPrincipal = idImovelPrincipal;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getIdNomeConta() {
		return idNomeConta;
	}

	public void setIdNomeConta(String idNomeConta) {
		this.idNomeConta = idNomeConta;
	}

	public String getInscricaoTipo() {
		return inscricaoTipo;
	}

	public void setInscricaoTipo(String inscricaoTipo) {
		this.inscricaoTipo = inscricaoTipo;
	}

	public String getLocalidadeDestinoID() {
		return localidadeDestinoID;
	}

	public void setLocalidadeDestinoID(String localidadeDestinoID) {
		this.localidadeDestinoID = localidadeDestinoID;
	}

	public String getLocalidadeOrigemID() {
		return localidadeOrigemID;
	}

	public void setLocalidadeOrigemID(String localidadeOrigemID) {
		this.localidadeOrigemID = localidadeOrigemID;
	}

	public String getLoteDestino() {
		return loteDestino;
	}

	public void setLoteDestino(String loteDestino) {
		this.loteDestino = loteDestino;
	}

	public String getLoteOrigem() {
		return loteOrigem;
	}

	public void setLoteOrigem(String loteOrigem) {
		this.loteOrigem = loteOrigem;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getNomeLocalidadeDestino() {
		return nomeLocalidadeDestino;
	}

	public void setNomeLocalidadeDestino(String nomeLocalidadeDestino) {
		this.nomeLocalidadeDestino = nomeLocalidadeDestino;
	}

	public String getNomeLocalidadeOrigem() {
		return nomeLocalidadeOrigem;
	}

	public void setNomeLocalidadeOrigem(String nomeLocalidadeOrigem) {
		this.nomeLocalidadeOrigem = nomeLocalidadeOrigem;
	}

	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getNomeSetorComercialDestino() {
		return nomeSetorComercialDestino;
	}

	public void setNomeSetorComercialDestino(String nomeSetorComercialDestino) {
		this.nomeSetorComercialDestino = nomeSetorComercialDestino;
	}

	public String getNomeSetorComercialOrigem() {
		return nomeSetorComercialOrigem;
	}

	public void setNomeSetorComercialOrigem(String nomeSetorComercialOrigem) {
		this.nomeSetorComercialOrigem = nomeSetorComercialOrigem;
	}

	public String getOcorrenciaCadastro() {
		return ocorrenciaCadastro;
	}

	public void setOcorrenciaCadastro(String ocorrenciaCadastro) {
		this.ocorrenciaCadastro = ocorrenciaCadastro;
	}

	public String getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public String getQuadraDestinoID() {
		return quadraDestinoID;
	}

	public void setQuadraDestinoID(String quadraDestinoID) {
		this.quadraDestinoID = quadraDestinoID;
	}

	public String getQuadraDestinoNM() {
		return quadraDestinoNM;
	}

	public void setQuadraDestinoNM(String quadraDestinoNM) {
		this.quadraDestinoNM = quadraDestinoNM;
	}

	public String getQuadraMensagemDestino() {
		return quadraMensagemDestino;
	}

	public void setQuadraMensagemDestino(String quadraMensagemDestino) {
		this.quadraMensagemDestino = quadraMensagemDestino;
	}

	public String getQuadraMensagemOrigem() {
		return quadraMensagemOrigem;
	}

	public void setQuadraMensagemOrigem(String quadraMensagemOrigem) {
		this.quadraMensagemOrigem = quadraMensagemOrigem;
	}

	public String getQuadraOrigemID() {
		return quadraOrigemID;
	}

	public void setQuadraOrigemID(String quadraOrigemID) {
		this.quadraOrigemID = quadraOrigemID;
	}

	public String getQuadraOrigemNM() {
		return quadraOrigemNM;
	}

	public void setQuadraOrigemNM(String quadraOrigemNM) {
		this.quadraOrigemNM = quadraOrigemNM;
	}

	public String getSetorComercialDestinoCD() {
		return setorComercialDestinoCD;
	}

	public void setSetorComercialDestinoCD(String setorComercialDestinoCD) {
		this.setorComercialDestinoCD = setorComercialDestinoCD;
	}

	public String getSetorComercialDestinoID() {
		return setorComercialDestinoID;
	}

	public void setSetorComercialDestinoID(String setorComercialDestinoID) {
		this.setorComercialDestinoID = setorComercialDestinoID;
	}

	public String getSetorComercialOrigemCD() {
		return setorComercialOrigemCD;
	}

	public void setSetorComercialOrigemCD(String setorComercialOrigemCD) {
		this.setorComercialOrigemCD = setorComercialOrigemCD;
	}

	public String getSetorComercialOrigemID() {
		return setorComercialOrigemID;
	}

	public void setSetorComercialOrigemID(String setorComercialOrigemID) {
		this.setorComercialOrigemID = setorComercialOrigemID;
	}

	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	public String getSituacaoCobranca() {
		return situacaoCobranca;
	}

	public void setSituacaoCobranca(String situacaoCobranca) {
		this.situacaoCobranca = situacaoCobranca;
	}

	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public String getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

	public String getTarifaConsumo() {
		return tarifaConsumo;
	}

	public void setTarifaConsumo(String tarifaConsumo) {
		this.tarifaConsumo = tarifaConsumo;
	}

	public String getTarifaSocialCartaoTipoId() {
		return tarifaSocialCartaoTipoId;
	}

	public void setTarifaSocialCartaoTipoId(String tarifaSocialCartaoTipoId) {
		this.tarifaSocialCartaoTipoId = tarifaSocialCartaoTipoId;
	}

	public String getTarifaSocialExclusaoMotivoId() {
		return tarifaSocialExclusaoMotivoId;
	}

	public void setTarifaSocialExclusaoMotivoId(String tarifaSocialExclusaoMotivoId) {
		this.tarifaSocialExclusaoMotivoId = tarifaSocialExclusaoMotivoId;
	}

	public String getTarifaSocialRendaTipoId() {
		return tarifaSocialRendaTipoId;
	}

	public void setTarifaSocialRendaTipoId(String tarifaSocialRendaTipoId) {
		this.tarifaSocialRendaTipoId = tarifaSocialRendaTipoId;
	}

	public String getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	public String getTipoPoco() {
		return tipoPoco;
	}

	public void setTipoPoco(String tipoPoco) {
		this.tipoPoco = tipoPoco;
	}

	public String getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	public String getTipoSituacaoEspecialCobranca() {
		return tipoSituacaoEspecialCobranca;
	}

	public void setTipoSituacaoEspecialCobranca(String tipoSituacaoEspecialCobranca) {
		this.tipoSituacaoEspecialCobranca = tipoSituacaoEspecialCobranca;
	}

	public String getTipoSituacaoEspecialFaturamento() {
		return tipoSituacaoEspecialFaturamento;
	}

	public void setTipoSituacaoEspecialFaturamento(
			String tipoSituacaoEspecialFaturamento) {
		this.tipoSituacaoEspecialFaturamento = tipoSituacaoEspecialFaturamento;
	}

	public void setIdLogradouro(String idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	public String getConsumoMinimoFinal() {
		return consumoMinimoFinal;
	}

	public void setConsumoMinimoFinal(String consumoMinimoFinal) {
		this.consumoMinimoFinal = consumoMinimoFinal;
	}

	public String getConsumoMinimoInicial() {
		return consumoMinimoInicial;
	}

	public void setConsumoMinimoInicial(String consumoMinimoInicial) {
		this.consumoMinimoInicial = consumoMinimoInicial;
	}

	public String getConsumoMinimo() {
		return consumoMinimo;
	}

	public void setConsumoMinimo(String consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}

	public String getIntervaloPercentualEsgotoFinal() {
		return intervaloPercentualEsgotoFinal;
	}

	public void setIntervaloPercentualEsgotoFinal(
			String intervaloPercentualEsgotoFinal) {
		this.intervaloPercentualEsgotoFinal = intervaloPercentualEsgotoFinal;
	}

	public String getIntervaloPercentualEsgotoInicial() {
		return intervaloPercentualEsgotoInicial;
	}

	public void setIntervaloPercentualEsgotoInicial(
			String intervaloPercentualEsgotoInicial) {
		this.intervaloPercentualEsgotoInicial = intervaloPercentualEsgotoInicial;
	}

	public String getConsumoMinimoFixadoEsgotoFinal() {
		return consumoMinimoFixadoEsgotoFinal;
	}

	public void setConsumoMinimoFixadoEsgotoFinal(
			String consumoMinimoFixadoEsgotoFinal) {
		this.consumoMinimoFixadoEsgotoFinal = consumoMinimoFixadoEsgotoFinal;
	}

	public String getConsumoMinimoFixadoEsgotoInicial() {
		return consumoMinimoFixadoEsgotoInicial;
	}

	public void setConsumoMinimoFixadoEsgotoInicial(
			String consumoMinimoFixadoEsgotoInicial) {
		this.consumoMinimoFixadoEsgotoInicial = consumoMinimoFixadoEsgotoInicial;
	}

	public String getIndicadorMedicao() {
		return indicadorMedicao;
	}

	public void setIndicadorMedicao(String indicadorMedicao) {
		this.indicadorMedicao = indicadorMedicao;
	}

	public String getIntervaloMediaMinimaImovelFinal() {
		return intervaloMediaMinimaImovelFinal;
	}

	public void setIntervaloMediaMinimaImovelFinal(
			String intervaloMediaMinimaImovelFinal) {
		this.intervaloMediaMinimaImovelFinal = intervaloMediaMinimaImovelFinal;
	}

	public String getIntervaloMediaMinimaImovelInicio() {
		return intervaloMediaMinimaImovelInicio;
	}

	public void setIntervaloMediaMinimaImovelInicio(
			String intervaloMediaMinimaImovelInicio) {
		this.intervaloMediaMinimaImovelInicio = intervaloMediaMinimaImovelInicio;
	}

	public String getIntervaloMediaMinimaHidrometroFinal() {
		return intervaloMediaMinimaHidrometroFinal;
	}

	public void setIntervaloMediaMinimaHidrometroFinal(
			String intervaloMediaMinimaHidrometroFinal) {
		this.intervaloMediaMinimaHidrometroFinal = intervaloMediaMinimaHidrometroFinal;
	}

	public String getIntervaloMediaMinimaHidrometroInicio() {
		return intervaloMediaMinimaHidrometroInicio;
	}

	public void setIntervaloMediaMinimaHidrometroInicio(
			String intervaloMediaMinimaHidrometroInicio) {
		this.intervaloMediaMinimaHidrometroInicio = intervaloMediaMinimaHidrometroInicio;
	}

	public String getQuantidadeEconomiasFinal() {
		return quantidadeEconomiasFinal;
	}

	public void setQuantidadeEconomiasFinal(String quantidadeEconomiasFinal) {
		this.quantidadeEconomiasFinal = quantidadeEconomiasFinal;
	}

	public String getQuantidadeEconomiasInicial() {
		return quantidadeEconomiasInicial;
	}

	public void setQuantidadeEconomiasInicial(String quantidadeEconomiasInicial) {
		this.quantidadeEconomiasInicial = quantidadeEconomiasInicial;
	}

	public String getNumeroPontosInicial() {
		return numeroPontosInicial;
	}

	public void setNumeroPontosInicial(String numeroPontosInicial) {
		this.numeroPontosInicial = numeroPontosInicial;
	}

	public String getNumeroPontosFinal() {
		return numeroPontosFinal;
	}

	public void setNumeroPontosFinal(String numeroPontosFinal) {
		this.numeroPontosFinal = numeroPontosFinal;
	}

	public String getNumeroMoradoresFinal() {
		return numeroMoradoresFinal;
	}

	public void setNumeroMoradoresFinal(String numeroMoradoresFinal) {
		this.numeroMoradoresFinal = numeroMoradoresFinal;
	}

	public String getNumeroMoradoresInicial() {
		return numeroMoradoresInicial;
	}

	public void setNumeroMoradoresInicial(String numeroMoradoresInicial) {
		this.numeroMoradoresInicial = numeroMoradoresInicial;
	}

	public String getAreaConstruidaFinal() {
		return areaConstruidaFinal;
	}

	public void setAreaConstruidaFinal(String areaConstruidaFinal) {
		this.areaConstruidaFinal = areaConstruidaFinal;
	}

	public String getAreaConstruidaInicial() {
		return areaConstruidaInicial;
	}

	public void setAreaConstruidaInicial(String areaConstruidaInicial) {
		this.areaConstruidaInicial = areaConstruidaInicial;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getIdClienteTipo() {
		return idClienteTipo;
	}

	public void setIdClienteTipo(String idClienteTipo) {
		this.idClienteTipo = idClienteTipo;
	}

	public String getDescricaoCep() {
		return descricaoCep;
	}

	public void setDescricaoCep(String descricaoCep) {
		this.descricaoCep = descricaoCep;
	}

	public String getInscricaoImovelCondominio() {
		return inscricaoImovelCondominio;
	}

	public void setInscricaoImovelCondominio(String inscricaoImovelCondominio) {
		this.inscricaoImovelCondominio = inscricaoImovelCondominio;
	}

	public String getInscricaoImovelPrincipal() {
		return inscricaoImovelPrincipal;
	}

	public void setInscricaoImovelPrincipal(String inscricaoImovelPrincipal) {
		this.inscricaoImovelPrincipal = inscricaoImovelPrincipal;
	}

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getCdRotaFinal() {
		return cdRotaFinal;
	}

	public void setCdRotaFinal(String cdRotaFinal) {
		this.cdRotaFinal = cdRotaFinal;
	}

	public String getCdRotaInicial() {
		return cdRotaInicial;
	}

	public void setCdRotaInicial(String cdRotaInicial) {
		this.cdRotaInicial = cdRotaInicial;
	}

	public String getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}

	public void setSequencialRotaFinal(String sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}

	public String getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}

	public void setSequencialRotaInicial(String sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}

	//Aba de Débito
	/**
	 * @return Retorna o campo qtdContasFinal.
	 */
	public String getQtdContasFinal() {
		return qtdContasFinal;
	}

	/**
	 * @param qtdContasFinal O qtdContasFinal a ser setado.
	 */
	public void setQtdContasFinal(String qtdContasFinal) {
		this.qtdContasFinal = qtdContasFinal;
	}

	/**
	 * @return Retorna o campo qtdContasInicial.
	 */
	public String getQtdContasInicial() {
		return qtdContasInicial;
	}

	/**
	 * @param qtdContasInicial O qtdContasInicial a ser setado.
	 */
	public void setQtdContasInicial(String qtdContasInicial) {
		this.qtdContasInicial = qtdContasInicial;
	}

	/**
	 * @return Retorna o campo qtdImoveis.
	 */
	public String getQtdImoveis() {
		return qtdImoveis;
	}

	/**
	 * @param qtdImoveis O qtdImoveis a ser setado.
	 */
	public void setQtdImoveis(String qtdImoveis) {
		this.qtdImoveis = qtdImoveis;
	}

	/**
	 * @return Retorna o campo qtdMaiores.
	 */
	public String getQtdMaiores() {
		return qtdMaiores;
	}

	/**
	 * @param qtdMaiores O qtdMaiores a ser setado.
	 */
	public void setQtdMaiores(String qtdMaiores) {
		this.qtdMaiores = qtdMaiores;
	}

	/**
	 * @return Retorna o campo referenciaFaturaFinal.
	 */
	public String getReferenciaFaturaFinal() {
		return referenciaFaturaFinal;
	}

	/**
	 * @param referenciaFaturaFinal O referenciaFaturaFinal a ser setado.
	 */
	public void setReferenciaFaturaFinal(String referenciaFaturaFinal) {
		this.referenciaFaturaFinal = referenciaFaturaFinal;
	}

	/**
	 * @return Retorna o campo referenciaFaturaInicial.
	 */
	public String getReferenciaFaturaInicial() {
		return referenciaFaturaInicial;
	}

	/**
	 * @param referenciaFaturaInicial O referenciaFaturaInicial a ser setado.
	 */
	public void setReferenciaFaturaInicial(String referenciaFaturaInicial) {
		this.referenciaFaturaInicial = referenciaFaturaInicial;
	}

	/**
	 * @return Retorna o campo tipoDebito.
	 */
	public String[] getTipoDebito() {
		return tipoDebito;
	}

	/**
	 * @param tipoDebito O tipoDebito a ser setado.
	 */
	public void setTipoDebito(String[] tipoDebito) {
		this.tipoDebito = tipoDebito;
	}

	/**
	 * @return Retorna o campo valorDebitoFinal.
	 */
	public String getValorDebitoFinal() {
		return valorDebitoFinal;
	}

	/**
	 * @param valorDebitoFinal O valorDebitoFinal a ser setado.
	 */
	public void setValorDebitoFinal(String valorDebitoFinal) {
		this.valorDebitoFinal = valorDebitoFinal;
	}

	/**
	 * @return Retorna o campo valorDebitoInicial.
	 */
	public String getValorDebitoInicial() {
		return valorDebitoInicial;
	}

	/**
	 * @param valorDebitoInicial O valorDebitoInicial a ser setado.
	 */
	public void setValorDebitoInicial(String valorDebitoInicial) {
		this.valorDebitoInicial = valorDebitoInicial;
	}

	/**
	 * @return Retorna o campo vencimentoFinal.
	 */
	public String getVencimentoFinal() {
		return vencimentoFinal;
	}

	/**
	 * @param vencimentoFinal O vencimentoFinal a ser setado.
	 */
	public void setVencimentoFinal(String vencimentoFinal) {
		this.vencimentoFinal = vencimentoFinal;
	}

	/**
	 * @return Retorna o campo vencimentoInicial.
	 */
	public String getVencimentoInicial() {
		return vencimentoInicial;
	}

	/**
	 * @param vencimentoInicial O vencimentoInicial a ser setado.
	 */
	public void setVencimentoInicial(String vencimentoInicial) {
		this.vencimentoInicial = vencimentoInicial;
	}

	/**
	 * @return Retorna o campo todosTipoDebito.
	 */
	public String getTodosTipoDebito() {
		return todosTipoDebito;
	}

	/**
	 * @param todosTipoDebito O todosTipoDebito a ser setado.
	 */
	public void setTodosTipoDebito(String todosTipoDebito) {
		this.todosTipoDebito = todosTipoDebito;
	}

	public void reset(ActionMapping actionMapping, 
			HttpServletRequest httpServletRequest) {   
		tipoDebito = null;

	}

	/**
	 * @return Retorna o campo ordenacaoRelatorio.
	 */
	public String getOrdenacaoRelatorio() {
		return ordenacaoRelatorio;
	}

	/**
	 * @param ordenacaoRelatorio O ordenacaoRelatorio a ser setado.
	 */
	public void setOrdenacaoRelatorio(String ordenacaoRelatorio) {
		this.ordenacaoRelatorio = ordenacaoRelatorio;
	}

	public String getIndicadorCpfCnpjInformado() {
		return indicadorCpfCnpjInformado;
	}

	public void setIndicadorCpfCnpjInformado(String indicadorCpfCnpjInformado) {
		this.indicadorCpfCnpjInformado = indicadorCpfCnpjInformado;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String[] getTipoPocoList() {
		return tipoPocoList;
	}

	public void setTipoPocoList(String[] tipoPocoList) {
		this.tipoPocoList = tipoPocoList;
	}

	public String getIndicadorHabilitaOuDesabilitaTipoPoco() {
		return indicadorHabilitaOuDesabilitaTipoPoco;
	}

	public void setIndicadorHabilitaOuDesabilitaTipoPoco(
			String indicadorHabilitaOuDesabilitaTipoPoco) {
		this.indicadorHabilitaOuDesabilitaTipoPoco = indicadorHabilitaOuDesabilitaTipoPoco;
	}
	
	
	
}

