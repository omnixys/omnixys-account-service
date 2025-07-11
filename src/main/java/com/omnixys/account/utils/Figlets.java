package com.omnixys.account.util;

import java.util.Random;

public class Figlets {

  private static final String IVRIT = """
     _____                               _   _               ____   ___ ____  _  _    ___   ___   _____ _\s
    |_   _| __ __ _ _ __  ___  __ _  ___| |_(_) ___  _ __   |___ \\ / _ \\___ \\| || |  / _ \\ ( _ ) |___ // |
      | || '__/ _` | '_ \\/ __|/ _` |/ __| __| |/ _ \\| '_ \\    __) | | | |__) | || |_| | | |/ _ \\   |_ \\| |
      | || | | (_| | | | \\__ \\ (_| | (__| |_| | (_) | | | |  / __/| |_| / __/|__   _| |_| | (_) | ___) | |
      |_||_|  \\__,_|_| |_|___/\\__,_|\\___|\\__|_|\\___/|_| |_| |_____|\\___/_____|  |_|(_)___/ \\___(_)____/|_|
                                                                                                         \s""";

  private static final String KBAN = """
    |''||''|                                                   .    ||                                        ,  .        /\\\\  . ____     \s
       ||    ... ..   ....   .. ...    ....   ....     ....  .||.  ...    ...   .. ...       /\\   /\\\\   /\\   /|     /\\\\  || ||   ` //  /| \s
       ||     ||' '' '' .||   ||  ||  ||. '  '' .||  .|   ''  ||    ||  .|  '|.  ||  ||     (  ) || || (  ) / |    || ||  \\ /     //  /|| \s
       ||     ||     .|' ||   ||  ||  . '|.. .|' ||  ||       ||    ||  ||   ||  ||  ||       // || ||   // __|_   || ||  /\\\\     \\\\   || \s
      .||.   .||.    '|..'|' .||. ||. |'..|' '|..'|'  '|...'  '|.' .||.  '|..|' .||. ||.     //  || ||  //  ----   || || // \\\\     ))  || \s
                                                                                            /(   || || /(     |    || || || ||    //   || \s
                                                                                            {___  \\\\/  {___  '-'    \\\\/   \\\\/    /'   ,/-'\s""";

  private static final String SPEED = """
    ________                                        __________                   ________________________ __  ______________  _____________
    ___  __/____________ ____________________ ________  /___(_)____________      __|__ \\_  __ \\_|__ \\_  // /  __  __ \\_( __ ) __|__  /_<  /
    __  /  __  ___/  __ `/_  __ \\_  ___/  __ `/  ___/  __/_  /_  __ \\_  __ \\     ____/ /  / / /___/ /  // /_  _  / / /  __  | ___/_ <__  /\s
    _  /   _  /   / /_/ /_  / / /(__  )/ /_/ // /__ / /_ _  / / /_/ /  / / /     _  __// /_/ /_  __//__  __/__/ /_/ // /_/ /______/ /_  / \s
    /_/    /_/    \\__,_/ /_/ /_//____/ \\__,_/ \\___/ \\__/ /_/  \\____//_/ /_/      /____/\\____/ /____/  /_/  _(_)____/ \\____/_(_)____/ /_/  \s
                                                                                                                                          \s
    """;

  private static final String STAMPATE = """
    ,--,--'                      .              ,--. ,--. ,--.   ,     ,--. ,--.    ,--.   , \s
    `- | ,-. ,-. ,-. ,-. ,-. ,-. |- . ,-. ,-.      / |  |    /  /|     |  | \\__/     __|  /| \s
     , | |   ,-| | | `-. ,-| |   |  | | | | |   ,-'  |  | ,-'  '-+- ,. |  | /  \\ ,.    | ' | \s
     `-' '   `-^ ' ' `-' `-^ `-' `' ' `-' ' '   `--- `--' `---   `  `' `--' `--' `' `--'  -^-\s
                                                                                             \s
                                                                                             \s
    """;

  private static final String SMALL_KEYBOARD = """
     ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ _________ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____\s
    ||T |||r |||a |||n |||s |||a |||c |||t |||i |||o |||n |||       |||2 |||0 |||2 |||4 |||. |||0 |||8 |||. |||3 |||1 ||
    ||__|||__|||__|||__|||__|||__|||__|||__|||__|||__|||__|||_______|||__|||__|||__|||__|||__|||__|||__|||__|||__|||__||
    |/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|/_______\\|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|
    """;

  private  static  final  String SMALL_CAPS = """
     _____  ____     _    _  _    ___     _    ___  _____   ___   ____   _  _     ___   ___   ___   _  _       ___   ___       ___    _ \s
    )__ __(/  _ \\   )_\\  ) \\/ (  (  _(   )_\\  / _( )__ __( )_ _( / __ \\ ) \\/ (   /__ ( / _ \\ /__ ( ) () (     / _ \\ / _ \\     (__ \\ /_ (\s
      | |  )  ' /  /( )\\ |  \\ |  _) \\   /( )\\ ))_    | |   _| |_ ))__(( |  \\ |    ( /  ))_((  ( /   \\_  |  _  ))_(( ) _ (  _   (_ |  ) |\s
      )_(  |_()_\\ )_/ \\_()_()_( )____) )_/ \\_(\\__(   )_(  )_____(\\____/ )_()_(   /___\\ \\___/ /___\\    )_( (_) \\___/ \\___/ (_) (___/ /__(\s
                                                                                                                                        \s
    """;

  private static final String GHOST = """
     .-') _   _  .-')     ('-.         .-') _   .-')     ('-.               .-') _                            .-') _                                                                                      \s
    (  OO) ) ( \\( -O )   ( OO ).-.    ( OO ) ) ( OO ).  ( OO ).-.          (  OO) )                          ( OO ) )                                                                                     \s
    /     '._ ,------.   / . --. /,--./ ,--,' (_)---\\_) / . --. /   .-----./     '._ ,-.-')  .-'),-----. ,--./ ,--,'        .-----.   .----.   .-----.     .---.      .----.    .-----.     .-----.  .---.\s
    |'--...__)|   /`. '  | \\-.  \\ |   \\ |  |\\ /    _ |  | \\-.  \\   '  .--./|'--...__)|  |OO)( OO'  .-.  '|   \\ |  |\\       / ,-.   \\ /  ..  \\ / ,-.   \\   / .  |     /  ..  \\  /  .-.  \\   /  -.   \\/_   |\s
    '--.  .--'|  /  | |.-'-'  |  ||    \\|  | )\\  :` `..-'-'  |  |  |  |('-.'--.  .--'|  |  \\/   |  | |  ||    \\|  | )      '-'  |  |.  /  \\  .'-'  |  |  / /|  |    .  /  \\  .|   \\_.' /   '-' _'  | |   |\s
       |  |   |  |_.' | \\| |_.'  ||  .     |/  '..`''.)\\| |_.'  | /_) |OO  )  |  |   |  |(_/\\_) |  |\\|  ||  .     |/          .'  / |  |  '  |   .'  /  / / |  |_   |  |  '  | /  .-. '.      |_  <  |   |\s
       |  |   |  .  '.'  |  .-.  ||  |\\    |  .-._)   \\ |  .-.  | ||  |`-'|   |  |  ,|  |_.'  \\ |  | |  ||  |\\    |         .'  /__ '  \\  /  ' .'  /__ /  '-'    |  '  \\  /  '|  |   |  |  .-.  |  | |   |\s
       |  |   |  |\\  \\   |  | |  ||  | \\   |  \\       / |  | |  |(_'  '--'\\   |  | (_|  |      `'  '-'  '|  | \\   |        |       | \\  `'  / |       |`----|  |-'.-.\\  `'  /  \\  '-'  /.-.\\ `-'   / |   |\s
       `--'   `--' '--'  `--' `--'`--'  `--'   `-----'  `--' `--'   `-----'   `--'   `--'        `-----' `--'  `--'        `-------'  `---''  `-------'     `--'  `-' `---''    `----'' `-' `----''  `---'\s""";

  private static final Random RANDOM = new Random();

  public String randomFigletGenerator() {
    // Generates a random number between 0 and 4 (inclusive)
    int choice = RANDOM.nextInt(7);
    // Selects the corresponding Figlet drawing based on the random number
    switch (choice) {
      case 0:
        return IVRIT;
      case 1:
        return KBAN;
      case 2:
        return SPEED;
      case 3:
        return STAMPATE;
      case 4:
        return GHOST;
      case 5:
        return SMALL_KEYBOARD;
     case 6:
        return SMALL_CAPS;
      default:
        throw new IllegalStateException("Unexpected value: " + choice);
    }
  }
}
