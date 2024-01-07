package pl.bookshop.gptservice.gpttemplates;

public class GPTNotificationsTemplates {

    private static final String WELCOME_MESSAGE_TEMPLATE =
        "Generate a welcome message for the user according to the following criteria:\n" +
                "1. Welcome message language: %s\n" +
                "2. User name: %s\n" +
                "3. User surname: %s\n" +
                "4. Thank him for joining our site\n" +
                "5. At the end of the message, add some cool fact about the books\n" +
                "6. Maintain the message format so that it displays nicely in the email\n" +
                "7. Format: JSON\n" +
                "8. 100%% completed, ready to parse JSON\n" +
                "9. Generate the text so that it displays clearly (IMPORTANT: use email formatting and spacing)\n" +
                "10. JSON Structure template:\n" +
                "{\n" +
                "    \"message\": \"xxxxxxx\"\n" +
                "}";

    public static String getTemplateForWelcomeMessageQuery(String messageLanguage, String userName, String userSurname) {
        return String.format(WELCOME_MESSAGE_TEMPLATE, messageLanguage, userName, userSurname);
    }

}
