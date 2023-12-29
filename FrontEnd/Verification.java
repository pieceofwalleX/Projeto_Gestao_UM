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
}
