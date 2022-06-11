# Modern-Dialog-Windows

![Language: Java](https://img.shields.io/badge/Language-Java-informational)
![Framework: JavaFX](https://img.shields.io/badge/Framework-JavaFX-informational)
![Version: 0.1.0](https://img.shields.io/badge/Version-0.1.0-red)
![Stable Release: 20th June 2022](https://img.shields.io/badge/Stable_Release-20th_June_2022-yellow)

> Modern-Dialog-Windows is a JavaFX library with modern-looking alerts. They are a user-friendly alternative to the
> normal JavaFX alerts.

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

## Alerts overview.

| Name                       | Version | Released |
|----------------------------|---------|----------|
| MDialogWindow (Superclass) | 0.1.0   | Yes      |
| MAlert                     | 0.2.0   | Yes      |
| MTextInputAlert            | 0.1.0   | Yes      |
| MImageAlert                | 0.1.0   | Yes      |
| MWelcomeAlert              | 0.1.0   | Yes      |

## MAlert Sneak Peek.

Let us create this warning:

![Example](images/example.png)

Code:
```java
    MAlert mAlert = new MAlert(MAlert.MAlertType.INFORMATION, "I like this.");
    mAlert.setMAlertStyle(MDialogWindow.MAlertStyle.DARK_ROUNDED); // This line is optional
    mAlert.setHeadline("A very informative Headline.");
    mAlert.setContentText("Looks modern and familiar. And it is very simple to use.");
    mAlert.addButton("My custom button", x -> System.out.println("Hello"), true);
    mAlert.getStage().show();
```

## More Screenshots.

![MTextInputAlert](images/mtia_example.png)
![MTextInputAlert](images/mwa_example.png)

## You want to see Modern-Dialog-Windows in Action?

Word Guesser uses Modern-Dialog-Windows (Version: 0.0.2): [GitHub Word Guesser](https://github.com/GregorGott/Word-Guesser)

Math Trainer uses Modern-Dialog-Windows (Version: 0.0.5): [GitHub Math Trainer](https://github.com/GregorGott/Math-Trainer)
