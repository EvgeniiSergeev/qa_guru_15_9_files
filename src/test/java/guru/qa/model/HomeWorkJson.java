package guru.qa.model;

import guru.qa.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;


public class HomeWorkJson {

        String jsonFile = "person.json";

        @DisplayName("Json check value")
        @Test
        void jsonCheckValueTest() throws Exception {
            File file = new File("src/test/resources/"+ jsonFile);
            ObjectMapper objectMapper = new ObjectMapper();
            Person person = objectMapper.readValue(file, Person.class);
            assertThat(person.age).isEqualTo(33);
            assertThat(person.name).isEqualTo("Kolya");
            assertThat(person.surname).isEqualTo("Bokov");
            assertThat(person.isMale).isEqualTo(true);
            assertThat(person.address[0]).isEqualTo("derevnya Babushkino","Dedushkina oblast");
        }

}
