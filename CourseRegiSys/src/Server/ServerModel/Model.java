package Server.ServerModel;
import Server.ServerModel.Registration.*;

public class Model {

	private RegistrationApp regApp;
	
	public Model(CourseCatalogue cat) {
		regApp = new RegistrationApp(cat);
	}
	
}