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
package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC005] Manter Municipio [SB0001]Atualizar Municipio
 * 
 * @author Kássia Albuquerque
 * @date 08/01/2007
 */

public class AtualizarMunicipioActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String codigoMunicipio;

	private String nomeMunicipio;

	private String codigoDdd;

	private String unidadeFederacao;

	private String microregiao;

	private String regiaoDesenv;

	private String cepInicial;

	private String cepFinal;

	private String dataInicioConcessao;

	private String dataFimConcessao;

	private String indicadorUso;

	private String codigoIbge;
	
	private String indicadorRelacaoQuadraBairro;

	public String getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(String codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getCepFinal() {
		return cepFinal;
	}

	public void setCepFinal(String cepFinal) {
		this.cepFinal = cepFinal;
	}

	public String getCepInicial() {
		return cepInicial;
	}

	public void setCepInicial(String cepInicial) {
		this.cepInicial = cepInicial;
	}

	public String getCodigoDdd() {
		return codigoDdd;
	}

	public void setCodigoDdd(String codigoDdd) {
		this.codigoDdd = codigoDdd;
	}

	public String getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(String codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public String getDataFimConcessao() {
		return dataFimConcessao;
	}

	public void setDataFimConcessao(String dataFimConcessao) {
		this.dataFimConcessao = dataFimConcessao;
	}

	public String getDataInicioConcessao() {
		return dataInicioConcessao;
	}

	public void setDataInicioConcessao(String dataInicioConcessao) {
		this.dataInicioConcessao = dataInicioConcessao;
	}

	public String getMicroregiao() {
		return microregiao;
	}

	public void setMicroregiao(String microregiao) {
		this.microregiao = microregiao;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getRegiaoDesenv() {
		return regiaoDesenv;
	}

	public void setRegiaoDesenv(String regiaoDesenv) {
		this.regiaoDesenv = regiaoDesenv;
	}

	public String getUnidadeFederacao() {
		return unidadeFederacao;
	}

	public void setUnidadeFederacao(String unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}

	public String getIndicadorRelacaoQuadraBairro() {
		return indicadorRelacaoQuadraBairro;
	}

	public void setIndicadorRelacaoQuadraBairro(String indicadorRelacaoQuadraBairro) {
		this.indicadorRelacaoQuadraBairro = indicadorRelacaoQuadraBairro;
	}
	
	// Esse método carrega todos os valores da base de dados
	// necesários para exibição da tela AtualizarMunicipio.

	public Municipio setFormValues(Municipio municipio) {

		municipio.setId(new Integer(getCodigoMunicipio()));
		municipio.setNome(getNomeMunicipio());
		municipio.setDdd(new Short(getCodigoDdd()));

		UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
		unidadeFederacao.setId(new Integer(getUnidadeFederacao()));
		municipio.setUnidadeFederacao(unidadeFederacao);

		Microrregiao microrregiao = new Microrregiao();
		microrregiao.setId(new Integer(getMicroregiao()));
		municipio.setMicrorregiao(microrregiao);

		RegiaoDesenvolvimento regiaoDesenvolvimento = new RegiaoDesenvolvimento();
		regiaoDesenvolvimento.setId(new Integer(getRegiaoDesenv()));
		municipio.setRegiaoDesenvolvimento(regiaoDesenvolvimento);

		municipio.setCepInicio(new Integer(getCepInicial()));
		municipio.setCepFim(new Integer(getCepFinal()));
		municipio.setDataConcessaoInicio(Util
				.converteStringParaDate(getDataInicioConcessao()));
		municipio.setDataConcessaoFim(Util
				.converteStringParaDate(getDataFimConcessao()));
		municipio.setIndicadorUso(new Short(getIndicadorUso()));
		municipio.setCodigoIbge(getCodigoIbge());
		// municipio.setUltimaAlteracao(new Date());
		
		if(getIndicadorRelacaoQuadraBairro() != null && getIndicadorRelacaoQuadraBairro().equals("1")){
			
			municipio.setIndicadorRelacaoQuadraBairro(ConstantesSistema.INDICADOR_USO_ATIVO);
		}else{
			municipio.setIndicadorRelacaoQuadraBairro(ConstantesSistema.INDICADOR_USO_DESATIVO);
		}

		return municipio;
	}

}
