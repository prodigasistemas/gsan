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
package gcom.util;

import java.util.ArrayList;

/**
 * Tem por objetivo conter os dados de uma pagína da funcionalidade de processo
 * 
 * @author Cesar
 */
public class Pagina {
    private String descricao;

    private String uriInicial;

    private String uriFinal;

    private String hint;

    private ArrayList atributosPagina;

    private int index;

    private String imagemAtiva;

    private String imagemInativa;

    private boolean paginaCorrente;

    private boolean paginaPreenchida;

    private boolean paginaInativa;

    private Integer tamanhoMaiorDescricao;

    /**
     * Construtor da classe Page
     */
    public Pagina() {
    }

    /**
     * Construtor da classe Page
     * 
     * @param descricao
     *            Descrição do parâmetro
     * @param uriInicial
     *            Descrição do parâmetro
     * @param uriFinal
     *            Descrição do parâmetro
     * @param imagemAtiva
     *            Descrição do parâmetro
     * @param imagemInativa
     *            Descrição do parâmetro
     */
    public Pagina(String descricao, String uriInicial, String uriFinal,
            String imagemAtiva, String imagemInativa) {
        this.descricao = descricao;
        this.uriInicial = uriInicial;
        this.uriFinal = uriFinal;
        this.imagemAtiva = imagemAtiva;
        this.imagemInativa = imagemInativa;
        atributosPagina = new ArrayList();
    }

    /**
     * Retorna o valor de caminhoImagemAtiva
     * 
     * @return O valor de caminhoImagemAtiva
     */
    public String getImagemAtiva() {
        return imagemAtiva;
    }

    /**
     * Retorna o valor de caminhoImagemInativa
     * 
     * @return O valor de caminhoImagemInativa
     */
    public String getImagemInativa() {
        return imagemInativa;
    }

    /**
     * Retorna o valor de atributos
     * 
     * @return O valor de atributos
     */
    public ArrayList getAtributosPagina() {
        return atributosPagina;
    }

    /**
     * Seta o valor de atributos
     * 
     * @return O valor de descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna o valor de hint
     * 
     * @return O valor de hint
     */
    public String getHint() {
        return hint;
    }

    /**
     * Seta o valor de hint
     * 
     * @param hint
     *            O novo valor de hint
     */
    public void setHint(String hint) {
        this.hint = hint;
    }

    /**
     * Retorna o valor de paginaCorrente
     * 
     * @return O valor de paginaCorrente
     */
    public boolean isPaginaCorrente() {
        return paginaCorrente;
    }

    /**
     * Seta o valor de paginaCorrente
     * 
     * @param paginaCorrente
     *            O novo valor de paginaCorrente
     */
    public void setPaginaCorrente(boolean paginaCorrente) {
        this.paginaCorrente = paginaCorrente;
    }

    /**
     * Retorna o valor de paginaPreenchida
     * 
     * @return O valor de paginaPreenchida
     */
    public boolean isPaginaPreenchida() {
        return paginaPreenchida;
    }

    /**
     * Seta o valor de paginaPreenchida
     * 
     * @param paginaPreenchida
     *            O novo valor de paginaPreenchida
     */
    public void setPaginaPreenchida(boolean paginaPreenchida) {
        this.paginaPreenchida = paginaPreenchida;
    }

    /**
     * < <Descrição do método>>
     * 
     * @param other
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof Pagina)) {
            return false;
        }
        Pagina castOther = (Pagina) other;

        return this.getUriInicial().equals(castOther.getUriInicial());
    }

    /**
     * Retorna o valor de index
     * 
     * @return O valor de index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Seta o valor de index
     * 
     * @param index
     *            O novo valor de index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Retorna o valor de paginaInativa
     * 
     * @return O valor de paginaInativa
     */
    public boolean isPaginaInativa() {
        return paginaInativa;
    }

    /**
     * Seta o valor de paginaInativa
     * 
     * @param paginaInativa
     *            O novo valor de paginaInativa
     */
    public void setPaginaInativa(boolean paginaInativa) {
        this.paginaInativa = paginaInativa;
    }

    /**
     * Retorna o valor de uriFinal
     * 
     * @return O valor de uriFinal
     */
    public String getUriFinal() {
        return uriFinal;
    }

    /**
     * Retorna o valor de uriInicial
     * 
     * @return O valor de uriInicial
     */
    public String getUriInicial() {
        return uriInicial;
    }

    /**
     * Retorna o valor de descricaoMaisLonga
     * 
     * @return O valor de descricaoMaisLonga
     */
    public Integer getTamanhoMaiorDescricao() {
        return this.tamanhoMaiorDescricao;
    }

    /**
     * Seta o valor de tamanhoDescricaoMaiorDescricao
     * 
     * @param tamanhoMaiorDescricao
     *            O novo valor de tamanhoDescricaoMaiorDescricao
     */
    public void setTamanhoMaiorDescricao(Integer tamanhoMaiorDescricao) {
        if (getTamanhoMaiorDescricao() == null
                || tamanhoMaiorDescricao.intValue() > getTamanhoMaiorDescricao()
                        .intValue()) {
            this.tamanhoMaiorDescricao = tamanhoMaiorDescricao;
        }

        //        this.tamanhoMaiorDescricao = tamanhoMaiorDescricao;
    }

    /**
     * Retorna o valor de tamanhoMaiorDescricaoEmPixels
     * 
     * @return O valor de tamanhoMaiorDescricaoEmPixels
     */
    public Integer getTamanhoMaiorDescricaoEmPixels() {
        if (this.getTamanhoMaiorDescricao() != null) {
            return new Integer(this.getTamanhoMaiorDescricao().intValue() * 15);
        } else {
            return new Integer(0);
        }
    }

    /**
     * Adds a feature to the Atributo attribute of the Pagina object
     * 
     * @param chave
     *            The feature to be added to the Atributo attribute
     * @param valor
     *            The feature to be added to the Atributo attribute
     */
    public void addAtributoPagina(String chave, String valor) {
        atributosPagina.add(new AtributoPagina(this, chave, valor));
    }

    /**
     * Remove todos os atributos da página
     */
    public void removerAtributosPagina() {
        this.atributosPagina.clear();
    }
}
