[![Build Status](https://app.bitrise.io/app/b28cd307efbcea81/status.svg?token=ZxfGqim_mTN5EkfBgEGM-A&branch=master)](https://app.bitrise.io/app/b28cd307efbcea81)

# moviedb
An app to make it easier to save information of your loved movies


## Getting started
Before you try to run this project you should go to [OMDB API KEY](http://www.omdbapi.com/apikey.aspx) to generate you own key.
Place the key on your root local.properties file like omdbApiKey=${your_key}.
I´ve made a gradle plugin to secure the key using NDK and JNI calls of C++ code.
Because of that I dont have to add the key to github and I can avoid decompiling security leaks.
Check the plugin here: [gradle-secretkey-plugin](https://github.com/hernandazevedo/gradle-secretkey-plugin)

## Check the development steps on Trello
 https://trello.com/b/TNCSKgUT/moviedb

## Code Walkthrough

The code is organized using a Clean Architecture with MVVM architecture for the presentatio layer. The implementation was heavily
in the articles and projects of Fernando Cejas:
 * Article: https://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/
 * Project: https://github.com/android10/Android-CleanArchitecture
 
 ### Modules

A brief description:

  * `domain`: Business rules and interfaces on how to interact with services and repositories.
  * `presentation`: Logic that connects the business rules with the UI. Manipulation of data for
  view.
  * `data`: Implementations of services and repositories.
  
  
  ## Decisions
  
  ### Why MVVM?
  
  As alternative to MVP, MVVM takes advantage of the reactive programming to leverage the coupling between the presentation
  and the business layer. In that case the same viewModel could be used in many screens.
  Check this presentation I´ve made about it some time ago:
  [MVVM - Presentation](https://docs.google.com/presentation/d/1NspsJ7r8qn7x7RMFNGNiFSrDhX2qBV59w5fAAUxi_Fs/edit?usp=sharing)
  
  ### Do I like tests?
  
  Yes, thats why that architecture was chosen in the first place. But give the time I had to make the whole project, I´ve made one test for each layer(junit).
  
  ### And Instrumentation tests?
  Because every implementation is made using interfaces, its easy to create the inversion of control using dagger.
  I hope I have time enough to implement by creating a custom implementation of AndroidJUnitRunner that serves a mocked implementation of dagger.
