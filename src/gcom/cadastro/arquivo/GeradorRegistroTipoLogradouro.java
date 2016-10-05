package gcom.cadastro.arquivo;

import java.util.Collection;

import gcom.cadastro.endereco.LogradouroTipo;
import gcom.util.Util;

public class GeradorRegistroTipoLogradouro {
    private Collection<LogradouroTipo> tipos;
    
    public GeradorRegistroTipoLogradouro(Collection<LogradouroTipo> tipos) {
        this.tipos = tipos;
    }

    public StringBuilder build() {
        StringBuilder linha = new StringBuilder();
        
        for (LogradouroTipo tipo : tipos) {
            linha.append("17");
            
            linha.append(Util.adicionarZerosEsquedaNumero(2, tipo.getId().toString()));
            
            linha.append(Util.completaString(tipo.getDescricao(), 20));
            
            linha.append(System.getProperty("line.separator"));
        }

        
        return linha;
    }    
}
