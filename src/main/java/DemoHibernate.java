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
        System.out.println(clientCrudService.getById(11));
        clientCrudService.deleteById(8L);
        System.out.println(clientCrudService.getAllClients());
    }

    public static void planetCrudServiceCheck() {
        PlanetCrudService planetCrudService = new PlanetCrudService();
        System.out.println(planetCrudService.getById("PL4"));
        planetCrudService.deleteById("PL2");
        System.out.println(planetCrudService.getAll());
    }
}
