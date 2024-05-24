package exceptionPackage;

public class DeleteValueException extends Exception{
    public DeleteValueException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return "Erreur de suppression ! Merci de contacter un administrateur ! ";
    }
}
