package se.yrgo.client;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.exceptions.CampaignNotFoundException;
import se.yrgo.exceptions.UserNotFoundException;
import se.yrgo.exceptions.CharacterNotFoundException;


public class SimpleClient {
    public static void main(String[] args) {

        try (ClassPathXmlApplicationContext container =
                     new ClassPathXmlApplicationContext("application.xml")) {

            Menu menu = container.getBean(Menu.class);
            menu.start();
        } catch (CampaignNotFoundException | CharacterNotFoundException | UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}