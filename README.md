# ConvertProgressDToCSV

Trata-se de um algortimo desenvolvido em java, que tem como objetivo realizar a conversão de arquivos de dados de extensão (".d") oriundos do Progress para o formato ".csv".
Possibilitando assim a importação para outros banco de dados, SQL Server, Oracle, etc...

# pré requisitos:
  Instaçao do java jdk.

# Passoa a passo

> Acesse a classe ConvertProgressDToCSV.java Altere o conteúdo das variáveis: 
  importFile = "<caminho_do_diretorio>\\<nome_do_arquivo_de_importacao>.d";
  Ex.: importFile = "C:\\Users\\usuario\\Downloads\\Import.d";
  
  exportFile = "<caminho_do_diretorio>\\<nome_do_arquivo_de_exportacao>.csv";
  Ex.: importFile = "C:\\Users\\usuario\\Downloads\\Export.csv";
  
> Você também pode alterar o caractere delimitar que deseja utilizar para delimitar os campos. Alterando a variável: 
  delimited = "<caratere_delimitador>". Ex.: delimited = ",";
