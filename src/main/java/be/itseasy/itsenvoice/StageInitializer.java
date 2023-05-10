package be.itseasy.itsenvoice;

import be.itseasy.itsenvoice.view.controllers.WindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<ItsEnvoiceApplication.StageReadyEvent> {
    @Value("classpath:/be/itseasy/itsenvoice/fxmls/main-window.fxml")
    private Resource fxmlResource;
    private final String applicationTitle;
    private final ApplicationContext applicationContext;

    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle, ApplicationContext applicationContext) {
        this.applicationTitle = applicationTitle;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ItsEnvoiceApplication.StageReadyEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlResource.getURL());
            fxmlLoader.setControllerFactory(aClass -> applicationContext.getBean(aClass));

            Parent parent = fxmlLoader.load();
            WindowController windowsController = fxmlLoader.getController();
            Stage stage = event.getStage();
            WindowController.setStage(stage);
            stage.setTitle(applicationTitle);
            Scene scene = new Scene(parent,  1280, 780);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
