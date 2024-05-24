package dataAccessPackage;

import modelPackage.Worker;
import exceptionPackage.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;
import java.util.Set;

public class WorkerBDAccess implements WorkerDataAccess{
    private Connection singleton;
    private ArrayList<Worker> workers;

    public WorkerBDAccess() throws ConnectionException {
        singleton = SingletonConnection.getInstance();
    }
    @Override
    public ArrayList<Worker> getAllWorkers() throws AllValuesException {
        try {
            workers = new ArrayList<>();
            String sqlInstruction = "select p.registration_number, p.first_name, p.last_name,p.birthday, p.email, p.phone_number, p.address, l.locality_id , w.is_admin,w.manager_id,b.bakery_id from worker w inner join person p on p.registration_Number = w.registration_Number inner join locality l on l.locality_id = p.locality_id inner join bakery b on b.bakery_id = w.bakery_id order by registration_number;";
            PreparedStatement preparedStatement = singleton.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            while(data.next()){
                Integer registrationNumber = data.getInt("registration_number");
                String firstName = data.getString("first_name");
                String lastName = data.getString("last_name");
                String email = data.getString("email");
                LocalDate birthday = data.getDate("birthday").toLocalDate();
                Integer locality = data.getInt("locality_id");
                Boolean isAdmin = data.getBoolean("is_admin");
                Integer bakery = data.getInt("bakery_id");

                try {
                    Worker worker = new Worker(registrationNumber,lastName,firstName,email,birthday,locality,isAdmin,bakery);
                    if (data.getObject("manager_id") != null) {
                        worker.setManagerId(data.getInt("manager_id"));
                    }
                    if (data.getObject("address") != null) {
                        worker.setAddress(data.getString("address"));
                    }
                    if (data.getObject("phone_number") != null) {
                        worker.setPhoneNumber(data.getInt("phone_number"));
                    }
                    workers.add(worker);
                }catch (SettorException exception){
                    throw new AllValuesException(exception.getMessage());
                }
            }
            return workers;
        }catch (SQLException exception){
            throw new AllValuesException(exception.getMessage());
        }
    }
    @Override
    public ArrayList<Worker> getAllWorkersByBakeries(int bakeryId) throws AllValuesException{
        try{
            workers = new ArrayList<>();
            String sqlInstruction ="select p.registration_number, p.first_name, p.last_name,p.birthday, p.email, p.phone_number, p.address, p.locality_id, w.is_admin,w.manager_id,b.bakery_id from worker w inner join person p on p.registration_number = w.registration_number inner join bakery b on w.bakery_id = b.bakery_id where b.bakery_id = ?";
            PreparedStatement preparedStatement = singleton.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1,bakeryId);
            ResultSet data = preparedStatement.executeQuery();

            while(data.next()){
                Integer registrationNumber = data.getInt("registration_number");
                String firstName = data.getString("first_name");
                String lastName = data.getString("last_name");
                String email = data.getString("email");
                LocalDate birthday = data.getDate("birthday").toLocalDate();
                Integer locality = data.getInt("locality_id");
                Boolean isAdmin = data.getBoolean("is_admin");
                Integer bakery = data.getInt("bakery_id");

                try {
                    Worker worker = new Worker(registrationNumber,lastName,firstName,email,birthday,locality,isAdmin,bakery);
                    if (data.getObject("manager_id") != null) {
                        worker.setManagerId(data.getInt("manager_id"));
                    }
                    if (data.getObject("address") != null) {
                        worker.setAddress(data.getString("address"));
                    }
                    if (data.getObject("phone_number") != null) {
                        worker.setPhoneNumber(data.getInt("phone_number"));
                    }
                    workers.add(worker);
                }catch (SettorException exception){
                    throw new AllValuesException(exception.getMessage());
                }
            }
            return workers;
        }catch (SQLException exception){
            throw new AllValuesException(exception.getMessage());
        }
    }

    @Override
    public ArrayList<String> getAllEmails() throws AllValuesException{
        try {
            ArrayList<String> emails = new ArrayList<>();
            String sqlInstruction = "select p.email from worker w inner join person p on p.registration_number = w.registration_number;";
            PreparedStatement preparedStatement = singleton.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                String email = data.getString("email");
                emails.add(email);
            }
            return emails;
        }catch (SQLException exception){
            throw new AllValuesException(exception.getMessage());
        }
    }

    @Override
    public void addWorker(Worker worker) throws AddValueException {
        try {
            String sqlInstructionPerson = "";
            PreparedStatement preparedStatement;
            sqlInstructionPerson = "INSERT INTO person (last_name,first_name,email,phone_number,birthday,address,locality_id) VALUES (?,?,?,?,?,?,?)";
            preparedStatement = singleton.prepareStatement(sqlInstructionPerson);
            preparedStatement.setString(1, worker.getLastName());
            preparedStatement.setString(2, worker.getFirstName());
            preparedStatement.setString(3, worker.getEmail());
            preparedStatement.setInt(4, worker.getPhoneNumber());
            preparedStatement.setDate(5, Date.valueOf(worker.getBirthday()));
            preparedStatement.setString(6, worker.getAddress());
            preparedStatement.setInt(7, worker.getLocality());
            preparedStatement.executeUpdate();

            String sqlInstructionRegistrationNumber = "select registration_number from person where email = '" + worker.getEmail() + "'";
            PreparedStatement preparedStatement2 = singleton.prepareStatement(sqlInstructionRegistrationNumber);
            ResultSet data = preparedStatement2.executeQuery();

            data.next();
            Integer registrationNumber = data.getInt("registration_number");

            String sqlInstructionWorker = " INSERT INTO worker value(?,?,?,?);";
            PreparedStatement preparedStatement3 = singleton.prepareStatement(sqlInstructionWorker);
            preparedStatement3.setInt(1, registrationNumber);
            preparedStatement3.setBoolean(2, worker.getAdmin());

            if (worker.getManagerId() == null) {
                preparedStatement3.setNull(3, Types.INTEGER);
            } else {
                preparedStatement3.setInt(3, worker.getManagerId());
            }
            preparedStatement3.setInt(4, worker.getBakery());
            preparedStatement3.executeUpdate();
        }catch (SQLException exception){
            throw new AddValueException(exception.getMessage());
        }
    }

    @Override
    public void updateWorker(Worker worker) throws UpdateValueException{
        try{
            String sqlInstructionPerson = "";
            PreparedStatement preparedStatement;
            sqlInstructionPerson = "UPDATE person set last_name = ?, first_name = ?, email = ?, phone_Number = ?, birthday = ?, address = ?, locality_id = ? where registration_number = ?;";
            preparedStatement = singleton.prepareStatement(sqlInstructionPerson);
            preparedStatement.setString(1, worker.getLastName());
            preparedStatement.setString(2, worker.getFirstName());
            preparedStatement.setString(3, worker.getEmail());
            if (worker.getPhoneNumber() != null) {
                preparedStatement.setInt(4, worker.getPhoneNumber());
            } else {
                preparedStatement.setNull(4, java.sql.Types.INTEGER);
            }
            preparedStatement.setDate(5, Date.valueOf(worker.getBirthday()));
            preparedStatement.setString(6, worker.getAddress());
            preparedStatement.setInt(7, worker.getLocality());
            preparedStatement.setInt(8,worker.getRegistrationNumber());
            preparedStatement.executeUpdate();

            String sqlInstruction = "UPDATE worker set is_admin = ?, manager_id = ?, bakery_id = ? where registration_number = ?";
            preparedStatement = singleton.prepareStatement(sqlInstruction);
            preparedStatement.setBoolean(1,worker.getAdmin());
            if (worker.getManagerId() == null) {
                preparedStatement.setNull(2, Types.INTEGER);
            } else {
                preparedStatement.setInt(2, worker.getManagerId());
            }
            preparedStatement.setInt(3,worker.getBakery());
            preparedStatement.setInt(4,worker.getRegistrationNumber());
            preparedStatement.executeUpdate();

        }catch (SQLException exception){
            throw new UpdateValueException(exception.getMessage());
        }
    }

    @Override
    public void deleteWorker(int registrationNumber) throws DeleteValueException{
        try{
            // Rechercher si il a un manager
            String instructionRechercheManager = "select manager_id from worker where registration_number = ?";
            PreparedStatement preparedStatement1 = singleton.prepareStatement(instructionRechercheManager);
            preparedStatement1.setInt(1,registrationNumber);
            ResultSet data = preparedStatement1.executeQuery();

            Integer managerId = null;
            while(data.next()) {
                Object managerIdObject = data.getObject("manager_id");
                if (managerIdObject != null) {
                    managerId = (Integer) managerIdObject;
                }
            }

            // Rattacher son manager
            if(managerId != null){
                String instructionUpdateManager = "update worker set manager_id = ? where manager_id = ?";
                PreparedStatement preparedStatement2 = singleton.prepareStatement(instructionUpdateManager);
                preparedStatement2.setInt(1,managerId);
                preparedStatement2.setInt(2,registrationNumber);
                preparedStatement2.executeUpdate();
            }else {
                String instructionUpdateSansManager = "update worker set manager_id = null where manager_id = ?";
                PreparedStatement preparedStatement5 = singleton.prepareStatement(instructionUpdateSansManager);
                preparedStatement5.setInt(1,registrationNumber);
                preparedStatement5.executeUpdate();
            }

            // Supprimer la personn puis le worker
            String sqlInstructionPerson = "delete from worker where registration_number = ?;";
            PreparedStatement preparedStatement = singleton.prepareStatement(sqlInstructionPerson);
            preparedStatement.setInt(1,registrationNumber);
            preparedStatement.executeUpdate();

            String sqlInstructionWorker = "delete from person where registration_number = ?;";
            PreparedStatement preparedStatement3 = singleton.prepareStatement(sqlInstructionWorker);
            preparedStatement3.setInt(1,registrationNumber);
            preparedStatement3.executeUpdate();

            String sqlInstructionAuto = "ALTER TABLE person AUTO_INCREMENT = ?;";
            PreparedStatement preparedStatement4 = singleton.prepareStatement(sqlInstructionAuto);
            preparedStatement4.setInt(1,registrationNumber);
            preparedStatement4.executeUpdate();
        }catch (SQLException exception){
            throw new DeleteValueException(exception.getMessage());
        }
    }
}
