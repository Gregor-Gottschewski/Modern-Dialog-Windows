# Modern-Dialog-Windows

![Language: Java](https://img.shields.io/badge/Language-Java-informational)
![Framework: JavaFX](https://img.shields.io/badge/Framework-JavaFX-informational)
![Version: 0.0.1](https://img.shields.io/badge/Version-0.0.1-red)
![Stable Release: 08.05.2022](https://img.shields.io/badge/Stable_Release-8th_June_2022-yellow)

>Modern-Dialog-Windows are JavaFX libraries with modern-looking alerts. They are a user-friendly alternative to the normal JavaFX alerts.

## Looks Awesome. Is Awesome To Use.
**Similar to JavaFX alerts, but with more adaptability.**

You can choose between
two styles: rounded and classic and two colour themes: dark and white.
To make the button handling easier, Modern-Dialog-Windows uses normal buttons you
can create with custom text and a custom ActionEvent.

## Based on Stages.
The idea behind Modern-Dialog-Windows is that you can customize everything you want.
And to do that, Modern-Dialog-Windows returns a Stage on which you can change everything
(e.g. you can apply your custom stylesheets).

## Alerts overview table
| Name            | Version | Released               |
| --------------- | ------- | ---------------------- |
| MAlert          | 0.0.1   | Yes                    |
| MTextInputAlert | -       | No (Planned for 0.0.2) |
| MImageAlert     | -       | No                     |

## Important Beta Informations.
> Modern-Dialog-Windows is currently a beta.

> MAlert is one Alert, more will be added during the beta phase.

Currently, all Modern-Dialog-Windows are saved in separate folders. That is only
because the program is still in beta. The final release will be bundled
in one library.

## Small example of MAlert
Let us create this warning:

![Example](images/example.png)

Code:

    MAlert mAlert = new MAlert(MAlertType.INFORMATION);
    mAlert.setAlertStyle(MAlert.MAlertStyle.DARK_ROUNDED);
    mAlert.setAlertTitle("I like this.");
    mAlert.setHeadline("A very informative headline.");
    mAlert.setContentText("Looks modern and familiar. And it is very simple to use.");
    mAlert.addButton("My custom button", x -> System.out.println("Hello"), true);

Optional to show the alert:

    Stage stage = mAlert.getStage();
    stage.show();

## You want to see Modern-Dialog-Windows in action?
Word Guesser uses MAlerts: [GitHub Word Guesser](https://github.com/GregorGott/Word-Guesser)
