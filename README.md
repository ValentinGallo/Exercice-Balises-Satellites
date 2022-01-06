# Exercice Balises / Satellites
Valentin Gallo | Pierre Le Brun (Master TIIL-A)

## Correction du bug :

Le bug  

## Ameliorations réalisées :

- ### Les balises collectaient trop de données

Nous avons remarqué que les balises récupéraient des données à chaque
tick même quand elle occupé à remonter à la surface (donc 350 dataSize /300 memorySize était possibles)
```java
pred void readSensors() {
        if(isCollectingData) this.dataSize++;otect
}
```
_Corrigé en vérifiant si la balise collecte des données_

- ### Ajout d'un indicateur de progression sur les balises

![balise](images/balise.png)

_Permet d'afficher le pourcentage de données collectées par une balise_

- ### Ajout d'un compteur

![compteur](images/compteur.png)

_Affiche le nombre total de données récupéré par les satellites_
- 4
- 5

## Diagramme UML :
A regénérer !
![UML](images/UML.png)