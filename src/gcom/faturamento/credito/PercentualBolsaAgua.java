package gcom.faturamento.credito;

import java.math.BigDecimal;
import java.math.RoundingMode;

import gcom.cadastro.imovel.Imovel;

/**
 * @author Kurt Matheus Sampaio de Matos
 * @date: 08/06/2022
 * Enum com constantes cujas quais conterão a lógica para retornar percentuais de agua e/ou esgoto
 */

public enum PercentualBolsaAgua {
	
	PERCENTUAL_AGUA {
		@Override
		public BigDecimal retornaValor(BigDecimal valor, Imovel imovel) {
			if(imovel.isLigadoAgua()) {
				if(imovel.isLigadoAgua() && imovel.isLigadoEsgoto()) {
					return valor.multiply(new BigDecimal("0.625")).setScale(2, RoundingMode.HALF_UP);
				}
				
				return valor;
			}
			return BigDecimal.ZERO;
		}
	},
	PERCENTUAL_ESGOTO {
		@Override
		public BigDecimal retornaValor(BigDecimal valor, Imovel imovel) {
			if(imovel.isLigadoEsgoto()) {
				if(imovel.isLigadoAgua() && imovel.isLigadoEsgoto()) {
					return valor.multiply(new BigDecimal("0.375")).setScale(2,  RoundingMode.HALF_UP);
				}
			
				return valor;
			}
			return BigDecimal.ZERO;
		}
	};
	
	public abstract BigDecimal retornaValor(BigDecimal valor, Imovel imovel);
}
