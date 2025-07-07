package com.prog.arith;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.*;

class StoredIntServiceTest {

  private StoredIntService service;
  private final File file = new File("stored-int.txt");

  @BeforeEach
  void setUp() {
    if (file.exists()) {
      file.delete();
    }
    service = new StoredIntService();
  }

  @AfterEach
  void cleanUp() {
    if (file.exists()) {
      file.delete();
    }
  }

  @Test
  void shouldCreateFileIfNotExistsAndReturnNumber() throws IOException {
    // Given
    assertFalse(file.exists());

    // When
    int result = service.getStoredInt();

    // Then
    assertTrue(file.exists(), "File should be created");
    String content = Files.readString(file.toPath());
    int stored = Integer.parseInt(content.trim());

    assertEquals(result, stored, "Returned number should match stored content");
  }

  @Test
  void shouldReturnSameNumberIfFileAlreadyExists() throws IOException {
    // Given
    int expected = 456;
    Files.writeString(file.toPath(), String.valueOf(expected));
    assertTrue(file.exists());

    // When
    int result = service.getStoredInt();

    // Then
    assertEquals(expected, result);
  }

  @Test
  void shouldReturnNumberInValidRange() {
    // When
    int result = service.getStoredInt();

    // Then
    assertTrue(result >= 0 && result < 1000, "Number should be between 0 and 999");
  }
}
