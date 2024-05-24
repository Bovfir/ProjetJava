package exceptionPackage;

public class ConnectionException extends Exception{

    public ConnectionException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return "Erreur lors de la connection ! Merci de contacter un administrateur ! ";
    }

}
