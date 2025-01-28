package valueObjects;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public class AddUserVO {

    private String firstName;
    private String middleName;
    private String lastName;
    private String employeeId;
    private File profilePicture;

    public AddUserVO() {
        Faker faker = new Faker();
        this.firstName = faker.name().firstName();
        this.middleName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.profilePicture = new File("src/test/resources/images/profile.jpg");
    }
}
