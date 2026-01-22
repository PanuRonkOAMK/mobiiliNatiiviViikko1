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
