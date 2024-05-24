package exceptionPackage;

public class AddValueException extends Exception{
    public AddValueException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return "Erreur lors d'un ajout ! Merci de contacter un administrateur ! ";
    }
}
