import org.flywaydb.core.Flyway;
import service.ClientCrudService;
import service.HibernateUtil;
import service.PlanetCrudService;

import java.util.ResourceBundle;

public class DemoHibernate {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("hibernate");
    private static final String JDBC_URL = "hibernate.connection.url";

    public static void main(String[] args) {
        Flyway.configure()
                .dataSource(resourceBundle.getString(JDBC_URL), null, null)
                .load()
                .migrate();

        clientCrudServiceCheck();
        planetCrudServiceCheck();

        HibernateUtil.getInstance().close();
    }

    public static void clientCrudServiceCheck() {
        ClientCrudService clientCrudService = new ClientCrudService();
        clientCrudService.createClient("New_Client");
        System.out.println(clientCrudService.getClientById(11));
        clientCrudService.updateClient(5, "Updated_client");
        clientCrudService.deleteClientById(8L);
        System.out.println(clientCrudService.getAllClients());
    }

    public static void planetCrudServiceCheck() {
        PlanetCrudService planetCrudService = new PlanetCrudService();
        planetCrudService.createPlanet("NEWPL", "New_Planet");
        System.out.println(planetCrudService.getPlanetById("PL4"));
        planetCrudService.updatePlanet("PL1", "Updated_Planet");
        planetCrudService.deletePlanet("PL2");
        System.out.println(planetCrudService.getAllPlanets());
    }
}
