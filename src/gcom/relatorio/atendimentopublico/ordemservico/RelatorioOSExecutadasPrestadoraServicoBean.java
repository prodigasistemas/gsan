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
package gcom.relatorio.atendimentopublico.ordemservico;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import gcom.relatorio.RelatorioBean;

public class RelatorioOSExecutadasPrestadoraServicoBean implements RelatorioBean {

	private String numeroOS;
	private String codigoServico;
	private String descServico;
	private String descTipoPavimento;
	private String materialrede;
	private String diametroRede;
	private String dataConclusao;
	private String codigoExcedente;
//	private String codigoMaterial;
	private String descMaterial;
	private String qtdeExcedente;
	private String profundRede;
	private String dimenBuraco;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String enderecoRA;
	
	private String descricaoGerencia;
	private String idGerencia;
	private String descricaoUnidadeNegocio;
	private String idUnidadeNegocio;
	
	private JRBeanCollectionDataSource arrayJRDetail;
	private ArrayList arrayRelatorioOSExecutadasPrestadoraServicoDetailBean;
	
	
	public RelatorioOSExecutadasPrestadoraServicoBean(String numeroOS, String codigoServico, String descServico, String descTipoPavimento, String materialrede, String diametroRede, String dataConclusao, String codigoExcedente, String descMaterial, String qtdeExcedente, String profundRede, String dimenBuraco, String idLocalidade, String descricaoLocalidade, String enderecoRA, String descricaoGerencia, String idGerencia, String descricaoUnidadeNegocio, String idUnidadeNegocio, Collection colecaoSubrelatorio) {
		super();
		// TODO Auto-generated constructor stub
		this.numeroOS = numeroOS;
		this.codigoServico = codigoServico;
		this.descServico = descServico;
		this.descTipoPavimento = descTipoPavimento;
		this.materialrede = materialrede;
		this.diametroRede = diametroRede;
		this.dataConclusao = dataConclusao;
		this.codigoExcedente = codigoExcedente;
//		this.codigoMaterial = codigoMaterial;
		this.descMaterial = descMaterial;
		this.qtdeExcedente = qtdeExcedente;
		this.profundRede = profundRede;
		this.dimenBuraco = dimenBuraco;
		this.idLocalidade = idLocalidade;
		this.descricaoLocalidade = descricaoLocalidade;
		this.enderecoRA = enderecoRA;
		this.descricaoGerencia = descricaoGerencia;
		this.idGerencia = idGerencia;
		this.descricaoUnidadeNegocio = descricaoUnidadeNegocio;
		this.idUnidadeNegocio = idUnidadeNegocio;
		
		this.arrayRelatorioOSExecutadasPrestadoraServicoDetailBean = new ArrayList();
		this.arrayRelatorioOSExecutadasPrestadoraServicoDetailBean.addAll(colecaoSubrelatorio);
		this.arrayJRDetail = new JRBeanCollectionDataSource(
				this.arrayRelatorioOSExecutadasPrestadoraServicoDetailBean);
		
	}
	public RelatorioOSExecutadasPrestadoraServicoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCodigoExcedente() {
		return codigoExcedente;
	}
	public void setCodigoExcedente(String codigoExcedente) {
		this.codigoExcedente = codigoExcedente;
	}
	public String getCodigoServico() {
		return codigoServico;
	}
	public void setCodigoServico(String codigoServico) {
		this.codigoServico = codigoServico;
	}
	public String getDataConclusao() {
		return dataConclusao;
	}
	public void setDataConclusao(String dataConclusao) {
		this.dataConclusao = dataConclusao;
	}
	public String getDescMaterial() {
		return descMaterial;
	}
	public void setDescMaterial(String descMaterial) {
		this.descMaterial = descMaterial;
	}
	public String getDescricaoGerencia() {
		return descricaoGerencia;
	}
	public void setDescricaoGerencia(String descricaoGerencia) {
		this.descricaoGerencia = descricaoGerencia;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String getDescricaoUnidadeNegocio() {
		return descricaoUnidadeNegocio;
	}
	public void setDescricaoUnidadeNegocio(String descricaoUnidadeNegocio) {
		this.descricaoUnidadeNegocio = descricaoUnidadeNegocio;
	}
	public String getDescServico() {
		return descServico;
	}
	public void setDescServico(String descServico) {
		this.descServico = descServico;
	}
	public String getDescTipoPavimento() {
		return descTipoPavimento;
	}
	public void setDescTipoPavimento(String descTipoPavimento) {
		this.descTipoPavimento = descTipoPavimento;
	}
	public String getDiametroRede() {
		return diametroRede;
	}
	public void setDiametroRede(String diametroRede) {
		this.diametroRede = diametroRede;
	}
	public String getDimenBuraco() {
		return dimenBuraco;
	}
	public void setDimenBuraco(String dimenBuraco) {
		this.dimenBuraco = dimenBuraco;
	}
	public String getEnderecoRA() {
		return enderecoRA;
	}
	public void setEnderecoRA(String enderecoRA) {
		this.enderecoRA = enderecoRA;
	}
	public String getIdGerencia() {
		return idGerencia;
	}
	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public String getMaterialrede() {
		return materialrede;
	}
	public void setMaterialrede(String materialrede) {
		this.materialrede = materialrede;
	}
	public String getNumeroOS() {
		return numeroOS;
	}
	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}
	public String getProfundRede() {
		return profundRede;
	}
	public void setProfundRede(String profundRede) {
		this.profundRede = profundRede;
	}
	public String getQtdeExcedente() {
		return qtdeExcedente;
	}
	public void setQtdeExcedente(String qtdeExcedente) {
		this.qtdeExcedente = qtdeExcedente;
	}
	public JRBeanCollectionDataSource getArrayJRDetail() {
		return arrayJRDetail;
	}
	public void setArrayJRDetail(JRBeanCollectionDataSource arrayJRDetail) {
		this.arrayJRDetail = arrayJRDetail;
	}
	public ArrayList getArrayRelatorioOSExecutadasPrestadoraServicoDetailBean() {
		return arrayRelatorioOSExecutadasPrestadoraServicoDetailBean;
	}
	public void setArrayRelatorioOSExecutadasPrestadoraServicoDetailBean(
			ArrayList arrayRelatorioOSExecutadasPrestadoraServicoDetailBean) {
		this.arrayRelatorioOSExecutadasPrestadoraServicoDetailBean = arrayRelatorioOSExecutadasPrestadoraServicoDetailBean;
	}
	
	
	
	

}
