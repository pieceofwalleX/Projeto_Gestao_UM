package FrontEnd;

public class Consola {


    public void escrever(String mensagem) {
        System.out.println(mensagem);
    }

    public void escreverErro(String mensagem) {
        System.err.println(mensagem);
    }


    public boolean lerInteiro(String input) throws InterruptedException {
        Integer numero = null;

        do {
            try {
                numero = Integer.valueOf(input);
            } catch (NumberFormatException e) {
                escreverErro(Color.RED +"#ERROR "+ input +" nao e um numero #\n" +Color.RESET);
                Thread.sleep(400);
                return false;
            }

        } while (numero == null);

        return true;
    }
}
