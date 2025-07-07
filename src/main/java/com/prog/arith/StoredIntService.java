package com.prog.arith;


import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.Random;

@Service
public class StoredIntService {

  private static final String FILE_NAME = "stored-int.txt";
  private static final File FILE = new File(FILE_NAME);

  public int getStoredInt() {
    if (FILE.exists()) {
      try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
        String content = reader.readLine();
        return Integer.parseInt(content.trim());
      } catch (IOException | NumberFormatException e) {
        throw new RuntimeException("Failed to read from file: " + FILE_NAME, e);
      }
    } else {
      int randomValue = new Random().nextInt(1_000);
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE))) {
        writer.write(String.valueOf(randomValue));
      } catch (IOException e) {
        throw new RuntimeException("Failed to write to file: " + FILE_NAME, e);
      }
      return randomValue;
    }
  }
}
