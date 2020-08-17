package br.com.serratec.main;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import br.com.serratec.excecoes.DependenteException;
import br.com.serratec.model.Dependente;
import br.com.serratec.model.Funcionario;
import br.com.serratec.model.Parentesco;

public class TesteFuncionario {
	public static void main(String[] args) {
		Boolean teste = false;
		Funcionario func = null; 
		Set<Funcionario> funcionarios = new HashSet<Funcionario>();
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Qual o diretorio do arquivo a ser escaneado?");
			String arquivo = sc.next();
            Scanner in = new Scanner(new InputStreamReader(new FileInputStream(arquivo), "UTF-8"));
            
            while(in.hasNext()) {
            	String linha = in.nextLine();
            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            	if (!linha.isEmpty() && teste == true){
            		String[] infoDep = linha.split(";");
            		Dependente dep = new Dependente (infoDep[0],
            										infoDep[1], 
            										LocalDate.parse(infoDep[2],formatter), 
            										Parentesco.valueOf(infoDep[3]));
            		dep.verificarIdade();
            		dep.tratarCpf();
            		func.setDependentes().add(dep);
            		           		            		
            		
            	} else if (!linha.isEmpty()){     		
            		String[] infoFunc = linha.split(";");            		
            		func = new Funcionario (infoFunc[0],
            											infoFunc[1], 
            											LocalDate.parse(infoFunc[2],formatter), 
            											Double.parseDouble(infoFunc[3]));            		
            	
            		func.tratarCpf();
            		funcionarios.add(func);
            		
            		teste = true;
            	} else {
            		teste = false;            		
            	}
         	
            }
        	
            in.close();
            System.out.println("Qual o diretorio do arquivo de saida?");
			String arquivoSaida = sc.next();
           BufferedWriter bf = new BufferedWriter(new FileWriter(arquivoSaida));
           for(Funcionario value : funcionarios) {
            	value.calculoInss();
        		value.calculaIr();
        		value.salarioLiquido();
        		bf.append(value.toString());
                 
                }
           
           bf.close();
           sc.close();
           System.out.println("Arquivo criado e texto inserido!!");
           
        }catch (IOException e) {
            System.out.println("Erro ao acessar arquivo");
        }catch (DependenteException e) {
        	System.out.println(e.getMessage());
        }
				
	}	
}