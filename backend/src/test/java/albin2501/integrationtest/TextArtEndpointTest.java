package albin2501.integrationtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TextArtEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void init() { }

    @Test
    void givenSomething_whenDoingSomething_thenResult() throws Exception {
        assert(true);
    }
}
