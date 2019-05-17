package ru.hfs.testing_vk_api.user_oauth;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
//        try {
//            LogManager
//                    .getLogManager()
//                    .readConfiguration(
//                            App.class.getResourceAsStream("/logging.properties")
//                    );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Properties properties = loadConfiguration();
        initServer(properties);
    }

    private static void initServer(Properties properties) throws Exception {
        Integer port = Integer.valueOf(properties.getProperty("server.port"));
        String host = properties.getProperty("server.host");

        Integer clientId = Integer.valueOf(properties.getProperty("client.id"));
        String clientSecret = properties.getProperty("client.secret");

        HandlerCollection handler = new HandlerCollection();

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setWelcomeFiles(new String[]{"index.html"});
        resourceHandler.setResourceBase(App.class.getResource("/static").getPath());

        VkApiClient vk = new VkApiClient(new HttpTransportClient());
        handler.setHandlers(
                new Handler[] {resourceHandler, new RequestHandler(vk, clientId, clientSecret, host)}
        );

        Server server = new Server(port);
        server.setHandler(handler);

        server.start();
        server.join();
    }

    private static Properties loadConfiguration() {
        Properties properties = new Properties();

        try (InputStream is = App.class.getResourceAsStream("./logging.properties")) {
            properties.load(is);
        } catch (IOException e) {
            LOG.error("Can't load properties file", e);
            throw new IllegalStateException(e);
        }

        return properties;
    }
}
