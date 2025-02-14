package entities;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public class AddCandidateVO {

    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String vacancy;
    private String contactNumber;
    private String resumePath;
    private String keywords;
    private String notes;
    private boolean consentToKeepData;

    public AddCandidateVO() {
        Faker faker = new Faker();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.middleName = faker.name().nameWithMiddle();
        this.email = faker.internet().emailAddress();
        this.vacancy = faker.job().title();
        this.contactNumber = faker.phoneNumber().cellPhone();
        this.resumePath = new File("src/test/resources/images/sample.pdf").getAbsolutePath();
        this.keywords = faker.lorem().words(3).toString();
        this.notes = faker.lorem().sentence();
        this.consentToKeepData = faker.bool().bool();
    }
}

