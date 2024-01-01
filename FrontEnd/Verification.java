package FrontEnd;

public class Verification {
    public boolean isInteger(String Id) throws InterruptedException{
        int number = 0;
        try{
            number = Integer.valueOf(Id);
        }catch(NumberFormatException e){
            System.err.println(Color.RED + "#ERROR Caracter invalido" + Color.RESET);
            Thread.sleep(400);
            return false;
        }
        return true;
    }
 
    public boolean isString(String input) throws InterruptedException{
        /*
         * Verificar se a String so contem Letras
         * Os caracteres [^] e [$] indicam o inicio e o fim do Input
         * [a-zA-Z ] Verifica se so existem caracteres de a-z MAIUSCULOS ou minusculos ou Espacos
         */

        if(!input.matches("^[a-zA-Z ]+$")){
            System.err.println(Color.RED + "#ERROR Use somente letras" + Color.RESET);
            Thread.sleep(400);
            return false;
        }
        return true;
    }
}
