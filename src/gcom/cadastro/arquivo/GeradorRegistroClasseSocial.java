package gcom.cadastro.arquivo;

import gcom.cadastro.imovel.enums.ClasseSocial;
import gcom.util.Util;

public class GeradorRegistroClasseSocial {
    public StringBuilder build() {
        StringBuilder linha = new StringBuilder();
        
        for(ClasseSocial classe: ClasseSocial.values()){
            linha.append("18");

            linha.append(Util.adicionarZerosEsquedaNumero(2, classe.getId()));

            linha.append(Util.completaString(classe.getDescricao(), 10));
            
            linha.append(System.getProperty("line.separator"));
        }
        
        return linha;
    }
}
