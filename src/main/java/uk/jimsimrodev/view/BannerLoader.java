package uk.jimsimrodev.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import uk.jimsimrodev.config.BannerConfig;

public class BannerLoader {

  public List<String> cargarBanner() {

    ObjectMapper mapper = new ObjectMapper();

    try (InputStream inputStream = getClass().getResourceAsStream("/banner.json")) {
      BannerConfig config = mapper.readValue(inputStream, BannerConfig.class);
      return config.banner();
    } catch (IOException e) {

      List<String> response = Arrays.asList("""
                         ▄▄▄▄   ▄▄▄
                        █▀ ██  ██
                           ██ ██           ▀▀
                           █████     ▄█▀█▄ ██ ▄██▀█ ▄▀▀█▄
                           ██ ██▄    ██▄█▀ ██ ▀███▄ ▄█▀██
                         ▀██▀  ▀██▄ ▄▀█▄▄▄▄███▄▄██▀▄▀█▄██

          Creado por: Ing. Jimmis J. Simanca | Version 0.1.1-BETA
          ========================================================
            """);

      return response;
    }
  }
}
