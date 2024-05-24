package exceptionPackage;

public class UpdateValueException extends Exception{
    public UpdateValueException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return "Erreur de mise à jour ! Merci de contacter un administrateur ! ";
    }
}
