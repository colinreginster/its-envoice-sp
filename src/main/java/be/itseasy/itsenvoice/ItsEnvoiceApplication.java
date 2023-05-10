package be.itseasy.itsenvoice;

import atlantafx.base.theme.PrimerDark;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

public class ItsEnvoiceApplication extends Application {
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() throws Exception {
        applicationContext = new SpringApplicationBuilder(ItsEnvoiceApplicationLauncher.class).run();
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image(ItsEnvoiceApplication.class.getResourceAsStream("/be/itseasy/itsenvoice/img/logo.png")));
        stage.setTitle("IT's Envoice");
        applicationContext.publishEvent(new StageReadyEvent(stage));
    }
    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }
    static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }
        public Stage getStage() {
            return ((Stage) getSource());
        }
    }
}
