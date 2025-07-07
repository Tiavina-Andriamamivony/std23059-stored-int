package com.prog.arith;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class StoredIntService {

    private static final String FILE_NAME = "stored-int.txt";
    private static final File FILE = new File(FILE_NAME);

    @SneakyThrows
    public int getStoredInt() {
        if (FILE.exists()) {
            // Lire le contenu du fichier
            String content = Files.readString(FILE.toPath());
            return Integer.parseInt(content.trim());
        } else {
            // Générer un nombre aléatoire, l’écrire dans le fichier, et le retourner
            int randomInt = new Random().nextInt(1_000); // entre 0 et 999
            writeToFile(randomInt);
            return randomInt;
        }
    }

    private void writeToFile(int value) throws IOException {
        try (FileWriter writer = new FileWriter(FILE)) {
            writer.write(String.valueOf(value));
        }
    }
}
