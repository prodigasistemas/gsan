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
package gcom.gui.cadastro.cliente;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author DDM
 */
public class ClienteTelefoneActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
    private String codigo;

    private String ddd;

    private String idTipoTelefone;

    private String indicadorTelefonePadrao;

    private String ramal;

    private String telefone;

    private String botaoClicado;

    private String botaoAdicionar;

    private String botaoRemover;

    private String idMunicipio;

    private String[] idRegistrosRemocao;

    private String textoSelecionado;

    /**
     * Retorna o valor de ddd
     * 
     * @return O valor de ddd
     */
    public String getDdd() {
        return ddd;
    }

    /**
     * Seta o valor de ddd
     * 
     * @param ddd
     *            O novo valor de ddd
     */
    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    /**
     * Retorna o valor de idTipoTelefone
     * 
     * @return O valor de idTipoTelefone
     */
    public String getIdTipoTelefone() {
        return idTipoTelefone;
    }

    /**
     * Seta o valor de idTipoTelefone
     * 
     * @param idTipoTelefone
     *            O novo valor de idTipoTelefone
     */
    public void setIdTipoTelefone(String idTipoTelefone) {
        this.idTipoTelefone = idTipoTelefone;
    }

    /**
     * Retorna o valor de indicadorTelefonePadrao
     * 
     * @return O valor de indicadorTelefonePadrao
     */
    public String getIndicadorTelefonePadrao() {
        return indicadorTelefonePadrao;
    }

    /**
     * Seta o valor de indicadorTelefonePadrao
     * 
     * @param indicadorTelefonePadrao
     *            O novo valor de indicadorTelefonePadrao
     */
    public void setIndicadorTelefonePadrao(String indicadorTelefonePadrao) {
        this.indicadorTelefonePadrao = indicadorTelefonePadrao;
    }

    /**
     * Retorna o valor de ramal
     * 
     * @return O valor de ramal
     */
    public String getRamal() {
        return ramal;
    }

    /**
     * Seta o valor de ramal
     * 
     * @param ramal
     *            O novo valor de ramal
     */
    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    /**
     * Retorna o valor de telefone
     * 
     * @return O valor de telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Seta o valor de telefone
     * 
     * @param telefone
     *            O novo valor de telefone
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionErrors validate(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {

        return null;
    }

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }

    /**
     * Retorna o valor de codigo
     * 
     * @return O valor de codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Seta o valor de codigo
     * 
     * @param codigo
     *            O novo valor de codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Retorna o valor de botaoAdicionar
     * 
     * @return O valor de botaoAdicionar
     */
    public String getBotaoAdicionar() {
        return botaoAdicionar;
    }

    /**
     * Seta o valor de botaoAdicionar
     * 
     * @param botaoAdicionar
     *            O novo valor de botaoAdicionar
     */
    public void setBotaoAdicionar(String botaoAdicionar) {
        this.botaoAdicionar = botaoAdicionar;
    }

    /**
     * Seta o valor de botaoClicado
     * 
     * @param botaoClicado
     *            O novo valor de botaoClicado
     */
    public void setBotaoClicado(String botaoClicado) {
        this.botaoClicado = botaoClicado;
    }

    /**
     * Retorna o valor de botaoClicado
     * 
     * @return O valor de botaoClicado
     */
    public String getBotaoClicado() {
        return botaoClicado;
    }

    /**
     * Retorna o valor de botaoRemover
     * 
     * @return O valor de botaoRemover
     */
    public String getBotaoRemover() {
        return botaoRemover;
    }

    /**
     * Seta o valor de botaoRemover
     * 
     * @param botaoRemover
     *            O novo valor de botaoRemover
     */
    public void setBotaoRemover(String botaoRemover) {
        this.botaoRemover = botaoRemover;
    }

    /**
     * Retorna o valor de idRegistrosRemocao
     * 
     * @return O valor de idRegistrosRemocao
     */
    public String[] getIdRegistrosRemocao() {
        return idRegistrosRemocao;
    }

    /**
     * Seta o valor de idRegistrosRemocao
     * 
     * @param idRegistrosRemocao
     *            O novo valor de idRegistrosRemocao
     */
    public void setIdRegistrosRemocao(String[] idRegistrosRemocao) {
        this.idRegistrosRemocao = idRegistrosRemocao;
    }

    /**
     * Retorna o valor de idMunicipio
     * 
     * @return O valor de idMunicipio
     */
    public String getIdMunicipio() {
        return idMunicipio;
    }

    /**
     * Seta o valor de idMunicipio
     * 
     * @param idMunicipio
     *            O novo valor de idMunicipio
     */
    public void setIdMunicipio(String idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getTextoSelecionado() {
        return textoSelecionado;
    }

    public void setTextoSelecionado(String textoSelecionado) {
        this.textoSelecionado = textoSelecionado;
    }
}
