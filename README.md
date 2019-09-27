# SYM - Laboratoire 1

## Manipulations

### Les activités

La manipulation que nous vous proposons est très simple ; il s'agit de réaliser une application qui demande à l'utilisateur par l'intermédiaire de deux champs textuels : un e-mail et un mot de passe à valider à l’aide d’un bouton. Cette application n'est pas forcément pertinente du point de vue de la logique de l'interface utilisateur, mais elle est plutôt destinée à vous faire prendre contact avec certains points élémentaires de la logique des activités et de certains composants sur Android (spécialement la logique des layouts et des ressources). Pour vous aider à mettre en place l'application, vous pouvez utiliser le modèle de projet (template) fourni. Bien sûr, l'utilisation de ce modèle est optionnelle ; ceux qui le désirent peuvent en découdre avec les multiples options de développement de projet Android proposées par le SDK ; il se trouve simplement que dans le cadre de ce laboratoire un projet très basique nous suffira.

Si la syntaxe de l'e-mail paraît incorrecte (au minimum on vérifiera l’absence du signe "@"), on affichera un Toast (message temporaire au bas de l'écran) indiquant qu'il est nécessaire de mettre un email valide, et rester dans le même dialogue en conservant les entrées de l'utilisateur.

Si l'e-mail n'est pas connu de l'application (on aura codé en "dur" une série de couples email/mot de passe dans l'application), on affichera un dialogue (pop-up) à quittancer affichant le message "Utilisateur ou mot de passe inconnu", et sur quittance de l'utilisateur, on reviendra à l'application en effaçant préalablement les entrées précédentes de l'utilisateur. Pour la création du pop-up, utiliser un DialogBuilder qui permet de se passer du fichier XML de définition d'interfaces. Le message affiché dans le dialogue popup ne sera pas codé en dur, mais extrait du fichier XML res/values/strings.xml (ressources textuelles), de manière à ce que l'application puisse ultérieurement être traduite en plusieurs langues.

En cas de succès (les entrées correspondent à ce qui a été prévu), on lancera une activité séparée permettant d'afficher l'e-mail introduit précédemment, l'IMEI du terminal (pour l'émulateur, ce sera "000000..." ou même null sur les versions d’Android 8.02+) ainsi qu'une photographie, censée représenter l'utilisateur qui vient de réussir son login. L’image sera stockée sur la carte SD (utilisez par exemple : `Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);` pour accéder au dossier des téléchargements).

Info :  La commande suivante vous permettra de copier un fichier sur l’émulateur

```
adb push <file-source-local> <destination-path-remote>
adb push perso.jpg /sdcard/Download/perso.jpg
```

### Permissions, version simple

Afin de pouvoir lire le numéro IMEI du téléphone ou alors accéder au stockage externe, votre application nécessitera des permissions spécifiques à ajouter au fichier AndroidManifest.xml :

```
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

Ces deux permissions nécessitent depuis Android 6.0 (Api 23) une validation par l’utilisateur lors de l’exécution, c’est une procédure relativement complexe à mettre en place pour un débutant sur cette plateforme. Dans le cadre de ce premier laboratoire, pour éviter cette difficulté, nous vous conseillons d’aller dans le fichier build.gradle (module app) et de mettre la valeur 22 pour la targetSdkVersion. Nous forçons donc l’utilisation de l’ancien système de permissions pour lequel il suffisait de lister les permissions utilisées dans le manifest pour qu’elles soient automatiquement accordées. Cette manière de procéder ne peut plus être réalisée pour les applications distribuées sur Google Play à partir de fin 2018, celles-ci devront utiliser une version du SDK datant de moins d’une année

### Permissions version avancée (Question 9 facultative)

Une fois que vous aurez terminé ce laboratoire, nous vous proposons d’implémenter la demande et gestion des permissions au runtime, comme il faut dorénavant le faire depuis Android 6.0.

Documentation officielle : https://developer.android.com/training/permissions/requesting.html

Vous vérifierez votre solution sur un téléphone (ou émulateur) avec une version Android >= 6.0 et aussi avec une version plus ancienne.

Astuce : il existe certainement des librairies qui faciliteront grandement votre travail...

## Rendu / évaluation

Pour rendre votre code, nous vous demandons de bien vouloir zipper votre projet Android Studio, vous
veillerez à bien supprimer les dossiers build (cf. git clean -fdX) pour limiter la taille du rendu. En
plus, vous remettrez un document pdf comportant au minimum les réponses aux questions posées.
Merci de rendre votre travail sur CyberLearn dans un zip unique. N’oubliez pas d’indiquer vos noms
dans le code, avec vos réponses et de commenter vos solutions.
