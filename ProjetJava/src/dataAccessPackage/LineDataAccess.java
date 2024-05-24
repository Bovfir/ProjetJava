package dataAccessPackage;

import modelPackage.Line;
import exceptionPackage.*;
import modelPackage.LineResearch;

import java.util.ArrayList;

public interface LineDataAccess {

    void addLine(Line line) throws AddValueException;
    ArrayList<LineResearch> getLineByCategory(String category) throws AllValuesException;
}
