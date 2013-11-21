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
package gcom.gui.seguranca.acesso;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import gcom.seguranca.acesso.usuario.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * Classe que constrói o menu para o sistema usando código javascript
 *
 * @author   rodrigo
 */
public class MenuGCOM {
    /**
     * Construtor da classe MenuSGCQ
     */
	private SistemaParametro sistemaParametro;
    
	public MenuGCOM() {
		
		Collection colecaoSistemaParametro = 
			Fachada.getInstancia().pesquisar(new FiltroSistemaParametro(),SistemaParametro.class.getName());
		
		sistemaParametro = (SistemaParametro) Util.retonarObjetoDeColecao(colecaoSistemaParametro);
		sistemaParametro.setarConstantes();
    }

    private int contadorId = 0;
    //StringBuffer que armazena todo o código do menu
    private StringBuffer menu = new StringBuffer();

    /**
     * Gera o menu
     *
     * @param arvoreFuncionalidades  Descrição do parâmetro
     * @return                       Retorna a string que representa todo o
     *      código javascript do menu
     */
    public String gerarMenu(FuncionalidadeCategoria arvoreFuncionalidades, Usuario usuario) {

        menu.append("<link rel=\"StyleSheet\" href=\"/gsan/css/dtree.css\" type=\"text/css\" /><script type=\"text/javascript\" src=\"/gsan/javascript/dtree.js\"></script>\n");

        menu.append("<div class=\"dtree\">\n");

        menu.append("<script><!--\n d = new dTree('d');\n");

        menu.append("d.add(0,-1,'Menu GSAN');\n");

         //Ordena os nós da árvore
        ordenarArvoreOrdemAlfabeticaPorProfundidade(arvoreFuncionalidades);

        //O primeiro elemento é o topo da árvore
//        percorrerArvorePorProfundidade(arvoreFuncionalidades, 0);
        percorrerArvorePorProfundidade(arvoreFuncionalidades, 0, usuario);

        //Fim de código dinâmico

        menu.append("d.draw();\n//--></script>\n");

        menu.append("</div>");

        return menu.toString();
    }
    
    /**
     * Gera o menu
     *
     * @param arvoreFuncionalidades  Descrição do parâmetro
     * @return                       Retorna a string que representa todo o
     *      código javascript do menu
     */
    public String gerarMenuOrganizarMenu(FuncionalidadeCategoria arvoreFuncionalidades) {

        menu.append("<link rel=\"StyleSheet\" href=\"/gsan/css/dtree.css\" type=\"text/css\" /><script type=\"text/javascript\" src=\"/gsan/javascript/dtree2.js\"></script>\n");

        menu.append("<div class=\"dtree\">\n");

        menu.append("<script><!--\n t = new dTree('t');\n");

        menu.append("t.add(0,-1,'Menu GSAN');\n");

         //Ordena os nós da árvore
        ordenarArvoreOrdemAlfabeticaPorProfundidade(arvoreFuncionalidades);

        //O primeiro elemento é o topo da árvore
        percorrerArvorePorProfundidadeOrganizarMenu(arvoreFuncionalidades, 0);

        //Fim de código dinâmico

        menu.append("t.draw();\n//--></script>\n");

        menu.append("</div>");

        return menu.toString();
    }

    /**
     * Insere funcionalidades no menu através de uma busca de profundidade na
     * árvore
     *
     * @param arvore       Árvore de funcionalidades
     * @param nivelArvore  Nível de profundidade da árvore
     */
    private void percorrerArvorePorProfundidade(FuncionalidadeCategoria arvore, int nivelArvore, Usuario usuario) {

        Iterator iterator = arvore.getElementos().iterator();

        while (iterator.hasNext()) {
            contadorId++;

            Object itemArvore = iterator.next();

            //Indica as folhas da arvore
            if (itemArvore instanceof Funcionalidade) {

                Funcionalidade funcionalidade = (Funcionalidade) itemArvore;

                //Só adiciona um item no menu se a funcionalidade for um ponto de entrada
                if (funcionalidade.getIndicadorPontoEntrada().equals(ConstantesSistema.SIM)) {
                	
                	/*
                	 * Caso a funcionalidade tenha o indicador de abrir em outra janela 
                	 * cria o item do menu com os parâmetros 
                	 */
                	
                	if(funcionalidade.getIndicadorNovaJanela().equals(ConstantesSistema.SIM)){
	                    //Insere um item na árvore
                		
                		//Alteracao Feita por Tiago Moreno
                		//Permite que a funcionalidade do Olap monte o servidor de acordo com o servidor da empresa
                		//Data: 28/04/2008
                       if (funcionalidade.getIndicadorOlap() != null && !funcionalidade.getIndicadorOlap().equals(ConstantesSistema.NAO)){
                        	
                        	menu.append("d.add(" + contadorId + "," + nivelArvore + ",'" +
	                        	funcionalidade.getDescricao() + "','" +
	                        	"http://" + sistemaParametro.getIpServidorModuloGerencial()+ "/" + funcionalidade.getCaminhoUrl() + ((funcionalidade.getCaminhoUrl().indexOf("?") != -1) ? "" : "?menu=sim")+  "','','_newblank');\n");
                        } else { 
                        	menu.append("d.add(" + contadorId + "," + nivelArvore + ",'" +
	                        	funcionalidade.getDescricao() + "','" +
	                    		funcionalidade.getCaminhoUrl() + ((funcionalidade.getCaminhoUrl().indexOf("?") != -1) ? "" : "?menu=sim")+  "','','_newblank');\n");
                        }
                	}else{
                		if (funcionalidade.getIndicadorOlap() != null && !funcionalidade.getIndicadorOlap().equals(ConstantesSistema.NAO)){
		                    menu.append("d.add(" + contadorId + "," + nivelArvore + ",'" +
		                    	funcionalidade.getDescricao() + "','" +
		                        "http://" + sistemaParametro.getIpServidorModuloGerencial()+ "/" + funcionalidade.getCaminhoUrl() + ((funcionalidade.getCaminhoUrl().indexOf("?") != -1) ? "" : "?menu=sim")+  "');\n");
                		} else if (funcionalidade.getId().equals(new Integer(Funcionalidade.TESTE_OPERACIONAL))) {
                            menu.append("d.add(" + contadorId + "," + nivelArvore + ",'" +
                                    funcionalidade.getDescricao() + "','" +
                                    "http://" + sistemaParametro.getIpServidorModuloOperacional()+ "/" + funcionalidade.getCaminhoUrl() + usuario.getId()
//                                    +
//                                    ((funcionalidade.getCaminhoUrl().indexOf("?") != -1) ? "" : "?menu=sim&id=" + usuario.getId())
                                    +  "');\n");
                        } else {
                			menu.append("d.add(" + contadorId + "," + nivelArvore + ",'" +
    	                        funcionalidade.getDescricao() + "','" +
    	                        funcionalidade.getCaminhoUrl() + ((funcionalidade.getCaminhoUrl().indexOf("?") != -1) ? "" : "?menu=sim")+  "');\n");
                		}
                	}
                } else {
                    //Volta o contador
                    contadorId--;
                }

                //Indica os nós da arvore
            } else if (itemArvore instanceof FuncionalidadeCategoria) {
                FuncionalidadeCategoria funcionalidadeCategoria = (FuncionalidadeCategoria) itemArvore;
                
                if(funcionalidadeCategoria.getElementos() != null && !funcionalidadeCategoria.getElementos().isEmpty()){
                    //Insere um item na árvore
                    menu.append("d.add(" + contadorId + "," + nivelArvore + ",'" + funcionalidadeCategoria.getNome() + "','#');\n");
                    percorrerArvorePorProfundidade(((FuncionalidadeCategoria) itemArvore), contadorId, usuario);
                	
                }
            }
        }

    }
    
    /**
     * Insere funcionalidades no menu através de uma busca de profundidade na
     * árvore
     *
     * @param arvore       Árvore de funcionalidades
     * @param nivelArvore  Nível de profundidade da árvore
     */
    private void percorrerArvorePorProfundidadeOrganizarMenu(FuncionalidadeCategoria arvore, int nivelArvore) {

        Iterator iterator = arvore.getElementos().iterator();

        while (iterator.hasNext()) {
            contadorId++;

            Object itemArvore = iterator.next();

            //Indica as folhas da arvore
            if (itemArvore instanceof Funcionalidade) {

                Funcionalidade funcionalidade = (Funcionalidade) itemArvore;

                //Só adiciona um item no menu se a funcionalidade for um ponto de entrada
                if (funcionalidade.getIndicadorPontoEntrada().equals(ConstantesSistema.SIM)) {
                	
        			menu.append("t.add(" 
        				+ contadorId + "," 
        				+ nivelArvore + ",'" 
        				+ funcionalidade.getDescricao() +"-"+ funcionalidade.getNumeroOrdemMenu()+ "','" 
        				+ "exibirOrganizarMenuArvoreAction.do?idFuncionalidade="+funcionalidade.getId() + "');\n");
            		
                } else {
                    //Volta o contador
                    contadorId--;
                }

                //Indica os nós da arvore
            } else if (itemArvore instanceof FuncionalidadeCategoria) {
            	
                FuncionalidadeCategoria funcionalidadeCategoria = (FuncionalidadeCategoria) itemArvore;

                if(funcionalidadeCategoria.getElementos() != null && 
                	!funcionalidadeCategoria.getElementos().isEmpty()){
	                
                	String url = "";
                	if(funcionalidadeCategoria.getFuncionalidadeCategoriaSuperior() != null && 
                			!funcionalidadeCategoria.getFuncionalidadeCategoriaSuperior().getId().equals(
                		FuncionalidadeCategoria.FUNCIONALIDADE_SUPERIOR_GSAN )){
                    	
                		url = 
                    		"exibirOrganizarMenuArvoreAction.do?idFuncionalidadeCategoria="+funcionalidadeCategoria.getId();
                	}
                	
                	//Insere um item na árvore
	                menu.append("t.add(" 
	                	+ contadorId + "," 
	                	+ nivelArvore + ",'" 
	                	+ funcionalidadeCategoria.getNome() +"-"+ funcionalidadeCategoria.getNumeroOrdemMenu()+ "','" 
	                	+ url + "');\n");
	                
	                percorrerArvorePorProfundidadeOrganizarMenu(((FuncionalidadeCategoria) itemArvore), contadorId);
                }
            }
        }

    }
        
    
    /**
     * Ordena por ordem alfabética todos os nós da árvore
     *
     * @param arvore  Árvore que será ordenada
     */
    private void ordenarArvoreOrdemAlfabeticaPorProfundidade(FuncionalidadeCategoria arvore) {

        Iterator iterator = arvore.getElementos().iterator();


        while (iterator.hasNext()) {

            Object itemArvore = iterator.next();

            //Indica os nós da arvore
            if (itemArvore instanceof FuncionalidadeCategoria) {
                FuncionalidadeCategoria funcionalidadeCategoria = (FuncionalidadeCategoria) itemArvore;
       
                List elementos = new ArrayList(funcionalidadeCategoria.getElementos()); 
                
                Collections.sort((elementos),
                        new Comparator() {
                            public int compare(Object a, Object b) {
                                if (a instanceof FuncionalidadeCategoria && b instanceof FuncionalidadeCategoria) {
                                    return ((FuncionalidadeCategoria) a).getNumeroOrdemMenu().compareTo(((FuncionalidadeCategoria) b).getNumeroOrdemMenu());
                                    
                                } else if (a instanceof Funcionalidade && b instanceof Funcionalidade) {
                                    return ((Funcionalidade) a).getNumeroOrdemMenu().compareTo(((Funcionalidade) b).getNumeroOrdemMenu());
	                                
                                } else if (a instanceof Funcionalidade && b instanceof FuncionalidadeCategoria){
                                    return ((Funcionalidade) a).getNumeroOrdemMenu().compareTo(((FuncionalidadeCategoria) b).getNumeroOrdemMenu());
                                
                                } else if (a instanceof FuncionalidadeCategoria && b instanceof Funcionalidade){
                                    return ((FuncionalidadeCategoria) a).getNumeroOrdemMenu().compareTo(((Funcionalidade) b).getNumeroOrdemMenu());
                                    
                                } else {
	                                return 0;
	                            } 
                            }
                					
                        });
                
                funcionalidadeCategoria.setElementos(new CopyOnWriteArraySet(elementos));
                

                //Chama recursivamente o próximo nó
                ordenarArvoreOrdemAlfabeticaPorProfundidade(funcionalidadeCategoria);
            }
        }

    }



}
