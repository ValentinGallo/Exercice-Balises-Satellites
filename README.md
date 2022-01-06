# Exercice Balises / Satellites
Valentin Gallo | Pierre Le Brun (Master TIIL-A)

## Correction du bug :

Le bug  

## Ameliorations réalisées :

- ### Les balises collectaient trop de données

Nous avons remarqué que les balises récupéraient des données à chaque
tick même quand elle occupé à remonter à la surface (donc 350 dataSize /300 memorySize était possibles)
![ReadSensor()](images/Fix_ReadSensors.png)

- ### Ajout d'un indicateur de progression sur les balises

Permet d'afficher le pourcentage de données collectées par une balise :
![balise](images/balise.png)

- 3
- 4
- 5

## Diagramme UML :
A regénérer !
![UML](images/UML.png)