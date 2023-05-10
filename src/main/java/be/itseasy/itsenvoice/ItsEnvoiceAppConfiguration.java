package be.itseasy.itsenvoice;

import com.jfoenix.controls.JFXTabPane;
import fr.dudie.nominatim.client.JsonNominatimClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.AnchorPane;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class ItsEnvoiceAppConfiguration {
    Locale locale =Locale.getDefault();
    ResourceBundle bundle = ResourceBundle.getBundle("itsenvoice", locale);

    private JsonNominatimClient nominatimClient;
    private SimpleStringProperty appTitle;


    @Bean(value = "appTitle",autowireCandidate = true)
    public SimpleStringProperty appTitle() {
        this.appTitle= new SimpleStringProperty();
        return appTitle;
    }

    @Bean(value = "nominatimClient",autowireCandidate = true)
    public JsonNominatimClient nominatimClient() {

        final SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        final ClientConnectionManager connexionManager = new SingleClientConnManager(null, registry);

        final HttpClient httpClient = new DefaultHttpClient(connexionManager, null);


        this.nominatimClient = new JsonNominatimClient( httpClient, "api@its-easy.be");

        return nominatimClient;
    }



    private SimpleStringProperty searchString;


    @Bean(value = "searchString",autowireCandidate = true)
    public SimpleStringProperty searchString() {
        this.searchString= new SimpleStringProperty();
        return searchString;
    }
    private ArrayList<TabInfo> tabsList;
    private JFXTabPane tabPane;

    @Bean(value = "tabPane",autowireCandidate = true)
    public JFXTabPane tabPane(){
        tabPane=new JFXTabPane();
        return tabPane;
    }

    @Bean(value = "tabList",autowireCandidate = true)
    public ArrayList tabsList() {
        tabsList = new ArrayList<TabInfo>();
        tabsList.add(new TabInfo(bundle.getString("app.tabs.companyInfo"), "/be/itseasy/itsenvoice/fxmls/cruds/company.fxml","fth-briefcase"));
        tabsList.add(new TabInfo(bundle.getString("app.tabs.item"), "/be/itseasy/itsenvoice/fxmls/cruds/item.fxml","fth-box"));
        tabsList.add(new TabInfo(bundle.getString("app.tabs.invoice"), "/be/itseasy/itsenvoice/fxmls/cruds/invoices.fxml","fth-file-text"));
        tabsList.add(new TabInfo(bundle.getString("app.tabs.invoiceLayout"), "/be/itseasy/itsenvoice/fxmls/cruds/invoice_layouts.fxml","fth-sidebar"));
        tabsList.add(new TabInfo(bundle.getString("app.tabs.customer"), "/be/itseasy/itsenvoice/fxmls/cruds/customers.fxml","fth-user"));
        tabsList.add(new TabInfo(bundle.getString("app.tabs.invoiceDashboard"), "/be/itseasy/itsenvoice/fxmls/cruds/invoice_dashboard.fxml","fth-trending-up"));
        tabsList.add(new TabInfo(bundle.getString("app.tabs.parameter"), "/be/itseasy/itsenvoice/fxmls/cruds/parameters.fxml","fth-sliders"));

        return tabsList;
    }

    public class TabInfo {
        private String title;
        private String fxmlLocation;
        private String graphicDescription;

        public TabInfo(String title, String fxmlLocation, String graphicDescription) {
            this.title=title;
            this.fxmlLocation=fxmlLocation;
            this.graphicDescription=graphicDescription;
        }

        public String getTitle() {
            return title;
        }

        public String getFxmlLocation() {
            return fxmlLocation;
        }

        public String getGraphicDescription() {
            return graphicDescription;
        }
    }
}
