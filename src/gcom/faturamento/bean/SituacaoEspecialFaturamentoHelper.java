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
package gcom.faturamento.bean;

import gcom.seguranca.acesso.usuario.Usuario;


public class SituacaoEspecialFaturamentoHelper {
	
	private String idImovel;
	
	private String matricula;
	
	private String localidadeOrigemID;
	
	private String localidadeDestinoID;
	
	private String nomeLocalidadeOrigem;
	
	private String nomeLocalidadeDestino;
	
	private String setorComercialOrigemCD;
	
	private String setorComercialDestinoCD;
	
	private String setorComercialOrigemID;
	
	private String setorComercialDestinoID;
	
	private String nomeSetorComercialOrigem;
	
	private String nomeSetorComercialDestino;
	
	private String quadraOrigemNM;
	
	private String quadraDestinoNM;
	
	private String quadraMensagemOrigem;
	
	private String quadraMensagemDestino;
	
	private String quadraOrigemID;
	
	private String quadraDestinoID;
	
	private String loteOrigem;
	
	private String loteDestino;
	
	private String subloteOrigem;
	
	private String subloteDestino;
	
	private String quantidadeImoveisCOMSituacaoEspecialFaturamento;
	
	private String quantidadeImoveisSEMSituacaoEspecialFaturamento;
	
	private String quantidadeImoveisAtualizados;
	
	private String tipoSituacaoEspecialFaturamento;
	
	private String motivoSituacaoEspecialFaturamento;
	
	private String mesAnoReferenciaFaturamentoInicial;
	
	private String mesAnoReferenciaFaturamentoFinal;

	private String inscricaoTipo;
	
	private String idFaturamentoSituacaoTipo;
	
	private String idFaturamentoSituacaoMotivo;	

	private String liberarBotoes;
	
	private String idUsuario;
	
	private String codigoRotaInicial;
	
	private String codigoRotaFinal;
	
	private String sequencialRotaInicial;
	
	private String sequencialRotaFinal;
	
	private Integer numeroConsumoAguaMedido;
    
    private Integer numeroConsumoAguaNaoMedido;
    
    private Integer numeroVolumeEsgotoMedido;
    
    private Integer numeroVolumeEsgotoNaoMedido;
    
    private String observacaoInforma;
    
    private String observacaoRetira;
    
    private String idUsuarioInforma;
    
    private String idUsuarioRetira;
    
    private String indicadorComercial;
    
    private String indicadorIndustrial;
    
    private String indicadorResidencial;
    
    private String indicadorPublico;
    
    private String[] idsCategoria;
    
    private String quantidadeDeImoveis;
    
    private Usuario usuarioLogado;
    
    private String observacao;
    
    private String indicadorConsumoImovel;
    
    private Integer anoMesReferenciaFaturamentoGrupo;
    
	private String mesAnoFaturamentoRetirada;    
	
	private String nomeUsuario;    

	public Integer getAnoMesReferenciaFaturamentoGrupo() {
		return anoMesReferenciaFaturamentoGrupo;
	}

	public void setAnoMesReferenciaFaturamentoGrupo(
			Integer anoMesReferenciaFaturamentoGrupo) {
		this.anoMesReferenciaFaturamentoGrupo = anoMesReferenciaFaturamentoGrupo;
	}

	public String getIndicadorConsumoImovel() {
		return indicadorConsumoImovel;
	}

	public void setIndicadorConsumoImovel(String indicadorConsumoImovel) {
		this.indicadorConsumoImovel = indicadorConsumoImovel;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String[] getIdsCategoria() {
		return idsCategoria;
	}

	public void setIdsCategoria(String[] idsCategoria) {
		this.idsCategoria = idsCategoria;
	}

	public String getIndicadorComercial() {
		return indicadorComercial;
	}

	public void setIndicadorComercial(String indicadorComercial) {
		this.indicadorComercial = indicadorComercial;
	}

	public String getIndicadorIndustrial() {
		return indicadorIndustrial;
	}

	public void setIndicadorIndustrial(String indicadorIndustrial) {
		this.indicadorIndustrial = indicadorIndustrial;
	}

	public String getIndicadorPublico() {
		return indicadorPublico;
	}

	public void setIndicadorPublico(String indicadorPublico) {
		this.indicadorPublico = indicadorPublico;
	}

	public String getIndicadorResidencial() {
		return indicadorResidencial;
	}

	public void setIndicadorResidencial(String indicadorResidencial) {
		this.indicadorResidencial = indicadorResidencial;
	}

	public String getCodigoRotaFinal() {
		return codigoRotaFinal;
	}

	public void setCodigoRotaFinal(String codigoRotaFinal) {
		this.codigoRotaFinal = codigoRotaFinal;
	}

	public String getCodigoRotaInicial() {
		return codigoRotaInicial;
	}

	public void setCodigoRotaInicial(String codigoRotaInicial) {
		this.codigoRotaInicial = codigoRotaInicial;
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

	public String getLiberarBotoes() {
		return liberarBotoes;
	}

	public void setLiberarBotoes(String liberarBotoes) {
		this.liberarBotoes = liberarBotoes;
	}
	
	
	public String getIdFaturamentoSituacaoMotivo() {
		return idFaturamentoSituacaoMotivo;
	}

	public void setIdFaturamentoSituacaoMotivo(String idFaturamentoSituacaoMotivo) {
		this.idFaturamentoSituacaoMotivo = idFaturamentoSituacaoMotivo;
	}

	public String getIdFaturamentoSituacaoTipo() {
		return idFaturamentoSituacaoTipo;
	}

	public void setIdFaturamentoSituacaoTipo(String idFaturamentoSituacaoTipo) {
		this.idFaturamentoSituacaoTipo = idFaturamentoSituacaoTipo;
	}

	public String getInscricaoTipo() {
		return inscricaoTipo;
	}

	public void setInscricaoTipo(String inscricaoTipo) {
		this.inscricaoTipo = inscricaoTipo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getQuantidadeImoveisCOMSituacaoEspecialFaturamento() {
		return quantidadeImoveisCOMSituacaoEspecialFaturamento;
	}

	public void setQuantidadeImoveisCOMSituacaoEspecialFaturamento(
			String quantidadeImoveisCOMSituacaoEspecialFaturamento) {
		this.quantidadeImoveisCOMSituacaoEspecialFaturamento = quantidadeImoveisCOMSituacaoEspecialFaturamento;
	}

	public String getQuantidadeImoveisSEMSituacaoEspecialFaturamento() {
		return quantidadeImoveisSEMSituacaoEspecialFaturamento;
	}

	public void setQuantidadeImoveisSEMSituacaoEspecialFaturamento(
			String quantidadeImoveisSEMSituacaoEspecialFaturamento) {
		this.quantidadeImoveisSEMSituacaoEspecialFaturamento = quantidadeImoveisSEMSituacaoEspecialFaturamento;
	}

	public String getSubloteDestino() {
		return subloteDestino;
	}

	public void setSubloteDestino(String subloteDestino) {
		this.subloteDestino = subloteDestino;
	}

	public String getSubloteOrigem() {
		return subloteOrigem;
	}

	public void setSubloteOrigem(String subloteOrigem) {
		this.subloteOrigem = subloteOrigem;
	}

	public String getMesAnoReferenciaFaturamentoFinal() {
		return mesAnoReferenciaFaturamentoFinal;
	}

	public void setMesAnoReferenciaFaturamentoFinal(
			String mesAnoReferenciaFaturamentoFinal) {
		this.mesAnoReferenciaFaturamentoFinal = mesAnoReferenciaFaturamentoFinal;
	}

	public String getMesAnoReferenciaFaturamentoInicial() {
		return mesAnoReferenciaFaturamentoInicial;
	}

	public void setMesAnoReferenciaFaturamentoInicial(
			String mesAnoReferenciaFaturamentoInicial) {
		this.mesAnoReferenciaFaturamentoInicial = mesAnoReferenciaFaturamentoInicial;
	}

	public String getMotivoSituacaoEspecialFaturamento() {
		return motivoSituacaoEspecialFaturamento;
	}

	public void setMotivoSituacaoEspecialFaturamento(
			String motivoSituacaoEspecialFaturamento) {
		this.motivoSituacaoEspecialFaturamento = motivoSituacaoEspecialFaturamento;
	}

	public String getQuantidadeImoveisAtualizados() {
		return quantidadeImoveisAtualizados;
	}

	public void setQuantidadeImoveisAtualizados(String quantidadeImoveisAtualizados) {
		this.quantidadeImoveisAtualizados = quantidadeImoveisAtualizados;
	}

	public String getTipoSituacaoEspecialFaturamento() {
		return tipoSituacaoEspecialFaturamento;
	}

	public void setTipoSituacaoEspecialFaturamento(
			String tipoSituacaoEspecialFaturamento) {
		this.tipoSituacaoEspecialFaturamento = tipoSituacaoEspecialFaturamento;
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

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getNumeroConsumoAguaMedido() {
		return numeroConsumoAguaMedido;
	}

	public void setNumeroConsumoAguaMedido(Integer numeroConsumoAguaMedido) {
		this.numeroConsumoAguaMedido = numeroConsumoAguaMedido;
	}

	public Integer getNumeroConsumoAguaNaoMedido() {
		return numeroConsumoAguaNaoMedido;
	}

	public void setNumeroConsumoAguaNaoMedido(Integer numeroConsumoAguaNaoMedido) {
		this.numeroConsumoAguaNaoMedido = numeroConsumoAguaNaoMedido;
	}

	public Integer getNumeroVolumeEsgotoMedido() {
		return numeroVolumeEsgotoMedido;
	}

	public void setNumeroVolumeEsgotoMedido(Integer numeroVolumeEsgotoMedido) {
		this.numeroVolumeEsgotoMedido = numeroVolumeEsgotoMedido;
	}

	public Integer getNumeroVolumeEsgotoNaoMedido() {
		return numeroVolumeEsgotoNaoMedido;
	}

	public void setNumeroVolumeEsgotoNaoMedido(Integer numeroVolumeEsgotoNaoMedido) {
		this.numeroVolumeEsgotoNaoMedido = numeroVolumeEsgotoNaoMedido;
	}

	public String getObservacaoInforma() {
		return observacaoInforma;
	}

	public void setObservacaoInforma(String observacaoInforma) {
		this.observacaoInforma = observacaoInforma;
	}

	public String getObservacaoRetira() {
		return observacaoRetira;
	}

	public void setObservacaoRetira(String observacaoRetira) {
		this.observacaoRetira = observacaoRetira;
	}

	public String getIdUsuarioInforma() {
		return idUsuarioInforma;
	}

	public void setIdUsuarioInforma(String idUsuarioInforma) {
		this.idUsuarioInforma = idUsuarioInforma;
	}

	public String getIdUsuarioRetira() {
		return idUsuarioRetira;
	}

	public void setIdUsuarioRetira(String idUsuarioRetira) {
		this.idUsuarioRetira = idUsuarioRetira;
	}

	public String getQuantidadeDeImoveis() {
		return quantidadeDeImoveis;
	}

	public void setQuantidadeDeImoveis(String quantidadeDeImoveis) {
		this.quantidadeDeImoveis = quantidadeDeImoveis;
	}

	public String getMesAnoFaturamentoRetirada() {
		return mesAnoFaturamentoRetirada;
	}

	public void setMesAnoFaturamentoRetirada(String mesAnoFaturamentoRetirada) {
		this.mesAnoFaturamentoRetirada = mesAnoFaturamentoRetirada;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
}
