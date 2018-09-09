package infoextractor;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoExtractor {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String nomeArquivo = "C://05.09.txt"; //Exemplo
        String document = LerArquivoParaString(nomeArquivo);
        String[] registros = document.split("Acomodação"); // Quebrar linhas a cada ocorrencia de "Acomodação"
        
        // Regex:
        Pattern carteirinhaPadrao = Pattern.compile("Carteirinha: ([0-9 ]*)");
        Pattern pacientePadrao = Pattern.compile("Paciente: (.*)Internação");
        Pattern internacaoPadrao = Pattern.compile("Internação: ([0-9\\/]*)");

        // Processar Regex:
        for (String line : registros) {
            String carteirinha = AcharPadrao(carteirinhaPadrao, line);
            String paciente = AcharPadrao(pacientePadrao, line);
            String internacao = AcharPadrao(internacaoPadrao, line);
            
            System.out.println(carteirinha + "," + paciente + "," + internacao + "");
        }
    }
    
    public static String AcharPadrao(Pattern pattern, String line){
        Matcher m = pattern.matcher(line);
        if (m.find( )) {
           return m.group(1);
        }else {
           System.out.println("NAO ENCONTRADO");
           return "";
        }
    }
    
    public static String LerArquivoParaString(String path) throws IOException{
        String line = "";
        String document = "";

        InputStreamReader fileReader =  new InputStreamReader(new FileInputStream(path), "UTF8");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((line = bufferedReader.readLine()) != null)
            document += line;

        bufferedReader.close();
        
        return document;
    }

}
