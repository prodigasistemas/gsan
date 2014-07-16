package gcom.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class SequenciaRelatorioContabilidade {

	public static Short getSequencia(Integer tipo) {
		Properties propriedades = SequenciaRelatorioContabilidade.carregaPropriedades();
		
		Set<Object> chaves = propriedades.keySet();
		
		for (Object chave : chaves) {
			
			String[] arrayChaves = chave.toString().split("\\.");
			
			if (arrayChaves[arrayChaves.length -1].equals(tipo.toString())) {
				String valor = (String) propriedades.get(chave);
				return new Short(valor);
			}
		}
		
		return null;
	}
	
	private static Properties carregaPropriedades() {
        Properties propriedades = new Properties();
        InputStream stream;

        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            stream = classLoader.getResourceAsStream("sequenciaRelatorioContabilidade.properties");
            if (stream == null) {
                stream = ConstantesAplicacao.class.getClassLoader().getResourceAsStream("sequenciaRelatorioContabilidade.properties");
            }
            if (stream == null) {
                stream = ConstantesAplicacao.class.getResourceAsStream("sequenciaRelatorioContabilidade.properties");
            }

            propriedades.load(stream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return propriedades;
    }	
}
