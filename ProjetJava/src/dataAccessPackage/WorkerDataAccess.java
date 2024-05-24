package dataAccessPackage;

import modelPackage.Worker;
import exceptionPackage.*;
import java.util.ArrayList;

public interface WorkerDataAccess {
    ArrayList<Worker> getAllWorkers() throws AllValuesException;
    ArrayList<Worker> getAllWorkersByBakeries(int bakeryId) throws AllValuesException;
    ArrayList<String> getAllEmails() throws AllValuesException;
    void addWorker(Worker worker) throws AddValueException;
    void updateWorker(Worker worker) throws UpdateValueException;
    void deleteWorker(int registrationNumber) throws DeleteValueException;
}
