function ChkCPF(const cCPF: String): boolean; 
var 
i, soma, multiplo: integer; 
CPF: string; 
begin 
ChkCPF := false; 
CPF := LimpaString(cCPF); 
if Length(CPF) <> 11 then exit; 
soma := 0; 
for i := 9 downto 1 do 
begin 
soma := soma + CharToInt(CPF[i]) * (11 - i); 
end; 
multiplo := soma mod 11; 
if multiplo <= 1 then multiplo := 0 else multiplo := 11 - multiplo; 
if (multiplo <> CharToInt(CPF[10])) then exit; 
soma := 0; 
for i := 10 downto 1 do 
begin 
soma := soma + CharToInt(CPF[i]) * (12 - i); 
end; 
multiplo := soma mod 11; 
if multiplo <= 1 then multiplo := 11; 
ChkCPF := CharToInt(CPF[11]) = (11 - multiplo); 
end; 
