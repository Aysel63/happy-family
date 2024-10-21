package az.edu.turing;

import az.edu.turing.controller.FamilyController;
import az.edu.turing.dao.FamilyDao;
import az.edu.turing.dao.impl.CollectionFamilyDao;
import az.edu.turing.service.FamilyService;

public class ConsoleApp {

    public static void main(String[] args) {
        FamilyDao familyDao = new CollectionFamilyDao();
        FamilyService familyService = new FamilyService(familyDao);
        FamilyController familyController = new FamilyController(familyService);

        Console console = new Console(familyController);
        console.start();
    }
}
