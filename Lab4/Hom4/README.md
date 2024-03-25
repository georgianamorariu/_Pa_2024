Homework 4


    Crearea unei clase care descrie problema:

Am creat clasa CarpoolProblem pentru a reprezenta problema. Aceasta clasa contine o lista de soferi si un set de pasageri, initializati utilizand lista de persoane furnizata. Am adaugat metode pentru a calcula destinatiile prin care trec soferii si pentru a genera o harta a destinatiilor si persoanelor care doresc să ajunga acolo.

    Utilizarea unei biblioteci third-party pentru a genera nume false ale persoanelor si destinatiilor:

Am adaugat dependenta către biblioteca "faker" în fișierul pom.xml. Am modificat metoda createRandomPeople() pentru a folosi biblioteca "faker" pentru a genera nume false ale persoanelor si destinatii aleatoare.

    Utilizarea Java Stream API pentru a calcula destinațiile și a hărții destinațiilor și persoanelor:

Am folosit Java Stream API pentru a filtra persoanele in soferi si pasageri. Am calculat destinatiile prin care trec soferii folosind Stream API. Am creat o harta a destinatiilor si persoanelor care doresc sa ajunga acolo, grupand persoanele dupa destinatie.

    Crearea unui algoritm greedy pentru a rezolva problema:

Am implementat un algoritm greedy in metoda matchDriversAndPassengers() din clasa CarpoolProblem. Algoritmul incearca sa asocieze fiecare sofer cu cel mai apropiat pasager in varsta sau cu aceeasi destinatie. Dupa fiecare asociere, pasagerul este eliminat din setul de pasageri pentru a evita o asociere multipla.

