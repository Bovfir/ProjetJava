package dataAccessPackage;

import modelPackage.Locality;
import exceptionPackage.*;
import modelPackage.WorkersByLocality;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocalityDBAccess implements LocalityDataAccess {

    private ArrayList<Locality> localities;
    private Connection connection;

    public LocalityDBAccess() throws ConnectionException {
            connection = SingletonConnection.getInstance();
    }
    @Override
    public ArrayList<Locality> getAllLocalities() throws AllValuesException {
        try {
            localities = new ArrayList<>();
            String sqlInstruction = "SELECT * FROM locality order by postal_code";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            Locality locality;
            while (data.next()) {
                Integer localityId = data.getInt("locality_id");
                Integer postalCode = data.getInt("postal_code");
                String wording = data.getString("wording");

                locality = new Locality(localityId, postalCode, wording);
                localities.add(locality);
            }
            return localities;
        } catch(SQLException exception){
            throw new AllValuesException(exception.getMessage());
        }
    }
    @Override
    public ArrayList<Locality> getLocalityWithBakery() throws AllValuesException {
        try {
            localities = new ArrayList<>();
            String sqlInstruction = "SELECT * FROM locality l INNER JOIN bakery b ON l.locality_id = b.locality_id";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            Locality locality;
            while (data.next()) {
                locality = new Locality(data.getInt("locality_id"), data.getInt("postal_code"), data.getString("wording"));
                localities.add(locality);
            }
            return localities;
        }catch (SQLException exception){
            throw new AllValuesException(exception.getMessage());
        }
    }

    @Override
    public ArrayList<WorkersByLocality> getWorkersByLocality(ArrayList<Integer> postalCodes) throws AllValuesException{
        try{
            ArrayList<WorkersByLocality> workersByLocalities = new ArrayList<>();
            StringBuilder stringBuilder = new StringBuilder("(");
            for(int i = 1; i <= postalCodes.size(); i++){
                stringBuilder.append((i == postalCodes.size() ? "?)": "?,"));
            }
            StringBuilder sqlInstructionBuilder = new StringBuilder("select last_name, first_name , b.wording as 'bakery', l.wording as 'locality', l.postal_code from locality l inner join person p on l.locality_id = p.locality_id  inner join worker w on w.registration_number = p.registration_number inner join bakery b on b.bakery_id = w.bakery_id where postal_code in ");
            sqlInstructionBuilder.append(stringBuilder);
            sqlInstructionBuilder.append(" ORDER BY l.postal_code, l.wording ");

            PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(sqlInstructionBuilder));

            for(int iParam = 1 ; iParam <= postalCodes.size();iParam++) {
                preparedStatement.setInt(iParam, postalCodes.get(iParam-1));
            }

            ResultSet data = preparedStatement.executeQuery();
            WorkersByLocality workersByLocality;
            while(data.next()){
                String lastName = data.getString("last_name");
                String firstName = data.getString("first_name");
                String bakery = data.getString("bakery");
                String locality = data.getString("locality");
                Integer postalCode = data.getInt("postal_code");

                workersByLocality = new WorkersByLocality(lastName,firstName,bakery,locality,postalCode);
                workersByLocalities.add(workersByLocality);
            }
            return workersByLocalities;
        }catch (SQLException exception){
            throw new AllValuesException(exception.getMessage());
        }
    }

    @Override
    public ArrayList<Integer> getAllPostalCodes() throws AllValuesException{
        try{
            ArrayList<Integer> postalCodes = new ArrayList<>();
            String sqlInstruction = "select distinct postal_code from locality order by postal_code;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            int postalCode;
            while(data.next()){
                postalCode = data.getInt("postal_code");
                postalCodes.add(postalCode);
            }

            return postalCodes;
        }catch (SQLException exception){
            throw new AllValuesException(exception.getMessage());
        }
    }
}