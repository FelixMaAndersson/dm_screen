package se.yrgo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.yrgo.exceptions.CampaignNotFoundException;
import se.yrgo.exceptions.UserNotFoundException;
import se.yrgo.exceptions.CharacterNotFoundException;
import se.yrgo.services.campaign.CampaignService;
import se.yrgo.services.playerCharacter.PlayerCharacterService;
import se.yrgo.services.user.UserService;


import java.util.Scanner;


@Component
public class Menu {

    private final Scanner input = new Scanner(System.in);
    private final CampaignService campaignService;
    private final UserService userService;
    private final PlayerCharacterService characterService;


    @Autowired
    public Menu(CampaignService campaignService,
                UserService userService,
                PlayerCharacterService characterService) {
        this.campaignService = campaignService;
        this.userService = userService;
        this.characterService = characterService;
    }


    /**
     * Prints the application header to the console.
     */
    public void header() {
        System.out.println("-------------------------------------------");
        System.out.println("                DMG SCREEN                 ");
        System.out.println("-------------------------------------------");
        System.out.println();
    }

    /**
     * Prints the main menu options to the console.
     */
    public void startMenu() {
        System.out.println("[1] CREATE");
        System.out.println("[2] VIEW");
        System.out.println("[3] EDIT");
        System.out.println("[0] EXIT");
        System.out.print("Your choice: ");
    }

    /**
     * Starts the main application loop.
     * Listens for user input and navigates to the appropriate menu.
     *
     * @throws CampaignNotFoundException if a campaign operation fails
     * @throws CharacterNotFoundException   if a character operation fails
     * @throws UserNotFoundException if a user operation fails
     */
    public void start() throws CampaignNotFoundException, CharacterNotFoundException, UserNotFoundException {


        while (true) {

            header();
            startMenu();
            String choice = input.nextLine();

            switch (choice) {
//                case "1" -> createMenu();
//                case "2" -> viewMenu();
//                case "3" -> editMenu();
//                case "4" -> joinLeague();
//                case "0" -> System.exit(0);
//                default -> System.out.println("You dumb puck, wrong choice, try again!");
            }
            System.out.println();
        }
    }



}
