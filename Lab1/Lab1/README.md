Compulsory 1

    Cerinta
        Write a Java application that implements the following  operations:
        1.Display on the screen the message "Hello World!". Run the application. If it works, go to step 2 :)
        2.Define an array of strings languages, containing {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"}
        3.Generate a random integer n: int n = (int) (Math.random() * 1_000_000);
        4.Compute the result obtained after performing the following calculations:
            multiply n by 3;
            add the binary number 10101 to the result;
            add the hexadecimal number FF to the result;
            multiply the result by 6;
        Compute the sum of the digits in the result obtained in the previous step. This is the new result. While the new result has more than one digit, continue to sum the digits of the result.
        5.Display on the screen the message: "Willy-nilly, this semester I will learn " + languages[result].

Pentru rezolvarea cerintelor am utilizat:

    CERINTA 1
Am afisat "Hello World!" folosind `System.out.println("Hello World!")

    CERINTA 2
Am definit un array de limbaje de programare folosind `String[] languages'

    CERINTA 3
Am generat un numar random utiliand Math.random()

    CERINTA 4
Se realizeaza operatiile cerute pe numarul generat anterior si se ccalculeaza suma cifrelor intr-o bucla

    CERINTA 5
Am afisat mesajul final folosind System.out.println("Willy-nilly, this semester I will learn " + languages[result])