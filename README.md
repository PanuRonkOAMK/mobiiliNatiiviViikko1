# Viikkotehtävä 1: “Domain + Kotlin-harjoitukset + ensimmäinen Compose-näkymä”

## Datamalli:

Sovelluksen datamalli on Task-data class joka on domain-kansiossa

Jokaisessa Task:issa täytyy olla id, title eli nimi, description eli kuvaus, priority eli tärkeys, dueData eli määräaika ja done eli valmistumistila

## Funktiot:

addTask(list: List&lt;Task&gt;, task: Task): List&lt;Task&gt;

Lisää listan perään uuden taskin

toggleDone(list: List&lt;Task&gt;, id: Int): List&lt;Task&gt;

Listan taskin Done tila käännetään True --> False tai toisinpäin

filterByDone(list: List&lt;Task&gt;, done: Boolean): List&lt;Task&gt;

Näytetään vain listan taskit joiden Done tila on joko True tai False

sortByDueDate(list: List&lt;Task&gt;): List&lt;Task&gt;

Sortataan listan taskin niiden määräajan mukaan

## Compose-tilanhallinta

Käyttöliittymä ei muokkaa itseään suoraan, vaan se kuvaillaan tilan (state) perusteella.
Kun Compose-näkymässä käytetään State-tyyppistä muuttujaa (esim. mutableStateOf), Compose seuraa sitä automaattisesti.
Kun tila muuttuu, Compose piirtää vain ne osat käyttöliittymästä uudelleen, jotka riippuvat kyseisestä tilasta.

## Viewmodel

remember-funktiota voidaan käyttää Composable-funktioissa tilan tallentamiseen, mutta sillä on rajoituksia:
* remember-tila katoaa, kun näkymä poistuu muistista
* remember-tila ei säily laitteen kiertämisessä (konfiguraatiomuutos)
* UI ja liiketoimintalogiikka sekoittuvat helposti keskenään

ViewModel ratkaisee nämä ongelmat:
* ViewModel säilyy konfiguraatiomuutoksissa (esim. näytön kääntö)
* ViewModel erottaa liiketoimintalogiikan käyttöliittymästä
* ViewModel on testattavampi kuin UI:ssa oleva tila
* Useat Composable-näkymät voivat käyttää samaa ViewModelia

## MVVM

*MVVM kategorisoi koodin, tehden siitä paremmin ymmärrettävä ja helpommin ylläpidettävä.

## StateFlow

*StateFlow on tilanhallintamekanismi. Se säilyttää aina sovelluksen ajantasaista tilaa ja lähetää uuden arvon kun tila muuttuu. Tämä on hyödyllistä käyttöliittymien luonnissa
