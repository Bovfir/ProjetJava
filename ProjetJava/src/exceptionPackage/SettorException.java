package exceptionPackage;

public class SettorException extends Exception{
    private String message;

    public SettorException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return "Erreur : Valeur(s) erronée(s) : \n" + message  + "\n Merci de contacter un administrateur !";
    }
}
