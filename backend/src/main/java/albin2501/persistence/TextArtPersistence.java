package albin2501.persistence;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import albin2501.entity.TextArt;
import albin2501.exception.PersistenceException;

// TODO: Add database instead of .json file

@Repository
public class TextArtPersistence {
    private String fileName = "./src/main/java/albin2501/util/textArt.json";

    public TextArt[] getTextArt() {
        try {
            TextArt[] data = null;
            File file = new File(fileName);
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            // Create new file if needed
            if (file.createNewFile()) {
                TextArt[] template = new TextArt[]{};
                mapper.writeValue(file, template);
            }

            data = mapper.readValue(file, TextArt[].class);

            return data;
        } catch (IOException e) {
            throw new PersistenceException(e.getMessage(), e);
        }
    }

    public TextArt postTextArt(String asciiArt, String name, Long width, Long height) {
        // index 0 = newest
        try {
            TextArt[] currData = getTextArt();
            Long highestId = 0L;
            for (TextArt x : currData) {
                highestId = highestId < x.getId() ? x.getId() : highestId;
            }
            TextArt textArt = new TextArt(asciiArt, ++highestId, name, LocalDateTime.now(), width, height);
            TextArt[] data = new TextArt[currData.length + 1];
            for (int i = 0; i < currData.length; i++) {
                data[i + 1] = currData[i];
            }
            data[0] = textArt;
            File file = new File(fileName);

            // Replace everything with the updated data
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.writeValue(file, data);

            return textArt;
        } catch (IOException e) {
            throw new PersistenceException(e.getMessage(), e);
        }
    }

    public boolean deleteTextArt() {
        try {
            File file = new File(fileName);
            ObjectMapper mapper = new ObjectMapper();

            // Create new file if needed
            file.createNewFile();
            TextArt[] template = new TextArt[]{};
            mapper.writeValue(file, template);

            return true;
        } catch (IOException e) {
            throw new PersistenceException(e.getMessage(), e);
        }
    }

    public boolean deleteTextArtById(Long id) {
        try {
            File file = new File(fileName);

            // Replace everything, but the deleted textArt
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.writeValue(file, Arrays.stream(getTextArt()).filter(x -> x.getId() != id).toArray(TextArt[]::new));
            
            return true;
        } catch (IOException e) {
            throw new PersistenceException(e.getMessage(), e);
        }
    }
}
