package gcom.gui.seguranca.acesso;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;


public class MenuGCOM {
	private SistemaParametro sistemaParametro;
    
	public MenuGCOM() {
		
		Collection colecaoSistemaParametro = 
			Fachada.getInstancia().pesquisar(new FiltroSistemaParametro(),SistemaParametro.class.getName());
		
		sistemaParametro = (SistemaParametro) Util.retonarObjetoDeColecao(colecaoSistemaParametro);
		sistemaParametro.setarConstantes();
    }

    private int contadorId = 0;

    private StringBuffer menu = new StringBuffer();

    public String gerarMenu(FuncionalidadeCategoria arvoreFuncionalidades, Usuario usuario) {

        menu.append("<link rel=\"StyleSheet\" href=\"/gsan/css/dtree.css\" type=\"text/css\" /><script type=\"text/javascript\" src=\"/gsan/javascript/dtree.js\"></script>\n");

        menu.append("<div class=\"dtree\">\n");

        menu.append("<script><!--\n d = new dTree('d');\n");

        menu.append("d.add(0,-1,'Menu GSAN');\n");

        ordenarArvoreOrdemAlfabeticaPorProfundidade(arvoreFuncionalidades);

        percorrerArvorePorProfundidade(arvoreFuncionalidades, 0, usuario);

        menu.append("d.draw();\n//--></script>\n");

        menu.append("</div>");

        return menu.toString();
    }
    
    public String gerarMenuOrganizarMenu(FuncionalidadeCategoria arvoreFuncionalidades) {

        menu.append("<link rel=\"StyleSheet\" href=\"/gsan/css/dtree.css\" type=\"text/css\" /><script type=\"text/javascript\" src=\"/gsan/javascript/dtree2.js\"></script>\n");

        menu.append("<div class=\"dtree\">\n");

        menu.append("<script><!--\n t = new dTree('t');\n");

        menu.append("t.add(0,-1,'Menu GSAN');\n");

        ordenarArvoreOrdemAlfabeticaPorProfundidade(arvoreFuncionalidades);

        percorrerArvorePorProfundidadeOrganizarMenu(arvoreFuncionalidades, 0);

        menu.append("t.draw();\n//--></script>\n");

        menu.append("</div>");

        return menu.toString();
    }

    private void percorrerArvorePorProfundidade(FuncionalidadeCategoria arvore, int nivelArvore, Usuario usuario) {

        Iterator iterator = arvore.getElementos().iterator();

        while (iterator.hasNext()) {
            contadorId++;

            Object itemArvore = iterator.next();

            if (itemArvore instanceof Funcionalidade) {

                Funcionalidade funcionalidade = (Funcionalidade) itemArvore;

                if (funcionalidade.getIndicadorPontoEntrada().equals(ConstantesSistema.SIM)) {
                	
                	if(funcionalidade.getIndicadorNovaJanela().equals(ConstantesSistema.SIM)){
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
                        } else {
                			menu.append("d.add(" + contadorId + "," + nivelArvore + ",'" +
    	                        funcionalidade.getDescricao() + "','" +
    	                        funcionalidade.getCaminhoUrl() + ((funcionalidade.getCaminhoUrl().indexOf("?") != -1) ? "" : "?menu=sim")+  "');\n");
                		}
                	}
                } else {
                    contadorId--;
                }

            } else if (itemArvore instanceof FuncionalidadeCategoria) {
                FuncionalidadeCategoria funcionalidadeCategoria = (FuncionalidadeCategoria) itemArvore;
                
                if(funcionalidadeCategoria.getElementos() != null && !funcionalidadeCategoria.getElementos().isEmpty()){
                    menu.append("d.add(" + contadorId + "," + nivelArvore + ",'" + funcionalidadeCategoria.getNome() + "','#');\n");
                    percorrerArvorePorProfundidade(((FuncionalidadeCategoria) itemArvore), contadorId, usuario);
                	
                }
            }
        }

    }

    private void percorrerArvorePorProfundidadeOrganizarMenu(FuncionalidadeCategoria arvore, int nivelArvore) {

        Iterator iterator = arvore.getElementos().iterator();

        while (iterator.hasNext()) {
            contadorId++;

            Object itemArvore = iterator.next();

            if (itemArvore instanceof Funcionalidade) {

                Funcionalidade funcionalidade = (Funcionalidade) itemArvore;

                if (funcionalidade.getIndicadorPontoEntrada().equals(ConstantesSistema.SIM)) {
                	
        			menu.append("t.add(" 
        				+ contadorId + "," 
        				+ nivelArvore + ",'" 
        				+ funcionalidade.getDescricao() +"-"+ funcionalidade.getNumeroOrdemMenu()+ "','" 
        				+ "exibirOrganizarMenuArvoreAction.do?idFuncionalidade="+funcionalidade.getId() + "');\n");
            		
                } else {
                    contadorId--;
                }
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
        
    private void ordenarArvoreOrdemAlfabeticaPorProfundidade(FuncionalidadeCategoria arvore) {

        Iterator iterator = arvore.getElementos().iterator();


        while (iterator.hasNext()) {

            Object itemArvore = iterator.next();

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

                ordenarArvoreOrdemAlfabeticaPorProfundidade(funcionalidadeCategoria);
            }
        }
    }
}
