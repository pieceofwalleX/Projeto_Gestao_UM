package FrontEnd;


/*
 * Esta classe e so para decoracao
 * Server para mudar as cores na consola com codigos ANSI
 */
public class Color {
    public static final String RESET = "\u001B[0m";

    public static final String BLACK = "\033[0;90m"; 
    public static final String RED = "\033[0;91m";  
    public static final String GREEN= "\033[0;92m"; 
    public static final String YELLOW = "\033[0;93m"; 
    public static final String BLUE = "\033[0;94m";   
    public static final String PURPLE = "\033[0;95m"; 
    public static final String CYAN= "\033[0;96m";   
    public static final String WHITE = "\033[0;97m"; 

    public static final String BLACK_BOLD = "\033[1;90m"; 
    public static final String RED_BOLD = "\033[1;91m";   
    public static final String GREEN_BOLD = "\033[1;92m";
    public static final String YELLOW_BOLD = "\033[1;93m";
    public static final String BLUE_BOLD = "\033[1;94m";  
    public static final String PURPLE_BOLD = "\033[1;95m";
    public static final String CYAN_BOLD = "\033[1;96m"; 
    public static final String WHITE_BOLD = "\033[1;97m"; 
}
