package businessPackage;

import dataAccessPackage.*;
import modelPackage.Worker;
import exceptionPackage.*;
import java.util.ArrayList;

public class WorkerManager {
    private WorkerDataAccess dao;

    public WorkerManager() throws ConnectionException {
        setDao(new WorkerBDAccess());
    }
    private void setDao(WorkerDataAccess workerDataAccess) {
        this.dao = workerDataAccess;
    }

    public ArrayList<Worker> getAllWorkers() throws AllValuesException {
        return dao.getAllWorkers();
    }
    public ArrayList<Worker> getAllWorkersByBakeries(int bakeryId) throws AllValuesException{
        return dao.getAllWorkersByBakeries(bakeryId);
    }

    public ArrayList<String> getAllEmails() throws AllValuesException {
        return dao.getAllEmails();
    }

    public void addWorker(Worker worker) throws AddValueException{
        dao.addWorker(worker);
    }
    public void updateWorker(Worker worker) throws UpdateValueException{
        dao.updateWorker(worker);
    }
    public void deleteWorker(int registrationNumber) throws DeleteValueException{
        dao.deleteWorker(registrationNumber);
    }
}
