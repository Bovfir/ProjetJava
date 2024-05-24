package exceptionPackage;

public class AllValuesException extends Exception{
    public AllValuesException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return "Erreur de lecture ! Merci de contacter un administrateur ! ";
    }
}
