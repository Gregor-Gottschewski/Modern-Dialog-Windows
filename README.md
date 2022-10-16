# Modern-Dialog-Windows

![Version: 1.1.0](https://img.shields.io/badge/Version-1.1.0-red)
![Language: Java](https://img.shields.io/badge/Language-Java-informational)
![Framework: JavaFX](https://img.shields.io/badge/Framework-JavaFX-informational)
![JavaFX Version: 19](https://img.shields.io/badge/JavaFX_Version-19-green)

> Modern-Dialog-Windows is a JavaFX library with modern-looking alerts. They are an user-friendly alternative to the
> normal JavaFX alerts.

## Looks Awesome. Is Awesome To Use.

**Similar to JavaFX alerts, but with more adaptability.**

You can choose between two styles: rounded and classic and the color themes black and white.
Moder-Dialog-Windows does not use `ButtonsType` objects but normal `Button` object, which is way simpler
to handle.

**Based on Stages.**

The idea behind Modern-Dialog-Windows is that you can customize everything you want.
And to do that, every alert can return a Stage with `getStage()`. Example: If you want to change the dimensions
of the Stage, just add `mAlert.getStage().setHeight(200);` to your code.

**Huge range of alerts**

This library not only contains a basic alert, but a big collection of seven different alerts. If an alert
of your need is not in the library, just create one with the `MDialogWindow` class. Following is a
list of all alerts.

## Alerts Overview.

| Hierarchy             | Name                   | Where to use?                                                     |
|-----------------------|------------------------|-------------------------------------------------------------------|
| _1._ **Superclass**   | MDialogWindow          | To create own alerts.                                             |
| _2._ MDialogWindow    | MAlert                 | Basic information or error alert.                                 |
| _2._ MDialogWindow    | MTextInputAlert        | Ask for input e.g. ask for a document name.                       |
| _2._ MDialogWindow    | MImageAlert            | Show an image and give options e.g. share.                        |
| _2._ MDialogWindow    | MWebAlert              | Show a website or HTML file e.g. for a login.                     |
| _2._ MDialogWindow    | MScrollPaneAlert       | Lot of information in one alert.                                  |
| _3._ MScrollPaneAlert | MWelcomeAlert          | Show text with an image in a scroll pane e.g. "Whats New?" Screen |
| _3._ MScrollPaneAlert | MMultiInformationAlert | Accordion in an scroll pane with a lot of space e.g. About Screen |

## What's New?

**Release 1.1.0 (Same, same but different)**

> ⚠️ Before you update: Please read the full changelog, because a few method names changed, however,
> no functionality was removed.

The new release contains a new alert called `MWebAlert` which can show websites or HTML files in your
alert. This allows you to create log-in alerts or other HTML, CSS and JavaScript based alerts.
The backend also changed a lot, however, nothing much changed for you, just use this version like any other before.

## Download

Read the [official documentation](https://github.com/GregorGott/Modern-Dialog-Windows/wiki) for more help.

### JavaFX 19 Downloads

| Version | Download                                                                                                               | Current |
|---------|------------------------------------------------------------------------------------------------------------------------|---------|
| 1.1.0   | [Download](https://github.com/GregorGott/Modern-Dialog-Windows/releases/download/v1.1.0/MDialogWindows-1.1.0_FX19.jar) | Yes     |


### JavaFX 18 Downloads

| Version | Download                                                                                                               | Current |
|---------|------------------------------------------------------------------------------------------------------------------------|---------|
| 1.1.0   | [Download](https://github.com/GregorGott/Modern-Dialog-Windows/releases/download/v1.1.0/MDialogWindows-1.1.0_FX18.jar) | Yes     |
| 1.0.0   | [Download](https://github.com/GregorGott/Modern-Dialog-Windows/releases/download/v1.0.0/MDialogWindows-1.0.0.jar)      | No      |

## MAlert Sneak Peek.

Let's create a basic MAlert which could be displayed when a file creation fails:

```java
MAlert mAlert = new MAlert(MAlert.MAlertType.ERROR, "Error"); // error alert with title
mAlert.setHeadline("Error while creating a new file."); // set the headliner
mAlert.setSecondHeadline("Check if you run this application as superuser and try it again."); // set the content text (wrap text is enabled)
mAlert.setInfoText("Error (23) - Contact our customer support if you need further help: +12 345 67890"); // adds some extra information
mAlert.addButton("Try again", event -> System.out.println("I need HEEELP"), true); // adds a button with a text and an ActionEvent (in this case printing something to the console)
mAlert.show(); // shows the stage
```

![Example](images/example.png)

You can do the following if you want to make some stage changes, such as change the
alert size or set the resizeable feature to `true`:

````java
mAlert.getStage().setHeight(100);
mAlert.getStage().setWidth(100);
mAlert.getStage().setResizable(true);
````

## More Screenshots.

![MTextInputAlert](images/mta_example.png)
![MTextInputAlert](images/mwa_example.png)

## You want to see Modern-Dialog-Windows in Action?

Here are a few projects who are using Modern-Dialog-Windows. Append this list when your project
uses Modern-Dialog-Windows!

Word Guesser uses Modern-Dialog-Windows (Version:
0.1.1): [Word Guesser on GitHub](https://github.com/GregorGott/Word-Guesser)
