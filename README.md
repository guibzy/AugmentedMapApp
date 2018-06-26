# AugmentedMapApp

Application Java qui rentre dans la mise en place d'un dispositif de carte en réalité augmentée.

À travers cette documentation, vous trouverez :
- le contexte de la réalisation de cette application
- Les outils nécessaires au déploiement d'un tel dispositif
- L'explication du cas d'utilisation fournit via ce dépôt
- Les parties de code à adapter pour personnaliser votre utilisation de cet outil
## Contexte

Cette application a été réalisée dans un projet de travaux personnels encadrés par des enseignants de l'[Université de Technologie de Troyes](https://www.utt.fr/). Ce projet consiste en la réalisation d'une Toolbox de réalité augmentée, c'est à dire fournir un ensemble d'outils documentés permettant de mettre en place des dispositifs de réalité augmentée. 

L'application ici présente incarne la première partie de cette Toolbox, offrant un dispositif de perception et d'action multi-utilisateurs. La seconde partie de la Toolbox est à trouver sur le [dépôt](https://github.com/guibzy/AugmentedMobileApp) concernant le dispositif de perception et d'action mono-utilisateur.  

## Outils nécessaires

- Un ordinateur exécutant l'application
- Une webcam connectée à l'ordinateur
- L'application [Reactivision](http://reactivision.sourceforge.net/#files)
- Des [marqueurs](https://github.com/mkalten/reacTIVision/blob/master/symbols/amoeba/default.pdf) Reactivision
- Un rétroprojecteur
- Une surface sur laquelle projeter


## Cas d'utilisation fourni
- Brancher webcam et rétroprojecteur (dans notre cas, ces derniers se trouvent à une distance d'environ 1m15 de la surface de projection)
- Lancer l'exécutable de reactivision
    - Calibrer la caméra, afin de déterminer la zone sur laquelle vous pourrez capter les marqueurs. Les dimensions de cette zone doivent en fait correspondre aux dimensions de la carte une fois projetée sur cette surface. Pour obtenir ces dimensions, vous pouvez par exemple exécuter l'application et prendre des repères physiques puis revenir à l'application Reactivision.
    
        Pour accéder au mode de calibration, il faut appuyer sur le bouton *c* du clavier lorsque vous êtes sur la fenêtre affichant le rendu de la webcam. Vous verrez alors apparaitre une grille de calibration. Les dimensions de cette grille doivent correspondre exactement aux dimensions de la surface physique de projection. Pour plus d'informations concernant la calibration, il faut se référer à la [documentation](https://github.com/mkalten/reacTIVision#calibration-and-distortion) de Reactivision.
    - Il est important d'avoir au moins 30 fps afin d'obtenir une fluidité dans la projection. Pour cela, vous pouvez éditer le fichier camera.xml. Ci-dessous, le contenu de ce fichier pour une caméra full HD
        
        `<?xml version="1.0" encoding="ISO-8859-1" ?>
            <portvideo>
                <camera id="1">
                    <capture width="1280" height="720" fps="60" compress="true" />
                    <settings brightness="default" contrast="min" gain="min" shutter="default" exposure="default" sharpness="default" gamma="min" focus="min" />
                    <frame width="max" height="max" xoff="0" yoff="0" />
                </camera>
            </portvideo>`
- Exécuter l'application Java (Si cas d'utilisation fourni, le JAR est ici : out/artifacts/AugmentedMapApp_jar/AugmentedMapApp.jar)
- Placer des marqueurs Reactivision pour faire apparaître vos informations augmentées. (Les marqueurs n°: 0,1,2,3 dans le cas d'application fourni, ou ceux que vous avez associés directement dans le code)
        

## Code à adapter

Si vous souhaitez adapter l'outils pour votre usage, vous trouverez dans cette partie toutes les informations nécessaires.
### Structure de l'application
- ressources - *Dossier contenant les fichiers de ressources permettant d'utiliser l'application*
    - images - *Dossier contenant les images que vous souhaitez projeter*
    - maps - *Dossier contenant les cartes (format image) que vous souhaitez utiliser*
- src - Dossier contenant les fichiers Java nécessaires au fonctionnement de l'application

### Adaptations nécessaires

#### Choix de la carte
Il est possible de choisir la carte servant de support à la projection. Il suffit qu'elle soit un fichier image (.png, .jpg...). Peu importe les dimensions de la carte, son affichage sera adaptée au dimensions de la fenêtre de l'application.

Pour cela, il faut modifier le contenu de la variable `nameMap` (ligne 24) de la fonction `main` du fichier `AugmentedMapApp.java`.

#### Choix des informations augmentées

Modifier le code de la fonction `fetchAugmentedInformationList` du fichier `AugmentedMapApp.java`. Ci-dessous, un exemple d'information augmentée à ajouter à la carte
<pre><code>
AugmentedInformationList.put(0,new AugmentedInformation("text","Eau",0));
AugmentedInformationList.put(1,new AugmentedInformation("image","Black.png",1));
</code></pre>


- Structure d'un ajout à la liste : `AugmentedInformationList.put(int identifiant_du_marqueur_reactivision, AugmentedInformation AugmentedInformationObject)`

- Création d'un objet AugmentedInformation :
`new AugmentedInformation(String type,String contenu,int identifiant_du_marqueur_reactivision, optionnel : double pourcentage)`

    type : "text" ou "image"

    contenu : Si type = "image", fournir le nom du fichier image qui doit être présent dans ressources/images

    pourcentage : paramètre **optionnel**, valeur entre 0 et 1, qui défini un pourcentage de dimension maximale concernant la taille du contenu par rapport à l'image. Exemple : 0.1 veut dire que l'information projetée ne prendra pas plus de 10% en hauteur/largeur de la carte'