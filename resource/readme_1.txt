Music Player++ Documentation & User Manual

By Jack Meng

Licensed under the EPL 2.0 License

!!! Documentation Version 1.0 !!!

> Pre-face <
GitHub is the main area for users to 
contribute to the project. (Submit
issues and PRs)

> Introduction <

This is a simple Music Player made using Java and C++
to be used to simply play music for now.

Although it is not a complete music player, it sure 
will be improved upon in the near future!

You might have noticed in the source code, there is a
package of source code called "Telemetry."

> Telemetry <
========================
The only part of the program that will use the internet
is when you open the settings window. The latest
release versions are fetched from the API to get the 
latest information on the application. 

The telemetry package in the source code is used to
track and log certain actions within the program.
Either for the program to self debug itself or to
collect any stack dumps!

========================
> Usage <
========================
This Documentation is made for the version of 
Music Player++: 
Release: 1.1
Beta: 1.2
Patch: 1.2.1

Please be sure to select the right version of the
documentations!
========================

This program currently fully supports the following
media file methods:
*.wav

Semi-Supported Media File Methods (no volume control):
*.mp3

Does not support Media File Methods:
*.flac
*.ogg
*.aac

For future versions, the settings window will show
everything needed about the supported features and 
file type.

>> Windows <<

- Splash Screen:
This is the first screen that is displayed, but 
can be disabled in future versions.

- Main Menu:
This is the window that shows right after the 
splash screen. It features the following buttons:
1. Select File
  a. Opens a window for you to select a file to
     play
2. GitHub Repository
  a. Will open the GitHub repository location in 
     your default web browser
3. License
  a. Will open the license window
4. Settings
  a. Will open the settings window
5. Documentation
  a. Opens a window for you to read a specific
     version of the license

- Select File:
Here you can either type in a path to the file 
manually or you can also click "Open Explorer"
button to select a file from the inbuilt File
Explorer.

- Player:
Here you can pause or play the media file
and also adjust the volume of the Media
being played.
  a. Please note that .mp3 file formats
     will not have volume support

- Settings: 
Here you can adjust the settings.
[INCOMPLETE] 
For now this window relies on an API
that will fetch the latest data regarding 
the development of the program and also
tell you other specific details on the program
regarding your specific build of your program.
The usage of the API is done through by 
accessing the REST API, but will require the 
internet

- License:
Read the license (EPL-2.0)

- Documentation:
Open a window with the most current 
documentation bundled with your version
of the Music Player

[ END OF DOCUMENTATIONS ]
