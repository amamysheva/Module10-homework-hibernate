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
        clientCrudService.create("New_Client");
        System.out.println(clientCrudService.getById(11));
        clientCrudService.update(5, "Updated_client");
        clientCrudService.deleteById(8L);
        System.out.println(clientCrudService.getAllClients());
    }

    public static void planetCrudServiceCheck() {
        PlanetCrudService planetCrudService = new PlanetCrudService();
        planetCrudService.create("NEWPL", "New_Planet");
        System.out.println(planetCrudService.getById("PL4"));
        planetCrudService.update("PL1", "Updated_Planet");
        planetCrudService.deleteById("PL2");
        System.out.println(planetCrudService.getAll());
    }
}
