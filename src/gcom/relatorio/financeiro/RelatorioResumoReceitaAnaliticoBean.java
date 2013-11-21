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
package gcom.relatorio.financeiro;

import gcom.financeiro.ResumoReceita;
import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

/**
 * classe responsável por criar o relatório de imoveis por situação da ligação de agua
 * 
 * @author Rafael Pinto
 * @created 03/12/2007
 */
public class RelatorioResumoReceitaAnaliticoBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String anoMesReferencia;
    
    private String dataRealizada;

    /** nullable persistent field */
    private String valorReceita;
    
    private String ultimaAlteracao;
    
    private String bancoId;
    
    private String arrecadador;
    
    private String contaContabil;
    
    private String gerenciaRegional;
    
    private String localidade;
    
    private String categoria;
    
    private String contaBancaria;
    
    private String unidadeNegocio;
    
    private String bancoNome;
    
    public RelatorioResumoReceitaAnaliticoBean(ResumoReceita resumo){
    	this.anoMesReferencia = resumo.getAnoMesReferencia()+"";
    	this.arrecadador = resumo.getArrecadador().getNumeroInscricaoEstadual();
    	this.bancoId = resumo.getBanco().getId() + "";
    	this.bancoNome = resumo.getBanco().getDescricao();
    	//this.categoria = resumo.getCategoria().getId() + "";
    	this.contaContabil = resumo.getContaContabil().getNumeroConta();
    	this.contaBancaria = resumo.getContaBancaria().getNumeroConta();
    	this.dataRealizada = Util.formatarData(resumo.getDataRealizada());
    	//this.gerenciaRegional = resumo.getGerenciaRegional().getId() + "";
    	//this.localidade = resumo.getLocalidade().getId() + "";
    	//this.ultimaAlteracao = Util.formatarData(resumo.getUltimaAlteracao());
    	//this.unidadeNegocio = resumo.getUnidadeNegocio().getId() + "";
    	this.valorReceita = resumo.getValorReceita()+"";
    }

	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public String getArrecadador() {
		return arrecadador;
	}

	public void setArrecadador(String arrecadador) {
		this.arrecadador = arrecadador;
	}

	public String getBancoId() {
		return bancoId;
	}

	public void setBancoId(String bancoId) {
		this.bancoId = bancoId;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(String contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public String getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(String contaContabil) {
		this.contaContabil = contaContabil;
	}

	public String getDataRealizada() {
		return dataRealizada;
	}

	public void setDataRealizada(String dataRealizada) {
		this.dataRealizada = dataRealizada;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getValorReceita() {
		return valorReceita;
	}

	public void setValorReceita(String valorReceita) {
		this.valorReceita = valorReceita;
	}

	public String getBancoNome() {
		return bancoNome;
	}

	public void setBancoNome(String bancoNome) {
		this.bancoNome = bancoNome;
	}
        

	
}
