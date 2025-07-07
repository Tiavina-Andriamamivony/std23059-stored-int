package com.prog.arith.endpoint.rest.controller.health;

import com.prog.arith.StoredIntService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@RestController
public class StoredIntController {
  private static final Path PATH = Paths.get("/tmp/stored-int.txt");
  private final Random random = new Random();

  @GetMapping("/stored-int")
  public String getStoredInt() throws IOException {
    if (Files.exists(PATH)) return Files.readString(PATH).trim();
    int v = random.nextInt(1000);
    Files.writeString(PATH, Integer.toString(v));
    return Integer.toString(v);
  }
}
