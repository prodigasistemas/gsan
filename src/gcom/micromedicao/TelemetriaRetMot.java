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
package gcom.micromedicao;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class TelemetriaRetMot implements Serializable {

	private static final long serialVersionUID = 1L;
    
	//Constantes telemetria ret mot >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
    public final static Integer ID_QUANTIDADE_INVALIDA = new Integer(1);
    public final static Integer ID_DATA_ENVIO_INVALIDA = new Integer(2);
    public final static Integer ID_CODIGO_EMPRESA_INVALIDO = new Integer(3);
    public final static Integer ID_INSCRICAO_INVALIDA = new Integer(4);
    public final static Integer ID_LEITURA_INVALIDA = new Integer(5);
    public final static Integer ID_HIDROMETRO_INVALIDO = new Integer(6);
    public final static Integer ID_MEDICAO_TIPO_INVALIDA = new Integer(7);
    public final static Integer ID_DATA_LEITURA_INVALIDA = new Integer(8);
    public final static Integer ID_MATRICULA_INVALIDA = new Integer(9);
    public final static Integer ID_MATRICULA_INEXISTENTE = new Integer(10);
    public final static Integer ID_DATA_LEITURA_INERVALO_INVALIDO = new Integer(11);
    public final static Integer ID_FATURAMENTO_GRUPO_INVALIDO = new Integer(12);
    public final static Integer ID_QUANTIDADE_DIFERENTE = new Integer(13);
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
    
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoRetorno;

    /** nullable persistent field */
    private Short indicadorUso;
    
    /** nullable persistent field */
    private Short indicadorMovAceito;

    /** persistent field */
    private Date ultimaAlteracao;

	
    public TelemetriaRetMot() {}
    
    public TelemetriaRetMot(Integer id, String descricaoRetorno, Short indicadorUso, Short indicadorMovAceito, Date ultimaAlteracao) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.descricaoRetorno = descricaoRetorno;
		this.indicadorUso = indicadorUso;
		this.indicadorMovAceito = indicadorMovAceito;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getDescricaoRetorno() {
		return descricaoRetorno;
	}

	public void setDescricaoRetorno(String descricaoRetorno) {
		this.descricaoRetorno = descricaoRetorno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorMovAceito() {
		return indicadorMovAceito;
	}

	public void setIndicadorMovAceito(Short indicadorMovAceito) {
		this.indicadorMovAceito = indicadorMovAceito;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
