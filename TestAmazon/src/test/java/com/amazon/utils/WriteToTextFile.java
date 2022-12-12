package com.amazon.utils;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WriteToTextFile {

            public void writeToTextFile(List<String> text) throws IOException {
                Path filename = Path.of("/Users/madhukumarnm/Desktop/TestAmazon/target/aboutThisForSamSung.txt");
                Files.write(filename, text);

            }
}
