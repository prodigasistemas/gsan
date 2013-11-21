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
* Ivan Sérgio Virginio da Silva Júnior
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
package gcom.gui.atendimentopublico.ordemservico;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0732] Gerar Relatorio Acompanhamento Ordem de Servico Hidrometro
 * 
 * @author Ivan Sérgio, Diogo Peixoto
 *
 * @date 04/12/2007, 27/03/2008, 04/04/2008, 31/08/2011
 * @alteracao: 27/03/2008 - Adicionado Motivo Encerramento; Setor Comercial;
 * 			   04/04/2008 - Adicionado o Tipo "Por Local de Instalação";
 * 			   31/08/2011 - Adicionado o Tipo "Por Motivo de Encerramento";
 */
public class GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String tipoOrdem;
	private String situacaoOrdemServico;
	private String firma;
	private String nomeFirma;
	private String localidadeInicial;
	private String localidadeFinal;
	private String nomeLocalidadeInicial;
	private String nomeLocalidadeFinal;
	private String dataEncerramentoInicial;
	private String dataEncerramentoFinal;
	private String tipoRelatorioAcompanhamento;
	private String idMotivoEncerramento;
	private String descricaoMotivoEncerramento;	
	private String setorComercialInicial;
	private String setorComercialFinal;
	private String codigoSetorComercialInicial;
	private String codigoSetorComercialFinal;
	private String nomeSetorComercialInicial;
	private String nomeSetorComercialFinal;
	private String quadraInicial;
	private String quadraFinal;
	private String idQuadraInicial;
	private String idQuadraFinal;
	private String rotaInicial;
	private String rotaFinal;
	private String rotaSequenciaInicial;
	private String rotaSequenciaFinal;
	private String inscricaoTipo;
	
	
	public static final String TIPO_ORDEM_INSTALACAO = "INSTALACAO";
	public static final String TIPO_ORDEM_SUBSTITUICAO = "SUBSTITUICAO";
	public static final String TIPO_ORDEM_REMOCAO = "REMOCAO";
	public static final String TIPO_ORDEM_INSPECAO_ANORMALIDADE = "INSPECAO DE ANORMALIDADE";
	public static final String TIPO_ORDEM_SUBSTITUICAO_COM_REMOVAO = "SUBSTITUICAO COM REMOCAO";
	public static final String SITUACAO_ORDEM_SERVICO_ENCERRADAS = "ENCERRADAS";
	public static final String SITUACAO_ORDEM_SERVICO_PENDENTES = "PENDENTES";
	public static final Integer TIPO_RELATORIO_ANALITICO = 1;
	public static final Integer TIPO_RELATORIO_SINTETICO = 2;
	public static final Integer TIPO_RELATORIO_POR_LOCAL_INSTALACAO = 3;
	public static final Integer TIPO_RELATORIO_POR_MOTIVO_ENCERRAMENTO = 4;

	public String getDataEncerramentoFinal() {
		return dataEncerramentoFinal;
	}

	public void setDataEncerramentoFinal(String dataEncerramentoFinal) {
		this.dataEncerramentoFinal = dataEncerramentoFinal;
	}

	public String getDataEncerramentoInicial() {
		return dataEncerramentoInicial;
	}

	public void setDataEncerramentoInicial(String dataEncerramentoInicial) {
		this.dataEncerramentoInicial = dataEncerramentoInicial;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public String getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public String getNomeFirma() {
		return nomeFirma;
	}

	public void setNomeFirma(String nomeFirma) {
		this.nomeFirma = nomeFirma;
	}

	public String getSituacaoOrdemServico() {
		return situacaoOrdemServico;
	}

	public void setSituacaoOrdemServico(String situacaoOrdemServico) {
		this.situacaoOrdemServico = situacaoOrdemServico;
	}

	public String getTipoOrdem() {
		return tipoOrdem;
	}

	public void setTipoOrdem(String tipoOrdem) {
		this.tipoOrdem = tipoOrdem;
	}

	public String gettipoRelatorioAcompanhamento() {
		return tipoRelatorioAcompanhamento;
	}

	public void settipoRelatorioAcompanhamento(String tipoRelatorioAcompanhamento) {
		this.tipoRelatorioAcompanhamento = tipoRelatorioAcompanhamento;
	}
	
	public String getDescricaoMotivoEncerramento() {
		return descricaoMotivoEncerramento;
	}

	public void setDescricaoMotivoEncerramento(String descricaoMotivoEncerramento) {
		this.descricaoMotivoEncerramento = descricaoMotivoEncerramento;
	}

	public String getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}

	public void setIdMotivoEncerramento(String idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}

	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}

	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}

	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}

	public void setNomeSetorComercialFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}

	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}

	public String getQuadraInicial() {
		return quadraInicial;
	}

	public void setQuadraInicial(String quadraInicial) {
		this.quadraInicial = quadraInicial;
	}

	public String getQuadraFinal() {
		return quadraFinal;
	}

	public void setQuadraFinal(String quadraFinal) {
		this.quadraFinal = quadraFinal;
	}

	public String getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(String rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public String getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(String rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public String getRotaSequenciaInicial() {
		return rotaSequenciaInicial;
	}

	public void setRotaSequenciaInicial(String rotaSequenciaInicial) {
		this.rotaSequenciaInicial = rotaSequenciaInicial;
	}

	public String getRotaSequenciaFinal() {
		return rotaSequenciaFinal;
	}

	public void setRotaSequenciaFinal(String rotaSequenciaFinal) {
		this.rotaSequenciaFinal = rotaSequenciaFinal;
	}

	public String getInscricaoTipo() {
		return inscricaoTipo;
	}

	public void setInscricaoTipo(String inscricaoTipo) {
		this.inscricaoTipo = inscricaoTipo;
	}

	public String getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(String setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	public String getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(String setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	public String getIdQuadraInicial() {
		return idQuadraInicial;
	}

	public void setIdQuadraInicial(String idQuadraInicial) {
		this.idQuadraInicial = idQuadraInicial;
	}

	public String getIdQuadraFinal() {
		return idQuadraFinal;
	}

	public void setIdQuadraFinal(String idQuadraFinal) {
		this.idQuadraFinal = idQuadraFinal;
	}
}