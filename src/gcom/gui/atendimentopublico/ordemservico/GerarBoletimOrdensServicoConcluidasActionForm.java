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
 * [UC0765] Gerar Boletim Ordens Servico Concluidas
 * 
 * @author Ivan Sérgio
 * @date 18/04/2008
 * 
 */
public class GerarBoletimOrdensServicoConcluidasActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idFirma;
	private String nomeFirma;
	private String idLocalidade;
	private String nomeLocalidade;
	private String anoMesReferenciaEncerramento;
	
	private Integer totalNaoFiscalizadas = 0;
	private Integer totalAprovadas = 0;
	private Integer totalNaoFiscalizadasAprovadas = 0;
	private Integer totalReprovadas = 0;
	private Integer totalEncerradasMesesAnterioresAprovadasMes = 0;	
	private Integer totalBoletim = 0;
	
	private Integer totalHidrometrosInstaladosMuro = 0;
	private Integer totalHidrometrosInstaladosCalcada = 0;
	private Integer totalHidrometrosInstaladosJardim = 0;
	
	private Integer totalHidrometrosSubstituidosSemTrocaCaixa = 0;
	private Integer totalHidrometrosSubstituidosComTrocaCaixaMuro = 0;
	private Integer totalHidrometrosSubstituidosComTrocaCaixaCalcada = 0;
	
	private Integer totalTrocaRegistro = 0;	
	
	public Integer getTotalAprovadas() {
		return totalAprovadas;
	}
	public void setTotalAprovadas(Integer totalAprovadas) {
		this.totalAprovadas = totalAprovadas;
	}
	public Integer getTotalBoletim() {
		return totalBoletim;
	}
	public void setTotalBoletim(Integer totalBoletim) {
		this.totalBoletim = totalBoletim;
	}
	public Integer getTotalEncerradasMesesAnterioresAprovadasMes() {
		return totalEncerradasMesesAnterioresAprovadasMes;
	}
	public void setTotalEncerradasMesesAnterioresAprovadasMes(
			Integer totalEncerradasMesesAnterioresAprovadasMes) {
		this.totalEncerradasMesesAnterioresAprovadasMes = totalEncerradasMesesAnterioresAprovadasMes;
	}
	public Integer getTotalHidrometrosInstaladosCalcada() {
		return totalHidrometrosInstaladosCalcada;
	}
	public void setTotalHidrometrosInstaladosCalcada(
			Integer totalHidrometrosInstaladosCalcada) {
		this.totalHidrometrosInstaladosCalcada = totalHidrometrosInstaladosCalcada;
	}
	public Integer getTotalHidrometrosInstaladosJardim() {
		return totalHidrometrosInstaladosJardim;
	}
	public void setTotalHidrometrosInstaladosJardim(
			Integer totalHidrometrosInstaladosJardim) {
		this.totalHidrometrosInstaladosJardim = totalHidrometrosInstaladosJardim;
	}
	public Integer getTotalHidrometrosInstaladosMuro() {
		return totalHidrometrosInstaladosMuro;
	}
	public void setTotalHidrometrosInstaladosMuro(
			Integer totalHidrometrosInstaladosMuro) {
		this.totalHidrometrosInstaladosMuro = totalHidrometrosInstaladosMuro;
	}
	public Integer getTotalHidrometrosSubstituidosComTrocaCaixaCalcada() {
		return totalHidrometrosSubstituidosComTrocaCaixaCalcada;
	}
	public void setTotalHidrometrosSubstituidosComTrocaCaixaCalcada(
			Integer totalHidrometrosSubstituidosComTrocaCaixaCalcada) {
		this.totalHidrometrosSubstituidosComTrocaCaixaCalcada = totalHidrometrosSubstituidosComTrocaCaixaCalcada;
	}
	public Integer getTotalHidrometrosSubstituidosComTrocaCaixaMuro() {
		return totalHidrometrosSubstituidosComTrocaCaixaMuro;
	}
	public void setTotalHidrometrosSubstituidosComTrocaCaixaMuro(
			Integer totalHidrometrosSubstituidosComTrocaCaixaMuro) {
		this.totalHidrometrosSubstituidosComTrocaCaixaMuro = totalHidrometrosSubstituidosComTrocaCaixaMuro;
	}
	public Integer getTotalHidrometrosSubstituidosSemTrocaCaixa() {
		return totalHidrometrosSubstituidosSemTrocaCaixa;
	}
	public void setTotalHidrometrosSubstituidosSemTrocaCaixa(
			Integer totalHidrometrosSubstituidosSemTrocaCaixa) {
		this.totalHidrometrosSubstituidosSemTrocaCaixa = totalHidrometrosSubstituidosSemTrocaCaixa;
	}
	public Integer getTotalNaoFiscalizadas() {
		return totalNaoFiscalizadas;
	}
	public void setTotalNaoFiscalizadas(Integer totalNaoFiscalizadas) {
		this.totalNaoFiscalizadas = totalNaoFiscalizadas;
	}
	public Integer getTotalTrocaRegistro() {
		return totalTrocaRegistro;
	}
	public void setTotalTrocaRegistro(Integer totalTrocaRegistro) {
		this.totalTrocaRegistro = totalTrocaRegistro;
	}
	public Integer getTotalReprovadas() {
		return totalReprovadas;
	}
	public void setTotalReprovadas(Integer totalReprovadas) {
		this.totalReprovadas = totalReprovadas;
	}
	public String getAnoMesReferenciaEncerramento() {
		return anoMesReferenciaEncerramento;
	}
	public void setAnoMesReferenciaEncerramento(String anoMesReferenciaEncerramento) {
		this.anoMesReferenciaEncerramento = anoMesReferenciaEncerramento;
	}
	public String getIdFirma() {
		return idFirma;
	}
	public void setIdFirma(String idFirma) {
		this.idFirma = idFirma;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getNomeFirma() {
		return nomeFirma;
	}
	public void setNomeFirma(String nomeFirma) {
		this.nomeFirma = nomeFirma;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public Integer getTotalNaoFiscalizadasAprovadas() {
		return totalNaoFiscalizadasAprovadas;
	}
	public void setTotalNaoFiscalizadasAprovadas(
			Integer totalNaoFiscalizadasAprovadas) {
		this.totalNaoFiscalizadasAprovadas = totalNaoFiscalizadasAprovadas;
	}
}