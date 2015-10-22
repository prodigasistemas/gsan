-- // Adicionando funcao Dias Uteis
-- Migration SQL that makes the change goes here.

CREATE OR REPLACE FUNCTION adiciona_dias_uteis
(
	var_data date, 
	dias integer,
	var_cidade_id integer
)
  RETURNS date AS
$BODY$

DECLARE
        intervalo interval := '1 day';
        contador integer = 0;
	verifica_cidade boolean;
BEGIN
	WHILE (contador < dias) dias LOOP

		var_data :=  var_data + intervalo;

		IF ((EXTRACT(DOW FROM var_data) = 0) OR (EXTRACT(DOW FROM var_data) = 6)) THEN
			CONTINUE;
		END IF;

		IF 
		(
			EXISTS 
			(
				SELECT nfer_dtferiado FROM cadastro.nacional_feriado 
				WHERE to_char(nfer_dtferiado, 'mm-dd') = to_char(var_data, 'mm-dd')
			) = TRUE
		) THEN
			CONTINUE;
		END IF;

		contador := contador + 1;

	END LOOP;
	RETURN var_data;
END
$BODY$
  LANGUAGE plpgsql STABLE
  COST 100;

  
ALTER FUNCTION adiciona_dias_uteis(date, integer, integer) OWNER TO postgres;



-- //@UNDO
-- SQL to undo the change goes here.

DROP FUNCTION adiciona_dias_uteis(date, integer, integer);
