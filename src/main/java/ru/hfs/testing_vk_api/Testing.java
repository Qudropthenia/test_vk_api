package ru.hfs.testing_vk_api;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public class Testing {

    Testing() {
        System.out.println("App is running");
        auth();
    }

    /**
     * 6. Инициализация
     * Создайте объект VkApiClient следующим образом:
     */
    void auth() {
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient client = new VkApiClient(transportClient);
    }
}
