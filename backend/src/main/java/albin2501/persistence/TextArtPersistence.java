package albin2501.persistence;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

import albin2501.entity.TextArt;
import albin2501.exception.PersistenceException;

// TODO: Add database instead of .json file

public class TextArtPersistence {
    private static String fileName = "./src/main/java/albin2501/util/textArt.json";

    public static TextArt[] getTextArt() {
        try {
            TextArt[] data = null;
            File file = new File(fileName);
            ObjectMapper mapper = new ObjectMapper();

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

    public static TextArt postTextArt(String asciiArt) {
        try {
            TextArt[] currData = getTextArt();
            Long highestId = 0L;
            for (TextArt x : currData) {
                highestId = highestId < x.id ? x.id : highestId;
            }
            TextArt textArt = new TextArt(asciiArt, ++highestId);
            TextArt[] data = new TextArt[currData.length + 1];
            for (int i = 0; i < currData.length; i++) {
                data[i] = currData[i];
            }
            data[data.length - 1] = textArt;
            File file = new File(fileName);

            // Replace everything with the updated data
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, data);

            return textArt;
        } catch (IOException e) {
            throw new PersistenceException(e.getMessage(), e);
        }
    }

    public static boolean deleteTextArt() {
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

    public static boolean deleteTextArtById(Long id) {
        try {
            File file = new File(fileName);

            // Replace everything, but the deleted textArt
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, Arrays.stream(getTextArt()).filter(x -> x.id != id).toArray(TextArt[]::new));
            
            return true;
        } catch (IOException e) {
            throw new PersistenceException(e.getMessage(), e);
        }
    }
}
