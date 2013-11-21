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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

public class ReplicarValorCobrancaServicoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String tipoServico;
	private String nomeTipoServico;
	private String subCategoria;
	private String nomeSubCategoria;
	private String perfilImovel;
	private String indicadorMedido;
	private String capacidadeHidrometro;
	private String valorServico;
	private String dataVigenciaInicial;
	private String dataVigenciaFinal;
	private String indicadorTipoServicoEconomia;
	private String indicadorFaixaEconomiaInicial;
	private String indicadorFaixaEconomiaFinal;
	private String novaDataVigenciaInicial;
	private String novaDataVigenciaFinal;
	private String indiceParaCorrecao;
	private String[] idRegistrosSelecionados;
	private boolean mensagem;
	
	private Collection<ServicoCobrancaValor> collServicoCobrancaValor;
	
	public ReplicarValorCobrancaServicoActionForm(){
		
		this.collServicoCobrancaValor = new ArrayList<ServicoCobrancaValor>();
		
	}
	
	public String getValorServico() {
		return valorServico;
	}
	public void setValorServico(String valorServico) {
		this.valorServico = valorServico;
	}
	public String getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}
	public void setCapacidadeHidrometro(String capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}
	public String getIndicadorMedido() {
		return indicadorMedido;
	}
	public void setIndicadorMedido(String indicadorMedido) {
		this.indicadorMedido = indicadorMedido;
	}
	public String getPerfilImovel() {
		return perfilImovel;
	}
	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
	public String getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}
	public String getNomeTipoServico() {
		return nomeTipoServico;
	}
	public void setNomeTipoServico(String nomeTipoServico) {
		this.nomeTipoServico = nomeTipoServico;
	}
	public String getNomeSubCategoria() {
		return nomeSubCategoria;
	}
	public void setNomeSubCategoria(String nomeSubCategoria) {
		this.nomeSubCategoria = nomeSubCategoria;
	}
	public String getSubCategoria() {
		return subCategoria;
	}
	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}
	public String getDataVigenciaFinal() {
		return dataVigenciaFinal;
	}
	public void setDataVigenciaFinal(String dataVigenciaFinal) {
		this.dataVigenciaFinal = dataVigenciaFinal;
	}
	public String getDataVigenciaInicial() {
		return dataVigenciaInicial;
	}
	public void setDataVigenciaInicial(String dataVigenciaInicial) {
		this.dataVigenciaInicial = dataVigenciaInicial;
	}
	public String getIndicadorFaixaEconomiaFinal() {
		return indicadorFaixaEconomiaFinal;
	}
	public void setIndicadorFaixaEconomiaFinal(String indicadorFaixaEconomiaFinal) {
		this.indicadorFaixaEconomiaFinal = indicadorFaixaEconomiaFinal;
	}
	public String getIndicadorFaixaEconomiaInicial() {
		return indicadorFaixaEconomiaInicial;
	}
	public void setIndicadorFaixaEconomiaInicial(
			String indicadorFaixaEconomiaInicial) {
		this.indicadorFaixaEconomiaInicial = indicadorFaixaEconomiaInicial;
	}
	public String getIndicadorTipoServicoEconomia() {
		return indicadorTipoServicoEconomia;
	}
	public void setIndicadorTipoServicoEconomia(String indicadorTipoServicoEconomia) {
		this.indicadorTipoServicoEconomia = indicadorTipoServicoEconomia;
	}

	public Collection<ServicoCobrancaValor> getCollServicoCobrancaValor() {
		return collServicoCobrancaValor;
	}

	public void setCollServicoCobrancaValor(
			Collection<ServicoCobrancaValor> collServicoCobrancaValor) {
		this.collServicoCobrancaValor = collServicoCobrancaValor;
	}

	public boolean getMensagem() {
		return mensagem;
	}

	public void setMensagem(boolean mensagem) {
		this.mensagem = mensagem;
	}

	public String getIndiceParaCorrecao() {
		return indiceParaCorrecao;
	}

	public void setIndiceParaCorrecao(String indiceParaCorrecao) {
		this.indiceParaCorrecao = indiceParaCorrecao;
	}

	public String getNovaDataVigenciaFinal() {
		return novaDataVigenciaFinal;
	}

	public void setNovaDataVigenciaFinal(String novaDataVigenciaFinal) {
		this.novaDataVigenciaFinal = novaDataVigenciaFinal;
	}

	public String getNovaDataVigenciaInicial() {
		return novaDataVigenciaInicial;
	}

	public void setNovaDataVigenciaInicial(String novaDataVigenciaInicial) {
		this.novaDataVigenciaInicial = novaDataVigenciaInicial;
	}
	
	public String[] getIdRegistrosSelecionados() {
		return idRegistrosSelecionados;
	}

	public void setIdRegistrosSelecionados(String[] idRegistrosSelecionados) {
		this.idRegistrosSelecionados = idRegistrosSelecionados;
	}
	
}