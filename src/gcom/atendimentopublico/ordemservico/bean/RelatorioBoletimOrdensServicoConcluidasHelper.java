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
package gcom.atendimentopublico.ordemservico.bean;



/**
 * [UC0766] Gerar Relatorio Boletim de Ordens de Servico Concluidas
 * 
 * @author Ivan Sérgio
 * @date 07/05/2008
 *  
 */
public class RelatorioBoletimOrdensServicoConcluidasHelper {
	public Integer idOrdemServico;
	public Integer anoMesReferenciaBoletim;
	public Short codigoFiscalizaco;
	public Short indicadorTorcaProtecaoHidrometro;
	public Short indicadorTorcaRegistroHidrometro;
	public Integer idTipoServico;
	public Integer idLocalidade;
	public String descricaoLocalidade;
	public Integer idLocalInstalacaoHidrometro;
	public String descricaoLocalInstalacaoHidrometro;
	public String descricaoAbreviadaFirma;
	public Integer codigoSetorComercial;
	public Integer numeroQuadra;
	public Short lote;
	public Short subLote;
	public String dataGeracaoOrdemServico;
	public String dataEncerramentoOrdemServico;
	public String dataFiscalizacao1;
	public String dataFiscalizacao2;
	public String dataFiscalizacao3;
	public String dataEncerramentoBoletim;
	public Integer idImovel;
	public Integer idSetorComercial;
	
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public Integer getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	public Integer getAnoMesReferenciaBoletim() {
		return anoMesReferenciaBoletim;
	}
	public void setAnoMesReferenciaBoletim(Integer anoMesReferenciaBoletim) {
		this.anoMesReferenciaBoletim = anoMesReferenciaBoletim;
	}
	public Short getCodigoFiscalizaco() {
		return codigoFiscalizaco;
	}
	public void setCodigoFiscalizaco(Short codigoFiscalizaco) {
		this.codigoFiscalizaco = codigoFiscalizaco;
	}
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public String getDataEncerramentoBoletim() {
		return dataEncerramentoBoletim;
	}
	public void setDataEncerramentoBoletim(String dataEncerramentoBoletim) {
		this.dataEncerramentoBoletim = dataEncerramentoBoletim;
	}
	public String getDataEncerramentoOrdemServico() {
		return dataEncerramentoOrdemServico;
	}
	public void setDataEncerramentoOrdemServico(String dataEncerramentoOrdemServico) {
		this.dataEncerramentoOrdemServico = dataEncerramentoOrdemServico;
	}
	public String getDataFiscalizacao1() {
		return dataFiscalizacao1;
	}
	public void setDataFiscalizacao1(String dataFiscalizacao1) {
		this.dataFiscalizacao1 = dataFiscalizacao1;
	}
	public String getDataFiscalizacao2() {
		return dataFiscalizacao2;
	}
	public void setDataFiscalizacao2(String dataFiscalizacao2) {
		this.dataFiscalizacao2 = dataFiscalizacao2;
	}
	public String getDataFiscalizacao3() {
		return dataFiscalizacao3;
	}
	public void setDataFiscalizacao3(String dataFiscalizacao3) {
		this.dataFiscalizacao3 = dataFiscalizacao3;
	}
	public String getDataGeracaoOrdemServico() {
		return dataGeracaoOrdemServico;
	}
	public void setDataGeracaoOrdemServico(String dataGeracaoOrdemServico) {
		this.dataGeracaoOrdemServico = dataGeracaoOrdemServico;
	}
	public String getDescricaoAbreviadaFirma() {
		return descricaoAbreviadaFirma;
	}
	public void setDescricaoAbreviadaFirma(String descricaoAbreviadaFirma) {
		this.descricaoAbreviadaFirma = descricaoAbreviadaFirma;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String getDescricaoLocalInstalacaoHidrometro() {
		return descricaoLocalInstalacaoHidrometro;
	}
	public void setDescricaoLocalInstalacaoHidrometro(
			String descricaoLocalInstalacaoHidrometro) {
		this.descricaoLocalInstalacaoHidrometro = descricaoLocalInstalacaoHidrometro;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public Integer getIdLocalInstalacaoHidrometro() {
		return idLocalInstalacaoHidrometro;
	}
	public void setIdLocalInstalacaoHidrometro(Integer idLocalInstalacaoHidrometro) {
		this.idLocalInstalacaoHidrometro = idLocalInstalacaoHidrometro;
	}
	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public Integer getIdTipoServico() {
		return idTipoServico;
	}
	public void setIdTipoServico(Integer idTipoServico) {
		this.idTipoServico = idTipoServico;
	}
	public Short getIndicadorTorcaProtecaoHidrometro() {
		return indicadorTorcaProtecaoHidrometro;
	}
	public void setIndicadorTorcaProtecaoHidrometro(
			Short indicadorTorcaProtecaoHidrometro) {
		this.indicadorTorcaProtecaoHidrometro = indicadorTorcaProtecaoHidrometro;
	}
	public Short getIndicadorTorcaRegistroHidrometro() {
		return indicadorTorcaRegistroHidrometro;
	}
	public void setIndicadorTorcaRegistroHidrometro(
			Short indicadorTorcaRegistroHidrometro) {
		this.indicadorTorcaRegistroHidrometro = indicadorTorcaRegistroHidrometro;
	}
	public Short getLote() {
		return lote;
	}
	public void setLote(Short lote) {
		this.lote = lote;
	}
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}
	public Short getSubLote() {
		return subLote;
	}
	public void setSubLote(Short subLote) {
		this.subLote = subLote;
	}
}