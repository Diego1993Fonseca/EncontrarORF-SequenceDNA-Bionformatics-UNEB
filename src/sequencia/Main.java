/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sequencia;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import jxl.*;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.Workbook;
import jxl.write.DateFormat;
import jxl.write.Number;
import jxl.write.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Diego
 */
public class Main {
    
      public static void main(String[] args) throws WriteException {
          //LISTAS DAS ORFS
          ArrayList<Orf> lista = new ArrayList<Orf>();
          String[] Titulos = new String[6];
          int fita;
          String troca;
          StringBuffer TrocaInvert;
          String linha; 
         
          //INSTANCIANDO CLASSE 
          SequenciaFasta seq = new SequenciaFasta(null,null);
          Orf NovaOrf;
         
          
          //TITULOS DA TABELA
           Titulos[0] = "ID";
           Titulos[1] = "SEQUÊNCIA";
           Titulos[2] = "FRAME";
           Titulos[3] = "INICIO";
           Titulos[4] = "FIM";
           Titulos[5] = "TAMANHO";
           
      
           
           
         Scanner ler = new Scanner(System.in);
         System.out.printf("Informe o nome de arquivo texto:\n");
         String nome = ler.nextLine();
         System.out.printf("\nConteúdo do arquivo texto:\n");
    
         try {
                FileReader arq = new FileReader(nome);
                BufferedReader lerArq = new BufferedReader(arq);
                
                // lê a primeira linha - o Nome da sequência
                linha = lerArq.readLine(); 
                   
               seq.setNomeSequencia(linha);
               

                while (linha != null) {
                  
                   // Lê a partir da segunda linha
                  linha = lerArq.readLine(); 
                   
                  if(seq.getSequencia() == null)
                        seq.setSequencia(linha);
                  
                  else
                    
                      seq.setSequencia(seq.getSequencia()+ linha);
                                 
                    }
 
                arq.close();
            } catch (IOException e) {
                System.err.printf("Erro na abertura do arquivo: %s.\n",
                e.getMessage());
            }
        
         
      
          
          linha = seq.getSequencia();
          char set[] =linha.toCharArray();
          System.out.printf("%d \n",linha.length());
          fita= 1;
          
          // Positiva= 1
          // Negativa = 2
          //
          lista = ProcurarOrf (linha,lista, fita);
          
          
          
           TrocaInvert = new StringBuffer(linha.substring(0, linha.length()-4));
           TrocaInvert.reverse();
           troca = TrocaInvert.toString();
       
           fita = 2;
           lista = ProcurarOrf (linha,lista, fita);
          

           
                    try{   
                                String filename = "entrada2.xls"; //INFORMANDO O NOME DO ARQUIVO EXCEL
                                WorkbookSettings ws = new WorkbookSettings();
                                ws.setLocale(new Locale("en", "EN"));

                                  //CRIANDO O ARQUIVO
                                WritableWorkbook arquivo = Workbook.createWorkbook(new File(filename), ws);


                                WritableSheet folha = arquivo.createSheet("Folha1", 0);
                                WritableFont FonteTitulo = new WritableFont(WritableFont.ARIAL,12, WritableFont.BOLD);
                                WritableFont FonteTexto = new WritableFont(WritableFont.ARIAL,10, WritableFont.NO_BOLD);
                                WritableCellFormat Ftitulo = new WritableCellFormat(FonteTitulo);
                                WritableCellFormat Ftexto = new WritableCellFormat(FonteTexto);


                                AdicionaTitulo (Titulos, Ftitulo, folha);
                                AdicionaCelulas(lista, Ftexto, folha);

                                 arquivo.write();
                                 arquivo.close();


                     }catch (IOException e)
                     {
                                e.printStackTrace();
                     }

      
      }
      
      
      
      public static void AdicionaTitulo(String[] titulos,WritableCellFormat FormatoEscrita,WritableSheet folha) throws WriteException{
            
   
             //CRIANDO  E ADICIONANDO CELULA- INFORMANDO INDICE E CONTEÚDO
             Label celula;
             for (int i =0;i< titulos.length; i++){
                 celula = new Label(i,0,titulos[i],FormatoEscrita);
                 folha.addCell(celula);
             }
        
            
   
    }
      
   private static void AdicionaCelulas(ArrayList<Orf> orf,WritableCellFormat FormatoEscrita,
                                            WritableSheet folha) throws WriteException{
   
       Label celula;
       int id=0;
       int linha=1;
       int larg=0;
       Number numberCell ;
        
        //FORMATO NUMERO
        WritableFont numberFont = new WritableFont(WritableFont.ARIAL);
        WritableCellFormat numberFormat = new WritableCellFormat(numberFont, new NumberFormat("####"));

        
        
       
       for (Orf Objeto : orf) {
           
           //ADICIOANDO ID
           id++;
           numberCell = new Number(larg,linha,id,numberFormat);
           folha.addCell(numberCell);
           
           
           
           larg++;
           //ADICIOANDO ORF
           celula = new Label(larg,linha,Objeto.getOrf(),FormatoEscrita);
           folha.addCell(celula);
           
           larg++;
           //ADICIOANDO FRAME
           numberCell = new Number(larg,linha,Objeto.getFrame(),numberFormat);
           folha.addCell(numberCell);
           
           larg++;
           //ADICIOANDO INICIO
           
           numberCell = new Number(larg,linha,Objeto.getInicio(),numberFormat);
           folha.addCell(numberCell);
           
           larg++;
           //ADICIOANDO FIM
           numberCell = new Number(larg,linha,Objeto.getFim(),numberFormat);
           folha.addCell(numberCell);
           
           larg++;
           //ADICIOANDO TAMANHO
          numberCell = new Number(larg,linha,Objeto.getTamanho(),numberFormat);
           folha.addCell(numberCell);
           
           linha++;
            larg = 0;
       }
   
   
   }
   
   private static ArrayList<Orf> ProcurarOrf (String linha,ArrayList<Orf> lista, int fita){
       
       
       Orf NovaOrf;
       char set[] =linha.toCharArray();
         
          for(int base = 1 ; base <= 3; base++){
          
          for (int i = base - 1; i < linha.length();i=i+3){
          
              //Encontrando o inicio da ORF
              if(set[i]== 'A' && set[i+1]== 'T' && set[i+2]== 'G'){
                  
                
                  for (int l = i+3; l < linha.length();l =l+3){
                      
                      //PROCURANDO O FIM DA ORF
                     if((set[l]== 'T' && set[l+1]== 'A' && set[l+2]== 'A') ||
                          (set[l]== 'T' && set[l+1]== 'A' && set[l+2]== 'G')||
                             (set[l]== 'T' && set[l+1]== 'G' && set[l+2]== 'A')){
                               
                                if(fita == 1)
                                 NovaOrf = new Orf(i,base,l+2,linha.substring(i, l+3),linha.substring(i,l+2).length());
                               else
                                 NovaOrf = new Orf(i,-base,l+2,linha.substring(i, l+3),linha.substring(i,l+2).length());   
                                 lista.add(NovaOrf);
                             /*   
                                System.out.printf(" - INICIO: %d - %d  FIM : %d - %d - %d\n",i, i+2, l, l+2, base);
                                System.out.printf("- INICIO: %s%s%s   FIM : %s%s%s - %d - TAMANHO: %d\n",set[i],set[i+1],set[i+2],set[l],set[l+1],set[l+2], base,linha.substring(i,l+2).length());
                                System.out.printf("%s \n ",linha.substring(i, l+3));
                               
                               */ 
                             
                                 l =linha.length();  
                     }
                      
                  }
              }
      }
   } 
   return lista ;
   }  
      
 
             
             
         
}
    

